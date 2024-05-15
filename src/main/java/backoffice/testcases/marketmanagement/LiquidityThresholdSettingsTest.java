package backoffice.testcases.marketmanagement;

import agentsite.pages.agentmanagement.eventbetsizesetting.EventBetSizeSetting;
import agentsite.ultils.agencymanagement.EventBetSizeSettingUtils;
import backoffice.pages.bo.marketmanagement.LiquidityThresholdSettingsPage;
import baseTest.BaseCaseTest;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Objects;

import static common.AGConstant.*;

public class LiquidityThresholdSettingsTest extends BaseCaseTest {

    /**
     * @title: Validate confirm message display when input Non-live setting
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Operations > Liquidity Threshold Settings
     * 2. Select Golf sport and search the market type: Top US
     * 3. Input the setting in None-live cell: 1 then press enter
     * 4. Click close
     * @expect: 1 Confirm popup display with the title : Update - [Market Type] with  the message: Are you sure to set this non live to setting value ?
     * 2. Verify the confirm popup is closed and the setting is not affect
     */
    @TestRails(id = "628")
    @Test(groups = {"smoke"})
    public void BO_Operations_Liquidity_Threshold_Setting_628() {
        log("@title: Validate confirm message display when input Non-live setting");
        String sportName = "Golf";
        String marketType = "Match Odds";
        log("Step 1. Access Operations > Liquidity Threshold Settings");
        LiquidityThresholdSettingsPage page = backofficeHomePage.navigateLiquidityThresholdSettings();

        log("Step 2. Select Golf sport and search the market type: Top US");
        page.searchSport(sportName);
        page.selectSport(1);
        page.searchMarketType(marketType);

        log("Step 3. Input the setting in None-live cell: 1 then press enter");
        page.setThreshold(marketType, "1", "");

        log("Verify 1 Confirm popup display with the title : Update - [Market Type] with  the message: Are you sure to set this non live to setting value ?");
        page.popup.isDisplayed(1);
        Assert.assertEquals(page.popup.getContent(), String.format("Are you sure to set this non live to %s ?", "1"), "FAILED! Confirm message is incorrect");

        log("Step 4. Click close");
        page.popup.clickCloseBtn();

        log("Verify 2. Verify the confirm popup is closed and the setting is not affect");
        Assert.assertFalse(page.popup.isDisplayed(), "FAILED! The popup not display after close");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate confirm message display when input Live setting
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Operations > Liquidity Threshold Settings
     * 2. Select Golf sport and search the market type: Top US
     * 3. Input the setting in live cell: 1 then press enter
     * 4. Click close
     * @expect: 1 Confirm popup display with the title : Update - [Market Type] with  the message: Are you sure to set this live to setting value ?
     * 2. Verify the confirm popup is closed and the setting is not affect
     */
    @TestRails(id = "629")
    @Test(groups = {"smoke"})
    public void BO_Operations_Liquidity_Threshold_Setting_629() {
        log("@title: Validate confirm message display when input Live setting");
        String sportName = "Golf";
        String marketType = "Handicap";
        log("Step 1. Access Operations > Liquidity Threshold Settings");
        LiquidityThresholdSettingsPage page = backofficeHomePage.navigateLiquidityThresholdSettings();

        log("Step 2. Select Golf sport and search the market type: Top US");
        page.searchSport(sportName);
        page.selectSport(1);
        page.searchMarketType(marketType);
        page.waitSpinIcon();
        log("Step 3. Input the setting in Live cell: 1 then press enter");
        page.setThreshold(marketType, "", "1");

        log("Verify 1 Confirm popup display with the title : Update - [Market Type] with  the message: Are you sure to set this live to setting value ?");
        Assert.assertEquals(page.popup.getContent(), String.format("Are you sure to edit this live from 20,000 to %s ?", "1"), "FAILED! Confirm message is incorrect");

        log("Step 4. Click close");
        page.popup.clickCloseBtn();

        log("Verify 2. Verify the confirm popup is closed and the setting is not affect");
        Assert.assertFalse(page.popup.isDisplayed(), "FAILED! The popup not display after close");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate odds is blur and unclick able when total match of the market not reach the setting for Non-live market
     * @pre-condition: 1. Login BO
     * 2. The agent of member account is active Liquidity Threshold
     * 3. Have a non-live Soccer event
     * @steps: 1. Access Operations > Liquidity Threshold Settings
     * 2. Select soccer get non-live liquidity threshold setting for match odds market
     * 3. Login member site and check match odds market of event
     * @expect: 1. Verify odds is blur and unclick able if total matched value of match odds does not reach the setting
     */
    @TestRails(id = "630")
    @Test(groups = {"regression"})
    public void BO_Operations_Liquidity_Threshold_Setting_630() {
        log("@title: Validate odds is blur and unclick able when total match of the market not reach the setting for Non-live market");
        log("2. The agent of member account is active Liquidity Threshold");
        log("3. Have a non-live Soccer event");
        //TODO: implement this case
        Assert.assertTrue(false, "Need to implement this case");
        log("Step 1. Access Operations > Liquidity Threshold Settings");

        log("Step 2. Select soccer get non-live liquidity threshold setting for match odds market");


        log("Step  3. Login member site and check match odds market of event");

        log("Verify 1. Verify odds is blur and unclick able if total matched value of match odds does not reach the setting");


        log("INFO: Executed completely");
    }

    /**
     * @title: Validate odds is blur and unclick able when total match of the market not reach the setting for Live market
     * @pre-condition: 1. Login BO
     * 2. The agent of member account is active Liquidity Threshold
     * 3. Have a non-live Soccer event
     * @steps: 1. Access Operations > Liquidity Threshold Settings
     * 2. Select soccer get Live liquidity threshold setting for match odds market
     * 3. Login member site and check match odds market of event
     * @expect: 1. Verify odds is blur and unclick able if total matched value of match odds does not reach the setting
     */
    @TestRails(id = "631")
    @Parameters({"satMemberLoginID", "memberPassword", "username", "password"})
    @Test(groups = {"regression"})
    public void BO_Operations_Liquidity_Threshold_Setting_631(String satMemberLoginID, String memberPassword, String username, String password) throws Exception{
        log("@title: Validate odds is blur and unclick able when total match of the market not reach the setting for Live market");
        _brandname = "satsport";
        loginMember(_brandname, satMemberLoginID, memberPassword);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = sportPage.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }

        log("Step 1. Access Operations > Liquidity Threshold Settings");
        loginBackoffice(username, password, true);
        LiquidityThresholdSettingsPage liquidPage = backofficeHomePage.navigateLiquidityThresholdSettings();
        try{
            log("Step 2. Select soccer get Live liquidity threshold setting for match odds market");
            liquidPage.setThreshold(SPORT_SOCCER, event.getMarketName(), "1", "");
            log("Step 3. Login member site and check match odds market of event");
            loginMember(_brandname, satMemberLoginID, memberPassword);
            memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
            MarketPage marketPage = sportPage.clickEventName(event.getEventName());
            Market market = marketPage.marketOddControl.getMarket(event, 1, false);
            log("Verify 1. Verify odds is blur and unclick able if total matched value of match odds does not reach the setting\n" +
                    "If there is no setting for Soccer - Match odds - Live -> Odds is clickable by default");
           Assert.assertTrue(!market.getBtnOdd().isEnabled(), "FAILED! Market was not blur");
            log("INFO: Executed completely");
        }finally {
            log("@Precondition: Re-assign value of Non Live");
            loginBackoffice(username, password, true);
            backofficeHomePage.navigateLiquidityThresholdSettings();
            liquidPage.setThreshold(SPORT_SOCCER, event.getMarketName(), "70", "");
        }


    }

}
