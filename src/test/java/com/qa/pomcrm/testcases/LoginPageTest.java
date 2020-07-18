package com.qa.pomcrm.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pomcrm.base.TestBase;
import com.qa.pomcrm.pages.HomePage;
import com.qa.pomcrm.pages.LoginPage;
import com.qa.pomcrm.util.TestConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;

	public LoginPageTest() throws IOException {
		super();
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("Allure - Pre-conditions")
	@BeforeMethod
	public void setUp() throws IOException {
		initalization();
		loginPage = new LoginPage();
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("Allure - Post-conditions")
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Allure - Test for validate the Login page URL")
	@Test(priority = 1, description = "Test for validate the Login page URL")
	public void verifyLoginPageURLTest() {
		String url =  loginPage.validateLoginPageURL();
		Assert.assertEquals(url, TestConstants.homePageURL);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Allure - Test for validate the Login page Title")
	@Test(priority = 2, description = "Test for validate the Login page Title")
	public void vaerifyLoginPageTitle() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, TestConstants.homePageTitle);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Allure - Test for validate the Login functionality")
	@Test(priority = 3, description = "Test for validate the Login functionality")
	public void verifyLoginTest() throws IOException {
		homePage = loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
}
