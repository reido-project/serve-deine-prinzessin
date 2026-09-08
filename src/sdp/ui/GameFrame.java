package sdp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Objects;

public class GameFrame extends JFrame {

    private final GameViewport gameViewport;

    private GameResolution resolution;
    private boolean fullscreen;

    public GameFrame(
        GameViewport gameViewport,
        GameResolution resolution
    ) {
        super("Serve Deine Prinzessin");
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/sdp/assets/icon.png"))).getImage();
        this.setIconImage(icon);

        this.gameViewport = gameViewport;
        this.resolution = resolution;

        configureFrame(resolution);
    }

    private void configureFrame(GameResolution resolution) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        setContentPane(gameViewport);

        pack();

        setSize(calculateWindowSize(resolution));
        setLocationRelativeTo(null);
    }

    private Dimension calculateWindowSize(GameResolution resolution) {
        GraphicsConfiguration configuration = getGraphicsConfiguration();

        AffineTransform transform = configuration.getDefaultTransform();

        double scaleX = transform.getScaleX();
        double scaleY = transform.getScaleY();

        int width = (int) Math.round(resolution.getWidth() / scaleX);
        int height = (int) Math.round(resolution.getHeight() / scaleY);

        Insets insets = getInsets();

        width += insets.left + insets.right;
        height += insets.top + insets.bottom;

        return new Dimension(width, height);
    }

    public void setResolution(GameResolution resolution) {
        if (fullscreen) {
            return;
        }

        this.resolution = resolution;

        setSize(calculateWindowSize(resolution));
        setLocationRelativeTo(null);
        revalidate();
        repaint();
    }

    public void toggleFullscreen() {
        if (fullscreen) {
            exitFullscreen();
            return;
        }

        enterFullscreen();
    }

    private void enterFullscreen() {
        GraphicsDevice device = getGraphicsConfiguration().getDevice();

        dispose();

        setUndecorated(true);

        fullscreen = true;

        device.setFullScreenWindow(this);

        revalidate();
        repaint();
    }

    private void exitFullscreen() {
        GraphicsDevice device = getGraphicsConfiguration().getDevice();

        device.setFullScreenWindow(null);

        dispose();

        setUndecorated(false);

        fullscreen = false;

        setSize(calculateWindowSize(resolution));
        setLocationRelativeTo(null);

        setVisible(true);

        revalidate();
        repaint();
    }

    public GameViewport getGameViewport() {
        return gameViewport;
    }

    public GameResolution getResolution() {
        return resolution;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }
}