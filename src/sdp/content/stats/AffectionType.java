package sdp.content.stats;

public enum AffectionType {
    NO_AFFECTION("NA", "NA"),
    HATES("Hates", "HD"),
    DISLIKES("Dislikes", "HD"),
    NEUTRAL("Neutral", "N"),
    LIKES("Likes", "LI"),
    LOVES("Loves", "LO"),
    MAX_AFFECTION("MA", "MA");

    private final String stringID;
    private final String feedResponseID;

    AffectionType(String stringID, String feedResponseID) {
        this.stringID = stringID;
        this.feedResponseID = feedResponseID;
    }

    public String getStringID() {
        return stringID;
    }

    public String getFeedResponseID() {
        return feedResponseID;
    }

    public static AffectionType getAffection(int affection) {
        if (affection < 1) return NO_AFFECTION;
        if (affection < 21) return HATES;
        if (affection < 41) return DISLIKES;
        if (affection < 61) return NEUTRAL;
        if (affection < 81) return LIKES;
        if (affection < 100) return LOVES;
        return MAX_AFFECTION;
    }
}