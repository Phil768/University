package Task1;

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.maltapark.com/");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void Test1() throws IOException, InterruptedException {
        WebElement searchBar = driver.findElement(By.id("search"));
        searchBar.sendKeys("Toyota");
        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-search']"));
        searchBtn.submit();
        //save all laptops in a list.
        //List<WebElement> productImage = driver.findElements(By.xpath("//img[@class='product-image-photo main-img']"));
        //List<WebElement> productPrice = driver.findElements(By.className("price"));
        for(int i = 0; i <= 4; i++) {
            List<WebElement> productUrl = driver.findElements(By.className("header"));
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement selectedProductDetails = wait.until(ExpectedConditions.elementToBeClickable(productUrl.get(i)));
            WebElement url = wait.until(ExpectedConditions.elementToBeClickable(selectedProductDetails));
            String URL = url.getAttribute("href");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            selectedProductDetails.click();
            String header = driver.findElement(By.xpath("//*[@id=\"page-content-left\"]/div[1]/h1[1]/span")).getText();
            int price = Integer.parseInt(driver.findElement(By.className("top-price")).getText().replace(",","").replace("€","").replace(" ", "").replace(".", ""));
            String description = driver.findElement(By.className("readmore-wrapper")).getText().replace("\n", "").replace("\"", "");
            String src = driver.findElement(By.className("fancybox")).getAttribute("href");

            requestObject request = new requestObject(6, header, description, URL, src, "01150cc0-eff8-4df5-a549-eb18cf7c6184", price);
            httpPostRequest httpPostRequest = new httpPostRequest();
            httpPostRequest.sendPostRequest(request);
            productUrl.clear();
            driver.navigate().back();
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        }
        /*
        * //WebElement selectedProductImage = productImage.get(i);
            //WebElement selectedProductPrice = productPrice.get(i);
            //String price = selectedProductPrice.getText();

            //String newPrice = price.replace(",","").replace("€","").replace(".", "");

            int priceInteger = Integer.parseInt(newPrice);
            String heading = driver.findElement(By.className("base")).getText();
           // String header = heading.substring(21, (heading.length()-1));
            String details = selectedProductDetails.getText().replace("\"", "");
            String url = selectedProductDetails.getAttribute("href");
            //String src = selectedProductImage.getAttribute("src");*/
    }
}
