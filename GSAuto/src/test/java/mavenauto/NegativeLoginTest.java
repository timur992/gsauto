package mavenauto;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NegativeLoginTest {
	//change from gsauto project
	
	// driver is a Class Level variable and its empty
	WebDriver driver;
	
	@BeforeMethod// -- will be run before each test method.
	public void setUp() throws InterruptedException {
		// System variables need to know what type of the driver we are using and the
		// path to the driver
		System.setProperty("webdriver.chrome.driver",
				"drivers\\chromedriver.exe");

		// instantiate and initialized a webdriver variable which is Chrome
		driver = new ChromeDriver();
		
		String urlLoginPage = "https://the-internet.herokuapp.com/login";

		driver.get(urlLoginPage);

		Thread.sleep(2000);
	}
	
	@AfterMethod // -- will be run after each test method.
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	
	@Test
	public void loginWithIncorrectPasswordTest() throws InterruptedException {
		
		WebElement usernameBox = driver.findElement(By.id("username"));
		usernameBox.sendKeys("tomsmith");

		Thread.sleep(2000);

		WebElement passwordBox = driver.findElement(By.xpath("//input[@name='password']"));
		passwordBox.sendKeys("qwerty");// wrong password

		Thread.sleep(2000);

		WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));
		loginButton.click();

		Thread.sleep(4000);

		String expectedMessage = "Your password is invalid!";
		WebElement actualMessageFlashed = driver.findElement(By.xpath("//div[@id='flash']"));
		String actualMessageDisplayed = actualMessageFlashed.getText();

		assertTrue(actualMessageDisplayed.contains(expectedMessage));// contains all the characters besides x

	}
}
