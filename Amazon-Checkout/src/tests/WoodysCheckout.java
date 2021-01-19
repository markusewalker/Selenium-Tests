/*
 * Created By: Markus Walker
 * Date Modified: 1/18/2021
 * 
 * Description: Selenium script written in Java to go to Amazon and search for Woody beard products. Once found,
 * 				add the items to an Amazon cart and proceed to checkout.
 */

package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;			

public class WoodysCheckout {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setup() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--ignore-certificate-errors");
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		// BROWSER VARIABLE DECLARATION/INITIALIZATION SECTION
		System.setProperty("webdriver.chrome.driver", "/home/markus/selenium/chromedriver");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver,20);
		keyboard = new Actions(driver);
		driver.manage().window().maximize();
		
		String url = "https://amazon.com";
		driver.get(url);
	}
	
	@Test(priority=0, alwaysRun=true, description="Search for Woody's beard oil in the Amazon product search bar")
	public void searchForOil() throws InterruptedException {
		// Wait until the search bar loads before attempting to click and type into it.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

		// Search for Woody's Beard oil in the search bar.
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Woody's Quality Grooming for Men Beard Oil, 1 oz. (Set of 2)");
		search.sendKeys(Keys.ENTER);
				
		// Select the appropriate item.
		driver.findElement(By.linkText("Woody's Quality Grooming for Men Beard Oil, 1 oz. (Set of 2)")).click();
	}
	
	@Test(priority=1, alwaysRun=true, description="Add the beard oil into the item cart")
	public void addOil() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
		driver.findElement(By.id("add-to-cart-button")).click();
	}
	
	@Test(priority=2, alwaysRun=true, description="Search for Woody's beard conditioner in the Amazon product search bar")
	public void searchForConditioner() throws InterruptedException {
		// Wait until the search bar loads before attempting to click and type into it.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

		// Search for Woody's Beard oil in the search bar.
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Woody's 2-in-1 Beard Conditioner for Men, Face Moisturizer & Beard Conditioner, 4 oz, 2-Pack");
		search.sendKeys(Keys.ENTER);
		
		// Select the appropriate item.
		driver.findElement(By.linkText("Woody's 2-in-1 Beard Conditioner for Men, Face Moisturizer & Beard Conditioner, 4 oz, 2-Pack")).click();
	}
	
	@Test(priority=3, alwaysRun=true, description="Add the beard conditioner into the item cart")
	public void addConditioner() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
		driver.findElement(By.id("add-to-cart-button")).click();
	}
	
	@Test(priority=4, alwaysRun=true, description="View the current items in the item cart")
	public void checkCart() {
		driver.findElement(By.xpath("//*[@id=\"hlb-view-cart-announce\"]")).click();
	}
	
	@AfterTest
	public void cleanup() throws InterruptedException {
		TimeUnit.SECONDS.sleep(40);
		driver.quit();
	}
}
