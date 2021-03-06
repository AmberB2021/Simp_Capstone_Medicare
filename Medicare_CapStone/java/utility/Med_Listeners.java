package utility;

import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import medPages.Base;

public class Med_Listeners extends Base implements ITestListener {
		
		private static String getTestMethodName(ITestResult iTestResult) {
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
	        WebDriver driver = ((Base) testClass).getDriver();
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

