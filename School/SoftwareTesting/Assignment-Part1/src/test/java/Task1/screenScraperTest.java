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

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class screenScraperTest
{
    WebDriver driver;
    requestObject request;

    @BeforeEach
    public void setup() {
        // #On Home PC use: /Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "/Users/phili/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.scanmalta.com/shop/");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void Test1() throws IOException {
        WebElement searchBar = driver.findElement(By.id("search"));
        searchBar.sendKeys("MacBook Pro");
        WebElement searchBtn = driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div/div/div[3]/div/div[3]/div[2]/div[1]/form/div[3]/button"));
        searchBtn.submit();
        //save all laptops in a list.
        List<WebElement> productUrl = driver.findElements(By.className("product-item-link"));
        List<WebElement> productImage = driver.findElements(By.xpath("//img[@class='product-image-photo main-img']"));
        List<WebElement> productPrice = driver.findElements(By.className("price"));
        for(int i = 0; i <= 4; i++) {
            WebElement selectedProductDetails = productUrl.get(i);
            WebElement selectedProductImage = productImage.get(i);
            WebElement selectedProductPrice = productPrice.get(i);
            String price = selectedProductPrice.getText();
            String newPrice = "";
            if(price.contains(",")) {
                newPrice = price.replace(",","").replace("€","");
            }
            double priceInteger = Double.parseDouble(newPrice);
            String heading = driver.findElement(By.className("base")).getText();
            String header = heading.substring(20);
            request = new requestObject(6, header, selectedProductDetails.getText(), selectedProductDetails.getAttribute("href"), selectedProductImage.getAttribute("src"), "01150cc0-eff8-4df5-a549-eb18cf7c6184", priceInteger);
            try {
                new httpPostRequest().sendPostRequest(request);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
