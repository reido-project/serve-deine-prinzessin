package sdp.modules.dialogue;

import sdp.shared.dtos.dialogue.DialogueDisplayDTO;
import sdp.persistence.api.PresentationDataAPI;

public class DialogueRunner implements PresentationDataAPI {
    public DialogueDisplayDTO run(Dialogue dialogue){
        if (dialogue == null) {
            throw new IllegalArgumentException("Dialogue cannot be null");
        }
        if (dialogue.onTrigger() != null) {
            dialogue.onTrigger().run();
        }
        if (dialogue.sprite() != null) {
            setSprite(dialogue.sprite());
        }
        return new DialogueDisplayDTO(dialogue.speaker(), dialogue.line());
    }
}