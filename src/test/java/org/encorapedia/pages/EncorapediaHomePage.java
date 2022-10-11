package org.encorapedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EncorapediaHomePage {

    WebDriver driver;
    By reportsLink = By.linkText("Reports");

    public EncorapediaHomePage(WebDriver driver) {
        this.driver=driver;
    }

    public void clickOnReports() {
        driver.findElement(reportsLink).click();
    }
}
