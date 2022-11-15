package classesPackage;

public class Layer {
    public double[][] neurons;
    public double[][] inputs, outputs;
    public int inputSize, hiddenSize, outputSize;

    public  double[][] inputLayer(double [][] data){
        inputs = new double[5][data.length];
        for(int i = 0; i < inputs.length; i ++){
            System.arraycopy(data[i], 0, inputs[i], 0, data.length);
        }
        return inputs;
    }

    public  double[][] hiddenLayer(){
        neurons = new double[4][hiddenSize];
        for(int i = 0; i < neurons.length; i ++){
            for(int j = 0; j< neurons.length; j++) {
                neurons[i][j] = 1 - Math.random();
            }
        }
        return neurons;
    }

    public  double[][] outputLayer(){
        neurons = new double[1][hiddenSize];
        for(int i = 0; i < neurons.length; i ++){
            for(int j = 0; j< neurons.length; j++) {
                neurons[i][j] = 1 - Math.random();
            }
        }
        return neurons;
    }
}
