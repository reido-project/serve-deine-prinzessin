package com.nub.app.dialogues;

public interface DialogueBank {
    Dialogue[] getPROLOGUE();

    Dialogue[] getFEED_SATISFIED();
    Dialogue[] getFEED_NEUTRAL();

    Dialogue[] getGIFT_SATISFIED();
    Dialogue[] getGIFT_LIKES();
    Dialogue[] getGIFT_NEUTRAL();
    Dialogue[] getGIFT_DISAPPOINTED();

    Dialogue[] getTEASE();
    Dialogue[] getTEASE_RESPONSE();
}
