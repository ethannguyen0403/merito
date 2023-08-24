package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import common.AGConstant;
import agentsite.pages.agentmanagement.CreateUserPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.*;

import static common.AGConstant.AgencyManagement.CreateUser.*;
import static common.AGConstant.BTN_CANCEL;
import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.HomePage.*;

public class CreateUserTest extends BaseCaseTest {


    @TestRails(id = "3492")
    @Test(groups = {"http_request"})
    public void Agent_AM_CreateUser_3492() {
        //Set isProxy = true
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Create User");
        agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Verify 1. Create User page is displayed with down console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3493")
    @Test(groups = {"regression_oldui"})
    public void Agent_AM_CreateUser_3493() {
        //login Control blocking level + Cash
        log("@title: Validate UI in Create User with Exchange Product setting for Credit Cash line");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Verify1. Account info section\n" +
                "- Login ID, Password, Account Status, First Name, Last Name, Mobile, and not display Level drop down\n" +
                "3 Rate Setting\n" +
                "4. Product Setting, select Exchange product\n" +
                "5 Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting\n" +
                "6. Submit and Cancel button");
        Assert.assertEquals(page.accountInforSection.getAccountInforSectionTitle(), TITLE_PAGE);
        Assert.assertTrue(page.accountInforSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");

        log(" 2. Display Cash Balance if account is Credit Cash and Credit Balance if account is Credit");
        Assert.assertEquals(page.cashBalanceInforSection.getCashSectionTitle().trim(), CASH_BALANCE, "FAILED! Cash balance title is incorrect display");
        Assert.assertTrue(page.cashBalanceInforSection.txtFirstTimeDeposit.isDisplayed(), "FAILED! Credit Initiation textbox is not displayed");

        log(" 4. Product Setting, select Exchange product");
        Assert.assertEquals(page.productSettingInforSection.getProductSettingSectionTitle(), PRODUCT_SETTINGS, "FAILED! Product Settings label is incorrect display");
//        List<String> lstProducts = page.productSettingInforSection.mnProductSetting.getListSubMenu();
//        Assert.assertEquals(lstProducts.stream().map(String::toLowerCase).collect(Collectors.toList()), LST_PRODUCTS.stream().map(String::toLowerCase).collect(Collectors.toList()), "FAILED! List products tab is incorrect");

        log(" 5 Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstSport = page.productSettingInforSection.getExchangeSportList();
        Assert.assertTrue(lstSport.contains("Soccer"), "FAILED! Sport list contains Soccer");
        Assert.assertTrue(lstSport.contains("Cricket"), "FAILED! Sport list contains Cricket");
        Assert.assertTrue(lstSport.contains("Tennis"), "FAILED! Sport list contains Tennis");

        List<String> lstSportBetSetting = page.betSettingInforSection.tblBetSettingEX.getHeaderNameOfRows();
        List<String> lstBetSettingColum = page.betSettingInforSection.tblBetSettingEX.getColumn(1, false);
        Assert.assertEquals(page.betSettingInforSection.getBetSettingSectionTitle(AGConstant.EXCHANGE).trim(), BET_SETTING, "FAILED! Bet Setting Header is incorrect");
        Assert.assertEquals(lstSportBetSetting, EX_BET_SETTING_HEADER, "FAILED! Sport in bet setting table is incorrect display");
        Assert.assertEquals(lstBetSettingColum, BET_SETTING_COLUMN, "FAILED! The first column in BET SETTING incorrect display");

        List<String> lstTaxSettingHeader = page.taxSettingInforSection.tblTaxSettingEX.getHeaderNameOfRows();
        List<String> lstTaxSettingColumn = page.taxSettingInforSection.tblTaxSettingEX.getColumn(1, false);
        Assert.assertEquals(page.taxSettingInforSection.getTaxSettingSectionTitle(AGConstant.EXCHANGE).trim(), TAX_SETTING, "FAILED! Tax setting Header is incorrect");
        Assert.assertEquals(lstTaxSettingHeader, EX_TAX_SETTING_HEADER_SAT, "FAILED! Sport in tax setting table is incorrect display");
        Assert.assertEquals(lstTaxSettingColumn, TAX_SETTING_COLUMN, "FAILED! Column in Tax SETTING incorrect display");

        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.EXCHANGE), POSITION_TAKING_SETTING, "FAILED! Position Taking Header is incorrect");
        List<String> lstPositionSettingHeader = page.positionTakingInforSection.tblPositionTakingEX.getHeaderNameOfRows();
        Assert.assertEquals(lstPositionSettingHeader, EX_POSITION_SETTING_HEADER, "FAILED! Sport in Position setting table is incorrect display");

        log(" 6. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect displayed");
        Assert.assertEquals(page.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect displayed");

        log("INFO: Executed completely");
    }
    @TestRails(id = "3985")
    @Test(groups = {"regression_newui"})
    public void Agent_AM_CreateUser_3985() {
        //login Control blocking level + Cash
        log("@title: Validate UI in Create User with Exchange Product setting for Credit Cash line");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Verify1. Account info section\n" +
                "- Login ID, Password, Account Status, First Name, Last Name, Mobile, and not display Level drop down\n" +
                "3 Rate Setting\n" +
                "4. Product Setting, select Exchange product\n" +
                "5 Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting\n" +
                "6. Submit and Cancel button");
        Assert.assertEquals(page.accountInforSection.getAccountInforSectionTitle(), TITLE_PAGE);
        Assert.assertTrue(page.accountInforSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");

        log(" 2. Display Cash Balance if account is Credit Cash and Credit Balance if account is Credit");
        Assert.assertEquals(page.cashBalanceInforSection.getCashSectionTitle().trim(), CASH_BALANCE, "FAILED! Cash balance title is incorrect display");
        Assert.assertTrue(page.cashBalanceInforSection.txtFirstTimeDeposit.isDisplayed(), "FAILED! Credit Initiation textbox is not displayed");

        log(" 4. Product Setting, select Exchange product");
        Assert.assertEquals(page.productSettingInforSection.getProductSettingSectionTitle(), PRODUCT_SETTINGS, "FAILED! Product Settings label is incorrect display");
//        List<String> lstProducts = page.productSettingInforSection.mnProductSetting.getListSubMenu();
//        Assert.assertEquals(lstProducts.stream().map(String::toLowerCase).collect(Collectors.toList()), LST_PRODUCTS.stream().map(String::toLowerCase).collect(Collectors.toList()), "FAILED! List products tab is incorrect");

        log(" 5 Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstSport = page.productSettingInforSection.getExchangeSportList();
        Assert.assertTrue(lstSport.contains("Soccer"), "FAILED! Sport list contains Soccer");
        Assert.assertTrue(lstSport.contains("Cricket"), "FAILED! Sport list contains Cricket");
        Assert.assertTrue(lstSport.contains("Tennis"), "FAILED! Sport list contains Tennis");

        List<String> lstSportBetSetting = page.betSettingInforSection.tblBetSettingEX.getHeaderNameOfRows();
        List<String> lstBetSettingColum = page.betSettingInforSection.tblBetSettingEX.getColumn(1, false);
        Assert.assertEquals(page.betSettingInforSection.getBetSettingSectionTitle(AGConstant.EXCHANGE).trim(), BET_SETTING, "FAILED! Bet Setting Header is incorrect");
        Assert.assertEquals(lstSportBetSetting, EX_BET_SETTING_HEADER, "FAILED! Sport in bet setting table is incorrect display");
        Assert.assertEquals(lstBetSettingColum, BET_SETTING_COLUMN, "FAILED! The first column in BET SETTING incorrect display");

        List<String> lstTaxSettingHeader = page.taxSettingInforSection.tblTaxSettingEX.getHeaderNameOfRows();
        List<String> lstTaxSettingColumn = page.taxSettingInforSection.tblTaxSettingEX.getColumn(1, false);
        Assert.assertEquals(page.taxSettingInforSection.getTaxSettingSectionTitle(AGConstant.EXCHANGE).trim(), TAX_SETTING, "FAILED! Tax setting Header is incorrect");
        Assert.assertEquals(lstTaxSettingHeader, EX_TAX_SETTING_HEADER, "FAILED! Sport in tax setting table is incorrect display");
        Assert.assertEquals(lstTaxSettingColumn, TAX_SETTING_COLUMN, "FAILED! Column in Tax SETTING incorrect display");

        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.EXCHANGE), POSITION_TAKING_SETTING, "FAILED! Position Taking Header is incorrect");
        List<String> lstPositionSettingHeader = page.positionTakingInforSection.tblPositionTakingEX.getHeaderNameOfRows();
        Assert.assertEquals(lstPositionSettingHeader, EX_POSITION_SETTING_HEADER, "FAILED! Sport in Position setting table is incorrect display");

        log(" 6. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect displayed");
        Assert.assertEquals(page.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect displayed");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3494")
    @Test(groups = {"regression_oldui"})
    public void Agent_AM_CreateUser_3494() {
        log("@title: Validate UI in Create User with Exchange Game Product setting");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step 2 Product Setting, select Exchange Game product ");
        page.productSettingInforSection.mnProductSetting.clickMenu(AGConstant.EXCHANGE_GAMES);

        Assert.assertEquals(page.productSettingInforSection.getProductSettingSectionTitle(), PRODUCT_SETTINGS, "FAILED! Product Settings label is incorrect display");
//        List<String> lstProducts = page.productSettingInforSection.mnProductSetting.getListSubMenu();
//        Assert.assertEquals(lstProducts.stream().map(String::toLowerCase).collect(Collectors.toList()), LST_PRODUCTS.stream().map(String::toLowerCase).collect(Collectors.toList()), "FAILED! List products tab is incorrect");

        log("Verify Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstSportBetSetting = page.betSettingInforSection.tblBetSettingEG.getHeaderNameOfRows();
        List<String> lstBetSettingColum = page.betSettingInforSection.tblBetSettingEG.getColumn(1, false);
        Assert.assertEquals(page.betSettingInforSection.getBetSettingSectionTitle(AGConstant.EXCHANGE_GAMES).trim(), BET_SETTING, "FAILED! Bet Setting Header is incorrect");
        Assert.assertEquals(lstSportBetSetting, EG_BET_TAX_PT_SETTING_HEADER_SAT, "FAILED! Sport in bet setting table is incorrect display");
        Assert.assertEquals(lstBetSettingColum, BET_SETTING_COLUMN, "FAILED! The first column in BET SETTING incorrect display");

        List<String> lstTaxSettingHeader = page.taxSettingInforSection.tblTaxSettingEG.getHeaderNameOfRows();
        List<String> lstTaxSettingColumn = page.taxSettingInforSection.tblTaxSettingEG.getColumn(1, false);
        Assert.assertEquals(page.taxSettingInforSection.getTaxSettingSectionTitle(AGConstant.EXCHANGE_GAMES).trim(), TAX_SETTING, "FAILED! Tax setting Header is incorrect");
        Assert.assertEquals(lstTaxSettingHeader, EG_BET_TAX_PT_SETTING_HEADER_SAT, "FAILED! Sport in tax setting table is incorrect display");
        Assert.assertEquals(lstTaxSettingColumn, TAX_SETTING_COLUMN, "FAILED! Column in Tax SETTING incorrect display");

        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.EXCHANGE_GAMES), POSITION_TAKING_SETTING, "FAILED! Position Taking Header is incorrect");
        List<String> lstPositionSettingHeader = page.positionTakingInforSection.tblPositionTakingEG.getHeaderNameOfRows();
        Assert.assertEquals(lstPositionSettingHeader, EG_BET_TAX_PT_SETTING_HEADER_SAT, "FAILED! Sport in Position setting table is incorrect display");

        log("Verify 2. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect displayed");
        Assert.assertEquals(page.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect displayed");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3986")
    @Test(groups = {"regression_newui"})
    public void Agent_AM_CreateUser_3986() {
        log("@title: Validate UI in Create User with Exchange Game Product setting");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step 2 Product Setting, select Exchange Game product ");
        page.productSettingInforSection.mnProductSetting.clickMenu(AGConstant.EXCHANGE_GAMES);

        Assert.assertEquals(page.productSettingInforSection.getProductSettingSectionTitle(), PRODUCT_SETTINGS, "FAILED! Product Settings label is incorrect display");
//        List<String> lstProducts = page.productSettingInforSection.mnProductSetting.getListSubMenu();
//        Assert.assertEquals(lstProducts.stream().map(String::toLowerCase).collect(Collectors.toList()), LST_PRODUCTS.stream().map(String::toLowerCase).collect(Collectors.toList()), "FAILED! List products tab is incorrect");

        log("Verify Verify Sport setting, Bet Settings, Tax Setting. Position Taking Setting");
        List<String> lstSportBetSetting = page.betSettingInforSection.tblBetSettingEG.getHeaderNameOfRows();
        List<String> lstBetSettingColum = page.betSettingInforSection.tblBetSettingEG.getColumn(1, false);
        Assert.assertEquals(page.betSettingInforSection.getBetSettingSectionTitle(AGConstant.EXCHANGE_GAMES).trim(), BET_SETTING, "FAILED! Bet Setting Header is incorrect");
        Assert.assertEquals(lstSportBetSetting, EG_BET_TAX_PT_SETTING_HEADER, "FAILED! Sport in bet setting table is incorrect display");
        Assert.assertEquals(lstBetSettingColum, BET_SETTING_COLUMN, "FAILED! The first column in BET SETTING incorrect display");

        List<String> lstTaxSettingHeader = page.taxSettingInforSection.tblTaxSettingEG.getHeaderNameOfRows();
        List<String> lstTaxSettingColumn = page.taxSettingInforSection.tblTaxSettingEG.getColumn(1, false);
        Assert.assertEquals(page.taxSettingInforSection.getTaxSettingSectionTitle(AGConstant.EXCHANGE_GAMES).trim(), TAX_SETTING, "FAILED! Tax setting Header is incorrect");
        Assert.assertEquals(lstTaxSettingHeader, EG_BET_TAX_PT_SETTING_HEADER, "FAILED! Sport in tax setting table is incorrect display");
        Assert.assertEquals(lstTaxSettingColumn, TAX_SETTING_COLUMN, "FAILED! Column in Tax SETTING incorrect display");

        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.EXCHANGE_GAMES), POSITION_TAKING_SETTING, "FAILED! Position Taking Header is incorrect");
        List<String> lstPositionSettingHeader = page.positionTakingInforSection.tblPositionTakingEG.getHeaderNameOfRows();
        Assert.assertEquals(lstPositionSettingHeader, EG_BET_TAX_PT_SETTING_HEADER, "FAILED! Sport in Position setting table is incorrect display");

        log("Verify 2. Submit and Cancel button");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit button is incorrect displayed");
        Assert.assertEquals(page.btnCancel.getText(), BTN_CANCEL, "FAILED! Submit button is incorrect displayed");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3495")
    @Test(groups = {"regression_oldui"})
    public void Agent_AM_CreateUser_3495() {
        log("@title: Validate can NOT Create User if not input Login ID");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("2. Left Login ID empty and click on submit button");
        page.createUserPage.createUser("", "123rraqt");

        log("Verify 1. Message \"Login ID is required.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), LBL_LOGINID_REQUIRED, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_REQUIRED, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3496")
    @Test(groups = {"regression"})
    public void Agent_AM_CreateUser_3496() {
        log("@title: Validate can NOT Create User if not input Password");
        log("Step 1. Navigate Agency Management > Create User");
        String loginId = StringUtils.generateAlphabetic( 10);
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("2. Left Password empty and click on submit button");
        page.createUserPage.createUser(loginId, "");

        log("Verify 1. Message \"Password is required.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), LBL_PASSWORD_REQUIRED, String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_REQUIRED, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3497")
    @Test(groups = {"regression_po"})
    public void Agent_AM_CreateUser_3497() {
        //login level = PO
        log("@title: Validate cannot create user from PO level");
        log("Step Precondition 1. Log in successfully by PO level");
        log("1. Click on Agency Management");
        agentHomePage.leftMenu.leftMenuList.expandMenu(AGENCY_MANAGEMENT);
        List<String> lstMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(AGENCY_MANAGEMENT);

        log("Verify 1. Verify there is no Create User menu in the left menu");
        Assert.assertFalse(lstMenu.contains(CREATE_USER), "FAILED! Create User display when login PO level");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3498")
    @Test(groups = {"regression_oldui"})
    public void Agent_AM_CreateUser_3498() {
        //login level != PO
        log("@title: Validate no Security poup display when access the page - SAT");
        log("Step Precondition 1. Log in successfully by SAD/SMA level");
        log("1. Navigate to Create User page");
        agentHomePage.leftMenu.clickSubMenu(AGENCY_MANAGEMENT,CREATE_USER);

        log("Verify 1. Validate there is no security popup display for SAT");
        Assert.assertFalse(agentHomePage.securityPopup.isDisplayed(),"FAILED! Security Popup is displayed");
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
    @Test(groups = {"smoke_sat"})
    public void Agent_AM_CreateUser_688() {
        log("@title: Validate if input incorrect Login ID format");
        log("Step 1. Navigate Agency Management > Create User");
        CreateUserPage page =  agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step 2. Input incorrect Login ID format");
        page.createDownline("auto_sad","1234qwert","");

        log("Verified 1. Message \"Login ID is invalid.\" display next to Cancel button");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate if input incorrect Change Password format
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Create User
     *          2. Input correct Login ID and incorrect password format
     * @expect: 1. Message "Password is invalid." display next to Cancel button
     */
    @TestRails(id = "689")
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateUser_689() {
        log("@title: Validate if input incorrect Change Password format");
        log("Step 1. Navigate Agency Management > Create User");
        String password = "p@ssword";
        String loginId = StringUtils.generateString("autoID.", 10);
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step  Enter security code");
        page.confirmSecurityCode(environment.getSecurityCode());

        log("Step  2. Input correct Login ID and incorrect password format");
        page.createUserPage.createUser(loginId, password);

        log("Verified 1. Message \"Password is invalid.\" display next to Cancel button");
        Assert.assertTrue(page.lblErrorMsg.getText().contains(AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID),String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_PASSWORD_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cannot create downline with the exist Login ID
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Create User
     *          2. Input Login ID that exist in the system and correct password then click submit
     * @expect: 1. Popup Create Downline with the message "Login ID already exist."
     */
    @TestRails(id = "690")
    @Test (groups = {"smoke_sat"})
    @Parameters({"memberAccount"})
    public void Agent_AM_CreateUser_690(String memberAccount) {
        log("@title: Validate cannot create downline with the exist Login ID");
        log("Step 1. Navigate Agency Management > Create User");
        String password = "1234qwer";
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step  Enter security code");
        page.confirmSecurityCode(environment.getSecurityCode());

        log("Step 2. Input Login ID that exist in the system and correct password then click submit");
        page.createUserPage.createUser(memberAccount,password);

        log("Verified  1. Popup Create Downline with the message \"User code exists.\"");
        Assert.assertEquals(page.successPopup.getContentMessage(), AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_EXIST,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_LOGINID_EXIST,page.successPopup.getContentMessage()));

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate cannot create downline if input invalid Min Bet Setting
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     *         2. Input valid Login ID and Password
     *         3. Input invalid Min bet setting less than required
     * @expect: 1. Message "Min Bet is invalid." and the valid is highlight
     */
    @TestRails(id = "691")
    @Test (groups = {"smoke"})
    @Parameters({"currency"})
    public void Agent_AM_CreateUser_691(String currency) {
        log("@title: Validate cannot  create downline if input invalid Min Bet Setting");
        log("Step 1. Navigate Agency Management > Create User");
       CreateUserPage page =  agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step Get list validation setting");
        List<ArrayList<String>> lstBetSettingValidation = page.betSettingInforSection.getBetSettingValidationValueLst(currency);
//        page.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst(currency);

        String minBet =Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1))-1);
        List<ArrayList<String>> lstBetSetting = new ArrayList<>();
        ArrayList<String> minBetLst = new ArrayList<String>(
                Arrays.asList(minBet, minBet, minBet, minBet, minBet, minBet));
        lstBetSetting.add(minBetLst);
        String password = "1234qwer";

        log("Step 3. Input invalid Min bet setting less than required");
        page.accInfoSection.txtPassword.sendKeys(password);
        page.betSettingInforSection.inputBetSetting(lstBetSetting);
//        page.productSettingsSection.betSettingSectionExchange.inputBetSetting(lstBetSetting);
        page.btnSubmit.click();

        log("Verified  1. Message \"Min Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MIN_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate cannot create downline if input invalid Max Bet Setting
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     *         2. Input valid Login ID and Password
     *         3. Input invalid Max bet setting greater than required
     *
     * @expect: 1. Message "Max Bet is invalid." and the valid is highlight
     */
    @TestRails(id = "692")
    @Test (groups = {"smoke"})
    @Parameters({"currency"})
    public void Agent_AM_CreateUser_692(String currency) {
        log("@title: Validate cannot  create downline if input invalid= -Max Bet Setting");
        log("Step 1. Navigate Agency Management > Create User");
       CreateUserPage page =  agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step Get list validation setting");
        List<ArrayList<String>> lstBetSettingValidation = page.betSettingInforSection.getBetSettingValidationValueLst(currency);
//        page.productSettingsSection.betSettingSectionExchange.getBetSettingValidationValueLst(currency);
        String minBet = Integer.toString(Integer.parseInt(lstBetSettingValidation.get(0).get(1)));
        String maxBet = Double.toString(Double.parseDouble(lstBetSettingValidation.get(1).get(1).replace(",",""))+1);
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
        page.betSettingInforSection.inputBetSetting(lstBetSetting);
//        page.productSettingsSection.betSettingSectionExchange.inputBetSetting(lstBetSetting);
        page.btnSubmit.click();

        log("Verified 1. Message \"Max Bet is invalid.\" and the valid is highlight");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_MAX_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_MAX_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate cannot create downline if  deposit/ update credit limit over the valid
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Create User
     *         2. Input valid Login ID and Password
     *        3. Input First Time Deposit value greater than the required value
     * @expect: 1. For Credit Cash line, display the message "Balance Deposit is invalid."
     *
     */
    @TestRails(id = "693")
    @Test (groups = {"smoke"})
    @Parameters("currency")
    public void Agent_AM_CreateUser_693(String currency) {
        log("@title: Validate cannot  create downline if  deposit/ update credit limit over the valid");
        log("Step 1. Navigate Agency Management > Create User");
        String password = "1234qwer";
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step 3. Input First Time Deposit value greater than the required value");
        String defineFirstTimeDepositValue = String.format("%.2f",page.creditBalanceInforSection.getCreditLimit(currency) + 1);
        page.createUserPage.createUserWithDeposit("autoId", password,"",defineFirstTimeDepositValue);

        log("Verified  1. For Credit Cash line, display the message \"Balance Deposit is invalid.\"");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.CreateUser.LBL_BALANCE_DEPOSIT_INVALID,String.format("FAILED! Expected error message is %s but found", AGConstant.AgencyManagement.CreateUser.LBL_BALANCE_DEPOSIT_INVALID, page.lblErrorMsg.getText()));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can Create User successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Create User
     *          2. Input required field and click on Submit button
     * @expect: 1. Popup Create Downline with the message "Downline was created successfully"
     *          2. Validate the popup is disappear when click on OK button
     *          3. Valid can login member site with the created account
     *          4. Verify change password page display after login
     *          5. Click on Skip Home page display
     */
    @TestRails(id = "687")
    @Test (groups = {"smoke"})
    @Parameters("password")
    public void Agent_AM_CreateUser_687(String password) throws Exception {
        log("@title: Validate can Create User successfully");
        log("Step 1. Navigate Agency Management > Create User");
        String passwordDecrypt = StringUtils.decrypt(password);
        String loginId = RandomStringUtils.randomAlphabetic(10);

        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Step 2. Input required field and click on Submit button");
        String loginID = page.createUserPage.createUser(loginId,passwordDecrypt);

        log("Verify 1. Popup Create Downline with the message \"Downline was created successfully\"");
        Assert.assertTrue(page.successPopup.isDisplayed(),"FAILED! Success popup does not display after create user");
        Assert.assertTrue(page.createUserPage.isCreateUserSuccessCorrect(),"FAILED! Success message after create user is incorrect");

        log("Verify 2. Validate the popup is disappear when click on OK button");
        page.successPopup.close();
        Assert.assertFalse(page.successPopup.isDisplayed(),"FAILED! Create Downline popup not disappear after clicking OK button");

//        log("Verify 3. Valid can login member site with the created account");
//        page.logout().txtUsername.isDisplayed(1);

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
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Click on Agency Management
     * 2. Click on Create User
     * 3. Input correct security code
     * @expect: 1. Verify security popup display
     * 2. The page display when input correct security code
     *
     */
    @Test (groups = {"smoke_newui"})
    public void Agent_AM_CreateUser_013() {
        log("@title: Validate cannot  create downline if  deposit/ update credit limit over the valid");
        log("Step 1. Navigate Agency Management > Create User");
        agentHomePage.leftMenu.clickSubMenu(AGENCY_MANAGEMENT, CREATE_USER);

        log("Verified 1. Verify security popup display");
        Assert.assertTrue(agentHomePage.securityPopup.isDisplayed(),"FAILED Security popup not display");

        log("Step  Enter security code");
        agentHomePage.confirmSecurityCode(environment.getSecurityCode());

        log("Verify 2. The page display when input correct security code");
        Assert.assertEquals(agentHomePage.header.lblPageTitle.getText(), AGConstant.AgencyManagement.CreateUser.TITLE_PAGE,"FAILED!Page not displayed");

        log("INFO: Executed completely");
    }
    @TestRails(id = "676")
    @Test(groups = {"smoke"})
    @Parameters({"level", "levelLogin"})
    public void Agent_AM_Downline_Listing_Edit_Agent_696(String level, String levelLogin) throws Exception {
        log("@title: Validate there Cannot update if Max Player Credit exceed the limit");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, level, "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any agent");
        page.downlineListing.searchDownline(loginID, "", "Agent");
        page.downlineListing.clickEditIcon(loginID);

        log("Verify 1. Verify Security Code popup prompted");
        Assert.assertTrue(page.securityPopup.isDisplayed(), "FAILED Security popup not display");

        log("Step  Enter security code");
        page.confirmSecurityCode(environment.getSecurityCode());

        log("Verify 2. Verify page display if input valid security code");
        Assert.assertEquals(page.editDownlinePopup.getTitle(), String.format("Edit %s", levelLogin), "FAILED!Page not displayed");
        log("INFO: Executed completely");
    }
    @TestRails(id = "679")
    @Test(groups = {"smoke_credit"})
    @Parameters({"password", "downlineLevel"})
    public void Agent_AM_Downline_Listing_Edit_Agent_679(String password, String downlineLevel) throws Exception {
        log("@title: Validate Max Player Credit setting display correctly when create user");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any agent");
        page.downlineListing.searchDownline(loginID, "", "");
        page.downlineListing.clickEditIcon(loginID);
        page.confirmSecurityCode(environment.getSecurityCode());

        log("Step 3. Input valid Max Player Credit and valid other information then click submit");
        String maxPlayerCreditLitmit = "1";
        page.editDownlinePopup.balanceSection.updateCashBalance(maxPlayerCreditLitmit);
        page.editDownlinePopup.btnSubmit.click();
        String message = page.downlineListing.getMessageUpdate(true);

        log("Verify 1. Verify can update agent with valid max player credit");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_DOWNLINE_SUCCESS, "FAILED, Success updating downline message not display");
        agentHomePage.logout();

        log("Step 4. Login agent with the agent in step 2");
        loginAgent(sosAgentURL, agentSecurityCodeURL, loginID, password, environment.getSecurityCode());

        log("5. Select Agency Management > Create Downline agent");
        CreateDownLineAgentPage createAgentPage = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Verify 2. Verify Max Player Credit display correctly as setting in First Time Deposit limit section");
        Assert.assertEquals(Integer.toString(createAgentPage.creditBalanceSection.getMaxPlayerLitmitCredit()), maxPlayerCreditLitmit, "FAILED! Max player credit not match with the setting");

        log("INFO: Executed completely");
    }
}

