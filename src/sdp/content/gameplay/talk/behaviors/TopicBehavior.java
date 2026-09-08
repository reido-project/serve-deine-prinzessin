package sdp.content.gameplay.talk.behaviors;

import sdp.modules.behavior.Behavior;
import sdp.content.gameplay.talk.topics.TopicID;
import sdp.persistence.api.StatDataAPI;
import sdp.persistence.api.specialized.TopicDataAPI;

public abstract class TopicBehavior implements Behavior, TopicDataAPI, StatDataAPI {
    private final TopicID topicID;

    public TopicBehavior(TopicID topicID) {
        this.topicID = topicID;
    }

    protected void setExhaustedTrue(){
        getTopic(topicID).setExhaustedTrue();
    }
}
