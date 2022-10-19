package Task1.Tests;

import Task1.PageObjects.MaltaParkPageObject;
import org.example.httpPostRequest;
import org.example.requestObject;
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
    int Car = 1;
    int Boat = 2;
    int PropertyForRent = 3;
    int PropertyForSale = 4;
    int Toys = 5;
    int Electronics = 6;

    @BeforeEach
    public void setup() {
        // #On Home PC use: /Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "/Users/phili/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.maltapark.com/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Initiating the Page object.
        object = new MaltaParkPageObject(driver);
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
        object.search("car");
        for(int i = 4; i <= 8; i++) {
            //Initiating driver.wait in order to wait for elements to load.
            WebDriverWait wait = new WebDriverWait(driver, 10);
            List<WebElement> productUrl = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.className("header"))));
            WebElement selectedProductDetails = wait.until(ExpectedConditions.elementToBeClickable(productUrl.get(i)));
            WebElement url = wait.until(ExpectedConditions.elementToBeClickable(selectedProductDetails));

            //Storing all the elements obtained in the correct variables to create the object.
            String URL = url.getAttribute("href");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            selectedProductDetails.click();
            String header = driver.findElement(By.xpath("//*[@id=\"page-content-left\"]/div[1]/h1[1]/span")).getText();
            //Cleaning the strings to be able tos end them through the API.
            int price = Integer.parseInt(driver.findElement(By.className("top-price")).getText().replace(",","").replace("€","").replace(" ", "").replace(".", "") + "00");
            String description = driver.findElement(By.className("readmore-wrapper")).getText().replace("\n", "").replace("\"", "");
            String src = driver.findElement(By.className("fancybox")).getAttribute("href");

            //Sending the request.
            requestObject request = new requestObject(Car, header, description, URL, src, "01150cc0-eff8-4df5-a549-eb18cf7c6184", price);
            httpPostRequest httpPostRequest = new httpPostRequest();
            httpPostRequest.sendPostRequest(request);
            productUrl.clear();
            driver.navigate().back();
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        }
    }
}
