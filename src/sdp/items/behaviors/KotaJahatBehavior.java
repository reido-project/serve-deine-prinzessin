package sdp.items.behaviors;

import sdp.items.ItemBehavior;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

public class KotaJahatBehavior implements ItemBehavior {

    private final RuntimeData data;

    public KotaJahatBehavior() {
        this.data =
            DataController.getInstance().getRuntimeData();
    }

    @Override
    public void use() {

        if (data.getKotaJahatUses() >= 11) {
            return;
        }

        data.incrementKotaJahatUses();

        data.setPlayerInsanity(
            data.getPlayerInsanity() + 1
        );
    }

    public boolean isExhausted() {
        return data.getKotaJahatUses() >= 11;
    }

    public int getDialogueIndex() {
        return data.getKotaJahatUses();
    }
}