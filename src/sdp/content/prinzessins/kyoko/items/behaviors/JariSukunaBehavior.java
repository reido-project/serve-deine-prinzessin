package sdp.content.prinzessins.kyoko.items.behaviors;

import sdp.content.gameplay.inventory.behaviors.ConsumableBehavior;
import sdp.content.gameplay.inventory.items.ItemID;

public class JariSukunaBehavior extends ConsumableBehavior {

    public JariSukunaBehavior(ItemID itemID) {
        super(itemID);
    }

    @Override
    public void execute() {
        setExhaustedTrue();
        changeHunger(100);
        changeAffection(-20);
    }
}
