package sdp.content.gameplay.feed;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueRepository;
import sdp.content.gameplay.feed.behaviors.FeedBehavior;
import sdp.content.gameplay.feed.types.FeedResponseIdentifier;
import sdp.content.gameplay.feed.types.FeedResponseType;
import sdp.content.gameplay.feed.types.FoodCommentType;
import sdp.content.stats.AffectionType;
import sdp.content.stats.HungerType;
import sdp.content.stats.InsanityType;
import sdp.content.stats.StatParser;

public class FeedController {
    private static final int ACCEPT_FEED_INSANITY_CHANGE = 12;
    private static final int SELF_EAT_INSANITY_CHANGE = -15;
    private static final int REJECT_FEED_INSANITY_CHANGE = -12;
    private static final int HUNGER_CHANGE = 15;

    private final DialogueRepository dialogueRepository;

    public FeedController(DialogueRepository dialogueRepository) {
        this.dialogueRepository = dialogueRepository;
    }

    public Dialogue[] feed() {
        AffectionType affection = StatParser.getAffection();
        HungerType hunger = StatParser.getHunger();
        InsanityType insanity = StatParser.getInsanity();

        validateStats(affection, hunger, insanity);

        FeedResponseType responseType = resolveFeedResponseType(affection, hunger);
        Action action = resolveAction(affection, hunger);
        FeedResponseIdentifier identifier = resolveIdentifier(responseType, affection, hunger, insanity, action);
        FoodCommentType commentType = resolveCommentType(affection, insanity, action);

        int insanityChange = resolveInsanityChange(affection, insanity, action);
        int hungerChange = action == Action.REJECT ? 0 : HUNGER_CHANGE;

        new FeedBehavior(insanityChange, hungerChange).execute();

        return dialogueRepository.getFeed(responseType, identifier, commentType, insanity);
    }

    private enum Action {
        ACCEPT_FEED("A"),
        SELF_EAT("S"),
        REJECT("R");

        private final String id;

        Action(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    private void validateStats(AffectionType affection, HungerType hunger, InsanityType insanity) {
        if (affection == AffectionType.NO_AFFECTION || affection == AffectionType.MAX_AFFECTION) {
            throw new IllegalStateException("Unexpected Affection: " + affection);
        }

        if (insanity == InsanityType.SANE || insanity == InsanityType.MAX_INSANITY) {
            throw new IllegalStateException("Unexpected Insanity: " + insanity);
        }

        if (hunger == HungerType.DEAD) {
            throw new IllegalStateException("Unexpected Hunger: " + hunger);
        }
    }

    private Action resolveAction(AffectionType affection, HungerType hunger) {
        if (affection == AffectionType.LOVES && hunger != HungerType.VERY_FULL) {
            return Action.ACCEPT_FEED;
        }

        return switch (affection) {
            case HATES -> hunger == HungerType.VERY_HUNGRY
                ? Action.SELF_EAT : Action.REJECT;

            case DISLIKES -> hunger == HungerType.VERY_HUNGRY || hunger == HungerType.HUNGRY
                ? Action.SELF_EAT : Action.REJECT;

            case NEUTRAL -> hunger == HungerType.VERY_HUNGRY || hunger == HungerType.HUNGRY
                ? Action.ACCEPT_FEED : Action.REJECT;

            case LIKES -> hunger != HungerType.FULL && hunger != HungerType.VERY_FULL
                ? Action.ACCEPT_FEED : Action.REJECT;

            case LOVES -> Action.REJECT;

            case NO_AFFECTION, MAX_AFFECTION ->
                throw new IllegalStateException("Unexpected Affection: " + affection);
        };
    }

    private FeedResponseType resolveFeedResponseType(AffectionType affection, HungerType hunger) {
        if (affection == AffectionType.NO_AFFECTION || affection == AffectionType.MAX_AFFECTION) {
            throw new IllegalStateException("Unexpected Affection: " + affection);
        }

        if (hunger == HungerType.DEAD) {
            throw new IllegalStateException("Unexpected Hunger: " + hunger);
        }

        return affection == AffectionType.LOVES && hunger != HungerType.VERY_FULL
            ? FeedResponseType.DEDICATED
            : FeedResponseType.GENERIC;
    }

    private FeedResponseIdentifier resolveIdentifier(
        FeedResponseType responseType,
        AffectionType affection,
        HungerType hunger,
        InsanityType insanity,
        Action action
    ) {
        String id;

        if (responseType == FeedResponseType.DEDICATED) {
            id = hunger.getFeedResponseID() + "_" + insanity.getFeedResponseID();
        } else {
            id = affection.getFeedResponseID() + "_" + action.getId();
        }

        try {
            return FeedResponseIdentifier.fromID(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Unexpected FeedResponseIdentifier: " + id, e);
        }
    }

    private int resolveInsanityChange(AffectionType affection, InsanityType insanity, Action action) {
        int baseGain = switch (affection) {
            case HATES -> 0;
            case DISLIKES -> 2;
            case NEUTRAL -> action == Action.ACCEPT_FEED ? -2 : 3;
            case LIKES -> action == Action.ACCEPT_FEED ? -1 : 5;
            case LOVES -> action == Action.ACCEPT_FEED ? 0 : 6;
            case NO_AFFECTION, MAX_AFFECTION ->
                throw new IllegalStateException("Unexpected Affection: " + affection);
        };

        if (action == Action.ACCEPT_FEED) {
            double step = switch (insanity) {
                case VERY_INSANE -> 0.466;
                case INSANE -> 0.733;
                case NEAR_SANE -> 1.0;
                case SANE, MAX_INSANITY ->
                    throw new IllegalStateException("Unexpected Insanity: " + insanity);
            };

            return (int) Math.ceil(baseGain + ACCEPT_FEED_INSANITY_CHANGE * step);
        }

        double step = switch (insanity) {
            case VERY_INSANE -> 1.0;
            case INSANE -> 0.733;
            case NEAR_SANE -> 0.466;
            case SANE, MAX_INSANITY ->
                throw new IllegalStateException("Unexpected Insanity: " + insanity);
        };

        int insanityChange = action == Action.SELF_EAT
            ? SELF_EAT_INSANITY_CHANGE
            : REJECT_FEED_INSANITY_CHANGE;

        return (int) Math.floor(baseGain + insanityChange * step);
    }

    private FoodCommentType resolveCommentType(
        AffectionType affection,
        InsanityType insanity,
        Action action
    ) {
        if (action != Action.ACCEPT_FEED) {
            return null;
        }

        String id = affection.getFeedResponseID() + "_" + insanity.getFeedResponseID();

        try {
            return FoodCommentType.fromID(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Unexpected FoodCommentType: " + id, e);
        }
    }
}