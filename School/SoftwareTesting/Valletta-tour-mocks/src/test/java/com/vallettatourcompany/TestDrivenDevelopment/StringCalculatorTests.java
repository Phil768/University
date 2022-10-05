package com.vallettatourcompany.TestDrivenDevelopment;

import com.vallettatourcompany.StringCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringCalculatorTests {

    StringCalculator calculator;

    @BeforeEach
    public void setup() { calculator = new StringCalculator(); }

    @Test
    public void testZeroNumbers() {
        //Verify
        Assertions.assertEquals(0, calculator.add(""));
    }

    @Test
    public void testOneNumber() {
        //Verify
        Assertions.assertEquals(1, calculator.add("1"));
    }
    @Test
    public void testAddTwoStrings() {
        //verify
        Assertions.assertEquals(3, calculator.add("1,2"));
    }

    @Test
    public void testAnyNumberOfStrings() {
        //verify
        Assertions.assertEquals(10, calculator.add("2,2,2,2,2"));
    }

    @Test
    public void testNumbersWithNewLine() {
        //Verify
        Assertions.assertEquals(3, calculator.add("1\n,2"));
    }

    @Test
    public void testWithoutNegativeNumbers() {
        //Verify
        Assertions.assertEquals(10, calculator.add("2,3,5"));
    }

    @Test
    public void testWithOneNegativeNumber() {
        //Verify
        Assertions.assertEquals(-1, calculator.add("-1,5,2,2"));
    }

    @Test
    public void testWithMultipleNegativeNumbers() {
        //Verify
        Assertions.assertEquals(-1, calculator.add("1,-1,-5,-2,-7,2"));
    }

    @Test
    public void testNumbersBiggerThanOneThousand() {
        //Verify
        Assertions.assertEquals(1, calculator.add("1001,1"));
    }

    @Test
    public void testNumbersBiggerThanTenThousand() {
        //Verify
        Assertions.assertEquals(1, calculator.add("10000,1"));
    }


}
