package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import common.AGConstant;
import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static common.AGConstant.HomePage.CREATE_DOWNLINE_AGENT;

public class CreateDownlineAgentTest extends BaseCaseTest {
    @TestRails(id = "678")
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_678() {
        log("@title: Validate UI in Create Downline Agent with Exchange Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Verify 1. Account info section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.CreateDownlineAgent.TITLE_PAGE, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();

        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(page.lblProductSetting.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        Assert.assertEquals(page.lblBetSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        page.betSettingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);

        Assert.assertEquals(page.lblTaxSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        page.taxSettingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);

        Assert.assertEquals(page.lblPositionTakingListing.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        page.positionTakingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(page.getSubmitBtn().getText(), AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(page.getBtnCancel().getText(), AGConstant.BTN_CANCEL,"FAILED! Cancel button display incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI in Create Downline Agent with Exchange Game Product setting
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Create Downline Agent
     *          2. Select Exchange Game Tab
     * @expect: 1. Account info section
     *          3 Rate Setting
     *          4. Product Setting, select Exchange Game product
     *          5 Verify Bet Settings, Tax Setting. Position Taking Setting
     *          6. Submit and Cancel button
     */
    @TestRails(id = "679")
    @Test (groups = {"smoke_creditcash"})
    public void Agent_AM_CreateDownline_Agent_679() {
        log("@title: Validate UI in Create Downline Agent with Exchange Game Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        log("Step 2. Enter Security code");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Step 2. Select Exchange Game Tab");
        page.tabExchangeGames.click();

        log("Verify 1. Account info section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.CreateDownlineAgent.TITLE_PAGE, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();

        log("Verify 2. Cash Balance");
        List<ArrayList<String>> lstBalance = page.cashBalanceInforSection.tblCashBalance.getRowsWithoutHeader(1,false);
        Assert.assertEquals(page.cashBalanceInforSection.getCashSectionTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(lstBalance.get(0).get(0), AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_INITIATION,"FAILED! Credit Initiation label displays incorrect");

        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(page.lblProductSetting.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = page.tblEGBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = page.tblEGBetSettings.getColumn(1,false);
        List<String> lstTaxSettingHeader = page.tblEGTaxSettings.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = page.tblEGTaxSettings.getColumn(1,false);
        List<String> lstPositionTakingHeader = page.tblEGPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(page.lblEGBetSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GROUP_HEADER,"FAILED! Exchange Game Bet Setting  Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION,"FAILED! Bet Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblEGTaxSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GROUP_HEADER,"FAILED! Exchange Game Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblEGPositionTakingListing.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GROUP_HEADER,"FAILED! Exchange Game Position Taking Header does not display as expected");

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(page.getSubmitBtn().getText(), AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(page.getBtnCancel().getText(), AGConstant.BTN_CANCEL,"FAILED! Cancel button display incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "681")
    @Test (groups = {"smoke_creditcash"})
    public void Agent_AM_CreateDownline_Agent_681() {
        log("@title: Validate display Cash Balance for Cash account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Verify 1.Credit Cash Balance section display");
//        List<ArrayList<String>> lstBalance = page.cashBalanceSection.tblCashBalance.getRowsWithoutHeader(2,false);
        Assert.assertEquals(page.cashBalanceInforSection.getCashSectionTitle(),AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Section display incorrect");
//        Assert.assertEquals(lstBalance.get(0).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_INITIATION,"FAILED! Credit Initiation label displays incorrect");
//        Assert.assertEquals(lstBalance.get(0).get(1),AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_TIME_DEPOSIT,"FAILED! First Time Deposit display incorrect");
//        Assert.assertEquals(lstBalance.get(1).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_MAX_PLAYER_CREDIT,"FAILED! Max Player Credit display incorrect");
        Assert.assertTrue(page.cashBalanceInforSection.txtFirstTimeDeposit.isDisplayed(),"FAILED! Credit Initiation textbox not display");
//        Assert.assertTrue(page.cashBalanceSection.txtFirstTimeDeposit.isDisplayed(),"FAILED! First Time Deposit textbox not display");
//        Assert.assertTrue(page.cashBalanceSection.txtMemberMaxCredit.isDisplayed(),"FAILED!Max Player Credit textbox not display");

        log("Verify 2. There is no Credit Balance section display");
        Assert.assertFalse(page.creditBalanceInforSection.lblDownlineAGMaxCreditLimit.isDisplayed(),"FAILED! Credit Limit label display for Cash Account");

        Assert.assertFalse(page.lblRiskSetting.isDisplayed(),"FAILED! Risk Setting section display for Cash account");
        log("INFO: Executed completely");
    }

    @TestRails(id = "682")
    @Test (groups = {"smoke_creditcash"})
    public void Agent_AM_CreateDownline_Agent_682() {
        log("@title: Validate display Cash Balance for Credit Cash account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Step 2. Enter Security code");
        page.confirmSecurityCode(environment.getSecurityCode());
        page.waitingLoadingSpinner();

        log("Verify  2. Hover to Password hint icon. The title should be \"New Password:\n" +
                " 1. Should be between 8 to 15 characters.\n" +
                "  2. Only alphanumeric characters are allowed.\n" +
                "  3. Should contains at least 1 letter and 1 number.\"");
        page.accountInforSection.lblPasswordHint.moveAndHoverOnControl();
        Assert.assertEquals(page.accountInforSection.lblPasswordHint.getAttribute("title").trim(), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD_HINT.trim(),"FAILED! Password Hint message not correct");

        log("Verify 3. Account Status: Active and Inactive");
        Assert.assertTrue(page.accountInforSection.isAccountStatusDropdownLoadCorrect(AGConstant.AgencyManagement.CreateAccount.LST_ACCOUNTS_STATUS_CREATE),"FAILED! Account status default value not include Active and Inactive Status");

//        log("Verify 4.Verify Currency");
//        Assert.assertEquals(page.accInfoSection.lblBaseCurrencyValue.getText(),currency,"FAILED! Account status default value not include Active and Inactive Status");

        log("INFO: Executed completely");
    }

    @TestRails(id = "683")
    @Test (groups = {"smoke","smoke_dev"})
    public void Agent_AM_CreateDownline_Agent_683() throws Exception {
        log("@title: Validate can Create Downline Agent successfully");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        String password = "1234qwer";
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Step 2. Input required field and click on Submit button");
        page.confirmSecurityCode(environment.getSecurityCode());
        String loginID = "test.AG"+ StringUtils.generateAlphabetic(5);
        String userName = page.createDownline(loginID, password,"Active");

        log("Verify 1. Popup Create Downline with the message \"Downline was created successfully\"");
        Assert.assertTrue(page.successPopup.isDisplayed(),"FAILED! Success popup does not display after create user");
        Assert.assertEquals(page.successPopup.getContentMessage(), AGConstant.AgencyManagement.CreateUser.CREATE_USER_SUCCEESS_MESSAGE,"FAILED! Success message after create user is incorrect");

        log("Verify 2. Validate the popup is disappear when click on OK button");
        page.successPopup.close();
        Assert.assertFalse(page.successPopup.isDisplayed(),"FAILED! Create Downline popup not disappear after clicking OK button");
        page.logout();

        log("Verify 3. Valid can login agent with the created account");
        loginNewAccount(sosAgentURL,agentNewAccURL,userName, StringUtils.encrypt(password),StringUtils.decrypt(environment.getSecurityCode()));
        Assert.assertEquals(agentHomePage.leftMenu.lblLoginID.getText(),userName,"Failed!, Login ID label display incorrect");
        log("INFO: Executed completely");
    }

    @TestRails(id = "684")
    public void Agent_AM_CreateDownline_Agent_684() {
        log("@title: Validate if input incorrect Login ID format");
        //TODO: implement this case
        log("@title: Validate Cricket match odds will include fancy market");
        log("INFO: Executed completely");
    }

    @TestRails(id = "685")
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_685() {
        log("@title: Validate if input incorrect Change Password format");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Step 2. Enter security code");
        page.confirmSecurityCode(environment.getSecurityCode());

        log("Step 3. Input correct Login ID and incorrect password format");
        page.createDownline("test.AG1", "1234567", "Active");

        log("Verify 1. Message \"Password is invalid.\" display next to Cancel button");
        Assert.assertTrue(page.lblErrorMsg.getText().contains(AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID), String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "686")
    public void Agent_AM_CreateDownline_Agent_686() {
        //TODO: implement test for this case
        log("INFO: Executed completely");
    }

    @TestRails(id = "3486")
    @Test(groups = {"regression_sat"})
    @Parameters({"password"})
    public void Agent_AM_CreateDownline_Agent_3486(String password) throws Exception {
        //Run for SAT only
        log("@title: Validate can NOT Create Downline Agent if not input Login ID");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());
        page.createDownline("", StringUtils.decrypt(password), "Active");

        log("Verify 1. Message \"Login ID is required.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_REQUIRED, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3487")
    @Test(groups = {"regression"})
    public void Agent_AM_CreateDownline_Agent_3487() {
        log("@title: Validate can NOT Create Downline Agent if not input Password");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Step 2: Left Password empty and click on submit button");
        page.createDownline("test.AG1", "", "Active");

        log("Verify 1.Message \"Password is required.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_REQUIRED, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_REQUIRED, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3488")
    @Test(groups = {"regression_sat"})
    public void Agent_AM_CreateDownline_Agent_3488() {
        log("@title:Validate there is no security popup display when active Create Downline Agent");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        agentHomePage.leftMenu.leftMenuList.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT);

        log("Verify 1. Verify there is no security popup display");
        Assert.assertFalse(agentHomePage.securityPopup.isDisplayed(), "FAILED! Security popup display when navigating Create Downline Agent page");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3489")
    @Test(groups = {"regression_po"})
    public void Agent_AM_CreateDownline_Agent_3489() {
        log("@title:Validate cannot access Create Downline Agent from the left menu");
        log("Step 1. Expand Agency Management ");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

        log("Verify 1. Verify there is no Create downline agent displays in the left menu and cannot access from the left menu");
        Assert.assertFalse(lstMenu.contains(CREATE_DOWNLINE_AGENT), "FAILED! Create Downline Agent not display when login Agent level");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3490")
    @Test(groups = {"regression_ag"})
    public void Agent_AM_CreateDownline_Agent_3490() {
        //login with level = AG
        log("@title:Validate there is no Create Downline Agent when login by lowest agent level");
        log("Step 1. Expand Agency Management ");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

        log("Verify 1. Create Downline Agent page is not displayed");
        Assert.assertFalse(lstMenu.contains(CREATE_DOWNLINE_AGENT), "FAILED! Create Downline Agent displays when login Agent level");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3491")
    @Test (groups = {"regression_creditcash"})
    public void Agent_AM_CreateDownline_Agent_016() throws Exception {
        //SAT + login level under SMA Cash only
        log("@title: Validate UI when access the page by the levels under SAD");
        log("Precondition. Log in successfully by SMA level");
        log("Step 1. Navigate Agency Management > Create Downline Agent Level");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 1. Validate UI when access the page by the levels under SAD\n" +
                "- Only have Info,  Cash Balance, Rate Setting, Product Setting, Bet Setting Section displays");
        Assert.assertEquals(page.accountInforSection.getAccountInforSectionTitle(),AGConstant.AgencyManagement.CreateDownlineAgent.TITLE_PAGE, "FAILED! Account Info Section display incorrect");
        Assert.assertEquals(page.cashBalanceInforSection.getCashSectionTitle(),AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(page.rateSettingInforSection.getRateSettingSectionTitle(),AGConstant.AgencyManagement.CreateAccount.LBL_RATE_SETTING,"FAILED! Rate Setting Section display incorrect");
        Assert.assertEquals(page.productStatusSettingInforSection.getProductSettingSectionTitle(),AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");
        Assert.assertEquals(page.betSettingInforSection.getBetSettingSectionTitle(AGConstant.EXCHANGE),AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section display incorrect");
        Assert.assertFalse(page.taxSettingInforSection.tblTaxSettingEX.isDisplayed(),"FAILED! Tax Setting Section is displayed");
        page.positionTakingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);
        log("INFO: Executed completely");
    }

    @TestRails(id = "680")
    @Test (groups = {"regression_credit"})
    public void Agent_AM_CreateDownline_Agent_680() throws Exception {
        log("@title: Validate display Credit Balance and Risk Setting for Credit account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 1. Risk Setting section display");
        Assert.assertEquals(page.riskSettingInforSection.getRiskSettingTitle().trim(),AGConstant.AgencyManagement.CreateAccount.LBL_RISK_SETTING,"FAILED! Risk Setting Section display incorrect");
        Assert.assertTrue(page.riskSettingInforSection.txtMaxExposure.isDisplayed(),"FAILED! Max Exposure Textbox is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3484")
    @Test (groups = {"regression_credit"})
    @Parameters({"username"})
    public void Agent_AM_CreateDownline_Agent_3484(String username) throws Exception {
        log("@title: Validate display Credit Balance and Risk Setting for Credit account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(StringUtils.decrypt(environment.getSecurityCode()));
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDownLineUsers(_brandname, username, userID);
        page.accountInforSection.selectAgentLevel(AGConstant.AG_LEVEL_NAMING_BRANDS.get(lstUsers.get(0).getLevel()));

        log("Verify 1. Credit Balance section display");
        Assert.assertEquals(page.creditBalanceInforSection.getCreditSectionTitle().trim(),AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_BALANCE,"FAILED! Credit Balance Section display incorrect");
        Assert.assertTrue(page.creditBalanceInforSection.txtCreditLimit.isDisplayed(),"FAILED! Credit Limit field does not display");
        Assert.assertTrue(page.creditBalanceInforSection.txtAGMaxCredit.isDisplayed(),"FAILED! SMA Max Credit field does not display");
        Assert.assertTrue(page.creditBalanceInforSection.txtMemberMaxCredit.isDisplayed(),"FAILED! Member Max Credit field does not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3485")
    @Test (groups = {"regression_credit"})
    public void Agent_AM_CreateDownline_Agent_3485() throws Exception {
        log("@title: Validate display Credit Balance and Risk Setting for Credit account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 1. Verify Credit Cash section does not display");
        Assert.assertFalse(page.cashBalanceInforSection.txtFirstTimeDeposit.isDisplayed(),"FAILED! Initial Deposit field display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3483")
    @Test (groups = {"http_request"})
    public void Agent_AM_CreateDownline_Agent_3483() throws Exception {
        //Set isProxy = true
        log("@title: Validate There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        agentHomePage.navigateCreateDownLineAgentPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 1. Create Downline page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");
        log("INFO: Executed completely");
    }
}

