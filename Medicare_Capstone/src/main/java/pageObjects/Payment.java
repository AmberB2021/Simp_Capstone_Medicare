package pageObjects;

import static org.testng.Assert.assertEquals;
import static utility.ExtentTestManager.getTest;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import utility.GenerateReport;

public class Payment extends BasePage {

	public Payment(ChromeDriver driver) {
		super (driver);
		PageFactory.initElements(driver, this);
	}
	
	By PaymentPageVal = By.xpath("//*[@class='panel-title']");
	
	By FinalPaymentSum = By.xpath("//span[@class='badge pull-right']");
	
	By PayButton = By.xpath("//a[@role='button' and contains(text(), 'Pay')]");
	
	@FindBy(xpath="//h3[contains(text(),'Grand Total')]")
	List<WebElement> GrandTotals;
	
	By cardNumberField = By.xpath("//input[@id='cardNumber']");
	By expMonthField = By.xpath("//input[@id='expityMonth']");	// take note the developer of medicare website got typo error
	By expYearField = By.xpath("//input[@id='expityYear']");
	By cvCodeField = By.xpath("//input[@id='cvCode']");
	
	
	public Payment isPaymentPage()  {
		GenerateReport.info("Validate is Payment Page.. ");
		assertEquals(readText(PaymentPageVal), "Payment Details");
		return this;
	}
		
	public Payment valFinalPaymentSum() {
		
		GenerateReport.info("Going to validate final payment sum..");
		getTest().log(Status.INFO, "validate final payment sum");
		
		float finalSum = 0.0f ;
		
		for (WebElement item : GrandTotals ) {
			
			String priceString = item.getText();
			String p2 = priceString.replaceAll("Grand Total - ₹ ", "");
			String p3 = p2.replaceAll("/-", "");
			
			finalSum += Float.parseFloat(p3);
			
			GenerateReport.debug("valFinalPaymentSum():: price [" + p3 + "]" );
			
		}
		
		GenerateReport.debug("valFinalPaymentSum():: final sum computed [" + finalSum + "]");
		GenerateReport.debug("valFinalPaymentSum():: final sum shown on page [" + readText(FinalPaymentSum) + "]");
		
		String computedFinalSum = "₹ " + finalSum + "/-";
		
		assertEquals(computedFinalSum,readText(FinalPaymentSum));
		
		return this;
	}	

	
	public Payment insertCardDetails(String cardNo, String expMonth, String expYear, String cvCode) {
		
		GenerateReport.info("Going to insert payment card details..");
		
		writeText(cardNumberField, cardNo);
		writeText(expMonthField, expMonth);
		writeText(expYearField, expYear);
		writeText(cvCodeField, cvCode);
		
		
		return this;
		
	}
	
	public OrderCon clickPay() {
		GenerateReport.info("Going to proceed confirm payment..");
		
		click(PayButton);
		
		return new OrderCon(driver);
	}
	

@Override
	public Payment sleep(int miliseconds) {
		super.threadSleep(miliseconds);
		return this;
	}	
	

}
