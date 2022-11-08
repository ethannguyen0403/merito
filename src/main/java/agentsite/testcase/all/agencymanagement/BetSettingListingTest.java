package agentsite.testcase.all.agencymanagement;

import agentsite.common.AGConstant;
import agentsite.pages.all.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import com.paltech.utils.StringUtils;
import agentsite.objects.agent.account.AccountInfo;
import membersite.common.FEMemberConstants;
import membersite.objects.AccountBalance;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.all.tabexchange.SportPage;
import membersite.utils.betplacement.BetUtils;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.BetSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.*;
import static agentsite.common.AGConstant.HomePage.BLOCK_UNBLOCK_EVENT;

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

    @Test (groups = {"interaction2"})
    @Parameters({"downlineAccount","memberAccount","password"})
    public void Agent_AM_Bet_Setting_Listing_0010(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Cannot place bet when max liability on a market excceed the setting");
        log("Step 1. Active Bet Setting Listing and update max liability on a market for a player on Cricket");
        String sportName ="Cricket";
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        AccountInfo accountInfo = DownLineListingUtils.getAccountInfoInList(lstUsers,memberAccount);
        String userCode = accountInfo.getUserCode();
       // String memberAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockUnblockEventPage.filter("",sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName,childID,"TODAY");
        Event event = eventList.get(0);
        blockUnblockEventPage.blockUnblockEvent(downlineAccount,event.getEventName(),"Unblock Now","",1);

        BetSettingListingPage page = blockUnblockEventPage.clickSubMenu(AGENCY_MANAGEMENT, BET_SETTING_LISTING, BetSettingListingPage.class);
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

        page.search(memberAccount,"", "","");
        page.enableSport(sport);
        List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(sport);
        String min = lstExpectedData.get(0).get(8);
       /* String max = lstExpectedData.get(1).get(1);
        String maxLiabilyBeforeUpdate = lstExpectedData.get(2).get(1);*/
        int maxLiabilityOnMarket = (int) Double.parseDouble(min);
        lstExpectedData.get(2).set(1,StringUtils.formatCurrency( String.format("%.2f",(double)maxLiabilityOnMarket)));
        page.updateBetSetting(memberAccount,-1,-1,maxLiabilityOnMarket,-1);

       // List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(sport);
        log("Step 2.Login member site with the player  in step 1");
        loginMember(memberAccount,password);
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName,SportPage.class);
        event = sportPage.setEventLink(event);
        event.getLinkEvent().click();
        Market market = sportPage.marketContainerControl_SAT.getMarket(event, 1, true);
        String odds = market.getBtnOdd().getText();
        market.getBtnOdd().click();

        log(String.format("Step 3. Place bet with odds:%s Stake: %s",odds,min));
        int stake = (int) Double.parseDouble(min) + 1;
        sportPage.betSlipControl.placeBet(odds, min);

        log(String.format("Verify 1: Verify cannot place bet if min bet less than the setting"));
        String actualError = sportPage.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(FEMemberConstants.BetSlip.ERROR_EXCEED_LIABILITY, String.format("%,.2f",stake),String.format("%(,.2f",min));

        log("Verify Verify error message display when place bet");
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));

        log("INFO: Executed completely");
    }

    @Test (groups = {"interaction"})
    @Parameters({"memberAccount","password"})
    public void Agent_AM_Bet_Setting_Listing_0011(String downlineAccount,String memeberAccount, String password) throws Exception {
        log("@title: Cannot place bet when potential win is greater than the setting");
        log("Step 1 Active Bet Setting Listing and update Max Win per market for Soccer");
        String sportName ="Cricket";
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        AccountInfo acc = ProfileUtils.getProfile();
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockUnblockEventPage.filter("",sportName, AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1));
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName,childID,"TODAY");
        Event event = eventList.get(0);

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

        page.search(loginID,"", "","");
        page.enableSport(sport);
        List<ArrayList<String>> lstExpectedData = page.getBetSettingofAccount(sport);
        String min = lstExpectedData.get(3).get(1);
        String maxLiabilyBeforeUpdate = lstExpectedData.get(3).get(1);
        String max = lstExpectedData.get(3).get(2);
        int maxLiabilityOnMarket = Integer.valueOf(min);
        lstExpectedData.get(3).set(1,StringUtils.formatCurrency( String.format("%.2f",(double)maxLiabilityOnMarket)));
        page.updateBetSetting(loginID,-1,-1,maxLiabilityOnMarket,-1);

        List<ArrayList<String>> lstActualData = page.getBetSettingofAccount(sport);

        log("Step 2 Login member site with the player  in step 1");
        loginMember(memeberAccount,password);
        SportPage sportPage = memberHomePage.navigateSportMenu(sportName,SportPage.class);
        event = sportPage.setEventLink(event);
        Market market = sportPage.marketContainerControl_SAT.getMarket(event, 1, true);
        String odds = market.getBtnOdd().getText();
        market.getBtnOdd().click();

        log("Step 3 On a market place more bet that has liability on a market is exceed the setting");
        sportPage.betSlipControl.placeBet(odds, min);

        log("Verify Verify error message display when place bet");
        String actualError = sportPage.myBetControl.getPlaceBetErrorMessage();
        String expectedError = String.format(FEMemberConstants.BetSlip.ERROR_STAKE_NOT_VALID, String.format("%.2f",min),String.format("%(,.2f",max),String.format("%.2f",min));
        Assert.assertEquals(actualError,expectedError,String.format("ERROR! Expected error message is %s but found %s", expectedError,actualError));


        log("INFO: Executed completely");
    }

}

