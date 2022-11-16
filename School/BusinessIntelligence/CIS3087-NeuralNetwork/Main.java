import classesPackage.Layer;
import classesPackage.matrixArithmetic;
import classesPackage.sigmoidFunction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String []args) throws IOException {
        List<String[]> lines = new ArrayList<>();
        double[][] data = new double[571][6];
        try (BufferedReader br = new BufferedReader(new FileReader("trainingData.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.split(","));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // convert our list to a String array.
        String[][] array = new String[lines.size()][0];
        lines.toArray(array);

        for(int i = 1; i < data.length; i ++) {
            for( int j = 0; j < 6; j++) {
                data[i][j] = Double.parseDouble(array[i][j]);
            }
        }

        double [][] input = new double[data.length-1][5];
        double[][] target = new double[data.length-1][1];
        //populating the input and target arrays from CSV data.
        for(int i = 0; i < input.length; i ++) {
            for(int j = 0; j < 5; j++) {
                input[i][j] = data[i+1][j];
            }
        }
        for(int i = 1; i < target.length; i ++) {
            target[i][0] = data[i+1][5];
        }

        Layer layer = new Layer();
        double[][] weights = layer.hiddenLayer();

        for(int i = 0; i < weights.length; i ++) {
            for( int j = 0; j < weights[0].length; j++) {
                System.out.print(weights[i][j] + ", ");
            }
            System.out.println();
        }

        matrixArithmetic multiplication = new matrixArithmetic();
        double[][] result = multiplication.multiplication(input, weights);

       /* for(int i = 0; i < input.length; i ++) {
            for( int j = 0; j < weights[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }*/

        //For sigmoid - the first column from each row?
        //Does something need to be done to instantiate the input layer?

    }
}
