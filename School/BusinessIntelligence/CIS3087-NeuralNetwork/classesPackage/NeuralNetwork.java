package classesPackage;

import java.io.FileNotFoundException;

public class NeuralNetwork {
    public int learningRate, errorThreshold;
    public int goodFacts, badFacts = 0;
    Layer layer;

    public NeuralNetwork(int learningRate, int errorThreshold) {
        this.learningRate = learningRate;
        this.errorThreshold = errorThreshold;
    }

    public void feedForward() throws FileNotFoundException {
        layer = new Layer();
        dataManagement d = new dataManagement();
        //Creating the necessary arrays.
        double[][] data = d.getData();
        //Input layer.
        double [][] input = new double[data.length][5];
        double[][] target = new double[data.length][1];
        //populating the input and target arrays from CSV data.
        for(int i = 0; i < 572; i ++) {
            for(int j = 0; j < 5; j++) {
                input[i][j] = data[i][j];
            }
        }
        for(int i = 0; i < 572; i ++) {
            target[i][0] = data[i][5];
        }
        double[][] hiddenWeights = layer.hiddenLayer();
        double [][] outputWeights = layer.outputLayer();


    }

    public void backwardsPropagation() {

    }
}
