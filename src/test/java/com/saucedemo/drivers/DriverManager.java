package com.saucedemo.drivers;

import com.saucedemo.config.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.SessionNotCreatedException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.io.File;
import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void initializeDriver() {
        AppiumDriver originalDriver = null;
        ConfigLoader configLoader = ConfigLoader.getInstance();
        
        String platformName = configLoader.getProperty("platformName");
        String appPath = configLoader.getProperty("appPath");
        String deviceName = configLoader.getProperty("deviceName");
        String automationName = configLoader.getProperty("automationName");
        String appPackage = configLoader.getProperty("appPackage", null);
        String appActivity = configLoader.getProperty("appActivity", null);

        try {
            if ("Android".equalsIgnoreCase(platformName)) {
                UiAutomator2Options options = new UiAutomator2Options();
                options.setDeviceName(deviceName);
                options.setAutomationName(automationName);
                
                // If app is provided locally, use it
                File app = new File(appPath);
                if (app.exists()) {
                    options.setApp(app.getAbsolutePath());
                } else {
                     // If file doesn't exist, maybe it assumes it is installed or it's a relative path issue?
                     // For now let's warn but try to continue if package/activity are set
                     if (appPackage != null && appActivity != null) {
                         options.setAppPackage(appPackage);
                         options.setAppActivity(appActivity);
                     } else {
                         throw new RuntimeException("App file not found at " + appPath + " and no appPackage/Activity specified.");
                     }
                }
                
                if (appPackage != null) { // Optional if app is set, but good for restart
                     options.setAppPackage(appPackage);
                     options.setAppActivity(appActivity);
                }
                
                // No Reset / Full Reset
                options.setNoReset(Boolean.parseBoolean(configLoader.getProperty("noReset", "false")));
                options.setFullReset(Boolean.parseBoolean(configLoader.getProperty("fullReset", "false")));

                originalDriver = new AndroidDriver(URI.create("http://127.0.0.1:4723/").toURL(), options);
            } else {
                throw new IllegalArgumentException("Platform " + platformName + " not supported yet.");
            }
            
            originalDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(configLoader.getProperty("implicitWait"))));
            driver.set(originalDriver);
            
        } catch (MalformedURLException | SessionNotCreatedException e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
