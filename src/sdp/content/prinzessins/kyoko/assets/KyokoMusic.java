package sdp.content.prinzessins.kyoko.assets;

import sdp.modules.assets.Asset;

public enum KyokoMusic implements Asset {
    Beautiful_Death("Beautiful Death.ogg"),
    Box_15("Box 15.ogg"),
    Death_Wish("Death Wish.ogg"),
    Distrust("Distrust.ogg"),
    Goodbye_Highschool("Goodbye Despair High School.ogg"),
    Welcome_To_Despair("Welcome to Despair.ogg"),;

    private final String fileName;

    KyokoMusic(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
