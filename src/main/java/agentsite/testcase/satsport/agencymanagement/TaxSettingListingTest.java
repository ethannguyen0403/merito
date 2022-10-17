package agentsite.testcase.satsport.agencymanagement;

import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.TaxSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.TAX_SETTING_LISTING;

public class TaxSettingListingTest extends BaseCaseMerito {
    /**
     * @title: Verify can search downline by Login ID
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management >  Tax Setting Listing
     * 2. Input Login ID and  click Submit button
     * @expect: 1. Verify Login display in the result table
     */
    @Test (groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Tax_Setting_Listing_0004(String brandname) {
        log("@title: Verify can search downline by Login ID");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(2).getLoginID();
        TaxSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, TAX_SETTING_LISTING, TaxSettingListingPage.class);

        log("Step 2. Search PL account and Exchange Product");
        page.search(loginID,"", "");

        log("Verify 1. Verify Login display in the result table");
        List<String> lstMembers = page.tblTax.getColumn(page.loginIDCol,false);
        Assert.assertEquals(lstMembers.get(0),loginID,"FALED! Login ID not display as search criteria");
        Assert.assertEquals(lstMembers.size(),1,"FAILED! Should only display 1 record when searching with correct username");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can update tax for all sports
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management >  Tax Setting Listing
     *         2. Search a Member account and check on Select All sport
     *         3. Update valid tax for Soccer, Tennis, Cricket, Basketball, Fancy Other and Click update
     * @expect: 1. Verify tax is updated for all sport and Update status is display green check
     */
    @Test (groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Tax_Setting_Listing_0005(String brandname) {
        log("@title: Verify can update tax for all sports");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(2).getLoginID();
        TaxSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, TAX_SETTING_LISTING, TaxSettingListingPage.class);

        double soccerTax = 0.10;
        double tennisTax = 0.20;
        double cricketTax = 0.40;
        double basketball = 0.50;
        //double fancyTax=0.701;
        double otherTax = 0.60;

        page.search(loginID,"", "");
        List<ArrayList<String>> lstExpectedData = page.tblTax.getRowsWithoutHeader(1,false);
        lstExpectedData.get(0).set(page.soccerCol -1,String.format("%.2f",soccerTax)+"%");
        lstExpectedData.get(0).set(page.cricketCol -1,String.format("%.2f",cricketTax)+"%");
        lstExpectedData.get(0).set(page.tennisCol -1,String.format("%.2f",tennisTax)+"%");
        lstExpectedData.get(0).set(page.basketballCol -1,String.format("%.2f",basketball)+"%");
        //lstExpectedData.get(0).set(page.fancyCol -1,String.format("%.2f",fancyTax)+"%");
        lstExpectedData.get(0).set(page.otherCol -1,String.format("%.2f",otherTax)+"%");

        log("Step 3. Update valid tax for Soccer, Tennis, Cricket, Basketball, Fancy Other and Click update");
        page.updateTaxSetting(loginID,soccerTax,cricketTax,tennisTax,basketball,otherTax);

        log("Verify 1. Verify tax is updated for all sport and Update status is display green check");
        List<ArrayList<String>> lstActualData = page.tblTax.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstActualData,lstExpectedData,"FAILED! Data does not update correctly after update tax");
        Assert.assertTrue(page.verifyUpdateStatus(lstActualData,true),"FAILED! Data does not update correctly after update tax");


        log("INFO: Executed completely");
    }

    /**
     * @title: Verify  Tax Setting Listing UI display correct
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management >  Tax Setting Listing
     * @expect: 1. Verify UI Tax Setting Listing display correctly
     */
    @Test (groups = {"smoke"})
    @Parameters("username")
    public void Agent_AM_Tax_Setting_Listing_0002(String username) {
        log("@title: Verify  Tax Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userCode = ProfileUtils.getProfile().getUserCode();
        TaxSettingListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, TAX_SETTING_LISTING, TaxSettingListingPage.class);

        log("Verify 1. Verify UI Tax Setting Listing display correctly");
        Assert.assertTrue(page.txtUsername.isDisplayed(),"FAILED Username textbox not display");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(),"FAILED Account Status Dropdown not display");
        Assert.assertTrue(page.ddbProduct.isDisplayed(),"FAILED Product Dropdown not display");
        Assert.assertEquals(page.btnSearch.getText(),AGConstant.BTN_SUBMIT,"FAILED Search button text should be Submit");
        Assert.assertTrue(page.txtSoccer.isDisplayed(),"FAILED! Soccer textbox not display");
        Assert.assertTrue(page.txtCricket.isDisplayed(),"FAILED! Cricket textbox not display");
        Assert.assertTrue(page.txtTennis.isDisplayed(),"FAILED! Tennis textbox not display");
        Assert.assertTrue(page.txtBasketball.isDisplayed(),"FAILED! Basketball textbox not display");
        Assert.assertTrue(page.txtOther.isDisplayed(),"FAILED! Other textbox not display");
        Assert.assertEquals(page.lblBreadcrumb.getText(),String.format("%s (%s)",userCode,username),"FAILED! Breadcrumb display incorrect value");
        Assert.assertEquals(page.tblTax.getHeaderNameOfRows(), AGConstant.AgencyManagement.TaxSettingListing.TABLE_TAX,"FAILED! Table header not match with the expected");

        log("INFO: Executed completely");
    }
}

