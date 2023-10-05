package membersite.testcases.cashsite;

import agentsite.pages.cashmanagement.PaymentChannelManagementPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import membersite.pages.DepositPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;
import java.util.Map;
import static common.MemberConstants.DEPOSIT_TAB;
import static common.MemberConstants.DepositPage.*;

public class PaymentTest extends BaseCaseTest {
    @TestRails(id = "3912")
    @Test(groups = {"regression_deposit"})
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
    @Test(groups = {"regression"})
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
    @Test(groups = {"regression_deposit"})
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
    @Test(groups = {"regression_deposit"})
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
    @Test(groups = {"regression_deposit"})
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
    @Test(groups = {"regression_deposit"})
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
    @Test(groups = {"regression_deposit"})
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
    @Test(groups = {"regression_deposit"})
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
    @Test(groups = {"regression_deposit"})
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

}
