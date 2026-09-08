package sdp.persistence.runtime.data;

import sdp.modules.behavior.UsageTrackable;
import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.inventory.items.ItemID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryData {
    private final List<Item> inventory = new ArrayList<>();


    public List<Item> getInventory() {
        return new ArrayList<>(this.inventory);
    }

    public Item getItemByID(ItemID itemID) {
        return inventory.stream().filter(item -> item.getId().equals(itemID)).findFirst().orElse(null);
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void addItems(List<Item> items) {
        inventory.addAll(items);
    }

    public void addItems(Item[] items) {
        inventory.addAll(Arrays.asList(items));
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public void removeItemByID(ItemID itemID) {
        removeItem(getItemByID(itemID));
    }

    public int getItemUsageCount(ItemID itemID) {
        Item item = getItemByID(itemID);
        if (item != null && item.getBehavior() instanceof UsageTrackable trackable) {
            return trackable.getUsageCount();
        }
        return -1;
    }
}