package com.remitassure.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.remitassure.base.AbstractPage;

public class DashboardPage extends AbstractPage {

	DashboardPage(WebDriver driver) {
		super(driver);
		  PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//a[@class=logactin]")
	public WebElement loginButton;
	
	@FindBy(xpath="//select[contains(@class,'login-code-select')]")
	public WebElement countryCodeButton;
	
	@FindBy(xpath="//select[contains(@class,'login-code-select')]//option")
	public List<WebElement> countryCodeOptions;
	
	@FindBy(css="input[placeholder='Email/Mobile']")
	public WebElement emailAndMobileInput;

	@FindBy(css="input[placeholder='Enter Password...']")
	public WebElement passwordField;

	@FindBy(xpath = "//a[@class=login_button]")
	public WebElement loginSubmit;
	
	public void loginClick() {
	loginButton.click();
	}
	
	public void loginByPhone(String countryCode, int number, String password) {
		countryCodeButton.click();
		
		
		
	}
	
	

}
