package sdp.persistence.persistent.dtos;

import sdp.content.gameplay.talk.topics.TopicID;

public record TopicDataDTO(TopicID topicID, boolean visible, boolean exhausted) {}