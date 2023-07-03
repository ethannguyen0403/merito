package agentsite.testcase.agencymanagement;

import agentsite.pages.agentmanagement.CreateUserPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static common.AGConstant.AgencyManagement.CreateUser.*;
import static common.AGConstant.BTN_CANCEL;
import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static common.AGConstant.HomePage.CREATE_USER;

public class CreateUserTest extends BaseCaseTest {


    @TestRails(id = "3492")
    @Test(groups = {"regression"})
    public void Agent_AM_CreateUser_3492() throws Exception {
        //Set isProxy = true
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Create User");
        agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 1. Create User page is displayed with down console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3493")
    @Test(groups = {"regression"})
    public void Agent_AM_CreateUser_3493() throws Exception {
        log("@title: Validate UI in Create User with Exchange Product setting for Credit Cash line");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify1. Account info section\n" +
                "- Login ID, Password, Account Status, First Name, Last Name, Mobile, and not display Level drop down\n" +
                "3 Rate Setting\n" +
                "4. Product Setting, select Exchange product\n" +
                "5 Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting\n" +
                "6. Submit and Cancel button");
        //List<String> lstLabelinAccountInfo = page.accInfoSection.getListLabelInfo();
        Assert.assertTrue(page.accInfoSection.txtLoginID.isDisplayed(), "FAILED! Login ID textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtPassword.isDisplayed(), "FAILED! Passwordtextbox does not display");
        Assert.assertTrue(page.accInfoSection.lblPasswordHint.isDisplayed(), "FAILED! Password hint icon does not display");
        Assert.assertTrue(page.accInfoSection.ddrAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(page.accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Nname Textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name Textbox does not display");
        Assert.assertTrue(page.accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobilie Textbox does not display");

        log(" 2. Display Cash Balance if account is Credit Cash and Credit Balance if account is Credit");
        Assert.assertEquals(page.cashBalanceSection.lblTitle.getText(), CASH_BALANCE, "FAILED! Cash balance title is incorrect display");
        Assert.assertEquals(page.cashBalanceSection.txtCreditInitiation.isDisplayed(), "FAILED! Credit Initiation textbox is not displayed");
        Assert.assertEquals(page.cashBalanceSection.txtFirstTimeDeposit.isDisplayed(), "FAILED! First Time Deposit textbox is not displayed");

        log(" 3 Rate Setting");
        Assert.assertEquals(page.rateSettingSection.getRateTitle(), RATE_SETTOMG, "FAILED! Rate Setting title is incorrect display");
        Assert.assertTrue(page.rateSettingSection.txtRate.isDisplayed(), "FAILED! Rate textbox is not dissplay");

        log(" 4. Product Setting, select Exchange product");
        Assert.assertEquals(page.productSettingsSection.lblProductSetting.getText(), PRODUCT_SETTINGS, "FAILED! Product Settings label is incorrect display");
        List<String> lstProducts = page.productSettingsSection.mnProduct.getListSubMenu();
        Assert.assertEquals(lstProducts, LST_PRODUCTS, "FAILED! List products tab is incorrect");

        log(" 5 Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstSport = page.productSettingsSection.getExchangeSportList();
        Assert.assertTrue(lstSport.contains("Soccer"), "FAILED! Sport list contains Soccer");
        Assert.assertTrue(lstSport.contains("Cricket"), "FAILED! Sport list contains Cricket");
        Assert.assertTrue(lstSport.contains("Tennis"), "FAILED! Sport list contains Tennis");

        List<String> lstSportBetSetting = page.productSettingsSection.betSettingSectionExchange.tblBetSetting.getHeaderNameOfRows();
        List<String> lstBestSettingColum = page.productSettingsSection.betSettingSectionExchange.tblBetSetting.getColumn(1, false);
        Assert.assertEquals(page.productSettingsSection.betSettingSectionExchange.lblBetSettingHeader.getText(), "Bet Settings", "FAILED! Bet Setting Header is incorrect");
        Assert.assertEquals(lstSportBetSetting, BET_SETTING_HEADER, "FAILED! Sport in bet setting table is incorrect display");
        Assert.assertEquals(lstBestSettingColum, BET_SETTING_COLUMN, "FAILED! The first column in BET SETTING incorrect display");

        List<String> lstTaxSettingHeader = page.productSettingsSection.taxSettingSectionExchange.tblTaxSetting.getHeaderNameOfRows();
        List<String> lstTaxSettingColumn = page.productSettingsSection.taxSettingSectionExchange.tblTaxSetting.getColumn(1, false);
        Assert.assertEquals(page.productSettingsSection.taxSettingSectionExchange.lblHeaderTitle.getText(), "Tax Settings", "FAILED! Tax setting Header is incorrect");
        Assert.assertEquals(lstTaxSettingHeader, TAX_SETTING_HEADER, "FAILED! Sport in tax setting table is incorrect display");
        Assert.assertEquals(lstTaxSettingColumn, TAX_SETTING_COLUMN, "FAILED! The first column in Tax SETTING incorrect display");

        Assert.assertEquals(page.positionTakingExchangeSection.lblHeaderTitle.getText(), "Position Taking", "FAILED! Position Taking Header is incorrect");
        List<String> lstPositionSettingHeader = page.positionTakingExchangeSection.tblPositionTaking.getHeaderNameOfRows();
        Assert.assertEquals(lstPositionSettingHeader, POSITION_SETTING_HEADER, "FAILED! Sport in Position setting table is incorrect display");

        log(" 6. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect displayed");
        Assert.assertEquals(page.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect displayed");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3494")
    @Test(groups = {"regression"})
    public void Agent_AM_CreateUser_003() throws Exception {
        log("@title: Validate UI in Create User with Exchange Game Product setting");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 2 Product Setting, select Exchange Game product ");
        page.productSettingsSection.mnProduct.clickMenu("Exchange Games");

        log("Verify Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstSportBetSetting = page.productSettingsSection.betSettingSectionExchangeGame.tblBetSetting.getHeaderNameOfRows();
        List<String> lstBestSettingColum = page.productSettingsSection.betSettingSectionExchangeGame.tblBetSetting.getColumn(1, false);
        Assert.assertEquals(page.productSettingsSection.betSettingSectionExchangeGame.lblBetSettingHeader.getText(), "Bet Settings", "FAILED! Bet Setting Header is incorrect");
        Assert.assertEquals(lstSportBetSetting, BET_AND_TAX_SETTING_HEADER_EG, "FAILED! Sport in bet setting table is incorrect display");
        Assert.assertEquals(lstBestSettingColum, BET_SETTING_COLUMN, "FAILED! The first column in BET SETTING incorrect display");

        List<String> lstTaxSettingHeader = page.productSettingsSection.taxSettingSectionExchangeGame.tblTaxSetting.getHeaderNameOfRows();
        List<String> lstTaxSettingColumn = page.productSettingsSection.taxSettingSectionExchangeGame.tblTaxSetting.getColumn(1, false);
        Assert.assertEquals(page.productSettingsSection.taxSettingSectionExchangeGame.lblHeaderTitle.getText(), "Tax Settings", "FAILED! Tax setting Header is incorrect");
        Assert.assertEquals(lstTaxSettingHeader, BET_AND_TAX_SETTING_HEADER_EG, "FAILED! Sport in tax setting table is incorrect display");
        Assert.assertEquals(lstTaxSettingColumn, TAX_SETTING_COLUMN, "FAILED! The first column in Tax SETTING incorrect display");

        Assert.assertEquals(page.positionTakingExchangeGAMESection.lblHeaderTitle.getText(), "Position Taking", "FAILED! Position Taking Header is incorrect");
        List<String> lstPositionSettingHeader = page.positionTakingExchangeGAMESection.tblPositionTaking.getHeaderNameOfRows();
        Assert.assertEquals(lstPositionSettingHeader, BET_AND_TAX_SETTING_HEADER_EG, "FAILED! Sport in Position setting table is incorrect display");

        log("Verify 2. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect displayed");
        Assert.assertEquals(page.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect displayed");


        log("INFO: Executed completely");
    }

    @TestRails(id = "3495")
    @Test(groups = {"regression"})
    public void Agent_AM_CreateUser_005() throws Exception {
        log("@title: Validate can NOT Create User if not input Login ID");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("2. Left Login ID empty and click on submit button");
        page.createUser("", "123rraqt");

        log("Verify 1. Message \"Login ID is required.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), LBL_LOGINID_REQUIRED, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_REQUIRED, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3496")
    @Test(groups = {"regression"})
    public void Agent_AM_CreateUser_006() throws Exception {
        log("@title: Validate can NOT Create User if not input Password");
        log("Step 1. Navigate Agency Management > Create User");
        String loginId = StringUtils.generateString("autoID.", 4);
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("2. Left Password empty and click on submit button");
        page.createUser(loginId, "");

        log("Verify 1. Message \"Password is required.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), LBL_PASSWORD_REQUIRED, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_REQUIRED, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3497")
    @Test(groups = {"poregression"})
    public void Agent_AM_CreateUser_012() {
        log("@title: Validate cannot create user from PO level");
        log("Step Precondition 1. Log in successfully by PO level");
        log("1. Click on Agency Management");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

        log("Verify 1. Verify there is no Create User menu in the left menu");
        Assert.assertFalse(lstMenu.contains(CREATE_USER), "FAILED! Create User not display when login PO level");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3498")
    @Test(groups = {"regression"})
    public void Agent_AM_CreateUser_3498() {
        log("@title: Validate no Security poup display when access the page");
        log("Step Precondition 1. Log in successfully by SAD/SMA level");
        log("1. Navigate to Create User page");
        agentHomePage.leftMenu.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER);

        log("Verify 1. Validate there is no security popup display for SAT");
        Assert.assertFalse(agentHomePage.isSecurityCodePopupDisplayed(_brandname), "FAILED! Security Popup is displayed");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate if input incorrect Login ID format
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     * 2. Input incorrect Login ID format
     * @expect: 1. Message "Login ID is invalid." display next to Cancel button
     */
    @TestRails(id = "688")
    @Test(groups = {"smoke"})
    public void Agent_AM_CreateUser_007() throws Exception {
        log("@title: Validate if input incorrect Login ID format");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 2. Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 2. Input incorrect Login ID format");
        page.createDownline("", "1234qwert", "");

        log("Verified 1. Message \"Login ID is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate if input incorrect Change Password format
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     * 2. Input correct Login ID and incorrect password format
     * @expect: 1. Message "Password is invalid." display next to Cancel button
     */
    @TestRails(id = "689")
    @Test(groups = {"smoke"})
    public void Agent_AM_CreateUser_008() throws Exception {
        log("@title: Validate if input incorrect Change Password format");
        log("Step 1. Navigate Agency Management > Create User");
        String password = "p@ssword";
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step  Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step  2. Input correct Login ID and incorrect password format");
        page.createUser(password);

        log("Verified 1. Message \"Password is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cannot create downline with the exist Login ID
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     * 2. Input Login ID that exist in the system and correct password then click submit
     * @expect: 1. Popup Create Downline with the message "Login ID already exist."
     */
    @TestRails(id = "690")
    @Test(groups = {"smoke"})
    @Parameters({"memberAccount"})
    public void Agent_AM_CreateUser_009(String memberAccount) throws Exception {
        log("@title: Validate cannot create downline with the exist Login ID");
        log("Step 1. Navigate Agency Management > Create User");
        String password = "1234qwer";
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step  Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 2. Input Login ID that exist in the system and correct password then click submit");
        page.createUser(memberAccount, password);

        log("Verified  1. Popup Create Downline with the message \"User code exists.\"");
        Assert.assertEquals(page.successPopup.getContentMessage(), AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_EXIST, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_EXIST, page.successPopup.getContentMessage()));

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate cannot create downline if input invalid Min Bet Setting
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     * 2. Input valid Login ID and Password
     * 3. Input invalid Min bet setting less than required
     * @expect: 1. Message "Min Bet is invalid." and the valid is highlight
     */
    @TestRails(id = "691")
    @Test(groups = {"smoke"})
    @Parameters({"currency"})
    public void Agent_AM_CreateUser_010(String currency) throws Exception {
        log("@title: Validate cannot  create downline if input invalid Min Bet Setting");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step  Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step Get list validation setting");
        List<ArrayList<String>> lstBetSettingValidation = page.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst(currency);

        String minBet = Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1)) - 1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        lstBetSetting.add(minBetLst);
        String password = "1234qwer";

        log("Step 3. Input invalid Min bet setting less than required");
        page.accInfoSection.txtPassword.sendKeys(password);
        page.productSettingsSection.betSettingSectionExchange.inputBetSetting(lstBetSetting);
        page.btnSubmit.click();

        log("Verified  1. Message \"Min Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate cannot create downline if input invalid Max Bet Setting
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     * 2. Input valid Login ID and Password
     * 3. Input invalid Max bet setting greater than required
     * @expect: 1. Message "Max Bet is invalid." and the valid is highlight
     */
    @TestRails(id = "692")
    @Test(groups = {"smoke"})
    @Parameters({"currency"})
    public void Agent_AM_CreateUser_010_1(String currency) throws Exception {
        log("@title: Validate cannot  create downline if input invalid= -Max Bet Setting");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step  Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step Get list validation setting");
        List<ArrayList<String>> lstBetSettingValidation = page.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst(currency);
        String minBet = Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1)));
        String maxBet = String.format("%.2f", Double.parseDouble(lstBetSettingValidation.get(1).get(1)) + 1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        ArrayList<String> maxBetLst = new ArrayList<String>(
                Arrays.asList(maxBet, maxBet, maxBet, maxBet, maxBet, maxBet));
        lstBetSetting.add(minBetLst);
        lstBetSetting.add(maxBetLst);
        String password = "1234qwer";


        log("Step 3. Input invalid MAx bet setting less than required");
        page.accInfoSection.txtPassword.sendKeys(password);
        page.productSettingsSection.betSettingSectionExchange.inputBetSetting(lstBetSetting);
        page.btnSubmit.click();

        log("Verified 1. Message \"Max Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_MAX_INVALID, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MAX_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate cannot create downline if  deposit/ update credit limit over the valid
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     * 2. Input valid Login ID and Password
     * 3. Input First Time Deposit value greater than the required value
     * @expect: 1. For Credit Cash line, display the message "Balance Deposit is invalid."
     */
    @TestRails(id = "693")
    @Test(groups = {"smoke"})
    @Parameters("currency")
    public void Agent_AM_CreateUser_011(String currency) throws Exception {
        log("@title: Validate cannot  create downline if  deposit/ update credit limit over the valid");
        log("Step 1. Navigate Agency Management > Create User");
        String password = "1234qwer";
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step  Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. Input First Time Deposit value greater than the required value");
        String defineFirstTimeDepositValue = String.format("%.2f", page.creditBalanceSection.getCreditLimit(currency) + 1);
        page.createUser(password, "", defineFirstTimeDepositValue);

        log("Verified  1. For Credit Cash line, display the message \"Balance Deposit is invalid.\"");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_BALANCE_DEPOSIT_INVALID, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_BALANCE_DEPOSIT_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can Create User successfully
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     * 2. Input required field and click on Submit button
     * @expect: 1. Popup Create Downline with the message "Downline was created successfully"
     * 2. Validate the popup is disappear when click on OK button
     * 3. Valid can login member site with the created account
     * 4. Verify change password page display after login
     * 5. Click on Skip Home page display
     */
    @TestRails(id = "687")
    @Test(groups = {"smoke"})
    @Parameters("password")
    public void Agent_AM_CreateUser_004(String password) throws Exception {
        log("@title: Validate can Create User successfully");
        log("Step 1. Navigate Agency Management > Create User");
        String passwordDecrypt = StringUtils.decrypt(password);
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step  Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 2. Input required field and click on Submit button");
        String loginID = page.createUser(passwordDecrypt);

        log("Verify 1. Popup Create Downline with the message \"Downline was created successfully\"");
        Assert.assertTrue(page.successPopup.isDisplayed(), "FAILED! Success popup does not display after create user");
        Assert.assertEquals(page.successPopup.getContentMessage(), AGConstant.AgencyManagement.CreateUser.MEMBER_CREATE_SUCCEESS_MESSAGE, "FAILED! Success message after create user is incorrect");

        log("Verify 2. Validate the popup is disappear when click on OK button");
        page.successPopup.close();
        Assert.assertFalse(page.successPopup.isDisplayed(), "FAILED! Create Downline popup not disappear after clicking OK button");

        log("Verify 3. Valid can login member site with the created account");
        page.logout().txtUsername.isDisplayed(1);

        /*BaseCaseFE.loginMemberviaUI(environment.getMemberSiteURL(),loginID,passwordDecrypt);
        ChangePasswordPage changePaswordPage = new ChangePasswordPage();

        log("Verify 4. Verify change password page display after login");
        Assert.assertEquals(changePaswordPage.lbltitle.getText(),String.format("Please change your password below"),"FAILED! Change password page not display when login member with new account");

        log("Verify 5. Click on Skip Home page display");
        pages.sat.tabexchange.HomePage memberHomePage = changePaswordPage.skip();
        memberagentHomePage.waitMenuLoading();
        Assert.assertTrue(memberagentHomePage.ddbMyAccount.isDisplayed(5), "ERROR: ddbMyAccount doesn't display after signed in");*/

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Security popup display when access the page
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Click on Agency Management
     * 2. Click on Create User
     * 3. Input correct security code
     * @expect: 1. Verify security popup display
     * 2. The page display when input correct security code
     */
    @Test(groups = {"smoke"})
    public void Agent_AM_CreateUser_013() throws Exception {
        log("@title: Validate cannot  create downline if  deposit/ update credit limit over the valid");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verified 1. Verify security popup display");
        Assert.assertTrue(page.securityPopup.isDisplayed(), "FAILED Security popup not display");

        log("Step  Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 2. The page display when input correct security code");
        Assert.assertEquals(page.header.lblPageTitle.getText(), AGConstant.AgencyManagement.CreateUser.TITLE_PAGE, "FAILED!Page not displayed");

        log("INFO: Executed completely");
    }
}

