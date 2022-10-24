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

    public MarketAlertUM(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String userID) {
        List<WebElement> navigation = driver.findElements(By.xpath("//a[@class='nav-link text-dark']"));
        navigation.get(2).click();
        WebElement idInput = driver.findElement(By.id("UserId"));
        idInput.sendKeys(userID);
        WebElement submit = driver.findElement(By.xpath("/html/body/div/main/form/input[2]"));
        submit.submit();
        if(!Objects.equals(driver.getCurrentUrl().toString(), "https://www.marketalertum.com/Alerts/List")) {
            loginStatus = "False";
        }else {
            loginStatus = "True";
        }
    }

    public void upload(int c) throws IOException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookiebar")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("closebutton"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement searchBar = driver.findElement(By.id("search"));
        searchBar.sendKeys("Toyota");
        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-search']"));
        searchBtn.submit();
        for (int i = 0; i <= c; i++) {
            //Initiating driver.wait in order to wait for elements to load.
            //WebDriverWait wait = new WebDriverWait(driver, 10);
            List<WebElement> productUrl = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.className("header"))));
            WebElement selectedProductDetails = wait.until(ExpectedConditions.elementToBeClickable(productUrl.get(i)));
            WebElement url = wait.until(ExpectedConditions.elementToBeClickable(selectedProductDetails));

            //Storing all the elements obtained in the correct variables to create the object.
            String URL = url.getAttribute("href");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            selectedProductDetails.click();
            String header = driver.findElement(By.xpath("//*[@id=\"page-content-left\"]/div[1]/h1[1]/span")).getText();
            //Cleaning the strings to be able tos end them through the API.
            String stringPrice = driver.findElement(By.className("top-price")).getText();
            int price = 0;
            if (stringPrice.equals("€ ---")) {
                stringPrice = "0";
            }
            price = (Integer.parseInt(stringPrice.replace(",", "").replace("€", "").replace(" ", "").replace(".", "")));
            price = price * 100;
            String description = driver.findElement(By.className("readmore-wrapper")).getText().replace("\n", "").replace("\"", "");
            String src = "";
            if(!driver.findElements(By.className("fancybox")).isEmpty()) {
                src = driver.findElement(By.className("fancybox")).getAttribute("href");
            }

            //Sending the request.
            requestObject request = new requestObject(1, header, description, URL, src, "01150cc0-eff8-4df5-a549-eb18cf7c6184", price);
            httpPostRequest httpPostRequest = new httpPostRequest();
            httpPostRequest.sendPostRequest(request);
            productUrl.clear();
            driver.navigate().back();
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        }
    }

    public void checkElements(String userID) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> navigation = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//a[@class='nav-link text-dark']"))));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        navigation.get(2).click();
        WebElement idInput = driver.findElement(By.id("UserId"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        idInput.sendKeys(userID);
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div/main/form/input[2]"))));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        submit.submit();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //navigation.get(1).click();
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        numberOfElements = tables.size();
        for(WebElement i : tables) {
            List<WebElement> elementList = i.findElements(By.xpath(".//*"));
            elementList.forEach((e) -> {
                if(Objects.equals(e.getTagName(), "img")) {
                    numberOfImages = numberOfImages + 1;
                } else if(Objects.equals(e.getTagName(), "a")) {
                    numberOfAnchors = numberOfAnchors + 1;
                } else if (Objects.equals(e.getTagName(), "h4")) {
                    numberOfHeadings = numberOfHeadings + 1;
                }
            });
        }
    }

    public void viewAlerts(String userID) {
        List<WebElement> navigation = driver.findElements(By.xpath("//a[@class='nav-link text-dark']"));
        navigation.get(3).click();
        WebElement idInput = driver.findElement(By.id("UserId"));
        idInput.sendKeys(userID);
        WebElement submit = driver.findElement(By.xpath("/html/body/div/main/form/input[2]"));
        submit.submit();
        navigation.get(2).click();
    }

    public void uploadWithType(int type) throws IOException, InterruptedException {
        String[] Car = {"Toyota", "Mazda", "Honda", "Suzuki"};
        String[] Boat = {"Yacht", "Sail boat", "Fishing boat", "Dingy"};
        String[] propertyForRent = {"Apartment to let", "Garage to let", "Office to let", "Flat to let"};
        String[] propertyForSale = {"Apartment for sale", "Garage for sale", "Office for sale", "Flat for sale"};
        String[] toys = {"Toy gun", "Toy car", "Toy boat", "Toys"};
        String[] electronics = {"Laptop", "iPhone", "Samsung", "Computer"};

        int x = (int) (Math.random() * (4 - 0 + 1) + 0);
        if(x == 4) {
            x= x - 1;
        }
        String searchString = switch (type) {
            case 1 -> Car[x];
            case 2 -> Boat[x];
            case 3 -> propertyForRent[x];
            case 4 -> propertyForSale[x];
            case 5 -> toys[x];
            case 6 -> electronics[x];
            default -> "";
        };
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        /*wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookiebar")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("closebutton"))).click();*/
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement searchBar = driver.findElement(By.id("search"));
        searchBar.sendKeys(searchString);
        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-search']"));
        searchBtn.submit();
        for (int i = 0; i <= 0; i++) {
            //Initiating driver.wait in order to wait for elements to load.
            //WebDriverWait wait = new WebDriverWait(driver, 10);
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
            int price = 0;
            if (stringPrice.equals("€ ---")) {
                stringPrice = "0";
            }
            price = (Integer.parseInt(stringPrice.replace(",", "").replace("€", "").replace(" ", "").replace(".", "")));
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
    public void checkFileName(String name) throws IOException, InterruptedException {
        WebElement image = driver.findElement(By.xpath("/html/body/div/main/table[1]/tbody/tr[1]/td/h4/img"));
        String imageSrc = image.getAttribute("src").toString();
        imageSource = imageSrc.substring(37, (imageSrc.length()));
        /*httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();*/
    }
}
