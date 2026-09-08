package sdp.content.prinzessins.kyoko.items.behaviors;

import sdp.content.gameplay.inventory.behaviors.ConsumableBehavior;
import sdp.modules.behavior.UsageTrackable;
import sdp.content.gameplay.inventory.items.ItemID;

public class KotaJahatBehavior extends ConsumableBehavior implements UsageTrackable {
    private int usageCount;

    public KotaJahatBehavior(ItemID itemID) { super(itemID); }

    @Override
    public void execute() {
        changeInsanity(1);
        usageCount++;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }
}
