package sdp.shared.dtos.inventory;

import sdp.content.gameplay.inventory.items.ItemID;
import sdp.content.gameplay.inventory.items.ItemType;

public record InventoryEntry(String name, String description, ItemID id, ItemType type) {}
