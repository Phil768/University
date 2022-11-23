package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MarketAlertUM {

    WebDriver driver;
    public String loginStatus;
    public String imageSource;
    public int numberOfElements;
    public int numberOfImages;
    public int numberOfHeadings;
    public int numberOfAnchors;
    public int numberOfPrices;
    public int numberOfDescriptions;

    //Constructor of the class.
    public MarketAlertUM(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String userID) {
        //Storing the navigation elements in a list of web elements.
        List<WebElement> navigation = driver.findElements(By.xpath("//a[@class='nav-link text-dark']"));
        //Clicking the third option which is the 'Login' option.
        navigation.get(2).click();
        //Storing the ID input field in a web element.
        WebElement idInput = driver.findElement(By.id("UserId"));
        //Sending the selected keys to the ID input field.
        idInput.sendKeys(userID);
        //Storing the submit button in a web element object.
        WebElement submit = driver.findElement(By.xpath("/html/body/div/main/form/input[2]"));
        //Submitting the ID field with the submit button.
        submit.submit();
        //Checking if the URL of the page matches the one that would be displayed if the user has locked in successfully, returning the appropriate string according to the success of the statement.
        if(!Objects.equals(driver.getCurrentUrl().toString(), "https://www.marketalertum.com/Alerts/List")) {
            loginStatus = "False";
        }else {
            loginStatus = "True";
        }
    }

    public void checkElements(String userID) {
        //Implicit timeout to wait for the elements to load.
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Storing the navigation elements in a list of web elements.
        List<WebElement> navigation = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//a[@class='nav-link text-dark']"))));
        //Implicit timeout to wait for the elements to load.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Clicking the third option which is the 'Login' option.
        navigation.get(2).click();
        //Storing the ID input field in a web element.
        WebElement idInput = driver.findElement(By.id("UserId"));
        //Sending the selected keys to the ID input field.
        idInput.sendKeys(userID);
        //Storing the submit button in a web element object.
        WebElement submit = driver.findElement(By.xpath("/html/body/div/main/form/input[2]"));
        //Submitting the ID field with the submit button.
        submit.submit();
        //Getting all the tables and storing them in a list of web elements.
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        //Getting all the table rows and storing them in a list of web elements.
        List<WebElement> tableData = driver.findElements(By.tagName("td"));
        //Getting the number of total elements.
        numberOfElements = tables.size();
        for(WebElement i : tables) {
            //Getting all the children of the table.
            List<WebElement> elementList = i.findElements(By.xpath(".//*"));
            /*Incrementing the variable if a corresponding element is found. This is done to check the total number of each element and thus determining the presence of each.*/
            elementList.forEach((e) -> {
                if(Objects.equals(e.getTagName(), "img")) {
                    numberOfImages = numberOfImages + 1;
                } else if(Objects.equals(e.getTagName(), "a")) {
                    numberOfAnchors = numberOfAnchors + 1;
                } else if (Objects.equals(e.getTagName(), "h4")) {
                    numberOfHeadings = numberOfHeadings + 1;
                }else if (Objects.equals(e.getTagName(), "b")) {
                    numberOfPrices = numberOfPrices + 1;
                }
            });
        }
        for(WebElement i : tableData) {
            /*Checking the if the total number of Descriptions.*/
            if (i.getText().contains("Description:")) {
                numberOfDescriptions = numberOfDescriptions + 1;
            }
        }
    }

    //Separate upload method for the final cucumber scenario outline since it requires access to each type.
    public void uploadWithType(int max, int type) throws IOException, InterruptedException {
        //Creating a number of different strings for each option.
        String[] Car = {"Toyota", "Mazda", "Honda", "Suzuki"};
        String[] Boat = {"Yacht", "Sail boat", "Fishing boat", "Dingy"};
        String[] propertyForRent = {"Apartment to let", "Garage to let", "Office to let", "Flat to let"};
        String[] propertyForSale = {"Apartment for sale", "Garage for sale", "Office for sale", "Flat for sale"};
        String[] toys = {"Toy gun", "Toy car", "Toy boat", "Toys"};
        String[] electronics = {"Laptop", "iPhone", "Samsung", "Computer"};

        int x = (int) (Math.random() * (4 - 0 + 1) + 0);
        //For integer out of bounds purposes.
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
        //Implicitly wia tto make sure that all elements load properly.
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        //Creating a new web driver wait object.
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        //Manually putting the system on hold for 11 seconds --> (I realize that this is bad practice, however MaltaPark recently added a popup which has a timer of 11 seconds before one is able to close it).
        Thread.sleep(11000);
        //Pressing the ol button of the popup.
        wait.until(ExpectedConditions.elementToBeClickable(By.id("okbutton"))).click();
        //Waiting for the cookie bar to become visible.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookiebar")));
        //Closing the cookie bar.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("closebutton"))).click();
        //Implicitly wia tto make sure that all elements load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Storing the search bar into a web element object.
        WebElement searchBar = driver.findElement(By.id("search"));
        //Sending the selected search string to the search bar (Toyota was chosen at random).
        searchBar.sendKeys(searchString);
        //Store the search button in a web element object.
        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-search']"));
        //Submitting the search string with the search button.
        searchBtn.submit();
        //The loop will only run once. (Could remove loop but left it for consistency).
        for (int i = 0; i <= max; i++) {
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
            String header = driver.findElement(By.xpath("//*[@id=\"page-content-left\"]/div[1]/h1[1]/span")).getText()/*.replace("\"", "")*/;
            String stringPrice = driver.findElement(By.className("top-price")).getText();
            //Cleaning the price using a created method.
            int price = new integerCleaner().cleanInteger(stringPrice);
            String stringDescription = driver.findElement(By.className("readmore-wrapper")).getText();
            //Cleaning the string using a created method.
            String description = new stringCleaner().cleanString(stringDescription);
            String src = "";
            //In case there is no image to the product.
            if(!driver.findElements(By.className("fancybox")).isEmpty()) {
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
    }
    public void checkFileName(String name) throws IOException, InterruptedException {
        //Storing the img element to a web element object.
        WebElement image = driver.findElement(By.xpath("/html/body/div/main/table[1]/tbody/tr[1]/td/h4/img"));
        //Obtaining the src attribute of the image.
        String imageSrc = image.getAttribute("src").toString();
        //Getting a substring of the image source in order to match that of the cucumber examples table.
        imageSource = imageSrc.substring(37, (imageSrc.length()));
        //Deleting the request so the next test will only have 1, the one it sends.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
    }
}
