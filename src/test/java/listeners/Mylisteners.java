package listeners;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.ElementUtilities;
import Utils.ExtentManager;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class Mylisteners implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public WebDriver driver;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());

        try {
            // Get WebDriver instance from the test class
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            // Capture screenshot
            String screenshotPath = captureScreenshot(result.getName(), driver);

            // Debugging: Print the screenshot path
            System.out.println("Screenshot saved at: " + screenshotPath);

            File screenshotFile = new File(screenshotPath);
            if (screenshotFile.exists()) {
                // Attach screenshot to Extent Report as a clickable image
				test.get().fail("Screenshot of failure: ",
				        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                System.err.println("Screenshot file not found at: " + screenshotPath);
            }
        } else {
            System.err.println("Driver is null, skipping screenshot capture.");
        }

        test.get().log(Status.INFO, result.getThrowable());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        //System.out.println("Test Suite finished: " + context.getName());
        File extentReportFile = new File(System.getProperty("user.dir")+"\\Reports\\extent-report.html");
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private String captureScreenshot(String testName, WebDriver driver) {
        // Define screenshot directory
        String screenshotDir = System.getProperty("user.dir") + "/Screenshots/";
        File directory = new File(screenshotDir);

        // Ensure the directory exists
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate unique file name with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = screenshotDir + testName + "_" + timestamp + ".png";

        try {
            // Capture the screenshot and save it to the specified path
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);

            // Debugging: Print confirmation
            System.out.println("Screenshot captured successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        }

        return filePath;
    }
    

}