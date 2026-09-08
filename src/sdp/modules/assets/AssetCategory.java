package sdp.modules.assets;

public enum AssetCategory {
    BACKGROUND,
    MUSIC,
    SFX,
    VOICE,
    SPRITE;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}