package sdp.content.prinzessins.kyoko.items.behaviors;

import sdp.content.gameplay.inventory.behaviors.ConsumableBehavior;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.shared.utils.NumberUtil;

public class MicrophonePelunasBehavior extends ConsumableBehavior {

    public MicrophonePelunasBehavior(ItemID itemID) {
        super(itemID);
    }

    @Override
    public void execute() {
        increaseMoney(NumberUtil.longRNG());

        if(getMoney() == Long.MIN_VALUE){
            setExhaustedTrue();
        }
    }
}
