package sdp.gameplay.interactions;

import sdp.characters.CharacterList;
import sdp.characters.CharacterState;
import sdp.dialogues.Dialogue;
import sdp.gameplay.interactions.talk.TalkController;
import sdp.gameplay.interactions.talk.Topic;
import sdp.gameplay.interactions.talk.TopicID;
import sdp.items.ItemController;
import sdp.items.ItemList;

public class InteractionController {

    private final ItemController itemController;
    private final TalkController talkController;

    public InteractionController(
        ItemController itemController,
        CharacterList characterID
    ) {
        if (itemController == null) {
            throw new IllegalArgumentException(
                "ItemController cannot be null."
            );
        }

        this.itemController = itemController;
        this.talkController = new TalkController(characterID);
    }

    public Topic[] getTalkTopics() {
        return talkController.getTopics();
    }

    public void gift(ItemList item) {
        itemController.give(item);
    }

    public void use(ItemList item) {
        itemController.use(item);
    }
}