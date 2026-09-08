package sdp.persistence;

import sdp.content.prinzessins.Prinzessin;
import sdp.content.gameplay.story.StoryState;
import sdp.modules.behavior.UsageTrackable;
import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.talk.topics.Topic;
import sdp.modules.assets.Asset;
import sdp.modules.assets.AssetResolver;
import sdp.persistence.persistent.PersistentData;
import sdp.persistence.persistent.dtos.*;
import sdp.persistence.runtime.RuntimeData;
import sdp.persistence.runtime.data.*;

import java.util.ArrayList;
import java.util.List;

public class DataController {
    // Instance
    private DataController(){ resetInstance(); }
    private static class Holder { private static final DataController INSTANCE = new DataController(); }
    public static DataController getInstance() {
        return Holder.INSTANCE;
    }


    // Fields
    private SessionData sessionData;
    private PresentationData presentationData;
    private StatData statData;
    private TopicData topicData;
    private InventoryData inventoryData;

    public void initializeSession(String playerName, Prinzessin prinzessinID) {
        initializeSession(playerName, prinzessinID, StoryState.PROLOGUE);
    }

    public void initializeSession(String playerName, Prinzessin prinzessinID, StoryState storyState) {
        this.sessionData = new SessionData();

        sessionData.setPlayerName(playerName);
        sessionData.setPrinzessinID(prinzessinID);
        sessionData.setStoryState(storyState);
    }

    // Logic
    public void loadRuntimeData(RuntimeData runtimeData) {
        resetInstance();

        // Presentation Data
        presentationData.setCurrentMusic(runtimeData.currentMusic());
        presentationData.setCurrentVoice(runtimeData.currentVoice());
        presentationData.setCurrentSprite(runtimeData.currentSprite());

        // Stat Data
        statData.setInsanity(runtimeData.insanity());
        statData.setAffection(runtimeData.affection());
        statData.setHunger(runtimeData.hunger());
        statData.setMoney(runtimeData.money());

        // Inventory & Topic
        inventoryData.addItems(runtimeData.items());
        topicData.addTopics(runtimeData.topics());
    }

    public PersistentData toPersistent(){
        SessionDataDTO sessionDataDTO = new SessionDataDTO(sessionData.getPlayerName(), sessionData.getPrinzessinID(), sessionData.getStoryState());
        PresentationDataDTO presentationDataDTO = new PresentationDataDTO(
            AssetResolver.nameOf(presentationData.getCurrentMusic()),
            AssetResolver.nameOf(presentationData.getCurrentVoice()),
            AssetResolver.nameOf(presentationData.getCurrentSprite()),
            AssetResolver.nameOf(presentationData.getCurrentBackground())
        );
        StatDataDTO statDataDTO = new StatDataDTO(statData.getInsanity(), statData.getAffection(), statData.getHunger(), statData.getMoney());

        List<TopicDataDTO> topicDataDTOs = new ArrayList<>();
        List<InventoryDataDTO> inventoryDataDTOs = new ArrayList<>();

        List<Topic> topics = topicData.getTopics();
        List<Item> items = inventoryData.getInventory();

        for(Topic topic : topics){
            topicDataDTOs.add(new TopicDataDTO(topic.getId(), topic.isVisible(), topic.isExhausted()));
        }

        for (Item item : items){
            int usageCount = -1;

            if(item.getBehavior() instanceof UsageTrackable){
                usageCount = ((UsageTrackable) item.getBehavior()).getUsageCount();
            }

            inventoryDataDTOs.add(new InventoryDataDTO(item.getId(), item.isExhausted(), usageCount));
        }

        return new PersistentData(sessionDataDTO, presentationDataDTO, statDataDTO, topicDataDTOs, inventoryDataDTOs);
    }

    private void resetInstance(){
        presentationData = new PresentationData();
        topicData = new TopicData();
        inventoryData = new InventoryData();
        statData = new StatData();
    }

    // ======================== Getters ======================== //

    public SessionData getSessionData() {
        return sessionData;
    }

    public PresentationData getPresentationData() {
        return presentationData;
    }

    public TopicData getTopicData() {
        return topicData;
    }

    public InventoryData getInventoryData() {
        return inventoryData;
    }

    public StatData getStatData() {
        return statData;
    }
}
