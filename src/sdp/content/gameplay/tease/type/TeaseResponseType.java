package sdp.content.gameplay.tease.type;

import java.util.HashMap;
import java.util.Map;

public enum TeaseResponseType {
    Hates_HungryVeryHungry("H_HVH"),
    Hates("H"),

    Dislikes_HungryVeryHungry("D_HVH"),
    Dislikes("D"),

    Neutral_HungryVeryHungry("N_HVH"),
    Neutral("N"),

    Likes_HungryVeryHungry("LI_HVH"),
    Likes("LI"),

    Loves_VeryHungry("LO_VH"),
    Loves("LO");

    private final String id;
    private static final Map<String, TeaseResponseType> BY_ID = new HashMap<>();

    static {
        for (TeaseResponseType type : values()) {
            BY_ID.put(type.id, type);
        }
    }

    TeaseResponseType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static TeaseResponseType fromId(String id) {
        TeaseResponseType result = BY_ID.get(id);
        if (result == null) {
            throw new IllegalArgumentException("Unknown TeaseResponseType ID: " + id);
        }
        return result;
    }
}