package com.anudip.project;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * BaseClass
 * ----------
 * Browser setup, common login, logout, teardown, and popup handling
 */
public class BaseClass {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.out.println("========== TEST EXECUTION STARTED ==========");

        WebDriverManager.chromedriver().setup();

        // Chrome preferences to disable built-in popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        System.out.println("🚀 Browser launched & URL opened");
    }

    /* ---------- Common Login ---------- */
    public void login() {
        System.out.println("➡ Performing Login");

        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();
        System.out.println("✔ Login submitted");

        handleLoginPopupIfPresent(); // Handle popup immediately after login
    }

    /* ---------- Handle login popup if it appears ---------- */
    public void handleLoginPopupIfPresent() {
        try {
            // Replace with your actual popup locator
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("password-change-popup"))); // example ID
            if (popup.isDisplayed()) {
                System.out.println("⚠ Popup detected, closing it");
                popup.findElement(By.className("close-button")).click(); // adjust locator
                wait.until(ExpectedConditions.invisibilityOf(popup));
            }
        } catch (Exception e) {
            System.out.println("✅ No popup detected, continuing");
        }
    }

    /* ---------- Logout ---------- */
    public void logout() {
        System.out.println("➡ Performing Logout");

        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        System.out.println("✔ Logout successful");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("🛑 Browser closed");
        System.out.println("========== TEST EXECUTION ENDED ==========");
    }
}
