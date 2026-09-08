package sdp.ui.dialog;

import sdp.ui.GameCanvas;

import javax.swing.*;
import java.awt.*;

public abstract class GameDialog extends JDialog {

    private final GameCanvas gameCanvas;

    protected GameDialog(
        Window owner,
        GameCanvas gameCanvas
    ) {
        super(owner);

        this.gameCanvas = gameCanvas;

        configureDialog();
    }

    private void configureDialog() {
        setModal(true);
        setResizable(false);
    }

    protected final double getScale() {
        return gameCanvas.getScale();
    }

    protected final int scale(int value) {
        return (int) Math.round(value * getScale());
    }

    protected final void applyLogicalSize() {
        int width = scale(getLogicalWidth());
        int height = scale(getLogicalHeight());

        getContentPane().setPreferredSize(
            new Dimension(width, height)
        );

        pack();

        centerOnGameCanvas();
    }

    private void centerOnGameCanvas() {
        if (!gameCanvas.isShowing()) {
            setLocationRelativeTo(getOwner());
            return;
        }

        Point canvasLocation =
            gameCanvas.getLocationOnScreen();

        int canvasWidth =
            gameCanvas.getWidth();

        int canvasHeight =
            gameCanvas.getHeight();

        int x =
            canvasLocation.x
                + (canvasWidth - getWidth()) / 2;

        int y =
            canvasLocation.y
                + (canvasHeight - getHeight()) / 2;

        setLocation(x, y);
    }

    protected abstract int getLogicalWidth();

    protected abstract int getLogicalHeight();
}