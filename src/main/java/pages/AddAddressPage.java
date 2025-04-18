package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AddAddressPage extends RootPage
{
	WebDriver driver;
	public AddAddressPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="input-firstname")
    private WebElement FirstNameFeild;
	
	@FindBy(id="input-lastname")
	private WebElement LastNameFeild;
	
	@FindBy(id="input-company")
	private WebElement CompanyFeild;
	
	@FindBy(id="input-address-1")
	private WebElement Address1Feild;
	
	@FindBy(id="input-address-2")
	private WebElement Address2Feild;
	
	@FindBy(id="input-city")
	private WebElement City;
	
	@FindBy(id="input-postcode")
	private WebElement PostCode;
	
	@FindBy(id="input-country")
	private WebElement Country;
	
	@FindBy(id="input-zone")
	private WebElement State;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement ContinueButton;
	
	public void enterFirstName(String FirstNameText)
	{
		elementUtilities.enterTextIntoElement(FirstNameFeild,FirstNameText);
	}
	public void enterLastName(String LastNameText)
	{
		elementUtilities.enterTextIntoElement(LastNameFeild, LastNameText);
	}
	public void enterCompanyFeild(String CompanyText)
	{
		elementUtilities.enterTextIntoElement(CompanyFeild, CompanyText);
	}
	public void enterAddress1(String Address1Text)
	{
		elementUtilities.enterTextIntoElement(Address1Feild,Address1Text);
	}
	public void enterAddress2(String Address2Text)
	{
		elementUtilities.enterTextIntoElement(Address2Feild, Address2Text);
	}
	public void enterCity(String cityText)
	{
		elementUtilities.enterTextIntoElement(City, cityText);
	}
	public void enterPostCode(String PostCodeText)
	{
		elementUtilities.enterTextIntoElement(PostCode,PostCodeText);
	}
	public void selectOptionFromCountryDropDownFeild(String optionText)
	{
		elementUtilities.selectDropdownByVisibleText(Country, optionText);
	}
	public void selectOptionFromStateDropDownFeild(String optionText)
	{
		elementUtilities.selectDropdownByVisibleText(State, optionText);
	}
	public void clickOnContinueButton()
	{
		elementUtilities.clickOnElement(ContinueButton);
	}
	

}
