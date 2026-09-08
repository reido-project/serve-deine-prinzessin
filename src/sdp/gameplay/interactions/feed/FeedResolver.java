package sdp.gameplay.interactions.feed;

import sdp.characters.Preference;

public final class FeedResolver {

    public FeedResult resolve(
        Preference affection,
        int hunger,
        int insanity
    ) {

        HungerState hungerState =
            HungerState.from(hunger);

        InsanityState insanityState =
            InsanityState.from(insanity);

        boolean accepted =
            isAccepted(
                affection,
                hungerState
            );

        boolean selfEat =
            !accepted
                && isSelfEat(
                affection,
                hungerState
            );

        int baseInsanityGain =
            getBaseInsanityGain(
                accepted,
                selfEat,
                insanityState
            );

        int insanityModifier =
            getInsanityModifier(
                affection,
                hungerState,
                insanityState,
                accepted
            );

        int totalInsanityGain =
            baseInsanityGain
                + insanityModifier;

        boolean dedicated =
            isDedicated(
                affection,
                hungerState
            );

        FeedState state =
            new FeedState(
                affection,
                hungerState,
                insanityState
            );

        return new FeedResult(
            state,
            accepted,
            selfEat,
            baseInsanityGain,
            insanityModifier,
            totalInsanityGain,
            dedicated
        );
    }

    // =========================================================
    // ACCEPTANCE
    // =========================================================

    private boolean isAccepted(
        Preference affection,
        HungerState hunger
    ) {
        return switch (affection) {

            case HATED, DISLIKED ->
                false;

            case NEUTRAL ->
                hunger == HungerState.VERY_HUNGRY
                    || hunger == HungerState.HUNGRY;

            case LIKED ->
                hunger == HungerState.VERY_HUNGRY
                    || hunger == HungerState.HUNGRY
                    || hunger == HungerState.NEUTRAL;

            case LOVED ->
                hunger != HungerState.VERY_FULL;
        };
    }

    // =========================================================
    // SELF EAT
    // =========================================================

    private boolean isSelfEat(
        Preference affection,
        HungerState hunger
    ) {
        return (
            affection == Preference.HATED
                || affection == Preference.DISLIKED
        )
            && (
            hunger == HungerState.VERY_HUNGRY
                || hunger == HungerState.HUNGRY
        );
    }

    // =========================================================
    // BASE INSANITY
    // =========================================================

    private int getBaseInsanityGain(
        boolean accepted,
        boolean selfEat,
        InsanityState insanity
    ) {
        if (accepted) {
            return switch (insanity) {
                case VERY_INSANE -> 2;
                case INSANE -> 5;
                case NEAR_SANE -> 8;
            };
        }

        if (selfEat) {
            return switch (insanity) {
                case VERY_INSANE -> -12;
                case INSANE -> -8;
                case NEAR_SANE -> -4;
            };
        }

        return switch (insanity) {
            case VERY_INSANE -> -8;
            case INSANE -> -5;
            case NEAR_SANE -> -2;
        };
    }

    // =========================================================
    // INSANITY MODIFIER
    // =========================================================

    private int getInsanityModifier(
        Preference affection,
        HungerState hunger,
        InsanityState insanity,
        boolean accepted
    ) {
        return switch (affection) {

            case HATED ->
                -3;

            case DISLIKED ->
                -1;

            case NEUTRAL ->
                accepted ? 2 : 0;

            case LIKED ->
                accepted ? 3 : 2;

            case LOVED ->
                getLovesModifier(
                    hunger,
                    insanity,
                    accepted
                );
        };
    }

    private int getLovesModifier(
        HungerState hunger,
        InsanityState insanity,
        boolean accepted
    ) {
        if (
            hunger == HungerState.VERY_FULL
                && !accepted
        ) {
            return switch (insanity) {
                case VERY_INSANE -> 4;
                case INSANE -> 3;
                case NEAR_SANE -> 1;
            };
        }

        return 4;
    }

    // =========================================================
    // RESPONSE TYPE
    // =========================================================

    private boolean isDedicated(
        Preference affection,
        HungerState hunger
    ) {
        /*
         * Spreadsheet:
         *
         * HATED/DISLIKED -> Generic
         * Neutral hungry -> Dedicated
         * Neutral refusal -> Generic
         * LIKED -> Dedicated
         * LOVED -> Dedicated
         */

        if (
            affection == Preference.HATED
                || affection == Preference.DISLIKED
        ) {
            return false;
        }

        return affection != Preference.NEUTRAL
            || (hunger != HungerState.NEUTRAL
            && hunger != HungerState.FULL
            && hunger != HungerState.VERY_FULL);
    }
}