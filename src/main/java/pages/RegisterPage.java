package pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RegisterPage extends RootPage
{
	WebDriver driver;
	public RegisterPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="input-firstname")
	private WebElement firstnameFeild;
	
	@FindBy(id="input-lastname")
	private WebElement lastnameFeild;
	
	@FindBy(id="input-email")
	private WebElement emailFeild;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneFeild;
	
	@FindBy(id="input-password")
	private WebElement passwordFeild;
	
	@FindBy(id="input-confirm")
	private WebElement confirmpasswordFeild;
	
	@FindBy(name="agree")
	private WebElement privacypolicyFeild;
	
	@FindBy(xpath="//input[@class='btn btn-primary'][@value='Continue']")
	private WebElement continuebuttonFeild;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement NewsletterFeild;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	private WebElement noNewsLetterOption;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	@FindBy(xpath="//input[@id='input-confirm']/following-sibling::div")
	private WebElement confirmPasswordWarning;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement pageLevelWarning;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement RegisterPageBreadCrumb;
	
	@FindBy(css="label[for='input-firstname']")
	private WebElement FirstNameLabel;
	
	@FindBy(css="label[for='input-lastname']")
	private WebElement LastNameLabel;
	
	@FindBy(css="label[for='input-email']")
	private WebElement EmailLabel;
	
	@FindBy(css="label[for='input-telephone']")
	private WebElement TelephoneLabel;
	
	@FindBy(css="label[for='input-password']")
	private WebElement PasswordLabel;
	
	@FindBy(css="label[for='input-confirm']")
	private WebElement PasswordConfirmLabel;
	
	@FindBy(css="div[class='pull-right']")
	private WebElement PrivacyPolicyFeildLabel;
	
	@FindBy(linkText="login page")
	private WebElement loginPageOption;
	
	public LoginPage selectLoginPageOption()
	{
		elementUtilities.clickOnElement(loginPageOption);
		return new LoginPage(driver);
	}
	public HeaderOptions getHeaderOptionsPage()
	{
		return new HeaderOptions(driver);
	}
	
	public String getFirstNameCSSValue(String propertyName)
	{
		return elementUtilities.getElementCssValue(firstnameFeild, propertyName);
	}
	public String getLastNameCSSValue(String propertyName)
	{
		return elementUtilities.getElementCssValue(lastnameFeild, propertyName);
	
	}
	public String getEmailCSSValue(String propertyName)
	{
		return elementUtilities.getElementCssValue(emailFeild, propertyName);
	}
	public String getTelephoneCSSValue(String propertyName)
	{
		return elementUtilities.getElementCssValue(telephoneFeild, propertyName);

	}
	public String getPasswordCSSValue(String propertyName)
	{
		return elementUtilities.getElementCssValue(passwordFeild, propertyName);
		
	}
	public String getPasswordConfirmCSSValue(String propertyName)
	{
	    return elementUtilities.getElementCssValue(confirmpasswordFeild, propertyName);
	
	}
	public boolean isPrivacyPolicyFeildSelected()
	{
		return privacypolicyFeild.isSelected();
	}
	public void isprivacyPolicyFeildSelected()
	{
		elementUtilities.clickOnElement(privacypolicyFeild);
	}
	
	public void clearFirstNameFeild()
	{
	   elementUtilities.clearTextFromElement(firstnameFeild);
	}
	public void clearLastNameFeild()
	{
		elementUtilities.clearTextFromElement(lastnameFeild);
		
	}
	public void clearTelephoneFeild()
	{
		elementUtilities.clearTextFromElement(telephoneFeild);
	}
	public void clearPasswordFeild()
	{
		elementUtilities.clearTextFromElement(passwordFeild);	
	}
	
	public boolean isFirstNameWarningDisplayed()
	{
		return elementUtilities.isElementDisplayed(firstNameWarning);
		
	}
	public boolean isLastNameWarningDisplayed()
	{
		return elementUtilities.isElementDisplayed(lastNameWarning);
	
	}
	public boolean isEmailWarningDisplayed()
	{
		return elementUtilities.isElementDisplayed(emailWarning);
	}
	public boolean isTelePhoneWarningDisplayed()
	{
		return elementUtilities.isElementDisplayed(telephoneWarning);
	
	}
	public boolean isPasswordWarningDisplayed()
	{
		return elementUtilities.isElementDisplayed(passwordWarning);
	}
	public WebElement getFirstNameFeildElement()
	{
		return firstnameFeild;
	}
	public WebElement getLastNameFeildElement()
	{
		return lastnameFeild;
	}
	public WebElement getEmailFeildElement()
	{
		return emailFeild;
	}
	public WebElement getTelephoneFeildElement()
	{
		return telephoneFeild;
	}
	public WebElement getPasswordFeildElement()
	{
		return passwordFeild;
	}
	public WebElement getConfirmPasswordFeildElement()
	{
		return confirmpasswordFeild;
	}
	public WebElement getContinueButtonElement()
	{
		return continuebuttonFeild;
	}
	
	public WebElement getFirstNameLabelElement()
	{
		return FirstNameLabel;
	}
	public WebElement getLastNameLabelElement()
	{
		return LastNameLabel;
	}
	public WebElement getEmailLabelElement()
	{
		return EmailLabel;
	}
	public WebElement getTelephoneLabel()
	{
		return TelephoneLabel;
	}
	public WebElement getPasswordLabel()
	{
		return PasswordLabel;
	}
	public WebElement getPasswordConfirmLabel()
	{
		return PasswordConfirmLabel;
	}
	public WebElement getPrivacyPolicyFeild()
	{
		return PrivacyPolicyFeildLabel;
	}
	
	public String getFirstNamePlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(firstnameFeild,"placeholder");
	
	}
	public String getLastNamePlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(lastnameFeild, "placeholder");
		
	}
	public String getEmailPlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(emailFeild,"placeholder");
		
	}
	public String getTelePhonePlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(telephoneFeild, "placeholder");
	
	}
	public String getPasswordPlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(passwordFeild,"placeholder");
		
	}
	public String getConfirmPasswordPlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(confirmpasswordFeild, "placeholder");
	}
	
	public void clearEmailFeild()
	{
		elementUtilities.clearTextFromElement(emailFeild);
		
	}
	
	public String getEmailValidationMessage()
	{
		return emailFeild.getDomProperty("validationMessage");
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	public boolean didWeNavigateToRegisterPage()
	{
		return elementUtilities.isElementDisplayed(RegisterPageBreadCrumb);
		
	}
	
	public String getFirstNameWarning()
	{
		return elementUtilities.getElementText(firstNameWarning);
	}
	
	public String getLastNameWarning()
	{
		return elementUtilities.getElementText(lastNameWarning);
	}
	public String getEmailWarning()
	{
		return elementUtilities.getElementText(emailWarning);
		
	}
	public String getTelephoneWarning()
	{
		return elementUtilities.getElementText(telephoneWarning);
    }
	public String getPasswordWarning()
	{
		return elementUtilities.getElementText(passwordWarning);
	}
	public String getconfirmPassword()
	{
		return elementUtilities.getElementText(confirmPasswordWarning);
	}
	public String pagelevelWarning()
	{
		return elementUtilities.getElementText(pageLevelWarning);
	}
	
	public void enterFirstName(String firstNameText)
	{
		elementUtilities.enterTextIntoElement(firstnameFeild, firstNameText);
	}
	public void enterLastName(String lastNameText)
	{
		elementUtilities.enterTextIntoElement(lastnameFeild, lastNameText);
		
	}
	public void enteremailFeild(String emailText)
	{
		elementUtilities.enterTextIntoElement(emailFeild, emailText);
    }
	public void enterTelephoneFeild(String telephoneText)
	{
		elementUtilities.enterTextIntoElement(telephoneFeild, telephoneText);
		
	}
	public void enterPassword(String passwordText)
	{
		elementUtilities.enterTextIntoElement(passwordFeild, passwordText);
	}
	public void enterconfirmPassword(String confirmText)
	{
		elementUtilities.enterTextIntoElement(confirmpasswordFeild, confirmText);
	}
	
	public void clickprivacyPolicyFeild()
	{
		elementUtilities.clickOnElement(privacypolicyFeild);
	
    }
	public AccountSuccessPage clickContinueButton()
	{
		elementUtilities.clickOnElement(continuebuttonFeild);
		return new AccountSuccessPage(driver);
	}
	public void clickonNewsletterFeild()
	{
		elementUtilities.clickOnElement(NewsletterFeild);
	}
	public void noNewsLetterOption()
	{
		elementUtilities.clickOnElement(noNewsLetterOption);
	}
	public RightColumnOptions getRightColoumOptions()
	{
		return new RightColumnOptions(driver);
	}
	public FooterOptionsPage getFooterOptions()
	{
		return new FooterOptionsPage(driver);
	}
	

}
