package membersite.testcases.funsport.tabexchange;

import org.testng.Assert;
import org.testng.annotations.Test;
import membersite.pages.all.tabexchange.MarketPage;
import membersite.pages.all.tabexchange.components.popups.MyMarketPopup;
import baseTest.BaseCaseMerito;

import java.util.List;

public class MyMarketPopupTest extends BaseCaseMerito {

    /**
     * @title: Validate Total Liability in My market popup is matched with Liability on header menu
     * @pre-condition
     *           1. Login member site
     * Have some matched bet
     * @steps:  1. Click on My market and sum total liability
     * @expect: Verify Total Liability in My market popup is matched with Liability on header menu
     */
    @Test(groups = {"smoke"})
    public void MyMarketPopup_001(){
        log("@title: Validate Total Liability in My market popup is matched with Liability on header menu");

        log("Step 1. Click on My market and sum total liability");
        MyMarketPopup myMarketPopup  =  memberHomePage.fsHeaderControl.openMyMarketPopup();
        String totalLiability = myMarketPopup.totalLiability();

        log("Verify 1: Total Liability in My market popup is matched with Liability on header menu");
        Assert.assertEquals(totalLiability, memberHomePage.lblLiabilityValue.getText(),"FAILED! Total liability in My Market popup not match with Liability");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate correct market page display when click on a market link
     * @pre-condition
     *           1. Sign in
     * @steps: 1. Click on My market
     * 2. Click on a Market
     * @expect: 1. Verify correct market display
     */
    @Test(groups = {"smoke"})
    public void MyMarketPopup_002(){
        log("@title: Validate correct market page display when click on a market link");

        log("Step: 1. Click on My market");
        MyMarketPopup myMarketPopup  =  memberHomePage.fsHeaderControl.openMyMarketPopup();

        log("Step: 2. Click on a Market");
        List<String> marketInfoLst = myMarketPopup.getMarketInfo(1);
        MarketPage marketPage = myMarketPopup.navigateToMarket(marketInfoLst.get(2));
        String ulr = myMarketPopup.getMarketURL(1);

        log("Verify: 1. Verify correct market display");
        Assert.assertTrue(marketPage.getPageUrl().contains(ulr),"FAILED! Url of the market not correct");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate liability matched with data in Matched bet
     * @pre-condition
     *           1. Sign in
     * @steps: 1. Click on My market
     * 2. Click on a Market
     * @expect: Validate liability matched with data in Matched bet
     */
    @Test(groups = {"smoke"})
    public void MyMarketPopup_003(){
        log("@title: Validate liability matched with data in Matched bet");

        log("Step 1. Click on My market");
        MyMarketPopup myMarketPopup  = memberHomePage.fsHeaderControl.openMyMarketPopup();

        log("Step2. Click on a Market");
        List<String> marketInfoLst = myMarketPopup.getMarketInfo(1);
        MarketPage marketPage = myMarketPopup.navigateToMarket(1);

        log("Verify: Validate liability matched with data in Matched bet");

        log("INFO: Executed completely");
    }

}
