package sdp.modules.audio;

import sdp.modules.assets.Asset;
import sdp.modules.assets.AssetCategory;
import sdp.persistence.DataController;
import sdp.persistence.runtime.data.PresentationData;

import static sdp.modules.assets.AssetCategory.*;

public interface AudioAPI {

    // Music
    default void playMusic(Asset music) { if (checkMusicState(music)) return; AudioController.playMusic(music); saveState(MUSIC, music); }
    default void playMusic(Asset music, Effects effect, long duration) { if (checkMusicState(music)) return; AudioController.playMusic(music, effect, duration); saveState(MUSIC, music); }
    default void playMusic(int channel, Asset music) { if (checkMusicState(music)) return; AudioController.playMusic(channel, music); saveState(MUSIC, music); }
    default void playMusic(int channel, Asset music, Effects effect, long duration) { if (checkMusicState(music)) return; AudioController.playMusic(channel, music, effect, duration); saveState(MUSIC, music); }

    default void stopMusic() { AudioController.stopMusic(); saveState(MUSIC, null); }
    default void stopMusic(Effects effect, long duration) { AudioController.stopMusic(effect, duration); saveState(MUSIC, null); }
    default void stopMusic(int channel) { AudioController.stopMusic(channel); saveState(MUSIC, null); }
    default void stopMusic(int channel, Effects effect, long duration) { AudioController.stopMusic(channel, effect, duration); saveState(MUSIC, null); }

    default void stopAllMusic() { AudioController.stopAllMusic(); saveState(MUSIC, null); }
    default void stopAllMusic(Effects effect, long duration) { AudioController.stopAllMusic(effect, duration); saveState(MUSIC, null); }

    // SFX
    default void playSfx(Asset sfx) { AudioController.playSfx(sfx); }
    default void playSfx(Asset sfx, Effects effect, long duration) { AudioController.playSfx(sfx, effect, duration); }
    default void playSfx(int channel, Asset sfx) { AudioController.playSfx(channel, sfx); }
    default void playSfx(int channel, Asset sfx, Effects effect, long duration) { AudioController.playSfx(channel, sfx, effect, duration); }

    default void stopSfx() { AudioController.stopSfx(); }
    default void stopSfx(Effects effect, long duration) { AudioController.stopSfx(effect, duration); }
    default void stopSfx(int channel) { AudioController.stopSfx(channel); }
    default void stopSfx(int channel, Effects effect, long duration) { AudioController.stopSfx(channel, effect, duration); }

    default void stopAllSfx() { AudioController.stopAllSfx(); }
    default void stopAllSfx(Effects effect, long duration) { AudioController.stopAllSfx(effect, duration); }

    // Voice
    default void playVoice(Asset voice) { AudioController.playVoice(voice); saveState(VOICE, voice); }
    default void playVoice(Asset voice, Effects effect, long duration) { AudioController.playVoice(voice, effect, duration); saveState(VOICE, voice); }
    default void playVoice(int channel, Asset voice) { AudioController.playVoice(channel, voice); saveState(VOICE, voice); }
    default void playVoice(int channel, Asset voice, Effects effect, long duration) { AudioController.playVoice(channel, voice, effect, duration); saveState(VOICE, voice); }

    default void stopVoice() { AudioController.stopVoice(); saveState(VOICE, null); }
    default void stopVoice(Effects effect, long duration) { AudioController.stopVoice(effect, duration); saveState(VOICE, null); }
    default void stopVoice(int channel) { AudioController.stopVoice(channel); saveState(VOICE, null); }
    default void stopVoice(int channel, Effects effect, long duration) { AudioController.stopVoice(channel, effect, duration); saveState(VOICE, null); }

    default void stopAllVoice() { AudioController.stopAllVoice(); saveState(VOICE, null);}
    default void stopAllVoice(Effects effect, long duration) { AudioController.stopAllVoice(effect, duration); saveState(VOICE, null); }

    // Global
    default void stopAll() { AudioController.stopAll(); saveState(MUSIC, null); saveState(VOICE, null); }
    default void stopAll(Effects effect, long duration) { AudioController.stopAll(effect, duration); saveState(MUSIC, null); saveState(VOICE, null); }

    // Special Permission
    private boolean checkMusicState(Asset audio) {
        return audio == DataController.getInstance().getPresentationData().getCurrentMusic();
    }

    private void saveState(AssetCategory category, Asset asset) {
        PresentationData presentation = DataController.getInstance().getPresentationData();
        switch (category) {
            case MUSIC -> presentation.setCurrentMusic(asset);
            case VOICE -> presentation.setCurrentVoice(asset);
            default -> throw new IllegalArgumentException("Category " + category + " not allowed");
        }
    }
}