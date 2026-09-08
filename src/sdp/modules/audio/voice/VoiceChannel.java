package sdp.modules.audio.voice;

import sdp.modules.assets.Asset;
import sdp.modules.audio.AudioChannel;

public class VoiceChannel extends AudioChannel {

    @Override
    public void play(String directory) {
        closeCurrentClip();

        clip = loadClip(directory);

        clip.setFramePosition(0);
        clip.start();
    }

    public void play(Asset voice) {
        play(voice.getDirectory());
    }

    @Override
    public void playFade(String directory, long duration) {
        closeCurrentClip();

        clip = loadClip(directory);

        fadeIn(duration);
    }

    public void playFade(Asset voice, long duration) {
        playFade(voice.getDirectory(), duration);
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