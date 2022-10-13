package org.encorapedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.util.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EncorapediaReportsPage extends EncorapediaBasePage{
    private By fromDate = By.id("fromDate");
    private By toDate = By.id("toDate");
    private By selectedReport = By.id("selectedReport");
    private By showTypeReport = By.name("reportType");
    private By viewReportButton = By.className("btn-primary");
    private DateUtil dateUtil;

    public EncorapediaReportsPage(WebDriver driver) {
        super(driver);
    }

    public void setFromDate (Date date) {
        WebElement fromDateBox = driver.findElement(fromDate);
        fromDateBox.sendKeys(dateUtil.formatDate(date));
    }

    public void setToDate (Date date) {
        WebElement toDateBox = driver.findElement(toDate);
        toDateBox.sendKeys(dateUtil.formatDate(date));
    }

    /**
     * This method selects the report to display based on the string received.
     * The Select options available:
     * "Prices report"
     * "Intenvory report" [sic]
     * @param report
     */
    public void selectReportByVisibleText(String report){
        Select dropdown = new Select(driver.findElement(selectedReport));
        dropdown.selectByVisibleText(report);
    }

    /**
     * This method selects how the report is going to show.
     * "full"
     * "condensed"
     * @param reportType
     */
    public void showTypeReport(String reportType) {
        Optional<WebElement> radio = driver.findElements(showTypeReport).stream().filter(
                element -> element.getAttribute("value").equalsIgnoreCase(reportType)).findFirst();
        if (radio.isPresent()) {
            radio.get().click();
        }
    }

    public void clickViewReport(){
        List<WebElement> viewReportButtons = driver.findElements(viewReportButton);
        if (viewReportButtons.size() > 1 ){
            viewReportButtons.get(1).click();
        }
    }

}
