package com.remitassure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.remitassure.base.AbstractPage;

public class LandingPage extends AbstractPage {

	LandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class=logactin]")
	public WebElement loginButton;
	
	
	public SignIn loginClick() {
	loginButton.click();
	return new SignIn(driver);
	}

	
	

}
