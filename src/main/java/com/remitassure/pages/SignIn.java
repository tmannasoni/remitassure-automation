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

    // --- Locators ---
    @FindBy(xpath = "//select[contains(@class,'login-code-select')]")
    public WebElement countryCodeButton;

    @FindBy(xpath = "//select[contains(@class,'login-code-select')]//option")
    public List<WebElement> countryCodeOptions;

    @FindBy(css = "input[placeholder='Email/Mobile']")
    public WebElement emailAndMobileInput;

    @FindBy(css = "input[placeholder='Enter Password...']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@class='login_button']")
    public WebElement loginSubmit;

    @FindBy(css = "div[role='alert']")
    private WebElement toastMessage;

    @FindBy(xpath = "//h4[text()='OTP Delivery Preference']")
    private WebElement otpHeading;

    // --- Methods ---

    /**
     * Fill the login form (does NOT click submit)
     */
    public void fillLoginDetails(String countryCode, int number, String password) {
        countryCodeButton.click();
        countryCodeOptions.stream()
            .filter(option -> option.getText().equalsIgnoreCase(countryCode))
            .findFirst()
            .ifPresent(WebElement::click);

        emailAndMobileInput.clear();
        emailAndMobileInput.sendKeys(String.valueOf(number));

        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Submit the login form and detect if OTP page appears.
     * Returns OTP page object if successful, otherwise returns null.
     */
    public OtpChannelSelection submitLogin() {
        loginSubmit.click();

        // Direct element check with try-catch
        try {
            if (otpHeading.isDisplayed()) {
                return new OtpChannelSelection(driver);
            }
        } catch (Exception e) {
            // OTP element not present → login failed
        }

        return null;
    }

    /**
     * Retrieve any toast/error message (for failed login)
     */
    public String getToastMessage() {
        waitForelement(toastMessage);
        return toastMessage.getText();
    }
}
