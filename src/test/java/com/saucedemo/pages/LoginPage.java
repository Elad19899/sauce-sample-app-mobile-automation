package com.saucedemo.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    
    private final By usernameField = AppiumBy.accessibilityId("test-Username");
    private final By passwordField = AppiumBy.accessibilityId("test-Password");
    private final By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private final By errorMessage = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Username and password do not match')]");

    public void login(String username, String password) {
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        click(loginButton);
    }
    
    public String getErrorMessage() {
        return getText(errorMessage);
    }
    
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }
}
