package sdp.dialogues.kyoko;

import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueInterface;
import sdp.items.ItemList;

import static sdp.assets.sprite.SpriteState.*;

public class SpecialItemDialogue extends DialogueInterface {

    Dialogue[] get(ItemList item) {
        switch (item) {
            case TOPI_SHERLOCK -> { return topiSherlock(); }
            case PHONE_WAVE -> { return phoneWave(); }
            case DISTRUST_WALKMAN -> { return distrustWalkman(); }
            case LIFE_NOTE -> { return lifeNote(); }
            case ADAPTER_ROKOK -> { return adapterRokok(); }
            case VHS_DESPAIR -> { return vhsDespair(); }

            case RAMUAN_POSESI -> { return ramuanPosesi(); }
            case MIC_PELUNAS -> { return microphonePelunas(); }
            // case jari sukuna
            // case kota jahat

            default -> {
                throw new IllegalArgumentException(
                    "Not Kyoko's Special Item: " + item
                );
            }
        }
    }

    private Dialogue[] topiSherlock() {
        return new Dialogue[] {
            d(p, "This...!", Surprised),
            d(p, "Nevermind, it's obviously not a real Sherlock Holmes' deerstalker cap.", Indifferent),
            d(p, "He's fictional, afterall..."),
            d(p, "But that's really thoughtful of you, i think...", Embarrassed),
            d(p, "Thank you.")
        };
    }

    private Dialogue[] phoneWave() {
        return new Dialogue[] {
            d(p, "What is this?", Serious),
            d(y, "This is a Phone Microwave (Name Subject to Change)"),
            d(p, "Looks like junk to me."),
            d(y, "There was a paper inside it. It said that it could send a message or an object into the past."),
            d(n, "!!!"),
            d(y, "M-maybe! Maybe we could try to send a message into the past!"),
            d(y, "For testing purpose. To Be Continued...")
        };
    }

    private Dialogue[] distrustWalkman() {
        return new Dialogue[] {
            d(p, "Distrust walkman testing")
        };
    }

    private Dialogue[] lifeNote() {
        return new Dialogue[] {
            d(p, "Life Note testing")
        };
    }

    private Dialogue[] adapterRokok() {
        return new Dialogue[] {
            d(p, "Adapter Rokok testing")
        };
    }

    private Dialogue[] vhsDespair() {
        return new Dialogue[] {
            d(p, "VHS Despair testing")
        };
    }

    private Dialogue[] ramuanPosesi() {
        return new Dialogue[] {
            d(p, "Ramuan posesi test", () -> {
                data.setPlayerInsanity(
                    data.getPlayerInsanity() + 3
                );
            })
        };
    }

    private Dialogue[] microphonePelunas() {
        return new Dialogue[] {
            d(p, "Mic pelunas test", () -> {
                data.setPlayerInsanity(
                    data.getPlayerInsanity() + 10
                );
            })
        };
    }
}