package agentsite.testcase.agencymanagement.downlinelisting;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
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

import java.text.NumberFormat;
import java.util.*;

import static common.AGConstant.*;
import static common.AGConstant.AgencyManagement.CreateDownlineAgent.LBL_ACCOUNT_TRANSFER_WEEKLY_INVALID;

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
        page.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any agent");
        page.clickEditIcon(loginID);

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
        page.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any Member level");
        page.clickEditIcon(loginID);

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
        page.verifyUIDisplayCorrect();
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
        page.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

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
        page.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

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
        page.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

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
        page.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

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
        page.searchDownline(loginID,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editDownLinePage = page.clickEditIcon(loginID);

        log("Verify 2. Validate Credit Initiation and Balance Textbox is disabled");
        Assert.assertFalse(editDownLinePage.cashBalanceInforSection.txtCreditInitiation.isEnabled());
        Assert.assertFalse(editDownLinePage.cashBalanceInforSection.txtFirstTimeDeposit.isEnabled());

        log("INFO: Executed completely");
    }

    @TestRails(id = "3527")
//    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_Downline_Listing_Edit_User_3527() throws Exception {
        //TODO: implement test this case
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
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(listAccount.get(0).getUserID(), "", _brandname);
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode,"","");

        log("Step 2. Click on Edit icon of direct agent");
        log("Step 3. Select Soccer sport and click edit icon");
        log("Step 4. Uncheck a market (e.g.: Half Time) and submit");
        EditDownLinePage editPage = page.clickEditIcon(userCode);
        editPage.productStatusSettingInforSection.updateMarket("Soccer", "Match Odds (MATCH_ODDS)", false);
        page.submitEditDownline();
        String message = page.getMessageUpdate(true);

        log("Step 5. Drill down downline of the level in Step 2 and click on Edit icon");
        page.searchDownline(listAccountDownline.get(0).getUserCode(),"","");
        log("Step 6. Click Edit icon and observe the market inactive in step 4");
        EditDownLinePage editDownLinePage = page.clickEditIcon(listAccountDownline.get(0).getUserCode());
        editDownLinePage.productStatusSettingInforSection.searchMarketOfSport("Soccer","Match Odds (MATCH_ODDS)");

        log("Verify 6. The market inactive not display for downline");
        Assert.assertTrue(editDownLinePage.productStatusSettingInforSection.editMarketPopup.lblNoRecordFound.isDisplayed(),"FAILED! Inactive market still display in search result");
        log("Validate Edit Member popup display with the message \"Member was updated successfully\"");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_DOWNLINE_SUCCESS, "FAILED! Message update downline is not correct");

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
        page.searchDownline(userCode,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editPage = page.clickEditIcon(userCode);
        List<ArrayList<String>> lstBetSettingValidation = editPage.betSettingInforSection.getBetSettingValidationValueLst(currency);
        String minBet =Double.toString(Double.parseDouble(lstBetSettingValidation.get(0).get(1))-1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        lstBetSetting.add(minBetLst);

        log("Step 3. Input Min bet less than the valid value");
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
        page.searchDownline(userCode,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editPage = page.clickEditIcon(userCode);
        List<ArrayList<String>> lstBetSettingValidation = editPage.betSettingInforSection.getBetSettingValidationValueLst(currency);
        NumberFormat nf = NumberFormat.getInstance();
        String minBet = String.valueOf(nf.parse(lstBetSettingValidation.get(0).get(1)).intValue());
        String maxBet = String.valueOf(nf.parse(lstBetSettingValidation.get(1).get(1)).intValue() + 1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<String>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);

        log("Step 3. Input Max bet greater than the valid value");
        editPage.betSettingInforSection.inputBetSetting(lstBetSetting);

        log("Verified  1. Message \"Max Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AgencyManagement.CreateUser.LBL_MAX_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MAX_INVALID, page.lblErrorMsg.getText()));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3534")
    @Test(groups = {"regression"})
    @Parameters({"currency"})
    public void Agent_AM_Downline_Listing_Edit_User_3534(String currency) throws Exception {
        log("@title: Validate cannot edit User if Max Liability Per Market is invalid");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editPage = page.clickEditIcon(userCode);
        List<ArrayList<String>> lstBetSettingValidation = editPage.betSettingInforSection.getBetSettingValidationValueLst(currency);

        NumberFormat nf = NumberFormat.getInstance();
        String minBet = String.valueOf(nf.parse(lstBetSettingValidation.get(0).get(1)).intValue());
        String maxBet = String.valueOf(nf.parse(lstBetSettingValidation.get(1).get(1)).intValue());
        String maxLiability = String.valueOf(nf.parse(lstBetSettingValidation.get(2).get(1)).intValue() + 1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        ArrayList<String> maxLiabilityLst = new ArrayList<>(
                Arrays.asList(maxLiability, maxLiability, maxLiability, maxLiability, maxLiability, maxLiability));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);
        lstBetSetting.add(maxLiabilityLst);

        log("Step 3. Input Max Liability bet greater than the valid value");
        editPage.betSettingInforSection.inputBetSetting(lstBetSetting);

        log("Verified  1. Message \"Max Liability Per Market is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AgencyManagement.CreateUser.LBL_MAX_LIABILITY_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MAX_LIABILITY_INVALID, page.lblErrorMsg.getText()));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3535")
    @Test(groups = {"regression"})
    @Parameters({"currency"})
    public void Agent_AM_Downline_Listing_Edit_User_3535(String currency) throws Exception {
        log("@title: Validate cannot edit User if  Max Win Per Market  is invalid");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID,"PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode,"","");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editPage = page.clickEditIcon(userCode);
        List<ArrayList<String>> lstBetSettingValidation = editPage.betSettingInforSection.getBetSettingValidationValueLst(currency);

        NumberFormat nf = NumberFormat.getInstance();
        String minBet = String.valueOf(nf.parse(lstBetSettingValidation.get(0).get(1)).intValue());
        String maxBet = String.valueOf(nf.parse(lstBetSettingValidation.get(1).get(1)).intValue());
        String maxLiability = String.valueOf(nf.parse(lstBetSettingValidation.get(2).get(1)).intValue());
        String maxWin = String.valueOf(nf.parse(lstBetSettingValidation.get(3).get(1)).intValue() + 1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        ArrayList<String> maxLiabilityLst = new ArrayList<>(
                Arrays.asList(maxLiability, maxLiability, maxLiability, maxLiability, maxLiability, maxLiability));
        ArrayList<String> maxWinLst = new ArrayList<>(
                Arrays.asList(maxWin, maxWin, maxWin, maxWin, maxWin, maxWin));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);
        lstBetSetting.add(maxLiabilityLst);
        lstBetSetting.add(maxWinLst);

        log("Step 3. Input Max Liability bet greater than the valid value");
        editPage.betSettingInforSection.inputBetSetting(lstBetSetting);

        log("Verified  1. Message \"Max Win Per Market is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AgencyManagement.CreateUser.LBL_MAX_WIN_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MAX_WIN_INVALID, page.lblErrorMsg.getText()));
        log("INFO: Executed completely");
    }
    @TestRails(id = "3536")
//    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_Downline_Listing_Edit_User_3536() throws Exception {
        //TODO: implement test this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "3539")
//    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_Downline_Listing_Edit_User_3539() throws Exception {
        //TODO: implement test this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "3540")
//    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_Downline_Listing_Edit_User_3540() throws Exception {
        //TODO: implement test this case
        log("INFO: Executed completely");
    }
    @TestRails(id = "3541")
//    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_Downline_Listing_Edit_User_3541() throws Exception {
        //TODO: implement test this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "3545")
//    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_Downline_Listing_Edit_User_3545() throws Exception {
        //TODO: implement test this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "3537")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3537() throws Exception {
        log("@title: Validate cannot edit User if input invalid password format");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String password = "p@ssword";
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editPage = page.clickEditIcon(userCode);
        log("Step 3. Input invalid password format");
        editPage.editDownlineListing.inputInfoSection(password,"","","","","","",true);

        log("Verified 3. Message \"Password is invalid.\" display next to Cancel button");
        Assert.assertTrue(page.lblErrorMsg.getText().contains(AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID),String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3538")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3538() throws Exception {
        log("@title: Validate edit User if input valid info: First Name, Last Name, Mobile");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String firstName = StringUtils.generateAlphabetic( 10);
        String lastName = StringUtils.generateAlphabetic( 10);
        String mobile = StringUtils.generateNumeric(10);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon of any Member level");
        EditDownLinePage editPage = page.clickEditIcon(userCode);
        log("Step 3. Input valid info of all fields and click Submit button");
        editPage.editDownlineListing.inputInfoSection("","",firstName,lastName,"",mobile,"",true);

        log("Verified 3. User info is updated with new data");
        page.closeSubmitEditDownlinePopup();
        page.clickEditIcon(userCode);
        Assert.assertEquals(editPage.accountInforSection.txtFirstName.getAttribute("value"), firstName, String.format("FAILED! First Name displays incorrect actual %s and expected %s", editPage.accountInforSection.txtFirstName.getText(), firstName));
        Assert.assertEquals(editPage.accountInforSection.txtLastName.getAttribute("value"), lastName, String.format("FAILED! First Name displays incorrect actual %s and expected %s", editPage.accountInforSection.txtLastName.getText(), lastName));
        Assert.assertEquals(editPage.accountInforSection.txtMobile.getAttribute("value"), mobile, String.format("FAILED! First Name displays incorrect actual %s and expected %s", editPage.accountInforSection.txtMobile.getText(), mobile));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3542")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_Edit_User_3542() throws Exception {
        log("@title: Validate there is no Security popup display when click Edit User");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon of any Member level");
        page.clickEditIcon(userCode);

        log("Verified 1. Validate there is no security popup display");
        Assert.assertFalse(page.successPopup.isDisplayed(), "FAILED! Security Popup is displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3543")
    @Test(groups = {"regression_ag"})
    public void Agent_AM_Downline_Listing_Edit_User_3543() throws Exception {
        //Run for SAT with level under SAD only
        log("@title: Validate Edit User UI when login from low level");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);

        log("Verified 1. Validate Edit User UI display correctly when view from SMA level:\n" +
                "-Info section\n" +
                "\n" +
                "Cash Balance section\n" +
                "Product Settings\n" +
                "Bet Settings\n" +
                "There is no Position Taking Setting section");
        Assert.assertTrue(editDownlinePage.accountInforSection.txtPassword.isDisplayed(), "FAILED! Account Info Section is not displayed");
        Assert.assertEquals(page.cashBalanceInforSection.getCashSectionTitle(),AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(page.productStatusSettingInforSection.getProductSettingSectionTitle(),AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");
        Assert.assertEquals(page.betSettingInforSection.getBetSettingSectionTitle(AGConstant.EXCHANGE),AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section display incorrect");
        page.positionTakingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3551")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3551() throws Exception {
        log("@title: Validate UI when open Edit Market popup");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);
        log("Step 3. In product Setting Section, click on any edit button of any Sport");
        editDownlinePage.productStatusSettingInforSection.searchMarketOfSport("Soccer","Test");

        log("Verify 3. Validate Edit Market popup display with the title \"Edit Market for [Sport]\"\n" +
                "2 Validate UI on Edit Market Popup:\n" +
                "\n" +
                "Search Market labels and textbox\n" +
                "Market list and check box\n" +
                "OK and Cancel popup");
        Assert.assertEquals(editDownlinePage.productStatusSettingInforSection.editMarketPopup.lblTitle.getText(), String.format(AgencyManagement.DownlineListing.EDIT_MARKET_TITLE, "Soccer"), "FAILED! Edit Market popup title displays incorrectly");
        Assert.assertTrue(editDownlinePage.productStatusSettingInforSection.editMarketPopup.cbAllMarket.isDisplayed(), "FAILED! Check all market checkbox does not display");
        Assert.assertTrue(editDownlinePage.productStatusSettingInforSection.editMarketPopup.btnOK.isDisplayed(), "FAILED! Button OK does not display");
        Assert.assertTrue(editDownlinePage.productStatusSettingInforSection.editMarketPopup.btnCancel.isDisplayed(), "FAILED! Button Cancel does not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3552")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3552() throws Exception {
        log("@title: Validate search correct Market works");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);
        log("Step 3. In product Setting Section, click on any edit button of any Sport");
        log("Step 4. Input a correct market and press Enter");
        editDownlinePage.productStatusSettingInforSection.searchMarketOfSport("Soccer","Match Odds (MATCH_ODDS)");

        log("Verify 3. Validate the correct market  display");
        List<String> columnMarket = editDownlinePage.productStatusSettingInforSection.editMarketPopup.tblMarket.getColumn(1, false);
        Assert.assertTrue(columnMarket.get(0).equalsIgnoreCase("Match Odds (MATCH_ODDS)"), String.format("FAILED! Search market does not display correct expected %s and actual %s","Match Odds (MATCH_ODDS)",columnMarket.get(0)));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3553")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3553() throws Exception {
        log("@title: Validate can search all the market have same prefix");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);
        log("Step 3. In product Setting Section, click on any edit button of any Sport");
        log("Step 4. Input a prefix of market (e.g.: Over) and press Enter");
        editDownlinePage.productStatusSettingInforSection.searchMarketOfSport("Soccer","Over");

        log("Verify 3. Validate the list markets have prefix = Over are  displayed");
        List<String> columnMarket = editDownlinePage.productStatusSettingInforSection.editMarketPopup.tblMarket.getColumn(1,false);
        Assert.assertTrue(editDownlinePage.productStatusSettingInforSection.editMarketPopup.isResultContainsPrefixMarket("Over", columnMarket), "FAILED! List result contains market having different prefix");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3554")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3554() throws Exception {
        log("@title: Validate no result display when search with invalid market");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);
        log("Step 3. In product Setting Section, click on any edit button of any Sport");
        log("Step 4. Input a invalid market and press Enter");
        editDownlinePage.productStatusSettingInforSection.searchMarketOfSport("Soccer","invalid market");

        log("Verify 3. Validate message \"No record found\" display");
        Assert.assertTrue(editDownlinePage.productStatusSettingInforSection.editMarketPopup.lblNoRecordFound.isDisplayed(), "FAILED! No record found does not displays when search invalid market");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3555")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3555() throws Exception {
        log("@title: Validate no result display when search with invalid market");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);
        log("Step 3. In product Setting Section, click on any edit button of any Sport");
        editDownlinePage.productStatusSettingInforSection.openEditMarketOfSport("Soccer");
        log("Step 4. On the list markets mark them is checked");
        editDownlinePage.productStatusSettingInforSection.editMarketPopup.clickCheckboxAllMarket(true);

        log("Verify 4. Validate all market checkbox is checked");
        Assert.assertTrue(editDownlinePage.productStatusSettingInforSection.editMarketPopup.isAllMarketChecked(), "FAILED! All markets checkbox is not checked");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3556")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3556() throws Exception {
        log("@title: Validate all market checkboxes is unchecked when all markets is unchecked");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);
        log("Step 3. In product Setting Section, click on any edit button of any Sport");
        editDownlinePage.productStatusSettingInforSection.openEditMarketOfSport("Soccer");
        log("Step 4. On the list markets mark them is unchecked");
        editDownlinePage.productStatusSettingInforSection.editMarketPopup.clickCheckboxAllMarket(false);

        log("Verify 4. Validate all market checkbox is unchecked");
        Assert.assertTrue(editDownlinePage.productStatusSettingInforSection.editMarketPopup.isAllMarketUnChecked(), "FAILED! All markets checkbox is not unchecked");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3557")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3557() throws Exception {
        log("@title: Validate cancel button work");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);
        log("Step 3. In product Setting Section, click on any edit button of any Sport");
        editDownlinePage.productStatusSettingInforSection.openEditMarketOfSport("Soccer");
        log("Step 4. Click on Cancel button");
        editDownlinePage.productStatusSettingInforSection.editMarketPopup.btnCancel.click();

        log("Verify 4. Validate Market popup is closed");
        Assert.assertFalse(editDownlinePage.productStatusSettingInforSection.editMarketPopup.isDisplayed(), "FAILED! Edit market popup is not closed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3558")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_Edit_User_3558() throws Exception {
        log("@title: Validate close popup icon work");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname);
        String userCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(userCode, "", "");

        log("Step 2. Click on Edit icon for member level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(userCode);
        log("Step 3. In product Setting Section, click on any edit button of any Sport");
        editDownlinePage.productStatusSettingInforSection.openEditMarketOfSport("Soccer");
        log("Step 4. Click on X button of the popup");
        editDownlinePage.productStatusSettingInforSection.editMarketPopup.iconClose.click();

        log("Verify 4. Validate Market popup is closed");
        Assert.assertFalse(editDownlinePage.productStatusSettingInforSection.editMarketPopup.isDisplayed(), "FAILED! Edit market popup is not closed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3559")
    @Test(groups = {"regression_credit"})
    public void Agent_AM_Downline_Listing_Edit_User_3559() throws Exception {
        log("@title: Validate Account Balance Transfer Condition display when edit for an agent account in any level");
        log("Precondition: Log in successfully Credit line");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String userName = ProfileUtils.getProfile().getUserCode();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getAllDownLineUsers(_brandname, userName, userID);
        String downlineUserCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(downlineUserCode, "", "");

        log("Step 2. Click on Edit icon of any agent level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(downlineUserCode);

        log("Verify 2. Validate Control in Account Balance Transfer Condition display");
        Assert.assertEquals(editDownlinePage.accountBalanceTransferConditionInforSection.getAccountBalanceTransferConditionTitle(),
                "Account Balance Transfer Condition", "FAILED! Account Balance Transfer Condition section is not displayed");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3560")
    @Test(groups = {"regression_credit"})
    public void Agent_AM_Downline_Listing_Edit_User_3560() throws Exception {
        log("@title: Validate Account Balance Transfer Condition NOT display when edit for player account");
        log("Precondition: Log in successfully Credit line");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getDownLineUsers(userID, "PL",_brandname);
        String downlineUserCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(downlineUserCode, "", "");

        log("Step 2. Click on Edit icon of any agent level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(downlineUserCode);

        log("Verify 2. Validate there is no section Account Balance Transfer Condition");
        Assert.assertEquals(editDownlinePage.accountBalanceTransferConditionInforSection.getAccountBalanceTransferConditionTitle(),
                "", "FAILED! Account Balance Transfer Condition section is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3563")
    @Test(groups = {"regression_credit"})
    public void Agent_AM_Downline_Listing_Edit_User_3563() throws Exception {
        log("@title: Validate An error message display when Select Weekly but not select day in Account Balance Transfer Condition");
        log("Precondition: Log in successfully Credit line");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        String userName = ProfileUtils.getProfile().getUserCode();
        List<AccountInfo> listAccountDownline = DownLineListingUtils.getAllDownLineUsers(_brandname, userName, userID);
        String downlineUserCode = listAccountDownline.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        page.searchDownline(downlineUserCode, "", "");

        log("Step 2. Click on Edit icon of any agent level");
        EditDownLinePage editDownlinePage = page.clickEditIcon(downlineUserCode);

        log("Step 3. In Account Balance Transfer Condition, Select Weekly and not select any day then click Submit button");
        editDownlinePage.accountBalanceTransferConditionInforSection.setTransferPeriod(false, Collections.singletonList(""));

        log("Verify 2. Validate Error message display \"Please select at least one day when Account balance transfer is Weekly.");
        Assert.assertEquals(editDownlinePage.lblErrorMsg.getText(),
                LBL_ACCOUNT_TRANSFER_WEEKLY_INVALID, "FAILED! Account Balance Transfer Condition section is not displayed");
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
        page.searchDownline(directDownline.getUserCode(), "All", "All");

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
        page.searchDownline(indirectDownline.getUserCode(), "All", "All");

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
        page.searchDownline("invalidloginID", "All", "All");

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
    @TestRails(id = "3505")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3505() {
        log("@title: Validate can search downline by Inactive  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

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
    @TestRails(id = "3506")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3506() {
        log("@title: Validate can search downline by Suspended  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

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
    @TestRails(id = "3507")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3507() {
        log("@title: Validate can search downline by Blocked  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

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
    @TestRails(id = "3508")
    @Test(groups = {"regression"})
    public void Agent_AM_Downline_Listing_3508() {
        log("@title: Validate can search downline by Closed  Status");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

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
        page.searchDownline(userCode, "All", "All");
        try {
            SuccessPopup popup = page.updateAccountStatus(userCode, "Inactive");

            log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                    "2. Click OK button => the popup is disappear\n" +
                    "3. Verify Account status Inactive\n");
            Assert.assertEquals(popup.getContentMessage(), "Update is successful!");
            popup.close();

            Assert.assertFalse(popup.isDisplayed(), "FAILED! Confirm popup is not disappear when closed");
            Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Inactive"), "FAILED! Status is incorrect display");
        } finally {
            log("Postcondition change status to active");
            page.updateAccountStatus(userCode, "Active").close();

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
        page.searchDownline(userCode, "All", "All");
        try {
            SuccessPopup popup = page.updateAccountStatus(userCode, "Suspended");
            log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                    "2. Click OK button => the popup is disappear\n" +
                    "3. Verify Account status Suspended\n ");
            Assert.assertEquals(popup.getContentMessage(), "Update is successful!");
            popup.close();
            Assert.assertFalse(popup.isDisplayed(), "FAILED! Confirm popup is not disappear when closed");
            Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Suspended"), "FAILED! Status is incorrect display");
        } finally {

            log("Postcondition change status to active");
            page.updateAccountStatus(userCode, "Active").close();
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
        page.searchDownline(userCode, "All", "All");
        page.updateAccountStatus(userCode, "Inactive").close();

        try {
            log("Step 3 Active the account");
            page.updateAccountStatus(userCode, "Active").close();
            log("Verify 1. Update popup display with the message \"Update is successful!\"\n" +
                    "2. Click OK button => the popup is disappear\n" +
                    "3. Verify Account status Active\n");
            Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Active"), "FAILED! Status is incorrect display");
        } finally {
            log("Postcondition change status to active");
            page.updateAccountStatus(userCode, "Active").close();
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
        page.searchDownline(userCode, "All", "All");
        try {
            log("Step 3 Suspend account");
            page.updateAccountStatus(userCode, "Suspended").close();

            log("Step 3 Active the account");
            page.updateAccountStatus(userCode, "Active").close();

            log("Verify 1. Verify Account status Active");
            Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Active"), "FAILED! Status is incorrect display");
        } finally {
            log("Postcondition change status to active");
            page.updateAccountStatus(userCode, "Active").close();
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
        page.searchDownline(userCode, "All", "All");
//        log("Step 3 Closed account");
//        page.updateAccountStatus(userCode, "Closed").close();

        log("Verify 1. Verify cannot active the closed account");
        Assert.assertFalse(page.getAccountStatusDropdown(userCode).isEnabled(), "FAILED! Account is closed should disable Account status dropdwon");

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
        page.searchDownline(userCode, "All", "Agent");

        try {
            log("Step 3 Inactive agent account");
            page.updateAccountStatus(userCode, "Inactive").close();

            log("Verify 1. Verify status is change to Inactive");
            Assert.assertTrue(page.isAccountStatusCorrect(userCode, "Inactive"), "FAILED! Status is incorrect display");
        } finally {
            log("Postcondition change status to active");
            page.updateAccountStatus(userCode, "Active").close();
            log("INFO: Executed completely");
        }


    }
    @TestRails(id = "3516")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_Downline_Listing_3516() {
        log("@title:Validate can open and update Delay Bet");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step2. Select agent account in any level\n" +
                "3. Click Delay bet icon\n" +
                "4. Input valid delay amount and click on submit button");
        page.searchDownline("invalidloginID", "All", "All");

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
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Select agent account in any level
     * 3. Click change password icon
     * 4. Update password
     * @expect: 1. Verify can change password successfully
     */
    @TestRails(id = "695")
    @Test(groups = {"smoke"})
    @Parameters({"password"})
    public void Agent_AM_Downline_Listing_695(String password) throws Exception {
        log("@title: Validate can change password from the table");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, _brandname);
        String loginID = listAccount.get(1).getUserCode();

        log("Step 2. Select agent account in any level");
        log("Step 3. Click change password icon");
        log("Step 4. Update password");
        try {
            String message = page.changePassword(loginID, "1234qwert");
            log("Verify 1. Verify can change password successfully");
            Assert.assertEquals(message, AgencyManagement.DownlineListing.MSG_CHANGE_PASSWORD_SUCCESS, "FAILED, Success message is incorrect when updating password");
            log("INFO: Executed completely");
        } finally {
            log("Post Condition: Re-change to old pw");
            page.changePassword(loginID, StringUtils.decrypt(password));
        }

    }

}

