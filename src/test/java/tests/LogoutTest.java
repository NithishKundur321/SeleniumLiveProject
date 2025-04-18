package tests;

import java.io.File;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.CommonUtilities;
import base.Base;
import pages.HeaderOptions;
import pages.LoginPage;

public class LogoutTest extends Base
{
	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		driver = openBrowserApplicationPageUrl();
		header = new HeaderOptions(driver);
		login = new LoginPage(driver);
		
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyLoggingOutUsingMyAccountLogoutOption()
	{
		header.clickonMyAccountDropMenu();
		header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		header=accountPage.getHeaderOptionsPage();
		header.clickonMyAccountDropMenu();
		accountLogoutPage=header.selectLogoutOption();
		home=accountLogoutPage.clickOnContinueButton();
	}
	@Test(priority=2)
	public void verifyLoggingOutUsingRightColumnLogoutOption() throws InterruptedException
	{
		header.clickonMyAccountDropMenu();
		header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		rightcolumn=accountPage.getRightColoumOptions();
		accountLogoutPage=rightcolumn.clickOnLogout();
		home=accountLogoutPage.clickOnContinueButton();
		
	}
	@Test(priority=3)
	public void verifyLoggingOutAndBrowsingBack() 
	{
		header.clickonMyAccountDropMenu();
		header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		header=accountPage.getHeaderOptionsPage();
		header.clickonMyAccountDropMenu();
		accountLogoutPage=header.selectLogoutOption();
		navigateBackInBrowser(driver);
		refreshPage(driver);
		header=accountLogoutPage.getHeaderOptions();
		header.clickonMyAccountDropMenu();
	}
	@Test(priority=4)
	public void verifyNoLogoutOptionBeforeLogginginMyAccountDropMenu()
	{
		header.clickonMyAccountDropMenu();
		Assert.assertFalse(header.isLogoutOptionunderMyAccountDropdownMenuisDisplayed());
	}
	@Test(priority=5)
	public void verifyNoLogoutOptionBeforeLoggingInRightColumnOptions()
	{
		header.clickonMyAccountDropMenu();
		register=header.clickonregister();
		rightcolumn=register.getRightColoumOptions();
		Assert.assertFalse(rightcolumn.isLogoutOptionDisplayed());
	}
	@Test(priority=6)
	public void verifyLoginImmediateAfterLogout()
	{
		header.clickonMyAccountDropMenu();
		header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		header=accountPage.getHeaderOptionsPage();
		header.clickonMyAccountDropMenu();
		accountLogoutPage=header.selectLogoutOption();
		header=accountLogoutPage.getHeaderOptions();
		header.clickonMyAccountDropMenu();
	    login=header.selectLoginOption();
	    login.enterEmail(prop.getProperty("existingEmail"));
	    login.enterPassword(prop.getProperty("validPassword"));
	    login.clickLoginButton();
	}
	@Test(priority=7)
	public void verifyBreadcrumbTitleURLAndHeadingOfAccountLogoutPage()
	{
		header.clickonMyAccountDropMenu();
		header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		header=accountPage.getHeaderOptionsPage();
		header.clickonMyAccountDropMenu();
		accountLogoutPage=header.selectLogoutOption();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Account Logout");
		Assert.assertEquals(getPageURL(accountLogoutPage.getDriver()), prop.getProperty("LogoutPageURL"));
		Assert.assertEquals(accountLogoutPage.getPageHeading(), "Account Logout");
	}
	@Test(priority=8)
	public void verifyLogoutUI() throws IOException
	{
		header.clickonMyAccountDropMenu();
		header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		header=accountPage.getHeaderOptionsPage();
		header.clickonMyAccountDropMenu();
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLogoutOptions.png"));
			
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			CommonUtilities.takeScreenshot(driver,System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLogoutOptions.png"));
			
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			CommonUtilities.takeScreenshot(driver,System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLogoutOptions.png"));
		}
	}
	@Test(priority=9)
	public void verifyLogoutPageinAllEnvironments()
	{
		header.clickonMyAccountDropMenu();
		header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		header=accountPage.getHeaderOptionsPage();
		header.clickonMyAccountDropMenu();
		accountLogoutPage=header.selectLogoutOption();
		header=accountLogoutPage.getHeaderOptions();
		header.clickonMyAccountDropMenu();
		home=accountLogoutPage.clickOnContinueButton();
	}
	

}
