package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class myWishListPage extends RootPage
{
	WebDriver driver;
	public myWishListPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	


}
