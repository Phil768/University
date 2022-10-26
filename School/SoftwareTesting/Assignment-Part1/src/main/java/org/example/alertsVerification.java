package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class alertsVerification {

    public int verifyAlerts() {
        //Initialize the driver.
        System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //Allocate the driver.
        driver.manage().window().maximize();
        driver.get("https://www.marketalertum.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> navigation = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//a[@class='nav-link text-dark']"))));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        navigation.get(2).click();
        WebElement idInput = driver.findElement(By.id("UserId"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        idInput.sendKeys("01150cc0-eff8-4df5-a549-eb18cf7c6184");
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div/main/form/input[2]"))));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        submit.submit();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //navigation.get(1).click();
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        return tables.size();
    }
}
