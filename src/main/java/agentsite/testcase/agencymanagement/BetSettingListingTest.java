package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.BetSettingListingPage;
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

public class BetSettingListingTest extends BaseCaseTest {
    /**
     * @title: Can update Min Bet, Max Bet, Max Liability per Market, Max Win per Market, for all sport of Exchange Product
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     * 2. Select All sport and  search  an PL account
     * 3. Select PL account then Input valid Min Bet, Max Bet, Max Liability per Market, Max Win per Market and click Submit button
     * @expect: 1. Min Bet, Max Bet, Max Liability per Market, Max Win per Market is update for all sports type : Soccer, Cricket, Tennis, Basketball, Fancy, Other
     * 2. Update Status column display green check
     */
    @TestRails(id = "743")
    @Test(groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0003(String brandname) {
        log("@title: Can update Min Bet, Max Bet, Max Liability per Market, Max Win per Market, for all sport of Exchange Product");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname);
        String loginID = listAccount.get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();

        int minBet = 5;
        int maxBet = 20;
        int maxLiabilityPerMarket = 210;
        int maxWinPerMarket = 131;
        log("Step 2. Select All sport and  search  an PL account");
        page.search(loginID, "", "", "");

        log("Step 3. Select PL account then Input valid Min Bet, Max Bet, Max Liability per Market, Max Win per Market and click Submit button");
        page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
        List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE, (double) minBet, (double) maxBet, (double) maxLiabilityPerMarket, (double) maxWinPerMarket);
        page.updateBetSetting(loginID, minBet, maxBet, maxLiabilityPerMarket, maxWinPerMarket);
        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);

        log("Verify 1. Min Bet, Max Bet, Max Liability per Market, Max Win per Market is update for all sports type : Soccer, Cricket, Tennis, Basketball, Fancy, Other");
        Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");

        log("Veriry 2. Update Status column display green check");
        Assert.assertTrue(page.verifyUpdateStatus(lstActualData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE), "FAILED! Green check not display on Update Status column");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify update bet setting with valid min bet Setting
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     * 2. Search PL account and Exchange Product
     * 3. Select Soccer only
     * 4. Input valid Min bet
     * 5. Click Update
     * @expect: 1. Verify Soccer Min bet is update correctly, Update Status column display green check
     */
    @TestRails(id = "744")
    @Test(groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0004(String brandname) {
        log("@title: Verify update bet setting with valid min bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        try {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", true);
            int minBet = 6;
            log("Step 2. Search PL account and Exchange Product");
            page.search(loginID, "", "", "");

            log("Step 3. Select Soccer only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            log("Step 3.1 Get current bet setting all sports");
            List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            lstExpectedData.get(0).set(9, String.format("%.2f", Double.valueOf(minBet)));

            log("Step 4.Input valid Min bet then click Update ");
            page.updateBetSetting(loginID, minBet, -1, -1, -1);
            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            log("Verify 1. Verify Soccer Min bet is update correctly, Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstActualData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");
            log("INFO: Executed completely");
        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Soccer", false);

        }

    }

    /**
     * @title: Verify update bet setting with valid max bet Setting
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     * 2. Search PL account and Exchange Product
     * 3. Select Tennis only
     * 4. Input valid Max bet
     * 5. Click Update
     * @expect: 1. Verify Tennis Max bet is update correctly, Update Status column display green check
     */
    @TestRails(id = "745")
    @Test(groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0005(String brandname) {
        log("@title: Verify update bet setting with valid max bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        try {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Tennis", true);
            int maxBet = 13;
            log("Step 2. Search PL account and Exchange Product");
            page.search(loginID, "", "", "");

            log("Step 3. Select Tennis only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            lstExpectedData.get(1).set(1, String.format("%.2f", (double) maxBet));

            log("Step 4.Input valid Max bet then click Update ");
            page.updateBetSetting(loginID, -1, maxBet, -1, -1);
            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            log("Verify 1. Verify Soccer Min bet is update correctly, Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstActualData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");
            log("INFO: Executed completely");
        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Tennis", false);
        }

    }

    /**
     * @title: Verify update bet setting with valid  Max Liability Per Market Setting
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     * 2. Search PL account and Exchange Product
     * 3. Select Cricket only
     * 4. Input valid Max Liability Per Market
     * 5. Click Update
     * @expect: 1. Verify Max Liability Per Market is updated, Update Status column display green check
     */
    @TestRails(id = "746")
    @Test(groups = {"smoke"})
    @Parameters({"username", "brandname"})
    public void Agent_AM_Bet_Setting_Listing_0006(String brandname) {
        log("@title: Verify update bet setting with valid Max Liability Per Market Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        try {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Cricket", true);
            int maxLiabilityPerMarket = 1011;
            log("Step 2. Search PL account and Exchange Product");
            page.search(loginID, "", "", "");

            log("Step 3. Select Cricket only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            lstExpectedData.get(2).set(1, StringUtils.formatCurrency(String.format("%.2f", (double) maxLiabilityPerMarket)));

            log("Step 4.Input valid Max Liability Per Market then click Update ");
            page.updateBetSetting(loginID, -1, -1, maxLiabilityPerMarket, -1);

            log("Verify 1. Verify Max Liability Per Market is update correctly, Update Status column display green check");
            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            Assert.assertTrue(page.verifyUpdateStatus(lstActualData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");

            log("INFO: Executed completely");
        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Cricket", false);
        }

    }

    @TestRails(id = "747")
    @Test(groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0007(String brandname) {
        log("@title: Verify update bet setting with valid  Max Win Per Market Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();

        try {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Other", true);
            int maxWinPerMarket = 1302;
            log("Step 2. Search PL account and Exchange Product");
            page.search(loginID, "", "", "");

            log("Step 3. Select Other only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            lstExpectedData.get(3).set(1, StringUtils.formatCurrency(String.format("%.2f", (double) maxWinPerMarket)));

            log("Step 4. Input valid Max Win Per Market then click Update ");
            page.updateBetSetting(loginID, -1, -1, -1, maxWinPerMarket);

            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            log("Verify 1. Verify  Input valid Max Win Per Market is update correctly, Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstActualData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");

            log("INFO: Executed completely");
        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put("Other", false);
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
    }

    @TestRails(id = "3634")
    @Test(groups = {"regression"})
    public void Agent_AM_Bet_Setting_Listing_3634() {
        log("@title: Validate There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        agentHomePage.navigateBetSettingListingPage();

        log("Verify 1. Verify the correct username is displayed");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");
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
    }
}

