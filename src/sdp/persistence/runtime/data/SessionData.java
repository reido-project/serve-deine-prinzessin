package sdp.persistence.runtime.data;

import sdp.content.prinzessins.Prinzessin;

public class SessionData {
    private String playerName = null;
    private Prinzessin prinzessinID = null;

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
}
