package sdp.content.prinzessins;

import sdp.content.gameplay.inventory.items.ItemID;

import static sdp.content.gameplay.inventory.items.ItemID.*;

public enum Prinzessin {
    KYOKO(
        "Kyoko Kirigiri", "Kyoko",
        30, 50, 100, 0,
        new ItemID[]{
            ITEM_01, ITEM_02, ITEM_03, ITEM_04, ITEM_05, ITEM_06, ITEM_07, ITEM_08, ITEM_09, ITEM_10,
            ITEM_11, ITEM_12, ITEM_13, ITEM_14, ITEM_15, ITEM_16, ITEM_17, ITEM_18, ITEM_19, ITEM_20,
            ITEM_21, ITEM_22, ITEM_23, ITEM_24, ITEM_25, ITEM_26, ITEM_27, ITEM_28, ITEM_29, ITEM_30,
        }
    ),

    CHIAKI(
        "Chiaki Nanami", "Chiaki",
        30, 50, 100, 0,
        new ItemID[]{
            ITEM_01, ITEM_02, ITEM_03, ITEM_04, ITEM_05, ITEM_06, ITEM_07, ITEM_08, ITEM_09, ITEM_10,
            ITEM_11, ITEM_12, ITEM_13, ITEM_14, ITEM_15, ITEM_16, ITEM_17, ITEM_18, ITEM_19, ITEM_20,
            ITEM_21, ITEM_22, ITEM_23, ITEM_24, ITEM_25, ITEM_26, ITEM_27, ITEM_28, ITEM_29, ITEM_30,
        }
    ),

    KAZUHA(
        "Kazuha Migiwa", "Kazuha",
        30, 50, 100, 0,
        new ItemID[]{
            ITEM_01, ITEM_02, ITEM_03, ITEM_04, ITEM_05, ITEM_06, ITEM_07, ITEM_08, ITEM_09, ITEM_10,
            ITEM_11, ITEM_12, ITEM_13, ITEM_14, ITEM_15, ITEM_16, ITEM_17, ITEM_18, ITEM_19, ITEM_20,
            ITEM_21, ITEM_22, ITEM_23, ITEM_24, ITEM_25, ITEM_26, ITEM_27, ITEM_28, ITEM_29, ITEM_30,
        }
    ),

    TOMOYO(
        "Tomoyo Sakagami", "Tomoyo",
        30, 50, 100, 0,
        new ItemID[]{
            ITEM_01, ITEM_02, ITEM_03, ITEM_04, ITEM_05, ITEM_06, ITEM_07, ITEM_08, ITEM_09, ITEM_10,
            ITEM_11, ITEM_12, ITEM_13, ITEM_14, ITEM_15, ITEM_16, ITEM_17, ITEM_18, ITEM_19, ITEM_20,
            ITEM_21, ITEM_22, ITEM_23, ITEM_24, ITEM_25, ITEM_26, ITEM_27, ITEM_28, ITEM_29, ITEM_30,
        }
    );

    // Identification
    private final String fullName;
    private final String nickname;


    // Initial Data
    private final int insanity;
    private final int affection;
    private final int hunger;
    private final long money;
    private final ItemID[] items;

    Prinzessin(String fullName, String nickname, int insanity, int affection, int hunger, long money, ItemID[] items) {
        this.fullName = fullName;
        this.nickname = nickname;
        this.insanity = insanity;
        this.affection = affection;
        this.hunger = hunger;
        this.money = money;
        this.items = items;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public int getInsanity() {
        return insanity;
    }

    public int getAffection() {
        return affection;
    }

    public int getHunger() {
        return hunger;
    }

    public long getMoney() {
        return money;
    }

    public ItemID[] getItems() {
        return items;
    }
}
