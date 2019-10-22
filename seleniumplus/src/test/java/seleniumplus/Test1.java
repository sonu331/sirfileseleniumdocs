package seleniumplus;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {
	
	@Test(groups = {"tag1"})
	public void testCreditCards() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://newsite.hdfcbank.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		  Actions act1=new Actions(driver);
		  act1.moveToElement(driver.findElement(By.xpath("//a[@title='Pay']"))).perform
		  ();
		 
		driver.findElement(By.xpath("//a[@href='/personal/pay/cards/credit-cards']")).click();
		String titlePage=driver.getTitle();
		Assert.assertTrue(titlePage.contains("Credit Cards"));
	}

}
