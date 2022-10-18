package org.browser;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {
    private static WebDriver driver;

    public static WebDriver getBrowser(String browser) throws BrowserException {
        browser = browser.trim();
        switch (browser) {
            case "chrome":
                driver = new Chrome().createInstance();
                break;
            case "firefox":
                driver = new Firefox().createInstance();
                break;
            case "safari":
                driver = new Safari().createInstance();
                break;
            case "edge":
                driver = new Edge().createInstance();
                break;
            default:
                throw new BrowserException(browser + "is not supported");
        }
        return driver;
    }
}
