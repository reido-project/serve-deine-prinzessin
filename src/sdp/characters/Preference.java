package sdp.characters;

public enum Preference {

    HATED,
    DISLIKED,
    NEUTRAL,
    LIKED,
    LOVED;

    public static Preference from(int affection) {
        if (affection < 20) {
            return HATED;
        }

        if (affection < 40) {
            return DISLIKED;
        }

        if (affection < 60) {
            return NEUTRAL;
        }

        if (affection < 80) {
            return LIKED;
        }

        return LOVED;
    }
}