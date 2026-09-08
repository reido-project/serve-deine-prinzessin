package sdp.gameplay.interactions.talk;

import sdp.misc.Util;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

import static sdp.gameplay.interactions.talk.TopicID.*;

public class KyokoTopic implements TopicInterface{
    public Topic[] get(){
        Topic[] topics = new Topic[]{
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
        };

        RuntimeData data = DataController.getInstance().getRuntimeData();

        if (data.getPlayerMoney() >= 160_000_000_000_000L) {
            //...
        }

        return topics.clone();
    }
}