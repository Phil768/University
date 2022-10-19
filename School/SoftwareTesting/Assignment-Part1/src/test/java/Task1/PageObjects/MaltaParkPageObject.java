package Task1.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class MaltaParkPageObject {
    WebDriver driver;
    public MaltaParkPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean search(String searchString) {
        if(Objects.equals(searchString, "")) {
            return false;
        }
        else {
            WebElement searchBar = driver.findElement(By.id("search"));
            searchBar.sendKeys(searchString);
            WebElement searchBtn = driver.findElement(By.xpath("//button[@class='btn btn-search']"));
            searchBtn.submit();

            return true;
        }
    }
}
