package org.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class WebDriverManagerUtil {
    private List<WebDriver> drivers = new ArrayList<>();

    public WebDriver createDriver() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);

        // Add arguments for running in Docker/CI environment
        options.addArguments("--headless=new");  // Updated headless argument
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // Additional options for stability in containerized environments
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        drivers.add(driver);
        return driver;
    }

    public void quitAllDrivers() {
        for (WebDriver driver : drivers) {
            try {
                if (driver != null) {
                    driver.quit();
                }
            } catch (Exception e) {
                System.out.println("Error quitting driver: " + e.getMessage());
            }
        }
        drivers.clear();
    }
}