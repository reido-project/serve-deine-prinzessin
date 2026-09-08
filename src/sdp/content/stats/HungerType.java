package sdp.content.stats;

public enum HungerType {
    DEAD("D", "D"),
    VERY_HUNGRY("VeryHungry", "VH"),
    HUNGRY("Hungry", "H"),
    NEUTRAL("Neutral", "N"),
    FULL("Full", "F"),
    VERY_FULL("VeryFull", "VF");

    private final String stringID;
    private final String feedResponseID;

    HungerType(String stringID, String feedResponseID) {
        this.stringID = stringID;
        this.feedResponseID = feedResponseID;
    }

    public String getStringID() {
        return stringID;
    }

    public String getFeedResponseID() {
        return feedResponseID;
    }

    public static HungerType getHunger(int hunger) {
        if (hunger > 100) {
            throw new IllegalArgumentException("Hunger value cannot exceed 100: " + hunger);
        }
        if (hunger < 1) {
            return DEAD;
        }
        if (hunger < 21) {
            return VERY_HUNGRY;
        }
        if (hunger < 41) {
            return HUNGRY;
        }
        if (hunger < 61) {
            return NEUTRAL;
        }
        if (hunger < 81) {
            return FULL;
        }
        return VERY_FULL;
    }
}