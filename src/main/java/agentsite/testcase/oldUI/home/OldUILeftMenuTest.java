package agentsite.testcase.oldUI.home;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.*;

public class OldUILeftMenuTest extends BaseCaseMerito {
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
        Assert.assertEquals(lstSubMenu.get(4),"Position Taking Listing", "FAILED! Position Taking Listingnot display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(5),"Transfer", "FAILED! Transfer not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(6),"Commission Listing", "FAILED! Commission Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(7),"Bet Setting Listing", "FAILED! Bet Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(8),"Risk Setting Listing", "FAILED! Risk Setting Listing not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(9), "Tax Settings", "FAILED! Tax Settings not display in Agency Management Menu Credit Line");
        Assert.assertEquals(lstSubMenu.get(10), "Sub User Listing", "FAILED! Sub User Listing not display in Agency Management Menu Credit Line");

        log("Verify:  2. Not display Deposit/Withdraw menu for credit account");
        Assert.assertFalse(lstSubMenu.contains(DEPOSIT_WITHDRAW), "FAILED! Credit Line should not display deposit withdraw");

        log("INFO: Executed completely");
    }
    /*

     */
/**
 * @title: Verify the list menu display correct when login Portal level
 * @steps: Login Agent site by PO level
 * 1. Expand all left menu
 * @expect: Verify the list menu display correct when login Portal level
 *//*

    @Test (groups = {"smokePO"})
    public void LeftMenu_TC019(){
        log("@title:  Verify the list menu display correct when login Portal level");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when login Portal level:");
        Assert.assertEquals(agentHomePage.menuAgencyManagement.getText(),"Agency Management","Failed! Agency Management not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCreateCompany.getText(),"Create Company","Failed! Create Company not display correctly");
        Assert.assertEquals(agentHomePage.subMenuDownLineListing.getText(),"Downline Listing","Failed! Downline Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCreditBalanceListing.getText(),"Credit/Balance Listing","Failed! Credit/Balance Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuPositionTakingListing.getText(),"Position Taking Listing","Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuDepositWithdrawal.getText(),"Deposit/Withdraw","Failed! Agency Management not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransfer.getText(),"Transfer","Failed! Transfer not display correctly");
        Assert.assertEquals(agentHomePage.subMenuFollowBets.getText(),"Follow Bets","Failed! Follow Bet not display correctly");

        agentHomePage.subMenuExpandAgencyManagement.click();
        Assert.assertEquals(agentHomePage.subMenuCommissionSettingListing.getText(),"Commission Listing","Failed!Commission Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBetSettingListing.getText(),"Bet Setting Listing","Failed!Bet Setting Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTaxSettingListing.getText(),"Tax Settings","Failed! Tax Settings not display correctly");
        Assert.assertEquals(agentHomePage.subMenuRiskSettingListing.getText(),"Risk Setting Listing","Failed! Risk Setting Listing not display correctly");
        Assert.assertFalse(agentHomePage.menuRiskManagement.isDisplayed(),"Failed!Risk Management should not displayed in Old Agent UI");

        agentHomePage.menuReport.click();
        Assert.assertEquals(agentHomePage.menuReport.getText(),"Report","Failed! Report not display correctly");
        Assert.assertEquals(agentHomePage.subMenuUnsettleBet.getText(),"Unsettled Bet","Failed! Unsettle Bet not display correctly");
        Assert.assertEquals(agentHomePage.subMenuProfitAndLoss.getText(),"Profit And Loss","Failed! Profit And Loss not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossSimple.getText(),"Win Loss Simple","Failed! Win Loss not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossDetail.getText(),"CO Win Loss Detail","Failed! CO By Detail not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCancelledBets.getText(),"Cancelled Bets","Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(agentHomePage.subMenuStatementReport.getText(),"Statement Report","Failed! Statement Report not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransferLog.getText(),"Transfer Log","Failed!Transfer Log not display correctly");
        Assert.assertEquals(agentHomePage.subMenuClientLedger.getText(),"Client Ledger","Failed! Client Ledger not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBFVoidedDiscrepancy.getText(),"BF Voided Discrepancy","Failed! BF Voided Discrepancy not display correctly");

        agentHomePage.subMenuExpandReport.click();
        Assert.assertEquals(agentHomePage.subMenuWinLossBySportAndMarketType.getText(),"Win Loss By Sport And Market Type","Failed! Win Loss By Sport And Market Type not display correctly");
        Assert.assertEquals(agentHomePage.subMenuAnalysisOfRunningMarkets.getText(),"Analysis of Running Markets","Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(agentHomePage.subMenuAgentExposureLimit.getText(),"Agent Exposure Limit","Failed! Agent Exposure Limit not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossByEvent.getText(),"Win Loss By Event","Failed! By Event not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransactionHistory.getText(),"Transaction History","Failed! Transaction History not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTopGainersTopLosers.getText(),"Top Gainers & Top Losers","Failed! Top Gainers & Top Losers not display correctly");
        Assert.assertEquals(agentHomePage.subMenuFollowAndSmallBetsPerformance.getText(),"Follow Bets Performance","Failed!Follow Bets Performance not display correctly");
        Assert.assertFalse(agentHomePage.subMenuPositionTakingReport.isDisplayed(),"Failed!Position Taking Report should not displayed on PO old UI agent");
        Assert.assertEquals(agentHomePage.subMenuViewLog.getText(),"View Log","Failed! View Log not display correctly");
        Assert.assertFalse(agentHomePage.subMenuPortalSummary.isDisplayed(),"Failed!Portal summary should not displayed. It's removed by request");

        agentHomePage.menuMarketsManagement.click();
        Assert.assertEquals(agentHomePage.menuMarketsManagement.getText(),"Bets/Markets Management","Failed! Bets/Markets Management not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBlockRacing.getText(),"Block Racing","Failed! Block Racing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBlockUnlockEvents.getText(),"Block/Unblock Events","Failed!Block/Unblock Events not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBlockUnblockCompetition.getText(),"Block/Unblock Competitions","Failed! Block/Unblock Competitions not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCurrentBlocking.getText(),"Current Blocking","Failed! Current Blocking not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBlockingLog.getText(),"Blocking Log","Failed! Blocking Log not display correctly");
        Assert.assertEquals(agentHomePage.subMenuLiquidityThreshold.getText(),"Liquidity Threshold","Failed! Liquidity Threshold not display correctly");

        agentHomePage.menuFraudDetection.click();
        Assert.assertEquals(agentHomePage.menuFraudDetection.getText(),"Fraud Detection","Failed! Fraud Detection not display correctly");
        Assert.assertEquals(agentHomePage.subMenuFraudDetection.getText(),"Fraud Detection","Failed! Fraud Detection not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWagerOddsHistory.getText(),"Wager Odds History","Failed! Wager Odds History not display correctly");
        Assert.assertEquals(agentHomePage.subMenuFraudPermission.getText(),"Fraud Permission","Failed! Fraud Permission not display correctly");

        log("INFO: Executed completely");
    }
*/

    /**
     * @title: Validate can expand/colapse Report and all item display correctly
     * @steps: 1 Click on Report
     * 2. Click the + to expand the rest menus
     * 3. Collapse the menu then click on Report
     * @expect:
     * 1. Verify Report is expanded/collapse a halt and full
     * 2. Verify all menu items in Report is correctly
     */
    @Test (groups = {"poregression"})
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
        Assert.assertEquals(lstSubReprotMenu.get(0),"Unsettled Bet", "FAILED!Create Downline Agent not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(1),"Profit And Loss", "FAILED! Profit And Loss not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(2),"Win Loss Simple", "FAILED! Win Loss Simple not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(3),"CO Win Loss Detail", "FAILED! AD Win Loss Detail not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(4),"Cancelled Bets", "FAILED! Cancelled Bets not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(5),"Statement Report", "FAILED! Statement Report not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(6),"Transfer Log", "FAILED! Transfer Log not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(7),"Client Ledger", "FAILED! Client Ledger not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(8),"BF Voided Discrepancy", "FAILED! BF Voided Discrepancy not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(9),"Agent Exposure Limit", "FAILED! Agent Exposure Limit not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(10),"Win Loss By Sport And Market Type", "FAILED! Win Loss By Sport And Market Type not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(11), "Win Loss By Event", "FAILED! Win Loss By Event not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(12), "Analysis of Running Markets", "FAILED! Analysis of Running Markets not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(13), "Transaction History", "FAILED! Transaction History not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(14), "Top Gainers & Top Losers", "FAILED! Top Gainers & Top Losers not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(15), "Follow Bets Performance", "FAILED! Follow Bets Performance not display in Report Menu Portal Level");
        Assert.assertEquals(lstSubReprotMenu.get(16), "View Log", "FAILED! View Log not display in Report Menu Portal Level");

        log("INFO: Executed completely");
    }

    /*  *//**
     * @title: Verify the list menu display correct when Non PO level Credit Cash line
     * @steps: Login Agent site by Non PO level
     * 1. Expand all left menu
     * @expect: Verify the list menu display correct when login Non PO level
     *//*
    @Test (groups = {"smoke_Credit_Cash"})
    public void LeftMenu_TC020(){
        log("@title: Verify the list menu display correct when login Non PO level Credit Cash");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when login Non PO level");
        Assert.assertEquals(agentHomePage.menuAgencyManagement.getText(),"Agency Management","Failed! Agency Management not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCreateCompany.getText(),"Create Downline Agent","Failed! Create Downline Agent not display correctly");
        Assert.assertEquals(agentHomePage.subMenuDownLineListing.getText(),"Downline Listing","Failed! Downline Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuPositionTakingListing.getText(),"Position Taking Listing","Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuDepositWithdrawal.getText(),"Deposit/Withdraw","Failed! Agency Management not display correctly");

        agentHomePage.subMenuExpandAgencyManagement.click();
        Assert.assertEquals(agentHomePage.subMenuCommissionSettingListing.getText(),"Commission Listing","Failed!Commission Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBetSettingListing.getText(),"Bet Setting Listing","Failed!Bet Setting Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTaxSettingListing.getText(),"Tax Settings","Failed! Tax Settings should not display at non PO level");
        Assert.assertEquals(agentHomePage.subMenuSubUserListing.getText(),"Sub User Listing","Failed! Sub User Listing should not display at non PO level");
        Assert.assertFalse(agentHomePage.subMenuRiskSettingListing.isDisplayed(),"Failed! Risk Setting Listing not display correctly");
        Assert.assertFalse(agentHomePage.subMenuCreditBalanceListing.isDisplayed(),"Failed!Deposit Withdraw should not displayed when login Non PO Credit cashline");
        Assert.assertFalse(agentHomePage.subMenuTransfer.isDisplayed(),"Failed! Transfer should not displayed when login Non PO Credit cash line");

        agentHomePage.menuReport.click();
        Assert.assertEquals(agentHomePage.menuReport.getText(),"Report","Failed! Report not display correctly");
        Assert.assertEquals(agentHomePage.subMenuUnsettleBet.getText(),"Unsettled Bet","Failed! Unsettle Bet not display correctly");
        Assert.assertEquals(agentHomePage.subMenuProfitAndLoss.getText(),"Profit And Loss","Failed! Profit And Loss not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossSimple.getText(),"Win Loss Simple","Failed! Win Loss not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossDetail.getText(),"MA Win Loss Detail","Failed! MA By Detail not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCancelledBets.getText(),"Cancelled Bets","Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(agentHomePage.subMenuStatementReport.getText(),"Statement Report","Failed! Statement Report not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransferLog.getText(),"Transfer Log","Failed!Transfer Log not display correctly");
        Assert.assertEquals(agentHomePage.subMenuClientLedger.getText(),"Client Ledger","Failed! Client Ledger not display correctly");
        Assert.assertEquals(agentHomePage.subMenuPositionTakingReport.getText(),"Position Taking Report","Failed!Position Taking Report should display");
        Assert.assertFalse(agentHomePage.subMenuBFVoidedDiscrepancy.isDisplayed(),"Failed! BF Voided Discrepancy should not display non PO level");

        agentHomePage.subMenuExpandReport.click();
        Assert.assertEquals(agentHomePage.subMenuWinLossBySportAndMarketType.getText(),"Win Loss By Sport And Market Type","Failed! Win Loss By Sport And Market Type not display correctly");
        Assert.assertEquals(agentHomePage.subMenuAnalysisOfRunningMarkets.getText(),"Analysis of Running Markets","Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(agentHomePage.subMenuAgentExposureLimit.getText(),"Agent Exposure Limit","Failed! Agent Exposure Limit not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossByEvent.getText(),"Win Loss By Event","Failed! Win Loss By Event not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransactionHistory.getText(),"Transaction History","Failed! Transaction History not display correctly");
        Assert.assertFalse(agentHomePage.subMenuTopGainersTopLosers.isDisplayed(),"Failed! Top Gainers & Top Losers should not display");
        Assert.assertFalse(agentHomePage.subMenuFollowAndSmallBetsPerformance.isDisplayed(),"Failed!Follow Bets Performance should not display");
        Assert.assertEquals(agentHomePage.subMenuViewLog.getText(),"View Log","Failed! View Log not display correctly");
        Assert.assertFalse(agentHomePage.subMenuPortalSummary.isDisplayed(),"Failed!Portal summary should not displayed. It's removed by request");

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
        Assert.assertFalse(agentHomePage.subMenuFraudPermission.isDisplayed(),"Failed! Fraud Permission not display correctly");

        log("INFO: Executed completely");
    }
    *//**
     * @title: Verify the list menu display correct when login Non PO level Credit line
     * @steps: Login Agent site by Non PO level
     * 1. Expand all left menu
     * @expect: Verify the list menu display correct when login Non PO level
     *//*
    @Test (groups = {"smoke_Credit"})
    public void LeftMenu_TC021(){
        log("@title: Verify the list menu display correct when login Non PO level at Credit line");
        log("Step 1: Expand all left menu");
        log("Verify:1 Verify the list menu display correct when login Non PO level");
        Assert.assertEquals(agentHomePage.menuAgencyManagement.getText(),"Agency Management","Failed! Agency Management not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCreateDownLineAgent.getText(),"Create Downline Agent","Failed! Create Downline Agent not display correctly");
        Assert.assertEquals(agentHomePage.subMenuDownLineListing.getText(),"Downline Listing","Failed! Downline Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuPositionTakingListing.getText(),"Position Taking Listing","Failed! Position Taking Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCreditBalanceListing.getText(),"Credit/Balance Listing","Failed! Credit/Balance Listing  not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransfer.getText(),"Transfer","Failed! Transfer not display correctly");

        agentHomePage.subMenuExpandAgencyManagement.click();
        Assert.assertEquals(agentHomePage.subMenuCommissionSettingListing.getText(),"Commission Listing","Failed!Commission Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuBetSettingListing.getText(),"Bet Setting Listing","Failed!Bet Setting Listing not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTaxSettingListing.getText(),"Tax Settings","Failed! Tax Settings should not display at non PO level");
        Assert.assertEquals(agentHomePage.subMenuSubUserListing.getText(),"Sub User Listing","Failed! Sub User Listing should not display at non PO level");
        Assert.assertFalse(agentHomePage.subMenuRiskSettingListing.isDisplayed(),"Failed! Risk Setting Listing not display correctly");
        Assert.assertFalse(agentHomePage.subMenuDepositWithdrawal.isDisplayed(),"Failed!Deposit Withdraw should not displayed when login Non PO Credit cashline");

        agentHomePage.menuReport.click();
        Assert.assertEquals(agentHomePage.menuReport.getText(),"Report","Failed! Report not display correctly");
        Assert.assertEquals(agentHomePage.subMenuUnsettleBet.getText(),"Unsettled Bet","Failed! Unsettle Bet not display correctly");
        Assert.assertEquals(agentHomePage.subMenuProfitAndLoss.getText(),"Profit And Loss","Failed! Profit And Loss not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossSimple.getText(),"Win Loss Simple","Failed! Win Loss not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossDetail.getText(),"MA Win Loss Detail","Failed! MA By Detail not display correctly");
        Assert.assertEquals(agentHomePage.subMenuCancelledBets.getText(),"Cancelled Bets","Failed! Cancelled Bets not display correctly");
        Assert.assertEquals(agentHomePage.subMenuStatementReport.getText(),"Statement Report","Failed! Statement Report not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransferLog.getText(),"Transfer Log","Failed!Transfer Log not display correctly");
        Assert.assertEquals(agentHomePage.subMenuClientLedger.getText(),"Client Ledger","Failed! Client Ledger not display correctly");
        Assert.assertEquals(agentHomePage.subMenuPositionTakingReport.getText(),"Position Taking Report","Failed!Position Taking Report should display");
        Assert.assertFalse(agentHomePage.subMenuBFVoidedDiscrepancy.isDisplayed(),"Failed! BF Voided Discrepancy should not display non PO level");

        agentHomePage.subMenuExpandReport.click();
        Assert.assertEquals(agentHomePage.subMenuWinLossBySportAndMarketType.getText(),"Win Loss By Sport And Market Type","Failed! Win Loss By Sport And Market Type not display correctly");
        Assert.assertEquals(agentHomePage.subMenuAnalysisOfRunningMarkets.getText(),"Analysis of Running Markets","Failed! Analysis of Running Markets not display correctly");
        Assert.assertEquals(agentHomePage.subMenuAgentExposureLimit.getText(),"Agent Exposure Limit","Failed! Agent Exposure Limit not display correctly");
        Assert.assertEquals(agentHomePage.subMenuWinLossByEvent.getText(),"Win Loss By Event","Failed! Win Loss By Event not display correctly");
        Assert.assertEquals(agentHomePage.subMenuTransactionHistory.getText(),"Transaction History","Failed! Transaction History not display correctly");
        Assert.assertFalse(agentHomePage.subMenuTopGainersTopLosers.isDisplayed(),"Failed! Top Gainers & Top Losers should not display");
        Assert.assertFalse(agentHomePage.subMenuFollowAndSmallBetsPerformance.isDisplayed(),"Failed!Follow Bets Performance should not display");
        Assert.assertEquals(agentHomePage.subMenuViewLog.getText(),"View Log","Failed! View Log not display correctly");
        Assert.assertFalse(agentHomePage.subMenuPortalSummary.isDisplayed(),"Failed!Portal summary should not displayed. It's removed by request");

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
        Assert.assertFalse(agentHomePage.subMenuFraudPermission.isDisplayed(),"Failed! Fraud Permission not display correctly");

        log("INFO: Executed completely");
    }

*/
}
