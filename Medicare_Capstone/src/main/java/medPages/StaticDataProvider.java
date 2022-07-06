package medPages;

import org.testng.annotations.DataProvider;

public class StaticDataProvider {

	@DataProvider(name ="invalidLogin")
	public static Object[][] param_il() {
		return new Object[][] {
			{ "dummy@simplilearn.com", "testTEST!" },
			{ "testingtesting123@simplilearn.com", "HEllOworld!" }
		};
	}
	
	@DataProvider(name ="validUserLogin")
	public static Object[][] param_ul() {
		return new Object[][] {
			{ "kn@gmail.com" , "12345!", "Test User1" }			
		};
	}

	@DataProvider(name ="validAdminLogin")
	public static Object[][] param_al() {
		return new Object[][] {
			{ "vk@gmail.com" , "admin", "Vikas Kashyap" }			
		};
	}

	@DataProvider(name ="buy2Products")
	public static Object[][] param_b2() {
		return new Object[][] {
			
			{ "kn@gmail.com" , "12345!", "Combiflame" , "Amoxicillin" }			
		};
}
}
