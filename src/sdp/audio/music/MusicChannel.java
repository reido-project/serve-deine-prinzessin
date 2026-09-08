package sdp.audio.music;

import sdp.assets.music.MusicList;
import sdp.audio.AudioChannel;

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

    public void play(MusicList music) {
        play(music.getDirectory());
    }

    @Override
    public void playFade(
        String directory,
        long duration
    ) {
        closeCurrentClip();

        clip = loadClip(directory);

        clip.loop(Clip.LOOP_CONTINUOUSLY);

        fadeIn(duration);
    }

    public void playFade(
        MusicList music,
        long duration
    ) {
        playFade(
            music.getDirectory(),
            duration
        );
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