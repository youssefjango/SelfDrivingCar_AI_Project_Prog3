
public class NeuralNetwork {

    private HiddenLayer[] hiddenLayers;
    private double[][] activations;
    private float learningRate; // Mutation Rate
    private int[] layers;

    public NeuralNetwork(float learningRate,int... layers) {

        this.learningRate = learningRate;
        this.layers = layers;

        activations = new double[layers.length][];
        hiddenLayers = new HiddenLayer[layers.length - 1];
        activations[0] = new double[layers[0]];

        // Hidden layers starts at the second neurons layer, with a previous layer as input layer?
        for (int i = 1; i < layers.length; i++) {
            activations[i] = new double[layers[i]];
            hiddenLayers[i - 1] = new HiddenLayer(layers[i], layers[i - 1]);
        }

    }

    @Override
    public NeuralNetwork clone() {

        NeuralNetwork neuralNetwork = new NeuralNetwork(this.learningRate, this.layers);
        neuralNetwork.hiddenLayers = this.hiddenLayers.clone();

        return neuralNetwork;
    }


    public double[] predict(double[] input) {
        activations[0] = input;

        for (int i = 0; i < hiddenLayers.length; i++) {
            activations[i + 1] = hiddenLayers[i].activate(activations[i]);
        }

        return activations[activations.length - 1];
    }

    public void mutate() {
        for (HiddenLayer layer: this.hiddenLayers) {
            layer.mutate(this.learningRate);
        }
    }

}