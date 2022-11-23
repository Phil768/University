package Task1;

import org.example.alertsVerification;
import org.example.httpDeleteRequest;
import org.example.screenScraper;
import org.example.utils.DriverStatusProvider;
import org.example.utils.RequestStatusProvider;
import org.example.utils.StatusProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class screenScraperTest
{
    WebDriver driver;
    screenScraper  scraper;
    final int Car = 1;
    final int Boat = 2;
    final int PropertyForRent = 3;
    final int PropertyForSale = 4;
    final int Toys = 5;
    final int Electronics = 6;

    @BeforeEach
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        //Create a new Chrome driver object.
        driver = new ChromeDriver();
        //Maximize the window to make sure that all the elements are displayed properly.
        driver.manage().window().maximize();
        //Implicitly wia tto make sure that all elements load properly.
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        //Setting the target of the chrome driver.
        driver.get("https://www.maltapark.com/");
        //Implicitly wia tto make sure that all elements load properly.
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
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
        //Initiating the Page object with the allocated driver.
        scraper = new screenScraper(driver);
    }

    @AfterEach
    public void teardown() {
        //Forcing the driver to quit after each test.
        driver.quit();
    }

    @Test
    public void testSearchWithCorrectString() throws IOException, InterruptedException {
        //Setup
        //Deleting all the previous requests in order to make sure that the 5 sent are of this test.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();

        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);

        //Exercise
        scraper.ScreenScraper(Car);
        int alerts = new alertsVerification().verifyAlerts();
        //Verification
        Assertions.assertEquals(5, alerts);
    }
    @Test
    public void testSearchWithEmptyString() throws IOException, InterruptedException {
        //Setup
        //Deleting all the previous requests in order to make sure that the 5 sent are of this test.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);

        //Exercise & Verification

        //Passing a 7 which in the switch statement will be considered as a default and thus passing an empty string to the website.
        Assertions.assertFalse(scraper.ScreenScraper(7));
    }

    @Test
    public void testForCars() throws IOException, InterruptedException {
        //Setup.
        //Deleting all the previous requests in order to make sure that the 5 sent are of this test.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);

        //Exercise
        scraper.ScreenScraper(Car);
        int alerts = new alertsVerification().verifyAlerts();
        //Verification
        Assertions.assertEquals(5, alerts);
    }

    @Test
    public void testForBoats() throws IOException, InterruptedException {
        //Setup.
        //Deleting all the previous requests in order to make sure that the 5 sent are of this test.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);

        //Exercise.
        scraper.ScreenScraper(Boat);
        int alerts = new alertsVerification().verifyAlerts();
        //Verification
        Assertions.assertEquals(5, alerts);
    }

    @Test
    public void testForPropertyForSale() throws IOException, InterruptedException {
        //Setup.
        //Deleting all the previous requests in order to make sure that the 5 sent are of this test.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);

        //Exercise.
        scraper.ScreenScraper(PropertyForSale);
        int alerts = new alertsVerification().verifyAlerts();
        //Verification
        Assertions.assertEquals(5, alerts);
    }

    @Test
    public void testForPropertyForRent() throws IOException, InterruptedException {
        //Setup.
        //Deleting all the previous requests in order to make sure that the 5 sent are of this test.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);

        //Exercise.
        scraper.ScreenScraper(PropertyForRent);
        int alerts = new alertsVerification().verifyAlerts();
        //Verification
        Assertions.assertEquals(5, alerts);
    }

    @Test
    public void testForToys() throws IOException, InterruptedException {
        //Setup.
        //Deleting all the previous requests in order to make sure that the 5 sent are of this test.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);

        //Exercise.
        scraper.ScreenScraper(Toys);
        int alerts = new alertsVerification().verifyAlerts();
        //Verification
        Assertions.assertEquals(5, alerts);
    }

    @Test
    public void testForElectronics() throws IOException, InterruptedException {
        //Setup.
        //Deleting all the previous requests in order to make sure that the 5 sent are of this test.
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);

        //Exercise.
        scraper.ScreenScraper(Electronics);
        int alerts = new alertsVerification().verifyAlerts();
        //Verification
        Assertions.assertEquals(5, alerts);
    }

    @Test
    public void testForOfflineStatus() throws IOException, InterruptedException {
        //Setup.

        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Setting the mock of the page status to offline which should return false.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.OFFLINE);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertFalse(bool);
    }

    @Test
    public void testForBadRequest() throws IOException, InterruptedException {
        //Setup.

        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the mock of the status to online.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the driver mock to successful.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Setting the mock of the request to a bad request.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.badRequest);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertFalse(bool);
    }

    @Test
    public void testForBadDriver() throws IOException, InterruptedException {
        //Setup

        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to failure.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.badDriver);
        //Passing the mock object to the page.
        scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertFalse(bool);
    }

    @Test
    public void testDriverOrStatusNull() throws IOException, InterruptedException {
        //Setup

        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to failure.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);

        // -> Passing a null page status. <-
        //scraper.setPageStatus(statusProvider);
        //Passing the mock object to the page.
        scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertFalse(bool);
    }

    @Test
    public void testForNullRequest() throws IOException, InterruptedException {
        //Setup

        //Mocking the status of the page.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        //Mocking the status of the request.
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        //Mocking the status of the driver.
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        //Setting the page mock to successful.
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(StatusProvider.ONLINE);
        //Setting the request mock to successful.
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(RequestStatusProvider.goodRequest);
        //Setting the driver mock to failure.
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(DriverStatusProvider.goodDriver);
        //Passing the mock object to the page
        scraper.setPageStatus(statusProvider);
        // -> Passing a null request <-
        //scraper.setRequestStatus(requestStatusProvider);
        //Passing the mock object to the page.
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertFalse(bool);
    }
}
