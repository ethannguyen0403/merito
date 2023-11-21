package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.BetSettingListingPage;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.agencymanagement.EventBetSizeSettingUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import common.MemberConstants;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.*;
import static common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static common.AGConstant.HomePage.BET_SETTING_LISTING;

public class BetSettingListingTest extends BaseCaseTest {
    @TestRails(id = "743")
    @Test(groups = {"smoke"})
    public void Agent_AM_Bet_Setting_Listing_743() {
        log("@title: Can update Min Bet, Max Bet, Max Liability per Market, Max Win per Market, for all sport of Exchange Product");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();

        log("Step 2. Select All sport and  search  an PL account");
        page.search(loginID, "", "", "");
        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
        double minBet = Double.parseDouble(lstActualData.get(0).get(9).replace(",","")) + 1;
        double maxBet = Double.parseDouble(lstActualData.get(1).get(1).replace(",","")) - 1;
        double maxLiabilityPerMarket = Double.parseDouble(lstActualData.get(2).get(1).replace(",","")) - 1;
        double maxWinPerMarket = Double.parseDouble(lstActualData.get(3).get(1).replace(",","")) - 1;

        log("Step 3. Select PL account then Input valid Min Bet, Max Bet, Max Liability per Market, Max Win per Market and click Submit button");
        page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
        List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE, minBet, maxBet, maxLiabilityPerMarket, maxWinPerMarket);
        try {
            page.updateBetSetting(loginID, (int) minBet, (int) maxBet, (int) maxLiabilityPerMarket, (int) maxWinPerMarket);
            lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);

            log("Verify 1. Min Bet, Max Bet, Max Liability per Market, Max Win per Market is update for all sports type : Soccer, Cricket, Tennis, Basketball, Fancy, Other");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");

            log("Verify 2. Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstActualData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE), "FAILED! Green check not display on Update Status column");
            log("INFO: Executed completely");
        } finally {
            page.updateBetSetting(loginID, (int) minBet - 1, (int) maxBet + 1, (int) maxLiabilityPerMarket + 1, (int) maxWinPerMarket + 1);
        }

    }

    @TestRails(id = "744")
    @Test(groups = {"smoke"})
    public void Agent_AM_Bet_Setting_Listing_744() {
        log("@title: Verify update bet setting with valid min bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        double minBet = 0;
        try {
            log("Step 2. Search PL account and Exchange Product");
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", true);
            page.search(loginID, "", "", "");

            List<ArrayList<String>> lstSetting = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
            minBet = Double.parseDouble(lstSetting.get(0).get(9).replace(",", "")) + 1;

            log("Step 3. Get current bet setting all sports");
            List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE, minBet, -1, -1, -1);

            log("Step 3.1 Select Soccer only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            log("Step 4.Input valid Min bet then click Update ");
            page.updateBetSetting(loginID, (int) minBet, -1, -1, -1);
            log("Verify 4. Verify Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstExpectedData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");

            log("Step 5. Select All Sports");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            log("Verify 5. Verify Soccer Min bet is update correctly");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");
            log("INFO: Executed completely");

        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", false);
            page.updateBetSetting(loginID, (int) minBet - 1, -1, -1, -1);
        }

    }

    @TestRails(id = "745")
    @Test(groups = {"smoke"})
    public void Agent_AM_Bet_Setting_Listing_745() {
        log("@title: Verify update bet setting with valid max bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        double maxBet = 0;
        try {
            log("Step 2. Search PL account and Exchange Product");
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", true);
            page.search(loginID, "", "", "");

            List<ArrayList<String>> lstSetting = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
            maxBet = Double.parseDouble(lstSetting.get(1).get(2).replace(",","")) - 1;

            log("Step 3. Get current bet setting all sports");
            List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE, -1, maxBet, -1, -1);

            log("Step 3.1 Select Soccer only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            log("Step 4.Input valid Max bet then click Update ");
            page.updateBetSetting(loginID, -1, (int) maxBet, -1, -1);
            log("Verify 4. Verify Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstExpectedData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");

            log("Step 5. Select All Sports");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            log("Verify 5. Verify Soccer Max bet is update correctly");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");
            log("INFO: Executed completely");

        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", false);
            page.updateBetSetting(loginID, -1, (int) maxBet + 1, -1, -1);
        }

    }

    @TestRails(id = "746")
    @Test(groups = {"smoke"})
    public void Agent_AM_Bet_Setting_Listing_746() {
        log("@title: Verify update bet setting with valid max liability per market bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        double maxLiability = 0;
        try {
            log("Step 2. Search PL account and Exchange Product");
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", true);
            page.search(loginID, "", "", "");

            List<ArrayList<String>> lstSetting = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
            maxLiability = Double.parseDouble(lstSetting.get(2).get(2).replace(",","")) - 1;

            log("Step 3. Get current bet setting all sports");
            List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE, -1, -1, maxLiability, -1);

            log("Step 3.1 Select Soccer only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            log("Step 4.Input valid max liability per market bet then click Update ");
            page.updateBetSetting(loginID, -1, -1, (int) maxLiability, -1);
            log("Verify 4. Verify Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstExpectedData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");

            log("Step 5. Select All Sports");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            log("Verify 5. Verify Soccer max liability per market bet is update correctly");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");
            log("INFO: Executed completely");

        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", false);
            page.updateBetSetting(loginID, -1, -1, (int) maxLiability + 1, -1);
        }

    }

    @TestRails(id = "747")
    @Test(groups = {"smoke"})
    public void Agent_AM_Bet_Setting_Listing_747() {
        log("@title: Verify update bet setting with valid max win per market bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        double maxWin = 0;
        try {
            log("Step 2. Search PL account and Exchange Product");
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", true);
            page.search(loginID, "", "", "");

            List<ArrayList<String>> lstSetting = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
            maxWin = Double.parseDouble(lstSetting.get(2).get(2).replace(",","")) - 1;

            log("Step 3. Get current bet setting all sports");
            List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE, -1, -1, -1, maxWin);

            log("Step 3.1 Select Soccer only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            log("Step 4.Input valid max win per market bet then click Update ");
            page.updateBetSetting(loginID, -1, -1, -1, (int) maxWin);
            log("Verify 4. Verify Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstExpectedData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");

            log("Step 5. Select All Sports");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            log("Verify 5. Verify Soccer max liability per market bet is update correctly");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");
            log("INFO: Executed completely");

        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", false);
            page.updateBetSetting(loginID, -1, -1, -1, (int) maxWin + 1);
        }

    }

    @Test(groups = {"interaction2"})
    @Parameters({"downlineAccount", "memberAccount", "password"})
    public void Agent_AM_Bet_Setting_Listing_0010(String downlineAccount, String memberAccount, String password) throws Exception {
        log("@title: Cannot place bet when max liability on a market excceed the setting");
        log("Step 1. Active Bet Setting Listing and update max liability on a market for a player on Cricket");
        String sportName = "Cricket";
        String userID = ProfileUtils.getProfile().getUserID();

        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDownLineUsers(_brandname, memberAccount, userID);
        AccountInfo accountInfo = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount);
        String userCode = accountInfo.getUserCode();
        // String memberAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Event event = eventList.get(0);
        blockUnblockEventPage.blockUnblockEvent(downlineAccount, event.getEventName(), "Unblock Now", "", 1);
        BetSettingListingPage page = blockUnblockEventPage.navigateBetSettingListingPage();

        try {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Cricket", true);
            page.search(memberAccount, "", "", "");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            String min = lstExpectedData.get(0).get(8);
            String max = lstExpectedData.get(1).get(1);
            String maxLiabilyBeforeUpdate = lstExpectedData.get(2).get(1);
            int maxLiabilityOnMarket = (int) Double.parseDouble(min);
            lstExpectedData.get(2).set(1, StringUtils.formatCurrency(String.format("%.2f", (double) maxLiabilityOnMarket)));
            page.updateBetSetting(userCode, -1, -1, maxLiabilityOnMarket, -1);

            log("Step 2.Login member site with the player  in step 1");
            loginMember(memberAccount, password);
            SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
            event = sportPage.setEventLink(event);
            MarketPage marketPage = sportPage.clickEvent(event);
            Market market = marketPage.marketOddControl.getMarket(event, 1, true);
            market.getBtnOdd().click();
       /* event.getLinkEvent().click();
        Market market = sportPage.marketContainerControl_SAT.getMarket(event, 1, true);*/
            String odds = market.getBtnOdd().getText();


            log(String.format("Step 3. Place bet with odds:%s Stake: %s", odds, min));
            int stake = ((int) Double.parseDouble(min)) + 1;
            sportPage.betsSlipContainer.placeBet(odds, String.valueOf(stake));

            log(String.format("Verify 1: Verify cannot place bet if min bet less than the setting"));
            String actualError = sportPage.myBetsContainer.getPlaceBetErrorMessage();
            String expectedError = String.format(MemberConstants.BetSlip.ERROR_EXCEED_LIABILITY, String.format("%,.2f", stake), String.format("%(,.2f", min));

            log("Verify Verify error message display when place bet");
            Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

            log("INFO: Executed completely");
        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Cricket", false);
        }

    }

    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Bet_Setting_Listing_0011(String downlineAccount, String memeberAccount, String password) throws Exception {
        log("@title: Cannot place bet when potential win is greater than the setting");
        log("Step 1 Active Bet Setting Listing and update Max Win per market for Soccer");
        String sportName = "Cricket";
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("", sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName, childID, "TODAY");
        Event event = eventList.get(0);

        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();

        try {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Cricket", true);
            page.search(loginID, "", "", "");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            String min = lstExpectedData.get(3).get(1);
            String maxLiabilyBeforeUpdate = lstExpectedData.get(3).get(1);
            String max = lstExpectedData.get(3).get(2);
            int maxLiabilityOnMarket = Integer.valueOf(min);
            lstExpectedData.get(3).set(1, StringUtils.formatCurrency(String.format("%.2f", (double) maxLiabilityOnMarket)));
            page.updateBetSetting(loginID, -1, -1, maxLiabilityOnMarket, -1);

            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            log("Step 2 Login member site with the player  in step 1");
            loginMember(memeberAccount, password);
            SportPage sportPage = memberHomePage.navigateSportHeaderMenu(sportName);
            event = sportPage.setEventLink(event);
            MarketPage marketPage = sportPage.clickEvent(event);
            Market market = marketPage.marketOddControl.getMarket(event, 1, true);
            String odds = market.getBtnOdd().getText();
            market.getBtnOdd().click();

            log("Step 3 On a market place more bet that has liability on a market is exceed the setting");
            sportPage.betsSlipContainer.placeBet(odds, min);

            log("Verify Verify error message display when place bet");
            String actualError = sportPage.myBetsContainer.getPlaceBetErrorMessage();
            String expectedError = String.format(MemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f", min), String.format("%(,.2f", max), String.format("%.2f", min));
            Assert.assertEquals(actualError, expectedError, String.format("ERROR! Expected error message is %s but found %s", expectedError, actualError));

            log("INFO: Executed completely");
        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Cricket", false);
        }

    }

    @Test(groups = {"interaction"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Bet_Setting_Listing_008(String memberAccount, String password) throws Exception {
        log("@title: Verify cannot place bet when place bet in member site smaller than min bet setting");
        log("Step 1. Navigate to Agent Site and get list Event");
        String userID = ProfileUtils.getProfile().getUserID();
        Event event = EventBetSizeSettingUtils.getEventList("Soccer", userID, "TODAY").get(0);
        agentHomePage.logout();

        log("Step 2. Login to Member Site");
        loginMember(memberAccount, password);
        memberHomePage = landingPage.login(memberAccount, StringUtils.decrypt(password), true);
        memberHomePage.closeBannerPopup();

        log("Step 3. Place bet with stake < min setting");
        String minBetSetting = BetUtils.getMinBet("SOCCER", "LAY");
        String maxBetSetting = BetUtils.getMaxBet("SOCCER", "LAY");
        Double minBet = Double.parseDouble(minBetSetting) - 1;
        String expectedMsg = String.format("Cannot place bet. The stake must be from %.2f to %.2f. Current Stake is %.2f"
                , Double.parseDouble(minBetSetting), Double.parseDouble(maxBetSetting), minBet);
        memberHomePage.leftMenu.searchEvent(event.getEventName(), true);
        MarketPage marketPage = memberHomePage.clickMenuIndex(1);
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("1.01", String.valueOf(minBet));

        log("Verify stake error message displays");
        Assert.assertTrue(memberHomePage.betsSlipContainer.isErrorDisplayed(marketPage.betsSlipContainer.lblMinMaxStakeErrorMessage, expectedMsg), "ERROR! Min Stake error message is not displayed");
        log("INFO: Executed completely");
    }

    @Test(groups = {"interaction01"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Bet_Setting_Listing_009(String memberAccount, String password) throws Exception {
        log("@title: Verify cannot place bet when place bet in member site greater than max bet setting");
        log("Step 1. Navigate to Agent Site and get list Event");
        String userID = ProfileUtils.getProfile().getUserID();
        Event event = EventBetSizeSettingUtils.getEventList("Soccer", userID, "TODAY").get(0);
        agentHomePage.logout();

        log("Step 2. Login to Member Site");
        loginMember(memberAccount, password);
        memberHomePage.closeBannerPopup();

        log("Step 3. Place bet with stake < min setting");
        String minBetSetting = BetUtils.getMinBet("SOCCER", "LAY");
        String maxBetSetting = BetUtils.getMaxBet("SOCCER", "LAY");
        Double maxBet = Double.parseDouble(maxBetSetting) + 1;
        String expectedMsg = String.format("Cannot place bet. The stake must be from %.2f to %.2f. Current Stake is %.2f"
                , Double.parseDouble(minBetSetting), Double.parseDouble(maxBetSetting), maxBet);
        memberHomePage.leftMenu.searchEvent(event.getEventName(), true);
        MarketPage marketPage = memberHomePage.clickMenuIndex(1);
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        memberHomePage.betsSlipContainer.placeBet("1.01", String.valueOf(maxBet));

        log("Verify stake error message displays");
        Assert.assertTrue(memberHomePage.betsSlipContainer.isErrorDisplayed(memberHomePage.betsSlipContainer.lblMinMaxStakeErrorMessage, expectedMsg)
                , "ERROR! Max Stake error message is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3634")
    @Test(groups = {"http_request"})
    public void Agent_AM_Bet_Setting_Listing_3634() {
        log("@title: Validate There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        agentHomePage.navigateBetSettingListingPage();

        log("Verify 1. Verify the correct username is displayed");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3635")
    @Test(groups = {"regression"})
    public void Agent_AM_Bet_Setting_Listing_3635() {
        log("@title: Validate Bet Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        BetSettingListingPage betSettingListingPage = agentHomePage.navigateBetSettingListingPage();

        String lstSports = betSettingListingPage.sportsRow.getText();

        log("Verify 1. Validate UI on Bet Setting Listing display correctly");
        Assert.assertEquals(betSettingListingPage.lblTitlePage.getText(), AGConstant.HomePage.BET_SETTING_LISTING, "FAILED! Page title displays incorrectly");
        Assert.assertTrue(betSettingListingPage.txtUsername.isDisplayed(), "FAILED! Username textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.ddbLevel.isDisplayed(), "FAILED! Level dropdown is not displayed");
        Assert.assertTrue(betSettingListingPage.ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdown is not displayed");
        Assert.assertTrue(betSettingListingPage.btnSubmit.isDisplayed(), "FAILED! Submit button is not displayed");
        Assert.assertTrue(lstSports.contains("Sport") && lstSports.contains("Select All") && lstSports.contains("Soccer") && lstSports.contains("Cricket")
                && lstSports.contains("Tennis") && lstSports.contains("Basketball") && lstSports.contains("Fancy") && lstSports.contains("Other"), "FAILED! List Sports does not display correctly");
        Assert.assertTrue(betSettingListingPage.txtMinBet.isDisplayed(), "FAILED! Min bet textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.txtMaxBet.isDisplayed(), "FAILED! Max bet textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.txtMaxLiabilityPerMarket.isDisplayed(), "FAILED! Max Liability textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.txtMaxWinPerMarket.isDisplayed(), "FAILED! Max Win Per Market textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.tblDownline.isDisplayed(), "FAILED! Result table is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3975")
    @Test(groups = {"regression_newui"})
    @Parameters({"password"})
    public void Agent_AM_Bet_Setting_Listing_3975(String password) throws Exception {
        log("@title: Validate the inactive product not display in product list in Bet Setting Listing page");
        log("Precondition. Login Agent Site with downline agent is inactive Exchange Game product");
        String userID = ProfileUtils.getProfile().getUserID();
        String downlineUserCode = DownLineListingUtils.getDownLineUsers(userID, "", "ACTIVE", _brandname).get(0).getUserCodeAndLoginID("%s-%s");
        //handle to get loginId for logging if having
        if(!downlineUserCode.contains("-")) {
            downlineUserCode = downlineUserCode.split("-")[0];
        } else {
            downlineUserCode = downlineUserCode.split("-")[1];
        }
        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
        downLineListingPage.searchDownline(downlineUserCode,"","");
        EditDownLinePage editDownLinePage = downLineListingPage.clickEditIcon(downlineUserCode);
        editDownLinePage.editDownlineListing.enableDisableSport(EXCHANGE_GAMES, false);
        editDownLinePage.editDownlineListing.clickSubmit();
        downLineListingPage = editDownLinePage.editDownlineListing.closeEditDownlinePopup();
        downLineListingPage.logout();
        Thread.sleep(2000);
        log("Step 1. Login the downline account in precondition");
        loginNewAccount(sosAgentURL, agentNewAccURL, downlineUserCode, password, StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 2. Active Bet Setting Listing page");
        BetSettingListingPage betSettingListingPage = agentHomePage.navigateBetSettingListingPage();
        log("Step 3. Click on product dropdown and observe data");
        List<String> lstProduct = betSettingListingPage.ddbProduct.getOptions();

        log("Verify 3. Verify Exchange Game is not display");
        Assert.assertFalse(lstProduct.contains(EXCHANGE_GAMES), "FAILED! List product contain Exchange Games");
        log("INFO: Executed completely");

    }

    @TestRails(id = "3976")
    @Test(groups = {"regression_newui"})
    @Parameters({"password"})
    public void Agent_AM_Bet_Setting_Listing_3976(String password) throws Exception {
        log("@title: Validate Bet Setting Listing page is hidden when Exchange /Exchange Game/ PS38 product is inactive");
        log("Precondition. Login agent site with a downline agent is inactive Exchange and Exchange Game and PS38 product");
        String userID = ProfileUtils.getProfile().getUserID();
        String downlineUserCode = DownLineListingUtils.getDownLineUsers(userID, "", "ACTIVE", _brandname).get(0).getUserCodeAndLoginID("%s-%s");
        //handle to get loginId for logging if having
        if(!downlineUserCode.contains("-")) {
            downlineUserCode = downlineUserCode.split("-")[0];
        } else {
            downlineUserCode = downlineUserCode.split("-")[1];
        }
        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
        downLineListingPage.searchDownline(downlineUserCode,"","");
        EditDownLinePage editDownLinePage = downLineListingPage.clickEditIcon(downlineUserCode);
        editDownLinePage.editDownlineListing.enableDisableSport(EXCHANGE_GAMES, false);
        editDownLinePage.editDownlineListing.enableDisableSport(EXCHANGE, false);
        editDownLinePage.editDownlineListing.enableDisableSport(PS38, false);

        editDownLinePage.editDownlineListing.clickSubmit();
        downLineListingPage = editDownLinePage.editDownlineListing.closeEditDownlinePopup();
        downLineListingPage.logout();
        Thread.sleep(2000);
        log("Step 1. Login the downline account in precondition");
        loginNewAccount(sosAgentURL, agentNewAccURL, downlineUserCode, password, StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 2. Expand left menu and observe Bet Setting Listing menu");
        List<String> lstSubMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

        log("Verify 2. The page is no longer display in the left menu");
        Assert.assertFalse(lstSubMenu.contains(BET_SETTING_LISTING), "FAILED! List product contain Bet Setting Listing page");
        log("INFO: Executed completely");
    }
}

