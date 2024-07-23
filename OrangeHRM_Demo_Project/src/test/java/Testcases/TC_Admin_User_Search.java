package Testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileReader;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Admin_User_Search {
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
		     driver.manage().deleteAllCookies();
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
		    AssertJUnit.assertEquals(driver.getTitle(),"OrangeHRM");
	 }
	@SuppressWarnings("unused")
	@Test(priority = 2)
	public void ApproveTS() throws InterruptedException {
		WebElement Admin=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("lnk_Admin"))));
		Admin.click();
		Thread.sleep(1000);
		WebElement User_Mgmt=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("optn_User_Mgmt"))));
		User_Mgmt.click();
		Thread.sleep(1000);
		WebElement User=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("mnu_User"))));
		User.click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath(props.getProperty("dt_user_tbl"))).isDisplayed());
		//User search 
		//Refine search with User role as Admin
		WebElement User_Role=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("drpdwn_User_Role"))));
		User_Role.sendKeys("Admin");
		WebElement Search=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("btn_Search"))));
		Search.click();
		Thread.sleep(1000);
		boolean dtUser_Verify;
		dtUser_Verify=driver.findElement(By.xpath(props.getProperty("dt_user_tbl"))).isDisplayed();
		if(dtUser_Verify=true) {
			System.out.println("User serch with Admin role have Data");			
		}else 
		{
			System.out.println("User serch with Admin role No Data Available");		
		}
		WebElement Reset=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("btn_Reset"))));
		Reset.click();
		
		//User search 
		//Refine search with User role as  ESS
		User_Role.sendKeys("ESS");
		Search.click();
		Thread.sleep(1000);
		dtUser_Verify=driver.findElement(By.xpath(props.getProperty("dt_user_tbl"))).isDisplayed();
		if(dtUser_Verify=true) {
			System.out.println("User serch with ESS role have Data");			
		}else 
		{
			System.out.println("User serch with ESS role No Data Available");		
		}
		Reset.click();
		Thread.sleep(1000);
		//User search 
		//Refine search with Status role as  Enabled
		WebElement Status=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("drpdwn_status"))));
		Status.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		Status.sendKeys(Keys.ENTER);
		Search.click();
		Thread.sleep(1000);
		dtUser_Verify=driver.findElement(By.xpath(props.getProperty("dt_user_tbl"))).isDisplayed();
		if(dtUser_Verify=true) {
			System.out.println("User serch with User Status Enabled have Data");			
		}else;
		{
			System.out.println("User serch with User Status Enabled, No Data Available");		
		}
		Reset.click();
		//User search 
		//Refine search with Status role as  Disabled
		//Status.sendKeys("Disabled");
		Status.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		Status.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		Status.sendKeys(Keys.ENTER);
		Search.click();
		Thread.sleep(1000);
		dtUser_Verify=driver.findElement(By.xpath(props.getProperty("dt_user_tbl"))).isDisplayed();
		if(dtUser_Verify=true) {
			System.out.println("User serch with User Status Disabled have Data");			
		}else 
		{
		System.out.println("User serch with User Status Disabled, No Data Available");		
		}
		Reset.click();
driver.close();	
}
}