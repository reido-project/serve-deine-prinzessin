package sdp.dialogues.kyoko;

import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueInterface;
import sdp.gameplay.interactions.feed.FeedResponseGroup;
import sdp.gameplay.interactions.feed.FeedResult;
import sdp.gameplay.interactions.feed.InsanityState;
import sdp.misc.Util;

public class FeedDialogue extends DialogueInterface {
    private final FeedInsanityComment insanityComment;

    public FeedDialogue() {
        this.insanityComment = new FeedInsanityComment();
    }

    public Dialogue[] get(FeedResult result) {
        if (result == null) {
            throw new IllegalArgumentException(
                "FeedResult cannot be null."
            );
        }

        if (result.dedicated()) {
            return getDedicated(result);
        }

        FeedResponseGroup group = FeedResponseGroup.from(result);

        return getGeneric(
            group,
            result.state().insanity()
        );
    }

    public Dialogue[] getDedicated(FeedResult result) {
        throw new UnsupportedOperationException(
            "Dedicated feed dialogue has not been implemented yet."
        );
    }

    public Dialogue[] getGeneric(FeedResponseGroup group, InsanityState insanity) {
        Dialogue[] response =
            switch (group) {
                case HATES_DISLIKES_SELF_EAT -> hatesDislikesSelfEat();

                case HATES_DISLIKES_REFUSE -> hatesDislikesRefuse();

                case NEUTRAL_REFUSE -> neutralRefuse();
            };

        Dialogue[] comment = insanityComment.get(insanity);

        return Util.combine(comment, response);
    }

    private Dialogue[] hatesDislikesSelfEat() {
        Dialogue[][] variants = {
            new Dialogue[]{
                d(p, "Generic self-eat response")
            },
        };
        return Util.randomizeArray(variants, 1)[0];
    }

    private Dialogue[] hatesDislikesRefuse() {
        Dialogue[][] variants = {
            new Dialogue[]{
                d(p, "Generic refusal response")
            },
        };
        return Util.randomizeArray(variants, 1)[0];
    }

    private Dialogue[] neutralRefuse() {
        Dialogue[][] variants = {
            new Dialogue[]{
                d(p, "Generic neutral refusal response")
            },
        };
        return Util.randomizeArray(variants, 1)[0];
    }
}