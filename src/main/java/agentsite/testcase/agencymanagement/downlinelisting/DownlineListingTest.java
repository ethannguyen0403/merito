package agentsite.testcase.agencymanagement.downlinelisting;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.pages.agentmanagement.betsettinglisting.BetSettingListing;
import agentsite.pages.components.SuccessPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.*;

import static common.AGConstant.*;

public class DownlineListingTest extends BaseCaseTest {
    @TestRails(id = "3499")
    @Test(groups = {"http_request"})
    public void Agent_AM_Downline_Listing_Edit_User_3499() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("1. Validate downline listing page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console Error display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3518")
    @Test(groups = {"http_request"})
    @Parameters({"memberAccount"})
    public void Agent_AM_Downline_Listing_Edit_User_3518(String memberAccount) throws Exception {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
//        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(_brandname, memberAccount, userID);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any agent");
        page.downlineListing.clickEditIcon(loginID);

        log("Verify There is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console Error display");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3522")
    @Test(groups = {"http_request"})
    public void Agent_AM_Downline_Listing_Edit_User_3522() throws Exception {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any Member level");
        page.downlineListing.clickEditIcon(loginID);

        log("Verify There is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console Error display");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3500")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3500() {
        log("@title: Validate UI in Downline Listing ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("1. Verify Title is : Downline Listing\n" +
                "2. Control display correctly\n" +
                "3. Root breadcrumb is login ID\n" +
                "4. Account List table display with correct header: No., Login ID, Client Name, Credit Initiation, Account Status, Edit, Change Password, Level, Delay Bet, Downline, Created Date, Last Login Time, Last Login IP\n" +
                "5 Pagingnation section in the bottom");
        page.downlineListing.verifyUIDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "3519")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3519() throws Exception {
        log("@title: Validate UI in Downline Listing ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"PL",_brandname);
//        List<AccountInfo> listAccount = DownLineListingUtils.getAllDownLineUsers(_brandname, memberAccount, userID);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.downlineListing.clickEditIcon(loginID);

        log("1. Verify Title is : Downline Listing\n" +
                "2. Control display correctly\n" +
                "3. Root breadcrumb is login ID\n" +
                "4. Account List table display with correct header\n" +
                "5 Pagingnation section in the bottom");
        editDownLinePage.editDownlineListing.verifyUIDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "3520")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3520() throws Exception {
        log("@title: Validate UI in Downline Listing ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.downlineListing.clickEditIcon(loginID);

        log("1. Validate there is no Security Code popup pormpted");
        Assert.assertFalse(editDownLinePage.securityPopup.isDisplayed(), "FAILED! Security Popup displays");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3521")
    @Test(groups = {"regression_sad"})
    public void Agent_AM_Downline_Listing_3521() throws Exception {
        //run for SAT only
        log("@title: Validate UI when access from the level under SAD ");
        log("Precondition: Log in successfully agent site by SMA level and belong to credit cash line");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.downlineListing.clickEditIcon(loginID);

        log("1. Validate UI at low levels(under SAD) display correctly\n" +
                "Info, Cash Balance, Rate Setting, Product Settings, Bet Setting Sections");
        editDownLinePage.editDownlineListing.verifyUIDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "3523")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3523() throws Exception {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editDownLinePage = page.downlineListing.clickEditIcon(loginID);

        log("Verify 2. Validate UI displays correct");
        editDownLinePage.editDownlineListing.verifyUIDisplayCorrect();

        log("INFO: Executed completely");
    }

    @TestRails(id = "3526")
    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_Downline_Listing_Edit_User_3526() throws Exception {
        log("@title: Validate Credit Initiation and Balance disabled for Credit Cash");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, "PL", _brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editDownLinePage = page.downlineListing.clickEditIcon(loginID);

        log("Verify 2. Validate Credit Initiation and Balance Textbox is disabled");
        Assert.assertFalse(editDownLinePage.cashBalanceInforSection.txtCreditInitiation.isEnabled());
        Assert.assertFalse(editDownLinePage.cashBalanceInforSection.txtFirstTimeDeposit.isEnabled());

        log("INFO: Executed completely");
    }

    @TestRails(id = "3531")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3531() throws Exception {
        log("@title: Validate downline will not display the update is inactive market");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String parentUserID = ProfileUtils.getProfile().getUserID();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(parentUserID, downlineLevel, _brandname);
        String userCode = listAccount.get(0).getUserCode();
        String userId = listAccount.get(0).getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userId,"PL", "", _brandname);
        String downlineUserCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(userCode,"","");

        log("Step 2. Click on Edit icon of direct agent");
        log("Step 3. Select Soccer sport and click edit icon");
        log("Step 4. Uncheck a market (e.g.: Half Time) and submit");
        EditDownLinePage editPage = page.downlineListing.clickEditIcon(userCode);
        editPage.productSettingsSection.productStatusSettingsSection.updateMarket("Soccer", "Match Odds (MATCH_ODDS)", false);
        page.downlineListing.submitEditDownline();
        String message = page.downlineListing.getMessageUpdate(true);

        log("Step 5. Drill down upline of the level in Step 2 and click on Edit icon");
        page.downlineListing.searchDownline("","","");
        log("Step 6. Click Edit icon and observe the market inactive in step 4");
        EditDownLinePage editDownLinePage = page.downlineListing.clickEditIcon("downlineUserCode");
        editDownLinePage.productSettingsSection.productStatusSettingsSection.searchMarketOfSport("Soccer","Match Odds (MATCH_ODDS)");

        log("Verify 6. The market inactive not display for downline");
        Assert.assertTrue(editDownLinePage.productSettingsSection.productStatusSettingsSection.editMarketPopup.lblNoRecordFound.isDisplayed(),"FAILED! Inactive market still display in search result");
        log("Validate Edit Member popup display with the message \"Member was updated successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_MEMBER_SUCCESS, "FAILED! Message update downline is not correct");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3532")
    @Test(groups = {"regression"})
    @Parameters({"currency"})
    public void Agent_AM_Downline_Listing_Edit_User_3532(String currency) throws Exception {
        log("@title: Validate cannot edit User if Min is invalid");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(userCode,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editPage = page.downlineListing.clickEditIcon(userCode);
        List<ArrayList<String>> lstBetSettingValidation = editPage.betSettingInforSection.getBetSettingValidationValueLst(currency);
        String minBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1))-1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        lstBetSetting.add(minBetLst);

        log("Step 3. Input Min bet less the valid value");
        editPage.betSettingInforSection.inputBetSetting(lstBetSetting);

        log("Verified  1. Message \"Min Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID, page.lblErrorMsg.getText()));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3533")
    @Test(groups = {"regression"})
    @Parameters({"currency"})
    public void Agent_AM_Downline_Listing_Edit_User_3533(String currency) throws Exception {
        log("@title: Validate cannot edit User if Max is invalid");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(userCode,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editPage = page.downlineListing.clickEditIcon(userCode);
        List<ArrayList<String>> lstBetSettingValidation = editPage.betSettingInforSection.getBetSettingValidationValueLst(currency);
        String minBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1).replace(",","")));
        String maxBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(1).get(1).replace(",","")) + 1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<String>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);

        log("Step 3. Input Min bet less the valid value");
        editPage.betSettingInforSection.inputBetSetting(lstBetSetting);

        log("Verified  1. Message \"Min Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AgencyManagement.CreateUser.LBL_MAX_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID, page.lblErrorMsg.getText()));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3501")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3501() {
        log("@title: Validate can search direct downline");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = loginAccInfo.getUserID();
//        ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Input direct downline , account status is All, and level is All");
        page.downlineListing.searchDownline(directDownline.getUserCode(), "All", "All");

        log("Verify 1.Account is display in the list");
        int totalRow = page.tblDowlineListing.getNumberOfRows(false, false);
        List<String> lstRecord = page.tblDowlineListing.getColumn(page.userCodeCol, false);
        Assert.assertEquals(totalRow, 1, String.format("Failed!There are more than 1 records when search login ID %s", directDownline.getUserCode()));
        Assert.assertEquals(lstRecord.get(0), directDownline.getUserCode(), String.format("Failed! Expected usser code %s display but found %s", directDownline.getUserCode(), lstRecord.get(0)));
        log("INFO: Executed completely");
    }
    @TestRails(id = "3502")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3502() {
        log("@title: Validate can search indirect downline ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        AccountInfo indirectDownline = DownLineListingUtils.getAllDownLineUsers(_brandname,"", directDownline.getUserID()).get(0);
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Input direct downline , account status is All, and level is All");
        page.downlineListing.searchDownline(indirectDownline.getUserCode(), "All", "All");

        log("Verify 1.Account is display in the list");
        int totalRow = page.tblDowlineListing.getNumberOfRows(false, false);
        List<String> lstRecord = page.tblDowlineListing.getColumn(page.userCodeCol, false);
        Assert.assertEquals(totalRow, 1, String.format("Failed!There are more than 1 records when search login ID %s", indirectDownline.getUserCode()));
        Assert.assertEquals(lstRecord.get(0), indirectDownline.getUserCode(), String.format("Failed! Expected login id %s display but found %s", indirectDownline.getUserCode(), lstRecord.get(0)));

        log("INFO: Executed completely");
    }
    @TestRails(id = "3503")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3503() {
        log("@title: Validate can only search with correctly account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Get the correct account and input the data that missing the last letter");
        page.downlineListing.searchDownline("invalidloginID", "All", "All");

        log("Verify 1. No record found");
        Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3504")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3504() {
        log("@title:Validate can search downline by Active Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2.  Search Active status and click submit button");
        page.downlineListing.searchDownline("", "Active", "All");

        log("Verify1. All account in Active status display\n" +
                "If have no active account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.downlineListing.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Active")), "FAILED! List downline account contain account status not in Active");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }

        log("INFO: Executed completely");
    }
    @TestRails(id = "3505")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3505() {
        log("@title: Validate can search downline by Inactive  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Select Inactive status and click submit button");
        page.downlineListing.searchDownline("", "Inactive", "All");

        log("Verify 1. All account in Inactive status display\n" +
                "If have no Inactive account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.downlineListing.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Inactive")), "FAILED! List downline account contain account status not in Inactive");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }

        log("INFO: Executed completely");
    }
    @TestRails(id = "3506")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3506() {
        log("@title: Validate can search downline by Suspended  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Select Suspended status and click submit button");
        page.downlineListing.searchDownline("", "Suspended", "All");

        log("Verify 1. All account in Suspended status display\n" +
                "If have no Suspended account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.downlineListing.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Suspended")), "FAILED! List downline account contain account status not in Suspended");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }
        log("INFO: Executed completely");
    }
    @TestRails(id = "3507")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3507() {
        log("@title: Validate can search downline by Blocked  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Select Blocked status and click submit button");
        page.downlineListing.searchDownline("", "Blocked", "All");

        log("Verify 1. All account in Blocked status display\n" +
                "If have no Blocked account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.downlineListing.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Blocked")), "FAILED! List downline account contain account status not in Suspended");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }
        log("INFO: Executed completely");
    }
    @TestRails(id = "3508")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3508() {
        log("@title: Validate can search downline by Closed  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step2.  Select Closed status and click submit button");
        page.downlineListing.searchDownline("", "Closed", "All");

        log("Verify 1. All account in Closed status display\n" +
                "If have no Closed account => display message \"No record found\"");
        if (!page.lblNoRecord.isDisplayed()) {
            List<String> lstRecord = page.downlineListing.getAccountStatus();
            Assert.assertTrue(lstRecord.containsAll(Collections.singleton("Closed")), "FAILED! List downline account contain account status not in Suspended");
        } else {
            Assert.assertEquals(page.lblNoRecord.getText(), NO_RECORD_FOUND, "FAILED! No record message is incorrect displayed when searching downline with incorrect login ID");
        }

        log("INFO: Executed completely");
    }
    @TestRails(id = "3509")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3509() {
        log("@title: Validate can inactive member account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Select member account \n" +
                "3. Update an active account to inactive status\n" +
                "4. Click save icon next to the drop box");
        page.downlineListing.searchDownline(userCode, "All", "All");
        try {
            SuccessPopup popup = page.downlineListing.updateAccountStatus(userCode, "Inactive");

            log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                    "2. Click OK button => the popup is disappear\n" +
                    "3. Verify Account status Inactive\n");
            Assert.assertEquals(popup.getContentMessage(), "Update is successful!");
            popup.close();

            Assert.assertFalse(popup.isDisplayed(), "FAILED! Confirm popup is not disappear when closed");
            Assert.assertTrue(page.downlineListing.isAccountStatusCorrect(userCode, "Inactive"), "FAILED! Status is incorrect display");
        } finally {
            log("Postcondition change status to active");
            page.downlineListing.updateAccountStatus(userCode, "Active").close();

            log("INFO: Executed completely");
        }

    }
    @TestRails(id = "3510")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3510() {
        log("@title:Validate can suspend  account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Select member account \n" +
                "3. Update an active account to suspended status\n" +
                "4. Click save icon next to the drop box");
        page.downlineListing.searchDownline(userCode, "All", "All");
        try {
            SuccessPopup popup = page.downlineListing.updateAccountStatus(userCode, "Suspended");
            log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                    "2. Click OK button => the popup is disappear\n" +
                    "3. Verify Account status Suspended\n ");
            Assert.assertEquals(popup.getContentMessage(), "Update is successful!");
            popup.close();
            Assert.assertFalse(popup.isDisplayed(), "FAILED! Confirm popup is not disappear when closed");
            Assert.assertTrue(page.downlineListing.isAccountStatusCorrect(userCode, "Suspended"), "FAILED! Status is incorrect display");
        } finally {

            log("Postcondition change status to active");
            page.downlineListing.updateAccountStatus(userCode, "Active").close();
            log("INFO: Executed completely");
        }
    }
    @TestRails(id = "3513")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3513() {
        log("@title: Validate can close an account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Select member account in any status\n" +
                "3. Close the account");
        page.downlineListing.searchDownline(userCode, "All", "All");
        SuccessPopup popup = page.downlineListing.updateAccountStatus(userCode, "Closed");

        log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                "2. Click OK button => the popup is disappear\n" +
                "3. Verify Account status Closed and readable. Save button is hidden\n");
        Assert.assertEquals(popup.getContentMessage(), "Update is successful!");
        popup.close();
        Assert.assertFalse(popup.isDisplayed(), "FAILED! Confirm popup is not disappear when closed");
        Assert.assertTrue(page.downlineListing.isAccountStatusCorrect(userCode, "Closed"), "FAILED! Status is incorrect display");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3511")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3511() {
        log("@title: Validate can active account from inactive status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Select member account that inactivated\n");
        page.downlineListing.searchDownline(userCode, "All", "All");
        page.downlineListing.updateAccountStatus(userCode, "Inactive").close();

        try {
            log("Step 3 Active the account");
            page.downlineListing.updateAccountStatus(userCode, "Active").close();
            log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                    "2. Click OK button => the popup is disappear\n" +
                    "3. Verify Account status Active\n");
            Assert.assertTrue(page.downlineListing.isAccountStatusCorrect(userCode, "Active"), "FAILED! Status is incorrect display");
        } finally {
            log("Postcondition change status to active");
            page.downlineListing.updateAccountStatus(userCode, "Active").close();
            log("INFO: Executed completely");
        }



    }
    @TestRails(id = "3512")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3512() {
        log("@title: Validate can active account from suspend status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String userID = loginAccInfo.getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Search an account");
        page.downlineListing.searchDownline(userCode, "All", "All");
        try {
            log("Step 3 Suspend account");
            page.downlineListing.updateAccountStatus(userCode, "Suspended").close();

            log("Step 3 Active the account");
            page.downlineListing.updateAccountStatus(userCode, "Active").close();

            log("Verify 1. Verify Account status Active");
            Assert.assertTrue(page.downlineListing.isAccountStatusCorrect(userCode, "Active"), "FAILED! Status is incorrect display");
        } finally {
            log("Postcondition change status to active");
            page.downlineListing.updateAccountStatus(userCode, "Active").close();
            log("INFO: Executed completely");
        }
    }
    @TestRails(id = "3514")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3514() {
        log("@title: Validate can not active close an account");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "Closed", _brandname).get(0);
        String userCode = directDownline.getUserCode();
        if(userCode.isEmpty()) {
            throw new SkipException("Have no closed account for testing");
        }
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Search an account");
        page.downlineListing.searchDownline(userCode, "All", "All");
//        log("Step 3 Closed account");
//        page.downlineListing.updateAccountStatus(userCode, "Closed").close();

        log("Verify 1. Verify cannot active the closed account");
        Assert.assertFalse(page.downlineListing.getAccountStatusDropdown(userCode).isEnabled(), "FAILED! Account is closed should disable Account status dropdwon");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3515")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3515() {
        log("@title: Validate can inactive an agent");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        String userCode = directDownline.getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Select agent account in any level\n" +
                "3. Update to Inactive status\n");
        page.downlineListing.searchDownline(userCode, "All", "Agent");

        try {
            log("Step 3 Inactive agent account");
            page.downlineListing.updateAccountStatus(userCode, "Inactive").close();

            log("Verify 1. Verify status is change to Inactive");
            Assert.assertTrue(page.downlineListing.isAccountStatusCorrect(userCode, "Inactive"), "FAILED! Status is incorrect display");
        } finally {
            log("Postcondition change status to active");
            page.downlineListing.updateAccountStatus(userCode, "Active").close();
            log("INFO: Executed completely");
        }


    }
    @TestRails(id = "3516")
    @Test(groups = {"regression_newui"})
    public void Agent_AM_Downline_Listing_3516() {
        log("@title:Validate can open and update Delay Bet");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step2. Select agent account in any level\n" +
                "3. Click Delay bet icon\n" +
                "4. Input valid delay amount and click on submit button");
        page.downlineListing.searchDownline("invalidloginID", "All", "All");

        log("Verify1. Verify Delay popup display with correct UI");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3517")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3517() {
        log("@title: Validate drill-down to member level");
        log("Step 1. Navigate Agency Management > Downline Listing");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(loginAccInfo.getUserID(), downlineLevel, "ACTIVE", _brandname).get(0);
        if (Objects.isNull(directDownline)) {
            log("INFO: There is no member under this account");
            return;
        }
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click Login ID that level is not Member\n" +
                "3. Click until only Member display");
        page.downlineListing.clickUserName(directDownline.getUserCode());
        String actualBreadcrumb = page.downlineListing.getBreadcrumb();

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
    @TestRails(id = "694")
    @Test(groups = {"smoke"})
    public void Agent_AM_Downline_Listing_694() {
        log("@title: Validate can search downline with Username ");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        String level = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, level, _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Input a Username exist indirect/direct downline");
        page.downlineListing.searchDownline(loginID, "", "");

        log("Verify 1. Corresponding account display in the list");
        int totalRow = page.tblDowlineListing.getNumberOfRows(false, false);
        List<String> lstRecord = page.tblDowlineListing.getColumn(page.userCodeCol, false);
        Assert.assertEquals(totalRow, 1, String.format("Failed!There are more than 1 records when search login ID %s", loginID));
        Assert.assertEquals(lstRecord.get(0), loginID, String.format("Failed! Expected login id %s display but found %s", loginID, lstRecord.get(0)));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can change password from the table
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Select agent account in any level
     * 3. Click change password icon
     * 4. Update password
     * @expect: 1. Verify can change password successfully
     */
    @TestRails(id = "695")
    @Test(groups = {"smoke"})
    @Parameters({"level", "password"})
    public void Agent_AM_Downline_Listing_695(String level, String password) throws Exception {
        log("@title: Validate can change password from the table");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, level, _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Select agent account in any level");
        log("Step 3. Click change password icon");
        log("Step 4. Update password");
        try {
            String message = page.downlineListing.changePassword(loginID, "1234qwert");
            log("Verify 1. Verify can change password successfully");
            Assert.assertEquals(message, AgencyManagement.DownlineListing.MSG_CHANGE_PASSWORD_SUCCESS, "FAILED, Success message is incorrect when updating password");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Re-change to old pw");
            page.downlineListing.changePassword(loginID, StringUtils.decrypt(password));
        }


    }

}

