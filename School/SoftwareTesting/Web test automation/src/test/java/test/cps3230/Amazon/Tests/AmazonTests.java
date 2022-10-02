package test.cps3230.Amazon.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.cps3230.Amazon.PageObjects.AmazonPageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonTests
{
    WebDriver driver;
    AmazonPageObjects amazon;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/School/SoftwareTesting/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.amazon.co.uk/");
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testTodaysDeals()
    {
        //Exercise
        WebElement anchor = driver.findElement(By.xpath("//*[@id=\"nav-xshop\"]/a[6]"));
        anchor.click();
        String title = driver.getTitle();
        WebElement header = driver.findElement(By.xpath("//*[@id=\"slot-2\"]/div/h1"));
        //Verify
        Assertions.assertEquals("Today's Deals: New Deals. Every Day.", title);
        Assertions.assertEquals("Today's Deals", header.getText());

    }

    @Test
    public void testNotepad()
    {
        //Exercise
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys("Notepad");
        WebElement searchBtn = driver.findElement(By.id("nav-search-submit-button"));
        searchBtn.submit();
        List<WebElement> notePads = driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
        WebElement secondOpt = notePads.get(1);
        //Element in DOM has not loaded yet so a wait condition had to be created.
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", secondOpt);
        WebElement notepadName = driver.findElement(By.id("productTitle"));
        WebElement addToBasket = driver.findElement(By.id("add-to-cart-button"));
        addToBasket.submit();
        WebElement basket = driver.findElement(By.id("nav-cart"));
        basket.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement notepadBasketName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sc-item-Ce7d202d7-eb9b-4993-a943-a2acd6dc6057\"]/div[4]/div/div[2]/ul/li[1]/span/a/span[1]/span/span[2]")));
        //Verify
        Assertions.assertEquals(notepadName.getText(), notepadBasketName.getText());
    }

    @Test
    public void testPromotedItems() {
        //Setup
        amazon = new AmazonPageObjects(driver);
        //Exercise & Verify
        Assertions.assertTrue(amazon.selectItem(4));
    }

    //*[@id="sc-item-Ce7d202d7-eb9b-4993-a943-a2acd6dc6057"]/div[4]/div/div[2]/div[1]/span[2]/span/input
    //*[@id="sc-item-Ce7d202d7-eb9b-4993-a943-a2acd6dc6057"]/div[4]/div/div[2]/div[1]/span[3]/span/input
}
