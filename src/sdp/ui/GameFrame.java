package sdp.ui;

import sdp.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Objects;

public class GameFrame extends JFrame {
	private final GameViewport viewport;
	private GameResolution resolution;
	private boolean fullscreen;

	public GameFrame(GameViewport viewport, GameResolution resolution) {
		super(Config.GAME_TITLE);
		setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(Config.GAME_ICON))).getImage());
		this.viewport = viewport;
		this.resolution = resolution;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setContentPane(viewport);
		setSize(calculateWindowSize(resolution));
		setLocationRelativeTo(null);
	}

	private Dimension calculateWindowSize(GameResolution resolution) {
		GraphicsConfiguration configuration = getGraphicsConfiguration();
		AffineTransform transform = configuration.getDefaultTransform();
		int width = (int) Math.round(resolution.getWidth() / transform.getScaleX());
		int height = (int) Math.round(resolution.getHeight() / transform.getScaleY());
		Insets insets = getInsets();

		return new Dimension(
			width + insets.left + insets.right,
			height + insets.top + insets.bottom
		);
	}

	public void setResolution(GameResolution resolution) {
		this.resolution = resolution;
		if (fullscreen) {
			disableFullscreen();
			return;
		}
		applyResolution();
	}

	private void applyResolution() {
		setSize(calculateWindowSize(resolution));
		setLocationRelativeTo(null);
		revalidate();
		repaint();
	}

	private void disableFullscreen() {
		GraphicsDevice device = getGraphicsConfiguration().getDevice();
		device.setFullScreenWindow(null);
		dispose();
		setUndecorated(false);
		fullscreen = false;
		applyResolution();
		setVisible(true);
	}

	private void enableFullscreen() {
		GraphicsDevice device = getGraphicsConfiguration().getDevice();
		dispose();
		setUndecorated(true);
		fullscreen = true;
		device.setFullScreenWindow(this);
	}

	public void toggleFullscreen() {
		if (fullscreen) {
			disableFullscreen();
		} else {
			enableFullscreen();
		}
		viewport.revalidate();
		viewport.repaint();
	}

	public GameViewport getViewport() { return viewport; }
	public GameResolution getResolution() { return resolution; }
	public boolean isFullscreen() { return fullscreen; }
}
