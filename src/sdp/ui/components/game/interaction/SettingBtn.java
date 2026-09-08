package sdp.ui.components.game.interaction;

import sdp.AppController;
import sdp.ui.GameCanvas;
import sdp.ui.components.common.setting.SettingDialog;
import sdp.ui.screens.ScreenList;

import javax.swing.*;
import java.awt.*;

public class SettingBtn extends JButton {

    private final AppController appController;
    private final GameCanvas gameCanvas;

    public SettingBtn(
        AppController appController,
        GameCanvas gameCanvas
    ) {
        super("Setting");

        this.appController = appController;
        this.gameCanvas = gameCanvas;

        initialize();

        addActionListener(e -> openSetting());
    }

    private void initialize() {
        setBackground(Color.decode("#6DFF70"));

        setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                30
            )
        );

        setForeground(Color.BLACK);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(true);
        setOpaque(true);

        setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void openSetting() {
        Window owner =
            SwingUtilities.getWindowAncestor(gameCanvas);

        SettingDialog dialog = new SettingDialog(
            owner,
            gameCanvas,
            appController,
            ScreenList.GAME
        );

        dialog.setVisible(true);
    }
}