package backoffice.testcases.system;

import backoffice.pages.bo.system.BetFairTaxRecrawlPage;
import baseTest.BaseCaseMerito;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class BetFairTaxRecrawlTest extends BaseCaseMerito {

    @TestRails(id = "1640")
    @Test(groups = {"regression"})
    public void BO_System_BetFair_Tax_Recrawl_1640() {
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Tool > BetFair Tax Recrawl");
        BetFairTaxRecrawlPage page = backofficeHomePage.navigateBetFairTaxRecrawl();
        log("Verify page title displays with text Betfair tax re-crawl");
        page.lblTitlePage.getText().equalsIgnoreCase("Betfair tax re-crawl");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1641")
    @Test(groups = {"regression"})
    public void BO_System_BetFair_Tax_Recrawl_1641() {
        String accountBetfair = "TestMrt1";
        String fromDate = String.format(DateUtils.getDate(-1, "yyyy/MM/dd", "GMT +7"));
        String toDate = String.format(DateUtils.getDate(-1, "yyyy/MM/dd", "GMT +7"));
        log("@title: Validate can select filter criteria and submit");
        log("Step 1. Access Tool > BetFair Tax Recrawl");
        BetFairTaxRecrawlPage page = backofficeHomePage.navigateBetFairTaxRecrawl();
        log("Step 2. Click Account Betfair dropdown/ FromDate/ ToDate and select a new value");
        page.search(accountBetfair, fromDate, toDate, true);
        String currentBetfair = page.dpAccountBetfair.getFirstSelectedOption();
        String currentFromDate = page.txtDateRangeFrom.getAttribute("value");
        String currentToDate = page.txtDateRangeTo.getAttribute("value");
        String currentStatus = page.lblStatus.getText();
        log("Verify selected value shows correctly");
        Assert.assertEquals(accountBetfair, currentBetfair, "FAILED! Current option selected is not matched expected: " + accountBetfair + " and current: " + currentBetfair);
        Assert.assertEquals(fromDate, currentFromDate, "FAILED! Current option selected is not matched expected: " + fromDate + " and current: " + currentFromDate);
        Assert.assertEquals(toDate, currentToDate, "FAILED! Current option selected is not matched expected: " + toDate + " and current: " + currentToDate);
        Assert.assertEquals("Tax info has been fetched, please help to check report again", currentStatus,
                "FAILED! Status is not shown correctly");

        log("INFO: Executed completely");
    }

}
