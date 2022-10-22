package Task2;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MarketAlertUM;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MarketAlertSteps {

    MarketAlertUM UM;
    WebDriver driver;
    @Given("I am a user of marketalertum")
    public void iAmUsingMarketAlert() {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();
        //Allocate teh driver.
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get("https://www.marketalertum.com");
        UM = new MarketAlertUM(driver);
    }
    @When("I login using {string}")
    public void iLoginUsing(String arg0) {
        UM.login(arg0);
    }
    @Then("I should get a login status of {string}")
    public void iShouldGetALoginStatusOf(String arg0) {
        Assertions.assertEquals(arg0, UM.loginStatus);
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) throws IOException, InterruptedException {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();
            //Allocate teh driver.
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.get("https://www.maltapark.com/");
            UM = new MarketAlertUM(driver);
            UM.upload(arg0);
    }

    @When("I view a list of alerts using {string}")
    public void iViewAListOfAlertsUsing(String arg0) {
        UM.checkElements(arg0);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        Assertions.assertEquals(arg0, UM.numberOfElements);
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws IOException, InterruptedException {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();
        //Allocate teh driver.
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get("https://www.maltapark.com/");
        UM = new MarketAlertUM(driver);
        UM.upload(arg0);
    }

    /*@When("I view a list of alerts {string}")
    public void iViewAListOfAlerts(String arg0) {
        UM.checkLayout(arg0);
    }*/

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

    @And("each alert should contain a link to the original product website, which would bring the total number of anchors present to {int}")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsiteWhichWouldBringTheTotalNumberOfAnchorsPresentTo(int arg0) {
        Assertions.assertEquals(arg0, UM.numberOfAnchors);
    }

    @Given("I am an administrator of the website and I upload an alert of type {string}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(String arg0) throws IOException, InterruptedException {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();
        //Allocate teh driver.
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get("https://www.maltapark.com/");
        UM = new MarketAlertUM(driver);
        int type = Integer.parseInt(arg0);
        UM.uploadWithType(type);
    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) {
        UM.checkFileName(arg0);
        Assertions.assertEquals(arg0, UM.imageSource);
    }
}
