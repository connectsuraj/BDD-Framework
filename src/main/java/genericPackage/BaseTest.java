package genericPackage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest implements IAutoConstant {

	protected static WebDriver driver;
	protected static ExtentReports extent=new ExtentReports();
	protected static ExtentSparkReporter spark=new ExtentSparkReporter(REPORT_PATH);
	protected static ExtentTest test;


	@BeforeClass
	public void setUp() throws IOException {
		FileLibrary flib =new FileLibrary();
		String browserValue=flib.readPropertyData(PROP_PATH, "browser");
		String url=flib.readPropertyData(PROP_PATH, "url");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-notification");

		if (browserValue.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			extent.attachReporter(spark);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			driver.get(url);

		}
		else {
			Reporter.log("Invalid Browser Value", true);
		}

	}

	@AfterClass
	public void tearDown() {
		extent.flush();
		driver.quit();
	}


	public void failed(String methodName) {

		try {
			TakesScreenshot ts= (TakesScreenshot)driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			File dest=new File(SCREENSHOT_PATH+methodName+".png");
			Files.copy(src,dest);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}







}
