package sdp.content.gameplay.tease;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueRepository;
import sdp.content.gameplay.tease.behaviors.TeaseBehavior;
import sdp.content.gameplay.tease.type.TeaseResponseType;
import sdp.content.stats.AffectionType;
import sdp.content.stats.HungerType;
import sdp.content.stats.StatParser;

public class TeaseController {
    private final DialogueRepository dialogueRepository;

    public TeaseController(DialogueRepository dialogueRepository) {
        this.dialogueRepository = dialogueRepository;
    }

    public Dialogue[] tease() {
        AffectionType affection = StatParser.getAffection();
        HungerType hunger = StatParser.getHunger();

        validateStats(affection, hunger);

        new TeaseBehavior(resolveAffectionChange(affection, hunger)).execute();

        return dialogueRepository.getTease(resolveResponseType(affection, hunger));
    }

    private void validateStats(AffectionType affection, HungerType hunger) {
        if (affection == AffectionType.NO_AFFECTION || affection == AffectionType.MAX_AFFECTION) {
            throw new IllegalStateException("Unexpected Affection: " + affection);
        }

        if (hunger == HungerType.DEAD) {
            throw new IllegalStateException("Unexpected Hunger: " + hunger);
        }
    }

    private TeaseResponseType resolveResponseType(AffectionType affection, HungerType hunger) {
        boolean isHungry = hunger == HungerType.HUNGRY || hunger == HungerType.VERY_HUNGRY;

        return switch (affection) {
            case HATES -> isHungry
                ? TeaseResponseType.Hates_HungryVeryHungry
                : TeaseResponseType.Hates;

            case DISLIKES -> isHungry
                ? TeaseResponseType.Dislikes_HungryVeryHungry
                : TeaseResponseType.Dislikes;

            case NEUTRAL -> isHungry
                ? TeaseResponseType.Neutral_HungryVeryHungry
                : TeaseResponseType.Neutral;

            case LIKES -> isHungry
                ? TeaseResponseType.Likes_HungryVeryHungry
                : TeaseResponseType.Likes;

            case LOVES -> hunger == HungerType.VERY_HUNGRY
                ? TeaseResponseType.Loves_VeryHungry
                : TeaseResponseType.Loves;

            case NO_AFFECTION, MAX_AFFECTION ->
                throw new IllegalStateException("Unexpected Affection: " + affection);
        };
    }

    private int resolveAffectionChange(AffectionType affection, HungerType hunger) {
        int baseAffectionChange = switch (affection) {
            case HATES -> -5;
            case DISLIKES -> -3;
            case NEUTRAL -> -2;
            case LIKES -> 0;
            case LOVES -> 1;
            case NO_AFFECTION, MAX_AFFECTION ->
                throw new IllegalStateException("Unexpected Affection: " + affection);
        };

        int hungerModifier = getHungerModifier(affection, hunger);

        return baseAffectionChange + hungerModifier;
    }

    private static int getHungerModifier(AffectionType affection, HungerType hunger) {
        int hungerModifier = 0;

        boolean isHungry = hunger == HungerType.HUNGRY || hunger == HungerType.VERY_HUNGRY;

        if (isHungry) {
            hungerModifier = switch (affection) {
                case HATES -> 2;
                case DISLIKES, NEUTRAL -> 1;
                case LIKES -> -1;
                case LOVES -> hunger == HungerType.VERY_HUNGRY ? -1 : 0;
                case NO_AFFECTION, MAX_AFFECTION ->
                    throw new IllegalStateException("Unexpected Affection: " + affection);
            };
        }
        return hungerModifier;
    }
}