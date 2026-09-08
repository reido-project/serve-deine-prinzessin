package sdp.persistence.runtime;

import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.talk.topics.Topic;
import sdp.modules.assets.Asset;

public record RuntimeData(
    Asset currentMusic,
    Asset currentVoice,
    Asset currentSprite,

    int insanity,
    int affection,
    int hunger,
    long money,

    Topic[] topics,
    Item[] items
){
    public RuntimeData(int insanity, int affection, int hunger, long money, Topic[] topics, Item[] items){
        this(null, null, null, insanity,  affection, hunger, money, topics, items);
    }
}
