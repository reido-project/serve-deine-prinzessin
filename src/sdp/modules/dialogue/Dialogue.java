package sdp.modules.dialogue;

import sdp.modules.assets.Asset;
import static sdp.modules.assets.AssetCategory.SPRITE;

public record Dialogue(String speaker, String line, Asset sprite, Runnable onTrigger) {
    public Dialogue {
        if (sprite != null && sprite.getCategory() != SPRITE) {
            sprite = null;
        }
    }

    public Dialogue(String speaker, String line) {
        this(speaker, line, null, null);
    }

    public Dialogue(String speaker, String line, Asset sprite) {
        this(speaker, line, sprite, null);
    }
}