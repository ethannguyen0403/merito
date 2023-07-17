package backoffice.testcases.paymentmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.paymentmanagement.DepositWithdrawalTransactionsPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Collections;
import java.util.List;

public class DepositWithdrawalTransactionsTest extends BaseCaseTest {

    /**
     * @title: Validate UI display correctly in BO Deposit Transactions
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Observe and verify UI
     * @expect: 1. Page title is correct: Deposit/Withdrawal Transactions
     *          2 Tab Deposit Transaction and Withdraw transactions. Deposit Transaction tab is active by default when access the page
     */
    @TestRails(id = "3843")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3843() {
        log("@title: Validate UI display correctly in BO Deposit Transactions");
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
     * @title: Validate UI Deposit Transactions display correctly in BO Deposit Transactions
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
        log("@title: Validate UI Deposit Transactions display correctly in BO Deposit Transactions");
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
     * @title: Validate UI Withdraw Transactions display correctly in BO Withdrawal Transactions
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
        log("@title: Validate UI Withdraw Transactions display correctly in BO Withdrawal Transactions");
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
     * @title: Validate default value on BO Deposit Transactions display correctly in BO Deposit Transactions
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
        log("@title: Validate default value on BO Deposit Transactions display correctly in BO Deposit Transactions");
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
     * @title: Validate default value on BO Withdrawal Transactions display correctly in BO Withdrawal Transactions
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
        log("@title: Validate default value on BO Withdrawal Transactions display correctly in BO Withdrawal Transactions");
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
     * @title: Validate filter data display correctly by date range in BO Deposit Transactions
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit tab
     *         3. Select data range from to in a week
     * @expect: 1. Validate the data has transaction date in the filter range display
     */
    @TestRails(id = "3848")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3848() {
        log("@title: Validate filter data display correctly by date range in BO Deposit Transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw tab, observe and verify value in the filter controls");
        page.btnDepositTab.click();
        log("Step 3. Select data range from to in a week");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,toDay,"","","","");
        log("Verify 1: Verify default value on BO Withdrawal Transactions display correctly");
        List<String> lstTransactionDate = page.tblDeposit.getColumn(4,20,true);
        Assert.assertTrue(page.checkTransactionDateInFilterRange(fromDay,toDay, lstTransactionDate));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data display correctly when filtering by brand in BO Deposit Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit tab
     *         3. Select data range from to
     *         4. Select a specific brand
     *         5. Click Search button
     * @expect: 1. Verify all accounts under select brand display only. Display "No records found" if the brand have no deposit transaction in filter range
     */
    @TestRails(id = "3849")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3849() {
        log("@title: Validate data display correctly when filtering by brand in BO Deposit Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Deposit tab");
        page.btnDepositTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select a specific brand");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"", page.ddnBrand.getOptions().get(1),"","","");
        log("Verify 1: Verify all accounts under select brand display only. Display \"No records found\" if the brand have no deposit transaction in filter range");
        List<String> lstBrand = page.tblDeposit.getColumn(2,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels(page.ddnBrand.getOptions().get(1),"","",lstBrand, Collections.singletonList(""),Collections.singletonList("")));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data display correctly when filtering by status=Success in BO Deposit Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit tab
     *         3. Select data range from to
     *         4. Select all brand and status is Success
     *         5. Click Search button
     * @expect: 1. Verify all accounts which have status = Success
     */
    @TestRails(id = "3850")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3850() {
        log("@title: Validate data display correctly when filtering by status=Success in BO Deposit Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Deposit tab");
        page.btnDepositTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select all brand and status is Success");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","Success","");
        log("Verify 1: Verify all accounts which have status = Success");
        List<String> lstStatus = page.tblDeposit.getColumn(9,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("","","Success",Collections.singletonList(""), Collections.singletonList(""),lstStatus));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data display correctly when filtering by status=Failed in BO Deposit Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit tab
     *         3. Select data range from to
     *         4. Select all brand and status is Failure
     *         5. Click Search button
     * @expect: 1. Verify all accounts which have status = Failure
     */
    @TestRails(id = "3851")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3851() {
        log("@title: Validate data display correctly when filtering by status=Failed in BO Deposit Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Deposit tab");
        page.btnDepositTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select all brand and status is Failure");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","Failure","");
        log("Verify 1: Verify all accounts which have status = Failure");
        List<String> lstStatus = page.tblDeposit.getColumn(9,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("","","Failure", Collections.singletonList(""),Collections.singletonList(""),lstStatus));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data display correctly when filtering by status=Pending in BO Deposit Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit tab
     *         3. Select data range from to
     *         4. Select all brand and status is Pending
     *         5. Click Search button
     * @expect: 1. Verify all accounts which have status = Pending
     */
    @TestRails(id = "3852")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3852() {
        log("@title: Validate data display correctly when filtering by status=Pending in BO Deposit Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Deposit tab");
        page.btnDepositTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select all brand and status is Pending");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","Pending","");
        log("Verify 1: Verify all accounts which have status = Pending");
        List<String> lstStatus = page.tblDeposit.getColumn(9,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("","","Pending", Collections.singletonList(""), Collections.singletonList(""), lstStatus));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data display correctly when filtering by status=Success in BO Withdrawal Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdraw Transactions tab
     *         3. Select data range from to
     *         4. Select all brand and status is Success
     *         5. Click Search button
     * @expect: 1. Verify all accounts which have status = Success
     */
    @TestRails(id = "3853")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3853() {
        log("@title: Validate data display correctly when filtering by status=Success in BO Withdrawal Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnWithdrawalTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select all brand and status is Success");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","Success","");
        log("Verify 1: Verify all accounts which have status = Success");
        List<String> lstTransactionDate = page.tblWithDrawal.getColumn(4,20,true);
        Assert.assertTrue(page.checkTransactionDateInFilterRange(fromDay,toDay, lstTransactionDate));

        List<String> lstStatus = page.tblWithDrawal.getColumn(12,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("","","Success",Collections.singletonList("") , Collections.singletonList(""), lstStatus));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data display correctly when filtering by status=Failed in BO Withdrawal Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdraw Transactions tab
     *         3. Select data range from to
     *         4. Select all brand and status is Failure
     *         5. Click Search button
     * @expect: 1. Verify all accounts which have status = Failure
     */
    @TestRails(id = "3854")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3854() {
        log("@title: Validate data display correctly when filtering by status=Failed in BO Withdrawal Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnWithdrawalTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select all brand and status is Failure");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","Failure","");
        log("Verify 1: Verify all accounts which have status = Failure");
        List<String> lstStatus = page.tblWithDrawal.getColumn(12,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("","","Failure",Collections.singletonList("") , Collections.singletonList(""), lstStatus));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data display correctly when filtering by status=Approved in BO Withdrawal Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdraw Transactions tab
     *         3. Select data range from to
     *         4. Select all brand and status is Approved
     *         5. Click Search button
     * @expect: 1. Verify all accounts which have status = Approved
     */
    @TestRails(id = "3855")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3855() {
        log("@title: Validate data display correctly when filtering by status=Approved in BO Withdrawal Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnWithdrawalTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select all brand and status is Approved");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","Approved","");
        log("Verify 1: Verify all accounts which have status = Approved");
        List<String> lstStatus = page.tblWithDrawal.getColumn(12,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("","","Approved",Collections.singletonList("") , Collections.singletonList(""), lstStatus));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data display correctly when filtering by status=Reject in BO Withdrawal Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdraw Transactions tab
     *         3. Select data range from to
     *         4. Select all brand and status is Reject
     *         5. Click Search button
     * @expect: 1. Verify all accounts which have status = Rejected
     */
    @TestRails(id = "3856")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3856() {
        log("@title: Validate data display correctly when filtering by status=Reject in BO Withdrawal Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnWithdrawalTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select all brand and status is Reject");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","Rejected","");
        log("Verify 1: Verify all accounts which have status = Rejected");
        List<String> lstStatus = page.tblWithDrawal.getColumn(12,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("","","Rejected",Collections.singletonList("") , Collections.singletonList(""), lstStatus));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data display correctly when filtering by status=Pending in BO Withdrawal Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdraw Transactions tab
     *         3. Select data range from to
     *         4. Select all brand and status is Pending
     *         5. Click Search button
     * @expect: 1. Verify all accounts which have status = Pending
     */
    @TestRails(id = "3857")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3857() {
        log("@title: Validate data display correctly when filtering by status=Pending in BO Withdrawal Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnWithdrawalTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Select all brand and status is Pending");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","Pending","");
        log("Verify 1: Verify all accounts which have status = Pending");
        List<String> lstStatus = page.tblWithDrawal.getColumn(12,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("","","Pending",Collections.singletonList("") , Collections.singletonList(""), lstStatus));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data display correctly when filtering by correct username in BO Withdrawal Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdraw Transactions tab
     *         3. Select data range from to
     *         4. Input a username that have deposit transaction
     *         5. Click Search button
     * @expect: 1. Verify only the transactions of the according account displayed
     */
    @TestRails(id = "3858")
    @Test(groups = {"regression"})
    @Parameters({"usernameDepositTransaction"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3858(String usernameDepositTransaction) {
        log("@title: Validate data display correctly when filtering by correct username in BO Withdrawal Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnWithdrawalTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Input a username that have deposit transaction");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","",usernameDepositTransaction);
        log("Verify 1: Verify only the transactions of the according account displayed");
        List<String> lstAgent = page.tblWithDrawal.getColumn(3,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("",usernameDepositTransaction,"",Collections.singletonList("") , lstAgent, Collections.singletonList("")));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate data display correctly when filtering by correct username in BO Deposit Transactions
     * @pre-condition: 1. Login BO, There is a deposit transaction from player
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit Transactions tab
     *         3. Select data range from to
     *         4. Input a username that have deposit transaction
     *         5. Click Search button
     * @expect: 1. Verify only the transactions of the according account displayed
     */
    @TestRails(id = "3859")
    @Test(groups = {"regression"})
    @Parameters({"usernameDepositTransaction"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3859(String usernameDepositTransaction) {
        log("@title: Validate data display correctly when filtering by correct username in BO Deposit Transactions");
        log("pre-condition 1: Login BO, There is a deposit transaction from player");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnDepositTab.click();
        log("Step 3. Select data range from to");
        log("Step 4. Input a username that have deposit transaction");
        log("Step 5. Click Search button");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"","","","",usernameDepositTransaction);
        log("Verify 1: Verify only the transactions of the according account displayed");
        List<String> lstAgent = page.tblDeposit.getColumn(3,20,true);
        Assert.assertTrue(page.checkAccountsSelectLabels("",usernameDepositTransaction,"",Collections.singletonList("") , lstAgent, Collections.singletonList("")));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate the list agent display correctly according selected brand in BO Deposit Transactions
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit Transactions tab
     *         3. Select a brand that having the agent added in Payment Configuration page
     *         4. Check the list Agent
     * @expect: 1. Verify the list agents of the according brand added in Payment Configuration page
     */
    @TestRails(id = "3860")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3860() {
        log("@title: Validate the list agent display correctly according selected brand in BO Deposit Transactions");
        log("pre-condition 1: Login BO");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnDepositTab.click();
        log("Step 3. Select a brand that having the agent added in Payment Configuration page");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"", page.ddnBrand.getOptions().get(1),"","","");
        log("Step 4. Check the list Agent");
        List<String> lstAgent = page.ddnAgent.getOptions().subList(1,page.ddnAgent.getOptions().size());
        log("Verify 1: Verify the list agents of the according brand added in Payment Configuration page");
        Assert.assertTrue(page.checkAgentOnPaymentConfigurationByBrandName( page.ddnBrand.getOptions().get(1), lstAgent),page.ddnBrand.getOptions().get(1)+" has not agent in Payment Configuration Page");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate BO Deposit transaction is sorted DESC by  BO Transactions Dates
     * @pre-condition: 1. Login BO and There are some deposit transactions
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Deposit Transactions tab
     *         3. Select a brand has deposit transaction
     * @expect: 1. Verify the list transaction is sort DESC by transaction date
     */
    @TestRails(id = "3861")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3861() {
        log("@title: Validate BO Deposit transaction is sorted DESC by  BO Transactions Dates");
        log("pre-condition 1: Login BO and There are some deposit transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnDepositTab.click();
        log("Step 3. Select a brand has deposit transaction");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"", "Pradhanbet","","","");
        log("Verify 1: Verify the list transaction is sort DESC by transaction date");
        List<String> lstTransaction = page.tblDeposit.getColumn(4,20,true);
        Assert.assertEquals(lstTransaction,page.getListTransactionSorted(lstTransaction));
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate BO Withdraw transaction is sorted DESC by  BO Transactions Dates
     * @pre-condition: 1. Login BO and There are some deposit transactions
     * @steps: 1. Access Deposit/Withdrawal Transactions page
     *         2. Active Withdrawal Transactions tab
     *         3. Select a brand has withdrawal transaction
     * @expect: 1. Verify the list transaction is sort DESC by transaction date
     */
    @TestRails(id = "3862")
    @Test(groups = {"regression"})
    public void BO_Payment_Management_Deposit_Withdrawal_Transactions_3862() {
        log("@title: Validate BO Deposit transaction is sorted DESC by BO Transactions Dates");
        log("pre-condition 1: Login BO and There are some deposit transactions");
        log("Step 1. Access Deposit/Withdrawal Transactions page");
        DepositWithdrawalTransactionsPage page = backofficeHomePage.navigateDepositWithdrawalTransactionsPage();
        log("Step 2. Active Withdraw Transactions tab");
        page.btnWithdrawalTab.click();
        log("Step 3. Select a brand has deposit transaction");
        String fromDay = DateUtils.getDate(-30, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        page.searchData(fromDay,"", "Pradhanbet","","","");
        log("Verify 1: Verify the list transaction is sort DESC by transaction date");
        List<String> lstTransaction = page.tblWithDrawal.getColumn(4,20,true);
        Assert.assertEquals(lstTransaction,page.getListTransactionSorted(lstTransaction));
        log("INFO: Executed completely");
    }
}
