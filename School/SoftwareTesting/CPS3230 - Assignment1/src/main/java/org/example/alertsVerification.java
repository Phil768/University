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
        //Set the target of the chrome driver.
        driver.get("https://www.marketalertum.com");
        //Initialize a wait in order to make sure that all elements have loaded.
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Wait for the expected conditions and store the navigation bar elements in list of web elements.
        List<WebElement> navigation = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//a[@class='nav-link text-dark']"))));
        //Implicitly wait 30 seconds to make sure that everything has loaded.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Click the third web element. (2 since this behaves like an array were it starts from 0)
        navigation.get(2).click();
        //Finding the user ID and storing it in web element.
        WebElement idInput = driver.findElement(By.id("UserId"));
        //Implicitly wait 30 seconds to make sure that everything has loaded.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Send the selected keys to the input field, in this case the ID input.
        idInput.sendKeys("01150cc0-eff8-4df5-a549-eb18cf7c6184");
        //Store the submit button in a web element after waiting for it to be clickable.
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div/main/form/input[2]"))));
        //Implicitly wait 30 seconds to make sure that everything has loaded.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Submitting the inputted ID by submitting the button.
        submit.submit();
        //Implicitly wait 30 seconds to make sure that everything has loaded.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Getting all the alerts.
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        //Returning the number of alerts in order to make sure that 5 where sent.
        return tables.size();
    }
}
