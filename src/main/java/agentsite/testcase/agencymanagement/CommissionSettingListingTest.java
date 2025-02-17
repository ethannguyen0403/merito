package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CommissionSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.ultils.agencymanagement.CommissionSettingListingUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommissionSettingListingTest extends BaseCaseTest {

    /**
     * @title: Verify Commission Setting Listing UI display correct
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Commission Setting Listing
     * @expect: 1. Verify UI of Commission Setting Listing display correctly
     */
    @TestRails(id = "740")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.6.0"})
    public void Agent_AM_Commission_Setting_Listing_740() {
        log("@title: Verify Commission Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        String userCode = ProfileUtils.getProfile().getUserCodeAndLoginID();
        CommissionSettingListingPage page = agentHomePage.navigateCommissionSettingListingPage();
        page.search("", "", "", AGConstant.LIVE_DEALER_ASIAN);

        log("Verify 1. Verify Commission Setting Listing UI display correct");
        Assert.assertTrue(page.txtUsername.isDisplayed(), "FAILED! Username textbox is not displayed");
//        Assert.assertTrue(page.ddbLevel.isDisplayed(), "FAILED! Level dropdown is not displayed");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdown is not displayed");
        Assert.assertTrue(page.ddbProduct.isDisplayed(), "FAILED! Product dropdown is not displayed");
        Assert.assertEquals(page.btnSearch.getText(), AGConstant.BTN_SUBMIT, "FAILED! Search button should be Submit");
        Assert.assertEquals(page.btnUpdate.getText(), AGConstant.BTN_UPDATE, "FAILED! Update button display incorrect");
        Assert.assertTrue(page.lblBreadcrumb.getText().contains(userCode), "FAILED! Breadcrumb not display the parent account");
        Assert.assertEquals(page.lblMemberBreadcrumb.getText(), "Members", "FAILED! Breadcrumb not display the parent account");
        page.verifyTableHeaderDisplayCorrect(AGConstant.LIVE_DEALER_ASIAN);
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can search commission setting Listing by Login ID
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Commission Setting Listing
     * 2. Enter valid Login ID in Username textbox and click on Submit button
     * @expect: 1. Verify the correct username is displayed
     */
    @TestRails(id = "741")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.5.0"})
    public void Agent_AM_Commission_Setting_Listing_741() {
        log("@title: Verify can search commission setting Listing by Login ID");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        CommissionSettingListingPage page = agentHomePage.navigateCommissionSettingListingPage();

        log("Step 2. Enter valid Login ID in Username textbox and click on Submit button");
        page.search(loginID, "", "", "");
        page.waitingLoadingSpinner();
        log("Verify 1. Verify the correct username is displayed");
        List<String> lstMembers = page.tblMemberCommission.getColumn(page.colUsername, false);
        Assert.assertEquals(page.tblAgentCommission.getRowsWithoutHeader(1, false).get(0).get(0), AGConstant.NO_RECORD_FOUND, "FAILED! Should display no record when search member only");
        Assert.assertEquals(lstMembers.get(0), loginID, "FALED! Username not display as search criteria");
        Assert.assertEquals(lstMembers.size(), 1, "FAILED! Should only display 1 record when searching with correct username");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can update commission for a member account
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Commission Setting Listing
     * 2. Search player account and select the active product: e.g. Live Dealer European
     * 3. Click on the check box to select the account and update commission for all games
     * @expect: 1. Verify commissions are update for all games
     * 2. Green check display at Update Status column if successfully update commission
     */
    @TestRails(id = "742")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.4.0"})
    public void Agent_AM_Commission_Setting_Listing_742() {
        log("@title: Verify can update commission for a member account");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        CommissionSettingListingPage page = agentHomePage.navigateCommissionSettingListingPage();
        List<Double> lstGameCommission = Arrays.asList(0.01);

        log("Step 2. Search player account and select the active product: e.g. Live Dealer European");
        page.search(loginID, "", "", AGConstant.LIVE_DEALER_ASIAN);
        List<ArrayList<String>> lstExpected = page.tblMemberCommission.getRowsWithoutHeader(1, false);
        lstExpected.get(0).set(9, String.format("%.2f", lstGameCommission.get(0)));

        try {
            log("Step 3. Click on the check box to select the account and update commission for all games");
            page.updateCommissionSetting(loginID, false, AGConstant.LIVE_DEALER_ASIAN, lstGameCommission.get(0));

            log("Verify 1. Verify commissions are update for all games");
            log("Verify 2. Green check display at Update Status column if successfully update commission");
            Assert.assertTrue(page.verifyCommissionUpdate(lstExpected, false, true), "FAILED! Green check is NOT display");

            log("INFO: Executed completely");
        } finally {
            log("Post-condition");
            page.updateCommissionSetting(loginID, false, AGConstant.LIVE_DEALER_ASIAN, 0.0);
        }

    }

    @TestRails(id = "3631")
    @Test(groups = {"http_request"})
    public void Agent_AM_Commission_Setting_Listing_3631() {
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        agentHomePage.navigateCommissionSettingListingPage();

        log("Verify 1. Verify the correct username is displayed");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3632")
    @Test(groups = {"regression"})
    @Parameters({"username"})
    public void Agent_AM_Commission_Setting_Listing_3632(String username) {
        log("@title: Verify can update commission for a member account");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDownLineUsers(_brandname, username, userID);
        CommissionSettingListingPage page = agentHomePage.navigateCommissionSettingListingPage();

        log("Step 2. Enter valid username in Username textbox and click on Submit button");
        page.search(lstUsers.get(0).getUserCode(), "", "", "");
        log("Verify 2. Validate the correct username is displayed");
        List<ArrayList<String>> lstRow = page.tblAgentCommission.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstUsers.get(0).getUserCode(), lstRow.get(0).get(1), "FAILED! Search result is not matched with filter criteria");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3633")
    @Test(groups = {"regression"})
    @Parameters({"username"})
    public void Agent_AM_Commission_Setting_Listing_3633(String username) {
        log("@title: Verify can update commission for a member account");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDownLineUsers(_brandname, username, userID);
        CommissionSettingListingPage page = agentHomePage.navigateCommissionSettingListingPage();
        double currentAgentSettingValue = CommissionSettingListingUtils.getAgentCommissionSettingByProduct(lstUsers.get(0).getUserCode(), "EZUGI");
        double commissionUpdateValue = currentAgentSettingValue + 0.01;
        try {
            page.updateCommissionSetting(lstUsers.get(0).getUserCode(), true, AGConstant.LIVE_DEALER_EUROPEAN, commissionUpdateValue);
            page.search(lstUsers.get(0).getUserCode(), "", "", AGConstant.LIVE_DEALER_EUROPEAN);

            page.verifyCommissionUpdated(true, String.valueOf(commissionUpdateValue));
            log("Step 2. Enter valid username in Username textbox and click on Submit button");
            log("INFO: Executed completely");
        } finally {
            page.updateCommissionSetting(lstUsers.get(0).getUserCode(), true, AGConstant.LIVE_DEALER_EUROPEAN, commissionUpdateValue - 0.01);
        }

    }

}

