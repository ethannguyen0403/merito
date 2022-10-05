package membersite.testcases.all.afterlogin.exchangegame;

import membersite.common.EGConstants;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchangegame.*;
import baseTest.BaseCaseMerito;

public class EGHomPageTest extends BaseCaseMerito {


    /**
     * @title: Verify all exchange game images are loaded
     * @precondition: 1. Login member site
     * @step: Navigate to exchange game
     * @expect: Verify all exchange game images are loaded
     */
    @Test(groups = {"smoke5"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC001(String skinName){
        log("@title: Verify all exchange game images are loaded");
        log("Step 1. Navigate to exchange game  ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);
        log("Verify all exchange game images are loaded");
        log("1.1 Verify Verify Baccarat Game: title, image, turbo, standard");
        Assert.assertEquals(egHomePage.gcBaccarat.lblGameTitle.getText(), EGConstants.BACCARAT,"FAILED! Baccarat title is incorrect");
        Assert.assertTrue(egHomePage.gcBaccarat.imgGame.getAttribute("src").contains( "BACCARAT.png"),"FAILED! Baccarat image is incorrect");
        Assert.assertEquals(egHomePage.gcBaccarat.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! Baccarat Play Turbo button is incorrect");
        Assert.assertEquals(egHomePage.gcBaccarat.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Baccarat Play Standard button is incorrect");

        log("1.2 Verify Verify Blackjack Game: title, image, turbo, standard");
        Assert.assertEquals(egHomePage.gcBlackjack.lblGameTitle.getText(), EGConstants.BLACKJACK,"FAILED! Blackjack title is incorrect");
        Assert.assertTrue(egHomePage.gcBlackjack.imgGame.getAttribute("src").contains("BLACKJACK.png"),"FAILED! Blackjack image is incorrect");
        Assert.assertEquals(egHomePage.gcBlackjack.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! Blackjack Play Turbo button is incorrect");
        Assert.assertEquals(egHomePage.gcBlackjack.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Blackjack Play Standard button is incorrect");

        log("1.3 Verify Verify CARD DERBY RACING Game: title, image, turbo, standard");
        Assert.assertEquals(egHomePage.gcCardRacing.lblGameTitle.getText(), EGConstants.CARD_DERBY_RACING,"FAILED! CARD DERBY RACING title is incorrect");
        Assert.assertTrue(egHomePage.gcCardRacing.imgGame.getAttribute("src").contains( "CARD_RACER.png"),"FAILED! CARD DERBY RACING image is incorrect");
        Assert.assertEquals(egHomePage.gcCardRacing.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! CARD DERBY RACING Play Turbo button is incorrect");
        Assert.assertEquals(egHomePage.gcCardRacing.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! CARD DERBY RACING Play Standard button is incorrect");

        log("1.4 Verify Verify Hilo Game: title, image, turbo, standard");
        Assert.assertEquals(egHomePage.gcHilo.lblGameTitle.getText(), EGConstants.HI_LO,"FAILED! Hilo title is incorrect");
        Assert.assertTrue(egHomePage.gcHilo.imgGame.getAttribute("src").contains("HILO.png"),"FAILED! Hilo image is incorrect");
        Assert.assertEquals(egHomePage.gcHilo.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! Hilo Play Turbo button is incorrect");
        Assert.assertEquals(egHomePage.gcHilo.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Hilo Play Standard button is incorrect");

        log("1.5 Verify Verify Omahahi Game: title, image, turbo, standard");
        Assert.assertEquals(egHomePage.gcOmahahi.lblGameTitle.getText(), EGConstants.OMAHA_HI,"FAILED! Omahahi title is incorrect");
        Assert.assertTrue(egHomePage.gcOmahahi.imgGame.getAttribute("src").contains("OMAHA_POKER.png"),"FAILED! Omahahi image is incorrect");
        Assert.assertFalse(egHomePage.gcOmahahi.btnTurbo.isDisplayed(),"FAILED! Omahahi Play Turbo button should not display");
        Assert.assertEquals(egHomePage.gcOmahahi.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Omahahi Play Standard button is incorrect");

        log("1.6 Verify Verify Holem Game: title, image, turbo, standard");
        Assert.assertEquals(egHomePage.gcHolem.lblGameTitle.getText(), EGConstants.HOLD_EM,"FAILED! Holem title is incorrect");
        Assert.assertTrue(egHomePage.gcHolem.imgGame.getAttribute("src").contains("POKER.png"),"FAILED! Holem image is incorrect");
        Assert.assertEquals(egHomePage.gcHolem.btnTurbo.getText(), EGConstants.PLAY_TURBO,"FAILED! Holem Play Turbo button is incorrect");
        Assert.assertEquals(egHomePage.gcHolem.btnStandard.getText(), EGConstants.PLAY_STANDARD,"FAILED! Holem Play Standard button is incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify all exchange game images are loaded
     * @precondition: 1. Login member site
     * @step: Navigate to exchange game
     * @expect: 1/ Verify the menu is loaded correctly
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC002(String skinName){
        log("@title: Verify all exchange game images are loaded");
        log("Step 1. Navigate to exchange game  ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);
        log("Verify the menu is loaded correctly");
        Assert.assertTrue(egHomePage.icHomeMenu.isDisplayed(),"FAILED! EG Home menu does not display");
        Assert.assertEquals(egHomePage.menuBaccarat.getText().trim(), EGConstants.BACCARAT_CAP,"FAILED! EG Baccarat menu does not display");
        Assert.assertEquals(egHomePage.menuBlackjack.getText().trim(),EGConstants.BLACKJACK_CAP,"FAILED! EG Blackjack menu does not display");
        Assert.assertEquals(egHomePage.menuCardDerbyRacing.getText().trim(), EGConstants.CARD_DERBY_RACING_CAP,"FAILED! EG Card Derby Racing menu does not display");
        Assert.assertEquals(egHomePage.menuHiLo.getText().trim(), EGConstants.HI_LO_CAP,"FAILED! EG Hi lo menu does not display");
        Assert.assertEquals(egHomePage.menuOmahaHi.getText().trim(), EGConstants.OMAHA_HI_CAP,"FAILED! EG Oma ha hi menu does not display");
        Assert.assertEquals(egHomePage.menuHoldEm.getText().trim(), EGConstants.HOLD_EM_CAP,"FAILED! EG Omahahi menu does not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open all games in the main menu
     * @precondition: 1. Login member site
     * @step: 1. Click on the list of game menu:
     * Baccarat, Blackjace, Racing, Hilo, Omahahi, Holdem
     * @expect: Verify the corresponding page is displayed
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC003(String skinName){
        log("@title: Verify can open all games in the main menu");
        log("Step 1. Navigate to exchange game  ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Verify the corresponding page is displayed when clicking on main menu");
        BaccaratPage baccaratPage = egHomePage.navigateGameFromMainMenu(EGConstants.BACCARAT,BaccaratPage.class);
        Assert.assertTrue(baccaratPage.getGameHeaderTitle().startsWith("Turbo Exchange Baccarat"),String.format("FAIELD! Baccarat title header is incorrect. Expected %s but found %s","Turbo Exchange Baccarat",baccaratPage.getGameHeaderTitle()));

        BlackJackPage blcBlackJackPage= egHomePage.navigateGameFromMainMenu(EGConstants.BLACKJACK,BlackJackPage.class);
        Assert.assertTrue(blcBlackJackPage.getGameHeaderTitle().startsWith("Turbo Exchange BlackJack"),String.format("FAIELD! Blackjack title header is incorrect. Expected %s but found %s","Turbo Exchange BlackJack",blcBlackJackPage.getGameHeaderTitle()));

        HiloPage hiloPage = egHomePage.navigateGameFromMainMenu(EGConstants.HI_LO,HiloPage.class);
        Assert.assertTrue(hiloPage.getGameHeaderTitle().startsWith("Turbo Exchange Hi Lo"),String.format("FAIELD! Hi Lo title header is incorrect. Expected %s but found %s","Turbo Exchange Hi Lo",hiloPage.getGameHeaderTitle()));

        HoldemPage holdemPage = egHomePage.navigateGameFromMainMenu(EGConstants.HOLD_EM,HoldemPage.class);
        Assert.assertTrue(holdemPage.getGameHeaderTitle().startsWith("Turbo Exchange Hold'em"),String.format("FAIELD! Omahahi title header is incorrect. Expected %s but found %s","Turbo Exchange Omahahi",holdemPage.getGameHeaderTitle()));

        OmahahiPage omahahiPage = egHomePage.navigateGameFromMainMenu(EGConstants.OMAHA_HI,OmahahiPage.class);
        Assert.assertTrue(omahahiPage.getGameHeaderTitle().startsWith("Exchange Omaha Hi"),String.format("FAIELD! Omaha Hi title header is incorrect. Expected %s but found %s","Exchange Omaha Hi",omahahiPage.getGameHeaderTitle()));

        CardDerbyRacingPage cardDerbyRacingPage = egHomePage.navigateGameFromMainMenu(EGConstants.CARD_DERBY_RACING,CardDerbyRacingPage.class);
        Assert.assertEquals(cardDerbyRacingPage.getGameHeaderTitle(),EGConstants.CARD_DERBY_RACING_CAP,String.format("FAIELD!Card Derby Racing title header is incorrect. Expected %s but found %s",EGConstants.CARD_DERBY_RACING_CAP,cardDerbyRacingPage.getGameHeaderTitle()));

        egHomePage  = egHomePage.navigateGameFromMainMenu("Home",EGHomePage.class);
        Assert.assertTrue(egHomePage.gcBaccarat.isDisplayed(),"FAIELD!Home page is not display");
        Assert.assertTrue(egHomePage.lblPanner.isDisplayed(),"FAIELD! EG Home page banner is not display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open all game by when click on the image
     * @precondition: 1. Login member site
     * @step: 1. Click on the list of image game on home page:
     * Baccarat, Blackjace, Racing, Hilo, Omahahi, Holdem
     * @expect: Verify can open all game by when click on the image
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC004(String skinName){
        log("@title: Verify can open all game by when click on the image");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Verify the corresponding page is displayed when clicking on main menu");
        BaccaratPage baccaratPage = egHomePage.navigateGameFromImage(EGConstants.BACCARAT,BaccaratPage.class);
        Assert.assertTrue(baccaratPage.getGameHeaderTitle().startsWith("Turbo Exchange Baccarat"),String.format("FAIELD! Baccarat title header is incorrect. Expected %s but found %s","Turbo Exchange Baccarat",baccaratPage.getGameHeaderTitle()));

        egHomePage  = egHomePage.navigateGameFromMainMenu("Home",EGHomePage.class);
        BlackJackPage blcBlackJackPage= egHomePage.navigateGameFromImage(EGConstants.BLACKJACK,BlackJackPage.class);
        Assert.assertTrue(blcBlackJackPage.getGameHeaderTitle().contains("Turbo Exchange BlackJack - Game ID:"),String.format("FAIELD! Blackjack title header is incorrect. Expected %s but found %s","Turbo Exchange BlackJack",blcBlackJackPage.getGameHeaderTitle()));

        egHomePage  = egHomePage.navigateGameFromMainMenu("Home",EGHomePage.class);
        HiloPage hiloPage = egHomePage.navigateGameFromImage(EGConstants.HI_LO,HiloPage.class);
        Assert.assertTrue(hiloPage.getGameHeaderTitle().contains("Turbo Exchange Hi Lo"),String.format("FAIELD! Hi Lo title header is incorrect. Expected %s but found %s","Turbo Exchange Hi Lo",hiloPage.getGameHeaderTitle()));

        egHomePage  = egHomePage.navigateGameFromMainMenu("Home",EGHomePage.class);
        HoldemPage holdemPage = egHomePage.navigateGameFromImage(EGConstants.HOLD_EM,HoldemPage.class);
        Assert.assertTrue(holdemPage.getGameHeaderTitle().contains("Turbo Exchange Hold'em"),String.format("FAIELD! Holdem title header is incorrect. Expected %s but found %s","Turbo Exchange Hold'em",holdemPage.getGameHeaderTitle()));

        egHomePage  = egHomePage.navigateGameFromMainMenu("Home",EGHomePage.class);
        OmahahiPage omahahiPage = egHomePage.navigateGameFromImage(EGConstants.OMAHA_HI,OmahahiPage.class);
        Assert.assertTrue(omahahiPage.getGameHeaderTitle().contains("Exchange Omaha Hi"),String.format("FAIELD! Omaha Hi title header is incorrect. Expected %s but found %s","Exchange Omaha Hi",omahahiPage.getGameHeaderTitle()));

        egHomePage  = egHomePage.navigateGameFromMainMenu("Home",EGHomePage.class);
        CardDerbyRacingPage cardDerbyRacingPage = egHomePage.navigateGameFromImage(EGConstants.CARD_DERBY_RACING,CardDerbyRacingPage.class);
        Assert.assertEquals(cardDerbyRacingPage.getGameHeaderTitle(),EGConstants.CARD_DERBY_RACING_CAP,String.format("FAIELD!Card Derby Racing title header is incorrect. Expected %s but found %s",EGConstants.CARD_DERBY_RACING_CAP,cardDerbyRacingPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can open BACCARAT by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Baccarat
     * @expect: Play Standard baccarat page is correct ly display
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC006(String skinName){
        log("@title: Verify can open BACCARAT by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Step 2. Click Play Standard of Baccarat");
        BaccaratPage baccaratPage = egHomePage.navigateStandarGame(EGConstants.BACCARAT,BaccaratPage.class);

        log("Verify 1 Play Standard baccarat page is correctly display ");
        Assert.assertTrue(baccaratPage.getGameHeaderTitle().startsWith("Exchange Baccarat"),String.format("FAIELD! Baccarat title header is incorrect. Expected %s but found %s","Turbo Exchange Baccarat",baccaratPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }
    /**
     * @title: Verify can open BACCARAT by when click Play turbo
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Turbo of Baccarat
     * @expect: Play Turbo Caccarat page is correct ly display
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC005(String skinName){
        log("@title: Verify can open BACCARAT by when click Play turbo");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame( skinName);

        log("Step 2. Click Play Turbo of Baccarat");
        BaccaratPage baccaratPage = egHomePage.navigateTurboGame(EGConstants.BACCARAT,BaccaratPage.class);

        log("Verify 1 Play Turbo Baccarat page is correctly display ");
        Assert.assertTrue(baccaratPage.getGameHeaderTitle().startsWith("Turbo Exchange Baccarat"),String.format("FAIELD! Baccarat title header is incorrect. Expected %s but found %s","Turbo Exchange Baccarat",baccaratPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }
    /**
     * @title: Verify can open Blackjack by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Baccarat
     * @expect: Play Standard Blackjack page is correct ly display
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC007(String skinName){
        log("@title: Verify can open Blackjack by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Step 2. Click Play Standard of Blackjack");
        BlackJackPage blackJackPage = egHomePage.navigateStandarGame(EGConstants.BLACKJACK,BlackJackPage.class);

        log("Verify 1 Play Standard Blackjack page is correctly display ");
        Assert.assertTrue(blackJackPage.getGameHeaderTitle().startsWith("Exchange "+EGConstants.BLACKJACK_CAP),String.format("FAIELD! Blackjack title header is incorrect. Expected %s but found %s","Turbo Exchange Baccarat",blackJackPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }
    /**
     * @title: Verify can open Hi lo by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Hilo
     * @expect: Play Standard baccarat page is correct ly display
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC008(String skinName){
        log("@title: Verify can open Hilo by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Step 2. Click Play Standard of Hilo");
        HiloPage hiloPage = egHomePage.navigateStandarGame(EGConstants.HI_LO,HiloPage.class);

        log("Verify 1 Play Standard Hi Lo page is correctly display ");
        Assert.assertTrue(hiloPage.getGameHeaderTitle().startsWith("Exchange "+EGConstants.HI_LO_CAP),String.format("FAIELD! Hilo title header is incorrect. Expected %s but found %s","Turbo Exchange Hilo",hiloPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }
    /**
     * @title: Verify can open Omahahi by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Omahahi
     * @expect: Play Standard Omahahi page is correct ly display
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC009(String skinName){
        log("@title: Verify can open Omahahi by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Step 2. Click Play Standard of Omahahi");
        HoldemPage holdemPage = egHomePage.navigateStandarGame(EGConstants.HOLD_EM,HoldemPage.class);

        log("Verify 1 Play Standard Omahahi page is correctly display ");
        Assert.assertTrue(holdemPage.getGameHeaderTitle().startsWith("Exchange "+EGConstants.HOLD_EM_CAP),String.format("FAIELD! Omahahi title header is incorrect. Expected %s but found %s","Turbo Exchange "+EGConstants.HOLD_EM_CAP,holdemPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }
    /**
     * @title: Verify can open Omahahi by when click Play Standard
     * @precondition: 1. Login member site > Active Exchange Game
     * @step: 1.  Click Play Standard of Omahahi
     * @expect: Play Standard Omahahi page is correct ly display
     */
    @Test(groups = {"smoke"})
    @Parameters({"skinName"})
    public void EG_Home_Page_TC010(String skinName){
        log("@title: Verify can open Omahahi by when click Play Standard");
        log("Step 1. Navigate to exchange game ");
        EGHomePage egHomePage = memberHomePage.openExchangeGame(skinName);

        log("Step 2. Click Play Standard of Omahahi");
        OmahahiPage omahahiPage = egHomePage.navigateStandarGame(EGConstants.OMAHA_HI,OmahahiPage.class);

        log("Verify 1 Play Standard Omahahi page is correctly display ");
        Assert.assertTrue(omahahiPage.getGameHeaderTitle().startsWith("Exchange "+EGConstants.OMAHA_HI_CAP),String.format("FAIELD! Omahahi title header is incorrect. Expected %s but found %s","Turbo Exchange "+EGConstants.OMAHA_HI_CAP,omahahiPage.getGameHeaderTitle()));

        log("INFO: Executed completely");
    }}
