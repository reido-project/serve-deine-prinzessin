package sdp.content.gameplay.feed.behaviors;

import sdp.modules.behavior.InteractionBehavior;

public class FeedBehavior extends InteractionBehavior {
    private final int insanityChange;
    private final int hungerChange;

    public FeedBehavior(int insanityChange, int hungerChange){
        this.insanityChange = insanityChange;
        this.hungerChange = hungerChange;
    }

    @Override
    public void execute() {
        changeHunger(hungerChange);
        changeInsanity(insanityChange);
    }
}
