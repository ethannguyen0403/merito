package agentsite.testcase.satsport.home;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.*;

public class SATLeftMenuTest extends BaseCaseMerito {
    /**
     * @title: Validate all items menu in Agency Management for Credit account
     * @steps:  Login agent by Control Blocking Level (SAT is SAD, and Partner for the white labels)\
     * 1 Click on Agency Management
     * 2. Click the + to expand the rest menus
     * @expect:
     * 1. Display Credit/Balance Listing, Transfer, and Risk Setting Listing menu
     * 2. Not display Deposit/Withdraw menu for credit account
     */
    @Test (groups = {"credit_regression"})
    public void LeftMenu_TC005(){
        log("@title: Validate can expand/collapse Agency Management");
        log("Step:1 Click on Agency Management collapse");
        List<String> lstSubMenu = agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        log("Verify:  1. Display Credit/Balance Listing, Transfer, and Risk Setting Listing menu");
        Assert.assertEquals(lstSubMenu.get(0),"Create Downline Agent", "FAILED!Create Downline Agent not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(1),"Create User", "FAILED! Create User not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(2),"Downline Listing", "FAILED! Downline Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(3),"Credit/Balance Listing", "FAILED! Credit/Balance Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(4),"Event Bet Size Settings", "FAILED! Event Bet Size Settings not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(5),"Position Taking Listing", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(6),"Transfer", "FAILED! Transfer not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(7),"Commission Listing", "FAILED! Commission Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(8),"Bet Setting Listing", "FAILED! Bet Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(9),"Risk Setting Listing", "FAILED! Risk Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(10),"Tax Settings", "FAILED! Tax Settings not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(11),"Sub User Listing", "FAILED! Sub User Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(12),"Announcement", "FAILED! Announcement not display in Agency Management Menu Credit Line");

        log("Verify:  2. Not display Deposit/Withdraw menu for credit account");
        Assert.assertFalse(lstSubMenu.contains(DEPOSIT_WITHDRAW),"FAILED! Credit Line should not display deposit withdraw");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate all items menu in Agency Management for Credit Cash account
     * @steps:  Login agent by Control Blocking Level (SAT is SAD, and Partner for the white labels)\
     * 1 Click on Agency Management
     * 2. Click the + to expand the rest menus
     * @expect:
     * 1. Display Credit/Balance Listing, Transfer, and Risk Setting Listing menu
     * 2. Not display Deposit/Withdraw menu for credit account
     */
    @Test (groups = {"regression"})
    public void LeftMenu_TC005_CreditCash(){
        log("@title: Validate all items menu in Agency Management for Credit Cash account");
        log("Step:1 Click on Agency Management collapse");
        List<String> lstSubMenu = agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        log("Verify:  1. Display Credit/Balance Listing, Transfer, and Risk Setting Listing menu");
        Assert.assertEquals(lstSubMenu.get(0),"Create Downline Agent", "FAILED!Create Downline Agent not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(1),"Create User", "FAILED! Create User not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(2),"Downline Listing", "FAILED! Downline Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(3),"Event Bet Size Settings", "FAILED! Event Bet Size Settings not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(4),"Position Taking Listing", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(5),DEPOSIT_WITHDRAW ,"FAILED! Transfer not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(6),"Commission Listing", "FAILED! Commission Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(7),"Bet Setting Listing", "FAILED! Bet Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(8),"Risk Setting Listing", "FAILED! Risk Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(9),"Tax Settings", "FAILED! Tax Settings not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(10),"Sub User Listing", "FAILED! Sub User Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(11),"Announcement", "FAILED! Announcement not display in Agency Management Menu Credit Line");

        log("Verify:  2. Not display Deposit/Withdraw menu for credit account");
        Assert.assertFalse(lstSubMenu.contains(DEPOSIT_WITHDRAW),"FAILED! Credit Line should not display deposit withdraw");

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate can expand/colapse Report and all item display correctly
     * @steps: 1 Click on Report
     * 2. Click the + to expand the rest menus
     * 3. Collapse the menu then click on Report
     * @expect:
     * 1. Verify Report is expanded/collapse a halt and full
     * 2. Verify all menu items in Report is correctly
     */
    @Test (groups = {"regression"})
    public void LeftMenu_TC007(){
        log("@title: Validate can expand/collapse Report and all item display correctly");
        log("Step:1 Expand the report");
        agentHomePage.leftMenuList.expandMenu(REPORT);

        log("Verify 1: Can expand Report");
        Assert.assertTrue(agentHomePage.isMenuExpanded(REPORT),"FAILED! The menu not expanded");

        log("Step 2 Click the + to expand the rest menus");
        List<String> lstSubReprotMenu = agentHomePage.leftMenuList.getListSubMenu(REPORT);

        log("Step 3. Collapse the menu then click on Report");
        agentHomePage.leftMenuList.collapsedMenu(REPORT);

        log("Verify 1: Can Collapse Report");
        Assert.assertFalse(agentHomePage.isMenuExpanded(REPORT),"FAILED! The menu is not collapsed");

        log("Verify 3: the list menu in report is correctly displayed");
        Assert.assertEquals(lstSubReprotMenu.get(0),"Unsettled Bet", "FAILED!Create Downline Agent not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(1),"Profit And Loss", "FAILED! Profit And Loss not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(2),"Win Loss Simple", "FAILED! Win Loss Simple not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(3),"AD Win Loss Detail", "FAILED! AD Win Loss Detail not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(4),"Position Taking Report", "FAILED! Position Taking Report not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(5),"Cancelled Bets", "FAILED! Cancelled Bets not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(6),"Statement Report", "FAILED! Statement Report not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(7),"Transfer Log", "FAILED! Transfer Log not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(8),"Client Ledger", "FAILED! Client Ledger not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(9),"Win Loss By Sport And Market Type", "FAILED! Win Loss By Sport And Market Type not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(10),"Win Loss By Event", "FAILED! Win Loss By Event not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(11),"Analysis of Running Markets", "FAILED! Analysis of Running Markets not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(12),"Transaction History", "FAILED! Transaction History not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(13),"Top Gainers & Top Losers", "FAILED! Top Gainers & Top Losers not display in Report Menu Credit Line");
        Assert.assertEquals(lstSubReprotMenu.get(14),"View Log", "FAILED! View Log not display in Report Menu Credit Line");

        log("INFO: Executed completely");
    }
    /**
     * @title: Verify the list menu display correct when login Portal level
     * @steps:   Login Agent site by PO level
     * 1. Expand all left menu
     * @expect: Verify the list menu display correct when login Portal level
     */
    @Test (groups = {"smokePO"})
    public void LeftMenu_TC019(){
        log("@title:  Verify the list menu display correct when login Portal level");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when login Portal level:");

        agentHomePage.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), "Create Company", "Failed! Create Company not display correctly");
        Assert.assertEquals(lstSubMennu.get(1), "Downline Listing", "Failed! Downline Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(2), "Position Taking Listing", "Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(3), "Event Bet Size Settings", "Failed! Event Bet Size Settings not display correctly");
        Assert.assertEquals(lstSubMennu.get(4), "Deposit/Withdraw", "Failed! Agency Management not display correctly");
        Assert.assertEquals(lstSubMennu.get(5), "Transfer", "Failed! Transfer not display correctly");
        Assert.assertEquals(lstSubMennu.get(6), "Credit/Balance Listing", "Failed!Credit/Balance Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(7), "Follow Bets", "Failed! Follow Bets not display correctly");
        Assert.assertEquals(lstSubMennu.get(8), "Commission Listing", "Failed!Commission Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(9), "Bet Setting Listing", "Failed!Bet Setting Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(10), "Tax Settings", "Failed! Tax Settings not display correctly");
        Assert.assertEquals(lstSubMennu.get(11), "Risk Setting Listing", "Failed! Risk Setting Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(12), "Announcement", "Failed!Announcement not display correctly");

        agentHomePage.leftMenuList.expandMenu(REPORT);
        List<String> lstReport = agentHomePage.leftMenuList.getListSubMenu(REPORT);
        Assert.assertEquals(lstReport.get(0), "Unsettled Bet", "Failed! Unsettle Bet not display correctly");
        Assert.assertEquals(lstReport.get(1), "Profit And Loss", "Failed! Profit And Loss not display correctly");
        Assert.assertEquals(lstReport.get(2), "Win Loss Simple", "Failed! Win Loss Simple not display correctly");
        Assert.assertEquals(lstReport.get(3), "CO Win Loss Detail", "Failed! CO Win Loss Detail not display correctly");
        Assert.assertEquals(lstReport.get(4), "Cancelled Bets", "Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(lstReport.get(5), "Statement Report", "Failed! Statement Report not display correctly");
        Assert.assertEquals(lstReport.get(6), "Transfer Log", "Failed!Transfer Log not display correctly");
        Assert.assertEquals(lstReport.get(7), "Client Ledger", "Failed! Client Ledger not display correctly");
        Assert.assertEquals(lstReport.get(8), "BF Voided Discrepancy", "Failed! BF Voided Discrepancy not display correctly");
        Assert.assertEquals(lstReport.get(9), "Agent Exposure Limit", "Failed!Agent Exposure Limit not display correctly");
        Assert.assertEquals(lstReport.get(10), "Win Loss By Sport And Market Type", "Failed!Win Loss By Sport And Market  not display correctly");
        Assert.assertEquals(lstReport.get(11), "Win Loss By Event", "Failed! Win Loss By Event not display correctly");
        Assert.assertEquals(lstReport.get(12), "Analysis of Running Markets", "Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(lstReport.get(13), "Transaction History", "Failed! View Log not display correctly");
        Assert.assertEquals(lstReport.get(14), "Top Gainers & Top Losers", "Failed! Top Gainers & Top Losers not display correctly");
        Assert.assertEquals(lstReport.get(15), "Follow Bets Performance", "Failed!Follow Bets Performance not display correctly");
        Assert.assertEquals(lstReport.get(16), "View Log", "Failed! View Log not display correctly");
        Assert.assertFalse(lstReport.contains("Portal summary"), "Failed!Portal summary should not display");
        Assert.assertFalse(lstReport.contains("Position Taking Report"), "Failed! Position Taking Report not display correctly");

        agentHomePage.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMarketManagement = agentHomePage.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);
        Assert.assertEquals(lstMarketManagement.get(0), "Block Racing", "Failed! Block Racing not display correctly");
        Assert.assertEquals(lstMarketManagement.get(1), "Block/Unblock Events", "Failed!Block/Unblock Events not display correctly");
        Assert.assertEquals(lstMarketManagement.get(2), "Block/Unblock Competitions", "Failed! Block/Unblock Competitions not display correctly");
        Assert.assertEquals(lstMarketManagement.get(3), "Current Blocking", "Failed! Current Blocking not display correctly");
        Assert.assertEquals(lstMarketManagement.get(4), "Blocking Log", "Failed! Blocking Log not display correctly");
        Assert.assertEquals(lstMarketManagement.get(5), "Liquidity Threshold", "Failed! Liquidity Threshold not display correctly");

        agentHomePage.leftMenuList.expandMenu(FRAUD_DETECTION);
        List<String> lstFraud = agentHomePage.leftMenuList.getListSubMenu(FRAUD_DETECTION);
        Assert.assertEquals(lstFraud.get(0), "Fraud Detection", "Failed! Fraud Detection not display correctly");
        Assert.assertEquals(lstFraud.get(1), "Wager Odds History", "Failed! Wager Odds History not display correctly");
        Assert.assertEquals(lstFraud.get(2), "Fraud Permission", "Failed! Fraud Permission not display correctly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify the list menu display correct when login Control Blocking  level
     * @steps:   Login Agent site by Control Blocking level
     * 1. Expand all left menu
     * @expect: Verify the list menu display correct when login Control Blocking level
     */
    @Test (groups = {"satsmoke"})
    public void LeftMenu_TC020() {
        log("@title: Verify the list menu display correct when login Control Blocking  level");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when login Control Blocking  level");

        agentHomePage.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), CREATE_DOWNLINE_AGENT, "Failed! Create Downline Agent not display correctly");
        Assert.assertEquals(lstSubMennu.get(1), CREATE_USER, "Failed! Create User not display correctly");
        Assert.assertEquals(lstSubMennu.get(2), DOWNLINE_LISTING, "Failed! Downline Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(3), EVENT_BET_STIE_SETTINGS, "Failed! Event Bet Site Settings not display correctly");
        Assert.assertEquals(lstSubMennu.get(4), POSITION_TAKING_LISTING, "Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(5), DEPOSIT_WITHDRAW, "Failed! Deposit Withdraw not display correctly");
        Assert.assertEquals(lstSubMennu.get(6), COMMISSION_LISTING, "Failed! Commission Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(7), BET_SETTING_LISTING, "Failed! Bet Setting not display correctly");
        Assert.assertEquals(lstSubMennu.get(8), TAX_SETTING_LISTING, "Failed! Tax Setting not display correctly");
        Assert.assertEquals(lstSubMennu.get(9), SUB_USER_LISTING, "Failed!Sub User Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(10), ANNOUNCEMENT, "Failed!Announcement not display correctly");

        agentHomePage.leftMenuList.expandMenu(REPORT);
        List<String> lstReport = agentHomePage.leftMenuList.getListSubMenu(REPORT);
        Assert.assertEquals(lstReport.get(0), UNSETTLED_BET, "Failed! Unsettle Bet not display correctly");
        Assert.assertEquals(lstReport.get(1), PROFIT_AND_LOSS, "Failed! Profit And Loss not display correctly");
        Assert.assertEquals(lstReport.get(2), WIN_LOSS_SIMPLE, "Failed! Win Loss Simple not display correctly");
        Assert.assertEquals(lstReport.get(3), "AD Win Loss Detail", "Failed! Ad Win Loss Detail not display correctly");
        Assert.assertEquals(lstReport.get(4), POSITION_TAKING_REPORT, "Failed! Position Taking Report not display correctly");
        Assert.assertEquals(lstReport.get(5), CANCELLED_BETS, "Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(lstReport.get(6), STATEMENT_REPORT, "Failed! Statement Report not display correctly");
        Assert.assertEquals(lstReport.get(7), TRANSFER_LOG, "Failed!Transfer Log not display correctly");
        Assert.assertEquals(lstReport.get(8), CLIENT_LEDGER, "Failed! Client Ledger not display correctly");
        Assert.assertEquals(lstReport.get(9), "Win Loss By Sport And Market Type", "Failed! Win Loss By Sport And Market Type not display correctly");
        Assert.assertEquals(lstReport.get(10), WIN_LOSS_BY_EVENT, "Failed! Win Loss By Event not display correctly");
        Assert.assertEquals(lstReport.get(11), ANALYSIS_OF_RUNNING_MARKETS, "Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(lstReport.get(12), TRANSACTION_HISTORY, "Failed! Transaction History not display correctly");
        Assert.assertEquals(lstReport.get(13), TOP_GAINER_TOP_LOSER, "Failed! Top Gainers & Top Losers not display correctly");
        Assert.assertEquals(lstReport.get(14), VIEW_LOG, "Failed! View Log not display correctly");

        agentHomePage.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMarketManagement = agentHomePage.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);
        Assert.assertEquals(lstMarketManagement.get(0), "Block Racing", "Failed! Block Racing not display correctly");
        Assert.assertEquals(lstMarketManagement.get(1), "Block/Unblock Events", "Failed!Block/Unblock Events not display correctly");
        Assert.assertEquals(lstMarketManagement.get(2), "Block/Unblock Competitions", "Failed! Block/Unblock Competitions not display correctly");
        Assert.assertEquals(lstMarketManagement.get(3), "Current Blocking", "Failed! Current Blocking not display correctly");
        Assert.assertEquals(lstMarketManagement.get(4), "Blocking Log", "Failed! Blocking Log not display correctly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify the list menu display correct when other level
     * @steps:   Login Agent site by the level under Control Blocking
     * 1. Expand all left menu
     * @expect: Verify the list menu display correct when other leve
     */
    @Test (groups = {"nonPOSmoke"})
    public void LeftMenu_TC021() {
        log("@title: Verify the list menu display correct when other level");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when other level");
        agentHomePage.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstSubMennu = agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        Assert.assertEquals(lstSubMennu.get(0), "Create Downline Agent", "Failed! Create Downline Agent not display correctly");
        Assert.assertEquals(lstSubMennu.get(1), "Create User", "Failed! Create User not display correctly");
        Assert.assertEquals(lstSubMennu.get(2), "Downline Listing", "Failed! Downline Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(3), "Deposit/Withdraw", "Failed! Agency Management not display correctly");
        Assert.assertEquals(lstSubMennu.get(4), "Sub User Listing", "Failed!Sub User Listing not display correctly");
        Assert.assertEquals(lstSubMennu.get(5), "Transfer", "Failed! Transfer not display correctly");

        agentHomePage.leftMenuList.expandMenu(REPORT);
        List<String> lstReport = agentHomePage.leftMenuList.getListSubMenu(REPORT);
        Assert.assertEquals(lstReport.get(0), "Unsettled Bet", "Failed! Unsettle Bet not display correctly");
        Assert.assertEquals(lstReport.get(1), "Profit And Loss", "Failed! Profit And Loss not display correctly");
        Assert.assertEquals(lstReport.get(2), "Cancelled Bets", "Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(lstReport.get(3), "Statement Report", "Failed! Statement Report not display correctly");
        Assert.assertEquals(lstReport.get(4), "Transfer Log", "Failed!Transfer Log not display correctly");
        Assert.assertEquals(lstReport.get(5), "Statement Report", "Failed! Statement Report not display correctly");
        Assert.assertEquals(lstReport.get(6), "Transfer Log", "Failed!Transfer Log not display correctly");
        Assert.assertEquals(lstReport.get(7), "Client Ledger", "Failed! Client Ledger not display correctly");
        Assert.assertEquals(lstReport.get(8), "Win Loss By Event", "Failed! Win Loss By Event not display correctly");
        Assert.assertEquals(lstReport.get(9), "Analysis of Running Markets", "Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(lstReport.get(10), "Transaction History", "Failed! View Log not display correctly");
        Assert.assertEquals(lstReport.get(11), "Top Gainers & Top Losers", "Failed! Top Gainers & Top Losers not display correctly");
        Assert.assertEquals(lstReport.get(12), "Analysis of Running Markets", "Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(lstReport.get(13), "View Log", "Failed! View Log not display correctly");

        log("INFO: Executed completely");
    }

}
