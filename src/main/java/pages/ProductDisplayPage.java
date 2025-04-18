package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.CommonUtilities;
import pages.root.RootPage;

public class ProductDisplayPage extends RootPage
{
	WebDriver driver;
	public ProductDisplayPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="button-cart")
	WebElement addtoCartButton;
	
	@FindBy(xpath="//a[text()='shopping cart']")
	WebElement shoppingcartoption;
	
	@FindBy(xpath="//a[text()='wish list']")
	WebElement wishlistOption;
	
	@FindBy(xpath="//a[text()='product comparison']")
	WebElement productcomparison;
	
	public boolean IsProductComparisonOptionDisplayedOnTheSuccessPage()
	{
		return elementUtilities.isElementDisplayed(productcomparison);
	}
	
	public boolean IsWishListOptionDisplayedOnTheSuccessPage()
	{
		return elementUtilities.isElementDisplayed(wishlistOption);	
	}
	public boolean didWeNavigateToProductDisplayPage()
	{
		return elementUtilities.isElementDisplayed(addtoCartButton);
	}
	public void clickOnAddToCartButton()
	{
		elementUtilities.clickOnElement(addtoCartButton);
	}
	public ShoppingCartPage selectShoppingCartOptionOnSuccessPage()
	{
		elementUtilities.waitForElementAndClick(shoppingcartoption,CommonUtilities.AVERAGE_TIME);
		return new ShoppingCartPage(driver);
	}
	public boolean IsShoppingCartOptionDisplayedOnTheSuccessPage()
	{
		return elementUtilities.isElementDisplayed(shoppingcartoption);
	}

}
