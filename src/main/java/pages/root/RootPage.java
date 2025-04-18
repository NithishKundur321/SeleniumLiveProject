package pages.root;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ElementUtilities;
import pages.RightColumnOptions;

public class RootPage 
{
	WebDriver driver;
	public ElementUtilities elementUtilities;
	public RootPage(WebDriver driver)
	{
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement pageHeading;
	
	public String getPageHeading()
	{
		return elementUtilities.getElementText(pageHeading);
	
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	public RightColumnOptions getRightColumnOptions()
	{
		return new RightColumnOptions(driver);
	}
	

}
