package sdp.content.prinzessins.kyoko.dialogues;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueBank;
import sdp.modules.dialogue.interfaces.TeaseDialogueInterface;
import sdp.shared.utils.ArrayUtil;

import static sdp.content.prinzessins.kyoko.assets.KyokoSprite.*;

public class KyokoTeaseDialogue extends DialogueBank implements TeaseDialogueInterface {
    public Dialogue[] variants() {
        Dialogue[][] variants = {
            new Dialogue[]{
                y("Aghhh Kyoko, meine Prinzessin!!! You're so wangy wangy >//<", Surprised)
            },
            new Dialogue[]{
                y("Omaygottt Kyoko-chan >//< I want to usap usap you >//<", Surprised)
            },
            new Dialogue[]{
                y("Meine Prinzessin!!!! please pukpuk me >//<", Surprised)
            },
            new Dialogue[]{
                y("Meine Prinzessin, if you ever feel insecure, please remember that flat is justice >//<", Surprised)
            },
            new Dialogue[]{
                y("Plezz lemme gigit you Kyoko-ko >//<", Surprised)
            }
        };
        return ArrayUtil.randomizeArray(variants, 1)[0];
    }

    @Override
    public Dialogue[] hatesHungryVeryHungry() {
        return new Dialogue[]{
            p("I am starving, irritable, and have zero patience left for your foolishness.", Pissed),
            p("Get out of my sight before I make you regret ever speaking to me."),
            n("EEEEEK! SHE'S GONNA KILL ME!")
        };
    }

    public Dialogue[] hates() {
        Dialogue[][] response = {
            new Dialogue[]{
                p("Don't touch me!", Pissed),
                p("Your lack of self-control is revolting.")
            },
            new Dialogue[]{
                p("Keep your distance!", Pissed),
                p("Your mere presence is becoming intolerable.")
            },
            new Dialogue[]{
                p("Is this what you've reduced yourself to?", Pissed),
                p("A groveling, mindless distraction?")
            },
            new Dialogue[]{
                p("Never call me by those pathetic names again if you value your life.", Pissed)
            },
            new Dialogue[]{
                p("I have zero tolerance for whatever disgusting delusion you're living in.", Pissed)
            }
        };
        return ArrayUtil.addEntry(
            ArrayUtil.randomizeArray(response, 1)[0],
            n("NOOOOOOOOOOOOO!!! MEINE PRINZESSIN HATES ME!!!")
        );
    }

    @Override
    public Dialogue[] dislikesHungryVeryHungry() {
        return new Dialogue[]{
            p("I am far too hungry to entertain your brainless antics.", Pissed),
            p("Unless you're holding a meal, save your breath."),
            n("Aaaah... She's even colder when her stomach is empty!")
        };
    }

    public Dialogue[] dislikes() {
        Dialogue[][] response = {
            new Dialogue[]{
                p("Could you refrain from acting like an utter fool for five minutes?", Pissed)
            },
            new Dialogue[]{
                p("Save your energy for finding clues instead of wasting it on brainless antics.", Pissed)
            },
            new Dialogue[]{
                p("What ridiculous jargon are you babbling now?", Pissed)
            },
            new Dialogue[]{
                p("I am not here to indulge your childish tantrums. Grow up.", Pissed)
            },
            new Dialogue[]{
                p("I suggest you remember your place before you run out of patience.", Pissed)
            }
        };
        return ArrayUtil.addEntry(
            ArrayUtil.randomizeArray(response, 1)[0],
            n("NOOOOOOOOOOOOO!!! BUT I REALLY WANT IT!!!")
        );
    }

    @Override
    public Dialogue[] neutralHungryVeryHungry() {
        return new Dialogue[]{
            p("I really don't have the energy for this right now.", Indifferent),
            p("I'm hungry. Please stop making things more complicated than they need to be."),
            n("Okay okay... food first, teasing later.")
        };
    }

    public Dialogue[] neutral() {
        Dialogue[][] response = {
            new Dialogue[]{
                p("Did you hit your head on something today?", Defensive)
            },
            new Dialogue[]{
                p("What in the world are you even talking about?", Defensive)
            },
            new Dialogue[]{
                p("How can you say something so embarrassing with a completely straight face?", Defensive)
            },
            new Dialogue[]{
                p("You must be joking.", Defensive),
                p("We have work to do.")
            },
            new Dialogue[]{
                p("Stop fooling around!", Defensive),
                p("You're making things unnecessarily weird.")
            }
        };
        return ArrayUtil.addEntry(
            ArrayUtil.randomizeArray(response, 1)[0],
            n("But I'm being serious rn!!!")
        );
    }

    @Override
    public Dialogue[] likesHungryVeryHungry() {
        return new Dialogue[]{
            p("I appreciate that you're trying to lighten the mood...", Resigned),
            p("...But my blood sugar is dropping. Can we please focus on getting food first?", Indifferent),
            n("Guess i'll have to cook for meine Prinzessin now >//<")
        };
    }

    public Dialogue[] likes() {
        Dialogue[][] response = {
            new Dialogue[]{
                p("Are you trying to test my patience, or is this just your usual coping mechanism?", Indifferent)
            },
            new Dialogue[]{
                p("If you have time to idle around saying strange things, I suggest using it to investigate instead.", Indifferent)
            },
            new Dialogue[]{
                p("Keep your hands to yourself!", Defensive),
                p("Though... I suppose I don't entirely mind your presence.", Indifferent)
            },
            new Dialogue[]{
                p("Don't expect me to react to your absurd remarks.", Defensive),
                p("Just stay close and make yourself useful during the investigation.", Indifferent)
            },
            new Dialogue[]{
                p("I'll never understand why you feel the need to say such strange things around me.", Defensive),
                p("Though... I suppose it's better than hearing you panic.", Indifferent)
            }
        };
        return ArrayUtil.addEntry(
            ArrayUtil.randomizeArray(response, 1)[0],
            n("Yay! She's not mad!")
        );
    }

    @Override
    public Dialogue[] lovesVeryHungry() {
        return new Dialogue[]{
            p("I don't mind your teasing. Really.", Resigned),
            p("But I'm starving, and I would genuinely appreciate something to eat right now.", Shy),
            n("SHE ADMITTED SHE NEEDS ME!!!")
        };
    }

    public Dialogue[] loves() {
        Dialogue[][] response = {
            new Dialogue[]{
                p("You really haven't changed at all...", Resigned),
                p("But I suppose that consistency is what makes you reliable.", Satisfied)
            },
            new Dialogue[]{
                p("If you need reassurance, you don't have to put on such a dramatic act.", Serious),
                p("Just ask.", Indifferent)
            },
            new Dialogue[]{
                p("Call me whatever ridiculous name you like.", Indifferent),
                p("As long as we're facing this together, I won't complain.")
            },
            new Dialogue[]{
                p("Your mind is a total enigma...", Serious),
                p("But it's one of the very few things keeping me grounded right now.", Resigned)
            },
            new Dialogue[]{
                p("You're absurd.", Resigned),
                p("Yet... somehow, your nonsensical banter has its own way of clearing the tension.", Satisfied)
            }
        };
        return ArrayUtil.addEntry(
            ArrayUtil.randomizeArray(response, 1)[0],
            n("WAAAAAAAAAAA!!! MEINE PRINZESSIN SUGOKU KAWAII >//<")
        );
    }
}
