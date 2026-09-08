package sdp.modules.assets.game.prinzessinSelect;

import sdp.modules.assets.Asset;

public enum PrinzessinSelectBackground implements Asset {
    PRINZESSIN_SELECT("PrinzessinSelect.png");

    private final String fileName;

    PrinzessinSelectBackground(String fileName){
        this.fileName = fileName;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
