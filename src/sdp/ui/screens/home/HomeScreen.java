package sdp.ui.screens.home;

import sdp.Config;
import sdp.GameController;
import sdp.modules.audio.AudioAPI;
import sdp.shared.utils.ImageUtil;
import sdp.ui.components.button.Button;
import sdp.ui.components.button.ButtonOption;
import sdp.ui.dialog.SettingDialog;
import sdp.ui.screens.Screen;
import sdp.ui.screens.ScreenController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HomeScreen extends JPanel implements Screen, AudioAPI {
    private final BufferedImage background = ImageUtil.loadImage(Config.HOME_BACKGROUND);

    public HomeScreen(GameController gameController, ScreenController screens) {
        setLayout(null);
        JPanel buttonColumn = createButtonColumn(gameController, screens);
        buttonColumn.setBounds(758, 450, 405, 604);
        add(buttonColumn);

        playMusic(Config.HOME_SOUNDTRACK);
    }

    private JPanel createButtonColumn(GameController gameController, ScreenController screens) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(null);
        Button start = new Button("Start", ButtonOption.LargeButton, screens::showPrinzessinSelect);
        Button[] buttons = getButtons(gameController, screens, start);

        int gap = 26;

        for (int index = 0; index < buttons.length; index++) {
            Button btn = buttons[index];
            int w = btn.getAppearance().getWidth();
            int h = btn.getAppearance().getHeight();

            int y = index * (h + gap);
            btn.setBounds(0, y, w, h);
            panel.add(btn);
        }
        return panel;
    }

    private Button[] getButtons(GameController gameController, ScreenController screens, Button start) {
        Button load = new Button("Load", ButtonOption.LargeButton, () -> {
            gameController.getSessionController().load();
            screens.showGame();
        });
        Button setting = new Button("Setting", ButtonOption.LargeButton, () -> {
            Window owner = SwingUtilities.getWindowAncestor(this);
            new SettingDialog(owner, gameController, screens.getFrame(), screens, false).setVisible(true);
        });
        Button quit = new Button("Quit", ButtonOption.LargeButton, () -> System.exit(0));
        return new Button[]{start, load, setting, quit};
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }

    @Override
    public JComponent getComponent() { return this; }
}