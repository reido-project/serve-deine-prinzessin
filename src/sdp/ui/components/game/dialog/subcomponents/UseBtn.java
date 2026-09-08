package sdp.ui.components.game.dialog.subcomponents;

import javax.swing.*;
import java.awt.*;

public class UseBtn extends JButton {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final int RADIUS = 10;

    private static final Color BUTTON_COLOR =
        Color.decode("#00D0FF");

    private final double scale;

    public UseBtn(
        Runnable action,
        double scale
    ) {
        super("Use");

        if (action == null) {
            throw new IllegalArgumentException(
                "Action cannot be null."
            );
        }

        this.scale = scale;

        initializeButton(action);
    }

    private void initializeButton(Runnable action) {
        setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
                scale(24)
            )
        );

        setForeground(Color.BLACK);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        addActionListener(e -> action.run());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 =
            (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(BUTTON_COLOR);

        int radius = scale(RADIUS);

        g2.fillRoundRect(
            0,
            0,
            getWidth(),
            getHeight(),
            radius,
            radius
        );

        g2.dispose();

        super.paintComponent(g);
    }

    private int scale(int value) {
        return (int) Math.round(
            value * scale
        );
    }
}