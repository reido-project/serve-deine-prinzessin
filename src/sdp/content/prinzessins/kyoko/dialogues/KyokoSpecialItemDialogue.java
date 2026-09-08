package sdp.content.prinzessins.kyoko.dialogues;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueBank;
import sdp.modules.dialogue.interfaces.SpecialItemDialogue;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.persistence.DataController;

public class KyokoSpecialItemDialogue extends DialogueBank implements SpecialItemDialogue {
    @Override
    public Dialogue[] get(ItemID itemID) {
        return switch (itemID) {
            case ITEM_01 -> adapterRokok();
            case ITEM_08 -> distrustAudioPlayer();
            case ITEM_10 -> jariSukuna();
            case ITEM_12 -> kotaJahat(itemID);
            case ITEM_13 -> vhsDespair();
            case ITEM_17 -> lifeNote();
            case ITEM_19 -> microphonePelunas();
            case ITEM_22 -> phoneWave();
            case ITEM_25 -> ramuanPosesiIblis();
            case ITEM_28 -> topiSherlock();
            default -> throw new IllegalArgumentException("Invalid item ID: " + itemID);
        };
    }

    private Dialogue[] adapterRokok() {
        return new Dialogue[0];
    }

    private Dialogue[] distrustAudioPlayer() {
        return new Dialogue[0];
    }

    private Dialogue[] jariSukuna() {
        return new Dialogue[0];
    }

    private static Dialogue[] kotaJahat(ItemID itemID) {
        int kotaJahatUsed = DataController.getInstance().getInventoryData().getItemUsageCount(itemID);

        if (kotaJahatUsed >= 11 || kotaJahatUsed < 0) {
            throw new IllegalArgumentException("Invalid Kota Jahat Usage: " + kotaJahatUsed);
        }

        Dialogue[][] variants = {
            new Dialogue[]{ // Use 1

            },
            new Dialogue[]{ // Use 2

            },
            new Dialogue[]{ // Use 3

            },
            new Dialogue[]{ // Use 4

            },
            new Dialogue[]{ // Use 5

            },
            new Dialogue[]{ // Use 6

            },
            new Dialogue[]{ // Use 7

            },
            new Dialogue[]{ // Use 8

            },
            new Dialogue[]{ // Use 9

            },
            new Dialogue[]{ // Use 10

            },
            new Dialogue[]{ // Use 11

            }
        };

        return variants[kotaJahatUsed];
    }

    private Dialogue[] vhsDespair() {
        return new Dialogue[0];
    }

    private Dialogue[] lifeNote() {
        return new Dialogue[0];
    }

    private Dialogue[] microphonePelunas() {
        return new Dialogue[0];
    }

    private Dialogue[] phoneWave() {
        return new Dialogue[0];
    }

    private Dialogue[] ramuanPosesiIblis() {
        return new Dialogue[0];
    }

    private Dialogue[] topiSherlock() {
        return new Dialogue[0];
    }
}