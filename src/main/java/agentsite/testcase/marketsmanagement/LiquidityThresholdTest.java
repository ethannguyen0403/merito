package agentsite.testcase.marketsmanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.components.ConfirmPopup;
import agentsite.pages.marketsmanagement.LiquidityThresholdPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.HomePage.LIQUIDITY_THRESHOLD;
import static common.AGConstant.HomePage.MARKET_MANAGEMENT;

public class LiquidityThresholdTest extends BaseCaseTest {
    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully from PO level
     * @steps: 1. Navigate Markets Management >Liquidity Threshold
     * @expect: 1. Verify there is no console error display
     */
    @TestRails(id = "780")
    @Test(groups = {"http_request"})
    public void Agent_MM_Liquidity_Threshold_TC780() {
        log("@title: Validate there is no http responded error returned");

        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        agentHomePage.navigateLiquidityThresholdPage();

        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    /**
     * @title: Can Active/Inactive Liquidity Threshold Status
     * @pre-condition: 1. Log in successfully from PO level
     * @steps: 1. Navigate Markets Management >Liquidity Threshold
     * 2. Search a CO account and active threshold
     * 3. Click deactive liquidity threshold
     * @expect: 1. Can active Liquidity Threshold, the column Last Update Time, Last Update By, History is empty
     * 2. Can inactive Liquidity Threshold and show log in Last Update Time, Last Update By and View link
     */
    @TestRails(id = "781")
    @Test(groups = {"smoke_po"})
    @Parameters({"username", "controlBlockingAccount"})
    public void Agent_MM_Liquidity_Threshold_TC781(String controlBlockingAccount) {
        log("@title: Can Active/Inactive Liquidity Threshold Status");

        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        LiquidityThresholdPage page = agentHomePage.navigateLiquidityThresholdPage();

        log("Step 2. Search the account account and active threshold");
        page.search(controlBlockingAccount);

        log("Step 3. Click active liquidity threshold");
        page.setLiquidityThreshold(controlBlockingAccount, true);

        log("Verify 1. Liquidity is active");
        Assert.assertTrue(page.isLiquidityStatusAsExpected(controlBlockingAccount, true));

        log("Step 3. Deactive liquidity threshold");
        page.setLiquidityThreshold(controlBlockingAccount, false);

        log("Verify 2. Can inactive Liquidity Threshold and show log in Last Update Time, Last Update By and View link");
        Assert.assertTrue(page.isLiquidityStatusAsExpected(controlBlockingAccount, false));

        log("INFO: Executed completely");
    }

    /**
     * @title: Can not active for downline when upline is inactive
     * @pre-condition: 1. Log in successfully from PO level
     * @steps: 1. Navigate Markets Management >Liquidity Threshold
     * 2. Search an account that upline is inactive liquidity Threshold
     * 3. Click Active liquidity threshold
     * @expect: 1.  Can not active for downline when upline is inactive
     */
    // Missing test case id in TestRail
    @Test(groups = {"smoke_po"})
    @Parameters({"controlBlockingAccount", "downlineAccount"})
    public void Agent_MM_Liquidity_Threshold_TC008(String controlBlockingAccount, String downlineAccount) {
        log("@title: Can not active for downline when upline is inactive");
        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        LiquidityThresholdPage page = agentHomePage.navigateLiquidityThresholdPage();

        log(String.format("Step 2. Search the account %S", controlBlockingAccount));
        page.search(controlBlockingAccount);

        log(String.format("Step 3. Click inactive liquidity threshold of %s", controlBlockingAccount));
        page.setLiquidityThreshold(controlBlockingAccount, false);

        log(String.format("Step 4 Search the downline %s of the account: %s", downlineAccount, controlBlockingAccount));
        page.search(downlineAccount);

        log(String.format("Step 5 Active liquidity threshold of %s", downlineAccount));
        page.setLiquidityThreshold(downlineAccount, true);

        log("Verify 1. Can not active for downline when upline is inactive");
        ConfirmPopup popup = new ConfirmPopup();
        Assert.assertEquals(popup.getContentMessage(), String.format("Upline %s of %s is OFF. Please help to ON it first.", controlBlockingAccount, downlineAccount),
                "FAILED! Error message not display when acitve downline has update inacive liquidity");

        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke_po"})
    @TestRails(id = "3712")
    public void Agent_MM_Liquidity_Threshold_TC3712() {
        log("@title: Validate Liquidity Threshold UI display correctly");
        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        LiquidityThresholdPage page = agentHomePage.navigateLiquidityThresholdPage();

        log("Step 2. Validate Liquidity Threshold UI display correctly");
        Assert.assertEquals(page.getLiquidityThresholdTitle(),LIQUIDITY_THRESHOLD);
        page.verifyUIDisplayCorrect();
        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke_po"})
    @TestRails(id = "3713")
    public void Agent_MM_Liquidity_Threshold_TC3713() {
        log("@title: Validate can search username/ Login ID");
        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        LiquidityThresholdPage page = agentHomePage.navigateLiquidityThresholdPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "CO", "ACTIVE", _brandname);
        String userCodeAndLoginID = listAccount.get(0).getUserCodeAndLoginID("%s-%s");
        if (userCodeAndLoginID.contains("-")) {
            String loginId = userCodeAndLoginID.split("-")[1];
            page.search(loginId);
            List<String> lstLoginId = page.tblLiquidity.getColumn(page.colLoginID, false);
            log("Validate the loginID display in the list");
            Assert.assertTrue(lstLoginId.size() == 1, "FAILED! Total result is greater than 1 record");
            Assert.assertEquals(lstLoginId.get(0), loginId, String.format("FAILED! Inputted loginId and result does not match, expected: %s but actual: %s", loginId, lstLoginId.get(0)));
        }
        String userCode = userCodeAndLoginID.split("-")[0];
        page.search(userCode);
        List<String> lstUsername = page.tblLiquidity.getColumn(page.colUsername, false);
        log("Validate the username display in the list");
        Assert.assertTrue(lstUsername.size() == 1, "FAILED! Total result is greater than 1 record");
        Assert.assertEquals(lstUsername.get(0), userCode, String.format("FAILED! Inputted username and result does not match, expected: %s but actual: %s", userCode, lstUsername.get(0)));
        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke"})
    @TestRails(id = "3714")
    public void Agent_MM_Liquidity_Threshold_TC3714() {
        log("@title: Validate the page not display when login agent with Non PO level");
        log("Step 1. Navigate Markets Management");
        agentHomePage.leftMenu.leftMenuList.expandMenu(MARKET_MANAGEMENT);
        List<String> lstMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(MARKET_MANAGEMENT);

        log("Validate Liquidity Threshold page not display from Non-PO account");
        Assert.assertTrue(!lstMenu.contains(LIQUIDITY_THRESHOLD), "FAILED! Liquidity Threshold sub menu show in list");
        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke_po1"})
    @TestRails(id = "3715")
    @Parameters({"controlBlockingAccount"})
    public void Agent_MM_Liquidity_Threshold_TC3715(String controlBlockingAccount) {
        log("@title: Validate all downline is affect threshold status from upline");
        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        LiquidityThresholdPage page = agentHomePage.navigateLiquidityThresholdPage();
        ArrayList<String> lstUpline = page.getUplineAgencies(controlBlockingAccount);
        String userCodeCO = lstUpline.get(1);
        String directUplineOfBlockingAccount = lstUpline.get(lstUpline.size()-1);
        log("Step 2. Search a CO account and active threshould");
        page.search(userCodeCO);
        page.setLiquidityThreshold(userCodeCO, true);

        log("Step 3. Inactive Threshold status from SAD level");
        page.search(controlBlockingAccount);
        page.setLiquidityThreshold(controlBlockingAccount, false);
        page.clickOnUserLink(controlBlockingAccount);

        log("Validate Threshold is active from CO-upline of SAD and inactive from SAD to indirect donwline");
        page.verifyAllDownlineStatusCorrect(false);
        page.search(directUplineOfBlockingAccount);
        page.verifyAllDownlineStatusCorrect(true);
        log("INFO: Executed completely");
    }

//    @Test(groups = {"interaction"})
//    @Parameters({"controlBlockingAccount","downlineAccount","memberAccount","password","boAccount","bopassword"})
//    public void Agent_MM_Liquidity_Threshold_TC007(String controlBlockingAccount,String memberAccount,String password,String boAccount, String bopassword) throws Exception {
//        log("@title: Validate odds in member affect when active/inactive liquidity threshold");
//        log("Step 1. Navigate Markets Management >Liquidity Threshold");
//        LiquidityThresholdPage page = agentHomePage.navigateLiquidityThresholdPage();
//
//        log(String.format("Step 2. Search the account %S",controlBlockingAccount));
//        page.search(controlBlockingAccount);
//
//        log(String.format("Step 3. Click active liquidity threshold of %s",controlBlockingAccount));
//        page.setLiquidityThreshold(controlBlockingAccount,true);
//
//        loginBackoffice(boAccount, bopassword,true);
//        LiquidityThresholdSettingsPage liquidityThresholdSettingsPage = backofficeHomePage.navigateLiquidityThresholdSettings();
//        List<String> lstThreshold =  liquidityThresholdSettingsPage.getThreshold("Soccer","Match Odds");
//        log("Verify 1. Verify odds is blur in member Soccer for Live/Non live if Match volume over the setting in BO");
//        loginMember(memberAccount,password);
//
//        log("INFO: Executed completely");
//    }

}
