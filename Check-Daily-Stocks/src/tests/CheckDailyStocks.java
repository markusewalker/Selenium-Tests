/*
 * Created By: Markus Walker
 * Date Modified: 1/18/2021
 * 
 * Description: Selenium script written in Java to look at the current stock price for the current stocks:
 * 		S&P500, Dow Jones and Nasdaq. It will take screenshots of each of their daily summaries 
 * 		and daily charts.
 */

package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;			

public class CheckDailyStocks {
	WebDriver driver;
	WebDriverWait wait;
	Actions keyboard;
	
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
		
		String url = "https://finance.yahoo.com";
		driver.get(url);
	}
	
	// Function to enable taking screenshots.
	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		// Convert WebDriver object to TakeScreenshot...
		TakesScreenshot scr_shot = ((TakesScreenshot)webdriver);
		
		// Create image file, move to new destination and copy file at the destination...
		File scr_file = scr_shot.getScreenshotAs(OutputType.FILE);
		File dest_file = new File(fileWithPath);
		FileUtils.copyFile(scr_file, dest_file);
	}
	
	@Test(priority=0, alwaysRun=true, description="Search for the S&P 500 page, print a view of the summary and chart page.")
	public void checkSP500() throws Exception {
		// Wait until search bar appears on page before searching S&P 500 stock.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yfin-usr-qry")));
		
		WebElement search = driver.findElement(By.id("yfin-usr-qry"));
		search.sendKeys("S&P500");
		search.sendKeys(Keys.ENTER);
		
		// Wait a bit and then take screenshot of the S&P 500 summary page.
		TimeUnit.SECONDS.sleep(3);
		//Edit where image gets saved!
		CheckDailyStocks.takeSnapShot(driver, "/home/markus/Downloads/S&P500-Summary.png");
		
		driver.findElement(By.xpath("//*[@id=\"quote-nav\"]/ul/li[2]/a")).click();
		
		// Scroll down a little bit and then take a screenshot of the S&P 500 chart.
		TimeUnit.SECONDS.sleep(3);
		for (int i = 0; i < 10; i++)
			keyboard.sendKeys(Keys.ARROW_DOWN).perform();
	
		//Edit where image gets saved!
		CheckDailyStocks.takeSnapShot(driver, "/home/markus/Downloads/S&P500-Chart.png");
		TimeUnit.SECONDS.sleep(3);
	}
	
	@Test(priority=1, alwaysRun=true, description="Search for the Dow Jones page, print a view of the summary and chart page.")
	public void checkDowJones() throws Exception {
		WebElement search = driver.findElement(By.id("yfin-usr-qry"));
		search.sendKeys("Dow Jones");
		search.sendKeys(Keys.ENTER);
		
		// Wait a bit and then take screenshot of the Dow Jones summary page.
		TimeUnit.SECONDS.sleep(3);
		//Edit where image gets saved!
		CheckDailyStocks.takeSnapShot(driver, "/home/markus/Downloads/DJI-Summary.png");
				
		driver.findElement(By.xpath("//*[@id=\"quote-nav\"]/ul/li[2]/a")).click();
				
		// Scroll down a little bit and then take a screenshot of the Dow Jones chart.
		TimeUnit.SECONDS.sleep(2);
		for (int i = 0; i < 10; i++)
			keyboard.sendKeys(Keys.ARROW_DOWN).perform();

		//Edit where image gets saved!
		CheckDailyStocks.takeSnapShot(driver, "/home/markus/Downloads/DJI-Chart.png");
		TimeUnit.SECONDS.sleep(3);
	}
	
	@Test(priority=2, alwaysRun=true, description="Search for the Nasdaq page, print a view of the summary and chart page.")
	public void checkNasdaq() throws Exception {
		WebElement search = driver.findElement(By.id("yfin-usr-qry"));
		search.sendKeys("Nasdaq");
		search.sendKeys(Keys.ENTER);
		
		// Wait a bit and then take screenshot of the Nasdaq summary page.
		TimeUnit.SECONDS.sleep(3);
		//Edit where image gets saved!
		CheckDailyStocks.takeSnapShot(driver, "/home/markus/Downloads/Nasdaq-Summary.png");
				
		driver.findElement(By.xpath("//*[@id=\"quote-nav\"]/ul/li[2]/a")).click();
				
		// Scroll down a little bit and then take a screenshot of the Nasdaq chart.
		TimeUnit.SECONDS.sleep(2);
		for (int i = 0; i < 10; i++)
			keyboard.sendKeys(Keys.ARROW_DOWN).perform();
				
		//Edit where image gets saved!
		CheckDailyStocks.takeSnapShot(driver, "/home/markus/Downloads/Nasdaq-Chart.png");
	}	
	
	@AfterTest
	public void cleanup() throws InterruptedException {
		TimeUnit.SECONDS.sleep(40);
		driver.quit();
	}
}
