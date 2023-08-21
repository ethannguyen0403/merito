package backoffice.testcases.paymentmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.paymentmanagement.PaymentConfigurationPage;
import backoffice.pages.bo.paymentmanagement.paymentconfiguration.PaymentConfigurationPopup;
import backoffice.utils.paymentmanagement.PaymentConfigurationPopupUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class PaymentConfigurationTest extends BaseCaseTest {

    /**
     * @title: Validate Admin cannot add an nonexist agent in BO Payment Configuration
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Payment Configuration
     * 2. Input a non-exist agent and click Add button
     * @expect: 1. Verify The error message "Agent fdfsdf does not exist in the System!" display
     */
    @TestRails(id = "3835")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_PaymentConfiguration_3835() {
        log("@title: Validate Admin cannot add an nonexist agent in BO Payment Configuration");
        log("Step 1. Access Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        log("Step 2. Input a non-exist agent and click Add button");
        page.addAgent("All", "fafssd");
        log("Verify 1: Verify The error message \"Agent fafssd does not exist in the System!\" display");
        Assert.assertEquals(page.lblDangerAlert.getText(), String.format(BOConstants.PaymentManagement.PaymentConfiguration.MSG_AGENT_NOT_EXIST,"fafssd"));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cannot add the agent already added in BO Payment Configuration
     * @pre-condition: 1. There is an agent added into Payment Configuration
     * @steps: 1. Access Payment Configuration
     * 2. Input a the agent in precondition and click add button
     * @expect: 1. Verify Error message "Agent [username] is already added" display
     */
    @TestRails(id = "3836")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_PaymentConfiguration_3836() {
        log("@title: Validate cannot add the agent already added in BO Payment Configuration");
        log("pre-condition 1: There is an agent added into Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        String agentAdded = page.tblReport.getColumn(page.colUsername,10,false).get(1);
        page.addAgent("All", agentAdded);
        log("Step 1. Access Payment Configuration");
        log("Step 2. Input a the agent in precondition and click add button");
        page.addAgent("All", agentAdded);
        log("Verify The error message \"Agent [username] is already added\" display");
        Assert.assertEquals(page.lblDangerAlert.getText(),
                String.format(BOConstants.PaymentManagement.PaymentConfiguration.MSG_AGENT_ALREADY_ADDED,agentAdded));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cannot add the agent if there is an agent in the same line is already added in BO Payment Configuration
     * @pre-condition: 1. There is an agent added into Payment Configuration
     * @steps: 1. Access Payment Configuration
     * 2. Input a the agent in the same line with the agent in precondition (downline or upline)
     * @expect: 1. Verify Error message "Only 1 level in a single line is allowed to configure" display
     */
    @TestRails(id = "3837")
    @Test(groups = {"regression_s"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_Payment_Management_PaymentConfiguration_3837(String satSADAgentLoginID) {
        log("@title: Validate cannot add the agent already added in BO Payment Configuration");
        log("pre-condition 1: There is an agent added into Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        page.addAgent("All", satSADAgentLoginID);
        try {
            log("Step 1. Access Payment Configuration");
            log("Step 2. Click on eye icon of the according agent");
            List<ArrayList<String>> lstData = page.tblReport.getRowsWithoutHeader(50,true);
            String upLineAgent = page.getUplineOfAgent(lstData, satSADAgentLoginID);
            page.scrollToTopTable();
            page.addAgent("All", upLineAgent);
            log("Verify The error message \"Only 1 level in a single line is allowed to configure\" display");
            Assert.assertEquals(page.lblDangerAlert.getText(), BOConstants.PaymentManagement.PaymentConfiguration.MSG_ONLY_1_LEVEL_IN_SINGLE_LINE);
        } finally {
            page.clickToRemoveByUsername(satSADAgentLoginID);
            page.btnOK.click();
            System.out.println("Remove Agent");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can view Payment Configuration Log in BO Payment Configuration
     * @pre-condition: 1. There is an agent is added in the list
     * @steps: 1. Access Payment Configuration
     * 2. Click on eye icon of the according agent
     * @expect: 1. A View log popup display with the title Payment Configuration Log - [Agent code]
     *          2. Display log data
     */
    @TestRails(id = "3839")
    @Test(groups = {"regression_s"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_Payment_Management_PaymentConfiguration_3839(String satSADAgentLoginID) {
        log("@title: Validate can view Payment Configuration Log in BO Payment Configuration");
        log("pre-condition 1: There is an agent added into Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        page.addAgent("All", satSADAgentLoginID);
        try {
            log("Step 1. Access Payment Configuration");
            log("Step 2. Click on eye icon of the according agent");
            PaymentConfigurationPopup popup = page.clickToViewLogByUsername(satSADAgentLoginID);
            log("Verify 1: A View log popup display with the title Payment Configuration Log - [Agent code]");
            Assert.assertEquals(popup.getTitlePopupByUsername(satSADAgentLoginID), "Payment Configuration Log - " + satSADAgentLoginID);
            log("Verify 2: Display log data");
            List<ArrayList<String>> lstData = popup.tblPopup.getRowsWithoutHeader(200,true);
            List<ArrayList<String>> lstDataExpect = PaymentConfigurationPopupUtils.getData(satSADAgentLoginID);
            Assert.assertEquals(lstData, lstDataExpect);
            popup.clickToClosePopup();
        } finally {
            page.clickToRemoveByUsername(satSADAgentLoginID);
            page.btnOK.click();
            System.out.println("Remove Agent");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate close view in BO Payment Configuration
     * @pre-condition: 1. There is an agent is added in the list
     * @steps: 1. Access Payment Configuration
     * 2. Click on eye icon of the according agent
     * 3. Click close button
     * @expect: 1. Verify Payment Configuration log popup is no longer display
     */
    @TestRails(id = "3840")
    @Test(groups = {"regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_Payment_Management_PaymentConfiguration_3840(String satSADAgentLoginID) {
        log("@title: Validate cannot add the agent already added in BO Payment Configuration");
        log("pre-condition 1: There is an agent added into Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        page.addAgent("All", satSADAgentLoginID);
        try {
            log("Step 1. Access Payment Configuration");
            log("Step 2. Click on eye icon of the according agent");
            PaymentConfigurationPopup popup = page.clickToViewLogByUsername(satSADAgentLoginID);
            log("Step 3. Click close button");
            popup.clickToClosePopup();
            log("Verify 1: Verify Payment Configuration log popup is no longer display");
            Assert.assertFalse(page.isDisplayLogPopup());
        } finally {
            page.clickToRemoveByUsername(satSADAgentLoginID);
            page.btnOK.click();
            System.out.println("Remove Agent");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate the list payment is sorted desc by updated date in BO Payment Configuration
     * @pre-condition: 1. There are more then 2 agent is added
     * @steps: 1. Access Payment Configuration
     *         2. Observe the list
     * @expect: 1. Verify the data is sorted by updated date
     */
    @TestRails(id = "3841")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_PaymentConfiguration_3841() {
        log("@title: Validate the list payment is sorted desc by updated date in BO Payment Configuration");
        log("pre-condition 1: There are more then 2 agent is added");
        log("Step 1. Access Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        log("Step 2. Observe the list");
        List<String> lstUpdateDate = page.tblReport.getColumn( page.colUpdatedDate,20,true);
        List<String> lstDataSorted = page.getListUpdateDateSorted(lstUpdateDate);
        log("Verify 1: Verify the data is sorted by updated date");
        Assert.assertEquals(lstUpdateDate,lstDataSorted);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI of Payment Configuration display correctly in BO Payment Configuration
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Payment Configuration
     *         2. Observe and verify UI
     * @expect: 1. The title is correct: Payment Configuration
     *          2. Payment Method label, dropdown with data: All, Kingspay, All is default value
     *          3. Agent label and textbox and button Add
     *          4. The table headers: No, Brand, Level, Username, Currency, Payment Method, Line, Update By, Update Date, Action
     */
    @TestRails(id = "3842")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_PaymentConfiguration_3842() {
        log("@title: Validate UI of Payment Configuration display correctly in BO Payment Configuration");
        log("pre-condition 1: Login BO");
        log("Step 1. Access Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        log("Step 2. Observe and verify UI");
        log("Verify 1: Verify UI display correctly");
        Assert.assertEquals(page.lblPaymentConfiguration.getText(),"Payment Configuration");
        Assert.assertTrue(page.lblPaymentMethod.isDisplayed());
        Assert.assertEquals(page.ddnPaymentMethod.getOptions(), BOConstants.PaymentManagement.PaymentConfiguration.OPTION_OF_PAYMENT_METHOD);
        Assert.assertEquals(page.ddnPaymentMethod.getFirstSelectedOption(),"All");
        Assert.assertEquals(page.lblAgent.getText(),"Agent");
        Assert.assertTrue(page.btnAdd.isEnabled());
        List<String> lstHeader = page.tblReport.getColumnNamesOfTable();
        Assert.assertEquals(lstHeader, BOConstants.PaymentManagement.PaymentConfiguration.HEADER_TABLE);
        log("INFO: Executed completely");
    }

}
