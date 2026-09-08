package sdp.persistence.persistent.dtos;

import sdp.content.gameplay.inventory.items.ItemID;

public record InventoryDataDTO(ItemID id, boolean exhausted, int usage) {}
