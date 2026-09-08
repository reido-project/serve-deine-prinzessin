package sdp.content.prinzessins.chiaki.assets;

import sdp.modules.assets.Asset;

public enum ChiakiSprite implements Asset {

    // C
    Confused,
    Curious,

    // D
    Defensive,
    Denial,
    Disappointed,

    // E
    Excited,
    Explaining,

    // H
    Happy,

    // N
    Neutral,

    // O
    Objecting,

    // P
    Pleased,
    Pouting,

    // S
    Sad,
    Serious,
    Sleeping,
    Sleepy,
    Suggesting,

    // T
    Thinking,

    // U
    Uncertain,

    // W
    Wondering,

    // Y
    Yawning;

    @Override
    public String getFileName() {
        return this.name() + ".png";
    }
}