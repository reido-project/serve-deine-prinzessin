package sdp.persistence.api;

import sdp.persistence.DataController;

public interface TopicRequirementAPI {
    default long getMoney(){
        return DataController.getInstance().getStatData().getMoney();
    }
}
