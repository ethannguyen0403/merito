package backoffice.testcases.marketmanagement;

import agentsite.pages.marketsmanagement.BlockUnblockEventPage;
import backoffice.objects.bo.marketmanagement.Market;
import backoffice.pages.bo.marketmanagement.EventMarketStatusPage;
import backoffice.pages.bo.marketmanagement.FindBlockedMarketPage;
import backoffice.utils.tools.EventMarketStatusUtils;
import backoffice.utils.tools.FindBlockedMarketUtils;
import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static backoffice.common.BOConstants.DASH_YYYY_MM_DD;
import static backoffice.common.BOConstants.GMT_FOUR;
import static common.AGConstant.HomePage.SPORT_ID;
import static common.AGConstant.SPORT_CRICKET;

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
    @Test(groups = {"smoke","MER.Maintenance.2024.V.5.0"})
    @Parameters("satMemberLoginID")
    public void BO_Tools_Find_Blocked_Market_609(String satMemberLoginID) {
        log("Step 2. Access Tool > Find Block Market");
        log("@title: Validate can find blocked market for an account");
        log("Step 1. Access Tool > Event/Market Status, filter Event date: Today and get sport, competition, event id, event name, of a market");
        String today = DateUtils.getDate(0, "yyyy-MM-dd", GMT_FOUR);
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

        String expectedMarketInfo = String.format("Market: %s | Status: %s | Market Type: %s | Start Time: %s | Total Matched: %s | Minute to start: %s",
                lstMarketInfo.get(0), lstMarketInfo.get(1), lstMarketInfo.get(2), lstMarketInfo.get(3), totalMatch, lstMarketInfo.get(5));
        Assert.assertEquals(page.lblMarketHeader.getText(), expectedMarketInfo, "FAILED! Market info not match with expected");
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
    @Parameters({"satMemberLoginID", "memberPassword", "satSADAgentLoginID"})
    public void BO_Tools_Find_Blocked_Market_610(String satMemberLoginID, String memberPassword, String satSADAgentLoginID) throws Exception {
        log("@title: Validate Agent site - Block unblock event status is correctly as filtering");
        log("Step 1. Access Tool > Event/Market Status, filter Event date: Today and get sport, competition, event id, event name, of a market");
        String date = DateUtils.getDate(0, DASH_YYYY_MM_DD, GMT_FOUR);
        EventMarketStatusPage eventMarketStatusPage = backofficeHomePage.navigateEventMarketStatus();
        Market market = eventMarketStatusPage.getMarketInfo(date, SPORT_ID.get(SPORT_CRICKET), false);

        log("Step 2. Access Tool > Find Block Market\n" +
                "Input username, Event ID, Market ID and click search button.");
        DriverManager.getDriver().switchToParentFrame();
        FindBlockedMarketPage page = backofficeHomePage.navigateFindBlockedMarket();
        page.search(satMemberLoginID, market.event.getEventId(), market.getMarketId());
        log("Step 3. The data display and get Status in Block/Unblock Event column");
        String blockStatus = page.getBlockedStatus(satSADAgentLoginID);
        log("Step 4. Login agent the level control blocking > Block/Unblock Event");
        agentHomePage = loginAgent(satSADAgentLoginID, memberPassword, "satsport");
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("",SPORT_CRICKET, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        blockUnblockEventPage.searchEvent(market.event.getEventName());

        log("Verify 1. Verify status in agent site is match with Find Blocked Market Page");
        blockUnblockEventPage.verifyBlockUnblockEvent(market.event.getEventName(), blockStatus);
        log("INFO: Executed completely");
    }


}
