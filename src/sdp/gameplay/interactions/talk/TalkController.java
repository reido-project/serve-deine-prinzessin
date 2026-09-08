package sdp.gameplay.interactions.talk;

import sdp.characters.CharacterList;

public class TalkController {

    private final TopicInterface topic;

    public TalkController(CharacterList characterID) {
        this.topic = switch (characterID) {
            case KYOKO -> new KyokoTopic();
            // case CHIAKI -> new ChiakiTopic();
            default -> throw new IllegalArgumentException(
                "Invalid character ID"
            );
        };
    }

    public Topic[] getTopics() {
        return topic.get();
    }
}
