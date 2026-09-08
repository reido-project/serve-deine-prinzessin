package sdp.gameplay.interactions.feed;

public enum HungerState {

    VERY_HUNGRY,
    HUNGRY,
    NEUTRAL,
    FULL,
    VERY_FULL;

    public static HungerState from(int hunger) {
        if (hunger < 20) {
            return VERY_HUNGRY;
        }

        if (hunger < 40) {
            return HUNGRY;
        }

        if (hunger < 60) {
            return NEUTRAL;
        }

        if (hunger < 80) {
            return FULL;
        }

        return VERY_FULL;
    }
}