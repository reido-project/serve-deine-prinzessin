package sdp.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.WeakHashMap;

public final class UiScaler {

    private static final Map<Component, Rectangle> BASE_BOUNDS = new WeakHashMap<>();
    private static final Map<Component, Font> BASE_FONTS = new WeakHashMap<>();

    private UiScaler() {
    }

    public static void scaleChildren(Container parent, double scale) {
        for (Component component : parent.getComponents()) {
            Rectangle bounds = BASE_BOUNDS.computeIfAbsent(component, Component::getBounds);

            component.setBounds(
                scale(bounds.x, scale),
                scale(bounds.y, scale),
                scale(bounds.width, scale),
                scale(bounds.height, scale)
            );

            scaleFont(component, scale);

            if (component instanceof Container container) {
                scaleChildren(container, scale);
            }
        }
    }

    public static int scale(int value, double scale) {
        return (int) Math.round(value * scale);
    }

    public static Rectangle scale(Rectangle bounds, double scale) {
        return new Rectangle(
            scale(bounds.x, scale),
            scale(bounds.y, scale),
            scale(bounds.width, scale),
            scale(bounds.height, scale)
        );
    }

    private static void scaleFont(Component component, double scale) {
        Font baseFont = BASE_FONTS.computeIfAbsent(component, Component::getFont);

        if (baseFont == null) {
            return;
        }

        component.setFont(
            baseFont.deriveFont(
                (float) (baseFont.getSize2D() * scale)
            )
        );
    }
}