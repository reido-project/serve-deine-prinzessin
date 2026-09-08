package sdp.content.prinzessins.kyoko.assets.config;

import sdp.content.stats.AffectionType;
import sdp.modules.assets.Asset;

import java.util.HashMap;
import java.util.Map;

import static sdp.content.prinzessins.kyoko.assets.KyokoSprite.*;
import static sdp.content.stats.AffectionType.*;

public class KyokoSpriteConfig {
    public static Map<AffectionType, Asset> getSprites() {
        Map<AffectionType, Asset> sprites = new HashMap<>();
        sprites.put(HATES, Pissed);
        sprites.put(DISLIKES, Defensive);
        sprites.put(NEUTRAL, Resigned);
        sprites.put(LIKES, Indifferent);
        sprites.put(LOVES, Neutral);
        return sprites;
    }

    public static Asset resolveDefaultSprite(AffectionType affection) {
        Map<AffectionType, Asset> sprites = getSprites();
        if (affection == null) {
            return sprites.getOrDefault(NEUTRAL, Resigned);
        }
        return sprites.getOrDefault(affection, sprites.getOrDefault(NEUTRAL, Resigned));
    }
}
