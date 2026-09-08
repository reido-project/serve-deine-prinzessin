package sdp.ui.components.common.setting.subcomponents;

import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

public class CloseBtn extends JButton {

    private static final int SIZE = 32;
    private static final int STROKE_WIDTH = 2;

    private final double scale;

    public CloseBtn(double scale) {
        this.scale = scale;

        initializeButton();

        addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);

            if (window != null) {
                window.dispose();
            }
        });
    }

    private void initializeButton() {
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = GraphicsUtil.createAntialiased(g);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(
            scale(STROKE_WIDTH),
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND
        ));

        int size = scale(SIZE);
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        g2.drawLine(x, y, x + size, y + size);
        g2.drawLine(x + size, y, x, y + size);

        g2.dispose();
    }

    private int scale(int value) {
        return (int) Math.round(value * scale);
    }
}