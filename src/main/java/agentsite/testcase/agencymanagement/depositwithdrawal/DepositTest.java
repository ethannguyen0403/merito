package agentsite.testcase.agencymanagement.depositwithdrawal;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositPopup;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositToPopup;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositWithdraw;
import agentsite.pages.agentmanagement.depositwithdrawal.WithdrawalPopup;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DoubleUtils;
import common.AGConstant;
import membersite.objects.AccountBalance;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DepositTest extends BaseCaseTest {
    @TestRails(id = "3621")
    @Test(groups = {"regression_creditcash"})
    public void DepositWithdrawal_Deposit_3621() {
        log("@title: Validate that Deposit popup displays correct info.");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 2: Open Deposit popup of an account " + userCode);
        DepositPopup popup = (WithdrawalPopup) page.action(DepositWithdraw.Actions.DEPOSIT, userCode);

        String popupTitle = popup.lblTitle.getText();
        String expectedTitle = String.format(AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_TITLE, userCode, lstUsers.get(0).getLoginID());
        String memberBalance = popup.lblMemberBalance.getText().replace(",", "");
        String yourBalance = popup.lblYourBalance.getText().replace(",", "");

        log("Verify 1: Deposit popup is displayed");
        log("Verify 2: Info is displayed correctly");
        Assert.assertTrue(popup.popupDepositWithDraw.isDisplayed(), "ERROR: popupWithdrawal is not displayed");
        Assert.assertEquals(popupTitle, expectedTitle, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedTitle, popupTitle));
        Assert.assertEquals(Double.parseDouble(yourBalance), accountInfo.getCashBalance(), String.format("ERROR: The expected current balance is '%s' but found '%s'", accountInfo.getCashBalance(), yourBalance));
        Assert.assertEquals(Double.parseDouble(memberBalance), lstUsers.get(0).getCashBalance(), String.format("ERROR: The expected member's current balance is '%s' but found '%s'", lstUsers.get(0).getCashBalance(), memberBalance));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Deposit popup is closed when clicking Cancel button
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Open Deposit popup of an account
     * 3. Click Cancel button
     * @expect: 1.  Deposit popup is closed
     */
    @TestRails(id = "3622")
    @Test(groups = {"regression_creditcash"})
    public void DepositWithdrawal_Deposit_3622() {
        log("@title: Validate that Deposit popup is closed when clicking Cancel button");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String userCode = lstUsers.get(0).getUserCode();

        log("Step 2. Open Deposit popup of an account " + userCode);
        DepositToPopup popup = (DepositToPopup) page.action(DepositWithdraw.Actions.DEPOSIT, userCode);

        log("Step 3. Click Cancel button");
        popup.clickCancelBtn();

        log("Verify 1.Deposit popup is closed");
        Assert.assertFalse(popup.lblTitle.isDisplayed(), "FAILED! Deposit popup not close after click Cancel button");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there is an error message displayed when submitted without any amount
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Open Deposit popup of an account
     * 3. Deposit without any amount
     * @expect: 1. There is an error message when submitted without any amount
     * "Amount must be positive decimal with maximum two places and greater than zero"
     */
    @TestRails(id = "722")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_722() throws InterruptedException {
        log("@title: Validate that there is an error message displayed when submitted without any amount");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String userCode = lstUsers.get(0).getUserCode();

        log("Step 2. Open Deposit popup of an account");
        DepositToPopup popup = (DepositToPopup) page.action(DepositWithdraw.Actions.DEPOSIT, userCode);

        log("Step 3. Deposit without any amount");
        popup.deposit("", "");
        Thread.sleep(1000);
        String errorMessage = popup.lblAmountError.getText();

        log("Verify 1. There is an error message when submitted without any amount \"Amount must be positive decimal with maximum two places and greater than zero\"");
        Assert.assertEquals(errorMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_AMOUNT, String.format("ERROR: The expected error message is '%s' but found '%s'", errorMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_AMOUNT));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there is an insufficient error displayed when inputted an amount more than the current cash balance
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Open Deposit popup of an account
     * 3.  Deposit an amount more than the current cash balance
     * @expect: 1. There is an error message when submitted  amount more than the current cash balance
     */
    @TestRails(id = "723")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_723() {
        log("@title: Validate that there is an insufficient error displayed when inputted an amount more than the current cash balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
        String userCode = memberInfo.getUserCode();
        double currentCashBalance = accountInfo.getAvailableBalance() + 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 2. Open Deposit popup of an account");
        DepositToPopup popup = (DepositToPopup) page.action(DepositWithdraw.Actions.DEPOSIT, userCode);

        log("Step 3.  Deposit an amount more than the current cash balance");
        popup.deposit(Double.toString(currentCashBalance), String.format("Deposit amount %.2f", currentCashBalance));
        String errorMessage = popup.lblAmountError.getText();
        String expectedError = String.format(AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_INSUFFICIENT, accountInfo.getLoginID());

        log("Verify 1. There is an error message when submitted  amount more than the current cash balance");
        Assert.assertEquals(errorMessage, expectedError, String.format("ERROR: The expected error message is '%s' but found '%s'", expectedError, errorMessage));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can deposited  successfully
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2.  Click on  Deposit link of an account
     * 3. Input amount to deposit and input remark  then click Submit
     * 4. Close Deposit popup
     * @expect: 1. Verify message "Deposit is successful"
     * 2. Verify available balance of deposit account is updated
     */
    @TestRails(id = "724")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_724() {
        log("@title: Validate can deposited successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        String userCode = memberInfo.getUserCode();
        Double amountDeposit = 1.0;
        double expectedNewMemberCash = memberInfo.getCashBalance() + amountDeposit;
        double expectedLoginAccountAvBalance = DoubleUtils.roundUpWithTwoPlaces(DownLineListingUtils.getMyCreditCashBalance() - amountDeposit);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log(String.format("Step 1.1 Get data of user code before deposit", userCode));
        List<String> userInfoBefore = page.getRowContainsUsercode(userCode);
        String expectedAmountAfterDeposit = String.format(Locale.getDefault(), "%,.2f", Double.valueOf(userInfoBefore.get(page.colAvailableBalance - 1).replaceAll(",", "")) + amountDeposit);

        log("Step 2. Click on  Deposit link of an account");
        DepositToPopup popup = (DepositToPopup) page.action(DepositWithdraw.Actions.DEPOSIT, userCode);

        log("Step 3. Input amount to deposit  and input remark  then click Submit");
        popup.deposit(amountDeposit.toString(), "TC005 Deposit 1", true, true);
        String successMessage = popup.lblAmountSuccess.getText();
        double newMemberCash = popup.getNewMemberCashBalance();
        double newMemberCashAfter = popup.getMemberCashBalance();
        double newYourCash = popup.getNewYourCashBalance();
        double newYourCashAfter = popup.getYourCashBalance();

        log("Verify 1. Verify message \"Deposit is successful\"");
        Assert.assertEquals(successMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL,
                String.format("ERROR: The expected success message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL, successMessage));

        log("Verify 2. Verify available balance of deposit account is updated");
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, 0.01, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedLoginAccountAvBalance, newYourCash, 0.01, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedLoginAccountAvBalance, newYourCash));
        Assert.assertEquals(expectedLoginAccountAvBalance, newYourCashAfter, 0.01, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedLoginAccountAvBalance, newYourCashAfter));
        Assert.assertEquals(expectedNewMemberCash, newMemberCashAfter, 0.01, String.format("ERROR: The expected new cash balance of member is '%s' but found '%s'", expectedNewMemberCash, newMemberCashAfter));

        log("Step 4. Close Deposit popup");
        popup.clickCancelBtn();

        log(String.format("Step  Get data of user code after deposit", userCode));
        List<String> userInfoAfter = page.getRowContainsUsercode(userCode);

        log("Verify 3. Verify available balance of deposit account is updated");
        Assert.assertEquals(userInfoAfter.get(page.colAvailableBalance - 1), expectedAmountAfterDeposit,
                String.format("FAILED! Available Balance of account %s expected is %s but found %s", userCode, expectedAmountAfterDeposit, userInfoAfter.get(page.colAvailableBalance - 1)));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can multiple deposit  by click on Deposit button
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Check on more than 1 account and click on Deposit button
     * 3. Input amount to deposit enter remark and click on Submit button
     * @expect: 1. Deposit popup is disappeared and there is a green check display in Update Status column
     * 2. Verify Balance is updated correctly
     */
    @TestRails(id = "725")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_725() throws InterruptedException {
        log("@title: Validate can multiple deposit by click on Deposit button");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 1.1 Select Active account status and 5 item per page");
        page.filter("", "Active", "");

        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        int n = lstUsers.size() > 2 ? 2 : lstUsers.size();
        Double loginAccBalance = DownLineListingUtils.getMyCreditCashBalance();
        double depositAmount = 1;

        log("Step 1.2 Get main balance and list member info");
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(n);

        log("Step  2. Check on more than 1 account and click on Deposit button");
        page.selectUser(listMemberInfo);
        DepositPopup popup = page.openDepositPopup();

        log("Step 3. Input amount to deposit, select Credit Update enter remark and click on Submit button");
        popup.deposit(Double.toString(depositAmount), "Deposit TC006 deposit all", true, true);
        Thread.sleep(1000);
        log("Verify 1. Deposit popup is disappeared and there is a green check display in Update Status column");
        List<ArrayList<String>> listMemberInfoAfter = page.getMemberInfo(n);


        log("Verify 2. Verify Balance is updated correctly");
        Assert.assertTrue(page.verifyBalanceUpdated(depositAmount, loginAccBalance, listMemberInfo, listMemberInfoAfter, true, DepositWithdraw.Actions.SUCCESS_ICON), "FAILED! Balance value or status not correct after deposit");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Deposit popup is displayed when clicking Deposit button
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Filter with username
     * 3. Check a checkbox of the account
     * 4. Open Deposit popup
     * @expect: 1. Deposit popup is displayed and UI display correctly
     */
    @TestRails(id = "726")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_726() {
        log("@title: Validate that Deposit popup is displayed when clicking Deposit button");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
        String userCode = memberInfo.getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 2: Filter with username " + userCode);
        page.filter(userCode, "", "");

        log("Step 3: Check a checkbox of the account " + userCode);
        page.action(DepositWithdraw.Actions.CHECK, userCode);

        log("Step 4: Open Deposit popup");
        DepositPopup popup = page.openDepositPopup();

        log("Verify 1. Deposit popup is displayed and UI display correctly");
        Assert.assertTrue(popup.popupDepositWithDraw.isDisplayed(), "ERROR: popupDeposit is not displayed");
        String popupTitle = popup.lblTitle.getText();
        Assert.assertEquals(popupTitle, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT, String.format("ERROR: The expected popup title is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT, popupTitle));
        log("INFO: Executed completely");
    }

    @TestRails(id = "727")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_727() {
        log("@title: Validate can deposited by Win/Loss Settle successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
        String userCode = memberInfo.getUserCode();
        double depositAmount = 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        List<ArrayList<String>> mainBalanceInfo = page.getLoginAccountBalanceInfo();
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step 2.  Click icon on Deposit link of an account ");
        DepositToPopup popup = (DepositToPopup) page.action(DepositWithdraw.Actions.DEPOSIT, userCode);

        log("Step 3: Input amount to deposit , select Win/Loss Settle option, and input remark  then click Submit");
        popup.deposit("1", "TC008 Deposit Win/Loss Settle 1", false, true);
        String successMessage = popup.getMessage();
        double expectedNewMemberCash = memberInfo.getCashBalance() + 1;
        double expectedNewYourCash = accountInfo.getAvailableBalance() - 1;
        double newMemberCash = popup.getNewMemberCashBalance();
        double newMemberCashAfter = popup.getMemberCashBalance();
        double newYourCash = popup.getNewYourCashBalance();
        double newYourCashAfter = popup.getYourCashBalance();

        log("Step 4. Close Deposit popup");
        popup.clickCancelBtn();

        log("Verify  1. Verify message \"Withdraw is successful\"");
        Assert.assertEquals(successMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL, String.format("ERROR: The expected success message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL, successMessage));

        log("Verify  2. Verify  Win/loss value is update correctly as value in the popup");
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, 0.03, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedNewYourCash, newYourCash, 0.03, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCash));
        Assert.assertEquals(expectedNewYourCash, newYourCashAfter, 0.03, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCashAfter));
        Assert.assertEquals(expectedNewMemberCash, newMemberCashAfter, 0.03, String.format("ERROR: The expected new cash balance of member is '%s' but found '%s'", expectedNewMemberCash, newMemberCashAfter));

        log("Verify  3. Verify main balance info");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, depositAmount, true);
        Assert.assertEquals(page.getLoginAccountBalanceInfo(), mainBalanceInfoExpected, "FAILED! Main account balance info is not match after deposit");

        log("Verify  3. Verify downline balance info");
        List<ArrayList<String>> listMemberInfoExpected = page.calculateDownlineBalanceInfo(listMemberInfo, depositAmount, true, false);
        Assert.assertEquals(page.getMemberInfo(1), listMemberInfoExpected, "FAILED! Members List balance is not match with the expected");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can deposited by Win/Loss Settle by click on Deposit button
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Check on more than 1 accounts and click on deposit button
     * 3. Input amount to deposit, select Win/Loss Settle, enter remark and click on Submit button
     * @expect: 1. Deposit popup is disappeared and there is a green check display in Update Status column
     * 2. Verify WinLoss is updated correctly
     */
    @TestRails(id = "728")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_728() {
        log("@title: Validate can deposited by Win/Loss Settle by click on Deposit button");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 1.1 Filter Active account");
        page.filter("", "Active", "");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        List<AccountInfo> lstFiveUser = new ArrayList<>();
        int n = lstUsers.size() > 2 ? 2 : lstFiveUser.size();
        for (int i = 0; i < n; i++) {
            lstFiveUser.add(i, lstUsers.get(i));
        }
        String username1 = lstUsers.get(0).getUserCode();
        String username2 = lstUsers.get(1).getUserCode();
        double depositAmount = 1;
        double totalDepositAmount = depositAmount * (2);

        log("Step 1.2 Get main balance and list member info");
        List<ArrayList<String>> mainBalanceInfo = page.getLoginAccountBalanceInfo();

        log("Step  2. Check on more than 1 account and click on Deposit button");
        page.selectUser(lstUsers.get(0).getUserCode());
        page.selectUser(lstUsers.get(1).getUserCode());

        double creditInitationAcc1 = page.getDataByColumn(username1, page.colCreditInitiation);
        double totalBalanceAcc1 = page.getDataByColumn(username1, page.colTotalBalance);
        double availableBalanceAcc1 = page.getDataByColumn(username1, page.colAvailableBalance);
        double winlossAcc1 = page.getDataByColumn(username1, page.colWinloss);
        double creditInitationAcc2 = page.getDataByColumn(username2, page.colCreditInitiation);
        double totalBalanceAcc2 = page.getDataByColumn(username2, page.colTotalBalance);
        double availableBalanceAcc2 = page.getDataByColumn(username2, page.colAvailableBalance);
        double winlossAcc2 = page.getDataByColumn(username2, page.colWinloss);
        DepositPopup popup = page.openDepositPopup();

        log("Step 3. Input amount to deposit, select Win/Loss Settle, enter remark and click on Submit button");
        popup.deposit(Double.toString(depositAmount), "Deposit TC006 deposit for account" + username1 + " and acocunt " + username2, false, true);

        log("Step 4. Get info after deposit");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, totalDepositAmount, true);
        double actualCreditInitationAcc1 = page.getDataByColumn(username1, page.colCreditInitiation);
        double actualTotalBalanceAcc1 = page.getDataByColumn(username1, page.colTotalBalance);
        double actualAvailableBalanceAcc1 = page.getDataByColumn(username1, page.colAvailableBalance);
        double actualWinlossAcc1 = page.getDataByColumn(username1, page.colWinloss);
        double actualCreditInitationAcc2 = page.getDataByColumn(username2, page.colCreditInitiation);
        double actualTotalBalanceAcc2 = page.getDataByColumn(username2, page.colTotalBalance);
        double actualAvailableBalanceAcc2 = page.getDataByColumn(username2, page.colAvailableBalance);
        double actualWinlossAcc2 = page.getDataByColumn(username2, page.colWinloss);

        log("Verify 1. Deposit popup is disappeared and there is a green check display in Update Status column");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(0).getUserCode()), "FAILED! Green check on Update Status column is not display as expected");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(1).getUserCode()), "FAILED! Green check on Update Status column is not display as expected");

        log("Verify  2. Verify Balance of main account");
        Assert.assertEquals(page.getLoginAccountBalanceInfo(), mainBalanceInfoExpected, "FAILED! Main account balance info is not match with the expected");

        log("Verify  3. Verify balance on deposit account");
        Assert.assertEquals(actualCreditInitationAcc1, creditInitationAcc1, String.format("FAILED! Credit Initiation account %s is incorrect after deposit expected %s actual %s", username1, actualCreditInitationAcc1, creditInitationAcc1));
        Assert.assertEquals(actualTotalBalanceAcc1, totalBalanceAcc1 + 1, String.format("FAILED! Total balance account %s is incorrect after deposit expected %s actual %s", username1, actualTotalBalanceAcc1, totalBalanceAcc1 + 1));
        Assert.assertEquals(actualAvailableBalanceAcc1, availableBalanceAcc1 + 1, String.format("FAILED! Available Balance account %s is incorrect after deposit expected %s actual %s", username1, actualAvailableBalanceAcc1, availableBalanceAcc1 + 1));
        Assert.assertEquals(actualWinlossAcc1, winlossAcc1 + 1, String.format("FAILED! Available Balance account %s is incorrect after deposit expected %s actual %s", username1, actualWinlossAcc1, winlossAcc1 + 1));
        Assert.assertEquals(actualCreditInitationAcc2, creditInitationAcc2, String.format("FAILED! Credit Initiation account %s is incorrect after deposit expected %s actual %s", username2, actualCreditInitationAcc2, creditInitationAcc2));
        Assert.assertEquals(actualTotalBalanceAcc2, totalBalanceAcc2 + 1, String.format("FAILED! Total balance account %s is incorrect after deposit expected %s actual %s", username2, actualTotalBalanceAcc2, totalBalanceAcc2 + 1));
        Assert.assertEquals(actualAvailableBalanceAcc2, availableBalanceAcc2 + 1, String.format("FAILED! Available Balance account %s is incorrect after deposit expected %s actual %s", username2, actualAvailableBalanceAcc2, availableBalanceAcc2 + 1));
        Assert.assertEquals(actualWinlossAcc2, winlossAcc2 + 1, String.format("FAILED! Available Balance account %s is incorrect after deposit expected %s actual %s", username1, actualWinlossAcc2, winlossAcc2 + 1));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cannot deposit an amount more than the current balance
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Filter with username
     * 3. Check a checkbox of the account
     * 4. Open Deposit popup
     * 5.Deposit an amount which is more than the current balance
     * @expect: 1. An amount is not deposited successfully
     * 2. Failure icon is displayed when depositing completely
     */
    @TestRails(id = "729")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_729() throws InterruptedException {
        log("@title:  Validate cannot deposit an amount more than the current balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
        String userCode = memberInfo.getUserCode();
        double currentCashBalance = accountInfo.getAvailableBalance() + 1;
        double depositAmount = currentCashBalance + 0.5;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step  2. Filter with username");
        page.filter(userCode, "", "");

        log("Step 3. Check a checkbox of the account");
        page.action(DepositWithdraw.Actions.CHECK, userCode);

        log("Step 4. Open Deposit popup");
        DepositPopup popup = page.openDepositPopup();

        log("Step 5.Deposit an amount which is more than the current balance");
        popup.deposit(Double.toString(depositAmount), String.format("Deposit TC010 deposit Credit with amount %s while current login balance is %s", depositAmount, currentCashBalance), true, true);
        Thread.sleep(1000);

        log("Verify 1. An amount is NOT deposited successfully");
        log("Verify 2. Failure icon is displayed when depositing completely");
        Assert.assertEquals(page.getMemberInfo(1), listMemberInfo, "FAILED! Members List balance is not match with the expected");
        Assert.assertTrue(page.verifyUpdateStatus(listMemberInfo, false), "FAILED! ERROR check on Update Status column is not display as expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Deposit/Withdraw Link is disabled /enable when select/unselect account
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Select/Unselect all account checkbox
     * @expect: 1. Verify Deposit/withdraw link is disabled/enabled
     */
    @TestRails(id = "730")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_Deposit_730() {
        log("@title:  Validate Deposit/Withdraw Link is disabled /enable when select/unselect account");
        log("Step 1. Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 2. Select/Unselect all account checkbox");
        page.selectAllCheckBoxes();

        log("Verify 1. Verify Deposit/withdraw link is disabled/enabled");
        Assert.assertTrue(page.verifyDepositWithDrawLink(false), String.format("ERROR! Deposit/withdraw link not disable when select all checkbox"));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3624")
    @Test(groups = {"interaction_creditcash"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_DepositWithdrawal_Deposit_3624(String memberAccount, String password) throws Exception {
        log("@title: Verify Balance member site is correct when deposit from agent site");
        log("Step 1. Navigate Agency Management > Deposit Withdrawal and filter an exist player ");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        AccountInfo accountInfo = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount);
        String userCode = accountInfo.getUserCode();
        double playerBalanceAfterDeposit = accountInfo.getCashBalance() - accountInfo.getMyOutstanding() + 0.5;
        double playerOustanding = accountInfo.getMyOutstanding() * (-1);
        double depositAmount = 0.5;

        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        page.filter(userCode, "", "");

        log("Step 2. Select direct member account and click on Deposit link");
        log("Step 3. Deposit an amount for a player" + userCode + " with amount " + depositAmount);
        page.deposit(userCode, String.format("%.2f", depositAmount), String.format("Deposit TC13 deposit for Player " + userCode + " with amount " + depositAmount), true, true);

        log("Verify 1. The balance in member site is deposited");
        loginMember(memberAccount, password);
        AccountBalance playerAccountBalance = memberHomePage.header.getUserBalance();
        Assert.assertEquals(playerAccountBalance.getBalance(), String.format("%,.2f", playerBalanceAfterDeposit), " FAILED! Player available balance is incorrect after agent deposit! Expected is " + String.format("%.2f", playerBalanceAfterDeposit));
        Assert.assertEquals(playerAccountBalance.getExposure(), String.format("%,.2f", playerOustanding), " FAILED! Player exposure is incorrect after agent deposit! Expected is " + String.format("%.2f", playerOustanding));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3625")
    @Test(groups = {"interaction_creditcash"})
    @Parameters({"memberAccount", "password"})
    public void Agent_AM_DepositWithdrawal_Deposit_3625(String memberAccount, String password) throws Exception {
        log("@title: Verify Balance member site is correct after withdraw from agent site");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        AccountInfo accountInfo = DownLineListingUtils.getAccountInfoInList(lstUsers, memberAccount);
        String userCode = accountInfo.getUserCode();
        double playerBalanceAfterWithdraw = accountInfo.getCashBalance() - accountInfo.getMyOutstanding() - 0.5;
        double playerOustanding = accountInfo.getMyOutstanding() * (-1);
        double withdraw = 0.5;

        log("Step 1. Navigate Agency Management > Deposit Withdrawal and filter an exist player ");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        page.filter(userCode, "", "");

        log("Step 2. Select direct member account and click on Deposit link");
        log("Step 3. Deposit an amount for a player" + userCode + " with amount " + withdraw);
        page.withdraw(userCode, String.format("%.2f", withdraw), String.format("Deposit TC14 withdraw for Player " + userCode + " with amount " + withdraw), true, true);

        log("Step 4. Login member site and get balance/exposure");
        loginMember(memberAccount, password);
        AccountBalance playerAccountBalance = memberHomePage.header.getUserBalance();

        log("Verify 1. The balance in member site is withdrawal");
        Assert.assertEquals(playerAccountBalance.getBalance(), String.format("%.2f", playerBalanceAfterWithdraw), " FAILED! Player available balance is incorrect after agent withdraw! Expected is " + String.format("%.2f", playerBalanceAfterWithdraw));
        Assert.assertEquals(playerAccountBalance.getExposure(), String.format("%,.2f", playerOustanding), " FAILED! Player exposure is incorrect after agent withdraw! Expected is " + String.format("%.2f", playerOustanding));

        log("INFO: Executed completely");
    }

    @TestRails(id = "3623")
    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_DepositWithdrawal_3623() {
        //TODO: implement this test case

        log("INFO: Executed completely");
    }

    @TestRails(id = "3626")
    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_DepositWithdrawal_3626() {
        //TODO: implement this test case

        log("INFO: Executed completely");
    }

    @TestRails(id = "3627")
    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_DepositWithdrawal_3627() {
        //TODO: implement this test case

        log("INFO: Executed completely");
    }
}
