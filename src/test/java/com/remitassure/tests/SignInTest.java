package com.remitassure.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.remitassure.base.BaseClass;
import com.remitassure.pages.DashboardPage;
import com.remitassure.pages.LandingPage;
import com.remitassure.pages.OTPScreen;
import com.remitassure.pages.OtpChannelSelection;
import com.remitassure.pages.SignIn;

public class SignInTest extends BaseClass {
	
	private LandingPage landingPage; 
	private SignIn signInPage;
	

	
	@BeforeMethod
	public void setUpLogin() throws InterruptedException {
	landingPage = new LandingPage(driver);
	signInPage=landingPage.loginButtonClick();
	 Thread.sleep(3000);
	}
	
	
	@Test
	public void testValidLogin() throws InterruptedException {

	 OtpChannelSelection otpChannelSelection =signInPage.loginByPhone("+55 (BR)",96969696,"Test@123");
	 Thread.sleep(3000);
	 otpChannelSelection.selectOtpViaPhone();
	 OTPScreen otpscreen=otpChannelSelection.confirmOtpChannel();
	 Thread.sleep(3000);
	 otpscreen.enterOtp(162583);
	 DashboardPage dashboardPage=otpscreen.submitOtp();
	 
	}
	
	 @Test
	 public void loginWithInvalidCredentials() throws InterruptedException {
		 
        OtpChannelSelection otpChannelSelection =signInPage.loginByPhone("+55 (BR)",969690696,"T0est@123");	    
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
}
