package pages;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Utils.ElementUtilities;
import pages.root.RootPage;

public class searchPage extends RootPage
{
	WebDriver driver;
	public searchPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}
	 @FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Search']")
     private WebElement searchPageBreadCrumb;
	 
	 @FindBy(linkText="HP LP3065")
	 private WebElement existingProductOne;
	 
	 @FindBy(xpath="//input[@id='button-search']/following-sibling::p")
	 private WebElement noProductMessage;
	 
	 @FindBy(xpath="//div[@class='product-thumb']")
	 private List<WebElement> productThumbnail;
	 
	 @FindBy(id="input-search")
	 private WebElement searchCriteriaFeild;
	 
	 @FindBy(id="button-search")
	 private WebElement clickSearchButton;
	 
	 @FindBy(id="description")
	 private WebElement selectproductdescription;
	 
	 @FindBy(linkText="iMac")
	 private WebElement existingProductThree;
	 
	 @FindBy(name = "category_id")
	 private WebElement categoriesDropDownFeild;
	 
	 @FindBy(name = "sub_category")
	 private WebElement searchInSubcategoriesOption;
	 
	 @FindBy(id="list-view")
	 private WebElement listOption;
	 
	 @FindBy(xpath="//div[@class='product-thumb']//span[text()='Add to Cart']")
	 private WebElement addToCartOption;
	 
	 @FindBy(xpath="//button[@*='Add to Wish List']")
	 private WebElement addToWishListOption;
	 
	 @FindBy(xpath="//button[@*='Compare this Product']")
	 private WebElement compareThisProduct;
	 
	 @FindBy(xpath="(//div[@class='product-thumb']//img)[1]")
	 private WebElement ProductOneImage;
	 
	 @FindBy(linkText = "HP LP3065")
	 private WebElement ProductOneName;
	 
	 @FindBy(id="grid-view")
	 private WebElement gridOption;
	 
	 @FindBy(id="compare-total")
	 private WebElement productComparison;
	 
	 @FindBy(id="input-sort")
	 private WebElement sortDropdownFeild;
	 
	 @FindBy(id="input-limit")
	 private WebElement showDropdownFeild;
	 
	 @FindBy(xpath="//div[@id='content']/h1")
	 private WebElement pageHeading;
	 
	 public String getPageHeading()
	 {
		 return elementUtilities.getElementText(pageHeading);
	 }
	 
	 public searchPage clickOnBreadCrumb()
	 {
		 elementUtilities.clickOnElement(searchPageBreadCrumb);
		 return new searchPage(driver);
	 }
	 
	 public String getSelectedShowOption() 
	 {
		 Select select = new Select(driver.findElement(By.id("input-limit"))); 
		 return select.getFirstSelectedOption().getText().trim(); 
	 }
	 
	 public void selectShowOptionInDropdownFeild(String optionText)
	 {
		 elementUtilities.selectDropdownByVisibleText(showDropdownFeild,optionText); 
	 }
	 
	 public void selectSortOptionInDropdownFeild(String optionText)
	 {
		 elementUtilities.selectDropdownByVisibleText(sortDropdownFeild,optionText);
	 }
	 
	 public ProductComparisonPage selectProductComparison()
	 {
		 elementUtilities.clickOnElement(productComparison);
		 return new ProductComparisonPage(driver);
	 }
	 
	 public void selectGridOption()
	 {
		 elementUtilities.clickOnElement(gridOption);
	 }
	 public ProductDisplayPage clickOnProductOneName()
	 {
		 elementUtilities.clickOnElement(ProductOneName);
		 return new ProductDisplayPage(driver);
	 }
	 
	 public ProductDisplayPage clickOnProductOneImage()
	 {
		 elementUtilities.clickOnElement(ProductOneImage); 
		 return new ProductDisplayPage(driver);
	 }
	 
	 public void clickOnCompareThisProduct()
	 {
		 elementUtilities.clickOnElement(compareThisProduct);
	 }
	 
	 public void clickOnAddToWishListOption()
	 {
		 elementUtilities.clickOnElement(addToWishListOption); 
	 }
	 
	 public void clickOnAddToCartOption()
	 {
		 elementUtilities.clickOnElement(addToCartOption); 
	 }
	 
	 public void selectListOption()
	 {
		 elementUtilities.clickOnElement(listOption); 
	 }
	 
	 public boolean isProductFromCorrectCategoryDisplayedInSearchResult()
	 {
		 return elementUtilities.isElementDisplayed(existingProductThree);
	 }
	 
	 public void selectSearchInSubCategories()
	 {
		 elementUtilities.clickOnElement(searchInSubcategoriesOption);
	 }
	 
	 public void selectOptionFromCategoryDropDownFeild(String optionText)
	 {
		 elementUtilities.selectDropdownByVisibleText(categoriesDropDownFeild, optionText);
	 }
	 
	 public void selectOptionFromCategoryDropDownFeild(int optionIndex)
	 {
		 elementUtilities.selectDropdownByIndex(categoriesDropDownFeild, optionIndex); 
	 }
	 
	 public boolean isProductHavingTextInItsDescriptionDisplayedInSearchResults()
	 {
		 return elementUtilities.isElementDisplayed(existingProductThree);
	 }
	 
	 public void selectProductDescription()
	 {
		 elementUtilities.clickOnElement(selectproductdescription);
	 }
	 
	 public void clickOnSearchButton()
	 {
		 elementUtilities.clickOnElement(clickSearchButton);
	 }
	 
	 public void enterTextInProductDescriptionInToSearchCriteriaFeild(String textInProductDescription)
	 {
		 elementUtilities.enterTextIntoElement(searchCriteriaFeild,textInProductDescription );
	 }
	 
	 public String getsearchCriteriaFeildPlaceholderText()
	 {
		 return elementUtilities.getElementDomAttribute(searchCriteriaFeild,"placeholder");
	 }
	 
	 public int getProductCount()
	 {
		 return elementUtilities.getElementsCount(productThumbnail);
	 }
	 
	 public String getnoProductMessage()
	 {
		 return elementUtilities.getElementText(noProductMessage);
	 }
	 
	 public boolean isProductDisplayedInSearchResult()
	 {
		 return elementUtilities.isElementDisplayed(existingProductOne);
	 }
	 
	 public boolean didWeNavigateToSearchResultPage()
	 {
		return elementUtilities.isElementDisplayed(searchPageBreadCrumb);
	 }
	
		
	 

}
