package Task1.Tests;

import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverStatusProvider;
import utils.RequestStatusProvider;
import utils.StatusProvider;

import java.io.IOException;
import java.util.List;
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
        // #On Home PC use: /Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get("https://www.maltapark.com/");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        Thread.sleep(11000);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookiebar")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("closebutton"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Initiating the Page object.
        scraper = new screenScraper(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testSearchWithCorrectString() throws IOException, InterruptedException {
        //Setup
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.goodRequest);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.goodDriver);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        scraper.setDriverStatus(driverStatusProvider);
        
        //Verification
        Assertions.assertTrue(scraper.ScreenScraper(Car));
    }
    @Test
    public void testSearchWithEmptyString() throws IOException, InterruptedException {
        //Setup
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.goodRequest);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.goodDriver);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise & Verification
        Assertions.assertFalse(scraper.ScreenScraper(7));
    }

    @Test
    public void testForCars() throws IOException, InterruptedException {
        //Setup.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.goodRequest);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.goodDriver);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Car);
        //Verification
        Assertions.assertTrue(bool);
    }

    @Test
    public void testForBoats() throws IOException, InterruptedException {
        //Setup.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.goodRequest);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.goodDriver);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Boat);
        //Verification
        Assertions.assertTrue(bool);
    }

    @Test
    public void testForPropertyForSale() throws IOException, InterruptedException {
        //Setup.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.goodRequest);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.goodDriver);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(PropertyForSale);
        //Verification
        Assertions.assertTrue(bool);
    }

    @Test
    public void testForPropertyForRent() throws IOException, InterruptedException {
        //Setup.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.goodRequest);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.goodDriver);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(PropertyForRent);
        //Verification
        Assertions.assertTrue(bool);
    }

    @Test
    public void testForToys() throws IOException, InterruptedException {
        //Setup.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.goodRequest);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.goodDriver);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Toys);
        //Verification
        Assertions.assertTrue(bool);
    }

    @Test
    public void testForElectronics() throws IOException, InterruptedException {
        //Setup.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.goodRequest);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.goodDriver);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertTrue(bool);
    }

    @Test
    public void testForOfflineStatus() throws IOException, InterruptedException {
        //Setup.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.OFFLINE);
        scraper.setPageStatus(statusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertFalse(bool);
    }

    @Test
    public void testForBadRequest() throws IOException, InterruptedException {
        //Setup.
        StatusProvider statusProvider = Mockito.mock(StatusProvider.class);
        RequestStatusProvider requestStatusProvider = Mockito.mock(RequestStatusProvider.class);
        Mockito.when(statusProvider.getStatusProvider()).thenReturn(statusProvider.ONLINE);
        Mockito.when(requestStatusProvider.getRequestStatusProvider()).thenReturn(requestStatusProvider.badRequest);
        scraper.setPageStatus(statusProvider);
        scraper.setRequestStatus(requestStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertTrue(bool);
    }

    @Test
    public void testForBadDriver() throws IOException, InterruptedException {
        DriverStatusProvider driverStatusProvider = Mockito.mock(DriverStatusProvider.class);
        Mockito.when(driverStatusProvider.getDriverStatusProvider()).thenReturn(driverStatusProvider.badDriver);
        scraper.setDriverStatus(driverStatusProvider);
        //Exercise.
        boolean bool = scraper.ScreenScraper(Electronics);
        //Verification
        Assertions.assertTrue(bool);
    }

    @Test
    public void testMarketAlertUM() {
        //Setup
        int alerts = new alertsVerification().verifyAlerts();
        //Verification
        Assertions.assertEquals(5, alerts);
    }

    @Test
    public void test() throws IOException, InterruptedException {
        httpDeleteRequest httpDeleteRequest = new httpDeleteRequest();
        httpDeleteRequest.sendDeleteRequest();
    }
}
