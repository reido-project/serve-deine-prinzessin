package sdp.characters;

import sdp.assets.sprite.SpriteState;

public enum CharacterList {
    KYOKO("Kyoko Kirigiri", "Kyoko", SpriteState.Resigned),
    CHIAKI("Chiaki Nanami", "Chiaki", SpriteState.Uncertain),
    KAZUHA("Kazuha Migiwa", "Kazuha", SpriteState.Neutral),
    TOMOYO("Tomoyo Sakagami", "Tomoyo", SpriteState.Neutral);

    private final String fullName;
    private final String nickname;
    private final SpriteState prinzessinSelectSprite;

    CharacterList(String fullName, String nickname, SpriteState prinzessinSelectSprite) {
        this.fullName = fullName;
        this.nickname = nickname;
        this.prinzessinSelectSprite = prinzessinSelectSprite;
    }

    public String getFullName() {
        return fullName;
    }

    public SpriteState getprinzessinSelectSprite() {
        return prinzessinSelectSprite;
    }

    public String getNickname() {
        return nickname;
    }
}