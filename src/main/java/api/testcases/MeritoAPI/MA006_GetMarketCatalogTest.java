package api.testcases.MeritoAPI;

import api.objects.meritoAPI.Market;
import api.objects.meritoAPI.result.CompetitionResult;
import api.objects.meritoAPI.result.EventListResult;
import api.objects.meritoAPI.result.MarketResult;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.GetCompetitionUtils;
import api.utils.MeritoAPI.GetEventsUtils;
import api.utils.MeritoAPI.GetMarketCatalogUtils;
import api.utils.MeritoAPI.LoginMemberUtils;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Objects;

public class MA006_GetMarketCatalogTest extends BaseCaseAPI {
    /**
     * @title: Validate that can get Event Type
     * @steps: 1 Access api url for merito
     * 2 Login with valid username and password
     * 3 Access get event api and intput sport id
     * @expect: Verify can get api
     */
    @TestRails(id = "423")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA006_GetMarketCatalogTest_423(String username, String password) throws Exception {
        log("@title: Validate that can get Event Type");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(0).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get market of the event get in step 4");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, sportID, eventId, Integer.toString(competitionId), "", "FIRST_TO_START");

        log("Verify Verify can get api success");
        Assert.assertTrue(marketResultObj.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertTrue(Objects.nonNull(marketResultObj.getMarketList()), "FAILED! Market count is null");
        log("INFO: Executed completely");
    }

    @TestRails(id = "421")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA006_GetMarketCatalogTest_002(String username, String password) throws Exception {
        log("@title: Validate that can get Event Type");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(0).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, sportID, eventId, Integer.toString(competitionId), "", "FIRST_TO_START");
        Market marketExpected = marketResultObj.getMarketList().get(0);

        log("Step 6 Access api with market id");
        marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, sportID, eventId, Integer.toString(competitionId), marketExpected.getMarketId(), "FIRST_TO_START");
        Market market = marketResultObj.getMarketList().get(0);

        log("Verify Verify can get api success");
        Assert.assertTrue(marketResultObj.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(market.getMarketId(), marketExpected.getMarketId(), "FAILED!The Search market is incorrect with the expected");
        Assert.assertEquals(market.getMarketName(), marketExpected.getMarketName(), "FAILED!The Search market is incorrect with the expected");
        Assert.assertEquals(marketResultObj.getMarketList().size(), 1, "FAILED!Should only display 1 result when input market id");

        log("INFO: Executed completely");
    }

    @TestRails(id = "424")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA006_GetMarketCatalogV2Test_002(String username, String password) throws Exception {
        log("@title: Validate that can get Event Type");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(0).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogV2API(token, sportID, eventId, Integer.toString(competitionId), "", "FIRST_TO_START");
        Market marketExpected = marketResultObj.getMarketList().get(0);

        log("Step 6 Access api with market id");
        marketResultObj = GetMarketCatalogUtils.getMarketCatalogV2API(token, sportID, eventId, Integer.toString(competitionId), marketExpected.getMarketId(), "FIRST_TO_START");
        Market market = marketResultObj.getMarketList().get(0);

        log("Verify Verify can get api success");
        Assert.assertTrue(marketResultObj.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(market.getMarketId(), marketExpected.getMarketId(), "FAILED!The Search market is incorrect with the expected");
        Assert.assertEquals(market.getMarketName(), marketExpected.getMarketName(), "FAILED!The Search market is incorrect with the expected");
        Assert.assertEquals(marketResultObj.getMarketList().size(), 1, "FAILED!Should only display 1 result when input market id");

        log("INFO: Executed completely");
    }
}
