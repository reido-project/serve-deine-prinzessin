package sdp.ui.components.game.interaction;

import sdp.gameplay.GameController;
import sdp.ui.screens.game.GameScreen;

public class TeaseBtn extends InteractionBtn {

    private final GameScreen gameScreen;

    public TeaseBtn(
        GameController gameController,
        GameScreen gameScreen
    ) {
        super("Tease", gameController);

        this.gameScreen = gameScreen;

        addActionListener(e -> {

            if (!isInteractionAllowed()) {
                return;
            }

            gameScreen.startTease();
        });
    }
}