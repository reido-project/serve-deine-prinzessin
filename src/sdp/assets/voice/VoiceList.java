package sdp.assets.voice;

public enum VoiceList {
    NUB("/sdp/assets/audio/voice/nub.ogg");

    private final String directory;

    VoiceList(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }
}
