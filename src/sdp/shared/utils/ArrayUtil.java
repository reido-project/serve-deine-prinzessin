package sdp.shared.utils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public final class ArrayUtil {
    public static <T> T[] addEntry(T[] array, T newEntry) {
        if (array == null) { throw new IllegalArgumentException("Array can't be null."); }

        if (newEntry == null) { return array.clone(); }

        T[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[newArray.length - 1] = newEntry;
        return newArray;
    }

    public static <T> T[] addEntries(T[] array1, T[] array2) {
        if (array1 == null) { throw new IllegalArgumentException("Array1 can't be null."); }
        if (array2 == null) { return array1.clone(); }

        T[] newArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);
        return newArray;
    }

    public static <T> T[] randomizeArray(T[] items, int size) {
        if (items == null || items.length == 0 || size <= 0) {
            return null;
        }

        T[] clone = items.clone();
        int targetSize = Math.min(size, clone.length);

        for (int i = 0; i < targetSize; i++) {
            int randomIndex = i + ThreadLocalRandom.current().nextInt(clone.length - i);

            T temp = clone[i];
            clone[i] = clone[randomIndex];
            clone[randomIndex] = temp;
        }

        return Arrays.copyOf(clone, targetSize);
    }
}
