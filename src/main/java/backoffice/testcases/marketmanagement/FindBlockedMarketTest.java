package backoffice.testcases.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.marketmanagement.FindBlockedMarketPage;
import backoffice.utils.tools.EventMarketStatusUtils;
import backoffice.utils.tools.FindBlockedMarketUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class FindBlockedMarketTest extends BaseCaseTest {

    /**
     * @title: Validate can find blocked market for an account
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Tool > Event/Market Status, filter Event date: Today and get sport, competition, event id, event name, of a market
     * 2. Access Tool > Find Block Market
     * 3.Input username, Event ID, Market ID and click search button
     * @expected: 1. Verify market info display correctly
     */
    @TestRails(id = "609")
    @Test(groups = {"smoke"})
    @Parameters("satMemberLoginID")
    public void BO_Tools_Find_Blocked_Market_609(String satMemberLoginID) {
        log("Step 2. Access Tool > Find Block Market");
        log("@title: Validate can find blocked market for an account");
        log("Step 1. Access Tool > Event/Market Status, filter Event date: Today and get sport, competition, event id, event name, of a market");
        String today = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = EventMarketStatusUtils.getSport(today, false);
        List<ArrayList<String>> lstCompetition = EventMarketStatusUtils.getCompetition(today, false, lstSport.get(0).get(0));
        List<ArrayList<String>> lstEvent = EventMarketStatusUtils.getEvent(today, false, lstSport.get(0).get(0), lstCompetition.get(0).get(0));
        List<ArrayList<String>> lstMarket = EventMarketStatusUtils.getMarket(today, false, lstEvent.get(0).get(0));
        List<String> lstEventInfo = FindBlockedMarketUtils.getEventInfo(satMemberLoginID, lstEvent.get(0).get(0), lstMarket.get(0).get(0));
        List<String> lstMarketInfo = FindBlockedMarketUtils.getMarketInfo(satMemberLoginID, lstEvent.get(0).get(0), lstMarket.get(0).get(0));
        String value = lstMarketInfo.get(4).split(" ")[0];
        String totalMatch = StringUtils.formatCurrency(value);
        totalMatch = lstMarketInfo.get(4).replace(value, totalMatch);
        log("Step 2. Access Tool > Find Block Market");
        FindBlockedMarketPage page = backofficeHomePage.navigateFindBlockedMarket();

        log("Step 3.Input username, Event ID, Market ID and click search button");
        page.search(satMemberLoginID, lstEvent.get(0).get(0), lstMarket.get(0).get(0));

        log("Verify 1. Verify market info display correctly");
        String expectedInfo = String.format("Sport: %s | Competition: %s | Country: %s | Venue: %s | Event: %s | Status: %s | Open Time: %s",
                lstEventInfo.get(0), lstEventInfo.get(1), lstEventInfo.get(2), lstEventInfo.get(3), lstEventInfo.get(4), lstEventInfo.get(5), lstEventInfo.get(6));
        Assert.assertEquals(page.lblEventHeader.getText(), expectedInfo, "FAILED! Event info not match with expected");

        String lblMarketInfoActual = page.lblMarketHeader.getText();
        String expectedMarketInfo = String.format("Market: %s | Status: %s | Market Type: %s | Start Time: %s | Total Matched: %s | Minute to start: %s",
                lstMarketInfo.get(0), lstMarketInfo.get(1), lstMarketInfo.get(2), lstMarketInfo.get(3), totalMatch, lstMarketInfo.get(5));
        Assert.assertEquals(lblMarketInfoActual, expectedMarketInfo, "FAILED! Market info not match with expected");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Agent site - Block unblock event status is correctly as filtering
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Event/Market Status, filter Event date: Today and get sport, competition, event id, event name, of a market
     * 2. Access Tool > Find Block Market
     * 3.Input username, Event ID, Market ID and click search button.
     * 4. The data display and get Status in Block/Unblock Event column
     * 5. Login agent the level control blocking > Block/Unblock Event
     * @expect: 1. Verify status in agent site is match with Find Blocked Market Page
     */
    @TestRails(id = "610")
    @Test(groups = {"smoke"})
    @Parameters({"satMemberLoginID", "memberPassword", "satSADAgentLoginID", "downlineSADAccount"})
    public void BO_Tools_Find_Blocked_Market_610(String satMemberLoginID, String memberPassword, String satSADAgentLoginID, String downlineSADAccount) throws Exception {
        log("@title: Validate Agent site - Block unblock event status is correctly as filtering");
        log("Step 1. Access Tool > Event/Market Status, filter Event date: Today and get sport, competition, event id, event name, of a market");
        String today = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = EventMarketStatusUtils.getSport(today, false);
        String sportName = "Soccer";
        String competitionID = EventMarketStatusUtils.getCompetition(today, false, "1").get(0).get(0);
        List<ArrayList<String>> lstEvent = EventMarketStatusUtils.getEvent(today, false, "1", competitionID);
        String eventID = lstEvent.get(0).get(0);
        String eventName = lstEvent.get(0).get(1);
        String marketID = EventMarketStatusUtils.getMarket(today, false, eventID).get(0).get(0);

        log("Step 2. Access Tool > Find Block Market");
        FindBlockedMarketPage page = backofficeHomePage.navigateFindBlockedMarket();

        log("Step 3.Input username, Event ID, Market ID and click search button.");
        page.search(satMemberLoginID, eventID, marketID);

        log("Step 4. The data display and get Status in Block/Unblock Event column");
        String eventStatus = page.tblBlockedMarket.getColumn(page.colBlockUnblockEventStatus, false).get(3);

       /* log("Step 5. Login agent the level control blocking > Block/Unblock Event");
      //  Helper.loginAgentIgnoreCaptchaTest(environment.getSatAgentSOSURL(),environment.getSatAgentSosValidationURL(),environment.getSatAgentDashboardURL(),satSADAgentLoginID,memberPassword,environment.getSecurityCode());
        BaseCaseSATAgent.loginAgent(environment.getSatAgentSOSURL(),environment.getSatAgentSecurityCodeUrl(),satSADAgentLoginID,memberPassword,environment.getSecurityCode());
        backofficeHomePage agentbackofficeHomePage = new backofficeHomePage();
        BlockUnblockEventPage blockUnblockEventPage = agentbackofficeHomePage.navigateBlockUnblockEvents();
        blockUnblockEventPage.filter("",sportName,"Today");
        blockUnblockEventPage.selectDownline(downlineSADAccount,true);
        blockUnblockEventPage.searchEvent(eventName);

        log("Verify 1. Verify status in agent site is match with Find Blocked Market Page");
        blockUnblockEventPage.verifyBlockUnblockEvent(lstEvent.get(0).get(1), eventStatus);
*/
        log("INFO: Executed completely");
    }


}
