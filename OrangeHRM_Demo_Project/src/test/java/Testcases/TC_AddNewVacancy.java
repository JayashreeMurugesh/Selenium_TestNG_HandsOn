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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_AddNewVacancy {	
    Properties props=new Properties(); 
	WebDriver driver=new ChromeDriver();
	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
	@BeforeTest
	public void login()throws IOException, InterruptedException
	 { 
		try {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	 }
	@SuppressWarnings("unused")
	@Test(priority = 1)	
	public void AddVacancy() throws IOException, InterruptedException { 
		//Add Vacancy
		try {
			WebElement Recruit=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("lnk_Recruit"))));
			Recruit.click();
			WebElement optVacancy=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("opt_Vacancy"))));
			optVacancy.click();
			WebElement btn_AddVacancy=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("btn_Add_Vacancy"))));
			btn_AddVacancy.click();
			Thread.sleep(1000);
			Assert.assertTrue(driver.findElement(By.xpath(props.getProperty("tle_Vanacy"))).isDisplayed());
			// Adding Vacancy Details
			WebElement txtvanacyName=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("txt_vanacyName"))));
			txtvanacyName.sendKeys("Immediate Vacancy");
			WebElement drpdwnVanacyTitle=driver.findElement(By.xpath(props.getProperty("drpdwn_VanacyTitle")));
			drpdwnVanacyTitle.click();
			Thread.sleep(4000);
			drpdwnVanacyTitle.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(4000);
			drpdwnVanacyTitle.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			WebElement txtHiringMangr=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("txt_HiringMangr"))));
			txtHiringMangr.sendKeys("H");
			Thread.sleep(4000);
			txtHiringMangr.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(4000);
			txtHiringMangr.sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			WebElement txtposition=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("txt_position"))));
			txtposition.sendKeys("6");
			Thread.sleep(1000);
			WebElement SaveVacancy = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("Save_Vacancy"))));
			SaveVacancy.click();
			Thread.sleep(4000);
			Assert.assertTrue(driver.findElement(By.xpath(props.getProperty("tle_EditVanacy"))).isDisplayed());
			boolean vacancy_verify =driver.findElement(By.xpath(props.getProperty("tle_EditVanacy"))).isDisplayed();
			if (vacancy_verify=true) {
				System.out.println("Adding new vacancy functionality is working");
			}
			else {
				System.out.println("Adding new vacancy functionality is working");
			}
			driver.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
