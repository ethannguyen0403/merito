package membersite.testcases.all.afterlogin.exchange;

import baseTest.BaseCaseMerito;
import com.paltech.utils.DateUtils;
import membersite.common.FEMemberConstants;
import membersite.objects.AccountBalance;
import membersite.pages.all.tabexchange.*;
import membersite.pages.all.tabexchange.components.popups.MyMarketPopup;
import membersite.pages.all.tabexchangegame.EGHomePage;
import membersite.pages.all.tablivedealer.AsianRoomPage;
import membersite.pages.all.tablivedealer.EuropeanRoomPage;
import membersite.pages.all.tablivedealer.components.LiveDealer;
import membersite.pages.all.tablotteryslot.LotterySlotsPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Objects;

public class InplayPageTest extends BaseCaseMerito {

    @TestRails(id="1010")
    @Test (groups = {"regression1"})
    public void FE_InPlay_TC1010() {
        log("@title: Validate site load markets as user selected correctly");
        log("Step 1. Navigate to In-Play page");
        memberHomePage.navigateInPlay();
        memberHomePage.collapseLeftMenu();
        memberHomePage.clickInplayEvent();
        String eventName = memberHomePage.getActiveEvent();
        List<String> marketList = memberHomePage.getMarkets();

        log("Verified  1. Market title displays with the corresponding with the left menu");
        for(int i =0 , n = marketList.size(); i< n; i++)
        {
            memberHomePage.clickMarket(marketList.get(i));
            String eventNameFull = String.format("%s/ %s", eventName,marketList.get(i));
            Assert.assertEquals(memberHomePage.marketContainerControl.getTitle().trim(), eventNameFull,String.format("ERROR! Click market %s but title display %s", eventNameFull, memberHomePage.marketContainerControl.getTitle()));
        }

    }

}
