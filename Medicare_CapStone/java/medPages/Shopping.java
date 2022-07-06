package medPages;

import org.testng.annotations.Test;

import medRunner.Runner;

public class Shopping extends Base {

	
	@Test(enabled=true, 
			priority = 3, 
			dataProvider= "buy2Products",
			dataProviderClass = StaticDataProvider.class,
			groups= {"addProductToCart"},
			description="add Product to cart")
	public void buy2ProductsTest(String uname, String pass, String productName1, String productName2, Method method) throws InterruptedException {		
		startTest(method.getName(), "Buy 2 Products Test Scenario");
		homePage
			.goToMedicare()
			.goToLoginPage()
			.loginMedicare(uname, pass)
			.sleep(1000)
			.goToLandingPage()
			.clickViewProductsMenu()
			.showAllEntries()
			.addProductToCart(productName1)
			.transitToCartPage()
			.sleep(1000)
			.clickContinueShopping()
			.sleep(1000)
			.addProductToCart(productName2)
			.transitToCartPage()
			.clickCheckout()
			.isShippingPage()
			.selectFirstAddress()
			.isPaymentPage()
			.valFinalPaymentSum()
			.insertCardDetails("4250-0000-7894-1114", "10", "10", "784")
			.clickPay()
			.validateOrderConfirmed()
			.continueShopping()
			.logoutAction();
			
	}
}