package seleniumplus;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test5 {
	WebDriver driver;
	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String value)
	{
		driver=DriverUtiltity.getDriverInstance(value);
		driver.get("http://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	@AfterTest
	public void afterTest()
	{
		driver.close();
	}

	@Test(dataProvider = "dp1" )
	public void testLoginUsers(String email,String pwd)
	{
	
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(pwd);
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());
		driver.findElement(By.linkText("Log out")).click();
		driver.findElement(By.linkText("Log in")).click();
	}
	
	@DataProvider(name="dp1")
	public Object[][] getData()
	{
	//get the data from excel and store in Object[][]	
	return ReadExcelUtility.readExcel("Sheet1");
	}

}
