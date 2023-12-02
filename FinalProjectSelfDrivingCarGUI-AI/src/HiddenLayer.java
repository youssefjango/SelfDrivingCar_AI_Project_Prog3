
import java.util.Arrays;
import java.util.Random;

/**
 *
 * Class which consists of the hidden layer used in the machine learning model.
 */
public class HiddenLayer {

    private double[][] weights;
    private int currentLayerSize;
    private int previousLayerSize;

    private Random random = new Random();

    /**
     *
     * @param currentLayerSize
     * @param previousLayerSize
     */
    public HiddenLayer(int currentLayerSize, int previousLayerSize) {
        this.currentLayerSize = currentLayerSize;
        this.previousLayerSize = previousLayerSize;
        weights = new double[currentLayerSize][previousLayerSize];
        initRandom();
    }

    /**
     *
     * @return
     */
    @Override
    public HiddenLayer clone() {
        HiddenLayer hiddenLayer = new HiddenLayer(this.currentLayerSize, this.previousLayerSize);
        for (int i = 0; i < this.currentLayerSize; i++) {
            for (int j = 0; j < this.previousLayerSize; j++) {
                hiddenLayer.weights[i][j] = this.weights[i][j];
            }
        }

        return hiddenLayer;
    }

    /**
     *
     * @param learningRate
     */
    public void mutate(float learningRate) {
        for (int i = 0; i < currentLayerSize; i++) {
            for (int j = 0; j < previousLayerSize; j++) {
                if (random.nextDouble() < learningRate) {
                    // Or Randomize the weight completely
                    weights[i][j] = random.nextDouble(-1, 1);
                }
            }
        }
    }

    private void initRandom() {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[i][j] = 2 * Math.random() - 1; // [-1 .. 1)
            }
        }
    }

    /**
     *
     * @param input
     * @return
     */
    public double[] activate(double[] input) {
        double[] output = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            output[i] = ActivationFunctions.tanh(Car.multiplyVectors(input, weights[i]));
        }

        return output;
    }

    /**
     *
     * @return
     */
    public String toString() {
        String ret = "[";

        for (double currentLayerIndex[] : this.weights) {
            for (double value : currentLayerIndex) {
                ret += value + ", ";
            }
            ret += "]\n";
        }

        return ret;
    }

    /**
     *
     * @return
     */
    public double[][] getWeights() {
        return weights;
    }

}
