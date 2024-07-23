package Testcases;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Login_HRM {		
	@SuppressWarnings("unused")
	@Test(priority = 0)
	public void login_test() throws IOException, InterruptedException
	 {
		try {
			Properties props; 
			WebDriver driver; 
				//Reading properties file
				FileReader reader = new FileReader("E:\\Selenium with Java\\Projects\\OrangeHRM_Demo\\TestData.properties");
				props = new Properties();
				props.load(reader);
				//Initiate chrome_driver
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			
			//Launching web page demo Orange HRM
			 driver.get(props.getProperty("aut_Url"));
			 WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
			//User Login
			WebElement txt_user=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("txtusername"))));
			txt_user.sendKeys(props.getProperty("in_Username"));
			
			// Decoding encrypted password and passing into the application
			byte[] pw_decrypt=Base64.getDecoder().decode((props.getProperty("in_Password")));
			String pw_decrypt_string = new String(pw_decrypt, StandardCharsets.UTF_8);
			WebElement txt_password=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("txtpassword"))));
			txt_password.sendKeys(pw_decrypt_string);

			WebElement btnLogin=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("btn_Login"))));
			btnLogin.click();
			Assert.assertEquals(driver.getTitle(),"OrangeHRM");
			driver.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
}
}
