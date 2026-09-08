package sdp.content.prinzessins.kyoko.dialogues;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueBank;
import sdp.modules.dialogue.interfaces.StoryDialogueInterface;

import static sdp.content.prinzessins.kyoko.assets.KyokoMusic.*;
import static sdp.content.prinzessins.kyoko.assets.KyokoSprite.*;

public class KyokoStoryDialogue extends DialogueBank implements StoryDialogueInterface {
    public Dialogue[] prologue(){
        return new Dialogue[] {
            y("It's been two weeks since Junko Enoshima's... 'extravagant' execution.", Indifferent, () -> playMusic(Welcome_To_Despair)),
            n("We won the Class Trial. She got her beloved despair. Everything should be fine, right?"),
            n("WRONG. That ultimate mastermind completely forgot to hand over the exit remote."),
            n("And it got pulverized into dust right inside her 'xX69SoulCrusherXD420Xx' execution machine."),
            n("Ever since Byakuya got executed after he killed Hina, only the two of us are left in this academy."),
            n("Monokuma is gone. Days feel meaningless. My sanity is slowly eroding into nothingness..."),
            n("And yet, looking at her... maybe this isn't the worst fate."),
            n("..."),
            n("I don't know anymore."),
            n("Perhaps my only purpose is to serve Kyoko now..."),
            n("Kyoko..."),
            n("...Meine Prinzessin."),

            p("What are you staring at?"),
            y("Ah! N-Nothing, I was just spacing out..."),
            p("Focus. We need to figure out how to get out of here.", Asserting),

            n("Right... what should I do today?")
        };
    }

    public Dialogue[] maxInsanityEnd() {
        return new Dialogue[0];
    }

    public Dialogue[] saneEnd() {
        return new Dialogue[0];
    }

    public Dialogue[] maxAffEnd() {
        return new Dialogue[0];
    }

    public Dialogue[] noAffEnd() {
        return new Dialogue[0];
    }

    public Dialogue[] deadEnd() {
        return new Dialogue[0];
    }
}
