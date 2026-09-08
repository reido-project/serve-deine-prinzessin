package com.nub.app.misc;

public class ItemList {
    private final int TI = -3; //Trash Items
    private int KY_LV = 0; //Kyoko Loves
    private int KY_LK = 0; //Kyoko Likes
    private int CH_LV = 0; //Chiaki Loves
    private int CH_LK = 0; //Chiaki Likes

    public ItemList(String currentCharacter){
        switch(currentCharacter){
            case "Kyoko":
                KY_LV = 2;
                KY_LK = 1;
                break;
            case "Chiaki":
                CH_LV = 2;
                CH_LK = 1;
                break;
        }
    }

    private final Items[] item = {
            new Items(1, "Many-Sided Dice Set", CH_LK), //21
            new Items(2, "Cherry Blossom Bouquet", KY_LV), //4
            new Items(3, "Voice-Changing Bowtie", KY_LK), //12
            new Items(4, "Abed's Difteri", TI), //28
            new Items(5, "Glasses", KY_LK), //7
            new Items(6, "Mini Wave-Dissipaters", CH_LK), //19
            new Items(7, "Blueberry Perfume", KY_LV), //2
            new Items(8, "Power Gauntlet", CH_LV), //15
            new Items(9, "Moon Rock", CH_LK), //25
            new Items(10, "Civet Coffee", KY_LV), //1
            new Items(11, "Self-Destructing Cassette", KY_LK), //9
            new Items(12, "Slap Bracelet", CH_LK), //23
            new Items(13, "Hope's Peak Ring", KY_LK), //6
            new Items(14, "The Funplane", KY_LK), //11
            new Items(15, "Blyoshi's Dirt Gun", TI), //27
            new Items(16, "Rose in Vitro", KY_LV), //3
            new Items(17, "Skullhead Mask", CH_LV), //18
            new Items(18, "Tips & Tips 2nd Edition", CH_LV), //14
            new Items(19, "Bryan The Alien", TI), //29
            new Items(20, "Bojobo Dolls", KY_LV), //5
            new Items(21, "The Funbox", CH_LK), //22
            new Items(22, "Antique Doll", KY_LK), //10
            new Items(23, "Marine Snow", CH_LK), //26
            new Items(24, "Century Potpourri", CH_LV), //17
            new Items(25, "Golden Gun", KY_LK), //8
            new Items(26, "Go Stone", CH_LK), //24
            new Items(27, "Meteorite Arrowhead", KY_LK), //13
            new Items(28, "Sunflower Seeds", CH_LK), //20
            new Items(29, "Nitro Racer", CH_LV) //16
    };

    public Items[] getItemList(){
        return item;
    }
}
