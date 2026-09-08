package sdp.ui.components.home.subcomponents;

import javax.swing.*;
import java.awt.*;

public class NameInput extends JTextField {

    private static final int FONT_SIZE = 24;

    public NameInput(double scale) {
        initialize(scale);
    }

    private void initialize(double scale) {
        setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                scaled(FONT_SIZE, scale)
            )
        );

        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);

        setBorder(
            BorderFactory.createEmptyBorder(
                0,
                scaled(10, scale),
                0,
                scaled(10, scale)
            )
        );
    }

    private int scaled(int value, double scale) {
        return (int) Math.round(value * scale);
    }
}