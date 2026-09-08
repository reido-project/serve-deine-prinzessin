package sdp.dialogues.kyoko;

import static sdp.assets.sprite.SpriteState.*;

import sdp.characters.Preference;
import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueInterface;
import sdp.misc.Util;

public class PreferenceDialogue extends DialogueInterface {
    Dialogue[] get(Preference preference){
        switch(preference){
            case LOVED -> {return loved();}
            case LIKED -> {return liked();}
            case NEUTRAL -> {return neutral();}
            case DISLIKED -> {return disliked();}
            case HATED -> {return hated();}
        }
        throw new IllegalArgumentException("Invalid Preference");
    }

    private Dialogue[] loved(){
        Dialogue[][] lovedVariants = {
            new Dialogue[]{ // Variant 1
                d(p, "You have rather unique taste.", Satisfied),
                d(p, "But I must admit, this is one of the more intriguing items I've received from you.", Flustered),
                d(p, "Thank you.", Embarrassed),
                d(n, "WAAAAAAAAAAAAAAAAAAAAA!!! MEINE PRINZESSIN LOVED IT >//<")
            },

            new Dialogue[]{ // Variant 2
                d(p, "How did you know I was looking for something like this?", Surprised),
                d(p, "That's an impressive deduction.", Satisfied),
                d(p, "You actually understood my preferences."),
                d(p, "I truly appreciate it.", Embarrassed),
                d(n, "WUOOOOOOGHHH!!! ANO KAO!!! SUGOKU KAWAII!!! >//<")
            },

            new Dialogue[]{ // Variant 3
                d(p, "A fascinating object...", Focused),
                d(p, "This item holds information far beyond the surface.", Thinking),
                d(p, "Excellent choice, thank you for bringing it to me.", Embarrassed),
                d(n, "AGHHH OMAYGOT! SHE'S SO DAMN KAWAII WHEN SHE'S EMBARRASSED LIKE THAT!!! >//<")
            }
        };
        return Util.randomizeArray(lovedVariants, 1)[0];
    }

    private Dialogue[] liked(){
        Dialogue[][] likedVariants = {
            new Dialogue[]{ // Variant 1
                d(p, "I see you put some thought into picking this out.", Satisfied),
                d(p, "Thank you, I'll keep it stored safely.", Shy)
            },

            new Dialogue[]{ // Variant 2
                d(p, "This could prove useful down the line.", Thinking),
                d(p, "I appreciate you sharing this with me.", Shy)
            },

            new Dialogue[]{ // Variant 3
                d(p, "Thank you for thinking of me when you found this.", Shy),
                d(p, "It’s practical, and I’m sure I can put it to good use.", Satisfied)
            }
        };
        return Util.randomizeArray(likedVariants, 1)[0];
    }

    private Dialogue[] neutral(){
        Dialogue[][] neutralVariants = {
            new Dialogue[]{ // Variant 1
              d(p, "An unusual item, but I don't have a immediate use for it.", Indifferent),
              d(p, "Still, I appreciate the gesture.")
            },

            new Dialogue[]{ // Variant 2
                d(p, "Thank you.", Indifferent),
                d(p, "I'll put it away for now.")
            },

            new Dialogue[]{ // Variant 3
                d(p, "Every object has its place, even if its purpose isn't clear yet.", Indifferent),
                d(p, "Thank you.")
            }
        };
        return Util.randomizeArray(neutralVariants, 1)[0];
    }

    private Dialogue[] disliked(){
        Dialogue[][] dislikedVariants = {
            new Dialogue[]{ // Variant 1
                d(p, "Is there a specific reason you brought this to me, or are you just testing my patience?", Defensive)
            },

            new Dialogue[]{ // Variant 2
                d(p, "I fail to see how this serves any logical purpose.", Indifferent),
                d(p, "Please take it back.", Defensive)
            },

            new Dialogue[]{ // Variant 3
                d(p, "I expected a bit more thoughtfulness from you.", Serious),
                d(p, "This is completely unnecessary.", Defensive)
            },

            new Dialogue[]{ // Variant 4
                d(p, "A bizarre item with zero investigative or practical value.", Defensive),
                d(p, "I don't want it.", Indifferent)
            }
        };
        return Util.randomizeArray(dislikedVariants, 1)[0];
    }

    private Dialogue[] hated(){
        Dialogue[][] hatedVariants = {
            new Dialogue[]{ // Variant 1
                d(p, "This isn't just useless, it's a liability!", Pissed),
                d(p, "I advise you to destroy it before it causes actual harm!", Objecting)
            },

            new Dialogue[]{ // Variant 2
                d(p, "There is no hidden value here, only clear negligence.", Pissed),
                d(p, "Don't bring such hazards near me again!", Objecting)
            },

            new Dialogue[]{ // Variant 3
                d(p, "Are you attempting to sabotage me, or is your judgment simply this compromised?", Pissed),
                d(p, "Remove it immediately!", Objecting)
            },

            new Dialogue[]{
                d(p, "What possessed you to give me this?", Pissed),
                d(y, "Raja Iblis Alas Baluran possessed me."),
                d(p, "I regret asking.", Indifferent),
                d(n,"..."),
                d(p, "What are you waiting for?", Defensive),
                d(p, "Dispose of it right now!", Objecting)
            }
        };
        return Util.randomizeArray(hatedVariants, 1)[0];
    }
}
