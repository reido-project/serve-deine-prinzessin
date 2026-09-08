package sdp.characters.kyoko;

import sdp.assets.sprite.SpriteState;
import sdp.characters.Character;
import sdp.characters.CharacterList;
import sdp.characters.Preference;
import sdp.items.ItemList;

import java.util.List;
import java.util.Map;

public class Kyoko extends Character {
    private static final Map<ItemList, Preference> preferences = KyokoAttribute.PREFERENCES;

    private static final List<ItemList> specials = KyokoAttribute.SPECIAL_ITEMS;

    private static final List<SpriteState> sprites = KyokoAttribute.SPRITES;

    public Kyoko() {
        super(CharacterList.KYOKO, CharacterList.KYOKO.getFullName(), "Kyoko", preferences, specials, sprites);
    }
}
