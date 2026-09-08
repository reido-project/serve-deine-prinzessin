package sdp.modules.assets;

import sdp.content.prinzessins.Prinzessin;
import sdp.content.prinzessins.chiaki.assets.ChiakiSprite;
import sdp.content.prinzessins.kazuha.assets.KazuhaSprite;
import sdp.content.prinzessins.kyoko.assets.KyokoBackground;
import sdp.content.prinzessins.kyoko.assets.KyokoMusic;
import sdp.content.prinzessins.kyoko.assets.KyokoSprite;
import sdp.content.prinzessins.kyoko.assets.KyokoVoice;
import sdp.content.prinzessins.tomoyo.assets.TomoyoSprite;

public final class AssetResolver {
    private AssetResolver() {}

    public static String nameOf(Asset asset) {
        return asset == null ? null : ((Enum<?>) asset).name();
    }

    public static Asset resolve(Prinzessin prinzessin, AssetCategory category, String name) {
        if (name == null) {
            return null;
        }

        Class<? extends Enum<?>> assetType = switch (prinzessin) {
            case KYOKO -> switch (category) {
                case BACKGROUND -> KyokoBackground.class;
                case MUSIC -> KyokoMusic.class;
                case SPRITE -> KyokoSprite.class;
                case VOICE -> KyokoVoice.class;
                default -> throw unsupported(prinzessin, category);
            };
            case CHIAKI -> switch (category) {
                case SPRITE -> ChiakiSprite.class;
                default -> throw unsupported(prinzessin, category);
            };
            case KAZUHA -> switch (category) {
                case SPRITE -> KazuhaSprite.class;
                default -> throw unsupported(prinzessin, category);
            };
            case TOMOYO -> switch (category) {
                case SPRITE -> TomoyoSprite.class;
                default -> throw unsupported(prinzessin, category);
            };
        };

        for (Enum<?> asset : assetType.getEnumConstants()) {
            if (asset.name().equals(name)) {
                return (Asset) asset;
            }
        }

        throw new IllegalArgumentException(
            "Unknown " + category + " asset '" + name + "' for " + prinzessin
        );
    }

    private static IllegalArgumentException unsupported(Prinzessin prinzessin, AssetCategory category) {
        return new IllegalArgumentException(
            "Unsupported " + category + " asset for " + prinzessin
        );
    }
}
