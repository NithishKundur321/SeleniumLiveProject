package tests;

import java.io.File;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utils.CommonUtilities;
import Utils.MyXLSReader;
import base.Base;
import pages.AboutUsPage;
import pages.AccountSuccessPage;
import pages.AffliateProgramPage;
import pages.BrandsPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.FooterOptionsPage;
import pages.ForgotYourPassword;
import pages.GiftCertificate;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountInfoPage;
import pages.MyAccountPage;
import pages.NewsLetterPage;
import pages.PrivacyPolicyPage;
import pages.RegisterPage;
import pages.ReturnsPage;
import pages.RightColumnOptions;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialOffers;
import pages.TermsConditions;
import pages.searchPage;

public class RegisterTest extends Base {
	WebDriver driver;
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver = openBrowserApplicationPageUrl();
		header = new HeaderOptions(driver);
		header.clickonMyAccountDropMenu();
        register = header.clickonregister();
	
		
	}

	@Test(priority=1)
	public void VerifyRegisterMandatoryFeilds() throws InterruptedException 
	{
		
		
		//driver.findElement(By.xpath("//input[@class='form-control'][@name='firstname']")).sendKeys("Nithish Reddy");
		register.enterFirstName(prop.getProperty("firstName"));
		
		register.enterLastName(prop.getProperty("lastName"));
		
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
		
	    register.enterPassword(prop.getProperty("validPassword"));
		
		register.enterconfirmPassword(prop.getProperty("validPassword"));
		
		register.clickprivacyPolicyFeild();
		accountsuccess = register.clickContinueButton();
		
		
		Assert.assertTrue(accountsuccess.isUserLoggedin());
		Assert.assertTrue(accountsuccess.SuccessBreadCrumb());
		
		String properDetailsOne="Your Account Has Been Created!";
		String properDetailsTwo="Congratulations! Your new account has been successfully created!";
		String properDetailThree="You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String properDetailsFour="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String properDetailsFive="A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please ";
		
		Assert.assertTrue(accountsuccess.AccountGetText().contains(properDetailsOne));
		Assert.assertTrue(accountsuccess.AccountGetText().contains(properDetailsTwo));
		Assert.assertTrue(accountsuccess.AccountGetText().contains(properDetailThree));
		Assert.assertTrue(accountsuccess.AccountGetText().contains(properDetailsFour));
		Assert.assertTrue(accountsuccess.AccountGetText().contains(properDetailsFive));
		//accountsuccess.clickonContinueButton();
		accountPage = accountsuccess.clickonContinueButton();
		
		Assert.assertTrue(accountPage.editInfo());
		
		}
	@Test(priority = 2)
	public void verifyThankYourConfirmationEmailOnSuccessfulRegistration() throws InterruptedException {

		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		String emailText = CommonUtilities.generateTimestampedEmail();
		driver.findElement(By.id("input-email")).sendKeys(emailText);
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String email = emailText;
		String appPasscode = "dbmm vncw rtja ewoo";

		Thread.sleep(2000);

		// Gmail IMAP configuration
		String host = "imap.gmail.com";
		String port = "993";
		String username = email; // Your Gmail address
		String appPassword = appPasscode; // Your app password
		String expectedSubject = "Welcome To TutorialNinja";
		String expectedFromEmail = "tutorialsninja<account-update@tn.in>";
		String expectedBodyContent = "Your account has been successfully created.";

		try {
			// Mail server connection properties
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "imaps");
			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", port);
			properties.put("mail.imap.ssl.enable", "true");

			// Connect to the mail server
			Session emailSession = Session.getDefaultInstance(properties);
			Store store = emailSession.getStore("imaps");
			store.connect(host, username, appPassword); // replace email password with App password

			// Open the inbox folder
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// Search for unread emails
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			boolean found = false;
			for (int i = messages.length - 1; i >= 0; i--) {

				Message message = messages[i];

				if (message.getSubject().contains(expectedSubject)) {
					found = true;
					Assert.assertEquals(message.getSubject(), expectedSubject);
					Assert.assertEquals(message.getFrom()[0].toString(), expectedFromEmail);
					Assert.assertTrue(CommonUtilities.getTextFromMessage(message).contains(expectedBodyContent));
					break;
				}
			}

			if (!found) {
				System.out.println("No confirmation email found.");
			}

			// Close the store and folder objects
			inbox.close(false);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test(priority=3)
	public void verifyregisteraccountusingallfeilds() throws InterruptedException 
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
	
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
		
	    register.enterPassword(prop.getProperty("validPassword"));
		
		register.enterconfirmPassword(prop.getProperty("validPassword"));
		
		register.clickonNewsletterFeild();
		
		register.clickprivacyPolicyFeild();
		
		accountsuccess = register.clickContinueButton();
		Assert.assertTrue(accountsuccess.isUserLoggedin());
		Assert.assertTrue(accountsuccess.SuccessBreadCrumb());
		accountPage = accountsuccess.clickonContinueButton();
		Assert.assertTrue(accountPage.ChangeYourPasswordInfo());
	}
	@Test(priority=4)
	public void verifyWarningMessageOfMandatoryFieldsInRegisterAccountPage()
	{
		register.clickContinueButton();
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		
		Assert.assertEquals(register.getFirstNameWarning(),expectedFirstNameWarning );
		Assert.assertEquals(register.getLastNameWarning(),expectedLastNameWarning );
		Assert.assertEquals(register.getEmailWarning(),expectedEmailWarning );
		Assert.assertEquals(register.getTelephoneWarning(),expectedTelephoneWarning );
		Assert.assertEquals(register.getPasswordWarning(),expectedPasswordWarning );
		Assert.assertEquals(register.getconfirmPassword(),expectedPrivacyPolicyWarning );
		}
	@Test(priority=5)
	public void verifyRegisteringAccountBySubscribingToNewsletter() throws InterruptedException
	{
		
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
		register.enterPassword(prop.getProperty("validPassword"));
		register.enterconfirmPassword(prop.getProperty("validPassword"));
		register.clickonNewsletterFeild();
		register.clickprivacyPolicyFeild();
		accountsuccess = register.clickContinueButton();
		
		accountPage = accountsuccess.clickonContinueButton();
		
		NewsLetterPage newsletterPage  = accountPage.clickNewsletter();
		
		Assert.assertTrue(newsletterPage.newsletterDisplayed());
		
		Assert.assertTrue(newsletterPage.isNewsletterSelected());
		
		
	}
	@Test(priority=6)
	public void verifyRegisteringAccountByNoSubscribingToNewsletter() throws InterruptedException
	{
		
	register.enterFirstName(prop.getProperty("firstName"));
	register.enterLastName(prop.getProperty("lastName"));
	register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
	register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
	register.enterPassword(prop.getProperty("validPassword"));
	register.enterconfirmPassword(prop.getProperty("validPassword"));
	register.noNewsLetterOption();
	register.clickprivacyPolicyFeild();
	accountsuccess = register.clickContinueButton();
	accountPage = accountsuccess.clickonContinueButton();
	newsletterPage = accountPage.clickNewsletter();
	Assert.assertTrue(newsletterPage.newsletterDisplayed());
	Assert.assertTrue(newsletterPage.isNoNewsletterSelected());
	}
	@Test(priority=7)
	public void verifyDifferentWaysOfNavigatingRegistrationPage() 
	{
		
	    Assert.assertTrue(register.didWeNavigateToRegisterPage());
	    header = new HeaderOptions(register.getDriver());
	    header.clickonMyAccountDropMenu();
	    login = header.selectLoginOption();
	    register = login.clickOnContinueButton();
	    Assert.assertTrue(register.didWeNavigateToRegisterPage());
	    header = new HeaderOptions(register.getDriver());
	    header.clickonMyAccountDropMenu();
	    login = header.selectLoginOption();
	    rightcolumn = new RightColumnOptions(login.getDriver());
	    register = rightcolumn.clickOnRegister();
		Assert.assertTrue(register.didWeNavigateToRegisterPage());
		
   }
	@Test(priority=8)
	public void verifyRegisteringAccountByProvidingMismatchedPasswords() 
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
		register.enterPassword(prop.getProperty("validPassword"));
	    register.enterconfirmPassword(prop.getProperty("mismatchingPassword"));
		register.clickonNewsletterFeild();
		register.clickprivacyPolicyFeild();
		register.clickContinueButton();
		String expectedwarning = "Password confirmation does not match password!";
		Assert.assertEquals(register.getconfirmPassword(), expectedwarning);
	}
	@Test(priority=9)
	public void verifyRegisterAccountWithExistingEmailAddress() 
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(prop.getProperty("existingEmail"));
	    register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
		register.enterPassword(prop.getProperty("validPassword"));
		register.enterconfirmPassword(prop.getProperty("validPassword"));
		register.clickonNewsletterFeild();
	    register.clickprivacyPolicyFeild();
		register.clickContinueButton();
	    String expectedwarning = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(register.pagelevelWarning(), expectedwarning);
	}
	@Test(priority=10)
	public void verifyRegistrationAccountUsingInvalidEmail() throws InterruptedException
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(prop.getProperty("invalidEmailOne"));
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
		register.enterPassword(prop.getProperty("validPassword"));
		register.enterconfirmPassword(prop.getProperty("validPassword"));
		register.clickonNewsletterFeild();
		register.clickprivacyPolicyFeild();
		register.clickContinueButton();
        if(browserName.equals("chrome") || browserName.equals("edge")) 
		{
		String expectedWarningMessageOne="Please include an '@' in the email address. 'nithish' is missing an '@'.";
		String actualWarningMessageOne = register.getEmailValidationMessage();
		Assert.assertEquals(actualWarningMessageOne, expectedWarningMessageOne);
		}
		else if(browserName.equals("firefox"))
		{
			String expectedWarningMessageOne="Please enter an email address.";
			String actualWarningMessageOne = register.getEmailValidationMessage();
			Assert.assertEquals(actualWarningMessageOne, expectedWarningMessageOne);
			
		}
		
		register.clearEmailFeild();
		register.enteremailFeild(prop.getProperty("invalidEmailTwo"));
		register.clickContinueButton();
		if(browserName.equals("chrome") || browserName.equals("edge")) 
		{
		String expectedWarningMessageTwo="Please enter a part following '@'. 'nithish@' is incomplete.";
		String actualWarningMessageTwo = register.getEmailValidationMessage();
		Assert.assertEquals(actualWarningMessageTwo,expectedWarningMessageTwo);
		}
		else if(browserName.equals("firefox"))
		{
			String expectedWarningMessageTwo="Please enter an email address.";
			String actualWarningMessageTwo = register.getEmailValidationMessage();
			Assert.assertEquals(actualWarningMessageTwo,expectedWarningMessageTwo);
			
		}
		register.clearEmailFeild();
		register.enteremailFeild(prop.getProperty("invalidEmailThree"));
		register.clickContinueButton();
		String expectedWarningMessageThree="E-Mail Address does not appear to be valid!";
		String actualWarningMessageThree = register.getEmailWarning();
		Assert.assertEquals(actualWarningMessageThree,expectedWarningMessageThree);
		
		register.clearEmailFeild();
		register.enteremailFeild(prop.getProperty("invalidEmailFour"));
		register.clickContinueButton();
		if(browserName.equals("chrome") || browserName.equals("edge")) 
		{
		String expectedWarningMessageFour ="'.' is used at a wrong position in 'gmail.'.";
		String actualWarningMessageFour = register.getEmailValidationMessage();
		Assert.assertEquals(actualWarningMessageFour,expectedWarningMessageFour);
		}else if(browserName.equals("firefox"))
		{
			String expectedWarningMessageTwo="Please enter an email address.";
			String actualWarningMessageTwo = register.getEmailValidationMessage();
			Assert.assertEquals(actualWarningMessageTwo,expectedWarningMessageTwo);
			
		}
	}
	@Test(priority=11)
	public void verifyRegisteringAccountUsingInvalidPhoneNumber()
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		register.enterTelephoneFeild(prop.getProperty("invalidTelephoneNumber"));
        register.enterPassword(prop.getProperty("validPassword"));
		register.enterconfirmPassword(prop.getProperty("validPassword"));
		register.clickonNewsletterFeild();
		register.clickprivacyPolicyFeild();
		accountsuccess = register.clickContinueButton();
	    String expectedWarningMessage="Telephone Number Entered By You Is Invalid";
		Assert.assertEquals(register.getTelephoneWarning(), expectedWarningMessage);
		Assert.assertFalse(accountsuccess.DidWeNavigateToAccountSuccessPage());
		
		
	}
	@Test(priority=12)
	public void verifyRegisterAccountUsingKeyBoardKeys()
	{
		
		try {
            Actions actions = new Actions(driver);

           
               for (int i = 0; i < 23; i++) 
               {
                actions.sendKeys(Keys.TAB).perform(); 
                Thread.sleep(200); 
               }

            WebElement firstNameFeild = driver.switchTo().activeElement();
            register.enterFirstName(prop.getProperty("firstName"));
            
            actions.sendKeys(Keys.TAB).perform();
            
            WebElement lastNameFeild = driver.switchTo().activeElement();
            register.enterLastName(prop.getProperty("lastName"));
            
            actions.sendKeys(Keys.TAB).perform();
            
            WebElement emailFeild = driver.switchTo().activeElement();
            register.enteremailFeild(prop.getProperty("email"));
            
            actions.sendKeys(Keys.TAB).perform();
            
            WebElement telephoneFeild = driver.switchTo().activeElement();
            register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
            
            actions.sendKeys(Keys.TAB).perform();
            
            WebElement passwordFeild = driver.switchTo().activeElement();
            register.enterPassword(prop.getProperty("validPassword"));
            
            actions.sendKeys(Keys.TAB).perform();
            
            WebElement confirmPasswordFeild = driver.switchTo().activeElement();
            register.enterconfirmPassword(prop.getProperty("mismatchingPassword"));
            
            actions.sendKeys(Keys.TAB).perform();
            
            register.clickContinueButton();
            System.out.println("Successfully navigated and clicked the Continue button!");
          } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
        
     }
	@Test(priority=13)
	public void verifyRegisterAccountPagePlaceHolders()
	{
		
		String expectedFirstNamePlaceHolder = "First Name";
		String expectedLastNamePlaceHolder = "Last Name";
		String expectedEmailPlaceHolder = "E-Mail";
		String expectedTelephonePlaceHolder="Telephone";
		String expectedPasswordPlaceHolder="Password";
		String expectedConfirmPasswordPlaceHolder="Password Confirm";
		
		Assert.assertEquals(register.getFirstNamePlaceholderText(), expectedFirstNamePlaceHolder);
		Assert.assertEquals(register.getLastNamePlaceholderText(), expectedLastNamePlaceHolder);
		Assert.assertEquals(register.getEmailPlaceholderText(), expectedEmailPlaceHolder);
		Assert.assertEquals(register.getTelePhonePlaceholderText(), expectedTelephonePlaceHolder);
		Assert.assertEquals(register.getPasswordPlaceholderText(), expectedPasswordPlaceHolder);
		Assert.assertEquals(register.getConfirmPasswordPlaceholderText(), expectedConfirmPasswordPlaceHolder);
		
		
	}
	@Test(priority=14)
	public void verifyMandatoryFeildsInRegisterAccountPage()
	{
		
		String expectedContent = "\"* \"";
		String expectedcolor = "rgb(255, 0, 0)";
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	     String fistNameLabelContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",register.getFirstNameLabelElement());
		 Assert.assertEquals(fistNameLabelContent,expectedContent);
	     String firstNameLabelColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",register.getFirstNameLabelElement());
	     Assert.assertEquals(firstNameLabelColor, expectedcolor);
		 
		 String lastNameLabelContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",register.getLastNameLabelElement());
		 Assert.assertEquals(lastNameLabelContent,expectedContent);
		 String lastNameLabelColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",register.getLastNameLabelElement());
		 Assert.assertEquals(lastNameLabelColor, expectedcolor);
		 
		 String emailLabelContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",register.getEmailLabelElement());
		 Assert.assertEquals(emailLabelContent,expectedContent);
		 String emailLabelColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",register.getEmailLabelElement());
		 Assert.assertEquals(emailLabelColor, expectedcolor);
		 
		 String telephoneLabelContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",register.getTelephoneLabel());
		 Assert.assertEquals(telephoneLabelContent,expectedContent);
		 String telephoneLabelColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",register.getTelephoneLabel());
		 Assert.assertEquals(telephoneLabelColor, expectedcolor);
		 
		 String passwordLabelContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",register.getPasswordLabel());
		 Assert.assertEquals(passwordLabelContent,expectedContent);
		 String passwordLabelColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",register.getPasswordLabel());
		 Assert.assertEquals(passwordLabelColor, expectedcolor);
		 
		 String passwordConfirmLabelContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",register.getPasswordConfirmLabel());
		 Assert.assertEquals(passwordConfirmLabelContent,expectedContent);
		 String passwordConfirmLabelColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",register.getPasswordConfirmLabel());
		 Assert.assertEquals(passwordConfirmLabelColor, expectedcolor);
		 
		 String privacyPolicyLabelContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",register.getPrivacyPolicyFeild());
		 Assert.assertEquals(privacyPolicyLabelContent,expectedContent);
		 String privacyPolicyLabelColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",register.getPrivacyPolicyFeild());
		 Assert.assertEquals(privacyPolicyLabelColor, expectedcolor);
		 
		 
		
	}
	@Test(priority=15)
	public void VerifyDataBaseTestingForRegisterAccount()
	{
		
		
		String enteredFirstNameData =prop.getProperty("firstName");
		driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstNameData);
		String enteredLastNameData = prop.getProperty("lastName");
		driver.findElement(By.id("input-lastname")).sendKeys(enteredLastNameData);
		String emailData = CommonUtilities.generateTimestampedEmail();
		driver.findElement(By.id("input-email")).sendKeys(emailData);
		String passwordData = prop.getProperty("telephoneNumber");
		driver.findElement(By.id("input-password")).sendKeys(passwordData);
		driver.findElement(By.id("input-newsletter")).click();
		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
		String jdbcURL = "jdbc:mysql://localhost:3306/opencart_db";
        String dbUser = "root";
        String dbPassword = "";

        String sqlQuery = "SELECT * FROM oc_customer";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        int newsletter = 0;
        

        try {
            // Step 1: Register JDBC driver (optional in newer versions)
        	
            // Step 2: Open a connection
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            System.out.println("Connected to the database!");

            // Step 3: Create a statement
            statement = connection.createStatement();

            // Step 4: Execute the query
            resultSet = statement.executeQuery(sqlQuery);

            // Step 5: Process the ResultSet
            while (resultSet.next()) {
                firstName = resultSet.getString("firstname"); // Replace with your column name
                lastName = resultSet.getString("lastname"); // Replace with your column name
                email = resultSet.getString("email");
                newsletter = resultSet.getInt("newsletter");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 6: Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        Assert.assertEquals(firstName,enteredFirstNameData );
        Assert.assertEquals(lastName,enteredLastNameData );
        Assert.assertEquals(email,emailData );
        Assert.assertEquals(newsletter,1 );
   }
	@Test(priority=16)
	public void verifyRegisterAccountByEnteringOnlySpaces()
	{
		register.enterFirstName("     ");
		register.enterLastName("     ");
		register.enteremailFeild("     ");
		register.enterTelephoneFeild("     ");
		register.enterPassword("     ");
		register.enterconfirmPassword("     ");
		register.clickprivacyPolicyFeild();
		register.clickContinueButton();
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone doesnot appear to be valid";
		if(browserName.equalsIgnoreCase("chrome")||browserName.equalsIgnoreCase("edge")) 
		{
		Assert.assertEquals(register.getFirstNameWarning(),expectedFirstNameWarning);
		Assert.assertEquals(register.getLastNameWarning(),expectedLastNameWarning);
		Assert.assertEquals(register.getEmailWarning(),expectedEmailWarning);
		Assert.assertEquals(register.getTelephoneWarning(),expectedTelephoneWarning);
	
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			String expectedWarningMessageTwo="Please enter an email address.";
		    Assert.assertEquals(register.getEmailValidationMessage(),expectedWarningMessageTwo);
			
		}
	}
	
	
	@Test(priority=17,dataProvider="passwordSupplier")
	public void verifyPasswords(HashMap<String,String>map)
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
		register.enterPassword(map.get("Passwords"));
		register.enterconfirmPassword(map.get("Passwords"));
		register.clickonNewsletterFeild();
	    register.clickprivacyPolicyFeild();
		register.clickContinueButton();
		String expectedwarning = "Enter password which follow complexity standards";
		Assert.assertEquals(register.getPasswordWarning(), expectedwarning);
		}
	@DataProvider(name="passwordSupplier")
	public Object[][] supplyPasswords()
	{
	  MyXLSReader myXLSReader = new MyXLSReader("//src//test//resources//TutorialsNinja.xlsx");		
      Object[][] data = CommonUtilities.getTestData(myXLSReader, "RegisterWithNoPasswordComplexityTest","BadPasswords");
      return data;
	}
	
	@Test(priority=18)
	public void verifyHeightWidthNumberOfCharacters()
	{
		String expectedHeight ="34px";
		String expectedWidth ="701.25px";
		//First Name Feild Check
		Assert.assertEquals(register.getFirstNameCSSValue("height"), expectedHeight);
		Assert.assertEquals(register.getFirstNameCSSValue("width"),expectedWidth );
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		register.clickContinueButton();
		Assert.assertEquals(register.getFirstNameWarning(), expectedFirstNameWarning);
		register.getFirstNameFeildElement();
		register.enterFirstName("a");
		register.clickContinueButton();
		boolean firstNameWarningStatus = false;
		try {
			firstNameWarningStatus = register.isFirstNameWarningDisplayed();
		}catch(NoSuchElementException e) {
			firstNameWarningStatus = false;
		}
		register.getFirstNameFeildElement();
		register.clearFirstNameFeild();
		register.enterFirstName("abcdeabcdeabcdeabcdeabcdeabcdeab");
		register.clickContinueButton();
		firstNameWarningStatus = false;
		try
		{
			firstNameWarningStatus = register.isFirstNameWarningDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		register.getFirstNameFeildElement();
	    register.clearFirstNameFeild();
		register.enterFirstName("abcdeabcdeabcdeabcdeabcdeabcdeabc");
		register.clickContinueButton();
		Assert.assertEquals(register.getFirstNameWarning(), expectedFirstNameWarning);
	    
		//Last Name Feild Check
		Assert.assertEquals(register.getLastNameCSSValue("height"),expectedHeight);
		Assert.assertEquals(register.getLastNameCSSValue("width"),expectedWidth );
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		register.clickContinueButton();
		Assert.assertEquals(register.getLastNameWarning(), expectedLastNameWarning);
		register.getLastNameFeildElement();
		register.enterLastName("a");
		register.clickContinueButton();
		boolean lastNameWarningStatus = false;
		try {
			lastNameWarningStatus = register.isLastNameWarningDisplayed();
		}catch(NoSuchElementException e) {
			lastNameWarningStatus = false;
		}
		register.getLastNameFeildElement();
		register.clearLastNameFeild();
		register.enterLastName("abcdeabcdeabcdeabcdeabcdeabcdeab");
		register.clickContinueButton();
		lastNameWarningStatus = false;
		try
		{
			lastNameWarningStatus = register.isLastNameWarningDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			lastNameWarningStatus = false;
		}
		Assert.assertFalse(lastNameWarningStatus);
		register.getLastNameFeildElement();
		register.clearFirstNameFeild();
		register.enterLastName("abcdeabcdeabcdeabcdeabcdeabcdeabc");
		register.clickContinueButton();
		Assert.assertEquals(register.getLastNameWarning(), expectedLastNameWarning);
		
		//Email Feild Check
		
		Assert.assertEquals(register.getEmailCSSValue("height"),expectedHeight);
		Assert.assertEquals(register.getEmailCSSValue("width"),expectedWidth );
		register.enteremailFeild("adsbasdbasbnsdbsnmdbmbdansbmdbsdbsmbddbsamndb@gmail.com");
		register.clickContinueButton();
		boolean EmailWarningStatus = false;
		try
		{
			EmailWarningStatus = register.isEmailWarningDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			EmailWarningStatus = false;
		}
		Assert.assertFalse(EmailWarningStatus);
		
		//Telephone Feild Check
		
		Assert.assertEquals(register.getTelephoneCSSValue("height"),expectedHeight);
		Assert.assertEquals(register.getTelephoneCSSValue("width"),expectedWidth );
		
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		register.clickContinueButton();
		Assert.assertEquals(register.getTelephoneWarning(), expectedTelephoneWarning);
		register.getTelephoneFeildElement();
		register.enterTelephoneFeild("1");
		register.clickContinueButton();
		Assert.assertEquals(register.getTelephoneWarning(), expectedTelephoneWarning);
		register.getTelephoneFeildElement();
		register.clearTelephoneFeild();
		register.enterTelephoneFeild("12");
		register.clickContinueButton();
		Assert.assertEquals(register.getTelephoneWarning(), expectedTelephoneWarning);
		register.getTelephoneFeildElement();
		register.clearTelephoneFeild();
		register.enterTelephoneFeild("123");
		register.clickContinueButton();
		boolean telephoneWarningStatus = false;
		try
		{
			telephoneWarningStatus = register.isTelePhoneWarningDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			telephoneWarningStatus = false;
		}
		Assert.assertFalse(telephoneWarningStatus);
		register.getTelephoneFeildElement();
		register.clearTelephoneFeild();
		register.enterTelephoneFeild("12345678901234567890123456789012");
		register.clickContinueButton();
		telephoneWarningStatus = false;
		try
		{
			telephoneWarningStatus = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).isDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			telephoneWarningStatus = false;
		}
		Assert.assertFalse(telephoneWarningStatus);
		
		register.getTelephoneFeildElement();
		register.clearTelephoneFeild();
		register.enterTelephoneFeild("1234567890123456789012345678901242434354534543454");
		register.clickContinueButton();
		Assert.assertEquals(register.getTelephoneWarning(), expectedTelephoneWarning);
		
		//Password Feild Check
		
		Assert.assertEquals(register.getPasswordCSSValue("height"),expectedHeight);
		Assert.assertEquals(register.getPasswordCSSValue("width"),expectedWidth );
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		register.clickContinueButton();
		Assert.assertEquals(register.getPasswordWarning(), expectedPasswordWarning);
		
		register.getPasswordFeildElement();
	    register.enterPassword("1");
		register.clickContinueButton();
		Assert.assertEquals(register.getPasswordWarning(), expectedPasswordWarning);
		register.getPasswordFeildElement();
		register.clearPasswordFeild();
		register.enterPassword("12");
		register.clickContinueButton();
		Assert.assertEquals(register.getPasswordWarning(), expectedPasswordWarning);
		register.getPasswordFeildElement();
		register.clearPasswordFeild();
		register.enterPassword("123");
		register.clickContinueButton();
		Assert.assertEquals(register.getPasswordWarning(), expectedPasswordWarning);
		register.getPasswordFeildElement();
		register.clearPasswordFeild();
		register.enterPassword("1234");
		register.clickContinueButton();
		boolean passwordfeildStatus = false;
		try
		{
			passwordfeildStatus = register.isPasswordWarningDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			passwordfeildStatus = false;
		}
		Assert.assertFalse(passwordfeildStatus);
		
		register.getPasswordFeildElement();
		register.clearPasswordFeild();
		register.enterPassword("12345678901234567890");
		register.clickContinueButton();
		passwordfeildStatus = false;
		try
		{
			passwordfeildStatus = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			passwordfeildStatus = false;
		}
		Assert.assertFalse(passwordfeildStatus);
		register.getPasswordFeildElement();
		register.clearPasswordFeild();
		register.enterPassword("1234567890123456789012345643234342424343242443423454874857397538457487557837749574759");
		register.clickContinueButton();
		passwordfeildStatus = false;
		try
		{
			passwordfeildStatus = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed();
			
		}
		catch(NoSuchElementException e)
		{
			passwordfeildStatus = false;
		}
		Assert.assertTrue(passwordfeildStatus);
		
		//Password Confirm Feild Check
		Assert.assertEquals(register.getPasswordConfirmCSSValue("height"),expectedHeight);
		Assert.assertEquals(register.getPasswordConfirmCSSValue("width"),expectedWidth );
		
		//Continue Button
		WebElement continueButton = register.getContinueButtonElement();
		String actualbuttonTextColor = continueButton.getCssValue("color");
		Assert.assertEquals(actualbuttonTextColor, "rgba(255, 255, 255, 1)");
		String actualButtonBackGroundColor = continueButton.getCssValue("background-color");
		Assert.assertEquals(actualButtonBackGroundColor, "rgba(34, 154, 200, 1)");
		String actualButtonSize = continueButton.getCssValue("font-size");
		Assert.assertEquals(actualButtonSize, "12px");
		}
	@Test(priority=19)
	public void verifyRegisterAccountUsingLeadingAndTrailingSpaces()
	{
		SoftAssert softassert = new SoftAssert();
		String firstName = "     "+prop.getProperty("firstName")+"     ";
		register.enterFirstName(firstName);
		String lastName = "     "+prop.getProperty("lastName")+"     ";
		register.enterLastName(lastName);
		String emailTimeStamp = CommonUtilities.generateTimestampedEmail();
		register.enteremailFeild( "     "+emailTimeStamp+"     ");
	
		String telephone = "     "+prop.getProperty("telephoneNumber")+"     ";
		register.enterTelephoneFeild(telephone);
		register.enterPassword("     "+prop.getProperty("validPassword")+"     ");
		register.enterconfirmPassword("     "+prop.getProperty("validPassword")+"     ");
		register.clickonNewsletterFeild();
		register.clickprivacyPolicyFeild();
		accountsuccess = register.clickContinueButton();
		
		if(browserName.equals("chrome")||browserName.equals("edge")) {
			accountPage = accountsuccess.clickonContinueButton();	
			myaccountinfo = accountPage.clickOnEditInfoLink();
	
		String actualFirstName = myaccountinfo.getFirstNameDomAttribute("value");
		softassert.assertEquals(actualFirstName, prop.getProperty("firstName"));
		String actualLastName = myaccountinfo.getLastNameDomAttribute("value");
		softassert.assertEquals(actualLastName,prop.getProperty("lastName"));
		String actualEmail = myaccountinfo.getEmailDomAttribute("value");
		softassert.assertEquals(actualEmail, emailTimeStamp);
		String actualTelephone = myaccountinfo.getTelephoneDomAttribute("value");
		softassert.assertEquals(actualTelephone,prop.getProperty("telephoneNumber") );
		softassert.assertAll();
		}
		else if(browserName.equals("firefox"))
		{
			String expectedWarningMessageTwo="Please enter an email address.";
			String actualWarningMessageTwo = myaccountinfo.getEmailDomProperty("validationMessage");
			Assert.assertEquals(actualWarningMessageTwo,expectedWarningMessageTwo);
			
		}
		
		
		
		
		
	}
	@Test(priority=20)
	public void verifyRegisterAccountPrivacyPolicyFeild()
	{
		
		Assert.assertFalse(register.isPrivacyPolicyFeildSelected());
		driver.quit();
	}
	
	@Test(priority=21)
	public void verifyRegisteringAccountWithoutSelectingPrivacyPolicyCheckboxFeild()
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
	    register.enterPassword(prop.getProperty("validPassword"));
		register.enterconfirmPassword(prop.getProperty("validPassword"));
		register.clickonNewsletterFeild();
		register.clickContinueButton();
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(register.pagelevelWarning(), expectedPrivacyPolicyWarning);
		driver.quit();
		
		
		
	}
	@Test(priority=22)
	public void verifyRegisterAccountPasswordFeildsforSecurity()
	{
		/*WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();*/
		WebElement passwordField = register.getPasswordFeildElement();
		//Assert.assertEquals(driver.findElement(By.id("input-password")).getDomAttribute("type"), "password");
		//Assert.assertEquals(driver.findElement(By.id("input-confirm")).getDomAttribute("type"), "password");
		String fieldType = passwordField.getDomAttribute("type");
        if (fieldType.equals("password")) {
            System.out.println("Password field is of type 'password'.");
        } else {
            System.out.println("Password field type is not 'password'. It is of type: " + fieldType);
        }
		
		//driver.quit();  
		
		
	}	
	@Test(priority=23)
	public void VerifyRegisterAccountPageNavigations()
	{
		header=register.getHeaderOptionsPage();
		
		contactus = header.SelectPhoneIcon();
		Assert.assertEquals(getPageTitle(contactus.getDriver()), "Contact Us");
		navigateBackInBrowser(contactus.getDriver());
		
		login= header.selectHeartIconOption();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
        
        login = header.selectWishList();
        Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		shoppingcartPage = header.selectShoppingCartIcon();
		Assert.assertEquals(getPageTitle(shoppingcartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingcartPage.getDriver());
		
		shoppingcartPage = header.selectShoppingCartOption();
		Assert.assertEquals(getPageTitle(shoppingcartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingcartPage.getDriver());
		
		shoppingcartPage = header.selectCheckOutIcon();
		Assert.assertEquals(getPageTitle(shoppingcartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingcartPage.getDriver());
		
		shoppingcartPage = header.selectCheckOutOption();
		Assert.assertEquals(getPageTitle(shoppingcartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingcartPage.getDriver());
		
		home = header.selectLogo();
		Assert.assertEquals(getPageTitle(home.getDriver()), "Your Store");
		navigateBackInBrowser(home.getDriver());

		search =header.selectsearchOption();
		Assert.assertEquals(getPageTitle(search.getDriver()), "Search");
		navigateBackInBrowser(search.getDriver());
	
		login=header.selectAccountOption();
		Assert.assertEquals(getPageTitle(login.getDriver()),"Account Login");
		navigateBackInBrowser(login.getDriver());
		
		register = header.selectRegisterOption();
		Assert.assertEquals(getPageTitle(register.getDriver()), "Register Account");
		
		login = header.selectLoginOption();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		rightcolumn = register.getRightColoumOptions();
		
		login = rightcolumn.selectRightColoumnLogin();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		register = rightcolumn.selectRightColumnRegister();
		Assert.assertEquals(getPageTitle(register.getDriver()),"Register Account" );
		
		forgotPassword = rightcolumn.selectRightColumnForgotPassword();
		Assert.assertEquals(getPageTitle(forgotPassword.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgotPassword.getDriver());
		
		login = rightcolumn.selectRightColumnMyAccount();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
	    login =rightcolumn.selectRightColumnAddressBook();
	    Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
	    navigateBackInBrowser(login.getDriver());
		
		login = rightcolumn.selectRightColumnWishList();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		login = rightcolumn.selectOrderHistory();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		login = rightcolumn.selectDownloads();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
	    login = rightcolumn.selectReccuringPayment();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		login = rightcolumn.selectRewardPoints();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		login = rightcolumn.selectReturns();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		login = rightcolumn.selectTransactions();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
		login = rightcolumn.selectNewsLetter();
		Assert.assertEquals(getPageTitle(login.getDriver()), "Account Login");
		navigateBackInBrowser(login.getDriver());
		
	    FooterOption=register.getFooterOptions();
	    
	    about = FooterOption.clickAboutUs();
	    Assert.assertEquals(getPageTitle(about.getDriver()), "About Us");
	    navigateBackInBrowser(about.getDriver());
	    
	    DeliveryInfo =FooterOption.clickFooterDelivery();
	    Assert.assertEquals(getPageTitle(DeliveryInfo.getDriver()), "Delivery Information");
	    navigateBackInBrowser(DeliveryInfo.getDriver());
	    
	    PrivacyPolicy = FooterOption.clickPrivacyPolicy();
		Assert.assertEquals(getPageTitle(PrivacyPolicy.getDriver()), "Privacy Policy");
		navigateBackInBrowser(PrivacyPolicy.getDriver());
		
		termsnconditions = FooterOption.clickTermsConditions();
		Assert.assertEquals(getPageTitle(termsnconditions.getDriver()), "Terms & Conditions");
		navigateBackInBrowser(termsnconditions.getDriver());
		
		contactus = FooterOption.clickContactUs();
		Assert.assertEquals(getPageTitle(contactus.getDriver()), "Contact Us");
		navigateBackInBrowser(contactus.getDriver());
		
		returns = FooterOption.clickReturns();
		Assert.assertEquals(getPageTitle(returns.getDriver()), "Product Returns");
		navigateBackInBrowser(returns.getDriver());
		
		sitemap = FooterOption.clickSiteMap();
		Assert.assertEquals(getPageTitle(sitemap.getDriver()), "Site Map");
		navigateBackInBrowser(returns.getDriver());
		
		Brands = FooterOption.clickBrands();
		Assert.assertEquals(getPageTitle(Brands.getDriver()), "Find Your Favorite Brand");
		navigateBackInBrowser(Brands.getDriver());
		
		giftcertificate = FooterOption.clickGiftCertificate();
		Assert.assertEquals(getPageTitle(giftcertificate.getDriver()), "Purchase a Gift Certificate");
		navigateBackInBrowser(giftcertificate.getDriver());
		
		affliate = FooterOption.clickAffliate();
		Assert.assertEquals(getPageTitle(affliate.getDriver()), "Affiliate Program");
		navigateBackInBrowser(affliate.getDriver());
		
		Special = FooterOption.clickSpecials();
		Assert.assertEquals(getPageTitle(Special.getDriver()), "Special Offers");
		navigateBackInBrowser(Special.getDriver());
		
		accountPage = FooterOption.clickMyAccount();
		Assert.assertEquals(getPageTitle(accountPage.getDriver()), "Account Login");
		navigateBackInBrowser(accountPage.getDriver());
		
		accountPage=FooterOption.clickOrderHistory();
		Assert.assertEquals(getPageTitle(accountPage.getDriver()), "Account Login");
		navigateBackInBrowser(accountPage.getDriver());
		
		accountPage = FooterOption.clickWishList();
		Assert.assertEquals(getPageTitle(accountPage.getDriver()), "Account Login");
		navigateBackInBrowser(accountPage.getDriver());
		
		accountPage = FooterOption.clickNewsLetter();
		Assert.assertEquals(getPageTitle(accountPage.getDriver()), "Account Login");
		navigateBackInBrowser(accountPage.getDriver());
		
		
		
		
	}
	@Test(priority=24)
	public void verifyRegisteringWithoutEnteringConfirmationPassword()
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
	    register.enterPassword(prop.getProperty("validPassword"));
		register.clickonNewsletterFeild();
		register.clickprivacyPolicyFeild();
		register.clickContinueButton();
		
		String passwordConfirmWarning="Password confirmation does not match password!";
		Assert.assertEquals(register.getconfirmPassword(), passwordConfirmWarning);
		driver.quit();
		
	}
	@Test(priority=25)
	public void verifyRegisterAccountPageBreadcrumbURLTitleHeading()
	{
		
		
		String expectedTitle = "Register Account";
		Assert.assertEquals(getPageTitle(register.getDriver()),expectedTitle);
		
		Assert.assertEquals(getPageURL(register.getDriver()), prop.getProperty("registerPageURL"));
		
		Assert.assertTrue(register.didWeNavigateToRegisterPage());
		
		Assert.assertEquals(register.getPageHeading(),expectedTitle);
		
		driver.quit();
		
		
		
		
	}
	@Test(priority=26)
	public void verifyRegisterAccountUI() throws IOException
	{
		
		CommonUtilities.takeScreenshot(driver, System.getProperty("user.dir") + "\\Screenshots\\actualRAPageUI.png");

		Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
				System.getProperty("user.dir") + "\\Screenshots\\actualRAPageUI.png",
				System.getProperty("user.dir") + "\\Screenshots\\expectedRAPageUI.png"));

		driver.quit();
		

		
		
	}
	@Test(priority=27)
	public void verifyRegisterAccountInAllEnvironments()
	{
		register.enterFirstName(prop.getProperty("firstName"));
		register.enterLastName(prop.getProperty("lastName"));
		register.enteremailFeild(CommonUtilities.generateTimestampedEmail());
		register.enterTelephoneFeild(prop.getProperty("telephoneNumber"));
	    register.enterPassword(prop.getProperty("validPassword"));
	    register.enterconfirmPassword(prop.getProperty("validPassword"));
		register.clickonNewsletterFeild();
		register.clickprivacyPolicyFeild();
		accountsuccess = register.clickContinueButton();
		Assert.assertTrue(accountsuccess.isUserLoggedin());
		Assert.assertTrue(accountsuccess.DidWeNavigateToAccountSuccessPage());
		
		
	}
	


}
