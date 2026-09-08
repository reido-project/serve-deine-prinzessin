package sdp.dialogues;

import sdp.assets.music.MusicList;
import sdp.assets.sfx.SfxList;
import sdp.assets.sprite.SpriteState;
import sdp.assets.voice.VoiceList;
import sdp.audio.AudioController;
import sdp.audio.Effects;
import sdp.characters.Character;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

public abstract class DialogueInterface {
    protected final DataController dataController;
    protected final AudioController audio;
    protected final RuntimeData data;

    protected final Character prinzessin;
    protected final String y;
    protected final String p;
    protected final String n = "";

    public DialogueInterface() {
        this.dataController = DataController.getInstance();
        this.audio = AudioController.getInstance();

        this.data = dataController.getRuntimeData();
        this.prinzessin = data.getPrinzessin();
        this.y = data.getPlayerName();
        this.p = prinzessin.getNickname();
    }

    // =========================================================
    // Music Delegation
    // =========================================================

    protected void playMusic(MusicList music) { audio.playMusic(music); }
    protected void playMusic(MusicList music, Effects effect, long duration) { audio.playMusic(music, effect, duration); }
    protected void playMusic(int channel, MusicList music) { audio.playMusic(channel, music); }
    protected void playMusic(int channel, MusicList music, Effects effect, long duration) { audio.playMusic(channel, music, effect, duration); }

    protected void stopMusic() { audio.stopMusic(); }
    protected void stopMusic(Effects effect, long duration) { audio.stopMusic(effect, duration); }
    protected void stopMusic(int channel) { audio.stopMusic(channel); }
    protected void stopMusic(int channel, Effects effect, long duration) { audio.stopMusic(channel, effect, duration); }

    protected void stopAllMusic() { audio.stopAllMusic(); }
    protected void stopAllMusic(Effects effect, long duration) { audio.stopAllMusic(effect, duration); }

    // =========================================================
    // SFX Delegation
    // =========================================================

    protected void playSfx(SfxList sfx) { audio.playSfx(sfx); }
    protected void playSfx(SfxList sfx, Effects effect, long duration) { audio.playSfx(sfx, effect, duration); }
    protected void playSfx(int channel, SfxList sfx) { audio.playSfx(channel, sfx); }
    protected void playSfx(int channel, SfxList sfx, Effects effect, long duration) { audio.playSfx(channel, sfx, effect, duration); }

    protected void stopSfx() { audio.stopSfx(); }
    protected void stopSfx(Effects effect, long duration) { audio.stopSfx(effect, duration); }
    protected void stopSfx(int channel) { audio.stopSfx(channel); }
    protected void stopSfx(int channel, Effects effect, long duration) { audio.stopSfx(channel, effect, duration); }

    protected void stopAllSfx() { audio.stopAllSfx(); }
    protected void stopAllSfx(Effects effect, long duration) { audio.stopAllSfx(effect, duration); }

    // =========================================================
    // Voice Delegation
    // =========================================================

    protected void playVoice(VoiceList voice) { audio.playVoice(voice); }
    protected void playVoice(VoiceList voice, Effects effect, long duration) { audio.playVoice(voice, effect, duration); }
    protected void playVoice(int channel, VoiceList voice) { audio.playVoice(channel, voice); }
    protected void playVoice(int channel, VoiceList voice, Effects effect, long duration) { audio.playVoice(channel, voice, effect, duration); }

    protected void stopVoice() { audio.stopVoice(); }
    protected void stopVoice(Effects effect, long duration) { audio.stopVoice(effect, duration); }
    protected void stopVoice(int channel) { audio.stopVoice(channel); }
    protected void stopVoice(int channel, Effects effect, long duration) { audio.stopVoice(channel, effect, duration); }

    protected void stopAllVoice() { audio.stopAllVoice(); }
    protected void stopAllVoice(Effects effect, long duration) { audio.stopAllVoice(effect, duration); }

    // =========================================================
    // Global Control Delegation
    // =========================================================

    protected void stopAll() { audio.stopAll(); }
    protected void stopAll(Effects effect, long duration) { audio.stopAll(effect, duration); }

    // =========================================================
    // Dialogue Builders
    // =========================================================

    protected Dialogue d(String speaker, String line) {
        return new Dialogue(speaker, line, null, null);
    }

    protected Dialogue d(String speaker, String line, SpriteState sprite) {
        return new Dialogue(speaker, line, sprite, null);
    }

    protected Dialogue d(String speaker, String line, SpriteState sprite, Runnable action) {
        return new Dialogue(speaker, line, sprite, action);
    }

    protected Dialogue d(String speaker, String line, Runnable action) {
        return new Dialogue(speaker, line, null, action);
    }
}