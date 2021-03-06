package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.GenerateReport;

public class HomePage extends BasePage {

	public HomePage(ChromeDriver driver ) {
		
		super(driver);
	}

	By medicareNavMenu = By.className("navbar-brand");
	
	By aboutNavMenu = By.id("about");
	By contactNavMenu = By.id("contact");
	By viewProductNavMenu = By.id("listProducts");

	By loginNavMenu = By.xpath("//*[@id='login']/a");
	By logoutNavMenu = By.id("logout");
	By signNavMenu = By.id("signup");


	public HomePage goToMedicare() {
		GenerateReport.info("Opening Medicare website..");
		driver.get(appURL);
		return this;
	}
	
	public LoginPage goToLoginPage() {
		GenerateReport.info("Going to Login Page..");
		click(loginNavMenu);
		return new LoginPage(driver);
	}
	
	@Override
	public HomePage sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	
}

}
