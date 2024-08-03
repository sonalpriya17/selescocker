package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebTest {
    private static final Logger log = LoggerFactory.getLogger(WebTest.class);
    private WebDriver driver;
    private WebDriverManagerUtil webDriverManagerUtil;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        log.info("Setting up WebDriver");
        webDriverManagerUtil = new WebDriverManagerUtil();
        driver = webDriverManagerUtil.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        log.info("WebDriver set up complete");
    }

    @Test
    public void testWebPageTitle() {
        log.info("Starting testRoposoTitle");
        driver.get("https://leetcode.com/");
        log.info("Navigated to Leetcode website");
        
        // Wait for 5 seconds
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.error("Sleep interrupted", e);
            Thread.currentThread().interrupt();
        }
        String title = driver.getTitle();
        System.out.println("The page title is: " + title);
        log.info("The page title is: " + title);
        Reporter.log("The page title is: " + title, true);

        Assert.assertTrue(title.contains("Just a moment"), "Title should contain 'Just a moment...'");
        log.info("testWebPageTitle completed successfully");
    }

    @AfterMethod
    public void tearDown() {
        log.info("Tearing down WebDriver");
        webDriverManagerUtil.quitAllDrivers();
        log.info("Teardown complete");
    }
}