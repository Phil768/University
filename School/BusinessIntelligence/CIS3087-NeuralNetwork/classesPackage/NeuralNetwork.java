package classesPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    public static double learningRate, errorThreshold;
    public int epochs;
    public static double[][] hiddenWeights;
    public static double[][] outputWeights;

    public NeuralNetwork(int epochs, double learningRate, double errorThreshold) {
        this.epochs = epochs;
        this.learningRate = learningRate;
        this.errorThreshold = errorThreshold;
    }

    public void feedForward() throws IOException {
        //Initializing the good and the bad facts.
        int goodFacts = 0, badFacts = 0;
        //Creating a new data object to get the required data from file.
        dataManagement d = new dataManagement();
        //Creating the necessary arrays.
        double[][] data = d.getTrainingData();
        //Creating an empty list to add the errors dynamically and not have empty space.
        List<Double> errors = new ArrayList<Double>();
        //Shuffling the content of the array.
        Shuffle shuffle = new Shuffle();
        data = shuffle.shuffle(data);
        //Input layer.
        double[][] input = new double[1][data[0].length - 1];
        //Target of each fact.
        double target = 0;
        //Creating a new layer object.
        Layer layer = new Layer();
        //Creating a new sigmoid object.
        sigmoidFunction sigmoid = new sigmoidFunction();
        //Creating new arrays to store the hidden and output weights.
        hiddenWeights = layer.hiddenLayer();
        outputWeights = layer.outputLayer();
        for(int x = 0; x < epochs; x++) {
            //Setting the bad and good facts to after the start of each epoch.
            badFacts = 0;
            goodFacts = 0;
            //Iterating through all the facts in each epoch.
            for(int y = 0; y < data.length; y++) {
                //Populating the Input layer for each fact.
                for(int z = 0; z < data[0].length - 1 ; z++){
                    input[0][z] = data[y][z];
                }
                //Determining the target value of each fact.
                target = data[y][data[0].length - 1];
                //Creating a new matrix object to calculate the required arithmetic.
                matrixArithmetic multiplication = new matrixArithmetic();
                //Multiplying the input by the weights of the hidden layer.
                double[][] hiddenResult = multiplication.multiplication(input, hiddenWeights);
                //The output of the hidden layer will become the input of the output.
                double[][] outputHidden = new double[input.length][hiddenWeights[0].length];
                //Calculating the sigmoid of each weight summation.
                for (int i = 0; i < outputHidden.length; i++) {
                    for (int j = 0; j < outputHidden[0].length; j++) {
                        outputHidden[i][j] = sigmoid.sigmoid(hiddenResult[i][j]);
                    }
                }
                //Multiplying the output from the hidden layer with the weights of the output layer.
                double[][] outputResult = multiplication.multiplication(outputHidden, outputWeights);
                //Creating new array to store the final output.
                double finalOutput = 0;
                //Passing the summation fo the weights from the output layer to the sigmoid function.
                for (int i = 0; i < outputResult.length; i++) {
                    for (int j = 0; j < outputResult[0].length; j++) {
                        finalOutput = sigmoid.sigmoid(outputResult[i][j]);
                    }
                }
                //Checking the output with the error threshold.
                if (Math.abs(target - finalOutput) > errorThreshold) {
                    backwardsPropagation(target, finalOutput, outputHidden, input);
                    badFacts++;
                } else {
                    //Do nothing.
                    goodFacts++;
                }
            }
            //Calculating the percentage of bad facts in the current epoch.
            double n = ((double)badFacts/(double)(data.length)) * 100;
            //Adding the percentage to the list.
            errors.add(n);
            //Training stops when we have an epoch which has no bad facts and the weights are saved.
            if(badFacts == 0) {
                break;
            }
        }
        //Creating an array to hold all the error data.
        double[] error = new double[errors.size()];
        //Converting the list to an array.
        for(int i = 0; i < errors.size(); i++)
        {
            error[i] = errors.get(i);
        }
        //Writing the error array to a CSV file.
        d.writeTrainingData(error);
    }

    public static void backwardsPropagation(double target, double finalOutput, double[][] outputHidden, double[][] input) {
        //Creating the required variables.
        double change = 0;
        double outputDelta = 0;
        double[] hiddenDelta = new double[outputHidden[0].length];
        //Computing the delta for each output.
        outputDelta = (finalOutput) * (1 - finalOutput) * (target - finalOutput);
        //Changing the weights of the output layer.
        for(int i = 0; i < outputWeights.length; i++) {
            for(int j = 0; j < outputWeights[0].length; j++) {
                //Determining the change value of the output weights.
                change = learningRate * outputDelta * outputHidden[0][i];
                outputWeights[i][j]  += change;
            }
        }
        //Computing the delta of the hidden layer;
        for(int i = 0; i < outputHidden[0].length; i++) {                 //Summation since there is only one output neuron.
            hiddenDelta[i] = outputHidden[0][i] * (1 - outputHidden[0][i]) * (outputDelta * outputWeights[i][0]);
        }
        //Computing the new weights of the hidden layer.
        for(int i = 0; i < hiddenWeights.length; i++)  {
            for(int j = 0; j < hiddenWeights[0].length; j++) {
                change = learningRate * hiddenDelta[j] * input[0][i];
                hiddenWeights[i][j] += change;
            }
        }
    }

    public void test() throws IOException {
        //Creating a new data object to get the required data from file.
        dataManagement d = new dataManagement();
        //Creating the necessary arrays.
        double[][] data = d.getTestData();
        //Input layer.
        double[][] input = new double[1][data[0].length - 1];
        //Target of each fact.
        double target = 0;
        //Creating two lists in order to store the final output and the target dynamically.
        List<Double> Predicted = new ArrayList<Double>();
        List<Double> Actual = new ArrayList<Double>();
        //Creating a new sigmoid object.
        sigmoidFunction sigmoid = new sigmoidFunction();
        //Creating a count to hold the number of bad facts.
        int goodFacts = 0;
        //Iterating through all the facts in each epoch.
        for (int y = 0; y < data.length; y++) {
            //Populating the Input layer for each fact.
            for(int z = 0; z < data[0].length - 1 ; z++){
                input[0][z] = data[y][z];
            }
            //Determining the target value of each fact.
            target = data[y][data[0].length - 1];
            //Creating a new matrix object to calculate the required arithmetic.
            matrixArithmetic multiplication = new matrixArithmetic();
            //Multiplying the input by the weights of the hidden layer.
            double[][] hiddenResult = multiplication.multiplication(input, hiddenWeights);
            //The output of the hidden layer will become the input of the output.
            double[][] outputHidden = new double[input.length][hiddenWeights[0].length];
            //Calculating the sigmoid of each weight summation.
            for (int i = 0; i < outputHidden.length; i++) {
                for (int j = 0; j < outputHidden[0].length; j++) {
                    outputHidden[i][j] = sigmoid.sigmoid(hiddenResult[i][j]);
                }
            }
            //Multiplying the output from the hidden layer with the weights of the output layer.
            double[][] outputResult = multiplication.multiplication(outputHidden, outputWeights);
            //Creating new array to store the final output.
            double finalOutput = 0;
            //Passing the summation fo the weights from the output layer to the sigmoid function.
            for (int i = 0; i < outputResult.length; i++) {
                for (int j = 0; j < outputResult[0].length; j++) {
                    finalOutput = sigmoid.sigmoid(outputResult[i][j]);
                }
            }
            //Incrementing the number of good facts in order to calculate the overall accuracy.
            if((Math.abs(target - finalOutput) < errorThreshold)) {
                goodFacts++;
            }
            //Adding the data to the lists.
            Predicted.add(finalOutput);
            Actual.add(target);
        }
        //Dividing the number of good facts by the total number of facts and multiplying everything by 100 to get the accuracy percentage.
        double accuracy = (double)goodFacts/(double)data.length * 100;
        //Creating new arrays to store the predictions and actual output.
        double[] predicted = new double[Predicted.size()];
        double[] actual = new double[Actual.size()];
        //Converting the list to an array.
        for(int i = 0; i < Predicted.size(); i++)
        {
            predicted[i] = Predicted.get(i);
            actual[i] = Actual.get(i);
        }
        //Writing the data to a CSV file.
        d.writeTestData(predicted, actual, accuracy);
    }
}