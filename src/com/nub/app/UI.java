package com.nub.app;

public class UI {
    private static final int terminalWidth = 80;

    public static void printCentered(String text){
        StringBuilder sb = new StringBuilder();

        if(text == null || text.length() >= terminalWidth){
            System.out.println(text);
            return;
        }

        int padding = (terminalWidth - text.length()) / 2;

        sb.append(" ".repeat(padding));
        sb.append(text);

        System.out.println(sb);
    }

    /*public static void printDialogue(String text){
        System.out.print("————————————————————————————————————————————————————————————————————————————————————\n|\n| ");
        System.out.print(" ");
        System.out.println("\n|\n————————————————————————————————————————————————————————————————————————————————————");
    }*/
}
