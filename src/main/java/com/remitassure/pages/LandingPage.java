package com.remitassure.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.remitassure.base.AbstractPage;

public class LandingPage extends AbstractPage {

	public LandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='logactin']")
	public WebElement loginButton;
	
	
	public SignIn loginButtonClick() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	loginButton.click();
	return new SignIn(driver);
	}



	
	

}
