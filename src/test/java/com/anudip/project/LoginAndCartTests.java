package com.anudip.project;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* AllTestsPart1
* =============
* This test class covers end-to-end flow of:
* - Login validation
* - Product listing verification
* - Add to cart functionality
* - Cart page validations
* - Remove item and continue shopping
*
* Website Tested: SauceDemo (Swag Labs)
* Framework: Selenium + TestNG
*/
public class LoginAndCartTests extends BaseClass {
	protected static final Logger log = LogManager.getLogger(LoginAndCartTests.class);
	/*
	* Test Case ID: TC01
	* Test Case Name: Verify Login Page Title
	* Description:
	* This test verifies that the login page title
	* is displayed correctly before login.
	* Expected Result:
	* Page title should be "Swag Labs".
	*/
    @Test(priority = 1)
    public void TC01_verifyLoginPageTitle() {
        log.info("TC01: Verify login page title");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        log.info("✔ TC01 Passed");
    }
    /*
    * Test Case ID: TC02
    * Test Case Name: Login with Valid User
    * Description:
    * Logs in using valid username and password
    * and waits for Products page to load.
    * Expected Result:
    * User should be successfully logged in.
    */
    @Test(priority = 2)
    public void TC02_loginValidUser() {
        log.info("TC02: Login with valid credentials");
        login(); // Reuse BaseClass login
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Products']")));
        log.info("✔ TC02 Passed");
    }
    /*
    * Test Case ID: TC03
    * Test Case Name: Verify Products Page Title
    * Description:
    * Verifies the heading text on Products page.
    * Expected Result:
    * Page heading should be "Products".
    */
    @Test(priority = 3)
    public void TC03_verifyProductsPageTitle() {
        log.info("TC03: Verify Products page title");
        Assert.assertEquals(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.className("title"))).getText(),
                "Products");
        log.info("✔ TC03 Passed");
    }
    /*
    * Test Case ID: TC04
    * Test Case Name: Verify Product List
    * Description:
    * Checks whether product list is displayed
    * on the Products page.
    * Expected Result:
    * At least one product should be visible.
    */
    @Test(priority = 4)
    public void TC04_verifyProductList() {
        log.info("TC04: Verify product list displayed");
        Assert.assertTrue(driver.findElements(By.className("inventory_item")).size() > 0);
        log.info("✔ TC04 Passed");
    }
    /*
    * Test Case ID: TC05
    * Test Case Name: Add First Product
    * Description:
    * Adds the first product to cart and waits
    * for cart badge to update.
    * Expected Result:
    * Cart badge count should be 1.
    */
    @Test(priority = 5)
    public void TC05_addFirstProduct() {
        log.info("TC05: Add first product");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack"))).click();

        // Wait for cart badge to update to 1
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("shopping_cart_badge"), "1"));
        log.info("✔ TC05 Passed");
    }
    /*
    * Test Case ID: TC06
    * Test Case Name: Add Second Product
    * Description:
    * Adds second product to cart.
    * Expected Result:
    * Cart badge count should update to 2.
    */
    @Test(priority = 6)
    public void TC06_addSecondProduct() {
        log.info("TC06: Add second product");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-bike-light"))).click();

        // Wait for cart badge to update to 2
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("shopping_cart_badge"), "2"));
        log.info("✔ TC06 Passed");
    }
    /*
    * Test Case ID: TC07
    * Test Case Name: Verify Cart Badge Count
    * Description:
    * Verifies cart badge count after adding products.
    * Expected Result:
    * Cart badge should display count as 2.
    */
    @Test(priority = 7)
    public void TC07_verifyCartBadgeCount() {
        log.info("TC07: Verify cart badge count");

        // Wait for cart badge and then assert
        String count = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("shopping_cart_badge"))).getText();
        Assert.assertEquals(count, "2");

        log.info("✔ TC07 Passed");
    }
    /*
    * Test Case ID: TC08
    * Test Case Name: Open Cart Page
    * Description:
    * Clicks on cart icon to open cart page.
    * Expected Result:
    * Cart page should open successfully.
    */
    @Test(priority = 8)
    public void TC08_openCartPage() {
        log.info("TC08: Open cart page");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link"))).click();
        log.info("✔ TC08 Passed");
    }
    /*
    * Test Case ID: TC09
    * Test Case Name: Verify Cart Items
    * Description:
    * Verifies number of items present in cart.
    * Expected Result:
    * Exactly 2 items should be displayed.
    */
    @Test(priority = 9)
    public void TC09_verifyCartItems() {
        log.info("TC09: Verify cart items");

        // Wait for both cart items to be present
        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("cart_item"), 2));
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2);

        log.info("✔ TC09 Passed");
    }
    /*
    * Test Case ID: TC10
    * Test Case Name: Remove One Item
    * Description:
    * Removes one product from cart.
    * Expected Result:
    * Cart badge count should reduce to 1.
    */
    @Test(priority = 10)
    public void TC10_removeOneItem() {
        log.info("TC10: Remove one item from cart");

        // Wait until remove button is clickable and click
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("remove-sauce-labs-backpack"))).click();

        // Wait for cart badge to update to 1
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("shopping_cart_badge"), "1"));

        log.info("✔ TC10 Passed");
    }
    /*
    * Test Case ID: TC11
    * Test Case Name: Verify Cart After Removal
    * Description:
    * Verifies cart after removing one item.
    * Expected Result:
    * Only one item should remain in cart.
    */
    @Test(priority = 11)
    public void TC11_verifyCartAfterRemove() {
        log.info("TC11: Verify cart after removal");

        // Wait for remaining cart item
        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("cart_item"), 1));
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 1);

        log.info("✔ TC11 Passed");
    }
    /*
    * Test Case ID: TC12
    * Test Case Name: Continue Shopping
    * Description:
    * Clicks Continue Shopping button.
    * Expected Result:
    * User should navigate back to Products page.
    */
    @Test(priority = 12)
    public void TC12_continueShopping() {
        log.info("TC12: Continue shopping");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("continue-shopping"))).click();
        log.info("✔ TC12 Passed");
    }
    /*
    * Test Case ID: TC13
    * Test Case Name: Verify Products Page URL
    * Description:
    * Verifies user is navigated back to products page.
    * Expected Result:
    * URL should contain 'inventory'.
    */
    @Test(priority = 13)
    public void TC13_verifyBackOnProducts() {
        log.info("TC13: Verify back on products page");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        log.info("✔ TC13 Passed");
    }
    /*
    * Test Case ID: TC14
    * Test Case Name: Add Product Again
    * Description:
    * Adds previously removed product again.
    * Expected Result:
    * Cart badge count should update to 2.
    */
    @Test(priority = 14)
    public void TC14_addProductAgain() {
        log.info("TC14: Add product again");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack"))).click();

        // Wait for cart badge to update to 2
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("shopping_cart_badge"), "2"));

        log.info("✔ TC14 Passed");
    }
    /*
    * Test Case ID: TC15
    * Test Case Name: Open Cart Again
    * Description:
    * Opens cart page again after adding product.
    * Expected Result:
    * Cart page should open successfully.
    */
    @Test(priority = 15)
    public void TC15_openCartAgain() {
        log.info("TC15: Open cart again");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link"))).click();
        log.info("✔ TC15 Passed");
    }
}
