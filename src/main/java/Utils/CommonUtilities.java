package Utils;

import java.awt.image.BufferedImage;
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.FileHandler;

import javax.imageio.ImageIO;
import javax.mail.Message;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CommonUtilities 
{
	public static final int AVERAGE_TIME = 10;
	
	public static Properties loadPropertiesFile()
	{
		Properties prop = new Properties();
		try 
		{
			FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\projectdata.properties");
			prop.load(fr);
			
			
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		return prop;
		
		
	}

    public static Object[][] getTestData(MyXLSReader xls_received, String testName, String sheetName) {

		MyXLSReader xls = xls_received;

		String testCaseName = testName;

		String testDataSheet = sheetName;

		int testStartRowNumber = 1;

		while (!(xls.getCellData(testDataSheet, 1, testStartRowNumber).equals(testCaseName))) {

			testStartRowNumber++;

		}

		int columnStartRowNumber = testStartRowNumber + 1;
		int dataStartRowNumber = testStartRowNumber + 2;

		int rows = 0;
		while (!(xls.getCellData(testDataSheet, 1, dataStartRowNumber + rows).equals(""))) {

			rows++;

		}

		// Total number of columns in the required test
		int columns = 1;

		while (!(xls.getCellData(testDataSheet, columns, columnStartRowNumber).equals(""))) {

			columns++;

		}

		Object[][] obj = new Object[rows][1];

		HashMap<String, String> map = null;

		// Reading the data in the test
		for (int i = 0, row = dataStartRowNumber; row < dataStartRowNumber + rows; row++, i++) {

			map = new HashMap<String, String>();

			for (@SuppressWarnings("unused")
			int j = 0, column = 1; column < columns; column++, j++) {

				String key = xls.getCellData(testDataSheet, column, columnStartRowNumber);

				String value = xls.getCellData(testDataSheet, column, row);

				map.put(key, value);

			}

			obj[i][0] = map;

		}

		return obj;

	}

   public static String generateTimestampedEmail() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        
        // Format the timestamp in a way that avoids invalid email characters
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        
        // Append timestamp to a base email name to make it unique
        String email = "testuser" + timestamp + "@gmail.com";
        
        return email;
    }

    public static void main(String[] args) {
        // Generate and print a timestamped Gmail address
        System.out.println("Generated Gmail Address: " + generateTimestampedEmail());
    }
       
    public static boolean compareTwoScreenshots(String actualImagePath,String expectedImagePath) throws IOException 
       {
		
		BufferedImage bufferedActualImage = ImageIO.read(new File(actualImagePath));
		BufferedImage bufferedExpectedImage = ImageIO.read(new File(expectedImagePath));
		ImageDiffer differ = new ImageDiffer();
		ImageDiff imageDiff = differ.makeDiff(bufferedExpectedImage, bufferedActualImage);
		return imageDiff.hasDiff();
	   }

    public static void takeScreenshot(WebDriver driver, String screenshotPath) {
    	    TakesScreenshot ts = (TakesScreenshot) driver;
    	    File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);

    	    try {
    	        Files.copy(srcScreenshot.toPath(), new File(screenshotPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}

	public static Properties getTextFromMessage(Message message) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	public static Integer convertToInteger(String value) 
	{
	        try {
	            return Integer.parseInt(value.trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid integer value: " + value);
	            return null; // Return null if conversion fails
	        }
	 }
     
	  /*public static ExtentReports getExtentReports() 
	  {
		ExtentReports extentReport = new ExtentReports();
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\ExtentReport.html");
		ExtentSparkReporterConfig sparkReporterConfig = sparkReporter.config();
		sparkReporterConfig.setReportName("TutorialsNinja Results");
		sparkReporterConfig.setDocumentTitle("TN Report");
		
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Username",System.getProperty("user.name"));
		extentReport.setSystemInfo("Seleium Version","4.27.0");
		extentReport.setSystemInfo("OS",System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentReport;
	  }*/
	
	


}
