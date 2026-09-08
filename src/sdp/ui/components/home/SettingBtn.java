package sdp.ui.components.home;

import sdp.AppController;
import sdp.ui.GameCanvas;
import sdp.ui.components.common.setting.SettingDialog;
import sdp.ui.screens.ScreenList;
import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

public class SettingBtn extends JButton {

    private static final int CORNER_RADIUS = 10;

    private final AppController appController;
    private final GameCanvas gameCanvas;
    private final ScreenList screen;

    public SettingBtn(
        AppController appController,
        GameCanvas gameCanvas,
        ScreenList screen
    ) {
        super("Setting");

        this.appController = appController;
        this.gameCanvas = gameCanvas;
        this.screen = screen;

        initialize();

        addActionListener(e -> {
            Window owner = SwingUtilities.getWindowAncestor(gameCanvas);

            SettingDialog dialog = new SettingDialog(
                owner,
                gameCanvas,
                appController,
                screen
            );

            dialog.setVisible(true);
        });
    }

    private void initialize() {
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        setForeground(Color.BLACK);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = GraphicsUtil.createAntialiased(g);

        g2.setColor(Color.LIGHT_GRAY);

        g2.fillRoundRect(
            0,
            0,
            getWidth(),
            getHeight(),
            CORNER_RADIUS,
            CORNER_RADIUS
        );

        super.paintComponent(g2);

        g2.dispose();
    }
}