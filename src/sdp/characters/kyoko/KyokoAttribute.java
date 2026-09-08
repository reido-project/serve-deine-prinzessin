package sdp.characters.kyoko;

import sdp.assets.sprite.SpriteState;
import sdp.characters.Preference;
import sdp.items.ItemList;

import java.util.List;
import java.util.Map;

public final class KyokoAttribute {
    static final Map<ItemList, Preference> PREFERENCES = Map.ofEntries(
        Map.entry(ItemList.TOPI_SHERLOCK, Preference.LOVED),
        Map.entry(ItemList.DISTRUST_WALKMAN, Preference.LOVED),
        Map.entry(ItemList.LIFE_NOTE, Preference.LOVED),

        Map.entry(ItemList.BUKU_PEREMPUAN, Preference.LIKED),
        Map.entry(ItemList.ULET_BULU, Preference.LIKED),
        Map.entry(ItemList.KAMUS_JAWA, Preference.LIKED),
        Map.entry(ItemList.PISTOL_KOREK, Preference.LIKED),
        Map.entry(ItemList.PHONE_WAVE, Preference.LIKED),

        Map.entry(ItemList.AHEGAO_HOODIE, Preference.DISLIKED),
        Map.entry(ItemList.KITAB_CHEAT, Preference.DISLIKED),
        Map.entry(ItemList.ADAPTER_ROKOK, Preference.DISLIKED),
        Map.entry(ItemList.KITAB_PRIMBON, Preference.DISLIKED),
        Map.entry(ItemList.PIRING_SEKOLAH, Preference.DISLIKED),

        Map.entry(ItemList.SWALLOW_KANAN, Preference.HATED),
        Map.entry(ItemList.DIFTERI_ABED, Preference.HATED),
        Map.entry(ItemList.VHS_DESPAIR, Preference.HATED)
    );

    static final List<ItemList> SPECIAL_ITEMS = List.of(
        ItemList.TOPI_SHERLOCK, ItemList.PHONE_WAVE, ItemList.DISTRUST_WALKMAN, ItemList.LIFE_NOTE,
        ItemList.ADAPTER_ROKOK, ItemList.VHS_DESPAIR
    );

    static final List<SpriteState> SPRITES = List.of(
        SpriteState.Asserting, SpriteState.Confident, SpriteState.Defensive, SpriteState.Dismissive,
        SpriteState.Disturbed, SpriteState.Embarrassed, SpriteState.Flustered, SpriteState.Focused,
        SpriteState.Indifferent, SpriteState.Neutral, SpriteState.Objecting, SpriteState.Pissed, SpriteState.Resigned,
        SpriteState.Satisfied, SpriteState.Serious, SpriteState.Shy, SpriteState.Surprised, SpriteState.Thinking
    );
}
