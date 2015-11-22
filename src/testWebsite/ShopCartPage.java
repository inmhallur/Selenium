package testWebsite;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopCartPage {
	
	private WebDriver driver;
	private DriverWait wait;
	
	private String pageLocator = "//h3[@class='cart-list-title']";
	//total items
	private String totalItem = "//h3[@class='cart-list-title']//span";
	//remove item button
	private String removeBtn = "//div[@class='cart-item-row' and contains(., '%1$s')]//div[contains(@class, 'xs-margin-ends')]//button[@id='CartRemItemBtn']";
	//item
	private String item = "//div[@class='cart-item-row' and contains(., '%1$s')]";
	//checkout
	private String checkoutBtn = "//div[contains(@class, 'margin-ends')]//a[@id='CartChkOutBtn']";
	
	private String emptyCart = "//div[@class='cart-item-row cart-list-empty']";
	
	public ShopCartPage(WebDriver driver){
		//this.driver = driver;
		wait = new DriverWait(driver);
		//assert page is loaded as expected
		Assert.assertTrue(wait.findElement(By.xpath(pageLocator)).getText().contains("Your cart:"));
	}
	
	/**
	 * Remove item based off item name
	 * @param name
	 */
	public void removeItemByName(String name){
		//can not maximum window size on mac chrome
		//the maximum display length on mac chrome is 70 chars.So truncate if exceeding the maximum
		if(name.length()>70){
			name = name.substring(0, 70);
		}
		WebElement rmvButton = wait.findElement(By.xpath(String.format(removeBtn, name)));
		rmvButton.click();
		wait.waitForPageToLoad();
	}
	
	/**
	 * Item is displayed on the page
	 * @param name - item name
	 */
	public void assertItemPresent(String name){
		//the maximum display length on mac chrome is 70 chars.So truncate if exceeding the maximum
		if(name.length()>70){
			name = name.substring(0, 70);
		}
		WebElement Item = wait.findElement(By.xpath(String.format(item, name)));
		Assert.assertTrue(Item.isDisplayed());
	}
	
	/**
	 * get total number of items in the shopping cart
	 * @return
	 */
	public int getTotalItem(){
		WebElement total = wait.findElement(By.xpath(totalItem));
		return Integer.parseInt(total.getText().split(" ")[0]);
	}
	
	public void EmptyItemPresent(){
		WebElement emptyItem =wait.findElement(By.xpath(emptyCart));
		Assert.assertTrue(emptyItem.isDisplayed());
	}
	
	/**
	 * nav to check out page
	 */
	public void goCheckout(){
		WebElement checkOut = wait.findElement(By.xpath(checkoutBtn));
		checkOut.click();
		wait.waitForPageToLoad();
	}
	
	

}
