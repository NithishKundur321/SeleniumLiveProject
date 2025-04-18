package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class AffiliatePage extends RootPage
{
	WebDriver driver;
	public AffiliatePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="input-cheque")
	private WebElement chequePayeeName;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement ContinueButton;
	
	@FindBy(name="agree")
	private WebElement agreeBox;
	
	public void selectagreeBox()
	{
		elementUtilities.clickOnElement(agreeBox);
	}
	
	public void enterchequePayeeName(String chequePayeeNameText)
	{
		elementUtilities.enterTextIntoElement(chequePayeeName,chequePayeeNameText );
	}
	
	public MyAccountPage clickContinueButton()
	{
		elementUtilities.clickOnElement(ContinueButton);
		return new MyAccountPage(driver);
	}


}
