package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class screenScraper {

    WebDriver driver;

    public screenScraper(WebDriver driver) {
        this.driver = driver;
    }

    public void ScreenScraper(String searchString, int type) throws IOException, InterruptedException {
        WebElement searchBar = driver.findElement(By.id("search"));
        searchBar.sendKeys(searchString);
        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-search']"));
        searchBtn.submit();
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
            int price = Integer.parseInt(driver.findElement(By.className("top-price")).getText().replace(",","").replace("€","").replace(" ", "").replace(".", ""));
            price = price * 100;
            String description = driver.findElement(By.className("readmore-wrapper")).getText().replace("\n", "").replace("\"", "");
            String src = driver.findElement(By.className("fancybox")).getAttribute("href");

            //Sending the request.
            requestObject request = new requestObject(type, header, description, URL, src, "01150cc0-eff8-4df5-a549-eb18cf7c6184", price);
            httpPostRequest httpPostRequest = new httpPostRequest();
            httpPostRequest.sendPostRequest(request);
            productUrl.clear();
            driver.navigate().back();
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        }

    }
}
