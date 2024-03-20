package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.FileUtils;
import common.MemberConstants;
import membersite.pages.MyBetsPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.io.IOException;
import java.util.List;

import static common.MemberConstants.MyBetsPage.*;

public class MyBetPageTest extends BaseCaseTest {
    @TestRails(id = "515")
    @Test(groups = {"regression"})
    @Parameters("timeZone")
    public void MyBetPage_TC515(String timeZone) {
        log("@title: Validate control display correctly when select Exchange Product");
        log("Step 1. Active My Account> My Bets");
        log("Step 2. Selection product dropdown box");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("SETTLED"));

        log("Verify 1. The Product label display accordingly");
        log("Verify 2. Controls display correctly");
        Assert.assertEquals(page.getNote(), String.format(NOTES, timeZone), String.format("ERROR! Current Note label shows %s", page.getNote()));
        Assert.assertEquals(page.getProduct(), DDB_PRODUCT_FILTER.get("Exchange"), String.format("ERROR! Expected product lable is %s but display %s", DDB_PRODUCT_FILTER.get("Exchange"), page.getProduct()));
        Assert.assertEquals(page.getOrderType(), ORDER_TYPE, String.format("ERROR! Expected Start Date but found %s", page.getOrderType()));
        Assert.assertEquals(page.getStartDate(), START_DATE, String.format("ERROR! Expected Start Date but found %s", page.getStartDate()));
        Assert.assertEquals(page.getEndDate(), END_DATE, String.format("ERROR! Expected End Date but found %s", page.getEndDate()));
        Assert.assertEquals(page.getLoadReport(), LOAD_REPORT, String.format("ERROR! Expected Load Report but found %s", page.getLoadReport()));

        List<String> tblHeaders = page.getTableHeaders();
        Assert.assertEquals(tblHeaders.size(), TABLE_HEADER.size(), String.format("ERROR: The expected no of columns is %s but found %s", TABLE_HEADER.size(), tblHeaders.size()));
        Assert.assertEquals(tblHeaders, TABLE_HEADER, "ERROR! Header list not display as expected");
        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Settled bet
     * @precondition: 1. Login member site
     * @step:c 1. Active My Account> My Bets
     * 2. Select Exchange Bets , Order Type: Settled , Start and End Date, click Load Report
     * @expect: 1. All settled bet is filtered
     */
    @TestRails(id = "516")
    @Test(groups = {"smoke"})
    public void MyBetPage_TC516() {
        log("@title: Validate can filter Settled bet");
        log("Step 1. Active My Account> My Bets");
        String startDate = DateUtils.getDate(-30, "yyyy-MM-dd", MemberConstants.TIMEZONE);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", MemberConstants.TIMEZONE);
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);
        log("Step 2. Select Exchange Bets , Order Type: Settled , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("SETTLED"), startDate, endDate);

        if (page.getReportIndex(1, false).get(0).get(0).equals(MemberConstants.NO_RECORD_FOUND)) {
            Assert.assertTrue(true, "By Passed as there is no data when filter Settled wagers");
            return;
        }

        log("Verify 1. Only settled orders displayed otherwise no data display");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("SETTLED")), "ERROR! Settled status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Matched bet
     * @precondition: 1. Login member site
     * @step:c 1. Active My Account> My Bets
     * 2. Select Exchange Bets , Order Type: Matched , Start and End Date, click Load Report
     * @expect: 1. All matched bet is filtered
     */
    @TestRails(id = "517")
    @Test(groups = {"smoke"})
    public void MyBetPage_TC517() {
        log("@title: Validate can filter Matched bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);
        log("Step 2. Select Exchange Bets , Order Type: Matched , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("MATCHED"));

        if (page.getReportIndex(1, false).get(0).get(0).equals(MemberConstants.NO_RECORD_FOUND)) {
            Assert.assertTrue(true, "By Passed as there is no data when filter Settled wagers");
            return;
        }
        log("Verify 1. All matched bet is filtered");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("MATCHED")), "ERROR! Matched status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Unmatched bet
     * @precondition: 1. Login member site
     * @step:c 1. Active My Account> My Bets
     * 2. Select Exchange Bets , Order Type: Unmatched , Start and End Date, click Load Report
     * @expect: 1. All Unmatched bet is filtered
     */
    @TestRails(id = "518")
    @Test(groups = {"smoke"})
    public void MyBetPage_TC518() {
        log("@title: Validate can filter Settled bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2. Select Exchange Bets , Order Type: Unmatched , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("UNMATCHED"));

        if (page.getReportIndex(1, false).get(0).get(0).equals(MemberConstants.NO_RECORD_FOUND)) {
            Assert.assertTrue(true, "By Passed as there is no data when filter Settled wagers");
            return;
        }

        log("Verify 1. All Unmatched bet is filtered");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("UNMATCHED")), "ERROR! Unmatched status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Canceled bet
     * @precondition: 1. Login member site
     * @step:c 1. Active My Account> My Bets
     * 2. Select Exchange Bets , Order Type: Cancelled , Start and End Date, click Load Report
     * @expect: 1. All Cancelled bet is filtered
     */
    @TestRails(id = "519")
    @Test(groups = {"smoke"})
    public void MyBetPage_TC519() {

        log("@title: Validate can filter Cancelled bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2. Select Exchange Bets , Order Type: Cancelled , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("CANCELLED"));

        if (page.getReportIndex(1, false).get(0).get(0).equals(MemberConstants.NO_RECORD_FOUND)) {
            Assert.assertTrue(true, "By Passed as there is no data when filter Settled wagers");
            return;
        }
        log("Verify 1. Bet info display correctly, all bets display with status: Canceled");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("CANCELLED")), "ERROR! Cancelled status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Lapsed bet
     * @precondition: 1. Login member site
     * @step:c 1. Active My Account> My Bets
     * 2. Select Exchange Bets , Order Type: Lapsed , Start and End Date, click Load Report
     * @expect: 1. Bet info display correctly, all bets display with status: Lapsed
     */
    @TestRails(id = "520")
    @Test(groups = {"smoke"})
    public void MyBetPage_TC520() {

        log("@title: Validate can filter Lapsed bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2.Select Exchange Bets , Order Type: Lapsed , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("LAPSED"));

        if (page.getReportIndex(1, false).get(0).get(0).equals(MemberConstants.NO_RECORD_FOUND)) {
            Assert.assertTrue(true, "By Passed as there is no data when filter Settled wagers");
            return;
        }
        log("Verify 1. Bet info display correctly, all bets display with status: Lapsed");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("LAPSED")), "ERROR! Lapsed status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate can filter Voided bet
     * @precondition: 1. Login member site
     * @step:c 1. Active My Account> My Bets
     * 2. Select Exchange Bets , Order Type: Voided , Start and End Date, click Load Report
     * @expect: 1. Bet info display correctly, all bets display with status: Voided
     */
    @TestRails(id = "521")
    @Test(groups = {"smoke"})
    public void MyBetPage_TC521() {
        log("@title: Validate can filter Voided bet");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2.Select Exchange Bets , Order Type: Voided , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("VOIDED"));

        if (page.getReportIndex(1, false).get(0).get(0).equals(MemberConstants.NO_RECORD_FOUND)) {
            Assert.assertTrue(true, "By Passed as there is no data when filter Settled wagers");
            return;
        }
        log("Verify 1. Bet info display correctly, all bets display with status: Voided");
        Assert.assertTrue(page.validateFilterStatus(DDB_ORDER_TYPE_FILTER.get("VOIDED")), "ERROR! Voided status not filter correctly.");

        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate no console error when navigate to My Bets
     * @precondition: 1. Login member site
     * @step: 1. Click My Account > My Bets
     * @expect: 1. There is no console error display
     */
    @Test(groups = {"http_request"})
    public void MyBetPage_TC009() {
        log("@title: Validate no console error when navigate to My Bets Page");
        log("Step 1. 1. Click My Account > My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);
        log("Verify 1. There is no console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when navigating to My Bets page");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1104")
    @Test(groups = {"regression"})
    public void MyBetPage_TC1104() {
        log("@title: Validate can export report");
        log("Step 1. Active My Account> My Bets");
        String dowloadPath = DriverManager.getDriver().getDriverSetting().getDownloadPath() + "My_Bets_EXCHANGE.csv";
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2.Select Exchange Bets , Order Type: Voided , Start and End Date, click Load Report");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange"), DDB_ORDER_TYPE_FILTER.get("VOIDED"));

        log("Step 3.Click on export icon and save in the local");
        page.clickDownload();

        log("@Verify: User can export file successfully with exported file name: Bet_Settlement");
        Assert.assertTrue(FileUtils.doesFileNameExist(dowloadPath), "Failed to download Expected document");

        log("@Post-condition: delete download file");
        try {
            FileUtils.removeFile(dowloadPath);
        } catch (IOException e) {
            log(e.getMessage());
        }
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1105")
    @Test(groups = {"regression"})
    public void MyBetPage_TC1105() {
        log("@title: Validate control display correctly when select Exchange Games");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2.Selection Exchange Game Bets");
        page.filter(DDB_PRODUCT_FILTER.get("Exchange Games"), DDB_ORDER_TYPE_FILTER.get("SETTLED"));

        log("@Verify1: Label : Exchange Game Bests  display");
        Assert.assertEquals(page.myBetsContainer.getProduct(), DDB_PRODUCT_FILTER.get("Exchange Games"), "Failed! Product title is incorrect");
        List<String> tblHeaders = page.getTableHeaders();
        Assert.assertEquals(tblHeaders.size(), TABLE_HEADER_EG.size(), String.format("ERROR: The expected no of columns is %s but found %s", TABLE_HEADER.size(), tblHeaders.size()));
        Assert.assertEquals(tblHeaders, TABLE_HEADER_EG, "ERROR! Header list not display as expected");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1106")
    @Test(groups = {"regression"})
    public void MyBetPage_TC1106() {
        log("@title: Validate control display correctly when select Lottery and Slot");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2.Select Lottery & Slots Bets");
        page.filter(DDB_PRODUCT_FILTER.get("Lottery & Slots"), DDB_ORDER_TYPE_FILTER.get("SETTLED"));

        log("@Verify1: Label : Lottery & Slots Bets display");
        Assert.assertEquals(page.getProduct(), DDB_PRODUCT_FILTER.get("Lottery & Slots"), "Failed! Product title is incorrect");
        List<String> tblHeaders = page.getTableHeaders();
        Assert.assertEquals(tblHeaders.size(), TABLE_HEADER_CASINO.size(), String.format("ERROR: The expected no of columns is %s but found %s", TABLE_HEADER.size(), tblHeaders.size()));
        Assert.assertEquals(tblHeaders, TABLE_HEADER_CASINO, "ERROR! Header list not display as expected");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1107")
    @Test(groups = {"regression"})
    public void MyBetPage_TC1107() {
        log("@title: Validate control display correctly when select Live Dealer Asian Bets");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2.Select Live Dealer Asian Bets");
        page.filter(DDB_PRODUCT_FILTER.get("Live Dealer Asian"), DDB_ORDER_TYPE_FILTER.get("SETTLED"));

        log("@Verify1: Label : Live Dealer Asian Bets display");
        Assert.assertEquals(page.getProduct(), DDB_PRODUCT_FILTER.get("Live Dealer Asian"), "Failed! Product title is incorrect");
        List<String> tblHeaders = page.getTableHeaders();
        Assert.assertEquals(tblHeaders.size(), TABLE_HEADER_CASINO.size(), String.format("ERROR: The expected no of columns is %s but found %s", TABLE_HEADER.size(), tblHeaders.size()));
        Assert.assertEquals(tblHeaders, TABLE_HEADER_CASINO, "ERROR! Header list not display as expected");
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1108")
    @Test(groups = {"regression"})
    public void MyBetPage_TC1108() {
        log("@title: Validate control display correctly when select Live Dealer European Bets");
        log("Step 1. Active My Account> My Bets");
        MyBetsPage page = memberHomePage.header.openMyBets(_brandname);

        log("Step 2.Select Live Dealer European Bets");
        page.filter(DDB_PRODUCT_FILTER.get("Live Dealer European"), DDB_ORDER_TYPE_FILTER.get("SETTLED"));

        log("@Verify1: Label :Live Dealer European Bets display");
        Assert.assertEquals(page.getProduct(), DDB_PRODUCT_FILTER.get("Live Dealer European"), "Failed! Product title is incorrect");

        log("@Verify2: Label :Live Dealer European Bets display");
        List<String> tblHeaders = page.getTableHeaders();
        Assert.assertEquals(tblHeaders.size(), TABLE_HEADER_CASINO.size(), String.format("ERROR: The expected no of columns is %s but found %s", TABLE_HEADER.size(), tblHeaders.size()));
        Assert.assertEquals(tblHeaders, TABLE_HEADER_CASINO, "ERROR! Header list not display as expected");
        log("INFO: Executed Completely!");
    }
}
