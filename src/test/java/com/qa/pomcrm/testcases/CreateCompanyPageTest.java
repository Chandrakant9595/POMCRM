package com.qa.pomcrm.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pomcrm.base.TestBase;
import com.qa.pomcrm.pages.CreateCompanyPage;
import com.qa.pomcrm.pages.HomePage;
import com.qa.pomcrm.pages.LoginPage;

public class CreateCompanyPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	CreateCompanyPage createCompanyPage;
		
	public CreateCompanyPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException {
		initalization();
		loginPage = new LoginPage();
		homePage = loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void addNewCompanyTest() throws IOException {
		createCompanyPage = homePage.clickOnNewCompanyLink();
	}
}
