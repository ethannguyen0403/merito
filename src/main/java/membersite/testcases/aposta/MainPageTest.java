package membersite.testcases.aposta;

import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.SportPage;
import membersite.pages.all.tabexchangegame.EGHomePage;
import membersite.pages.all.tablivedealer.components.LiveDealer;
import membersite.pages.aposta.MainPage;
import baseTest.BaseCaseMerito;

public class MainPageTest extends BaseCaseMerito {
    /**
     * @title: Validate Verify banner, sport image is correctly display on Home Page
     * @steps:   1. Navigate to Home page     *
     * @expect:
     * 1. Verify banner, sport image is correctly display
     */

    @Test (groups = {"smoke"})
    public void Login_001() {
        log("@title: Validate Verify banner, sport image is correctly display on Home Page");
        log("Step 1: Navigate to Home page");
        MainPage mainPage = memberHomePage.apHeaderControl.navigateSportMenu("Home",MainPage.class);

        log("Veriry 1. Verify banner, sport image is correctly display");
        Assert.assertTrue(mainPage.bannerImg.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.imgCricket.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.imgExchangeGame.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.imgLiveDealer.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.imgCasio.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.bannerImg.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.imgSuperNowa.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.imgFootBall.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.imgSlot.isDisplayed(),"FAILED! Banner image is not display");
        Assert.assertTrue(mainPage.imgTeenpati.isDisplayed(),"FAILED! Banner image is not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to Cricket when clicking on Cricket
     * @steps:   1. Navigate to Home page   
     * 2: Click on cricket image
     * @expect:
     * 1. Verify banner, sport image is correctly display
     */

    @Test (groups = {"smoke"})
    public void Login_003() {
        log("@title: Validate can navigate to Tennis when clicking on Tennis");
        log("Step 1: Navigate to Home page");
        MainPage mainPage = memberHomePage.apHeaderControl.navigateSportMenu("Home",MainPage.class);

        log("Step 2: Click on Tennis image");
        SportPage sportPage = mainPage.clickImage("Tennis",SportPage.class);

        log("Veriry 1. Can open Tennis page");
        Assert.assertEquals(sportPage.eventContainerControl.getSportHeader(),"Tennis Highlights");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to Football when clicking on Football
     * @steps:   1. Navigate to Home page   
     * 2: Click on cricket image
     * @expect:
     * 1. Verify banner, sport image is correctly display
     */

    @Test (groups = {"smoke"})
    public void Login_002() {
        log("@title: Validate can navigate to Cricket when clicking on Cricket");
        log("Step 1: Navigate to Home page");
        MainPage mainPage = memberHomePage.apHeaderControl.navigateSportMenu("Home",MainPage.class);

        log("Step 2: Click on Cricket image");
        SportPage sportPage = mainPage.clickImage("Cricket",SportPage.class);

        log("Veriry 1. Can open Soccer page");
        Assert.assertEquals(sportPage.eventContainerControl.getSportHeader(),"Cricket Highlights");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate can navigate to Exchange Games when clicking on Exchange Games
     * @steps:   1. Navigate to Home page   
     * 2: Click on cricket image
     * @expect:
     * 1. Verify banner, sport image is correctly display
     */

    @Test (groups = {"smoke"})
    public void Login_004() {
        log("@title: Validate can navigate to Exchange Games when clicking on Exchange Games");
        log("Step 1: Navigate to Home page");
        MainPage mainPage = memberHomePage.apHeaderControl.navigateSportMenu("Home",MainPage.class);

        log("Step 2: Click on cricket image");
        EGHomePage egHomePage = mainPage.clickImage("Exchange Games",EGHomePage.class);

        log("Veriry 1. Can open Exchange Games page");
        Assert.assertEquals(egHomePage.gcBaccarat.lblGameTitle.getText(),"BACCARAT","Exchange Games page does not display");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate can navigate to Live Dealer when clicking on Live Dealer
     * @steps:   1. Navigate to Home page
     * 2: Click on Live Dealer image
     * @expect:
     * 1. Verify can open Live Dealer page
     */

    @Test (groups = {"smoke"})
    public void Login_005() {
        log("@title: Validate can navigate to Live Dealer when clicking on Live Dealer");
        log("Step 1: Navigate to Home page");
        MainPage mainPage = memberHomePage.apHeaderControl.navigateSportMenu("Home",MainPage.class);

        log("Step 2: Click on Live Dealer image");
        LiveDealer liveDealer = mainPage.clickImage("Live Dealer",LiveDealer.class);

        log("Verify 1. Can open Live Dealer page");
        Assert.assertTrue(liveDealer.getPageUrl().contains("home/live-dealer/ezugi"),"Live Dealer page does not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can navigate to Solt when clicking on Slot
     * @steps:   1. Navigate to Home page
     * 2: Click on Slot image
     * @expect:
     * 1. Verify can open Slot page
     */

    @Test (groups = {"smoke"})
    public void Login_006() {
        log("@title: Validate can navigate to Slot when clicking on Slot");
        log("Step 1: Navigate to Home page");
        MainPage mainPage = memberHomePage.apHeaderControl.navigateSportMenu("Home",MainPage.class);

        log("Step 2: Click on Slot image");
        LiveDealer liveDealer = mainPage.clickImage("Slot",LiveDealer.class);

        log("Verify 1. Can open Live Dealer page");
        Assert.assertTrue(liveDealer.getPageUrl().contains("home/lottery-slots"),"Live Dealer page does not display");

        log("INFO: Executed completely");
    }
}
