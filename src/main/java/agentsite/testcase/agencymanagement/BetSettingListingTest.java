package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.BetSettingListingPage;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.report.ReportslUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.objects.sat.Order;
import membersite.pages.MarketPage;
import membersite.pages.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.*;
import static common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static common.AGConstant.HomePage.BET_SETTING_LISTING;
import static common.MemberConstants.*;
import static common.MemberConstants.BetSlip.*;

public class BetSettingListingTest extends BaseCaseTest {
    @TestRails(id = "743")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.6.0"})
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
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.6.0"})
    public void Agent_AM_Bet_Setting_Listing_744() {
        log("@title: Verify update bet setting with valid min bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        double minBet = 0;

        log("Step 2. Search PL account and Exchange Product");
        AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put(SPORT_SOCCER, true);
        page.search(loginID, "", "", "");

        List<ArrayList<String>> lstSetting = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
        minBet = Double.parseDouble(lstSetting.get(0).get(9).replace(",", "")) + 1;

        log("Step 3. Get current bet setting all sports");
        List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE, minBet, -1, -1, -1);

        log("Step 3.1 Select Soccer only");
        page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
        try {
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
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put(SPORT_SOCCER, false);
            page.updateBetSetting(loginID, (int) minBet - 1, -1, -1, -1);
        }

    }

    @TestRails(id = "745")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.6.0"})
    public void Agent_AM_Bet_Setting_Listing_745() {
        log("@title: Verify update bet setting with valid max bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        double maxBet = 0;
        try {
            log("Step 2. Search PL account and Exchange Product");
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put(SPORT_SOCCER, true);
            page.search(loginID, "", "", "");

            List<ArrayList<String>> lstSetting = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
            maxBet = Double.parseDouble(lstSetting.get(1).get(2).replace(",","")) - 1;

            log("Step 3. Get current bet setting all sports");
            List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE, -1, maxBet, -1, -1);

            log("Step 3.1 Select Soccer only");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            log("Step 4.Input valid Max bet then click Update ");
            page.updateBetSetting(loginID, -1, (int) maxBet, -1, -1);
            log("Verify 1. Verify Update Status column display green check");
            Assert.assertTrue(page.verifyUpdateStatus(lstExpectedData, true, AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE), "FAILED! Green check not display on Update Status column");

            log("Step 5. Select All Sports");
            page.enableSport(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);

            List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE);
            log("Verify 2. Verify Soccer Max bet is update correctly");
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Expected not match with the actual");
            log("INFO: Executed completely");

        } finally {
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put(SPORT_SOCCER, false);
            page.updateBetSetting(loginID, -1, (int) maxBet + 1, -1, -1);
        }

    }

    @TestRails(id = "746")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.6.0"})
    public void Agent_AM_Bet_Setting_Listing_746() {
        log("@title: Verify update bet setting with valid max liability per market bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        double maxLiability = 0;
        try {
            log("Step 2. Search PL account and Exchange Product");
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put(SPORT_SOCCER, true);
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
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put(SPORT_SOCCER, false);
            page.updateBetSetting(loginID, -1, -1, (int) maxLiability + 1, -1);
        }

    }

    @TestRails(id = "747")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.6.0"})
    public void Agent_AM_Bet_Setting_Listing_747() {
        log("@title: Verify update bet setting with valid max win per market bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        double maxWin = 0;
        try {
            log("Step 2. Search PL account and Exchange Product");
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put(SPORT_SOCCER, true);
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
            AGConstant.AgencyManagement.BetSettingListing.SPORT_COLUMN_FALSE.put(SPORT_SOCCER, false);
            page.updateBetSetting(loginID, -1, -1, -1, (int) maxWin + 1);
        }

    }

    @TestRails(id = "3638")
    @Test(groups = {"interaction","tim"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Bet_Setting_Listing_3638(String memberAccount, String password) throws Exception {
        log("@title: Validate Cannot place bet when max liability on a market exceed the setting");
        log("Step 1. Active Bet Setting Listing and update Max Liability per market for Soccer");
        log("Step 2. Login member site with the player that update Max Liability in step 1");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        page.search(memberAccount, "", "", "");
        List<ArrayList<String>> lstSetting = page.getBetSettingofAccount(AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
        double maxLiability = Double.parseDouble(lstSetting.get(2).get(1).replace(",",""));

        memberHomePage = loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = sportPage.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        event.setMarketName(MATCH_ODDS_TITLE);

        log("Step 3. On a market place more bet that has liability on a market is exceed the setting");
        String maxBetSetting = BetUtils.getMaxBet(SPORT_SOCCER, LBL_LAY_TYPE);
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.inputStake(maxBetSetting);
        Order order = marketPage.betsSlipContainer.getBet(0);
        //check if liability could be greater than max liability setting with stake = max bet setting before submit, otherwise the error message cannot be appeared
        if(Double.valueOf(order.getLiability().replace(",","")) < maxLiability) {
            log(String.format("DEBUG: Liability %s cannot be greater than max liability setting %s with input stake is max bet %s and odds %s, please update bet setting or change odds", order.getLiability(), maxLiability, maxBetSetting, order.getOdds()));
            return;
        }
        marketPage.betsSlipContainer.clickSubmit();

        log("Verify max liability error message displays");
        String actualMsg = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        String expectedMsg = String.format(ERROR_EXCEED_MAX_LIABILITY, order.getLiability(), maxLiability);
        Assert.assertEquals(actualMsg, expectedMsg, "ERROR! Max Liability error message is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3639")
    @Test(groups = {"interaction","tim"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Bet_Setting_Listing_3639(String memberAccount, String password) throws Exception {
        log("@title: Validate Cannot place bet when potential win is greater than the setting");
        log("Step 1. Active Bet Setting Listing and update Max Win per market for Soccer");
        log("Step 2. Login member site with the player that update Max Win in step 1");
        BetSettingListingPage page = agentHomePage.navigateBetSettingListingPage();
        page.search(memberAccount, "", "", "");
        List<ArrayList<String>> lstSetting = page.getBetSettingofAccount(AgencyManagement.BetSettingListing.SPORT_COLUMN_TRUE);
        double maxWin = Double.parseDouble(lstSetting.get(3).get(1).replace(",",""));

        memberHomePage = loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = sportPage.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        event.setMarketName(MATCH_ODDS_TITLE);

        log("Step 3. On a market place more bet that has liability on a market is exceed the setting");
        String maxBetSetting = BetUtils.getMaxBet(SPORT_SOCCER, LBL_BACK_TYPE);
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        Market market = marketPage.marketOddControl.getMarket(event, 1, true);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.inputStake(maxBetSetting);
        Order order = marketPage.betsSlipContainer.getBet(0);
        //check if liability could be greater than max liability setting with stake = max bet setting before submit, otherwise the error message cannot be appeared
        if(Double.valueOf(order.getProfit().replace(",","")) < maxWin) {
            log(String.format("DEBUG: Liability %s cannot be greater than max liability setting %s with input stake is max bet %s and odds %s, please update bet setting or change odds", order.getLiability(), maxWin, maxBetSetting, order.getOdds()));
            return;
        }
        marketPage.betsSlipContainer.clickSubmit();

        log("Verify max win error message displays");
        String actualMsg = marketPage.myBetsContainer.getPlaceBetErrorMessage();
        String expectedMsg = String.format(ERROR_EXCEED_MAX_WIN, order.getLiability(), maxWin);
        Assert.assertEquals(actualMsg, expectedMsg, "ERROR! Max Win error message is not displayed");
        log("INFO: Executed completely");

    }

    @TestRails(id = "3636")
    @Test(groups = {"interaction","tim"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Bet_Setting_Listing_3636(String memberAccount, String password) throws Exception {
        log("@title: Validate cannot place bet when place bet in member site smaller than min bet setting");
        log("Step 1. Active Bet Setting Listing and update min bet for an player");
        log("Step 2. Login member site with the player that update min bet in step 1");
        memberHomePage = loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = sportPage.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }
        event.setMarketName(MATCH_ODDS_TITLE);

        log("Step 3. Place bet with stake less than min setting");
        String minBetSetting = BetUtils.getMinBet(SPORT_SOCCER, LBL_LAY_TYPE);
        String maxBetSetting = BetUtils.getMaxBet(SPORT_SOCCER, LBL_LAY_TYPE);
        Double minBet = Double.parseDouble(minBetSetting) - 1;
        if(minBet < 1) {
            log("DEBUG: Min bet is less than 1 so cannot place bet, please change setting");
            return;
        }
        String expectedMsg = String.format(VALIDATE_STAKE_NOT_VALID, minBetSetting, maxBetSetting, minBet);
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        Market market = marketPage.marketOddControl.getMarket(event, 1, false);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("1.01", String.valueOf(minBet));

        log("Verify stake error message displays");
        String actualMsg = memberHomePage.betsSlipContainer.getBetSlipErrorMessage();
        Assert.assertEquals(actualMsg, expectedMsg, "ERROR! Min Stake error message is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3637")
    @Test(groups = {"interaction","tim"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_Bet_Setting_Listing_3637(String memberAccount, String password) throws Exception {
        log("@title: Validate cannot place bet when place bet in member site greater than max bet setting");
        log("Step 1. Active Bet Setting Listing and update max bet for an player");
        log("Step 2. Login member site with the player that update max bet in step 1");
        memberHomePage = loginMember(memberAccount, password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu(SPORT_SOCCER);
        Event event = sportPage.eventContainerControl.getEventRandom(false, false);
        if (Objects.isNull(event) || event.getEventName().isEmpty()) {
            log("DEBUG: There is no event available");
            return;
        }

        log("Step 3. Place bet with stake greater  than max setting");
        String minBetSetting = BetUtils.getMinBet(SPORT_SOCCER, LBL_LAY_TYPE);
        String maxBetSetting = BetUtils.getMaxBet(SPORT_SOCCER, LBL_LAY_TYPE);
        long maxBet = Long.valueOf(maxBetSetting) + 1000;
        String expectedMsg = String.format(VALIDATE_STAKE_NOT_VALID, minBetSetting, maxBetSetting, maxBet);
        MarketPage marketPage = sportPage.clickEventName(event.getEventName());
        Market market = marketPage.marketOddControl.getMarket(event, 2, false);
        market.getBtnOdd().click();
        marketPage.betsSlipContainer.placeBet("1.01", String.valueOf(maxBet));

        log("Verify stake error message displays");
        String actualMsg = memberHomePage.betsSlipContainer.getBetSlipErrorMessage();
        Assert.assertEquals(actualMsg, expectedMsg, "ERROR! Max Stake error message is not displayed");
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
        Assert.assertTrue(lstSports.contains("Sport") && lstSports.contains("Select All") && lstSports.contains(SPORT_SOCCER) && lstSports.contains("Cricket")
                && lstSports.contains("Tennis") && lstSports.contains("Basketball") && lstSports.contains("Fancy") && lstSports.contains("Other"), "FAILED! List Sports does not display correctly");
        Assert.assertTrue(betSettingListingPage.txtMinBet.isDisplayed(), "FAILED! Min bet textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.txtMaxBet.isDisplayed(), "FAILED! Max bet textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.txtMaxLiabilityPerMarket.isDisplayed(), "FAILED! Max Liability textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.txtMaxWinPerMarket.isDisplayed(), "FAILED! Max Win Per Market textbox is not displayed");
        Assert.assertTrue(betSettingListingPage.tblDownline.isDisplayed(), "FAILED! Result table is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3975")
    @Test(groups = {"regression","tim"})
    public void Agent_AM_Bet_Setting_Listing_3975() {
        log("@title: Validate the inactive product not display in product list in Bet Setting Listing page");
        log("Precondition. Login Agent Site with downline agent is inactive Exchange Game product");
        List<String> lstProductActive = ReportslUtils.getProductActive();
        if(lstProductActive.contains(EXCHANGE_GAMES)) {
            log("DEBUG: Exchange Games is not inactivated, skip test");
            return;
        }
        BetSettingListingPage betSettingListingPage = agentHomePage.navigateBetSettingListingPage();
        Assert.assertFalse(betSettingListingPage.ddbProduct.getOptions().contains(EXCHANGE_GAMES), String.format("FAILED! Dropdown product %s contains inactive product %s",betSettingListingPage.ddbProduct.getOptions(), EXCHANGE_GAMES));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3976")
    @Test(groups = {"regression"})
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
        editDownLinePage.editDownlineListing.enableDisableSport(AGConstant.EXCHANGE, false);
        editDownLinePage.editDownlineListing.enableDisableSport(AGConstant.PS38, false);

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

