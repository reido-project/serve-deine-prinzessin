package sdp.modules.dialogue.interfaces;

import sdp.modules.dialogue.Dialogue;

public interface TeaseDialogueInterface {
    Dialogue[] variants();

    Dialogue[] hatesHungryVeryHungry();
    Dialogue[] hates();
    Dialogue[] dislikesHungryVeryHungry();
    Dialogue[] dislikes();
    Dialogue[] neutralHungryVeryHungry();
    Dialogue[] neutral();
    Dialogue[] likesHungryVeryHungry();
    Dialogue[] likes();
    Dialogue[] lovesVeryHungry();
    Dialogue[] loves();
}
