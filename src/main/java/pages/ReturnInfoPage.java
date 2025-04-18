package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ReturnInfoPage extends RootPage
{
	WebDriver driver;
	public ReturnInfoPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    @FindBy(linkText="Account")
    private WebElement AccountBreadCrumb;
    
    public MyAccountPage clickOnAccountBreadCrumb()
    {
    	elementUtilities.clickOnElement(AccountBreadCrumb);
    	return new MyAccountPage(driver);
    }
}
