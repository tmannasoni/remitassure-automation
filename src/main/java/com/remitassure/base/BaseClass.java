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
   // public WebDriver driver;
    /*
     * WHY ThreadLocal<WebDriver> instead of normal WebDriver variable?
     * ---------------------------------------------------------------
     * 1. TestNG can run tests in parallel.
     * 2. A normal WebDriver variable gets overwritten by parallel tests.
     * 3. ThreadLocal gives EACH test thread its OWN WebDriver instance.
     *
     * HOW it works:
     * - Each test method runs in a separate thread
     * - ThreadLocal stores data specific to that thread only
     */
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /*
     * WHY this method?
     * ----------------
     * 1. Listener does NOT extend BaseClass
     * 2. Listener still needs access to WebDriver (for screenshots, logs)
     * 3. Static method allows global, safe access to current test's driver
     *
     * WHAT it returns:
     * - WebDriver associated with the CURRENT test thread
     */
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    // Load config file
    public void loadConfig() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
            "C:\\Users\\Ramneek\\eclipse-workspace\\automationFramework\\src\\test\\resources\\config.properties");
        prop.load(fis);
    }
    
    // Initialize driver using WebDriverManager
    /*
     * WHY initializeDriver()?
     * -----------------------
     * 1. Browser selection should be dynamic
     * 2. Controlled in one place (BaseClass)
     * 3. Prevents duplicate driver creation in tests
     *
     * HOW ThreadLocal is used here:
     * - driver.set(...) binds driver to the current test thread
     */
    public void initializeDriver() {
        String browser = prop.getProperty("browser").trim().toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
        }

        getDriver().manage().window().maximize();
    }
    
    // Launch the URL
    public void launchURL() {
    	getDriver().get(prop.getProperty("url"));
    }
    
    // Tear down driver
    public void tearDown() {
        if (getDriver() != null) {
        	getDriver().quit();
        	driver.remove(); 
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
