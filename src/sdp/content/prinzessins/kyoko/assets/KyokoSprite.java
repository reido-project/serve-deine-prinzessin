package sdp.content.prinzessins.kyoko.assets;

import sdp.modules.assets.Asset;

public enum KyokoSprite implements Asset {

    // A
    Asserting,

    // C
    Confident,

    // D
    Defensive,
    Dismissive,
    Disturbed,

    // E
    Embarrassed,

    // F,
    Flustered,
    Focused,

    // I
    Indifferent,

    // N
    Neutral,

    // O
    Objecting,

    // P
    Pissed,

    // R
    Resigned,

    // S
    Satisfied,
    Serious,
    Shy,
    Surprised,

    // T
    Thinking;

    @Override
    public String getFileName() {
        return this.name() + ".png";
    }
}