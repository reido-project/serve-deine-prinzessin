package sdp.content.gameplay.tease.behaviors;

import sdp.modules.behavior.InteractionBehavior;

public class TeaseBehavior extends InteractionBehavior {
    private final int affectionChange;

    public TeaseBehavior(int affectionChange) {
        this.affectionChange = affectionChange;
    }

    @Override
    public void execute() {
        changeHunger(-15);
        changeAffection(affectionChange);
    }
}
