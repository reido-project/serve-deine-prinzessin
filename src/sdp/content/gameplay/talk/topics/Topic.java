package sdp.content.gameplay.talk.topics;

import sdp.content.gameplay.talk.behaviors.TopicBehavior;
import sdp.modules.requirement.Requirement;

public class Topic{
    private final TopicID id;
    private final String topic;
    private final Requirement requirement;
    private final TopicBehavior behavior;

    boolean visible = false;
    boolean exhausted = false;

    public Topic(TopicID id, String topic, Requirement requirement, TopicBehavior behavior) {
        this.id = id;
        this.topic = topic;
        this.requirement = requirement;
        this.behavior = behavior;
    }

    public Topic(TopicID id, String topic, Requirement requirement) {
        this(id, topic, requirement, null);
    }

    public Topic(TopicID id, String topic, TopicBehavior behavior) {
        this(id, topic, null, behavior);
    }

    public Topic(TopicID id, String topic) {
        this(id, topic, null, null);
    }


    // Getters & Setters
    public TopicID getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public TopicBehavior getBehavior() {
        return behavior;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisibleTrue() {
        visible = true;
    }

    public boolean isExhausted() {
        return exhausted;
    }

    public void setExhaustedTrue() {
        exhausted = true;
    }
}
