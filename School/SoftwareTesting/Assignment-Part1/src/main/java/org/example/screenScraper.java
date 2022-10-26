package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.RequestStatusProvider;
import utils.StatusProvider;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class screenScraper {

    WebDriver driver;
    public StatusProvider statusProvider;
    public RequestStatusProvider requestStatusProvider;

    public screenScraper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean ScreenScraper(int type) throws IOException, InterruptedException {

        if(statusProvider != null) {
            int status = statusProvider.getStatusProvider();

            if (status == statusProvider.OFFLINE) {
                return false;
            } else if (status == statusProvider.ONLINE) {

                String[] Car = {"Toyota", "Mazda", "Honda", "Suzuki"};
                String[] Boat = {"Yacht", "Sail boat", "Fishing boat", "Dingy"};
                String[] propertyForRent = {"Apartment to let", "Garage to let", "Office to let", "Flat to let"};
                String[] propertyForSale = {"Apartment for sale", "Garage for sale", "Office for sale", "Flat for sale"};
                String[] toys = {"Toy gun", "Toy car", "Toy boat", "Toys"};
                String[] electronics = {"Laptop", "iPhone", "Samsung", "Computer"};

                int x = (int) (Math.random() * (4 - 0 + 1) + 0);
                String searchString = switch (type) {
                    case 1 -> Car[x];
                    case 2 -> Boat[x];
                    case 3 -> propertyForRent[x];
                    case 4 -> propertyForSale[x];
                    case 5 -> toys[x];
                    case 6 -> electronics[x];
                    //case 0 ->
                    default -> "";
                };

                if (searchString.equals("")) {
                    return false;
                } else {
                    WebElement searchBar = driver.findElement(By.id("search"));
                    searchBar.sendKeys(searchString);
                    WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-search']"));
                    searchBtn.submit();

                    if(requestStatusProvider != null) {
                        int requestStatus = requestStatusProvider.getRequestStatusProvider();

                        if(requestStatus == requestStatusProvider.goodRequest) {
                            for (int i = 0; i <= 4; i++) {
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
                                String stringPrice = driver.findElement(By.className("top-price")).getText();
                                int price = new integerCleaner().cleanInteger(stringPrice);
                                String stringDescription = driver.findElement(By.className("readmore-wrapper")).getText();
                                String description = new stringCleaner().cleanString(stringDescription);
                                String src = "";
                                if (!driver.findElements(By.className("fancybox")).isEmpty()) {
                                    src = driver.findElement(By.className("fancybox")).getAttribute("href");
                                }

                                //Sending the request.
                                requestObject request = new requestObject(type, header, description, URL, src, "01150cc0-eff8-4df5-a549-eb18cf7c6184", price);
                                httpPostRequest httpPostRequest = new httpPostRequest();
                                httpPostRequest.sendPostRequest(request);
                                productUrl.clear();
                                driver.navigate().back();
                                driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
                                /*if(Objects.equals(httpPostRequest.statusCode, "200") || Objects.equals(httpPostRequest.statusCode, "201")) {
                                    return true;
                                } else {
                                    return false;
                                }*/
                            }
                        } else if(requestStatus == requestStatusProvider.badRequest) {
                            return false;
                        }
                    }
                }

            } else {
                return false;
            }
        }
        return false;
    }

    public void setPageStatus(StatusProvider statusProvider) {
        this.statusProvider = statusProvider;
    }
}
