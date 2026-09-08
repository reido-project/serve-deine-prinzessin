package sdp.content.gameplay.inventory.items;

import sdp.content.gameplay.inventory.behaviors.ConsumableBehavior;

import static sdp.content.gameplay.inventory.items.ItemType.*;

public class Item {
    private final ItemID id;
    private final String title;
    private final String description;
    private final Preference preference;
    private final ItemType type;
    private final boolean special;
    private final ConsumableBehavior behavior;

    private boolean exhausted = false;

    private Item(ItemID id, String title, String description, Preference preference, ItemType type, boolean special, ConsumableBehavior behavior) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.preference = preference;
        this.type = type;
        this.special = special;
        this.behavior = behavior;
    }

    public Item(ItemID id, String title, String description, Preference preference, boolean special) {
        this(id, title, description, preference, GIFT, special, null);
    }

    public Item(ItemID id, String title, String description, ConsumableBehavior behavior) {
        this(id, title, description, null, CONSUMABLE, false, behavior);
    }

    public ItemID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Preference getPreference() {
        return preference;
    }

    public ItemType getType() {
        return type;
    }

    public boolean isSpecial() {
        return special;
    }

    public ConsumableBehavior getBehavior() {
        return behavior;
    }

    public boolean isExhausted() {
        return exhausted;
    }

    public void setExhaustedTrue() {
        exhausted = true;
    }
}
