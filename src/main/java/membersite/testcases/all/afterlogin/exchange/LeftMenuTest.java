package membersite.testcases.all.afterlogin.exchange;

import com.paltech.element.common.Label;
import membersite.common.FEMemberConstants;
import membersite.objects.sat.Event;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;
import util.testraildemo.TestRails;

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
    @TestRails(id="494")
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC001() {
        log("@title: Validate that can search event name in search textbox");
        log("Step 1. Get any event name of the sport has data");
        Event event = memberHomePage.eventContainerControl.getEvent(false, false, 20, 1);
       if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }

        log("Step 2. Input event name in search textbox");
        Label lblResult = memberHomePage.searchEvent(event.getEventName());
        String searchResult = lblResult.getText();

        log("Step 3. Click on the event in the search list");
        lblResult.click();

        log("Verify 1. Event display in  the search list");
        Assert.assertEquals(searchResult, event.getEventName(),String.format("ERROR! Can not search event"));

        log("Verify 2. Left menu display event including it's market");
        Assert.assertEquals(memberHomePage.getActiveEvent(), event.getEventName(),String.format("ERROR! Expected event in left menu is %s but found %s", event.getEventName(),memberHomePage.getActiveEvent()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can collapse/expand left menu
     * @pre-condition   1. Login member site
     * @steps:          1. Click on Menu icon in the top left corner
     *                  2. Re-click on menu icon
     * @expect:         1. Left menu is collapse/expand
     */
    @TestRails(id="496")
    @Test (groups = {"smoke"})
    @Parameters({"skinName"})
    public void FE_LeftMenu_TC003(String skinName) {
        log("@title: Validate can collapse/expand left menu");
        log("Step 1. Click on Menu icon in the top left corner");
        memberHomePage.expandLeftMenu();
        log("Verify 1. Left menu is expanded");
        Assert.assertTrue(memberHomePage.imgLeftMenu.getAttribute("src").contains(String.format(FEMemberConstants.Header.LEFT_MENU_ICON_EXPAND,skinName)),
                String.format("ERROR! Expected left menu is expand but found %s",memberHomePage.imgLeftMenu.getAttribute("src")));
        Assert.assertTrue(memberHomePage.menuHome.isDisplayed(),"ERROR! Menu not display when expand left menu");

        log("Step 2. Re-click on menu icon");
        log("Verify 1. Left menu is collapsed");
        memberHomePage.collapseLeftMenu();
        Assert.assertTrue(memberHomePage.imgLeftMenu.getAttribute("src").contains(String.format(FEMemberConstants.Header.LEFT_MENU_ICON_COLLAPSE,skinName)),
                String.format("ERROR! Expected left menu is collapse but found %s",memberHomePage.imgLeftMenu.getAttribute("src")));
        Assert.assertFalse(!memberHomePage.menuHome.isDisplayed(),"ERROR! Menu not display when collapse left menu");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Left menu display correct sport when active a sport
     * @pre-condition   1. Login member site
     * @steps:          1. Click on the sport in main menu
     * @expect:         1. Left menu display corresponding sport
     */
    @TestRails(id="497")
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC005(){
        log("@title: Validate Left menu display correct sport when active a sport");
        log("Step  1. Click on the sport in main menu");
        List<String> menuList = memberHomePage.getMainSportsMenu();
        for(int i = 3; i < menuList.size(); i++)
        {
            log(String.format("Verified  1. Left menu display when clicking on %s",menuList.get(i)));
            SportPage page = memberHomePage.navigateSportMenu(menuList.get(i), SportPage.class);
            String menu = page.getActiveSport();
            Assert.assertEquals(menu,menuList.get(i),String.format("ERROR! Expected Sport active in left menu is %s but found %s", menuList.get(i),menu));
        }
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
    @TestRails(id="498")
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC006(){
        log("@title: Validate odds section is active when clicking on market");
        log("Step  1. Select a sport ");
        memberHomePage.clickSport(0);

        log("Verified 1. Sport Highlights section is display when clicking on Sport");
        log("Verified 2. Odds section display. Validate Event/market is displayed accordingly(Event/Market)");
        Assert.assertEquals(memberHomePage.lblSportHighLight.getText(), FEMemberConstants.HomePage.SPORT_HIGHLIGHT,String.format("ERROR! Expected is Sport Highlight but found %s",memberHomePage.lblSportHighLight.getText()));
        Assert.assertFalse(memberHomePage.marketContainerControl.isDisplayed(),"ERROR! Market page display when clicking on sport in left menu");
        log("Step 2. Select a competition ");
        memberHomePage.clickCompetition(0);

        log("Verified 3. Sport Highlights section is display when clicking on competition");
        log("Verified 4. Odds section display. Validate Event/market is displayed accordingly(Event/Market)");
        Assert.assertEquals(memberHomePage.lblSportHighLight.getText(), FEMemberConstants.HomePage.SPORT_HIGHLIGHT,String.format("ERROR! Expected is Sport Highlight but found %s",memberHomePage.lblSportHighLight.getText()));
        Assert.assertFalse(memberHomePage.marketContainerControl.isDisplayed(),"ERROR! Market page display when clicking on Competition in left menu");

        log("Step 3. Select a event ");
        memberHomePage.clickEvent(0);
        String eventName = memberHomePage.getActiveEvent();

        log("Verified 5. Sport Highlights section is display when clicking on event");
        log("Verified 6. Odds section display. Validate Event/market is displayed accordingly(Event/Market)");
        Assert.assertEquals(memberHomePage.lblSportHighLight.getText(), FEMemberConstants.HomePage.SPORT_HIGHLIGHT,String.format("ERROR! Expected is Sport Highlight but found %s",memberHomePage.lblSportHighLight.getText()));
        Assert.assertFalse(memberHomePage.marketContainerControl.isDisplayed(),"ERROR! Market page display when clicking on Event in left menu");

        log("Step 4. Select a market ");
        memberHomePage.clickMarket(1);
        String marketName = memberHomePage.getMarketByIndex(1);
        String marketTitle = String.format("%s/ %s", eventName,marketName);

        log("Verified 1. Sport Highlights section is no longer display when clicking on market");
        log("Verified 2. Market page display with the corresponding market");
        Assert.assertFalse(memberHomePage.lblSportHighLight.isDisplayed(),"ERROR! Expected is Sport Highlight should not display when active market");
        Assert.assertTrue(memberHomePage.marketContainerControl.isDisplayed(),"ERROR! Market page not display when clicking on Event in left menu");
        Assert.assertEquals(memberHomePage.marketContainerControl.getTitle(), marketTitle,"ERROR! Market title does not match with the expected");

        log("INFO:Executed Completely!");
    }

    /**
     * @title: Validate Market display corresponding when clicking on the left menu
     * @pre-condition   1. Login member site
     * @steps:          1. Active any market of the Cricket
     *                  2. On Left menu, click on the market list
     * @expect:        1. Market title displays with the corresponding with the left menu
     */
    @TestRails(id="499")
    @Test (groups = {"smoke"})
    public void FE_LeftMenu_TC008(){
        log("@title: Validate Market display corresponding when clicking on the left menu");
        log("Step  1. Active any market of the Cricket");
        log("Step  2. On Left menu, click on the market list");
        memberHomePage.clickEvent(0,0,0);
        String eventName = memberHomePage.getActiveEvent();
        List<String> marketList = memberHomePage.getMarkets();
        String marketTitle = eventName;

        log("Verified  1. Market title displays with the corresponding with the left menu");
        for(int i =0 , n = marketList.size(); i< n; i++)
        {
            memberHomePage.clickMarket(marketList.get(i));
            eventName = String.format("%s/ %s", eventName,marketList.get(i));
            Assert.assertEquals(memberHomePage.marketContainerControl.getTitle(), eventName,String.format("ERROR! Click market %s but title display %s", eventName, memberHomePage.marketContainerControl.getTitle()));
        }
        log("INFO: Executed Completely!");
    }
}
