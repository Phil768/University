package test.cps3230.google;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class GoogleTests {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.get("https://www.google.com");
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }


    @Test
    public void testSimpleGoogleSearchFrance() throws Exception {

        //Exercise
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("France");
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchButton.submit();

        //Verify
        String title = driver.getTitle();
        Assertions.assertEquals("France - Google Search", title);
    }

    @Test
    public void testSimpleGoogleSearchItaly() throws Exception {

        //Exercise
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Italy");
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchButton.submit();

        //Verify
        String title = driver.getTitle();
        Assertions.assertEquals("Italy - Google Search", title);
    }

    @Test
    public void testSimpleGoogleSearchMalta() throws Exception {

        //Exercise
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Malta");
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchButton.submit();

        //Verify
        String title = driver.getTitle();
        Assertions.assertEquals("Malta - Google Search", title);

        //Check that result stats component exists
        List<WebElement> resultStats = driver.findElements(By.id("result-stats"));
        Assertions.assertEquals(1, resultStats.size());

        //Check that the map of malta exists
        List<WebElement> mapOfMalta = driver.findElements(By.id("lu_map"));
        Assertions.assertEquals(1, mapOfMalta.size());

        //Check Map of Malta Title
        String mapOfMaltaTitle = mapOfMalta.get(0).getAttribute("title");
        Assertions.assertEquals("Map of Malta", mapOfMaltaTitle);

        //Check map of Malta using Xpath
        String xpathToMapOfMalta = "//img[@id = 'lu_map' and @title='Map of Malta']";
        mapOfMalta = driver.findElements(By.xpath(xpathToMapOfMalta));
        Assertions.assertEquals(1, mapOfMalta.size());
    }


    @Test
    public void testGoogleCalculator() throws Exception {

        //Exercise
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("5+2");
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchButton.submit();

        //Verify
        List<WebElement> calculatorElements = driver.findElements(By.xpath("//h2[text() = 'Calculator result']"));
        Assertions.assertEquals(1, calculatorElements.size());

        calculatorElements = driver.findElements(By.xpath("//span[text() = '  5 + 2 =  ']"));
        Assertions.assertEquals(1, calculatorElements.size());

        WebElement resultElement = driver.findElement(By.id("cwos"));
        Assertions.assertEquals("7", resultElement.getText());
    }

}
