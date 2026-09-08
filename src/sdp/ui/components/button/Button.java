package sdp.ui.components.button;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Button extends JButton {
    private final ButtonOption appearance;
    private Color selectedBackground;
    private Color selectedForeground;

    public Button(String text, ButtonOption appearance, Runnable onClick) {
        super(text);
        this.appearance = appearance;

        initializeButton();
        if (onClick != null) {
            addActionListener(e -> onClick.run());
        }
    }

    public Button(ButtonOption appearance, Runnable onClick) {
        this(appearance.getText(), appearance, onClick);
    }

    public <T> Button(String text, ButtonOption appearance, Supplier<T> action, Consumer<T> hostReceiver) {
        super(text);
        this.appearance = appearance;

        initializeButton();
        if (action != null && hostReceiver != null) {
            addActionListener(e -> {
                T result = action.get();
                hostReceiver.accept(result);
            });
        }
    }

    public <T> Button(ButtonOption appearance, Supplier<T> action, Consumer<T> hostReceiver) {
        this(appearance.getText(), appearance, action, hostReceiver);
    }

    private void initializeButton() {
        setFont(appearance.getFont());
        setForeground(appearance.getForeground());

        Dimension size = new Dimension(appearance.getWidth(), appearance.getHeight());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setMargin(new Insets(0, 0, 0, 0));

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setSelectedState(boolean selected) {
        if (selected) {
            selectedBackground = Color.decode("#45BEFF");
            selectedForeground = Color.BLACK;
        } else {
            selectedBackground = null;
            selectedForeground = null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(getFont());

        Color backgroundColor = selectedBackground != null ? selectedBackground : appearance.getBackground();
        Color foregroundColor = selectedForeground != null ? selectedForeground : getForeground();

        g2.setColor(backgroundColor);
        g2.fillRoundRect(
            0,
            0,
            getWidth(),
            getHeight(),
            appearance.getCornerRadius(),
            appearance.getCornerRadius()
        );

        FontMetrics metrics = g2.getFontMetrics();
        String text = getText();

        int textWidth = metrics.stringWidth(text);
        int textX = (getWidth() - textWidth) / 2;
        int textY = (getHeight() - metrics.getAscent() - metrics.getDescent()) / 2 + metrics.getAscent();

        g2.setColor(foregroundColor);
        g2.drawString(text, textX, textY);

        g2.dispose();
    }

    public ButtonOption getAppearance() {
        return appearance;
    }
}