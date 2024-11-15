package membersite.testcases.exchange;

import baseTest.BaseCaseTest;
import com.paltech.element.common.Label;
import common.MemberConstants;
import membersite.controls.EditStakeControl;
import membersite.objects.AccountBalance;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.objects.sat.Order;
import membersite.pages.MarketPage;
import membersite.pages.RacingPage;
import membersite.pages.SportPage;
import membersite.pages.popup.RulePopup;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.*;
import static common.MemberConstants.*;
import static common.MemberConstants.HomePage.SPORT_ID;

public class MarketPageTest extends BaseCaseTest {
    @TestRails(id = "72889")
    @Test(groups = {"http_request"})
    public void MarketPage_72889() {
        log("@title: There is no http responded error returned");
        log("Step 1. Login and navigate to Market Page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        memberHomePage.clickEventName(event.getEventName());

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    @TestRails(id = "1074")
    @Test(groups = {"regression"})
    public void MB_Change_Password_TC1074() {
        log("@title:Validate can open rule popup");
        log("Step 1 Active any market");
        log("Step 2.Click on Rule button");
        SportPage page = memberHomePage.navigateSportHeaderMenu("Soccer");
        Event event = page.eventContainerControl.getEventRandom(false, false);
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
        MarketPage marketPage = page.clickEventName(event.getEventName());
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
    @Test(groups = {"smoke","smoke_dev"})
    public void FE_BetSlipMyBet_980(){
        log("@title: Validate Odds display correct when clicking on the corresponding odds of all Back selection");
        log("Step 1. Click on Home page and click on any event");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = sportPage.eventContainerControl.getEventRandom(false, false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no events available");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        List<Label> lblBackOdds = marketPage.marketOddControl.getAllOddsValueByMarket("Match Odds",true);
        log("Step 2. Click on All Back Odds button of all selections");
        log("Verify 1: Selection will be added in bet slip and Back odds value is corresponding updated");
        marketPage.verifySelectedSelectionDisplayOnBetSlip(event, lblBackOdds.size(), true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "981")
    @Test(groups = {"smoke","smoke_dev"})
    public void FE_BetSlipMyBet_981(){
        log("@title: Validate Odds display correct when clicking on the corresponding odds of all Lay selections");
        log("Step 1. Click on Home page and click on any event");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_TENNIS);
        Event event = sportPage.eventContainerControl.getEventRandom(true, false);
        if(Objects.isNull(event)) {
            log("DEBUG: There is no events available");
            return;
        }
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        List<Label> lblBackOdds = marketPage.marketOddControl.getAllOddsValueByMarket("Match Odds",false);
        log("Step 2. Click on All Lay Odds button of all selections");
        log("Verify 1: Selection will be added in bet slip and Lay odds value is corresponding updated");
        marketPage.verifySelectedSelectionDisplayOnBetSlip(event, lblBackOdds.size(), false);
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
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        log("Step 3: Click on Clear all button");
        marketPage.betsSlipContainer.clearAll();
        log("Verify 1: All bet in bet slip is cleared");
        Assert.assertEquals(marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY,String.format("ERROR: Expected empty bet slip display %s but found %s",marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY));
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "984")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.5.0"})
    public void FE_BetSlipMyBet_984() {
        log("@title:  Validate default message display when there is no bet");
        log("Step 1. Click on any event to open market page");
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_TENNIS);
        Event event = sportPage.eventContainerControl.getEventRandom(false, false);
        log("Step 2: Click on any odds");
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        marketPage.bannerPopup.waitForControlInvisible();
        log("Verify 1: Bet Slip display the message \"Click on the odds to add selection to the Bet Slip.\"");
        Assert.assertEquals(marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY,String.format("ERROR: Expected empty bet slip display %s but found %s",marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY));
        log("INFO: Executed Completely!");
    }

    @TestRails(id = "985")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_985(){
        log("@title: Validate can update fast button");
        log("Step 1: Navigate to Market page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEventName(event.getEventName());
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        log("Step 2: Click on Edit stake fast button on Bet slip");
        EditStakeControl editStakeControl = marketPage.betsSlipContainer.openEditStake();

        log("Step 3: Update stake with valid value in range [min, max]");
        log("Step 4: Click Save button");
        List<String> newListStakeFast = editStakeControl.defineValidListStake();
        editStakeControl.updateStake(newListStakeFast, true);

        log("Verify 1: Edit stake is disappear when successfully save");
        Assert.assertTrue(!marketPage.betsSlipContainer.isEditStakeControlDisplay(), "FAILED! Edit stake still displayed after saving.");
        log("Step 5: Click on any odds button");
        market.getBtnOdd().click();
        log("Verify 2: Fast button in bet slip display as expected with new stake value after updated");
        Assert.assertEquals(marketPage.betsSlipContainer.getListFastButton(), newListStakeFast,
                "FAILED! List stake fast is not updated correctly");
        log("INFO: Executed Completely");
    }

    @TestRails(id = "986")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_986(){
        log("@title: Validate Stake textbox display correct value when clicking on the corresponding fast button");
        log("Step 1: Navigate to Market page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEventName(event.getEventName());
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        log("Step 2: Click on any odds button");
        market.getBtnOdd().click();
        log("Step 3: Click on all fast buttons");
        Assert.assertTrue(marketPage.betsSlipContainer.isStakeDisplayAsClickingOnFastButton(), "FAILED! Stake is not displayed correctly when clicking on fast stake button");
        log("INFO: Executed Completely");
    }

    @TestRails(id = "987")
    @Test(groups = {"smoke"})
    public void FE_BetSlipMyBet_987(){
        log("@title: Validate Cancel button in Edit Stake popup work");
        log("Step 1: Navigate to Market page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            log("DEBUG: There is no event available");
            return;
        }
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 2: Click on Edit Stake button");
        EditStakeControl editStakeControl = marketPage.betsSlipContainer.openEditStake();
        Assert.assertTrue(editStakeControl.isDisplayed(), "FAILED! Edit stake popup is NOT displayed");
        log("Step 3: Click Cancel button");
        editStakeControl.cancelEditStake();
        log("Verify 1: Edit stake popup is disappear");
        Assert.assertFalse(editStakeControl.isDisplayed(), "FAILED! Edit stake popup is displayed");
        log("INFO: Executed Completely");
    }

    @TestRails(id = "996")
    @Test(groups = {"smoke_dev"})
    @Parameters({"currency"})
    public void FE_BetSlipMyBet_996(String currency) {
        log("@title: Validate info of unmatched bet in Mini My bet is correctly");
        String odds = "30";
        AccountBalance balance = memberHomePage.getUserBalance();
        String minBet = BetUtils.getMinBet(LBL_CRICKET_SPORT, LBL_BACK_TYPE);

        log("Step 1. Active any market of Cricket");
        SportPage page = memberHomePage.navigateSportHeaderMenu(LBL_CRICKET_SPORT);
        try {
            Event event = page.eventContainerControl.getEventMatchOddsRandom(SPORT_ID.get(LBL_CRICKET_SPORT), currency,false, false);
            if (Objects.isNull(event) || event.getEventName().isEmpty()) {
                log("DEBUG: There is no event available");
                return;
            }
            MarketPage marketPage = page.clickEventName(event.getEventName());

            log("Step 2. Click on any Back odds");
            Market market = marketPage.marketOddControl.getMarket(event, 1, true);
            market.getBtnOdd().click();

            log("Step 3. Input stake and place this bet");
            marketPage.betsSlipContainer.placeBet(odds, minBet);
            List<Order> wagers = marketPage.myBetsContainer.getOrder(false, true, 1);

            log("Verify 1. Bet is display in unmatched section");
            log("Verify 2. Remove unmatched bet icon display in front off selection name");
            log("Verify 3. Selection , Odds, Stake, Profit display correctly");
            Assert.assertEquals(market.getSelectionName(), wagers.get(0).getSelectionName(), "Place on incorrect selection");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(odds)), wagers.get(0).getOdds(), "Incorrect Odds");
            Assert.assertEquals(String.format("%.2f", Double.parseDouble(minBet)), wagers.get(0).getStake(), "Incorrect Stake");
            Assert.assertEquals(wagers.get(0).getStake(), wagers.get(0).getLiability(), "Incorrect Liability");

            log("Verify 4. At in-play will check  on Cancel option by default");
            log("Verify 5. Back bet background is green #C9E6EF");
            log("Verify 6. Account Balance/Outstanding updated correctly");
            AccountBalance balanceExpected = page.getUserBalance();
            String expectedBalance = page.calculateBalance(balance.getBalance(), wagers.get(0).getLiability());
            Assert.assertEquals(balanceExpected.getBalance(), expectedBalance, "Balance update incorrectly after place bet");
            Assert.assertEquals(balanceExpected.getExposure(), String.format("%.2f", Double.parseDouble(balance.getExposure()) - Double.parseDouble(wagers.get(0).getLiability())), "Outstanding update incorrectly after place bet");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Cancel all unmatched bets");
            page.myBetsContainer.cancelAllBetUnmatched();
        }
    }

    @TestRails(id = "997")
    @Test(groups = {"smoke_oldui"})
    public void HeaderSection_TC997() {
        log("@title: Validate Can update unmatched bet");
        throw new SkipException("SKIP! This case should only run on old UI");
    }

    @TestRails(id = "968")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_968() {
        log("@title: Validate that 1Click button");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: On bet slip, observe and Off 1 Click button");
        log("Verify 1: Verify 1Click Off by default");
        Assert.assertTrue(!marketPage.oneClickBettingControl.btn1ClickBet.isSelected(), "FAILED! 1 click button is not off by default");
        log("Verify 2: Verify 1Click button is able to switch on and off");
        Assert.assertTrue(marketPage.oneClickBettingControl.switchToggle1ClickBet(true), "FAILED! 1 click button is not on ");
        Assert.assertFalse(marketPage.oneClickBettingControl.switchToggle1ClickBet(false), "FAILED! 1 click button is not off");
        log("INFO: Executed completely");
    }
    @TestRails(id = "969")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void  FE_BetSlipMyBet_969() {
        log("@title: Validate that user can remove a selected odd successfully on Bet Slip");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click on any odds button");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        log("Step 4: CLick x icon to remove the selected odd");
        marketPage.betsSlipContainer.removeBetByBinIcon(market);
        log("Verify 1: The selected odd is removed successfully");
        Assert.assertEquals(marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY, "FAILED! Bet slip is not empty");
        log("INFO: Executed completely");
    }
    @TestRails(id = "970")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_970() {
        log("@title: \tValidate that a selected odd is removed successfully on Bet Slip by clicking Cancel All selections button");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click on any odds button");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        log("Step 4: Click Cancel All Selections to remove the selected odd");
        marketPage.betsSlipContainer.cancelAllSelections();
        log("Verify 1: The selected odd is removed successfully");
        Assert.assertEquals(marketPage.betsSlipContainer.getEmptyBetMessage(), MemberConstants.BetSlip.SMG_BET_SLIP_EMPTY, "FAILED! Bet slip is not empty");
        log("INFO: Executed completely");
    }

    @TestRails(id = "971")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_971() {
        log("@title: Validate that Place Bet button's behaviors are correct in case of inputted stake and no stake");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click on any odds button");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        log("Verify 1: At step 3, Place bets button is disabled");
        Assert.assertFalse(marketPage.betsSlipContainer.isPlacBetButtonEnable(), "FAILED! Place bet button is enabled");
        log("Step 4: Input stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_BACK_TYPE);
        marketPage.betsSlipContainer.inputStake(minBet);
        log("Verify 2: At step 4, Place bets button is enabled");
        Assert.assertTrue(marketPage.betsSlipContainer.isPlacBetButtonEnable(), "FAILED! Place bet button is disabled");
        log("INFO: Executed completely");
    }
    @TestRails(id = "972")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_972() {
        log("@title: Validate that user cannot place bet when inputting a stake more than maximum stake");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click on any odds button");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        log("Verify 1: At step 3, Place bets button is disabled");
        Assert.assertFalse(marketPage.betsSlipContainer.isPlacBetButtonEnable(), "FAILED! Place bet button is enabled");
        log("Step 4: Input a stake more than maximum stake into Stake text-box");
        String maxBet = BetUtils.getMaxBet(SPORT_SOCCER, market.getMarketName());
        String inputStake = String.valueOf(Integer.valueOf(maxBet) + 10);
        marketPage.betsSlipContainer.inputStake(inputStake);
        log("Verify 2: At step 4, Place bets button is disabled after inputting a stake more than max stake");
        Assert.assertFalse(marketPage.betsSlipContainer.isPlacBetButtonEnable(), "FAILED! Place bet button is enabled");
        log("INFO: Executed completely");
    }

    @TestRails(id = "973")
    @Test(groups = {"smoke_dev", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_973() {
        log("@title: Validate that user cannot place bet when inputting a stake less than minimum stake");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click on any odds button");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        log("Verify 1: At step 3, Place bets button is disabled");
        Assert.assertFalse(marketPage.betsSlipContainer.isPlacBetButtonEnable(), "FAILED! Place bet button is enabled");
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_BACK_TYPE);
        String inputStake = String.valueOf(Integer.valueOf(minBet) - 1);
        marketPage.betsSlipContainer.inputStake(inputStake);
        log("Verify 2: At step 4, Place bets button is disabled after inputting a stake more than max stake");
        Assert.assertFalse(marketPage.betsSlipContainer.isPlacBetButtonEnable(), "FAILED! Place bet button is enabled");
        log("INFO: Executed completely");
    }
    @TestRails(id = "974")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_974() {
        log("@title: Validate that a selected odd at HOME-BACK displays correct data both Odd page and on Bet Slip");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at Home team and Back type");
        Market marketBack = marketPage.marketOddControl.getMarket(event, 1, true);
        marketBack.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_BACK_TYPE);
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketBack, betOrder);
        log("INFO: Executed completely");
    }
    @TestRails(id = "975")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_975() {
        log("@title: Validate that a selected odd at HOME-LAY displays correct data both Odd page and on Bet Slip");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at HOME team and LAY type");
        Market marketLay = marketPage.marketOddControl.getMarket(event, 1, false);
        marketLay.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_LAY_TYPE);
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketLay, betOrder);
        log("INFO: Executed completely");
    }
    @TestRails(id = "976")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_976() {
        log("@title: Validate that a selected odd at DRAW-BACK displays correct data both Odd page and on Bet Slip");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at DRAW team and BACK type");
        Market marketDrawBack = marketPage.marketOddControl.getMarket(event, 3, true);
        marketDrawBack.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_BACK_TYPE);
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketDrawBack, betOrder);
        log("INFO: Executed completely");
    }

    @TestRails(id = "977")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_977() {
        log("@title: Validate that a selected odd at DRAW-LAY displays correct data both Odd page and on Bet Slip");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at DRAW team and LAY type");
        Market marketDrawLay = marketPage.marketOddControl.getMarket(event, 3, false);
        marketDrawLay.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_LAY_TYPE);
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketDrawLay, betOrder);
        log("INFO: Executed completely");
    }

    @TestRails(id = "978")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_978() {
        log("@title: Validate that a selected odd at AWAY-BACK displays correct data both Odd page and on Bet Slip \t");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at AWAY team and BACK type");
        Market marketAwayBack = marketPage.marketOddControl.getMarket(event, 1, true);
        marketAwayBack.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_BACK_TYPE);
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketAwayBack, betOrder);
        log("INFO: Executed completely");
    }
    @TestRails(id = "979")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_979() {
        log("@title: Validate that a selected odd at AWAY-LAY displays correct data both Odd page and on Bet Slip");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at AWAY team and LAY type");
        Market marketAwayBack = marketPage.marketOddControl.getMarket(event, 1, false);
        marketAwayBack.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_LAY_TYPE);
        marketPage.betsSlipContainer.inputStake(minBet);
        Order betOrder = marketPage.betsSlipContainer.getBet(0);
        log("Verify 1: Odd rate on Odd page and on Bet Slip is the same\n" +
                "Selected team on Odd page and on Bet Slip is the same\n" +
                "Event name on Odd page and on Bet Slip is the same\n" +
                "Is in-play of this event is the same on Odd page and Bet Slip");
        marketPage.betsSlipContainer.verifyInfoBetSlipAndOddsPage(marketAwayBack, betOrder);
        log("INFO: Executed completely");
    }
    @TestRails(id = "988")
    @Test(groups = {"smoke_dev", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_988() {
        log("@title: Validate that user can place a bet with HOME - BACK successfully on Market Page");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at Home team and Back type");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_BACK_TYPE);
        marketPage.betsSlipContainer.placeBet(minBet);
        List<Order> betOrder = marketPage.myBetsContainer.getOrder(true, true, 1);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same\n" +
                "Market name on My Bet and on Bet Slip is the same\n" +
                "Event name on My Bet and on Bet Slip is the same\n" +
                "Selected team on My Bet and on Bet Slip is the same\n" +
                "Liability on My Bet and on Bet Slip is the same\n" +
                "Profit on My Bet and on Bet Slip is the same");
        marketPage.myBetsContainer.verifyInfoBetSlipAndOddsPage(market, betOrder.get(0));
        log("INFO: Executed completely");
    }
    @TestRails(id = "989")
    @Test(groups = {"smoke_dev", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_989() {
        log("@title: Validate that user can place a bet with HOME - LAY successfully on Market Page");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at Home team and Lay type");
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_LAY_TYPE);
        marketPage.betsSlipContainer.placeBet(minBet);
        List<Order> betOrder = marketPage.myBetsContainer.getOrder(true, false, 1);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same\n" +
                "Market name on My Bet and on Bet Slip is the same\n" +
                "Event name on My Bet and on Bet Slip is the same\n" +
                "Selected team on My Bet and on Bet Slip is the same\n" +
                "Liability on My Bet and on Bet Slip is the same\n" +
                "Profit on My Bet and on Bet Slip is the same");
        marketPage.myBetsContainer.verifyInfoBetSlipAndOddsPage(market, betOrder.get(0));
        log("INFO: Executed completely");
    }
    @TestRails(id = "990")
    @Test(groups = {"smoke_dev", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_990() {
        log("@title: Validate that user can place a bet with AWAY-BACK successfully on Market Page");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at Away team and Back type");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_BACK_TYPE);
        marketPage.betsSlipContainer.placeBet(minBet);
        List<Order> betOrder = marketPage.myBetsContainer.getOrder(true, true, 1);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same\n" +
                "Market name on My Bet and on Bet Slip is the same\n" +
                "Event name on My Bet and on Bet Slip is the same\n" +
                "Selected team on My Bet and on Bet Slip is the same\n" +
                "Liability on My Bet and on Bet Slip is the same\n" +
                "Profit on My Bet and on Bet Slip is the same");
        marketPage.myBetsContainer.verifyInfoBetSlipAndOddsPage(market, betOrder.get(0));
        log("INFO: Executed completely");
    }
    @TestRails(id = "991")
    @Test(groups = {"smoke_dev", "MER.Maintenance.2024.V.4.0"})
    public void FE_BetSlipMyBet_991() {
        log("@title: Validate that user can place a bet with AWAY-LAY successfully on Market Page");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Click an odd without empty at Away team and Lay type");
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        log("Step 4: Input a stake less than minimum stake into Stake text-box");
        String minBet = BetUtils.getMinBet(SPORT_SOCCER, LBL_LAY_TYPE);
        marketPage.betsSlipContainer.placeBet(minBet);
        List<Order> betOrder = marketPage.myBetsContainer.getOrder(true, false, 1);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same\n" +
                "Market name on My Bet and on Bet Slip is the same\n" +
                "Event name on My Bet and on Bet Slip is the same\n" +
                "Selected team on My Bet and on Bet Slip is the same\n" +
                "Liability on My Bet and on Bet Slip is the same\n" +
                "Profit on My Bet and on Bet Slip is the same");
        marketPage.myBetsContainer.verifyInfoBetSlipAndOddsPage(market, betOrder.get(0));
        log("INFO: Executed completely");
    }
    @TestRails(id = "992")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void MarketPage_TC992() {
        log("@title: Validate that forecast/ liability value display correctly when place back bet on a selection on Market Page");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_TENNIS);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Place a matched back bet");
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        String minBet = BetUtils.getMinBet(SPORT_TENNIS, LBL_BACK_TYPE);

        List<ArrayList<String>> foreCastInfoBefore = marketPage.marketOddControl.getUIForeCast(market.getMarketName());
        marketPage.placeBet(market, minBet);
        List<Order> betOrder = marketPage.myBetsContainer.getOrder(true, true, 1);
        List<ArrayList<String>> foreCastInfoAfter = marketPage.marketOddControl.getUIForeCast(market.getMarketName());
        log("Verify 1: Verify forecast display correct on the selection has bet placed correct:\n" +
                "Display profit for under placed selection\n" +
                "Display liability of the bet under other selections");
        marketPage.verifyForeCastIsCorrect(foreCastInfoBefore, foreCastInfoAfter, betOrder.get(0));
        log("INFO: Executed completely");
    }
    @TestRails(id = "993")
    @Test(groups = {"smoke_dev", "MER.Maintenance.2024.V.4.0"})
    public void MarketPage_TC993() {
        log("@title: Validate that forecast/ liability value display correctly when place back and Lay bet on a selection on Market Page");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any sport page");
        SportPage page = memberHomePage.navigateSportHeaderMenu(SPORT_TENNIS);
        Event event = page.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event)) {
            throw new SkipException("SKIPPED! There is no event available");
        }
        log("Step 2: Click on any event");
        MarketPage marketPage = page.clickEventName(event.getEventName());
        log("Step 3: Place a matched Lay bet");
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        String minBet = BetUtils.getMinBet(SPORT_TENNIS, LBL_LAY_TYPE);

        List<ArrayList<String>> foreCastInfoBefore = marketPage.marketOddControl.getUIForeCast(market.getMarketName());
        marketPage.placeBet(market, minBet);
        List<Order> betOrder = marketPage.myBetsContainer.getOrder(true, false, 1);
        List<ArrayList<String>> foreCastInfoAfter = marketPage.marketOddControl.getUIForeCast(market.getMarketName());
        log("Verify 1: Verify forecast display correct on the selection has bet placed correct:\n" +
                "Display profit for under placed selection\n" +
                "Display liability of the bet under other selections");
        marketPage.verifyForeCastIsCorrect(foreCastInfoBefore, foreCastInfoAfter, betOrder.get(0));
        log("INFO: Executed completely");
    }
    @TestRails(id = "994")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void MarketPage_TC994() {
        log("@title: Validate that that user can place a bet with BACK Horse Racing successfully on Market Page ");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any horse racing race");
        String minBet = BetUtils.getMinBet("OTHER", LBL_BACK_TYPE);
        RacingPage racingPage = memberHomePage.navigateRacing(LBL_HORSE_RACING_SPORT);
        if (racingPage.racingContainer.isNoRace()) {
            log("DEBUG: There is no event available");
            return;
        }
        String country = racingPage.racingContainer.getCountry(0);
        List<String> trackLst = racingPage.racingContainer.getAllTrackName(country);
        String trackName = trackLst.get(trackLst.size() - 1);
        List<String> racelst = racingPage.racingContainer.getAllRacingList(country, trackName);
        String race = racelst.get(racelst.size() - 1);
        MarketPage marketPage = racingPage.clickRacing(country, trackName, race);
        Market market = marketPage.racingMarketContainer.getRace(1, true);
        log("Step 2: Place bet");
        marketPage.placeBet(market, minBet);
        List<Order> betOrder = marketPage.myBetsContainer.getOrder(true, true, 1);
        log("Verify 1: Odd rate on My Bet and on Bet Slip is the same\n" +
                "Market name on My Bet and on Bet Slip is the same\n" +
                "Event name on My Bet and on Bet Slip is the same\n" +
                "Selected team on My Bet and on Bet Slip is the same\n" +
                "Liability on My Bet and on Bet Slip is the same\n" +
                "Profit on My Bet and on Bet Slip is the same");
        marketPage.myBetsContainer.verifyInfoBetSlipAndOddsPage(market, betOrder.get(0));
        log("INFO: Executed completely");
    }
    @TestRails(id = "995")
    @Test(groups = {"smoke_market", "MER.Maintenance.2024.V.4.0"})
    public void MarketPage_TC995() {
        log("@title: Validate that Horse Racing is inactive Lay odds button on Market Page");
        log("Precondition: Login member account");
        log("Step 1: Navigate to any horse racing race");
        RacingPage racingPage = memberHomePage.navigateRacing(LBL_HORSE_RACING_SPORT);
        log("Step 2: Active any horse racing race and verify lay odds");
        String country = racingPage.racingContainer.getCountry(0);
        List<String> trackLst = racingPage.racingContainer.getAllTrackName(country);
        String trackName = trackLst.get(trackLst.size() - 1);
        List<String> racelst = racingPage.racingContainer.getAllRacingList(country, trackName);
        String race = racelst.get(racelst.size() - 1);
        MarketPage marketPage = racingPage.clickRacing(country, trackName, race);
        if (racingPage.racingContainer.isNoRace()) {
            log("DEBUG: There is no event available");
            return;
        }
        log("Verify 1: Verify Lay odds is unclickable");
        Assert.assertTrue(marketPage.racingMarketContainer.isAllLayButtonDisable(), "FAILED! All Lay odd button is NOT disabled");
        log("INFO: Executed completely");
    }
}

