package seleniumplus;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test3 {
	@Test
	public void testTrip() throws InterruptedException
	{

		System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(3000);
		WebElement fromCity=driver.findElement(By.id("FromTag"));
		Actions act1=new Actions(driver);
		act1.keyDown(fromCity,Keys.SHIFT).sendKeys( "hyd").pause(5000).sendKeys(Keys.ENTER).perform();
		
		WebElement toCity=driver.findElement(By.id("ToTag"));
		toCity.sendKeys("bangk");

		WebElement ul=driver.findElement(By.id("ui-id-2"));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-2"),
				By.tagName("li")));
		List<WebElement> unorderedlist=ul.findElements(By.tagName("li"));
		for(WebElement e:unorderedlist)
		{
			if(e.getText().contains("BKK"))
			{
				e.click();
				break;
			}
		}
		
		
	}

}
