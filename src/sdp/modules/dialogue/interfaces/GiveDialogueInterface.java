package sdp.modules.dialogue.interfaces;

import sdp.modules.dialogue.Dialogue;

public interface GiveDialogueInterface {
    Dialogue[] hated();
    Dialogue[] disliked();
    Dialogue[] neutral();
    Dialogue[] liked();
    Dialogue[] loved();
}
