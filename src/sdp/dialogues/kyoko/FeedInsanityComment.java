package sdp.dialogues.kyoko;

import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueInterface;
import sdp.gameplay.interactions.feed.InsanityState;

public class FeedInsanityComment extends DialogueInterface {

    public Dialogue[] get(InsanityState insanity) {
        return switch (insanity) {
            case VERY_INSANE -> veryInsane();
            case INSANE -> insane();
            case NEAR_SANE -> nearSane();
        };
    }

    private Dialogue[] veryInsane() {
        return new Dialogue[] {
            d(p, "Egmasnumapil very insane")
        };
    }

    private Dialogue[] insane() {
        return new Dialogue[] {
            d(p, "Depok toast insane")
        };
    }

    private Dialogue[] nearSane() {
        return new Dialogue[] {
            d(p, "Cocoa Japanese Curry near sane")
        };
    }
}