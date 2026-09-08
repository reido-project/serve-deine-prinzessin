package sdp.content.prinzessins.kyoko.dialogues;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueBank;
import sdp.modules.dialogue.interfaces.GiveDialogueInterface;
import sdp.shared.utils.ArrayUtil;

import static sdp.content.prinzessins.kyoko.assets.KyokoSprite.*;


public class KyokoGiveDialogue extends DialogueBank implements GiveDialogueInterface {
    public Dialogue[] hated() {
        Dialogue[][] hatedVariants = {
            new Dialogue[]{ // Variant 1
                p("This isn't just useless, it's a liability!", Pissed),
                p("I advise you to destroy it before it causes actual harm!", Objecting)
            },

            new Dialogue[]{ // Variant 2
                p("There is no hidden value here, only clear negligence.", Pissed),
                p("Don't bring such hazards near me again!", Objecting)
            },

            new Dialogue[]{ // Variant 3
                p("Are you attempting to sabotage me, or is your judgment simply this compromised?", Pissed),
                p("Remove it immediately!", Objecting)
            },

            new Dialogue[]{ // Variant 4
                p("What possessed you to give me this?", Pissed),
                y("Raja Iblis Alas Baluran possessed me."),
                p("I regret asking.", Indifferent),
                n("..."),
                p("What are you waiting for?", Defensive),
                p("Dispose of it right now!", Objecting)
            }
        };
        return ArrayUtil.randomizeArray(hatedVariants, 1)[0];
    }

    public Dialogue[] disliked() {
        Dialogue[][] dislikedVariants = {
            new Dialogue[]{ // Variant 1
                p("Is there a specific reason you brought this to me, or are you just testing my patience?", Defensive)
            },

            new Dialogue[]{ // Variant 2
                p("I fail to see how this serves any logical purpose.", Indifferent),
                p("Please take it back.", Defensive)
            },

            new Dialogue[]{ // Variant 3
                p("I expected a bit more thoughtfulness from you.", Serious),
                p("This is completely unnecessary.", Defensive)
            },

            new Dialogue[]{ // Variant 4
                p("A bizarre item with zero investigative or practical value.", Defensive),
                p("I don't want it.", Indifferent)
            }
        };
        return ArrayUtil.randomizeArray(dislikedVariants, 1)[0];
    }

    public Dialogue[] neutral() {
        Dialogue[][] neutralVariants = {
            new Dialogue[]{ // Variant 1
                p("An unusual item, but I don't have a immediate use for it.", Indifferent),
                p("Still, I appreciate the gesture.")
            },

            new Dialogue[]{ // Variant 2
                p("Thank you.", Indifferent),
                p("I'll put it away for now.")
            },

            new Dialogue[]{ // Variant 3
                p("Every object has its place, even if its purpose isn't clear yet.", Indifferent),
                p("Thank you.")
            }
        };
        return ArrayUtil.randomizeArray(neutralVariants, 1)[0];
    }

    public Dialogue[] liked() {
        Dialogue[][] likedVariants = {
            new Dialogue[]{ // Variant 1
                p("I see you put some thought into picking this out.", Satisfied),
                p("Thank you, I'll keep it stored safely.", Shy)
            },

            new Dialogue[]{ // Variant 2
                p("This could prove useful down the line.", Thinking),
                p("I appreciate you sharing this with me.", Shy)
            },

            new Dialogue[]{ // Variant 3
                p("Thank you for thinking of me when you found this.", Shy),
                p("It’s practical, and I’m sure I can put it to good use.", Satisfied)
            }
        };
        return ArrayUtil.randomizeArray(likedVariants, 1)[0];
    }

    public Dialogue[] loved() {
        Dialogue[][] lovedVariants = {
            new Dialogue[]{ // Variant 1
                p("You have rather unique taste.", Satisfied),
                p("But I must admit, this is one of the more intriguing items I've received from you.", Flustered),
                p("Thank you.", Embarrassed),
                n("WAAAAAAAAAAAAAAAAAAAAA!!! MEINE PRINZESSIN LOVED IT >//<")
            },

            new Dialogue[]{ // Variant 2
                p("How did you know I was looking for something like this?", Surprised),
                p("That's an impressive deduction.", Satisfied),
                p("You actually understood my preferences."),
                p("I truly appreciate it.", Embarrassed),
                n("WUOOOOOOGHHH!!! ANO KAO!!! SUGOKU KAWAII!!! >//<")
            },

            new Dialogue[]{ // Variant 3
                p("A fascinating object...", Focused),
                p("This item holds information far beyond the surface.", Thinking),
                p("Excellent choice, thank you for bringing it to me.", Embarrassed),
                n("AGHHH OMAYGOT! SHE'S SO DAMN KAWAII WHEN SHE'S EMBARRASSED LIKE THAT!!! >//<")
            }
        };
        return ArrayUtil.randomizeArray(lovedVariants, 1)[0];
    }
}
