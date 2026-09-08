package sdp.shared.dtos.inventory;

import sdp.content.gameplay.inventory.items.ItemID;

public record InventoryEntry(String name, String description, ItemID id) {}
