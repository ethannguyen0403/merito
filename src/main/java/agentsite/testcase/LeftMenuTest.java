package agentsite.testcase;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.components.QuickSetting;
import agentsite.pages.components.SuccessPopup;
import agentsite.pages.report.ClientLedgerPage;
import agentsite.pages.report.ProfitAndLossPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.*;
import static common.AGConstant.MEMBER;

public class LeftMenuTest extends BaseCaseTest {

    /**
     * @title: Validate can active Quick Search
     * @steps: Login agent by Control Blocking Level (SAT is SAD, and Partner for the white labels)
     * 1. Click on Quick Search button
     * @expect: 1 Textbox Username or Login ID with Submit button display
     */
    @TestRails(id = "3464")
    @Test(groups = {"regression"})
    public void LeftMenu_TC3464() {
        log("@title: Validate can active Quick Search");
        log("Step 1: Click on Quick Search button");
        agentHomePage.leftMenu.switchQuickSearch();

        log("Verify: 1.Textbox Username or Login ID with Submit button display");
        Assert.assertTrue(agentHomePage.quickSearch.txtQuickSearch.isDisplayed(), "FAILED! Quịck Search textbox does not display");
        Assert.assertEquals(agentHomePage.quickSearch.btnSearchQuickSearch.getText(), BTN_SUBMIT, "FAILED! Quịck Search textbox does not display");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Main Menu is active by default
     * @steps: Login agent by Control Blocking Level (SAT is SAD, and Partner for the white labels)
     * @expect: 1. Main Menu, Quick Search
     * 2. A menu in Main menu display to verify Main menu is active by default
     */
    @TestRails(id = "3465")
    @Test(groups = {"regression"})
    public void LeftMenu_TC3465() {
        log("@title: Validate Main Menu is active by default");
        log("Verify: 1. Main Menu, Quick Search");
        Assert.assertEquals(agentHomePage.leftMenu.tabMainMenu.getText(), MAIN_MENU, "Failed! Quick Search tab is incorrect displayed");
        Assert.assertEquals(agentHomePage.leftMenu.tabQuickSearch.getText(), QUICK_SEARCH, "Failed! Quick Search tab is incorrect displayed");

        log("Verify: 2. A menu in Main menu display to verify Main menu is active by default");
        List<String> lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertTrue(lstSubReprotMenu.contains(DOWNLINE_LISTING), "FAILED! Agency Management section does not expand by default after login");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate all items menu in Agency Management for Credit account
     * @steps: Login agent by Control Blocking Level (SAT is SAD, and Partner for the white labels)\
     * 1 Click on Agency Management
     * 2. Click the + to expand the rest menus
     * @expect: 1. Display Credit/Balance Listing, Transfer, and Risk Setting Listing menu
     * 2. Not display Deposit/Withdraw menu for credit account
     */
    @TestRails(id = "3467")
    @Test(groups = {"regression_credit"})
    public void LeftMenu_TC3467() {
        log("@title: Validate all items menu in Agency Management for Credit account");
        log("Step:1 Click on Agency Management collapse");
        List<String> lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        log("Verify:  1. Display Credit/Balance Listing, Transfer, and Risk Setting Listing menu");
        Assert.assertEquals(lstSubReprotMenu.get(0), "Create Downline Agent", "FAILED!Create Downline Agent not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(1), "Create User", "FAILED! Create User not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(2), "Downline Listing", "FAILED! Downline Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(3), "Position Taking Listing", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(4), "Transfer", "FAILED! Transfer not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(5), "Statement Report", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(6), "Client Ledger", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(7), "Credit/Balance Listing", "FAILED! Credit/Balance Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(8), "Commission Listing", "FAILED! Commission Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(9), "Bet Setting Listing", "FAILED! Bet Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(10), "Risk Setting Listing", "FAILED! Risk Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(11), "Tax Settings", "FAILED! Tax Settings not display in Agency Management Menu Credit Line");

        log("Verify:  2. Not display Deposit/Withdraw menu for credit account");
        Assert.assertFalse(lstSubReprotMenu.contains(DEPOSIT_WITHDRAW), "FAILED! Credit Line should not display deposit withdraw");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate all items menu in Agency Management for Credit account
     * @steps: Login agent by Control Blocking Level (SAT is SAD, and Partner for the white labels)\
     * 1 Click on Agency Management
     * 2. Click the + to expand the rest menus
     * @expect: 1. Display Credit/Balance Listing, Transfer, and Risk Setting Listing menu
     * 2. Not display Deposit/Withdraw menu for credit account
     */
    @TestRails(id = "3889")
    @Test(groups = {"regression_creditcash"})
    public void LeftMenu_TC3889_CreditCash() {
        log("@title: Validate all items menu in Agency Management for Credit Cash account");
        log("Step:1 Click on Agency Management collapse");
        List<String> lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        log("Verify:  1. Display Credit/Balance Listing, Transfer, and Risk Setting Listing menu");
        Assert.assertEquals(lstSubReprotMenu.get(0), "Create Downline Agent", "FAILED!Create Downline Agent not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(1), "Create User", "FAILED! Create User not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(2), "Downline Listing", "FAILED! Downline Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(3), "Position Taking Listing", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(4), "Transfer", "FAILED! Transfer not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(5), "Statement Report", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(6), "Client Ledger", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(7), "Credit/Balance Listing", "FAILED! Credit/Balance Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(8), "Commission Listing", "FAILED! Commission Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(9), "Bet Setting Listing", "FAILED! Bet Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(10), "Risk Setting Listing", "FAILED! Risk Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(11), "Tax Settings", "FAILED! Tax Settings not display in Agency Management Menu Credit Line");

        log("Verify:  2. Not display Deposit/Withdraw menu for credit account");
        Assert.assertFalse(lstSubReprotMenu.contains(CREDIT_BALANCE_LISTING), "FAILED! Credit Line should not display deposit withdraw");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can expand/collapse Agency Management
     * @steps: Login agent by Control Blocking Level (SAT is SAD, and Partner for the white labels)\
     * 1 Click on Agency Management collapse
     * 2. Expand Agency Management
     * @expect: 1.Can Expand/Colapse and all menus display correctly
     */
    @TestRails(id = "3466")
    @Test(groups = {"regression"})
    public void LeftMenu_TC3466() {
        log("@title: Validate can expand/collapse Agency Management");
        log("Step:1 Click on Agency Management collapse");
        agentHomePage.leftMenu.leftMenuList.collapsedMenu(AGENCY_MANAGEMENT);

        log("Verify: 1. Agency Management collapsed");
        Assert.assertFalse(agentHomePage.leftMenu.isMenuExpanded(AGENCY_MANAGEMENT), "FAILED! The menu not collapsed");

        log("Step:2. Expand Agency Management");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);

        log("Verify: 2. Agency Management expanded");
        Assert.assertTrue(agentHomePage.leftMenu.isMenuExpanded(AGENCY_MANAGEMENT), "FAILED! The menu not collapsed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate display Commission Setting listing in Agency Management if 1 of the product: Live Dealer Europeans, Lottery & Slot, Live Dealer Asian display
     * @steps: Login agent by Control Blocking Level (SAT is SAD, and Partner for the white labels)\
     * 1 Click on Agency Management
     * 2. Click the + to expand the rest menus
     * @expect: 1/ If the account is active  one  of the products: Live Dealer Europeans, Lottery & Slot, Live Dealer Asian display, Commission Setting Listing menu display
     */
    @TestRails(id = "3468")
    @Test(groups = {"regression"})
    public void LeftMenu_TC3468() {
        log("@title: Validate can expand/collapse Agency Management");
        log("Step:1 Have an account that active the product Live Dealer Europeans, Lottery & Slot, Live Dealer Asian");
        log("Click on Agency Management collapse");
        List<String> lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        log("Verify: 1Commission Setting Listing menu display");
        Assert.assertTrue(lstSubReprotMenu.contains(COMMISSION_LISTING), "FAILED! Commission Listing menu not display ");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can expand/colapse Report and all item display correctly
     * @steps: 1 Click on Report
     * 2. Click the + to expand the rest menus
     * 3. Collapse the menu then click on Report
     * @expect: 1. Verify Report is expanded/collapse a halt and full
     * 2. Verify all menu items in Report is correctly
     */
    @TestRails(id = "3469")
    @Test(groups = {"regression_po"})
    public void LeftMenu_TC3469_PO() {
        log("@title: Validate can expand/collapse Report and all item display correctly");
        log("Step:1 Expand the report");
        agentHomePage.leftMenu.leftMenuList.expandMenu(REPORT);

        log("Verify 1: Can expand Report");
        Assert.assertTrue(agentHomePage.leftMenu.isMenuExpanded(REPORT), "FAILED! The menu not expanded");

        log("Step 2 Click the + to expand the rest menus");
        List<String> lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(REPORT);

        log("Step 3. Collapse the menu then click on Report");
        agentHomePage.leftMenu.leftMenuList.collapsedMenu(REPORT);

        log("Verify 1: Can Collapse Report");
        Assert.assertFalse(agentHomePage.leftMenu.isMenuExpanded(REPORT), "FAILED! The menu is not collapsed");

        log("Verify 3: the list menu in report is correctly displayed");
        Assert.assertEquals(lstSubReprotMenu.get(0), "Profit And Loss", "FAILED! Profit And Loss not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(1), "Win Loss", "FAILED! Win Loss Simple not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(2), "Unsettled Bet", "FAILED!Create Downline Agent not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(3), "Cancelled Bets", "FAILED! Cancelled Bets not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(4), "BF Voided Discrepancy", "FAILED! BF Voided Discrepancy not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(5), "Top Gainers & Top Losers", "FAILED! Top Gainers & Top Losers not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(6), "Follow Bets Performance", "FAILED! Follow Bets Performance not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(7), "Transfer Log", "FAILED! Transfer Log not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(8), "View Log", "FAILED! View Log not display in Report Menu Credit Line");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can expand/colapse Report and all item display correctly
     * @steps: 1 Click on Report
     * 2. Click the + to expand the rest menus
     * 3. Collapse the menu then click on Report
     * @expect: 1. Verify Report is expanded/collapse a halt and full
     * 2. Verify all menu items in Report is correctly
     */
    @TestRails(id = "3888")
    @Test(groups = {"regression_newui"})
    public void LeftMenu_TC3888() {
        log("@title: Validate can expand/collapse Report and all item display correctly");
        log("Step:1 Expand the report");
        agentHomePage.leftMenu.leftMenuList.expandMenu(REPORT);

        log("Verify 1: Can expand Report");
        Assert.assertTrue(agentHomePage.leftMenu.isMenuExpanded(REPORT), "FAILED! The menu not expanded");

        log("Step 2 Click the + to expand the rest menus");
        List<String> lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(REPORT);

        log("Step 3. Collapse the menu then click on Report");
        agentHomePage.leftMenu.leftMenuList.collapsedMenu(REPORT);

        log("Verify 1: Can Collapse Report");
        Assert.assertFalse(agentHomePage.leftMenu.isMenuExpanded(REPORT), "FAILED! The menu is not collapsed");

        log("Verify 3: the list menu in report is correctly displayed");
        Assert.assertEquals(lstSubReprotMenu.get(0), "Profit And Loss", "FAILED! Profit And Loss not display in Report Menu ");
        Assert.assertEquals(lstSubReprotMenu.get(1), "Win Loss", "FAILED! Win Loss Simple not display in Report Menu ");
        Assert.assertEquals(lstSubReprotMenu.get(2), "Position Taking Report", "FAILED! Position Taking Report not display in Report Menu ");
        Assert.assertEquals(lstSubReprotMenu.get(3), "Unsettled Bet", "FAILED!Create Downline Agent not display in Report Menu ");
        Assert.assertEquals(lstSubReprotMenu.get(4), "Cancelled Bets", "FAILED! Cancelled Bets not display in Report Menu ");
        Assert.assertEquals(lstSubReprotMenu.get(5), "Top Gainers & Top Losers", "FAILED! Top Gainers & Top Losers not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(6), "Transfer Log", "FAILED! Transfer Log not display in Report Menu ");
        Assert.assertEquals(lstSubReprotMenu.get(7), "View Log", "FAILED! View Log not display in Report Menu ");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can expand/colapse Market Management and all item display correctly
     * @steps: 1 Click on Market Management
     * 2. Expand and collapse the menu
     * @expect: 1. Verify Market Management is expanded/collapse correctly
     * 2. Verify all menu items in Market Management is correctly
     */
    @TestRails(id = "3470")
    @Test(groups = {"regression"})
    public void LeftMenu_TC3470() {
        log("@title: Validate can expand/collapse  Market Management and all item display correctly");
        log("Step:1 Expand the Market Management");
        agentHomePage.leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);

        log("Verify 1: Can expand Market Management");
        Assert.assertTrue(agentHomePage.leftMenu.isMenuExpanded(MARKET_MANAGEMENT), "FAILED! The menu not expanded");

        log("Step 2. Get all sub menu in Market Management");
        List<String> lstSubReprotMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);

        log("Step 3. Collapse the menu then click on  Market Management");
        agentHomePage.leftMenu.leftMenuList.collapsedMenu(MARKET_MANAGEMENT);

        log("Verify 1: Can Collapse Market Management");
        Assert.assertFalse(agentHomePage.leftMenu.isMenuExpanded(MARKET_MANAGEMENT), "FAILED! The menu is not collapsed");

        log("Verify 3: the list menu in Market Management is correctly displayed");
        Assert.assertEquals(lstSubReprotMenu.get(0), "Block Racing", "FAILED! Block Racing not display in Market Management ");
        Assert.assertEquals(lstSubReprotMenu.get(1), "Block/Unblock Events", "FAILED! Block/Unblock Events not display in Market Management ");
        Assert.assertEquals(lstSubReprotMenu.get(2), "Block/Unblock Competitions", "FAILED! Block/Unblock Competitions not display in Market Management ");
        Assert.assertEquals(lstSubReprotMenu.get(3), "Current Blocking", "FAILED! Current Blocking not display in Market Management ");
        Assert.assertEquals(lstSubReprotMenu.get(4), "Blocking Log", "FAILED! Blocking Log not display in Market Management ");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search direct member account in quick search
     * @steps: 1. Click on Quick Search button
     * 2. Enter direct login ID of agent level
     * 3. Click Submit button
     * @expect: 1 Verify account info and his direct update display
     * (e.g (1) AD   0GU (smaag1)
     * (2) Member  0GU0000003 (sabella1))
     * 2. Verify all report display : Balance, Unsettled Bet, Client Ledger, Settings, Login
     */
    @TestRails(id = "3471")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3471(String brandname) {
        log("@title: Validate can search direct member account in quick search");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname);
        String accountDisplay = listAccount.get(0).getUserCodeAndLoginID("%s (%s)");

        log("Step:1. Click on Quick Search button");
        log("Step:2. Enter direct login ID of agent level");
        log("Step:3. Click Submit button");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(accountDisplay);

        log("Verify 1 Verify account info and his direct update display");
        Assert.assertEquals(agentHomePage.quickSearch.lblLevelQS.getText(), ProfileUtils.convertDownlineByBrand(downlineLevel, ProfileUtils.getAppName()), "FAILED! Level in quick search is incorrect displayed");
        Assert.assertEquals(agentHomePage.quickSearch.lblAccountQS.getText(), accountDisplay, "FAILED! Account display in quick search incorrect");

        log("Verify 2. Verify all report display : Balance, Unsettled Bet, Client Ledger, Settings, Login");
        List<String> lstQuickSearchMenu = agentHomePage.quickSearch.mtQuickSearchMenu.getListSubMenu();
        Assert.assertEquals(lstQuickSearchMenu, LST_QUICK_SEARCH_MENU, "FAILED, Quick Search menu is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search indirect member account in quick search
     * @steps: 1. Click on Quick Search button
     * 2. Enter user name of the indirect member account
     * 3. Click Submit button
     * @expect: 1 Verify account info and direct member agent account and member account display
     * 2. Verify all report display : Balance, Unsettled Bet, Client Ledger, Settings, Login
     */
    @TestRails(id = "3472")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3472(String brandname) {
        log("@title: Validate can search indirect member account in quick search");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        AccountInfo inDirectDownline = DownLineListingUtils.getDownLineUsers(directDownline.getUserID(), "", "ACTIVE", brandname).get(0);
        String directAccountDisplay = directDownline.getUserCodeAndLoginID("%s (%s)");
        String indirectAccountDisplay = inDirectDownline.getUserCodeAndLoginID("%s (%s)");
        log("Step:1. Click on Quick Search button");
        log("Step:2. Enter direct login ID of agent level");
        log("Step:3. Click Submit button");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(inDirectDownline.getUserCode());

        log("Verify 1 Verify account info and his direct update display");
        Assert.assertEquals(agentHomePage.quickSearch.lblLevelQS.getText(), ProfileUtils.convertDownlineByBrand(directDownline.getLevel(), ProfileUtils.getAppName()), "FAILED! Level in quick search is incorrect displayed");
        Assert.assertEquals(agentHomePage.quickSearch.lblAccountQS.getText(), directAccountDisplay, "FAILED! Account display in quick search incorrect");
        Assert.assertEquals(agentHomePage.quickSearch.lblLevelIndrectQS.getText(), ProfileUtils.convertDownlineByBrand(inDirectDownline.getLevel(), ProfileUtils.getAppName()), "FAILED! Level in quick search is incorrect displayed");
        Assert.assertEquals(agentHomePage.quickSearch.lblAccountIndrectQS.getText(), indirectAccountDisplay, "FAILED! Account display in quick search incorrect");

        log("Verify 2. Verify all report display : Balance, Unsettled Bet, Client Ledger, Settings, Login");
        List<String> lstQuickSearchMenu = agentHomePage.quickSearch.mtQuickSearchMenu.getListSubMenu();
        Assert.assertEquals(lstQuickSearchMenu, LST_QUICK_SEARCH_MENU, "FAILED, Quick Search menu is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Balance button in quick search section works
     * @steps: 1. Click on Quick Search button
     * 2. Search any available account
     * 3. Click on Balance button
     * @expect: 1. Verify Balance Page displayed
     */
    @TestRails(id = "3473")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3473(String brandname) {
        log("@title: Verify Balance button in quick search section works");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        log("Step: 1. Click on Quick Search button");
        log("Step: 2. Search any available account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());
        log("Step:1. Verify Balance Page displayed");
        Table tblBalance = agentHomePage.quickSearch.clickBalance();

        log("Verify 1 Verify account info and his direct update display");
        Assert.assertTrue(tblBalance.getColumn(1, false).contains("Available Balance"), "FAILED! Balance table info not display after clicking");


        log("INFO: Executed completely");
    }

    /**
     * @title:Verify Downline Listing button in quick search section works
     * @steps: 1. Click on Quick Search button
     * 2. Search any available account
     * 3. Click on Downline Listing button
     * @expect: 1. Verify Downline Listing page display
     */
    @TestRails(id = "3474")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3474(String brandname) throws Exception {
        log("@title: Downline Listing button in quick search section works");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        log("Step: 1. Click on Quick Search button");
        log("Step: 2. Search any available account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());

        log("Step:3. Click on Downline Listing button");

        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingFromQuickSearch();

        log("Verify 1. Verify Downline Listing page is displayed");
        Assert.assertEquals(downLineListingPage.header.lblPageTitle.getText(), DOWNLINE_LISTING, "FAILED! Page title is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Profit & Loss button in quick search section works
     * @steps: 1. Click on Quick Search button
     * 2. Search any available account
     * 3. Click on Profit & Loss button
     * @expect: 1. Verify Profit & Loss page display
     */
    @TestRails(id = "3475")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3475(String brandname) {
        log("@title: Verify Profit & Loss button in quick search section works");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        log("Step: 1. Click on Quick Search button");
        log("Step: 2. Search any available account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());

        log("Step:3. Click on Profit & Loss button");
        ProfitAndLossPage profitAndLossPage = agentHomePage.clickProfitAndLossinQuickSearch();

        log("Verify 1. Verify Profit & Loss page is displayed");
        Assert.assertEquals(profitAndLossPage.header.lblPageTitle.getText(), PROFIT_AND_LOSS, "FAILED! Page title is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Client Ledger button in quick search section works
     * @steps: 1. Click on Quick Search button
     * 2. Search any available account
     * 3. Click on Client Ledger button
     * @expect: 1. Verify Client Ledger page display
     */
    @TestRails(id = "3476")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3476(String brandname) {
        log("@title: Verify Client Ledger button in quick search section works");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        log("Step: 1. Click on Quick Search button");
        log("Step: 2. Search any available account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());

        log("Step:3. Click on Client Ledger button");
        ClientLedgerPage clientLedgerPage = agentHomePage.clickClientLedgerinQuickSearch();

        log("Verify 1. Verify Client Ledger page is displayed");
        Assert.assertEquals(clientLedgerPage.header.lblPageTitle.getText(), CLIENT_LEDGER, "FAILED! Client Ledger title page is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Setting button in quick search section works
     * @steps: 1. Click on Quick Search button
     * 2. Search any available account
     * 3. Click on Setting button
     * @expect: 1. Verify Setting section display
     */
    @TestRails(id = "3478")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3478(String brandname) {
        log("@title: Verify Setting button in quick search section works");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        log("Step: 1. Click on Quick Search button");
        log("Step: 2. Search any available account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());

        log("Step:3. Click on Setting button");
        QuickSetting quickSetting = agentHomePage.quickSearch.clickSetting();

        log("Verify 1. Verify Setting is displayed");
        Assert.assertEquals(quickSetting.lblAccStatus.getText(), "Acc Status", "FAILED! setting table not displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Login button in quick search section works
     * @steps: 1. Click on Quick Search button
     * 2. Search any available account
     * 3. Click on lOGIN button
     * @expect: 1. Verify login info display
     */
    @TestRails(id = "3477")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3477(String brandname) {
        log("@title: Verify Login button in quick search section works");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        log("Step: 1. Click on Quick Search button");
        log("Step: 2. Search any available account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());

        log("Step:3. Click on Login button");
        Table tblLogin = agentHomePage.quickSearch.clickLogin();

        log("Verify 1. Verify Login page is displayed");
        Assert.assertEquals(tblLogin.getColumn(1, false), LST_QUICK_SEARCH_LOGIN, "FAILED! Page title is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can update account status in Quick Search
     * @steps: 1. Click on Quick Search button
     * 2. Search any available account
     * 3. Click on Setting button
     * 4. Update Account status and click submit
     * 5. Post-Condition: Update status of account to active
     * @expect: 1. Account status is update
     * 2. Active downline setting page and search the acording account
     * 3. Status is updated
     */
    @TestRails(id = "3479")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3479(String brandname) throws Exception {
        log("@title: Verify can update account status in Quick Search");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0);
        String displayAccount = directDownline.getUserCodeAndLoginID("%s (%s)");
        log("Step: 1. Click on Quick Search button");
        log("Step: 2. Search any available account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());

        log("Step:3. Click on Setting button");
        QuickSetting quickSetting = agentHomePage.quickSearch.clickSetting();

        log("Step:4. Update Account status and click submit");
        SuccessPopup popup = quickSetting.updateStatus("Suspended");

        log("Verify 1. Account status is update");
        Assert.assertEquals(popup.getContentMessage(), String.format("%s is updated to Suspended successfully.", displayAccount), "FAILED! Success message is incorrect display after update status");
        popup.close();

        log("Step: 5. Active downline setting page and search the acording account");
        agentHomePage.leftMenu.switchMainMenu();
        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
        downLineListingPage.downlineListing.searchDownline(directDownline.getUserCode(), "Suspended", MEMBER);

        log("Verify 2. Status is updated");
        List<String> lstRecord = downLineListingPage.tblDowlineListing.getColumn(downLineListingPage.userCodeCol, false);
        Assert.assertEquals(lstRecord.get(0), directDownline.getUserCode(), String.format("Failed! Expected login id %s display but found %s", directDownline.getUserCode(), lstRecord.get(0)));

        log("Step: Post condition: Reactive the account");
        downLineListingPage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());
        agentHomePage.quickSearch.clickSetting().updateStatus("Active").close();

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify all sub menu in Setting page work
     * @steps: 1. Click on Quick Search button
     * 2. Search any available event
     * 3. Click on Setting button
     * @expect: 1. Verify sub menu item is correctly displayed
     */
    @TestRails(id = "3480")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3480(String brandname) {
        log("@title: Verify all sub menu in Setting page work");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        log("Step: 1. Click on Quick Search button");
        log("Step: 2. Search any available account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());

        log("Step:3. Click on Setting button");
        QuickSetting quickSetting = agentHomePage.quickSearch.clickSetting();
        List<String> lstMenuSetting = quickSetting.mtSettingMenu.getListSubMenu();

        log("Verify1. Verify sub menu item is correctly displayed");
        Assert.assertEquals(lstMenuSetting, LST_QUICK_SEARCH_SETTING_MENU, "FAILED! List menu in Setting is incorrect displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify the list menu display correct when login Portal level
     * * @steps:   Login Agent site by PO level
     * 1. Expand all left menu
     * @expect: Verify the list menu display correct when login Portal level
     */
    @TestRails(id = "675")
    @Test(groups = {"smoke_po"})
    public void LeftMenu_TC019() {
        log("@title:  Verify the list menu display correct when login Portal level");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when login Portal level:");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), "Create Company", "Failed! Create Company not display correctly");
        Assert.assertEquals(lstSubMennu.get(1), "Downline Listing", "Failed! Downline Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(2), "Position Taking Listing", "Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(3), "Deposit/Withdraw", "Failed! Agency Management not display correctly");
        Assert.assertEquals(lstSubMennu.get(4), "Transfer", "Failed! Transfer not display correctly");
        Assert.assertEquals(lstSubMennu.get(5), "Client Ledger", "Failed! Client Ledger not display correctly");
        Assert.assertEquals(lstSubMennu.get(6), "Follow Bets", "Failed! Agency Management not display correctly");
        Assert.assertEquals(lstSubMennu.get(7), "Commission Listing", "Failed!Commission Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(8), "Bet Setting Listing", "Failed!Bet Setting Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(9), "Tax Settings", "Failed! Tax Settings not display correctly");
        Assert.assertEquals(lstSubMennu.get(10), "Risk Setting Listing", "Failed! Risk Setting Listing not display correctly");
        agentHomePage.leftMenu.leftMenuList.expandMenu(RISK_MANAGEMENT);
        List<String> lstRiskManagement = agentHomePage.leftMenu.leftMenuList.getListSubMenu(RISK_MANAGEMENT);
        Assert.assertEquals(lstRiskManagement.get(0), "Risk Management", "Failed!Risk Management not display correctly");
        Assert.assertEquals(lstRiskManagement.get(1), "Analysis of Running Markets", "Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(lstRiskManagement.get(2), "Agent Exposure Limit", "Failed! Agent Exposure Limit not display correctly");
        agentHomePage.leftMenu.leftMenuList.expandMenu(REPORT);
        List<String> lstReport = agentHomePage.leftMenu.leftMenuList.getListSubMenu(REPORT);
        Assert.assertEquals(lstReport.get(0), "Report", "Failed! Report not display correctly");
        Assert.assertEquals(lstReport.get(1), "Profit And Loss", "Failed! Profit And Loss not display correctly");
        Assert.assertEquals(lstReport.get(2), "Win Loss", "Failed! Win Loss not display correctly");
        Assert.assertEquals(lstReport.get(3), "Unsettled Bet", "Failed! Unsettle Bet not display correctly");
        Assert.assertEquals(lstReport.get(4), "Cancelled Bets", "Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(lstReport.get(5), "Statement Report", "Failed! Statement Report not display correctly");
//        Assert.assertEquals(lstReport.get(2),"Simple","Failed!Simple not display correctly");
//        Assert.assertEquals(lstReport.get(2),"By Market","Failed! By Market not display correctly");
//        Assert.assertEquals(lstReport.get(2),"By Event","Failed! By Event not display correctly");
//        Assert.assertEquals(lstReport.get(2),"CO By Detail","Failed! CO By Detail not display correctly");
//        Assert.assertFalse(lstReport.get(2),"Failed! Position Taking Report not display correctly");
        Assert.assertEquals(lstReport.get(6), "BF Voided Discrepancy", "Failed! BF Voided Discrepancy not display correctly");
        Assert.assertEquals(lstReport.get(7), "Top Gainers & Top Losers", "Failed! Top Gainers & Top Losers not display correctly");
        Assert.assertEquals(lstReport.get(8), "Follow Bets Performance", "Failed!Follow Bets Performance not display correctly");
        Assert.assertEquals(lstReport.get(9), "View Log", "Failed! View Log not display correctly");
        Assert.assertEquals(lstReport.get(10), "Transfer Log", "Failed!Transfer Log not display correctly");
        //   Assert.assertFalse(lstReport.get(11),"Failed!Portal summary should not display correctly");
        agentHomePage.leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMarketManagement = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);
        Assert.assertEquals(lstMarketManagement.get(0), "Bets/Markets Management", "Failed! Bets/Markets Management not display correctly");
        Assert.assertEquals(lstMarketManagement.get(1), "Block Racing", "Failed! Block Racing not display correctly");
        Assert.assertEquals(lstMarketManagement.get(2), "Block/Unblock Events", "Failed!Block/Unblock Events not display correctly");
        Assert.assertEquals(lstMarketManagement.get(3), "Block/Unblock Competitions", "Failed! Block/Unblock Competitions not display correctly");
        Assert.assertEquals(lstMarketManagement.get(4), "Current Blocking", "Failed! Current Blocking not display correctly");
        Assert.assertEquals(lstMarketManagement.get(5), "Blocking Log", "Failed! Blocking Log not display correctly");
        Assert.assertEquals(lstMarketManagement.get(6), "Liquidity Threshold", "Failed! Liquidity Threshold not display correctly");
        agentHomePage.leftMenu.leftMenuList.expandMenu(FRAUD_DETECTION);
        List<String> lstFraud = agentHomePage.leftMenu.leftMenuList.getListSubMenu(FRAUD_DETECTION);
        Assert.assertEquals(lstFraud.get(0), "Fraud Detection", "Failed! Fraud Detection not display correctly");
        Assert.assertEquals(lstFraud.get(1), "Fraud Detection", "Failed! Fraud Detection not display correctly");
        Assert.assertEquals(lstFraud.get(2), "Wager Odds History", "Failed! Wager Odds History not display correctly");
        Assert.assertEquals(lstFraud.get(3), "Fraud Permission", "Failed! Fraud Permission not display correctly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify the list menu display correct when login Control Blocking  level
     * @steps: Login Agent site by Control Blocking level
     * 1. Expand all left menu
     * @expect: Verify the list menu display correct when login Control Blocking level
     */
    @TestRails(id = "676")
    @Test(groups = {"smoke_po"})
    public void LeftMenu_TC020() {
        log("@title: Verify the list menu display correct when login Control Blocking level");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when login Control Blocking  level");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), "Create Downline Agent", "Failed! Create Company not display correctly");
        Assert.assertEquals(lstSubMennu.get(1), "Downline Listing", "Failed! Downline Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(2), "Position Taking Listing", "Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(3), "Deposit/Withdraw", "Failed! Agency Management not display correctly");
        Assert.assertEquals(lstSubMennu.get(4), "Statement Report", "Failed! Transfer not display correctly");
        Assert.assertEquals(lstSubMennu.get(5), "Client Ledger", "Failed! Client Ledger not display correctly");
        Assert.assertEquals(lstSubMennu.get(7), "Commission Listing", "Failed!Commission Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(8), "Bet Setting Listing", "Failed!Bet Setting Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(9), "Tax Settings", "Failed! Tax Settings not display correctly");
        Assert.assertFalse(lstSubMennu.contains("Transfer"), "Failed! Transfer not display correctly");
        Assert.assertFalse(lstSubMennu.contains("Credit Balance Listing"), "Failed! Credit Balance Listing. not display correctly");
        Assert.assertFalse(lstSubMennu.contains(" Follow Bets "), "Failed! Follow Bets not display correctly");
        Assert.assertEquals(lstSubMennu.contains("Transfer"), "Risk Setting Listing", "Failed! Risk Setting Listing not display correctly");

        agentHomePage.leftMenu.leftMenuList.expandMenu(RISK_MANAGEMENT);
        List<String> lstRiskManagement = agentHomePage.leftMenu.leftMenuList.getListSubMenu(RISK_MANAGEMENT);
        Assert.assertEquals(lstRiskManagement.get(0), "Risk Management", "Failed!Risk Management not display correctly");
        Assert.assertEquals(lstRiskManagement.get(1), "Analysis of Running Markets", "Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(lstRiskManagement.get(2), "Agent Exposure Limit", "Failed! Agent Exposure Limit not display correctly");


        agentHomePage.leftMenu.leftMenuList.expandMenu(REPORT);
        List<String> lstReport = agentHomePage.leftMenu.leftMenuList.getListSubMenu(REPORT);
        Assert.assertEquals(lstReport.get(0), "Report", "Failed! Report not display correctly");
        Assert.assertEquals(lstReport.get(1), "Profit And Loss", "Failed! Profit And Loss not display correctly");
        Assert.assertEquals(lstReport.get(2), "Win Loss", "Failed! Win Loss not display correctly");
        Assert.assertEquals(lstReport.get(3), "Unsettled Bet", "Failed! Unsettle Bet not display correctly");
        Assert.assertEquals(lstReport.get(4), "Cancelled Bets", "Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(lstReport.get(5), "Statement Report", "Failed! Statement Report not display correctly");
//        Assert.assertEquals(lstReport.get(2),"Simple","Failed!Simple not display correctly");
//        Assert.assertEquals(lstReport.get(2),"By Market","Failed! By Market not display correctly");
//        Assert.assertEquals(lstReport.get(2),"By Event","Failed! By Event not display correctly");
//        Assert.assertEquals(lstReport.get(2),"CO By Detail","Failed! CO By Detail not display correctly");
//        Assert.assertFalse(lstReport.get(2),"Failed! Position Taking Report not display correctly");
        Assert.assertEquals(lstReport.get(6), "BF Voided Discrepancy", "Failed! BF Voided Discrepancy not display correctly");
        Assert.assertEquals(lstReport.get(7), "Top Gainers & Top Losers", "Failed! Top Gainers & Top Losers not display correctly");
        Assert.assertEquals(lstReport.get(8), "Follow Bets Performance", "Failed!Follow Bets Performance not display correctly");
        Assert.assertEquals(lstReport.get(9), "View Log", "Failed! View Log not display correctly");
        Assert.assertEquals(lstReport.get(10), "Transfer Log", "Failed!Transfer Log not display correctly");

        agentHomePage.leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMarketManagement = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);
        Assert.assertEquals(lstMarketManagement.get(0), "Bets/Markets Management", "Failed! Bets/Markets Management not display correctly");
        Assert.assertEquals(lstMarketManagement.get(1), "Block Racing", "Failed! Block Racing not display correctly");
        Assert.assertEquals(lstMarketManagement.get(2), "Block/Unblock Events", "Failed!Block/Unblock Events not display correctly");
        Assert.assertEquals(lstMarketManagement.get(3), "Block/Unblock Competitions", "Failed! Block/Unblock Competitions not display correctly");
        Assert.assertEquals(lstMarketManagement.get(4), "Current Blocking", "Failed! Current Blocking not display correctly");
        Assert.assertEquals(lstMarketManagement.get(5), "Blocking Log", "Failed! Blocking Log not display correctly");
        Assert.assertEquals(lstMarketManagement.get(6), "Liquidity Threshold", "Failed! Liquidity Threshold not display correctly");

        agentHomePage.leftMenu.leftMenuList.expandMenu(FRAUD_DETECTION);
        List<String> lstFraud = agentHomePage.leftMenu.leftMenuList.getListSubMenu(FRAUD_DETECTION);
        Assert.assertEquals(lstFraud.get(0), "Fraud Detection", "Failed! Fraud Detection not display correctly");
        Assert.assertEquals(lstFraud.get(1), "Wager Odds History", "Failed! Wager Odds History not display correctly");
        Assert.assertEquals(lstFraud.get(2), "Fraud Permission", "Failed! Fraud Permission not display correctly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify the list menu display correct when other level
     * @steps: Login Agent site by the level under Control Blocking
     * 1. Expand all left menu
     * @expect: Verify the list menu display correct when other leve
     */
    @TestRails(id = "677")
    @Test(groups = {"nonPOSmoke"})
    public void LeftMenu_TC021() {
        log("@title: Verify the list menu display correct when other level");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when other level");
        /* Assert.assertEquals(agentHomePage.menuAgencyManagement.getText(),"Agency Management","Failed! Agency Management not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCreateDownLineAgent.getText(),"Create Downline Agent","Failed! Create DownLine Agent not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCreateUser.getText(),"Create User","Failed! Create User not display correctly");
        Assert.assertEquals(agentHomePage.subMenuDownLineListing.getText(),"Downline Listing","Failed! Downline Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuPositionTakingListing.getText(),"Position Taking Listing","Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuDepositWithdrawal.getText(),"Deposit/Withdraw","Failed! Agency Management not display correctly");
        Assert.assertEquals(agentHomePage.subMenuStatementReport.getText(),"Statement Report","Failed! Statement Report not display correctly");
        Assert.assertEquals(agentHomePage.subMenuClientLedger.getText(),"Client Ledger","Failed! Client Ledger not display correctly");
        agentHomePage.subMenuExpandAgencyManagement.click();
        Assert.assertEquals(agentHomePage.subMenuCommissionSettingListing.getText(),"Commission Listing","Failed!Commission Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBetSettingListing.getText(),"Bet Setting Listing","Failed!Bet Setting Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTaxSettingListing.getText(),"Tax Settings","Failed! Tax Settings not display correctly");
        Assert.assertFalse(agentHomePage.subMenuTransfer.isDisplayed(),"Failed! Transfer not display correctly");
        Assert.assertFalse(agentHomePage.subMenuCreditBalanceListing.isDisplayed(),"Failed! Credit Balance Listing. not display correctly");
        Assert.assertFalse(agentHomePage.subMenuFollowBets.isDisplayed(),"Failed! Follow Bets not display correctly");*/
        /*Assert.assertFalse(agentHomePage.subMenuRiskSettingListing.isDisplayed(),"Failed!Risk Setting Listing not display correctly");

        agentHomePage.menuRiskManagement.click();
        Assert.assertEquals(agentHomePage.menuRiskManagement.getText(),"Risk Management","Failed!Risk Management not display correctly");
        Assert.assertEquals(agentHomePage.subMenuAnalysisOfRunningMarkets.getText(),"Analysis of Running Markets","Failed! Analysis of Running Markets not display correctly");
        Assert.assertFalse(agentHomePage.subMenuAgentExposureLimit.isDisplayed(),"Failed! Agent Exposure Limit display ");

        agentHomePage.menuReport.click();
        Assert.assertEquals(agentHomePage.menuReport.getText(),"Report","Failed! Report not display correctly");
        Assert.assertEquals(agentHomePage.subMenuProfitAndLoss.getText(),"Profit And Loss","Failed! Profit And Loss not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLoss.getText(),"Win Loss","Failed! Win Loss not display correctly");

        agentHomePage.subMenuWinLoss.click();
        Assert.assertEquals(agentHomePage.subMenuWinLossSimple.getText(),"Simple","Failed!Simple not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossBySportAndMarketType.getText(),"By Market","Failed! By Market not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossByEvent.getText(),"By Event","Failed! By Event not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossDetail.getText(),"AG By Detail","Failed! MA By Detail not display correctly");
        Assert.assertEquals(agentHomePage.subMenuUnsettleBet.getText(),"Unsettled Bet","Failed! Unsettle Bet not display correctly");*/
        /*Assert.assertEquals(agentHomePage.subMenuCancelledBets.getText(),"Cancelled Bets","Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(agentHomePage.subMenuPositionTakingReport.getText(),"Position Taking Report","Failed!Position Taking Report not display correctly");

        agentHomePage.subMenuExpandReport.click();
        Assert.assertEquals(agentHomePage.subMenuTopGainersTopLosers.getText(),"Top Gainers & Top Losers","Failed! Top Gainers & Top Losers not display correctly");
        Assert.assertEquals(agentHomePage.subMenuViewLog.getText(),"View Log","Failed! View Log not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransferLog.getText(),"Transfer Log","Failed!Transfer Log not display correctly");
        Assert.assertFalse(agentHomePage.subMenuBFVoidedDiscrepancy.isDisplayed(),"Failed! BF Voided Discrepancy display");
        Assert.assertFalse(agentHomePage.subMenuFollowAndSmallBetsPerformance.isDisplayed(),"Failed! Follow Bets Performance display");

        Assert.assertFalse(agentHomePage.menuMarketsManagement.isDisplayed(),"Failed! Bets/Markets Management not display correctly");
        Assert.assertFalse(agentHomePage.subMenuBlockRacing.isDisplayed(),"Failed! Block Racing not display correctly");
        Assert.assertFalse(agentHomePage.subMenuBlockUnlockEvents.isDisplayed(),"Failed!Block/Unblock Events not display correctly");
        Assert.assertFalse(agentHomePage.subMenuBlockUnblockCompetition.isDisplayed(),"Failed! Block/Unblock Competitions not display correctly");
        Assert.assertFalse(agentHomePage.subMenuCurrentBlocking.isDisplayed(),"Failed! Current Blocking not display correctly");
        Assert.assertFalse(agentHomePage.subMenuBlockingLog.isDisplayed(),"Failed! Blocking Log not display correctly");
        Assert.assertFalse(agentHomePage.subMenuLiquidityThreshold.isDisplayed(),"Failed! Liquidity Threshold display");

        Assert.assertFalse(agentHomePage.menuFraudDetection.isDisplayed(),"Failed! Fraud Detection not display correctly");
        Assert.assertFalse(agentHomePage.subMenuFraudDetection.isDisplayed(),"Failed! Fraud Detection not display correctly");
        Assert.assertFalse(agentHomePage.subMenuWagerOddsHistory.isDisplayed(),"Failed! Wager Odds History not display correctly");
        Assert.assertFalse(agentHomePage.subMenuFraudPermission.isDisplayed(),"Failed! Fraud Permission not display correctly");*/

        log("INFO: Executed completely");
    }

    @TestRails(id = "3481")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3481(String brandname) throws Exception {
        log("@title: Verify Account Status in Downline Listing page is updated according after update in quick search");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);

        log("Step 1. Click on Quick Search button");
        agentHomePage.leftMenu.switchQuickSearch();

        log("Step 2. Search a account " + directDownline.getUserCode() + " account and active Settings button then update account status to Suspended");
        agentHomePage.quickSearch.updateStatus(directDownline.getUserCode(), "Suspended", true);

        log("Step 3 Active downline Listing page and search the player");
        agentHomePage.leftMenu.switchMainMenu();
        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
        downLineListingPage.downlineListing.searchDownline(directDownline.getUserCode(), "", "");

        log("Verify 1 :All accounts under suspended account is suspended");
        Assert.assertEquals(downLineListingPage.downlineListing.getAccountStatus(directDownline.getUserCode()), "Suspended", "FAILED! List downline account contain account status not in Suspended");

        log("Post Condition: Active the account");
        agentHomePage.leftMenu.switchQuickSearch();
        agentHomePage.quickSearch.updateStatus(directDownline.getUserCode(), "Active", true);

        log("INFO: Executed completely");
    }

    @TestRails(id = "3482")
    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void LeftMenu_TC3482(String brandname) throws Exception {
        log("@title: Can update user profile in quick search");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);

        log("Step 1. Click on Quick Search button");
        agentHomePage.leftMenu.switchQuickSearch();

        log("Step 2. Search a account " + directDownline.getUserCode());
        agentHomePage.quickSearch.quickSearch(directDownline.getUserCode());

        log("Step 3. Click Setting > User Profile> Update profile for account");
        String firstName = StringUtils.generateString("FirstName ", 13);
        String lastName = StringUtils.generateString("LastName", 13);
        String mobile = StringUtils.generateNumeric(7);
        agentHomePage.quickSearch.updateUserProfile("", "", firstName, lastName, mobile, "", "", true);

        log("Step 3 Active downline Listing page and search the account then click on Edit icon");
        agentHomePage.leftMenu.switchMainMenu();
        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
        EditDownLinePage editDownLinePage = downLineListingPage.downlineListing.clickEditIcon(directDownline.getUserCode());

        log("Verify 1 Verify info(first name, Last name, Mobile) is updated and display in edit downline accordingly");
        Assert.assertEquals(editDownLinePage.accInfoSection.txtFirstName.getAttribute("value"), firstName, "Failed! First name not display incorrect like when update in user profile");
        Assert.assertEquals(editDownLinePage.accInfoSection.txtLastName.getAttribute("value"), lastName, "Failed! Last name not display incorrect like when update in user profile");
        Assert.assertEquals(editDownLinePage.accInfoSection.txtMobile.getAttribute("value"), mobile, "Failed! Mobile not display incorrect like when update in user profile");
        log("INFO: Executed completely");
    }
}
