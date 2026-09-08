package sdp.modules.dialogue;

import sdp.shared.dtos.dialogue.DialogueDisplayDTO;
import sdp.persistence.api.PresentationDataAPI;

public class DialogueRunner implements PresentationDataAPI {
    public DialogueDisplayDTO run(Dialogue dialogue){
        dialogue.onTrigger().run();
        setSprite(dialogue.sprite());
        return new DialogueDisplayDTO(dialogue.speaker(), dialogue.line());
    }
}