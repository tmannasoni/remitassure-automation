package com.remitassure.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    // Utility class → no object creation needed
    private ScreenshotUtils() {}

    /**
     * Captures screenshot and returns absolute file path
     */
    public static String captureScreenshot(WebDriver driver, String testName) {

        // 1️⃣ Convert driver to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // 2️⃣ Capture screenshot as FILE
        File source = ts.getScreenshotAs(OutputType.FILE);

        // 3️⃣ Create timestamp (to avoid overwriting screenshots)
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        // 4️⃣ Destination path
        String destinationPath = System.getProperty("user.dir")
                + "/screenshots/"
                + testName + "_" + timestamp + ".png";

        File destination = new File(destinationPath);

        // 5️⃣ Copy file
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 6️⃣ Return path (useful later for reports)
        return destinationPath;
    }
}
