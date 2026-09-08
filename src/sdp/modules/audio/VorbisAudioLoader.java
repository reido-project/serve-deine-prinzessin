package sdp.modules.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public final class VorbisAudioLoader {

    private VorbisAudioLoader() {}

    public static Clip loadClip(Class<?> owner, String resourcePath) {
        URL resource = owner.getResource(resourcePath);

        if (resource == null) {
            throw new IllegalArgumentException("Audio resource not found: " + resourcePath);
        }

        try (AudioInputStream encoded = AudioSystem.getAudioInputStream(resource)) {

            AudioFormat sourceFormat = encoded.getFormat();

            AudioFormat pcmFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                sourceFormat.getSampleRate(),
                16,
                sourceFormat.getChannels(),
                sourceFormat.getChannels() * 2,
                sourceFormat.getSampleRate(),
                false
            );

            try (AudioInputStream pcm = AudioSystem.getAudioInputStream(pcmFormat, encoded)) {
                Clip clip = AudioSystem.getClip();
                clip.open(pcm);

                return clip;
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("Failed to load audio: " + resourcePath, e);
        }
    }
}