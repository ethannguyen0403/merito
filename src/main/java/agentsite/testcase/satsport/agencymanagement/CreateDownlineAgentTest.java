package agentsite.testcase.satsport.agencymanagement;

import com.paltech.driver.DriverManager;
import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.CreateDownLineAgentPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

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
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_002() {
        log("@title: Validate UI in Create Downline Agent with Exchange Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);
        log("Verify 1. Account info section");
        List<ArrayList<String>> lstInfo = page.accInfoSection.tblAccountInfo.getRowsWithoutHeader(2, false);
        Assert.assertEquals(lstInfo.get(0).get(0), AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(0).get(2), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(0).get(4), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(0).get(6), AGConstant.AgencyManagement.CreateAccount.LBL_LEVEL, "FAILED! Level label display incorrect");
        Assert.assertEquals(lstInfo.get(1).get(0), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(1).get(2), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
        Assert.assertEquals(lstInfo.get(1).get(4), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
        Assert.assertTrue(page.accInfoSection.txtLoginID.isDisplayed(), "FAILED! Login ID textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(page.accInfoSection.ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");

        log("Verify 2. Cash Balance");

        List<ArrayList<String>> lstBalance = page.cashBalanceSection.tblCashBalance.getRowsWithoutHeader(2,false);
        Assert.assertEquals(page.cashBalanceSection.lblTitle.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(lstBalance.get(0).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_INITIATION,"FAILED! Credit Initiation label displays incorrect");
        Assert.assertEquals(lstBalance.get(0).get(2),AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_TIME_DEPOSIT,"FAILED! First Time Deposit display incorrect");
     //   Assert.assertEquals(lstBalance.get(1).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_MAX_PLAYER_CREDIT,"FAILED! Max Player Credit display incorrect");

        log("Verify 2. Rate Setting");
        Assert.assertEquals(page.rateSettingSection.getRateTitle(),AGConstant.AgencyManagement.CreateAccount.LBL_RATE_SETTING,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(page.rateSettingSection.getRateLabel(),AGConstant.AgencyManagement.CreateAccount.LBL_RATE,"FAILED! Rate label display incorrect");
        Assert.assertTrue(page.rateSettingSection.txtRate.isDisplayed(), "FAILED! Rate textbox does not display");

        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(page.lblProductSetting.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = page.tblBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = page.tblBetSettings.getColumn(1,false);
        List<String> lstTaxSettingHeader = page.tblTaxSettings.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = page.tblTaxSettings.getColumn(1,false);
        List<String> lstPositionTakingHeader = page.tblPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(page.lblBetSettings.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_HEADER,"FAILED! Bet Setting Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption,AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION,"FAILED! Bet Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblTaxSettings.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_HEADER,"FAILED! Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption,AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblPositionTakingListing.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_POSITION_TAKING_HEADER,"FAILED! Position Taking Header does not display as expected");

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(),AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(page.btnCancel.getText(),AGConstant.BTN_CANCEL,"FAILED! Cancel button display incorrect");
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
    public void Agent_AM_CreateDownline_Agent_003(){
        log("@title: Validate UI in Create Downline Agent with Exchange Game Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Select Exchange Game Tab");
        page.tabExchangeGames.click();

        log("Verify 1. Account info section");
        List<ArrayList<String>> lstInfo = page.accInfoSection.tblAccountInfo.getRowsWithoutHeader(2,false);
        Assert.assertEquals(lstInfo.get(0).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID,"FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(0).get(2),AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD,"FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(0).get(4),AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS,"FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(0).get(6),AGConstant.AgencyManagement.CreateAccount.LBL_LEVEL,"FAILED! Level label display incorrect");
        Assert.assertEquals(lstInfo.get(1).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME,"FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(1).get(2),AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME,"FAILED! Last Name label display incorrect");
        Assert.assertEquals(lstInfo.get(1).get(4),AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE,"FAILED! Mobile display incorrect");
        Assert.assertTrue(page.accInfoSection.txtLoginID.isDisplayed(), "FAILED! Login ID textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(page.accInfoSection.ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.ddpLevel.isDisplayed(), "FAILED! Level dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");


        log("Verify 2. Cash Balance");
        List<ArrayList<String>> lstBalance = page.cashBalanceSection.tblCashBalance.getRowsWithoutHeader(2,false);
        Assert.assertEquals(page.cashBalanceSection.lblTitle.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(lstBalance.get(0).get(0),AGConstant.AgencyManagement.CreateAccount.LBL_CREDIT_INITIATION,"FAILED! Credit Initiation label displays incorrect");
        Assert.assertEquals(lstBalance.get(0).get(2),AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_TIME_DEPOSIT,"FAILED! First Time Deposit display incorrect");

        log("Verify 2. Rate Setting");
        Assert.assertEquals(page.rateSettingSection.getRateTitle(),AGConstant.AgencyManagement.CreateAccount.LBL_RATE_SETTING,"FAILED! Cash Balance Section display incorrect");
        Assert.assertEquals(page.rateSettingSection.getRateLabel(),AGConstant.AgencyManagement.CreateAccount.LBL_RATE,"FAILED! Rate label display incorrect");
        Assert.assertTrue(page.rateSettingSection.txtRate.isDisplayed(), "FAILED! Rate textbox does not display");

        log("Verify 3. Product Setting, select Exchange product");
        Assert.assertEquals(page.lblProductSetting.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");

        log("Verify 4. Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstBetSettingHeader = page.tblEGBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = page.tblEGBetSettings.getColumn(1,false);
        List<String> lstTaxSettingHeader = page.tblEGTaxSettings.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = page.tblEGTaxSettings.getColumn(1,false);
        List<String> lstPositionTakingHeader = page.tblEGPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(page.lblEGBetSettings.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Bet Setting  Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption,AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION,"FAILED! Bet Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblEGTaxSettings.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption,AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(page.lblEGPositionTakingListing.getText(),AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_EG_GAME_GORUP_HEADER,"FAILED! Exchange Game Position Taking Header does not display as expected");

        log("Verify 5. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(),AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(page.btnCancel.getText(),AGConstant.BTN_CANCEL,"FAILED! Cancel button display incorrect");
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
     *           1. Log in successfully with Credit  Account
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
    public void Agent_AM_CreateDownline_Agent_006(){
        log("@title: Validate display Cash Balance for Credit Cash account");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Verify 1. Hover to Login hint icon. The hint message display correctly \"Login ID must be unique and at least a minimum of 6 characters and maximum of 15 characters\"");
        page.accInfoSection.lblLoginIDHint.moveAndHoverOnControl();
        Assert.assertEquals(page.accInfoSection.lblLoginIDHint.getAttribute("title"),AGConstant.AgencyManagement.CreateAccount.LBL_LOGINID_HINT,"FAILED! Login ID Hint message not correct");

        log("Verify  2. Hover to Password hint icon. The title should be \"New Password:\n" +
                "     *          1. Should be between 8 to 15 characters.\n" +
                "     *          2. Only alphanumeric characters are allowed.\n" +
                "     *          3. Should contains at least 1 letter and 1 number.\"");
        page.accInfoSection.lblPasswordHint.moveAndHoverOnControl();
        Assert.assertEquals(page.accInfoSection.lblPasswordHint.getAttribute("title"),AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD_HINT,"FAILED! Login ID Hint message not correct");

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
        String loginID = String.format("ATAD%s",StringUtils.generateAlphabetic(4));
        String password = "1234qwer";

        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Input required field and click on Submit button");
        page.createDonwline(loginID,password,"Admin");
        log("Verify 1. Popup Create Downline with the message \"Downline was created successfully\"");
        Assert.assertTrue(page.successPopup.isDisplayed(),"FAILED! Success popup does not display after create user");
        Assert.assertEquals(page.successPopup.getContentMessage(),AGConstant.AgencyManagement.CreateUser.CREATE_USER_SUCCEESS_MESSAGE,"FAILED! Success message after create user is incorrect");

        log("Verify 2. Validate the popup is disappear when click on OK button");
        page.successPopup.close();
        Assert.assertFalse(page.successPopup.isDisplayed(),"FAILED! Create Downline popup not disappear after clicking OK button");

        log("Verify 3. Valid can login agent with the created account");
        DriverManager.getDriver().switchToParentFrame();
        page.logout();

        loginNewAccount(sosAgentURL,agentLoginURL+"/update",loginID,StringUtils.encrypt(password),"112233");
        Assert.assertEquals(agentHomePage.lblLoginID.getText(),loginID,"Failed!, Login ID lable display incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate if input incorrect Login ID format
     * @pre-condition:
     *           1. Log in successfully with Credit Cash Account
     * @steps:  1. Navigate Agency Management > Create Downline Agent
     *          2. Input incorrect Login ID format
     * @expect: 1. Message "Login ID is invalid." display next to Cancel button
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_010(){
        log("@title: Validate if input incorrect Login ID format");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Input incorrect Login ID format");
        page.createDonwline("123rw","","Admin");

        log("Verify 1. Message \"Login ID is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(),AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID, page.lblErrorMsg.getText()));

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
    public void Agent_AM_CreateDownline_Agent_011(){
        log("@title: Validate if input incorrect Change Password format");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        String loginID = String.format("ATAD%s", StringUtils.generateAlphabetic(4));
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Input correct Login ID and incorrect password format");
        page.createDonwline(loginID,"1234567","Admin");

        log("Verify 1. Message \"Password is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(),AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cannot create downline with the exist Login ID
     * @pre-condition:
     *           1. Log in successfully with Credit Cash Account
     * @steps:  1. Navigate Agency Management > Create Downline Agent
     *          2. Input Login ID that exist in the system and correct password then click submit
     * @expect: 1. Popup Create Downline with the message "Login ID already exist."
     */
    @Test (groups = {"smoke"})
    @Parameters({"level"})
    public void Agent_AM_CreateDownline_Agent_012(String level){
        log("@title: Validate cannot create downline with the exist Login ID");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,level);

        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateDownLineAgentPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Step 2. Input Login ID that exist in the system and correct password then click submit");
        page.createDonwline(listAccount.get(0).getLoginID(),"1234qwer","Admin");

        log("Verify 1. Popup Create Downline with the message \"Login ID already exist.\"");
        Assert.assertEquals(page.successPopup.getContentMessage(),AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_EXIST,String.format("FAILED! Expected error message is %s but found",AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_EXIST,page.successPopup.getContentMessage()));

        log("INFO: Executed completely");
    }

}

