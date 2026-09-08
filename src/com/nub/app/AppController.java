package com.nub.app;

import java.util.Scanner;

import com.nub.app.dialogues.*;
import com.nub.app.misc.ItemList;
import com.nub.app.misc.Items;
import com.nub.app.models.*;
import java.util.Random;

public class AppController {
    private final Scanner input = new Scanner(System.in);

    private AppState currentState = AppState.HOME;
    private boolean isRunning = true;

    private Player player;
    private Characters currentCharacter;
    private DialogueBank characterDialogue;

    public void run(){
        while(isRunning){
            switch(currentState){
                case HOME:
                    showHomeScreen();
                    currentState = AppState.CHARSELECT;
                    break;
                case CHARSELECT:
                    selectChar();
                    startPrologue();
                    break;
                case IDLE:
                    handleGameplay();
                    break;
                case INVENTORY:
                    gift();
                    currentState = AppState.IDLE;
                    break;
                case EXIT:
                    isRunning = false;
                    break;
            }
        }
        input.close();
    }

    private void showHomeScreen(){
        System.out.print("Enter Player Name To Start: ");
        String playerName = input.nextLine();
        player = new Player(playerName);
    }

    private void selectChar(){
        System.out.println("""
                1. Kyoko
                0. Return
                """);
        System.out.print("Select Character To Interact With: ");
        String charSelect = input.nextLine();

        switch(charSelect){
            case "1":
                currentCharacter = new Kyoko();
                characterDialogue = new KyokoDialogue(player.getName());
                break;
            case "0":
                currentState = AppState.HOME;
                return;
            default:
                return;
        }
        currentState = AppState.IDLE;
    }

    private void showSprite(CharacterState state){
        UI.printCentered(currentCharacter.getSprite(state));
    }

    private void startPrologue(){
        dialogue(characterDialogue.getPROLOGUE());
    }

    private Dialogue dialogueRandomizer(Dialogue[] dialogues){
        Random rand = new Random();
        return dialogues[rand.nextInt(dialogues.length)];
    }

    private void gift(){
        ItemList itemList = new ItemList(currentCharacter.getName());
        Dialogue response;

        Items[] item = itemList.getItemList();

        for(int i = 1;i <= 80;i++){
            System.out.print("=");
        }
        System.out.println();
        for(int i = 0;i < item.length;i++){
            System.out.println((i + 1) + ". " + item[i].getItemName());
        }
        for(int i = 1;i <= 80;i++){
            System.out.print("=");
        }
        System.out.println("\n");
        System.out.print("Select Gift: ");
        int selectItem = input.nextInt();
        input.nextLine();

        response = switch (item[selectItem - 1].getItemImpact()) {
            case 2 -> dialogueRandomizer(characterDialogue.getGIFT_SATISFIED());
            case 1 -> dialogueRandomizer(characterDialogue.getGIFT_LIKES());
            case 0 -> dialogueRandomizer(characterDialogue.getGIFT_NEUTRAL());
            case -3 -> dialogueRandomizer(characterDialogue.getGIFT_DISAPPOINTED());
            default -> new Dialogue("Error", "Something went wrong", CharacterState.NEUTRAL);
        };
        dialogue(response);
    }

    private void dialogue(Dialogue dialogue){
        showSprite(dialogue.getState());
        System.out.println("[ " + dialogue.getSpeaker() + " ]");
        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.print  ("│ ");
        for (int j = 0; j < dialogue.getLine().length(); j++) {
            System.out.print(dialogue.getLine().charAt(j));
            System.out.flush();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
        System.out.print("└─────────────────────────────────────────────────────────────────────────────────────┘");
        input.nextLine();
        currentState = AppState.IDLE;
    }

    private void dialogue(Dialogue[] dialogues){
        for (Dialogue dialogue : dialogues) {
            String lineQueue = dialogue.getLine();

            showSprite(dialogue.getState());
            System.out.println("[ " + dialogue.getSpeaker() + " ]");
            System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.print("│ ");
            for (int j = 0; j < lineQueue.length(); j++) {
                System.out.print(lineQueue.charAt(j));
                System.out.flush();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println();
            System.out.print("└─────────────────────────────────────────────────────────────────────────────────────┘");
            input.nextLine();
        }
        currentState = AppState.IDLE;
    }

    private void tease(){
        Dialogue[] teaseLine = characterDialogue.getTEASE();
        Dialogue[] teaseResponse = characterDialogue.getTEASE_RESPONSE();

        Dialogue playerLine = dialogueRandomizer(teaseLine);
        Dialogue charLine = dialogueRandomizer(teaseResponse);

        Dialogue[] teaseSequence = {playerLine, charLine};

        dialogue(teaseSequence);
        currentCharacter.updateAffection(0);
    }

    private void handleGameplay(){
        System.out.println();
        System.out.println("[G] Gift   [T] Tease   [F] Feed");
        System.out.print("> ");
        String btnInput = input.nextLine().toUpperCase();

        switch(btnInput){
            case "G":
                currentState = AppState.INVENTORY;
                break;
            case "T":
                tease();
                break;
            case "F":
                currentCharacter.feed();
                break;
        }
    }
}
