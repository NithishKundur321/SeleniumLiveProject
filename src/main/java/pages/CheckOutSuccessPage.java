package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class CheckOutSuccessPage extends RootPage
{
	WebDriver driver;
	public CheckOutSuccessPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="my account")
	private WebElement myAccount;
	
	public MyAccountPage clickOnMyAccount()
	{
		elementUtilities.clickOnElement(myAccount);
		return new MyAccountPage(driver);
	}


}
