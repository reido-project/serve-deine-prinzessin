package sdp.items.behaviors;

import sdp.items.ItemBehavior;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

import java.util.concurrent.ThreadLocalRandom;

public class MicrophonePelunasBehavior
    implements ItemBehavior {

    private final RuntimeData data;

    public MicrophonePelunasBehavior() {
        this.data =
            DataController.getInstance().getRuntimeData();
    }

    @Override
    public void use() {

        if (data.isMicrophonePelunasBroken()) {
            return;
        }

        long money =
            data.getPlayerMoney();

        long amount =
            ThreadLocalRandom.current()
                .nextLong(1, Long.MAX_VALUE);

        // Overflow check
        if (money > Long.MAX_VALUE - amount) {

            data.setPlayerMoney(
                Long.MIN_VALUE
            );

            data.setPlayerInsanity(
                data.getPlayerInsanity() + 10
            );

            data.setMicrophonePelunasBroken(true);

            return;
        }

        data.setPlayerMoney(
            money + amount
        );
    }
}