package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class GiftCertificate extends RootPage
{
	WebDriver driver;
	public GiftCertificate(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


}
