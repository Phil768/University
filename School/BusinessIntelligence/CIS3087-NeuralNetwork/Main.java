import classesPackage.Layer;
import classesPackage.NeuralNetwork;
import classesPackage.matrixArithmetic;
import classesPackage.sigmoidFunction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String []args) throws IOException {

        /*//NeuralNetwork network = new NeuralNetwork(1000, 0.2, 0.2);
        int[][] array1 = {{1, 2, 3, 4}};
        int[][] array2 = {{1 ,2, 3}};

        for(int i = 0; i < array1.length; i++){
            for(int j =0; j < array1[0].length; j++) {
                System.out.println(array1[i][j] * array2[i][j]);
            }
        }*/

        //Creating a new neural network.
        NeuralNetwork network = new NeuralNetwork(10, 0.2, 0.2);
        //Passing the network through the feed forward algorithm.
        network.feedForward();
    }
}
