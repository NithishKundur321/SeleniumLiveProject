package pages;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ElementUtilities;
import pages.root.RootPage;

public class HeaderOptions extends RootPage
{
	WebDriver driver;
	ElementUtilities elementUtilities;
    
	public HeaderOptions(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Register")
	private WebElement clickonRegister;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(xpath="//i[@class='fa fa-phone']")
	private WebElement PhoneIcon;
	
	@FindBy(xpath="//i[@class='fa fa-heart']")
	private WebElement Hearticon;
	
	@FindBy(xpath="//span[@class='hidden-xs hidden-sm hidden-md'][text()='Wish List (0)']")
	private WebElement WishList;
	
	@FindBy(xpath="//i[@class='fa fa-shopping-cart']")
	private WebElement shoppingcartIcon;
	
	@FindBy(xpath="//span[@class='hidden-xs hidden-sm hidden-md'][text()='Shopping Cart']")
	private WebElement shoppingcartOption;
	
	@FindBy(xpath="//i[@class='fa fa-share']")
	private WebElement checkoutIcon;
	
	@FindBy(xpath="//span[@class='hidden-xs hidden-sm hidden-md'][text()='Checkout']")
	private WebElement checkoutOption;
	
	@FindBy(linkText="Qafox.com")
	private WebElement logoOption;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchOption;
	
	@FindBy(xpath="//i[@class='fa fa-home']")
	private WebElement HomeBreadCrumb;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement accountbreadCrumb;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement registerBreadCrumb;
	
	@FindBy(linkText="login page")
	private WebElement loginPageLink;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Logout']")
	private WebElement logoutOption;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginbreadCrumb;
	
	@FindBy(name="search")
	private WebElement searchBoxFeild;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	@FindBy(linkText="My Account")
	private WebElement selectMyAccountOption;
	
	public MyAccountPage clickOnMyAccountOption()
	{
		elementUtilities.clickOnElement(selectMyAccountOption);
		return new MyAccountPage(driver);
	}
	public RegisterPage navigateToRegisterPage()
	{
		clickonMyAccountDropMenu();
		return selectRegisterOption();
	}
	
	public boolean areSearchBoxFeildAndSearchButtonFeildDisplayed()
	{
		return isSearchBoxFeildDisplayed() && isSearchButtonFeildDisplayed(); 
	}
	public boolean isSearchButtonFeildDisplayed()
	{
		return elementUtilities.isElementDisplayed(searchButton);
	}
	public boolean isSearchBoxFeildDisplayed()
	{
		return elementUtilities.isElementDisplayed(searchBoxFeild);
	}
	
	public String getSearchBoxFeildPlaceholderText()
	{
		return elementUtilities.getElementDomAttribute(searchBoxFeild,"placeholder");
	}
	
	public searchPage clickSearchButton()
	{
		elementUtilities.clickOnElement(searchButton);
		return new searchPage(driver);
	}
	
	public void enterProductInToSearchBoxFeild(String productname)
	{
		elementUtilities.enterTextIntoElement(searchBoxFeild,productname);
	}
	
	public boolean isLogoutOptionunderMyAccountDropdownMenuisDisplayed()
	{
		return elementUtilities.isElementDisplayed(logoutOption);
	}
	
	public AccountLogoutPage getAccountLogoutPage()
	{
		return new AccountLogoutPage(driver);
	}
		
	public LoginPage selectLoginBreadCrumb()
	{
		elementUtilities.clickOnElement(loginbreadCrumb);
		return new LoginPage(driver);
	}
	
	public LoginPage selectAccountBreadCrumb()
	{
		elementUtilities.clickOnElement(accountbreadCrumb);
		return new LoginPage(driver);
	}
	
	public HomePage selectHomeBreadCrumb()
	{
		elementUtilities.clickOnElement(HomeBreadCrumb);
		return new HomePage(driver);
	}
	
	public AccountLogoutPage selectLogoutOption()
	{
		elementUtilities.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}
	public LoginPage clickLoginOption()
	{
		elementUtilities.clickOnElement(loginPageLink);
		return new LoginPage(driver);
	}
	public RegisterPage selectRegisterOption()
	{
		elementUtilities.clickOnElement(clickonRegister);	
		return new RegisterPage(driver);
	}
	public LoginPage selectAccountOption()
	{
		elementUtilities.clickOnElement(accountbreadCrumb);
	    return new LoginPage(driver);
	}
	
	public searchPage selectsearchOption()
	{
		elementUtilities.clickOnElement(searchOption);
		return new searchPage(driver);
	}
	
	public HomePage selectLogo()
	{
		elementUtilities.clickOnElement(logoOption);
		return new HomePage(driver);
	}
	
	public ShoppingCartPage selectCheckOutOption()
	{
		elementUtilities.clickOnElement(checkoutOption);
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage selectCheckOutIcon()
	{
		elementUtilities.clickOnElement(checkoutIcon);
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage selectShoppingCartOption()
	{
		elementUtilities.clickOnElement(shoppingcartOption);
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage selectShoppingCartIcon()
	{
		elementUtilities.clickOnElement(shoppingcartIcon);
	    return new ShoppingCartPage(driver);
	}
	
	public LoginPage selectWishList()
	{
		elementUtilities.clickOnElement(WishList);
	    return new LoginPage(driver);
	}
	
	public LoginPage selectHeartIconOption()
	{
		elementUtilities.clickOnElement(Hearticon);	
		return new LoginPage(driver);
	}
	
	public ContactUsPage SelectPhoneIcon()
	{
		elementUtilities.clickOnElement(PhoneIcon);
	    return new ContactUsPage(driver);
	}
	
	public LoginPage selectLoginOption()
	{
		elementUtilities.clickOnElement(loginOption);
	    return new LoginPage(driver);
	}
	public void clickonMyAccountDropMenu()
	{
		elementUtilities.clickOnElement(myAccountDropMenu);
		
	}
	public RegisterPage clickonregister()
	{
		elementUtilities.clickOnElement(clickonRegister);
		return new RegisterPage(driver);
	}

}
