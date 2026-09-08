package sdp.modules.dialogue.interfaces;

import sdp.modules.dialogue.Dialogue;
import sdp.content.gameplay.inventory.items.ItemID;

public interface SpecialItemDialogue {
    Dialogue[] get(ItemID itemID);
}
