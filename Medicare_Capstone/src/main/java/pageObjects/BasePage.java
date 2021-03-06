package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

	public ChromeDriver driver;
	public WebDriverWait wait;
	
	public static String appURL;
	public void setAppURL (String url) { appURL = url; }
	public String getAppURL() {	return appURL;	}


	public BasePage(ChromeDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public WebElement waitVisibility(By by) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}


	public void click(By by) {
		
		waitVisibility(by).click();
	}
	
	
	public void writeText(By by, String text) {
		waitVisibility(by).sendKeys(text);
	}
	
	public String readText(By by) {
		return waitVisibility(by).getText();
	}
	
	public String readValue(By by) {
		return waitVisibility(by).getAttribute("value");
	}
	
	public void pickSelection(By by, String selText) {
				
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement sel = driver.findElement(by);
		Select selShowList = new Select(sel);
		selShowList.selectByVisibleText(selText);	
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public abstract BasePage sleep(int miliseconds);
	
	public void threadSleep(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
