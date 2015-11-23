package testWebsite;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AddAndRemoveItemTest_Chrome {
	
		private WebDriver driver;
		private String baseUrl;
		private DriverWait wait;
		
		private String searchItem = "tv";
		private int itemIndex = 1;
		private String userName = "jiangshaorui@yahoo.com";
		private String pwd = "12qwaszx!@";
		private int totalItems = 1;
		
		//customer address info
		private String address1 = "850 Cherry Ave";
		private String city = "San Bruno";
		private String state = "CA";
		private String zipCode = "94066";
		
		@Before
		public void setUp() throws Exception{						
			System.setProperty("webdriver.chrome.driver", "/Users/shaoruijiang/Downloads/chromedriver.exe");
			driver = new ChromeDriver();
			baseUrl = "http://www.walmart.com/";
			driver.manage().window().maximize();
			wait = new DriverWait(driver);
			
		}
		
		@Test
		public void test() throws Exception{
			//go to walmart.com
			driver.get(baseUrl);
			//search item from search section
			HeaderPage hp = new HeaderPage(driver);
			hp.searchItem(searchItem);
			
			//on the result page, select the 1st available item and save the name
			SearchResultPage sr = new SearchResultPage(driver);
			String name = sr.selectItemByOrder(itemIndex);
			
			//go to next page - item detail
			ShopItemDetailsPage shopDetail = new ShopItemDetailsPage(driver, name);
			shopDetail.addItemToCart();
			shopDetail.checkOutItems();
						
			//login page before checkout and add customer info
			Customer customer = new Customer(userName, pwd);
			customer.setAddress1(address1);
			customer.setCity(city);
			customer.setState(state);
			customer.setZipCode(zipCode);
			CheckOutLogInPage login = new CheckOutLogInPage(driver);
			login.signIn(customer);
			
			//check out page
			CheckOutMainPage checkout = new CheckOutMainPage(driver);
			
			//go to shopping cart page from checkout page
			checkout.navToCartPage();
			ShopCartPage cart = new ShopCartPage(driver);
			//assert item is present
			cart.assertItemPresent(name);
			//total number of items is correct
			Assert.assertEquals(totalItems, cart.getTotalItem());
			cart.goCheckout();

			//reload check out page
			checkout = new CheckOutMainPage(driver);
			//select shipping method
			checkout.chooseShippingMethod(CheckOutMainPage.ShipMethod.FREE.toString());			
			//select home shipping address and continue
			checkout.selectPreferredAddress(customer);		
			//load payment section and assert is displayed
			checkout.assertPaymentSectionDisplay();
			
			//go back to cart page and remove the item
			checkout.navToCartPage();
			cart = new ShopCartPage(driver);
			cart.removeItemByName(name);			
			//assert empty cart
			Assert.assertEquals(0, cart.getTotalItem());
			
			//log out from the site
			hp = new HeaderPage(driver);
			hp.selectionFromMyAccount(HeaderPage.AccountOptions.SIGN_OUT.toString());
			LogoutPage lg = new LogoutPage(driver);
			lg.LogoutPagePresent();
			
		
		}
		
		@After
		public void tearDown() throws Exception{
			
			driver.quit();
		}

}