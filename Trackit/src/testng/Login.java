package testng;

import org.testng.annotations.Test;

import pageObject.TILogin;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login 
{
	WebDriver wd = null;

  @BeforeClass
  public void beforeClass() 
  {
	  System.out.println("In method beforeClass");
	  System.setProperty("webdriver.chrome.driver", "C:\\BMCSelenium\\CoreFiles\\chromedriver.exe");
	  wd=new ChromeDriver();
	  wd.manage().window().maximize();
	  wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() 
  {
	  System.out.println("In method afterClass");
	  wd.quit();
  }
  
  @Test(priority=1)
  public void LaunchTrackit()
  {
	  wd.get("http://vw-pun-trt-qa16/TrackIt");
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //Wait for response from server
	  Assert.assertTrue(wd.getTitle().startsWith("Track-It!"));
  }
  
  @Test(priority=2, dependsOnMethods = "LaunchTrackit")
  public void LoginTrackit() throws InterruptedException
  {
	  TILogin l = PageFactory.initElements(wd, TILogin.class);
	  l.setUserID("AUTOSATECH2");
	  l.setUserPwd("welcome");
	  l.setUserGroup("AUTOGROUP2");
	  l.Login();
	  //wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	  Assert.assertEquals(wd.findElement(By.xpath("//*[@id=\"ti-add-new-button\"]")).getText(), "ADD NEW");
	  boolean loginSucceeded = l.VerifyLogin("AUTOSATECH2");
	  if(!loginSucceeded)
		  Assert.fail("Login unsuccessfull");
		  
  }
  

  
  

}
