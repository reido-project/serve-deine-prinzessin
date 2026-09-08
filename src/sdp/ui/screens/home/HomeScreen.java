package sdp.ui.screens.home;

import sdp.AppController;
import sdp.assets.music.MusicList;
import sdp.audio.AudioController;
import sdp.ui.GameCanvas;
import sdp.ui.components.home.LoadBtn;
import sdp.ui.components.home.QuitGameBtn;
import sdp.ui.components.home.SettingBtn;
import sdp.ui.components.home.StartBtn;
import sdp.ui.screens.Screen;
import sdp.ui.screens.ScreenController;
import sdp.ui.screens.ScreenList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class HomeScreen extends JPanel implements Screen {

    private final ScreenList screen = ScreenList.HOME;

    private static final int BUTTON_WIDTH = 400;
    private static final int BUTTON_HEIGHT = 100;
    private static final int BUTTON_GAP = 42;

    private static final int BUTTON_X = (GameCanvas.WIDTH - BUTTON_WIDTH) / 2;

    private static final int BUTTON_START_Y = 440;

    private static final String BACKGROUND_PATH = "/sdp/assets/bg/Home.png";

    private final AppController appController;
    private final GameCanvas gameCanvas;
    private final ScreenController screenController;

    private BufferedImage background;

    private final AudioController audio = AudioController.getInstance(); //test

    public HomeScreen(
        AppController appController,
        GameCanvas gameCanvas,
        ScreenController screenController
    ) {
        this.appController = appController;
        this.gameCanvas = gameCanvas;
        this.screenController = screenController;

        audio.playMusic(MusicList.SECOND_HEARTBEAT); //test

        initialize();
    }

    private void initialize() {
        setLayout(null);

        loadBackground();
        initializeComponents();
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResource(BACKGROUND_PATH)));
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalStateException("Failed to load Home background: " + BACKGROUND_PATH, e);
        }
    }

    private void initializeComponents() {
        StartBtn startBtn = new StartBtn(appController, gameCanvas, screenController);

        LoadBtn loadBtn = new LoadBtn();

        SettingBtn settingBtn = new SettingBtn(appController, gameCanvas, screen);

        QuitGameBtn quitGameBtn = new QuitGameBtn();

        int buttonStep = BUTTON_HEIGHT + BUTTON_GAP;

        startBtn.setBounds(
            BUTTON_X,
            BUTTON_START_Y,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        loadBtn.setBounds(
            BUTTON_X,
            BUTTON_START_Y + buttonStep,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        settingBtn.setBounds(
            BUTTON_X,
            BUTTON_START_Y + buttonStep * 2,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        quitGameBtn.setBounds(
            BUTTON_X,
            BUTTON_START_Y + buttonStep * 3,
            BUTTON_WIDTH,
            BUTTON_HEIGHT
        );

        add(startBtn);
        add(loadBtn);
        add(settingBtn);
        add(quitGameBtn);
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
}