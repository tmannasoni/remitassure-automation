package com.remitassure.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseClass {
    Properties prop;
    WebDriver driver;
    
	//loadConfigFile
	public void loadConfig() throws IOException{
		prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Ramneek\\eclipse-workspace\\automationFramework\\src\\test\\resources\\config.properties");
		prop.load(fis);
	}
	
	// initialize the driver according to browser mentioned in the config file
	public void initializeDriver() {
		String browser=prop.getProperty("browser").trim().toLowerCase();
		
		if (browser.equals("chrome")) {
		 driver=new ChromeDriver();
		}
		else if(browser.equals("edge")) {
		 driver = new EdgeDriver();
		}
		else {
		    //if no mentioned if found, then switch to chrome    
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
	}
	
	//launch the url
	public void launchURL() {
		String url=prop.getProperty("url");
		driver.get(url);
	}
	
	//tear Down if driver is there
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	@BeforeMethod
    public void setUp() throws IOException {
        loadConfig();
        initializeDriver();
        launchURL();
    }

    @AfterMethod
    public void cleanUp() {
        tearDown();
    }
	
	
	

}
