package sdp.ui.components.prinzessinSelect;

import sdp.ui.util.GraphicsUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class CharacterSprite extends JPanel {

    private BufferedImage sprite;

    public CharacterSprite() {
        setOpaque(false);
    }

    public void loadSprite(String path) {
        try {
            sprite = ImageIO.read(
                Objects.requireNonNull(
                    getClass().getResource(path)
                )
            );
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalStateException(
                "Failed to load character sprite: " + path,
                e
            );
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (sprite == null) {
            return;
        }

        Graphics2D g2 = GraphicsUtil.createAntialiased(g);

        int containerWidth = getWidth();
        int containerHeight = getHeight();

        double scaleX = (double) containerWidth / sprite.getWidth();
        double scaleY = (double) containerHeight / sprite.getHeight();
        double scale = Math.min(scaleX, scaleY);

        int width = (int) Math.round(sprite.getWidth() * scale);
        int height = (int) Math.round(sprite.getHeight() * scale);

        int x = (containerWidth - width) / 2;
        int y = (containerHeight - height) / 2;

        g2.drawImage(
            sprite,
            x,
            y,
            width,
            height,
            null
        );

        g2.dispose();
    }
}