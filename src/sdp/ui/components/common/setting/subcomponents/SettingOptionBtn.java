package sdp.ui.components.common.setting.subcomponents;

import sdp.ui.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;

public abstract class SettingOptionBtn extends JButton {

    private static final int CORNER_RADIUS = 10;
    private static final int FONT_SIZE = 24;

    private final double scale;

    protected SettingOptionBtn(String text, double scale) {
        super(text);

        this.scale = scale;

        initializeButton();
    }

    private void initializeButton() {
        setFont(new Font(
            Font.SANS_SERIF,
            Font.PLAIN,
            scale(FONT_SIZE)
        ));

        setForeground(Color.WHITE);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setMargin(new Insets(0, 0, 0, 0));
    }

    protected Color getButtonColor() {
        return Color.decode("#737373");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = GraphicsUtil.createAntialiased(g);

        g2.setColor(getButtonColor());
        g2.fillRoundRect(
            0,
            0,
            getWidth(),
            getHeight(),
            scale(CORNER_RADIUS),
            scale(CORNER_RADIUS)
        );

        FontMetrics metrics = g2.getFontMetrics(getFont());
        String text = getText();

        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent() - metrics.getDescent();

        int textX = (getWidth() - textWidth) / 2;
        int textY = (getHeight() - textHeight) / 2 + metrics.getAscent();

        g2.setColor(getForeground());
        g2.setFont(getFont());
        g2.drawString(text, textX, textY);

        g2.dispose();
    }

    private int scale(int value) {
        return (int) Math.round(value * scale);
    }
}