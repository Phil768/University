package Task2;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MarketAlertUM;
import org.example.httpDeleteRequest;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MarketAlertSteps {

    MarketAlertUM UM;
    @Given("I am a user of marketalertum")
    public void iAmUsingMarketAlert() {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        //Allocate the driver.
        WebDriver driver = new ChromeDriver();
        //Maximize the screen to make sure that all elements have loaded correctly.
        driver.manage().window().maximize();
        //Setting the target of the driver.
        driver.get("https://www.marketalertum.com");
        //Allocating the MarketAlertUM object.
        UM = new MarketAlertUM(driver);
    }
    @When("I login using {string}")
    public void iLoginUsing(String arg0) {
        //Logging in using the ID>
        UM.login(arg0);
    }
    @Then("I should get a login status of {string}")
    public void iShouldGetALoginStatusOf(String arg0) {
        //Checking if the user had logged in.
        Assertions.assertEquals(arg0, UM.loginStatus);
    }

   /* @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) throws IOException, InterruptedException {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        //Allocate the driver.
        WebDriver driver = new ChromeDriver();
        //Maximize the screen to make sure that all elements have loaded correctly.
        driver.manage().window().maximize();
        //Implicitly wait for the website to load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Setting the target of the driver.
        driver.get("https://www.maltapark.com/");
        //Implicitly wait for the website to load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        UM = new MarketAlertUM(driver);
        UM.upload(arg0);
    }*/

    @When("I view a list of alerts using {string}")
    public void iViewAListOfAlertsUsing(String arg0) {
        //Checking the elements.
        UM.checkElements(arg0);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        //Checking that there are a total of 5 alerts.
        Assertions.assertEquals(arg0, UM.numberOfElements);
    }

    /*@Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws IOException, InterruptedException {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        //Allocate the driver.
        WebDriver driver = new ChromeDriver();
        //Maximize the screen to make sure that all elements have loaded correctly.
        driver.manage().window().maximize();
        //Implicitly wait for the website to load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Setting the target of the driver.
        driver.get("https://www.maltapark.com/");
        //Allocating the MarketAlertUM object.
        UM = new MarketAlertUM(driver);
        UM.upload(arg0);
    }*/

    @Given("I am an administrator of the website and I upload {int} alerts of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlertsOfType(int arg0, int arg1) throws IOException, InterruptedException {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        //Allocate the driver.
        WebDriver driver = new ChromeDriver();
        //Maximize the screen to make sure that all elements have loaded correctly.
        driver.manage().window().maximize();
        //Implicitly wait for the website to load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Setting the target of the driver.
        driver.get("https://www.maltapark.com/");
        //Allocating the MarketAlertUM object.
        UM = new MarketAlertUM(driver);
        UM.uploadWithType(arg0, arg1);
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlertsOfType(int arg0, int arg1) throws IOException, InterruptedException {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        //Allocate the driver.
        WebDriver driver = new ChromeDriver();
        //Maximize the screen to make sure that all elements have loaded correctly.
        driver.manage().window().maximize();
        //Implicitly wait for the website to load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Setting the target of the driver.
        driver.get("https://www.maltapark.com/");
        //Implicitly wait for the website to load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        UM = new MarketAlertUM(driver);
        UM.uploadWithType(arg0, arg1);
    }

    /*Checking that the presence of each element of the alerts in the website.*/

    @Then("each alert should contain an icon, which would bring the total number of images present to {int}")
    public void eachAlertShouldContainAnIconWhichWouldBringTheTotalNumberOfImagesPresentTo(int arg0) {
        Assertions.assertEquals(arg0, UM.numberOfImages);
    }

    @And("each alert should contain a heading, which would bring the total number of headers present to {int}")
    public void eachAlertShouldContainAHeadingWhichWouldBringTheTotalNumberOfHeadersPresentTo(int arg0) {
        Assertions.assertEquals(arg0, UM.numberOfHeadings);
    }

    @And("each alert should contain an image, which would bring the total number of images present to {int}")
    public void eachAlertShouldContainAnImageWhichWouldBringTheTotalNumberOfImagesPresentTo(int arg0) {
        Assertions.assertEquals(arg0, UM.numberOfImages);
    }
    @And("each alert should contain a description, which would bring the total number of descriptions to {int}")
    public void eachAlertShouldContainADescriptionWhichWouldBringTheTotalNumberOfDescriptionsTo(int arg0) {
        Assertions.assertEquals(arg0, UM.numberOfDescriptions);
    }
    @And("each alert should contain a price, which would bring the total number of prices to {int}")
    public void eachAlertShouldContainAPriceWhichWouldBringTheTotalNumberOfPricesTo(int arg0) {
        Assertions.assertEquals(arg0, UM.numberOfPrices);
    }

    @And("each alert should contain a link to the original product website, which would bring the total number of anchors present to {int}")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsiteWhichWouldBringTheTotalNumberOfAnchorsPresentTo(int arg0) {
        Assertions.assertEquals(arg0, UM.numberOfAnchors);
    }

    /*@Given("I am an administrator of the website and I upload an alert of type {string}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(String arg0) throws IOException, InterruptedException {
        //Deleting the previous requests.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        //Allocating the driver.
        WebDriver driver = new ChromeDriver();
        //Maximize the screen to make sure that all elements have loaded correctly.
        driver.manage().window().maximize();
        //Implicitly wait for the website to load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Setting the target of the driver.
        driver.get("https://www.maltapark.com/");
        //Allocating the MarketAlertUM object.
        UM = new MarketAlertUM(driver);
        //Parsing the integer type to be a suitable parameter.
        int type = Integer.parseInt(arg0);
        UM.uploadWithType(type);
    }*/

    @Given("I am an administrator of the website and I upload {int} alerts of type {string}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlertsOfType(int arg0, String arg1) throws IOException, InterruptedException {
        //Deleting the previous requests.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        //Allocating the driver.
        WebDriver driver = new ChromeDriver();
        //Maximize the screen to make sure that all elements have loaded correctly.
        driver.manage().window().maximize();
        //Implicitly wait for the website to load properly.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Setting the target of the driver.
        driver.get("https://www.maltapark.com/");
        //Allocating the MarketAlertUM object.
        UM = new MarketAlertUM(driver);
        //Parsing the integer type to be a suitable parameter.
        int type = Integer.parseInt(arg1);
        UM.uploadWithType(arg0, type);
    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) throws IOException, InterruptedException {
        //Checking the file names.
        UM.checkFileName(arg0);
        Assertions.assertEquals(arg0, UM.imageSource);
    }



}
