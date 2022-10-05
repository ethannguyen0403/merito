package agentsite.testcase.oldUI.agencymanagement;

import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.oldUI.agentmanagement.CreateDownLineAgentPageOldUI;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.CREATE_DOWNLINE_AGENT;

public class CreateDownlineAgentTest extends BaseCaseMerito {

    @Test(groups = {"https_request"})
    public void Agent_AM_CreateDownline_Agent_001(String currency, String prefix) throws Exception {
        log("@title:There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPageOldUI page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPageOldUI.class);

        log("Verify 1. Create Downline Agent page is displayed with down console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error when nagigate to the site");

        log("INFO: Executed completely");
    }

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
    @Test (groups = {"smoke"})
    @Parameters({"currency","prefix"})
    public void Agent_AM_CreateDownline_Agent_002(String currency,String prefix) throws Exception {
        log("@title: Validate UI in Create Downline Agent with Exchange Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPageOldUI page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPageOldUI.class);
        log("Step 2. Enter Security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
        page.waitingLoadingSpinner();

        log("Verify 1.Verify Page title correct");
        Assert.assertEquals(page.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.CreateDownlineAgent.TITLE_PAGE, "Failed! Page title is incorrect");

        log("Verify 2.Verify Labels name correct");
        List<String> lstInfo = page.accInfoSection.getListLabelInfo();
        Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! Frist Name display incorrect");
        Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_PHONE, "FAILED! Phone label display incorrect");
        Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_BASE_CURRENCY, "FAILED! Base Currency label display incorrect");
        Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status label display incorrect");
        Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_LEVEL, "FAILED! Level display incorrect");
        Assert.assertEquals(lstInfo.get(7), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name display incorrect");
        Assert.assertEquals(lstInfo.get(8), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
        Assert.assertEquals(lstInfo.get(9), AGConstant.AgencyManagement.CreateAccount.LBL_FAX, "FAILED! Fax display incorrect");
        Assert.assertEquals(page.accInfoSection.lblUsernamePrefix.getText(),prefix, "FAILED! Login ID textbox does not display");
        Assert.assertEquals(page.accInfoSection.lblBaseCurrencyValue.getText(), currency, "FAILED!Base currency is not correct");
        Assert.assertTrue(page.accInfoSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(page.accInfoSection.ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtPhone.isDisplayed(), "FAILED! Phone textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtFax.isDisplayed(), "FAILED! Tax textbox does not display");
        Assert.assertFalse(page.accInfoSection.cbAllowExtraPT.isDisplayed(), "FAILED! Allow Extra PT checkbox does not display");

        log("Verify 2. Cash Balance");
        Assert.assertEquals(page.creditBalanceSection.lblCashBalanceTitle.getText().trim(), AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Title display incorrect");

        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(page.productSettingsSection.getTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = page.productSettingsSection.betSettingSectionExchange.tblBetSetting.getHeaderNameOfRows();
        List<String> lstBetSettingOption = page.productSettingsSection.betSettingSectionExchange.tblBetSetting.getColumn(1,false);
        List<String> lstTaxSettingHeader = page.productSettingsSection.taxSettingSectionExchange.tblTaxSetting.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = page.productSettingsSection.taxSettingSectionExchange.tblTaxSetting.getColumn(1,false);
        List<String> lstPositionTakingHeader = page.productSettingsSection.positionTakingSectionExchange.tblPositionTaking.getHeaderNameOfRows();
        Assert.assertEquals(page.productSettingsSection.betSettingSectionExchange.getTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_HEADER,"FAILED! Bet Setting Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION,"FAILED! Bet Setting options in the first column does not display as expected");

        Assert.assertEquals(page.productSettingsSection.taxSettingSectionExchange.getTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_HEADER,"FAILED! Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(page.productSettingsSection.positionTakingSectionExchange.getTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_POSITION_TAKING_HEADER_OLD_UI,"FAILED! Position Taking Header does not display as expected");

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
    @Test (groups = {"smoke"})
    @Parameters({"currency","prefix"})
    public void Agent_AM_CreateDownline_Agent_003(String currency,String prefix) throws Exception {
        log("@title: Validate UI in Create Downline Agent with Exchange Game Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPageOldUI page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPageOldUI.class);

        log("Step 2. Enter Security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
        page.waitingLoadingSpinner();

        log("Step 2. Select Exchange Game Tab");
        page.productSettingsSection.tabExchangeGames.click();

        log("Verify 1. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = page.productSettingsSection.betSettingSectionExchangeGame.tblBetSetting.getHeaderNameOfRows();
        List<String> lstBetSettingOption = page.productSettingsSection.betSettingSectionExchangeGame.tblBetSetting.getColumn(1,false);
        List<String> lstTaxSettingHeader = page.productSettingsSection.taxSettingSectionExchangeGame.tblTaxSetting.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = page.productSettingsSection.taxSettingSectionExchangeGame.tblTaxSetting.getColumn(1,false);
        List<String> lstPositionTakingHeader = page.productSettingsSection.positionTakingSectionExchangeGame.tblPositionTaking.getHeaderNameOfRows();

        Assert.assertEquals(page.productSettingsSection.betSettingSectionExchangeGame.getTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Bet Setting  Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION,"FAILED! Bet Setting options in the first column does not display as expected");

        Assert.assertEquals(page.productSettingsSection.taxSettingSectionExchangeGame.getTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(page.productSettingsSection.positionTakingSectionExchangeGame.getTitle(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Position Taking Header does not display as expected");

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
    @Test (groups = {"smoke"})
    @Parameters({"currency"})
    public void Agent_AM_CreateDownline_Agent_006(String currency) throws Exception {
        log("@title: Validate display Cash Balance for Credit Cash account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPageOldUI page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPageOldUI.class);

        log("Step 2. Enter Security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
        page.waitingLoadingSpinner();

        log("Verify  2. Hover to Password hint icon. The title should be \"New Password:\n" +
                " 1. Should be between 8 to 15 characters.\n" +
                "  2. Only alphanumeric characters are allowed.\n" +
                "  3. Should contains at least 1 letter and 1 number.\"");
        page.accInfoSection.lblPasswordHint.moveAndHoverOnControl();
        Assert.assertEquals(page.accInfoSection.lblPasswordHint.getAttribute("title").trim(), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD_HINT,"FAILED! Password Hint message not correct");

        log("Verify 3. Account Status: Active and Inactive");
        Assert.assertTrue(page.accInfoSection.ddrAccountStatus.areOptionsMatched(AGConstant.AgencyManagement.CreateAccount.LST_ACCOUNTS_STATUS_CREATE),"FAILED! Account status default value not include Active and Inactive Status");

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
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_007() throws Exception {
        log("@title: Validate can Create Downline Agent successfully");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        String password = "1234qwer";
        CreateDownLineAgentPageOldUI page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPageOldUI.class);

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
        loginNewAccount(sosAgentURL,agentSecurityCodeURL+"/agent/#/1/update",loginID,StringUtils.encrypt(password),StringUtils.decrypt(environment.getSecurityCode()));
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
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_011() throws Exception {
        log("@title: Validate if input incorrect Change Password format");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPageOldUI page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPageOldUI.class);

        log("Step 2. Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. Input correct Login ID and incorrect password format");
        page.createDonwline("1234567","Agent");

        log("Verify 1. Message \"Password is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

}

