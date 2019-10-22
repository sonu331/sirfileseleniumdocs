package seleniumplus;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
public class Test7 {

  @Test(groups = {"tag1"})
  public void test1() {
	  System.out.println("in test1");
  }
  @Test(groups = {"tag1","tag2"})
  public void test2()
  {
	  System.out.println("in test2");
  }
  @Test(groups = {"tag2"})
  public void test3()
  {
	  System.out.println("in test3");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("in before method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("in after method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("in before class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("in after class");
  }

}
