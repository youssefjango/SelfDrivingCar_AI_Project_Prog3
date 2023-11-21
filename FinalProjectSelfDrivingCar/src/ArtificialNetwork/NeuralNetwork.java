package ArtificialNetwork;



public class NeuralNetwork {

    private int generation;
    private HiddenLayer[] hiddenLayers;
    private double[][] activations;
    private int mutationCount;

    // layers[0] = input
    // layers[].length-1 = output
    // the rest is all hidden layers


    public NeuralNetwork(int... layers) {

        activations = new double[layers.length][];
        hiddenLayers = new HiddenLayer[layers.length - 1];

        activations[0] = new double[layers[0]];

        for (int i = 1; i < layers.length; i++) {
            activations[i] = new double[layers[i]];
            hiddenLayers[i - 1] = new HiddenLayer(layers[i], layers[i - 1]);
        }


    }

    public NeuralNetwork(NeuralNetwork other) {

        double[][] otherActivations = other.activations;
        activations = new double[otherActivations.length][];
        activations[0] = new double[otherActivations[0].length];

        HiddenLayer[] otherHiddenLayers = other.hiddenLayers;
        hiddenLayers = new HiddenLayer[activations.length - 1];

        for (int i = 1; i < activations.length; i++) {
            activations[i] = new double[otherActivations[i].length];
            hiddenLayers[i - 1] = new HiddenLayer(otherHiddenLayers[i - 1]);
        }
    }




}
