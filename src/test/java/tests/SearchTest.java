package tests;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.CommonUtilities;
import base.Base;
import pages.AddressBookPage;
import pages.AffiliatePage;
import pages.CheckOutSuccessPage;
import pages.FooterOptionsPage;
import pages.HeaderOptions;
import pages.LoginPage;
import pages.ProductComparisonPage;
import pages.ProductDisplayPage;
import pages.searchPage;


public class SearchTest extends Base
{
	WebDriver driver;
	AddressBookPage addressBookPage;
	CheckOutSuccessPage checkoutSuccessPage;
	AffiliatePage affiliatePage;
	searchPage search;
	@BeforeMethod
	public void setup()
	{
		driver = openBrowserApplicationPageUrl();
		header = new HeaderOptions(driver);
		addressBookPage = new AddressBookPage(driver);
		checkoutSuccessPage = new CheckOutSuccessPage(driver);
		affiliatePage = new AffiliatePage(driver);
		search= new searchPage(driver);
		
	}
	@Test(priority=1)
	public void verifySearchWithAnExistingProduct()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProduct"));
		search=header.clickSearchButton();
		Assert.assertTrue(search.didWeNavigateToSearchResultPage());
		Assert.assertTrue(search.isProductDisplayedInSearchResult());
	}
	@Test(priority=2)
	public void verifySearchWithNonExistingProduct()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("nonExistingProduct"));
		search=header.clickSearchButton();
		Assert.assertTrue(search.didWeNavigateToSearchResultPage());
		Assert.assertEquals(search.getnoProductMessage(),"There is no product that matches the search criteria.");
	}
	@Test(priority=3)
	public void verifySearchWithoutEnterAnyProduct()
	{
		search=header.clickSearchButton();
		Assert.assertTrue(search.didWeNavigateToSearchResultPage());
		Assert.assertEquals(search.getnoProductMessage(),"There is no product that matches the search criteria.");
	}
	@Test(priority=4)
	public void verifyProductSearchAfterLogin()
	{
		header.clickonMyAccountDropMenu();
		login=header.selectLoginOption();
		login.enterEmail(prop.getProperty("existingEmail"));
		login.enterPassword(prop.getProperty("validPassword"));
		accountPage=login.clickLoginButton();
		header=accountPage.getHeaderOptionsPage();
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProduct"));
		search=header.clickSearchButton();
		Assert.assertTrue(search.didWeNavigateToSearchResultPage());
		Assert.assertTrue(search.isProductDisplayedInSearchResult());
	}
	@Test(priority=5)
	public void verifyProductSearchResultingMultipleProducts()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProduct2"));
		search=header.clickSearchButton();
		Assert.assertTrue(search.didWeNavigateToSearchResultPage());
		Assert.assertTrue(search.getProductCount()>0);
	}
	@Test(priority=6)
	public void verifySearchFuntionalityFeildPlaceholder()
	{
		Assert.assertEquals(header.getSearchBoxFeildPlaceholderText(), "Search");
		search=header.clickSearchButton();
		Assert.assertEquals(search.getsearchCriteriaFeildPlaceholderText(), "Keywords");
	}
	@Test(priority=7)
	public void verifySearchFunctionalityUsingSearchCriteriaFeild()
	{
		search=header.clickSearchButton();
		search.enterTextInProductDescriptionInToSearchCriteriaFeild(prop.getProperty("existingProduct"));
		search.clickOnSearchButton();
		Assert.assertTrue(search.isProductDisplayedInSearchResult());
	}
	@Test(priority=8)
	public void verifySearchUsingTextIntoProductDescription()
	{
		search=header.clickSearchButton();
		search.enterTextInProductDescriptionInToSearchCriteriaFeild(prop.getProperty("textInProductDescription"));
		search.selectProductDescription();
		search.clickOnSearchButton();
		search.isProductHavingTextInItsDescriptionDisplayedInSearchResults();
		
	}
	@Test(priority=9)
	public void verifySearchBySelectingTheCategory()
	{
		search=header.clickSearchButton();
		search.enterTextInProductDescriptionInToSearchCriteriaFeild(prop.getProperty("existingProductThree"));
		search.selectOptionFromCategoryDropDownFeild(CommonUtilities.convertToInteger(prop.getProperty("correctCategoryIndex")));
		search.clickOnSearchButton();
		search.selectOptionFromCategoryDropDownFeild(CommonUtilities.convertToInteger(prop.getProperty("wrongCategoryIndex")));
		search.clickOnSearchButton();
		Assert.assertEquals(search.getnoProductMessage(), "There is no product that matches the search criteria.");
	}
	@Test(priority=10)
	public void verifySearchSelectingToSearchinSubCategories()
	{
		search = header.clickSearchButton();
		search.enterTextInProductDescriptionInToSearchCriteriaFeild(prop.getProperty("existingProductThree"));
		search.selectOptionFromCategoryDropDownFeild(prop.getProperty("superCategory"));
		search.clickOnSearchButton();
		Assert.assertEquals(search.getnoProductMessage(), "There is no product that matches the search criteria.");
		search.selectSearchInSubCategories();
		search.clickOnSearchButton();
		Assert.assertTrue(search.isProductFromCorrectCategoryDisplayedInSearchResult());
	}
	@Test(priority=11)
	public void verifyListAndGridViewsInSearchResultPageHavingOneProduct()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProductThree"));
		search = header.clickSearchButton();
		search.selectListOption();
		Assert.assertTrue(search.getProductCount()==1);
		search.clickOnAddToCartOption();
		search.clickOnAddToWishListOption();
		search.clickOnCompareThisProduct();
		productDisplayPage=search.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = search.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		refreshPage(search.getDriver());
		search.selectGridOption();
		Assert.assertTrue(search.getProductCount()==1);
		search.clickOnAddToCartOption();
		search.clickOnAddToWishListOption();
		search.clickOnCompareThisProduct();
		productDisplayPage=search.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
	    productDisplayPage=search.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
	}
	@Test(priority=12)
	public void verifyListandGridViewsWhenMultipleProductsareDisplayed()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProduct2"));
		search=header.clickSearchButton();
		Assert.assertTrue(search.getProductCount()>1);
		search.selectListOption();
		Assert.assertTrue(search.getProductCount()>1);
		search.selectGridOption();
		Assert.assertTrue(search.getProductCount()>1);
	}
	@Test(priority=13)
	public void verifyNavigationToProductComparisonPageFromSearchResultPage()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProductThree"));
		search=header.clickSearchButton();
		productComparisonPage=search.selectProductComparison();
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
	}
	@Test(priority=14)
	public void verifyAllSortingOptionsInSearchResultPage()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProduct2"));
		search=header.clickSearchButton();
		search.selectSortOptionInDropdownFeild("Name (Z - A)");
	}
	@Test(priority=15)
	public void verifyShowProductsByLimitingCount() 
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProduct2"));
		search=header.clickSearchButton();
		
		String productLimitOne = "20";
		search.selectShowOptionInDropdownFeild(productLimitOne);
		String selectedValue = search.getSelectedShowOption(); 
        System.out.println("Expected: " + productLimitOne + ", Actual: " + selectedValue);
		Assert.assertEquals(selectedValue, productLimitOne, "Dropdown selection did not update correctly");
		
		String productLimitTwo = "25";
		search.selectShowOptionInDropdownFeild(productLimitTwo);
		String selectedValue2 = search.getSelectedShowOption();
		System.out.println("Expected: " + productLimitTwo + ", Actual: " + selectedValue2);
		Assert.assertEquals(selectedValue2, productLimitTwo, "Dropdown selection did not update correctly");
		
		String productLimitThree = "50";
		search.selectShowOptionInDropdownFeild(productLimitThree);
		String selectedValue3 = search.getSelectedShowOption();
		System.out.println("Expected: " + productLimitThree + ", Actual: " + selectedValue3);
		Assert.assertEquals(selectedValue3, productLimitThree, "Dropdown selection did not update correctly");
	    
		String productLimitFour = "75";
		search.selectShowOptionInDropdownFeild(productLimitFour);
		String selectedValue4 = search.getSelectedShowOption();
		System.out.println("Expected: " + productLimitFour + ", Actual: " + selectedValue4);
		Assert.assertEquals(selectedValue4, productLimitFour, "Dropdown selection did not update correctly");
		
		String productLimitFive = "100";
		search.selectShowOptionInDropdownFeild(productLimitFive);
		String selectedValue5 = search.getSelectedShowOption();
		System.out.println("Expected: " + productLimitFive + ", Actual: " + selectedValue5);
		Assert.assertEquals(selectedValue5, productLimitFive, "Dropdown selection did not update correctly");
	}
	@Test(priority=16)
	public void verifyDisplayingOfSearchFeildAndSearchButtonOnAllPagesOfApplication() throws InterruptedException 
	{
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("contactUsPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("registerPageURL"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("LoginPageURL"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("forgottenPasswordPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		register=header.navigateToRegisterPage();
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		String emailAddress=CommonUtilities.generateTimestampedEmail();
		register.enteremailFeild(emailAddress);
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
		register.enterPassword(prop.getProperty("password"));
		register.enterconfirmPassword(prop.getProperty("confirmPassword"));
		register.isprivacyPolicyFeildSelected();
		accountsuccess=register.clickContinueButton();
		rightcolumn=accountsuccess.getRightColumnOptions();
		accountPage=rightcolumn.clickOnMyAccountOptionAfterLogin();
		accountPage.clickOnEditYourAccountInfoPage();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateBackInBrowser(header.getDriver());
		accountPage.ClickChangeYourPasswordOption();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateBackInBrowser(header.getDriver());
		accountPage.clickOnModifyBookEntries();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		addAddressPage=addressBookPage.clickOnNewAddressButton();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		addAddressPage.enterFirstName(prop.getProperty("firstName"));
		addAddressPage.enterLastName(prop.getProperty("lastName"));
		addAddressPage.enterCompanyFeild(prop.getProperty("company"));
		addAddressPage.enterAddress1(prop.getProperty("Address1"));
		addAddressPage.enterAddress2(prop.getProperty("Address2"));
		addAddressPage.enterCity(prop.getProperty("City"));
		addAddressPage.enterPostCode(prop.getProperty("PostCode"));
		addAddressPage.selectOptionFromCountryDropDownFeild(prop.getProperty("Country"));
		addAddressPage.selectOptionFromStateDropDownFeild(prop.getProperty("State"));
		addAddressPage.clickOnContinueButton();
		editaddressPage=addressBookPage.clickOnEditButton();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		accountPage=editaddressPage.clickOnAccountBreadCrumb();
		accountPage.clickOnModifyYourWishList();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProductFour"));
		search=header.clickSearchButton();
		productDisplayPage=search.clickOnProductOneName();
		productDisplayPage.clickOnAddToCartButton();
		shoppingcartPage=productDisplayPage.selectShoppingCartOptionOnSuccessPage();
		checkoutPage=shoppingcartPage.clickOnCheckOutButton();
		Thread.sleep(4000);
		checkoutPage.clickOnBillingDetailsContinueButton();
		Thread.sleep(4000);
		checkoutPage.clickOnDeliveryDetailsButton();
		Thread.sleep(4000);
		checkoutPage.clickOnDeliveryMethod();
		Thread.sleep(4000);
		checkoutPage.selectTermsAndConditions();
		checkoutPage.clickOnPaymentMethod();
		Thread.sleep(4000);
		checkoutPage.clickOnConfirmOrderButton();
		checkoutSuccessPage.clickOnMyAccount();
		orderhistoryPage=accountPage.clickOnViewYourOrderHistoryOption();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		orderinformationPage=orderhistoryPage.selectViewOption();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		productreturnPage=orderinformationPage.selectReturnOption();
		productreturnPage.selectReasonForReturn();
		productreturnPage.clickOnSubmitButton();
		rightcolumn=productreturnPage.getRightColumnOptions();
		rightcolumn.clickOnMyAccountOption();
		accountPage.clickonDownloadsOption();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateBackInBrowser(header.getDriver());
		accountPage.clickOnRewardPointsOption();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateBackInBrowser(header.getDriver());
		productreturnPage=accountPage.clickOnViewYourReturnRequestOption();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		returninfoPage=productreturnPage.selectViewOption();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		returninfoPage.clickOnAccountBreadCrumb();
		accountPage.clickonYourTransaction();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateBackInBrowser(header.getDriver());
		affiliatePage=accountPage.clickOnRegisterForAnAffliateAccountOption();
		affiliatePage.enterchequePayeeName(prop.getProperty("firstName"));
		affiliatePage.selectagreeBox();
		accountPage=affiliatePage.clickContinueButton();
		affiliatePage=accountPage.clickOnEditYourAffiliateInformationOption();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		affiliatePage.enterchequePayeeName(prop.getProperty("UpdateFirstName"));
		accountPage=affiliatePage.clickContinueButton();
		accountPage.clickOnCustomAffiliateTrackingCode();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateBackInBrowser(header.getDriver());
		accountPage.clickOnSubscribeUnSubcribeToNewsLetter();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateBackInBrowser(header.getDriver());
		rightcolumn=accountPage.getRightColoumOptions();
		accountLogoutPage=rightcolumn.clickOnLogout();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("aboutusPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("DeliveryInformationPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("privacyPolicyPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("Term&Conditions"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("brandsPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("sitemapPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("DesktopCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("PCSubCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("MACSubCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("LaptopAndNoteBookCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("MacSubCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("WindowSubCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("componentsCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("mickandTrackballsPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("monitorsSubCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("subsubCategoryPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("specialoffersPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("brandsPage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("giftCertificatePage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		navigateToPage(prop.getProperty("affiliatePage"));
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProduct"));
		search=header.clickSearchButton();
		productDisplayPage=search.clickOnProductOneName();
		productDisplayPage.clickOnAddToCartButton();
		shoppingcartPage=productDisplayPage.selectShoppingCartOptionOnSuccessPage();
		checkoutPage=shoppingcartPage.clickOnCheckOutButton();
		Assert.assertTrue(header.areSearchBoxFeildAndSearchButtonFeildDisplayed());
	}
	@Test(priority=17)
	public void verifyNavigatingToSearchPageFromSiteMapPage()
	{
		FooterOption = new FooterOptionsPage(header.getDriver());
		sitemap=FooterOption.selectSiteMapOption();
		search=sitemap.clickOnSearchOption();
		Assert.assertTrue(search.didWeNavigateToSearchResultPage());
	}
	@Test(priority=18)
	public void verifyBreadcrumbOptionInSearchResultPage()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProductThree"));
		header.clickSearchButton();
		search=search.clickOnBreadCrumb();
		Assert.assertTrue(search.didWeNavigateToSearchResultPage());
	}
	@Test(priority=19)
	public void verifyUsingAllOptionsOnSearchResultsPageUsingKeyboardKeys()
	{
		header.clickSearchButton();
		actions = clickKeyboradKeyMultipleTimes(header.getDriver(),Keys.TAB,21);
		actions = typeTextUsingActions(actions,prop.getProperty("existingProduct"));
		actions = clickKeyboradKeyMultipleTimes(actions,Keys.TAB,1);
		actions = clickKeyboradKeyMultipleTimes(actions,Keys.ARROW_DOWN,1);
		actions = clickKeyboradKeyMultipleTimes(actions,Keys.TAB,1);
		actions = clickKeyboradKeyMultipleTimes(actions,Keys.SPACE,1);
		actions = clickKeyboradKeyMultipleTimes(actions,Keys.TAB,2);
		actions = clickKeyboradKeyMultipleTimes(actions,Keys.ENTER,1);
		search = new searchPage(header.getDriver());
		Assert.assertTrue(search.isProductDisplayedInSearchResult());
		actions = clickKeyboradKeyMultipleTimes(getActions(driver), Keys.TAB, 21);
		actions = typeTextUsingActions(actions, prop.getProperty("textInProductDescription"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 3);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.SPACE, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(search.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 26);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(search.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(search.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		productComparisonPage = new ProductComparisonPage(search.getDriver());
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
		navigateBackInBrowser(productComparisonPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_DOWN, 1);
		Assert.assertTrue(search.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 30);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_DOWN, 1);
		Assert.assertTrue(search.isProductFromCorrectCategoryDisplayedInSearchResult());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 31);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		productDisplayPage = new ProductDisplayPage(search.getDriver());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productComparisonPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsShoppingCartOptionDisplayedOnTheSuccessPage());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsWishListOptionDisplayedOnTheSuccessPage());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsProductComparisonOptionDisplayedOnTheSuccessPage());
	}
	@Test(priority=20)
	public void verifySearchPageHeadingTitleURL()
	{
		search=header.clickSearchButton();
		Assert.assertEquals(search.getPageHeading(), "Search");
		Assert.assertEquals(getPageURL(search.getDriver()), prop.getProperty("searchPage"));
		Assert.assertEquals(getPageTitle(search.getDriver()), "Search");
    }
	@Test(priority=21)
	public void searchPageUI() throws IOException
	{
		search=header.clickSearchButton();
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedSearchPageUI.png"));
			
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) 
		{
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedSearchPageUI.png"));
			
			
		}
		else if(browserName.equalsIgnoreCase("edge")) 
		{
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png");
			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedSearchPageUI.png"));
			
			
		}
		
	}
	@Test(priority=22)
	public void verifySearchFuntionalityInAllEnvironments()
	{
		header.enterProductInToSearchBoxFeild(prop.getProperty("existingProductThree"));
		header.clickSearchButton();
		Assert.assertTrue(search.didWeNavigateToSearchResultPage());
	}
	
		
	 

	

}
