package backoffice.testcases.temp;

import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import backoffice.common.BOConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.temp.RunnerStatusPage;
import baseTest.BaseCaseMerito;
import backoffice.utils.settlement.RunnerStatusUtils;

import java.util.ArrayList;
import java.util.List;

public class RunnerStatusTest extends BaseCaseMerito{

    /**
     * @title: Validate can search Runner Status
     * @pre-condition:
     *           1. Log in BO
     * @steps: 1. Access Settlement > Runner Status
     *           2. Select event date: Today
     *           Sport: and sport have runner today
     *           Event: any event
     *           Market: any market:
     *           3. Click Search
     * @expect: 1. Verify Data is display  correctly event id and market id
     */
    @Test (groups = {"smoke"})
    public void BO_Settlement_Runner_Status_001(){
        log("@title: Validate can search Runner Status");
        log("Step 1. Access Settlement > Runner Status");
        String date = DateUtils.getDate(0,"yyy-MM-dd",BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = RunnerStatusUtils.getSports(date);
        String sportId = lstSport.get(0).get(0);
        String sportName = lstSport.get(0).get(1);
        List<ArrayList<String>> lstEvent = RunnerStatusUtils.getEvents(sportId,date);
        String eventId = lstEvent.get(0).get(0);
        String eventName = lstEvent.get(0).get(1);
        List<ArrayList<String>> lstMarket = RunnerStatusUtils.getMarkets(eventId);
        String marketId = lstMarket.get(0).get(0);
        String marketName = lstMarket.get(0).get(1);

        RunnerStatusPage page = backofficeHomePage.navigateRunnerStatus();
        log("Step 2. Select event date: Today\n" +
                "     *           Sport: and sport have runner today\n" +
                "     *           Event: any event\n" +
                "     *           Market: any market:\n" +
                "     *           3. Click Search");
        log("Step 3. Click Search");
        page.search("",sportName,eventName,marketName);

        log("Verify 1. Verify Data is display correctly event id and market id");
        List<ArrayList<String>> lstData = page.tblRunnerStatus.getRowsWithoutHeader(20,false);
        for(int i =0; i<lstData.size() ;i++){
            Assert.assertEquals(lstData.get(i).get(page.colEventID -1),eventId,"FAILED! Event ID not match with the expected");
            Assert.assertEquals(lstData.get(i).get(page.colMarketID -1),marketId,"FAILED! Market ID not match with the expected");
        }

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate check external button works
     * @pre-condition:
     *           1. Login BO
     * @steps: 1. Access Settlement > Runner Status
     *          2. Search event date of an event have runner and click on Check external button
     * @expect: 1. New tab will navigate to the betfair site:
     * https://www.betfair.com/exchange/plus/football/market/1.[market ID]
     */
    @Test (groups = {"smoke"})
    public void BO_Settlement_Runner_Status_002(){
        log("@title: Validate check external button works");
        log("Step 1. Access Settlement > Runner Status");
        String date = DateUtils.getDate(0,"yyy-MM-dd",BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = RunnerStatusUtils.getSports(date);
        String sportId = lstSport.get(0).get(0);
        String sportName = lstSport.get(0).get(1);
        List<ArrayList<String>> lstEvent = RunnerStatusUtils.getEvents(sportId,date);
        String eventId = lstEvent.get(0).get(0);
        String eventName = lstEvent.get(0).get(1);
        List<ArrayList<String>> lstMarket = RunnerStatusUtils.getMarkets(eventId);
        String marketId = lstMarket.get(0).get(0);
        String marketName = lstMarket.get(0).get(1);
        RunnerStatusPage page = backofficeHomePage.navigateRunnerStatus();

        log("Step 2. Search event date of an event have runner and click on Check external button");
        page.search("",sportName,eventName, marketName);
        page.btnCheckExternal.click();

        log("Verify 1. New tab will navigate to the betfair site:\n" +
                "     * https://www.betfair.com/exchange/plus/football/market/1.[market ID]");
        ArrayList<String> newtab = new ArrayList<>(DriverManager.getDriver().getWindowHandles());
        DriverManager.getDriver().switchTo().window(newtab.get(1));
        String actualUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl,String.format("https://www.betfair.com/exchange/plus/%s/market/1.%s",sportName.toLowerCase(),marketId),"FAILED! Direct to incorrect BF url");

        log("INFO: Executed completely");
    }
}
