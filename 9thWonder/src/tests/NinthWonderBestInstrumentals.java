/*
 * Created By: Markus Walker
 * Date Modified: 1/18/2021
 * 
 * Description: Selenium script written in Java to head over to Youtube and select a 9th Wonder video of some
 * 		of his best instrumentals.
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

public class NinthWonderBestInstrumentals {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setup() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--ignore-certificate-errors");
		options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		// BROWSER VARIABLE DECLARATION/INITIALIZATION SECTION
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver,20);
		driver.manage().window().maximize();
		
		String url = "https://youtube.com";
		driver.get(url);
	}
	
	@Test(priority=0, alwaysRun=true, description="Navgiate over to Youtube and search for a video")
	public void searchForVid() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\'search\']")));
		driver.getCurrentUrl();
		
		WebElement search = driver.findElement(By.xpath("//input[@id=\'search\']"));
		search.click();
		search.sendKeys("9th Wonder the best instrumentals");
		search.sendKeys(Keys.ENTER);
	}
	
	@Test(priority=1, alwaysRun=true, description="Click on the video and enjoy the 9th Wonder of the world...")
	public void clickVid() {
		driver.findElement(By.linkText("9Th Wonder | The best instrumentals & Hip Hop Beats.")).click();
	}
	
	@AfterTest
	public void cleanup() throws InterruptedException {
		TimeUnit.MINUTES.sleep(60);
		driver.quit();
	}
}
