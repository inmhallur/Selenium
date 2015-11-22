package testWebsite;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopItemDetailsPage {

	private WebDriver driver;
	private DriverWait wait;
	
	private String itemHeader = "//h1[@itemprop='name']//span";
	private String addToCartButton = "//button[@id='WMItemAddToCartBtn' and @type='button']";
	private String checkOutBtn = "//div[contains(@class, 'Modal')]//a[@id='PACCheckoutBtn']";
	
	/**
	 * Constructor
	 * @param driver
	 * @param itemName - the item that is selected for detail
	 */
	public ShopItemDetailsPage(WebDriver driver, String itemName){
		//this.driver = driver;
		wait = new DriverWait(driver);
		WebElement pageHeader = wait.findElement(By.xpath(itemHeader));
		Assert.assertEquals(itemName, pageHeader.getText());
	}
	
	
	/**
	 * click "Add to cart" button 
	 */
	public void addItemToCart(){
		WebElement cartBtn = wait.findElement(By.xpath(addToCartButton));
		cartBtn.click();
		wait.waitForPageToLoad();
	}
	
	/**
	 * Go to check out page from the popup window
	 */
	public void checkOutItems(){
		WebElement checkOutButton = wait.findElement(By.xpath(checkOutBtn));
		checkOutButton.click();
		wait.waitForPageToLoad();
	}
	
}
