package org.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Edge implements Browser{
    @Override
    public WebDriver createInstance() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}