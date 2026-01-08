package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CatalogPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(description = "Navigate through checkout steps up to confirmation")
    public void testBasicCheckout() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.selectFirstProduct();

        ProductDetailsPage detailsPage = new ProductDetailsPage();
        detailsPage.addToCart();
        detailsPage.goToCart();

        CartPage cartPage = new CartPage();
        cartPage.checkout();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.enterShippingDetails("John", "Doe", "12345");
        checkoutPage.finishCheckout();

        Assert.assertTrue(checkoutPage.isOrderComplete(), "Order should be completed");
    }
}
