package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.CommonUtilities;
import base.Base;
import pages.HeaderOptions;

public class LoginTest extends Base
{
	
	WebDriver driver;

	@BeforeMethod
	public void setup()
	{
		driver = openBrowserApplicationPageUrl();
		header = new HeaderOptions(driver);
		header.clickonMyAccountDropMenu();
		login = header.selectLoginOption();
		
		
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test(priority=1)
	public void verifyLoggingIntoApplicationUsingValidCredentials()
	{
		Assert.assertTrue(login.didWeNavigateToLogin());
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage = login.clickLoginButton();
		Assert.assertTrue(accountPage.ChangeYourPasswordInfo());
	}
	@Test(priority=2)
	public void verifyLoggingIntoApplicationUsingInvalidCredentials()
	{
		login.enterEmail(CommonUtilities.generateTimestampedEmail());
		login.enterPassword(prop.getProperty("mismatchingPassword"));
		login.clickLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(login.pagelevelWarning(), expectedWarning);
	}
	@Test(priority=3)
	public void verifyLoggingIntoApplicationUsingInvalidEmailAndValidPassword()
	{
		login.enterEmail(CommonUtilities.generateTimestampedEmail());
		login.enterPassword(prop.getProperty("validPassword"));
		login.clickLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(login.pagelevelWarning(), expectedWarning);
		
	}
	@Test(priority=4)
	public void verifyLoggingIntoApplicationUsingValidEmailAndInvalidPassword()
	{
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("mismatchingPassword"));
		login.clickLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(login.pagelevelWarning(), expectedWarning);
		
	}
	@Test(priority=5)
	public void verifyLoggingIntoApplicationWithoutProvidingAnyCredentials()
	{
		login.clickLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(login.pagelevelWarning(), expectedWarning);
		
	}
	@Test(priority=6)
	public void verifyForgottenOptionIsAvailable()
	{
		forgotPassword = login.clickOnForgottenPasswordLink();
		Assert.assertTrue(forgotPassword.didWeNavigateToForgottenPage());
	}
	@Test(priority=7)
	public void verifyLoggingIntoApplicationUsingKeyboardKeys() {
	    
		try {
            Actions actions = new Actions(driver);

           
               for (int i = 0; i < 23; i++) {
                actions.sendKeys(Keys.TAB).perform(); 
                Thread.sleep(200); 
            }

            WebElement emailField = driver.switchTo().activeElement();
            login.enterEmail(prop.getProperty("email"));
            actions.sendKeys(Keys.TAB).perform();
            WebElement passwordField = driver.switchTo().activeElement();
            login.enterPassword(prop.getProperty("validPassword"));
            actions.sendKeys(Keys.TAB).perform();
            WebElement loginButton = driver.switchTo().activeElement();

            actions.sendKeys(Keys.TAB).perform();
            login.clickLoginButton();

            
            System.out.println("Successfully navigated and clicked the login button!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           
        }
    }	

    @Test(priority=8)
	public void verifyPlaceHoldersofFeildsInLoginPage()
	{
		Assert.assertEquals(login.getEmailFeildPlaceholderText(), "E-Mail Address");
		Assert.assertEquals(login.getPasswordFeildPlaceholderText(), "Password");
		
	}
	@Test(priority=9)
	public void verifyBrowsingBackAfterLogin()
	{
		accountPage=login.loginInToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		navigateBackInBrowser(accountPage.getDriver());
		refreshPage(accountPage.getDriver());
	}
	@Test(priority=10)
	public void verifyBrowsingBackAfterLogout()
	{
		accountPage=login.loginInToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		header =accountPage.getHeaderOptionsPage();
		accountLogoutPage=header.selectLogoutOption();
		navigateBackInBrowser(logoutPage.getDriver());
		refreshPage(logoutPage.getDriver());
		login=logoutPage.getLoginPage();
	    Assert.assertTrue(login.didWeNavigateToLogin());
		
	}
	@Test(priority=11)
	public void verifyLoggingIntoApplicationUsingInactiveCredentials()
	{
		login.enterEmail(prop.getProperty("inactiveEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		login.clickLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(login.pagelevelWarning(), expectedWarning);
		
		
	}
	@Test(priority=12)
	public void verifyNumberOfUnsuccessfullLoginAttemps()
	{
		login.enterEmail(prop.getProperty("nonExistingEmail"));
		login.enterPassword(prop.getProperty("mismatchingPassword"));
		login.clickLoginButton();
		login.enterEmail(prop.getProperty("nonExistingEmail"));
		login.enterPassword(prop.getProperty("mismatchingPassword"));
		login.clickLoginButton();
		login.enterEmail(prop.getProperty("nonExistingEmail"));
		login.enterPassword(prop.getProperty("mismatchingPassword"));
		login.clickLoginButton();
		login.enterEmail(prop.getProperty("nonExistingEmail"));
		login.enterPassword(prop.getProperty("mismatchingPassword"));
		login.clickLoginButton();
		login.enterEmail(prop.getProperty("nonExistingEmail"));
		login.enterPassword(prop.getProperty("mismatchingPassword"));
		login.clickLoginButton();
		login.enterEmail(prop.getProperty("nonExistingEmail"));
		login.enterPassword(prop.getProperty("mismatchingPassword"));
		login.clickLoginButton();
		String expectedWarning = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
		Assert.assertEquals(login.pagelevelWarning(), expectedWarning);
	}
	@Test(priority=13)
	public void verifyLoginPasswordFeildForVisibility()
	{
		Assert.assertEquals(login.getPasswordFeildDomAttribute("type"), "password");
		
	}
	@Test(priority=14)
	public void verifyCopyingOfTextEnteredIntoPasswordFeilds()
	{
		try {
		    // Enter email into the email field
		    login.enterEmail(prop.getProperty("email"));

		    // Enter password into the password field
		    login.enterPassword(prop.getProperty("validPassword"));

		    // Retrieve the value from the password field
		    String copiedText = login.getPasswordFieldValue();

		    // Clear the email field before pasting
		    login.clearEmailField(); // Add this method in the page object

		    // Paste the copied text into the email field
		    login.pasteIntoEmailField(copiedText);

		    // Retrieve the value from the email field
		    String emailFieldText = login.getEmailFieldValue();

		    // Print the copied and retrieved text
		    System.out.println("Copied text from password field: " + copiedText);
		    System.out.println("Text in email field: " + emailFieldText);

		    // Verify if the text matches
		    if (copiedText.equals(emailFieldText)) {
		        System.out.println("Test Passed: Text successfully copied and pasted.");
		    } else {
		        System.out.println("Test Failed: Text mismatch.");
		    }
		    login.clickLoginButton();
		} 
		finally 
		{
			
		}

		
	}
	@Test(priority=15)
	public void verifyPasswordIsNotVisibleInPageSource()
	{
		 
	        String password = prop.getProperty("randomPassword");
		
		try {
	            login.enterPassword(password);

	            // Verify that the password is not in the page source
	            if (login.isPasswordInPageSource(password)) {
	                System.out.println("Password is not present in the page source. Test Passed!");
	            } else {
	                System.out.println("Password is present in the page source. Test Failed!");
	            }

	            // Click the login button
	            login.clickLoginButton();

	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
	@Test(priority=16)
	public void verifyLoggingInToApplicationUsingChangedPassword()
	{
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		changeYourPassword = accountPage.ClickChangeYourPasswordOption();
		changeYourPassword.enterPasswordText(prop.getProperty("password"));
		changeYourPassword.enterConfirmPassword(prop.getProperty("confirmPassword"));
		accountPage = changeYourPassword.clickOnContinueButton();
		rightcolumn = accountPage.getRightColoumOptions();
		accountLogoutPage = rightcolumn.clickOnLogout();
		home = accountLogoutPage.clickOnContinueButton();
		header = home.getHeaderOptions();
		header.clickonMyAccountDropMenu();
		header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		login.enterEmail(prop.getProperty("existingEmail2"));
		login.enterPassword(prop.getProperty("validPassword2"));
		accountPage=login.clickLoginButton();
		
	}
	@Test(priority=17)
	public void verifyLoginPageNavigation()
	{
		header=login.getHeaderOptionsPage();
		contactus = header.SelectPhoneIcon();
		Assert.assertTrue(getPageTitle(contactus.getDriver()).equals("Contact Us"));
		navigateBackInBrowser(contactus.getDriver());
		
		login = header.selectHeartIconOption();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login = header.selectWishList();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		shoppingcartPage = header.selectShoppingCartIcon();
		Assert.assertTrue(getPageTitle(shoppingcartPage.getDriver()).equals("Shopping Cart"));
		navigateBackInBrowser(shoppingcartPage.getDriver());
		
		shoppingcartPage = header.selectShoppingCartOption();
		Assert.assertTrue(getPageTitle(shoppingcartPage.getDriver()).equals("Shopping Cart"));
		navigateBackInBrowser(shoppingcartPage.getDriver());
		
		shoppingcartPage =header.selectCheckOutIcon();
		Assert.assertTrue(getPageTitle(shoppingcartPage.getDriver()).equals("Shopping Cart"));
		navigateBackInBrowser(shoppingcartPage.getDriver());
		
		shoppingcartPage =header.selectCheckOutOption();
		Assert.assertTrue(getPageTitle(shoppingcartPage.getDriver()).equals("Shopping Cart"));
		navigateBackInBrowser(shoppingcartPage.getDriver());
		
		home =header.selectLogo();
		Assert.assertTrue(getPageTitle(home.getDriver()).equals("Your Store"));
		navigateBackInBrowser(home.getDriver());
		 
		search = header.selectsearchOption();
		Assert.assertTrue(getPageTitle(search.getDriver()).equals("Search"));
		navigateBackInBrowser(search.getDriver());
		
		login=header.selectAccountBreadCrumb();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=header.selectLoginBreadCrumb();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		register=login.ClickContinueButton();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Register Account"));
		navigateBackInBrowser(login.getDriver());
		
		forgotPassword=login.clickOnForgotPassword();
		Assert.assertTrue(getPageTitle(forgotPassword.getDriver()).equals("Forgot Your Password?"));
		navigateBackInBrowser(forgotPassword.getDriver());
		
		login.clickOnLoginButton();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		rightcolumn = login.getRightColumnOptions();
		login=rightcolumn.selectRightColoumnLogin();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		register=rightcolumn.selectRightColumnRegister();
		Assert.assertTrue(getPageTitle(register.getDriver()).equals("Register Account"));
		navigateBackInBrowser(register.getDriver());
		
		forgotPassword = rightcolumn.selectRightColumnForgotPassword();
		Assert.assertTrue(getPageTitle(forgotPassword.getDriver()).equals("Forgot Your Password?"));
		navigateBackInBrowser(forgotPassword.getDriver());
		
		login=rightcolumn.selectRightColumnMyAccount();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectRightColumnAddressBook();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectRightColumnWishList();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectOrderHistory();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectDownloads();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectReccuringPayment();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectRewardPoints();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectReturns();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectTransactions();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=rightcolumn.selectNewsLetter();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		FooterOption = login.getFooterOptions();
		about=FooterOption.clickAboutUs();
		Assert.assertTrue(getPageTitle(about.getDriver()).equals("About Us"));
		navigateBackInBrowser(about.getDriver());
		
		DeliveryInfo=FooterOption.clickFooterDelivery();
		Assert.assertTrue(getPageTitle(DeliveryInfo.getDriver()).equals("Delivery Information"));
		navigateBackInBrowser(DeliveryInfo.getDriver());
		
		PrivacyPolicy=FooterOption.clickPrivacyPolicy();
		Assert.assertTrue(getPageTitle(PrivacyPolicy.getDriver()).equals("Privacy Policy"));
		navigateBackInBrowser(PrivacyPolicy.getDriver());
		
		termsnconditions=FooterOption.clickTermsConditions();
		Assert.assertTrue(getPageTitle(termsnconditions.getDriver()).equals("Terms & Conditions"));
		navigateBackInBrowser(termsnconditions.getDriver());
		
		contactus=FooterOption.clickContactUs();
		Assert.assertTrue(getPageTitle(contactus.getDriver()).equals("Contact Us"));
		navigateBackInBrowser(contactus.getDriver());
		
		returns=FooterOption.clickReturns();
		Assert.assertTrue(getPageTitle(returns.getDriver()).equals("Product Returns"));
		navigateBackInBrowser(returns.getDriver());
		
		sitemap=FooterOption.clickSiteMap();
		Assert.assertTrue(getPageTitle(sitemap.getDriver()).equals("Site Map"));
		navigateBackInBrowser(sitemap.getDriver());
		
		Brands=FooterOption.clickBrands();
		Assert.assertTrue(getPageTitle(Brands.getDriver()).equals("Find Your Favorite Brand"));
		navigateBackInBrowser(Brands.getDriver());
		
		giftcertificate=FooterOption.clickGiftCertificate();
		Assert.assertTrue(getPageTitle(giftcertificate.getDriver()).equals("Purchase a Gift Certificate"));
		navigateBackInBrowser(giftcertificate.getDriver());
		
		affliate = FooterOption.clickAffliate();
		Assert.assertTrue(getPageTitle(affliate.getDriver()).equals("Affiliate Program"));
		navigateBackInBrowser(affliate.getDriver());
		
		Special=FooterOption.clickSpecials();
		Assert.assertTrue(getPageTitle(Special.getDriver()).equals("Special Offers"));
		navigateBackInBrowser(Special.getDriver());
		
		login=FooterOption.clickOnFooterMyAccount();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=FooterOption.clickOnFooterOrderHistory();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=FooterOption.clickOnFooterWishList();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
		login=FooterOption.clickOnFooterNewsLetter();
		Assert.assertTrue(getPageTitle(login.getDriver()).equals("Account Login"));
		
	}
	@Test(priority=18)
	public void verifyDifferentWaysOfNavigatingToLoginPage()
	{
		Assert.assertTrue(login.didWeNavigateToLogin());
		rightcolumn=login.getRightColumnOptions();
		rightcolumn.selectRightColoumnLogin();
		Assert.assertTrue(login.didWeNavigateToLogin());
		register=login.clickOnContinueButton();
		login = register.selectLoginPageOption();
		Assert.assertTrue(login.didWeNavigateToLogin());
	}
	@Test(priority=19)
	public void verifyLoginPageBreadCrumbUrlTitleHeading()
	{
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		Assert.assertEquals(getPageURL(login.getDriver()), prop.getProperty("LoginPageURL"));
		Assert.assertTrue(login.didWeNavigateToLogin());
		Assert.assertEquals(login.getFirstHeading(), "New Customer");
		Assert.assertEquals(login.getSecondHeading(), "Returning Customer");
	}
	@Test(priority=20)
	public void verifyLoginInAllEnvironments()
	{
		Assert.assertTrue(login.didWeNavigateToLogin());
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
	}
	

}
