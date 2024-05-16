package genericPackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListner extends BaseTest implements ITestListener  {
   // @Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Start");
	}
    //@Override
	public void onTestFailure(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		failed(methodName);
	}
	

}
