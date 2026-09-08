package sdp.persistence.api;

import sdp.content.gameplay.story.StoryState;
import sdp.persistence.DataController;

public interface SessionDataAPI {
    default void setPlayerName(String playerName) {
        DataController.getInstance().getSessionData().setPlayerName(playerName);
    }

    default StoryState getStoryState() {
        return DataController.getInstance().getSessionData().getStoryState();
    }

    default void setStoryState(StoryState storyState) {
        DataController.getInstance().getSessionData().setStoryState(storyState);
    }
}
