package org.example;

public class sigmoidFunction {
    public double sigmoid(double x) {
        return (1 / ( 1 + Math.pow(Math.E, (-1 * x))));
    }
}
