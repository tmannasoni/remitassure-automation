package com.remitassure.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

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
        signInPage = landingPage.loginButtonClick();
        Thread.sleep(3000);  // Consider replacing with explicit wait
    }

    @Test(groups= {"login","smoke"})
    public void testValidLogin() throws InterruptedException {
        // Fill login details
        signInPage.fillLoginDetails("+55 (BR)", 96969696, "Test@123");

        // Submit login and get OTP page object
        OtpChannelSelection otpChannelSelection = signInPage.submitLogin();

        // Assert login was successful
        assertNotNull(otpChannelSelection, "Login failed: OTP page not found");

        // Continue OTP flow
        otpChannelSelection.selectOtpViaPhone();
        OTPScreen otpScreen = otpChannelSelection.confirmOtpChannel();
        Thread.sleep(3000);  // Replace with explicit wait

        otpScreen.enterOtp(162583);
        DashboardPage dashboardPage = otpScreen.submitOtp();

        // Optional: Assert dashboard page loaded
        assertNotNull(dashboardPage, "Dashboard not loaded after OTP submission");
    }

    @Test(groups= {"login","negative"})
    public void loginWithInvalidCredentials() throws InterruptedException {
        // Fill login details
        signInPage.fillLoginDetails("+55 (BR)", 969690696, "T0est@123");

        // Submit login and get OTP page object
        OtpChannelSelection otpChannelSelection = signInPage.submitLogin();

        // Assert login failed
        assertNull(otpChannelSelection, "Login unexpectedly succeeded with invalid credentials");

        // Capture and assert error message
        String errorMessage = signInPage.getToastMessage();
        System.out.println("Error message: " + errorMessage);
        assertEquals(errorMessage, "Invalid credentials");  // Replace with actual app message
    }
}
