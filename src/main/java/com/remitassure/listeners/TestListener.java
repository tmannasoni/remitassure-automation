package com.remitassure.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.remitassure.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

	    @Override
	    public void onTestStart(ITestResult result) {
	        // called before each @Test method
	        System.out.println("STARTING TEST:");
	        System.out.println("Test Name  : " + result.getMethod().getMethodName());
	        System.out.println("Test Class : " + result.getTestClass().getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        // called when test passes
	        long duration = result.getEndMillis() - result.getStartMillis();

	        System.out.println("TEST PASSED:");
	        System.out.println("Test Name : " + result.getMethod().getMethodName());
	        System.out.println("Duration  : " + duration + " ms");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        // called when test fails
	        System.out.println("TEST FAILED:");
	        System.out.println("Test Name : " + result.getMethod().getMethodName());
	        // 1️⃣ Get driver safely (thread-safe)
	        WebDriver driver = com.remitassure.base.BaseClass.getDriver();

	        // 2️⃣ Capture screenshot
	        String screenshotPath = ScreenshotUtils.captureScreenshot(
	                driver,
	                result.getMethod().getMethodName()
	        );

	        System.out.println("Screenshot saved at: " + screenshotPath);

	        Throwable error = result.getThrowable();
	        if (error != null) {
	            System.out.println("Failure Reason : " + error.getMessage());
	        }
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        // called when test is skipped
	        System.out.println("TEST SKIPPED:");
	        System.out.println("Test Name : " + result.getMethod().getMethodName());

	        Throwable skipReason = result.getThrowable();
	        if (skipReason != null) {
	            System.out.println("Skip Reason : " + skipReason.getMessage());
	        }
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        // called before <test> tag in testng.xml
	        System.out.println("=====================================");
	        System.out.println("TEST BLOCK STARTED: " + context.getName());
	        System.out.println("TOTAL TEST METHODS: " + context.getAllTestMethods().length);
	        System.out.println("=====================================");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        // called after <test> tag finishes
	        System.out.println("=====================================");
	        System.out.println("TEST BLOCK FINISHED: " + context.getName());
	        System.out.println("PASSED  : " + context.getPassedTests().size());
	        System.out.println("FAILED  : " + context.getFailedTests().size());
	        System.out.println("SKIPPED : " + context.getSkippedTests().size());
	        System.out.println("=====================================");
	    }
	}


