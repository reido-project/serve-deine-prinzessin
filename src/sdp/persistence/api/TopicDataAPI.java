package sdp.persistence.api;

import sdp.content.gameplay.talk.topics.Topic;
import sdp.content.gameplay.talk.topics.TopicID;
import sdp.persistence.DataController;

public interface TopicDataAPI {
    default Topic getTopic(TopicID topicID){
        return DataController.getInstance().getTopicData().getTopicByID(topicID);
    }
}
