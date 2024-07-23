package membersite.testcases.exchangegame;

import baseTest.BaseCaseTest;
import common.EGConstants;
import membersite.pages.exchangegames.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class EGHomPageTest extends BaseCaseTest {

//
//    /**
//     * @title: Verify all exchange game images are loaded
//     * @precondition: 1. Login member site
//     * @step: Navigate to exchange game
//     * @expect: Verify all exchange game images are loaded
//     */
//    @Test(groups = {"regression"})
//    public void EG_Home_Page_TC001(){
//        log("@title: Verify all exchange game images are loaded");
//        log("Step 1. Navigate to exchange game  ");
//        EGHomePage egHomePage = memberHomePage.openExchangeGame();
//        log("Verify all exchange game images are loaded");
//        log("1.1 Verify Verify Baccarat Game: title, image, turbo, standard");
//        Assert.assertEquals(egHomePage.gcBaccarat.lblGameTitle.getText(), EGConstants.BACCARAT,"FAILED! Baccarat title is incorrect");
//        Assert.assertTrue(egHomePage.gcBaccarat.imgGame.getAttribute("src").contains( "BACCARAT.png"),"FAILED! Baccarat image is incorrect");
//        Assert.assertEquals(egHomePage.gcBaccarat.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! Baccarat Play Turbo button is incorrect");
//        Assert.assertEquals(egHomePage.gcBaccarat.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Baccarat Play Standard button is incorrect");
//
//        log("1.2 Verify Verify Blackjack Game: title, image, turbo, standard");
//        Assert.assertEquals(egHomePage.gcBlackjack.lblGameTitle.getText(), EGConstants.BLACKJACK,"FAILED! Blackjack title is incorrect");
//        Assert.assertTrue(egHomePage.gcBlackjack.imgGame.getAttribute("src").contains("BLACKJACK.png"),"FAILED! Blackjack image is incorrect");
//        Assert.assertEquals(egHomePage.gcBlackjack.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! Blackjack Play Turbo button is incorrect");
//        Assert.assertEquals(egHomePage.gcBlackjack.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Blackjack Play Standard button is incorrect");
//
//        log("1.3 Verify Verify CARD DERBY RACING Game: title, image, turbo, standard");
//        Assert.assertEquals(egHomePage.gcCardRacing.lblGameTitle.getText(), EGConstants.CARD_DERBY_RACING,"FAILED! CARD DERBY RACING title is incorrect");
//        Assert.assertTrue(egHomePage.gcCardRacing.imgGame.getAttribute("src").contains( "CARD_RACER.png"),"FAILED! CARD DERBY RACING image is incorrect");
//        Assert.assertEquals(egHomePage.gcCardRacing.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! CARD DERBY RACING Play Turbo button is incorrect");
//        Assert.assertEquals(egHomePage.gcCardRacing.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! CARD DERBY RACING Play Standard button is incorrect");
//
//        log("1.4 Verify Verify Hilo Game: title, image, turbo, standard");
//        Assert.assertEquals(egHomePage.gcHilo.lblGameTitle.getText(), EGConstants.HI_LO,"FAILED! Hilo title is incorrect");
//        Assert.assertTrue(egHomePage.gcHilo.imgGame.getAttribute("src").contains("HILO.png"),"FAILED! Hilo image is incorrect");
//        Assert.assertEquals(egHomePage.gcHilo.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! Hilo Play Turbo button is incorrect");
//        Assert.assertEquals(egHomePage.gcHilo.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Hilo Play Standard button is incorrect");
//
//        log("1.5 Verify Verify Omahahi Game: title, image, turbo, standard");
//        Assert.assertEquals(egHomePage.gcOmahahi.lblGameTitle.getText(), EGConstants.OMAHA_HI,"FAILED! Omahahi title is incorrect");
//        Assert.assertTrue(egHomePage.gcOmahahi.imgGame.getAttribute("src").contains("OMAHA_POKER.png"),"FAILED! Omahahi image is incorrect");
//        Assert.assertFalse(egHomePage.gcOmahahi.btnTurbo.isDisplayed(),"FAILED! Omahahi Play Turbo button should not display");
//        Assert.assertEquals(egHomePage.gcOmahahi.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Omahahi Play Standard button is incorrect");
//
//        log("1.6 Verify Verify Holem Game: title, image, turbo, standard");
//        Assert.assertEquals(egHomePage.gcHolem.lblGameTitle.getText(), EGConstants.HOLD_EM,"FAILED! Holem title is incorrect");
//        Assert.assertTrue(egHomePage.gcHolem.imgGame.getAttribute("src").contains("POKER.png"),"FAILED! Holem image is incorrect");
//        Assert.assertEquals(egHomePage.gcHolem.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! Holem Play Turbo button is incorrect");
//        Assert.assertEquals(egHomePage.gcHolem.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Holem Play Standard button is incorrect");
//
//        log("INFO: Executed completely");
//    }
//

    /**
     * @title: Verify all exchange game images are loaded
     * @precondition: 1. Login member site
     * @step: Navigate to exchange game
     * @expect: 1/ Verify the menu is loaded correctly
     */
    @TestRails(id = "527")
    @Test(groups = {"smoke", "nolan_stabilize"})
    public void EG_Home_Page_TC527() {
        log("@title: Verify all exchange game images are loaded");
        log("Step 1. Navigate to exchange game  ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();
        log("Verify the menu is loaded correctly");
        Assert.assertTrue(egHomePage.icHomeMenu.isDisplayed(), "FAILED! EG Home menu does not display");
        Assert.assertEquals(egHomePage.menuBaccarat.getText().trim(), EGConstants.BACCARAT_CAP, "FAILED! EG Baccarat menu does not display");
        Assert.assertEquals(egHomePage.menuBlackjack.getText().trim(), EGConstants.BLACKJACK_CAP, "FAILED! EG Blackjack menu does not display");
        Assert.assertEquals(egHomePage.menuCardDerbyRacing.getText().trim(), EGConstants.CARD_DERBY_RACING_CAP, "FAILED! EG Card Derby Racing menu does not display");
        Assert.assertEquals(egHomePage.menuHiLo.getText().trim(), EGConstants.HI_LO_CAP, "FAILED! EG Hi lo menu does not display");
        Assert.assertEquals(egHomePage.menuOmahaHi.getText().trim(), EGConstants.OMAHA_HI_CAP, "FAILED! EG Oma ha hi menu does not display");
        Assert.assertEquals(egHomePage.menuHoldEm.getText().trim(), EGConstants.HOLD_EM_CAP, "FAILED! EG Omahahi menu does not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open all games in the main menu
     * @precondition: 1. Login member site
     * @step: 1. Click on the list of game menu:
     * Baccarat, Blackjace, Racing, Hilo, Omahahi, Holdem
     * @expect: Verify the corresponding page is displayed
     */
    @TestRails(id = "529")
    @Test(groups = {"smoke", "nolan_stabilize"})
    public void EG_Home_Page_TC529() {
        log("@title: Verify can open all games in the main menu");
        log("Step 1. Navigate to exchange game  ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Verify the corresponding page is displayed when clicking on main menu");
        BaccaratPage baccaratPage = (BaccaratPage) egHomePage.navigateGameFromMainMenu(EGConstants.BACCARAT);
        Assert.assertTrue(baccaratPage.getGameHeaderTitle().startsWith("Turbo Exchange Baccarat"), String.format("FAIELD! Baccarat title header is incorrect. Expected %s but found %s", "Turbo Exchange Baccarat", baccaratPage.getGameHeaderTitle()));

        BlackJackPage blcBlackJackPage = (BlackJackPage) egHomePage.navigateGameFromMainMenu(EGConstants.BLACKJACK);
        Assert.assertTrue(blcBlackJackPage.getGameHeaderTitle().startsWith("Turbo Exchange BlackJack"), String.format("FAIELD! Blackjack title header is incorrect. Expected %s but found %s", "Turbo Exchange BlackJack", blcBlackJackPage.getGameHeaderTitle()));

        HiloPage hiloPage = (HiloPage) egHomePage.navigateGameFromMainMenu(EGConstants.HI_LO);
        Assert.assertTrue(hiloPage.getGameHeaderTitle().startsWith("Turbo Exchange Hi Lo"), String.format("FAIELD! Hi Lo title header is incorrect. Expected %s but found %s", "Turbo Exchange Hi Lo", hiloPage.getGameHeaderTitle()));

        HoldemPage holdemPage = (HoldemPage) egHomePage.navigateGameFromMainMenu(EGConstants.HOLD_EM);
        Assert.assertTrue(holdemPage.getGameHeaderTitle().startsWith("Turbo Exchange Hold'em"), String.format("FAIELD! Omahahi title header is incorrect. Expected %s but found %s", "Turbo Exchange Omahahi", holdemPage.getGameHeaderTitle()));

        OmahahiPage omahahiPage = (OmahahiPage) egHomePage.navigateGameFromMainMenu(EGConstants.OMAHA_HI);
        Assert.assertTrue(omahahiPage.getGameHeaderTitle().startsWith("Exchange Omaha Hi"), String.format("FAIELD! Omaha Hi title header is incorrect. Expected %s but found %s", "Exchange Omaha Hi", omahahiPage.getGameHeaderTitle()));

        CardDerbyRacingPage cardDerbyRacingPage = (CardDerbyRacingPage) egHomePage.navigateGameFromMainMenu(EGConstants.CARD_DERBY_RACING);
        Assert.assertEquals(cardDerbyRacingPage.getGameHeaderTitle(), EGConstants.CARD_DERBY_RACING_CAP, String.format("FAIELD!Card Derby Racing title header is incorrect. Expected %s but found %s", EGConstants.CARD_DERBY_RACING_CAP, cardDerbyRacingPage.getGameHeaderTitle()));

        egHomePage = (EGHomePage) egHomePage.navigateGameFromMainMenu("Home");
        Assert.assertTrue(egHomePage.gcBaccarat.isDisplayed(), "FAIELD!Home page is not display");
        Assert.assertTrue(egHomePage.lblPanner.isDisplayed(), "FAIELD! EG Home page banner is not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open all game by when click on the image
     * @precondition: 1. Login member site
     * @step: 1. Click on the list of image game on home page:
     * Baccarat, Blackjace, Racing, Hilo, Omahahi, Holdem
     * @expect: Verify can open all game by when click on the image
     */
    @TestRails(id = "530")
    @Test(groups = {"smoke", "nolan_stabilize"})
    public void EG_Home_Page_TC530() {
        log("@title: Verify can open all game by when click on the image");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Verify the corresponding page is displayed when clicking on main menu");
        BaccaratPage baccaratPage = (BaccaratPage) egHomePage.navigateGameFromImage(EGConstants.BACCARAT);
        Assert.assertTrue(baccaratPage.getGameHeaderTitle().startsWith("Turbo Exchange Baccarat"), String.format("FAIELD! Baccarat title header is incorrect. Expected %s but found %s", "Turbo Exchange Baccarat", baccaratPage.getGameHeaderTitle()));

        egHomePage = (EGHomePage) egHomePage.navigateGameFromMainMenu("Home");
        BlackJackPage blcBlackJackPage = (BlackJackPage) egHomePage.navigateGameFromImage(EGConstants.BLACKJACK);
        Assert.assertTrue(blcBlackJackPage.getGameHeaderTitle().contains("Turbo Exchange BlackJack"), String.format("FAIELD! Blackjack title header is incorrect. Expected %s but found %s", "Turbo Exchange BlackJack", blcBlackJackPage.getGameHeaderTitle()));

        egHomePage = (EGHomePage) egHomePage.navigateGameFromMainMenu("Home");
        HiloPage hiloPage = (HiloPage) egHomePage.navigateGameFromImage(EGConstants.HI_LO);
        Assert.assertTrue(hiloPage.getGameHeaderTitle().contains("Turbo Exchange Hi Lo"), String.format("FAIELD! Hi Lo title header is incorrect. Expected %s but found %s", "Turbo Exchange Hi Lo", hiloPage.getGameHeaderTitle()));

        egHomePage = (EGHomePage) egHomePage.navigateGameFromMainMenu("Home");
        HoldemPage holdemPage = (HoldemPage) egHomePage.navigateGameFromImage(EGConstants.HOLD_EM);
        Assert.assertTrue(holdemPage.getGameHeaderTitle().contains("Turbo Exchange Hold'em"), String.format("FAIELD! Holdem title header is incorrect. Expected %s but found %s", "Turbo Exchange Hold'em", holdemPage.getGameHeaderTitle()));

        egHomePage = (EGHomePage) egHomePage.navigateGameFromMainMenu("Home");
        OmahahiPage omahahiPage = (OmahahiPage) egHomePage.navigateGameFromImage(EGConstants.OMAHA_HI);
        Assert.assertTrue(omahahiPage.getGameHeaderTitle().contains("Exchange Omaha Hi"), String.format("FAIELD! Omaha Hi title header is incorrect. Expected %s but found %s", "Exchange Omaha Hi", omahahiPage.getGameHeaderTitle()));

        egHomePage = (EGHomePage) egHomePage.navigateGameFromMainMenu("Home");
        CardDerbyRacingPage cardDerbyRacingPage = (CardDerbyRacingPage) egHomePage.navigateGameFromImage(EGConstants.CARD_DERBY_RACING);
        Assert.assertEquals(cardDerbyRacingPage.getGameHeaderTitle(), EGConstants.CARD_DERBY_RACING_CAP, String.format("FAIELD!Card Derby Racing title header is incorrect. Expected %s but found %s", EGConstants.CARD_DERBY_RACING_CAP, cardDerbyRacingPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open BACCARAT by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Baccarat
     * @expect: Play Standard baccarat page is correct ly display
     */
    @TestRails(id = "532")
    @Test(groups = {"smoke", "nolan_stabilize"})
    public void EG_Home_Page_TC532() {
        log("@title: Verify can open BACCARAT by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Step 2. Click Play Standard of Baccarat");
        egHomePage.navigateStandarGame(EGConstants.BACCARAT);
        BaccaratPage baccaratPage = new BaccaratPage(_brandname);

        log("Verify 1 Play Standard baccarat page is correctly display ");
        Assert.assertTrue(baccaratPage.getGameHeaderTitle().startsWith("Exchange Baccarat"), String.format("FAIELD! Baccarat title header is incorrect. Expected %s but found %s", "Turbo Exchange Baccarat", baccaratPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open BACCARAT by when click Play turbo
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Turbo of Baccarat
     * @expect: Play Turbo Caccarat page is correct ly display
     */

    @TestRails(id = "531")
    @Test(groups = {"smoke", "nolan_stabilize"})
    public void EG_Home_Page_TC531() {
        log("@title: Verify can open BACCARAT by when click Play turbo");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Step 2. Click Play Turbo of Baccarat");
        BaccaratPage baccaratPage = (BaccaratPage) egHomePage.navigateTurboGame(EGConstants.BACCARAT);

        log("Verify 1 Play Turbo Baccarat page is correctly display ");
        Assert.assertTrue(baccaratPage.getGameHeaderTitle().startsWith("Turbo Exchange Baccarat"), String.format("FAIELD! Baccarat title header is incorrect. Expected %s but found %s", "Turbo Exchange Baccarat", baccaratPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open Blackjack by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Baccarat
     * @expect: Play Standard Blackjack page is correct ly display
     */
    @Test(groups = {"regression"})
    public void EG_Home_Page_TC007() {
        log("@title: Verify can open Blackjack by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Step 2. Click Play Standard of Blackjack");
        BlackJackPage blackJackPage = (BlackJackPage) egHomePage.navigateStandarGame(EGConstants.BLACKJACK);

        log("Verify 1 Play Standard Blackjack page is correctly display ");
        Assert.assertTrue(blackJackPage.getGameHeaderTitle().startsWith("Exchange " + EGConstants.BLACKJACK_CAP), String.format("FAIELD! Blackjack title header is incorrect. Expected %s but found %s", "Turbo Exchange Baccarat", blackJackPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open Hi lo by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Hilo
     * @expect: Play Standard baccarat page is correct ly display
     */
    @Test(groups = {"regression"})
    public void EG_Home_Page_TC008() {
        log("@title: Verify can open Hilo by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Step 2. Click Play Standard of Hilo");
        HiloPage hiloPage = (HiloPage) egHomePage.navigateStandarGame(EGConstants.HI_LO);

        log("Verify 1 Play Standard Hi Lo page is correctly display ");
        Assert.assertTrue(hiloPage.getGameHeaderTitle().startsWith("Exchange " + EGConstants.HI_LO_CAP), String.format("FAIELD! Hilo title header is incorrect. Expected %s but found %s", "Turbo Exchange Hilo", hiloPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open Omahahi by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Omahahi
     * @expect: Play Standard Omahahi page is correct ly display
     */
    @Test(groups = {"regression"})
    public void EG_Home_Page_TC009() {
        log("@title: Verify can open Omahahi by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Step 2. Click Play Standard of Omahahi");
        HoldemPage holdemPage = (HoldemPage) egHomePage.navigateStandarGame(EGConstants.HOLD_EM);

        log("Verify 1 Play Standard Omahahi page is correctly display ");
        Assert.assertTrue(holdemPage.getGameHeaderTitle().startsWith("Exchange " + EGConstants.HOLD_EM_CAP), String.format("FAIELD! Omahahi title header is incorrect. Expected %s but found %s", "Turbo Exchange " + EGConstants.HOLD_EM_CAP, holdemPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open Omahahi by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Omahahi
     * @expect: Play Standard Omahahi page is correct ly display
     */
    @Test(groups = {"regression"})
    public void EG_Home_Page_TC010() {
        log("@title: Verify can open Omahahi by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame();

        log("Step 2. Click Play Standard of Omahahi");
        OmahahiPage omahahiPage = (OmahahiPage) egHomePage.navigateStandarGame(EGConstants.OMAHA_HI);

        log("Verify 1 Play Standard Omahahi page is correctly display ");
        Assert.assertTrue(omahahiPage.getGameHeaderTitle().startsWith("Exchange " + EGConstants.OMAHA_HI_CAP), String.format("FAIELD! Omahahi title header is incorrect. Expected %s but found %s", "Turbo Exchange " + EGConstants.OMAHA_HI_CAP, omahahiPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "528")
    public void EG_Home_Page_TC528() {
        //TODO: implement this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "1011")
    public void EG_Home_Page_TC1011() {
        //TODO: implement this case
        log("INFO: Executed completely");
    }
}
