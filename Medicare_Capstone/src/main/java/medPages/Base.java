package medPages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import utility.GenerateReport;


public class Base {

	public RemoteWebDriver Rdriver;
	public ChromeDriver driver;
	public Base(ChromeDriver driver) {
	}

	public ChromeDriver getDriver() { return driver; }
	

	public static boolean gridFlag=false;
	public static boolean testLoginOnlyFlag=false;

	SoftAssert soft = new SoftAssert();
	
	public static XSSFWorkbook wbook;
	public static XSSFSheet sheet;

	
	public HomePage homePage;
	public LandingPage landingPage;

	@BeforeClass(enabled=true, alwaysRun = true, description="Setup the webdriver, either local or grid")
	@Parameters({"headlessFlag", "loginOnlyFlag"})
	public void classLevelSetup(String headlessFlag, String loginOnlyFlag) throws MalformedURLException, IOException {
		
		GenerateReport.info("Starting test!");

		if ( loginOnlyFlag.equalsIgnoreCase("TRUE") ) {
			testLoginOnlyFlag = true;
		}
		
		GenerateReport.debug("testLoginOnlyFlag is [" + testLoginOnlyFlag + "]" );
		
		gridFlag=true;
		
		if (gridFlag) {
			
			String sURL = "http://localhost:8085/medicare";

			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.LINUX);
			cap.setBrowserName("chrome");
			

	        ChromeOptions options = new ChromeOptions();
	        
	        if ( headlessFlag.equalsIgnoreCase("TRUE")) {
	        	options.addArguments("--headless");     
	        	GenerateReport.info("Setup():: Running in headless chrome mode.");
	        }
	        options.addArguments("--disable-gpu");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--no-sandbox");		
	        options.addArguments("--window-size=1400,800");  

	        cap.setCapability(ChromeOptions.CAPABILITY, options);
			
			Rdriver = new RemoteWebDriver(new URL(sURL), cap);
			driver = (ChromeDriver) Rdriver;
			
			cap =  new DesiredCapabilities();
			
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.LINUX);
			

		}
		else {
			System.setProperty("webdriver.chrome.driver","chromedriver.");
	        ChromeOptions options = new ChromeOptions();
	        
	        if ( headlessFlag.equalsIgnoreCase("TRUE")) {
	        	options.addArguments("--headless");     
	        	GenerateReport.info("Setup():: Running in headless chrome mode.");
	        }
	        options.addArguments("--disable-gpu");
	        options.addArguments("--disable-crash-reporter");
	        options.addArguments("--disable-logging");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--no-sandbox");		
	        options.addArguments("--window-size=1400,800");  
	        driver = new ChromeDriver(options);   
		
			
		}

		GenerateReport.info("WebDriver created");
	
	}
	
	@BeforeMethod
	@Parameters({"aws_host"})
	public void methodLevelSetup(String aws_host) {
		
		String medicareURL = aws_host + "/medicare/";
		
		homePage = new HomePage(driver);
		landingPage = new LandingPage(driver);
		
		homePage.setAppURL(medicareURL);
		
		GenerateReport.debug("appURL is : [" + medicareURL + "]");
		
	}
	
	@AfterClass (enabled=true, alwaysRun = true, description="Close report & driver")
	public void teardown() {
		GenerateReport.info("End of test.");

		try {
			  Thread.sleep(3000);
			  driver.quit();
		} catch (InterruptedException e) {e.printStackTrace();}			


}
}
