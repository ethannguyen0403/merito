package agentsite.testcase.all.agencymanagement.downlinelisting;

import baseTest.BaseCaseMerito;
import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.pages.all.components.SuccessPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import util.testraildemo.TestRails;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static agentsite.common.AGConstant.*;
import static agentsite.common.AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL;
import static agentsite.common.AGConstant.AgencyManagement.DownlineListing.LST_ACCOUNT_STATUS;
import static agentsite.common.AGConstant.AgencyManagement.DownlineListing.LST_SAT_DOWLINE_LISTING_TABLE_HEADER;
import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.DOWNLINE_LISTING;

public class DownlineListingTest extends BaseCaseMerito {

    @Test(groups = {"http_request"})
    public void Agent_AM_Downline_Listing_001() {
        log("@title: Validate can search downline with Username ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Verify There is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console Error display");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression1"})
    public void Agent_AM_Downline_Listing_002() {
        log("@title: Validate UI in Downline Listing ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
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

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_003(String brandname) {
        log("@title: Validate can search direct downline");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = loginAccInfo.getUserID();
        ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Input direct downline , account status is All, and level is All");
        page.searchDownline(directDownline.getLoginID(), "All", "All");

        log("Verify 1.Account is display in the list");
        int totalRow = page.tblDowlineListing.getNumberOfRows(false, false);
        List<String> lstRecord = page.tblDowlineListing.getColumn(page.userCodeCol, false);
        Assert.assertEquals(totalRow, 1, String.format("Failed!There are more than 1 records when search login ID %s", directDownline.getUserCode()));
        Assert.assertEquals(lstRecord.get(0), directDownline.getUserCode(), String.format("Failed! Expected usser code %s display but found %s", directDownline.getUserCode(), lstRecord.get(0)));
        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_004(String brandname) {
        log("@title: Validate can search indirect downline ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        AccountInfo indirectDownline = DownLineListingUtils.getDownLineUsers(directDownline.getUserID(), "PL", "ACTIVE", brandname).get(0);
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Input direct downline , account status is All, and level is All");
        page.searchDownline(indirectDownline.getLoginID(), "All", "All");

        log("Verify 1.Account is display in the list");
        int totalRow = page.tblDowlineListing.getNumberOfRows(false, false);
        List<String> lstRecord = page.tblDowlineListing.getColumn(page.userCodeCol, false);
        Assert.assertEquals(totalRow, 1, String.format("Failed!There are more than 1 records when search login ID %s", indirectDownline.getUserCode()));
        Assert.assertEquals(lstRecord.get(0), indirectDownline.getUserCode(), String.format("Failed! Expected login id %s display but found %s", indirectDownline.getUserCode(), lstRecord.get(0)));

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_005() {
        log("@title: Validate can only search with correctly account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Get the correct account and input the data that missing the last letter");
        page.searchDownline("invalidloginID", "All", "All");

        log("Verify 1. No record found");
        Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_007() {
        log("@title:Validate can search downline by Active Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2.  Search Active status and click submit button");
        page.searchDownline("", "Active", "All");

        log("Verify1. All account in Active status display\n" +
                "If have no active account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Active")), "FAILED! List downline account contain account status not in Active");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_008() {
        log("@title: Validate can search downline by Inactive  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Select Inactive status and click submit button");
        page.searchDownline("", "Inactive", "All");

        log("Verify 1. All account in Inactive status display\n" +
                "If have no Inactive account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Inactive")), "FAILED! List downline account contain account status not in Inactive");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_010() {
        log("@title: Validate can search downline by Suspended  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Select Suspended status and click submit button");
        page.searchDownline("", "Suspended", "All");

        log("Verify 1. All account in Suspended status display\n" +
                "If have no Suspended account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Suspended")), "FAILED! List downline account contain account status not in Suspended");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }
        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_011() {
        log("@title: Validate can search downline by Blocked  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Select Blocked status and click submit button");
        page.searchDownline("", "Blocked", "All");

        log("Verify 1. All account in Blocked status display\n" +
                "If have no Blocked account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Blocked")), "FAILED! List downline account contain account status not in Suspended");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }
        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_012() {
        log("@title: Validate can search downline by Closed  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step2.  Select Closed status and click submit button");
        page.searchDownline("", "Closed", "All");

        log("Verify 1. All account in Closed status display\n" +
                "If have no Closed account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Closed")), "FAILED! List downline account contain account status not in Suspended");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_013(String brandname) throws Exception {
        log("@title: Validate can inactive member account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Select member account \n" +
                "3. Update an active account to inactive status\n" +
                "4. Click save icon next to the drop box");
        page.searchDownline(userCode, "All", "All");
        SuccessPopup popup = page.updateAccountStatus(userCode, "Inactive");

        log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                "2. Click OK button => the popup is disappear\n" +
                "3. Verify Account status Inactive\n");
        Assert.assertEquals(popup.getContentMessage(), "Update is successful!");
        popup.close();
        Assert.assertFalse(popup.isDisplayed(), "FAILED! Confirm popup is not disappear when closed");
        Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Inactive"), "FAILED! Status is incorrect display");

        log("Postcondition change status to active");
        page.updateAccountStatus(userCode, "Active").close();

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_014(String brandname) {
        log("@title:Validate can suspend  account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Select member account \n" +
                "3. Update an active account to suspended status\n" +
                "4. Click save icon next to the drop box");
        page.searchDownline(userCode, "All", "All");
        SuccessPopup popup = page.updateAccountStatus(userCode, "Suspended");
        log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                "2. Click OK button => the popup is disappear\n" +
                "3. Verify Account status Suspended\n ");
        Assert.assertEquals(popup.getContentMessage(), "Update is successful!");
        popup.close();
        Assert.assertFalse(popup.isDisplayed(), "FAILED! Confirm popup is not disappear when closed");
        Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Suspended"), "FAILED! Status is incorrect display");

        log("Postcondition change status to active");
        page.updateAccountStatus(userCode, "Active").close();

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_017(String brandname) {
        log("@title: Validate can close an account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Select member account in any status\n" +
                "3. Close the account");
        page.searchDownline(userCode, "All", "All");
        SuccessPopup popup = page.updateAccountStatus(userCode, "Closed");

        log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                "2. Click OK button => the popup is disappear\n" +
                "3. Verify Account status Closed and readable. Save button is hidden\n");
        Assert.assertEquals(popup.getContentMessage(), "Update is successful!");
        popup.close();
        Assert.assertFalse(popup.isDisplayed(), "FAILED! Confirm popup is not disappear when closed");
        Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Closed"), "FAILED! Status is incorrect display");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_015(String brandname) {
        log("@title: Validate can active account from inactive status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Select member account that inactivated\n");
        page.searchDownline(userCode, "All", "All");
        log("Inactive account");
        page.updateAccountStatus(userCode, "Inactive").close();

        log("Step 3 Active the account");
        page.updateAccountStatus(userCode, "Active").close();
        log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                "2. Click OK button => the popup is disappear\n" +
                "3. Verify Account status Active\n");
        Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Acitve"), "FAILED! Status is incorrect display");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_016(String brandname) {
        log("@title: Validate can active account from suspend status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Search an account");
        page.searchDownline(userCode, "All", "All");

        log("Step 3 Suspend account");
        page.updateAccountStatus(userCode, "Suspended").close();

        log("Step 3 Active the account");
        page.updateAccountStatus(userCode, "Active").close();

        log("Verify 1. Verify Account status Active");
        Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Active"), "FAILED! Status is incorrect display");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_018(String brandname) {
        log("@title: Validate can not active close an account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Search an account");
        page.searchDownline(userCode, "All", "All");

        log("Step 3 Closed account");
        page.updateAccountStatus(userCode, "Closed").close();

        log("Verify 1. Verify cannot active the closed account");
        Assert.assertFalse(page.getAccountStatusDropdown(userCode).isEnabled(), "FAILED! Account is closed should disable Account status dropdwon");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_019(String brandname) {
        log("@title: Validate can inactive an agent");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Select agent account in any level\n" +
                "3. Update to Inactive status\n");
        page.searchDownline(userCode, "All", "Agent");

        log("Step 3 Inactive agent account");
        page.updateAccountStatus(userCode, "Inactive").close();

        log("Verify 1. Verify status is change to Inactive");
        Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Inactive"), "FAILED! Status is incorrect display");
        log("INFO: Executed completely");
    }

    @Test(groups = {"regressionfe"})
    public void Agent_AM_Downline_Listing_021() {
        log("@title:Validate can open and update Delay Bet");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step2. Select agent account in any level\n" +
                "3. Click Delay bet icon\n" +
                "4. Input valid delay amount and click on submit button");
        page.searchDownline("invalidloginID", "All", "All");

        log("Verify1. Verify Delay popup display with correct UI");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_022(String brandname) {
        log("@title: Validate drill-down to member level");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(loginAccInfo.getUserID(), downlineLevel, "ACTIVE", brandname).get(0);
        if (Objects.isNull(directDownline)) {
            log("INFO: There is no member under this account");
            return;
        }
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Click Login ID that level is not Member\n" +
                "3. Click until only Member display");
        page.clickUserName(directDownline.getUserCode());
        String actualBreadcrumb = page.getBreadcrumb();

        log("Verify 1. Can drill down to the member level, the breadcrumb is correct\n" +
                "2. Login ID at Member level is not a Link");
        Assert.assertEquals(actualBreadcrumb, String.format("%s\\%s", loginAccInfo.getUserCodeAndLoginID("%s (%s)"), directDownline.getUserCodeAndLoginID("%s (%s)")), "FAILED! Downline Bar is incorrect");
        log("INFO: Executed completely");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search downline with Username
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Input a Username exist indirect/direct downline
     * @expect: 1. Corresponding account display in the list
     */
    @TestRails(id="694")
    @Test(groups = {"smoke"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_006(String brandname) {
        log("@title: Validate can search downline with Username ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        String level = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, level, brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Input a Username exist indirect/direct downline");
        page.searchDownline(loginID, "", "");

        log("Verify 1. Corresponding account display in the list");
        int totalRow = page.tblDowlineListing.getNumberOfRows(false, false);
        List<String> lstRecord = page.tblDowlineListing.getColumn(page.userCodeCol, false);
        Assert.assertEquals(totalRow, 1, String.format("Failed!There are more than 1 records when search login ID %s", loginID));
        Assert.assertEquals(lstRecord.get(0), loginID, String.format("Failed! Expected login id %s display but found %s", loginID, lstRecord.get(0)));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can change password from the table
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *         2. Select agent account in any level
     *         3. Click change password icon
     *         4. Update password
     * @expect: 1. Verify can change password successfully
     */
    @TestRails(id="695")
    @Test (groups = {"smoke"})
    @Parameters({"level","password","brandname"})
    public void Agent_AM_Downline_Listing_020(String level,String password, String brandname) throws Exception {
        log("@title: Validate can change password from the table");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,level,_brandname);
        String loginID =listAccount.get(0).getUserCode();

        log("Step 2. Select agent account in any level");
        log("Step 3. Click change password icon");
        log("Step 4. Update password");
        try {
            String message = page.changePassword(loginID, "1234qwert");
            log("Verify 1. Verify can change password successfully");
            Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_CHANGE_PASSWORD_SUCCESS, "FAILED, Success message is incorrect when updating password");
            log("INFO: Executed completely");
        }finally {
            log("Post Condition: Re-change to old pw");
            page.changePassword(loginID, StringUtils.decrypt(password));
        }


    }

}

