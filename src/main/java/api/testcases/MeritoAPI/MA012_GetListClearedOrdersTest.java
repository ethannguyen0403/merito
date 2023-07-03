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

public class MA012_GetListClearedOrdersTest extends BaseCaseAPI {
    /**
     * @title: Validate can get Cleared Orders
     * Precondition: Have place bet order on the account
     * @steps:
     * 1/ Access get event api get list Cleared order
     * 2/ Input valid info and submit
     * @expect: Verify can get api
     */

    @TestRails(id = "437")
    @Parameters({"username", "password"})
    @Test(groups = {"smoke"})
    public void MA011_GetListClearedOrdersTest_437(String username, String password) throws Exception {
        log("@title: Validate can get Cleared Orders");
        log("Step 1 Access get event api get list current order");
        log("Step 2 Get market info");
        String sportID = "1";
        String passDes = StringUtils.decrypt(password);
        String token= LoginMemberUtils.loginAPISuccess(username,passDes).getMessage();
        MemberInfo memberInfo =GetMemberInfoUtils.getMembrInfo(token);

        CompetitionResult competitionResult = GetCompetitionUtils.getCompetitionAPI(token,sportID,"false");
        int competitionId = competitionResult.getCompetitionList().get(1).getId();
        EventListResult resultObj = GetEventsUtils.getEventsAPI(token,sportID,"", Integer.toString(competitionId));
        String eventId = Integer.toString(resultObj.getEventList().get(0).getId());
        MarketResult marketResultObj = GetMarketCatalogUtils.getMarketCatalogAPI(token,sportID,eventId, Integer.toString(competitionId),"","FIRST_TO_START");
        int totalMarket = marketResultObj.getMarketList().size();
        Market marketCatalog = marketResultObj.getMarketList().get(totalMarket-1);
        MarketResult marketBookResultObj = GetMarketBookUtils.getMarketBookAPI(token,marketCatalog.getMarketId());
        Market marketBook = marketBookResultObj.getMarketList().get(0);
        String runnerId = Integer.toString(marketBook.getSelectionList().get(0).getid());
        String handicap = Double.toString(marketBook.getSelectionList().get(0).gethandicap());
        String price =Integer.toString(marketBook.getSelectionList().get(0).get_availableBack().intValue()+20);
        String size = Double.toString(memberInfo.getLstBetSetting().get(0).getMinBet());
        String orderType ="LIMIT";
        String side ="BACK";
        String persistenceType ="LAPSE";

        log("Step 3 Place a unmatched bet and cancel");
        OrderResult orderResult = PlaceOrderUtils.placeOrder(token,eventId,runnerId,marketBook.getMarketId(),handicap,price,size,orderType,side,persistenceType);
        String betId= Integer.toString(orderResult.getOrderList().get(0).getBetId());
        CancelOrderUtils.cancelOrder(token,betId,"0");

        log("Step 4 Get cleared order with input canceled bet");
        OrderResult lstOrderResut= GetClearedOderUtils.getClearedOrder(token,"CANCELLED",betId,marketBook.getMarketId(),eventId,sportID,side);
        Assert.assertTrue(orderResult.getIsSuccess(),"FAILED! isSuccess should be true but cannnot get as expected");
        Assert.assertEquals(Integer.toString(lstOrderResut.getOrderList().get(0).getBetId()),betId,"FAILED! Bet ID matched is incorrect");
        Assert.assertEquals(lstOrderResut.getOrderList().size(),1,"FAILED! Bet ID matched is incorrect");

        log("INFO: Executed completely");
    }

}
