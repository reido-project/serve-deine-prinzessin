package sdp.ui.components.home.subcomponents;

import sdp.AppController;
import sdp.ui.screens.ScreenController;

import javax.swing.*;
import java.awt.*;

public class OkayBtn extends JButton {

    private static final int FONT_SIZE = 24;

    private final AppController appController;
    private final ScreenController screenController;
    private final NameInput nameInput;

    public OkayBtn(
        double scale,
        AppController appController,
        ScreenController screenController,
        NameInput nameInput
    ) {
        super("Okay");

        this.appController = appController;
        this.screenController = screenController;
        this.nameInput = nameInput;

        initialize(scale);

        addActionListener(e -> confirmName());
    }

    private void confirmName() {
        String playerName = nameInput.getText().trim();

        if (playerName.isEmpty()) {
            return;
        }

        appController.setPlayerName(playerName);

        Window window =
            SwingUtilities.getWindowAncestor(this);

        if (window != null) {
            window.dispose();
        }

        screenController.showPrinzessinSelect();
    }

    private void initialize(double scale) {
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, scaled(FONT_SIZE, scale)));

        setForeground(Color.BLACK);

        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setMargin(new Insets(0, 0, 0, 0));
    }

    private int scaled(int value, double scale) {
        return (int) Math.round(value * scale);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(Color.LIGHT_GRAY);

        g2.fillRoundRect(
            0,
            0,
            getWidth(),
            getHeight(),
            10,
            10
        );

        super.paintComponent(g2);

        g2.dispose();
    }
}