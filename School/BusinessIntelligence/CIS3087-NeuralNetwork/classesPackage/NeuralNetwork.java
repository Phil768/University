package classesPackage;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        double[][] data = d.getData();
        //Creating an array to hold all the error data.
        double[] error = new double[epochs];
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
            System.out.println(">>Epoch " + (x+1) + ": ");
            //Iterating through all the facts in each epoch.
            for (int y = 1; y < data.length; y++) {
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

               System.out.println("Hidden Weights: ");
                for(int i = 0; i < hiddenWeights.length; i++) {
                    for(int j = 0; j < hiddenWeights[0].length; j++) {
                        System.out.print(hiddenWeights[i][j] + " ");
                    }
                    System.out.println();
                }

                System.out.println("Output Weights: ");
                for(int i = 0; i < outputWeights.length; i++) {
                    for(int j = 0; j < outputWeights[0].length; j++) {
                        System.out.print(outputWeights[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println("ERROR: " + (target - finalOutput));
                System.out.println();
                //Testing purposes.
                /*System.out.println("Fact " + y + ": ");
                System.out.println("Output: " + finalOutput);
                System.out.println("Target: " + target);
                System.out.println();*/
                //Checking the output with the error threshold.
                if (Math.abs(target - finalOutput) > errorThreshold) {
                    backwardsPropagation(target, finalOutput, outputHidden, input);
                    badFacts++;
                } else {
                    //Do nothing.
                    goodFacts++;
                }
            }
            //Populating the error data array.
            double n = ((double)badFacts/(double)(data.length-1)) * 100;
            error[x] = n;
            //Training stops when we have an epoch which has no bad facts and the weights are saved.
            if(badFacts == 0) {
                break;
            }
        }
        //Writing the error array to a CSV file.
        d.writeData(error);
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
}