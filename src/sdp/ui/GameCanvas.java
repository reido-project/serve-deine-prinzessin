package sdp.ui;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    private double scale = 1.0;

    private int offsetX;
    private int offsetY;

    public GameCanvas() {
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    public void doLayout() {
        updateTransform();

        for (Component component : getComponents()) {
            if (component instanceof JComponent jComponent) {
                layoutComponent(jComponent);
            }
        }
    }

    private void layoutComponent(JComponent component) {
        component.setBounds(
            offsetX,
            offsetY,
            scale(WIDTH),
            scale(HEIGHT)
        );

        UiScaler.scaleChildren(
            component,
            scale
        );
    }

    private void updateTransform() {
        int width = getWidth();
        int height = getHeight();

        if (width <= 0 || height <= 0) {
            return;
        }

        double scaleX =
            width / (double) WIDTH;

        double scaleY =
            height / (double) HEIGHT;

        scale = Math.min(scaleX, scaleY);

        int renderedWidth = scale(WIDTH);
        int renderedHeight = scale(HEIGHT);

        offsetX =
            (width - renderedWidth) / 2;

        offsetY =
            (height - renderedHeight) / 2;
    }

    private int scale(int value) {
        return (int) Math.round(value * scale);
    }

    public double getScale() {
        return scale;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
}