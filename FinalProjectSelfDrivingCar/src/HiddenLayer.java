


public class HiddenLayer {

    private double[][] weights;

    private double[] biases;


    public HiddenLayer(int currentLayerSize, int previousLayerSize) {
        weights = new double[currentLayerSize][previousLayerSize];
        biases = new double[currentLayerSize];
        initRandom();
    }

    public HiddenLayer(HiddenLayer other) {
        double[][] otherWeights = other.weights;
        double[] otherBiases = other.biases;
        weights = new double[otherWeights.length][otherWeights[0].length];
        biases = new double[otherBiases.length];

        for (int i = 0; i < weights.length; i++) {
            biases[i] = otherBiases[i];
            System.arraycopy(otherWeights[i], 0, weights[i], 0, weights[0].length);
        }
    }


    private void initRandom() {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[i][j] = 2 * Math.random() - 1; // [-1 .. 1)
            }
        }

        for (int i = 0; i < biases.length; i++) {
            biases[i] = 2 * Math.random() - 1; // [-1 .. 1)
        }
    }

    public String toString() {
        String title = String.format("HiddenLayer[%d][%d]:", weights.length, weights[0].length);
        return title;
    }

    public double[][] getWeights() {
        return weights;
    }

    public double[] getBiases() {
        return biases;
    }
}

