package sdp.ui.components.button;

import java.awt.*;

import static java.awt.Color.*;

public enum ButtonOption {
    // Relative to 1920x1080

    HomeOption("Home Option", true, "#45BEFF", BLACK, 10, 50, 405, 100),
    InteractionOption("Interaction Option", true, "#45BEFF", BLACK, 10, 30, 210, 45),
    ResolutionOption("Resolution Option", false, "#737373", WHITE, 10, 24, 176, 42),
    GameOption("Game Option", true, "#45BEFF", BLACK, 10, 24, 176, 42),
    SubmitBtn("Submit", true, "#45BEFF", BLACK, 10, 20, 110, 45),

    SettingBtn("Setting", true, "#6DFF70", BLACK, 10, 30, 210, 45);

    private final String text;
    private final Font font;
    private final Color background;
    private final Color foreground;
    private final int cornerRadius;
    private final int fontSize;
    private final int width;
    private final int height;

    ButtonOption(String text, boolean bold, Color background, Color foreground, int cornerRadius, int fontSize, int width, int height) {
        this.text = text;
        this.fontSize = fontSize;
        this.width = width;
        this.height = height;

        int style = bold ? Font.BOLD : Font.PLAIN;
        this.font = new Font(Font.SANS_SERIF, style, fontSize);

        this.background = background;
        this.foreground = foreground;
        this.cornerRadius = cornerRadius;
    }

    ButtonOption(String text, boolean bold, String background, Color foreground, int cornerRadius, int fontSize, int width, int height) {
        this(text, bold, Color.decode(background), foreground, cornerRadius, fontSize, width, height);
    }

    public String getText() {
        return text;
    }

    public Font getFont() {
        return font;
    }

    public Color getBackground() {
        return background;
    }

    public Color getForeground() {
        return foreground;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getSize() {
        return new Dimension(width, height);
    }
}