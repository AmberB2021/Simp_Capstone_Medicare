package pageObjects;

import static org.testng.Assert.assertEquals;
import static utility.ExtentTestManager.getTest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.Status;

import utility.GenerateReport;

public class LoginPage extends BasePage {
	
	public LoginPage(ChromeDriver driver) {
		super(driver);
	}

	
	By alertBanner = By.xpath("//*[@class='alert alert-danger fade in']");
	By usernameField = By.id("username");
	By passwordField = By.id("password");
	By loginButton =  By.xpath("//*[@value='Login']");
	By logonName = By.id("dropdownMenu1");

	
	public LoginPage loginMedicare(String username, String password) {
		GenerateReport.info("Trying to login using [" + username + "/" + password + "]");
		getTest().log(Status.INFO, "Login via [" + username + "/" + password + "]");
		
		writeText(usernameField, username);
		writeText(passwordField, password);
		click(loginButton);
		return this;
	}

	public LoginPage verifyInvalidLogin() {
		GenerateReport.info("Verifying invalid login");
		waitVisibility(alertBanner);
		assertEquals(readText(alertBanner), "Username and Password is invalid!");
		return this;
	}
	
	public LandingPage goToLandingPage() {
		GenerateReport.info("Going to Logged-in Landing Page..");
		return new LandingPage(driver);
	}
	
	
	@Override
	public LoginPage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}
	

}
