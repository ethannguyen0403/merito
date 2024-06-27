package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import membersite.objects.sat.Order;
import membersite.pages.InPlayPage;
import org.testng.Assert;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Objects;

import static common.MemberConstants.LBL_BACK_TYPE;
import static common.MemberConstants.LBL_SOCCER_SPORT;

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
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void In_play_TC_967() {
        log("@title: Validate that no Bet Slip and My Bet on In play page");
        log("Precondition 1: Login member account");
        log("Step 1: Click Inplay page");
        InPlayPage inPlayPage = memberHomePage.navigateInPlayPage();
        log("Verify 1: Verify Bet Slip and Mini My bet UI not displayed on Inplay page");
        Assert.assertEquals(inPlayPage.betsSlipContainer.getEmptyBetMessage(),  "", "FAILED! Bet slip UI is displayed");
        log("INFO: Executed completely");
    }


    @TestRails(id = "946")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void FE_InPlay_TC946() {
        log("@title: Validate that a selected odd at HOME-BACK displays correct data both Odd page and on Bet Slip in In-Play Page");
        log("Precondition 1: Login member site");
        log("Step 1: Navigate to In-Play page");
        InPlayPage inPlayPage = memberHomePage.navigateInPlayPage();
        log("Step 2: Click an odd without empty at Home team and Back type");
        Event event = inPlayPage.getEventRandom(true);
        if (Objects.isNull(event)) {
            throw new SkipException("DEBUG: There is no event available");
        }
        MarketPage marketPage = inPlayPage.clickEventName(event.getEventName());
        Market marketHomeBack = marketPage.marketOddControl.getMarket(event, 1, true);
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        marketHomeBack.getBtnOdd().click();
        log("Step 3: Input stake and calculate Profit and Liability");
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketHomeBack, betOrder);
        log("INFO: Executed completely");
    }

    @TestRails(id = "947")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void FE_InPlay_TC947() {
        log("@title: Validate that a selected odd at HOME-LAY displays correct data both Odd page and on Bet Slip in In-Play Page");
        log("Precondition 1: Login member site");
        log("Step 1: Navigate to In-Play page");
        InPlayPage inPlayPage = memberHomePage.navigateInPlayPage();
        log("Step 2: Click an odd without empty at HOME team and LAY type");
        Event event = inPlayPage.getEventRandom(true);
        if (Objects.isNull(event)) {
            throw new SkipException("DEBUG: There is no event available");
        }
        MarketPage marketPage = inPlayPage.clickEventName(event.getEventName());
        Market marketHomeBack = marketPage.marketOddControl.getMarket(event, 1, false);
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        marketHomeBack.getBtnOdd().click();
        log("Step 3: Input stake and calculate Profit and Liability");
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketHomeBack, betOrder);
        log("INFO: Executed completely");
    }

    @TestRails(id = "948")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void FE_InPlay_TC948() {
        log("@title: Validate that a selected odd at AWAY-BACK displays correct data both Odd page and on Bet Slip in In-Play Page ");
        log("Precondition 1: Login member site");
        log("Step 1: Navigate to In-Play page");
        InPlayPage inPlayPage = memberHomePage.navigateInPlayPage();
        log("Step 2: Click an odd without empty at AWAY team and BACK type");
        Event event = inPlayPage.getEventRandom(true);
        if (Objects.isNull(event)) {
            throw new SkipException("DEBUG: There is no event available");
        }
        MarketPage marketPage = inPlayPage.clickEventName(event.getEventName());
        Market marketHomeBack = marketPage.marketOddControl.getMarket(event, 2, true);
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        marketHomeBack.getBtnOdd().click();
        log("Step 3: Input stake and calculate Profit and Liability");
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketHomeBack, betOrder);
        log("INFO: Executed completely");
    }

    @TestRails(id = "949")
    @Test(groups = {"regression", "MER.Implementation.V.1.0"})
    public void FE_InPlay_TC949() {
        log("@title: Validate that a selected odd at AWAY-LAY displays correct data both Odd page and on Bet Slip in In-Play Page");
        log("Precondition 1: Login member site");
        log("Step 1: Navigate to In-Play page");
        InPlayPage inPlayPage = memberHomePage.navigateInPlayPage();
        log("Step 2: Click an odd without empty at AWAY team and LAY type");
        Event event = inPlayPage.getEventRandom(true);
        if (Objects.isNull(event)) {
            throw new SkipException("DEBUG: There is no event available");
        }
        MarketPage marketPage = inPlayPage.clickEventName(event.getEventName());
        Market marketHomeBack = marketPage.marketOddControl.getMarket(event, 2, false);
        String minBet = BetUtils.getMinBet(LBL_SOCCER_SPORT, LBL_BACK_TYPE);
        marketHomeBack.getBtnOdd().click();
        log("Step 3: Input stake and calculate Profit and Liability");
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketHomeBack, betOrder);
        log("INFO: Executed completely");
    }
    }
