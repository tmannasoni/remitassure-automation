package com.remitassure.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.remitassure.base.AbstractPage;

public class NewTransferPage extends AbstractPage{
	
	public NewTransferPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//select[@aria-label='Select a reason'][1]")
	WebElement fromCurrencySelection;
	
	@FindBy(xpath="//select[@aria-label='Select a reason'][2]")
	WebElement toCurrencySelection;
   
	@FindBy(xpath="//select[@aria-label='Select a reason'][1]//option")
	List<WebElement> fromCurrencyOptions;
	
	@FindBy(xpath="//select[@aria-label='Select a reason'][2]//option")
	List<WebElement> toCurrencyOptions;
   
	@FindBy(name="send_amt")
	WebElement sendAmountInputField;
	
	@FindBy(name="exchange_amt")
	WebElement receiveAmountInputField;
	
	@FindBy(xpath="//a[contains(text(), 'Send Money')")
	WebElement clickToGetAmount;
	//Continue
	@FindBy(xpath="//a[contains(text(), 'Continue')]")
	WebElement ContinueButtonFromNewTransfer;
	
	
	public void selectCurrencies(String fromCurrency, String toCurrency) {
		fromCurrencySelection.click();
		fromCurrencyOptions.stream().filter(s->s.getText().equalsIgnoreCase(fromCurrency)).findFirst().ifPresent(WebElement::click);
		
		toCurrencySelection.click();
		toCurrencyOptions.stream().filter(s->s.getText().equalsIgnoreCase(toCurrency)).findFirst().ifPresent(WebElement::click);	
	}
	
	public TransferRecipientPage inputAmountAndGetExchange(float sendAmountValue) {
		sendAmountInputField.sendKeys(String.valueOf(sendAmountValue));
		clickToGetAmount.click();
		ContinueButtonFromNewTransfer.click();
		return new TransferRecipientPage(driver);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
