package sdp.ui.util;

import java.awt.*;
import java.util.function.Consumer;

public final class GraphicsUtil {

    private GraphicsUtil() {
    }

    public static Graphics2D createAntialiased(Graphics graphics) {
        Graphics2D g2 =
            (Graphics2D) graphics.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        return g2;
    }

    public static void renderAntialiased(
        Graphics graphics,
        Consumer<Graphics2D> renderer
    ) {
        Graphics2D g2 =
            createAntialiased(graphics);

        try {
            renderer.accept(g2);
        } finally {
            g2.dispose();
        }
    }
}