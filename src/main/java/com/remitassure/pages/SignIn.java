package com.remitassure.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.remitassure.base.AbstractPage;

public class SignIn extends AbstractPage {

	public SignIn(WebDriver driver) {
		super(driver);
		  PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//select[contains(@class,'login-code-select')]")
	public WebElement countryCodeButton;
	
	@FindBy(xpath="//select[contains(@class,'login-code-select')]//option")
	public List<WebElement> countryCodeOptions;
	
	@FindBy(css="input[placeholder='Email/Mobile']")
	public WebElement emailAndMobileInput;

	@FindBy(css="input[placeholder='Enter Password...']")
	public WebElement passwordField;

	@FindBy(xpath = "//button[@class='login_button']")
	public WebElement loginSubmit;
	
	@FindBy(css = "div[role='alert']")
	private WebElement toastMessage;
	
	public OtpChannelSelection loginByPhone(String countryCode, int number, String password) throws InterruptedException {
		
		countryCodeButton.click();
		Thread.sleep(2000);
		countryCodeOptions.stream().filter(s->s.getText().equalsIgnoreCase(countryCode))
		.findFirst()
		.ifPresent(WebElement::click);;
		 
		emailAndMobileInput.sendKeys(String.valueOf(number));
		passwordField.sendKeys(password);
		loginSubmit.click();
		
		return new OtpChannelSelection(driver);
	}
	
	 public String getToastMessage() {
		 waitForelement(toastMessage);
	        return toastMessage.getText();
	    }
	
	

}
