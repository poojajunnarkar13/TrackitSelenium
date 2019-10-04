package testng;

import org.testng.annotations.Test;

import pageObject.TILogin;
import utilityScripts.ReadPropertiesFile;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login extends ReadPropertiesFile
{
	WebDriver wd = null;
	

  @BeforeClass
  public void beforeClass() throws IOException 
  {
	  System.out.println("In method beforeClass");	
	  String br = getPropertyValue("browser");
	  if(br.equals("chrome"))
	  {		  
		  System.setProperty("webdriver.chrome.driver", "C:\\BMCSelenium\\CoreFiles\\chromedriver.exe");
		  wd=new ChromeDriver();
	  }
	  else if(br.equals("firefox"))
	  {
		  System.setProperty("webdriver.gecko.driver", "C:\\BMCSelenium\\CoreFiles\\geckodriver.exe");
		  wd=new FirefoxDriver();
	  }
	  else if(br.equals("ie"))
	  {
		  System.setProperty("webdriver.ie.driver", "C:\\BMCSelenium\\CoreFiles\\IEDriverServer.exe");
		  wd=new InternetExplorerDriver();
	  }
	  
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
  public void LaunchTrackit() throws IOException
  {
	  wd.get("http://"+getPropertyValue("server")+"/TrackIt");
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //Wait for response from server
	  Assert.assertTrue(wd.getTitle().startsWith("Track-It!"));
  }
  
  @Test(priority=2, dependsOnMethods = "LaunchTrackit")
  public void LoginTrackit() throws InterruptedException, IOException
  {
	  TILogin l = PageFactory.initElements(wd, TILogin.class);
	  l.setUserID(getPropertyValue("username"));
	  l.setUserPwd(getPropertyValue("password"));
	  l.setUserGroup(getPropertyValue("group"));
	  l.Login();
	  wd.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	  l.VerifyLoginPopup();
	  Assert.assertEquals(wd.findElement(By.xpath("//*[@id=\"ti-add-new-button\"]")).getText(), "ADD NEW");
	  boolean loginSucceeded = l.VerifyLogin(getPropertyValue("username"));
	  if(!loginSucceeded)
		  Assert.fail("Login unsuccessfull");
		  
  }
  

  
  

}
