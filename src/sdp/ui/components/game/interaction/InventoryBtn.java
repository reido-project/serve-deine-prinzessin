package sdp.ui.components.game.interaction;

import sdp.gameplay.GameController;
import sdp.gameplay.GameState;
import sdp.ui.GameCanvas;
import sdp.ui.components.game.dialog.InventoryDialog;
import sdp.ui.screens.game.GameScreen;

import javax.swing.*;
import java.awt.*;

public class InventoryBtn extends InteractionBtn {

    private final GameCanvas gameCanvas;
    private final GameScreen gameScreen;

    public InventoryBtn(
        GameCanvas gameCanvas,
        GameController gameController,
        GameScreen gameScreen
    ) {
        super("Inventory", gameController);

        this.gameCanvas = gameCanvas;
        this.gameScreen = gameScreen;

        addActionListener(e -> {

            if (!isInteractionAllowed()) {
                return;
            }

            Window owner =
                SwingUtilities.getWindowAncestor(
                    gameCanvas
                );

            InventoryDialog dialog =
                new InventoryDialog(
                    owner,
                    gameCanvas,
                    gameController,
                    gameScreen::startItemDialogue
                );

            gameController.setGameState(
                GameState.INVENTORY
            );

            dialog.setVisible(true);
        });
    }
}