package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ChangeYourPasswordPage extends RootPage
{
	WebDriver driver;
	public ChangeYourPasswordPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-password")
	private WebElement enterPassword;
	
	@FindBy(id="input-confirm")
	private WebElement enterConfirmPassword;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement clickContinueButton;
	
	public void enterPasswordText(String PasswordText)
	{
		elementUtilities.enterTextIntoElement(enterPassword, PasswordText);
		
	}
	public void enterConfirmPassword(String ConfirmPasswordText)
	{
		elementUtilities.enterTextIntoElement(enterConfirmPassword, ConfirmPasswordText);
	}
	public MyAccountPage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(clickContinueButton);
		return new MyAccountPage(driver);
	}

}
