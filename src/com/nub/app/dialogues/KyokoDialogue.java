package com.nub.app.dialogues;

public class KyokoDialogue implements DialogueBank{
    private final String P;
    private final String C = "Kyoko";

    public KyokoDialogue(String name) {
        this.P = name;
    }

    public Dialogue[] getPROLOGUE(){
        return new Dialogue[]{
                new Dialogue(P, "(Ever since Byakuya got executed after he killed Hina...)", CharacterState.NEUTRAL),
                new Dialogue(P, "(For some reason Monokuma hasn't show up ever since...", CharacterState.NEUTRAL),
                new Dialogue(P, "(It's been two weeks, Kyoko and I haven't found a way out.", CharacterState.NEUTRAL),
                new Dialogue(P, "(We've searched for any clue days after days as my sanity began to degrade.", CharacterState.NEUTRAL),
                new Dialogue(P, "(And now... I don't know anymore. Perhaps my only purpose is to serve Kyoko now...)", CharacterState.NEUTRAL),
                new Dialogue(P, "(...Meine Prinzessin.)", CharacterState.NEUTRAL),
                new Dialogue(C, "What are you staring at?", CharacterState.INDIFFERENT),
                new Dialogue(P, "Ah, i was just spacing out...", CharacterState.NEUTRAL),
                new Dialogue(P, "(What should i do today?)", CharacterState.NEUTRAL)
        };
    }

    public Dialogue[] getFEED_SATISFIED() {
        return new Dialogue[]{
                new Dialogue(C, "You're unusually attentive today. This is... acceptable.", CharacterState.SURPRISED),
                new Dialogue(P, "(She looks pleased. That small nod was worth the effort >//<)", CharacterState.SATISFIED),
                new Dialogue(C, "Don't just stand there. We still have mysteries to solve.", CharacterState.UNDERSTANDING)
        };
    }

    public Dialogue[] getFEED_NEUTRAL() {
        return new Dialogue[]{
                new Dialogue(C, "It's edible. I suppose I should thank you for the effort.", CharacterState.INDIFFERENT),
                new Dialogue(P, "(She barely looked up from her notes... I need to do better next time.)", CharacterState.INDIFFERENT)
        };
    }

    public Dialogue[] getGIFT_SATISFIED() {
        return new Dialogue[]{
                new Dialogue(C, "You've anticipated my needs perfectly. Perhaps there's a benefit to your... obsession after all.", CharacterState.SHY),
                new Dialogue(C, "This is exactly what I required. Your dedication to your 'Prinzessin' is almost impressive.", CharacterState.SHY),
                new Dialogue(C, "I'll accept this. You're becoming quite the efficient tool, aren't you?", CharacterState.SHY),
                new Dialogue(C, "A perfect offering. It seems you've truly accepted your purpose here.", CharacterState.SHY),
                new Dialogue(C, "I'm pleased. It's rare to find such absolute subservience... but I could get used to it.", CharacterState.SHY)
        };
    }

    public Dialogue[] getGIFT_LIKES() {
        return new Dialogue[]{
                new Dialogue(C, "This is actually quite useful. You're showing a surprising amount of foresight.", CharacterState.SATISFIED),
                new Dialogue(C, "I'll take it. It's rare that you provide something I don't immediately want to discard.", CharacterState.SATISFIED),
                new Dialogue(C, "A suitable offering. Perhaps there is still some value in keeping you around.", CharacterState.SATISFIED),
                new Dialogue(C, "I've been looking for something like this. Your timing is... efficient.", CharacterState.SATISFIED),
                new Dialogue(C, "Not bad. It seems you haven't completely lost your ability to be helpful.", CharacterState.SATISFIED)
        };
    }

    public Dialogue[] getGIFT_NEUTRAL(){
        return new Dialogue[]{
                new Dialogue(C, "A gift? I'll accept it, though I'm struggling to see the utility in this.", CharacterState.NEUTRAL),
                new Dialogue(C, "It's... functional. I suppose I should thank you for the gesture.", CharacterState.NEUTRAL),
                new Dialogue(C, "I'll set this aside for now. We have more pressing matters to attend to.", CharacterState.NEUTRAL),
                new Dialogue(C, "Is this meant to be a bribe, or just a distraction? Either way, I've noted it.", CharacterState.NEUTRAL),
                new Dialogue(C, "I appreciate the effort, but don't let these trivialities cloud your focus.", CharacterState.NEUTRAL)
        };
    }

    public Dialogue[] getGIFT_DISAPPOINTED() {
        return new Dialogue[]{
            new Dialogue(C, "...What exactly am I supposed to do with this?", CharacterState.INDIFFERENT),
            new Dialogue(C, "You went through the trouble of giving me this. I'm struggling to understand why.", CharacterState.INDIFFERENT),
            new Dialogue(C, "I see. So this is the level of thought you put into your choices.", CharacterState.INDIFFERENT),
            new Dialogue(C, "If this was meant as a joke, I'm afraid the logic behind it escapes me.", CharacterState.INDIFFERENT),
            new Dialogue(C, "...I would recommend disposing of this somewhere far away from me.", CharacterState.INDIFFERENT),
            new Dialogue(C, "I'm certain this meant something to you. Unfortunately, that meaning isn't clear to me.", CharacterState.INDIFFERENT),
            new Dialogue(C, "Next time, try applying at least a little reasoning before handing me something like this.", CharacterState.INDIFFERENT),
            new Dialogue(C, "...I'll pretend this never happened.", CharacterState.INDIFFERENT)
        };
    }

    public Dialogue[] getTEASE() {
        return new Dialogue[]{
                new Dialogue(P, "Aghhh Kyoko, meine Prinzessin!!! You're so wangy wangy >//<", CharacterState.SURPRISED),
                new Dialogue(P, "Omggggg Kyoko-chan >//< I want to usap usap you >//<", CharacterState.SURPRISED),
                new Dialogue(P, "Meine Prinzessin!!!! please pukpuk me >//<", CharacterState.SURPRISED),
                new Dialogue(P, "Meine Prinzessin, if you ever feel insecure, please remember that flat is justice >//<", CharacterState.SURPRISED),
                new Dialogue(P, "Plezz lemme bite you Kyoko-ko >//<", CharacterState.SURPRISED)
        };
    }

    public Dialogue[] getTEASE_RESPONSE(){
        return new Dialogue[]{
                new Dialogue(C,"Don't touch me. Your lack of self-control is... revolting.", CharacterState.ANGRY),
                new Dialogue(C,"I'm beginning to wonder if there's anything left of your mind at all. How pathetic.", CharacterState.ANGRY),
                new Dialogue(C,"Is this what you've reduced yourself to? A groveling, mindless distraction", CharacterState.ANGRY),
                new Dialogue(C,"Keep your distance. Your presence is becoming increasingly difficult to tolerate.", CharacterState.ANGRY),
                new Dialogue(C,"I suggest you remember your place. I have no use for a partner who can't control their impulses.", CharacterState.ANGRY)
        };
    }
}