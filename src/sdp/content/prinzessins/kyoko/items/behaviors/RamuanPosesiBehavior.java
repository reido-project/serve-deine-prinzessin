package sdp.content.prinzessins.kyoko.items.behaviors;

import sdp.content.gameplay.inventory.behaviors.ConsumableBehavior;
import sdp.content.gameplay.inventory.items.ItemID;

public class RamuanPosesiBehavior extends ConsumableBehavior {
    public RamuanPosesiBehavior(ItemID itemID) {
        super(itemID);
    }

    @Override
    public void execute() {
        setExhaustedTrue();
        changeInsanity(15);
    }
}
