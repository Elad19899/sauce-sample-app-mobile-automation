package com.saucedemo.tests;

import com.saucedemo.pages.CatalogPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        CatalogPage catalogPage = new CatalogPage();
        Assert.assertTrue(catalogPage.isPageLoaded(), "Catalog page should be loaded after login");
    }

    @Test(description = "Login with invalid credentials shows error")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("invalid_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message text mismatch");
    }
}
