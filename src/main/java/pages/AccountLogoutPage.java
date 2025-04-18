package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AccountLogoutPage extends RootPage
{
	WebDriver driver;
	public AccountLogoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Continue")
	private WebElement clickContinueButton;
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement pageHeading;
	
	public HomePage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(clickContinueButton);
		return new HomePage(driver);
	}
	
	public HeaderOptions getHeaderOptions()
	{
		return new HeaderOptions(driver);
	}
	
	public String getPageHeading()
	{
		return elementUtilities.getElementText(pageHeading);
	}
	

}
