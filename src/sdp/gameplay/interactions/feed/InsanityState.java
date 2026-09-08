package sdp.gameplay.interactions.feed;

public enum InsanityState {

    VERY_INSANE,
    INSANE,
    NEAR_SANE;

    private static final int VERY_INSANE_THRESHOLD = 67;
    private static final int INSANE_THRESHOLD = 34;

    public static InsanityState from(int insanity) {
        if (insanity >= VERY_INSANE_THRESHOLD) {
            return VERY_INSANE;
        }

        if (insanity >= INSANE_THRESHOLD) {
            return INSANE;
        }

        return NEAR_SANE;
    }
}