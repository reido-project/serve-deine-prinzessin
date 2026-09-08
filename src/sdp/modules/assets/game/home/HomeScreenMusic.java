package sdp.modules.assets.game.home;

import sdp.modules.assets.Asset;

public enum HomeScreenMusic implements Asset {
    SECOND_HEARTBEAT("Second Heartbeat.ogg");

    private final String fileName;

    HomeScreenMusic(String fileName){
        this.fileName = fileName;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
