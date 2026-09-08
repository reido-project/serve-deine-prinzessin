package sdp.ui;

import javax.swing.*;
import java.awt.*;

public class GameViewport extends JPanel {
    private final GameCanvas canvas;

    public GameViewport() {
        canvas = new GameCanvas();
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        add(canvas, BorderLayout.CENTER);
    }

    public GameCanvas getCanvas() { return canvas; }
}