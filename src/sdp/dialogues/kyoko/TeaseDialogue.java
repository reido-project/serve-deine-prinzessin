package sdp.dialogues.kyoko;

import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueInterface;
import sdp.misc.Util;

import static sdp.assets.sprite.SpriteState.*;

public class TeaseDialogue extends DialogueInterface {
    public Dialogue[] get(){
        return variants();
    }

    public Dialogue[] get(int affection){
        if (affection <= 0 || affection >= 100) {
            throw new IllegalArgumentException(
                "Invalid affection: " + affection
            );
        }
        if(affection < 20){
            return hates();
        }
        else if(affection < 40){
            return dislikes();
        }
        else if(affection < 61){
            return neutral();
        }
        else if(affection < 81){
            return likes();
        }
        else {
            return loves();
        }
    }

    private Dialogue[] hates(){
        Dialogue[][] response = {
            new Dialogue[]{
                d(p, "Don't touch me!", Pissed),
                d(p, "Your lack of self-control is revolting.")
            },
            new Dialogue[]{
                d(p, "Keep your distance!", Pissed),
                d(p, "Your mere presence is becoming intolerable.")
            },
            new Dialogue[]{
                d(p, "Is this what you've reduced yourself to?", Pissed),
                d(p, "A groveling, mindless distraction?")
            },
            new Dialogue[]{
                d(p, "Never call me by those pathetic names again if you value your life.", Pissed)
            },
            new Dialogue[]{
                d(p, "I have zero tolerance for whatever disgusting delusion you're living in.", Pissed)
            }
        };
        return Util.addEntry(Util.randomizeArray(response, 1)[0], d(n, "NOOOOOOOOOOOOO!!! MEINE PRINZESSIN HATES ME!!!"));
    }

    private Dialogue[] dislikes(){
        Dialogue[][] response = {
            new Dialogue[]{
                d(p, "Could you refrain from acting like an utter fool for five minutes?", Pissed)
            },
            new Dialogue[]{
                d(p, "Save your energy for finding clues instead of wasting it on brainless antics.", Pissed)
            },
            new Dialogue[]{
                d(p, "What ridiculous jargon are you babbling now?", Pissed)
            },
            new Dialogue[]{
                d(p, "I am not here to indulge your childish tantrums. Grow up.", Pissed)
            },
            new Dialogue[]{
                d(p, "I suggest you remember your place before you run out of patience.", Pissed)
            }
        };
        return Util.addEntry(Util.randomizeArray(response, 1)[0], d(n, "NOOOOOOOOOOOOO!!! BUT I REALLY WANT IT!!!"));
    }

    private Dialogue[] neutral(){
        Dialogue[][] response = {
            new Dialogue[]{
                d(p, "Did you hit your head on something today?", Defensive)
            },
            new Dialogue[]{
                d(p, "What in the world are you even talking about?", Defensive)
            },
            new Dialogue[]{
                d(p, "How can you say something so embarrassing with a completely straight face?", Defensive)
            },
            new Dialogue[]{
                d(p, "You must be joking.", Defensive),
                d(p, "We have work to do.")
            },
            new Dialogue[]{
                d(p, "Stop fooling around!", Defensive),
                d(p, "You're making things unnecessarily weird.")
            }
        };
        return Util.addEntry(Util.randomizeArray(response, 1)[0], d(n, "But I'm being serious rn!!!"));
    }

    private Dialogue[] likes(){
        Dialogue[][] response = {
            new Dialogue[]{
                d(p, "Are you trying to test my patience, or is this just your usual coping mechanism?", Indifferent)
            },
            new Dialogue[]{
                d(p, "If you have time to idle around saying strange things, I suggest using it to investigate instead.", Indifferent)
            },
            new Dialogue[]{
                d(p, "Keep your hands to yourself!", Defensive),
                d(p, "Though... I suppose I don't entirely mind your presence.", Indifferent)
            },
            new Dialogue[]{
                d(p, "Don't expect me to react to your absurd remarks.", Defensive),
                d(p, "Just stay close and make yourself useful during the investigation.", Indifferent)
            },
            new Dialogue[]{
                d(p, "I'll never understand why you feel the need to say such strange things around me.", Defensive),
                d(p, "Though... I suppose it's better than hearing you panic.", Indifferent)
            }
        };
        return Util.addEntry(Util.randomizeArray(response, 1)[0], d(n, "Yay! She's not mad!"));
    }

    private Dialogue[] loves(){
        Dialogue[][] response = {
            new Dialogue[]{
                d(p, "You really haven't changed at all...", Resigned),
                d(p, "But I suppose that consistency is what makes you reliable.", Satisfied)
            },
            new Dialogue[]{
                d(p, "If you need reassurance, you don't have to put on such a dramatic act.", Serious),
                d(p, "Just ask.", Indifferent)
            },
            new Dialogue[]{
                d(p, "Call me whatever ridiculous name you like.", Indifferent),
                d(p, "As long as we're facing this together, I won't complain.")
            },
            new Dialogue[]{
                d(p, "Your mind is a total enigma...", Serious),
                d(p, "But it's one of the very few things keeping me grounded right now.", Resigned)
            },
            new Dialogue[]{
                d(p, "You're absurd.", Resigned),
                d(p, "Yet... somehow, your nonsensical banter has its own way of clearing the tension.", Satisfied)
            }
        };
        return Util.addEntry(Util.randomizeArray(response, 1)[0], d(n, "WAAAAAAAAAAA!!! MEINE PRINZESSIN SUGOKU KAWAII >//<"));
    }

    private Dialogue[] variants(){
        Dialogue[][] variants = {
            new Dialogue[]{
                d(y, "Aghhh Kyoko, meine Prinzessin!!! You're so wangy wangy >//<", Surprised)
            },
            new Dialogue[]{
                d(y, "Omaygottt Kyoko-chan >//< I want to usap usap you >//<", Surprised)
            },
            new Dialogue[]{
                d(y, "Meine Prinzessin!!!! please pukpuk me >//<", Surprised)
            },
            new Dialogue[]{
                d(y, "Meine Prinzessin, if you ever feel insecure, please remember that flat is justice >//<", Surprised)
            },
            new Dialogue[]{
                d(y, "Plezz lemme gigit you Kyoko-ko >//<", Surprised)
            }
        };
        return Util.randomizeArray(variants, 1)[0];
    }
}
