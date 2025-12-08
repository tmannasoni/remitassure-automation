package com.remitassure.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {
	
    protected WebDriver driver;
	
	protected AbstractPage(WebDriver driver){
		this.driver=driver;

	}

	
	public void waitForelement(WebElement element) {
		
	}
	
	
	
	
	
}
