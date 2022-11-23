package test.cps3230.calculator.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalculatorPageObject {

    WebDriver driver;

    public CalculatorPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void pressButton(char buttonChar) {
        String name = null;

        switch (buttonChar) {
            case '1' : name = "one";
                break;
            case '2' : name = "two";
                break;
            case '3' : name = "three";
                break;
            case '4' : name = "four";
                break;
            case '5' : name = "five";
                break;
            case '6' : name = "six";
                break;
            case '7' : name = "seven";
                break;
            case '8' : name = "eight";
                break;
            case '9' : name = "nine";
                break;
            case '0' : name = "zero";
                break;
            case '+' : name = "plus";
                break;
            case '-' : name = "minus";
                break;
            case '*' : name = "times";
                break;
            case '/' : name = "div";
                break;
            case '=' : name = "DoIt";
                break;
        }

        if (name != null) {
            driver.findElement(By.name(name)).click();
        }
    }

    public void inputExpression(String expression) {
        for (int i=0; i<expression.length(); i++) {
            pressButton(expression.charAt(i));
        }
    }

    public String getResult() {
        return driver.findElement(By.name("Input")).getAttribute("value");
    }

}
