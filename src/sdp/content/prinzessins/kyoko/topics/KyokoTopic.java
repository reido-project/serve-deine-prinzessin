package sdp.content.prinzessins.kyoko.topics;

import sdp.content.gameplay.talk.topics.Topic;
import sdp.content.prinzessins.kyoko.topics.behaviors.KyokoTopic16Behavior;
import sdp.content.prinzessins.kyoko.topics.requirements.KyokoTopic16Requirement;

import static sdp.content.gameplay.talk.topics.TopicID.*;

public final class KyokoTopic{
    private static final Topic[] topics = {
        new Topic(TOPIC_01, "The last trial"),
        new Topic(TOPIC_02, "xX69SoulCrusherXD420Xx"),
        new Topic(TOPIC_03, "Byakuya & Hina"),
        new Topic(TOPIC_04, "\"Meine Prinzessin\""),
        new Topic(TOPIC_05, "\"Wangy wangy\""),
        new Topic(TOPIC_06, "Things we found"),
        new Topic(TOPIC_07, "Food stock"),
        new Topic(TOPIC_08, "Our clothes"),
        new Topic(TOPIC_09, "What time is it?"),
        new Topic(TOPIC_10, "Hope's Peak library"),
        new Topic(TOPIC_11, "Monokuma & Hope's Peak Academy"),
        new Topic(TOPIC_12, "Our past"),
        new Topic(TOPIC_13, "The outside world"),
        new Topic(TOPIC_14, "Our situation"),
        new Topic(TOPIC_15, "Mental health"),
        new Topic(TOPIC_16, "I'M RICH!!!", new KyokoTopic16Requirement(), new KyokoTopic16Behavior(TOPIC_16))
    };

    public static Topic[] getTopics() {
        return topics.clone();
    }
}
