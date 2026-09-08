package sdp.modules.assets.game.home;

import sdp.modules.assets.Asset;

public enum HomeScreenBackground implements Asset {
    HOME("Home.png");

    private final String fileName;

    HomeScreenBackground(String name){
        this.fileName = name;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
