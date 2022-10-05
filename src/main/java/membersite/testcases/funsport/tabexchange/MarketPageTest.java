package membersite.testcases.funsport.tabexchange;

import membersite.controls.funsport.EventOddContentControl;
import membersite.controls.funsport.OddPageControl;
import membersite.objects.funsport.Odd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.EventPage;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;

import java.util.Objects;

public class MarketPageTest extends BaseCaseMerito {

    /**
     * @title: Validate Soccer Market UI display correct
     * @pre-condition
     *           1. Sign in
     * @steps: 1. Click on Soccer
     *          2. Click on and event on Soccer
     *          3. Click on Match odds market in the left menu
     * @expect: 1. Info display correctly: Event name, market name,
     * Team name
     */
    @Test(groups = {"smoke"})
    public void MarketPageTest_001(){
        log("@title: Validate Soccer event UI display correct");

        log("Step1: 1. Click on Soccer");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step1: 2. Click on and event on Soccer");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage eventPage =page.oddPageControl.clickEvent(odd);
        Odd marketOdds = eventPage.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);
        log("Step 3. Click on Match odds market in the left menu");
        MarketPage marketPage = eventPage.openMarketPage(marketOdds.getEventName());

        log("Verify: 1. Verify Event name is correct");
        Assert.assertEquals(odd.getEventName(),marketPage.lblTitle.getText(),"FAILED! Incorrect event name");

        log("Verify: 2. Verify Market name is correct");
        Assert.assertEquals(marketOdds.getEventName(),marketPage.marketOddControl.lblMarketTitle.getText(),"FAILED! Incorrect event name");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate Tennis Market UI display correct
     * @pre-condition
     *           1. Sign in
     * @steps: 1. Click on Tennis
     * 2. Click on and event on Tennis
     * 3. Click on Match odds market in the left menu
     * @expect: 1. Info display correctly: Event name, market name,
     * Team name
     */
    @Test(groups = {"smoke"})
    public void MarketPageTest_002(){
        log("@title: Validate Tennis Market UI display correct");

        log("Step1: 1. Click on Tennis");
        SportPage page = memberHomePage.navigateTennis();

        log("Step1: 2. Click on and event on Tennis");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }

        log("Step 2: Click on Event Name");
        EventPage eventPage =page.oddPageControl.clickEvent(odd);
        Odd marketOdds = eventPage.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);

        log("Step 3. Click on Match odds market in the left menu");
        MarketPage marketPage = eventPage.openMarketPage(marketOdds.getEventName());

        log("Verify: 1. Verify Event name is correct");
        Assert.assertEquals(odd.getEventName(),marketPage.lblTitle.getText(),"FAILED! Incorrect event name");

        log("Verify: 2. Verify Market name is correct");
        Assert.assertEquals(marketOdds.getEventName(),marketPage.marketOddControl.lblMarketTitle.getText(),"FAILED! Incorrect event name");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate Cricket Market UI display correct
     * @pre-condition
     *           1. Sign in
     * @steps: 1. Click on Cricket
     * 2. Click on and event on Cricket
     * 3. Click on Match odds market in the left menu
     * @expect: 1. Info display correctly: Event name, market name,
     * Team name
     */
    @Test(groups = {"smoke"})
    public void MarketPageTest_003(){
        log("@title: Validate Cricket Market UI display correct");

        log("Step1: 1. Click on Cricket");
        SportPage page = memberHomePage.navigateCricket();

        log("Step1: 2. Click on and event on Cricket");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        log("Step 2: Click on Event Name");
        EventPage eventPage =page.oddPageControl.clickEvent(odd);
        Odd marketOdds = eventPage.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);
        log("Step 3. Click on Match odds market in the left menu");

        MarketPage marketPage = eventPage.openMarketPage(marketOdds.getEventName());

        log("Verify: 1. Verify Event name is correct");
        Assert.assertEquals(odd.getEventName(),marketPage.lblTitle.getText(),"FAILED! Incorrect event name");

        log("Verify: 2. Verify Market name is correct");
        Assert.assertEquals(marketOdds.getEventName(),marketPage.marketOddControl.lblMarketTitle.getText(),"FAILED! Incorrect event name");

        log("INFO: Executed completely");
    }

}
