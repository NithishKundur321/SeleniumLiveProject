package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class SpecialOffers extends RootPage
{
	WebDriver driver;
	public SpecialOffers(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


}
