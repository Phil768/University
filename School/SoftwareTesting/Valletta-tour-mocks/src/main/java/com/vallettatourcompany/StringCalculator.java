package com.vallettatourcompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StringCalculator {
    public int add(String numbers) {

        int answer = 0;
        List<Integer> negativeNumbers = new ArrayList<>();

        if(numbers.contains("\n")) {
            numbers = numbers.replaceAll("[\\n]", "");
        }

        String[] numberArray = numbers.split(",");

        if(numbers == "") {
            return 0;
        }
        else if (numbers.length() == 1) {
            return Integer.parseInt(numbers);
        }
        else {
            for(int i = 0; i < numberArray.length; i++) {
                String c = numberArray[i];
                //handling negative numbers.
                int intC = Integer.parseInt(numberArray[i]);
                if(Objects.equals(c, ",") || intC > 1000) { //GAy!!!
                    continue;
                }
                else if(intC < 0 ) {
                    negativeNumbers.add(intC);
                }
                else {
                    if(negativeNumbers.size() > 0)
                    {
                        System.out.println("Negative numbers not allowed: " +Arrays.toString(negativeNumbers.toArray()));
                        return -1;
                    }
                    else {
                        answer += intC;
                    }
                }
            }
            return answer;
        }
    }
}
