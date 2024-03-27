package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.element.common.Label;
import common.MemberConstants;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.objects.sat.Order;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import membersite.pages.popup.RulePopup;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;
import java.util.Objects;

import static common.AGConstant.*;

public class MarketPageTest extends BaseCaseTest {
    @TestRails(id = "1074")
    @Test(groups = {"regression"})
    @Parameters({"password", "skinName"})
    public void MB_Change_Password_TC1074(String password, String skinName) throws Exception {
        log("@title:Validate can open rule popup");
        log("Step 1 Active any market");
        log("Step 2.Click on Rule button");
        Event event = memberHomePage.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = memberHomePage.clickEventName(event.getEventName());
        RulePopup rulePopup = marketPage.openRules();
        log("Verify 1. Rule popup display with the title : Market name - Rules");
        Assert.assertTrue(rulePopup.getTitle().contains("Match Odds - Rules"));

        log("INFO: Executed completely");
    }

    @TestRails(id = "982")
    @Test(groups = {"smoke"})
    public void Market_Page_982() {
        log("@title Validate odds remove out bet slip when double click on odds");
        log("Step 1. Click on Soccer and click on any event");
        log("Step 1. Click Soccer menu");
        SportPage page = memberHomePage.navigateSportHeaderMenu("Soccer");
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEvent(event);
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        log("Step 2. Click on any odds button in market page");
        market.getBtnOdd().click();

        log("Verify: 1. Odds is added to bet slip");
        Order bet = marketPage.betsSlipContainer.getBet(0);
        int countBet = marketPage.betsSlipContainer.getBet().size();
        Assert.assertEquals(bet.getSelectionName(), market.getSelectionName(), String.format("ERROR: Expected selection name in bet slip is %s but found %s", market.getSelectionName(), bet.getSelectionName()));
        Assert.assertEquals(bet.getOdds(), market.getBtnOdd().getText(), String.format("ERROR: Expected Odds in bet slip is %s but found %s", market.getBtnOdd().getText(), bet.getOdds()));
        Assert.assertTrue(countBet == 1, String.format("ERROR: There are some bets (%d) added. Expected is just one", countBet));

        log("Step 3. Re-click on the odds in step 2");
        market.getBtnOdd().click();

        log("Verify: 2. Odds is removed out bet slip after re-clicking");
        Assert.assertEquals(page.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY, String.format("ERROR: Expected empty bet slip display %s but found %s", page.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY));

        log("INFO: Executed Completely!");
    }

    @TestRails(id = "980")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_980(){
        log("@title: Validate Odds display correct when clicking on the corresponding odds of all Back selection");
        log("Step 1. Click on Home page and click on any event");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_TENNIS);
        Event event = sportPage.eventContainerControl.getEventRandom(true, false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no events available");
            return;
        }
        MarketPage marketPage = sportPage.clickEvent(event);
        List<Label> lblBackOdds = marketPage.marketOddControl.getAllOddsListLabel(true);
        log("Step 2. Click on All Back Odds button of all selections");
        log("Verify 1: Selection will be added in bet slip and Back odds value is corresponding updated");
        marketPage.verifyAllSelectionDisplayOnBetSlip(event, lblBackOdds.size(), true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "981")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_981(){
        log("@title: Validate Odds display correct when clicking on the corresponding odds of all Lay selections");
        log("Step 1. Click on Home page and click on any event");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_TENNIS);
        Event event = sportPage.eventContainerControl.getEventRandom(true, false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no events available");
            return;
        }
        MarketPage marketPage = sportPage.clickEvent(event);
        List<Label> lblBackOdds = marketPage.marketOddControl.getAllOddsListLabel(false);
        log("Step 2. Click on All Lay Odds button of all selections");
        log("Verify 1: Selection will be added in bet slip and Lay odds value is corresponding updated");
        marketPage.verifyAllSelectionDisplayOnBetSlip(event, lblBackOdds.size(), false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no events available");
            return;
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "983")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_983() {
        log("@title: Validate Clear All button works");
        log("Step 1: Click on any event");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_TENNIS);
        Event event = sportPage.eventContainerControl.getEventRandom(true, false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no events available");
            return;
        }
        log("Step 2: Click on any odds");
        MarketPage marketPage = sportPage.clickEvent(event);
        marketPage.verifyAllSelectionDisplayOnBetSlip(event, 1, true);
        log("Step 3: Click on Clear all button");
        marketPage.betsSlipContainer.clearAll();
        log("Verify 1: All bet in bet slip is cleared");
        Assert.assertEquals(marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY,String.format("ERROR: Expected empty bet slip display %s but found %s",marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY));
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "984")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_984() {
        log("@title:  Validate default message display when there is no  bet");
        log("Step 1. Click on any event to open market page");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_TENNIS);
        Event event = sportPage.eventContainerControl.getEventRandom(true, false);
        log("Step 2: Click on any odds");
        MarketPage marketPage = sportPage.clickEvent(event);
        log("Verify 1: Bet Slip display the message \"Click on the odds to add selection to the Bet Slip.\"");
        Assert.assertEquals(marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY,String.format("ERROR: Expected empty bet slip display %s but found %s",marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY));
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "985")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_011(){
        log("@title: Validate can update fast button");
        Assert.assertTrue(false,"Update this case");
//        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
//        String maxBet = BetUtils.getMaxBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
//        List<String> quickStake = Arrays.asList(minBet,
//                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
//                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
//                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
//                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
//                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
//                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
//                maxBet);
//        log("Step 1.Active any market page");
//        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
//        Event event = page.eventContainerControl.getEventRandom(false,false);
//        if(Objects.isNull(event)) {
//            log("DEBUG: There is no event available");
//            return;
//        }
//        page.clickEvent(event);
//        page.marketContainerControl.waitControlLoadCompletely(5);
//
//        log("Step 2. Update stake with valid value in range [min, max]");
//        log("Step 3. Click Save button");
//        page.betSlipControl.openEditStake().updateStake(quickStake,true);
//
//        log("Verify 1. Edit stake is disappear when successfully save");
//        Assert.assertFalse(page.betSlipControl.isEditStakeControlDisplay(), "ERROR! Edit Stake popup is no longer display but found " +page.betSlipControl.isEditStakeControlDisplay());
//
//        log("Step 4. Click on any odds button");
//        page.marketContainerControl.getMarket(event,1,true).getBtnOdd().click();
//        List<String> actualQuickStake = page.betSlipControl.getListFastButton();
//
//        log("Verify 2. Fast  button in bet slip display as expected");
//        Assert.assertEquals(actualQuickStake,quickStake,"ERROR! Quick stake not match as expected");

        log("INFO: Executed Completely");
    }

    @TestRails(id = "986")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_012(){
        log("Step 1. Validate Stake textbox display correct value when clicking on the corresponding fast button");
        log("Step 2. Click on any odds");
        Assert.assertTrue(false,"Update this case");
//        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
//        Event event = page.eventContainerControl.getEventRandom(false,false);
//        if(Objects.isNull(event)) {
//            log("DEBUG: There is no event available");
//            return;
//        }
//        page.clickEvent(event);
//        page.marketContainerControl.waitControlLoadCompletely(5);
//        page.marketContainerControl.getMarket(event,1,true).getBtnOdd().click();
//
//        log("Step  3. Click on all fast buttons");
//        log("Verify 1. Stake textbox update data correctly");
//        boolean expected = page.betSlipControl.isStakeDisplayAsClickingOnFastButton();
//
//        Assert.assertTrue(expected, "ERROR! Expected stake not match when clicking on fast button "+ expected);
        log("INFO: Executed Completely");
    }

    @TestRails(id = "987")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_015(){
        log("@title: Validate Cancel button in Edit Stake popup work");
        log("Step 1.Active any market page");
        Assert.assertTrue(false,"Update this case");
//        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
//        Event event = page.eventContainerControl.getEventRandom(false,false);
//        if(Objects.isNull(event)) {
//            log("DEBUG: There is no event available");
//            return;
//        }
//        page.clickEvent(event);
//        page.marketContainerControl.waitControlLoadCompletely(5);
//
//        log("Step 2. Click on Edit Stake button");
//        log("Verify 1. Edit stake popup is opened");
//        page.betSlipControl.openEditStake();
//        Assert.assertTrue(page.betSlipControl.isEditStakeControlDisplay(), "ERROR! Edit Stake popup is no longer display but found " +page.betSlipControl.isEditStakeControlDisplay());
//
//        log("Step 3. Click Cancel button");
//        log("Verify 2. Edit stake popup is disappear");
//        page.betSlipControl.editStakeControl.cancelEditStake();
//        Assert.assertFalse(page.betSlipControl.isEditStakeControlDisplay(), "ERROR! Edit Stake popup is no longer display but found " +page.betSlipControl.isEditStakeControlDisplay());

        log("INFO: Executed Completely");
    }

    @TestRails(id = "996")
    public void HeaderSection_TC996() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }

    @TestRails(id = "997")
    public void HeaderSection_TC997() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "967")
    public void HeaderSection_TC967() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }

    @TestRails(id = "968")
    public void HeaderSection_TC968() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "969")
    public void HeaderSection_TC969() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "970")
    public void HeaderSection_TC970() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }

    @TestRails(id = "971")
    public void HeaderSection_TC971() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "972")
    public void HeaderSection_TC972() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }

    @TestRails(id = "973")
    public void HeaderSection_TC973() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "974")
    public void HeaderSection_TC974() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "975")
    public void HeaderSection_TC975() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "976")
    public void HeaderSection_TC976() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "977")
    public void HeaderSection_TC977() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }

    @TestRails(id = "978")
    public void HeaderSection_TC978() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "979")
    public void HeaderSection_TC979() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "988")
    public void HeaderSection_TC988() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "989")
    public void HeaderSection_TC989() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "990")
    public void HeaderSection_TC990() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "991")
    public void HeaderSection_TC991() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "992")
    public void HeaderSection_TC992() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "993")
    public void HeaderSection_TC993() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "994")
    public void HeaderSection_TC994() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
    @TestRails(id = "995")
    public void HeaderSection_TC995() {
        //TODO: implement this test case
        log("INFO: Executed completely");
    }
}

