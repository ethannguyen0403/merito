package membersite.testcases.all.afterlogin.exchange;

import com.paltech.utils.DateUtils;
import membersite.common.FEMemberConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.MyBetsPage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import baseTest.BaseCaseMerito;
import util.testraildemo.TestRails;

import java.util.List;

import static membersite.common.FEMemberConstants.ProfitAndLossPage.*;

public class ProfitAndLossPageTest extends BaseCaseMerito {
    /**
     * @title: Validate Data Profit display correctly
     * @precondition: 1. Login member site
     * @step:   1. Active My Account> Profit and loss
     *          2. Filter in a date range
     *          3. Click on Load report
     *          4. Click on any sport and check details
     * @expect: 1. Verify Total profit = sum profit of all sports
     *          2. Profit of each sport match with when summary the details
     */
    @TestRails(id="522")
    @Test(groups = {"smoke"})
    public void FE_ProfitAndLossPage_TC001(){
        log("@title: Validate Product display correctly");
        log("Step 1. Active My Account> Profit and loss");
        ProfitAndLossPage page = memberHomePage.apHeaderControl.openProfitLossPage();
        String startDate = DateUtils.getDate(-14,"yyyy-MM-dd","IST");
        String endDate = DateUtils.getDate(0,"yyyy-MM-dd","IST");

        log("Step 2. Filter in a date range");
        log("Step 3. Click on Load report");
        page.filter(startDate,endDate);
        if(page.lblNoRecord.isDisplayed())
        {
            Assert.assertEquals(page.lblNoRecord.getText(),FEMemberConstants.NO_RECORD_FOUND,"FAILED! No record message not display");
            return;
        }
        log("Step 4. Click on any sport and check details");
        log("Verify 1. Verify Total profit = sum profit of all sports");
        log("Verify 2. Profit of each sport match with when summary the details");
        int totalRow = page.tblSport.getNumberOfRows(false,false);
        Assert.assertTrue(page.verifyProfitLostMatchedWithDetails(totalRow));
    }

    /**
     * @title: Validate Table header when clicking on sport and market
     * @precondition: 1. Login member site
     * @step:   1. Active My Account> Profit and loss
     *          2. Filter in a date range
     *          3. Click on Load report
     *          4. Click on a sport
     *          5. Click on a event
     * @expect: 1. Table header display correctly when clicking on sport> event
     *
     */
    @TestRails(id="523")
    @Test(groups = {"smoke"})
    public void FE_ProfitAndLossPage_TC002(){
        log("@title: Validate Table header when clicking on sport and market");
        String startDate = DateUtils.getDate(-14,"yyyy-MM-dd","IST");
        String endDate = DateUtils.getDate(0,"yyyy-MM-dd","IST");

        log("Step 1. Active My Account> Profit and loss");
        ProfitAndLossPage page = memberHomePage.apHeaderControl.openProfitLossPage();

        log("Step 2. Filter in a date range");
        log("Step 3. Click on Load report");
        page.filter(startDate,endDate);
        List<String> tblHeaders = page.tblSport.getColumnNamesOfTable(1);

        log("Verify 1. Sport table header display correctly");
        Assert.assertEquals(tblHeaders,TABLE_SUMMARY_HEADER,"ERROR! Sport header table not match as expected");

        if(page.lblNoRecord.isDisplayed())
        {
            Assert.assertEquals(page.lblNoRecord.getText(),FEMemberConstants.NO_RECORD_FOUND,"FAILED! No record message not display");
            return;
        }

        log("Step 4. Click on a sport");
        page.tblSport.getControlOfCell(1,page.colSportGame,1,"span[@class='hover hyperlink']").click();
        tblHeaders = page.tblMarket.getColumnNamesOfTable();

        log("Verify 2. Market table header display correctly");
        Assert.assertEquals(tblHeaders,TABLE_MARKET_HEADER,"ERROR! Market header table not match as expected");

        log("Step 5. Click on an event ");
        page.tblMarket.getControlOfCell(1,page.colMarketName,1,"span[@class='hover hyperlink']").click();
        tblHeaders = page.tblWager.getColumnNamesOfTable();

        log("Verify 3. Wager table header display correctly");
        Assert.assertEquals(tblHeaders,TABLE_WAGER_HEADER,"ERROR! Wager header table not match as expected");
    }

    /**
     * @title: Validate no console error when navigate to Profit and Loss page
     * @precondition: 1. Login member site
     * @step: 1. Click My Account > Profit & Loss
     * @expect: 1. There is no console error display
     */
    @Test(groups = {"http_request"})
    public void FE_ProfitAndLossPage_TC005(){
        log("@title: Validate no console error when navigate to Profit and Loss page");
        log("Step 1. Click My Account > Profit & Loss");
        MyBetsPage page =memberHomePage.satHeaderControl.openMyBets();
        log("Verify 1. There is no console error display");
        boolean isError = hasHTTPRespondedOK();
        Assert.assertTrue(isError, "ERROR: There are some response request error when navigating to Account Statement page");
        log("INFO: Executed Completely!");
    }
}
