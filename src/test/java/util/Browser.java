package util;

import base.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Browser {
    private static String url;
    private final Logger logger = Logger.getLogger(Browser.class);
    private DesiredCapabilities capabilities;
    private String chrome = "chrome";


    public void createLocalDriver() {
        capabilities = new DesiredCapabilities().chrome();
        capabilities.setPlatform(Platform.getCurrent());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-sandbox");
        options.addArguments("headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-proxy-server");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        capable(options);
        selectPath(capabilities.getPlatform());
        BaseTest.setDriver(new ChromeDriver(options));
    }

    private void capable(ChromeOptions options) {
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability("chrome.switches", Collections.singletonList("--incognito"));
    }


    private void selectPath(Platform platform) {
        String browser;
        if (chrome.equalsIgnoreCase(capabilities.getBrowserName())) {
            browser = "webdriver.chrome.driver";
            switch (platform) {
                case MAC:
                    System.setProperty(browser, "properties/driver/chromedriverMAC");
                    break;
                case WIN10:
                    System.setProperty(browser, "./properties/driver/chromedriver.exe");
                case XP:
                    System.setProperty(browser, "./properties/driver/chromedriver.exe");
                case WIN8:
                case WIN8_1:
                case WINDOWS:
                    System.setProperty(browser, "./properties/driver/chromedriver.exe");
                    break;
                case LINUX:
                    System.setProperty(browser, "/usr/bin/chromedriver");
                    break;
                default:
                    logger.info("PLATFORM DOES NOT EXISTS");
                    break;
            }
        }
    }


}
