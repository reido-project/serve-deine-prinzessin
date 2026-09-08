package sdp.content.prinzessins.kyoko.topics.behaviors;

import sdp.content.gameplay.talk.behaviors.TopicBehavior;
import sdp.content.gameplay.talk.topics.TopicID;

public class KyokoTopic16Behavior extends TopicBehavior {

    public KyokoTopic16Behavior(TopicID topicID) {
        super(topicID);
    }

    @Override
    public void execute() {
        setExhaustedTrue();
        changeInsanity(5);
    }
}
