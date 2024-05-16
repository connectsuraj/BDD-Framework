package genericPackage;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry extends BaseTest  implements IRetryAnalyzer{

	int initCount=0;
	int maxCount=1;
	//@Override
	public boolean retry(ITestResult result) {
		if (initCount<maxCount) {
			initCount++;
			return true;
		}
		return false;
	}

}
