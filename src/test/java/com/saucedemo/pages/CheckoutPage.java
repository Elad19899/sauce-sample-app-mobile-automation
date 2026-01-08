package com.saucedemo.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    // Step 1: Information
    private final By firstNameField = AppiumBy.accessibilityId("test-First Name");
    private final By lastNameField = AppiumBy.accessibilityId("test-Last Name");
    private final By zipField = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private final By continueButton = AppiumBy.accessibilityId("test-CONTINUE");

    // Step 2: Overview
    private final By finishButton = AppiumBy.accessibilityId("test-FINISH");
    private final By itemOverview = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Item']");

    // Step 3: Complete
    private final By thankYouMessage = AppiumBy.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']");
    private final By backHomeButton = AppiumBy.accessibilityId("test-BACK HOME");

    public void enterShippingDetails(String firstName, String lastName, String zip) {
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(zipField, zip);
        click(continueButton);
    }

    public void finishCheckout() {
        // Here we might want to scroll down if FINISH is not visible, but for MVP let's assume it works or use logic
        // UiScrollable logic is specific to Android.
        try {
             click(finishButton);
        } catch (Exception e) {
             // Basic swipe or try to find it
             // For now assume it is visible or we are on large screen.
             // If not, we might need a scroll helper.
             // Scroll to text "FINISH"
             driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"FINISH\"))"));
             click(finishButton);
        }
    }

    public boolean isOrderComplete() {
        return isDisplayed(thankYouMessage);
    }
}
