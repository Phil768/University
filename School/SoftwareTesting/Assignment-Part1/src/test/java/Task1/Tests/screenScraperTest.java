package Task1.Tests;

import Task1.PageObjects.MaltaParkPageObject;
import org.example.httpPostRequest;
import org.example.requestObject;
import org.example.screenScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class screenScraperTest
{
    WebDriver driver;
    MaltaParkPageObject object;
    screenScraper  scraper;
    int Car = 1;
    int Boat = 2;
    int PropertyForRent = 3;
    int PropertyForSale = 4;
    int Toys = 5;
    int Electronics = 6;

    @BeforeEach
    public void setup() {
        // #On Home PC use: /Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get("https://www.maltapark.com/");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        /*driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);*/
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookiebar")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("closebutton"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Initiating the Page object.
        object = new MaltaParkPageObject(driver);
        scraper = new screenScraper(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testSearchWithCorrectString() {
        //Verification
        Assertions.assertTrue(object.search("car"));
    }
    @Test
    public void testSearchWithEmptyString() {
        //Verification
        Assertions.assertFalse(object.search(""));
    }

    @Test
    public void testForCars() throws IOException, InterruptedException {
        scraper.ScreenScraper("Toyota", Car);
    }
}
