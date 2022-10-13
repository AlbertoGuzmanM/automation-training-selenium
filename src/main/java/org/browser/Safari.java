package org.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.browser.Browser;
import org.openqa.selenium.WebDriver;

public class Safari implements Browser {
    @Override
    public WebDriver createInstance() {
        WebDriver driver= WebDriverManager.safaridriver().create();
        return driver;
    }
}