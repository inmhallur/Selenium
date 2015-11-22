package testWebsite;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class DriverWait {
	
	private static long defaultTimeOut = 60;
	private WebDriver driver;
	
	/**
	 * Class Constructor
	 * @param driver
	 */
	
	public DriverWait(WebDriver webdriver){
		driver = webdriver;
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	
	public WebElement findElement(final By locator){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			    .withTimeout(30, TimeUnit.SECONDS)
			    .pollingEvery(5, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() 
			{
			  public WebElement apply(WebDriver driver) {
				  return driver.findElement(locator);
			}
			});
		return element;
	}
	
	public WebElement isVisible(final By locator){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;		
	}
	
	
	/**
	 * Wait for page to load completely
	 */
	public void waitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeOut);

		try {
			final String oldPage = driver.getPageSource();
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver d) {
					if (!(d instanceof JavascriptExecutor)) {
						return true;
					}
					Object result = ((JavascriptExecutor) d)
							.executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
					String newPage = driver.getPageSource();
					if (result != null && result instanceof Boolean && (Boolean) result&&(!oldPage.equals(newPage))) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}