package sdp.gameplay.interactions.feed;

import sdp.characters.Preference;

public record FeedState(
    Preference affection,
    HungerState hunger,
    InsanityState insanity
) {}