package seleniumplus;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2 {
	@Test
	public void testDragAndDrop() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demos.telerik.com/aspnet-ajax/treeview/examples/overview/defaultcs.aspx");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		WebElement pack1=driver.findElement(By.xpath
		("//div[@id='ctl00_ContentPlaceholder1_RadTreeView1']/ul/li/ul/li[3]/ul/li[1]/div/div/span"));
		WebElement price= driver.findElement(By.id("ctl00_ContentPlaceholder1_priceChecker"));

		Actions act1=new Actions(driver);
		//act1.dragAndDrop(pack1, price).perform();
		act1.clickAndHold(pack1).pause(2000).release(price).perform();
		//Thread.sleep(10000);

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementWithText
				(By.id("ctl00_ContentPlaceholder1_Label1"),"Drop a package here to check price"));

		String label=driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText();
		System.out.println(label);
		Assert.assertTrue(label.endsWith("$3999"));
	}

}
