package org.encorapedia;

import com.sun.istack.internal.NotNull;
import org.browser.BrowserFactory;
import org.encorapedia.pages.EncorapediaHomePage;
import org.encorapedia.pages.EncorapediaReportsPage;
import org.encorapedia.pages.EncorapediaResultsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EncorapediaReportPricesResultsTest {
    private WebDriver driver;

    public void chrome() throws Exception {
        driver= BrowserFactory.getBrowser("chrome");
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/choice-selenium-testing/index.html");
    }

    @Before
    public void setup() throws Exception {
        chrome();
    }

    public void navigateToReports() {
        EncorapediaHomePage objEncorapediaHomePage = new EncorapediaHomePage(driver);
        objEncorapediaHomePage.clickOnReports();
    }

    @Test
        public void validatingReportPricesResults() {
        navigateToReports();
        EncorapediaReportsPage objEncorapediaReportsPage = new EncorapediaReportsPage(driver);
        EncorapediaResultsPage objEncorapediaResultsPage = new EncorapediaResultsPage(driver);

        // from date: today + 2 days
        LocalDate fromLocalDate =  LocalDate.now().plusDays(2);
        Date fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // to date: fromDate + 5 days
        LocalDate toLocalDate =  fromLocalDate.plusDays(5);
        Date toDate = Date.from(toLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        objEncorapediaReportsPage.setFromDate(fromDate);
        objEncorapediaReportsPage.setToDate(toDate);
        objEncorapediaReportsPage.selectReportByVisibleText("Prices report");
        objEncorapediaReportsPage.showTypeReport("full");
        objEncorapediaReportsPage.clickViewReport();

        objEncorapediaResultsPage.selectSortPricesBy("Price descending");
        List<Integer> pricesList = objEncorapediaResultsPage.getPrices();
        Assert.assertTrue(isDescendingSorted(pricesList));

        int totalPrice = objEncorapediaResultsPage.getTotalPrice();
        Assert.assertEquals(totalPrice, getTotalFromPriceList(pricesList));
    }

    int getTotalFromPriceList(@NotNull List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }

    boolean isDescendingSorted(@NotNull List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    @After
    public void tearDown(){
        driver.quit();
    }


}
