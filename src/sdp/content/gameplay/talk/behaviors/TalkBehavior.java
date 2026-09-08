package sdp.content.gameplay.talk.behaviors;

import sdp.modules.behavior.InteractionBehavior;

public class TalkBehavior extends InteractionBehavior {
    @Override
    public void execute() {
        changeHunger(-15);
    }
}
