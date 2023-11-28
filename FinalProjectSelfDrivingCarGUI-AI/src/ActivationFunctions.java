
public class ActivationFunctions {
    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double relu(double x) {
        return Math.max(0, x);
    }


    public static double tanh(double x) {
        double e2x = Math.exp(2 * x);
        return (e2x - 1) / (e2x + 1);
    }


}
