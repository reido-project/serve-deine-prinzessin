package sdp.modules.audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class AudioChannel {

    protected Clip clip;

    private final ScheduledExecutorService fadeExecutor = Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture<?> fadeTask;

    // =========================================================
    // Playback
    // =========================================================

    public abstract void play(String directory);

    public abstract void playFade(String directory, long duration);

    public abstract void stop();

    public abstract void stopFade(long duration);

    // =========================================================
    // Audio loading
    // =========================================================

    protected Clip loadClip(String directory) {
        return VorbisAudioLoader.loadClip(getClass(), directory);
    }

    // =========================================================
    // Clip management
    // =========================================================

    protected void closeCurrentClip() {
        cancelFade();

        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    // =========================================================
    // Volume
    // =========================================================

    protected FloatControl getVolumeControl() {
        if (clip == null) {
            throw new IllegalStateException("Cannot access volume control without an active clip.");
        }

        if (!clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            throw new UnsupportedOperationException("This audio clip does not support MASTER_GAIN control.");
        }

        return (FloatControl) clip.getControl(
            FloatControl.Type.MASTER_GAIN
        );
    }

    public void setVolume(float volume) {
        FloatControl control = getVolumeControl();

        float clampedVolume = Math.clamp(volume, 0.0f, 1.0f);

        control.setValue(linearToDecibel(clampedVolume, control.getMinimum()));
    }

    // =========================================================
    // Fade
    // =========================================================

    protected void fadeIn(long duration) {
        if (clip == null) {
            return;
        }

        cancelFade();

        FloatControl control = getVolumeControl();

        float minimum = control.getMinimum();
        float maximum = control.getMaximum();

        control.setValue(minimum);

        clip.setFramePosition(0);
        clip.start();

        fadeVolume(
            control,
            minimum,
            maximum,
            duration,
            false
        );
    }

    protected void fadeOut(long duration) {
        if (clip == null) {
            return;
        }

        cancelFade();

        FloatControl control = getVolumeControl();

        float current = control.getValue();
        float minimum = control.getMinimum();

        fadeVolume(
            control,
            current,
            minimum,
            duration,
            true
        );
    }

    private void fadeVolume(FloatControl control, float from, float to, long duration, boolean stopAfterFade) {
        if (duration <= 0) {
            control.setValue(to);

            if (stopAfterFade) {
                closeCurrentClip();
            }

            return;
        }

        final long startTime = System.nanoTime();
        final long interval = 10L;

        fadeTask = fadeExecutor.scheduleAtFixedRate(() -> {

            if (clip == null) {
                cancelFade();
                return;
            }

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);

            float progress =Math.min(1.0f, (float) elapsed / duration);

            float value = from + ((to - from) * progress);

            try {
                control.setValue(value);
            } catch (IllegalArgumentException ignored) {
                cancelFade();
                return;
            }

            if (progress >= 1.0f) {
                cancelFade();

                if (stopAfterFade && clip != null) {
                    closeCurrentClip();
                }
            }

        }, 0L, interval, TimeUnit.MILLISECONDS);
    }

    // =========================================================
    // Fade management
    // =========================================================

    private void cancelFade() {
        if (fadeTask != null) {
            fadeTask.cancel(false);
            fadeTask = null;
        }
    }

    // =========================================================
    // Utility
    // =========================================================

    private float linearToDecibel(float volume, float minimum) {
        if (volume <= 0.0f) {
            return minimum;
        }

        float decibel = (float) (20.0 * Math.log10(volume));

        return Math.max(minimum, decibel);
    }

    // =========================================================
    // Cleanup
    // =========================================================

    public void dispose() {
        closeCurrentClip();
        fadeExecutor.shutdownNow();
    }
}