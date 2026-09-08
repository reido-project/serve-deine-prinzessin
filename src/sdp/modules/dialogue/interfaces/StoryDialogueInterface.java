package sdp.modules.dialogue.interfaces;

import sdp.modules.dialogue.Dialogue;

public interface StoryDialogueInterface {
    Dialogue[] prologue();
    Dialogue[] maxInsanityEnd();
    Dialogue[] saneEnd();
    Dialogue[] maxAffEnd();
    Dialogue[] noAffEnd();
    Dialogue[] deadEnd();
}
