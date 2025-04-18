package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class NewsLetterPage extends RootPage
{
	WebDriver driver;
	public NewsLetterPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Newsletter']")
	private WebElement NewsletterClick;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement isNewsletterSelected;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	private WebElement isNoNewLetterOptionSelected;
	
	public boolean newsletterDisplayed()
	{
	   return elementUtilities.isElementDisplayed(NewsletterClick);
	}
	
	public boolean isNewsletterSelected()
	{
		return elementUtilities.isElementInSelectedState(isNewsletterSelected);
		
	}
	public boolean isNoNewsletterSelected()
	{
		return elementUtilities.isElementInSelectedState(isNoNewLetterOptionSelected);
	}
	
	

}
