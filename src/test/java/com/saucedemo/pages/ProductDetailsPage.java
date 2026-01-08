package com.saucedemo.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {

    private final By addToCartButton = AppiumBy.accessibilityId("test-ADD TO CART");
    private final By removeButton = AppiumBy.accessibilityId("test-REMOVE");
    private final By backToProducts = AppiumBy.accessibilityId("test-BACK TO PRODUCTS"); // Usually visible
    private final By cartIcon = AppiumBy.accessibilityId("test-Cart");

    public void addToCart() {
        click(addToCartButton);
    }

    public void removeFromCart() {
        click(removeButton);
    }

    public void goToCart() {
        click(cartIcon);
    }

    public boolean isAddToCartDisplayed() {
        return isDisplayed(addToCartButton);
    }

    public boolean isRemoveDisplayed() {
        return isDisplayed(removeButton);
    }
}
