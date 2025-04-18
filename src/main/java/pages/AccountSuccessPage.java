package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AccountSuccessPage extends RootPage
{
    WebDriver driver;
	public AccountSuccessPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']")
	private WebElement logoutOption;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement Success;
	
	@FindBy(id="content")
	private WebElement accountgetText;
	
	@FindBy(xpath="//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement clicktheContinueButton;
	
	
	public MyAccountPage clickonContinueButton()
	{
		elementUtilities.clickOnElement(clicktheContinueButton);
	    return new MyAccountPage(driver);
	}
	
	public boolean DidWeNavigateToAccountSuccessPage()
	{
		return elementUtilities.isElementDisplayed(Success);
	
	}
	
	public boolean isUserLoggedin()
	{
		return elementUtilities.isElementDisplayed(logoutOption);
	
	}
	
	public boolean SuccessBreadCrumb()
	{
	  return elementUtilities.isElementDisplayed(Success);
	}
	
	public String AccountGetText()
	{
		return elementUtilities.getElementText(accountgetText);
	}
	

}
