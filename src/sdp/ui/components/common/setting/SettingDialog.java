package sdp.ui.components.common.setting;

import sdp.AppController;
import sdp.ui.GameCanvas;
import sdp.ui.components.common.setting.subcomponents.*;
import sdp.ui.dialog.GameDialog;
import sdp.ui.screens.ScreenList;

import javax.swing.*;
import java.awt.*;

public class SettingDialog extends GameDialog {

    private static final int WIDTH = 865;
    private static final int HEIGHT = 770;

    private static final int TITLE_Y = 25;

    private static final int CLOSE_X = 800;
    private static final int CLOSE_Y = 25;

    private static final int DISPLAY_X = 58;
    private static final int DISPLAY_Y = 130;

    private static final int DISPLAY_BUTTON_Y = 190;
    private static final int DISPLAY_BUTTON_WIDTH = 176;
    private static final int DISPLAY_BUTTON_HEIGHT = 42;
    private static final int DISPLAY_BUTTON_GAP = 15;

    private static final int GAME_BUTTON_X = 58;
    private static final int GAME_BUTTON_Y = 690;

    private final AppController appController;
    private final ScreenList screen;
    private final GameCanvas gameCanvas;

    public SettingDialog(
        Window owner,
        GameCanvas gameCanvas,
        AppController appController,
        ScreenList screen
    ) {
        super(owner, gameCanvas);

        this.appController = appController;
        this.screen = screen;
        this.gameCanvas = gameCanvas;

        initialize();

        applyLogicalSize();
    }

    private void initialize() {
        JPanel content = new JPanel(null);
        content.setBackground(Color.decode("#454545"));

        setContentPane(content);

        initializeTitle(content);
        initializeCloseButton(content);
        initializeDisplay(content);
        initializeGameButtons(content);
    }

    private void initializeTitle(JPanel content) {
        JLabel title = new JLabel("Setting", SwingConstants.CENTER);

        title.setForeground(Color.WHITE);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, scale(50)));
        title.setBounds(0, scale(TITLE_Y), scale(WIDTH), scale(60));

        content.add(title);
    }

    private void initializeCloseButton(JPanel content) {
        CloseBtn closeBtn = new CloseBtn(getScale());

        closeBtn.setBounds(
            scale(CLOSE_X),
            scale(CLOSE_Y),
            scale(45),
            scale(45)
        );

        content.add(closeBtn);
    }

    private void initializeDisplay(JPanel content) {
        JLabel display = new JLabel("Display");

        display.setForeground(Color.WHITE);
        display.setFont(new Font(Font.SANS_SERIF, Font.BOLD, scale(30)));
        display.setBounds(
            scale(DISPLAY_X),
            scale(DISPLAY_Y),
            scale(200),
            scale(40)
        );

        content.add(display);

        int buttonStep = DISPLAY_BUTTON_WIDTH + DISPLAY_BUTTON_GAP;

        Resolution1920Btn resolution1920Btn = new Resolution1920Btn(appController, getScale());
        resolution1920Btn.setBounds(
            scale(DISPLAY_X),
            scale(DISPLAY_BUTTON_Y),
            scale(DISPLAY_BUTTON_WIDTH),
            scale(DISPLAY_BUTTON_HEIGHT)
        );

        Resolution1600Btn resolution1600Btn = new Resolution1600Btn(appController, getScale());
        resolution1600Btn.setBounds(
            scale(DISPLAY_X + buttonStep),
            scale(DISPLAY_BUTTON_Y),
            scale(DISPLAY_BUTTON_WIDTH),
            scale(DISPLAY_BUTTON_HEIGHT)
        );

        Resolution1280Btn resolution1280Btn = new Resolution1280Btn(appController, getScale());
        resolution1280Btn.setBounds(
            scale(DISPLAY_X + buttonStep * 2),
            scale(DISPLAY_BUTTON_Y),
            scale(DISPLAY_BUTTON_WIDTH),
            scale(DISPLAY_BUTTON_HEIGHT)
        );

        FullscreenBtn fullscreenBtn = new FullscreenBtn(appController, getScale());
        fullscreenBtn.setBounds(
            scale(DISPLAY_X + buttonStep * 3),
            scale(DISPLAY_BUTTON_Y),
            scale(DISPLAY_BUTTON_WIDTH),
            scale(DISPLAY_BUTTON_HEIGHT)
        );

        content.add(resolution1920Btn);
        content.add(resolution1600Btn);
        content.add(resolution1280Btn);
        content.add(fullscreenBtn);
    }

    private void initializeGameButtons(JPanel content) {
        SaveBtn saveBtn = new SaveBtn(getScale());
        LoadBtn loadBtn = new LoadBtn(getScale());
        BackToTitleBtn backToTitleBtn = new BackToTitleBtn(getScale(), appController, gameCanvas);
        QuitGameBtn quitGameBtn = new QuitGameBtn(getScale());

        int buttonStep = DISPLAY_BUTTON_WIDTH + DISPLAY_BUTTON_GAP;

        saveBtn.setBounds(
            scale(GAME_BUTTON_X),
            scale(GAME_BUTTON_Y),
            scale(DISPLAY_BUTTON_WIDTH),
            scale(DISPLAY_BUTTON_HEIGHT)
        );

        loadBtn.setBounds(
            scale(GAME_BUTTON_X + buttonStep),
            scale(GAME_BUTTON_Y),
            scale(DISPLAY_BUTTON_WIDTH),
            scale(DISPLAY_BUTTON_HEIGHT)
        );

        backToTitleBtn.setBounds(
            scale(GAME_BUTTON_X + buttonStep * 2),
            scale(GAME_BUTTON_Y),
            scale(DISPLAY_BUTTON_WIDTH),
            scale(DISPLAY_BUTTON_HEIGHT)
        );

        quitGameBtn.setBounds(
            scale(GAME_BUTTON_X + buttonStep * 3),
            scale(GAME_BUTTON_Y),
            scale(DISPLAY_BUTTON_WIDTH),
            scale(DISPLAY_BUTTON_HEIGHT)
        );

        boolean showGameButtons = screen == ScreenList.GAME;

        saveBtn.setVisible(showGameButtons);
        loadBtn.setVisible(showGameButtons);
        backToTitleBtn.setVisible(showGameButtons);
        quitGameBtn.setVisible(showGameButtons);

        content.add(saveBtn);
        content.add(loadBtn);
        content.add(backToTitleBtn);
        content.add(quitGameBtn);
    }

    @Override
    protected int getLogicalWidth() {
        return WIDTH;
    }

    @Override
    protected int getLogicalHeight() {
        return HEIGHT;
    }
}