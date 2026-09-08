package sdp.ui.components.game.interaction;

import sdp.gameplay.GameController;
import sdp.ui.GameCanvas;
import sdp.ui.components.game.dialog.TalkDialog;

import javax.swing.*;
import java.awt.*;

public class TalkBtn extends InteractionBtn {

    private final GameCanvas gameCanvas;
    private final Runnable onTalk;

    public TalkBtn(
        GameController gameController,
        GameCanvas gameCanvas,
        Runnable onTalk
    ) {
        super("Talk", gameController);

        if (gameCanvas == null) {
            throw new IllegalArgumentException(
                "GameCanvas cannot be null."
            );
        }

        if (onTalk == null) {
            throw new IllegalArgumentException(
                "onTalk cannot be null."
            );
        }

        this.gameCanvas = gameCanvas;
        this.onTalk = onTalk;

        addActionListener(e -> {

            if (!isInteractionAllowed()) {
                return;
            }

            Window owner =
                SwingUtilities.getWindowAncestor(
                    gameCanvas
                );

            TalkDialog dialog =
                new TalkDialog(
                    owner,
                    gameCanvas,
                    gameController,
                    onTalk
                );

            gameController.setGameState(
                sdp.gameplay.GameState.DIALOGUE
            );

            dialog.setVisible(true);
        });
    }
}