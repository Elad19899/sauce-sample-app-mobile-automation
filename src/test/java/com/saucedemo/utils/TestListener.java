package com.saucedemo.utils;

import com.saucedemo.drivers.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    
    @Override
    public void onTestFailure(ITestResult result) {
        if (DriverManager.getDriver() != null) {
            saveScreenshot(result.getName());
        }
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(String testName) {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
