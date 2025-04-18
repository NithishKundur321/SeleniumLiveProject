package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class FooterOptionsPage extends RootPage
{
	WebDriver driver;
	public FooterOptionsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
     
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='About Us']")
	private WebElement FooterAboutUs;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Delivery Information']")
	private WebElement FooterDeliveryInformation;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Privacy Policy']")
	private WebElement FooterPrivacyPolicy;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Terms & Conditions']")
	private WebElement FooterTermsConditions;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Contact Us']")
	private WebElement FooterContactUs;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Returns']")
	private WebElement FooterReturns;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Site Map']")
	private WebElement FooterSiteMap;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Brands']")
	private WebElement FooterBrands;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Gift Certificates']")
	private WebElement FooterGiftCertificate;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Affiliate']")
	private WebElement AffliateProgram;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Specials']")
	private WebElement Specials;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='My Account']")
	private WebElement FooterMyAccount;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Order History']")
	private WebElement FooterOrderHistory;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Wish List']")
	private WebElement FooterWishList;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Newsletter']")
	private WebElement FooterNewsLetter;
	
	@FindBy(linkText="Site Map")
	private WebElement SiteMap;
	
	public SiteMapPage selectSiteMapOption()
	{
		elementUtilities.clickOnElement(SiteMap);
		return new SiteMapPage(driver);
	}
	
	public LoginPage clickOnFooterNewsLetter()
	{
		elementUtilities.clickOnElement(FooterNewsLetter);
		return new LoginPage(driver);
	}
	
	
	public LoginPage clickOnFooterWishList()
	{
		elementUtilities.clickOnElement(FooterWishList);
		return new LoginPage(driver);
	}
	
	
	public LoginPage clickOnFooterOrderHistory()
	{
		elementUtilities.clickOnElement(FooterOrderHistory);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnFooterMyAccount()
	{
		elementUtilities.clickOnElement(FooterMyAccount);
		return new LoginPage(driver);
		
	}
	
	public MyAccountPage clickNewsLetter()
	{
		elementUtilities.clickOnElement(FooterNewsLetter);
		return new MyAccountPage(driver);
	}
	
	public MyAccountPage clickWishList()
	{
		elementUtilities.clickOnElement(FooterWishList);
	    return new MyAccountPage(driver);
	}
	
	public MyAccountPage clickOrderHistory()
	{
		elementUtilities.clickOnElement(FooterOrderHistory);
		return new MyAccountPage(driver);
	}
	
	public MyAccountPage clickMyAccount()
	{
		elementUtilities.clickOnElement(FooterMyAccount);
		return new MyAccountPage(driver);
	}
	
	public SpecialOffers clickSpecials()
	{
		elementUtilities.clickOnElement(Specials);
	    return new SpecialOffers(driver);
	}
	
	public AffliateProgramPage clickAffliate()
	{
		elementUtilities.clickOnElement(AffliateProgram);
		return new AffliateProgramPage(driver);
	}
	
	public GiftCertificate clickGiftCertificate()
	{
		elementUtilities.clickOnElement(FooterGiftCertificate);
		return new GiftCertificate(driver);
	}
	
	public BrandsPage clickBrands()
	{
		elementUtilities.clickOnElement(FooterBrands);
		return new BrandsPage(driver);
	}
	
	public SiteMapPage clickSiteMap()
	{
		elementUtilities.clickOnElement(FooterSiteMap);
		return new SiteMapPage(driver);
	}
	
	public ReturnsPage clickReturns()
	{
		elementUtilities.clickOnElement(FooterReturns);
		return new ReturnsPage(driver);
	}
	
	public ContactUsPage clickContactUs()
	{
		elementUtilities.clickOnElement(FooterContactUs);
		return new ContactUsPage(driver);
	}
	
	public TermsConditions clickTermsConditions()
	{
		elementUtilities.clickOnElement(FooterTermsConditions);
		return new TermsConditions(driver);
	}
	
	public PrivacyPolicyPage clickPrivacyPolicy()
	{
		elementUtilities.clickOnElement(FooterPrivacyPolicy);
        return new PrivacyPolicyPage(driver);
	}
	
	public DeliveryInformationPage clickFooterDelivery()
	{
		elementUtilities.clickOnElement(FooterDeliveryInformation);
		return new DeliveryInformationPage(driver);
	}
	
	public AboutUsPage clickAboutUs()
	{
		elementUtilities.clickOnElement(FooterAboutUs);
	    return new AboutUsPage(driver);
	}
}
