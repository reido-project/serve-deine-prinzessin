package sdp.gameplay.interactions;

import sdp.characters.Preference;
import sdp.dialogues.DialogueController;
import sdp.gameplay.interactions.feed.FeedResolver;
import sdp.gameplay.interactions.feed.FeedResult;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

public class Feed {

    private static final int HUNGER_GAIN = 10;

    private final DialogueController dialogueController;
    private final RuntimeData runtimeData;
    private final FeedResolver resolver;

    public Feed(
        DialogueController dialogueController
    ) {
        if (dialogueController == null) {
            throw new IllegalArgumentException(
                "DialogueController cannot be null."
            );
        }

        this.dialogueController =
            dialogueController;

        this.runtimeData =
            DataController
                .getInstance()
                .getRuntimeData();

        this.resolver =
            new FeedResolver();
    }

    public FeedResult execute() {

        Preference affection =
            Preference.from(
                runtimeData.getPrinzessinAffection()
            );

        int hunger =
            runtimeData.getPrinzessinHunger();

        int insanity =
            runtimeData.getPlayerInsanity();

        FeedResult result =
            resolver.resolve(
                affection,
                hunger,
                insanity
            );

        dialogueController.startFeed(result);

        if (result.accepted() || result.selfEat()) {
            runtimeData.setPrinzessinHunger(
                hunger + HUNGER_GAIN
            );
        }

        runtimeData.setPlayerInsanity(
            insanity + result.totalInsanityGain()
        );

        return result;
    }
}