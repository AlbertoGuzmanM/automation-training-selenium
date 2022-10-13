package org.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Chrome implements Browser {
    @Override
    public WebDriver createInstance() {
        WebDriverManager.chromedriver().setup();;
        return new ChromeDriver();
    }
}
