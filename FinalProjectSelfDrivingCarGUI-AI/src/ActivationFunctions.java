
/**
 *
 * CLASS of method used to activate the artificial intelligence model
 */
public class ActivationFunctions {

    /**
     * This method is one of the activation methods
     *
     * @param x
     * 
     * @return it returns the result used to activate the model with the help of sigmoid activation method
     */
    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    /**
     * This method is one of the activation methods
     *
     * @param x
     * 
     * @return it returns the result used to activate the model with the help of the relu activation method
     */
    public static double relu(double x) {
        return Math.max(0, x);
    }

    /**
     * This method is one of the activation methods
     *
     * @param x
     * 
     * @return it returns the result used to activate the model with the help of tanh. THIS IS THE CHOSEN ACTIVATION METHOD FOR THE ENTIRE PROJECT.
     */
    public static double tanh(double x) {
        double e2x = Math.exp(2 * x);
        return (e2x - 1) / (e2x + 1);
    }

}
