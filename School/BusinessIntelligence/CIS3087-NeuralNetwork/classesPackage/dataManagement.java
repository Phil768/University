package classesPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dataManagement {
    public double[][] getTrainingData() throws FileNotFoundException {
        List<String[]> lines = new ArrayList<>();
        double[][] data = new double[571][6];//32
        try (BufferedReader br = new BufferedReader(new FileReader("trainingData.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.split(","));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Removing the headers.
        lines.remove(0);
        // convert our list to a String array.
        String[][] array = new String[lines.size()][0];
        lines.toArray(array);

        for(int i = 0; i < data.length; i ++) {
            for( int j = 0; j < data[0].length; j++) {
                data[i][j] = Double.parseDouble(array[i][j]);
            }
        }
        return data;
    }

    public double[][] getTestData() throws FileNotFoundException{
        List<String[]> lines = new ArrayList<>();
        double[][] data = new double[143][6];//8
        try (BufferedReader br = new BufferedReader(new FileReader("testData.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.split(","));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Removing the headers.
        lines.remove(0);
        // convert our list to a String array.
        String[][] array = new String[lines.size()][0];
        lines.toArray(array);

        for(int i = 0; i < data.length; i ++) {
            for( int j = 0; j < data[0].length; j++) {
                data[i][j] = Double.parseDouble(array[i][j]);
            }
        }
        return data;
    }

    public void writeTrainingData(double[] data) throws IOException {
        FileWriter writer = new FileWriter("train.csv");
        writer.write("Epoch, Bad Facts (%)\n"); //Write headers

        for (int i =0; i < data.length; i++) {
            writer.write(String.valueOf(i + 1)+","+ data[i] +"\n");
        }
        writer.close();
    }

    public void writeTestData(double[] predicted, double[] actual, double accuracy) throws IOException {
        FileWriter writer = new FileWriter("test.csv");
        writer.write("Predicted, Actual\n"); //Write headers

        for (int i =0; i < predicted.length; i++) {
            writer.write(predicted[i]+","+ actual[i] +"\n");
        }
        writer.write("Accuracy,"+accuracy+"%");
        writer.close();
    }
}
