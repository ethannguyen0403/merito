package backoffice.testcases.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.marketmanagement.EventMarketStatusPage;
import backoffice.utils.tools.EventMarketStatusUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class EventMarketStatusTest extends BaseCaseTest {

    /**
     * @title: Validate can search Sport
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Events/Market Status
     * 2. Select Event Date
     * 3. Input correct Sport in Search Sport textbox
     * 4. Click on a sport
     * @expect: 1. Verify sport display in the list
     * 2. The Competition display corresponding
     */
    @TestRails(id = "605")
    @Test(groups = {"smoke"})
    public void BO_Market_ManagementEvent_Market_Status_001() {
        log("@title: Validate can search Sport");
        log("Step 1. Access Tool > Events/Market Status");
        EventMarketStatusPage page = backofficeHomePage.navigateEventMarketStatus();
        String today = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = EventMarketStatusUtils.getSport(today, true);
        String sportID = lstSport.get(0).get(0);
        String sportName = lstSport.get(0).get(1);

        log("Step 2. Select Event Date");
        log("Step 3. Input correct Sport in Search Sport textbox");
        page.searchSport(sportName);

        log("Step 4. Click on a sport");
        log("Verify 1. Verify sport display in the list");
        Assert.assertTrue(page.isSportDisplay(sportName, true), "FAILED! the Sport not display when searching");
        List<ArrayList<String>> lstCompetition = EventMarketStatusUtils.getCompetition(today, true, sportID);

        log("Verify 2. The Competition display corresponding");
        Assert.assertTrue(page.isCompetitionDisplay(lstCompetition), "FAILED! the list Competition not display as expected when select the according sport");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search Competition
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Events/Market Status
     * 2. Select Event Date : today date
     * 3. Click on a sport
     * 4. Input a competition into search textbox
     * 5. Click on a Competition
     * @expect: 1. The events display corresponding when selecting competition
     **/
    @TestRails(id = "606")
    @Test(groups = {"smoke"})
    public void BO_Market_ManagementEvent_Market_Status_002() {
        log("@title: Validate can search Competition ");
        log("Step 1. Access Tool > Events/Market Status");
        String today = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = EventMarketStatusUtils.getSport(today, false);
        String sportID = lstSport.get(0).get(0);
        String sportName = lstSport.get(0).get(1);
        EventMarketStatusPage page = backofficeHomePage.navigateEventMarketStatus();

        log("Step 2. Select Event Date");
        log("Step 3. Click on a sport");
        page.isSportDisplay(sportName, true);

        log("Step 4. Input a competition into search textbox");
        List<ArrayList<String>> lstCompetition = EventMarketStatusUtils.getCompetition(today, false, sportID);
        String compID = lstCompetition.get(0).get(0);
        String competitionName = lstCompetition.get(0).get(1);
        page.searchCompetition(competitionName);

        log("Verify 1.Competition display in the list");
        log("Step 5. Click on a Competition");
        Assert.assertTrue(page.isCompetitionDisplay(competitionName, true), "FAILED! The searching competition not display on the list");
        List<ArrayList<String>> lstEvent = EventMarketStatusUtils.getEvent(today, false, sportID, compID);

        log("Verify 1. The events display corresponding when selecting competition");
        Assert.assertTrue(page.isEventDisplay(lstEvent), "FAILED! Event list does not display as api when selecting according competition");

        log("INFO: Executed completely");
    }

    /**
     * @title:Validate can search Event Name
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Events/Market Status
     * 2. Select Event Date : today date
     * 3. Click on a sport
     * 4. Select any competition
     * 5. Get any event and search with event ID
     * 6. Click on Event
     * @expect: 1. Verify Event display in the list
     * 2. The markets display corresponding
     **/
    @TestRails(id = "607")
    @Test(groups = {"smoke"})
    public void BO_Market_ManagementEvent_Market_Status_003() {
        log("@title: Validate can search Event Name ");
        log("Step 1. Access Tool > Events/Market Status");
        String today = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = EventMarketStatusUtils.getSport(today, false);
        String sportID = lstSport.get(0).get(0);
        String sportName = lstSport.get(0).get(1);
        EventMarketStatusPage page = backofficeHomePage.navigateEventMarketStatus();

        log("Step 2. Select Event Date");
        log("Step 3. Click on a sport");
        page.isSportDisplay(sportName, true);

        log("Step 4. Select any competition");
        List<ArrayList<String>> lstCompetition = EventMarketStatusUtils.getCompetition(today, false, sportID);
        String compID = lstCompetition.get(0).get(0);
        String competitionName = lstCompetition.get(0).get(1);
        page.isCompetitionDisplay(competitionName, true);

        log("Step 5. Get any event and search with event ID");
        List<ArrayList<String>> lstEvent = EventMarketStatusUtils.getEvent(today, false, sportID, compID);
        String eventId = lstEvent.get(0).get(0);
        page.searchEvent(eventId, "", "");

        log("Verify 1. Verify Event display in the list");
        log("Step 6. Click on Event");
        Assert.assertTrue(page.isEventDisplay(eventId, "", true), "FAILED! The event ID not display in the list after searching");
        List<ArrayList<String>> lstMarket = EventMarketStatusUtils.getMarket(today, false, eventId);

        log("Verify 1. The markets display corresponding");
        Assert.assertTrue(page.isMarketDisplay(lstMarket), "FAILED! Market list does not display as api when selecting according competition");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search market ID
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Events/Market Status
     * 2. Select Event Date : today date
     * 3. Click on a sport
     * 4. Select any competition
     * 5. Get any event and search with event ID
     * 6. Click on Event
     * @expect: 1. Verify Event display in the list
     * 2. The markets display corresponding
     **/
    @TestRails(id = "608")
    @Test(groups = {"smoke"})
    public void BO_Market_ManagementEvent_Market_Status_004() {
        log("@title: Validate can search market ID ");
        log("Step 1. Access Tool > Events/Market Status");
        String today = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = EventMarketStatusUtils.getSport(today, false);
        String sportID = lstSport.get(0).get(0);
        String sportName = lstSport.get(0).get(1);
        EventMarketStatusPage page = backofficeHomePage.navigateEventMarketStatus();

        log("Step 2. Select Event Date");
        log("Step 3. Click on a sport");
        page.isSportDisplay(sportName, true);

        log("Step 4. Select any competition");
        List<ArrayList<String>> lstCompetition = EventMarketStatusUtils.getCompetition(today, false, sportID);
        String compID = lstCompetition.get(0).get(0);
        String competitionName = lstCompetition.get(0).get(1);
        page.isCompetitionDisplay(competitionName, true);

        log("Step 5. Select any event");
        List<ArrayList<String>> lstEvent = EventMarketStatusUtils.getEvent(today, false, sportID, compID);
        String eventId = lstEvent.get(0).get(0);
        page.isEventDisplay(eventId, "", true);
        List<ArrayList<String>> lstMarket = EventMarketStatusUtils.getMarket(today, false, eventId);
        String marketID = lstMarket.get(0).get(0);

        log("Verify 1. Verify Event display in the list");
        log("Step 6. Input a market ID exist in the list and press enter");
        page.searchMarket(marketID, "", "", "");

        log("Verify 2. The markets display corresponding");
        Assert.assertTrue(page.isMarketDisplay(marketID, "", false), "FAILED! Market list does not display as api when selecting according competition");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate event in open status only display when select Open Only checkbox
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > Events/Market Status
     * 2. Select Event Date : today
     * 3. Observe event/market status of all sport
     * @expect: 1. Verify the status of event/market always open
     **/

    @Test(groups = {"regression"})
    public void BO_Market_ManagementEvent_Market_Status_005() {
        log("@title:  Validate event in open status only display when select Open Only checkbox");
        log("Step 1. Access Tool > Events/Market Status");
        EventMarketStatusPage page = backofficeHomePage.navigateEventMarketStatus();

        log("Step  2. Select Event Date : today");
        log("Step 3. Observe event/market status of all sport");
        //  page.cbOpenOnly.click();
        String today = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = EventMarketStatusUtils.getSport(today, false);
        String sportID = lstSport.get(0).get(0);
        String sportName = lstSport.get(0).get(1);
        page.isSportDisplay(sportName, true);
        List<ArrayList<String>> lstCompetition = EventMarketStatusUtils.getCompetition(today, true, sportID);


        log("Verify 1. Verify the status of event/market always open");
        for (int i = 0; i < lstCompetition.size(); i++) {
            page.isCompetitionDisplay(lstCompetition.get(i).get(1), true);
            Assert.assertTrue(page.verifyEventsStatus("Open"), String.format("FAILED, All Event status not Open as expected"));
        }

        log("INFO: Executed completely");
    }
}
