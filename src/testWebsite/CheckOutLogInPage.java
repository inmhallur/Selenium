package testWebsite;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutLogInPage {
	
	private String userBox = "//input[@id='COAC0WelAccntEmail']";
	private String passwordBox = "//input[@id='COAC0WelAccntPswd']";
	private String signInBtn = "//button[@id='COAC0WelAccntSignInBtn']";
	private String header = "//h1[@class='checkout-heading']";
	
	private WebDriver driver;
	private DriverWait wait;
	
	public CheckOutLogInPage(WebDriver driver){
		//this.driver = driver;
		wait = new DriverWait(driver);
		//assert page is loaded as expected
		Assert.assertEquals("Checkout", wait.findElement(By.xpath(header)).getText());
	}
	public void signIn(Customer customer){
		//specify username and password
		setExistingUserName(customer.getUserName());
		setExistingPassword(customer.getPassword());
		
		//click sign in
		clickSignIn();
	}
	
	public void setExistingUserName(String userName){
		WebElement userTbx = wait.findElement(By.xpath(userBox));
		userTbx.sendKeys(userName);
	}
	
	public void setExistingPassword(String password){
		WebElement pwdTbx = wait.findElement(By.xpath(passwordBox));
		pwdTbx.sendKeys(password);
	}
	
	public void clickSignIn(){
		WebElement signInButton = wait.findElement(By.xpath(signInBtn));
		signInButton.click();
		wait.waitForPageToLoad();
	}

}
