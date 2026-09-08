package sdp.ui.components.prinzessinSelect;

import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

public class ChangeBtn extends JButton {

    private static final int CORNER_RADIUS = 10;

    public ChangeBtn() {
        super("Change");

        initialize();
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

        g2.setColor(new Color(64, 180, 235));

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