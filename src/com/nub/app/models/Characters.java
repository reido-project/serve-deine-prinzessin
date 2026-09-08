package com.nub.app.models;

import com.nub.app.dialogues.CharacterState;

public abstract class Characters {
    protected String name;

    Characters(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void updateAffection(int impact){}

    public String getSprite(CharacterState state){return "";}

    public void feed(){}
}
