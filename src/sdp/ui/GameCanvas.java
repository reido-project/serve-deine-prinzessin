package sdp.ui;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    public static final int LOGICAL_WIDTH = 1920;
    public static final int LOGICAL_HEIGHT = 1080;
    private double scale = 1.0;
    private int offsetX;
    private int offsetY;

    public GameCanvas() {
        setLayout(null);
        setPreferredSize(new Dimension(LOGICAL_WIDTH, LOGICAL_HEIGHT));
        setOpaque(true);
        setBackground(Color.BLACK);
    }

    public double getScale() { return scale; }
    public int getOffsetX() { return offsetX; }
    public int getOffsetY() { return offsetY; }

    @Override
    public void doLayout() {
        updateTransform();

        for (Component component : getComponents()) {
            component.setBounds(offsetX, offsetY, scale(LOGICAL_WIDTH), scale(LOGICAL_HEIGHT));
            if (component instanceof Container container) {
                for (Component child : container.getComponents()) {
                    scaleTree(child);
                }
            }
        }
    }

    private void updateTransform() {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) return;

        scale = Math.min(width / (double) LOGICAL_WIDTH, height / (double) LOGICAL_HEIGHT);
        int renderedWidth = scale(LOGICAL_WIDTH);
        int renderedHeight = scale(LOGICAL_HEIGHT);
        offsetX = (width - renderedWidth) / 2;
        offsetY = (height - renderedHeight) / 2;
    }

    private int scale(int value) {
        return (int) Math.round(value * scale);
    }

    private void scaleTree(Component component) {
        if (!(component instanceof JComponent swingComponent)) return;
        Rectangle logical = (Rectangle) swingComponent.getClientProperty("logicalBounds");
        if (logical == null) {
            logical = component.getBounds();
            swingComponent.putClientProperty("logicalBounds", logical);
        }
        component.setBounds(
            (int) Math.round(logical.x * scale),
            (int) Math.round(logical.y * scale),
            (int) Math.round(logical.width * scale),
            (int) Math.round(logical.height * scale)
        );
        Font logicalFont = (Font) swingComponent.getClientProperty("logicalFont");
        if (logicalFont == null) {
            logicalFont = swingComponent.getFont();
            swingComponent.putClientProperty("logicalFont", logicalFont);
        }
        if (logicalFont != null) {
            swingComponent.setFont(logicalFont.deriveFont((float) (logicalFont.getSize2D() * scale)));
        }
        if (component instanceof Container container) {
            for (Component child : container.getComponents()) scaleTree(child);
        }
    }
}