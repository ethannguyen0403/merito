package membersite.testcases.funsport.tabexchange;

import membersite.controls.funsport.OddPageControl;
import membersite.objects.funsport.Odd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.EventPage;
import membersite.pages.all.tabexchange.SportPage;
import baseTest.BaseCaseMerito;

import java.util.Objects;

public class EventPageTest extends BaseCaseMerito {

    /**
     * @title: Validate Soccer event UI display correct
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click on Soccer
     *  2. Click on and event on Soccer
     * @expect: 1. Verify Home team and away team display correct
     *          2. Have view as Line view and column View, and Line view is active by default
     *          3. Display match odds first
     */
    @Test(groups = {"smoke"})
    public void EventPageTest_001(){
        log("@title: Validate Soccer event UI display correct");

        log("Step1: 1. Click on Soccer");
        SportPage page = memberHomePage.navigateSoccer();

        log("Step1: 2. Click on and event on Soccer");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        EventPage eventPage =page.oddPageControl.clickEvent(odd);

        log("Verify: 1. Verify Home team and away team display correct");
        Assert.assertEquals(odd.getEventName(), eventPage.oddPageControl.getEventName());

        log("Verify: 2. Have view as Line view and column View, and Line view is active by default");
        Assert.assertEquals(eventPage.oddPageControl.lblViewAs.getText(),"View as:","FAILED! View as label is incorrect");
        Assert.assertFalse(eventPage.oddPageControl.isLineViewEnable(),"FAILED! View Line should be disable by default");
        Assert.assertTrue(eventPage.oddPageControl.isViewColumnEnable(),"FAILED! View Line should be disable by default");

        log("Verify: 3. Display match odds first");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate Tennis event UI display correct
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click on Tennis
     * 2. Click on and event on Tennis
     * @expect: 1. Verify Home team and away team display correct
     *          2. Have view as Line view and column View, and Line view is active by default
     *          3. Display match odds first
     */
    @Test(groups = {"smoke"})
    public void EventPageTest_002(){
        log("@title: Validate Tennis event UI display correct");

        log("Step1: 1. Click on Tennis");
        SportPage page = memberHomePage.navigateTennis();

        log("Step1: 2. Click on and event on Tennis");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        EventPage eventPage =page.oddPageControl.clickEvent(odd);

        log("Verify: 1. Verify Home team and away team display correct");
        Assert.assertEquals(odd.getEventName(), eventPage.oddPageControl.getEventName());

        log("Verify: 2. Have view as Line view and column View, and Line view is active by default");
        Assert.assertEquals(eventPage.oddPageControl.lblViewAs.getText(),"View as:","FAILED! View as label is incorrect");
        Assert.assertFalse(eventPage.oddPageControl.isLineViewEnable(),"FAILED! View Line should be disable by default");
        Assert.assertTrue(eventPage.oddPageControl.isViewColumnEnable(),"FAILED! View Line should be disable by default");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate Cricket event UI display correct
     * @pre-condition
     *           1. Sign in
     * @steps:   1. Click on Cricket
     * 2. Click on and event on Cricket
     * @expect: 1. Verify Home team and away team display correct
     *          2. Have view as Line view and column View, and Line view is active by default
     *           3. Display match odds first
     */
    @Test(groups = {"smoke"})
    public void EventPageTest_003(){
        log("@title: Validate Cricket event UI display correct");

        log("Step1: 1. Click on Cricket");
        SportPage page = memberHomePage.navigateCricket();

        log("Step1: 2. Click on and event on Cricket");
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        EventPage eventPage =page.oddPageControl.clickEvent(odd);

        log("Verify: 1. Verify Home team and away team display correct");
        Assert.assertEquals(odd.getEventName(), eventPage.oddPageControl.getEventName());

        log("Verify: 2. Have view as Line view and column View, and Line view is active by default");
        Assert.assertEquals(eventPage.oddPageControl.lblViewAs.getText(),"View as:","FAILED! View as label is incorrect");
        Assert.assertFalse(eventPage.oddPageControl.isLineViewEnable(),"FAILED! View Line should be disable by default");
        Assert.assertTrue(eventPage.oddPageControl.isViewColumnEnable(),"FAILED! View Line should be disable by default");

        log("INFO: Executed completely");
    }

}
