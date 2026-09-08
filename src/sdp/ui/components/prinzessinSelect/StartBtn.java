package sdp.ui.components.prinzessinSelect;

import sdp.AppController;
import sdp.gameplay.GameController;
import sdp.ui.GameCanvas;
import sdp.ui.screens.prinzessinSelect.PrinzessinSelect;
import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

public class StartBtn extends JButton {

    private static final int CORNER_RADIUS = 10;

    private final AppController appController;
    private final GameCanvas gameCanvas;

    private final PrinzessinSelect prinzessinSelect;

    public StartBtn(
        AppController appController,
        GameCanvas gameCanvas,
        PrinzessinSelect prinzessinSelect
    ) {
        super("Start");

        this.appController = appController;
        this.gameCanvas = gameCanvas;
        this.prinzessinSelect = prinzessinSelect;

        initializeButton();

        addActionListener(e -> startGame());
    }

    private void initializeButton() {
        setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
                50
            )
        );

        setForeground(Color.BLACK);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }

    private void startGame() {
        GameController gameController =
            appController.getGameController();

        gameController.selectPrinzessin(
            prinzessinSelect.getCurrentCharacter()
        );

        gameController.startNewGame();
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