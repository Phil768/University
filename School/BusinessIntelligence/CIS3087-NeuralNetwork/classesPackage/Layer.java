package classesPackage;

public class Layer {
    public double[][] weights;
    public double[][] inputs, outputs;
    public int inputSize, hiddenSize, outputSize;



    public  double[][] hiddenLayer(){
        weights = new double[5][4];
        for(int i = 0; i < 5; i ++){
            for(int j = 0; j< 4; j++) {
                weights[i][j] = 1 - Math.random();
            }
        }
        return weights;
    }

    public  double[][] outputLayer(){
        weights = new double[1][1];
        for(int i = 0; i < weights.length; i ++){
            for(int j = 0; j< weights.length; j++) {
                weights[i][j] = 1 - Math.random();
            }
        }
        return weights;
    }
}
