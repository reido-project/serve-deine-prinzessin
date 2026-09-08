package sdp.modules.dialogue;

import sdp.content.gameplay.tease.type.TeaseResponseType;
import sdp.content.prinzessins.PrinzessinAttributes;
import sdp.modules.dialogue.interfaces.*;
import sdp.content.gameplay.feed.types.FeedResponseIdentifier;
import sdp.content.gameplay.feed.types.FeedResponseType;
import sdp.content.gameplay.feed.types.FoodCommentType;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.content.gameplay.inventory.items.Preference;
import sdp.content.gameplay.story.StoryState;
import sdp.content.gameplay.talk.topics.TopicID;
import sdp.content.stats.InsanityType;
import sdp.shared.utils.ArrayUtil;

public class DialogueRepository {
    private final StoryDialogueInterface storyDialogue;

    private final TalkDialogueInterface talkDialogue;

    private final TeaseDialogueInterface teaseDialogue;

    private final GiveDialogueInterface giveDialogue;
    private final SpecialItemDialogue specialItemDialogue;

    private final GenericFeedDialogueInterface genericFeedDialogue;
    private final DedicatedFeedDialogueInterface dedicatedFeedDialogue;
    private final FoodCommentDialogueInterface foodCommentDialogue;

    public DialogueRepository(PrinzessinAttributes prinzessinAttributes){
        storyDialogue = prinzessinAttributes.getStoryDialogue();
        talkDialogue = prinzessinAttributes.getTalkDialogue();
        teaseDialogue = prinzessinAttributes.getTeaseDialogue();
        giveDialogue = prinzessinAttributes.getGiveDialogue();
        specialItemDialogue = prinzessinAttributes.getSpecialItemDialogue();
        genericFeedDialogue = prinzessinAttributes.getGenericFeedDialogue();
        dedicatedFeedDialogue = prinzessinAttributes.getDedicatedFeedDialogue();
        foodCommentDialogue = prinzessinAttributes.getFoodCommentDialogue();
    }

    public Dialogue[] getStory(StoryState story){
        return switch (story) {
            case PROLOGUE -> storyDialogue.prologue();
            case MAX_INSANITY_END -> storyDialogue.maxInsanityEnd();
            case SANE_END -> storyDialogue.saneEnd();
            case MAX_AFF_END -> storyDialogue.maxAffEnd();
            case NO_AFF_END -> storyDialogue.noAffEnd();
            case DEAD_END -> storyDialogue.deadEnd();
            default -> throw new IllegalArgumentException("There is no dialogue for this state");
        };
    }

    public Dialogue[] getTalk(TopicID topic){
        return switch (topic) {
            case TOPIC_01 -> talkDialogue.topic01();
            case TOPIC_02 -> talkDialogue.topic02();
            case TOPIC_03 -> talkDialogue.topic03();
            case TOPIC_04 -> talkDialogue.topic04();
            case TOPIC_05 -> talkDialogue.topic05();
            case TOPIC_06 -> talkDialogue.topic06();
            case TOPIC_07 -> talkDialogue.topic07();
            case TOPIC_08 -> talkDialogue.topic08();
            case TOPIC_09 -> talkDialogue.topic09();
            case TOPIC_10 -> talkDialogue.topic10();
            case TOPIC_11 -> talkDialogue.topic11();
            case TOPIC_12 -> talkDialogue.topic12();
            case TOPIC_13 -> talkDialogue.topic13();
            case TOPIC_14 -> talkDialogue.topic14();
            case TOPIC_15 -> talkDialogue.topic15();
            case TOPIC_16 -> talkDialogue.topic16();
        };
    }

    public Dialogue[] getTease(TeaseResponseType type){
        Dialogue[] response = switch (type){
            case Hates_HungryVeryHungry -> teaseDialogue.hatesHungryVeryHungry();
            case Hates -> teaseDialogue.hates();
            case Dislikes_HungryVeryHungry -> teaseDialogue.dislikesHungryVeryHungry();
            case Dislikes -> teaseDialogue.dislikes();
            case Neutral_HungryVeryHungry -> teaseDialogue.neutralHungryVeryHungry();
            case Neutral -> teaseDialogue.neutral();
            case Likes_HungryVeryHungry -> teaseDialogue.likesHungryVeryHungry();
            case Likes -> teaseDialogue.likes();
            case Loves_VeryHungry -> teaseDialogue.lovesVeryHungry();
            case Loves -> teaseDialogue.loves();
        };

        return ArrayUtil.addEntries(getTeaseVariant(), response);
    }

    private Dialogue[] getTeaseVariant(){
        return teaseDialogue.variants();
    }

    public Dialogue[] getGive(Preference preference){
        return switch (preference){
            case HATED -> giveDialogue.hated();
            case DISLIKED -> giveDialogue.disliked();
            case NEUTRAL -> giveDialogue.neutral();
            case LIKED -> giveDialogue.liked();
            case LOVED -> giveDialogue.loved();
        };
    }

    public Dialogue[] getSpecialItem(ItemID item){
        return specialItemDialogue.get(item);
    }

    public Dialogue[] getFeed(FeedResponseType type, FeedResponseIdentifier identifier, FoodCommentType foodComment, InsanityType insanity){
        Dialogue[] foodCommentDialogue = ArrayUtil.addEntries(getGivenFood(insanity), getFoodComment(foodComment));
        Dialogue[] responseDialogue;

        if(type == FeedResponseType.DEDICATED){
            responseDialogue = getDedicatedFeed(identifier);
        }
        else{
            responseDialogue = getGenericFeed(identifier);
        }

        return ArrayUtil.addEntries(foodCommentDialogue, responseDialogue);
    }

    private Dialogue[] getGenericFeed(FeedResponseIdentifier identifier){
        return switch (identifier) {
            case HateDislikes_Self -> genericFeedDialogue.hateDislikeSelf();
            case HateDislikes_Reject -> genericFeedDialogue.hateDislikeReject();

            case Neutral_Accept -> genericFeedDialogue.neutralAccept();
            case Neutral_Reject -> genericFeedDialogue.neutralReject();

            case Likes_Accept -> genericFeedDialogue.likeAccept();
            case Likes_Reject -> genericFeedDialogue.likeReject();

            case Loves_Reject -> genericFeedDialogue.loveReject();

            default -> throw new IllegalStateException("Unexpected value: " + identifier);
        };
    }

    private Dialogue[] getDedicatedFeed(FeedResponseIdentifier identifier){
        return switch (identifier){
            case VeryHungry_VeryInsane -> dedicatedFeedDialogue.veryHungryVeryInsane();
            case VeryHungry_Insane -> dedicatedFeedDialogue.veryHungryInsane();
            case VeryHungry_NearSane -> dedicatedFeedDialogue.veryHungryNearSane();

            case Hungry_VeryInsane -> dedicatedFeedDialogue.hungryVeryInsane();
            case Hungry_Insane -> dedicatedFeedDialogue.hungryInsane();
            case Hungry_NearSane -> dedicatedFeedDialogue.hungryNearSane();

            case Neutral_VeryInsane -> dedicatedFeedDialogue.neutralVeryInsane();
            case Neutral_Insane -> dedicatedFeedDialogue.neutralInsane();
            case Neutral_NearSane -> dedicatedFeedDialogue.neutralNearSane();

            case Full_VeryInsane -> dedicatedFeedDialogue.fullVeryInsane();
            case Full_Insane -> dedicatedFeedDialogue.fullInsane();
            case Full_NearSane -> dedicatedFeedDialogue.fullNearSane();

            default -> throw new IllegalStateException("Unexpected value: " + identifier);
        };
    }

    private Dialogue[] getFoodComment(FoodCommentType foodComment){
        return switch (foodComment){
            case Neutral_VeryInsane -> foodCommentDialogue.neutralVeryInsane();
            case Neutral_Insane -> foodCommentDialogue.neutralInsane();
            case Neutral_NearSane -> foodCommentDialogue.neutralNearSane();
            case Likes_VeryInsane -> foodCommentDialogue.likeVeryInsane();
            case Likes_Insane -> foodCommentDialogue.likeInsane();
            case Likes_NearSane -> foodCommentDialogue.likeNearSane();
            case Loves_VeryInsane -> foodCommentDialogue.loveVeryInsane();
            case Loves_Insane -> foodCommentDialogue.loveInsane();
            case Loves_NearSane ->  foodCommentDialogue.loveNearSane();
            case null -> new Dialogue[0];
        };
    }

    private Dialogue[] getGivenFood(InsanityType insanity){
        return switch (insanity){
            case VERY_INSANE -> foodCommentDialogue.veryInsaneFood();
            case INSANE -> foodCommentDialogue.insaneFood();
            case NEAR_SANE -> foodCommentDialogue.nearSaneFood();
            default -> throw new IllegalStateException("Unexpected value: " + insanity);
        };
    }
}
