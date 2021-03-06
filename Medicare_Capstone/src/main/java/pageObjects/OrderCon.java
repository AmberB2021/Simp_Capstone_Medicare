package pageObjects;

import static org.testng.Assert.assertEquals;
import static utility.ExtentTestManager.getTest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.Status;
import utility.GenerateReport;

public class OrderCon extends BasePage {

	public OrderCon(ChromeDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	By SuccessBanner = By.xpath("//div[@class='alert alert-success']/h3");
	By continueShoppingButton = By.xpath("//a[contains(text(),'Continue')]");

	
	public OrderCon validateOrderConfirmed() {
		GenerateReport.info("Validate order is confirmed..");
		getTest().log(Status.INFO, "Order is confirmed");
		
		assertEquals(readText(SuccessBanner), "Your Order is Confirmed!!");
		return this;
	}
	
	public LandingPage continueShopping() {
		GenerateReport.info("Return to shopping");
		
		click(continueShoppingButton);
		return new LandingPage(driver);
	}	
	
	@Override
	public OrderCon sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}

}
