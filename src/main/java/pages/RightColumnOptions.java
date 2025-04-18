package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RightColumnOptions extends RootPage
{
	WebDriver driver;
	
	public RightColumnOptions(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//aside[@id='column-right']//a[text()='Register']")
    private WebElement registerOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']")
	private WebElement logout;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Login']")
	private WebElement RightColumnLogin;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Register']")
	private WebElement RightColumnRegister;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Forgotten Password']")
	private WebElement RightColumnForgotPassword;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='My Account']")
	private WebElement RightColumnMyAccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Address Book']")
	private WebElement RightColumnAddressBook;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Wish List']")
	private WebElement RightColumnWishList;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Order History']")
	private WebElement OrderHistory;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Downloads']")
	private WebElement Downloads;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Recurring payments']")
	private WebElement ReccuringPayment;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Reward Points']")
	private WebElement RewardPoints;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Returns']")
	private WebElement Returns;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Transactions']")
	private WebElement Transactions;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Newsletter']")
	private WebElement NewsLetter;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='My Account']")
	private WebElement myaccountoption;
	
	public MyAccountPage clickOnMyAccountOption()
	{
		elementUtilities.clickOnElement(myaccountoption);
		return new MyAccountPage(driver);
	}
	
	public MyAccountPage clickOnMyAccountOptionAfterLogin()
	{
		elementUtilities.clickOnElement(myaccountoption);
		return new MyAccountPage(driver);
	}
	public boolean isLogoutOptionDisplayed()
	{
		return elementUtilities.isElementDisplayed(logout);
	}
	
	public AccountLogoutPage clickOnLogout()
	{
		elementUtilities.clickOnElement(logout);
		return new AccountLogoutPage(driver);
	}
	public LoginPage selectNewsLetter()
	{
		elementUtilities.clickOnElement(NewsLetter);
	    return new LoginPage(driver);
	}
	
	public LoginPage selectTransactions()
	{
		elementUtilities.clickOnElement(Transactions);
	    return new LoginPage(driver);
	}
	
	public LoginPage selectReturns()
	{
		elementUtilities.clickOnElement(Returns);
        return new LoginPage(driver);
	}
	
	public LoginPage selectRewardPoints()
	{
		elementUtilities.clickOnElement(RewardPoints);
		return new LoginPage(driver);
	}
	
	public LoginPage selectReccuringPayment()
	{
		elementUtilities.clickOnElement(ReccuringPayment);
		return new LoginPage(driver);
	}
	
	public LoginPage selectDownloads()
	{
		elementUtilities.clickOnElement(Downloads);
		return new LoginPage(driver);
	}
	
	public LoginPage selectOrderHistory()
	{
		elementUtilities.clickOnElement(OrderHistory);
		return new LoginPage(driver);
	}
	
	public LoginPage selectRightColumnWishList()
	{
		elementUtilities.clickOnElement(RightColumnWishList);
		return new LoginPage(driver);
	}
	
	public LoginPage selectRightColumnAddressBook()
	{
		elementUtilities.clickOnElement(RightColumnAddressBook);
	    return new LoginPage(driver);
	}
	
	public LoginPage selectRightColumnMyAccount()
	{
		elementUtilities.clickOnElement(RightColumnMyAccount);
		return new LoginPage(driver);
	}
	
	public ForgotYourPassword selectRightColumnForgotPassword()
	{
		elementUtilities.clickOnElement(RightColumnForgotPassword);
		return new ForgotYourPassword(driver);
	}
	
	public RegisterPage selectRightColumnRegister()
	{
		elementUtilities.clickOnElement(RightColumnRegister);
		return new RegisterPage(driver);
	}
	
	public LoginPage selectRightColoumnLogin()
	{
		elementUtilities.clickOnElement(RightColumnLogin);
	    return new LoginPage(driver);
	}
	
	public boolean DidWeLoggedIn()
	{
		return elementUtilities.isElementDisplayed(logout);
		
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	public RegisterPage clickOnRegister()
	{
		elementUtilities.clickOnElement(registerOption);
	    return new RegisterPage(driver);
	}
}
