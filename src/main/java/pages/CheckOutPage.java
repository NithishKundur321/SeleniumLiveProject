package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class CheckOutPage extends RootPage
{
	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
   @FindBy(id="button-payment-address")	
   private WebElement BillingDetailsButton;
   
   @FindBy(id="button-shipping-address")
   private WebElement DeliveryDetailsButton;
   
   @FindBy(id="button-shipping-method")
   private WebElement DeliveryMethodButton;
   
   @FindBy(name="agree")
   private WebElement TermsandConditions;
   
   @FindBy(id="button-payment-method")
   private WebElement buttonPaymentMethod;
   
   @FindBy(id="button-confirm")
   private WebElement confirmOrder;
   
   public CheckOutSuccessPage clickOnConfirmOrderButton()
   {
	   elementUtilities.clickOnElement(confirmOrder);
	   return new CheckOutSuccessPage(driver);
   }
   
   public void clickOnPaymentMethod()
   {
	   elementUtilities.clickOnElement(buttonPaymentMethod); 
   }
   
   public void selectTermsAndConditions()
   {
	   elementUtilities.clickOnElement(TermsandConditions); 
   }
   
   public void clickOnDeliveryDetailsButton()
   {
	   elementUtilities.clickOnElement(DeliveryDetailsButton);
   }
   public void clickOnBillingDetailsContinueButton()
   {
	   elementUtilities.clickOnElement(BillingDetailsButton);
   }
   public void clickOnDeliveryMethod()
   {
	   elementUtilities.clickOnElement(DeliveryMethodButton);
   }
   
   
    
  
}
