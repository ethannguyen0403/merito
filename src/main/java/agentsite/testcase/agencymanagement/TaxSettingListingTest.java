package agentsite.testcase.agencymanagement;

import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.agentmanagement.TaxSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.AGConstant.EXCHANGE_GAMES;
import static common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static common.AGConstant.HomePage.TAX_SETTING_LISTING;


public class TaxSettingListingTest extends BaseCaseTest {
    @TestRails(id = "3643")
    @Test(groups = {"http_request"})
    public void Agent_AM_Tax_Setting_Listing_3643() {
        log("@title: Validate There is no http responded error returned");
        log("Step 1. Navigate Agency Management  > Tax Setting Listing");
        agentHomePage.navigateTaxSettingListingPage();

        log("Verify 1. Tax Setting Listing page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3644")
    @Test(groups = {"regression"})
    public void Agent_AM_Tax_Setting_Listing_3644() {
        log("@title: Validate can search downline by username");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String userCode = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();

        log("Step 2. Search PL account and Exchange Product");
        page.taxSettingListing.search(userCode, "", "");

        log("Verify 1. Verify Login display in the result table");
        List<String> lstMembers = page.taxSettingListing.tblTax.getColumn(page.taxSettingListing.usernameCol, false);
        Assert.assertEquals(lstMembers.get(0), userCode, "FAILED! Login ID not display as search criteria");
        Assert.assertEquals(lstMembers.size(), 1, "FAILED! Should only display 1 record when searching with correct username");

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can search downline by Login ID
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management >  Tax Setting Listing
     * 2. Input Login ID and  click Submit button
     * @expect: 1. Verify Login display in the result table
     */
    @TestRails(id = "749")
    @Test(groups = {"smoke", "MER.Maintenance.2024.V.4.0"})
    public void Agent_AM_Tax_Setting_Listing_749() {
        log("@title: Verify can search downline by Login ID");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = "";
        loginID = _brandname.equalsIgnoreCase("satsport") ?
                DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getLoginID():
                DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        if (loginID.isEmpty()) {
            throw new SkipException("SKIPPED! The player have no login ID for filter");
        }
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();

        log("Step 2. Search PL account and Exchange Product");
        page.taxSettingListing.search(loginID, "", "");

        log("Verify 1. Verify Login display in the result table");
        List<String> lstMembers = page.getListLoginID();
        Assert.assertEquals(lstMembers.get(0), loginID, "FAILED! Login ID not display as search criteria");
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
    @Test(groups = {"smoke", "nolan"})
    public void Agent_AM_Tax_Setting_Listing_750() {
        log("@title: Verify can update tax for all sports");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String loginID = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();
        double adjustValue = 0.10;

        log("Step 2. Search a Member account and check on Select All sport");
        page.taxSettingListing.search(loginID, "", "");
        List<ArrayList<String>> lstExpectedData = page.taxSettingListing.defineListTaxSetting(adjustValue);

        try {
            log("Step 3. Update valid tax for Soccer, Tennis, Cricket, Basketball, Fancy Other and Click update");
            page.taxSettingListing.updateTaxSetting(loginID, lstExpectedData);

            log("Verify 1. Verify tax is updated for all sport and Update status is display green check");
            List<ArrayList<String>> lstActualData = page.taxSettingListing.tblTax.getRowsWithoutHeader(1, false);
            Assert.assertEquals(lstActualData, lstExpectedData, "FAILED! Data does not update correctly after update tax");
            Assert.assertTrue(page.taxSettingListing.verifyUpdateStatus(lstActualData, true), "FAILED! Data does not update correctly after update tax");

            log("INFO: Executed completely");
        } finally {
            log("Post-condition: Revert tax to old value");
            lstExpectedData = page.taxSettingListing.defineListTaxSetting(-0.10);
            page.taxSettingListing.updateTaxSetting(loginID, lstExpectedData);
        }

    }

    /**
     * @title: Verify  Tax Setting Listing UI display correct
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Agency Management >  Tax Setting Listing
     * @expect: 1. Verify UI Tax Setting Listing display correctly
     */
    @TestRails(id = "748")
    @Test(groups = {"smoke_newui", "MER.Maintenance.2024.V.4.0"})
    public void Agent_AM_Tax_Setting_Listing_748() {
        log("@title: Verify  Tax Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userCode = ProfileUtils.getProfile().getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();

        log("Verify 1. Verify UI Tax Setting Listing display correctly");
        page.verifyUITaxSetting(userCode);
        log("INFO: Executed completely");
    }

    @TestRails(id = "4110")
    @Test(groups = {"smoke_sat", "MER.Maintenance.2024.V.4.0"})
    public void Agent_AM_Tax_Setting_Listing_4110() {
        log("@title: Verify  Tax Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userCode = ProfileUtils.getProfile().getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();

        log("Verify 1. Verify UI Tax Setting Listing display correctly (does not have Fancy)");
        page.verifyUITaxSetting(userCode);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3973")
    @Test(groups = {"regression"})
    @Parameters({"username", "downlineAccount", "password"})
    public void Agent_AM_Tax_Setting_Listing_3973(String username, String downlineAccount, String password) throws Exception {
        log("@title: Validate the Tax Setting page is hidden when Exchange and Exchange Game product is inactive");
        log("Step 1. Login Agent Site");
        log("There is a downline agent is inactive Exchange and Exchange Game product");
        final Map<String, Boolean> PRODUCTS = new HashMap<String, Boolean>() {
            {
                put("Exchange", false);
                put("Exchange Games", false);
            }
        };

        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(downlineAccount, "", "Agent");
        EditDownLinePage editDownLinePage = page.downlineListing.clickEditIcon(downlineAccount);
        editDownLinePage.confirmSecurityCode(environment.getSecurityCode());
        try {
            editDownLinePage.updateProducts(PRODUCTS);
            page.logout();

            log("Step 1. Login the downline account in precondition");
            loginAgent(downlineAccount, password, true);

            log("Step 2: Expand left menu and observe Tax Setting menu");
            List<String> lstSubMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

            log("Verify: The page is no longer display in the left menu");
            Assert.assertFalse(lstSubMenu.contains(TAX_SETTING_LISTING), "Failed! The menu display even Exchange and Exchange Game are inactive ");

            log("Post-condition Step: Expand left menu and observe Tax Setting menu");
            page.logout();
            loginAgent(username, password, true);
            page.searchDownline(downlineAccount, "", "Agent");
            editDownLinePage = page.downlineListing.clickEditIcon(downlineAccount);
            editDownLinePage.confirmSecurityCode(environment.getSecurityCode());
        } finally {
            final Map<String, Boolean> PRODUCTS1 = new HashMap<String, Boolean>() {
                {
                    put("Exchange", true);
                    put("Exchange Games", true);
                }
            };
            editDownLinePage.updateProducts(PRODUCTS1);

            log("INFO: Executed completely");
        }

    }

    @TestRails(id = "3974")
    @Test(groups = {"regression"})
    @Parameters({"downlineAccount", "password"})
    public void Agent_AM_Tax_Setting_Listing_3974(String downlineAccount, String password) throws Exception {
        log("@title: Validate the Tax Setting pa ge is hidden when Exchange and Exchange Game product is inactive");
        log("Step 1. Login Agent Site");
        log("There is a downline agent is inactive Exchange and Exchange Game product");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(downlineAccount, "", "Agent");
        EditDownLinePage editDownLinePage = page.downlineListing.clickEditIcon(downlineAccount);
        editDownLinePage.confirmSecurityCode(environment.getSecurityCode());
        final Map<String, Boolean> PRODUCTS1 = new HashMap<String, Boolean>() {
            {
                put("Exchange", true);
                put("Exchange Games", false);
            }
        };
        editDownLinePage.updateProducts(PRODUCTS1);
        page.logout();

        log("Step 1. Login the downline account in precondition");
        loginAgent(downlineAccount, password, true);

        log("Step 2:  Active Tax Setting page");
        TaxSettingListingPage taxSettingListingPage = agentHomePage.navigateTaxSettingListingPage();
        List<String> products = taxSettingListingPage.getProducts();


        log("Step 3: Click on product dropdown");
        log("Verify: Verify Exchange Game is not display");
        Assert.assertFalse(products.contains(EXCHANGE_GAMES), "FAILED, Exchange Game should not display");

        log("INFO: Executed completely");
    }
}

