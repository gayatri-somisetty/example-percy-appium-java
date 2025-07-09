package io.percy.examplepercyappiumjava;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import io.percy.appium.AppPercy;

public class iospoc {
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
        capabilities.setCapability("app", "bs://8549e23587eb5fdd980133e9c9cf35f99fcc93c6");
        capabilities.setCapability("device", "iPhone 15 Pro");
        capabilities.setCapability("os_version", "17.1");
        capabilities.setCapability("project", "First Java Project");

        // Create sessioin
        IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL(HUB_URL), capabilities);

        // Initialize AppPercy
        percy = new AppPercy(driver);

        // Take First Screenshot
        percy.screenshot("First Screenshot");

        // Find element and click to change screen
        try{IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.xpath("//XCUIElementTypeTextField[@value=\"username”]")));
        textButton.sendKeys("iospocusername");
        percy.screenshot("First Screenshot");
        }
        catch (Exception e) {
            System.out.println("Something went wrong.");
          }
        // Find textInput and send some data to it
       IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(MobileBy.xpath("//XCUIElementTypeButton[@name=\"Click for Surprise\"]")));
        textInput.click();

        // Take Second Screenshot Post screen update
        percy.screenshot("Second Screenshot");
        driver.quit();
    }
}
