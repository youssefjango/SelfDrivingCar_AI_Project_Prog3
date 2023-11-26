public class HiddenLayer {

    private double[][] weights;
    private int currentLayerSize;
    private int previousLayerSize;


    public HiddenLayer(int currentLayerSize, int previousLayerSize) {
        this.currentLayerSize = currentLayerSize;
        this.previousLayerSize = previousLayerSize;
        weights = new double[currentLayerSize][previousLayerSize];
        initRandom();
    }

    @Override
    public HiddenLayer clone() {
        HiddenLayer hiddenLayer = new HiddenLayer(this.currentLayerSize,this.previousLayerSize);
        for (int i =0; i<this.currentLayerSize;i++) {
            for (int j =0; j<this.previousLayerSize;j++) {
                hiddenLayer.weights[i][j] = this.weights[i][j];
            }
        }

        return  hiddenLayer;
    }
    public void mutate(float learningRate) {
        for (int i = 0; i < currentLayerSize; i++) {
            for (int j = 0; j < previousLayerSize; j++) {
                if (Math.random() < learningRate) {
                    // Mutation: Add a small random value to the weight
                    weights[i][j] += (Math.random() * 2 - 1) * 0.1;

                    // Or Randomize the weight completely
                    // weights[i][j] = 2 * (Math.random() - 0.5)
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

    public double[] activate(double[] input) {
        double[] output = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            output[i] = ActivationFunctions.sigmoid(Car.multiplyVectors(input, weights[i]));
        }

        return output;
    }


    public String toString() {
        String title = String.format("HiddenLayer[%d][%d]:", weights.length, weights[0].length);
        return title;
    }

}


