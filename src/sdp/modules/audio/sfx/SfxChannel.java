package sdp.modules.audio.sfx;

import sdp.modules.assets.Asset;
import sdp.modules.audio.AudioChannel;

public class SfxChannel extends AudioChannel {

    @Override
    public void play(String directory) {
        closeCurrentClip();

        clip = loadClip(directory);

        clip.setFramePosition(0);
        clip.start();
    }

    public void play(Asset sfx) {
        play(sfx.getDirectory());
    }

    @Override
    public void playFade(String directory, long duration) {
        closeCurrentClip();

        clip = loadClip(directory);

        fadeIn(duration);
    }

    public void playFade(Asset sfx, long duration) {
        playFade(sfx.getDirectory(), duration);
    }

    @Override
    public void stop() {
        closeCurrentClip();
    }

    @Override
    public void stopFade(long duration) {
        fadeOut(duration);
    }
}