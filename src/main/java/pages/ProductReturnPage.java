package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProductReturnPage extends RootPage
{
	WebDriver driver;
	public ProductReturnPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="return_reason_id")
    private WebElement ReasonForReturn;
	
	@FindBy(css="input[value='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath="//a[@*='View']")
	private WebElement view;
	
	public ReturnInfoPage selectViewOption()
	{
		elementUtilities.clickOnElement(view);
		return new ReturnInfoPage(driver);
	}
	
	public void selectReasonForReturn()
	{
		elementUtilities.clickOnElement(ReasonForReturn);
	}
	
	public void clickOnSubmitButton()
	{
		elementUtilities.clickOnElement(submitButton);
	}

}
