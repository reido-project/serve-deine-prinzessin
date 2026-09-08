package sdp.persistence.api.specialized;

import sdp.persistence.DataController;

public interface TopicRequirementAPI {
    default long getMoney(){
        return DataController.getInstance().getStatData().getMoney();
    }
}
