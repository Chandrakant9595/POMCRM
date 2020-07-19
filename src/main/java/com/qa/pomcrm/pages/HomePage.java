package com.qa.pomcrm.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.pomcrm.base.TestBase;
import com.qa.pomcrm.util.TestUtil;


public class HomePage extends TestBase{

	//company menu
	@FindBy(xpath = "//a[text()='Companies']")
	WebElement companyLink;
	
	//New company link New Company
	@FindBy(xpath = "//a[text()='New Company']")
	WebElement newCompanyLink;
	
	public HomePage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	public CreateCompanyPage clickOnNewCompanyLink() throws IOException {
		TestUtil.moveToElement(driver, companyLink);
		newCompanyLink.click();
		return new CreateCompanyPage();
	}
}