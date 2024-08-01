package api.testcases.MeritoAPI;

import api.objects.meritoAPI.Market;
import api.objects.meritoAPI.result.CompetitionResult;
import api.objects.meritoAPI.result.EventListResult;
import api.objects.meritoAPI.result.MarketResult;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.*;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MA013_GetMarketChangeTest extends BaseCaseAPI {
    /**
     * @title: Validate that can get Market Change
     * @steps: 1 Access api url for merito
     * 2 Login with valid username and password and get a market id
     * 3 Access Get market change info
     * @expect: Verify can get api
     */

    @TestRails(id = "426")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA013_GetMarketChangeTest_426(String username, String password) throws Exception {
        log("@title: Validate that can get Market Change");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(1).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, sportID, eventId, Integer.toString(competitionId), "", "FIRST_TO_START");
        Market marketCatalog = marketResultObj.getMarketList().get(0);

        log("Step 6 Access API Market Change with market id");
        MarketResult marketBookResultObj = GetMarketChangeUtils.getMarketChangeAPI(token, marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);

        log("Verify Verify can get api success");
        Assert.assertTrue(marketResultObj.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(marketCatalog.getMarketId(), marketBook.getMarketId(), "FAILED! Market id is incorrect");
        Assert.assertEquals(marketCatalog.getSelectionList().size(), marketBook.getSelectionList().size(), "FAILED! List runners is not correct");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that can get Market Change V2
     * @steps: 1 Access api url for merito
     * 2 Login with valid username and password and get a market id
     * 3 Access Get market change V2 API info
     * @expect: Verify can get api
     */

    @TestRails(id = "427")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA013_GetMarketChangeV2Test_427(String username, String password) throws Exception {
        log("@title: Validate that can get Market Change V2");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(1).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, sportID, eventId, Integer.toString(competitionId), "", "FIRST_TO_START");
        Market marketCatalog = marketResultObj.getMarketList().get(0);

        log("Step 6 Access API Market Change V2 with market id");
        MarketResult marketBookResultObj = GetMarketChangeUtils.getMarketChangeV2API(token, marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);

        log("Verify Verify can get api success");
        Assert.assertTrue(marketResultObj.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(marketCatalog.getMarketId(), marketBook.getMarketId(), "FAILED! Market id is incorrect");
        Assert.assertEquals(marketCatalog.getSelectionList().size(), marketBook.getSelectionList().size(), "FAILED! List runners is not correct");

        log("INFO: Executed completely");
    }

}
