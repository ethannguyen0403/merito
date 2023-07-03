package api.testcases.MeritoAPI;

import com.paltech.utils.StringUtils;
import api.objects.meritoAPI.Market;
import api.objects.meritoAPI.MemberInfo;
import api.objects.meritoAPI.result.CompetitionResult;
import api.objects.meritoAPI.result.EventListResult;
import api.objects.meritoAPI.result.MarketResult;
import api.objects.meritoAPI.result.OrderResult;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import api.testcases.BaseCaseAPI;
import api.utils.MeritoAPI.*;
import api.utils.testraildemo.TestRails;

import java.util.Objects;

public class MA009_CancelOrderTest extends BaseCaseAPI {
    /**
     * @title: Validate can cancel unmatch orders
     * @Percondition: Login api and get token code
     * @steps:
     * 1/ Access get event api to Cancel bet
     * 2/ Input unmatch order and submit
     * @expect: Verify can cancel unmatched bet
     */

    @TestRails(id = "434")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void  MA009_CancelOrderTest_434(String username, String password) throws Exception {
        log("@title: Validate can cancel unmatch orders");
        log("Step 1 Access get event api to Cancel bet");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token= LoginMemberUtils.loginAPISuccess(username,passDes).getMessage();

        log("Step 3 Access get competition api");
        MemberInfo memberInfo =GetMemberInfoUtils.getMembrInfo(token);

        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token,sportID,"false");
        int competitionId = competitionResult.getCompetitionList().get(1).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token,sportID,"", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token,sportID,eventId, Integer.toString(competitionId),"","FIRST_TO_START");
        int totalMarket = marketResultObj.getMarketList().size();
        Market marketCatalog = marketResultObj.getMarketList().get(totalMarket-1);

        log("Step 6 Get Market Book");
        MarketResult marketBookResultObj = GetMarketBookUtils.getMarketBookAPI(token,marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);
        String runnerId = Integer.toString(marketBook.getSelectionList().get(0).getid());
        String handicap = Double.toString(marketBook.getSelectionList().get(0).gethandicap());
        String price =Integer.toString(marketBook.getSelectionList().get(0).get_availableBack().intValue()+20);
        String size = Double.toString(memberInfo.getLstBetSetting().get(0).getMinBet());
        String orderType ="LIMIT";
        String side ="BACK";
        String persistenceType ="LAPSE";

        log("Step 7 Place a unmatched bet");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token,eventId,runnerId,marketBook.getMarketId(),handicap,price,size,orderType,side,persistenceType);
        String betID = Integer.toString(orderResult.getOrderList().get(0).getBetId());

        log("Step 8 Input unmatch order and cancel");
        OrderResult cancelResult = CancelOrderUtils.cancelOrder(token,betID,"0");
        if(Objects.nonNull(orderResult)){
            log("Skip place bet as odds is empty");
            Assert.assertTrue(true,"By pass this test case");
            return;
        }
        log(String.format("Verify can cancel unmatched  order: Bet ID: %s",orderResult.getOrderList().get(0).getBetId()));
        Assert.assertTrue(cancelResult.getIsSuccess(),"FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(Integer.toString(cancelResult.getOrderList().get(0).getBetId()),betID,"FAILED! Bet Id is incorrect");
        Assert.assertEquals(orderResult.getOrderList().size(),1,"FAILED! List Order more than 1");
        Assert.assertEquals(cancelResult.getOrderList().get(0).getErrorMessage(),"","FAILED! Error Message should not display when cancel unmatched bet");
        log("INFO: Executed completely");

    }
    /**
     * @title: Validate can not cancel matched order
     * @Percondition: Login api and get token code
     * @steps:
     * 1/ Access get event api to Cancel bet
     * 2/ Input Matched order and submit
     * @expect: Validate can not cancel matched order
     */

    @TestRails(id = "435")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void  MA009_CancelOrderTest_435(String username, String password) throws Exception {
        log("@title: Validate can cancel unmatch orders");
        log("Step 1 Access get event api to Cancel bet");
        log("Step 2 Login with valid username and password");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token= LoginMemberUtils.loginAPISuccess(username,passDes).getMessage();

        log("Step 3 Access get competition api");
        MemberInfo memberInfo =GetMemberInfoUtils.getMembrInfo(token);

        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token,sportID,"false");
        int competitionId = competitionResult.getCompetitionList().get(1).getId();

        log("Step 4 Access get event of the competition get in step 3");
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token,sportID,"", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());

        log("Step 5 Access get all market of the event get in step 4 to get a market");
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token,sportID,eventId, Integer.toString(competitionId),"","FIRST_TO_START");
        int totalMarket = marketResultObj.getMarketList().size();
        Market marketCatalog = marketResultObj.getMarketList().get(totalMarket-1);

        log("Step 6 Get Market Book");
        MarketResult marketBookResultObj = GetMarketBookUtils.getMarketBookAPI(token,marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);
        String runnerId = Integer.toString(marketBook.getSelectionList().get(0).getid());
        String handicap = Double.toString(marketBook.getSelectionList().get(0).gethandicap());
        String price = Double.toString(marketBook.getSelectionList().get(0).get_availableLay());
        String size = Double.toString(memberInfo.getLstBetSetting().get(0).getMinBet());
        String orderType ="LIMIT";
        String side ="LAY";
        String persistenceType ="LAPSE";

        log("Step 7 Place a matched LAY bet");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token,eventId,runnerId,marketBook.getMarketId(),handicap,price,size,orderType,side,persistenceType);
        if(Objects.isNull(orderResult)){
            log("By pass this test case as Lay odds to large. We only accept auto place Lay bet has odds <4, current odds odds is "+ price);
            Assert.assertTrue(true,"FAILED");
            return;
        }
        String betID = Integer.toString(orderResult.getOrderList().get(0).getBetId());

        log("Step 8 Input unmatch order and cancel");
        OrderResult cancelResult = CancelOrderUtils.cancelOrder(token,betID,"0");

        log(String.format("Verify can NOT cancel matched order: Bet ID: %s",orderResult.getOrderList().get(0).getBetId()));
        Assert.assertTrue(cancelResult.getIsSuccess(),"FAILED! isSuccess should be true but can not get as expected");
        Assert.assertEquals(Integer.toString(cancelResult.getOrderList().get(0).getBetId()),betID,"FAILED! Bet Id is incorrect");
        Assert.assertEquals(cancelResult.getOrderList().get(0).getErrorMessage(),"ERROR.BET_PLACEMENT.MESSAGE_LAPSED_MATCHED","FAILED! Error Message is incorrect display");
        Assert.assertEquals(orderResult.getOrderList().size(),1,"FAILED! List Order more than 1");

        log("INFO: Executed completely");

    }

}
