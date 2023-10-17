package mavenauto;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	
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
	public void loginWithCorrectCredentialsTest() throws InterruptedException {
		//logging our actions
		Reporter.log("Starting execution of loginWithCorrectCredentialsTest", true);

		Reporter.log("set up process has finished", true);
		
		WebElement loginPageTitle = driver.findElement(By.xpath("//h2[contains(text(),'Login Page')]"));
		Reporter.log("Printing Page Title", true);
		System.out.println("Page is " + loginPageTitle.getText());
		// Checkpoint with assertion - when you are trying to compare expected with
		// actual behavior
		String expectedPage = "Login Page";
		String actualPage = loginPageTitle.getText();
		// Checking if actual behavior equals to expected
		Reporter.log("comparing actualPage vs expectedPage", true);
		assertEquals(actualPage, expectedPage, "Expected wasnt equal to Actual!!!");

		WebElement usernameBox = driver.findElement(By.id("username"));
		Reporter.log("supplying tomsmith as a username", true);
		usernameBox.sendKeys("tomsmith");

		Thread.sleep(2000);

		WebElement passwordBox = driver.findElement(By.xpath("//input[@name='password']"));
		Reporter.log("supplying SuperSecretPassword! as a password", true);
		passwordBox.sendKeys("SuperSecretPassword!");

		Thread.sleep(2000);

		WebElement loginButton = driver.findElement(By.cssSelector("#login > button"));
		Reporter.log("clicking login button", true);
		loginButton.click();

		Thread.sleep(4000);

		String expectedMessage = "You logged into a secure area!";
		WebElement actualMessageFlashed = driver.findElement(By.xpath("//div[@id='flash']"));
		String actualMessageDisplayed = actualMessageFlashed.getText();
		// checkpoint actualMessageDisplayed(larger than) should not be equal to
		// expectedMessage because of "x" at the end
		Reporter.log("comparing expectedMessage vs actualMessageDisplayed", true);
		assertFalse(expectedMessage.contains(actualMessageDisplayed));

		assertTrue(actualMessageDisplayed.contains(expectedMessage));// contains all the characters besides x
		Reporter.log("closing the browser session", true);
		
	}

}
