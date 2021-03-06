package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.GenerateReport;

public class CartPage extends BasePage {

	
	public CartPage(ChromeDriver driver) {
		super(driver);

	}
	By continueShoppingButton = By.xpath("//table[@id='cart']//a[contains(text(),'Continue')]");
	By checkoutButton = By.xpath("//table[@id='cart']//a[contains(text(),'Checkout')]");

	
	public LandingPage clickContinueShopping() {
		GenerateReport.info("Go back shopping..");
		click(continueShoppingButton);
		return new LandingPage(driver);
	}
	
	public Shipping clickCheckout() {
		GenerateReport.info("Checking out..");
		click(checkoutButton);
		return new Shipping(driver);
	}
	
	public CartPage goToCartPage() {
		GenerateReport.info("Access to Cart Page from drop down menu..");
		return new CartPage(driver);
	}
	
	@Override
	public CartPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}

}
