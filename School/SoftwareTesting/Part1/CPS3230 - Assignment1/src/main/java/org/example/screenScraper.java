package org.example;

import org.example.utils.DriverStatusProvider;
import org.example.utils.RequestStatusProvider;
import org.example.utils.StatusProvider;
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
    protected StatusProvider statusProvider;
    protected RequestStatusProvider requestStatusProvider;
    protected DriverStatusProvider driverStatusProvider;

    public screenScraper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean ScreenScraper(int type) throws IOException, InterruptedException {

        //If the status and the driver are not null.
        if(statusProvider != null && driver != null) {
            //Storing the status of the page status in an integer, according to the mock.
            int status = statusProvider.getStatusProvider();

            //If the status is set to offline, the program will terminate.
            if (status == statusProvider.OFFLINE) {
                return false;
                //If the status is online it will proceed accordingly.
            } else if (status == statusProvider.ONLINE) {

                //Creating a number of different strings for each option.
                String[] Car = {"Toyota", "Mazda", "Honda", "Suzuki"};
                String[] Boat = {"Yacht", "Sail boat", "Fishing boat", "Dingy"};
                String[] propertyForRent = {"Apartment to let", "Garage to let", "Office to let", "Flat to let"};
                String[] propertyForSale = {"Apartment for sale", "Garage for sale", "Office for sale", "Flat for sale"};
                String[] toys = {"Toy gun", "Toy car", "Toy boat", "Toys"};
                String[] electronics = {"Laptop", "iPhone", "Samsung", "Computer"};

                //Allocating a search string index.
                int x = (int) (Math.random() * (4 + 1) + 0);
                //For integer out of bound purposes.
                if(x == 4) {
                    x= x - 1;
                }
                //Choosing which type of option to search based on the parameter passed from the test.
                String searchString = switch (type) {
                    case 1 -> Car[x];
                    case 2 -> Boat[x];
                    case 3 -> propertyForRent[x];
                    case 4 -> propertyForSale[x];
                    case 5 -> toys[x];
                    case 6 -> electronics[x];
                    default -> "";
                };

                //If the string is empty, the program will terminate.
                if (searchString.equals("")) {
                    return false;
                } else {
                    System.out.println(searchString);
                    int driverStatus = driverStatusProvider.getDriverStatusProvider();
                    if (driverStatus == driverStatusProvider.badDriver) {
                        return false;
                    } else if (driverStatus == driverStatusProvider.goodDriver) {
                        //Storing the search bar into a web element object.
                        WebElement searchBar = driver.findElement(By.id("search"));
                        //Sending the selected search string to the search bar.
                        searchBar.sendKeys(searchString);
                        //Store the search button in a web element object.
                        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-search']"));
                        //Submitting the search string with the search button.
                        searchBtn.submit();
                        //Checking if the request status is null.
                        if (requestStatusProvider != null) {
                            //Storing the request status in an integer, based on what is passed from the mock.
                            int requestStatus = requestStatusProvider.getRequestStatusProvider();
                            //If the request is bad.
                            if (requestStatus == requestStatusProvider.badRequest) {
                                return false;
                                //If the request is good.
                            } else if (requestStatus == requestStatusProvider.goodRequest) {
                                //Looping through the first 5 items in the website (4 since the count start from 0).
                                for (int i = 0; i <= 4; i++) {
                                    //Initiating driver.wait in order to wait for elements to load.
                                    WebDriverWait wait = new WebDriverWait(driver, 10);
                                    //Storing the products in a list of web elements.
                                    List<WebElement> productUrl = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.className("header"))));
                                    //Storing the details of the selected product in a web element object.
                                    WebElement selectedProductDetails = wait.until(ExpectedConditions.elementToBeClickable(productUrl.get(i)));
                                    //Storing the url of the product in a web element object.
                                    WebElement url = wait.until(ExpectedConditions.elementToBeClickable(selectedProductDetails));

                                    //Storing all the elements obtained in the correct variables to create the object.
                                    String URL = url.getAttribute("href");
                                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                                    selectedProductDetails.click();
                                    String header = driver.findElement(By.xpath("//*[@id=\"page-content-left\"]/div[1]/h1[1]/span")).getText().replace("\"", "");
                                    String stringPrice = driver.findElement(By.className("top-price")).getText();
                                    //Cleaning the price using a created method.
                                    int price = new integerCleaner().cleanInteger(stringPrice);
                                    String stringDescription = driver.findElement(By.className("readmore-wrapper")).getText();
                                    //Cleaning the string using a created method.
                                    String description = new stringCleaner().cleanString(stringDescription);
                                    String src = "";
                                    //In case there is no image to the product.
                                    if (!driver.findElements(By.className("fancybox")).isEmpty()) {
                                        src = driver.findElement(By.className("fancybox")).getAttribute("href");
                                    }

                                    //Creating the request object and using the created methods, send the request to the MarketAlertUm website.
                                    requestObject request = new requestObject(type, header, description, URL, src, "01150cc0-eff8-4df5-a549-eb18cf7c6184", price);
                                    httpPostRequest httpPostRequest = new httpPostRequest();
                                    httpPostRequest.sendPostRequest(request);
                                    productUrl.clear();
                                    //Go back to the products page in order for the next element to be visible when clicked.
                                    driver.navigate().back();
                                    //Implicit timeout to wait for the elements to load.
                                    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
                                }
                                //If request provider is null.
                            }
                        }else {
                            return false;
                        }
                    }
                }

            }
            //If either the page status or the driver are null.
        } else {
            return false;
        }
        return true;
    }

    /*Setting the status of the page, request and driver based on the mocks used in the tests.*/

    public void setPageStatus(StatusProvider statusProvider) {
        this.statusProvider = statusProvider;
    }
    public void setRequestStatus(RequestStatusProvider requestStatusProvider) {
        this.requestStatusProvider = requestStatusProvider;
    }
    public void setDriverStatus(DriverStatusProvider driverStatusProvider) {
        this.driverStatusProvider = driverStatusProvider;
    }
}
