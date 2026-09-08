package sdp.ui.components.home;

import sdp.AppController;
import sdp.ui.GameCanvas;
import sdp.ui.dialog.GameDialog;
import sdp.ui.screens.ScreenController;
import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

public class StartBtn extends JButton {

    private static final int CORNER_RADIUS = 10;

    private final AppController appController;
    private final GameCanvas gameCanvas;
    private final ScreenController screenController;

    public StartBtn(
        AppController appController,
        GameCanvas gameCanvas,
        ScreenController screenController
    ) {
        super("Start");

        this.appController = appController;
        this.gameCanvas = gameCanvas;
        this.screenController = screenController;

        initializeButton();

        addActionListener(e -> {
            Window owner = SwingUtilities.getWindowAncestor(gameCanvas);

            NameInputDialog dialog = new NameInputDialog(
                owner,
                gameCanvas,
                appController,
                screenController
            );

            dialog.setVisible(true);
        });
    }

    private void initializeButton() {
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