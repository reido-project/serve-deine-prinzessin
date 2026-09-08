package sdp.shared.utils;

import java.util.concurrent.ThreadLocalRandom;

public class NumberUtil {
    public static long longRNG() {
        return ThreadLocalRandom.current().nextLong(1L, Long.MAX_VALUE);
    }

    public static long longRNG(double modifier) {
        return Math.round(ThreadLocalRandom.current().nextLong(1L, Long.MAX_VALUE) * modifier);
    }

    public static int intRNG() {
        return ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
    }
}