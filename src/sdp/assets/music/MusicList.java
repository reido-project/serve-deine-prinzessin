package sdp.assets.music;

public enum MusicList {
    SECOND_HEARTBEAT("/sdp/assets/music/Second Heartbeat.ogg"),
    BEAUTIFUL_DEATH("/sdp/assets/music/Beautiful Death.ogg"),
    BOX_15("/sdp/assets/music/Box 15.ogg"),
    DEATH_WISH("/sdp/assets/music/Death Wish.ogg"),
    DISTRUST("/sdp/assets/music/Distrust.ogg"),
    GOODBYE_DESPAIR("/sdp/assets/music/Goodbye Despair High School.ogg"),
    WELCOME_TO_DESPAIR("/sdp/assets/music/Welcome to Despair.ogg"),

    // Kota Jahat
    BINATANG_DAN_PELACUR("/sdp/assets/music/Kota Jahat/Binatang dan Pelacur.ogg"),
    BAKAR_ITU("/sdp/assets/music/Kota Jahat/Bakar Itu.ogg"),
    DIBUTAKAN_RANTAI("/sdp/assets/music/Kota Jahat/Dibutakan Rantai.ogg"),
    NEGARA_KELELAWAR("/sdp/assets/music/Kota Jahat/Negara Kelelawar.ogg"),
    DIBUANG_BERSERAKAN("/sdp/assets/music/Kota Jahat/Dibuang dan Berserakan.ogg"),
    SITA_HARI_INI("/sdp/assets/music/Kota Jahat/Sita Hari Ini.ogg"),
    ULAR_BELUDAK_GURUN("/sdp/assets/music/Kota Jahat/Ular Beludak Gurun.ogg"),
    AKHIR_YANG_BIADAB("/sdp/assets/music/Kota Jahat/Akhir Yang Biadab.ogg"),
    KEKUATAN_DUNIA("/sdp/assets/music/Kota Jahat/Kekuatan Dunia.ogg"),
    DIKHIANATI("/sdp/assets/music/Kota Jahat/Dikhianati.ogg"),
    HDA("/sdp/assets/music/Kota Jahat/Hilang Dalam Aksi.ogg");

    private final String directory;

    MusicList(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }
}