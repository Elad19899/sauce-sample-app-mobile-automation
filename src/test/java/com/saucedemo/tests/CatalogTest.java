package com.saucedemo.tests;

import com.saucedemo.pages.CatalogPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CatalogTest extends BaseTest {

    // We could make this part of BaseTest if every test needs login, but LoginTest
    // doesn't want pre-login.
    // So distinct manual login is fine.

    @Test(description = "Product list loads and key elements are visible")
    public void testProductListLoads() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        CatalogPage catalogPage = new CatalogPage();
        Assert.assertTrue(catalogPage.isPageLoaded(), "Product list should load");
    }

    @Test(description = "Open product details from the list")
    public void testOpenProductDetails() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.selectFirstProduct();

        ProductDetailsPage detailsPage = new ProductDetailsPage();
        Assert.assertTrue(detailsPage.isAddToCartDisplayed(), "Add to cart button should be displayed on details page");
    }
}
