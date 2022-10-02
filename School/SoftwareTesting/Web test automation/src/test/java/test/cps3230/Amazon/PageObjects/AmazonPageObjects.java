package test.cps3230.Amazon.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

public class AmazonPageObjects {
    WebDriver driver;
    int index;

    public AmazonPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public boolean selectItem(int index) {
        this.index = index;
        List<WebElement> promotedItems = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));
        WebElement selected = promotedItems.get(index);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", selected);
        WebElement addToBasket = driver.findElement(By.id("add-to-cart-button"));
        addToBasket.submit();
        WebElement basket = driver.findElement(By.id("nav-cart"));
        basket.click();
        WebElement quantity = driver.findElement(By.className("a-dropdown-prompt"));

        //WebElement delete = driver.findElement(By.xpath("//*[@id=\"sc-item-Ca1834358-0ca2-4320-97d7-60c64cbf0744\"]/div[4]/div/div[2]/div[1]/span[2]/span/input"));

        if(Objects.equals(quantity.getText(), "1")) {
            WebDriverWait some_element = new WebDriverWait(driver,100);
            some_element.until(ExpectedConditions.elementToBeClickable(By.id("//*[@id=\"sc-item-Ca1834358-0ca2-4320-97d7-60c64cbf0744\"]/div[4]/div/div[2]/div[1]/span[2]/span/input"))).submit();
            return true;
        }
        return false;
    }
}
