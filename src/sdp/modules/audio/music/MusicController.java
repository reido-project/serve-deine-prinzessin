package sdp.modules.audio.music;

import sdp.modules.assets.Asset;
import sdp.modules.audio.Effects;

public final class MusicController {

    private static final int MAX_CHANNELS = 5;
    private static final int DEFAULT_CHANNELS = 2;

    private final MusicChannel[] channels;

    public MusicController() {
        channels = new MusicChannel[MAX_CHANNELS];
        registerChannels(DEFAULT_CHANNELS);
    }

    // =========================================================
    // Channel registration
    // =========================================================

    private void registerChannels(int totalChannels) {
        validateChannelCount(totalChannels);

        for (int i = 0; i < totalChannels; i++) {
            channels[i] = new MusicChannel();
        }
    }

    private void validateChannelCount(int totalChannels) {
        if (totalChannels > MAX_CHANNELS) {
            throw new IllegalArgumentException(
                String.format("Total music channels exceeds maximum (%d) allowed.", MAX_CHANNELS)
            );
        }

        if (totalChannels < 1) {
            throw new IllegalArgumentException("There must be at least one music channel.");
        }
    }

    // =========================================================
    // Channel validation
    // =========================================================

    private MusicChannel getChannel(int channel) {
        checkChannelNumber(channel);

        MusicChannel target = channels[channel - 1];

        if (target == null) {
            throw new IllegalArgumentException("Music channel " + channel + " is not registered.");
        }

        return target;
    }

    private void checkChannelNumber(int channel) {
        if (channel < 1 || channel > MAX_CHANNELS) {
            throw new IllegalArgumentException("Invalid channel: " + channel);
        }
    }

    // =========================================================
    // Playback
    // =========================================================

    public void play(Asset music) {
        play(1, music);
    }

    public void play(Asset music, Effects effect, long duration) {
        play(1, music, effect, duration);
    }

    public void play(int channel, Asset music) {
        getChannel(channel).play(music);
    }

    public void play(int channel, Asset music, Effects effect, long duration) {
        MusicChannel target = getChannel(channel);

        if (effect == Effects.FADE) {
            target.playFade(music, duration);
        } else {
            target.play(music);
        }
    }

    // =========================================================
    // Stop
    // =========================================================

    public void stop() {
        stop(1);
    }

    public void stop(Effects effect, long duration) {
        stop(1, effect, duration);
    }

    public void stop(int channel) {
        getChannel(channel).stop();
    }

    public void stop(int channel, Effects effect, long duration) {
        MusicChannel target = getChannel(channel);

        if (effect == Effects.FADE) {
            target.stopFade(duration);
        } else {
            target.stop();
        }
    }

    // =========================================================
    // Stop all
    // =========================================================

    public void stopAll() {
        for (MusicChannel channel : channels) {
            if (channel != null) {
                channel.stop();
            }
        }
    }

    public void stopAll(Effects effect, long duration) {
        for (MusicChannel channel : channels) {
            if (channel != null) {
                if (effect == Effects.FADE) {
                    channel.stopFade(duration);
                } else {
                    channel.stop();
                }
            }
        }
    }

    // =========================================================
    // Cleanup
    // =========================================================

    public void dispose() {
        for (MusicChannel channel : channels) {
            if (channel != null) {
                channel.dispose();
            }
        }
    }
}