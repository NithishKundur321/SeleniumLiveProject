package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ShoppingCartPage extends RootPage
{
	WebDriver driver;
	public ShoppingCartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[text()='Checkout']")
	private WebElement checkoutButton;
	
	public CheckOutPage clickOnCheckOutButton()
	{
		elementUtilities.clickOnElement(checkoutButton);
		return new CheckOutPage(driver);
	}

}
