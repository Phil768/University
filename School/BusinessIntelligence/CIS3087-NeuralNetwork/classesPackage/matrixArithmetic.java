package classesPackage;

public class matrixArithmetic {

    public double[][] multiplication(double[][] input, double[][] weights) {
        //Creating a new array to store the result.
        double[][] result = new double[input.length][weights[0].length];
        //Multiplying the matrices.
        for(int i = 0; i < input.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                for(int k = 0; k < weights.length; k++) {
                    result[i][j] += input[i][k] * weights[k][j];
                }
            }
        }
        return result;
    }
}
