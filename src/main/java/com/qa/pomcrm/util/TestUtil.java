package com.qa.pomcrm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.pomcrm.base.TestBase;

public class TestUtil extends TestBase{

	public TestUtil() throws IOException {
		super();
	}
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;
	
	static String parentWindow;
	static String childWindow;
	
	public static String TESTDATA_SHEET_PATH = "D:\\Projects\\POMCRM\\src\\main\\java\\com\\qa\\pomcrm\\testdata\\TestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	
	//data driven function
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}	
	
	//screen shot function
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	//page scroll down function
	public static void scrollPage() {
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
	}
	
	//get current date function
	public static String getCurrectDate() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timeStamp;
	}

	//replace string function
	public static String replaceString(String rString) {
		return rString.replaceAll("[^a-zA-Z0-9]", "");
	}
	
	//trim string function
	public static String trimString(String tString) {
		return tString.trim();
	}
	
	//Switch to child window
	public static void switchToChildWindow() {
		parentWindow = driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		java.util.Iterator<String> itr= set.iterator();
		while(itr.hasNext()) {
			childWindow = itr.next();
			if(!parentWindow.equalsIgnoreCase(childWindow)){
				driver.switchTo().window(childWindow);
			}
		}
	}
	
	//switch to parent window
	public static void switchToParentWindow() {
		driver.switchTo().window(parentWindow);
	}
	
	//wait for web element
	public static void waitForWebElementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
