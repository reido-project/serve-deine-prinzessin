package sdp.items;

import sdp.characters.Character;
import sdp.characters.Preference;
import sdp.dialogues.DialogueController;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;
import sdp.items.behaviors.JariSukunaBehavior;
import sdp.items.behaviors.KotaJahatBehavior;
import sdp.items.behaviors.MicrophonePelunasBehavior;
import sdp.items.behaviors.RamuanPosesiBehavior;

import java.util.Set;

public class ItemController {

    private final RuntimeData data;
    private final DialogueController dialogueController;
    private static final int GIFT_HUNGER_COST = 10;

    public ItemController(DialogueController dialogueController) {
        if (dialogueController == null) {
            throw new IllegalArgumentException(
                "DialogueController cannot be null."
            );
        }

        this.data = DataController.getInstance().getRuntimeData();
        this.dialogueController = dialogueController;
    }

    // =========================================================
    // CONSUMABLE
    // =========================================================

    public void use(ItemList item) {
        validateItem(item);

        if (item.getItemType() != ItemType.CONSUMABLE) {
            throw new IllegalArgumentException(
                "Item is not a consumable: " + item
            );
        }

        validateInventory(item);

        ItemBehavior behavior = resolveBehavior(item);

        behavior.use();

        handlePostUseDialogue(item);
    }

    // =========================================================
    // GIFT
    // =========================================================

    // =========================================================
// GIFT
// =========================================================

    public void give(ItemList item) {
        validateItem(item);

        if (item.getItemType() != ItemType.GIFT) {
            throw new IllegalArgumentException(
                "Item is not a gift: " + item
            );
        }

        validateInventory(item);

        Character prinzessin = data.getPrinzessin();

        if (prinzessin == null) {
            throw new IllegalStateException(
                "Cannot give item without a Prinzessin."
            );
        }

        Preference preference = prinzessin.getPreference(item);

        applyAffection(preference);
        data.getInventory().remove(item);
        if (prinzessin.hasSpecial(item)) {
            dialogueController.startSpecialItem(item);
            return;
        }

        dialogueController.startPreference(preference);
        data.setPrinzessinHunger(data.getPrinzessinHunger() - GIFT_HUNGER_COST);
    }

    private void applyAffection(Preference preference) {
        int affectionChange = switch (preference) {
            case LOVED -> 10;
            case LIKED -> 5;
            case NEUTRAL -> 0;
            case DISLIKED -> -5;
            case HATED -> -10;
        };

        data.setPrinzessinAffection(
            data.getPrinzessinAffection() + affectionChange
        );
    }

    // =========================================================
    // BEHAVIOR
    // =========================================================

    private ItemBehavior resolveBehavior(ItemList item) {
        return switch (item) {
            case JARI_SUKUNA -> new JariSukunaBehavior();
            case MIC_PELUNAS -> new MicrophonePelunasBehavior();
            case KOTA_JAHAT -> new KotaJahatBehavior();
            case RAMUAN_POSESI -> new RamuanPosesiBehavior();

            default -> throw new IllegalArgumentException(
                "No behavior defined for item: " + item
            );
        };
    }

    // =========================================================
    // POST USE
    // =========================================================

    private void handlePostUseDialogue(ItemList item) {
        switch (item) {
            case RAMUAN_POSESI -> {
                dialogueController.startSpecialItem(item);
            }

            case MIC_PELUNAS -> {
                if (data.isMicrophonePelunasBroken()) {
                    dialogueController.startSpecialItem(item);
                }
            }

            default -> {
                // No dialogue after use.
            }
        }
    }

    // =========================================================
    // VALIDATION
    // =========================================================

    private void validateItem(ItemList item) {
        if (item == null) {
            throw new IllegalArgumentException(
                "Item cannot be null."
            );
        }
    }

    private void validateInventory(ItemList item) {
        if (data.getInventory() == null) {
            throw new IllegalStateException(
                "Inventory has not been initialized."
            );
        }

        if (!data.getInventory().contains(item)) {
            throw new IllegalStateException(
                "Item is not in inventory: " + item
            );
        }
    }

    public Set<ItemList> getInventory() {
        return data.getInventory();
    }
}