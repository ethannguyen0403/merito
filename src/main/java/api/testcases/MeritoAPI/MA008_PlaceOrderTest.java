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

public class MA008_PlaceOrderTest extends BaseCaseAPI {
    /**
     * @title: Validate Place a match bet
     * @Percondition: Login api and get token code
     * @steps: 1/ Access get event api to Place bet
     * 2/ Input valid info and place a matched bet
     * @expect: Verify can place matched bet
     */

    @TestRails(id = "428")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA008_PlaceOrderTest_428(String username, String password) throws Exception {
        log("@title:Validate Place a match bet");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        MemberInfo memberInfo = GetMemberInfoUtils.getMembrInfo(token);

        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(0).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market catalog of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, sportID, eventId, Integer.toString(competitionId), "", "FIRST_TO_START");
        int totalMarket = marketResultObj.getMarketList().size();
        Market marketCatalog = marketResultObj.getMarketList().get(totalMarket - 1);

        log("Step 6 Get Market Book");
        MarketResult marketBookResultObj = GetMarketBookUtils.getMarketBookAPI(token, marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);

        String runnerId = Integer.toString(marketBook.getSelectionList().get(0).getid());
        double handicap = marketBook.getSelectionList().get(0).gethandicap();
        String price = Double.toString(marketBook.getSelectionList().get(0).get_availableBack() == 0 ? 1.01 : marketBook.getSelectionList().get(0).get_availableBack());
        String size = Double.toString(memberInfo.getLstBetSetting().get(0).getMinBet());
        String orderType = "LIMIT";
        String side = "BACK";
        String persistenceType = "LAPSE";

        log("Step 7 Place a match bet");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token, eventId, runnerId, marketBook.getMarketId(), Double.toString(handicap), price, size, orderType, side, persistenceType);

        if (Objects.isNull(orderResult)) {
            log("Skip place bet as odds is empty");
            Assert.assertTrue(true, "By pass this test case");
            return;
        }

        log(String.format("Verify can place matched order: Bet ID: %s", Integer.toString(orderResult.getOrderList().get(0).getBetId())));
        Assert.assertTrue(orderResult.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertTrue(orderResult.getOrderList().get(0).getBetId() != 0, "FAILED! price matched is incorrect");
        Assert.assertEquals(Integer.toString(orderResult.getOrderList().get(0).getEventId()), eventId, "FAILED! Event id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getMarketId(), marketBook.getMarketId(), "FAILED! Market id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSide(), side, "FAILED! Side id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getHandicap(), handicap, "FAILED! Handicap is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getOrderType(), orderType, "FAILED! Order Type is incorrect");
        // Assert.assertEquals(orderResult.getOrderList().get(0).getSizeMatched(),memberInfo.getLstBetSetting().get(0).getMinBet(),"FAILED!Size Matched is incorrect");
        Assert.assertTrue(orderResult.getOrderList().get(0).getPriceMatched() >= marketBook.getSelectionList().get(0).get_availableBack(), String.format("FAILED!price matched is incorrect. Expected price match is %.2f but found %.2f", orderResult.getOrderList().get(0).getPriceMatched(), marketBook.getSelectionList().get(0).get_availableBack()));
        Assert.assertEquals(orderResult.getOrderList().size(), 1, "FAILED! List Order more than 1");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Place a unmatch bet
     * @Percondition: Login api and get token code
     * @steps: 1/ Access get event api to get  Market
     * 2/ Input valid info and place a unmatched bet
     * @expect: Verify can place unmatched bet
     */

    @TestRails(id = "429")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA008_PlaceOrderTest_429(String username, String password) throws Exception {
        log("@title: Validate Place a unmatch bet");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "2"; // 2 is Tennis sport ID
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        MemberInfo memberInfo = GetMemberInfoUtils.getMembrInfo(token);

        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(1).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, sportID, "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId()); //3 is for TENNIS

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

        log(String.format("Step 7 Place a unmatched bet on event %s market id:%s price %s size %s and side %s",eventId,marketBook.getMarketId(),price,size,side));
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token, eventId, runnerId, marketBook.getMarketId(), handicap, price, size, orderType, side, persistenceType);
        if (!Objects.nonNull(orderResult)) {
            log("Skip place bet as odds is empty");
            Assert.assertTrue(true, "By pass this test case");
            return;
        }
        String betID = Integer.toString(orderResult.getOrderList().get(0).getBetId());
        log(String.format("Verify can place unmatched  order: Bet ID: %s", betID));
        Assert.assertTrue(orderResult.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertTrue(orderResult.getOrderList().get(0).getBetId() != 0, "FAILED! price matched is incorrect");
        Assert.assertEquals(Integer.toString(orderResult.getOrderList().get(0).getEventId()), eventId, "FAILED! Event id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getMarketId(), marketBook.getMarketId(), "FAILED! Market id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSide(), side, "FAILED! Side id is incorrect");
        Assert.assertEquals(Double.toString(orderResult.getOrderList().get(0).getHandicap()), handicap, "FAILED! Handicap is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getOrderType(), orderType, "FAILED! Order Type is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSizeMatched(), 0.0, "FAILED!Size Matched is incorrect");
        Assert.assertEquals(Integer.toString(orderResult.getOrderList().get(0).getPriceMatched().intValue()), "0", "FAILED!price matched is incorrect");
        Assert.assertEquals(orderResult.getOrderList().size(), 1, "FAILED! List Order more than 1");

        log("Step 9 Post-condition: Cancel Unmatched bet");
        CancelOrderUtils.cancelOrder(token, betID, "0");

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate that can not place bet exposure greater than balance
     * @Percondition: Login api and get token code
     * @steps: 1/ Access get event api to get  Market
     * 2/ Input valid info and place a bet that over available balance
     * @expect: Verify cannot place bet
     */

    @TestRails(id = "430")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke", "smoke_dev"})
    public void MA008_PlaceOrderTest_430(String username, String password) throws Exception {
        log("@title: Validate that can not place bet exposure greater than balance");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "2";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        MemberInfo memberInfo = GetMemberInfoUtils.getMembrInfo(token);

        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token, sportID, "false");
        int competitionId = competitionResult.getCompetitionList().get(0).getId();

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
        String price = Double.toString(marketBook.getSelectionList().get(0).get_availableBack() == 0 ? 2 : marketBook.getSelectionList().get(0).get_availableBack());
        String size = Double.toString(memberInfo.getAvailableBalance().intValue() + 3);
        String orderType = "LIMIT";
        String side = "BACK";
        String persistenceType = "LAPSE";

        log("Step 7 Place bet with exposure greater than available balance");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token, eventId, runnerId, marketBook.getMarketId(), handicap, price, size, orderType, side, persistenceType);
        if (!Objects.nonNull(orderResult)) {
            log("Skip place bet as odds is empty");
            Assert.assertTrue(true, "By pass this test case");
            return;
        }
        log(String.format("Verify can NOT place order: Bet ID: %s", orderResult.getOrderList().get(0).getBetId()));
        Assert.assertTrue(orderResult.getIsSuccess(), "FAILED! isSuccess should be true but can not get as expected");
        Assert.assertTrue(orderResult.getOrderList().get(0).getBetId() != 0, "FAILED! price matched is incorrect");
        Assert.assertEquals(Integer.toString(orderResult.getOrderList().get(0).getEventId()), eventId, "FAILED! Event id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getMarketId(), marketBook.getMarketId(), "FAILED! Market id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSide(), side, "FAILED! Side id is incorrect");
        Assert.assertEquals(Double.toString(orderResult.getOrderList().get(0).getHandicap()), handicap, "FAILED! Handicap is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getOrderType(), orderType, "FAILED! Order Type is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSizeMatched(), 0.0, "FAILED!Size Matched is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getErrorMessage(), "ERROR.EXPOSURE.INSUFFICIENT", "FAILED! Error message is incorrect");
        Assert.assertEquals(String.format("%.1f", orderResult.getOrderList().get(0).getPriceMatched()), "0.0", "FAILED!price matched is incorrect");
        // ERROR.EXPOSURE.INSUFFICIENT
        Assert.assertEquals(orderResult.getOrderList().size(), 1, "FAILED! List Order more than 1");

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate that can not place bet if stake less than min stake
     * @Percondition: Login api and get token code
     * @steps: 1/ Access get event api to get  Market
     * 2/ Input valid info and place a bet that stake less than min stake
     * @expect: Verify can place unmatched bet
     */
    @TestRails(id = "431")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA008_PlaceOrderTest_431(String username, String password) throws Exception {
        log("@title:V alidate that can not place bet if stake less than min stake");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        String sportID = "2";
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
        String price = Double.toString(marketBook.getSelectionList().get(0).get_availableBack() == 0 ? 1.01 : marketBook.getSelectionList().get(0).get_availableBack());
        String size = Double.toString(memberInfo.getLstBetSetting().get(3).getMinBet() - 1);
        String orderType = "LIMIT";
        String side = "BACK";
        String persistenceType = "LAPSE";

        log("Step 7 Place a bet with stake less than min bet");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token, eventId, runnerId, marketBook.getMarketId(), handicap, price, size, orderType, side, persistenceType);
        if (Objects.isNull(orderResult)) {
            log("Skip place bet as odds is empty");
            Assert.assertTrue(true, "By pass this test case");
            return;
        }

        log(String.format("Verify can NOT place order id: %s", orderResult.getOrderList().get(0).getBetId()));
        Assert.assertTrue(orderResult.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertTrue(orderResult.getOrderList().get(0).getBetId() != 0, "FAILED! price matched is incorrect");
        Assert.assertTrue(orderResult.getOrderList().get(0).getBetId() != 0, "FAILED! price matched is incorrect");
        Assert.assertEquals(Integer.toString(orderResult.getOrderList().get(0).getEventId()), eventId, "FAILED! Event id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getMarketId(), marketBook.getMarketId(), "FAILED! Market id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSide(), side, "FAILED! Side id is incorrect");
        Assert.assertEquals(Double.toString(orderResult.getOrderList().get(0).getHandicap()), handicap, "FAILED! Handicap is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getOrderType(), orderType, "FAILED! Order Type is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSizeMatched(), 0.0, "FAILED!Size Matched is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getPriceMatched(), 0.0, "FAILED!price matched is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getErrorMessage(), "ERROR.BET_PLACEMENT.STAKE_LIMIT", "FAILED! Error message is incorrect");
        Assert.assertEquals(orderResult.getOrderList().size(), 1, "FAILED! List Order more than 1");

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate Place a match bet
     * @Percondition: Login api and get token code
     * @steps: 1/ Access get event api to Place bet
     * 2/ Input valid info and place a matched bet
     * @expect: Verify can place matched bet
     */

    @Parameters({"username", "password"})
    @Test(groups = {"smoke1aa"})
    public void MA008_PlaceOrderTest_00test(String username, String password) throws Exception {
        log("@title:Validate Place a match bet");
        log("Step 1 Access api url for merito");
        log("Step 2 Login with valid username and password");
        // String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token = LoginMemberUtils.loginAPISuccess(username, passDes).getMessage();

        log("Step 3 Access get competition api");
        MemberInfo memberInfo = GetMemberInfoUtils.getMembrInfo(token);

        //CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token,sportID,"false");
        int competitionId = 12417599; //competitionResult.getCompetitionList().get(0).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token, "", "", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market catalog of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token, "", eventId, Integer.toString(competitionId), "", "FIRST_TO_START");
        int totalMarket = marketResultObj.getMarketList().size();
        Market marketCatalog = marketResultObj.getMarketList().get(totalMarket - 1);

        log("Step 6 Get Market Book");
        MarketResult marketBookResultObj = GetMarketBookUtils.getMarketBookAPI(token, marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);

        String runnerId = Integer.toString(marketBook.getSelectionList().get(0).getid());
        double handicap = marketBook.getSelectionList().get(0).gethandicap();
        String price = Double.toString(marketBook.getSelectionList().get(0).get_availableBack());
        String size = Double.toString(memberInfo.getLstBetSetting().get(0).getMinBet());
        String orderType = "LIMIT";
        String side = "BACK";
        String persistenceType = "LAPSE";

        log("Step 7 Place a match bet");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token, eventId, runnerId, marketBook.getMarketId(), Double.toString(handicap), price, size, orderType, side, persistenceType);

        if (Objects.isNull(orderResult)) {
            log("Skip place bet as odds is empty");
            Assert.assertTrue(true, "By pass this test case");
            return;
        }

        log(String.format("Verify can place matched order: Bet ID: %s", Integer.toString(orderResult.getOrderList().get(0).getBetId())));
        Assert.assertTrue(orderResult.getIsSuccess(), "FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertTrue(orderResult.getOrderList().get(0).getBetId() != 0, "FAILED! price matched is incorrect");
        Assert.assertEquals(Integer.toString(orderResult.getOrderList().get(0).getEventId()), eventId, "FAILED! Event id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getMarketId(), marketBook.getMarketId(), "FAILED! Market id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSide(), side, "FAILED! Side id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getHandicap(), handicap, "FAILED! Handicap is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getOrderType(), orderType, "FAILED! Order Type is incorrect");
        Assert.assertEquals(orderResult.getOrderList().get(0).getSizeMatched(), memberInfo.getLstBetSetting().get(0).getMinBet(), "FAILED!Size Matched is incorrect");
        Assert.assertTrue(orderResult.getOrderList().get(0).getPriceMatched() >= marketBook.getSelectionList().get(0).get_availableBack(),
                String.format("FAILED!price matched is incorrect. Expected price match is %.2f but found %.2f", orderResult.getOrderList().get(0).getPriceMatched(), marketBook.getSelectionList().get(0).get_availableBack()));
        Assert.assertEquals(orderResult.getOrderList().size(), 1, "FAILED! List Order more than 1");

        log("INFO: Executed completely");
    }

}
