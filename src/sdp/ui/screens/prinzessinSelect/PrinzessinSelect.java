package sdp.ui.screens.prinzessinSelect;

import sdp.AppController;
import sdp.ui.components.prinzessinSelect.CharacterName;
import sdp.characters.CharacterList;
import sdp.misc.Util;
import sdp.ui.GameCanvas;
import sdp.ui.components.common.setting.subcomponents.BackToTitleBtn;
import sdp.ui.components.prinzessinSelect.ChangeBtn;
import sdp.ui.components.prinzessinSelect.CharacterSprite;
import sdp.ui.components.prinzessinSelect.StartBtn;
import sdp.ui.screens.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PrinzessinSelect extends JPanel implements Screen {

    private static final String BACKGROUND_PATH = "/sdp/assets/bg/PrinzessinSelect.png";

    private static final int NAME_X = 640;
    private static final int NAME_Y = 260;
    private static final int NAME_WIDTH = 640;
    private static final int NAME_HEIGHT = 100;

    private static final int SPRITE_X = 560;
    private static final int SPRITE_Y = 350;
    private static final int SPRITE_WIDTH = 800;
    private static final int SPRITE_HEIGHT = 610;

    private static final int START_X = 530;
    private static final int CHANGE_X = 970;
    private static final int BUTTON_Y = 960;
    private static final int BUTTON_WIDTH = 400;
    private static final int BUTTON_HEIGHT = 100;

    private static final int BACK_BUTTON_X = 30;
    private static final int BACK_BUTTON_Y = 1000;
    private static final int BACK_BUTTON_WIDTH = 250;
    private static final int BACK_BUTTON_HEIGHT = 60;

    private final AppController appController;
    private final GameCanvas gameCanvas;
    private final Util util;

    private BufferedImage background;
    private CharacterList currentCharacter;

    private JLabel characterName;
    private CharacterSprite characterSprite;

    public PrinzessinSelect(AppController appController, GameCanvas gameCanvas) {
        this.appController = appController;
        this.gameCanvas = gameCanvas;
        this.util = new Util();

        currentCharacter = CharacterList.KYOKO;

        initialize();
    }

    private void initialize() {
        setLayout(null);

        loadBackground();
        initializeCharacterName();
        initializeCharacterSprite();
        initializeButtons();
        initializeBackButton();

        updateCharacterDisplay();
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(
                Objects.requireNonNull(
                    getClass().getResource(BACKGROUND_PATH)
                )
            );
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalStateException(
                "Failed to load PrinzessinSelect background: " + BACKGROUND_PATH,
                e
            );
        }
    }

    private void initializeCharacterName() {
        characterName = new CharacterName();

        characterName.setBounds(
            NAME_X,
            NAME_Y,
            NAME_WIDTH,
            NAME_HEIGHT
        );

        add(characterName);
    }

    private void initializeCharacterSprite() {
        characterSprite = new CharacterSprite();

        characterSprite.setBounds(
            SPRITE_X,
            SPRITE_Y,
            SPRITE_WIDTH,
            SPRITE_HEIGHT
        );

        add(characterSprite);
    }

    private void initializeButtons() {
        StartBtn startBtn = new StartBtn(appController, gameCanvas, this);
        ChangeBtn changeBtn = new ChangeBtn();

        startBtn.setBounds(
            START_X,
            BUTTON_Y,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        changeBtn.setBounds(
            CHANGE_X,
            BUTTON_Y,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        changeBtn.addActionListener(e -> changeCharacter());

        add(startBtn);
        add(changeBtn);
    }

    private void initializeBackButton() {
        BackToTitleBtn backToTitleBtn = new BackToTitleBtn(
            gameCanvas.getScale(),
            appController,
            gameCanvas
        );

        backToTitleBtn.setBounds(
            BACK_BUTTON_X,
            BACK_BUTTON_Y,
            BACK_BUTTON_WIDTH,
            BACK_BUTTON_HEIGHT
        );

        add(backToTitleBtn);
    }

    private void changeCharacter() {
        CharacterList[] characters = CharacterList.values();

        int currentIndex = currentCharacter.ordinal();
        int nextIndex = (currentIndex + 1) % characters.length;

        currentCharacter = characters[nextIndex];

        updateCharacterDisplay();
    }

    private void updateCharacterDisplay() {
        characterName.setText(currentCharacter.getFullName());

        String spritePath = util.parseSprite(
            currentCharacter.getNickname(),
            currentCharacter.getprinzessinSelectSprite()
        );

        characterSprite.loadSprite(spritePath);
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

    public CharacterList getCurrentCharacter() {
        return currentCharacter;
    }
}