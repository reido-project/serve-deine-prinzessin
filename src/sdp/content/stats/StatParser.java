package sdp.content.stats;

import sdp.content.gameplay.story.StoryState;

public final class StatParser {
    public static InsanityType getInsanity() {
        return InsanityType.getInsanity(new Stat().getInsanity());
    }

    public static AffectionType getAffection() {
        return AffectionType.getAffection(new Stat().getAffection());
    }

    public static HungerType getHunger() {
        return HungerType.getHunger(new Stat().getHunger());
    }

    public static StoryState checkStoryState() {
        InsanityType insanity = getInsanity();
        if (insanity == InsanityType.MAX_INSANITY) return StoryState.MAX_INSANITY_END;
        if (insanity == InsanityType.SANE) return StoryState.SANE_END;

        AffectionType affection = getAffection();
        if (affection == AffectionType.MAX_AFFECTION) return StoryState.MAX_AFF_END;
        if (affection == AffectionType.NO_AFFECTION) return StoryState.NO_AFF_END;

        if (getHunger() == HungerType.DEAD) return StoryState.DEAD_END;

        return null;
    }
}