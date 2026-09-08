package sdp.content.gameplay.feed.types;

import java.util.HashMap;
import java.util.Map;

public enum FeedResponseIdentifier {
    // Format: AFF/HUNGER - ACTION/INSANITY. Example: HD_S (Hate/Dislike Self-eat), VH_NS (Very-hungry Near-sane)

    // Generic
    HateDislikes_Self("HD_S"),
    HateDislikes_Reject("HD_R"),

    Neutral_Accept("N_A"),
    Neutral_Reject("N_R"),

    Likes_Accept("LI_A"),
    Likes_Reject("LI_R"),

    Loves_Reject("LO_R"),

    // Dedicated
    VeryHungry_VeryInsane("VH_VI"),
    VeryHungry_Insane("VH_I"),
    VeryHungry_NearSane("VH_NS"),

    Hungry_VeryInsane("H_VI"),
    Hungry_Insane("H_I"),
    Hungry_NearSane("H_NS"),

    Neutral_VeryInsane("N_VI"),
    Neutral_Insane("N_I"),
    Neutral_NearSane("N_NS"),

    Full_VeryInsane("F_VI"),
    Full_Insane("F_I"),
    Full_NearSane("F_NS");

    private final String id;
    private static final Map<String, FeedResponseIdentifier> BY_ID = new HashMap<>();

    static {
        for (FeedResponseIdentifier identifier : values()) {
            BY_ID.put(identifier.id, identifier);
        }
    }

    FeedResponseIdentifier(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static FeedResponseIdentifier fromID(String id) {
        FeedResponseIdentifier result = BY_ID.get(id);
        if (result == null) {
            throw new IllegalArgumentException("Unknown FeedResponseIdentifier ID: " + id);
        }
        return result;
    }
}