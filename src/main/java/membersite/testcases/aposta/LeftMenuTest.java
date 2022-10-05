package membersite.testcases.aposta;

import baseTest.BaseCaseMerito;
import com.paltech.element.common.Label;
import membersite.objects.sat.Event;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import membersite.pages.all.tabexchangegame.EGHomePage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class LeftMenuTest extends BaseCaseMerito {

    /**
     * @title: Validate that can search event name in search textbox
     * @pre-condition   1. Login member site
     * @steps:          1. Get any event name of the sport  has data
     *                  2. Input event name in search textbox
     *                  3. Click on the event in the search list
     * @expect:         1. Event display in  the search list
     *                  2. Left menu display event including it's market
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC001() {
        log("@title: Validate that can search event name in search textbox");
        log("Step 1. Get any event name of the sport has data");
        SportPage page = memberHomePage.activeSportInLefMenu("Soccer");
        Event event = page.eventContainerControl.getEvent(false, false, 20, 1);
       if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }

        log("Step 2. Input event name in search textbox");
        page.apHeaderControl.clickLeftMenu();
        Label lblResult = page.apLeftMenuControl.searchEvent(event.getEventName());
        String searchResult = lblResult.getText();

        log("Step 3. Click on the event in the search list");
        lblResult.click();

        log("Verify 1. Event display in  the search list");
        Assert.assertEquals(searchResult, event.getEventName(),String.format("ERROR! Can not search event"));

        log("Verify 2. Left menu display event including it's market");
        Assert.assertEquals(page.apLeftMenuControl.getActiveEvent(), event.getEventName(),String.format("ERROR! Expected event in left menu is %s but found %s", event.getEventName(),memberHomePage.getActiveEvent()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can collapse/expand left menu
     * @pre-condition   1. Login member site
     * @steps:          1. Click on Menu icon in the top left corner
     *                  2. Re-click on menu icon
     * @expect:         1. Left menu is collapse/expand
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC003() {
        log("@title: Validate can collapse/expand left menu");
        log("Step 1. Click on Menu icon in the top left corner");
        memberHomePage.apHeaderControl.clickLeftMenu();

        log("Verify 1. Left menu is expanded");
        Assert.assertTrue(memberHomePage.apLeftMenuControl.isDisplayed(2), String.format("ERROR! Expected left menu is not display after expanded "));

        log("Step 2. Re-click on menu icon");
        log("Verify 1. Left menu is collapsed");
        memberHomePage.apHeaderControl.clickLeftMenu();
        Assert.assertFalse(memberHomePage.apLeftMenuControl.isDisplayed(2), String.format("ERROR! Expected left menu is displayed after collapsed"));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Left menu display correct sport when active a sport in main menu
     * @pre-condition   1. Login member site
     * @steps:          1. Click on the sport in main menu: Scocer, Tennis, Cricket
     * @expect:         1. Left menu display corresponding sport
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC005(){
        log("@title: Validate Left menu display correct sport when active a sport on main menu");
        log("Step  1. Click on the sport in main menu");

        memberHomePage.apHeaderControl.clickLeftMenu();
        SportPage page = memberHomePage.activeSportInLefMenu("Soccer");

        Assert.assertEquals( page.apLeftMenuControl.getActiveSport(),"Soccer",String.format("ERROR! Expected Sport active in left menu is %s but found %s","Soccer",page.apLeftMenuControl.getActiveSport()));
        page =memberHomePage.apHeaderControl.navigateSportMenu("Cricket",SportPage.class);
        Assert.assertEquals( page.apLeftMenuControl.getActiveSport(),"Cricket",String.format("ERROR! Expected Sport active in left menu is %s but found %s","Cricket",page.apLeftMenuControl.getActiveSport()));

        page =memberHomePage.apHeaderControl.navigateSportMenu("Tennis",SportPage.class);
        Assert.assertEquals( page.apLeftMenuControl.getActiveSport(),"Tennis",String.format("ERROR! Expected Sport active in left menu is %s but found %s","Tennis",page.apLeftMenuControl.getActiveSport()));

       log(" INFO: Executed Completely!");
    }

    /**
     * @title: Validate odds section is active when clicking on market
     * @pre-condition   1. Login member site
     * @steps:          1. Select a sport click on competition > Event
     *                  2. Click on any market
     * @expect:         1.Sport Highlights section is display when clicking on Sport>Competition>Event name
     *                  2. Odds section display. Validate Event/market is displayed accordingly(Event/Market)
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC006(){
        log("@title: Validate odds section is active when clicking on market");
        log("Step  1. Select a sport ");
        memberHomePage.openLeftMenu();
        SportPage page = memberHomePage.apLeftMenuControl.clickLeftMenuItem("Cricket",SportPage.class);

        log("Verified 1. Sport Highlights section is display when clicking on Sport");
        log("Verified 2. Odds section display. Validate Event/market is displayed accordingly(Event/Market)");
        Assert.assertEquals(page.lblSportHighLight.getText(), "Cricket Highlights",String.format("ERROR! Expected is Cricket Highlight but found %s",page.lblSportHighLight.getText()));
        Assert.assertFalse(page.marketContainerControl.isDisplayed(),"ERROR! Market page display when clicking on sport in left menu");
        log("Step 2. Select a competition ");
        page = page.apLeftMenuControl.clickLeftMenuItem(page.apLeftMenuControl.allSportMenu.getOptions(false).get(0), SportPage.class);

        log("Verified 3. Sport Highlights section is display when clicking on competition");
        log("Verified 4. Odds section display. Validate Event/market is displayed accordingly(Event/Market)");
        Assert.assertEquals(page.lblSportHighLight.getText(), "Cricket Highlights",String.format("ERROR! Expected is Sport Highlight but found %s",page.lblSportHighLight.getText()));
        Assert.assertFalse(page.marketContainerControl.isDisplayed(),"ERROR! Market page display when clicking on Competition in left menu");

        log("Step 3. Select a event ");
        String eventName = page.apLeftMenuControl.allSportMenu.getOptions(false).get(0);
        page = page.apLeftMenuControl.clickLeftMenuItem(eventName, SportPage.class);

        log("Verified 5. Sport Highlights section is display when clicking on event");
        log("Verified 6. Odds section display. Validate Event/market is displayed accordingly(Event/Market)");
        Assert.assertEquals(page.lblSportHighLight.getText(),"Cricket Highlights",String.format("ERROR! Expected is Sport Highlight but found %s",page.lblSportHighLight.getText()));
        Assert.assertFalse(page.marketContainerControl.isDisplayed(),"ERROR! Market page display when clicking on Event in left menu");

        log("Step 4. Select a market ");
        String marketName = page.apLeftMenuControl.allSportMenu.getOptions(false).get(0);
        MarketPage marketPage = page.apLeftMenuControl.clickLeftMenuItem(marketName, MarketPage.class);
        String marketTitle = String.format("%s/ %s", eventName,marketName);

        log("Verified 1. Sport Highlights section is no longer display when clicking on market");
        log("Verified 2. Market page display with the corresponding market");
        Assert.assertFalse(marketPage.lblSportHighLight.isDisplayed(),"ERROR! Expected is Sport Highlight should not display when active market");
        Assert.assertTrue(marketPage.marketContainerControl.isDisplayed(),"ERROR! Market page not display when clicking on Event in left menu");
        Assert.assertEquals(marketPage.marketContainerControl.getTitle(), marketTitle,"ERROR! Market title does not match with the expected");

        log("INFO:Executed Completely!");
    }

    /**
     * @title: Validate Market display corresponding when clicking on the left menu
     * @pre-condition   1. Login member site
     * @steps:          1. Active any market of the Cricket
     *                  2. On Left menu, click on the market list
     * @expect:        1. Market title displays with the corresponding with the left menu
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC008(){
        log("@title: Validate Market display corresponding when clicking on the left menu");
        log("Step  1. Active any market of the Cricket");
        log("Step  2. On Left menu, click on the market list");
        memberHomePage.openLeftMenu();
        SportPage page = memberHomePage.apLeftMenuControl.clickLeftMenuItem("Cricket",SportPage.class);
        String competitionName = page.apLeftMenuControl.allSportMenu.getOptions(false).get(0);
        page.apLeftMenuControl.clickLeftMenuItem(competitionName,SportPage.class);
        String eventName = page.apLeftMenuControl.allSportMenu.getOptions(false).get(0);
        page.apLeftMenuControl.clickLeftMenuItem(eventName,SportPage.class);
        List<String> marketList = page.apLeftMenuControl.allSportMenu.getOptions(false);
        String marketTitle = "";

        log("Verified  1. Market title displays with the corresponding with the left menu");
        for(int i =0 , n = marketList.size(); i< n; i++)
        {
            MarketPage marketPage = page.apLeftMenuControl.clickLeftMenuItem(marketList.get(i),MarketPage.class);
            marketTitle = String.format("%s/ %s", eventName,marketList.get(i));
            Assert.assertEquals(marketPage.marketContainerControl.getTitle(), marketTitle,String.format("ERROR! Click market %s but title display %s", marketTitle, marketPage.marketContainerControl.getTitle()));
        }
        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate In-play page not display left menu
     * @pre-condition   1. Login member site
     * @steps:         1. Active Inplay page
     * @expect:       Validate there is no In-Play menu in the Left menu
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC009(){
        log("@title: Validate In-play page not display left menu");
        log("Step  1. Active Inplay page");
        log("Step  2. On Left menu, click on the market list");
        memberHomePage.apHeaderControl.clickLeftMenu();
        SportPage page =memberHomePage.apHeaderControl.navigateSportMenu("In-Play",SportPage.class);

       List<String> lstMenu= page.apLeftMenuControl.allSportMenu.getOptions(false);
        log("Verified Validate there is no In-Play menu in the Left menu");
        Assert.assertFalse(lstMenu.contains("In-Play"),"FAILED! Leftmenu should not contain In-Play menu");
        Assert.assertEquals(page.lblSportHighLight.getText(),"In-Play Matches","FAILED! In-Play page title is incorrect display");
        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate odds section is active when clicking on market
     * @pre-condition   1. Login member site
     * @steps:          1. Select a sport click on competition > Event
     *                  2. Click on any market
     * @expect:         1.Sport Highlights section is display when clicking on Sport>Competition>Event name
     *                  2. Odds section display. Validate Event/market is displayed accordingly(Event/Market)
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC010() {
        log("@title: Validate odds section is active when clicking on market");
        log("Step  1. Select a sport ");
        memberHomePage.openLeftMenu();
        SportPage page = memberHomePage.apLeftMenuControl.clickLeftMenuItem("Cricket", SportPage.class);

        log("Verified 1. Sport Highlights section is display when clicking on Sport");
        log("Verified 2. Odds section display. Validate Event/market is displayed accordingly(Event/Market)");
        Assert.assertEquals(page.lblSportHighLight.getText(), "Cricket Highlights", String.format("ERROR! Expected is Cricket Highlight but found %s", page.lblSportHighLight.getText()));
        Assert.assertFalse(page.marketContainerControl.isDisplayed(), "ERROR! Market page display when clicking on sport in left menu");

        log("Step 2. Select a competition ");
        String competition = page.apLeftMenuControl.allSportMenu.getOptions(false).get(0);
        page = page.apLeftMenuControl.clickLeftMenuItem(competition, SportPage.class);
        Assert.assertEquals(page.apLeftMenuControl.allSportMenu.getSelectedItem(),competition,"Failed! Incorrect competition is active in the left menu");
        log(" INFO: Executed Completely!");
    }
    /**
     * @title: Validate correct activate sport display in the left menu when click on sport menu
     * @pre-condition   1. Login member site
     * @steps: 1. Click on any sport on main menu
     * @expect:   Verify left menu display correct sport
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC011(){
        log("@title: Validate correct activate sport display in the left menu when click on sport menu");
        log("Step  1. Expand left menu");
        memberHomePage.apHeaderControl.clickLeftMenu();
        log("Verify:  Verify left menu display Soccer sport after clicking");
        SportPage page =memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Assert.assertEquals( page.apLeftMenuControl.getActiveSport(),"Soccer",String.format("ERROR! Expected Sport active in left menu is %s but found %s","Soccer",page.apLeftMenuControl.getActiveSport()));

        log("Verify:  Verify left menu display Cricket sport after clicking");
        page.apLeftMenuControl.clickBack();
        page =page.apLeftMenuControl.clickLeftMenuItem("Cricket",SportPage.class);
        Assert.assertEquals( page.apLeftMenuControl.getActiveSport(),"Cricket",String.format("ERROR! Expected Sport active in left menu is %s but found %s","Cricket",page.apLeftMenuControl.getActiveSport()));

        log("Verify:  Verify left menu display Tennis sport after clicking");
        page.apLeftMenuControl.clickBack();
        page =page.apLeftMenuControl.clickLeftMenuItem("Tennis",SportPage.class);
        Assert.assertEquals( page.apLeftMenuControl.getActiveSport(),"Tennis",String.format("ERROR! Expected Sport active in left menu is %s but found %s","Tennis",page.apLeftMenuControl.getActiveSport()));
        log("INFO: Executed Completely!");
    }

    /**
     * @title: Validate Casino on the left menu
     * @pre-condition   1. Login member site
     * @steps: 1/ Active home page
     * 2/ Expand left menu
     * @expect:    1. Verify Casino display and sub menu display the product is activate
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC013(){
        log("@title: Validate Casino on the left menu");
        log("Step 1/ Active home page");
        log("Step 2/ Expand left menu");
       // List<String> lstProduct = BetUtils.getProductInfo();
        memberHomePage.openLeftMenu();
        List<String>lstMenu =  memberHomePage.apLeftMenuControl.allSportMenu.getOptions(false);

        log("1 Verified Validate Casino in the Left menu");
        Assert.assertTrue(lstMenu.contains("Casino"),"FAILED! Left menu does not contains Casino  menu");

        memberHomePage.apLeftMenuControl.allSportMenu.clickSubMenu("Casino",false);
        List<String> lstCasinoSubMenu = memberHomePage.apLeftMenuControl.casinotMenu.getOptions(false);

        log("2 Verify Home page with Cricket image still display after click on Casino on the left menu");
        Assert.assertTrue(memberHomePage.apHomeContainerControl.imgCricket.isDisplayed(),"FAILED! Cricket image menu not display in home page");

        log("3.Verified Casino sub menu in the Left menu contains Live Dealer Asian");
        Assert.assertTrue(lstCasinoSubMenu.contains("Live Dealer Asian"),"FAILED! Casino submenu does not contain Live Dealer Asian");
        log("INFO: Executed Completely!");
    }
    /**
     * @title: Validate Exchange Game on the left menu
     * @pre-condition   1. Login member site
     * @steps: 1/ Active home page
     * 2/ Expand left menu
     * @expect:    1. Verify Exchange game display in the left menu
     */
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC014(){
        log("@title: Validate Casino on the left menu");
        log("Step 1/ Active home page");
        log("Step 2/ Expand left menu");
        List<String> lstProduct = BetUtils.getProductInfo();
        memberHomePage.openLeftMenu();
        List<String>lstMenu =  memberHomePage.apLeftMenuControl.allSportMenu.getOptions(false);

        log("1 Verified Validate Casino in the Left menu");
        Assert.assertTrue(lstMenu.contains("Exchange Games"),"FAILED! Left menu does not contains Exchange Games menu");

       EGHomePage egHomePage =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Exchange Games", EGHomePage.class);
       Assert.assertTrue(egHomePage.getPageUrl().contains("exchange-game"),"FAILED! Exchange Game does not display");
        log("INFO: Executed Completely!");
    }
}
