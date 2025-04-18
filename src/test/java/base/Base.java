package base;

import java.time.Duration;


import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import Utils.CommonUtilities;
import pages.AboutUsPage;
import pages.AccountLogoutPage;
import pages.AccountSuccessPage;
import pages.AddAddressPage;
import pages.AddressBookPage;
import pages.AffiliatePage;
import pages.AffliateProgramPage;
import pages.BrandsPage;
import pages.ChangeYourPasswordPage;
import pages.CheckOutPage;
import pages.CheckOutSuccessPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.FooterOptionsPage;
import pages.ForgotYourPassword;
import pages.GiftCertificate;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.MyAccountInfoPage;
import pages.MyAccountPage;
import pages.NewsLetterPage;
import pages.OrderHistoryPage;
import pages.OrderInformationPage;
import pages.PrivacyPolicyPage;
import pages.ProductComparisonPage;
import pages.ProductDisplayPage;
import pages.ProductReturnPage;
import pages.RegisterPage;
import pages.ReturnInfoPage;
import pages.ReturnsPage;
import pages.RightColumnOptions;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialOffers;
import pages.TermsConditions;
import pages.editAddressPage;
import pages.searchPage;
import pages.root.RootPage;

public class Base 
{
	public AffiliatePage affiliatePage;
	public AccountLogoutPage accountLogoutPage;
	public ChangeYourPasswordPage changeYourPassword;
	public String browserName;
	public LogoutPage logoutPage;
	public Properties prop;
	public HeaderOptions header;
    public WebDriver driver;
    public RegisterPage register;
	public AccountSuccessPage accountsuccess;
	public MyAccountPage accountPage;
	public NewsLetterPage newsletterPage;
	public LoginPage login;
	public RightColumnOptions rightcolumn;
	public MyAccountInfoPage myaccountinfo;
	public ContactUsPage contactus;
	public ShoppingCartPage shoppingcartPage;
	public HomePage home;
	public searchPage search;
	public ForgotYourPassword forgotPassword;
	public FooterOptionsPage FooterOption;
	public AboutUsPage about;
	public DeliveryInformationPage DeliveryInfo;
	public PrivacyPolicyPage PrivacyPolicy;
	public TermsConditions termsnconditions;
	public ReturnsPage returns;
	public SiteMapPage sitemap;
	public BrandsPage Brands;
	public GiftCertificate giftcertificate;
	public AffliateProgramPage affliate;
	public SpecialOffers Special;
	public Actions actions;
	public ProductDisplayPage productDisplayPage;
	public ProductComparisonPage productComparisonPage;
    public AddressBookPage addressBookPage;
    public AddAddressPage addAddressPage;
    public editAddressPage editaddressPage;
    public CheckOutPage checkoutPage;
    public CheckOutSuccessPage checkoutSuccessPage;
    public OrderHistoryPage orderhistoryPage;
    public OrderInformationPage orderinformationPage;
    public ProductReturnPage productreturnPage;
    public ReturnInfoPage returninfoPage;
    public RootPage rootPage;
    
	public WebDriver openBrowserApplicationPageUrl()
	{
		prop= CommonUtilities.loadPropertiesFile();
		browserName = prop.getProperty("browserName");
		
		if(browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("appURL"));
		return driver;
	}
	public void refreshPageAndNavigatePage(WebDriver driver,String pageURL)
	{
		refreshPage(driver);
		navigateToPage(pageURL);
		
	}
	public String getPageTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
    public String getPageURL(WebDriver driver)
    {
    	return driver.getCurrentUrl();
    }
    public void navigateBackInBrowser(WebDriver driver)
    {
    	driver.navigate().back();
    }
    public void refreshPage(WebDriver driver)
    {
    	driver.navigate().refresh();
    	
    }
    public void navigateToPage(String pageURL)
    {
    	driver.navigate().to(pageURL);
    }
    public Actions getActions(WebDriver driver) {
		Actions actions = new Actions(driver);
		return actions;
	}
	
	public Actions clickKeyboradKeyMultipleTimes(WebDriver driver,Keys keyName,int noOfTimes) {
		actions = getActions(driver);
		for (int i = 1; i <= noOfTimes; i++) {
			actions.sendKeys(keyName).perform();
		}
		return actions;
	}
	public Actions clickKeyboradKeyMultipleTimes(Actions actions,Keys keyName,int noOfTimes) {
		for (int i = 1; i <= noOfTimes; i++) {
			actions.sendKeys(keyName).perform();
		}
		return actions;
	}
	public Actions typeTextUsingActions(Actions actions,String text) {
		actions.sendKeys(text).perform();
		return actions;
	}
	
    
 }
