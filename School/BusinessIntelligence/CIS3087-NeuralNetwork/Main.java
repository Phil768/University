import classesPackage.Layer;
import classesPackage.matrixArithmetic;
import classesPackage.sigmoidFunction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String []args) throws IOException {
        double[][] matrix = new double[1][1];
        matrix[0][0] = 5.4;
        System.out.println(matrix[0][0]);
        matrix[0][0] = 7.2;
        System.out.println(matrix[0][0]);

        double i = 1;
        int j = 2;

        System.out.println(i + j);
    }
}
