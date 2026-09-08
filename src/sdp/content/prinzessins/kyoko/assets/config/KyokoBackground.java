package sdp.content.prinzessins.kyoko.assets.config;

import sdp.content.gameplay.story.StoryState;
import sdp.modules.assets.Asset;

import java.util.HashMap;
import java.util.Map;

import static sdp.content.gameplay.story.StoryState.*;
import static sdp.content.prinzessins.kyoko.assets.KyokoBackground.*;

public class KyokoBackground {
    private static final Map<StoryState, Asset> backgrounds = new HashMap<>(Map.of());

    public static Map<StoryState, Asset> getBackgrounds() {
        backgrounds.put(PROLOGUE, Makoto_Room);
        backgrounds.put(GAME, Makoto_Room);
        backgrounds.put(MAX_INSANITY_END, Makoto_Room);
        backgrounds.put(SANE_END, Makoto_Room);
        backgrounds.put(MAX_AFF_END, Makoto_Room);
        backgrounds.put(NO_AFF_END, Makoto_Room);
        backgrounds.put(DEAD_END, Makoto_Room);

        return backgrounds;
    }
}
