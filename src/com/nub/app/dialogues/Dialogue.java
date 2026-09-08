package com.nub.app.dialogues;

public class Dialogue {
    private final String speaker;
    private final String line;
    private final CharacterState state;

    public Dialogue(String speaker, String line, CharacterState state){
        this.speaker = speaker;
        this.line = line;
        this.state = state;
    }

    public String getSpeaker(){
        return speaker;
    }

    public String getLine(){
        return line;
    }

    public CharacterState getState(){
        return state;
    }
}
