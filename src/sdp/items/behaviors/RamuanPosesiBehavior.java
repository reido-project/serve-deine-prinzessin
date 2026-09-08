package sdp.items.behaviors;

import sdp.items.ItemBehavior;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

public class RamuanPosesiBehavior
    implements ItemBehavior {

    private final RuntimeData data;

    public RamuanPosesiBehavior() {
        this.data =
            DataController.getInstance().getRuntimeData();
    }

    @Override
    public void use() {

        data.setPlayerInsanity(
            data.getPlayerInsanity() + 15
        );
    }
}