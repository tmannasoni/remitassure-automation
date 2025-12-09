package com.remitassure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.remitassure.base.AbstractPage;

public class OTPScreen  extends AbstractPage {

	public OTPScreen(WebDriver driver) {
		super(driver);
		  PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//input[contains(@aria-label, 'Digit 1')]")
	WebElement otpEntryField;
	
	@FindBy(xpath="//button[contains(@class, 'signup_button')]")
	WebElement verifyOtp;
	
	public void enterOtp(int otp) {
		otpEntryField.sendKeys(String.valueOf(otp));
		
	}
	
	public DashboardPage submitOtp() {
		verifyOtp.click();
	    return new DashboardPage(driver);
	}
	
	
}
