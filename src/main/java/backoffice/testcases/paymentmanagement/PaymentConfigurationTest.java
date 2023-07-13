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
        Assert.assertEquals(page.lblAlertAgentDoesNotExist.getText(), "Agent fafssd does not exist in the System!");
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
    @Parameters({"satSADAgentLoginID"})
    public void BO_Payment_Management_PaymentConfiguration_3836(String satSADAgentLoginID) {
        log("@title: Validate cannot add the agent already added in BO Payment Configuration");
        log("pre-condition 1: There is an agent added into Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        page.addAgent("All", satSADAgentLoginID);
        try {
            log("Step 1. Access Payment Configuration");
            log("Step 2. Input a the agent in precondition and click add button");
            page.addAgent("All", satSADAgentLoginID);
            log("Verify The error message \"Agent [username] is already added\" display");
            Assert.assertEquals(page.lblAlertAgentAlreadyAdd.getText(), "Agent " + satSADAgentLoginID + " is already added");
        } finally {
            page.clickToRemoveByUsername(satSADAgentLoginID);
            page.btnOK.click();
            System.out.println("Remove Agent");
        }
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
    @Test(groups = {"regression"})
    @Parameters({"satSADAgentLoginID"})
    public void BO_Payment_Management_PaymentConfiguration_3837(String satSADAgentLoginID) {
        log("@title: Validate cannot add the agent already added in BO Payment Configuration");
        log("pre-condition 1: There is an agent added into Payment Configuration");
        PaymentConfigurationPage page = backofficeHomePage.navigatePaymentConfigurationPage();
        page.addAgent("All", satSADAgentLoginID);
        try {
            log("Step 1. Access Payment Configuration");
            log("Step 2. Click on eye icon of the according agent");
            List<ArrayList<String>> lstData = page.getAllDataOnTable();
            String lineAgent = null;
            for (int i = 0; i < lstData.size();i++){
                if (lstData.get(i).get(3).equals(satSADAgentLoginID)){
                    lineAgent = lstData.get(i).get(6);
                    break;
                }
            }
            String upLineAgent = null;
            for (int i = 0; i < lineAgent.length();i++){
                if (String.valueOf(lineAgent.charAt(i)).equals("/")) {
                    upLineAgent = lineAgent.substring(0,i);
                    break;
                }
            }
            page.addAgent("All", upLineAgent);
            log("Verify The error message \"Only 1 level in a single line is allowed to configure\" display");
            Assert.assertEquals(page.lblAlertOnly1Level.getText(), "Only 1 level in a single line is allowed to configure");
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
    @Test(groups = {"regression"})
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
            List<ArrayList<String>> lstData = popup.getLogOfUser();
            List<ArrayList<String>> lstDataExpect = PaymentConfigurationPopupUtils.getData(satSADAgentLoginID);
            for (int i = 0; i < lstDataExpect.size(); i++){
                lstDataExpect.get(i).set(2,lstDataExpect.get(i).get(2).replace(".0",""));
            }
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
        List<ArrayList<String>> lstData = page.getAllDataOnTable();
        ArrayList<String> lstUpdateDate = new ArrayList<>();
        for (int i = 0; i < lstData.size();i++){
            lstUpdateDate.add(i,lstData.get(i).get(8));
        }
        ArrayList<String> lstDataSorted = page.getListUpdateDateSorted(lstUpdateDate);
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
        ArrayList<String> lstHeader = page.getHeaderTableName();
        Assert.assertEquals(lstHeader, BOConstants.PaymentManagement.PaymentConfiguration.HEADER_TABLE);
        log("INFO: Executed completely");
    }

}
