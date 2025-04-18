package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class SiteMapPage extends RootPage
{
	WebDriver driver;
	public SiteMapPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Search")
	private WebElement SearchOption;
	
	public searchPage clickOnSearchOption()
	{
		elementUtilities.clickOnElement(SearchOption);
		return new searchPage(driver);
	}


}
