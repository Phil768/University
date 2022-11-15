package classesPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dataManagement {
    public double[][] getData() throws FileNotFoundException {
        List<String[]> lines = new ArrayList<>();
        double[][] data = new double[572][6];
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

        for(int i = 1; i < 572; i ++) {
            for( int j = 0; j < 6; j++) {
                data[i][j] = Double.parseDouble(array[i][j]);
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        return data;

    }

    public void writeData(double[][] data) throws IOException {
        FileWriter writer = new FileWriter("data.csv");
        writer.write("Epoch, Error\n"); //Write headers

        for (int i =0; i < data.length; i++) {
            writer.write(String.valueOf(i + 1)+","+String.valueOf(data[i])+"\n");
        }
    }
}
