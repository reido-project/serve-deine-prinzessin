package sdp.content.stats;

public enum InsanityType {
    MAX_INSANITY("MI", "MI"),
    VERY_INSANE("VeryInsane", "VI"),
    INSANE("Insane", "I"),
    NEAR_SANE("NearSane", "NS"),
    SANE("Sane", "S");

    private final String stringID;
    private final String feedResponseID;

    InsanityType(String stringID, String feedResponseID) {
        this.stringID = stringID;
        this.feedResponseID = feedResponseID;
    }

    public String getStringID() {
        return stringID;
    }

    public String getFeedResponseID() {
        return feedResponseID;
    }

    public static InsanityType getInsanity(int insanity) {
        if (insanity < 1) {
            return SANE;
        }
        if (insanity < 34) {
            return NEAR_SANE;
        }
        if (insanity < 67) {
            return INSANE;
        }
        if (insanity < 100) {
            return VERY_INSANE;
        }
        return MAX_INSANITY;
    }
}