package sdp.persistence.api;

import sdp.shared.exceptions.ItemNotFoundException;
import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.persistence.DataController;

public interface ItemDataAPI {
    default Item getItem(ItemID itemID){
        Item item = DataController.getInstance().getInventoryData().getItemByID(itemID);
        if(item == null){
            throw new ItemNotFoundException();
        }
        return item;
    }

    default void removeItem(Item item){
        DataController.getInstance().getInventoryData().removeItem(item);
    }
}
