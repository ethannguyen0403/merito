package agentsite.testcase.all.agencymanagement;

import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.CommissionSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.COMMISSION_LISTING;

public class CommissionSettingListingTest extends BaseCaseMerito {

    /**
     * @title: Verify Commission Setting Listing UI display correct
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:  1. Navigate Agency Management > Commission Setting Listing
     * @expect: 1. Verify UI of Commission Setting Listing display correctly
     */
    @Test (groups = {"smoke"})
    @Parameters("username")
    public void Agent_AM_Commission_Setting_Listing_0002(String username) {
        log("@title: Verify Commission Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        String userCode = ProfileUtils.getProfile().getUserCodeAndLoginID();
        CommissionSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, COMMISSION_LISTING, CommissionSettingListingPage.class);
        page.search("", "", "", AGConstant.LIVE_DEALER_ASIAN);

        log("Verify 1. Verify Commission Setting Listing UI display correct");
        Assert.assertTrue(page.txtUsername.isDisplayed(), "FAILED! Username textbox is not displayed");
        Assert.assertTrue(page.ddbLevel.isDisplayed(), "FAILED! Level dropdown is not displayed");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdown is not displayed");
        Assert.assertTrue(page.ddbProduct.isDisplayed(), "FAILED! Product dropdown is not displayed");
        Assert.assertEquals(page.btnSearch.getText(), AGConstant.BTN_SUBMIT, "FAILED! Search button should be Submit");
        Assert.assertEquals(page.btnUpdate.getText(), AGConstant.BTN_UPDATE, "FAILED! Update button display incorrect");
        Assert.assertEquals(page.lblBreadcrumb.getText(), userCode, "FAILED! Breadcrumb not display the parent account");
        Assert.assertEquals(page.lblMemberBreadcrumb.getText(),"Members","FAILED! Breadcrumb not display the parent account");
        Assert.assertTrue(page.isGameDropdownExist(AGConstant.AgencyManagement.CommissionSettingListing.LST_LIVE_DEALER_ASIAN_GAMES),"FAILED! Missing a game dropdwon");
        ArrayList<String> headerMemberTitle = page.tblMemberCommission.getHeaderNameOfRows();
        ArrayList<String> expectedMemberTitle = new ArrayList<String>();
        expectedMemberTitle.addAll(AGConstant.AgencyManagement.CommissionSettingListing.TABLE_AGENT_HEADER_LIVE_DEALER_ASIAN);
      /*  ArrayList<String> title2 = new ArrayList<String>(){
            {
                add("SAD");
                add("Member");
                add("SAD");
                add("Member");
                add("SAD");
                add("Member");
                add("SAD");
                add("Member");
                add("SAD");
                add("Member");
                add("SAD");
                add("Member");
                add("SAD");
                add("Member");
                add("SAD");
                add("Member");
            }
        };
        expectedMemberTitle.addAll(title2);*/

        Assert.assertEquals(page.tblAgentCommission.getHeaderNameOfRows(), AGConstant.AgencyManagement.CommissionSettingListing.TABLE_AGENT_HEADER_LIVE_DEALER_ASIAN,"FAILED! Admin header table display not correct");
       // Assert.assertEquals(headerMemberTitle,expectedMemberTitle,"FAILED! Member header table display not correct");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can search commission setting Listing by Login ID
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:  1. Navigate Agency Management > Commission Setting Listing
     *          2. Enter valid Login ID in Username textbox and click on Submit button
     * @expect: 1. Verify the correct username is displayed
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_Commission_Setting_Listing_0004() {
        log("@title: Verify can search commission setting Listing by Login ID");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE").get(0).getUserCode();
        CommissionSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, COMMISSION_LISTING, CommissionSettingListingPage.class);

        log("Step 2. Enter valid Login ID in Username textbox and click on Submit button");
        page.search(loginID,"", "","");

        log("Verify 1. Verify the correct username is displayed");
        List<String> lstMembers = page.tblMemberCommission.getColumn(page.colUsername,false);
        Assert.assertEquals(page.tblAgentCommission.getRowsWithoutHeader(1,false).get(0).get(0), AGConstant.NO_RECORD_FOUND,"FAILED! Should display no record when search member only");
        Assert.assertEquals(lstMembers.get(0),loginID,"FALED! Username not display as search criteria");
        Assert.assertEquals(lstMembers.size(),1,"FAILED! Should only display 1 record when searching with correct username");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can update commission for a member account
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management > Commission Setting Listing
     *          2. Search player account and select the active product: e.g. Live Dealer European
     *          3. Click on the check box to select the account and update commission for all games
     * @expect: 1. Verify commissions are update for all games
     *           2. Green check display at Update Status column if successfully update commission
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_Commission_Setting_Listing_0005() {
        log("@title: Verify can update commission for a member account");
        log("Step 1. Navigate Agency Management > Commission Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE").get(2).getUserCode();
        CommissionSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, COMMISSION_LISTING, CommissionSettingListingPage.class);
        List<Double> lstGameCommission = Arrays.asList(0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08);

        log("Step 2. Search player account and select the active product: e.g. Live Dealer European");
        page.search(loginID, "", "", AGConstant.LIVE_DEALER_ASIAN);
        List<ArrayList<String>> lstExpected = page.tblMemberCommission.getRowsWithoutHeader(1, false);
        lstExpected.get(0).set(9, String.format("%.2f", lstGameCommission.get(0)));
        lstExpected.get(0).set(11, String.format("%.2f", lstGameCommission.get(1)));
        lstExpected.get(0).set(13, String.format("%.2f", lstGameCommission.get(2)));
        lstExpected.get(0).set(15, String.format("%.2f", lstGameCommission.get(3)));
        lstExpected.get(0).set(17, String.format("%.2f", lstGameCommission.get(4)));
        lstExpected.get(0).set(19,String.format("%.2f",lstGameCommission.get(5)));
        lstExpected.get(0).set(21,String.format("%.2f",lstGameCommission.get(6)));
        lstExpected.get(0).set(23,String.format("%.2f",lstGameCommission.get(7)));

        log("Step 3. Click on the check box to select the account and update commission for all games");
        page.updateCommissiongSetting(loginID,false, AGConstant.LIVE_DEALER_ASIAN,lstGameCommission);

        log("Verify 1. Verify commissions are update for all games");
        log("Verify 2. Green check display at Update Status column if successfully update commission");
        Assert.assertTrue(page.veifyComissionUpdate(lstExpected, false,true),"FAILED! ");

        log("INFO: Executed completely");
    }

}

