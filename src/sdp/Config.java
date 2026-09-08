package sdp;

import sdp.modules.assets.Asset;
import sdp.modules.assets.game.home.HomeScreenMusic;

import java.nio.charset.StandardCharsets;

public class Config {
    // Asset Config
    public static final String ASSETS_DIR = "/assets";

    // Game Config
    public static final String GAME_TITLE = "Serve Deine Prinzessin";
    public static final String GAME_ICON = ASSETS_DIR + "/icon.png";

    // Save Load Config
    public static final String FILE_EXTENSION = "sdp";
    public static final String FILE_EXTENSION_DESCRIPTION = "Save Deine Prinzessin Save File";
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";
    public static final int GCM_TAG_LENGTH = 128;
    public static final int IV_LENGTH = 12;
    public static final byte[] ENCRYPTION_KEY = "b1lm4rk0n70LdAmArBaJiN94nAq546Ut".getBytes(StandardCharsets.UTF_8);

    // Screen Config
    public static final String HOME_BACKGROUND = ASSETS_DIR + "/game/home/background/Home.png";
    public static final String PRINZESSIN_SELECT_BACKGROUND = ASSETS_DIR + "/game/prinzessinSelect/background/PrinzessinSelect.png";
    public static final Asset HOME_SOUNDTRACK = HomeScreenMusic.SECOND_HEARTBEAT;
}
