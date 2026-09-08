package sdp.ui;

import java.awt.*;

public enum GameResolution {
    HD(1280, 720), FULL_HD(1920, 1080), QHD(2560, 1440);

    private final int width;
    private final int height;

    GameResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Dimension getSize() { return new Dimension(width, height); }
}