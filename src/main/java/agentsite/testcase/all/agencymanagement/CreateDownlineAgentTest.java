package agentsite.testcase.all.agencymanagement;

import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.CreateDownLineAgentPage;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.CREATE_DOWNLINE_AGENT;

public class CreateDownlineAgentTest extends BaseCaseMerito {
    /**
     * @title: Validate UI in Create Downline Agent with Exchange Product setting
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create Downline Agent
     * @expect: 1. Account info section
     *          2. Rate Setting
     *          3. Product Setting, select Exchange product
     *          4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting
     *          5. Submit and Cancel button
     */

    @TestRails(id = "678")
    @Test (groups = {"smoke"})
    @Parameters({"currency","prefix"})
    public void Agent_AM_CreateDownline_Agent_002(String currency,String prefix) throws Exception {
        log("@title: Validate UI in Create Downline Agent with Exchange Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);
        log("Step 2. Enter Security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
        page.waitingLoadingSpinner();

        log("Verify 1. Account info section");
        Assert.assertEquals(page.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.CreateDownlineAgent.TITLE_PAGE, "Failed! Page title is incorrect");
        List<String> lstInfo = page.accInfoSection.getListLabelInfo();
        Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_LEVEL, "FAILED! Level label display incorrect");
        Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
        Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_PHONE, "FAILED! Phone display incorrect");
        Assert.assertEquals(lstInfo.get(7), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
        Assert.assertEquals(lstInfo.get(8), AGConstant.AgencyManagement.CreateAccount.LBL_FAX, "FAILED! Fax display incorrect");
        Assert.assertEquals(lstInfo.get(9), AGConstant.AgencyManagement.CreateAccount.LBL_BASE_CURRENCY, "FAILED! Base Currency display incorrect");
        Assert.assertEquals(lstInfo.get(10), AGConstant.AgencyManagement.CreateAccount.LBL_ALLOW_AG_EXTRA, "FAILED! Allow Extra display incorrect");
        Assert.assertEquals(page.accInfoSection.lblUsernamePrefix.getText(),prefix, "FAILED! Login ID textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(page.accInfoSection.ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtPhone.isDisplayed(), "FAILED! Phone textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtFax.isDisplayed(), "FAILED! Tax textbox does not display");
        Assert.assertEquals(page.accInfoSection.lblBaseCurrencyValue.getText(), currency, "FAILED!Base currency is not correct");
        Assert.assertTrue(page.accInfoSection.cbAllowExtraPT.isDisplayed(), "FAILED! Allow Extra PT checkbox does not display");

        log("Verify 2. Cash Balance");
        List<ArrayList<String>> lstBalance = page.creditBalanceSection.tblCashBalance.getRowsWithoutHeader(1,false);
        Assert.assertEquals(page.creditBalanceSection.lblCashBalanceTitle.getText().trim(), AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Title display incorrect");
        //Assert.assertEquals(lstBalance.get(0).get(0), FEAGConstant.AgencyManagement.CreateAccount.LBL_INITIATION_DEPOSIT,"FAILED! Initiation Deposit label displays incorrect");


        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(page.lblProductSetting.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = page.tblBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = page.tblBetSettings.getColumn(1,false);
        List<String> lstTaxSettingHeader = page.tblTaxSettings.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = page.tblTaxSettings.getColumn(1,false);
        List<String> lstPositionTakingHeader = page.tblPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(page.lblBetSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_HEADER,"FAILED! Bet Setting Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION,"FAILED! Bet Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblTaxSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_HEADER,"FAILED! Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblPositionTakingListing.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_POSITION_TAKING_HEADER,"FAILED! Position Taking Header does not display as expected");

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(), AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(page.btnCancel.getText(), AGConstant.BTN_CANCEL,"FAILED! Cancel button display incorrect");
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
    @Test (groups = {"smoke"})
    @Parameters({"currency","prefix"})
    public void Agent_AM_CreateDownline_Agent_003(String currency,String prefix) throws Exception {
        log("@title: Validate UI in Create Downline Agent with Exchange Game Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Enter Security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
        page.waitingLoadingSpinner();

        log("Step 2. Select Exchange Game Tab");
        page.tabExchangeGames.click();

        log("Verify 1. Account info section");
       // List<ArrayList<String>> lstInfo = page.tblAccountInfo.getRowsWithoutHeader(2,false);
        Assert.assertEquals(page.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.CreateDownlineAgent.TITLE_PAGE, "Failed! Page title is incorrect");
        List<String> lstInfo = page.accInfoSection.getListLabelInfo();
        Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_LEVEL, "FAILED! Level label display incorrect");
        Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
        Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_PHONE, "FAILED! Phone display incorrect");
        Assert.assertEquals(lstInfo.get(7), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
        Assert.assertEquals(lstInfo.get(8), AGConstant.AgencyManagement.CreateAccount.LBL_FAX, "FAILED! Fax display incorrect");
        Assert.assertEquals(lstInfo.get(9), AGConstant.AgencyManagement.CreateAccount.LBL_BASE_CURRENCY, "FAILED! Base Currency display incorrect");
        Assert.assertEquals(lstInfo.get(10), AGConstant.AgencyManagement.CreateAccount.LBL_ALLOW_AG_EXTRA, "FAILED! Allow Extra display incorrect");
        Assert.assertEquals(page.accInfoSection.lblUsernamePrefix.getText(),prefix, "FAILED! Login ID textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(page.accInfoSection.ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtPhone.isDisplayed(), "FAILED! Phone textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtFax.isDisplayed(), "FAILED! Tax textbox does not display");
        Assert.assertEquals(page.accInfoSection.lblBaseCurrencyValue.getText(), currency, "FAILED!Base currency is not correct");
        Assert.assertTrue(page.accInfoSection.cbAllowExtraPT.isDisplayed(), "FAILED! Allow Extra PT checkbox does not display");

        log("Verify 2. Cash Balance");

        List<ArrayList<String>> lstBalance = page.creditBalanceSection.tblCashBalance.getRowsWithoutHeader(1,false);
        Assert.assertEquals(page.creditBalanceSection.lblCashBalanceTitle.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(lstBalance.get(0).get(0), AGConstant.AgencyManagement.CreateAccount.LBL_INITIATION_DEPOSIT,"FAILED! Credit Initiation label displays incorrect");
//
        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(page.lblProductSetting.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = page.tblEGBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = page.tblEGBetSettings.getColumn(1,false);
        List<String> lstTaxSettingHeader = page.tblEGTaxSettings.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = page.tblEGTaxSettings.getColumn(1,false);
        List<String> lstPositionTakingHeader = page.tblEGPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(page.lblEGBetSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Bet Setting  Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION,"FAILED! Bet Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblEGTaxSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblEGPositionTakingListing.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Position Taking Header does not display as expected");

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(), AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(page.btnCancel.getText(), AGConstant.BTN_CANCEL,"FAILED! Cancel button display incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate display Cash Balance and Risk Setting for Credit account
     * @pre-condition:
     *           1. Log in successfully with Credit Account
     * @steps:  1. Navigate Agency Management > Create Downline Agent
     * @expect: 1.Credit Balance section display
     *      - Credit Limit
     *      - SMA Max Credit
     *      - PL Max Credit
     *      2. Risk Setting section display
     *      3. There is no cash Balance section display
     */
    @TestRails(id = "680")
    @Test (groups = {"credit_smoke"})
    public void Agent_AM_CreateDownline_Agent_004(){
        log("@title: Validate display Credit Balance and Risk Setting for Credit account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);
        page.accInfoSection.ddpLevel.selectByVisibleText("Admin");
        page.waitingLoadingSpinner();

        log("Verify 1. Credit Balance section display");
        Assert.assertEquals(page.creditBalanceSection.lblCashBalanceTitle.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_BALANCE,"FAILED! Credit Balance Section display incorrect");
        List<String> lstLabels = page.creditBalanceSection.tblCashBalance.getColumn(1,false);
        Assert.assertEquals(lstLabels.get(0),AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_LIMIT,"FAILED! Credit Limit Label display incorrect");
        Assert.assertEquals(lstLabels.get(1),AGConstant.AgencyManagement.CreateAccount.LBL_SMA_BALANCE,"FAILED! SMA Max Credit Label display incorrect");
        Assert.assertEquals(lstLabels.get(2),AGConstant.AgencyManagement.CreateAccount.LBL_MEMBER_MAX_CREDIT,"FAILED! Member Max Credit Label display incorrect");
        Assert.assertTrue(page.creditBalanceSection.txtCreditLimit.isDisplayed(),"FAILED! Member Max Credit textbox display incorrect");
        Assert.assertTrue(page.creditBalanceSection.txtDownlineAGMaxCredit.isDisplayed(),"FAILED! SMA Max Credit textbox display incorrect");
        Assert.assertTrue(page.creditBalanceSection.txtMemberMaxCredit.isDisplayed(),"FAILED! Member Max Credit textbox display incorrect");


        log("Verify 2. Risk Setting section display");
        Assert.assertEquals(page.lblRiskSetting.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_RISK_SETTING,"FAILED! Risk Setting Section display incorrect");
        Assert.assertEquals(page.lblMaxExposure.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_MAX_EXPOSURE,"FAILED! Max Exposure label display incorrect");
        Assert.assertEquals(page.lblMaxExposureHint.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_MAX_EXPOSURE_HINT,"FAILED! Max Exposure hint label display incorrect");
        Assert.assertTrue(page.txtMaxExposure.isDisplayed(),"FAILED! Max Exposure Textbox display incorrect");

        log("Verify 3. There is no cash Balance section display");
        Assert.assertFalse(page.creditBalanceSection.txtInitiationDeposit.isDisplayed(),"FAILED! Credit Initiation textbox display for credit account");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate display Cash Balance for Cash account
     * @pre-condition:
     *           1. Log in successfully with Cash Account
     * @steps:  1. Navigate Agency Management > Create Downline Agent
     * @expect: 1.Cash Balance section display
     *      - Credit Initiation
     *      - Max Player Credit
     *      - First  Time Deposit
     *      2. There is no Credit Balance section display
     */
    @TestRails(id = "681")
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_005(){
        log("@title: Validate display Cash Balance for Cash account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Verify 1.Credit Cash Balance section display");
        List<ArrayList<String>> lstBalance = page.cashBalanceSection.tblCashBalance.getRowsWithoutHeader(2,false);
        Assert.assertEquals(page.cashBalanceSection.lblTitle.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_BALANCE,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(lstBalance.get(0).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_INITIATION,"FAILED! Credit Initiation label displays incorrect");
        Assert.assertEquals(lstBalance.get(0).get(2),AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_TIME_DEPOSIT,"FAILED! First Time Deposit display incorrect");
        Assert.assertEquals(lstBalance.get(1).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_MAX_PLAYER_CREDIT,"FAILED! Max Player Credit display incorrect");
        Assert.assertFalse(page.cashBalanceSection.txtInitiationDeposit.isDisplayed(),"FAILED! Credit Initiation textbox not display");
        Assert.assertTrue(page.cashBalanceSection.txtFirstTimeDeposit.isDisplayed(),"FAILED! First Time Deposit textbox not display");
        Assert.assertTrue(page.cashBalanceSection.txtMemberMaxCredit.isDisplayed(),"FAILED!Max Player Credit textbox not display");

        log("Verify 2. There is no Credit Balance section display");
        Assert.assertFalse(page.creditBalanceSection.txtInitiationDeposit.isDisplayed(),"FAILED! Credit Limit textbox display for Cash Account");

        Assert.assertFalse(page.lblRiskSetting.isDisplayed(),"FAILED! Risk Setting section display for Cash account");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate default value in Create Downline Agent
     * @pre-condition:
     *           1. Log in successfully with Credit Cash Account
     * @steps:  1. Navigate Agency Management > Create Downline Agent
     * @expect: 1. Hover to Login hint icon. The hint message display correctly "Login ID must be unique and at least a minimum of 6 characters and maximum of 15 characters"
     *          2. Hover to Password hint icon. The title should be "New Password:
     *          1. Should be between 8 to 15 characters.
     *          2. Only alphanumeric characters are allowed.
     *          3. Should contains at least 1 letter and 1 number."
     *          3. Account Status: Active and Inactive
     *          4. The agent level under login level
     */
    @TestRails(id = "682")
    @Test (groups = {"smoke"})
    @Parameters({"currency"})
    public void Agent_AM_CreateDownline_Agent_006(String currency) throws Exception {
        log("@title: Validate display Cash Balance for Credit Cash account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Enter Security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
        page.waitingLoadingSpinner();

        log("Verify  2. Hover to Password hint icon. The title should be \"New Password:\n" +
                " 1. Should be between 8 to 15 characters.\n" +
                "  2. Only alphanumeric characters are allowed.\n" +
                "  3. Should contains at least 1 letter and 1 number.\"");
        page.accInfoSection.lblPasswordHint.moveAndHoverOnControl();
        Assert.assertEquals(page.accInfoSection.lblPasswordHint.getAttribute("title").trim(), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD_HINT.trim(),"FAILED! Password Hint message not correct");

        log("Verify 3. Account Status: Active and Inactive");
        Assert.assertTrue(page.accInfoSection.ddrAccountStatus.areOptionsMatched(AGConstant.AgencyManagement.CreateAccount.LST_ACCOUNTS_STATUS_CREATE),"FAILED! Account status default value not include Active and Inactive Status");

        log("Verify 4.Verify Currency");
        Assert.assertEquals(page.accInfoSection.lblBaseCurrencyValue.getText(),currency,"FAILED! Account status default value not include Active and Inactive Status");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can Create Downline Agent successfully
     * @pre-condition:
     *           1. Log in successfully with Credit Cash Account
     * @steps:  1. Navigate Agency Management > Create Downline Agent
     *          2. Input required field and click on Submit button
     * @expect: 1. Popup Create Downline with the message "Downline was created successfully"
     *          2. Validate the popup is disappear when click on OK button
     *          3. Valid can login agent with the created account
     */
    @TestRails(id = "683")
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_007() throws Exception {
        log("@title: Validate can Create Downline Agent successfully");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        String password = "1234qwer";
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Input required field and click on Submit button");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
        String loginID = page.createDonwline(password,"Agent");

        log("Verify 1. Popup Create Downline with the message \"Downline was created successfully\"");
        Assert.assertTrue(page.successPopup.isDisplayed(),"FAILED! Success popup does not display after create user");
        Assert.assertEquals(page.successPopup.getContentMessage(), AGConstant.AgencyManagement.CreateUser.CREATE_USER_SUCCEESS_MESSAGE,"FAILED! Success message after create user is incorrect");

        log("Verify 2. Validate the popup is disappear when click on OK button");
        page.successPopup.close();
        Assert.assertFalse(page.successPopup.isDisplayed(),"FAILED! Create Downline popup not disappear after clicking OK button");
        page.logout();

        log("Verify 3. Valid can login agent with the created account");
        loginNewAccount(sosAgentURL,agentNewAccURL,loginID,StringUtils.encrypt(password),StringUtils.decrypt(environment.getSecurityCode()));
        Assert.assertEquals(agentHomePage.lblLoginID.getText(),loginID,"Failed!, Login ID label display incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate if input incorrect Change Password format
     * @pre-condition:
     *           1. Log in successfully with Credit Cash Account
     * @steps:  1. Navigate Agency Management > Create Downline Agent
     *          2. Input correct Login ID and incorrect password format
     * @expect: 1. Message "Password is invalid." display next to Cancel button
     */
    @TestRails(id = "685")
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_011() throws Exception {
        log("@title: Validate if input incorrect Change Password format");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. Input correct Login ID and incorrect password format");
        page.createDonwline("1234567", "Agent");

        log("Verify 1. Message \"Password is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_CreateDownline_Agent_008() throws Exception {
        log("@title: Validate can NOT Create Downline Agent if not input Login ID");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        String password = "1234qwer";
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());
        page.createDonwline("", password, "Active");

        log("Verify 1. Message \"Login ID is required.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_REQUIRED, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_CreateDownline_Agent_009() throws Exception {
        log("@title: Validate can NOT Create Downline Agent if not input Password");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Step 2: Left Password empty and click on submit button");
        page.createDonwline("test.AG1", "", "Active");

        log("Verify 1.Message \"Password is required.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_REQUIRED, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @Test(groups = {"regression"})
    public void Agent_AM_CreateDownline_Agent_013() throws Exception {
        log("@title:Validate there is no security popup display when active Create Downline Agent");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage("");

        log("Verify 1. Verify there is no security popup display");
        Assert.assertFalse(page.securityPopup.isDisplayed(), "FAILED! Security popup should not display when navigating Create Downline Agent page ");

        log("INFO: Executed completely");
    }

    @Test(groups = {"poregression"})
    public void Agent_AM_CreateDownline_Agent_014() throws Exception {
        log("@title:Validate cannot access Create Downline Agent from the left menu");
        log("Step 1. Expand Agency Management ");
        agentHomePage.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstMenu = agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

        log("Verify 1. Verify there is no Create downline agent displays in the left menu and cannot access from the left menu");
        Assert.assertFalse(lstMenu.contains(CREATE_DOWNLINE_AGENT), "FAILED! Create Downline Agent not diplay when login Agent level");

        log("INFO: Executed completely");
    }

    @Test(groups = {"regressionAGLevel"})
    public void Agent_AM_CreateDownline_Agent_015() throws Exception {
        log("@title:Validate there is no Create Downline Agent when login by lowest agent level");
        log("Step 1. Expand Agency Management ");
        agentHomePage.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstMenu = agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

        log("Verify 1. Create Downline Agent page is not displayed");
        Assert.assertFalse(lstMenu.contains(CREATE_DOWNLINE_AGENT), "FAILED! Create Downline Agent not diplay when login Agent level");

        log("INFO: Executed completely");
    }

   /* @Test (groups = {"regression"})
    public void Agent_AM_CreateDownline_Agent_016() throws Exception {
        log("@title: Validate UI when access the page by the levels under SAD");
        log("Step Precondition1. Log in successfully by SMA level");
        agentHomePage.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstMenu = agentHomePage.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);
        log("Step 1. Navigate Agency Management > Create Downlien Agent Level");

        log("Verify 1. Verify UI display correct for low level\n" +
                "- Only have Info,  Cash Balance, Rate Setting, Product Setting, Bet Setting Section");
        Assert.assertFalse(lstMenu.contains(CREATE_DOWNLINE_AGENT),"FAILED! Create Downline Agent not diplay when login Agent level");

        log("INFO: Executed completely");
    }*/

}

