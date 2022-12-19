package membersite.testcases.sat;

import baseTest.BaseCaseMerito;
import com.paltech.element.common.Link;
import com.paltech.utils.DateUtils;
import common.MemberConstants;
import membersite.objects.sat.Event;
import membersite.pages.all.tabexchange.HomePage;
import membersite.pages.all.tabexchange.SportPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static common.MemberConstants.TIMEZONE;


public class SATOddsSectionTest extends BaseCaseMerito {

    /**
     * @title: Validate Market page UI
     * @precondition: 1. Login member site
     * @step: 1. Active any market non-in-play of soccer
     * @expect: 1. Validate Title display correctly : Event name/ Market Name
     * 2. Validate Start time display, There is no in-play label
     * 3. Validate Selection list and odds list display
     */
    @Test(groups = {"smoke"})
    public void SAT_OddsSection_TC003() {
        log("@title: Validate Market page UI");
        log("Step 1. Active any market non-in-play of soccer");
        SportPage page = memberHomePage.navigateSportMenu("Soccer", SportPage.class);
        Event event = page.getEvent(false, false, 20, 1);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }

        String eventStartTimeExpected = page.marketContainerControl_SAT.convertDate(event.getStartTime(),TIMEZONE);
        page.clickEvent(event);

        log("Verify 1. Validate Title display correctly : Event name/ Market Name");
        String actualTitle = page.marketContainerControl_SAT.getTitle();
        String expectedTitle = String.format("%s/ %s", event.getEventName(), page.marketContainerControl_SAT.getTitle(false));
        String actualStartTime = page.marketContainerControl_SAT.getEventStartTime();

        Assert.assertEquals(actualTitle, expectedTitle, String.format("ERROR! Market title is incorrect %s when navigate to event> market: %s ", actualTitle, expectedTitle));

        log("Verify 2. Validate Start time display, There is no in-play label");
        Assert.assertFalse(page.marketContainerControl_SAT.isLblInPlayDisplay(), "ERROR! In-Play label is displayed");
        Assert.assertEquals(eventStartTimeExpected, actualStartTime, String.format("ERROR! Expected Event Start Time is %s but found %s", actualStartTime, eventStartTimeExpected));
        Assert.assertEquals(page.marketContainerControl_SAT.getRuleButton(), MemberConstants.MarketContainer.RULE, String.format("ERROR! Expected rule name is %s but found %s", MemberConstants.MarketContainer.RULE, page.marketContainerControl_SAT.getRuleButton()));
        Assert.assertTrue(page.marketContainerControl_SAT.getTotalMatched().contains(MemberConstants.MarketContainer.MATCHED), String.format("ERROR! Matched label not display as expected", page.marketContainerControl_SAT.getTotalMatched()));

        log("Verify 3. Validate Selection list and odds list display");
        Assert.assertTrue(Objects.nonNull(page.marketContainerControl_SAT.getListSelection()), "ERROR! Selection list is empty");
        Assert.assertTrue(Objects.nonNull(page.marketContainerControl_SAT.getOddsListLabel(1, true)), "ERROR! Odds list is empty");
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
    public void SAT_OddsSection_TC007() {
        log("@title: Validate event link work");
        log("Step 1. Click on any event");
        Event event = memberHomePage.eventContainerControl_SAT.getEvent(false, false, 0, 1);

        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        memberHomePage.clickEvent(event);
        log("Verify 1. Market page display, Match Odds market is active by default");
        String marketNameExpected = memberHomePage.marketContainerControl_SAT.getTitle(false);
        Assert.assertTrue(memberHomePage.marketContainerControl_SAT.isDisplayed(), "ERROR! Market page not display after click on the event");
        Assert.assertEquals(marketNameExpected, "Match Odds", String.format("ERROR! Default market is Match Odds but found %s", marketNameExpected));
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
    public void SAT_OddsSection_TC008() {
        log("@title: Validate Event has match odds market type are displayed in the list");
        log("Step 1. Click on the event in the list");
        log("Step 2. Back to Home page and click on all event and check match odds market type is active by default");
        List<String> lstEvent = memberHomePage.eventContainerControl_SAT.getAllEventHighlight();
        if (Objects.isNull(lstEvent)) {
            log("DEBUG: There is no event available");
            return;
        }
        log("Verified: All market active is MATCH_ODDS market type");
        for (String event : lstEvent) {
            memberHomePage.eventContainerControl_SAT.clickEvent(event);
            String marketName = memberHomePage.marketContainerControl_SAT.getTitle(false);
            String matchOdd ="Most Points or Match Odds";
            Assert.assertTrue(matchOdd.contains(marketName),String.format("ERROR! Match odds market not active by default, Currently is %s", marketName));
            memberHomePage.navigateHomePage();
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
    public void SAT_OddsSection_TC015() {
        log("@title: Validate Horse Racing link works");
        log("Step 1. In home page - Next Up Racing section");
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