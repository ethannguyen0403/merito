package membersite.testcases.cashsite;

import agentsite.pages.agentmanagement.SubUserListingPage;
import agentsite.pages.cashmanagement.*;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import agentsite.yopmail.YopmailMailBoxPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import com.paltech.utils.StringUtils;
import membersite.objects.AccountBalance;
import membersite.pages.PaymentPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.text.ParseException;
import java.util.*;

import static agentsite.yopmail.YopmailPage.openNewTab;
import static common.AGConstant.CashManagement.DepositWithdrawalTransaction.*;
import static common.AGConstant.HomePage.*;
import static common.AGConstant.LBL_WITHOUT_PERMISSION_ACCESS;
import static common.MemberConstants.*;
import static common.MemberConstants.CashManagement.*;

public class PaymentTest extends BaseCaseTest {
    @TestRails(id = "3912")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3912() {
        log("@title: Validate user can access deposit page when login is cash type in member site");
        log("Precondition: Login SAT account for cash site");
        log("Step 1. Click on Deposit button in header menu");
        PaymentPage page = memberHomePage.header.openDepositPage(_brandname);

        log("Verify deposit page display");
        Assert.assertTrue(page.lblTitle.isDisplayed(), "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3913")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3913() {
        log("@title: Validate deposit button not display if login account is not cash type member site");
        log("Precondition: Login SAT by the account is not for cash");
        log("Step 1. Observe Deposit in header menu");
        PaymentPage page = memberHomePage.header.openDepositPage(_brandname);

        log("Verify deposit button does not display");
        Assert.assertFalse(page.lblTitle.isDisplayed(), "FAILED! Deposit button is displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3914")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Payment_Page_TC3914(String agentLoginAccount, String username, String password) throws Exception {
        log("@title: Validate the list Payment Channel in member site is corrected as the active list payment channel in agent site");
        log("Precondition: Get the list deposit method that in active status in agent site");
        memberHomePage.logout();
        loginAgentCash(agentLoginAccount, password, true);
        PaymentChannelManagementPage paymentPage = agentHomePage.navigatePaymentChannelManagement();
        Map<String, String> mapPaymentStatus = paymentPage.getListPaymentStatus();

        log("Step 1. Login member site SAT by cash account");
        loginMember(username, password);

        log("Step 2. Active deposit site and check the list deposit method is correct as agent");
        PaymentPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        log("Verify the list deposit method is correct");
        depositPage.verifyListPaymentChannelDisplayCorrect(mapPaymentStatus, true);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3915")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3915() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: BANK TRANSFER Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on BANK TRANSFER tab");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        paymentPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        paymentPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3916")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3916() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: PAYTM Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on PAYTM tab");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        paymentPage.deposit(LBL_PAYTM, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        paymentPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3917")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3917() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: PHONEPE Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on PHONEPE tab");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        paymentPage.deposit(LBL_PHONEPE, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        paymentPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3918")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3918() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: GPAY Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on GPAY tab");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        paymentPage.deposit(LBL_GPAY, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        paymentPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3919")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3919() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: UPI Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on UPI tab");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        paymentPage.deposit(LBL_UPI, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        paymentPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "8576")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC8576() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: QR CODE Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on QR CODE tab");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        paymentPage.deposit(LBL_QR_CODE, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        paymentPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3920")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password", "emailAddress"})
    public void Payment_Page_TC3920(String agentLoginAccount, String password, String emailAddress) throws Exception {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate user receive an email when deposit amount is accepted by agent");
        log("Precondition: BANK TRANSFER Payment Chanel is active" +
                "Login SAT account for cash");
        log("Step 1. Deposit by any payment channel");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);
        paymentPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        String refNo = paymentPage.getRefNo();
        memberHomePage.logout();

        log("Step 2. Agent approve the transaction");
        loginAgentCash(agentLoginAccount, password, true);
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();

        depositWithdrawalTransactionPage.search("", "", "", "", "", refNo);
        depositWithdrawalTransactionPage.actionTransaction(ACTION_LST.get(0), String.format("Automation QC Test Approve Txn %s", refNo), true);
        depositWithdrawalTransactionPage.closeActionTransactionAlertMessage();

        log("Step 3. User login the email that register  when sign in");
        List<ArrayList<String>> emailInfo = YopmailMailBoxPage.getFirstActiveMailBox("https://yopmail.com/", emailAddress);
        log("Verify user can receive email successfully");
        Assert.assertTrue(emailInfo.get(0).contains("Your request has been approved"), "FAILED! User is not received approval email");
        log("INFO: Executed completely");
    }

    @TestRails(id = "193")
    @Test(groups = {"cashsite_stg", "2022.10.31"})
    @Parameters({"agentLoginAccount", "agentWithoutPermissionLoginAccount", "password"})
    public void Payment_Page_TC193(String agentLoginAccount, String agentWithoutPermissionLoginAccount, String password) throws Exception {
        //only run in STG since unable to create account without permission for testing in PROD
        log("@title: Validate accounts without permission cannot see the menu item 'Deposit/Withdrawal Transactions'");
        log("Precondition: Account is inactivated permission 'Deposit/Withdrawal Transactions'");
        memberHomePage.logout();
        HashMap<String, Boolean> permissions = new HashMap<String, Boolean>() {
            {
                put("Deposit/Withdrawal Transactions", false);
                put("Payment Channel Management", true);
                put("Quick Deposit Configuration", true);
            }
        };
        loginAgentCash(agentLoginAccount, password, true);
        SubUserListingPage subUserListingPage = agentHomePage.navigateSubUserListingPage();
        subUserListingPage.subUserListing.editSubUser(agentWithoutPermissionLoginAccount, "", "Active", "", "", permissions);
        agentHomePage.logout();

        log("Step 1. Login the Cash Backend site agent site");
        loginAgentCash(agentWithoutPermissionLoginAccount, password, true);
        log("Step 2. Check menu item 'Deposit/Withdrawal Transactions");
        List<String> lstSubMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(CASH_MANAGEMENT);
        log("Verify. Menu 'Deposit/Withdrawal Transactions' is not shown");
        Assert.assertFalse(lstSubMenu.contains(DEPOSIT_WITHDRAWAL_TRANSACTION), "FAILED! Sub menu list displays Deposit/ Withdrawal Transaction");

        log("Step 3. Trying to access page by using url");
        String bypassURL = domainCashURL + "/agent-management-ui/#/home/deposit-transaction";
        openNewTab(bypassURL);
        log("Verify. User cannot access 'Deposit/Withdrawal Transactions' page");
        Assert.assertEquals(agentHomePage.lblAlert.getText(), LBL_WITHOUT_PERMISSION_ACCESS, "FAILED! Alert message does not appear when user access by bypass URL");
        log("INFO: Executed completely");
    }

    @TestRails(id = "195")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC193(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate accounts without permission cannot see the menu item 'Deposit/Withdrawal Transactions'");
        log("Precondition: Account is inactivated permission 'Deposit/Withdrawal Transactions'");
        memberHomePage.logout();
        log("Step 1. Navigate to the Cash Backend site");
        loginAgentCash(agentLoginAccount, password, true);
        log("Step 2. Expand the menu 'Cash Management' and access 'Deposit/Withdrawal Transactions' page");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();

        log("Verify. User can access 'Deposit/Withdrawal Transactions' page successfully");
        Assert.assertEquals(depositWithdrawalTransactionPage.getPageTitle(), DEPOSIT_WITHDRAWAL_TRANSACTION, "FAILED! Deposit/ Withdrawal Transaction page does not display");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3921")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3921() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        String filterDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);

        log("@title: Validate transaction history is correctly in member site when user submit a success deposit transaction");
        log("Precondition: Login member site SAT by cash account");
        log("Step 2. Access deposit page and deposit by any payment channel");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);
        paymentPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        String depositDateTime = DateUtils.getDate(0, "yyyy-MM-dd HH:mm", GMT_5_30);
        String refNo = paymentPage.getRefNo();
        AccountBalance accountBalance = memberHomePage.header.getUserCashBalance();
        log("Step 3. Access Transaction History and verify the deposit transaction");
        paymentPage.switchTab(TRANSACTION_HISTORY_TAB);
        paymentPage.filterTransactionHistory(filterDate, filterDate);

        log("Verify. the deposit transaction in Pending status with correct info: Reference No., Type, Status ,Start Balance, Amount ,End Balance Transaction Date\n" +
                "Start Balance = End Balance");
        paymentPage.verifyTransactionHistoryInfo(refNo, "DEPOSIT", "Pending", accountBalance.getBalance(), "9.00", accountBalance.getBalance(), depositDateTime);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3922")
    @Test(groups = {"cashsite_stg", "2022.10.31"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Payment_Page_TC3922(String agentLoginAccount, String username, String password) throws Exception {
        //This test case cannot run in PRO
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        String filterDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);

        log("@title: Validate transaction history is correctly in member site when user submit a success deposit transaction");
        log("Precondition: Login member site SAT by cash account");
        log("Step 1. Login member site SAT by cash account and get the balance before deposit");
        AccountBalance accountBalanceBefore = memberHomePage.header.getUserCashBalance();

        log("Step 2. Access deposit page and deposit by any payment channel");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);
        paymentPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        String refNo = paymentPage.getRefNo();
        memberHomePage.logout();

        log("Step 3. Login agent site > Deposit/withdrawal transaction and reject the transaction");
        loginAgentCash(agentLoginAccount, password, true);
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        depositWithdrawalTransactionPage.search("", "", "", "", "", refNo);
        depositWithdrawalTransactionPage.actionTransaction(ACTION_LST.get(1), String.format("Automation QC Test Reject Txn %s", refNo), true);
        String depositDateTime = DateUtils.getDate(0, "yyyy-MM-dd HH:mm", GMT_5_30);
        depositWithdrawalTransactionPage.closeActionTransactionAlertMessage();
        agentHomePage.logout();

        log("Step 4. Re-login member site and check the transaction");
        loginMember(username, password);
        memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(TRANSACTION_HISTORY_TAB);
        paymentPage.filterTransactionHistory(filterDate, filterDate);

        log("Verify. User balance does not update\n" +
                "Verify the deposit transaction in reject  status with correct info: Reference No., Type, Status ,Start Balance, Amount ,End Balance Transaction Date\n" +
                "Start Balance = End Balance");
        paymentPage.verifyTransactionHistoryInfo(refNo, "DEPOSIT", "Rejected", accountBalanceBefore.getBalance(), "9.00", accountBalanceBefore.getBalance(), depositDateTime);
        log("INFO: Executed completely");
    }

//    @TestRails(id = "3924")
//    @Test(groups = {"cashsite_stg"})
//    @Parameters({"agentLoginAccount", "username", "password"})
//    public void Payment_Page_TC3924(String agentLoginAccount, String username, String password) throws Exception {
//        //This test case cannot run in PRO
//        String amount = "9";
//        String transactionId = StringUtils.generateNumeric(10);
//        String filterDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_TIMEZONE);
//
//        log("@title: Validate transaction history is correctly in member site when user submit a success deposit transaction");
//        log("Precondition: Login member site SAT by cash account");
//
//        log("Step 2. Access deposit page and deposit by any payment channel");
//        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
//        paymentPage.switchTab(DEPOSIT_TAB);
//        paymentPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
//        String refNo = paymentPage.getRefNo();
//        AccountBalance accountBalanceBefore = memberHomePage.header.getUserCashBalance();
//        Double calculateBalance = Double.valueOf(accountBalanceBefore.getBalance().replace(",","")) - Double.valueOf(amount);
//        String expectedBalanceAfterApprove = String.format("%,.2f",calculateBalance);
//        memberHomePage.logout();
//
//        log("Step 3. Login agent site > Deposit/withdrawal transaction and reject the transaction");
//        loginAgentCash(agentLoginAccount, password, true);
//        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
//        depositWithdrawalTransactionPage.filter("","","","","",refNo);
//        depositWithdrawalTransactionPage.actionTransaction(ACTION_LST.get(0), String.format("Automation QC Test Reject Txn %s", refNo), true);
//        String depositDateTime = DateUtils.getDate(0, "yyyy-MM-dd HH:mm", GMT_TIMEZONE);
//        depositWithdrawalTransactionPage.closeActionTransactionAlertMessage();
//        agentHomePage.logout();
//
//        log("Step 4. Re-login member site and check the transaction");
//        loginMember(username, password);
//        memberHomePage.header.openDepositPage(_brandname);
//        paymentPage.switchTab(TRANSACTION_HISTORY_TAB);
//        paymentPage.filterTransactionHistory(filterDate, filterDate);
//
//        log("Verify. User balance does not update\n" +
//                "Verify the deposit transaction in reject  status with correct info: Reference No., Type, Status ,Start Balance, Amount ,End Balance Transaction Date\n" +
//                "Start Balance = End Balance");
//        paymentPage.verifyTransactionHistoryInfo(refNo, "DEPOSIT", "Rejected", expectedBalanceAfterApprove, "9.00", expectedBalanceAfterApprove, depositDateTime);
//        log("INFO: Executed completely");
//    }

    @TestRails(id = "3925")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3925() throws ParseException {
        String startDate = DateUtils.getDate(-2, "yyyy-MM-dd", GMT_5_30);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);

        log("@title: Validate transaction history is filtered by searching date range  in member site");
        log("Precondition: Login member site SAT by cash account");
        log("Step 1. Access deposit page and click on Transaction History");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(TRANSACTION_HISTORY_TAB);

        log("Step 2. Search transaction in 2 days");
        paymentPage.filterTransactionHistory(startDate, endDate);

        log("Verify the transactions in 2 days displays");
        paymentPage.verifyFilterDateTimeShowCorrect(startDate, endDate);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3926")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3926() throws ParseException {
        String date = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);

        log("@title: Validate can filter transaction history  in today  in member site");
        log("Precondition: Login member site SAT by cash account");
        log("Step 1. Access deposit page and click on Transaction History");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(TRANSACTION_HISTORY_TAB);

        log("Step 2. Click on Today button");
        paymentPage.clickPeriodTime(TODAY);

        log("Verify Start date and  End Date is today\n" +
                "Verify the today transaction display. If have no data, display the message \"No record found\"");
        paymentPage.verifyFilterDateTimeShowCorrect(date, date);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3927")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3927() throws ParseException {
        String date = DateUtils.getDate(-1, "yyyy-MM-dd", GMT_5_30);

        log("@title: Validate can filter transaction history in yesterday in member site");
        log("Precondition: Login member site SAT by cash account");
        log("Step 1. Access deposit page and click on Transaction History");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(TRANSACTION_HISTORY_TAB);

        log("Step 2. Click on Yesterday button");
        paymentPage.clickPeriodTime(YESTERDAY);

        log("Verify Start date and  End Date is today\n" +
                "Verify the today transaction display. If have no data, display the message \"No record found\"");
        paymentPage.verifyFilterDateTimeShowCorrect(date, date);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3928")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3928() throws ParseException {
        log("@title: Validate can filter transaction history in this week in member site");
        log("Precondition: Login member site SAT by cash account");
        log("Step 1. Access deposit page and click on Transaction History");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        String firstDateWeek = DateUtils.getFirstDayOfCurrentWeek("yyyy-MM-dd");
        String currentDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);
        paymentPage.switchTab(TRANSACTION_HISTORY_TAB);

        log("Step 2. Click on Yesterday button");
        paymentPage.clickPeriodTime(THIS_WEEK);

        log("Verify Start date and  End Date is today\n" +
                "Verify the today transaction display. If have no data, display the message \"No record found\"");
        Assert.assertEquals(paymentPage.getValueOfEndDate(), currentDate, "FAILED! End Date textbox value is not current date");
        paymentPage.verifyFilterDateTimeShowCorrect(firstDateWeek, currentDate);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3929")
    @Test(groups = {"cashsite", "2022.10.31"})
    public void Payment_Page_TC3929() throws ParseException {
        log("@title: Validate can filter transaction history in this month in member site");
        log("Precondition: Login member site SAT by cash account");
        log("Step 1. Access deposit page and click on Transaction History");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        String firstDateMonth = DateUtils.getFirstDateOfMonth(DateUtils.getYear(GMT_5_30), DateUtils.getMonth(GMT_5_30), "yyyy-MM-dd");
        String currentDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);
        paymentPage.switchTab(TRANSACTION_HISTORY_TAB);

        log("Step 2. Click on Yesterday button");
        paymentPage.clickPeriodTime(THIS_MONTH);

        log("Verify the transactions created in this month display\n" +
                "Verify Start date is the first day of the current month and End Date is the current day");
        Assert.assertEquals(paymentPage.getValueOfEndDate(), currentDate, "FAILED! End Date textbox value is not current date");
        paymentPage.verifyFilterDateTimeShowCorrect(firstDateMonth, currentDate);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3930")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3930(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate Cash Management section display when login agent site for cash domain");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);
        log("Step 2. Expand left menu and observe Cash Management");
        List<String> lstSubMenu = agentHomePage.leftMenu.leftMenuList.getListSubMenu(CASH_MANAGEMENT);
        List<String> lstExpectedSubMenu = Arrays.asList(DEPOSIT_WITHDRAWAL_TRANSACTION, PAYMENT_CHANNEL_MANAGEMENT, QUICK_DEPOSIT_CONFIG);
        log("Verify Cash Management section display with 3 sub menus:\n" +
                "Deposit/withdrawal Transactions\n" +
                "Payment Channel Management\n" +
                "Quick Deposit Configuration");
        Assert.assertEquals(lstSubMenu, lstExpectedSubMenu, String.format("FAILED! Cash Management does not show with 3 sub menu %s", lstExpectedSubMenu));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3931")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentNonCashLoginAccount", "password"})
    public void Payment_Page_TC3931(String agentNonCashLoginAccount, String password) throws Exception {
        log("@title: Validate Cash Management section is not display when login agent site none cash domain");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgent(agentNonCashLoginAccount, password, true);
        log("Step 2. Expand left menu and observe Cash Management");
        log("Verify Menu Cash Management does not display");
        Assert.assertFalse(agentHomePage.leftMenu.leftMenuList.isMenuDisplay(CASH_MANAGEMENT));
        log("INFO: Executed completely");
    }

    @TestRails(id = "3932")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3932(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate UI Deposit/Withdrawal Transaction in agent site is correct");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);
        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Verify UI is displayed correctly: Page title\n" +
                "Labels: Deposit radio button, Userban, Transaction Status, From, To, Internal Ref No\n" +
                "Default values: Username textbox place holder: Username or Login ID\n" +
                "Transaction Status: All, Pending, Approval, Rejected\n" +
                "Payment Type: All,\n" +
                "From: today date yyyyy-mm-dd\n" +
                "To: today date yyyyy-mm-dd\n" +
                "Table header: No. Username, LoginID, Internal Ref No, Payment Type, Currency, Deposit Amount, Create Date, Update Date, Status, Action");
        depositWithdrawalTransactionPage.verifyUIDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "3933")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3933(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate Cash Management section is not display when login agent site none cash domain");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);
        String userID = ProfileUtils.getProfile().getUserID();
        String downlineUserCode = DownLineListingUtils.getDownLineUsers(userID, "PL", "ACTIVE", _brandname).get(0).getUserCode();

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Input a Username and click search");
        depositWithdrawalTransactionPage.search(downlineUserCode, "", "","","","");
        log("Verify transaction of the account with the according username display");
        depositWithdrawalTransactionPage.verifySearchResult(downlineUserCode, "", "", "", "", "", "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3934")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Payment_Page_TC3934(String agentLoginAccount, String username, String password) throws Exception {
        log("@title: Validate can filter deposit by login ID in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Input a Login ID and click search");
        depositWithdrawalTransactionPage.search(username, "", "","","","");
        log("Verify transaction of the account with the according Login Id display");
        depositWithdrawalTransactionPage.verifySearchResult("", username, "", "", "", "", "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3935")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3935(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate can filter deposit by status = Pending in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select Transaction status= Pending");
        depositWithdrawalTransactionPage.search("", LST_TRANSACTION_STATUS.get(1), "","","","");
        log("Verify only pending transaction display");
        depositWithdrawalTransactionPage.verifySearchResult("", "", LST_TRANSACTION_STATUS.get(1), "", "", "", "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3936")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3936(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate can filter deposit by status = Approved in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select Transaction status= Approved");
        depositWithdrawalTransactionPage.search("", LST_TRANSACTION_STATUS.get(2), "","","","");
        log("Verify only Approved transaction display");
        depositWithdrawalTransactionPage.verifySearchResult("", "", LST_TRANSACTION_STATUS.get(2), "", "", "", "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3937")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3937(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate can filter deposit by status = Rejected in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select Transaction status= Rejected");
        depositWithdrawalTransactionPage.search("", LST_TRANSACTION_STATUS.get(3), "","","","");
        log("Verify only Rejected transaction display");
        depositWithdrawalTransactionPage.verifySearchResult("", "", LST_TRANSACTION_STATUS.get(3), "", "", "", "");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3938")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3938(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate can filter deposit by status = All in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select Transaction status= All");
        depositWithdrawalTransactionPage.search("", LST_TRANSACTION_STATUS.get(0), "","","","");
        log("Verify Transaction in any status display");
        depositWithdrawalTransactionPage.verifySearchResult("", "", LST_TRANSACTION_STATUS.get(0), "", "", "", "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3939")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3939(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate can filter deposit by Payment Type =  All in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select Payment Type= All");
        depositWithdrawalTransactionPage.search("", "", LST_PAYMENT_TYPE.get(0),"","","");
        log("Verify all payment transaction display");
        depositWithdrawalTransactionPage.verifySearchResult("", "", "", LST_PAYMENT_TYPE.get(0), "", "", "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3940")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3940(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate can filter deposit by Payment Type =  UPI in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select Payment Type= All");
        depositWithdrawalTransactionPage.search("", "", LST_PAYMENT_TYPE.get(5),"","","");
        log("Verify Transaction in any status display");
        depositWithdrawalTransactionPage.verifySearchResult("", "", "", LST_PAYMENT_TYPE.get(5), "", "", "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3941")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3941(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate can filter deposit in valid date range Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();
        String startDate = DateUtils.getDate(-2, "yyyy-MM-dd", GMT_5_30);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);
        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select from and to range in a week");
        depositWithdrawalTransactionPage.search("", "","",startDate,endDate,"");
        log("Verify only transaction created in date range from/ to selected display");
        depositWithdrawalTransactionPage.verifySearchResult("", "", "", "", startDate, endDate, "");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3942")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3942(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate message if input from date greater than to date in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();
        String startDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);
        String endDate = DateUtils.getDate(-1, "yyyy-MM-dd", GMT_5_30);
        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select from and to range in a week");
        depositWithdrawalTransactionPage.search("", "","",startDate,endDate,"");
        log("Verify validate message incorrect date range display \"To date cannot earlier than From date. Please redefine the search criteria.");
        depositWithdrawalTransactionPage.verifyDateRangeErrorMessage("To date cannot earlier than From date. Please redefine the search criteria.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "9142")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC9142(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate message if input from date/ to date greater than 1 month in Deposit/Withdrawal Transaction page Agent Site");
        memberHomePage.logout();
        String startDate = DateUtils.getDate(-32, "yyyy-MM-dd", GMT_5_30);
        String endDate = DateUtils.getDate(0, "yyyy-MM-dd", GMT_5_30);
        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select from and to range in a week");
        depositWithdrawalTransactionPage.search("", "","",startDate, endDate,"");
        log("Verify validate message incorrect date range display \"Invalid time range. Please redefine the search criteria.");
        depositWithdrawalTransactionPage.verifyDateRangeErrorMessage("Invalid time range. Please redefine the search criteria.");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3943")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3943(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate valdiate can filter by Interal Ref No in Deposit/Withdrawal Transaction page Agent Site");
        log("Precondition: The account has deposit transaction in any status");
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate user receive an email when deposit amount is accepted by agent");
        log("Precondition: BANK TRANSFER Payment Chanel is active" +
                "Login SAT account for cash");
        log("Step 1. Deposit by any payment channel");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);
        paymentPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        String refNo = paymentPage.getRefNo();
        memberHomePage.logout();

        log("Step 1. Login SAT Cash site Agent Site");
        loginAgentCash(agentLoginAccount, password, true);

        log("Step 2. Click on menu Deposit/Withdrawal Transaction");
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        log("Step 3. Select from and to range in a week");
        depositWithdrawalTransactionPage.search("", "","","", "",refNo);
        log("Verify validate message incorrect date range display \"Invalid time range. Please redefine the search criteria.");
        depositWithdrawalTransactionPage.verifySearchResult("", "", "", "", "", "", refNo);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3944")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3944(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate the list payment channel display correct in Agent Site");
        log("Precondition: Login Agent Site Cash account");
        log("Step 1. Click Payment Channel Management menu");
        memberHomePage.logout();
        loginAgentCash(agentLoginAccount, password, true);
        PaymentChannelManagementPage paymentChannelManagementPage = agentHomePage.navigatePaymentChannelManagement();

        log("Verify this list payment channel is correct:\n" +
                "BANK TRANSFER\n" +
                "PAYTM\n" +
                "PHONEPE\n" +
                "GPAY\n" +
                "UPI\n" +
                "QR CODE");
        paymentChannelManagementPage.verifyListChannelDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "3945")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Payment_Page_TC3945(String agentLoginAccount, String username, String password) throws Exception {
        log("@title: Validate the list Payment Channel in member site is corrected as the active list payment channel in agent site");
        log("Precondition: Get the list deposit method that in active status in agent site");
        memberHomePage.logout();
        loginAgentCash(agentLoginAccount, password, true);
        PaymentChannelManagementPage paymentPage = agentHomePage.navigatePaymentChannelManagement();
        Map<String, String> mapPaymentStatus = paymentPage.getListPaymentStatus();

        log("Step 1. Login member site SAT by cash account");
        loginMember(username, password);

        log("Step 2. Active deposit site and check the list deposit method is correct as agent");
        PaymentPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        log("Verify the list deposit method is correct");
        depositPage.verifyListPaymentChannelDisplayCorrect(mapPaymentStatus, false);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3946")
    @Test(groups = {"cashsite", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3946(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate can view history of payment change in Agent site");
        log("Precondition: Login Agent Site Cash account");
        memberHomePage.logout();
        loginAgentCash(agentLoginAccount, password, true);
        log("Step 1. Click Payment Channel Management menu");
        PaymentChannelManagementPage paymentPage = agentHomePage.navigatePaymentChannelManagement();
        log("Step 2. Click on View link in History column of any payment channel e.g: BANK TRANSFER");
        PaymentHistoryPopup popup = paymentPage.clickViewLink(LBL_BANK_TRANSFER);

        log("Verify the history popup display with correct UI\n" +
                "\n" +
                "Title: History - BANK TRANSFER\n" +
                "Table header:  NO, Actions, Last update Time, Last Update By");
        popup.verifyUIDisplayCorrect(LBL_BANK_TRANSFER);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3951")
    @Test(groups = {"cashsite_stg", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password", "username"})
    public void Payment_Page_TC3951(String agentLoginAccount, String password, String username) throws Exception {
        //only run in STG
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can approve a pending transaction");
        log("Precondition: Have a deposit transaction submit by player");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);
        paymentPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        String refNo = paymentPage.getRefNo();
        memberHomePage.logout();

        log("Step 1. Search the transaction in precondition");
        log("Step 2. Click on Review link of the transaction");
        loginAgentCash(agentLoginAccount, password, true);
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        depositWithdrawalTransactionPage.search("", "", "", "", "", refNo);
        log("Step 3. Click on Approve button");
        String comment = String.format("Automation QC Test Approve Txn %s", refNo);
        depositWithdrawalTransactionPage.actionTransaction(ACTION_LST.get(0), comment, true);
        depositWithdrawalTransactionPage.closeActionTransactionAlertMessage();
        log("Step 4. Click Detail link and verify the approve info");
        TransactionDetailPopup popup = depositWithdrawalTransactionPage.openTransactionDetail(TRANSACTION_DETAIL_ACTION_LST.get(1));
        log("Verify the transaction is change status to approved in summary and transaction details");
        popup.verifyInfoDetailDisplayCorrect(username, refNo, LBL_BANK_TRANSFER, LST_TRANSACTION_STATUS.get(2), amount, comment);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3952")
    @Test(groups = {"cashsite_stg", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password", "username"})
    public void Payment_Page_TC3952(String agentLoginAccount, String password, String username) throws Exception {
        //only run in STG
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can reject a pending transaction");
        log("Precondition: Have a deposit transaction submit by player");
        PaymentPage paymentPage = memberHomePage.header.openDepositPage(_brandname);
        paymentPage.switchTab(DEPOSIT_TAB);
        paymentPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        String refNo = paymentPage.getRefNo();
        memberHomePage.logout();

        log("Step 1. Search the transaction in precondition");
        log("Step 2. Click on Review link of the transaction");
        loginAgentCash(agentLoginAccount, password, true);
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();
        depositWithdrawalTransactionPage.search("", "", "", "", "", refNo);
        log("Step 3. Click on Reject button");
        String comment = String.format("Automation QC Test Reject Txn %s", refNo);
        depositWithdrawalTransactionPage.actionTransaction(ACTION_LST.get(1), comment, true);
        depositWithdrawalTransactionPage.closeActionTransactionAlertMessage();
        log("Step 4. Click Detail link and verify the Reject info");
        TransactionDetailPopup popup = depositWithdrawalTransactionPage.openTransactionDetail(TRANSACTION_DETAIL_ACTION_LST.get(1));
        log("Verify the transaction is change status to Rejected in summary and transaction details");
        popup.verifyInfoDetailDisplayCorrect(username, refNo, LBL_BANK_TRANSFER, LST_TRANSACTION_STATUS.get(3), amount, comment);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3948")
    @Test(groups = {"cashsite1", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3948(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate Clear button works");
        log("Precondition: Login Agent Site Cash account");
        memberHomePage.logout();
        loginAgentCash(agentLoginAccount, password, true);
        log("Step 1. Click Quick Deposit Configuration  menu");
        QuickDepositConfigurationPage quickDepositConfigurationPage = agentHomePage.navigateQuickDepositConfiguration();
        log("Step 2. Click clear button");
        quickDepositConfigurationPage.clickClear();

        log("Verify all values in 6 buttons is reset to 0");
        List<String> lstUpdateValue = Arrays.asList("0", "0", "0", "0", "0", "0");
        quickDepositConfigurationPage.verifyQuickDepositAmount(lstUpdateValue);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3949")
    @Test(groups = {"cashsite1", "2022.10.31"})
    @Parameters({"agentLoginAccount", "password"})
    public void Payment_Page_TC3949(String agentLoginAccount, String password) throws Exception {
        log("@title: Validate Clear button works");
        log("Precondition: Login Agent Site Cash account");
        memberHomePage.logout();
        loginAgentCash(agentLoginAccount, password, true);
        log("Step 1. Click Quick Deposit Configuration  menu");
        QuickDepositConfigurationPage quickDepositConfigurationPage = agentHomePage.navigateQuickDepositConfiguration();
        log("Step 2. Click clear button");
        List<String> lstBeforeUpdate = quickDepositConfigurationPage.getListQuickDepositAmount();
        List<String> lstUpdateValue = Arrays.asList("1", "2", "3", "4", "5", "6");
        try {
            quickDepositConfigurationPage.updateQuickDepositAmount(lstUpdateValue);

            log("Verify all values in 6 buttons is reset to 0");
            quickDepositConfigurationPage.verifyQuickDepositAmount(lstUpdateValue);

        } finally {
            log("Post-condition: Revert all updated Quick Deposit amount value");
            quickDepositConfigurationPage.updateQuickDepositAmount(lstBeforeUpdate);
        }
        log("INFO: Executed completely");
    }
}
