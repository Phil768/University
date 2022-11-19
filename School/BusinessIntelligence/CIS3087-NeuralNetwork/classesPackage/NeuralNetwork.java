package classesPackage;

import java.io.FileNotFoundException;

public class NeuralNetwork {
    public double learningRate, errorThreshold;
    public int epochs;

    public NeuralNetwork(int epochs, double learningRate, double errorThreshold) {
        this.epochs = epochs;
        this.learningRate = learningRate;
        this.errorThreshold = errorThreshold;
    }

    public void feedForward() throws FileNotFoundException {
        //Initializing the good and the bad facts.
        int goodFacts = 0, badFacts = 0;
        do {
            for(int x = 0; x <= epochs; x++) {
                //Setting the bad and good facts to after the start of each epoch.
                badFacts = 0;
                goodFacts = 0;
                //Creating a new data object to get the required data from file.
                dataManagement d = new dataManagement();
                //Creating the necessary arrays.
                double[][] data = d.getData();
                //Input layer.
                double[][] input = new double[data.length - 1][5];
                //Target of each fact.
                double[][] target = new double[data.length - 1][1];
                //populating the input and target arrays from CSV data.
                for (int i = 0; i < input.length; i++) {
                    for (int j = 0; j < input[0].length; j++) {
                        input[i][j] = data[i + 1][j];
                    }
                }
                for (int i = 0; i < target.length; i++) {
                    target[i][0] = data[i + 1][5];
                }
                //Creating a new layer object.
                Layer layer = new Layer();
                //Creating a new sigmoid object.
                sigmoidFunction sigmoid = new sigmoidFunction();
                //Creating new arrays to store the hidden and output weights.
                double[][] hiddenWeights = layer.hiddenLayer();
                double[][] outputWeights = layer.outputLayer();
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
                double[][] finalOutput = new double[outputResult.length][outputResult[0].length];
                //Passing the summation fo the weights from the output layer to the sigmoid function.
                for (int i = 0; i < outputResult.length; i++) {
                    for (int j = 0; j < outputResult[0].length; j++) {
                        finalOutput[i][j] = sigmoid.sigmoid(outputResult[i][j]);
                    }
                }
                //Checking the output with the error threshold.
                for (int i = 0; i < outputResult.length; i++) {
                    for (int j = 0; j < outputResult[0].length; j++) {
                        if (target[i][j] - outputResult[i][j] > 0.2) {
                            backwardsPropagation(outputWeights, target, finalOutput, outputHidden, input, hiddenWeights);
                            badFacts++;
                        } else {
                            //Do nothing.
                            goodFacts++;
                        }
                    }
                }
            }
        }while(badFacts == 0);

    }

    public static void backwardsPropagation(double[][] outputWeights, double[][] target, double[][] finalOutput, double[][] outputHidden, double[][] input, double[][] hiddenWeights) {
        //Computing the new outputLayer weights.
        double delta, change = 0;
        double[][] outputDelta = new double[finalOutput.length][finalOutput[0].length];
        double[][] hiddenDelta = new double[outputHidden.length][outputHidden[0].length];
        //Computing the delta for each output.
        for(int i = 0; i < outputWeights.length; i++) {
            for(int j = 0; j < outputWeights[0].length; j++) {
                delta = (finalOutput[i][0]) * (1 - finalOutput[i][0]) * (target[i][0] - finalOutput[i][0]);
                outputDelta[i][0] = delta;
                change = 0.2 * delta * outputHidden[i][j];
                //Determining the new output weights
                outputWeights[i][j] = outputWeights[i][j] + change;
            }
        }
        //Computing the summation of output layer weights.
        double summation = 0;
        for(int i = 0; i < outputWeights.length; i++) {
            for(int j = 0; j < outputWeights[0].length; j++) {
                summation += outputDelta[i][0] * outputWeights[i][j];
            }
        }
        //Computing the delta of the hidden layer;
        for(int i = 0; i < outputHidden.length; i++) {
            for(int j = 0; j < outputHidden[0].length; j++) {
                delta = outputHidden[i][j] * (1 - outputHidden[i][j]) * summation;
                hiddenDelta[i][j] = delta;
            }
        }
        //Computing the new weights of the hidden layer.
        for(int i = 0; i < ; i++)  {
            for(int j = 0; j < ; j++) {
                change = 0.2 * hiddenDelta[][] * input[][];
                hiddenWeights[][] = hiddenWeights[][] + change;
            }
        }

    }
}
