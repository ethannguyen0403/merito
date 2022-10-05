package membersite.testcases.all.beforelogin;

import membersite.common.FEMemberConstants;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.beforelogin.popups.UnderageGamblingPopup;
import membersite.pages.all.tabexchange.InPlayPage;
import membersite.pages.all.tabexchange.SoccerPage;
import membersite.pages.all.tablivedealer.AsianRoomPage;
import membersite.pages.all.tablivedealer.EuropeanRoomPage;
import membersite.pages.all.tablotteryslot.LotterySlotsPage;
import baseTest.BaseCaseMerito;

import java.util.Objects;

public class HeaderSectionTest extends BaseCaseMerito {
    /**
     * @title: Validate Before Login Page header
     * @steps     1. Access member site
     * @expect:   1. Verify Header Section UI Correctly
     *          -       Left menu icon, Logo, Time zone, Login button
     */
    @Test (groups = {"smoke1"})
    @Parameters({"brandname","username","password"})
    public void FE_HeaderSection_0010(String brandname,String username, String password){
        log("@title: Validate Before Login Page header");

        log("Step 1: Access member site");
        log("Verify: 1. Verify Header Section UI Correctly\n" +
                "- Left menu icon, Logo, Time zone, Login button");
       landingPage.login(brandname,username,password,true);

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate Before Login Page header
     * @steps     1. Access member site
     * @expect:   1. Verify Header Section UI Correctly
     *          -       Left menu icon, Logo, Time zone, Login button
     */
    @Test (groups = {"smoke"})
    public void FE_HeaderSection_001(){
        log("@title: Validate Before Login Page header");

        log("Step 1: Access member site");
        log("Verify: 1. Verify Header Section UI Correctly\n" +
                "- Left menu icon, Logo, Time zone, Login button");
        Assert.assertTrue(memberHomePage.btnLogin.isDisplayed(),String.format( "ERROR: Expected display Login button but found %s ", memberHomePage.btnLogin.isDisplayed()));
        Assert.assertTrue(memberHomePage.satHeaderControl.imgLogo.isDisplayed(),String.format( "ERROR: Expected display SAT Logo but found %s ", memberHomePage.satHeaderControl.imgLogo.isDisplayed()));
        Assert.assertTrue(memberHomePage.imgLeftMenu.isDisplayed(),String.format( "ERROR: Expected display Left menu icon button but found %s ", memberHomePage.imgLeftMenu.isDisplayed()));
        Assert.assertTrue(memberHomePage.lblTimezone.isDisplayed(),String.format( "ERROR: Expected display timezone but found %s ", memberHomePage.lblTimezone.isDisplayed()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate there is no Exchange Game product for Before login page
     * @steps:   1. Access member site
     * @expect:  1.Verify There is no Exchange Game product
     */
    @Test (groups = {"smoke"})
    public void FE_HeaderSection_002(){
        log("@title: Validate there is no Exchange Game product for Before login page");
        log("Step 1. Access member site");
        log("Verify  1.Verify There is no Exchange Game product");
        Assert.assertFalse(memberHomePage.tabExchangeGames.isDisplayed(), "ERROR! Expected is Exchange Game menu not display in Befor Login but found");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Can open Exchange, Live Dealer, Lottery & Slot product
     * @pre-condition  1. Access member site
     * @steps:      1. Click  on Live Dealer > Asian room
     *              2. Click on Live Dealer > European room
     *              3. Click on Lottery & Slot
     *              4. Click on Exchange
     * @expect: 1. The product displays accordingly
     */
    @Test (groups = {"smoke"})
    public void FE_HeaderSection_003(){
        log("@title: Validate Can open Exchange, Live Dealer, Lottery & Slot product");
        log("Step 1. Click  on Live Dealer > Asian room");
        log("Verify 1.  Asian room page display");
        AsianRoomPage page = memberHomePage.switchLiveDealerTab().switchAsianRoomTab();
        Assert.assertEquals(page.getPageUrl(),String.format(FEMemberConstants.AsianLiveDealer.URL,memberLoginURL),
                String.format("ERROR! Expected URL is %s but found %s", String.format(FEMemberConstants.AsianLiveDealer.URL,memberLoginURL),page.getPageUrl()));

        log("Step 2. Click on Live Dealer > European room");
        log("Verify 2.  European room page display");
        EuropeanRoomPage euroRomePage  = page.switchLiveDealerTab().switchEuropeansRoomTab();
        Assert.assertEquals(euroRomePage.getPageUrl(),String.format(FEMemberConstants.EuropeanRoom.URL,memberLoginURL),
                String.format("ERROR! Expected URL is %s but found %s", String.format(FEMemberConstants.EuropeanRoom.URL,memberLoginURL),euroRomePage.getPageUrl()));

        log("Step 3. Click on Lottery & Slot");
        log("Verify 3. Lottery & Slot page display");
        LotterySlotsPage lsPage = euroRomePage.switchLotterySlotsTab();
        lsPage.menuGameGroup.isDisplayed();
        Assert.assertEquals(lsPage.getPageUrl(),String.format(FEMemberConstants.LotterySlot.URL,memberLoginURL),
                String.format("ERROR! Expected  URL is %s but found %s", String.format(FEMemberConstants.LotterySlot.URL,memberLoginURL),lsPage.getPageUrl()));

        log("Step 4. Click on Exchange");
        log("Verify 4. Exchange Home page display");
        memberHomePage = lsPage.switchExchangeTab();
        Assert.assertTrue(memberHomePage.getPageUrl().contains(FEMemberConstants.HomePage.URL),
                String.format("ERROR! Expected  URL is %s but found %s", String.format(FEMemberConstants.HomePage.URL,memberLoginURL),memberHomePage.getPageUrl()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate odds button on Home Page is un-clickable
     * @steps:  1. Access member site
     *          2. In Home page, click on any odds in the list event     *
     * @expect: 1. Odds is unclick able
     */
    @Test (groups = {"smoke"})
    public void FE_HeaderSection_004() {
        log("@title:Validate odds button on Home , In play page is unclick able");
        log("@Step 1. Access member site");
        log("@Step 2. In Home page, click on any odds in the list event ");
        Event event = memberHomePage.eventContainerControl.getEvent(false,false,3,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        log("@Verify:  Odds is un-click able");
        Assert.assertTrue(memberHomePage.eventContainerControl.isOddsUnclickable(event.getEventName()),"ERROR! Can click on odds in Home page");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate odds button on In-Play Page is un-clickable
     * @steps:  1. Access member site
     *          2. In  In-Play page, click on any odds in the list event     *
     * @expect: 1. Odds is un-clickable
     */
    @Test (groups = {"smoke"})
    public void FE_HeaderSection_007() {
        log("@title:Validate odds button on In-Play Page is un-clickable");
        log("@Step 1. Access member site");
        log("@Step 2. In  In-Play page, click on any odds in the list event  ");
        InPlayPage page = memberHomePage.navigateSportMenu(FEMemberConstants.HomePage.SPORT_ID.get("In-Play"),InPlayPage.class);
        Event event = memberHomePage.eventContainerControl.getEvent(false,false,3,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        log("@Verify:  Odds is un-clickable");
        Assert.assertTrue(page.eventContainerControl.isOddsUnclickable(event.getEventName()),"ERROR! Can click on odds in Inplay page");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate odds button on Soccer page is un-clickable
     * @steps:  1. Access member site
     *          2. In Soccer page, click on any odds in the list event
     * @expect: 1. Odds is un-clickable
     */
    @Test (groups = {"smoke"})
    public void FE_HeaderSection_008() {
        log("@title:Validate odds button on Soccer page is un-clickable");
        log("@Step 1. Access member site");
        log("@Step 2. In Soccer page, click on any odds in the list event  ");
        SoccerPage page = memberHomePage.navigateSportMenu(FEMemberConstants.HomePage.SPORT_ID.get("Soccer"),SoccerPage.class);
        Event event = page.eventContainerControl.getEvent(false,false,3,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        log("@Verify:  Odds is un-click able");
        Assert.assertTrue(page.eventContainerControl.isOddsUnclickable(event.getEventName()),"ERROR! Can click on odds in Soccer page");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Event link works
     * @steps:  1. Access member site
     *          2. Click on any event
     * @expect: Match Odds market is active by default
     */
    @Test (groups = {"smoke"})
    public void FE_HeaderSection_005() {
        log("@title: Validate Event link works");
        log("@Step 1. Access member site");
        log("@Step 2. Click on any event");
        Event event = memberHomePage.eventContainerControl.getEvent(false,false,3,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        memberHomePage.clickEvent(event);
        Market market = memberHomePage.marketContainerControl.getMarket(event,1,true);

        log("Verify: Match Odds market is active by default");
        String marketName = memberHomePage.marketContainerControl.getTitle(false);
        String fullTitle = memberHomePage.marketContainerControl.getTitle();
        String expectedTitle = String.format("%s/ %s",market.getEventName(), market.getMarketName());
        Assert.assertEquals(fullTitle,expectedTitle, String.format("ERROR! Event Title expected is %s but found %s", fullTitle,expectedTitle));
        Assert.assertEquals(marketName,"Match Odds",String.format("ERROR! Default is not Match Odds market, actual is %s", market));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate login popup display when clicking on odds button in market page
     * @steps:      1. Access member site
     *              2. Active any market page and click on odds
     * @expect:     1 Verify login popup display
     */
    @Test (groups = {"smoke"})
    public void FE_HeaderSection_006() {
        log("@title: Validate login popup display when clicking on odds button in market page");

        log("@Step 1. Access member site");
        log("@Step 2. Active any market page and click on odds");
        Event event = memberHomePage.eventContainerControl.getEvent(false,false,3,1);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        memberHomePage.clickEvent(event);
        Market market = memberHomePage.marketContainerControl.getMarket(event,1,true);

        UnderageGamblingPopup gamblingPopup = memberHomePage.marketContainerControl.clickOdd(market);
        log("@Verify: login popup display");
        Assert.assertTrue(gamblingPopup.popupUnderageGambling.isDisplayed(), "ERROR! Underage Gambling Popup does not display");
    }
}
