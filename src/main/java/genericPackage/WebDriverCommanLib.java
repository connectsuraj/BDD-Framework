package genericPackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class WebDriverCommanLib extends BaseTest {

	public void selectTheOptionOfDropdown(WebElement element,int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void selectTheOptionOfDropdown(WebElement element,String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void selectTheOptionOfDropdown(String visibleText,WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	//Frames

	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}


	//Mouse Actions
	public void rightClick(WebElement targetElement) {
		Actions act =new Actions(driver);
		act.contextClick(targetElement).perform();
	}
	public void mouseOver(WebElement targetElement) {
		Actions act= new Actions(driver);
		act.moveToElement(targetElement).perform();
	}
	public void doubleClick(WebElement targetElement) {
		Actions act= new Actions(driver);
		act.doubleClick(targetElement).perform();
	}
	public void dragAndDrop(WebElement src,WebElement destination) {
		Actions act= new Actions(driver);
		act.dragAndDrop(src,destination).perform();
	}

	//Scrolling Operations
	public void scrollDown(int pixels) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,"+pixels+")");
	}

	public void scrollUp(int pixels1) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,"+(-pixels1)+")");
	}
	public void scrollRight(int pixels) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy("+pixels+",0)");
	}
	public void scrollLeft(int pixels) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy("+(-pixels)+",0)");
	}

	public void scrollTillPerticularElement(WebElement element) {
		Point loc = element.getLocation();
		int xaxis = loc.getX();
		int yaxis=loc.getY();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy("+xaxis+","+yaxis+")");
	}

	public void waitUntilElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementIsClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}


	public void saveWebTableValuesInExcel(ArrayList<String> listName1,List<WebElement> listWebElementName,String excelPath,String sheetName,int columnCount) throws EncryptedDocumentException, IOException {
		FileLibrary flib = new FileLibrary();


		for(int i=0;i<listWebElementName.size();i++) {
			String data = listWebElementName.get(i).getText();
			listName1.add(data);
			flib.writeExcelData(excelPath, sheetName, i, columnCount, data);			
		}

	}
	public void saveClinicianInExcel(List<WebElement> listWebElementName,String excelPath,String sheetName,int rowCount,int columnCount) throws EncryptedDocumentException, IOException {
		FileLibrary flib = new FileLibrary();


		for(int i=0;i<listWebElementName.size();i++) {
			String data = listWebElementName.get(i).getText();
			flib.writeExcelData(excelPath, sheetName, rowCount, columnCount, data);			
		}
	}



	public boolean sortStringTableData(List<WebElement> listElement,WebElement element,String headerName,int count) throws InterruptedException {
		WebDriverCommanLib driverWCL=new WebDriverCommanLib();
		SoftAssert softAssert=new SoftAssert();
		Thread.sleep(6000);
		List<String> beforeSortpatientName=new ArrayList();
		for (WebElement data : listElement) {
			String value = data.getText();
			System.out.println(value);
			beforeSortpatientName.add(value);
		}
		driverWCL.waitUntilElementIsClickable(element);
		for (int i = 1; i <=count; i++) {
			element.click();
		}


		List<String> FirstClickData= new ArrayList();
		for (WebElement data0 : listElement) {
			String value0 = data0.getText();
			System.out.println(value0);
			FirstClickData.add(value0);
		}

		Collections.sort(beforeSortpatientName);
		boolean sucessfull=false;
		boolean sort=false;
		if (beforeSortpatientName.equals(FirstClickData)) {
			System.out.println("Table Data is sorted in Ascending order when we click on '"+headerName+"' link");
			sort=true;
			sucessfull=true;
		}
		else {
			Collections.reverse(beforeSortpatientName);
			if (beforeSortpatientName.equals(FirstClickData)) {
				System.out.println("Table Data is Sorted in Descending order when we click on '"+headerName+"' link");
				sucessfull=true;
			}
			else {
				System.out.println("Table Data is not Sorted in Descending or Ascending order when we click on '"+headerName+"' link");
				sucessfull=false;

			}

		}

		element.click();
		List<String> SecondClickData= new ArrayList();
		for (WebElement data1 : listElement) {
			String value1 = data1.getText();
			SecondClickData.add(value1);
		}

		if (sort) {
			Collections.reverse(beforeSortpatientName);
			if (beforeSortpatientName.equals(SecondClickData)) {
				System.out.println("Table Data is Sorted in Descending order when we click on '"+headerName+"' link");
				sucessfull=true;
			}
			else {
				System.out.println("Table Data is not Sorted in Descending order when we click on '"+headerName+"' link");
				sucessfull=false;

			}
		}
		else {
			Collections.reverse(beforeSortpatientName);
			if (beforeSortpatientName.equals(SecondClickData)) {
				System.out.println("Table Data is Sorted in Ascending order when we click on '"+headerName+"' link");
				sucessfull=true;
			}
			else {
				System.out.println("Table Data is not Sorted in Ascending order when we click on '"+headerName+"' link");
				sucessfull=false;

			}
		}
		return sucessfull;
	}


	public void selectRandomValueFromDropdown(List<WebElement> list) {
		Random random= new Random();
		int dropdownsize = list.size();
		System.out.println(dropdownsize);
		ArrayList<Integer> totalValues= new ArrayList();
		for (int i = 0; i < dropdownsize; i++) {
			totalValues.add(i);
		}
		System.out.println(totalValues);
		int index = random.nextInt(totalValues.size());
		Integer randomInt = totalValues.get(index);
		System.out.println(randomInt);
		System.out.println(index);
		for (int i = 0; i <totalValues.size(); i++) {
			if (i==randomInt) {
				String ddV = list.get(i).getText();
				System.out.println(ddV);
				list.get(i).click();
				break;
			}

		}
	}

	public Alert alertIsPresent() {
		WebDriverWait driverWait=new WebDriverWait(driver, Duration.ofSeconds(40));
		Alert alert = driverWait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}

	public void acceptAlert() {
		try {
			WebDriverWait driverWait=new WebDriverWait(driver, Duration.ofSeconds(40));
			driverWait.until(ExpectedConditions.alertIsPresent());
			Alert alert=driver.switchTo().alert();
			alert.accept();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void dismissAlert() {
		try {
			WebDriverWait driverWait=new WebDriverWait(driver, Duration.ofSeconds(40));
			driverWait.until(ExpectedConditions.alertIsPresent());
			Alert alert=driver.switchTo().alert();
			alert.dismiss();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public boolean validate_Dropdown_Values(WebElement dropdown,List<WebElement> dropdownOptions, WebElement dropdownFirstValue,ArrayList<String> dropdownValuesToBe) {
		
		dropdown.click();
		waitUntilElementVisible(dropdownFirstValue);
		
		ArrayList<String> dropdownOptionValueText= new ArrayList<>();
		
		for (WebElement options : dropdownOptions) {
			String optionValueText = options.getText();
			dropdownOptionValueText.add(optionValueText);
		}
		
		boolean areEqual = new HashSet<String>(dropdownOptionValueText).equals(new HashSet<>(dropdownValuesToBe));
		return areEqual;
		
	}
	
	public ArrayList<String> convertArrayIntoArraylist(String[] array) {
		ArrayList<String> arrayList = new ArrayList<>(array.length);
        for (String item : array) {
            arrayList.add(item);
        }
        
        return arrayList;
	}
	
	
	public void selectRandomFlag(List<WebElement> list) {
		Random random= new Random();
		int flagsize = list.size();
		System.out.println(flagsize);
		ArrayList<Integer> totalValues= new ArrayList();
		for (int i = 0; i < flagsize; i++) {
			totalValues.add(i);
		}
		System.out.println(totalValues);
		int index = random.nextInt(totalValues.size());
		Integer randomInt = totalValues.get(index);
		System.out.println(randomInt);
		System.out.println(index);
		for (int i = 0; i <totalValues.size(); i++) {
			if (i==randomInt) {
				list.get(i).click();
				break;
			}

		}
	}


}



