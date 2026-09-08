package sdp.assets.sfx;

public enum SfxList {
    NUB("/sdp/assets/audio/sfx/nub.ogg");

    private final String directory;

    SfxList(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }
}
