package sdp.gameplay.interactions.feed;

import sdp.characters.Preference;

public enum FeedResponseGroup {

    HATES_DISLIKES_SELF_EAT,
    HATES_DISLIKES_REFUSE,
    NEUTRAL_REFUSE;

    public static FeedResponseGroup from(
        FeedResult result
    ) {
        if (result.selfEat()) {
            return HATES_DISLIKES_SELF_EAT;
        }

        if (!result.accepted()) {
            if (
                result.state().affection()
                    == Preference.NEUTRAL
            ) {
                return NEUTRAL_REFUSE;
            }

            return HATES_DISLIKES_REFUSE;
        }

        throw new IllegalArgumentException(
            "Accepted FeedResult has no generic response group."
        );
    }
}