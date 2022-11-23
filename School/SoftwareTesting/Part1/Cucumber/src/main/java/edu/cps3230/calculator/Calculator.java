package edu.cps3230.calculator;

public class Calculator {

    public int additionResult;
    public int subtractionResult;
    public int divisionResult;
    public int multiplicationResult;

    public void add(int x, int y) {
        additionResult = x + y;
    }
    public void subtract(int x, int y) {
        subtractionResult = x - y;
    }
    public void division(int x, int y) {
        divisionResult = x / y;
    }
    public void multiplication(int x, int y) {
        multiplicationResult = x * y;
    }
}
