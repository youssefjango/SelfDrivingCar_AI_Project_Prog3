package ArtificialIntelligenceComponents;


public class ActivationFunctions {
    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
//    public static double sigmoidDerivative(double x) {
//        double sigmoid = sigmoid(x);
//        return sigmoid * (1 - sigmoid);
//    }

}
