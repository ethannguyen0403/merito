package membersite.testcases.aposta;

import com.paltech.element.common.Link;
import com.paltech.utils.DateUtils;
import membersite.common.FEMemberConstants;
import membersite.objects.sat.Event;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;

import java.util.List;
import java.util.Objects;

public class OddsSectionTest extends BaseCaseMerito {

    /**
     * @title: Validate Market page UI
     * @precondition: 1. Login member site
     * @step: 1. Active any market non-in-play of soccer
     * @expect: 1. Validate Title display correctly : Event name/ Market Name
     * 2. Validate Start time display, There is no in-play label
     * 3. Validate Selection list and odds list display
     */
    @Test(groups = {"smoke"})
    public void OddsSection_TC003() {
        log("@title: Validate Market page UI");
        log("Step 1. Active any market non-in-play of soccer");
        SportPage page = memberHomePage.activeSportInLefMenu("Soccer");
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }

     //   String eventStartTimeExpected = page.marketContainerControl.convertDate(event.getStartTime());
        page.clickEvent(event);

        log("Verify 1. Validate Title display correctly : Event name/ Market Name");
        String actualTitle = page.marketContainerControl.getTitle();
        String expectedTitle = String.format("%s/ %s", event.getEventName(), page.marketContainerControl.getTitle(false));
        String actualStartTime = page.marketContainerControl.getEventStartTime();

        Assert.assertEquals(actualTitle, expectedTitle, String.format("ERROR! Market title is incorrect %s when navigate to event> market: %s ", actualTitle, expectedTitle));

        log("Verify 2. Validate Start time display, There is no in-play label");
        Assert.assertFalse(page.marketContainerControl.isLblInPlayDisplay(), "ERROR! In-Play label is displayed");
      //  Assert.assertEquals(eventStartTimeExpected, actualStartTime, String.format("ERROR! Expected Event Start Time is %s but found %s", actualStartTime, eventStartTimeExpected));
        Assert.assertEquals(page.marketContainerControl.getRuleButton(), FEMemberConstants.MarketContainer.RULE, String.format("ERROR! Expected rule name is %s but found %s", FEMemberConstants.MarketContainer.RULE, page.marketContainerControl.getRuleButton()));
        Assert.assertTrue(page.marketContainerControl.getTotalMatched().contains(FEMemberConstants.MarketContainer.MATCHED), String.format("ERROR! Matched label not display as expected", page.marketContainerControl.getTotalMatched()));

        log("Verify 3. Validate Selection list and odds list display");
        Assert.assertTrue(Objects.nonNull(page.marketContainerControl.getListSelection()), "ERROR! Selection list is empty");
        Assert.assertTrue(Objects.nonNull(page.marketContainerControl.getOddsListLabel(1, true)), "ERROR! Odds list is empty");
        log("INFO: Executed completely!");
    }

    /**
     * @title: Validate event link work
     * @precondition: 1. Sports should unblock some events
     * 2. Login member site
     * @step: 1. Click on any event
     * @expect: 1. Market page display, Match Odds market is active by default
     */
    @Test(groups = {"smoke"})
    public void OddsSection_TC007() {
        log("@title: Validate event link work");
        log("Step 1. Click Soccer and on any event");
        SportPage page = memberHomePage.activeSportInLefMenu("Soccer");
        Event event = page.eventContainerControl.getEventRandom( false,false);

        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);

        log("Verify 1. Market page display, Match Odds market is active by default");
        String marketNameExpected = page.marketContainerControl.getTitle();
        Assert.assertTrue(page.marketContainerControl.isDisplayed(), "ERROR! Market page not display after click on the event");
        Assert.assertEquals(marketNameExpected, String.format("%s/ %s",event.getEventName(),"Match Odds"), String.format("ERROR! Default market is Match Odds but found %s", marketNameExpected));
        log("INFO: Executed completely!");
    }

    /**
     * @title: Validate Event has match odds market type are displayed in the list
     * @precondition: 1. Sports should unblock some events
     * 2. Login member site
     * @step: 1. Click on the event in the list
     * 2. Back to Home page and click on all event and check match odds market type is active by default
     * @expect: 1. All market active is MATCH_ODDS market type
     */
    @Test(groups = {"smoke"})
    public void OddsSection_TC008() {
        log("@title: Validate Event has match odds market type are displayed in the list");
        log("Step 1. Click on the event in the list");
        log("Step 2. Back to Home page and click on all event and check match odds market type is active by default");
        SportPage page = memberHomePage.activeSportInLefMenu("Soccer");
        List<String> lstEvent = page.eventContainerControl.getAllEventHighlight();
        if (Objects.isNull(lstEvent)) {
            log("DEBUG: There is no event available");
            return;
        }
        log("Verified: All market active is MATCH_ODDS market type");
        for (String event : lstEvent) {
            memberHomePage.clickEventName(event);
            String marketName = memberHomePage.marketContainerControl.getTitle();
            Assert.assertTrue(marketName.startsWith(event), String.format("ERROR! Match odds market not active by default, Currently is %s", marketName));
            Assert.assertTrue(memberHomePage.getPageUrl().contains("mtype=MATCH_ODDS"),"FAILED! Market Type not display as MATCH_ODDS ");
            page =  page.apHeaderControl.navigateSportMenu("Soccer",SportPage.class);
        }
        log("INFO: Executed completely!");
    }

    /**
     * @title: Validate Horse Racing link works
     * @precondition: 1. Login member Site
     * @step: 1. In home page - Next Up Racing section
     * 2. Click on any Horse racing link
     * @expect: 1. Racing market page display correctly. Country, market start time, market name is corrected
     */
    @Test(groups = {"smoke"})
    public void OddsSection_TC015() {
        log("@title: Validate Horse Racing link works");
        log("Step 1. In home page - Next Up Racing section");
        memberHomePage.apHeaderControl.navigateSportMenu("Exchange",HomePage.class);
        Link lnkHR = Link.xpath(memberHomePage.lnkHRRaceListXpath);
        if (lnkHR.getWebElements()!= null) {
            int totalRC = lnkHR.getWebElements().size();
            for (int i = 1, n = totalRC; i <= n; i++) {
                Link item = Link.xpath(String.format("(%s)[%d]", memberHomePage.lnkHRRaceListXpath, i));
                String racingLink = item.getText().trim();
                String marketStartTime = String.format("%s %s", DateUtils.getDate(0, "EEE dd MMM", "IST"), racingLink.split(" ")[0]);
                log(String.format("Step 2.%d Click on any Horse racing link %s",1, racingLink));
                String title = item.getAttribute("title");
                String hrMarket = title.substring(0, title.indexOf("|")).trim();
                item.click();

                String marketTitle = memberHomePage.racingMarketControl.getTitle(false);
                log(String.format("Verify 1.%d Racing market page display correctly. Country, market start time, market name is corrected",i));
                Assert.assertEquals(hrMarket, marketTitle, String.format("ERROR! HoseRacing Market not display as expected"));
                Assert.assertEquals(memberHomePage.racingMarketControl.lblMarketStartTime.getText(), marketStartTime, "ERROR! Market Start Time not match");
                memberHomePage.navigateSportMenu("Home", HomePage.class);
            }
        }
        log("INFO: Executed completely!");
    }
}