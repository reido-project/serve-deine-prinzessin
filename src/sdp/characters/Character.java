package sdp.characters;

import sdp.assets.sprite.SpriteState;
import sdp.items.ItemList;

import java.util.List;
import java.util.Map;

public abstract class Character {

    private final CharacterList id;
    private final String fullName;
    private final String nickname;

    private final Map<ItemList, Preference> preferences;
    private final List<ItemList> specials;

    private final List<SpriteState> sprites;

    protected Character(
        CharacterList id,
        String fullName,
        String nickname,
        Map<ItemList, Preference> preferences,
        List<ItemList> specials,
        List<SpriteState> sprites
        ) {
        this.id = id;
        this.fullName = fullName;
        this.nickname = nickname;
        this.preferences = preferences;
        this.specials = specials;
        this.sprites = sprites;
    }

    public CharacterList getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public Preference getPreference(ItemList item) {
        return preferences.getOrDefault(item, Preference.NEUTRAL);
    }

    public List<ItemList> getSpecials() {
        return specials;
    }

    public boolean hasSpecial(ItemList item) {
        return specials.contains(item);
    }
}
