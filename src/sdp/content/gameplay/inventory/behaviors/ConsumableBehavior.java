package sdp.content.gameplay.inventory.behaviors;

import sdp.modules.behavior.Behavior;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.persistence.api.specialized.ConsumableBehaviorDataAPI;
import sdp.persistence.api.specialized.ItemDataAPI;
import sdp.persistence.api.StatDataAPI;

public abstract class ConsumableBehavior implements Behavior, ConsumableBehaviorDataAPI, ItemDataAPI, StatDataAPI {
    private final ItemID itemID;

    public ConsumableBehavior(ItemID itemID) {
        this.itemID = itemID;
    }

    protected void setExhaustedTrue(){
        getItem(itemID).setExhaustedTrue();
    }
}
