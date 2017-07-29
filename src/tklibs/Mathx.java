package tklibs;

/**
 * Created by huynq on 5/20/17.
 */
public class Mathx {
    public static double lerp(double a, double b, double f) {
        return a + f * (b - a);
    }

//    /**
//     *
//     * @param origin
//     * @param destination
//     * @param f = currentime / time [0 1]
//     * @return
//     */
//    public static bases lerp(bases origin, bases destination, double f) {
//        return origin.multiply(1  - f).add(destination.multiply(f));
//    }

    public static float clamp(float value, float min, float max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static boolean inRange(float value,float min,float max){
        return (value >= min && value <= max);
    }
}
