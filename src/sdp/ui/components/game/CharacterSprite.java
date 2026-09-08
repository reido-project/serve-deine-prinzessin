package sdp.ui.components.game;

import sdp.assets.sprite.SpriteState;
import sdp.characters.CharacterList;
import sdp.misc.Util;
import sdp.ui.util.GraphicsUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class CharacterSprite extends JPanel {

    private final Util util;

    private BufferedImage sprite;

    public CharacterSprite() {
        this.util = new Util();

        setOpaque(false);
    }

    public void loadSprite(
        CharacterList character,
        SpriteState state
    ) {
        if (character == null || state == null) {
            sprite = null;
            repaint();
            return;
        }

        String path = util.parseSprite(
            character.name().substring(0, 1)
                + character.name().substring(1).toLowerCase(),
            state
        );

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

        Graphics2D g2 =
            GraphicsUtil.createAntialiased(g);

        int imageWidth = sprite.getWidth();
        int imageHeight = sprite.getHeight();

        double scale =
            (double) getWidth() / imageWidth;

        if (imageHeight * scale > getHeight()) {
            scale =
                (double) getHeight() / imageHeight;
        }

        int width = (int) Math.round(
            imageWidth * scale
        );

        int height = (int) Math.round(
            imageHeight * scale
        );

        int x = (getWidth() - width) / 2;
        int y = getHeight() - height;

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