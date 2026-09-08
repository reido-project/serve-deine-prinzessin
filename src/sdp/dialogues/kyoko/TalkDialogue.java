package sdp.dialogues.kyoko;

import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueInterface;
import sdp.gameplay.interactions.talk.TopicID;

public class TalkDialogue extends DialogueInterface {
    public Dialogue[] get(TopicID topicID) {
        switch (topicID) {
            case TOPIC_01 -> {return topic01();}
            case TOPIC_02 -> {return topic02();}
            case TOPIC_03 -> {return topic03();}
            case TOPIC_04 -> {return topic04();}
            case TOPIC_05 -> {return topic05();}
            case TOPIC_06 -> {return topic06();}
            case TOPIC_07 -> {return topic07();}
            case TOPIC_08 -> {return topic08();}
            case TOPIC_09 -> {return topic09();}
            case TOPIC_10 -> {return topic10();}
            case TOPIC_11 -> {return topic11();}
            case TOPIC_12 -> {return topic12();}
            case TOPIC_13 -> {return topic13();}
            case TOPIC_14 -> {return topic14();}
            case TOPIC_15 -> {return topic15();}
            case TOPIC_16 -> {return topic16();}
        }
        throw new IllegalArgumentException("Invalid topicID");
    }

    private Dialogue[] topic01(){
        return new Dialogue[]{
            d(p, "topic 001 test")
        };
    }

    private Dialogue[] topic02(){
        return new Dialogue[]{
            d(p, "topic 002 test")
        };
    }

    private Dialogue[] topic03(){
        return new Dialogue[]{
            d(p, "topic 003 test")
        };
    }

    private Dialogue[] topic04(){
        return new Dialogue[]{
            d(p, "topic 004 test")
        };
    }

    private Dialogue[] topic05(){
        return new Dialogue[]{
            d(p, "topic 005 test")
        };
    }

    private Dialogue[] topic06(){
        return new Dialogue[]{
            d(p, "topic 006 test")
        };
    }

    private Dialogue[] topic07(){
        return new Dialogue[]{
            d(p, "topic 007 test")
        };
    }

    private Dialogue[] topic08(){
        return new Dialogue[]{
            d(p, "topic 008 test")
        };
    }

    private Dialogue[] topic09(){
        return new Dialogue[]{
            d(p, "topic 009 test")
        };
    }

    private Dialogue[] topic10(){
        return new Dialogue[]{
            d(p, "topic 010 test")
        };
    }

    private Dialogue[] topic11(){
        return new Dialogue[]{
            d(p, "topic 011 test")
        };
    }

    private Dialogue[] topic12(){
        return new Dialogue[]{
            d(p, "topic 012 test")
        };
    }

    private Dialogue[] topic13(){
        return new Dialogue[]{
            d(p, "topic 013 test")
        };
    }

    private Dialogue[] topic14(){
        return new Dialogue[]{
            d(p, "topic 014 test")
        };
    }

    private Dialogue[] topic15(){
        return new Dialogue[]{
            d(p, "topic 015 test")
        };
    }

    private Dialogue[] topic16(){
        return new Dialogue[]{
            d(p, "topic 016 test")
        };
    }
}
