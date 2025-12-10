package com.remitassure.tests;

import org.testng.annotations.Test;

import com.remitassure.base.BaseClass;
import com.remitassure.pages.DashboardPage;
import com.remitassure.pages.LandingPage;
import com.remitassure.pages.OTPScreen;
import com.remitassure.pages.OtpChannelSelection;
import com.remitassure.pages.SignIn;

public class SignInTest extends BaseClass {

	@Test
	public void testValidLogin() throws InterruptedException {
	 LandingPage landingPage = new LandingPage(driver);
	 SignIn signInPage=landingPage.loginButtonClick();
	 Thread.sleep(3000);
	 OtpChannelSelection otpChannelSelection =signInPage.loginByPhone("+55 (BR)",96969696,"Test@123");
	 Thread.sleep(3000);
	 otpChannelSelection.selectOtpViaPhone();
	 OTPScreen otpscreen=otpChannelSelection.confirmOtpChannel();
	 Thread.sleep(3000);
	 otpscreen.enterOtp(162583);
	 DashboardPage dashboardPage=otpscreen.submitOtp();
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	}
	
}
