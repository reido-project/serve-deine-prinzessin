package sdp.modules.audio.music;

import sdp.modules.assets.Asset;
import sdp.modules.audio.AudioChannel;

import javax.sound.sampled.Clip;

public class MusicChannel extends AudioChannel {

    @Override
    public void play(String directory) {
        closeCurrentClip();

        clip = loadClip(directory);

        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public void play(Asset music) {
        play(music.getDirectory());
    }

    @Override
    public void playFade(String directory, long duration) {
        closeCurrentClip();

        clip = loadClip(directory);

        clip.loop(Clip.LOOP_CONTINUOUSLY);

        fadeIn(duration);
    }

    public void playFade(Asset music, long duration) {
        playFade(music.getDirectory(), duration);
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