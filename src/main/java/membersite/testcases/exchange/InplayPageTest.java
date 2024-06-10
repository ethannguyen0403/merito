package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import membersite.pages.InPlayPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class InplayPageTest extends BaseCaseTest {

    @TestRails(id = "1010")
    @Test(groups = {"regression"})
    public void FE_InPlay_TC1010() {
        //TODO: Need to re-implement this case
//        log("@title: Validate site load markets as user selected correctly");
//        log("Step 1. Navigate to In-Play page");
//        memberHomePage.navigateInPlay();
//        memberHomePage.collapseLeftMenu();
//        memberHomePage.clickInplayEvent();
//        String eventName = memberHomePage.getActiveEvent();
//        List<String> marketList = memberHomePage.getMarkets();
//
//        log("Verified  1. Market title displays with the corresponding with the left menu");
//        for(int i =0 , n = marketList.size(); i< n; i++)
//        {
//            memberHomePage.clickMarket(marketList.get(i));
//            String eventNameFull = String.format("%s/ %s", eventName,marketList.get(i));
//            Assert.assertEquals(memberHomePage.marketContainerControl.getTitle().trim(), eventNameFull,String.format("ERROR! Click market %s but title display %s", eventNameFull, memberHomePage.marketContainerControl.getTitle()));
//        }

    }

    @TestRails(id = "967")
    @Test(groups = {"smoke", "nolan_stabilize_06.24"})
    public void In_play_TC_967() {
        log("@title: Validate that no Bet Slip and My Bet on In play page");
        log("Precondition 1: Login member account");
        log("Step 1: Click Inplay page");
        InPlayPage inPlayPage = memberHomePage.navigateInPlayPage();
        log("Verify 1: Verify Bet Slip and Mini My bet UI not displayed on Inplay page");
        Assert.assertEquals(inPlayPage.betsSlipContainer.getEmptyBetMessage(),  "", "FAILED! Bet slip UI is displayed");
        log("INFO: Executed completely");
    }

}
