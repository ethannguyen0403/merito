package backoffice.testcases.temp;

import backoffice.common.BOConstants;
import backoffice.pages.bo.temp.NetProfitConfigurationPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NetProfitConfigurationTest extends BaseCaseTest {

    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Net Profit Configuration
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void BO_Report_Net_Profit_Configuration_001() {
        log("@title: Validate there is no http responded error returned");
        log("Step 1. Access Reports > Net Profit Configuration");
        backofficeHomePage.navigateNetProfitConfiguration();

        log("Verify 1. There is no http responded error returned");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI is loaded when selecting INR to HKD Rate
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Net Profit Configuration
     * @expect: 1. Verify the options displays: INR to HKD Rate (default is selected), AA Rate, SAT Rate, Casino Virtual Currency Provider Rate, Exclude Agent, Special Setting
     * 2. the label 1 INR = textbox HKD, Submit button
     * 3. Table header: Rate, Last Update Date, Last Update By
     */
    @Test(groups = {"smoke"})
    public void BO_Report_Net_Profit_Configuration_002() {
        log("@title: Validate UI is loaded when selecting INR to HKD Rate");
        log("Step 1. Access Reports > Net Profit Configuration");
        NetProfitConfigurationPage page = backofficeHomePage.navigateNetProfitConfiguration();
        Assert.assertEquals(page.lblPageTitle.getText(), BOConstants.Reports.NetProfitConfiguration.TITLE, "FAILED! Page title is incorrectly display");

        page.selectRate(NetProfitConfigurationPage.Type.INRToHKDRATE);
        log("Verify 1. Verify the options displays: INR to HKD Rate (default is selected), AA Rate, SAT Rate, Casino Virtual Currency Provider Rate, Exclude Agent, Special Setting" +
                "2. the label 1 INR = textbox HKD, Submit button\n" +
                "3. Table header: Rate, Last Update Date, Last Update By");
        Assert.assertEquals(page.lblINRToHKDRate.getText(), BOConstants.Reports.NetProfitConfiguration.INR_HKD_RATE, "FAILED! Option radio button INR to HKD Rate is incorrect");
        Assert.assertEquals(page.lblAARate.getText(), BOConstants.Reports.NetProfitConfiguration.AA_RATE, "FAILED! Option radio button AA Rate is incorrect");
        Assert.assertEquals(page.lblSATRate.getText(), BOConstants.Reports.NetProfitConfiguration.SAT_RATE, "FAILED! Option radio button SAT Rate is incorrect");
        Assert.assertEquals(page.lblCasinoRate.getText(), BOConstants.Reports.NetProfitConfiguration.CASINO_RATE, "FAILED! Option radio button Casino virtual currency provider Rate is incorrect");
        Assert.assertEquals(page.lblExcludeAgent.getText(), BOConstants.Reports.NetProfitConfiguration.EXCLUDE_AG, "FAILED! Option radio button Exclude Agent is incorrect");
        Assert.assertEquals(page.lblSpecialSetting.getText(), BOConstants.Reports.NetProfitConfiguration.SPECIAL_SETTING, "FAILED! Option radio button Special Setting is incorrect");
        Assert.assertTrue(page.txtToHKD.isDisplayed(), "The textbox to HKD does not display");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "The Submut button does not display");
        Assert.assertEquals(page.tblRateINRToHKD.getColumnNamesOfTable(), BOConstants.Reports.NetProfitConfiguration.LST_INRtoHKD__HEADER, "FAILED!Table header when select INT o HKD rare is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI is loaded when selecting AA Rate
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Net Profit Configuration
     * @expect: 1. Verify UI is loaded when selecting AA Rate
     */
    @Test(groups = {"smoke"})
    public void BO_Report_Net_Profit_Configuration_003() {
        log("@title: Validate UI is loaded when selecting AA Rate");
        log("Step 1. Access Reports > Net Profit Configuration");
        NetProfitConfigurationPage page = backofficeHomePage.navigateNetProfitConfiguration();
        page.selectRate(NetProfitConfigurationPage.Type.AARATE);

        log("Verify 1. Verify UI is loaded when selecting AA Rate");
        Assert.assertTrue(page.txtUserName.isDisplayed(), "FAILED! Username textbox is not displayed!");
        Assert.assertTrue(page.txtPlayerRate.isDisplayed(), "FAILED! Player Rate textbox is not displayed!");
        Assert.assertTrue(page.txtPortalRate.isDisplayed(), "FAILED! Portal Rate textbox is not displayed!");
        Assert.assertTrue(page.btnSubmit.isEnabled(), "FAILED! Submit button does not display");
        Assert.assertTrue(page.btnCancel.isDisplayed(), "FAILED! Cancel button does not display");
        Assert.assertEquals(page.tblAARate.getHeaderNameOfRows(), BOConstants.Reports.NetProfitConfiguration.LST_AA_RATE__HEADER, "FAILED!Table header when select AA Rate is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI is loaded when selecting SAT Rate
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Net Profit Configuration
     * @expect: 1. Verify UI is loaded when selecting SAT Rate
     */
    @Test(groups = {"smoke"})
    public void BO_Report_Net_Profit_Configuration_004() {
        log("@title:Validate  UI is loaded when selecting SAT Rate");
        log("Step 1. Access Reports > Net Profit Configuration");
        NetProfitConfigurationPage page = backofficeHomePage.navigateNetProfitConfiguration();
        page.selectRate(NetProfitConfigurationPage.Type.SATRATE);

        log("Verify 1. Verify UI is loaded when selecting SAT Rate");
        Assert.assertTrue(page.txtBrand.isDisplayed(), "FAILED! Brand dropdown box does not display");
        Assert.assertTrue(page.txtUsernameLoginID.isDisplayed(), "FAILED! Username textbox does not display");
        Assert.assertTrue(page.txtDealRate.isDisplayed(), "FAILED! Deal Rate textbox does not display");
        Assert.assertTrue(page.btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertEquals(page.tblSATRAte.getColumnNamesOfTable(), BOConstants.Reports.NetProfitConfiguration.LST_SAT_RATE__HEADER, "FAILED!Table header when select SAT Rate is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate  UI is loaded when selecting Exclude Agent
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Reports > Net Profit Configuration
     * @expect: 1. Verify UI is loaded when selecting Exclude Agent
     */
    @Test(groups = {"smoke"})
    public void BO_Report_Net_Profit_Configuration_005() {
        log("@title: Validate  UI is loaded when selecting Exclude Agent");
        log("Step 1. Access Reports > Net Profit Configuration");
        NetProfitConfigurationPage page = backofficeHomePage.navigateNetProfitConfiguration();
        page.selectRate(NetProfitConfigurationPage.Type.EXCLUDEAG);

        log("Verify 1. Verify UI is loaded when selecting Exclude Agent");
        Assert.assertTrue(page.txtBrandExclude.isDisplayed(), "FAILED! Brand dropdown box does not display");
        Assert.assertTrue(page.txtUsernameExclude_Agent.isDisplayed(), "FAILED! Brand dropdown box does not display");
        Assert.assertTrue(page.btnAddAgent.isDisplayed(), "FAILED! Brand dropdown box does not display");
        Assert.assertEquals(page.tblExcludeAgent.getColumnNamesOfTable(), BOConstants.Reports.NetProfitConfiguration.LST_EXCLUDE_AGENT__HEADER, "FAILED!Table header when select SAT Rate is incorrect");

        log("INFO: Executed completely");
    }
}
