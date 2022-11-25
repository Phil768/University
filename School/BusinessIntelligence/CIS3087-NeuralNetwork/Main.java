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

        /*//NeuralNetwork network = new NeuralNetwork(1000, 0.2, 0.2);
        int[][] array1 = {{1, 2, 3, 4}};
        int[][] array2 = {{1 ,2, 3}};

        for(int i = 0; i < array1.length; i++){
            for(int j =0; j < array1[0].length; j++) {
                System.out.println(array1[i][j] * array2[i][j]);
            }
        }*/

        //Creating a new neural network.
        //NeuralNetwork network = new NeuralNetwork(10, 0.2, 0.2);
        //Passing the network through the feed forward algorithm.
        //network.feedForward();

        double[][] test = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        List<List<Double>> lists = new ArrayList<>();


        for(int i = 0; i < test.length; i ++){
            List<Double> l = new ArrayList<>();
            for(int j = 0; j < test[0].length; j++){
                l.add(test[i][j]);
            }
            lists.add(l);
        }

        Collections.shuffle(lists);

        double[][] test2 = new double[4][4];
        int i = 0, j = 0;


        for(List<Double> l: lists) {
            for(double n : l) {
                test2[i][j] = n;
                j++;
            }
            j=0;
            i++;
        }
        for(int x = 0; x < test2.length; x ++){
            for(int y = 0; y < test2[0].length; y++){
                System.out.print(test2[x][y] + " ");
            }
            System.out.println();
        }
    }
}
