package io.percy.examplepercyappiumjava;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.percy.appium.AppPercy;

public class Android {
    private static AppPercy percy;

    // Hub Url to connect to Automation session
    private static String HUB_URL = "https://hub.browserstack.com/wd/hub";

    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Browserstack specific capabiilities
        capabilities.setCapability("browserstack.user", "mdbpunes1");
        capabilities.setCapability("browserstack.key", "p7wyhyhpXs97oZ9jURkM");
        capabilities.setCapability("browserstack.appium_version", "1.20.2");

        // Percy Options
        capabilities.setCapability("percy.enabled", "true");
        capabilities.setCapability("percy.ignoreErrors", "true");

        // App url we get post uploading in response
        capabilities.setCapability("app", "bs://6c91154b1ad48c661d8e523de34a2d58cddb8777");
        capabilities.setCapability("device", "Google Pixel 3");
        capabilities.setCapability("os_version", "9.0");
        capabilities.setCapability("project", "First Java Project");

        // Create sessioin
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL(HUB_URL), capabilities);

        // Initialize AppPercy
        percy = new AppPercy(driver);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Take First Screenshot
        percy.screenshot("First Screenshot");


        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
        searchElement.click();

        AndroidElement textInput = (AndroidElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        textInput.sendKeys("App percy\n");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Take Second Screenshot post scrolling
        percy.screenshot("Second Screenshot");

        driver.quit();
    }
}
