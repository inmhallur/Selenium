package testWebsite;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {
	
	private WebDriver driver;
	private DriverWait wait;
	
	private String resultContainer = "//*[@id='tile-container']";
	private String ithItemSelected = "//*[@id='tile-container']/div[%1$s]//h4/a";
	
	/**
	 * Constructor
	 * @param driver
	 */
	public SearchResultPage(WebDriver driver){
		//this.driver = driver;
		wait = new DriverWait(driver);
	}
	
	public String selectItemByOrder(int index){
		String itemName;
		WebElement Container = wait.findElement(By.xpath(resultContainer));		
		
		//fail test if no results found
		Assert.assertTrue("Invalid Search", Container.isDisplayed());
		WebElement findItem = wait.findElement(By.xpath(String.format(ithItemSelected, String.valueOf(index))));
		//get the name of selected item
		itemName = findItem.getText();
		findItem.click();
		wait.waitForPageToLoad();
		return itemName;
	}

}
