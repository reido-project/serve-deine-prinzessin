package sdp.items.behaviors;

import sdp.items.ItemBehavior;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

public class JariSukunaBehavior implements ItemBehavior {

    private static final int AFFECTION_COST = 20;

    private final RuntimeData data;

    public JariSukunaBehavior() {
        this.data =
            DataController.getInstance().getRuntimeData();
    }

    @Override
    public void use() {

        if (data.isJariSukunaUsed()) {
            return;
        }

        data.setJariSukunaUsed(true);

        data.setPrinzessinHunger(100);

        data.changePrinzessinAffection(-AFFECTION_COST);
    }
}