package sdp;

import sdp.ui.GameFrame;
import sdp.ui.GameResolution;
import sdp.ui.GameViewport;
import sdp.ui.screens.ScreenController;

import javax.swing.*;

public class Main {

    static void main() {
        AppController appController = new AppController();

        SwingUtilities.invokeLater(() -> {
            GameViewport gameViewport = new GameViewport();

            ScreenController screenController = new ScreenController(appController, gameViewport.getGameCanvas());
            appController.setScreenController(screenController);
            screenController.showHome();

            GameFrame gameFrame = new GameFrame(gameViewport, GameResolution.R1920x1080);
            appController.setGameFrame(gameFrame);
            gameFrame.setVisible(true);
        });
    }
}