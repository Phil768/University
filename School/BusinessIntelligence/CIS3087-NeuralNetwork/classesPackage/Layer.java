package classesPackage;

public class Layer {
    public double[][] neurons;
    public double[][] inputs, outputs;
    public int inputSize, hiddenSize, outputSize;



    public  double[][] hiddenLayer(){
        neurons = new double[5][4];
        for(int i = 0; i < 5; i ++){
            for(int j = 0; j< 4; j++) {
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
