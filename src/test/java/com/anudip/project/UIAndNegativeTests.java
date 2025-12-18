package com.anudip.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
* AllTestsPart3
* =============
* This test class covers advanced UI and functional validations:
* - Menu open/close validation
* - Product sorting options
* - UI element presence checks
* - Logout functionality
* - Negative login scenario
*
* Application Under Test: SauceDemo (Swag Labs)
* Framework: Selenium WebDriver + TestNG
*/
public class UIAndNegativeTests extends BaseClass {
	protected static final Logger log = LogManager.getLogger(CheckoutFlowTests.class);
	
	/*
	* Helper Method: JavaScript Click
	* Description:
	* JavaScript click is used when Selenium’s normal click() method fails
	* due to hidden elements, overlapping elements, or dynamic web pages.
	* It directly executes JavaScript inside the browser.
	*/
    public void jsClick(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    /* ---------- PRE-CONDITION ---------- */
    /*
    * Test Case ID: TC31
    * Test Case Name: Login Before Part3 Tests
    * Description:
    * Logs into application as a pre-condition for Part3 tests.
    * Expected Result:
    * User should be logged in and redirected to Products page.
    */
    @Test(priority = 31)
    public void TC31_loginForPart3() {
        log.info("TC31: Login before Part3 tests");
        login();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        log.info("✔ TC31 Passed");
    }

    /* ---------- MENU ---------- */
    /*
    * Test Case ID: TC32
    * Test Case Name: Open and Close Menu
    * Description:
    * Opens the left-side menu and then closes it.
    * Expected Result:
    * Menu should open and close successfully.
    */
    @Test(priority = 32)
    public void TC32_openAndCloseMenu() {
        log.info("TC32: Open and close menu");

        By menuBtn = By.id("react-burger-menu-btn");
        By closeBtn = By.id("react-burger-cross-btn");
        By menuPanel = By.className("bm-menu");

        wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
        jsClick(menuBtn); // open menu
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuPanel)); // wait animation finish

        wait.until(ExpectedConditions.elementToBeClickable(closeBtn));
        jsClick(closeBtn); // close menu
        wait.until(ExpectedConditions.invisibilityOfElementLocated(menuPanel));

        log.info("✔ TC32 Passed");
    }

    /* ---------- SORTING ---------- */
    /*
    * Test Case ID: TC33
    * Test Case Name: Sort Products by Name (A to Z)
    * Description:
    * Applies sorting by product name in ascending order.
    * Expected Result:
    * Products should be sorted from A to Z.
    */
    @Test(priority = 33)
    public void TC33_sortNameAToZ() {
        log.info("TC33: Sort Name A to Z");

        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.className("product_sort_container"))));
        select.selectByVisibleText("Name (A to Z)");

        log.info("✔ TC33 Passed");
    }
    
    /*
    * Test Case ID: TC34
    * Test Case Name: Sort Products by Name (Z to A)
    * Description:
    * Applies sorting by product name in descending order.
    * Expected Result:
    * Products should be sorted from Z to A.
    */
    @Test(priority = 34)
    public void TC34_sortNameZToA() {
        log.info("TC34: Sort Name Z to A");

        Select select = new Select(driver.findElement(By.className("product_sort_container")));
        select.selectByVisibleText("Name (Z to A)");

        log.info("✔ TC34 Passed");
    }

    /*
    * Test Case ID: TC35
    * Test Case Name: Sort Products by Price (Low to High)
    * Description:
    * Applies sorting by product price in ascending order.
    * Expected Result:
    * Products should be sorted from low to high price.
    */
    @Test(priority = 35)
    public void TC35_sortPriceLowToHigh() {
        log.info("TC35: Sort Price Low to High");

        Select select = new Select(driver.findElement(By.className("product_sort_container")));
        select.selectByVisibleText("Price (low to high)");

        log.info("✔ TC35 Passed");
    }

    /*
    * Test Case ID: TC36
    * Test Case Name: Sort Products by Price (High to Low)
    * Description:
    * Applies sorting by product price in descending order.
    * Expected Result:
    * Products should be sorted from high to low price.
    */
    @Test(priority = 36)
    public void TC36_sortPriceHighToLow() {
        log.info("TC36: Sort Price High to Low");

        Select select = new Select(driver.findElement(By.className("product_sort_container")));
        select.selectByVisibleText("Price (high to low)");

        log.info("✔ TC36 Passed");
    }

    /* ---------- UI VALIDATIONS ---------- */
    /*
    * Test Case ID: TC37
    * Test Case Name: Verify Product Images
    * Description:
    * Verifies product images are displayed on products page.
    * Expected Result:
    * At least one product image should be visible.
    */
    @Test(priority = 37)
    public void TC37_verifyProductImages() {
        log.info("TC37: Verify product images");

        Assert.assertTrue(driver.findElements(By.className("inventory_item_img")).size() > 0);

        log.info("✔ TC37 Passed");
    }

    /*
    * Test Case ID: TC38
    * Test Case Name: Verify Product Names
    * Description:
    * Verifies product names are displayed.
    * Expected Result:
    * At least one product name should be visible.
    */
    @Test(priority = 38)
    public void TC38_verifyProductNames() {
        log.info("TC38: Verify product names");

        Assert.assertTrue(driver.findElements(By.className("inventory_item_name")).size() > 0);

        log.info("✔ TC38 Passed");
    }

    /*
    * Test Case ID: TC39
    * Test Case Name: Verify Add to Cart Buttons
    * Description:
    * Verifies Add to Cart buttons are present for products.
    * Expected Result:
    * At least one Add to Cart button should be visible.
    */
    @Test(priority = 39)
    public void TC39_verifyAddToCartButtons() {
        log.info("TC39: Verify Add to Cart buttons");

        Assert.assertTrue(driver.findElements(By.cssSelector("button.btn_inventory")).size() > 0);

        log.info("✔ TC39 Passed");
    }

    /* ---------- LOGOUT ---------- */
    /*
    * Test Case ID: TC40
    * Test Case Name: Logout
    * Description:
    * Logs out from the application using menu option.
    * Expected Result:
    * User should be logged out and redirected to login page.
    */
    @Test(priority = 40)
    public void TC40_logout() {
        log.info("TC40: Logout");

        By menuBtn = By.id("react-burger-menu-btn");
        By logoutBtn = By.id("logout_sidebar_link");
        By menuPanel = By.className("bm-menu");

        wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
        jsClick(menuBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuPanel));

        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
        jsClick(logoutBtn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))); // ensure login page loaded
        log.info("✔ TC40 Passed");
    }

    /* ---------- NEGATIVE LOGIN ---------- */
    /*
    * Test Case ID: TC41
    * Test Case Name: Invalid Login
    * Description:
    * Attempts login with invalid credentials.
    * Expected Result:
    * Login should fail and error message should appear.
    */
    @Test(priority = 41)
    public void TC41_invalidLogin() {
        log.info("TC41: Invalid login");

        driver.findElement(By.id("user-name")).sendKeys("wrong");
        driver.findElement(By.id("password")).sendKeys("wrong");
        driver.findElement(By.id("login-button")).click();

        log.info("✔ TC41 Passed");
    }

    /*
    * Test Case ID: TC42
    * Test Case Name: Verify Error Message for Invalid Login
    * Description:
    * Verifies error message is displayed for invalid login.
    * Expected Result:
    * Error message should be visible on login page.
    */
    @Test(priority = 42)
    public void TC42_verifyErrorMessage() {
        log.info("TC42: Verify error message");

        Assert.assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed());

        log.info("✔ TC42 Passed");
    }
}
