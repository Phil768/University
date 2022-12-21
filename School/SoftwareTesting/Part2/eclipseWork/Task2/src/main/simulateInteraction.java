package main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class simulateInteraction {
	static WebDriver driver;
	
	public static void main(String []args){
		//simulate();
	}
	public void simulate(){
		System.setProperty("webdriver.chrome.driver", "/Users/phili/OneDrive/Desktop/University/School/SoftwareTesting/Part2/chromedriver.exe");
		//Create a new Chrome driver object.
        driver = new ChromeDriver();
        //Maximise the window to make sure that all the elements are displayed properly.
        driver.manage().window().maximize();
        //Navigating to the marketAlert site.
        driver.get("https://www.marketalertum.com/");
        ///Waiting for the site to fully load.
		//driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Storing the navigation elements in a list of web elements.
        List<WebElement> navigation = driver.findElements(By.xpath("//a[@class='nav-link text-dark']"));
        //Clicking the third option which is the 'Login' option.
        navigation.get(2).click();
        //Storing the ID input field in a web element.
        WebElement idInput = driver.findElement(By.id("UserId"));
        //Sending the selected keys to the ID input field.
        idInput.sendKeys("01150cc0-eff8-4df5-a549-eb18cf7c6184");
        //Storing the submit button in a web element object.
        WebElement submit = driver.findElement(By.xpath("/html/body/div/main/form/input[2]"));
        //Submitting the ID field with the submit button.
        submit.submit();
      	//Waiting for the site to fully load.
        //driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Quitting the site when finished.
        driver.quit();
	}

}
