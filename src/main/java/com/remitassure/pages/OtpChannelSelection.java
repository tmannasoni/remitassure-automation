package com.remitassure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.remitassure.base.AbstractPage;

public class OtpChannelSelection extends AbstractPage{

		  OtpChannelSelection(WebDriver driver) {
			super(driver);
			PageFactory.initElements(driver, this);
		}
		  
 @FindBy(xpath="//label[text()='Mobile Number']")
	public WebElement otpViaPhone;	
 @FindBy(xpath="//label[text()='Email Id']")
	public WebElement otpViaEmail;	
 @FindBy(xpath="//button[contains(@class,\"otp-btn\")]")
	public WebElement otpPreferenceContinueButton;			  
  
		  
		  public void selectOtpViaPhone() {
			  otpViaPhone.click();
		  }
		  
		  public void selectOtpViaEmail() {
			  otpViaEmail.click();
		  }
		  public OTPScreen confirmOtpChannel() {
			  otpPreferenceContinueButton.click();
			  return new OTPScreen(driver);
		  }
		  
		  

}
