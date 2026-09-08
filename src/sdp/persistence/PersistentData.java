package sdp.persistence;

import sdp.assets.music.MusicList;
import sdp.assets.sprite.SpriteState;
import sdp.characters.Preference;
import sdp.dialogues.DialogueType;
import sdp.dialogues.StoryState;
import sdp.gameplay.GameState;
import sdp.characters.CharacterList;
import sdp.items.ItemList;

import java.util.Set;

public final class PersistentData {

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

    private CharacterList prinzessinId;
    private int prinzessinAffection;
    private int prinzessinHunger;


    // =========================
    // DIALOGUE DATA
    // =========================

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


    public CharacterList getPrinzessinId() {
        return prinzessinId;
    }

    public void setPrinzessinId(CharacterList prinzessinId) {
        this.prinzessinId = prinzessinId;
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
}