package genericPackage;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class Report extends BaseTest{



	public void updateReport(String TestID,int groupNumber,String TestCondition,String ActualResultPass,String ActualResultFail,boolean condition1) throws IOException {
		generateReport(TestID+" "+groupNumber+" "+TestCondition, TestID+" "+groupNumber, ActualResultPass, ActualResultFail, condition1);
	}


	public void updateReport(String TestID,String TestCondition,String ActualResultPass,String ActualResultFail,boolean condition1) throws IOException {
		generateReport(TestID+" "+TestCondition, TestID, ActualResultPass, ActualResultFail, condition1);
	}

	public void addRegressionTestResults(String ticketID,String TestCondition,String ActualResultPass,String ActualResultFail,boolean condition1) throws IOException {
		generateReport("TicketID"+ticketID+" "+TestCondition, ticketID, ActualResultPass, ActualResultFail, condition1);
	}
	
	public void addRegressionTestResults(String ticketID,int subTestID,String TestCondition,String ActualResultPass,String ActualResultFail,boolean condition1) throws IOException {
		generateReport("TicketID"+ticketID+" "+subTestID+" "+TestCondition, ticketID+" "+subTestID, ActualResultPass, ActualResultFail, condition1);
	}

	public void generateReport(String condition,String Screenshot_Number,String ActualResultPass,String ActualResultFail,boolean condition1) throws IOException {
		String testCondition=condition;
		String Screenshot_ID=Screenshot_Number;
		test=extent.createTest(condition);
		Status status=Status.PASS;
		Status status1=Status.FAIL;
		if (condition1) {
			test.log(status.PASS, ActualResultPass).addScreenCaptureFromPath(captureScreenshot(Screenshot_ID));
		}
		else {
			test.log(status.FAIL, ActualResultFail).addScreenCaptureFromPath(captureScreenshot(Screenshot_ID));
		}
		
		/*if(ActualResultPass.equals(ActualResultFail)) {
			throw new ArithmeticException("Both Strings are same");

		}*/
	}

	public String captureScreenshot(String no) throws IOException {

		TakesScreenshot ts= (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(SCREENSHOT_PATH+no+".jpeg");
		String absolutepath_screen="."+SCREENSHOT_PATH+no+".jpeg";/* dest.getAbsolutePath();*/
		Files.copy(src,dest);

		return absolutepath_screen;
	}
}
