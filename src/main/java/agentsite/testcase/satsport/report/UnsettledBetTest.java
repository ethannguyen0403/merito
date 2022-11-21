package agentsite.testcase.satsport.report;

import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.report.UnsettledBetPage;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.UNSETTLED_BET;

public class UnsettledBetTest extends BaseCaseMerito {
    /**
     * @title: Validate Last Best Mode - Can filter Matched bet
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Unsettled Bet
     * 2. Select Last Best Mode > Select Matched and click Submit
     * @expect: 1. Match bet display correctly
     */
    @TestRails(id="786")
    @Test (groups = {"smoke"})
    @Parameters("memberAccount")
    public void Agent_Report_Unsettled_Bet_005(String memberAccount){
        log("@title: Validate Last Best Mode - Can filter Matched bet");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.clickSubMenu(REPORT, UNSETTLED_BET, UnsettledBetPage.class);

        log("Step 2. Select Last Best Mode > Select Matched and click Submit");
        page.search("Matched","",memberAccount,"","","");

        log("Verify 1. Match bet display correctly");
        List<ArrayList<String>> data = page.tblLastBetsMode.getRowsWithoutHeader(false);
        if(data.get(0).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND))
        {
            Assert.assertEquals(page.lblLastBetsModeNoRecord.getText(),AGConstant.NO_RECORD_FOUND,"FAILED! No record label is incorrect");
            return;
        }
        page.verifySearchLastBetsMode(data,memberAccount,"Matched","","","","");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Sport Mode - Total bet list is correct
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:  1. Navigate Report > Unsettled Bet
     *          2. Select Sport Mode
     *          3. If have bet click on sport then get total bet
     *          4. Click on event
     * @expect: 1. Verify bet number is matched with total bet
     */
    @TestRails(id="787")
    @Test (groups = {"smoke"})
    public void Agent_Report_Unsettled_Bet_008(){
        log("@title: Validate Sport Mode - Total bet list is correct");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.clickSubMenu(REPORT, UNSETTLED_BET, UnsettledBetPage.class);

        log("Step 2. Select Sport Mode");
        page.selectMode("Sport Mode");

        log("Verify 3. If have bet click on sport then get total bet");
        if(page.lblNoRecordSportMode.isDisplayed())
        {
            Assert.assertEquals(page.lblNoRecordSportMode.getText(),AGConstant.NO_RECORD_FOUND,"FAILED! No record label is incorrect");
            return;
        }
        String totalBetFirstRow = page.lblFirstRowTotalBetNumber.getText();

        log("Verify  4. Click on event");
        List<ArrayList<String>> betListInfo = page.tblSportMode.getRowsWithoutHeader(false);
        Assert.assertEquals(betListInfo.size() -1, Integer.parseInt(totalBetFirstRow),"Failed! Bet list number not display as total bet");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate Hierarchy Mode - Can navigate to bet list
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Unsettled Bet
     *         2. Select Hierarchy Mode
     *         3. Enter Soccer and click submit
     * @expect: 1. Verify data is display
     */
    @TestRails(id="788")
    @Test (groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_Report_Unsettled_Bet_009(String downlineAccount){
        log("@title:Validate Hierarchy Mode - Can navigate to bet list");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page = agentHomePage.clickSubMenu(REPORT, UNSETTLED_BET, UnsettledBetPage.class);

        log("Step 2. Select Hierarchy Mode");
        page.selectMode("Hierarchy Mode");
        List<ArrayList<String>> data = page.tblHierarchyMode.getRowsWithoutHeader(1,false);

        log("Step 3. Enter Soccer and click submit");
        page.search("","",downlineAccount,"","","");

        log("Verify 1. Verify data is display");
        if(data.get(0).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND))
        {
            Assert.assertEquals(data.get(0).get(0),AGConstant.NO_RECORD_FOUND,"FAILED! No record message display incorrect when have no result found"); Assert.assertEquals(page.lblNoRecord.getText(),AGConstant.NO_RECORD_FOUND,"FAILED! No record label is incorrect");
            return;
        }
        Assert.assertEquals(data.get(0).get(page.colLoginID -1),downlineAccount,"Failed! Data not display");

        log("INFO: Executed completely");
    }

}
