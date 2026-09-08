package sdp.ui.screens;

import sdp.GameController;
import sdp.ui.GameCanvas;
import sdp.ui.GameFrame;
import sdp.ui.screens.home.HomeScreen;
import sdp.ui.screens.prinzessinSelect.PrinzessinSelectScreen;
import sdp.ui.UiScaler;

import java.awt.*;

public class ScreenController {
    private final GameController gameController;
    private final GameCanvas canvas;
    private Screen currentScreen;
    private GameFrame frame;

    public ScreenController(GameController gameController, GameCanvas canvas) {
        this.gameController = gameController;
        this.canvas = canvas;
    }

    public void setFrame(GameFrame frame) { this.frame = frame; }
    public GameFrame getFrame() { return frame; }

    public void showHome() { show(new HomeScreen(gameController, this)); }
    public void showPrinzessinSelect() { show(new PrinzessinSelectScreen(gameController, this)); }
    public void showGame() { show(new sdp.ui.screens.game.GameScreen(gameController, this)); }

    private void show(Screen screen) {
        currentScreen = screen;
        canvas.removeAll();
        canvas.add(screen.getComponent());
        UiScaler.captureLogicalTree(screen.getComponent());
        UiScaler.setLogicalBounds(
            (javax.swing.JComponent) screen.getComponent(),
            new Rectangle(0, 0, GameCanvas.LOGICAL_WIDTH, GameCanvas.LOGICAL_HEIGHT)
        );
        screen.getComponent().setBounds(0, 0, GameCanvas.LOGICAL_WIDTH, GameCanvas.LOGICAL_HEIGHT);
        canvas.revalidate();
        canvas.repaint();
    }

    public Screen getCurrentScreen() { return currentScreen; }
}