package com.remitassure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.remitassure.base.AbstractPage;

import org.openqa.selenium.support.FindBy;

public class DashboardPage extends AbstractPage{

	public DashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
   
	@FindBy(xpath="//a[contains(text(), 'Send Money')]")
	WebElement sendMoney;
	
	public NewTransferPage clickOnSendMoney() {
		sendMoney.click();
		return new NewTransferPage(driver);
		
	}
}
