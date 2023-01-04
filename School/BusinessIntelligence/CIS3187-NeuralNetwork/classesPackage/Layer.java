package classesPackage;

import java.util.Random;

public class Layer {
    public double[][] weights;
    public double[][] inputs, outputs;

    public  double[][] hiddenLayer(){
        weights = new double[5][4];
        for(int i = 0; i < weights.length; i ++){
            for(int j = 0; j< weights[0].length; j++) {
                //Generating random numbers between 1 and -1.
                weights[i][j] = Math.random() * (1 - -1) + -1;
            }
        }
        return weights;
    }

    public  double[][] outputLayer(){
        weights = new double[4][1];
        for(int i = 0; i < weights.length; i ++){
            for(int j = 0; j< weights[0].length; j++) {
                //Generating random numbers between 1 and -1.
                weights[i][j] = Math.random() * (1 - -1) + -1;
            }
        }
        return weights;
    }
}
