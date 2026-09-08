package sdp.ui.components.game.dialog.subcomponents;

import javax.swing.*;
import java.awt.*;

public class DiscussBtn extends JButton {

    private static final Color BUTTON_COLOR =
        Color.decode("#00D0FF");

    private static final int FONT_SIZE = 20;

    public DiscussBtn(
        Runnable action,
        double scale
    ) {
        super("Discuss");

        if (action == null) {
            throw new IllegalArgumentException(
                "action cannot be null."
            );
        }

        setFont(
            new Font(
                Font.SANS_SERIF,
                Font.BOLD,
                scale(FONT_SIZE, scale)
            )
        );

        setForeground(Color.BLACK);
        setBackground(BUTTON_COLOR);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(true);
        setOpaque(true);

        setHorizontalAlignment(
            SwingConstants.CENTER
        );

        addActionListener(e -> action.run());
    }

    private static int scale(
        int value,
        double scale
    ) {
        return (int) Math.round(
            value * scale
        );
    }
}