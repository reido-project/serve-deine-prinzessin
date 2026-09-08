package sdp.modules.dialogue.interfaces;

import sdp.modules.dialogue.Dialogue;

public interface FoodCommentDialogueInterface {
    Dialogue[] neutralVeryInsane();
    Dialogue[] neutralInsane();
    Dialogue[] neutralNearSane();

    Dialogue[] likeVeryInsane();
    Dialogue[] likeInsane();
    Dialogue[] likeNearSane();

    Dialogue[] loveVeryInsane();
    Dialogue[] loveInsane();
    Dialogue[] loveNearSane();

    Dialogue[] veryInsaneFood();
    Dialogue[] insaneFood();
    Dialogue[] nearSaneFood();
}
