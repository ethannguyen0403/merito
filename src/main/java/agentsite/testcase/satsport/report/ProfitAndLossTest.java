package agentsite.testcase.satsport.report;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.report.ProfitAndLossPage;
import agentsite.pages.all.report.components.TransactionDetailsPopup;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.PROFIT_AND_LOSS;
import static agentsite.common.AGConstant.HomePage.REPORT;

public class ProfitAndLossTest extends BaseCaseMerito {

    /**
     * @title: Validate can filter data on summary display correctly with total in detail
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Profit And Loss
     *          2.Filter date range that have settled bet and select Exchange
     *          3. Get summary data then Drill down to Member level and click on the user name
     * @expect:  1. Verify data display correctly on summary and transaction detail at Member result column
     */
    @Test (groups = {"smoke"})
    public void Agent_Report_Profit_And_Loss_004(){
        log("@title: Validate can filter data on summary display correctly with total in detail");
        log("Step 1. Navigate Report > Profit And Loss");
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Step 2.Filter date range that have settled bet and select Exchange");
        page.dpFrom.previousMonthWithDate(-1, "27");
        page.filterbyProduct("Exchange");

        log("Step 3. Get summary data then Drill down to Member level and click on the user name");
        List<ArrayList<String>> list = page.drilldownToLevel("Member",true);
        TransactionDetailsPopup popup = new TransactionDetailsPopup();
        ArrayList<String> totalRowData = popup.getTotalRowData();

        log("Verify 1. Verify data display correctly on summary and transaction detail at Member result column");
        Assert.assertEquals(totalRowData.get(5),list.get(1).get(page.colBalance-1),"FAILED! Balance of member in Downine Profit and Loss not match with Total- Member result in Transaction Details");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify data in transaction report display correctly
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Profit And Loss
     *          2.Filter date range that have settled bet and select Exchange
     *          3. Drill down to Member level and click on the user name
     * @expect:  1. Sum member result column and verify it match with total column
     */
    @Test (groups = {"smoke11"})
    public void Agent_Report_Profit_And_Loss_005(){
        log("@title: Validate can filter data on summary display correctly with total in detail");
        log("Step 1. Navigate Report > Profit And Loss");
        ProfitAndLossPage page = agentHomePage.clickSubMenu(REPORT, PROFIT_AND_LOSS, ProfitAndLossPage.class);

        log("Step 2.Filter date range that have settled bet and select Exchange");
        page.dpFrom.currentMonthWithDate("1");
        page.filterbyProduct("Exchange");

        log("Step 3. Get summary data then Drill down to Member level and click on the user name");
        page.drilldownToLevel("Member",true);
        TransactionDetailsPopup popup = new TransactionDetailsPopup();

        log("Verify 1. Sum member result column and verify it match with total column");
        Assert.assertTrue(popup.isColumnDataMatchedWithTotal("Member Result", ProfitAndLossPage.downlineLevelList));

        log("INFO: Executed completely");
    }


}
