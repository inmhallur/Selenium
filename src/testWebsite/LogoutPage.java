package testWebsite;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage {
	
	private DriverWait wait;
	private String successfulMsg = "You've successfully signed out of your Walmart.com account.";
	private String msgElemnet = "//div[@class='signout-wrapper']/h3";
	/**
	 * Constructor
	 * @param driver
	 */
	public LogoutPage(WebDriver driver){
		wait = new DriverWait(driver);
	}
	
	public void LogoutPagePresent(){
		WebElement msgEle = wait.findElement(By.xpath(msgElemnet));
		Assert.assertEquals(successfulMsg, msgEle.getText());
	}

}
