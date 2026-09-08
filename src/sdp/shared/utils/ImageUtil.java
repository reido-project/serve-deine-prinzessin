package sdp.shared.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageUtil {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageUtil.class.getResource(path)));
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalStateException("Failed to load image: " + path, e);
        }
    }
}
