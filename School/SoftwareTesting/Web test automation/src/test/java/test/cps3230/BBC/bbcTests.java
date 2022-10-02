package test.cps3230.BBC;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import test.cps3230.calculator.pageobjects.CalculatorPageObject;

import java.util.List;

public class bbcTests
{
    WebDriver driver;
    CalculatorPageObject calc;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.bbc.co.uk/news");
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testBBC(){
        //Exercise
        WebElement selectedTab = driver.findElement(By.xpath("//span[text() = 'Coronavirus']"));
        selectedTab.click();
        List<WebElement> articles = driver.findElements(By.xpath("//h3[@class='gs-c-promo-heading__title gel-pica-bold nw-o-link-split__text']"));

        boolean size = false;
        if(articles.size() > 3)
            size = true;

        List<WebElement> numberOfImages = driver.findElements(By.tagName("img"));
        System.out.println(numberOfImages.size());
        //Verify
        Assertions.assertTrue(size);


    }
}
