import classesPackage.Layer;
import classesPackage.NeuralNetwork;
import classesPackage.matrixArithmetic;
import classesPackage.sigmoidFunction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String []args) throws IOException {
        //Creating a new neural network.
        NeuralNetwork network = new NeuralNetwork(1000, 0.2, 0.2);
        //Passing the network through the feed forward algorithm in order to train.
        network.feedForward();
        //Passing the test data through the acquired trained weights.
        network.test();
    }
}
