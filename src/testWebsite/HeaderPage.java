package testWebsite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HeaderPage {
	
	private WebDriver driver;
	private DriverWait wait;
	
	//Search section
	private String searchBox = "//input[@id='search' and @type='text']";
	private String searchButton = "//button[contains(@class, 'searchbar-submit') and @type='submit']";
	
	//My Account
	private String MyAccount = "//a[contains(@class, 'dropdown-link') and contains(., 'My Account')]";
	private String MyAccount_Options = "//li/a[@class='js-sign-out' and contains(., '%1$s')]";
	
	/**
	 * Constructor
	 * @param driver
	 */
	public HeaderPage(WebDriver driver){
		this.driver = driver;
		wait = new DriverWait(driver);
	}
	
	
	public enum AccountOptions{
		SIGN_OUT("Sign Out");
		
		private String value;
		
		private AccountOptions(String value){
			this.value = value;
		}
		
		public String toString(){
			return value;
		}
		
	}
	
	/**
	 * pass the query that is entered for search
	 * @param query
	 */
	public void searchItem(String query){
		//specify item in the search box
		WebElement search= wait.findElement(By.xpath(searchBox));
		search.sendKeys(query);
		//submit the search
		WebElement submitSearch = wait.findElement(By.xpath(searchButton));
		submitSearch.click();
		wait.waitForPageToLoad();
	}
	
	/**
	 * hover over "My Account" section and select an item to perform
	 * @param value
	 */
	public void selectionFromMyAccount(String value){
		WebElement myAccount = wait.findElement(By.xpath(MyAccount));
		Actions action = new Actions(driver);
		//hover over the "My Account" link
		action.moveToElement(myAccount).build().perform();
		
		//click on the specified value 
		WebElement option = wait.findElement(By.xpath(String.format(MyAccount_Options, value)));
		option.click();
		wait.waitForPageToLoad();
	}

}
