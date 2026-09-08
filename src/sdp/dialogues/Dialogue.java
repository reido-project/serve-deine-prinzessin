package sdp.dialogues;

import sdp.assets.sprite.SpriteState;

public record Dialogue(String speaker, String line, SpriteState sprite, Runnable onTrigger) {
    public Dialogue(String speaker, String line) {
        this(speaker, line, null, null);
    }
    public Dialogue(String speaker, String line, SpriteState sprite) {
        this(speaker, line, sprite, null);
    }
}