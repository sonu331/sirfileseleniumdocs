package seleniumplus;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
public class Test8 {
	ExtentHtmlReporter reporter;
	ExtentReports reports;
	ExtentTest test;
	WebDriver driver;
	@BeforeTest
	public void beforeTest()
	{
		driver=DriverUtiltity.getDriverInstance("chrome");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-ms");
		String filePath=System.getProperty("user.dir")+"/extent-reports/"
		+sdf.format(new Date())+".html";
		reporter=new ExtentHtmlReporter(filePath);
		reports=new ExtentReports();
		reports.attachReporter(reporter);
	}
	@AfterTest
	public void afterTest()
	{
		reports.flush();// all the messages will be written to the reports
	}
	@BeforeMethod
	public void beforeMethod(ITestResult result)
	{
		test=reports.createTest(result.getMethod().getMethodName());
	}
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "The "+result.getMethod().getMethodName()+" is passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "The "+result.getMethod().getMethodName()+" is failed");
			TakesScreenshot ts=(TakesScreenshot) driver;
			File srcFile=ts.getScreenshotAs(OutputType.FILE);
			String destfile=System.getProperty("user.dir")+"/extent-reports/screenshots/"+result.getMethod().getMethodName()+".png";
			FileUtils.copyFile(srcFile, new File(destfile));
			test.addScreenCaptureFromPath(destfile, "Demo Web Shop");
			test.log(Status.FAIL,result.getThrowable().getMessage());
			
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(Status.SKIP, "The "+result.getMethod().getMethodName()+" is skipped");
		}

	}
	@Test
	public void testDemoWebShopLogin()
	{
	
		driver.get("http://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.id("Email")).sendKeys("askmail@email.com");
		driver.findElement(By.id("Pass")).sendKeys("abc123");
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
	}
	@Test
	public void testFail()
	{
		Assert.assertTrue(false);
	}
	@Test
	public void testSkip()
	{
		throw new SkipException("Skips the Test");
	}
}
