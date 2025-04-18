package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountInfoPage extends RootPage
{
	WebDriver driver;
	public MyAccountInfoPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="input-firstname")
	private WebElement firstNameFeild;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameFeild;
	
	@FindBy(id="input-email")
	private WebElement emailFeild;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneFeild;
	
	
	public String getFirstNameDomAttribute(String attributeName)
	{
	   return elementUtilities.getElementDomAttribute(firstNameFeild, attributeName);
	}
	
	public String getLastNameDomAttribute(String attributeName)
	{
	    return elementUtilities.getElementDomAttribute(lastNameFeild, attributeName);
    }
	
	public String getEmailDomAttribute(String attributeName)
	{
		return elementUtilities.getElementDomAttribute(emailFeild, attributeName);
	
	}
	public String getEmailDomProperty(String propertyName)
	{
		return elementUtilities.getElementDomProperty(emailFeild, propertyName);
	
	}
	
	public String getTelephoneDomAttribute(String attributeName)
	{
		return elementUtilities.getElementDomAttribute(telephoneFeild, attributeName);
	}
	
	

}
