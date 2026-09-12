import sdp.GameController;
import sdp.ui.GameFrame;
import sdp.ui.GameResolution;
import sdp.ui.GameViewport;
import sdp.ui.screens.ScreenController;

import javax.swing.*;

void main() {
    SwingUtilities.invokeLater(() -> {
        GameViewport viewport = new GameViewport();
        ScreenController screens = new ScreenController(GameController.getInstance(), viewport.getCanvas());
        screens.showHome();
        GameFrame frame = new GameFrame(viewport, GameResolution.FULL_HD);
        screens.setFrame(frame);
        frame.setVisible(true);
    });
}
