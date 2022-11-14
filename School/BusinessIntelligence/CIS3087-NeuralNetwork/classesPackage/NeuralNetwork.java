package classesPackage;

public class NeuralNetwork {
    public int SIZE_OF_INPUT_LAYER = 5;
    public int SIZE_OF_HIDDEN_LAYER = 4;
    public int SIZE_OF_OUTPUT_Layer = 1;
    Layer layer;

    public NeuralNetwork() {
        this.layer = new Layer(SIZE_OF_INPUT_LAYER, SIZE_OF_HIDDEN_LAYER, SIZE_OF_OUTPUT_Layer);
    }

    public void feedForward() {
        /*
        * Layer.inputlayer() -> Creating the input Layer*/
    }

    public void backwardsPropagation() {

    }
}
