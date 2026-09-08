package sdp.content.prinzessins;

import sdp.modules.dialogue.interfaces.*;
import sdp.content.gameplay.inventory.items.Item;
import sdp.content.gameplay.talk.topics.Topic;
import sdp.content.prinzessins.kyoko.dialogues.*;
import sdp.content.prinzessins.kyoko.items.KyokoItem;
import sdp.content.prinzessins.kyoko.topics.KyokoTopic;

public class PrinzessinAttributes {
    private final Item[] items;
    private final Topic[] topics;

    private final StoryDialogueInterface storyDialogue;
    private final TalkDialogueInterface talkDialogue;
    private final TeaseDialogueInterface teaseDialogue;

    private final GiveDialogueInterface giveDialogue;
    private final SpecialItemDialogue specialItemDialogue;

    private final GenericFeedDialogueInterface genericFeedDialogue;
    private final DedicatedFeedDialogueInterface dedicatedFeedDialogue;
    private final FoodCommentDialogueInterface foodCommentDialogue;

    public PrinzessinAttributes(Prinzessin prinzessin) {
        switch(prinzessin) {
            case KYOKO -> {
                items = KyokoItem.getItems();
                topics = KyokoTopic.getTopics();

                storyDialogue = new KyokoStoryDialogue();

                talkDialogue = new KyokoTalkDialogue();

                teaseDialogue = new KyokoTeaseDialogue();

                giveDialogue = new KyokoGiveDialogue();
                specialItemDialogue = new KyokoSpecialItemDialogue();

                genericFeedDialogue = new KyokoGenericFeedDialogue();
                dedicatedFeedDialogue = new KyokoDedicatedFeedDialogue();
                foodCommentDialogue = new KyokoFoodCommentDialogue();
            }
            //case CHIAKI -> {}
            //case KAZUHA -> {}
            //case TOMOYO -> {}
            default -> {throw new IllegalStateException("Invalid Prinzessin: " + prinzessin);}
        }
    }


    // Getters
    public Item[] getItems() {
        return items;
    }

    public Topic[] getTopics() {
        return topics;
    }

    public StoryDialogueInterface getStoryDialogue() {
        return storyDialogue;
    }

    public TalkDialogueInterface getTalkDialogue() {
        return talkDialogue;
    }

    public TeaseDialogueInterface getTeaseDialogue() {
        return teaseDialogue;
    }

    public GiveDialogueInterface getGiveDialogue() {
        return giveDialogue;
    }

    public SpecialItemDialogue getSpecialItemDialogue() {
        return specialItemDialogue;
    }

    public GenericFeedDialogueInterface getGenericFeedDialogue() {
        return genericFeedDialogue;
    }

    public DedicatedFeedDialogueInterface getDedicatedFeedDialogue() {
        return dedicatedFeedDialogue;
    }

    public FoodCommentDialogueInterface getFoodCommentDialogue() {
        return foodCommentDialogue;
    }
}
