package com.saucedemo.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CatalogPage extends BasePage {

    private final By productTitle = AppiumBy.xpath("//android.widget.TextView[@text='PRODUCTS']");
    private final By firstProduct = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='test-Item'])[1]");
    private final By cartIcon = AppiumBy.accessibilityId("test-Cart");
    // This assumes we are on the product details page if we click a product, or add
    // to cart button on list
    private final By addToCartButtonOriginal = AppiumBy.accessibilityId("test-ADD TO CART"); // Capitalization varies
                                                                                             // sometimes, let's assume
                                                                                             // this for now.
    // Actually the button text changes to REMOVE so accessing by ID is safer if
    // it's consistent.
    // The accessibility ID is usually "test-ADD TO CART" or "test-REMOVE" depending
    // on state.

    // Let's refine selectors.
    // The main list has items with "test-Item". Inside that, "test-Item title".

    public boolean isPageLoaded() {
        return isDisplayed(productTitle);
    }

    public void selectFirstProduct() {
        click(firstProduct);
        // This should navigate to Product Details.
    }

    public void goToCart() {
        click(cartIcon);
    }

    public String getProductTitle(int index) {
        // XPath index starts at 1
        return getText(AppiumBy.xpath("(//android.view.ViewGroup[@content-desc='test-Item'])[" + (index + 1)
                + "]//android.widget.TextView[@content-desc='test-Item title']"));
    }
}
