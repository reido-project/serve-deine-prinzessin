package sdp.content.gameplay.inventory;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueRepository;
import sdp.content.gameplay.inventory.behaviors.ConsumableBehavior;
import sdp.modules.behavior.UsageTrackable;
import sdp.content.gameplay.inventory.behaviors.ItemBehavior;
import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.content.gameplay.inventory.items.ItemType;
import sdp.content.gameplay.inventory.items.Preference;
import sdp.persistence.api.ItemDataAPI;
import sdp.persistence.persistent.dtos.InventoryDataDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryController implements ItemDataAPI {
    private final Map<ItemID, Item> itemMap;
    private final DialogueRepository dialogueRepository;

    public InventoryController(Item[] items, DialogueRepository dialogueRepository) {
        this.itemMap = Arrays.stream(items).collect(Collectors.toMap(Item::getId, item -> item));
        this.dialogueRepository = dialogueRepository;
    }

    public Dialogue[] use(ItemID itemID) {
        Item item = getItem(itemID);

        new ItemBehavior(resolveAffectionChange(item.getPreference())).execute();

        if (item.getType() == ItemType.CONSUMABLE) {
            ConsumableBehavior consumableBehavior = item.getBehavior();

            if (!item.isExhausted() && consumableBehavior != null) {
                consumableBehavior.execute();
            }

            return dialogueRepository.getSpecialItem(itemID);
        }

        removeItem(item);

        if (item.isSpecial()) {
            return dialogueRepository.getSpecialItem(itemID);
        }

        return dialogueRepository.getGive(item.getPreference());
    }

    public Item[] resolveInventory(ItemID[] itemIDs) {
        List<Item> resolvedItems = new ArrayList<>();

        for(ItemID itemID : itemIDs){
            resolvedItems.add(itemMap.get(itemID));
        }

        return resolvedItems.toArray(new Item[0]);
    }

    public Item[] resolveInventory(List<InventoryDataDTO> itemData){
        List<Item> resolvedItems = new ArrayList<>();

        for(InventoryDataDTO item : itemData){
            Item resolvedItem = itemMap.get(item.id());

            if(item.exhausted()){
                resolvedItem.setExhaustedTrue();
            }

            ConsumableBehavior consumableBehavior = resolvedItem.getBehavior();

            if(item.usage() > -1 && consumableBehavior instanceof UsageTrackable){
                ((UsageTrackable) consumableBehavior).setUsageCount(item.usage());
            }

            resolvedItems.add(resolvedItem);
        }
        return resolvedItems.toArray(new Item[0]);
    }

    private int resolveAffectionChange(Preference preference){
        return switch (preference){
            case HATED -> -10;
            case DISLIKED -> -5;
            case NEUTRAL -> 0;
            case LIKED -> 5;
            case LOVED -> 10;
            case null -> 0;
        };
    }
}
