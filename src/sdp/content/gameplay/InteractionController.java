package sdp.content.gameplay;

import sdp.content.prinzessins.Prinzessin;
import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueRepository;
import sdp.content.gameplay.feed.FeedController;
import sdp.content.gameplay.inventory.InventoryController;
import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.content.gameplay.talk.TalkController;
import sdp.content.gameplay.talk.topics.Topic;
import sdp.content.gameplay.talk.topics.TopicID;
import sdp.content.gameplay.tease.TeaseController;
import sdp.persistence.DataController;
import sdp.persistence.persistent.dtos.InventoryDataDTO;
import sdp.persistence.persistent.dtos.TopicDataDTO;
import sdp.content.prinzessins.PrinzessinAttributes;

import java.util.List;

public class InteractionController {
    private final TalkController talkController;
    private final TeaseController teaseController;
    private final InventoryController inventoryController;
    private final FeedController feedController;

    public InteractionController(){
        Prinzessin prinzessin = DataController.getInstance().getSessionData().getPrinzessinID();

        if(prinzessin == null){
            throw new IllegalStateException("Prinzessin has not been initialized");
        }

        PrinzessinAttributes attributes = new PrinzessinAttributes(prinzessin);

        DialogueRepository dialogueRepository = new DialogueRepository(attributes);

        talkController = new TalkController(attributes.getTopics(),dialogueRepository);
        teaseController = new TeaseController(dialogueRepository);
        inventoryController = new InventoryController(attributes.getItems(), dialogueRepository);
        feedController = new FeedController(dialogueRepository);
    }


    // Logic
    public Dialogue[] talk(TopicID topicID) {
        return talkController.talk(topicID);
    }

    public Topic[] getDefaultTopics() {
        return talkController.getDefaultTopics();
    }

    public Topic[] resolveTopic(List<TopicDataDTO> topicData){
        return talkController.resolveTopic(topicData);
    }

    public Dialogue[] tease(){
        return teaseController.tease();
    }

    public Dialogue[] use(ItemID itemID) {
        return inventoryController.use(itemID);
    }

    public Item[] resolveInventory(ItemID[] itemIDs) {
        return inventoryController.resolveInventory(itemIDs);
    }

    public Item[] resolveInventory(List<InventoryDataDTO> itemData){
        return inventoryController.resolveInventory(itemData);
    }

    public Dialogue[] feed(){
        return feedController.feed();
    }
}
