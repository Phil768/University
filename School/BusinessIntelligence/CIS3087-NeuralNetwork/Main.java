import classesPackage.sigmoidFunction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String []args) throws IOException {

        double[][] data = new double[572][5];
        String[] value = {};
        try (BufferedReader br = new BufferedReader(new FileReader("trainingData.csv"))) {
            String line = br.readLine();
            for(int i = 0; i < 570; i++){
                for(int j = 0; j < 5; j++) {
                    value[i] = Arrays.toString(line.split(","));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*for(int i = 0; i < 571; i++){
            for(int j = 0; j < 5; j++) {
                System.out.println(data[i][j]);
            }
        }*/
    }
}
