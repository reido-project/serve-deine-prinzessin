package sdp.gameplay.interactions.feed;

public enum AffectionState {

    HATES,
    DISLIKES,
    NEUTRAL,
    LIKES,
    LOVES;

    /*
     * TODO:
     * Ganti threshold sesuai sistem affection final.
     */
    private static final int DISLIKES_THRESHOLD = 20;
    private static final int NEUTRAL_THRESHOLD = 40;
    private static final int LIKES_THRESHOLD = 60;
    private static final int LOVES_THRESHOLD = 80;

    public static AffectionState from(int affection) {

        if (affection < DISLIKES_THRESHOLD) {
            return HATES;
        }

        if (affection < NEUTRAL_THRESHOLD) {
            return DISLIKES;
        }

        if (affection < LIKES_THRESHOLD) {
            return NEUTRAL;
        }

        if (affection < LOVES_THRESHOLD) {
            return LIKES;
        }

        return LOVES;
    }
}