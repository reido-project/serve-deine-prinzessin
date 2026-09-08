package com.nub.app.models;

public class Player{
    private final String name;
    private int sanity;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
