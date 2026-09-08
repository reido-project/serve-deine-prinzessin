package sdp.content.stats;

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
}