package sdp.content.prinzessins.kyoko.dialogues;

import sdp.modules.dialogue.Dialogue;
import sdp.modules.dialogue.DialogueBank;
import sdp.modules.dialogue.interfaces.SpecialItemDialogue;
import sdp.content.gameplay.inventory.items.ItemID;
import sdp.persistence.DataController;

import static sdp.content.prinzessins.kyoko.assets.KyokoSprite.*;

public class KyokoSpecialItemDialogue extends DialogueBank implements SpecialItemDialogue {
    @Override
    public Dialogue[] get(ItemID itemID) {
        return switch (itemID) {
            case ITEM_01 -> adapterRokok();
            case ITEM_08 -> distrustAudioPlayer();
            case ITEM_10 -> jariSukuna();
            case ITEM_12 -> kotaJahat(itemID);
            case ITEM_13 -> vhsDespair();
            case ITEM_17 -> lifeNote();
            case ITEM_19 -> microphonePelunas();
            case ITEM_22 -> phoneWave();
            case ITEM_25 -> ramuanPosesiIblis();
            case ITEM_28 -> topiSherlock();
            default -> throw new IllegalArgumentException("Invalid item ID: " + itemID);
        };
    }

    private Dialogue[] adapterRokok() {
        return new Dialogue[]{
            y("Meine Prinzessin!!!"),
            y("Look what I found here!"),
            p("What?", Indifferent),
            y("Tadaaa~ A cigarette adapter that allows me to smoke 500 cigarettes simultaneously!", Surprised),
            p("...And how are you going to find 500 cigarettes in this locked-down academy?", Indifferent),
            y("It already came with 500 cigarettes attached to it!"),
            p("...", Defensive),
            y("Would you permit me to light them all up? Prinzessin-sama >//<"),
            p("No."),
            y("You've never seen smoke as huge as the Mt. Krakatoa eruption, have you?"),
            p("I suggest you don't make me repeat myself.", Objecting),
            y("B-but!!!"),
            n("Kyoko immediately destroyed the adapter.", Pissed)
        };
    }

    private Dialogue[] distrustAudioPlayer() {
        return new Dialogue[]{
            y("Meine Prinzessin!!!"),
            y("Look what I found!"),
            p("What is it?", Indifferent),
            y("A Walkman! And there's only one song on it."),
            p("\"DISTRUST\"?", Thinking),
            y("Yep! Wanna listen to it together?"),
            p("Perhaps later.", Indifferent),
            y("Aww, come on! Just try it."),
            p("...Fine."),
            n("Kyoko put on the headphones and listened to the song."),
            p("...", Focused),
            y("Well?"),
            p("It's useful.", Resigned),
            y("Useful? That's it?"),
            p("It helps me concentrate.", Indifferent),
            y("So you like it!"),
            p("I didn't say that."),
            y("You totally do."),
            p("..."),
            n("Kyoko puts the audio player into her pocket.")
        };
    }

    private Dialogue[] jariSukuna() {
        return new Dialogue[]{

        };
    }

    private static Dialogue[] kotaJahat(ItemID itemID) {
        int kotaJahatUsed = DataController.getInstance().getInventoryData().getItemUsageCount(itemID);

        if (kotaJahatUsed >= 11 || kotaJahatUsed < 0) {
            throw new IllegalArgumentException("Invalid Kota Jahat Usage: " + kotaJahatUsed);
        }

        Dialogue[][] variants = {
            new Dialogue[]{ // Use 1

            },
            new Dialogue[]{ // Use 2

            },
            new Dialogue[]{ // Use 3

            },
            new Dialogue[]{ // Use 4

            },
            new Dialogue[]{ // Use 5

            },
            new Dialogue[]{ // Use 6

            },
            new Dialogue[]{ // Use 7

            },
            new Dialogue[]{ // Use 8

            },
            new Dialogue[]{ // Use 9

            },
            new Dialogue[]{ // Use 10

            },
            new Dialogue[]{ // Use 11

            }
        };

        return variants[kotaJahatUsed];
    }

    private Dialogue[] vhsDespair() {
        return new Dialogue[]{
            y("Meine Prinzessin!!!"),
            y("Look what I found!"),
            p("What is it?", Indifferent),
            y("A VHS tape. Some kind of anime about despair."),
            p("...", Defensive),
            y("Apparently, it's made by Ryota Mitarai."),
            p("Where did you get that?", Serious),
            y("Huh? What's wrong?"),
            p("Give it to me!", Asserting),
            y("Wait, you know what this is?"),
            p("Yes.", Serious),
            y("Why!? Let's watch it together!1!1!1! >//<"),
            p("Are you insane? That video is brainwashing propaganda.", Pissed),
            y("B-brainwashing?! But it's just anime! Look at the colorful cover-"),
            p("Hand it over. Right now.", Objecting),
            y("Eek! A-All right, all right, take it! Just don't look at me with those cold eyes..."),
            n("Kyoko snatched the tape and smashed it under her heel without hesitation.", Hide),
            p("We have enough despair to deal with in this school. We don't need any more.", Asserting),
            y("My precious anime find... crushed into pieces...", Defensive),
            p("Be glad it was the tape that got crushed, not your brain!", Asserting)
        };
    }

    private Dialogue[] lifeNote() {
        return new Dialogue[]{
            y("Meine Prinzessin!1!1!1!"),
            y("This is for you!1!1! >//<"),
            p("What is it?", Indifferent),
            y("A cursed notebook called the Life Note!"),
            p("A... cursed notebook?", Thinking),
            y("Yep! Apparently, if you write someone's name in it, they can come back to life!"),
            p("That's impossible.", Indifferent),
            y("But that's what it says!"),
            p("And you believed it?"),
            y("Well... it does sound pretty convincing."),
            p("Give it to me.", Resigned),
            y("Huh? You want to try it?"),
            p("No. I want to examine it.", Indifferent),
            n("Kyoko carefully examined the notebook and read its instructions.", Focused),
            p("Interesting."),
            y("See!? It really can revive people!"),
            p("No. It can't.", Indifferent),
            y("Eh?"),
            p("The instructions say it only works on people who are still alive."),
            y("..."),
            p("That's not resurrection. That's just writing someone's name in a notebook."),
            y("So... it's basically useless?"),
            p("As a supernatural artifact, yes."),
            n("Kyoko flipped through the empty pages."),
            p("But the notebook itself is quite practical.", Thinking),
            y("Practical?"),
            p("Good paper. Plenty of pages. Easy to carry."),
            y("Wait..."),
            p("I'll keep it.", Satisfied),
            y("You're keeping the cursed notebook?!"),
            p("It's a notebook. That's all I need it to be.", Indifferent),
            y("So you actually like it!"),
            p("Don't misunderstand me.", Defensive),
            y("You totally do."),
            p("...", Flustered),
            n("Kyoko slipped the Life Note into her pocket and continued walking.")
        };
    }

    private Dialogue[] microphonePelunas() {
        return new Dialogue[]{
            n("")
        };
    }

    private Dialogue[] phoneWave() {
        return new Dialogue[]{
            y("Meine Prinzessin!!!"),
            y("Look what I found!"),
            p("Why are you carrying a whole microwave?!", Surprised),
            y("No, no! This isn't an ordinary microwave!"),
            y("Look at the label!"),
            p("Future Gadget Lab...", Focused),
            p("I've heard of it.", Thinking),
            p("So what does it do?", Focused),
            y("Apparently, it can turn bananas into green jelly!"),
            p("Green jelly?", Thinking),
            y("And then send them into the past!"),
            p("...", Focused),
            y("Pretty amazing, right?"),
            p("If that's true, then this could be extremely important."),
            y("You believe me?!"),
            p("I said 'if'.", Resigned),
            n("Kyoko examined the microwave carefully, checking its switches, wiring, and the label attached to its side.", Focused),
            p("There's a problem.", Serious),
            y("Huh?"),
            p("It's missing a component.", Asserting),
            y("Missing?"),
            p("The instructions say it requires a CRT television underneath the microwave.", Thinking),
            y("A CRT television..."),
            p("Without it, the device can't be operated.", Resigned),
            y("So we can't send anything into the past?"),
            p("Not yet.", Indifferent),
            y("But there aren't any CRT televisions anywhere in the academy!"),
            p("I know."),
            n("Kyoko looked around the empty hallway, then back at the microwave.", Dismissive),
            p("Still, I don't think we should discard it.", Resigned),
            y("Really?"),
            p("If the Future Gadget Lab actually built this, then its specifications are worth investigating.", Thinking),
            p("And if it really can transmit matter into the past...", Focused),
            y("Then we could use it to escape Hope's Peak!"),
            p("Exactly.", Serious),
            y("We could send a message to ourselves before we got trapped here!"),
            p("Or perhaps send something else back far enough to alter what happened.", Focused),
            y("You're already thinking about time travel?!"),
            p("I'm considering every possibility.", Thinking),
            n("Kyoko removed the microwave from the pile and carried it with her.", Resigned),
            y("You're taking it?"),
            p("Yes.", Asserting),
            y("Even though we can't use it?"),
            p("For now.", Indifferent),
            y("You really think we can find a CRT somewhere in this academy?"),
            p("I don't know."),
            p("But if we're going to escape, we can't afford to ignore a possible method of time travel."),
            y("Waaaaaaaa >//< Meine Prinzessin... you're amazing!1!1!1!1!"),
            p("Don't get sentimental.", Defensive),
            y("I wasn't! I was just thinking how cool it would be if we could send a banana back in time."),
            p("That is not our priority.", Indifferent),
            y("Right. Escape first. Time-travel bananas later."),
            p("...")
        };
    }

    private Dialogue[] ramuanPosesiIblis() {
        return new Dialogue[]{

        };
    }

    private Dialogue[] topiSherlock() {
        return new Dialogue[]{
            y("Meine Prinzessin!!!"),
            y("Look what I found!"),
            p("A hat?", Indifferent),
            y("Not just any hat!"),
            y("This is the ORIGINAL Sherlock Holmes hat!"),
            p("...", Neutral),
            p("Sherlock Holmes is a fictional character."),
            y("Exactly! That's what makes it so valuable!"),
            p("That makes no sense.", Indifferent),
            y("Look! It even says \"Original Sherlock Holmes Property\"!"),
            p("Where?"),
            y("Right here!"),
            p("...", Resigned),
            n("Kyoko took the hat and examined the tag carefully.", Focused),
            p("\"Pasar Merakmati\".", Indifferent),
            y("..."),
            p("That's a market."),
            y("Maybe Sherlock Holmes secretly owned a shop there?!"),
            p("Sherlock Holmes is fictional.", Asserting),
            y("But what if he isn't?!", Defensive),
            p("Then he would have to be over a century old.", Asserting),
            y("So you're saying there's still a chance?!", Defensive),
            p("No.", Indifferent),
            y("Aww..."),
            p("...", Resigned),
            p("Still, I understand why someone would make this kind of hat."),
            y("Huh?"),
            p("When I was younger, I used to read detective stories constantly.", Shy),
            y("Really?!"),
            p("Yes."),
            y("You mean you were already into detective stuff as a kid?!"),
            p("I suppose so.", Satisfied),
            p("...", Resigned),
            p("I used to imagine myself solving mysteries alongside the detectives in those books.", Shy),
            y("WAAAAAAAAA SUGOKU KAWAII >//<", Surprised),
            p("It wasn't about being cute.", Pissed),
            y("I didn't say it was!"),
            p("You were thinking it.", Objecting),
            y("M-maybe..."),
            p("Forget it.", Resigned),
            y("..."),
            p("The stories taught me something useful, though.", Indifferent),
            y("What?"),
            p("That every mystery has an answer."),
            p("Even when the truth is unpleasant, there is always something left to investigate."),
            y("..."),
            p("I suppose that's one of the reasons I became a detective.", Satisfied),
            y("Meine Prinzessin..."),
            p("What?", Indifferent),
            y("Nothing. I just think that's really cool."),
            p("You're staring again."),
            y("S-sorry!"),
            p("It's not particularly remarkable."),
            y("It is to me."),
            p("You should pay more attention to the investigation than to me.", Asserting),
            y("Right..."),
            n("Kyoko quietly placed the deerstalker into her bag.", Satisfied),
        };
    }
}