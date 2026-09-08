package sdp.modules.audio.sfx;

import sdp.modules.assets.Asset;
import sdp.modules.audio.Effects;

public final class SfxController {

    private static final int MAX_CHANNELS = 5;
    private static final int DEFAULT_CHANNELS = 3;

    private final SfxChannel[] channels;

    public SfxController() {
        channels = new SfxChannel[MAX_CHANNELS];
        registerChannels(DEFAULT_CHANNELS);
    }

    // =========================================================
    // Channel registration
    // =========================================================

    private void registerChannels(int totalChannels) {
        validateChannelCount(totalChannels);

        for (int i = 0; i < totalChannels; i++) {
            channels[i] = new SfxChannel();
        }
    }

    private void validateChannelCount(int totalChannels) {
        if (totalChannels > MAX_CHANNELS) {
            throw new IllegalArgumentException(
                String.format("Total SFX channels exceeds maximum (%d) allowed.", MAX_CHANNELS)
            );
        }

        if (totalChannels < 1) {
            throw new IllegalArgumentException("There must be at least one SFX channel.");
        }
    }

    // =========================================================
    // Channel validation
    // =========================================================

    private SfxChannel getChannel(int channel) {
        checkChannelNumber(channel);

        SfxChannel target = channels[channel - 1];

        if (target == null) {
            throw new IllegalArgumentException("SFX channel " + channel + " is not registered.");
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

    public void play(Asset sfx) {
        play(1, sfx);
    }

    public void play(Asset sfx, Effects effect, long duration) {
        play(1, sfx, effect, duration);
    }

    public void play(int channel, Asset sfx) {
        getChannel(channel).play(sfx);
    }

    public void play(int channel, Asset sfx, Effects effect, long duration) {
        SfxChannel target = getChannel(channel);

        if (effect == Effects.FADE) {
            target.playFade(sfx, duration);
        } else {
            target.play(sfx);
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
        SfxChannel target = getChannel(channel);

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
        for (SfxChannel channel : channels) {
            if (channel != null) {
                channel.stop();
            }
        }
    }

    public void stopAll(Effects effect, long duration) {
        for (SfxChannel channel : channels) {
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
        for (SfxChannel channel : channels) {
            if (channel != null) {
                channel.dispose();
            }
        }
    }
}