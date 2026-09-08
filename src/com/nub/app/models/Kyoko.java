package com.nub.app.models;

import com.nub.app.dialogues.CharacterState;

public class Kyoko extends Characters {
    private int affectionPoint = 0;
    private boolean isHungry = false;
    private int moveCounter = 0;
    private int moveSinceHungry = 0;

    public Kyoko(){
        super("Kyoko");
    }

    public void updateMoveCounter(){
        moveCounter++;
        if(moveCounter == 3){
            isHungry = true;
        }
        else if(moveCounter >= 3){
            moveSinceHungry++;
        }
        if(moveSinceHungry >= 5){

        }
    }

    @Override
    public void feed(){
        if(moveSinceHungry < 3){
            isHungry = false;
            affectionPoint++;
        }
        else if(moveSinceHungry < 5){
            isHungry = false;
        }
    }

    @Override
    public String getSprite(CharacterState state){
        KyokoSprite sprite = new KyokoSprite();
        switch(state){
            case SHY:
                return sprite.kyoko_shy;
            case ANGRY:
                return sprite.kyoko_angry;
            case NEUTRAL:
                return sprite.kyoko_neutral;
            case SATISFIED:
                return sprite.kyoko_satisfied;
            case SURPRISED:
                return sprite.kyoko_surprised;
            case INDIFFERENT:
                return sprite.kyoko_indifferent;
            case DISAPPOINTED:
                return sprite.kyoko_disappointed;
            case UNDERSTANDING:
                return sprite.kyoko_understanding;
            default:
                return "Error getting sprite";
        }
    }
}
