package sdp.modules.audio;

import sdp.modules.assets.Asset;
import sdp.modules.assets.AssetCategory;
import sdp.modules.audio.music.MusicController;
import sdp.modules.audio.sfx.SfxController;
import sdp.modules.audio.voice.VoiceController;

public final class AudioController {

    private static final MusicController MUSIC = new MusicController();
    private static final SfxController SFX = new SfxController();
    private static final VoiceController VOICE = new VoiceController();

    private AudioController() {
    }

    private static class Holder {
        @SuppressWarnings("InstantiationOfUtilityClass")
        private static final AudioController INSTANCE = new AudioController();
    }

    public static AudioController getInstance() {
        return Holder.INSTANCE;
    }

    // =========================================================
    // Validation Helpers
    // =========================================================

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isValidMusic(Asset asset) {
        return asset != null && asset.getCategory() == AssetCategory.MUSIC;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isValidSfx(Asset asset) {
        return asset != null && asset.getCategory() == AssetCategory.SFX;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean isValidVoice(Asset asset) {
        return asset != null && asset.getCategory() == AssetCategory.VOICE;
    }

    // =========================================================
    // Music
    // =========================================================

    public static void playMusic(Asset music) {
        if (!isValidMusic(music)) return;
        MUSIC.play(music);
    }

    public static void playMusic(Asset music, Effects effect, long duration) {
        if (!isValidMusic(music)) return;
        MUSIC.play(music, effect, duration);
    }

    public static void playMusic(int channel,Asset music) {
        if (!isValidMusic(music)) return;
        MUSIC.play(channel, music);
    }

    public static void playMusic(int channel, Asset music, Effects effect, long duration) {
        if (!isValidMusic(music)) return;
        MUSIC.play(channel, music, effect, duration);
    }

    public static void stopMusic() {
        MUSIC.stop();
    }

    public static void stopMusic(Effects effect, long duration) {
        MUSIC.stop(effect, duration);
    }

    public static void stopMusic(int channel) {
        MUSIC.stop(channel);
    }

    public static void stopMusic(int channel, Effects effect, long duration) {
        MUSIC.stop(channel, effect, duration);
    }

    public static void stopAllMusic() {
        MUSIC.stopAll();
    }

    public static void stopAllMusic(Effects effect, long duration) {
        MUSIC.stopAll(effect, duration);
    }

    // =========================================================
    // SFX
    // =========================================================

    public static void playSfx(Asset sfx) {
        if (!isValidSfx(sfx)) return;
        SFX.play(sfx);
    }

    public static void playSfx(Asset sfx, Effects effect, long duration) {
        if (!isValidSfx(sfx)) return;
        SFX.play(sfx, effect, duration);
    }

    public static void playSfx(int channel, Asset sfx) {
        if (!isValidSfx(sfx)) return;
        SFX.play(channel, sfx);
    }

    public static void playSfx(int channel, Asset sfx, Effects effect, long duration) {
        if (!isValidSfx(sfx)) return;
        SFX.play(channel, sfx, effect, duration);
    }

    public static void stopSfx() {
        SFX.stop();
    }

    public static void stopSfx(Effects effect, long duration) {
        SFX.stop(effect, duration);
    }

    public static void stopSfx(int channel) {
        SFX.stop(channel);
    }

    public static void stopSfx(int channel, Effects effect, long duration) {
        SFX.stop(channel, effect, duration);
    }

    public static void stopAllSfx() {
        SFX.stopAll();
    }

    public static void stopAllSfx(Effects effect, long duration) {
        SFX.stopAll(effect, duration);
    }

    // =========================================================
    // Voice
    // =========================================================

    public static void playVoice(Asset voice) {
        if (!isValidVoice(voice)) return;
        VOICE.play(voice);
    }

    public static void playVoice(Asset voice, Effects effect, long duration) {
        if (!isValidVoice(voice)) return;
        VOICE.play(voice, effect, duration);
    }

    public static void playVoice(int channel, Asset voice) {
        if (!isValidVoice(voice)) return;
        VOICE.play(channel, voice);
    }

    public static void playVoice(int channel, Asset voice, Effects effect, long duration) {
        if (!isValidVoice(voice)) return;
        VOICE.play(channel, voice, effect, duration);
    }

    public static void stopVoice() {
        VOICE.stop();
    }

    public static void stopVoice(Effects effect, long duration) {
        VOICE.stop(effect, duration);
    }

    public static void stopVoice(int channel) {
        VOICE.stop(channel);
    }

    public static void stopVoice(int channel, Effects effect, long duration) {
        VOICE.stop(channel, effect, duration);
    }

    public static void stopAllVoice() {
        VOICE.stopAll();
    }

    public static void stopAllVoice(Effects effect, long duration) {
        VOICE.stopAll(effect, duration);
    }

    // =========================================================
    // Global controls
    // =========================================================

    public static void stopAll() {
        MUSIC.stopAll();
        SFX.stopAll();
        VOICE.stopAll();
    }

    public static void stopAll(Effects effect, long duration) {
        MUSIC.stopAll(effect, duration);
        SFX.stopAll(effect, duration);
        VOICE.stopAll(effect, duration);
    }

    // =========================================================
    // Cleanup
    // =========================================================

    public static void dispose() {
        MUSIC.dispose();
        SFX.dispose();
        VOICE.dispose();
    }
}