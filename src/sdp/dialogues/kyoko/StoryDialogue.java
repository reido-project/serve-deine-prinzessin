package sdp.dialogues.kyoko;

import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueInterface;
import sdp.dialogues.StoryState;

import static sdp.assets.sprite.SpriteState.*;
import static sdp.assets.music.MusicList.*;

public class StoryDialogue extends DialogueInterface {
    Dialogue[] get(StoryState storyState){
        switch(storyState){
            case PROLOGUE -> {return prologue();}
            case GOOD_END -> {return goodEnd();}
            case BAD_END -> {return badEnd();}
        }
        throw new IllegalArgumentException("Invalid Label");
    }

    private Dialogue[] prologue() {
        return new Dialogue[] {
            d(n, "It's been two weeks since Junko Enoshima's... 'extravagant' execution.", Indifferent, () -> playMusic(WELCOME_TO_DESPAIR)),
            d(n, "We won the Class Trial. She got her beloved despair. Everything should be fine, right?"),
            d(n, "WRONG. That ultimate mastermind completely forgot to hand over the exit remote."),
            d(n, "And it got pulverized into dust right inside her 'xX69SoulCrusherXD420Xx' execution machine."),
            d(n, "Ever since Byakuya got executed after he killed Hina, only the two of us are left in this academy."),
            d(n, "Monokuma is gone. Days feel meaningless. My sanity is slowly eroding into nothingness..."),
            d(n, "And yet, looking at her... maybe this isn't the worst fate."),
            d(n, "..."),
            d(n, "I don't know anymore."),
            d(n, "Perhaps my only purpose is to serve Kyoko now..."),
            d(n, "Kyoko..."),
            d(n, "...Meine Prinzessin."),

            d(p, "What are you staring at?"),
            d(y, "Ah! N-Nothing, I was just spacing out..."),
            d(p, "Focus. We need to figure out how to get out of here.", Asserting),

            d(n, "Right... what should I do today?"),
        };
    }

    private Dialogue[] goodEnd() {
        return new Dialogue[] {

        };
    }

    private Dialogue[] badEnd() {
        return new Dialogue[] {

        };
    }
}