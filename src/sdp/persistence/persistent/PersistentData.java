package sdp.persistence.persistent;

import sdp.persistence.persistent.dtos.*;

import java.util.List;

public class PersistentData {
    private SessionDataDTO sessionDataDTO;
    private PresentationDataDTO presentationDataDTO;
    private StatDataDTO statDataDTO;

    private List<TopicDataDTO> topicDatumDTOS;
    private List<InventoryDataDTO> inventoryDatumDTOS;

    public PersistentData() {}

    public PersistentData(SessionDataDTO sessionDataDTO, PresentationDataDTO presentationDataDTO, StatDataDTO statDataDTO, List<TopicDataDTO> topicDatumDTOS, List<InventoryDataDTO> inventoryDatumDTOS) {
        this.sessionDataDTO = sessionDataDTO;
        this.presentationDataDTO = presentationDataDTO;
        this.statDataDTO = statDataDTO;
        this.topicDatumDTOS = topicDatumDTOS;
        this.inventoryDatumDTOS = inventoryDatumDTOS;
    }

    public SessionDataDTO getSessionData() {
        return sessionDataDTO;
    }

    public PresentationDataDTO getPresentationData() {
        return presentationDataDTO;
    }

    public StatDataDTO getStatData() {
        return statDataDTO;
    }

    public List<TopicDataDTO> getTopicData() {
        return topicDatumDTOS;
    }

    public List<InventoryDataDTO> getInventoryData() {
        return inventoryDatumDTOS;
    }
}
