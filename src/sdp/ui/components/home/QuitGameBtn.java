package sdp.ui.components.home;

import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

public class QuitGameBtn extends JButton {

    private static final int CORNER_RADIUS = 10;

    public QuitGameBtn() {
        super("Quit Game");

        initialize();

        addActionListener(e -> {
            System.exit(0);
        });
    }

    private void initialize() {
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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 =
            GraphicsUtil.createAntialiased(g);

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