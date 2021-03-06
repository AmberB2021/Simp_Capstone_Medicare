package pageObjects;

import static org.testng.Assert.assertEquals;
import static utility.ExtentTestManager.getTest;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import utility.GenerateReport;

public class LandingPage extends BasePage {

	public LandingPage(ChromeDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	By logonName = By.id("dropdownMenu1");
	By logoutButton = By.id("logout");
	
	By manageProductNavMenu = By.xpath("//*[@id='manageProduct']/a");
	By viewProductNavMenu = By.xpath("//*[@id='listProducts']/a");

	By productShowLengthSelection = By.xpath("//select[@name='productListTable_length']");


	@FindBy(xpath= "//table/tbody/tr[@role='row']")
	List<WebElement> ProductListings;

	@FindBy(xpath= "//table/tbody/tr[@role='row']/td[2]")
	List<WebElement> ProdList_Name;

	@FindBy(xpath="//table/tbody/tr[@role='row']/td[6]/a[2]")
	List<WebElement> ProdList_AddToCart;
	
	
	public LandingPage clickViewProductsMenu( ) {
		GenerateReport.info("Going to click View Products nagivation menu");
		click(viewProductNavMenu);
		return this;
	}
	
	public LandingPage verifyValidUserLogin(String fname) {
		GenerateReport.info("Verifying valid user login");
		waitVisibility(logonName);
		assertEquals(readText(logonName), fname);
		return this;
	}

	public LandingPage verifyValidAdminLogin(String fname) {
		GenerateReport.info("Verifying valid admin login");
		waitVisibility(logonName);
		assertEquals(readText(logonName), fname);
		
		waitVisibility(manageProductNavMenu);
		assertEquals(readText(manageProductNavMenu), "Manage Product");
		return this;
	}
	
	public LandingPage showAllEntries() {
		GenerateReport.info("Select show ALL entries of product");
		
		pickSelection(productShowLengthSelection, "ALL");
		return this;
		
	}
	
	public CartPage transitToCartPage() {
		GenerateReport.info("Transit to Cart Page..");
		return new CartPage(driver);
	}
	
	public LandingPage addProductToCart(String product) {
		
		GenerateReport.info("Going to add [" + product + "] to cart..");
		getTest().log(Status.INFO, "Add [" + product + "] to cart");

		boolean matchedFlag = false;

		int totalProductListed = ProductListings.size();

		if ( totalProductListed > 0 ) {

			for ( int i = 0; i < totalProductListed; i++) {

				String pName = ProdList_Name.get(i).getText();

				GenerateReport.debug("addProductToCart()::iterating..[" + i + "] - " + pName);

				
				if ( pName.equals(product) ) {
					
					try {
						Thread.sleep(1000);
						ProdList_AddToCart.get(i).click();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
					GenerateReport.debug("Product [" + product + "] found & added to cart!");
					
					//http://localhost:8085/medicare1/cart/show?result=added

					String ExpectedAddedURL = super.getAppURL() + "cart/show?result=added";

					GenerateReport.debug("ExpectedAddedURL [" + ExpectedAddedURL + "]");
					GenerateReport.debug("DriverCurrentURL [" + driver.getCurrentUrl() + "]");

					try {

						assertEquals(driver.getCurrentUrl(), ExpectedAddedURL);
						GenerateReport.info("Added Product [" + product + "] to cart!");
						Thread.sleep(2000);

						matchedFlag = true;
						
						return this;

					} catch(Throwable e) {
						GenerateReport.error("FAILED to add Product [" + product + "] to cart!");
						Assert.fail();
					}

					break;
				}

			}
		} 
		else {
			GenerateReport.error("No product listed!");
			Assert.fail();
		}			


		if ( !matchedFlag ) {
			GenerateReport.error("Product [" + product + "] not found! Validation FAILED!");
			Assert.fail();
		}		
		
		return this;
	
	}

	public void logoutAction() {
		GenerateReport.info("Going to logout");
		
		click(logonName);
		click(logoutButton);
	}

	@Override
	public LandingPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}

}
