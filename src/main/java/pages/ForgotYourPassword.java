package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ForgotYourPassword extends RootPage
{
	WebDriver driver;
	public ForgotYourPassword(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Forgotten Password']")
	private WebElement ForgottenBreadCrumb;
	
	public boolean didWeNavigateToForgottenPage()
	{
	     return	elementUtilities.isElementDisplayed(ForgottenBreadCrumb);
	}

}
