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
        //Setup done in beforeEach
        //Exercise
        calculator.add("");
        //Verify
        Assertions.assertEquals(0, calculator.add(""));
    }
    @Test
    public void addTwoStrings() {

    }
}
