import classesPackage.sigmoidFunction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String []args) throws IOException {
        int data[] = {100,90,87,76,64,5,8,43,39,2,5,1,2};

        FileWriter writer = new FileWriter("data.csv");
        writer.write("Epoch, Error\n"); //Write headers

        for (int i =0; i < data.length; i++) {
            writer.write(String.valueOf(i + 1)+","+String.valueOf(data[i])+"\n");
        }

        // closing writer connection
        writer.close();
    }
}
