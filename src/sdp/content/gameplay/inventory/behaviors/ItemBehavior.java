package sdp.content.gameplay.inventory.behaviors;

import sdp.modules.behavior.InteractionBehavior;

public class ItemBehavior extends InteractionBehavior {
    private final int affectionChange;

    public ItemBehavior(int affectionChange){
        this.affectionChange = affectionChange;
    }

    @Override
    public void execute() {
        changeAffection(affectionChange);
    }
}
