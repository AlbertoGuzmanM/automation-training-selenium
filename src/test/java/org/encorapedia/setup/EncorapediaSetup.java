package org.encorapedia.setup;

import org.encorapedia.pages.EncorapediaHomePage;
import org.encorapedia.pages.EncorapediaReportsPage;
import org.encorapedia.pages.EncorapediaResultsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EncorapediaSetup {

    WebDriver driver;
    EncorapediaHomePage objEncorapediaHomePage;
    EncorapediaReportsPage objEncorapediaReportsPage;
    EncorapediaResultsPage objEncorapediaResultsPage;
    private static final String CHROME_DRIVER_PATH = "src/test/resources/chromedriver";

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/choice-selenium-testing/index.html");

    }

    public void navigateToReports() {
        objEncorapediaHomePage = new EncorapediaHomePage(driver);
        objEncorapediaHomePage.clickOnReports();
    }

    @Test
    public void enterSearchCriteria() {
        navigateToReports();
        objEncorapediaReportsPage = new EncorapediaReportsPage(driver);
        objEncorapediaResultsPage = new EncorapediaResultsPage(driver);

        // from date: today + 2 days
        LocalDate fromLocalDate =  LocalDate.now().plusDays(2);
        Date fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // to date: fromDate + 5 days
        LocalDate toLocalDate =  fromLocalDate.plusDays(5);
        Date toDate = Date.from(toLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        objEncorapediaReportsPage.setFromDate(fromDate);
        objEncorapediaReportsPage.setToDate(toDate);
        objEncorapediaReportsPage.selectReport("Prices report");
        objEncorapediaReportsPage.showTypeReport("full");
        objEncorapediaReportsPage.clickViewReport();

        objEncorapediaResultsPage.selectSortPricesBy("Price descending");
        List<Integer> pricesList = objEncorapediaResultsPage.getPrices();
        Assert.assertTrue(isDescendingSorted(pricesList));

        int totalPrice = objEncorapediaResultsPage.getTotalPrice();
        Assert.assertEquals(totalPrice, getTotalFromPriceList(pricesList));
    }

    int getTotalFromPriceList (List<Integer> list) {
        int total = 0;
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                total = total + list.get(i);
            }
        }
        return total;
    }
    boolean isDescendingSorted(List<Integer> list) {
        if (null != list) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i)  < list.get(i + 1)) {
                    return false;
                }
            }
        }
        return true;
    }
    @After
    public void tearDown(){
        driver.quit();
    }


}
