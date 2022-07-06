package utility;

import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.Status;
import static utility.ExtentTestManager.getTest;

import medPages.Base;

public class Med_Listeners extends Base implements ITestListener {
		

		public Med_Listeners(ChromeDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

		public static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getMethod().getConstructorOrMethod().getName();
	    }
		
	    @Override
	    public void onStart(ITestContext iTestContext) {
	    	GenerateReport.info("I am in onStart method " + iTestContext.getName());
	        iTestContext.setAttribute("WebDriver", this.driver);
	    }
	    
	    @Override
	    public void onFinish(ITestContext iTestContext) {
	    	GenerateReport.info("Finish method " + iTestContext.getName());
	       
	        ExtentManager.extentReports.flush();
	    }
	    
	    @Override
	    public void onTestStart(ITestResult iTestResult) {
	    	GenerateReport.info("Test [" + getTestMethodName(iTestResult)+ "] is starting...");
	    }
	    
	    @Override
	    public void onTestSuccess(ITestResult iTestResult) {
	    	GenerateReport.info("Test [" + getTestMethodName(iTestResult) + "] is succeed!");
	        getTest().log(Status.PASS, "Test passed");
	    }
	    
	    @Override
	    public void onTestFailure(ITestResult iTestResult) {
	    	GenerateReport.info(getTestMethodName(iTestResult) + "::" + " test is failed.");
	        Object testClass = iTestResult.getInstance();
	        ChromeDriver driver = (ChromeDriver) ((Base) testClass).getDriver();
	        String base64Screenshot =
	            "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
	        getTest().log(Status.FAIL, "Test Failed",
	            getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
	    }
	    
	    @Override
	    public void onTestSkipped(ITestResult iTestResult) {
	    	GenerateReport.info(getTestMethodName(iTestResult) + "::" +  " test is skipped.");
	        getTest().log(Status.SKIP, "Test Skipped");
	    }
	    
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	    	GenerateReport.info("Test failed" + getTestMethodName(iTestResult));
	    }
	}

