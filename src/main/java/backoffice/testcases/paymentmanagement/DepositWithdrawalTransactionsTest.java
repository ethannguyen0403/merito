package backoffice.testcases.paymentmanagement;

import backoffice.common.BOConstants;
import backoffice.controls.DateTimePicker;
import backoffice.pages.bo.paymentmanagement.DepositWithdrawalTransactionsPage;
import backoffice.utils.paymentmanagement.PaymentConfigurationUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepositWithdrawalTransactionsTest extends BaseCaseTest {

    /**
     * @title: Validate UI display correctly in BO Deposit/Withdrawal Transactions
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Observe and verify UI
     * @expect: 1. Page title is correct: Deposit/Withdrawal Transactions
     *          2 Tab Deposit Transaction and Withdraw transactions. Deposit Transaction tab is active by default when access the page
     */
    @TestRails(id = "3843")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3843() {
        log("@title: Validate UI display correctly in BO Deposit/Withdrawal Transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Observe and verify UI");
        log("Verify 1: Verify UI display correctly");
        Assert.assertEquals(page.lblTitlePage.getText(),"Deposit/Withdrawal Transactions");
        Assert.assertEquals(page.btnTabActive.getText(),"Deposit Transactions");
        Assert.assertEquals(page.getTabName(),BOConstants.PaymentManagement.DepositWithdrawalTransactions.TAB_NAME);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI Deposit Transactions display correctly in BO Deposit/Withdrawal Transactions
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Observe and verify in Deposit Transaction
     * @expect: 1. Filter section displays with the control:
     *              From, To label and textbox.
     *              Brand, Agent, Status Username/Nickname labels with dropdown and textbox and Search button
     *              The note : You can see report data up to 6 months
     *              Table headers: No., Brand, Username, Transaction Date, Transaction ID, Payment Method, Currency, Deposit Amount, Status
     */
    @TestRails(id = "3844")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3844() {
        log("@title: Validate UI Deposit Transactions display correctly in BO Deposit/Withdrawal Transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Observe and verify in Deposit Transaction");
        log("Verify 1: Verify UI display correctly in Deposit Transaction");
        Assert.assertTrue(page.getLblByName("From").isDisplayed());
        Assert.assertTrue(page.getLblByName("To").isDisplayed());
        Assert.assertTrue(page.txbFrom.isDisplayed());
        Assert.assertTrue(page.txbTo.isDisplayed());
        Assert.assertTrue(page.getLblByName("Brand").isDisplayed());
        Assert.assertTrue(page.getLblByName("Agent").isDisplayed());
        Assert.assertTrue(page.getLblByName("Status").isDisplayed());
        Assert.assertTrue(page.getLblByName("Username/Nickname").isDisplayed());
        Assert.assertTrue(page.ddnBrand.isDisplayed());
        Assert.assertTrue(page.ddnAgent.isDisplayed());
        Assert.assertTrue(page.ddnStatus.isDisplayed());
        Assert.assertTrue(page.txbUsername.isDisplayed());
        Assert.assertTrue(page.btnSearch.isDisplayed());
        Assert.assertTrue(page.getLblByName("You can see report data up to 6 months.").isDisplayed());
        Assert.assertEquals(page.getHeaderTableName(),BOConstants.PaymentManagement.DepositWithdrawalTransactions.HEADER_TABLE_OF_DEPOSIT_TAB);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI Withdraw Transactions display correctly in BO Deposit/Withdrawal Transactions
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Click on Withdrawal Transaction tab and verify UI
     * @expect: 1. Filter section displays with the control:
     * From, To label and textbox.
     * Brand, Agent, Status Username/Nickname labels with dropdown and textbox and Search button
     * The note : You can see report data up to 6 months
     * Table headers: No., Brand, Username, Transaction Date, Transaction ID, Bank Account Name, Bank Name. IFSC Code, Bank Account No. Currency, Withdrawal Amount, Status, Update Date, Update By
     */
    @TestRails(id = "3845")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3845() {
        log("@title: Validate UI Withdraw Transactions display correctly in BO Deposit/Withdrawal Transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Click on Withdrawal Transaction tab and verify UI");
        page.btnWithdrawalTab.click();
        log("Verify 1: Verify UI display correctly in Deposit Transaction");
        Assert.assertTrue(page.getLblByName("From").isDisplayed());
        Assert.assertTrue(page.getLblByName("To").isDisplayed());
        Assert.assertTrue(page.txbFrom.isDisplayed());
        Assert.assertTrue(page.txbTo.isDisplayed());
        Assert.assertTrue(page.getLblByName("Brand").isDisplayed());
        Assert.assertTrue(page.getLblByName("Agent").isDisplayed());
        Assert.assertTrue(page.getLblByName("Status").isDisplayed());
        Assert.assertTrue(page.getLblByName("Username/Nickname").isDisplayed());
        Assert.assertTrue(page.ddnBrand.isDisplayed());
        Assert.assertTrue(page.ddnAgent.isDisplayed());
        Assert.assertTrue(page.ddnStatus.isDisplayed());
        Assert.assertTrue(page.txbUsername.isDisplayed());
        Assert.assertTrue(page.btnSearch.isDisplayed());
        Assert.assertTrue(page.getLblByName("You can see report data up to 6 months.").isDisplayed());
        Assert.assertEquals(page.getHeaderTableName(),BOConstants.PaymentManagement.DepositWithdrawalTransactions.HEADER_TABLE_OF_WITHDRAWAL_TAB);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate default value on BO Deposit Transactions display correctly in BO Deposit/Withdrawal Transactions
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit tab, Observe and verify value in the filter controls
     * @expect: 1. Verify From - To is display current day in time zone GMT-4
     *          2. Brand display All by default, and display all brands
     *          3. Agent display All by default. It display all agents of all brands which is added in Payment Configuration
     *          4. Status: All is default value. When clicking on the dropdown, the status include: All, Pending, Success, Failure
     */
    @TestRails(id = "3846")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3846() {
        log("@title: Validate default value on BO Deposit Transactions display correctly in BO Deposit/Withdrawal Transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Deposit tab, Observe and verify value in the filter controls");
        page.btnDepositTab.click();
        log("Verify 1: Verify default value on BO Deposit Transactions display correctly");
        Assert.assertEquals(page.txbFrom.getAttribute("value"), DateUtils.getDate(0, "yyyy/MM/dd", BOConstants.GMT_FOUR));
        Assert.assertEquals(page.txbTo.getAttribute("value"), DateUtils.getDate(0, "yyyy/MM/dd", BOConstants.GMT_FOUR));
        Assert.assertEquals(page.ddnBrand.getFirstSelectedOption(),"All");
        Assert.assertEquals(page.ddnAgent.getFirstSelectedOption(),"All");
        List<String> lstAgent = page.ddnAgent.getOptions();
        Assert.assertEquals(page.getNumberAgentSameAsPaymentConfigurationPage(lstAgent),lstAgent.size()-1);
        Assert.assertEquals(page.ddnStatus.getFirstSelectedOption(),"All");
        Assert.assertEquals(page.ddnStatus.getOptions(),BOConstants.PaymentManagement.DepositWithdrawalTransactions.STATUS_OF_DEPOSIT);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate default value on BO Withdrawal Transactions display correctly in BO Deposit/Withdrawal Transactions
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdraw tab, observe and verify value in the filter controls
     * @expect: 1. Verify From - To is display current day in time zone GMT-4
     *          2. Brand display All by default, and display all brands
     *          3. Agent display All by default. It display all agents of all brands which is added in Payment Configuration
     *          4. Status: All is default value. When clicking on the dropdown, the status include: All, Pending, Success, Failure, Approved, Reject
     */
    @TestRails(id = "3847")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3847() {
        log("@title: Validate default value on BO Withdrawal Transactions display correctly in BO Deposit/Withdrawal Transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw tab, observe and verify value in the filter controls");
        page.btnWithdrawalTab.click();
        log("Verify 1: Verify default value on BO Withdrawal Transactions display correctly");
        Assert.assertEquals(page.txbFrom.getAttribute("value"), DateUtils.getDate(0, "yyyy/MM/dd", BOConstants.GMT_FOUR));
        Assert.assertEquals(page.txbTo.getAttribute("value"), DateUtils.getDate(0, "yyyy/MM/dd", BOConstants.GMT_FOUR));
        Assert.assertEquals(page.ddnBrand.getFirstSelectedOption(),"All");
        Assert.assertEquals(page.ddnAgent.getFirstSelectedOption(),"All");
        List<String> lstAgent = page.ddnAgent.getOptions();
        Assert.assertEquals(page.getNumberAgentSameAsPaymentConfigurationPage(lstAgent),lstAgent.size()-1);
        Assert.assertEquals(page.ddnStatus.getFirstSelectedOption(),"All");
        Assert.assertEquals(page.ddnStatus.getOptions(),BOConstants.PaymentManagement.DepositWithdrawalTransactions.STATUS_OF_WITHDRAWAL);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate default value on BO Withdrawal Transactions display correctly in BO Deposit/Withdrawal Transactions
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdraw tab, observe and verify value in the filter controls
     * @expect: 1. Verify From - To is display current day in time zone GMT-4
     *          2. Brand display All by default, and display all brands
     *          3. Agent display All by default. It display all agents of all brands which is added in Payment Configuration
     *          4. Status: All is default value. When clicking on the dropdown, the status include: All, Pending, Success, Failure, Approved, Reject
     */
    @TestRails(id = "3848")
    @Test(groups = {"regression1"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3848() {
        log("@title: Validate default value on BO Withdrawal Transactions display correctly in BO Deposit/Withdrawal Transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw tab, observe and verify value in the filter controls");
        page.btnWithdrawalTab.click();
        log("Verify 1: Verify default value on BO Withdrawal Transactions display correctly");
        Assert.assertEquals(page.txbFrom.getAttribute("value"), DateUtils.getDate(0, "yyyy/MM/dd", BOConstants.GMT_FOUR));
        Assert.assertEquals(page.txbTo.getAttribute("value"), DateUtils.getDate(0, "yyyy/MM/dd", BOConstants.GMT_FOUR));
        Assert.assertEquals(page.ddnBrand.getFirstSelectedOption(),"All");
        Assert.assertEquals(page.ddnAgent.getFirstSelectedOption(),"All");
        List<String> lstAgent = page.ddnAgent.getOptions();
        Assert.assertEquals(page.getNumberAgentSameAsPaymentConfigurationPage(lstAgent),lstAgent.size()-1);
        Assert.assertEquals(page.ddnStatus.getFirstSelectedOption(),"All");
        Assert.assertEquals(page.ddnStatus.getOptions(),BOConstants.PaymentManagement.DepositWithdrawalTransactions.STATUS_OF_WITHDRAWAL);
        log("INFO: Executed completely");
    }
}
