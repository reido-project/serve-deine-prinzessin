package sdp.ui.screens.game;

import sdp.AppController;
import sdp.assets.sprite.SpriteState;
import sdp.characters.CharacterList;
import sdp.dialogues.Dialogue;
import sdp.dialogues.DialogueController;
import sdp.dialogues.StoryState;
import sdp.gameplay.GameController;
import sdp.gameplay.GameState;
import sdp.gameplay.interactions.Tease;
import sdp.persistence.DataController;
import sdp.ui.GameCanvas;
import sdp.ui.components.game.CharacterSprite;
import sdp.ui.components.game.DialogueBox;
import sdp.ui.components.game.Stat;
import sdp.ui.components.game.interaction.FeedBtn;
import sdp.ui.components.game.interaction.InventoryBtn;
import sdp.ui.components.game.interaction.SettingBtn;
import sdp.ui.components.game.interaction.TalkBtn;
import sdp.ui.components.game.interaction.TeaseBtn;
import sdp.ui.screens.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameScreen extends JPanel implements Screen {

    // --------------------------------------------------
    // GAME RESOLUTION
    // --------------------------------------------------

    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;

    // --------------------------------------------------
    // BUTTON
    // --------------------------------------------------

    private static final int BUTTON_X = 20;
    private static final int BUTTON_Y = 795;

    private static final int BUTTON_WIDTH = 212;
    private static final int BUTTON_HEIGHT = 43;

    private static final int BUTTON_GAP = 50;

    // --------------------------------------------------
    // SPRITE
    // --------------------------------------------------

    private static final int SPRITE_WIDTH = 970;

    private static final int SPRITE_X =
        (SCREEN_WIDTH - SPRITE_WIDTH) / 2;

    private static final int SPRITE_BOTTOM =
        SCREEN_HEIGHT;

    // --------------------------------------------------
    // SETTING
    // --------------------------------------------------

    private final AppController appController;
    private final GameCanvas gameCanvas;
    private final GameController gameController;

    private BufferedImage background;

    private CharacterSprite characterSprite;
    private Stat stat;

    private TalkBtn talkBtn;
    private TeaseBtn teaseBtn;
    private FeedBtn feedBtn;
    private InventoryBtn inventoryBtn;
    private SettingBtn settingBtn;

    private DialogueFlow dialogueFlow = DialogueFlow.NORMAL;

    private DialogueBox dialogueBox;

    public GameScreen(
        AppController appController,
        GameCanvas gameCanvas
    ) {
        this.appController = appController;
        this.gameCanvas = gameCanvas;
        this.gameController = appController.getGameController();

        initialize();
    }

    private void initialize() {
        setLayout(null);

        loadBackground();
        initializeComponents();
    }

    private void loadBackground() {
        String backgroundPath =
            gameController.resolveBackground(
                gameController.getPrinzessinId()
            );

        try {
            background = ImageIO.read(
                Objects.requireNonNull(
                    getClass().getResource(backgroundPath)
                )
            );
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalStateException(
                "Failed to load Game background: " + backgroundPath,
                e
            );
        }
    }

    private void initializeComponents() {
        initializeCharacterSprite();

        initializeStat();

        initializeDialogueBox();

        initializeTalkButton();
        initializeTeaseButton();
        initializeInventoryButton();
        initializeFeedButton();
        initializeSettingButton();

        setComponentZOrder(dialogueBox, 0);
        setComponentZOrder(stat, 1);
    }

    // --------------------------------------------------
    // SPRITE
    // --------------------------------------------------

    private void initializeCharacterSprite() {
        characterSprite = new CharacterSprite();

        characterSprite.setBounds(
            SPRITE_X,
            0,
            SPRITE_WIDTH,
            SCREEN_HEIGHT
        );

        CharacterList character = gameController.getPrinzessinId();

        if (character != null) {
            characterSprite.loadSprite(
                character,
                gameController.getSpriteState()
            );
        }

        add(characterSprite);
    }

    private void initializeStat() {
        stat = new Stat();

        stat.setBounds(
            0,
            0,
            SCREEN_WIDTH,
            SCREEN_HEIGHT
        );

        add(stat);
    }

    private enum DialogueFlow {
        NORMAL,
        TEASE_INITIAL,
        TEASE_RESPONSE
    }

    private void initializeDialogueBox() {
        DialogueController controller =
            gameController.getDialogueController();

        dialogueBox = new DialogueBox(
            controller,
            this::advanceDialogue
        );

        dialogueBox.setBounds(
            245,
            795,
            1650,
            285
        );

        add(dialogueBox);

        controller.startStory(
            StoryState.PROLOGUE
        );

        gameController.setGameState(
            GameState.DIALOGUE
        );

        refreshDialogue();
    }

    private void advanceDialogue() {

        DialogueController controller =
            gameController.getDialogueController();

        Dialogue current =
            controller.getCurrentDialogue();

        if (current == null) {
            return;
        }

        boolean hasNext =
            controller.advance();

        if (hasNext) {
            refreshDialogue();
            return;
        }

        // =====================================================
        // TEASE INITIAL -> TEASE RESPONSE
        // =====================================================

        if (dialogueFlow == DialogueFlow.TEASE_INITIAL) {

            controller.clear();

            int affection =
                DataController
                    .getInstance()
                    .getRuntimeData()
                    .getPrinzessinAffection();

            int delta =
                Tease.getAffectionDelta(affection);

            DataController
                .getInstance()
                .getRuntimeData()
                .changePrinzessinAffection(delta);

            affection += delta;

            controller.startTeaseResponse(
                affection
            );

            dialogueFlow =
                DialogueFlow.TEASE_RESPONSE;

            refreshDialogue();
            return;
        }

        // =====================================================
        // DIALOGUE SELESAI
        // =====================================================

        controller.clear();

        dialogueFlow =
            DialogueFlow.NORMAL;

        gameController.setGameState(
            GameState.IDLE
        );

        dialogueBox.refresh();

        characterSprite.loadSprite(
            gameController.getPrinzessinId(),
            gameController.getSpriteState()
        );

        repaint();
    }

    // --------------------------------------------------
    // INTERACTION BUTTONS
    // --------------------------------------------------

    private void initializeTalkButton() {
        talkBtn = new TalkBtn(
            gameController,
            gameCanvas,
            this::refreshDialogue
        );

        talkBtn.setBounds(
            BUTTON_X,
            BUTTON_Y,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        add(talkBtn);
    }

    private void initializeTeaseButton() {
        teaseBtn = new TeaseBtn(
            gameController,
            this
        );

        teaseBtn.setBounds(
            BUTTON_X,
            BUTTON_Y + BUTTON_GAP,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        add(teaseBtn);
    }

    private void initializeInventoryButton() {
        inventoryBtn = new InventoryBtn(
            gameCanvas, gameController, this
        );

        inventoryBtn.setBounds(
            BUTTON_X,
            BUTTON_Y + BUTTON_GAP * 2,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        add(inventoryBtn);
    }

    private void initializeFeedButton() {
        feedBtn = new FeedBtn(
            gameController,
            this::refreshDialogue
        );

        feedBtn.setBounds(
            BUTTON_X,
            BUTTON_Y + BUTTON_GAP * 3,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        add(feedBtn);
    }

    public void startTease() {

        dialogueFlow = DialogueFlow.TEASE_INITIAL;

        gameController.startTease();

        refreshDialogue();
    }

    private void initializeSettingButton() {
        settingBtn = new SettingBtn(
            appController,
            gameCanvas
        );

        settingBtn.setBounds(
            BUTTON_X,
            BUTTON_Y + BUTTON_GAP * 4,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        add(settingBtn);
    }

    // --------------------------------------------------
    // BACKGROUND
    // --------------------------------------------------
    public void startItemDialogue() {
        gameController.setGameState(GameState.DIALOGUE);

        refreshDialogue();
    }

    public void refreshDialogue() {
        DialogueController controller =
            gameController.getDialogueController();

        Dialogue dialogue =
            controller.getCurrentDialogue();

        dialogueBox.refresh();

        stat.refresh();

        if (dialogue == null) {
            return;
        }

        SpriteState sprite =
            dialogue.sprite();

        if (sprite != null) {
            characterSprite.loadSprite(
                gameController.getPrinzessinId(),
                sprite
            );
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background == null) {
            return;
        }

        g.drawImage(
            background,
            0,
            0,
            getWidth(),
            getHeight(),
            null
        );
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    private double getScale() {
        return (double) gameCanvas.getWidth() / SCREEN_WIDTH;
    }
}