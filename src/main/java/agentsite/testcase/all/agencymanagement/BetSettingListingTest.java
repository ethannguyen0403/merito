package agentsite.testcase.all.agencymanagement;

import com.paltech.utils.StringUtils;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.BetSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.BET_SETTING_LISTING;

public class BetSettingListingTest extends BaseCaseMerito {
    /**
     * @title: Can update Min Bet, Max Bet, Max Liability per Market, Max Win per Market, for all sport of Exchange Product
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     *         2. Select All sport and  search  an PL account
     *         3. Select PL account then Input valid Min Bet, Max Bet, Max Liability per Market, Max Win per Market and click Submit button
     * @expect: 1. Min Bet, Max Bet, Max Liability per Market, Max Win per Market is update for all sports type : Soccer, Cricket, Tennis, Basketball, Fancy, Other
     *          2. Update Status column display green check
     */
    @Test (groups = {"demo_smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0003_demo(String brandname) {
        log("@title: Can update Min Bet, Max Bet, Max Liability per Market, Max Win per Market, for all sport of Exchange Product");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE", brandname);
        String loginID = listAccount.get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, BET_SETTING_LISTING, BetSettingListingPage.class);
        HashMap<String, Boolean> sport = new HashMap<String, Boolean>() {
            {
                put("Soccer", true);
                put("Cricket", true);
                put("Tennis", true);
                put("Basketball", true);
                put("Fancy", true);
                put("Other", true);
            }
        };
        int minBet = 5;
        int maxBet = 20;
        int maxLiabilityPerMarket = 210;
        int maxWinPerMarket=131;
        log("Step 2. Select All sport and  search  an PL account");
        page.search(loginID,"", "","");

        log("Step 3. Select PL account then Input valid Min Bet, Max Bet, Max Liability per Market, Max Win per Market and click Submit button");
        page.enableSport(sport);
        List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(sport,(double)minBet,(double)maxBet,(double)maxLiabilityPerMarket,(double)maxWinPerMarket);
        page.updateBetSetting(loginID,minBet,maxBet,maxLiabilityPerMarket,maxWinPerMarket);
        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(sport);

        log("Verify 1. Min Bet, Max Bet, Max Liability per Market, Max Win per Market is update for all sports type : Soccer, Cricket, Tennis, Basketball, Fancy, Other");
        Assert.assertEquals(lstActualData,lstExpectedData,"FAILED! Expected not match with the actual");

        log("Veriry 2. Update Status column display green check");
        Assert.assertTrue(page.verifyUpdateStatus(lstActualData,true,sport),"FAILED! Green check not display on Update Status column");
        log("INFO: Executed completely");
    }
    /**
     * @title: Can update Min Bet, Max Bet, Max Liability per Market, Max Win per Market, for all sport of Exchange Product
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     *         2. Select All sport and  search  an PL account
     *         3. Select PL account then Input valid Min Bet, Max Bet, Max Liability per Market, Max Win per Market and click Submit button
     * @expect: 1. Min Bet, Max Bet, Max Liability per Market, Max Win per Market is update for all sports type : Soccer, Cricket, Tennis, Basketball, Fancy, Other
     *          2. Update Status column display green check
     */
    @Test (groups = {"smoke1"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0003(String brandname) {
        log("@title: Can update Min Bet, Max Bet, Max Liability per Market, Max Win per Market, for all sport of Exchange Product");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE", brandname);
        String loginID = listAccount.get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, BET_SETTING_LISTING, BetSettingListingPage.class);
        HashMap<String, Boolean> sport = new HashMap<String, Boolean>() {
            {
                put("Soccer", true);
                put("Cricket", true);
                put("Tennis", true);
                put("Basketball", true);
                put("Fancy", true);
                put("Other", true);
            }
        };
        int minBet = 5;
        int maxBet = 20;
        int maxLiabilityPerMarket = 210;
        int maxWinPerMarket=131;
        log("Step 2. Select All sport and  search  an PL account");
        page.search(loginID,"", "","");

        log("Step 3. Select PL account then Input valid Min Bet, Max Bet, Max Liability per Market, Max Win per Market and click Submit button");
        page.enableSport(sport);
        List<ArrayList<String>> lstExpectedData = page.defineActualDataForOneAccount(sport,(double)minBet,(double)maxBet,(double)maxLiabilityPerMarket,(double)maxWinPerMarket);
        page.updateBetSetting(loginID,minBet,maxBet,maxLiabilityPerMarket,maxWinPerMarket);
        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(sport);

        log("Verify 1. Min Bet, Max Bet, Max Liability per Market, Max Win per Market is update for all sports type : Soccer, Cricket, Tennis, Basketball, Fancy, Other");
        Assert.assertEquals(lstActualData,lstExpectedData,"FAILED! Expected not match with the actual");

        log("Veriry 2. Update Status column display green check");
        Assert.assertTrue(page.verifyUpdateStatus(lstActualData,true,sport),"FAILED! Green check not display on Update Status column");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify update bet setting with valid min bet Setting
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     *          2. Search PL account and Exchange Product
     *          3. Select Soccer only
     *          4. Input valid Min bet
     *          5. Click Update
     * @expect: 1. Verify Soccer Min bet is update correctly, Update Status column display green check
     */
    @Test (groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0004(String brandname) {
        log("@title: Verify update bet setting with valid min bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, BET_SETTING_LISTING, BetSettingListingPage.class);
        HashMap<String, Boolean> sport = new HashMap<String, Boolean>() {
            {
                put("Soccer", true);
                put("Cricket", false);
                put("Tennis", false);
                put("Basketball", false);
                put("Fancy", false);
                put("Other", false);
            }
        };
        int minBet = 6;
        log("Step 2. Search PL account and Exchange Product");
        page.search(loginID,"", "","");

        log("Step 3. Select Soccer only");
        page.enableSport(sport);

        log("Step 3.1 Get current bet setting all sports");
        List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(sport);
        lstExpectedData.get(0).set(9, String.format("%.2f",Double.valueOf(minBet)));

        log("Step 4.Input valid Min bet then click Update ");
        page.updateBetSetting(loginID,minBet,-1,-1,-1);
        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(sport);
        log("Verify 1. Verify Soccer Min bet is update correctly, Update Status column display green check");
        Assert.assertTrue(page.verifyUpdateStatus(lstActualData,true,sport),"FAILED! Green check not display on Update Status column");
        Assert.assertEquals(lstActualData,lstExpectedData,"FAILED! Expected not match with the actual");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify update bet setting with valid max bet Setting
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     *          2. Search PL account and Exchange Product
     *          3. Select Tennis only
     *          4. Input valid Max bet
     *          5. Click Update
     * @expect: 1. Verify Tennis Max bet is update correctly, Update Status column display green check
     */
    @Test (groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0005(String brandname) {
        log("@title: Verify update bet setting with valid max bet Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, BET_SETTING_LISTING, BetSettingListingPage.class);
        HashMap<String, Boolean> sport = new HashMap<String, Boolean>() {
            {
                put("Soccer", false);
                put("Cricket", false);
                put("Tennis", true);
                put("Basketball", false);
                put("Fancy", false);
                put("Other", false);
            }
        };
        int maxBet = 13;
        log("Step 2. Search PL account and Exchange Product");
        page.search(loginID,"", "","");

        log("Step 3. Select Tennis only");
        page.enableSport(sport);
        List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(sport);
        lstExpectedData.get(1).set(1, String.format("%.2f",(double)maxBet));

        log("Step 4.Input valid Max bet then click Update ");
        page.updateBetSetting(loginID,-1,maxBet,-1,-1);
        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(sport);

        log("Verify 1. Verify Soccer Min bet is update correctly, Update Status column display green check");
        Assert.assertTrue(page.verifyUpdateStatus(lstActualData,true,sport),"FAILED! Green check not display on Update Status column");
        Assert.assertEquals(lstActualData,lstExpectedData,"FAILED! Expected not match with the actual");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify update bet setting with valid  Max Liability Per Market Setting
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     *          2. Search PL account and Exchange Product
     *          3. Select Cricket only
     *          4. Input valid Max Liability Per Market
     *          5. Click Update
     * @expect: 1. Verify Max Liability Per Market is updated, Update Status column display green check
     */

    @Test (groups = {"smoke"})
    @Parameters({"username","brandname"})
    public void Agent_AM_Bet_Setting_Listing_0006(String brandname) {
        log("@title: Verify update bet setting with valid Max Liability Per Market Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, BET_SETTING_LISTING, BetSettingListingPage.class);
        HashMap<String, Boolean> sport = new HashMap<String, Boolean>() {
            {
                put("Soccer", false);
                put("Cricket", true);
                put("Tennis", false);
                put("Basketball", false);
                put("Fancy", false);
                put("Other", false);
            }
        };
        int maxLiabilityPerMarket = 1011;
        log("Step 2. Search PL account and Exchange Product");
        page.search(loginID,"", "","");

        log("Step 3. Select Cricket only");
        page.enableSport(sport);
        List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(sport);
        lstExpectedData.get(2).set(1, StringUtils.formatCurrency(String.format("%.2f",(double) maxLiabilityPerMarket)));

        log("Step 4.Input valid Max Liability Per Market then click Update ");
        page.updateBetSetting(loginID,-1,-1,maxLiabilityPerMarket,-1);

        log("Verify 1. Verify Max Liability Per Market is update correctly, Update Status column display green check");
        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(sport);
        Assert.assertTrue(page.verifyUpdateStatus(lstActualData,true,sport),"FAILED! Green check not display on Update Status column");
        Assert.assertEquals(lstActualData,lstExpectedData,"FAILED! Expected not match with the actual");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify update bet setting with valid  Max Liability Per Market Setting
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Bet Setting Listing
     *          2. Search PL account and Exchange Product
     *          3. Select Other only
     *          4. Input valid Max Win Per Market
     *          5. Click Update
     * @expect: 1. Verify Max Liability Per Market is updated, Update Status column display green check
     */

    @Test (groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Bet_Setting_Listing_0007(String brandname) {
        log("@title: Verify update bet setting with valid  Max Win Per Market Setting");
        log("Step 1. Navigate Agency Management > Bet Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0).getUserCode();
        BetSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, BET_SETTING_LISTING, BetSettingListingPage.class);
        HashMap<String, Boolean> sport = new HashMap<String, Boolean>() {
            {
                put("Soccer", false);
                put("Cricket", false);
                put("Tennis", false);
                put("Basketball", false);
                put("Fancy", false);
                put("Other", true);
            }
        };
        int maxWinPerMarket = 1302;
        log("Step 2. Search PL account and Exchange Product");
        page.search(loginID,"", "","");

        log("Step 3. Select Other only");
        page.enableSport(sport);
        List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(sport);
        lstExpectedData.get(3).set(1,StringUtils.formatCurrency( String.format("%.2f",(double)maxWinPerMarket)));

        log("Step 4. Input valid Max Win Per Market then click Update ");
        page.updateBetSetting(loginID,-1,-1,-1,maxWinPerMarket);

        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(sport);
        log("Verify 1. Verify  Input valid Max Win Per Market is update correctly, Update Status column display green check");
        Assert.assertTrue(page.verifyUpdateStatus(lstActualData,true,sport),"FAILED! Green check not display on Update Status column");
        Assert.assertEquals(lstActualData,lstExpectedData,"FAILED! Expected not match with the actual");

        log("INFO: Executed completely");
    }

}

