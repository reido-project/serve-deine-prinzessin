package sdp.gameplay;

import sdp.AppController;
import sdp.assets.sprite.SpriteState;
import sdp.characters.CharacterList;
import sdp.characters.Preference;
import sdp.dialogues.DialogueController;
import sdp.dialogues.DialogueType;
import sdp.gameplay.interactions.Feed;
import sdp.gameplay.interactions.InteractionController;
import sdp.gameplay.interactions.talk.TopicID;
import sdp.items.ItemController;
import sdp.items.ItemList;
import sdp.persistence.DataController;
import sdp.persistence.RuntimeData;

public class GameController {

    private static final String MAKOTO_ROOM = "/sdp/assets/bg/game/Makoto_Room.png";

    private static final String AMATSUME_JINJA = "/sdp/assets/bg/game/Amatsume_Jinja.png";

    private static final String HIKARIZAKA_HIGH_SCHOOL = "/sdp/assets/bg/game/Hikarizaka_High_School.png";

    private final AppController appController;
    private final DataController dataController;
    private DialogueController dialogueController;
    private ItemController itemController;
    private InteractionController interactionController;
    private Feed feed;

    private final RuntimeData runtimeData;

    private GameState gameState;

    private static final int TALK_HUNGER_COST = 15;
    private static final int TEASE_HUNGER_COST = 10;

    public GameController(AppController appController) {
        this.appController = appController;
        this.dataController = DataController.getInstance();
        this.runtimeData = dataController.getRuntimeData();

        this.gameState = GameState.IDLE;
    }

    public void startNewGame() {
        if (runtimeData.getPrinzessin() == null) {
            throw new IllegalStateException(
                "Cannot start game without a Prinzessin."
            );
        }

        initializeDialogueController();

        gameState = GameState.IDLE;

        appController.showGameScreen();
    }

    public void initializeGame(CharacterList prinzessinId) {
        dataController.initializeNewGame(prinzessinId);

        initializeDialogueController();

        gameState = GameState.IDLE;

        appController.showGameScreen();
    }

    public void initializeDialogueController() {
        CharacterList character = getPrinzessinId();

        if (character == null) {
            throw new IllegalStateException(
                "Cannot initialize dialogue without a Prinzessin."
            );
        }

        dialogueController =
            new DialogueController(character);

        feed = new Feed(dialogueController);

        itemController =
            new ItemController(
                dialogueController
            );

        interactionController =
            new InteractionController(
                itemController,
                character
            );
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public CharacterList getPrinzessinId() {
        if (runtimeData.getPrinzessin() == null) {
            return null;
        }

        return runtimeData.getPrinzessin().getId();
    }

    public String getBackgroundPath() {
        return resolveBackground(getPrinzessinId());
    }

    public String resolveBackground(CharacterList character) {
        if (character == null) {
            throw new IllegalStateException(
                "Cannot resolve game background without a selected character."
            );
        }

        return switch (character) {
            case KYOKO, CHIAKI -> MAKOTO_ROOM;
            case KAZUHA -> AMATSUME_JINJA;
            case TOMOYO -> HIKARIZAKA_HIGH_SCHOOL;
        };
    }

    public void selectPrinzessin(CharacterList character) {
        if (character == null) {
            throw new IllegalArgumentException(
                "Prinzessin cannot be null."
            );
        }

        dataController.initializeNewGame(character);
    }

    public SpriteState getSpriteState() {
        SpriteState state = runtimeData.getSpriteState();

        if (state != null) {
            return state;
        }

        return SpriteState.Neutral;
    }

    public DialogueController getDialogueController() {
        if (dialogueController == null) {
            throw new IllegalStateException(
                "DialogueController has not been initialized."
            );
        }

        return dialogueController;
    }

    public ItemController getItemController() {
        if (itemController == null) {
            throw new IllegalStateException(
                "ItemController has not been initialized."
            );
        }

        return itemController;
    }

    public InteractionController getInteractionController() {
        if (interactionController == null) {
            throw new IllegalStateException(
                "InteractionController has not been initialized."
            );
        }

        return interactionController;
    }

    public void startPreferenceDialogue(Preference preference) {
        getDialogueController().startPreference(preference);
        gameState = GameState.DIALOGUE;
    }

    public void startSpecialItemDialogue(ItemList item) {
        getDialogueController().startSpecialItem(item);
        gameState = GameState.DIALOGUE;
    }

    public void startTalk(TopicID topicID) {
        changePrinzessinHunger(-TALK_HUNGER_COST);

        getDialogueController().startTalk(topicID);
        gameState = GameState.DIALOGUE;
    }

    public void startTease() {
        changePrinzessinHunger(-TEASE_HUNGER_COST);

        getDialogueController().startTease();

        gameState = GameState.DIALOGUE;
    }

    public void feed() {
        if (gameState != GameState.IDLE) {
            return;
        }

        feed.execute();

        gameState = GameState.DIALOGUE;
    }

    private void changePrinzessinHunger(int delta) {
        runtimeData.setPrinzessinHunger(
            runtimeData.getPrinzessinHunger() + delta
        );
    }
}