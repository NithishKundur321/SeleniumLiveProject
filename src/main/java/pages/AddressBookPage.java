package pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import pages.root.RootPage;

public class AddressBookPage extends RootPage
{
	WebDriver driver;
    public AddressBookPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText="New Address")
	private WebElement newAddressButton;
	
	@FindBy(linkText="Edit")
	private WebElement editAddressButton; 
	
	public AddAddressPage clickOnNewAddressButton()
	{
		elementUtilities.clickOnElement(newAddressButton);
		return new AddAddressPage(driver);
	}

    public editAddressPage clickOnEditButton()
    {
    	elementUtilities.clickOnElement(editAddressButton);
    	return new editAddressPage(driver);
    }
}
