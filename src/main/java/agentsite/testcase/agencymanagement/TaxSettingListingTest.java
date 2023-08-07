package agentsite.testcase.agencymanagement;

import agentsite.pages.agentmanagement.TaxSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class TaxSettingListingTest extends BaseCaseTest {
    /**
     * @title: Verify can search downline by Login ID
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management >  Tax Setting Listing
     * 2. Input Login ID and  click Submit button
     * @expect: 1. Verify Login display in the result table
     */
    @TestRails(id = "749")
    @Test(groups = {"smoke"})
    public void Agent_AM_Tax_Setting_Listing_749() {
        log("@title: Verify can search downline by Login ID");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();

        log("Step 2. Search PL account and Exchange Product");
        page.taxSettingListing.search(loginID, "", "");

        log("Verify 1. Verify Login display in the result table");
        List<String> lstMembers = page.tblTax.getColumn(page.usernameCol, false);
        Assert.assertEquals(lstMembers.get(0), loginID, "FALED! Login ID not display as search criteria");
        Assert.assertEquals(lstMembers.size(), 1, "FAILED! Should only display 1 record when searching with correct username");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can update tax for all sports
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management >  Tax Setting Listing
     * 2. Search a Member account and check on Select All sport
     * 3. Update valid tax for Soccer, Tennis, Cricket, Basketball, Fancy Other and Click update
     * @expect: 1. Verify tax is updated for all sport and Update status is display green check
     */
    @TestRails(id = "750")
    @Test(groups = {"smoke"})
    public void Agent_AM_Tax_Setting_Listing_750() {
        log("@title: Verify can update tax for all sports");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(2).getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();
        double soccerTax = 0.10;
        double tennisTax = 0.20;
        double cricketTax = 0.40;
        double basketball = 0.50;
//        double fancyTax=0.70;
        double otherTax = 0.60;

        log("Step 2. Search a Member account and check on Select All sport");
        page.taxSettingListing.search(loginID, "", "");
        List<ArrayList<String>> lstExpectedData = page.tblTax.getRowsWithoutHeader(1, false);
        lstExpectedData.get(0).set(page.soccerCol - 1, String.format("%.2f", soccerTax) + "%");
        lstExpectedData.get(0).set(page.cricketCol - 1, String.format("%.2f", cricketTax) + "%");
        lstExpectedData.get(0).set(page.tennisCol - 1, String.format("%.2f", tennisTax) + "%");
        lstExpectedData.get(0).set(page.basketballCol - 1, String.format("%.2f", basketball) + "%");
//        lstExpectedData.get(0).set(page.fancyCol -1,String.format("%.2f",fancyTax)+"%");
        lstExpectedData.get(0).set(page.otherCol - 1, String.format("%.2f", otherTax) + "%");

        log("Step 3. Update valid tax for Soccer, Tennis, Cricket, Basketball, Fancy Other and Click update");
        page.taxSettingListing.updateTaxSetting(loginID, soccerTax, cricketTax, tennisTax, basketball, otherTax);

        log("Verify 1. Verify tax is updated for all sport and Update status is display green check");
        List<ArrayList<String>> lstActualData = page.tblTax.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Data does not update correctly after update tax");
        Assert.assertTrue(page.taxSettingListing.verifyUpdateStatus(lstActualData, true), "FAILED! Data does not update correctly after update tax");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify  Tax Setting Listing UI display correct
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management >  Tax Setting Listing
     * @expect: 1. Verify UI Tax Setting Listing display correctly
     */
    @TestRails(id = "748")
    @Test(groups = {"smoke_newui"})
    public void Agent_AM_Tax_Setting_Listing_748() {
        log("@title: Verify  Tax Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userCode = ProfileUtils.getProfile().getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();

        log("Verify 1. Verify UI Tax Setting Listing display correctly");
        Assert.assertTrue(page.txtUsername.isDisplayed(), "FAILED Username textbox not display");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(), "FAILED Account Status Dropdown not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(), "FAILED Product Dropdown not display");
        Assert.assertEquals(page.btnSearch.getText(), AGConstant.BTN_SUBMIT, "FAILED Search button text should be Submit");
        Assert.assertTrue(page.txtSoccer.isDisplayed(), "FAILED! Soccer textbox not display");
        Assert.assertTrue(page.txtCricket.isDisplayed(), "FAILED! Cricket textbox not display");
        Assert.assertTrue(page.txtTennis.isDisplayed(), "FAILED! Tennis textbox not display");
        Assert.assertTrue(page.txtBasketball.isDisplayed(), "FAILED! Basketball textbox not display");
        Assert.assertTrue(page.txtOther.isDisplayed(), "FAILED! Other textbox not display");
        Assert.assertTrue(page.lblBreadcrumb.getText().contains(userCode), "FAILED! Breadcrumb display incorrect value");
        Assert.assertEquals(page.tblTax.getHeaderNameOfRows(), AGConstant.AgencyManagement.TaxSettingListing.TABLE_TAX, "FAILED! Table header not match with the expected");

        log("INFO: Executed completely");
    }

    @TestRails(id = "4110")
    @Test(groups = {"smoke_sat"})
    public void Agent_AM_Tax_Setting_Listing_4110() {
        log("@title: Verify  Tax Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userCode = ProfileUtils.getProfile().getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();

        log("Verify 1. Verify UI Tax Setting Listing display correctly (does not have Fancy)");
        Assert.assertTrue(page.txtUsername.isDisplayed(), "FAILED Username textbox not display");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(), "FAILED Account Status Dropdown not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(), "FAILED Product Dropdown not display");
        Assert.assertEquals(page.btnSearch.getText(), AGConstant.BTN_SUBMIT, "FAILED Search button text should be Submit");
        Assert.assertTrue(page.txtSoccer.isDisplayed(), "FAILED! Soccer textbox not display");
        Assert.assertTrue(page.txtCricket.isDisplayed(), "FAILED! Cricket textbox not display");
        Assert.assertTrue(page.txtTennis.isDisplayed(), "FAILED! Tennis textbox not display");
        Assert.assertTrue(page.txtBasketball.isDisplayed(), "FAILED! Basketball textbox not display");
        Assert.assertTrue(page.txtOther.isDisplayed(), "FAILED! Other textbox not display");
        Assert.assertTrue(page.lblBreadcrumb.getText().contains(userCode), "FAILED! Breadcrumb display incorrect value");
        Assert.assertEquals(page.tblTax.getHeaderNameOfRows(), AGConstant.AgencyManagement.TaxSettingListing.TABLE_TAX_SAT, "FAILED! Table header not match with the expected");

        log("INFO: Executed completely");
    }
}

