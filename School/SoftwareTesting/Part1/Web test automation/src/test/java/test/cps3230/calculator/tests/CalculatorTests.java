package test.cps3230.calculator.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.cps3230.calculator.pageobjects.CalculatorPageObject;

public class CalculatorTests {

    WebDriver driver;
    CalculatorPageObject calc;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/mark/webtesting/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://www.math.com/students/calculators/source/basic.htm");

        calc = new CalculatorPageObject(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void additionTest() {
        //Exercise
        calc.inputExpression("5+2=");

        //Verify
        Assertions.assertEquals("7", calc.getResult());
    }
}
