package org.encorapedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EncorapediaBasePage {

    protected WebDriver driver;

    private By homeLink = By.linkText("Home");
    private By inventoryLink = By.linkText("Inventory");
    private By reportsLink = By.linkText("Reports");

    public EncorapediaBasePage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickOnReports() {
        driver.findElement(reportsLink).click();
    }
    public WebElement getHomeWE() {
        return  driver.findElement(homeLink);
    }
    public WebElement getInventoryWE() {
        return driver.findElement(inventoryLink);
    }
    public WebElement getReportsWE() {
        return driver.findElement(reportsLink);
    }
}
