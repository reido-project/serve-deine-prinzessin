package sdp.content.gameplay.talk;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueRepository;
import sdp.content.gameplay.talk.behaviors.TopicBehavior;
import sdp.content.gameplay.talk.behaviors.TalkBehavior;
import sdp.content.gameplay.talk.topics.Topic;
import sdp.content.gameplay.talk.topics.TopicID;
import sdp.persistence.api.TopicDataAPI;
import sdp.persistence.persistent.dtos.TopicDataDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TalkController implements TopicDataAPI {
    private final Map<TopicID, Topic> topicMap;
    private final DialogueRepository dialogueRepository;

    public TalkController(Topic[] topics, DialogueRepository dialogueRepository){
        this.topicMap = Arrays.stream(topics).collect(Collectors.toMap(Topic::getId, topic -> topic));
        this.dialogueRepository = dialogueRepository;
    }

    public Dialogue[] talk(TopicID topicID){
        Topic topic = getTopic(topicID);
        TopicBehavior specialBehavior = topic.getBehavior();

        if(!topic.isExhausted() && specialBehavior != null){
            specialBehavior.execute();
        }

        new TalkBehavior().execute();
        return dialogueRepository.getTalk(topicID);
    }

    public Topic[] getDefaultTopics(){
        return topicMap.values().toArray(Topic[]::new);
    }

    public Topic[] resolveTopic(List<TopicDataDTO> topicData){
        List<Topic> resolvedTopics = new ArrayList<>();

        for(TopicDataDTO topic : topicData){
            Topic resolvedTopic = topicMap.get(topic.topicID());

            if(topic.visible()){
                resolvedTopic.setVisibleTrue();
            }

            if(topic.exhausted()){
                resolvedTopic.setExhaustedTrue();
            }
            resolvedTopics.add(resolvedTopic);
        }
        return resolvedTopics.toArray(new Topic[0]);
    }
}
