package com.remitassure.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	
    protected WebDriver driver;
	
	protected AbstractPage(WebDriver driver){
		this.driver=driver;

	}

	
	public void waitForelement(WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	
	
}
