package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class LoginPage extends RootPage
{
	
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement pageLevelWarning;
	
	@FindBy(id="input-email")
	private WebElement emailFeild;
	
	@FindBy(id="input-password")
	private WebElement passwordFeild;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadCrumb;
	
	@FindBy(linkText="Forgotten Password")
	private WebElement forgottenPassword;
	
	@FindBy(xpath="//div[@id='content']//h2)[1]")
	private WebElement firstHeading;
	
	@FindBy(xpath="//div[@id='content']//h2)[2]")
	private WebElement secondHeading;
	
	@FindBy(xpath="//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement clickContinueButton;
	
	@FindBy(xpath="//a[text()='Forgotten Password']")
	private WebElement clickForgotPassword;
	
	@FindBy(xpath="//input[@class='btn btn-primary'][@value='Login']")
	private WebElement clickLoginButton;
	
	public FooterOptionsPage getFooterOptions()
	{
		return new FooterOptionsPage(driver);
	}
	
	public RightColumnOptions getRightColumnOptions()
	{
		return new RightColumnOptions(driver);
	}
	
	public LoginPage clickOnLoginButton()
	{
		elementUtilities.clickOnElement(clickLoginButton);
		return new LoginPage(driver);
	}
	
	public ForgotYourPassword clickOnForgotPassword()
	{
		elementUtilities.clickOnElement(clickForgotPassword);
		return new ForgotYourPassword(driver);
	}
	
	public RegisterPage ClickContinueButton()
	{
		elementUtilities.clickOnElement(clickContinueButton);
		return new RegisterPage(driver);
	}
	
	public HeaderOptions getHeaderOptionsPage()
	{
		return new HeaderOptions(driver);
	}
	
	public String getFirstHeading() {
		return elementUtilities.getElementText(firstHeading);
	}
	
	public String getSecondHeading() {
		return elementUtilities.getElementText(secondHeading);
	}
	
	
	public boolean isPasswordInPageSource(String password) 
	{
        return driver.getPageSource().contains(password);
    }
	
	public String getPasswordFeildDomAttribute(String attributeName)
	{
		return elementUtilities.getElementDomAttribute(passwordFeild,attributeName);
	}
	
    public String getEmailFeildPlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(emailFeild,"placeholder");
	}
	public String getPasswordFeildPlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(passwordFeild,"placeholder");
	}
	
	public ForgotYourPassword clickOnForgottenPasswordLink()
	{
		forgottenPassword.click();
		return new ForgotYourPassword(driver);
	}
	public String pagelevelWarning()
	{
		return elementUtilities.getElementText(pageLevelWarning);
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	public MyAccountPage loginInToApplication(String emailText,String passwordText)
	{
		elementUtilities.enterTextIntoElement(emailFeild, emailText);
		elementUtilities.enterTextIntoElement(passwordFeild, passwordText);
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);
		
	}
	
	
    public RegisterPage clickOnContinueButton()
	{
		elementUtilities.clickOnElement(continueButton);
		return new RegisterPage(driver);
		
	}
	public void enterEmail(String emailText)
	{
		elementUtilities.enterTextIntoElement(emailFeild, emailText);
	}
	public void enterPassword(String passwordText)
	{
		elementUtilities.enterTextIntoElement(passwordFeild, passwordText);
	}
	public MyAccountPage clickLoginButton()
	{
		elementUtilities.clickOnElement(loginButton);
		return new MyAccountPage(driver);
	}
	public boolean didWeNavigateToLogin()
	{
		return elementUtilities.isElementDisplayed(loginBreadCrumb);
	}
	public String getPasswordFieldValue() {
	    return passwordFeild.getAttribute("value");
	}

	public String getEmailFieldValue() {
	    return emailFeild.getAttribute("value");
	}

	public void pasteIntoEmailField(String text) {
	    emailFeild.sendKeys(text);
	}

	public void clearEmailField() {
	    emailFeild.clear();
	}

	


}
