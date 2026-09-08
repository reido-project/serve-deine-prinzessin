package sdp.modules.dialogue.interfaces;

import sdp.modules.dialogue.Dialogue;

public interface DedicatedFeedDialogueInterface {
    Dialogue[] veryHungryVeryInsane();
    Dialogue[] veryHungryInsane();
    Dialogue[] veryHungryNearSane();

    Dialogue[] hungryVeryInsane();
    Dialogue[] hungryInsane();
    Dialogue[] hungryNearSane();

    Dialogue[] neutralVeryInsane();
    Dialogue[] neutralInsane();
    Dialogue[] neutralNearSane();

    Dialogue[] fullVeryInsane();
    Dialogue[] fullInsane();
    Dialogue[] fullNearSane();
}
