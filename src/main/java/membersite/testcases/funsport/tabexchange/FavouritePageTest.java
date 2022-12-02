package membersite.testcases.funsport.tabexchange;

import membersite.controls.funsport.EventOddContentControl;
import membersite.controls.funsport.OddPageControl;
import membersite.objects.funsport.Odd;
import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.EventPage;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.SportPage;
import membersite.pages.funsport.tabexchange.FavoritePage;
import baseTest.BaseCaseMerito;
import util.testraildemo.TestRails;

import java.util.Objects;

public class FavouritePageTest extends BaseCaseMerito {
    /**
     * @title: Verify Favourite page display when have no market add
     * @pre-condition
     *           1. Login member site
     * @steps:  1. Click on Favourite
     * @expect: Verify favourite page display
     */
    @Test(groups = {"smoke"})
    public void Favourite_Page_TC_001(){
        log("@title: Verify Favourite page display when have no market add");

        log("Step1:  1. Click on Favourite");
        FavoritePage page = memberHomePage.navigateFavourite();

        log("Verify 1: Verify favourite page display");
        Assert.assertEquals(page.lblNoRecord.getText(),"Add Favourite Markets here","FAILED! Message when have no market in favourite page is incorrect");

        log("INFO: Executed completely");
    }
    /**
     * @title: Verify can add/remove event from sport page
     * @pre-condition
     *           1. Login member site
     * @steps: 1. Select Cricket and click on + in the list event to add event
     * 2. Navigate to favourite page
     * 3. Back to sport page and click - of the added event
     * @expect: Verify favourite page display
     */
    @Test(groups = {"smoke"})
    public void Favourite_Page_TC_002(){
        log("@title: Verify can add/remove event from sport page");
        log("Step1: 1. Select Cricket and click on + in the list event to add event");
        SportPage page = memberHomePage.navigateCricket();
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        log("Step1: 2. Navigate to favourite page");
        page.addRemoveFavorite(odd.getEventIndex(),true);
        Assert.assertFalse(page.isIconFavoriteDisplay(odd.getEventIndex(),true),"FAILED! Add favorite icon should not display after adding");
        Assert.assertTrue(page.isIconFavoriteDisplay(odd.getEventIndex(),false),"FAILED! Remive favorite icon should display after adding");

        log("Step1: 3. Back to sport page and click - of the added event");
        FavoritePage favoritePage = page.navigateFavourite();
        int index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");
        Assert.assertTrue(index!=0,"FAILED! The market-event not display in favorite page");

        log("Verify 1 Verify event is add and remove from favourite page");
        favoritePage.removeMarket(odd.getEventName(),"Match Odds");

        index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");
        Assert.assertTrue(index==0,"FAILED! The market-event not display in favorite page");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can add/remove event from event page
     * @pre-condition
     *           1. Login member site
     * @steps: 2. Select Cricket and click on an event and click on + in the list event to add event
     * 3. Navigate to favourite page
     * 4. Back to sport page and click - of the added event
     * @expect: 1 Verify event is add and remove from favourite page
     */
    @Test(groups = {"smoke"})
    public void Favourite_Page_TC_003(){
        log("@title: Verify can add/remove event from event page");
        log("Step1: 1. Select Cricket and click on an event and click on + in the list event to add event");
        SportPage page = memberHomePage.navigateCricket();
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        EventPage eventPage = page.oddPageControl.clickEvent(odd);
        Odd oddEventPage = eventPage.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);

        log("Step2.Click on + in the list event to add event");
        eventPage.oddPageControl.addRemoveFavorite(oddEventPage,true);
        Assert.assertTrue(oddEventPage.getCcFavorite().getAttribute("class").contains("minus_sign_icon"),"FAILED! Add favorite icon should not display after adding");

        log("Step 2. Navigate to favourite page");
        FavoritePage favoritePage = eventPage.navigateFavourite();
        int index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");
        Assert.assertTrue(index!=0,"FAILED! The market-event not display in favorite page");

        log("Verify 1 Verify event is add and remove from favourite page");
        favoritePage.removeMarket(odd.getEventName(),"Match Odds");

        index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");
        Assert.assertTrue(index==0,"FAILED! The market-event not display in favorite page");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can add/remove event from market page
     * @pre-condition
     *           1. Login member site
     * @steps: 1. Click on Favourite
     * 2. Select Cricket and click on + in the list event to add event
     * 3. Navigate to favourite page
     * 4. Back to evnet page and click - of the added event
     * @expect: 1 Verify event is add and remove from favourite page
     */
    @Test(groups = {"smoke"})
    public void Favourite_Page_TC_004(){
        log("@title: Verify can add/remove event from market page");
        log("Step1: 1. Select Cricket and click on an event and click on + in the list event to add event");
        SportPage page = memberHomePage.navigateCricket();
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        EventPage eventPage = page.oddPageControl.clickEvent(odd);
        Odd oddEventPage = eventPage.oddPageControl.getMatchOdds(EventOddContentControl.Team.HOME,true);
        MarketPage marketPage = eventPage.openMarketPage(oddEventPage.getEventName());
        marketPage.icFavorite.click();

        log("Step 3.Click on + in the list event to add event");
        Assert.assertTrue(marketPage.icFavorite.getAttribute("class").contains("glyphicon-minus-sign"),"FAILED! Add favorite icon should not display after adding");

        log("Step 4. Navigate to favourite page");
        FavoritePage favoritePage = eventPage.navigateFavourite();

        log("Verify 1 Verify event is added in favourite page");
        int index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");
        Assert.assertTrue(index!=0,"FAILED! The market-event not display in favorite page");

        log("Step 5 Click cricket > event> Market > Remove Match odds from favorite");
        page = memberHomePage.navigateCricket();
        eventPage = page.oddPageControl.clickEvent(odd.getEventName());
        marketPage = eventPage.openMarketPage(oddEventPage.getEventName());
        marketPage.icFavorite.click();

        log("Verify 2 Verify add  favourite  icon display page");
        Assert.assertTrue(marketPage.icFavorite.getAttribute("class").contains("glyphicon-plus-sign"),"FAILED! Add favorite icon should not display after adding");

        log("Step6 Active favorite page");
        favoritePage = eventPage.navigateFavourite();
        index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");

        log("Verify 3 Match odds no longer exit");
        Assert.assertTrue(index==0,"FAILED! The market-event not display in favorite page");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can remove event from favourite page
     * @pre-condition
     *           1. Login member site
     * @steps: 1. Click on Favourite
     * 2. Select Cricket then active a market and click on + in the list market
     * 3. Navigate to favourite page
     * 4. Back to market page and click - of the added event
     * @expect: 1 Verify event is add and remove from favourite page
     */
    @Test(groups = {"smoke"})
    public void Favourite_Page_TC_005(){
        log("@title: Verify can remove event from favourite page");
        log("Step1: 1. Click on Favourite");
        SportPage page = memberHomePage.navigateCricket();
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);
        if (Objects.isNull(odd)) {
            log ("DEBUG: There is no event");
            return;
        }
        page.addRemoveFavorite(odd.getEventIndex(),true);

        log("Step 3. Navigate to favourite page");
        FavoritePage favoritePage = page.navigateFavourite();
        // Loop here
        int index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");

        log("Verify 1 Verify event is add into favourite page");
        Assert.assertTrue(index!=0,"FAILED! The market-event not display in favorite page");

        log("Step 4. remove market from favourite page");
        favoritePage.removeMarket(odd.getEventName(),"Match Odds");

        log("Verify 3 Verify event is removed from favourite page");
        index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");
        Assert.assertTrue(index==0,"FAILED! The market-event not display in favorite page");

        log("INFO: Executed completely");
    }

    @TestRails(id="1007")
    @Test(groups = {"regression"})
    public void Favourite_Page_TC_1007(){
        log("@title: Verify can add market to Favourites page");
        log("Step1: 1. Select Cricket and click on + in the list event to add event");
        SportPage page = memberHomePage.navigateSoccer();
        Odd odd = page.oddPageControl.getOdd(OddPageControl.Team.HOME, true, false, false,2, 1);

        log("Step1: 2. Navigate to favourite page");
        page.addRemoveFavorite(odd.getEventIndex(),true);

        log("Step1: 3. Verify Page is added to Favorites as normally ");
        FavoritePage favoritePage = page.navigateFavourite();
        int index = favoritePage.getMarketAddIndex(odd.getEventName(),"Match Odds");
        Assert.assertTrue(index!=0,"FAILED! The market-event not display in favorite page");

        log("Cleanup: Remove favourites market added");
        favoritePage.removeMarket(odd.getEventName(),"Match Odds");
    }
}
