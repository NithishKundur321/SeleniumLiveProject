package Utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.io.FileHandler;

public class ElementUtilities 
{
	WebDriver driver;
	WebDriverWait wait;
	private static final int DEFAULT_TIMEOUT = 10;
	public ElementUtilities(WebDriver driver)
	{
		this.driver=driver;
		
	}
	public void waitForElementAndClick(WebElement element, int seconds) {
		waitForElement(element, seconds);
		clickOnElement(element);
	}
	
    public void waitForElement(WebElement element, int seconds) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void clickOnElement(WebElement element) {
	    try {
	        if (element != null && element.isDisplayed() && element.isEnabled()) {
	            element.click(); // Perform the click action
	            System.out.println("Element clicked successfully.");
	        } else {
	            System.out.println("Element is either null, not displayed, or not enabled.");
	        }
	    } catch (Exception e) {
	        System.out.println("Exception in clickOnElement: " + e.getMessage());
	        e.printStackTrace(); // Print stack trace for more detailed debugging
	    }
	}

	public String getElementText(WebElement element)
	{
		String elementText="";
		if(element.isDisplayed())
		{
			elementText=element.getText();
		}
		return elementText;
	}
	public boolean isElementDisplayed(WebElement element)
	{
		boolean b= false;
		try
		{
			b=element.isDisplayed();
		}
		catch(NoSuchElementException e)
		{
			b=false;
			
		}
		return b;
		
	}
	public boolean isElementDisplayedOnPage(WebElement element)
	{
		boolean b = false;
		b = element.isDisplayed();
		return b;
	}
	public String getElementDomAttribute(WebElement element, String attributeName)
	{
	    return element.getDomAttribute(attributeName);	
	}
	public String getElementDomProperty(WebElement element,String attributeName)
	{
		return element.getDomProperty(attributeName);
	}
    public boolean isElementInSelectedState(WebElement element)
    {
    	boolean b = false;
    	if(isElementDisplayed(element))
    	{
    		b = element.isSelected();
    	}
    	return b;
    }
    public String getElementCssValue(WebElement element, String cssPropertyName)
    {
    	String value="";
    	value = element.getCssValue(cssPropertyName);
    	return value;
    	
    }
    public void clearTextFromElement(WebElement element)
    {
    	if(isElementDisplayedOnPage(element)&& element.isEnabled())
    	{
    		element.clear();
    	}
    }
    
    public void enterTextIntoElement(WebElement element, String text) {
        try {
            element.clear();  // Clear any existing text
            element.sendKeys(text);  // Enter the new text
        } catch (Exception e) {
            System.out.println("Unable to enter text '" + text + "' into element: " + element.toString());
        }
    }
    public int getElementsCount(List<WebElement> elements) 
    {
        return elements.size();
    }
    public void selectDropdownByIndex(WebElement dropdown, int index) 
    {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    public void selectDropdownByVisibleText(WebElement dropdown, String visibleText) 
    {
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
    /*public String captureScreenshotAndReturnPath(String testName,WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		String destScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot,new File(destScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destScreenshotPath;
	}*/

	
   
	
	
	
	
	
}
