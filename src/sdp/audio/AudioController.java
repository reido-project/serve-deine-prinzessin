package sdp.audio;

import sdp.assets.music.MusicList;
import sdp.assets.sfx.SfxList;
import sdp.assets.voice.VoiceList;
import sdp.audio.music.MusicChannel;
import sdp.audio.sfx.SfxChannel;
import sdp.audio.voice.VoiceChannel;

public class AudioController {

    private static final int MAX_MUSIC_CHANNELS = 5;
    private static final int MAX_SFX_CHANNELS = 5;
    private static final int MAX_VOICE_CHANNELS = 5;

    private final MusicChannel[] musicChannels;
    private final SfxChannel[] sfxChannels;
    private final VoiceChannel[] voiceChannels;

    private static AudioController instance;

    private AudioController() {
        musicChannels = new MusicChannel[MAX_MUSIC_CHANNELS];
        sfxChannels = new SfxChannel[MAX_SFX_CHANNELS];
        voiceChannels = new VoiceChannel[MAX_VOICE_CHANNELS];

        registerMusicChannel(2);
        registerSfxChannel(3);
        registerVoiceChannel(2);
    }

    private static class Holder {
        private static final AudioController INSTANCE = new AudioController();
    }

    public static AudioController getInstance() {
        return Holder.INSTANCE;
    }

    // =========================================================
    // Channel registration
    // =========================================================

    private void registerMusicChannel(int totalChannels) {
        validateChannelCount(totalChannels, MAX_MUSIC_CHANNELS, "music");

        for (int i = 0; i < totalChannels; i++) {
            musicChannels[i] = new MusicChannel();
        }
    }

    private void registerSfxChannel(int totalChannels) {
        validateChannelCount(totalChannels, MAX_SFX_CHANNELS, "SFX");

        for (int i = 0; i < totalChannels; i++) {
            sfxChannels[i] = new SfxChannel();
        }
    }

    private void registerVoiceChannel(int totalChannels) {
        validateChannelCount(totalChannels, MAX_VOICE_CHANNELS, "voice");

        for (int i = 0; i < totalChannels; i++) {
            voiceChannels[i] = new VoiceChannel();
        }
    }

    private void validateChannelCount(int totalChannels, int maximum, String channelType) {
        if (totalChannels > maximum) {
            throw new IllegalArgumentException(String.format("Total %s channels exceeds maximum (%d) allowed.", channelType, maximum));
        }

        if (totalChannels < 1) {
            throw new IllegalArgumentException(String.format("There must be at least one %s channel.", channelType));
        }
    }

    // =========================================================
    // Channel validation
    // =========================================================

    private void checkMusicChannel(int channel) {
        checkChannelNumber(channel, musicChannels.length);

        if (musicChannels[channel - 1] == null) {
            throw new IllegalArgumentException("Music channel " + channel + " is not registered.");
        }
    }

    private void checkSfxChannel(int channel) {
        checkChannelNumber(channel, sfxChannels.length);

        if (sfxChannels[channel - 1] == null) {
            throw new IllegalArgumentException("SFX channel " + channel + " is not registered.");
        }
    }

    private void checkVoiceChannel(int channel) {
        checkChannelNumber(channel, voiceChannels.length);

        if (voiceChannels[channel - 1] == null) {
            throw new IllegalArgumentException("Voice channel " + channel + " is not registered.");
        }
    }

    private void checkChannelNumber(int channel, int maximum) {
        if (channel < 1 || channel > maximum) {
            throw new IllegalArgumentException("Invalid channel: " + channel);
        }
    }

    // =========================================================
    // Music
    // =========================================================

    public void playMusic(MusicList music) {
        playMusic(1, music);
    }

    public void playMusic(MusicList music,Effects effect, long duration) {
        playMusic(1, music, effect, duration);
    }

    public void playMusic(int channel, MusicList music) {
        checkMusicChannel(channel);

        musicChannels[channel - 1].play(music.getDirectory());
    }

    public void playMusic(int channel, MusicList music, Effects effect, long duration) {
        checkMusicChannel(channel);

        MusicChannel target = musicChannels[channel - 1];

        if (effect == Effects.FADE) {
            target.playFade(music.getDirectory(), duration);
        } else {
            target.play(music.getDirectory());
        }
    }

    public void stopMusic() {
        stopMusic(1);
    }

    public void stopMusic(Effects effect, long duration) {
        stopMusic(1, effect, duration);
    }

    public void stopMusic(int channel) {
        checkMusicChannel(channel);

        musicChannels[channel - 1].stop();
    }

    public void stopMusic(int channel, Effects effect, long duration) {
        checkMusicChannel(channel);

        MusicChannel target = musicChannels[channel - 1];

        if (effect == Effects.FADE) {
            target.stopFade(duration);
        } else {
            target.stop();
        }
    }

    public void stopAllMusic() {
        for (MusicChannel channel : musicChannels) {
            if (channel != null) {
                channel.stop();
            }
        }
    }

    public void stopAllMusic(Effects effect, long duration) {
        for (MusicChannel channel : musicChannels) {
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
    // SFX
    // =========================================================

    public void playSfx(SfxList sfx) {
        playSfx(1, sfx);
    }

    public void playSfx(SfxList sfx, Effects effect, long duration) {
        playSfx(1, sfx, effect, duration);
    }

    public void playSfx(int channel, SfxList sfx) {
        checkSfxChannel(channel);

        sfxChannels[channel - 1].play(sfx.getDirectory());
    }

    public void playSfx(int channel, SfxList sfx, Effects effect, long duration) {
        checkSfxChannel(channel);

        SfxChannel target = sfxChannels[channel - 1];

        if (effect == Effects.FADE) {
            target.playFade(sfx.getDirectory(), duration);
        } else {
            target.play(sfx.getDirectory());
        }
    }

    public void stopSfx() {
        stopSfx(1);
    }

    public void stopSfx(Effects effect, long duration) {
        stopSfx(1, effect, duration);
    }

    public void stopSfx(int channel) {
        checkSfxChannel(channel);

        sfxChannels[channel - 1].stop();
    }

    public void stopSfx(int channel, Effects effect, long duration) {
        checkSfxChannel(channel);

        SfxChannel target = sfxChannels[channel - 1];

        if (effect == Effects.FADE) {
            target.stopFade(duration);
        } else {
            target.stop();
        }
    }

    public void stopAllSfx() {
        for (SfxChannel channel : sfxChannels) {
            if (channel != null) {
                channel.stop();
            }
        }
    }

    public void stopAllSfx(Effects effect, long duration) {
        for (SfxChannel channel : sfxChannels) {
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
    // Voice
    // =========================================================

    public void playVoice(VoiceList voice) {
        playVoice(1, voice);
    }

    public void playVoice(VoiceList voice, Effects effect, long duration) {
        playVoice(1, voice, effect, duration);
    }

    public void playVoice(int channel, VoiceList voice) {
        checkVoiceChannel(channel);

        voiceChannels[channel - 1].play(voice.getDirectory());
    }

    public void playVoice(int channel, VoiceList voice, Effects effect, long duration) {
        checkVoiceChannel(channel);

        VoiceChannel target = voiceChannels[channel - 1];

        if (effect == Effects.FADE) {
            target.playFade(voice.getDirectory(), duration);
        } else {
            target.play(voice.getDirectory());
        }
    }

    public void stopVoice() {
        stopVoice(1);
    }

    public void stopVoice(Effects effect, long duration) {
        stopVoice(1, effect, duration);
    }

    public void stopVoice(int channel) {
        checkVoiceChannel(channel);

        voiceChannels[channel - 1].stop();
    }

    public void stopVoice(int channel, Effects effect, long duration) {
        checkVoiceChannel(channel);

        VoiceChannel target = voiceChannels[channel - 1];

        if (effect == Effects.FADE) {
            target.stopFade(duration);
        } else {
            target.stop();
        }
    }

    public void stopAllVoice() {
        for (VoiceChannel channel : voiceChannels) {
            if (channel != null) {
                channel.stop();
            }
        }
    }

    public void stopAllVoice(Effects effect, long duration) {
        for (VoiceChannel channel : voiceChannels) {
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
    // Global controls
    // =========================================================

    public void stopAll() {
        stopAllMusic();
        stopAllSfx();
        stopAllVoice();
    }

    public void stopAll(Effects effect, long duration) {
        stopAllMusic(effect, duration);
        stopAllSfx(effect, duration);
        stopAllVoice(effect, duration);
    }

    // =========================================================
    // Cleanup
    // =========================================================

    public void dispose() {
        for (MusicChannel channel : musicChannels) {
            if (channel != null) {
                channel.dispose();
            }
        }

        for (SfxChannel channel : sfxChannels) {
            if (channel != null) {
                channel.dispose();
            }
        }

        for (VoiceChannel channel : voiceChannels) {
            if (channel != null) {
                channel.dispose();
            }
        }
    }
}