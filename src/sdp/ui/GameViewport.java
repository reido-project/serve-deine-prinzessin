package sdp.ui;

import javax.swing.*;
import java.awt.*;

public class GameViewport extends JPanel {

    private final GameCanvas gameCanvas;

    public GameViewport() {
        gameCanvas = new GameCanvas();

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        add(
            gameCanvas,
            BorderLayout.CENTER
        );
    }

    public GameCanvas getGameCanvas() {
        return gameCanvas;
    }
}