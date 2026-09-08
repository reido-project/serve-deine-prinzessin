package sdp;

import java.nio.charset.StandardCharsets;

public class Config {
    public static final String ASSETS_DIR = "/assets";

    public static final String FILE_EXTENSION = "sdp";
    public static final String FILE_EXTENSION_DESCRIPTION = "Save Deine Prinzessin Save File";
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";
    public static final int GCM_TAG_LENGTH = 128;
    public static final int IV_LENGTH = 12;

    public static final byte[] ENCRYPTION_KEY = "b1rUm4rUk0n70LdAmArBaJiN94nAq5h46Ut".getBytes(StandardCharsets.UTF_8);
}
