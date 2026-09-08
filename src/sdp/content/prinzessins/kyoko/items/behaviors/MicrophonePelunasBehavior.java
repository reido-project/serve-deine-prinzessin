package sdp.content.prinzessins.kyoko.items.behaviors;

import sdp.content.gameplay.inventory.behaviors.ConsumableBehavior;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.persistence.DataController;
import sdp.shared.utils.NumberUtil;

public class MicrophonePelunasBehavior extends ConsumableBehavior {

    public MicrophonePelunasBehavior(ItemID itemID) {
        super(itemID);
    }

    @Override
    public void execute() {
        increaseMoney(NumberUtil.longRNG(0.1));

        if(getMoney() == Long.MIN_VALUE) {
            setExhaustedTrue();
        }
    }

    @Override
    public void increaseMoney(long money) {
        try {
            DataController.getInstance().getStatData().setMoney(Math.addExact(getMoney(), money));
        } catch (ArithmeticException e) {
            setMoneyLongMinValue();
        }
    }
}