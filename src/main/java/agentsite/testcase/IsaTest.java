package agentsite.testcase;

import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.TaxSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.driver.DriverManager;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.EXCHANGE_GAMES;

public class IsaTest extends BaseCaseTest {
    /**
     * @title: Validate that user can logout successfully
     * @steps: 1. Log in with a valid username and password
     * 2. Click Logout button
     * @expect: 1. Logout is successful
     */
    @TestRails(id = "672")
    @Test(groups = {"smoke"})
    public void Agent_Logout_672() {
        log("@title: Validate that user can logout successfully");
        log("Step 1: Log in with a valid username and password");
        log("Step 2: Click Logout button");
        agentLoginPage = agentHomePage.logout();

        log("Verify: Logout is successful");
        Assert.assertTrue(agentLoginPage.txtUsername.isDisplayed(), "ERROR: Username text-box is not displayed after logging out");
        String login = agentLoginPage.lblLogin.getText();
        Assert.assertEquals(login.toUpperCase(), AGConstant.LoginPage.LOGIN, String.format("ERROR: The expected text is '%s' but found '%s'", AGConstant.LoginPage.LOGIN, login));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3974")
    @Test(groups = {"regression"})
    public void Agent_AM_Tax_Setting_Listing_3974() {
        log("@title: Validate the in active product not display in product list in Tax Setting page");
        log("@Precondition_Step: 1 Login Agent Site\n" +
                "2 There is a downline agent is inactive  Exchange Game product");
        DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
        downLineListingPage.downlineListing.searchDownline("","","");
        downLineListingPage.productStatusSettingInforSection.selectProduct(EXCHANGE_GAMES);
        log("Step 1. Navigate Agency Management > Tax Setting Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String userCode = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();
        TaxSettingListingPage page = agentHomePage.navigateTaxSettingListingPage();

        log("Step 2. Search PL account and Exchange Product");
        page.taxSettingListing.search(userCode, "", "");

        log("Verify 1. Verify Login display in the result table");
        List<String> lstMembers = page.tblTax.getColumn(page.usernameCol, false);
        Assert.assertEquals(lstMembers.get(0), userCode, "FAILED! Login ID not display as search criteria");
        Assert.assertEquals(lstMembers.size(), 1, "FAILED! Should only display 1 record when searching with correct username");

        log("INFO: Executed completely");
    }

}
