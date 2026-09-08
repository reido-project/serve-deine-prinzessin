package sdp.ui.screens;

import sdp.AppController;
import sdp.gameplay.GameController;
import sdp.ui.GameCanvas;
import sdp.ui.screens.game.GameScreen;
import sdp.ui.screens.home.HomeScreen;
import sdp.ui.screens.prinzessinSelect.PrinzessinSelect;

public class ScreenController {

    private final AppController appController;
    private final GameCanvas gameCanvas;


    private Screen currentScreen;

    public ScreenController(
        AppController appController,
        GameCanvas gameCanvas
    ) {
        this.appController = appController;
        this.gameCanvas = gameCanvas;
    }

    public void showHome() {
        showScreen(new HomeScreen(appController, gameCanvas, this));
    }

    public void showPrinzessinSelect() {
        showScreen(new PrinzessinSelect(appController, gameCanvas));
    }

    public void showGame() {
        showScreen(new GameScreen(appController, gameCanvas));
    }

    private void showScreen(Screen screen) {
        currentScreen = screen;

        gameCanvas.removeAll();

        gameCanvas.add(
            screen.getComponent()
        );

        gameCanvas.revalidate();
        gameCanvas.repaint();
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }
}