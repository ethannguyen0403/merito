package membersite.testcases.cashsite;

import agentsite.pages.agentmanagement.SubUserListingPage;
import agentsite.pages.cashmanagement.DepositWithdrawalTransactionPage;
import agentsite.pages.cashmanagement.PaymentChannelManagementPage;
import agentsite.yopmail.YopmailMailBoxPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.DepositPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static agentsite.yopmail.YopmailPage.openNewTab;
import static common.AGConstant.HomePage.CASH_MANAGEMENT;
import static common.AGConstant.HomePage.DEPOSIT_WITHDRAWAL_TRANSACTION;
import static common.AGConstant.LBL_WITHOUT_PERMISSION_ACCESS;
import static common.MemberConstants.CashManagement.*;

public class PaymentTest extends BaseCaseTest {
    @TestRails(id = "3912")
    @Test(groups = {"cashsite"})
    public void Register_Page_TC3912() {
        log("@title: Validate user can access deposit page when login is cash type in member site");
        log("Precondition: Login SAT account for cash site");
        log("Step 1. Click on Deposit button in header menu");
        DepositPage page = memberHomePage.header.openDepositPage(_brandname);

        log("Verify deposit page display");
        Assert.assertTrue(page.lblTitle.isDisplayed(), "FAILED! Deposit page is not displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3913")
    @Test(groups = {"cashsite"})
    public void Register_Page_TC3913() {
        log("@title: Validate deposit button not display if login account is not cash type member site");
        log("Precondition: Login SAT by the account is not for cash");
        log("Step 1. Observe Deposit in header menu");
        DepositPage page = memberHomePage.header.openDepositPage(_brandname);

        log("Verify deposit button does not display");
        Assert.assertFalse(page.lblTitle.isDisplayed(), "FAILED! Deposit button is displayed");
        log("INFO: Executed completely");
    }

    @TestRails(id = "3914")
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Register_Page_TC3914(String agentLoginAccount, String username, String password) throws Exception {
        log("@title: Validate the list Payment Channel in member site is corrected as the active list payment channel in agent site");
        log("Precondition: Get the list deposit method that in active status in agent site");
        memberHomePage.logout();
        loginAgentCash(agentLoginAccount, password, true);
        PaymentChannelManagementPage paymentPage = agentHomePage.navigatePaymentChannelManagement();
        Map<String, String> mapPaymentStatus = paymentPage.getListPaymentStatus();

        log("Step 1. Login member site SAT by cash account");
        loginMember(username, password);

        log("Step 2. Active deposit site and check the list deposit method is correct as agent");
        DepositPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        log("Verify the list deposit method is correct");
        depositPage.verifyListPaymentChannelDisplayCorrect(mapPaymentStatus);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3915")
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Register_Page_TC3915() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: BANK TRANSFER Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on BANK TRANSFER tab");
        DepositPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        depositPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        depositPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        depositPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3916")
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Register_Page_TC3916() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: PAYTM Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on PAYTM tab");
        DepositPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        depositPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        depositPage.deposit(LBL_PAYTM, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        depositPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3917")
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Register_Page_TC3917() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: PHONEPE Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on PHONEPE tab");
        DepositPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        depositPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        depositPage.deposit(LBL_PHONEPE, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        depositPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3918")
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Register_Page_TC3918() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: GPAY Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on GPAY tab");
        DepositPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        depositPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        depositPage.deposit(LBL_GPAY, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        depositPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3919")
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Register_Page_TC3919() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: UPI Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on UPI tab");
        DepositPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        depositPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        depositPage.deposit(LBL_UPI, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        depositPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "8576")
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "username", "password"})
    public void Register_Page_TC8576() {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate can deposit by Bank Transfer in member site");
        log("Precondition: QR CODE Payment Chanel is active\n" +
                "Login SAT account for cash");
        log("Step 2. Active deposit site and click on QR CODE tab");
        DepositPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        depositPage.switchTab(DEPOSIT_TAB);

        log("Step 3. Input the required fields with valid info and click submit");
        depositPage.deposit(LBL_QR_CODE, amount, transactionId, true, true);
        log("Verify message deposit success display with the template:\n" +
                "Thank you for your deposit with SAT Sport\n" +
                "Here are your deposit details: Deposit details successfully received and currently under approval\n" +
                "Reference ID: PPD1678345532\n" +
                "Estimation time of approval 15mins\n" +
                "You will get an approval message on your email once deposit is available in your account");
        depositPage.verifyDepositSuccessMessage(_brandname);
        log("INFO: Executed completely");
    }

    @TestRails(id = "3920")
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "password", "emailAddress"})
    public void Register_Page_TC3920(String agentLoginAccount, String password, String emailAddress) throws Exception {
        String amount = "9";
        String transactionId = StringUtils.generateNumeric(10);
        log("@title: Validate user receive an email when deposit amount is accepted by agent");
        log("Precondition: BANK TRANSFER Payment Chanel is active" +
                "Login SAT account for cash");
        log("Step 1. Deposit by any payment channel");
        DepositPage depositPage = memberHomePage.header.openDepositPage(_brandname);
        depositPage.switchTab(DEPOSIT_TAB);
        depositPage.deposit(LBL_BANK_TRANSFER, amount, transactionId, true, true);
        String refNo = depositPage.getRefNo();
        memberHomePage.logout();

        log("Step 2. Agent approve the transaction");
        loginAgentCash(agentLoginAccount, password, true);
        DepositWithdrawalTransactionPage depositWithdrawalTransactionPage = agentHomePage.navigateDepositWithdrawalTransaction();

        depositWithdrawalTransactionPage.filter("","","","","",refNo);
        depositWithdrawalTransactionPage.actionTransaction(ACTION_LST.get(0), String.format("Automation QC Test Txn %s", refNo), true);
        depositWithdrawalTransactionPage.closeActionTransactionAlertMessage();

        log("Step 3. User login the email that register  when sign in");
        List<ArrayList<String>> emailInfo = YopmailMailBoxPage.getFirstActiveMailBox("https://yopmail.com/",emailAddress);
        log("Verify user can receive email successfully");
        Assert.assertTrue(emailInfo.get(0).contains("Your request has been approved"), "FAILED! User is not received approval email");
        log("INFO: Executed completely");
    }

    @TestRails(id = "193")
    @Test(groups = {"cashsite_stg"})
    @Parameters({"agentLoginAccount", "agentWithoutPermissionLoginAccount", "password"})
    public void Register_Page_TC193(String agentLoginAccount, String agentWithoutPermissionLoginAccount, String password) throws Exception {
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
    @Test(groups = {"cashsite"})
    @Parameters({"agentLoginAccount", "password"})
    public void Register_Page_TC193(String agentLoginAccount, String password) throws Exception {
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

}
