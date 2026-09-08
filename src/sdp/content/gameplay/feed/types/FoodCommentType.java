package sdp.content.gameplay.feed.types;

public enum FoodCommentType {
    Neutral_VeryInsane("N_VI"),
    Neutral_Insane("N_I"),
    Neutral_NearSane("N_NS"),

    Likes_VeryInsane("LI_VI"),
    Likes_Insane("LI_I"),
    Likes_NearSane("LI_NS"),

    Loves_VeryInsane("LO_VI"),
    Loves_Insane("LO_I"),
    Loves_NearSane("LO_NS");

    private final String id;

    FoodCommentType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static FoodCommentType fromID(String id) {
        for (FoodCommentType type : values()) {
            if (type.id.equalsIgnoreCase(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown FoodCommentType ID: " + id);
    }
}