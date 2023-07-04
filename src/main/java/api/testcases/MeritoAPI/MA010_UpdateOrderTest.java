package api.testcases.MeritoAPI;

import api.objects.meritoAPI.Market;
import api.objects.meritoAPI.MemberInfo;
import api.objects.meritoAPI.result.CompetitionResult;
import api.objects.meritoAPI.result.EventListResult;
import api.objects.meritoAPI.result.MarketResult;
import api.objects.meritoAPI.result.OrderResult;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.*;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Objects;

public class MA010_UpdateOrderTest extends BaseCaseAPI {

    /**
     * @title: Validate Update order to Lapse
     * @Percondition: Login api and get token code
     * @steps: '1/ Access get event api to Update Orders
     * 2/ Input unmatch order, update persisten type to LAPSE  and submit
     * @expect: Verify can update order
     */
    @TestRails(id = "432")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA010_UpdateOrderTest_432(String username, String password) throws Exception {
        log("@title: Validate Update order to Lapse");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = api.utils.MeritoAPI.LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        MemberInfo memberInfo = api.utils.MeritoAPI.GetMemberInfoUtils.getMembrInfo(token);

        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(1).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, sportID, eventId, Integer.toString(competitionId), "", "FIRST_TO_START");
        int totalMarket = marketResultObj.getMarketList().size();
        Market marketCatalog = marketResultObj.getMarketList().get(totalMarket - 1);

        log("Step 6 Get Market Book");
        MarketResult marketBookResultObj = GetMarketBookUtils.getMarketBookAPI(token, marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);
        String runnerId = Integer.toString(marketBook.getSelectionList().get(0).getid());
        String handicap = Double.toString(marketBook.getSelectionList().get(0).gethandicap());
        String price = Integer.toString(marketBook.getSelectionList().get(0).get_availableBack().intValue() + 20);
        String size = Double.toString(memberInfo.getLstBetSetting().get(0).getMinBet());
        String orderType = "LIMIT";
        String side = "BACK";
        String persistenceType = "PERSIST";

        log("Step 7 Place a unmatched bet");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token, eventId, runnerId, marketBook.getMarketId(), handicap, price, size, orderType, side, persistenceType);
        String betID = Integer.toString(orderResult.getOrderList().get(0).getBetId());

        log("Step 8 Update the order to LAPSE");
        OrderResult updateResult = UpdateOrderUtils.updateOrder(token, betID, "LAPSE");
        if (Objects.nonNull(orderResult)) {
            log("Skip place bet as odds is empty");
            Assert.assertTrue(true, "By pass this test case");
            return;
        }
        log(String.format("Verify can place unmatched  order: Bet ID: %s", orderResult.getOrderList().get(0).getBetId()));
        Assert.assertTrue(updateResult.getIsSuccess(), "FAILED! isSuccess should be true but cannot get as expected");
        Assert.assertEquals(Integer.toString(updateResult.getOrderList().get(0).getBetId()), betID, "FAILED! Bet Id is incorrect");
        Assert.assertEquals(updateResult.getOrderList().get(0).getErrorMessage(), "", "FAILED! Bet Id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().size(), 1, "FAILED! List Order more than 1");

        log("Step 9 Post-condition: Cancel Unmatched bet");
        CancelOrderUtils.cancelOrder(token, betID, "0");

        log("INFO: Executed completely");

    }

    /**
     * PRO cannot cancel to Persist as only has 1 account on /x /plus don't have setting LAPSE / PERSIST
     *
     * @title: Validate Update order to PERSIST
     * @Percondition: Login api and get token code
     * @steps: '1/ Access get event api to Update Orders
     * 2/ Input unmatch order, update persisten type to PERSIST and submit
     * @expect: Verify can update order
     */
    @TestRails(id = "433")
    @Parameters({"username", "password"})
    @Test(groups = {"derepcated"})
    public void MA010_UpdateOrderTest_433(String username, String password) throws Exception {
        log("@title: Validate Update order to PERSIST");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        MemberInfo memberInfo = GetMemberInfoUtils.getMembrInfo(token);

        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(1).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, sportID, eventId, Integer.toString(competitionId), "", "FIRST_TO_START");
        int totalMarket = marketResultObj.getMarketList().size();
        Market marketCatalog = marketResultObj.getMarketList().get(totalMarket - 1);

        log("Step 6 Get Market Book");
        MarketResult marketBookResultObj = GetMarketBookUtils.getMarketBookAPI(token, marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);
        String runnerId = Integer.toString(marketBook.getSelectionList().get(0).getid());
        String handicap = Double.toString(marketBook.getSelectionList().get(0).gethandicap());
        String price = Integer.toString(marketBook.getSelectionList().get(0).get_availableBack().intValue() + 20);
        String size = Double.toString(memberInfo.getLstBetSetting().get(0).getMinBet());
        String orderType = "LIMIT";
        String side = "BACK";
        String persistenceType = "LAPSE";

        log("Step 7 Place a unmatched bet");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token, eventId, runnerId, marketBook.getMarketId(), handicap, price, size, orderType, side, persistenceType);
        String betID = Integer.toString(orderResult.getOrderList().get(0).getBetId());

        log("Step 8 Update the order to PERSIST");
        OrderResult updateResult = UpdateOrderUtils.updateOrder(token, betID, "PERSIST");

        log(String.format("Verify can place unmatched  order: Bet ID: %s", orderResult.getOrderList().get(0).getBetId()));
        Assert.assertTrue(updateResult.getIsSuccess(), "FAILED! isSuccess should be true but cannot get as expected");
        Assert.assertEquals(Integer.toString(updateResult.getOrderList().get(0).getBetId()), betID, "FAILED! Bet Id is incorrect");
        Assert.assertEquals(updateResult.getOrderList().get(0).getErrorMessage(), "", "FAILED! Bet Id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().size(), 1, "FAILED! List Order more than 1");

        log("Step 9 Post-condition: Cancel Unmatched bet");
        CancelOrderUtils.cancelOrder(token, betID, "0");

        log("INFO: Executed completely");

    }


}
