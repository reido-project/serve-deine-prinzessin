package sdp.content.prinzessins.kazuha.assets;

import sdp.modules.assets.Asset;

public enum KazuhaSprite implements Asset {
    // N
    Neutral;

    @Override
    public String getFileName() {
        return this.name() + ".png";
    }
}
