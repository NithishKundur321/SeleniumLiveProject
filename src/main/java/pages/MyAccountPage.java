package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class MyAccountPage extends RootPage
{
	WebDriver driver;
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Edit your account information")
	private WebElement editYourAccountInformation;
	
	@FindBy(linkText="Change your password")
	private WebElement changeyourPasswordInfo;
	
	@FindBy(linkText="Subscribe / unsubscribe to newsletter")
	private WebElement clickonNewsLetter;
	
	@FindBy(linkText="Change your password")
	private WebElement clickOnChangeYourPassword;
	
	@FindBy(linkText="Edit your account information")
	private WebElement edityouraccountinfoPage;
	
	@FindBy(linkText="Change your password")
	private WebElement ChangeyourPassword;
	
	@FindBy(linkText="Modify your address book entries")
	private WebElement modifyYourAddressBookEntries;
	
	@FindBy(linkText="Modify your wish list")
	private WebElement modifyYourWishList;
	
	@FindBy(linkText="View your order history")
	private WebElement ViewYourOrderHistory;
	
	@FindBy(linkText="Downloads")
	private WebElement Downloads;
	
	@FindBy(linkText="Your Reward Points")
	private WebElement YourRewardPoints;
	
	@FindBy(linkText="View your return requests")
	private WebElement ViewYourReturnRequest;
	
	@FindBy(linkText="Your Transactions")
	private WebElement YourTransaction;
	
	@FindBy(linkText="Register for an affiliate account")
	private WebElement RegisterForAnAffliateAccount;
	
	@FindBy(linkText="Edit your affiliate information")
	private WebElement EditYourAffiliateInformation;
	
	@FindBy(linkText="Custom Affiliate Tracking Code")
	private WebElement CustomAffiliateTrackingCode;
	
	@FindBy(linkText="Subscribe / unsubscribe to newsletter")
	private WebElement ClickSubscribeUnSubcribeNewsLetter;
	
	public void clickOnSubscribeUnSubcribeToNewsLetter()
	{
		elementUtilities.clickOnElement(ClickSubscribeUnSubcribeNewsLetter);
	}
	
	public void clickOnCustomAffiliateTrackingCode()
	{
		elementUtilities.clickOnElement(CustomAffiliateTrackingCode);
	}
	
	public AffiliatePage clickOnEditYourAffiliateInformationOption()
	{
		elementUtilities.clickOnElement(EditYourAffiliateInformation);
		return new AffiliatePage(driver);
	}
	
	public AffiliatePage clickOnRegisterForAnAffliateAccountOption()
	{
		elementUtilities.clickOnElement(RegisterForAnAffliateAccount);
		return new AffiliatePage(driver);
	}
	public void clickonYourTransaction()
	{
		elementUtilities.clickOnElement(YourTransaction);
	}
	
	public ProductReturnPage clickOnViewYourReturnRequestOption()
	{
		elementUtilities.clickOnElement(ViewYourReturnRequest);
		return new ProductReturnPage(driver);
	}
	
	public void clickOnRewardPointsOption()
	{
		elementUtilities.clickOnElement(YourRewardPoints);
	}
	
	public DownloadsPage clickonDownloadsOption()
	{
		elementUtilities.clickOnElement(Downloads);
		return new DownloadsPage(driver);
	}
	
	public OrderHistoryPage clickOnViewYourOrderHistoryOption()
	{
		elementUtilities.clickOnElement(ViewYourOrderHistory);
		return new OrderHistoryPage(driver);
	}
	
	public void clickOnModifyYourWishList()
	{
		elementUtilities.clickOnElement(modifyYourWishList);
		
	}
	
	public AddressBookPage clickOnModifyBookEntries()
	{
		elementUtilities.clickOnElement(modifyYourAddressBookEntries);
		return new AddressBookPage(driver);
	}
	
	public ChangeYourPasswordPage clickOnChangeYourPassword()
	{
		elementUtilities.clickOnElement(ChangeyourPassword);
		return new ChangeYourPasswordPage(driver);
	}
	
	public MyAccountPage clickOnEditYourAccountInfoPage()
	{
		elementUtilities.clickOnElement(edityouraccountinfoPage);
		return new MyAccountPage(driver);
	}
	
	public RightColumnOptions getRightColoumOptions()
	{
		return new RightColumnOptions(driver);
	}
	public HeaderOptions getHeaderOptionsPage()
	{
		return new HeaderOptions(driver);
	}
	
	public ChangeYourPasswordPage ClickChangeYourPasswordOption()
	{
		elementUtilities.clickOnElement(clickOnChangeYourPassword);
		return new ChangeYourPasswordPage(driver);
	}
	
	public NewsLetterPage clickNewsletter()
	{
		elementUtilities.clickOnElement(clickonNewsLetter);
		return new NewsLetterPage(driver);
	}
	
	public boolean editInfo()
	{
		return elementUtilities.isElementDisplayed(editYourAccountInformation);
	
	}
	public boolean ChangeYourPasswordInfo()
	{
		return elementUtilities.isElementDisplayed(changeyourPasswordInfo);
		
	}
	public MyAccountInfoPage clickOnEditInfoLink()
	{
		elementUtilities.isElementDisplayed(editYourAccountInformation);
		return new MyAccountInfoPage(driver);
	}

}
