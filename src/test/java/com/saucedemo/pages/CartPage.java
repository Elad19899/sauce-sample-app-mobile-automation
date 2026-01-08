package com.saucedemo.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By cartItem = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Item']");
    private final By removeButton = AppiumBy.accessibilityId("test-REMOVE");
    private final By checkoutButton = AppiumBy.accessibilityId("test-CHECKOUT");
    private final By continueShoppingButton = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");

    public boolean isItemDisplayed() {
        return isDisplayed(cartItem);
    }

    public void removeItem() {
        click(removeButton);
    }

    public void checkout() {
        click(checkoutButton);
    }
}
