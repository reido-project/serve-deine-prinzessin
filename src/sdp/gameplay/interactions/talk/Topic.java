package sdp.gameplay.interactions.talk;

public class Topic {
    private TopicID id;
    private String topic;

    public Topic(TopicID id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public TopicID getId() {
        return id;
    }
    public String getTopic() {
        return topic;
    }
}
