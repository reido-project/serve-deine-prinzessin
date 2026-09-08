package sdp;

import sdp.characters.CharacterList;
import sdp.persistence.DataController;
import sdp.ui.GameFrame;
import sdp.ui.GameResolution;
import sdp.ui.screens.ScreenController;
import sdp.gameplay.GameController;
import sdp.ui.screens.ScreenList;

public class AppController {

    private GameFrame gameFrame;

    private ScreenController screenController;

    private final DataController dataController = DataController.getInstance();

    private final GameController gameController = new GameController(this);

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public ScreenController getScreenController() {
        return screenController;
    }

    public void setScreenController(ScreenController screenController) {
        this.screenController = screenController;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void changeResolution(GameResolution resolution) {
        if (gameFrame == null) {
            throw new IllegalStateException(
                "GameFrame has not been initialized."
            );
        }

        gameFrame.setResolution(resolution);
    }

    public void toggleFullscreen() {
        if (gameFrame == null) {
            throw new IllegalStateException(
                "GameFrame has not been initialized."
            );
        }

        gameFrame.toggleFullscreen();
    }

    public boolean isFullscreen() {
        if (gameFrame == null) {
            return false;
        }

        return gameFrame.isFullscreen();
    }

    // --------------------------------------------------
    // DATA
    // --------------------------------------------------

    public void setPlayerName(String playerName) {
        dataController.getRuntimeData().setPlayerName(playerName);
    }

    public void initializeNewGame(
        CharacterList prinzessinId
    ) {
        dataController.initializeNewGame(
            prinzessinId
        );
    }

    // --------------------------------------------------
    // SCREEN
    // --------------------------------------------------

    public void showGameScreen() {
        screenController.showGame();
    }

    public void showPrinzessinSelect() {
        screenController.showPrinzessinSelect();
    }

    public void startNewGame() {
        gameController.startNewGame();
    }

    public void initializeGame(CharacterList prinzessinId) {
        gameController.initializeGame(prinzessinId);
    }
}