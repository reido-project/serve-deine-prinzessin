package sdp.persistence.runtime.data;

import sdp.content.gameplay.story.StoryState;
import sdp.content.prinzessins.Prinzessin;

import static sdp.content.gameplay.story.StoryState.*;

public class SessionData {
    private String playerName = null;
    private Prinzessin prinzessinID = null;
    private StoryState storyState = PROLOGUE;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Prinzessin getPrinzessinID() {
        return prinzessinID;
    }

    public void setPrinzessinID(Prinzessin prinzessinID) {
        this.prinzessinID = prinzessinID;
    }

    public StoryState getStoryState() {
        return storyState;
    }

    public void setStoryState(StoryState storyState) {
        this.storyState = storyState;
    }
}
