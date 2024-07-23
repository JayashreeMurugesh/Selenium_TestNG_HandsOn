package Testcases;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Logout {
	 Properties props=new Properties(); 
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
		@BeforeTest
		public void login()throws IOException, InterruptedException
		 { 
			//Reading properties file
		    FileReader reader=new FileReader("E:\\Selenium with Java\\Projects\\OrangeHRM_Demo\\TestData.properties"); 
			props.load(reader); 
		    
			//Initiate chrome_driver
			 WebDriverManager.chromedriver().setup();	 
		     driver.manage().window().maximize(); 
		     
		     //Launching web page demo Orange HRM
		     driver.get(props.getProperty("aut_Url"));
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
		    //  
		 }
		@SuppressWarnings("unused")
		@Test(priority = 3)
		public void Logut() {
			try {
				WebElement profile=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("drpdwn_profile"))));
				profile.click();
				WebElement logut=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("mnu_logut"))));
				logut.click();
				Thread.sleep(1000);
				boolean Logout_Verify = driver.findElement(By.xpath(props.getProperty("form_Login"))).isDisplayed();
				Thread.sleep(1000);
				if(Logout_Verify=true) {
					System.out.println("User Logged out successfully");			
				}else 
				{
					System.out.println("User Log out doesn't work, No Login page available after clicking logut");		
				}
				driver.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}
