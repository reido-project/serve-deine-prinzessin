package sdp.modules.audio.voice;

import sdp.modules.assets.Asset;
import sdp.modules.audio.Effects;

public final class VoiceController {

    private static final int MAX_CHANNELS = 5;
    private static final int DEFAULT_CHANNELS = 2;

    private final VoiceChannel[] channels;

    public VoiceController() {
        channels = new VoiceChannel[MAX_CHANNELS];
        registerChannels(DEFAULT_CHANNELS);
    }

    // =========================================================
    // Channel registration
    // =========================================================

    private void registerChannels(int totalChannels) {
        validateChannelCount(totalChannels);

        for (int i = 0; i < totalChannels; i++) {
            channels[i] = new VoiceChannel();
        }
    }

    private void validateChannelCount(int totalChannels) {
        if (totalChannels > MAX_CHANNELS) {
            throw new IllegalArgumentException(
                String.format("Total voice channels exceeds maximum (%d) allowed.", MAX_CHANNELS)
            );
        }

        if (totalChannels < 1) {
            throw new IllegalArgumentException("There must be at least one voice channel.");
        }
    }

    // =========================================================
    // Channel validation
    // =========================================================

    private VoiceChannel getChannel(int channel) {
        checkChannelNumber(channel);

        VoiceChannel target = channels[channel - 1];

        if (target == null) {
            throw new IllegalArgumentException("Voice channel " + channel + " is not registered.");
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

    public void play(Asset voice) {
        play(1, voice);
    }

    public void play(Asset voice, Effects effect, long duration) {
        play(1, voice, effect, duration);
    }

    public void play(int channel, Asset voice) {
        getChannel(channel).play(voice);
    }

    public void play(int channel, Asset voice, Effects effect, long duration) {
        VoiceChannel target = getChannel(channel);

        if (effect == Effects.FADE) {
            target.playFade(voice, duration);
        } else {
            target.play(voice);
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
        VoiceChannel target = getChannel(channel);

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
        for (VoiceChannel channel : channels) {
            if (channel != null) {
                channel.stop();
            }
        }
    }

    public void stopAll(Effects effect, long duration) {
        for (VoiceChannel channel : channels) {
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
        for (VoiceChannel channel : channels) {
            if (channel != null) {
                channel.dispose();
            }
        }
    }
}