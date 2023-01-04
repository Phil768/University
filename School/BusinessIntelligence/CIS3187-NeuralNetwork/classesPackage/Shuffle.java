package classesPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffle {
    public double[][] shuffle(double[][] array)
    {
        //Creating a new list of lists to hold all the data.
        List<List<Double>> lists = new ArrayList<>();
        //Adding all the data from the array to the list.
        for(int i = 0; i < array.length; i ++){
            List<Double> l = new ArrayList<>();
            for(int j = 0; j < array[0].length; j++){
                l.add(array[i][j]);
            }
            lists.add(l);
        }
        //Shuffling the List collection.
        Collections.shuffle(lists);
        //Creating a new array to hold the shuffled data.
        double[][] data = new double[array.length][array[0].length];
        int i = 0, j = 0;
        //Writing the data from the list to the array.
        for(List<Double> l: lists) {
            for(double n : l) {
                data[i][j] = n;
                j++;
            }
            j=0;
            i++;
        }
        //Returning the shuffled data.
        return data;
    }
}
