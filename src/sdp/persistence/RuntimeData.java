package sdp.persistence;

import sdp.assets.music.MusicList;
import sdp.assets.sprite.SpriteState;
import sdp.characters.Character;
import sdp.characters.Preference;
import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueType;
import sdp.dialogues.StoryState;
import sdp.gameplay.GameState;
import sdp.items.ItemList;

import java.util.Set;

public final class RuntimeData {

    // =========================
    // GAMEPLAY STATE
    // =========================

    private GameState gameState;
    private StoryState storyState;


    // =========================
    // PLAYER DATA
    // =========================

    private String playerName;
    private int playerInsanity;

    private Set<ItemList> inventory;


    // =========================
    // PRINZESSIN DATA
    // =========================

    private Character prinzessin;
    private int prinzessinAffection;
    private int prinzessinHunger;


    // =========================
    // DIALOGUE DATA
    // =========================

    private Dialogue[] dialogue;
    private DialogueType dialogueType;
    private Preference preference;
    private ItemList itemList;
    private int variantIdentifier;
    private int dialogueIndex;


    // =========================
    // AUDIO / VISUAL STATE
    // =========================

    private MusicList[] music;
    private SpriteState spriteState;


    //Consumable
    private long playerMoney;
    private int kotaJahatUses;
    private boolean microphonePelunasBroken;
    private boolean jariSukunaUsed;

    public boolean isJariSukunaUsed() {
        return jariSukunaUsed;
    }

    public void setJariSukunaUsed(boolean jariSukunaUsed) {
        this.jariSukunaUsed = jariSukunaUsed;
    }
//Getter Setter


    // =========================
    // GAMEPLAY STATE
    // =========================

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public StoryState getStoryState() {
        return storyState;
    }

    public void setStoryState(StoryState storyState) {
        this.storyState = storyState;
    }


    // =========================
    // PLAYER DATA
    // =========================

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerInsanity() {
        return playerInsanity;
    }

    public void setPlayerInsanity(int playerInsanity) {
        this.playerInsanity = playerInsanity;
    }

    public Set<ItemList> getInventory() {
        return inventory;
    }

    public void setInventory(Set<ItemList> inventory) {
        this.inventory = inventory;
    }


    // =========================
    // PRINZESSIN DATA
    // =========================

    public Character getPrinzessin() {
        return prinzessin;
    }

    public void setPrinzessin(Character prinzessin) {
        this.prinzessin = prinzessin;
    }

    public int getPrinzessinAffection() {
        return prinzessinAffection;
    }

    public void setPrinzessinAffection(int prinzessinAffection) {
        this.prinzessinAffection = prinzessinAffection;
    }

    public int getPrinzessinHunger() {
        return prinzessinHunger;
    }

    public void setPrinzessinHunger(int prinzessinHunger) {
        this.prinzessinHunger = prinzessinHunger;
    }


    // =========================
    // DIALOGUE DATA
    // =========================

    public Dialogue[] getDialogue() {
        return dialogue;
    }

    public void setDialogue(Dialogue[] dialogue) {
        this.dialogue = dialogue;
    }

    public DialogueType getDialogueType() {
        return dialogueType;
    }

    public void setDialogueType(DialogueType dialogueType) {
        this.dialogueType = dialogueType;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public ItemList getItemList() {
        return itemList;
    }

    public void setItemList(ItemList itemList) {
        this.itemList = itemList;
    }

    public int getVariantIdentifier() {
        return variantIdentifier;
    }

    public void setVariantIdentifier(int variantIdentifier) {
        this.variantIdentifier = variantIdentifier;
    }

    public int getDialogueIndex() {
        return dialogueIndex;
    }

    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }


    // =========================
    // AUDIO / VISUAL STATE
    // =========================

    public MusicList[] getMusic() {
        return music;
    }

    public void setMusic(MusicList[] music) {
        this.music = music;
    }

    public SpriteState getSpriteState() {
        return spriteState;
    }

    public void setSpriteState(SpriteState spriteState) {
        this.spriteState = spriteState;
    }


    // Consumables

    public long getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(long playerMoney) {
        this.playerMoney = playerMoney;
    }

    public int getKotaJahatUses() {
        return kotaJahatUses;
    }

    public void setKotaJahatUses(int kotaJahatUses) {
        this.kotaJahatUses = kotaJahatUses;
    }

    public boolean isMicrophonePelunasBroken() {
        return microphonePelunasBroken;
    }

    public void setMicrophonePelunasBroken(boolean microphonePelunasBroken) {
        this.microphonePelunasBroken = microphonePelunasBroken;
    }

    public void changePrinzessinAffection(int delta) {
        setPrinzessinAffection(
            getPrinzessinAffection() + delta
        );
    }

    public void incrementKotaJahatUses() {
        kotaJahatUses++;
    }
}