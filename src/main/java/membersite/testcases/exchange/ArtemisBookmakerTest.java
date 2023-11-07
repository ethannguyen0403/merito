package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.MemberConstants.*;
import static common.MemberConstants.HomePage.SPORT_ID;

public class ArtemisBookmakerTest extends BaseCaseTest {
    @TestRails(id = "2058")
    @Test(groups = {"smoke"})
    public void FancyTest_2058() {
        log("@title: Validate correct Forecast displays");
        log("Step 1. Login member site");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);

        log("Step 2 Open Match odds market of any Artemis event");
        BookmakerMarket bookmakerMarket = BetUtils.findOpenBookmakerMarket(SPORT_ID.get(LBL_CRICKET_SPORT), ARTEMIS_BOOKMAKER_CODE, "OPEN");
        if (Objects.isNull(bookmakerMarket)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker");
            Assert.assertTrue(true, "By passed as has no Artemis Bookmaker on all available event");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(bookmakerMarket.getEventName());

        log("Step 3 Added an odds into Bet slip and enter Stake");
        memberHomePage.leftMenu.openBookmakerMarket(ARTEMIS_BOOKMAKER);
        Market market = marketPage.getBookmakerMarketInfo(bookmakerMarket, true);
        if (Objects.isNull(marketPage)) {
            log("DEBUG: Skip as have no event has Artemis Bookmaker market");
            Assert.assertFalse(true, "Cannot place on the market, please check market status");
            return;
        }
        market.getBtnOdd().click();
        String maxBet = BetUtils.getMaxBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);
        marketPage.betsSlipContainer.inputStake(maxBet);

        log("Validate The correct forecast liability with the same formula of Wicket Bookmaker displays");
        List<ArrayList> lstBMBetSlip = marketPage.geBookmakerBetSlipMiniMyBet(true);
        Wager wg = marketPage.defineBookmakerWager(bookmakerMarket, true, Double.valueOf(maxBet));
        Assert.assertEquals(Double.valueOf(String.valueOf(lstBMBetSlip.get(3)).replace(",","")), wg.getProfitWicketBookmakerWager(), "FAILED! Profit/Liability does not show correct");
        log("INFO: Executed completely");
    }
}
