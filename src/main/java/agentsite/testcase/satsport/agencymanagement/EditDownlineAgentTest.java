package agentsite.testcase.satsport.agencymanagement;

import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.pages.all.agentmanagement.EditDownLinePage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.DOWNLINE_LISTING;

public class EditDownlineAgentTest extends BaseCaseMerito {

    @Test(groups = {"http_request"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_Edit_Agent_001(String brandname) {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, ProfileUtils.getDownlineBalanceInfo().get(0).get(0), brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Click on Edit icon of any agent");
        page.clickEditIcon(loginID);

        log("Verify There is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console Error display");

        log("INFO: Executed completely");
    }


    @Test(groups = {"satregression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_Edit_Agent_002(String brandname) {
        log("@title: Validate UI in Edit Downline Agent");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, ProfileUtils.getDownlineBalanceInfo().get(0).get(0), brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);

        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLineAgentPage = (EditDownLinePage) page.clickEditIcon(loginID);

        log("Verify 1. Verify UI in Edit Downline Agent is corrected");
        Assert.assertEquals(page.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.EDIT_DOWNLINE_AGENT_TITLE, "Failed! Page title is incorrect");
        List<String> lstInfo = editDownLineAgentPage.accInfoSection.getListLabelInfo();
        Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
        Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");

        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtPhone.isDisplayed(), "FAILED! Phone textbox does not display");

        log("Verify 2. Cash Balance");
        List<ArrayList<String>> lstBalance = editDownLineAgentPage.creditBalanceSection.tblCashBalance.getRowsWithoutHeader(1, false);
        Assert.assertEquals(editDownLineAgentPage.creditBalanceSection.lblCashBalanceTitle.getText().trim(), AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE, "FAILED! Cash Balance Title display incorrect");

        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(editDownLineAgentPage.lblProductSetting.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING, "FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = editDownLineAgentPage.tblBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = editDownLineAgentPage.tblBetSettings.getColumn(1, false);
        List<String> lstTaxSettingHeader = editDownLineAgentPage.tblTaxSettings.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = editDownLineAgentPage.tblTaxSettings.getColumn(1, false);
        List<String> lstPositionTakingHeader = editDownLineAgentPage.tblPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(editDownLineAgentPage.lblBetSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING, "FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_HEADER, "FAILED! Bet Setting Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION, "FAILED! Bet Setting options in the first column does not display as expected");
        Assert.assertEquals(editDownLineAgentPage.lblTaxSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING, "FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_HEADER, "FAILED! Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION, "FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(editDownLineAgentPage.lblPositionTakingListing.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING, "FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_POSITION_TAKING_HEADER, "FAILED! Position Taking Header does not display as expected");

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(editDownLineAgentPage.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "FAILED! Submit button display incorrect");
        Assert.assertEquals(editDownLineAgentPage.btnCancel.getText(), AGConstant.BTN_CANCEL, "FAILED! Cancel button display incorrect");
        log("INFO: Executed completely");
    }

    @Test(groups = {"satregression"})
    @Parameters({"brandname"})
    public void Agent_AM_Downline_Listing_Edit_Agent_003(String brandname) {
        log("@title: There is no Security Code prompted when access the page");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE",brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any agent");
        page.searchDownline(loginID, "", "Agent");
        page.clickEditIcon(loginID);

        log("Verify 1. Verify there is no Security Code popup prompted");
        Assert.assertFalse(page.securityPopup.isDisplayed(), "FAILED Security popup  display when edit agent");

        log("INFO: Executed completely");
    }


    @Test(groups = {"satregression"})
    @Parameters({"password","brandname"})
    public void Agent_AM_Downline_Listing_Edit_Agent_006(String password, String brandname) throws Exception {
        log("@title:Validate UI when access from the level under SAD");
        log("Step 1. Navigate Agency Management > Downline Listing");

        String userID = ProfileUtils.getProfile().getUserID();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE",brandname);

        agentHomePage.logout();
        loginAgent(sosAgentURL, agentNewAccURL, listAccount.get(0).getUserCodeAndLoginID(), StringUtils.decrypt(password), environment.getSecurityCode());
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        userID = ProfileUtils.getProfile().getUserID();
        downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", brandname);
        String loginID = listAccount.get(0).getUserCode();
        log("Step 2. Click on Edit icon of any agent");
        EditDownLinePage editDownLineAgentPage = (EditDownLinePage) page.clickEditIcon(loginID);

        log("Verify 1. Verify UI at low levels(under SAD) display correctly\n" +
                "- Info, Cash Balance, Rate Setting, Product Settings, Bet Setting Sections");


        log("Verify 1. Verify UI in Edit Downline Agent is corrected");
        Assert.assertEquals(page.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.EDIT_DOWNLINE_AGENT_TITLE, "Failed! Page title is incorrect");
        List<String> lstInfo = editDownLineAgentPage.accInfoSection.getListLabelInfo();
        Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
        Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");

        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
        Assert.assertTrue(editDownLineAgentPage.accInfoSection.txtPhone.isDisplayed(), "FAILED! Phone textbox does not display");

        log("Verify 2. Cash Balance");
        Assert.assertEquals(editDownLineAgentPage.creditBalanceSection.lblCashBalanceTitle.getText().trim(), AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE, "FAILED! Cash Balance Title display incorrect");

        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(editDownLineAgentPage.lblProductSetting.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING, "FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = editDownLineAgentPage.tblBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = editDownLineAgentPage.tblBetSettings.getColumn(1, false);
        List<String> lstPositionTakingHeader = editDownLineAgentPage.tblPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(editDownLineAgentPage.lblBetSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING, "FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_HEADER, "FAILED! Bet Setting Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption.get(0), "Max Win Per Market", "FAILED! Bet Setting options in the first column does not display as expected");
        Assert.assertFalse(editDownLineAgentPage.tblTaxSettings.isDisplayed(), "FAILED! Tax Setting table should not display when login lever under control blocking");
        Assert.assertFalse(editDownLineAgentPage.tblPositionTakingListing.isDisplayed(), "FAILED! Position Taking Listing table should not display when login lever under control blocking");

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(editDownLineAgentPage.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "FAILED! Submit button display incorrect");
        Assert.assertEquals(editDownLineAgentPage.btnCancel.getText(), AGConstant.BTN_CANCEL, "FAILED! Cancel button display incorrect");
        log("INFO: Executed completely");
    }


}

