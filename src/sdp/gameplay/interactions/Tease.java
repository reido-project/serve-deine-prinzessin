package sdp.gameplay.interactions;

public class Tease {

    public static int getAffectionDelta(int affection) {

        if (affection < 20) {
            return -5;
        }

        if (affection < 40) {
            return -3;
        }

        if (affection < 61) {
            return -1;
        }

        if (affection < 81) {
            return 0;
        }

        return 1;
    }
}