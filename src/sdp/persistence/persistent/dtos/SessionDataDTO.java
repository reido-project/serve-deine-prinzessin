package sdp.persistence.persistent.dtos;

import sdp.content.gameplay.story.StoryState;
import sdp.content.prinzessins.Prinzessin;

public record SessionDataDTO(String playerName, Prinzessin prinzessinID, StoryState storyState) {}
