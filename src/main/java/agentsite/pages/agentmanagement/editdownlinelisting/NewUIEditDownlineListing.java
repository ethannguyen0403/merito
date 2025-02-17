package agentsite.pages.agentmanagement.editdownlinelisting;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.DownLineListingPage;
import com.paltech.element.common.Button;
import common.AGConstant;
import org.testng.Assert;

import java.util.List;

import static common.AGConstant.HomePage.DOWNLINE_LISTING;

public class NewUIEditDownlineListing extends EditDownlineListing {
    private int totalTaxSettingsColumns = 9;
    private int totalPositionTakingColumns = 13;
    private Button btnCloseAlert = Button.xpath("//app-alert//button[@class='close']");
    private Table tblTaxSettings = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table[contains(@class,'betTable')]", totalTaxSettingsColumns);
    private Table tblPositionTakingListing = Table.xpath("//div[@id='EXCHANGE-position-taking']//table[contains(@class,'ptable info betTable')]", totalPositionTakingColumns);
    public NewUIEditDownlineListing(String types) {
        super(types);
    }

    public void clickSubmit() {
        btnSubmit.click();
        waitingLoadingSpinner();
        btnCloseAlert.click();
        waitingLoadingSpinner();
    }
    public DownLineListingPage closeEditDownlinePopup() {
        btnClosePopup.click();
        return new DownLineListingPage(_type);
    }

    public void verifyUIDisplayCorrect() {
        Assert.assertEquals(header.lblPageTitle.getText().trim(), DOWNLINE_LISTING, "Failed! Page title is incorrect");
        List<String> lstInfo = accountInforSection.getListLabelEditDownlineInfo();
        Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.LBL_LOGINID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Last Name label display incorrect");
        Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_PHONE, "FAILED! Phone display incorrect");
        Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
        Assert.assertEquals(lstInfo.get(7), AGConstant.AgencyManagement.CreateAccount.LBL_EMAIL, "FAILED! Email display incorrect");
        Assert.assertEquals(lstInfo.get(8), AGConstant.AgencyManagement.CreateAccount.LBL_FAX, "FAILED! Fax display incorrect");
        Assert.assertEquals(lstInfo.get(9), AGConstant.AgencyManagement.CreateAccount.LBL_ALLOW_PARTIAL_TRANSFER, "FAILED! Allow Partial Transfer display incorrect");
        Assert.assertEquals(lstInfo.get(10), AGConstant.AgencyManagement.CreateAccount.LBL_BASE_CURRENCY, "FAILED! Base Currency display incorrect");
        Assert.assertEquals(lstInfo.get(11), AGConstant.AgencyManagement.CreateAccount.LBL_ALLOW_PL_EXTRA, "FAILED! Allow PL Extra PT display incorrect");
        Assert.assertTrue(accountInforSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
        Assert.assertTrue(accountInforSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(accountInforSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(accountInforSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
        Assert.assertTrue(accountInforSection.txtPhone.isDisplayed(), "FAILED! Phone textbox does not display");
        Assert.assertTrue(accountInforSection.txtFax.isDisplayed(), "FAILED! Tax textbox does not display");

        Assert.assertEquals(lblProductSetting.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_PRODUCT_SETTING,"FAILED! Product Setting Section display incorrect");

        List<String> lstBetSettingHeader = tblBetSettings.getHeaderNameOfRows();
        List<String> lstBetSettingOption = tblBetSettings.getColumn(1,false);
        List<String> lstTaxSettingHeader = tblTaxSettings.getHeaderNameOfRows();
        List<String> lstTaxSettingOption = tblTaxSettings.getColumn(1,false);
        List<String> lstPositionTakingHeader = tblPositionTakingListing.getHeaderNameOfRows();
        Assert.assertEquals(lblBetSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_BET_SETTING,"FAILED! Bet Setting Section Label display incorrect");
        Assert.assertEquals(lstBetSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_HEADER,"FAILED! Bet Setting Header does not display as expected");
        Assert.assertEquals(lstBetSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_BET_SETTING_OPTION,"FAILED! Bet Setting options in the first column does not display as expected");

        Assert.assertEquals(lblTaxSettings.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_TAX_SETTING,"FAILED! Tax Setting Section Label display incorrect");
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_HEADER_NEWUI,"FAILED! Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(lblPositionTakingListing.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_POSITION_TAKING_SPORT_HEADER,"FAILED! Position Taking Header does not display as expected");

        Assert.assertEquals(btnSubmit.getText(), AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(btnCancel.getText(), AGConstant.BTN_CANCEL,"FAILED! Cancel button display incorrect");
    }

}
