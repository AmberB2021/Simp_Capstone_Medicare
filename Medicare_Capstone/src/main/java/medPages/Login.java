package medPages;

import java.lang.reflect.Method;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import utility.GenerateReport;
import static utility.ExtentTestManager.startTest;

public class Login extends Base {
	

	public Login(ChromeDriver driver2) {
		super(driver2);
		// TODO Auto-generated constructor stub
	}

	@Test(enabled=true, 
			priority = 0, 
			dataProvider= "invalidLogin",
			dataProviderClass = Shopping.class,
			groups= {"invalidLoginGrp"}, 
			description="Invalid login")
	public void invalidLoginTest(String uname, String pass, Method method) throws InterruptedException {		
		
		startTest(method.getName(), "Invalid login screnario with invalid username and password.");
		homePage
			.goToMedicare()
			.goToLoginPage()
			.loginMedicare(uname, pass)
			.verifyInvalidLogin();
	}	

	@Test(enabled=true, 
			priority = 1, 
			dataProvider= "validUserLogin",
			dataProviderClass = Shopping.class,
			groups= {"validUserLoginGrp"}, 
			description="Valid user login")
	public void validUserLoginTest(String uname, String pass, String fname, Method method) throws InterruptedException {		
		
		startTest(method.getName(), "Valid user login screnario with username and password. To validate full name.");
		homePage
			.goToMedicare()
			.goToLoginPage()
			.loginMedicare(uname, pass)
			.sleep(1000)
			.goToLandingPage()
			.verifyValidUserLogin(fname);
		
		if (testLoginOnlyFlag) {
			GenerateReport.debug(" Logout");
			landingPage.logoutAction();
		}

	}		

	@Test(enabled=true, 
			priority = 2, 
			dataProvider= "validAdminLogin",
			dataProviderClass = Shopping.class,
			groups= {"validAdminLoginGrp"}, 
			description="Valid admin login")
	public void validAdminLoginTest(String uname, String pass, String fname, Method method) throws InterruptedException {		
		
		startTest(method.getName(), "Valid admin login screnario with username and password. To validate full name.");
		homePage
			.goToMedicare()
			.goToLoginPage()
			.loginMedicare(uname, pass)
			.sleep(1000)
			.goToLandingPage()
			.verifyValidAdminLogin(fname);

		if (testLoginOnlyFlag) landingPage.logoutAction();
			
	}		

}
