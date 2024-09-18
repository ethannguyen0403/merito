package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.FileUtils;
import common.MemberConstants;
import membersite.pages.ProfitAndLossPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.io.IOException;
import java.util.List;

import static common.MemberConstants.ProfitAndLossPage.*;

public class ProfitAndLossPageTest extends BaseCaseTest {

    /**
     * @title: Validate Data Profit display correctly
     * @precondition: 1. Login member site
     * @step: 1. Active My Account> Profit and loss
     * 2. Filter in a date range
     * 3. Click on Load report
     * 4. Click on any sport and check details
     * @expect: 1. Verify Total profit = sum profit of all sports
     * 2. Profit of each sport match with when summary the details
     */
    @TestRails(id = "522")
    @Test(groups = {"smoke", "smoke_dev","MER.Maintenance.2024.V.6.0"})
    @Parameters("timeZone")
    public void ProfitAndLossPage_TC522(String timeZone) {
        log("@title: Validate Data Profit display correctly");
        log("Step 1. Active My Account> Profit and loss");
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);
        String startDate = DateUtils.getDate(-14, "yyyy-MM-dd", timeZone);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", timeZone);

        log("Step 2. Filter in a date range");
        log("Step 3. Click on Load report");
        page.filter(startDate, endDate);
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecord.getText(), MemberConstants.NO_RECORD_FOUND, "FAILED! No record message not display");
            return;
        }
        log("Step 4. Click on any sport and check details");
        log("Verify 1. Verify Total profit = sum profit of all sports");
        log("Verify 2. Profit of each sport match with when summary the details");
        int totalRow = page.tblSport.getNumberOfRows(false, false);
        Assert.assertTrue(page.verifyProfitLostMatchedWithDetails(totalRow));
    }

    /**
     * @title: Validate Table header when clicking on sport and market
     * @precondition: 1. Login member site
     * @step: 1. Active My Account> Profit and loss
     * 2. Filter in a date range
     * 3. Click on Load report
     * 4. Click on a sport
     * 5. Click on a event
     * @expect: 1. Table header display correctly when clicking on sport> event
     */
    @TestRails(id = "523")
    @Test(groups = {"smoke", "smoke_dev"})
    @Parameters("timeZone")
    public void ProfitAndLossPage_TC523(String timeZone) {
        log("@title: Validate Table header when clicking on sport and market");
        String startDate = DateUtils.getDate(-14, "yyyy-MM-dd", timeZone);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", timeZone);

        log("Step 1. Active My Account> Profit and loss");
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);

        log("Step 2. Filter in a date range");
        log("Step 3. Click on Load report");
        page.filter(startDate, endDate);
        List<String> tblHeaders = page.tblSport.getColumnNamesOfTable(1);

        log("Verify 1. Sport table header display correctly");
        Assert.assertEquals(tblHeaders, TABLE_SUMMARY_HEADER, "ERROR! Sport header table not match as expected");

        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.lblNoRecord.getText(), MemberConstants.NO_RECORD_FOUND, "FAILED! No record message not display");
            return;
        }

        log("Step 4. Click on a sport");
        page.clickFirstSport();
        tblHeaders = page.tblMarket.getColumnNamesOfTable();

        log("Verify 2. Market table header display correctly");
        Assert.assertEquals(tblHeaders, TABLE_MARKET_HEADER, "ERROR! Market header table not match as expected");

        log("Step 5. Click on an event ");
        page.clickFirstEvent();
        tblHeaders = page.tblWager.getColumnNamesOfTable();

        log("Verify 3. Wager table header display correctly");
        Assert.assertEquals(tblHeaders, TABLE_WAGER_HEADER, "ERROR! Wager header table not match as expected");
    }

    @TestRails(id = "1109")
    @Test(groups = {"regression"})
    @Parameters("timeZone")
    public void ProfitAndLossPage_TC1109(String timeZone) {
        log("@title: Validate Can export Profit and loss report");
        String startDate = DateUtils.getDate(-14, "yyyy-MM-dd", timeZone);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", timeZone);

        String fileName = String.format("Profit_n_Loss__%s_%s.csv", startDate, endDate);
        String dowloadPath = DriverManager.getDriver().getDriverSetting().getDownloadPath() + fileName;
        log("Step 1. Active My Account> Profit and loss");
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);

        log("Step 2. Filter in a date range");
        log("Step 3. Click on Load report");
        page.filter(startDate, endDate);

        log("Step 4. Click on export icon");
        page.clickDownload();

        log("Verify 1.Can export");
        Assert.assertTrue(FileUtils.doesFileNameExist(dowloadPath), "Failed to download Expected document");

        log("@Post-condition: delete download file");
        try {
            FileUtils.removeFile(dowloadPath);
        } catch (IOException e) {
            log(e.getMessage());
        }

        log("INFO: Executed Completely!");
    }

    @TestRails(id = "1110")
    @Test(groups = {"regression"})
    @Parameters("timeZone")
    public void ProfitAndLossPage_TC1110(String timeZone) {
        log("@title: Validate Back button work Profit and Loss");
        String startDate = DateUtils.getDate(-14, "yyyy-MM-dd", timeZone);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", timeZone);

        log("Step 1. Active My Account> Profit and loss");
        ProfitAndLossPage page = memberHomePage.header.openProfitAndLoss(_brandname);

        log("Step 2. Filter in a date range");
        log("Step 3. Click on Load report");
        page.filter(startDate, endDate);

        log("Step 4. Click on any sport then click back button");
        page.clickFirstSport();
        page.clickBackButton();
        log("Verify 1.Report back to the summary page");
        List<String> tblHeaders = page.tblSport.getColumnNamesOfTable(1);
        Assert.assertEquals(tblHeaders, TABLE_SUMMARY_HEADER, "Failed! Sport table should display after clicking Back button");

        log("INFO: Executed Completely!");
    }
}
