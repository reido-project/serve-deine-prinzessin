package sdp.content.prinzessins.kyoko.topics.requirements;

import sdp.content.gameplay.talk.requirements.TopicRequirement;

public class KyokoTopic16Requirement extends TopicRequirement {
    @Override
    public Boolean[] require() {
        return new Boolean[]{
            getMoney() > 160_000_000_000_000L
        };
    }
}
