package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TILogin 
{
	WebDriver driver;
	@FindBy(how=How.XPATH,using="//*[@id=\"login-user-inputEl\"]")
	private WebElement userID;
	@FindBy(how=How.XPATH,using="//*[@id=\"login-password-inputEl\"]")
	private WebElement userPwd;
	@FindBy(how=How.XPATH,using="//*[@id=\"login-group-inputEl\"]")
	private WebElement userGroup;
	@FindBy(how=How.XPATH,using="//*[@id=\"login-group-trigger-picker\"]")
	private WebElement userGroupPicker;
	@FindBy(how=How.XPATH,using="//*[@id=\"login-button\"]")
	private WebElement loginBtn;
	@FindBy(how=How.XPATH,using="//label[@id='login-status']")
	private WebElement loginStatus;
	@FindBy(how=How.XPATH,using="//a[@id='usersFirstName']")
	private WebElement myProfile;
	@FindBy(how=How.XPATH,using="//a[@id='userDetails-itemEl']")
	private WebElement userDetails;
	@FindBy(how=How.XPATH,using="//div[@id='splashScreen']")
	private WebElement splashScreen;
	@FindBy(how=How.XPATH,using="//div[@id='product-alert-widget']")
	private WebElement productAlertWin;



	public TILogin(WebDriver driver)
	{
		this.driver=driver;
	}

	public void setUserID(String userName)
	{
		userID.clear();
		userID.sendKeys(userName);
	}
	
	public void setUserPwd(String Pwd)
	{
		userPwd.clear();
		userPwd.sendKeys(Pwd);
	}
	
	public void setUserGroup(String Group) throws InterruptedException
	{
		userGroupPicker.click();
		userGroupPicker.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[text()='"+Group+"']")).click();
		
	}
	
	public void Login() throws InterruptedException
	{
		loginBtn.click();
		Thread.sleep(10000);
	}
	
	public boolean VerifyLogin(String userName)
	{
		myProfile.click();
		String s = userDetails.getText();
		System.out.println(s);
		if(s.contains(userName))
		{
			System.out.println("User "+userName+" is logged in successfully");
			return true;
		}
		else 
			return false;
		
		
		
		
	}

}
