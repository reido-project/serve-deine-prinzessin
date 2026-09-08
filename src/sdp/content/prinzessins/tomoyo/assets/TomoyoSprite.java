package sdp.content.prinzessins.tomoyo.assets;

import sdp.modules.assets.Asset;

public enum TomoyoSprite implements Asset {
    Neutral;

    @Override
    public String getFileName() {
        return this.name() + ".png";
    }
}
