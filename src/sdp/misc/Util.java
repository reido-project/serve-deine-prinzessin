package sdp.misc;

import sdp.assets.sprite.SpriteState;
import sdp.dialogues.Dialogue;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    private static final Random rand = new Random();

    public static <T> T[] randomizeArray(T[] items, int size) {
        if (items == null || items.length == 0 || size <= 0) {
            return null;
        }

        T[] clone = items.clone();
        int targetSize = Math.min(size, clone.length);

        for (int i = 0; i < targetSize; i++) {
            // Safe untuk multi-threading
            int randomIndex = i + ThreadLocalRandom.current().nextInt(clone.length - i);

            T temp = clone[i];
            clone[i] = clone[randomIndex];
            clone[randomIndex] = temp;
        }

        return Arrays.copyOf(clone, targetSize);
    }

    public static <T> T[] addEntry(T[] array, T newEntry) {
        if (array == null) {
            throw new IllegalArgumentException("Array can't be null.");
        }

        T[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[newArray.length - 1] = newEntry;
        return newArray;
    }

    public String parseSprite(String prinzessin, SpriteState state){
        return String.format("/sdp/assets/sprite/%s/%s.png", prinzessin, state.toString());
    }

    public static  Dialogue[] combine(
        Dialogue[] first,
        Dialogue[] second
    ) {
        Dialogue[] result =
            new Dialogue[
                first.length + second.length
                ];

        System.arraycopy(
            first,
            0,
            result,
            0,
            first.length
        );

        System.arraycopy(
            second,
            0,
            result,
            first.length,
            second.length
        );

        return result;
    }
}
