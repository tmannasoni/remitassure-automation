package com.remitassure.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;


public class BaseClass {
    protected Properties prop;
    public WebDriver driver;
    
    // Load config file
    public void loadConfig() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
            "C:\\Users\\Ramneek\\eclipse-workspace\\automationFramework\\src\\test\\resources\\config.properties");
        prop.load(fis);
    }
    
    // Initialize driver using WebDriverManager
    public void initializeDriver() {
        String browser = prop.getProperty("browser").trim().toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
    }
    
    // Launch the URL
    public void launchURL() {
        driver.get(prop.getProperty("url"));
    }
    
    // Tear down driver
    public void tearDown() {
        if (driver != null) {
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
