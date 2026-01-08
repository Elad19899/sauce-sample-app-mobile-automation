package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CatalogPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(description = "Add item to cart")
    public void testAddItemToCart() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.selectFirstProduct();
        
        ProductDetailsPage detailsPage = new ProductDetailsPage();
        detailsPage.addToCart();
        detailsPage.goToCart();
        
        CartPage cartPage = new CartPage();
        Assert.assertTrue(cartPage.isItemDisplayed(), "Item should be in cart");
    }

    @Test(description = "Remove item from cart")
    public void testRemoveItemFromCart() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.selectFirstProduct();
        
        ProductDetailsPage detailsPage = new ProductDetailsPage();
        detailsPage.addToCart();
        detailsPage.goToCart();
        
        CartPage cartPage = new CartPage();
        cartPage.removeItem();
        
        Assert.assertFalse(cartPage.isItemDisplayed(), "Item should be removed from cart");
    }
}
