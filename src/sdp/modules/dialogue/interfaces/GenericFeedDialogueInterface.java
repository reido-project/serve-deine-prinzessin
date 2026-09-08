package sdp.modules.dialogue.interfaces;

import sdp.modules.dialogue.Dialogue;

public interface GenericFeedDialogueInterface {
    Dialogue[] hateDislikeSelf();
    Dialogue[] hateDislikeReject();

    Dialogue[] neutralAccept();
    Dialogue[] neutralReject();

    Dialogue[] likeAccept();
    Dialogue[] likeReject();

    Dialogue[] loveReject();
}
