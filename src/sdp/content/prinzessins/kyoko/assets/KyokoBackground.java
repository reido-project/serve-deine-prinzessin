package sdp.content.prinzessins.kyoko.assets;

import sdp.modules.assets.Asset;

public enum KyokoBackground implements Asset {
    Makoto_Room;

    @Override
    public String getFileName() {
        return this.name() + ".png";
    }
}
