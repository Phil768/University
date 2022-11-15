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
        double [][] input = new double[data.length][5];
        double[][] target = new double[data.length][1];
        //populating the input and target arrays from CSV data.
        for(int i = 0; i < 572; i ++) {
            System.arraycopy(input[i], 0, data[i], 0, 5);
        }
        for(int i = 0; i < 572; i ++) {
            System.arraycopy(target[i], 5, data[i], 5, 1);
        }



    }

    public void backwardsPropagation() {

    }
}
