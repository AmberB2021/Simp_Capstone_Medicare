package pageObjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import utility.GenerateReport;

public class Shipping extends BasePage {

	public Shipping(ChromeDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Select")
	List<WebElement> ShippingAddresses;	
	
	By ShippingPageVal = By.xpath("//div[@class='col-md-4']/h4");

	public Shipping isShippingPage()  {
		GenerateReport.info("Validate is Shipping Page.. ");
		assertEquals(readText(ShippingPageVal), "Select Shipping Address");
		return this;
	}
		
	public Payment selectFirstAddress() {
		
		GenerateReport.info("Selecting first address.. ");
		ShippingAddresses.get(0).click();
		return new Payment(driver);
	}

	
	@Override
	public Shipping sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}	
	
}