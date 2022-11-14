import classesPackage.sigmoidFunction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String []args) throws IOException {
        /*int data[] = {100,90,87,76,64,5,8,43,39,2,5,1,2};

        FileWriter writer = new FileWriter("data.csv");
        writer.write("Epoch, Error\n"); //Write headers

        for (int i =0; i < data.length; i++) {
            writer.write(String.valueOf(i + 1)+","+String.valueOf(data[i])+"\n");
        }*/

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("trainingData.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        //records.forEach(System.out::println);
        System.out.println(records.get(0));
    }
}
