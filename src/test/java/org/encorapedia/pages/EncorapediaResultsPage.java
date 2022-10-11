package org.encorapedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class EncorapediaResultsPage {

    WebDriver driver;
    By sortPricesBy = By.id("sort");
    By tableRows = By.xpath("//*[@id='results']/table/tbody/tr/td[1]");
    By totalPrice = By.xpath("//*[@id=\"price-sum\"]");

    public EncorapediaResultsPage(WebDriver driver) {
        this.driver=driver;
    }

    public void selectSortPricesBy(String sortBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(sortPricesBy));
        Select dropdown = new Select(driver.findElement(sortPricesBy));
        dropdown.selectByVisibleText(sortBy);
    }

    public int getNumberOfRows(){
        List<WebElement> rowList = driver.findElements(tableRows);
        return rowList.size();
    }

    public int getTotalPrice(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(totalPrice)));
        WebElement totalPriceWE = driver.findElement(totalPrice);
        String totalPrice = totalPriceWE.getText().replace("$","");
        return Integer.valueOf(totalPrice);
    }
    public List<Integer> getPrices(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(tableRows)));
        List<Integer> pricesList = new ArrayList<>();
        for (int i = 0; i < getNumberOfRows(); i++) {
            WebElement priceWE = driver.findElement(By.xpath("//*[@id='results']/table/tbody/tr[" + (i+1) + "]/td[4]"));
            String price = priceWE.getText().replace("$","");
            pricesList.add(Integer.valueOf(price));
        }
        return pricesList;
    }
}
