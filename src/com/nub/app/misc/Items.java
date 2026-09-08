package com.nub.app.misc;

public class Items {
    private final int itemId;
    private final String itemName;
    private final int itemImpact;

    public Items(int itemId, String itemName, int itemImpact){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemImpact  = itemImpact;
    }

    public int getItemId(){
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemImpact() {
        return itemImpact;
    }
}