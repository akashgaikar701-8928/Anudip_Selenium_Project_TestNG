package com.anudip.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
* AllTestsPart2
* =============
* This test class covers the complete checkout workflow of SauceDemo:
* - Login
* - Add product to cart
* - Checkout process
* - Order confirmation
* - Cart validation after order
* - Logout
*
* Framework Used: Selenium WebDriver + TestNG
* Application Under Test: SauceDemo (Swag Labs)
*/
public class CheckoutFlowTests extends BaseClass {
	protected static final Logger log = LogManager.getLogger(CheckoutFlowTests.class);

    /* ---------- TC16 ---------- */
	/*
	* Test Case ID: TC16
	* Test Case Name: Login for Checkout Flow
	* Description:
	* Logs into the application to start checkout flow.
	* Expected Result:
	* User should be redirected to Products page.
	*/
    @Test(priority = 16)
    public void TC16_loginForCheckout() {
        log.info("TC16: Login for checkout flow");

        login();

        wait.until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        log.info("✔ TC16 Passed");
    }

    /* ---------- TC17 ---------- */
    /*
    * Test Case ID: TC17
    * Test Case Name: Add Product to Cart
    * Description:
    * Adds a product to the shopping cart.
    * Expected Result:
    * Product should be added successfully.
    */
    @Test(priority = 17)
    public void TC17_addProduct() {
        log.info("TC17: Add product to cart");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack"))).click();

        log.info("✔ TC17 Passed");
    }

    /* ---------- TC18 ---------- */
    /*
    * Test Case ID: TC18
    * Test Case Name: Open Cart Page
    * Description:
    * Opens the shopping cart page.
    * Expected Result:
    * Cart page should be displayed.
    */
    @Test(priority = 18)
    public void TC18_openCart() {
        log.info("TC18: Open cart page");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link"))).click();

        log.info("✔ TC18 Passed");
    }

    /* ---------- TC19 ---------- */
    /*
    * Test Case ID: TC19
    * Test Case Name: Click Checkout Button
    * Description:
    * Clicks on checkout button from cart page.
    * Expected Result:
    * Checkout information page should open.
    */
    @Test(priority = 19)
    public void TC19_clickCheckout() {
        log.info("TC19: Click checkout button");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("checkout"))).click();

        log.info("✔ TC19 Passed");
    }

    /* ---------- TC20 ---------- */
    /*
    * Test Case ID: TC20
    * Test Case Name: Enter Checkout Details
    * Description:
    * Enters user details required for checkout.
    * Expected Result:
    * Details should be entered successfully.
    */
    @Test(priority = 20)
    public void TC20_enterCheckoutDetails() {
        log.info("TC20: Enter checkout details");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("first-name"))).sendKeys("Akash");

        driver.findElement(By.id("last-name")).sendKeys("Gaikar");
        driver.findElement(By.id("postal-code")).sendKeys("400606");

        log.info("✔ TC20 Passed");
    }

    /* ---------- TC21 ---------- */
    /*
    * Test Case ID: TC21
    * Test Case Name: Continue Checkout
    * Description:
    * Clicks Continue button after entering details.
    * Expected Result:
    * User should navigate to checkout overview page.
    */
    @Test(priority = 21)
    public void TC21_continueCheckout() {
        log.info("TC21: Continue checkout");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("continue"))).click();

        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"));

        log.info("✔ TC21 Passed");
    }

    /* ---------- TC22 ---------- */
    /*
    * Test Case ID: TC22
    * Test Case Name: Verify Checkout Overview
    * Description:
    * Verifies checkout overview information is displayed.
    * Expected Result:
    * Summary information should be visible.
    */
    @Test(priority = 22)
    public void TC22_verifyCheckoutOverview() {
        log.info("TC22: Verify checkout overview page");

        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.className("summary_info"))).isDisplayed());

        log.info("✔ TC22 Passed");
    }

    /* ---------- TC23 ---------- */
    /*
    * Test Case ID: TC23
    * Test Case Name: Finish Order
    * Description:
    * Completes the checkout by clicking Finish.
    * Expected Result:
    * Order should be placed successfully.
    */
    @Test(priority = 23)
    public void TC23_finishOrder() {
        log.info("TC23: Finish the order");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("finish"))).click();

        log.info("✔ TC23 Passed");
    }

    /* ---------- TC24 ---------- */
    /*
    * Test Case ID: TC24
    * Test Case Name: Verify Order Confirmation
    * Description:
    * Verifies order confirmation message.
    * Expected Result:
    * Confirmation page should be displayed.
    */
    @Test(priority = 24)
    public void TC24_verifyOrderConfirmation() {
        log.info("TC24: Verify order confirmation");

        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.className("complete-header"))).isDisplayed());

        log.info("✔ TC24 Passed");
    }

    /* ---------- TC25 ---------- */

/*
* Test Case ID: TC25
* Test Case Name: Back to Products Page
* Description:
* Navigates back to products page after order.
* Expected Result:
* Products page should be displayed.
*/
    @Test(priority = 25)
    public void TC25_backToProducts() {
        log.info("TC25: Back to products page");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("back-to-products"))).click();

        log.info("✔ TC25 Passed");
    }

    /* ---------- TC26 ---------- */
    /*
    * Test Case ID: TC26
    * Test Case Name: Verify Cart Empty After Order
    * Description:
    * Checks cart is empty after successful checkout.
    * Expected Result:
    * Cart should contain no items.
    */
    @Test(priority = 26)
    public void TC26_verifyCartEmptyAfterOrder() {
        log.info("TC26: Verify cart is empty");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link"))).click();

        Assert.assertTrue(driver.findElements(By.className("cart_item")).isEmpty());

        log.info("✔ TC26 Passed");
    }

    /* ---------- TC27 ---------- */

/*
* Test Case ID: TC27
* Test Case Name: Continue Shopping After Order
* Description:
* Clicks Continue Shopping button from cart.
* Expected Result:
* User should return to products page.
*/
    @Test(priority = 27)
    public void TC27_continueShopping() {
        log.info("TC27: Continue shopping");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("continue-shopping"))).click();

        log.info("✔ TC27 Passed");
    }

    /* ---------- TC28 ---------- */
    /*
    * Test Case ID: TC28
    * Test Case Name: Verify Products Page Again
    * Description:
    * Verifies user is on products page again.
    * Expected Result:
    * URL should contain 'inventory'.
    */
    @Test(priority = 28)
    public void TC28_verifyProductsPageAgain() {
        log.info("TC28: Verify products page again");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        log.info("✔ TC28 Passed");
    }

    /* ---------- TC29 ---------- */
    /*
    * Test Case ID: TC29
    * Test Case Name: Logout After Checkout
    * Description:
    * Logs out from the application.
    * Expected Result:
    * User should be logged out successfully.
    */
    @Test(priority = 29)
    public void TC29_logout() {
        log.info("TC29: Logout after checkout");

        logout();

        log.info("✔ TC29 Passed");
    }

    /* ---------- TC30 ---------- */
    /*
    * Test Case ID: TC30
    * Test Case Name: Verify Login Page After Logout
    * Description:
    * Verifies login page is displayed after logout.
    * Expected Result:
    * User should be redirected to login page.
    */
    @Test(priority = 30)
    public void TC30_verifyLoginPageAfterLogout() {
        log.info("TC30: Verify login page after logout");

        wait.until(ExpectedConditions.urlContains("saucedemo"));
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"));

        log.info("✔ TC30 Passed");
    }
}
