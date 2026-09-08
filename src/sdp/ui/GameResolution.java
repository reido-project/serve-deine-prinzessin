package sdp.ui;

public enum GameResolution {

    R1920x1080(1920, 1080),
    R1600x900(1600, 900),
    R1280x720(1280, 720);

    private final int width;
    private final int height;

    GameResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}