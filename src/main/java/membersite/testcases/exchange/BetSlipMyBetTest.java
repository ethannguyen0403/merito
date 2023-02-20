package membersite.testcases.exchange;

import baseTest.BaseCaseMerito;
import baseTest.BaseCaseTest;
import com.paltech.element.common.Label;
import com.paltech.utils.StringUtils;
import common.MemberConstants;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.objects.sat.Order;
import membersite.pages.all.tabexchange.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BetSlipMyBetTest extends BaseCaseTest {/*
    *//**
     * @title Validate Odds display correct when clicking on the corresponding odds of all BAck selection
     * @Precondition:   1. Login member site
     * @Step            1. Click on Home page and click on any event
     *                  2. Click on All Back Odds button of all selections
     * @Expected        1. Selection will be added in bet slip and Back odds value is corresponding updated
     *//*
    @TestRails(id = "980")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_003(){
        log("@title: Validate Odds display correct when clicking on the corresponding odds of all Back selection");
        log("Step 1. Click on Home page and click on any event");
        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);
        Market market = page.marketContainerControl.getMarket(event,1,true);
        log("Step 2. Click on All Back Odds button of all selections");
        List<Label> lblBackOdds = page.marketContainerControl.getOddsListLabel(1,true);
        Order expectedOrder = new Order.Builder()
                .selectionName(market.getSelectionName())
                .build();
        log("Verify: Selection will be added in bet slip and Back odds value is corresponding updated");
        for(int i =0; i<lblBackOdds.size(); i++)
        {
           expectedOrder.setOdds(lblBackOdds.get(i).getText());
           lblBackOdds.get(i).click();
           Order bet = page.betSlipControl.getBet(0);
           Assert.assertEquals(bet.getSelectionName(),expectedOrder.getSelectionName(),String.format("ERROR: Expected selection name in bet slip is %s but found %s", expectedOrder.getSelectionName(), bet.getSelectionName()));
           Assert.assertEquals(bet.getOdds(),expectedOrder.getOdds(),String.format("ERROR: Expected Odds in bet slip is %s but found %s", expectedOrder.getOdds(), bet.getOdds()));
        }
        log("Verify: Only 1 bet added in bet slips when click on multiple Back Odds in the same selection ");
        int countBet = page.betSlipControl.getBet().size();
        Assert.assertTrue(countBet==1, String.format("ERROR: There are some bets(%d)added. Expected is 1",countBet));

        log("INFO: Executed completely");
    }

    *//**
     * @title Validate Odds display correct when clicking on the corresponding odds of all Lay selections
     * @Precondition:   1. Login member site
     * @Step            1. Click on Home page and click on any event
     *                  2. Click on All Lay Odds button of all selections
     * @Expected        1. Selection will be added in bet slip and Lay odds is corresponding updated
     *//*
    @TestRails(id = "981")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_004(){
        log("@title: Validate Odds display correct when clicking on the corresponding odds of all Lay selections");
        log("Step 1. Click on Home page and click on any event");
        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no events available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);
        Market market = page.marketContainerControl.getMarket(event,1,false);
        Order expectedOrder = new Order.Builder()
                .selectionName(market.getSelectionName())
                .build();

        log("Step 2. Click on All Lay Odds button of all selections");
        log("Verify 1: Selection will be added in bet slip and Lay odds is corresponding updated");
        List<Label> lblLayOdds = page.marketContainerControl.getOddsListLabel(1,false);
        for(int i =0; i<lblLayOdds.size(); i++)
        {
            expectedOrder.setOdds(lblLayOdds.get(i).getText());
            lblLayOdds.get(i).click();
            Order bet = page.betSlipControl.getBet(0);
            Assert.assertEquals(bet.getSelectionName(),expectedOrder.getSelectionName(),String.format("ERROR: Expected selection name in bet slip is %s but found %s", expectedOrder.getSelectionName(), bet.getSelectionName()));
            Assert.assertEquals(bet.getOdds(),expectedOrder.getOdds(),String.format("ERROR: Expected Odds in bet slip is %s but found %s", expectedOrder.getOdds(), bet.getOdds()));
        }
        log("Verify 2: Only 1 bet added in bet slips when click on multiple Back Odds in the same selection ");
        int countBet = page.betSlipControl.getBet().size();
        Assert.assertTrue(countBet==1, String.format("ERROR: There are some bets(%d)added. Expected is 1",countBet));

        log("INFO: Executed completely");
    }

    *//**
     * @title Validate odds remove out bet slip when double click on odds
     * @Precondition:   1. Login member site
     * @Step            1. Click on Soccer and click on any event
     *                  2. Click on any odds button in market page
     *                  3. Re-click on the odds in step 2
     * @Expected        1. Odds is added to bet slip
     *                  2. Odds is removed out bet slip after re-clicking
     *//*
    @TestRails(id = "982")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_005() {
        log("@title Validate odds remove out bet slip when double click on odds");
        log("Step 1. Click on Soccer and click on any event");
        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 2. Click on any odds button in market page");
        Market market = page.marketContainerControl.getMarket(event,1,false);
        market.getBtnOdd().click();

        log("Verify: 1. Odds is added to bet slip");
        Order bet = page.betSlipControl.getBet(0);
        int countBet = page.betSlipControl.getBet().size();
        Assert.assertEquals(bet.getSelectionName(),market.getSelectionName(),String.format("ERROR: Expected selection name in bet slip is %s but found %s", market.getSelectionName(), bet.getSelectionName()));
        Assert.assertEquals(bet.getOdds(),market.getBtnOdd().getText(),String.format("ERROR: Expected Odds in bet slip is %s but found %s", market.getBtnOdd().getText(), bet.getOdds()));
        Assert.assertTrue(countBet==1, String.format("ERROR: There are some bets (%d) added. Expected is just one",countBet));

        log("Step 3. Re-click on the odds in step 2");
        market.getBtnOdd().click();

        log("Verify: 2. Odds is removed out bet slip after re-clicking");
        Assert.assertEquals(page.betSlipControl.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY,String.format("ERROR: Expected empty bet slip display %s but found %s",page.betSlipControl.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY));

        log("INFO: Executed Completely!");
    }

    *//**
     * @title Validate Clear All button works
     * @Precondition:   1. Login member site
     * @Step            1. Click on any event and click on any odds
     *                  2. Click on Clear all button
     * @Expected        1. All bet in bet slip is cleared
     *//*
    @TestRails(id = "983")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_008() {
        log("@title: Validate Clear All button works");
        log("Step 1. Click on any event and click on any odds");
        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);
        page.marketContainerControl.getMarket(event,1,true).getBtnOdd().click();

        log("Step 2. Click on Clear all button");
        page.betSlipControl.clearAll();

        log("Verify: 1. All bet in bet slip is cleared");
        Assert.assertEquals(page.betSlipControl.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY,String.format("ERROR: Expected empty bet slip display %s but found %s",page.betSlipControl.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY));
        log("INFO: Executed Completely!");
    }

    *//**
     * @title Validate default message display when there is no bet
     * @Precondition:  1. Login member site
     * @Step           1. Click on any event to open market page
     * @Expected       1. Bet Slip display the message "Click on the odds to add selection to the Bet Slip."
     *//*
    @TestRails(id = "984")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_010() {
        log("@title:  Validate default message display when there is no  bet");
        log("Step 1. Click on any event to open market page");
        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);

        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.betSlipControl.lblNoBetInBetSlipMessage.isTextDisplayed("Constants.BetSlip.SMG_BET_SLIP_EMPTY",3);

        log("Verify: 1. Bet Slip display the message \"Click on the odds to add selection to the Bet Slip.");
        Assert.assertEquals(page.betSlipControl.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY,String.format("ERROR: Expected empty bet slip display %s but found %s",page.betSlipControl.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY));

        log("INFO: Executed Completely!");
    }

    *//**
     * @title Validate can update fast button
     * @Precondition:  1. Login member site
     * @Step           1. Active any market page
     *                 2. Update stake with valid value in range [min, max]
     *                 3. Click Save button
     *                 4. Click on any odds button
     * @Expected        1. Edit stake is disappear when successfully save
     *                  2. Fast  button in bet slip display as expected
     *//*
    @TestRails(id = "985")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_011(){
        log("@title: Validate can update fast button");
        String minBet = BetUtils.getMinBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        String maxBet = BetUtils.getMaxBet(SportPage.Sports.SOCCER, SportPage.BetType.BACK);
        List<String> quickStake = Arrays.asList(minBet,
                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
                StringUtils.generateNumeric(Integer.parseInt(minBet),Integer.parseInt(maxBet)),
                maxBet);
        log("Step 1.Active any market page");
        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 2. Update stake with valid value in range [min, max]");
        log("Step 3. Click Save button");
        page.betSlipControl.openEditStake().updateStake(quickStake,true);

        log("Verify 1. Edit stake is disappear when successfully save");
        Assert.assertFalse(page.betSlipControl.isEditStakeControlDisplay(), "ERROR! Edit Stake popup is no longer display but found " +page.betSlipControl.isEditStakeControlDisplay());

        log("Step 4. Click on any odds button");
        page.marketContainerControl.getMarket(event,1,true).getBtnOdd().click();
        List<String> actualQuickStake = page.betSlipControl.getListFastButton();

        log("Verify 2. Fast  button in bet slip display as expected");
        Assert.assertEquals(actualQuickStake,quickStake,"ERROR! Quick stake not match as expected");

        log("INFO: Executed Completely");
    }

    @TestRails(id = "986")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_012(){
        log("Step 1. Validate Stake textbox display correct value when clicking on the corresponding fast button");
        log("Step 2. Click on any odds");
        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);
        page.marketContainerControl.getMarket(event,1,true).getBtnOdd().click();

        log("Step  3. Click on all fast buttons");
        log("Verify 1. Stake textbox update data correctly");
        boolean expected = page.betSlipControl.isStakeDisplayAsClickingOnFastButton();

        Assert.assertTrue(expected, "ERROR! Expected stake not match when clicking on fast button "+ expected);
        log("INFO: Executed Completely");
    }

    *//**
     * @title Validate Cancel button in Edit Stake popup work
     * @Precondition:  1. Login member site
     * @Step           1. Active any market page
     *                 2. Click on Edit Stake button
     *                 3. Click Cancel button
     * @Expected       1. Edit stake popup is disappear
     *//*
    @TestRails(id = "987")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_015(){
        log("@title: Validate Cancel button in Edit Stake popup work");
        log("Step 1.Active any market page");
        SportPage page =  memberHomePage.apLeftMenuControl.clickLeftMenuItem("Soccer",SportPage.class);
        Event event = page.eventContainerControl.getEventRandom(false,false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        page.clickEvent(event);
        page.marketContainerControl.waitControlLoadCompletely(5);

        log("Step 2. Click on Edit Stake button");
        log("Verify 1. Edit stake popup is opened");
        page.betSlipControl.openEditStake();
        Assert.assertTrue(page.betSlipControl.isEditStakeControlDisplay(), "ERROR! Edit Stake popup is no longer display but found " +page.betSlipControl.isEditStakeControlDisplay());

        log("Step 3. Click Cancel button");
        log("Verify 2. Edit stake popup is disappear");
        page.betSlipControl.editStakeControl.cancelEditStake();
        Assert.assertFalse(page.betSlipControl.isEditStakeControlDisplay(), "ERROR! Edit Stake popup is no longer display but found " +page.betSlipControl.isEditStakeControlDisplay());

        log("INFO: Executed Completely");
    }*/
}

