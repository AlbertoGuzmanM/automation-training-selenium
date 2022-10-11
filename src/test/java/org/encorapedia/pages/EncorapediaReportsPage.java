package org.encorapedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EncorapediaReportsPage {

    WebDriver driver;
    By fromDate = By.id("fromDate");
    By toDate = By.id("toDate");
    By selectedReport = By.id("selectedReport");
    By showTypeReport = By.name("reportType");
    By viewReportButton = By.className("btn-primary");

    //    By topMenu = By.xpath("//div[@id='navbarSupportedContent']");
    public EncorapediaReportsPage(WebDriver driver) {
        this.driver=driver;
    }

    public void setFromDate (Date date) {
        WebElement fromDateBox = driver.findElement(fromDate);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String formattedDate = dateFormat.format(date);
        fromDateBox.sendKeys(formattedDate);
        System.out.println(formattedDate + " set to fromDate");
    }

    public void setToDate (Date date) {
        WebElement toDateBox = driver.findElement(toDate);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String formattedDate = dateFormat.format(date);
        toDateBox.sendKeys(formattedDate);
        System.out.println(formattedDate + " set to toDate");
    }

    public void selectReport(String report){
        Select dropdown = new Select(driver.findElement(selectedReport));
        dropdown.selectByVisibleText(report);
    }

    public void showTypeReport(String reportType) {
        List<WebElement> radioList = driver.findElements(showTypeReport);

        for (int i = 0; i < radioList.size(); i++) {
            WebElement radioButton = radioList.get(i);
            String val = radioButton.getAttribute("value");
            if (val.equalsIgnoreCase(reportType))
                radioButton.click();
            break;
        }
    }

    public void clickViewReport(){
        List<WebElement> viewReportButtons = driver.findElements(viewReportButton);
        if (viewReportButtons.size() > 1 ){
            viewReportButtons.get(1).click();
        }
    }

}
