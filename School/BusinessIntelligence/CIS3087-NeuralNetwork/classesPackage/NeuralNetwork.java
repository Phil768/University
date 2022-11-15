package classesPackage;

public class NeuralNetwork {
    public int learningRate, errorThreshold;
    public int goodFacts, badFacts = 0;
    Layer layer;

    public NeuralNetwork(int learningRate, int errorThreshold) {
        this.learningRate = learningRate;
        this.errorThreshold = errorThreshold;
    }

    public void feedForward() {
        layer = new Layer();

    }

    public void backwardsPropagation() {

    }
}
