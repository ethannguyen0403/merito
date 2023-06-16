package agentsite.testcase.reports;

import common.AGConstant;
import agentsite.pages.report.UnsettledBetPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.Report.UnsettleBet.*;

public class UnsettledBetTest extends BaseCaseTest {

    @Test (groups = {"http_request"})
    public void Agent_Report_Unsettled_Bet_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report > Unsettled Bet");
       agentHomePage.navigateUnsettledBetPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }
    @Test (groups = {"regression1"})
    public void Agent_Report_Unsettled_Bet_002(){
        log("@title: Validate Unsettled Bet - Last Bet Mode UI display correctly");
        log("Step 1. Navigate Report > Unsettled Bet");
        String today = DateUtils.getDate(0,"dd/MM/yyyy","GMT-4:00");
        UnsettledBetPage page =  agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Last Bets Mode");
        page.selectMode("Last Bets Mode");

        log("Verify1. Verify Last Bet Mode UI display correctly");
        Assert.assertEquals(page.rdModeGroup.getAllLables(),MODE_LIST,"FAILED! Mode radio button list is incorrect ");
        Assert.assertEquals(page.rdBetType.getAllLables(),LIST_BET_TYPE_RADIO_BUTTON,"FAILED! Mode radio button list is incorrect ");
        Assert.assertEquals(page.lblInfo.getText(),HINT_MESSAGE,"FAILED! Note message is incorrect");
        Assert.assertEquals(page.tblLastBetsMode.getColumnNamesOfTable(),LAST_BETS_MODE_TABLE_HEADER,"FAILED! Table header is incorrect");
        Assert.assertEquals(page.txtFrom.getAttribute("value"),today,"FAILED! From date is incorrect");
        Assert.assertEquals(page.txtTo.getAttribute("value"),today,"FAILED! To date is incorrect");
        Assert.assertEquals(page.txtUsername.getAttribute("placeholder"),"Username or Login ID","FAILED! Username placehoder is incorrect");
        Assert.assertEquals(page.lblSatkeAll.getText(),"All", "FAILED! Stake all is incorrect displayed");
        Assert.assertEquals(page.btnSearch.getText(),"Submit", "FAILED! Submit button is incorrect displayed");
        Assert.assertTrue(page.txtWagerID.isDisplayed(),"FAILED! Wager id textbox is not displayed");
        Assert.assertTrue(page.ddbSport.isDisplayed(),"FAILED! Sport dropdown is not displayed");
        Assert.assertTrue(page.ddbEvent.isDisplayed(),"FAILED! Event dropdown is not displayed");
        log("INFO: Executed completely");
    }

    @Test (groups = {"poregression"})
    @Parameters("memberAccount")
    public void Agent_Report_Unsettled_Bet_003(){
        log("@title:Validate Unsettled Bet - Hierarchy Mode UI display correctly");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page =  agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Hierarchy Mode");
        page.selectMode("Hierarchy Mode");

        log("Verify 1. Verify Hierarchy Mode Ui display correctly");
        Assert.assertTrue(page.ddpSportHiearchyMode.isDisplayed(),"FAILED! sport dropdown is not displayed");
        Assert.assertTrue(page.ddpEventHiearchyMode.isDisplayed(),"FAILED! Event dropdown is not displayed");
        Assert.assertEquals(page.btnSearch.getText(),"Submit","FAILED! Submit button is incorrect");
        Assert.assertEquals(page.tblHierarchyMode.getColumnNamesOfTable(),HIERARCHY_MODE_PO_TABLE_HEADER,"FAILED! Table header is incorrect");
        log("INFO: Executed completely");
    }

    @Test (groups = {"poregression"})
    public void Agent_Report_Unsettled_Bet_004(){
        log("@title:Validate Unsettled Bet - Sport Mode UI display correctly");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page =  agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Sport Mode");
        page.selectMode("Sport Mode");

        log("Verify 1. Verify Sport Mode Ui display correctly");
        Assert.assertTrue(Objects.nonNull(page.unsettleBetSportModeContainer.getAllSports()),"FAILED! Sport list not displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Last Best Mode - Can filter Matched bet
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Unsettled Bet
     * 2. Select Last Best Mode > Select Matched and click Submit
     * @expect: 1. Match bet display correctly
     */
    @Test (groups = {"smoke"})
    @Parameters("memberAccount")
    public void Agent_Report_Unsettled_Bet_005(String memberAccount){
        log("@title: Validate Last Best Mode - Can filter Matched bet");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page =  agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Last Best Mode > Select Matched and click Submit");
        page.dpFrom.previousMonthWithDate(-1,"25");
        page.search("Matched","",memberAccount,"","","");

        log("Verify 1. Match bet display correctly");
        List<ArrayList<String>> data = page.tblLastBetsMode.getRowsWithoutHeader(false);
        if(data.get(0).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND))
        {
            Assert.assertEquals(page.lblLastBetsModeNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record label is incorrect");
            return;
        }
        page.verifySearchLastBetsMode(data,memberAccount,"Matched","","","","");

        log("INFO: Executed completely");
    }

    @Test (groups = {"regression"})
    @Parameters("memberAccount")
    public void Agent_Report_Unsettled_Bet_006(String memberAccount){
        log("@title: Validate Last Best Mode - Can filter Unmatched bet");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page =  agentHomePage.navigateUnsettledBetPage();

        log("Step Select Last Best Mode > Select Unmatched and click Submit");
        page.dpFrom.previousMonthWithDate(-1,"25");
        page.search("Un-matched","",memberAccount,"","","");

        log("Verify 1. Unmatch bet display correctly");
        List<ArrayList<String>> data = page.tblLastBetsMode.getRowsWithoutHeader(false);
        if(data.get(0).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND))
        {
            Assert.assertEquals(page.lblLastBetsModeNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record label is incorrect");
            return;
        }
        page.verifySearchLastBetsMode(data,memberAccount,"Matched","","","","");

        log("INFO: Executed completely");
    }

    @Test (groups = {"regression"})
    @Parameters("memberAccount")
    public void Agent_Report_Unsettled_Bet_007(String memberAccount){
        log("@title: Validate Last Best Mode - Can filter Unmatched bet");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page =  agentHomePage.navigateUnsettledBetPage();

        log("Step Select Last Best Mode > Select Unmatched and click Submit");
        page.dpFrom.previousMonthWithDate(-1,"25");
        page.search("Cancelled","",memberAccount,"","","");

        log("Verify 1. Unmatch bet display correctly");
        List<ArrayList<String>> data = page.tblLastBetsMode.getRowsWithoutHeader(false);
        if(data.get(0).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND))
        {
            Assert.assertEquals(page.lblLastBetsModeNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record label is incorrect");
            return;
        }
        page.verifySearchLastBetsMode(data,memberAccount,"Cancelled","","","","");

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
    @Test (groups = {"smoke"})
    public void Agent_Report_Unsettled_Bet_008(){
        log("@title: Validate Sport Mode - Total bet list is correct");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page =  agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Sport Mode");
        page.selectMode("Sport Mode");

        log("Verify 3. If have bet click on sport then get total bet");
        if(page.lblNoRecordSportMode.isDisplayed())
        {
            Assert.assertEquals(page.lblNoRecordSportMode.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record label is incorrect");
            return;
        }
        String sport =page.unsettleBetSportModeContainer.getAllSports().get(0);
        String totalBetFirstRow = page.unsettleBetSportModeContainer.getdataofSport(sport).get(0).get(0);

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
    @Test (groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_Report_Unsettled_Bet_009(String downlineAccount){
        log("@title:Validate Hierarchy Mode - Can navigate to bet list");
        log("Step 1. Navigate Report > Unsettled Bet");
        UnsettledBetPage page =  agentHomePage.navigateUnsettledBetPage();

        log("Step 2. Select Hierarchy Mode");
        page.selectMode("Hierarchy Mode");
        List<ArrayList<String>> data = page.tblHierarchyMode.getRowsWithoutHeader(1,false);

        log("Step 3. Enter Soccer and click submit");
        page.search("Matched","",downlineAccount,"","","");

        log("Verify 1. Verify data is display");
        if(data.get(0).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND))
        {
            Assert.assertEquals(data.get(0).get(0), AGConstant.NO_RECORD_FOUND,"FAILED! No record message display incorrect when have no result found"); Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND,"FAILED! No record label is incorrect");
            return;
        }
        Assert.assertEquals(data.get(0).get(page.colLoginID -1),downlineAccount,"Failed! Data not display");

        log("INFO: Executed completely");
    }

}
