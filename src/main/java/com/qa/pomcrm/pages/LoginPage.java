package com.qa.pomcrm.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.pomcrm.base.TestBase;

import io.qameta.allure.Step;

public class LoginPage extends TestBase{

	@FindBy(name = "username")
	WebElement userName;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginButton;
	
	public LoginPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	@Step("Test step for validate Login Page title")
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	@Step("Test step for validate Login Page URL")
	public String validateLoginPageURL() {
		return driver.getCurrentUrl();
	}

	@Step("Test step for validate Login functionality")
	public HomePage validateLogin(String un, String pass) throws IOException {
		userName.clear();
		userName.sendKeys(un);
		password.clear();
		password.sendKeys(pass);
		loginButton.click();
		
		return new HomePage();
	}
}
