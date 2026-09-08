package sdp.ui.components.game.interaction;

import sdp.gameplay.GameController;
import sdp.gameplay.GameState;

import javax.swing.*;
import java.awt.*;

public abstract class InteractionBtn extends JButton {

    protected static final Color INTERACTION_COLOR =
        Color.decode("#00D0FF");

    protected static final Color SETTING_COLOR =
        Color.decode("#6DFF70");

    protected final GameController gameController;

    protected InteractionBtn(
        String text,
        GameController gameController
    ) {
        super(text);

        if (gameController == null) {
            throw new IllegalArgumentException(
                "GameController cannot be null."
            );
        }

        this.gameController = gameController;

        initialize();
    }

    protected boolean isInteractionAllowed() {
        return gameController.getGameState() == GameState.IDLE;
    }

    private void initialize() {
        setBackground(INTERACTION_COLOR);
        setForeground(Color.BLACK);

        setFont(
            new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                30
            )
        );

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(true);
        setOpaque(true);

        setHorizontalAlignment(
            SwingConstants.CENTER
        );
    }

    protected void setSettingStyle() {
        setBackground(SETTING_COLOR);
    }
}