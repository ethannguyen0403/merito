package agentsite.pages.agentmanagement.editdownlinelisting;

import agentsite.controls.Table;
import agentsite.pages.agentmanagement.DownLineListingPage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import common.AGConstant;
import org.testng.Assert;
import java.util.List;

public class OldUIEditDownlineListing extends EditDownlineListing {
    private TextBox txtEmail = TextBox.xpath("//input[contains(@class,'extended-email')]");
    private int totalTaxSettingsColumns = 7;
    private int totalPositionTakingColumns = 9;
    private Button btnCloseAlert = Button.xpath("//app-comfirm//button[@class='close']");
    private Table tblTaxSettings = Table.xpath("//div[@id='EXCHANGE-tax-settings']//table[contains(@class,'betTable')]", totalTaxSettingsColumns);
    private Table tblPositionTakingListing = Table.xpath("//div[@id='EXCHANGE-position-taking']//table[contains(@class,'ptable info betTable')]", totalPositionTakingColumns);
    private DropDownBox ddbAccountStatus = DropDownBox.xpath("//select[@class='full-width ng-pristine ng-valid ng-touched']");
    private Label lblCashBalanceTitle = Label.xpath("//div[@id='credit-balance-setting']//div[@class='psection']");
    public OldUIEditDownlineListing(String types) {
        super(types);
    }

    public void clickSubmit() {
        btnSubmit.click();
        waitingLoadingSpinner();
        btnCloseAlert.click();
        waitingLoadingSpinner();
    }
    public DownLineListingPage closeEditDownlinePopup() {
        btnCancel.click();
        return new DownLineListingPage(_type);
    }

    public void verifyUIDisplayCorrect() {
        Assert.assertEquals(header.lblPageTitle.getText().trim(), "Edit User", "Failed! Page title is incorrect");
        List<String> lstInfo = accInfoSection.getListLabelInfo();
        Assert.assertEquals(lstInfo.get(0), AGConstant.AgencyManagement.CreateAccount.LBL_LOGIN_ID, "FAILED! Login ID label display incorrect");
        Assert.assertEquals(lstInfo.get(1), AGConstant.AgencyManagement.CreateAccount.LBL_PASSWORD, "FAILED! Password label display incorrect");
        Assert.assertEquals(lstInfo.get(2), AGConstant.AgencyManagement.CreateAccount.LBL_ACCOUNT_STATUS, "FAILED! Account Status display incorrect");
        Assert.assertEquals(lstInfo.get(3), AGConstant.AgencyManagement.CreateAccount.LBL_EMAIL, "FAILED! First Name label display incorrect");
        Assert.assertEquals(lstInfo.get(4), AGConstant.AgencyManagement.CreateAccount.LBL_FIRST_NAME, "FAILED! Last Name label display incorrect");
        Assert.assertEquals(lstInfo.get(5), AGConstant.AgencyManagement.CreateAccount.LBL_LAST_NAME, "FAILED! Phone display incorrect");
        Assert.assertEquals(lstInfo.get(6), AGConstant.AgencyManagement.CreateAccount.LBL_MOBILE, "FAILED! Mobile display incorrect");
        Assert.assertTrue(accInfoSection.txtPassword.isDisplayed(), "FAILED! Password textbox does not display");
//        Assert.assertTrue(ddbAccountStatus.isDisplayed(), "FAILED! Account Status dropdown box does not display");
        Assert.assertTrue(accInfoSection.txtFirstName.isDisplayed(), "FAILED! First Name textbox does not display");
        Assert.assertTrue(accInfoSection.txtLastName.isDisplayed(), "FAILED! Last Name textbox does not display");
        Assert.assertTrue(accInfoSection.txtMobile.isDisplayed(), "FAILED! Mobile textbox does not display");
        Assert.assertTrue(txtEmail.isDisplayed(), "FAILED! Email textbox does not display");

        Assert.assertEquals(lblCashBalanceTitle.getText().trim(), AGConstant.AgencyManagement.CreateAccount.LBL_CASH_BALANCE,"FAILED! Cash Balance Title display incorrect");

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
        Assert.assertEquals(lstTaxSettingHeader, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_HEADER_OLDUI,"FAILED! Tax Setting Header does not display as expected");
        Assert.assertEquals(lstTaxSettingOption, AGConstant.AgencyManagement.CreateAccount.LST_TAX_SETTING_OPTION,"FAILED! Tax Setting options in the first column does not display as expected");

        Assert.assertEquals(lblPositionTakingListing.getText(), AGConstant.AgencyManagement.CreateAccount.LBL_POSITION_TAKING,"FAILED! Position Taking Section Label display incorrect");
        Assert.assertEquals(lstPositionTakingHeader, AGConstant.AgencyManagement.CreateAccount.LST_POSITION_TAKING_HEADER,"FAILED! Position Taking Header does not display as expected");

        Assert.assertEquals(btnSubmit.getText(), AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(btnCancel.getText(), AGConstant.BTN_CANCEL,"FAILED! Cancel button display incorrect");
    }
}