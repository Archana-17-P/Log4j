package Log4jExample;
/*
 * 1)LoginTest class
 * 
 * 2)log4j.properties is saved under a path : src/main/resources
 * 
 * Log4j creates 3 files that are stored under a folder log which is located in a same project GenerateLogs.
 * The three files are :  logging.log
 * 						  logging1.log
 * 						  application.log
 * 
 */
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	WebDriver driver;
	public static Logger logger = null;
	
	  @BeforeTest
	  public void beforeTest() {
		  //	String log4jPath = System.getProperty("user.dir") + "/log4j.properties";
		  //	PropertyConfigurator.configure(log4jPath);
		  	BasicConfigurator.configure();
			PropertyConfigurator.configure("log4j.properties");
		
	  }

	  @Test(priority=1)
	  public void initialization() {
		  	logger = logger.getLogger(LoginTest.class.getName());
		  	
			WebDriverManager.chromedriver().setup();
			
			
			driver = new ChromeDriver();
			logger.info("OPENING Chrome Browser");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			driver.get("https://www.echotrak.com");
			logger.info("Navigating to www.echotrak.com");  
		  
	  }
	
	  @Test(priority=2)
	  public void loginEchoTrak() throws InterruptedException {
		  
		  
		  WebElement custId = driver.findElement(By.xpath("//input[@name='txtCustomerID' and @id='txtCustomerID']"));
		  custId.sendKeys("Archana");
		  logger.info("Entering value in Username : Archana");
		  
		  
		  WebElement custPwd = driver.findElement(By.xpath("//html/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div/form/fieldset/div[2]/fieldset/input[2]"));
		  custPwd.sendKeys("apurva");
		  logger.info("Entering value in Password : apurva");
		  
		  Thread.sleep(2000);

		  driver.findElement(By.xpath("//input[@type='submit']")).click();
		  logger.info("Clicking a SUBMIT button");
		  Thread.sleep(1000);
		  
		  

	  }

	  @AfterTest
	  public void afterTest() {
		  driver.close();
		  logger.info("CLOSING Browser");
	  }



}
