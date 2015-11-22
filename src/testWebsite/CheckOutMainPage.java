package testWebsite;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutMainPage {
	
	private String header = "//h1[@class='checkout-heading']";
	private WebDriver driver;
	private DriverWait wait;
	
	//item listed on the right of page
	private String items = "//div[contains(@class, 'persistent-order-summary checkout-dropdown')]//div[contains(@class, 'brick js-order-item')]";
	private String selectedItem = "//div[contains(@class, 'persistent-order-summary checkout-dropdown')]//div[contains(@class, 'brick js-order-item')][%1$s]//span[not (@class)]";
	private String expandSelectedItem = "//div[contains(@class, 'persistent-order-summary checkout-dropdown')]//div[contains(@class, 'brick js-order-item')][%1$s]//div[@class='truncate-disclosure-arrow']";
	private String quantityOnSelectedItem = "//div[contains(@class, 'persistent-order-summary checkout-dropdown')]//div[contains(@class, 'brick js-order-item')][%1$s]//div[@class='product-qty-price']/label";
	
	//shipping method section on the left of page
	private String shippingMethodTemplate = "//div[contains(@class, 'shipping-opts')]//div[contains(@class, 'radio-expanded')]//input[contains(@data-automation-id, '%1$s')]";
	private String cnt1Button = "//button[@id='COAC1ShpOptContBtn']";
	
	//selected shipping address
	private String selectedShippingAddress = "//div[@class='address-col-inner']/div[contains(@class,'selected')]";
	private String addressLine1 = "//div[@class='address-col-inner']/div[contains(@class,'selected')]//div[@class='address-details']//div[@class='js-address-line-1']";
	private String addressLine2 = "//div[@class='address-col-inner']/div[contains(@class,'selected')]//div[@class='address-details']//div[@class='js-address-line-2']";
	private String city = "//div[@class='address-col-inner']/div[contains(@class,'selected')]//div[@class='address-details']//span[@class='js-city']";
	private String state = "//div[@class='address-col-inner']/div[contains(@class,'selected')]//div[@class='address-details']//span[@class='js-state']";
	private String zipCode = "//div[@class='address-col-inner']/div[contains(@class,'selected')]//div[@class='address-details']//span[@class='js-postal-code']";
	private String cnt2Button = "//button[@id='COAC2ShpAddrContBtn']";

	//payment method section
	private String paymentSection = "//section[@class='accordion-module checkout-payment-options expanded']";
	private String shippingMethodSection = "//div[contains(@class, 'fulfillment-groups-container module expanded')]";
	//cart icon
	private String cartIcon = "//i[@class='wmicon wmicon-cart']";
	
	/**
	 * Constructor
	 * @param driver
	 */
	public CheckOutMainPage(WebDriver driver){
		wait = new DriverWait(driver);
		//assert page is loaded as expected
		wait.waitForPageToLoad();
		wait.findElement(By.xpath(shippingMethodSection));
	}
	
	public enum ShipMethod{
		FREE("VALUE"),
		STANDARD("STANDARD"),
		EXPEDITED("EXPEDITED"),
		RUSH("RUSH");
		
		private String value;
		
		private ShipMethod(String value){
			this.value = value;
		}
		
		public String toString(){
			return value;
		}
		
	}
	
	/**
	 * 
	 * @param index - specify the item among items based off order
	 */
	public int getQuantitiesOnSelectedItem(String index){
		WebElement quantityLabel = wait.findElement(By.xpath(String.format(quantityOnSelectedItem, index)));
		//return number
		return Integer.parseInt(quantityLabel.getText().split(" ")[1]);
	}
	
	/**
	 * Consider customer buys different items
	 * @return
	 */
	public int getItemsNumber(){
		
		return driver.findElements(By.xpath(items)).size();
		
	}
	
	/**
	 * Get name of selected item
	 * @param index - specify the item to get items based off order
	 * @return
	 */
	public String getSelectedItem(String index){
		//expand to display the item completely
		WebElement expandItem = wait.findElement(By.xpath(String.format(expandSelectedItem, index)));
		expandItem.click();
		
		WebElement item = wait.findElement(By.xpath(String.format(selectedItem, index)));
		return item.getText();
	}
	
	/**
	 * Choose shipping method: free, standard, expedited, rush
	 * @param value
	 */
	public void chooseShippingMethod(String value){
		WebElement method = wait.findElement(By.xpath(String.format(shippingMethodTemplate, value)));
		if(!method.isSelected()){
			method.click();
		}
		
		shippingMethodContinue();		
	}
	
	public void selectPreferredAddress(Customer customer){
		WebElement selectedSection = wait.findElement(By.xpath(selectedShippingAddress));
		//assert if the selected address is correct
		/*
		WebElement AddL1 = wait.findElement(By.xpath(addressLine1));
		WebElement City = wait.findElement(By.xpath(city));
		WebElement State = wait.findElement(By.xpath(state));
		WebElement Zip = wait.findElement(By.xpath(zipCode));
	    //assert the selected address is preferred home address
		Assert.assertTrue(customer.getAddress1().equals(AddL1.getText())
						&&customer.getCity().equals(City.getText())
						&&customer.getState().equals(State.getText())
						&&customer.getZipCode().equals(Zip.getText()));*/
		shippingAddressContinue();
	}
	
	/**
	 * click continue button to move forward after selecting shipping or pickup options
	 */
	public void shippingMethodContinue(){
		WebElement continueBtn = wait.isVisible(By.xpath(cnt1Button));
		continueBtn.click();
		wait.waitForPageToLoad();
	}
	
	
	/**
	 * click continue button to move forward after selecting shipping address
	 */
	public void shippingAddressContinue(){
		WebElement continueBtn= wait.isVisible(By.xpath(cnt2Button));
		continueBtn.click();
		wait.waitForPageToLoad();
	}
	
	/**
	 * Assert Payment Section is displayed
	 */
	public void assertPaymentSectionDisplay(){
		WebElement paymentSec = wait.findElement(By.xpath(paymentSection));
		Assert.assertTrue(paymentSec.isDisplayed());
	}
	
	public void navToCartPage(){
		WebElement cart = wait.findElement(By.xpath(cartIcon));
		cart.click();
		wait.waitForPageToLoad();
	}

}
