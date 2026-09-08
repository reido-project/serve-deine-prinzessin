package sdp.ui.components.prinzessinSelect;

import javax.swing.*;
import java.awt.*;

public class CharacterName extends JLabel {
    private static final int CORNER_RADIUS = 10;

    public CharacterName() {
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setForeground(Color.WHITE);
        setFont(new Font(Font.SERIF, Font.PLAIN, 50));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(new Color(0, 0, 0, 175));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS);

        super.paintComponent(g2);
        g2.dispose();
    }
}
