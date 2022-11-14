package classesPackage;

public class Layer {
    public Neuron[] neurons;
    public int inputSize, hiddenSize, outputSize;
    public Layer(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
    }

    public  Neuron[] inputLayer(){
        neurons = new Neuron[inputSize];
        for(int i = 0; i < neurons.length; i ++){
            neurons[i] = new Neuron(0);
        }
        return neurons;
    }

    public  Neuron[] hiddenLayer(){
        neurons = new Neuron[hiddenSize];
        for(int i = 0; i < neurons.length; i ++){
            neurons[i] = new Neuron(inputSize);
        }
        return neurons;
    }

    public  Neuron[] outputLayer(){
        neurons = new Neuron[outputSize];
        for(int i = 0; i < neurons.length; i ++){
            neurons[i] = new Neuron(hiddenSize);
        }
        return neurons;
    }
}
