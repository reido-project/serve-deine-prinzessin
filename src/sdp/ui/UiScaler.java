package sdp.ui;

import javax.swing.JComponent;
import java.awt.*;

public final class UiScaler {
    private UiScaler() {}

    public static void setLogicalBounds(JComponent component, Rectangle bounds) {
        component.putClientProperty("logicalBounds", new Rectangle(bounds));
    }

    public static void setLogicalFont(JComponent component, Font font) {
        component.putClientProperty("logicalFont", font);
    }

    public static void captureLogicalTree(Component component) {
        if (component instanceof JComponent swingComponent) {
            if (swingComponent.getClientProperty("logicalBounds") == null) {
                setLogicalBounds(swingComponent, component.getBounds());
            }
            if (swingComponent.getClientProperty("logicalFont") == null) {
                setLogicalFont(swingComponent, component.getFont());
            }
        }
        if (component instanceof Container container) {
            for (Component child : container.getComponents()) {
                captureLogicalTree(child);
            }
        }
    }

    public static void scaleDialogTree(Component component, double scale) {
        if (component instanceof JComponent swingComponent) {
            Rectangle bounds = (Rectangle) swingComponent.getClientProperty("logicalDialogBounds");
            if (bounds == null) {
                bounds = component.getBounds();
                swingComponent.putClientProperty("logicalDialogBounds", new Rectangle(bounds));
            }
            component.setBounds(scale(bounds, scale));

            Dimension preferred = (Dimension) swingComponent.getClientProperty("logicalPreferredSize");
            if (preferred == null) {
                preferred = swingComponent.getPreferredSize();
                swingComponent.putClientProperty("logicalPreferredSize", new Dimension(preferred));
            }
            swingComponent.setPreferredSize(scale(preferred, scale));

            Font font = (Font) swingComponent.getClientProperty("logicalFont");
            if (font == null) {
                font = swingComponent.getFont();
                setLogicalFont(swingComponent, font);
            }
            if (font != null) {
                swingComponent.setFont(font.deriveFont((float) (font.getSize2D() * scale)));
            }
        }
        if (component instanceof Container container) {
            for (Component child : container.getComponents()) {
                scaleDialogTree(child, scale);
            }
        }
    }

    private static Dimension scale(Dimension size, double scale) {
        return new Dimension(
            (int) Math.round(size.width * scale),
            (int) Math.round(size.height * scale)
        );
    }

    public static Rectangle scale(Rectangle bounds, double scale) {
        return new Rectangle(
            (int) Math.round(bounds.x * scale),
            (int) Math.round(bounds.y * scale),
            (int) Math.round(bounds.width * scale),
            (int) Math.round(bounds.height * scale)
        );
    }
}