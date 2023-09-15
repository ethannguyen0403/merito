package agentsite.testcase.agencymanagement;

import agentsite.pages.agentmanagement.createcompany.CreateCompany;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.AgencyManagement.CreateAccount.*;

public class CreateCompanyTest extends BaseCaseTest {
    @TestRails(id = "3657")
    @Test(groups = {"smoke_po"})
    public void Agent_AM_CreateDownline_Agent_3657() {
        log("@title: Validate UI in Create Downline Agent with Exchange Product setting");
        log("Precondition: Log in successfully by  PO");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateCompany page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());

        log("Verify 1. Validate Create Company UI when active Exchange product display correct");
        log("Verify Account Information section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.HomePage.CREATE_COMPANY, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();
        log("Verify Account Balance Transfer Condition section");
        Assert.assertEquals(page.accountBalanceTransferConditionInforSection.getAccountBalanceTransferConditionTitle(), LBL_ACCOUNT_BALANCE_TRANSFER_CONDITION);
        page.accountBalanceTransferConditionInforSection.verifyUIDisplayCorrect();
        log("Verify Credit Balance section");
        Assert.assertEquals(page.creditBalanceInforSection.getCreditSectionTitle(), LBL_CREDIT_BALANCE);
        page.creditBalanceInforSection.verifyUIDisplayCorrect();
        log("Verify Risk Setting section");
        Assert.assertEquals(page.riskSettingInforSection.getRiskSettingTitle(), LBL_RISK_SETTING);
        page.riskSettingInforSection.verifyUIDisplayCorrect();
        log("Verify Product Setting section");
        Assert.assertEquals(page.productStatusSettingInforSection.getProductSettingSectionTitle(), LBL_PRODUCT_SETTING);
        page.productStatusSettingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);
        log("Verify Bet Setting section");
        Assert.assertEquals(page.betSettingInforSection.getBetSettingSectionTitle(AGConstant.EXCHANGE), LBL_BET_SETTING);
        page.betSettingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);
        log("Verify Tax Setting section");
        Assert.assertEquals(page.taxSettingInforSection.getTaxSettingSectionTitle(AGConstant.EXCHANGE), LBL_TAX_SETTING);
        page.taxSettingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);
        log("Verify Position Taking section");
        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.EXCHANGE), LBL_POSITION_TAKING);
        page.positionTakingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE);
        log("Verify Submit and Cancel button");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertTrue(page.btnCancel.isDisplayed(), "FAILED! Cancel button does not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3658")
    @Test(groups = {"smoke_po"})
    public void Agent_AM_CreateDownline_Agent_3658() {
        log("@title: Validate Create Company UI when active Exchange Game product display correct");
        log("Precondition: Log in successfully by  PO");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateCompany page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());

        log("Step 2. Select Exchange Game Product");
        page.productStatusSettingInforSection.selectProduct(AGConstant.EXCHANGE_GAMES);

        log("Verify 1. Validate Create Company UI when active Exchange Game product display correct");
        log("Verify Account Information section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.HomePage.CREATE_COMPANY, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();
        log("Verify Account Balance Transfer Condition section");
        Assert.assertEquals(page.accountBalanceTransferConditionInforSection.getAccountBalanceTransferConditionTitle(), LBL_ACCOUNT_BALANCE_TRANSFER_CONDITION);
        page.accountBalanceTransferConditionInforSection.verifyUIDisplayCorrect();
        log("Verify Credit Balance section");
        Assert.assertEquals(page.creditBalanceInforSection.getCreditSectionTitle(), LBL_CREDIT_BALANCE);
        page.creditBalanceInforSection.verifyUIDisplayCorrect();
        log("Verify Risk Setting section");
        Assert.assertEquals(page.riskSettingInforSection.getRiskSettingTitle(), LBL_RISK_SETTING);
        page.riskSettingInforSection.verifyUIDisplayCorrect();
        log("Verify Product Setting section");
        Assert.assertEquals(page.productStatusSettingInforSection.getProductSettingSectionTitle(), LBL_PRODUCT_SETTING);
        page.productStatusSettingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE_GAMES);
        log("Verify Bet Setting section");
        Assert.assertEquals(page.betSettingInforSection.getBetSettingSectionTitle(AGConstant.EXCHANGE_GAMES), LBL_BET_SETTING);
        page.betSettingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE_GAMES);
        log("Verify Tax Setting section");
        Assert.assertEquals(page.taxSettingInforSection.getTaxSettingSectionTitle(AGConstant.EXCHANGE_GAMES), LBL_TAX_SETTING);
        page.taxSettingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE_GAMES);
        log("Verify Position Taking section");
        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.EXCHANGE_GAMES), LBL_POSITION_TAKING);
        page.positionTakingInforSection.verifyUIDisplayCorrect(AGConstant.EXCHANGE_GAMES);
        log("Verify Submit and Cancel button");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertTrue(page.btnCancel.isDisplayed(), "FAILED! Cancel button does not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3659")
    @Test(groups = {"smoke_po"})
    public void Agent_AM_CreateDownline_Agent_3659() {
        log("@title: Validate Create Company UI when active Live Dealer Asian product display correct");
        log("Precondition: Log in successfully by  PO");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateCompany page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());

        log("Step 2. Select Live Dealer Asian Product");
        page.productStatusSettingInforSection.selectProduct(AGConstant.LIVE_DEALER_ASIAN);

        log("Verify 1. Validate Create Company UI when active Live Dealer Asian product display correct");
        log("Verify Account Information section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.HomePage.CREATE_COMPANY, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();
        log("Verify Account Balance Transfer Condition section");
        Assert.assertEquals(page.accountBalanceTransferConditionInforSection.getAccountBalanceTransferConditionTitle(), LBL_ACCOUNT_BALANCE_TRANSFER_CONDITION);
        page.accountBalanceTransferConditionInforSection.verifyUIDisplayCorrect();
        log("Verify Credit Balance section");
        Assert.assertEquals(page.creditBalanceInforSection.getCreditSectionTitle(), LBL_CREDIT_BALANCE);
        page.creditBalanceInforSection.verifyUIDisplayCorrect();
        log("Verify Risk Setting section");
        Assert.assertEquals(page.riskSettingInforSection.getRiskSettingTitle(), LBL_RISK_SETTING);
        page.riskSettingInforSection.verifyUIDisplayCorrect();
        log("Verify Product Setting section");
        Assert.assertEquals(page.productStatusSettingInforSection.getProductSettingSectionTitle(), LBL_PRODUCT_SETTING);
        page.productStatusSettingInforSection.verifyUIDisplayCorrect(AGConstant.LIVE_DEALER_ASIAN);
        log("Verify Commission Setting section");
        Assert.assertEquals(page.commissionSettingSection.getCommissionSettingSectionTitle(AGConstant.LIVE_DEALER_ASIAN), LBL_COMMISSION_SETTING);
        page.commissionSettingSection.verifyUIDisplayCorrect(AGConstant.LIVE_DEALER_ASIAN);
        log("Verify Position Taking section");
        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.LIVE_DEALER_ASIAN), LBL_POSITION_TAKING);
        page.positionTakingInforSection.verifyUIDisplayCorrect(AGConstant.LIVE_DEALER_ASIAN);
        log("Verify Submit and Cancel button");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertTrue(page.btnCancel.isDisplayed(), "FAILED! Cancel button does not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3660")
    @Test(groups = {"smoke_po"})
    public void Agent_AM_CreateDownline_Agent_3660() {
        log("@title: Validate Create Company UI when active Live Dealer European product display correct");
        log("Precondition: Log in successfully by  PO");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateCompany page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());

        log("Step 2. Select Live Dealer Asian Product");
        page.productStatusSettingInforSection.selectProduct(AGConstant.LIVE_DEALER_EUROPEAN);

        log("Verify 1. Validate Create Company UI when active Live Dealer European product display correct");
        log("Verify Account Information section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.HomePage.CREATE_COMPANY, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();
        log("Verify Account Balance Transfer Condition section");
        Assert.assertEquals(page.accountBalanceTransferConditionInforSection.getAccountBalanceTransferConditionTitle(), LBL_ACCOUNT_BALANCE_TRANSFER_CONDITION);
        page.accountBalanceTransferConditionInforSection.verifyUIDisplayCorrect();
        log("Verify Credit Balance section");
        Assert.assertEquals(page.creditBalanceInforSection.getCreditSectionTitle(), LBL_CREDIT_BALANCE);
        page.creditBalanceInforSection.verifyUIDisplayCorrect();
        log("Verify Risk Setting section");
        Assert.assertEquals(page.riskSettingInforSection.getRiskSettingTitle(), LBL_RISK_SETTING);
        page.riskSettingInforSection.verifyUIDisplayCorrect();
        log("Verify Product Setting section");
        Assert.assertEquals(page.productStatusSettingInforSection.getProductSettingSectionTitle(), LBL_PRODUCT_SETTING);
        page.productStatusSettingInforSection.verifyUIDisplayCorrect(AGConstant.LIVE_DEALER_EUROPEAN);
        log("Verify Commission Setting section");
        Assert.assertEquals(page.commissionSettingSection.getCommissionSettingSectionTitle(AGConstant.LIVE_DEALER_EUROPEAN), LBL_COMMISSION_SETTING);
        page.commissionSettingSection.verifyUIDisplayCorrect(AGConstant.LIVE_DEALER_EUROPEAN);
        log("Verify Position Taking section");
        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.LIVE_DEALER_EUROPEAN), LBL_POSITION_TAKING);
        page.positionTakingInforSection.verifyUIDisplayCorrect(AGConstant.LIVE_DEALER_EUROPEAN);
        log("Verify Submit and Cancel button");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertTrue(page.btnCancel.isDisplayed(), "FAILED! Cancel button does not display");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3661")
    @Test(groups = {"smoke_po"})
    public void Agent_AM_CreateDownline_Agent_3661() {
        log("@title: Validate Create Company UI when active Lottery and Slot product display correct");
        log("Precondition: Log in successfully by  PO");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateCompany page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());

        log("Step 2. Select Live Dealer Asian Product");
        page.accountInforSection.selectCurrency("INR");
        page.productStatusSettingInforSection.selectProduct(AGConstant.LOTTERY_SLOT);

        log("Verify 1. Validate Create Company UI when active Live Dealer European product display correct");
        log("Verify Account Information section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.HomePage.CREATE_COMPANY, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();
        log("Verify Account Balance Transfer Condition section");
        Assert.assertEquals(page.accountBalanceTransferConditionInforSection.getAccountBalanceTransferConditionTitle(), LBL_ACCOUNT_BALANCE_TRANSFER_CONDITION);
        page.accountBalanceTransferConditionInforSection.verifyUIDisplayCorrect();
        log("Verify Credit Balance section");
        Assert.assertEquals(page.creditBalanceInforSection.getCreditSectionTitle(), LBL_CREDIT_BALANCE);
        page.creditBalanceInforSection.verifyUIDisplayCorrect();
        log("Verify Risk Setting section");
        Assert.assertEquals(page.riskSettingInforSection.getRiskSettingTitle(), LBL_RISK_SETTING);
        page.riskSettingInforSection.verifyUIDisplayCorrect();
        log("Verify Product Setting section");
        Assert.assertEquals(page.productStatusSettingInforSection.getProductSettingSectionTitle(), LBL_PRODUCT_SETTING);
        page.productStatusSettingInforSection.verifyUIDisplayCorrect(AGConstant.LOTTERY_SLOT);
        log("Verify Commission Setting section");
        Assert.assertEquals(page.commissionSettingSection.getCommissionSettingSectionTitle(AGConstant.LOTTERY_SLOT), LBL_COMMISSION_SETTING);
        page.commissionSettingSection.verifyUIDisplayCorrect(AGConstant.LOTTERY_SLOT);
        log("Verify Position Taking section");
        Assert.assertEquals(page.positionTakingInforSection.getPositionTakingSectionTitle(AGConstant.LOTTERY_SLOT), LBL_POSITION_TAKING);
        page.positionTakingInforSection.verifyUIDisplayCorrect(AGConstant.LOTTERY_SLOT);
        log("Verify Submit and Cancel button");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertTrue(page.btnCancel.isDisplayed(), "FAILED! Cancel button does not display");
        log("INFO: Executed completely");
    }

}

