package sdp.gameplay.interactions.feed;

public record FeedResult(
    FeedState state,
    boolean accepted,
    boolean selfEat,
    int baseInsanityGain,
    int insanityModifier,
    int totalInsanityGain,
    boolean dedicated
) {}