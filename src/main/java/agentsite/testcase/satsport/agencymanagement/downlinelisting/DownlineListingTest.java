package agentsite.testcase.satsport.agencymanagement.downlinelisting;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.sat.agentmanagement.SATDownLineListingPage;

import java.util.List;

import static agentsite.common.AGConstant.*;
import static agentsite.common.AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL;
import static agentsite.common.AGConstant.AgencyManagement.DownlineListing.LST_ACCOUNT_STATUS;
import static agentsite.common.AGConstant.AgencyManagement.DownlineListing.LST_SAT_DOWLINE_LISTING_TABLE_HEADER;
import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.DOWNLINE_LISTING;

public class DownlineListingTest extends BaseCaseMerito {
    @Test(groups = {"satregression"})
    public void Agent_AM_Downline_Listing_002() {
        log("@title: Validate UI in Downline Listing ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        SATDownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, SATDownLineListingPage.class);
        List<String> lstHeaderTable = page.tblDowlineListing.getHeaderNameOfRows();

        log("1. Verify Title is : Downline Listing\n" +
                "2. Control display correctly\n" +
                "3. Root breadcrumb is login ID\n" +
                "4. Account List table display with correct header: No., Login ID, Client Name, Credit Initiation, Account Status, Edit, Change Password, Level, Delay Bet, Downline, Created Date, Last Login Time, Last Login IP\n" +
                "5 Pagingnation section in the bottom");
        Assert.assertEquals(page.getPageTitle(), DOWNLINE_LISTING, "FAILED! Page title is incorrect displayed");
        Assert.assertEquals(page.lblLoginId.getText(), LOGIN_ID, "FAILED! Login ID is incorrect displayed");
        Assert.assertEquals(page.lblAccountStatus.getText(), ACCOUNT_STATUS, "FAILED! Account Status is incorrect displayed");
        Assert.assertEquals(page.lblLevel.getText(), LEVEL, "FAILED! Account Status is incorrect displayed");
        Assert.assertEquals(page.btnSearch.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect display");
        Assert.assertTrue(page.txtLoginID.isDisplayed(), "FAILED! Login ID textbox is incorrect display");
        Assert.assertTrue(page.ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdownbox is incorrect display");
        Assert.assertTrue(page.ddbLevel.isDisplayed(), "FAILED! Level dropdown is incorrect display");
        Assert.assertEquals(page.ddbAccountStatus.getOptions(), LST_ACCOUNT_STATUS, "FAILED! Data in Account Status dropdownbox is incorrect displayed");
        Assert.assertEquals(page.ddbLevel.getOptions(), DDB_LEVEL, "FAILED! Data in Account Status dropdownbox is incorrect displayed");
        Assert.assertEquals(lstHeaderTable, LST_SAT_DOWLINE_LISTING_TABLE_HEADER, "FAILED! Table header is incorrect displayed");

        log("INFO: Executed completely");
    }
}

