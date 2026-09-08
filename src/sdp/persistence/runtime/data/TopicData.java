package sdp.persistence.runtime.data;

import sdp.content.gameplay.talk.topics.Topic;
import sdp.content.gameplay.talk.topics.TopicID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopicData {
    List<Topic> topics = new ArrayList<>();

    public List<Topic> getTopics() {
        return new ArrayList<>(this.topics);
    }

    public Topic getTopicByID(TopicID id){
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);
    }

    public void addTopic(Topic topic){
        topics.add(topic);
    }

    public void addTopics(List<Topic> topics){
        this.topics.addAll(topics);
    }

    public void addTopics(Topic[] topics){
        this.topics.addAll(Arrays.asList(topics));
    }

    public void removeTopic(Topic topic){
        topics.remove(topic);
    }
}
