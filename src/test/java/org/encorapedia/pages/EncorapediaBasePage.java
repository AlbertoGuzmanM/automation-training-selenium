package org.encorapedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EncorapediaBasePage {

    WebDriver driver;

    private By homeLink = By.linkText("Home");
    private By inventoryLink = By.linkText("Inventory");
    private By reportsLink = By.linkText("Reports");

    public EncorapediaBasePage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnReports() {
        driver.findElement(reportsLink).click();
    }
    public By getHomeLink() {
        return homeLink;
    }
    public By getInventoryLink() {
        return inventoryLink;
    }
    public By getReportsLink() {
        return reportsLink;
    }
}
