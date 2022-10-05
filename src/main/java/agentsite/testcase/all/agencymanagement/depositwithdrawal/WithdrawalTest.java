package agentsite.testcase.all.agencymanagement.depositwithdrawal;

import com.paltech.utils.DoubleUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.all.agentmanagement.depositwithdrawal.WithdrawalPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.List;

public class WithdrawalTest extends BaseCaseMerito {
    /**
     * @title: Validate that Withdrawal popup displays correct info.
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Open Withdrawal popup of an account
     * @expect:  1. Withdrawal popup is displayed
     *           2. Info is displayed correctly
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_001() throws Exception {
        log("@title: Validate that Withdrawal popup displays correct info.");
     //   AccountInfo acc = ProfileUtils.getProfile();
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Double loginAccBalance= DownLineListingUtils.getMyCreditBalance();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String fullUserAlias =  lstUsers.get(0).getUserCodeAndLoginID();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step 2: Open Withdrawal popup of an account " + userCode);
        WithdrawalPopup popup = (WithdrawalPopup)page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);
        String popupTitle = popup.lblTitle.getText();
        String expectedTitle = String.format(AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_TITLE, fullUserAlias);
        String memberBalance = popup.lblMemberBalance.getText().replace(",", "");
        String yourBalance = popup.lblYourBalance.getText().replace(",", "");

        log("Verify 1: Withdrawal popup is displayed");
        Assert.assertTrue(popup.popupWithdrawal.isDisplayed(), "ERROR: popupWithdrawal is not displayed");
        Assert.assertTrue(popup.iconClose.isDisplayed(),"FAILED! Close popup icon not display");
        Assert.assertEquals(popupTitle,expectedTitle, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedTitle, popupTitle));

        log("Verify 2: Info is displayed correctly");
        Assert.assertEquals(Double.parseDouble(yourBalance), accountInfo.getCashBalance(), String.format("ERROR: The expected current balance is '%s' but found '%s'", accountInfo.getCashBalance(), yourBalance));
        Assert.assertEquals(Double.parseDouble(memberBalance), lstUsers.get(0).getCashBalance(), String.format("ERROR: The expected member's current balance is '%s' but found '%s'", lstUsers.get(0).getCashBalance(), memberBalance));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Withdrawal popup is closed when clicking Cancel button
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Open Withdrawal popup of an account
     * @expect:  1. Withdrawal popup is closed
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_002() throws Exception {
        log("@title: Validate that there is an error message displayed when submitted without inputting");
        String userID = ProfileUtils.getProfile().getUserID();
        List<String> lstUsers = DownLineListingUtils.getDownLineUsers(userID);
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step 2: Open Withdrawal popup of an account " + userCode);
        WithdrawalPopup popup = (WithdrawalPopup)page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log("Step 3: Click Cancel button");
        popup.clickCancelBtn();

        log("Verify 1: Withdrawal popup is closed");
        Assert.assertTrue(popup.popupWithdrawal.isInvisible(2), "ERROR: popupWithdrawal is still displayed");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there is an error message displayed when submitted without any amount
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Open Withdrawal popup of an account
     *           3. Withdraw without any amount
     * @expect:  1. There is an error message when submitted without any amount
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_003() throws Exception {
        log("@title: Validate that there is an error message displayed when submitted without any amount");
        String userID = ProfileUtils.getProfile().getUserID();
        List<String> lstUsers = DownLineListingUtils.getDownLineUsers(userID);
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal and put security code");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step 2: Open Withdrawal popup of an account " + userCode);
        WithdrawalPopup popup = (WithdrawalPopup)page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log("Step 3: Withdrawal without any amount");
        popup.withdraw("", "");

        popup.lblAmountError.isTextDisplayed(AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_AMOUNT,1);
        String errorMessage = popup.lblAmountError.getText();

        log("Verify 1: There is an error message when submitted without any amount");
        Assert.assertEquals(errorMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_AMOUNT, String.format("ERROR: The expected error message is '%s' but found '%s'", errorMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_AMOUNT));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there is an insufficient error displayed when inputted an amount more than the current cash balance
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Open Withdrawal popup of an account
     *         3. Withdraw an amount more than the current cash balance
     * @expect: 1. There is an error message when submitted without any amount
     */
    @Test(groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_004() throws Exception {
        log("@title: Validate that there is an insufficient error displayed when inputted an amount more than the current cash balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        String userCode = memberInfo.getUserCode();
        double withDrawAmount = memberInfo.getCashBalance() + 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step  2. Open Withdrawal popup of an account");
        WithdrawalPopup popup = (WithdrawalPopup) page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log("Step 3. Withdraw an amount more than the current cash balance");
        popup.withdraw(Double.toString(withDrawAmount), "WithdrawTC004 withdraw amount more than member cash balance");

        String errorMessage = popup.lblAmountError.getText();
        String expectedError = String.format(AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_INSUFFICIENT,memberInfo.getUserCodeAndLoginID());

        log("Verify 1. There is an error message when submitted without any amount");
        Assert.assertEquals(errorMessage, expectedError, String.format("ERROR: The expected error message is '%s' but found '%s'", expectedError,errorMessage));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that an amount is withdrawn successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Open Withdrawal popup of an account
     *         3. Withdraw an amount
     * @expect: 1. An amount is withdrawn successfully
     */
    @Test(groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_005() throws Exception {
        log("@title:Validate that an amount is withdrawn successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String userCode = memberInfo.getUserCode();
        double withDrawAmount = 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step  2. Open Withdrawal popup of an account");
        WithdrawalPopup popup = (WithdrawalPopup) page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log(String.format("Step 3. Withdraw an amount account %s",userCode));
        popup.withdraw(Double.toString(withDrawAmount), "TC005 Withdraw with amount = 1");
        String successfulMessage = popup.lblAmountSuccess.getText();
        double expectedNewMemberCash = memberInfo.getCashBalance() -  1;
        double expectedNewYourCash = accountInfo.getCashBalance() +  1;
        double newMemberCash = popup.getNewMemberCashBalance();
        double newMemberCashAfter = popup.getMemberCashBalance();
        double newYourCash = popup.getNewYourCashBalance();
        double newYourCashAfter = popup.getYourCashBalance();

        log("Verify 1: An amount is withdrawn successfully");
        Assert.assertEquals(successfulMessage, AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_SUCCESSFUL, String.format("ERROR: The expected error message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_SUCCESSFUL, successfulMessage));
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedNewYourCash, newYourCash, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCash));
        Assert.assertEquals(expectedNewYourCash, newYourCashAfter, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCashAfter));
        Assert.assertEquals(expectedNewMemberCash, newMemberCashAfter, String.format("ERROR: The expected new cash balance of member is '%s' but found '%s'", expectedNewMemberCash, newMemberCashAfter));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can withdraw Credit update by click on Withdraw link
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *      2. Open Withdrawal popup of an account
     *      3. Withdraw an amount from this username
     *      4. Close Withdrawal popup
     * @expect: 1. Message Withdraw successfully is displayed
     *          2. An amount is updated correctly on this table
     */
    @Test(groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_006() throws Exception {
        log("@title:Validate can withdraw by click on Withdraw link");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        double withDrawAmount = 1;
        Double loginAccBalance= DoubleUtils.roundUpWithTwoPlaces(DownLineListingUtils.getMyCreditBalance());

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step  2. Open Withdrawal popup of an account");
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);
        WithdrawalPopup popup = (WithdrawalPopup) page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, listMemberInfo.get(0).get(page.defineDepositWithdrawTableColumn("User Name")-1));

        log("Step 3. Withdraw an amount from this username");
        popup.withdraw(Double.toString(withDrawAmount), "WithdrawTC006 withdraw amount ");
        String successfulMessage = popup.lblAmountSuccess.getText();

        log("Step 4. Close Withdrawal popup");
        popup.clickCancelBtn();
        double expectedNewMemberCash =Double.valueOf(listMemberInfo.get(0).get(page.colAvailableBalance - 1).replaceAll(",", "").toString())- withDrawAmount;
        double expectedNewYourCash = loginAccBalance +  withDrawAmount;
        double newMemberCash = popup.getNewMemberCashBalance();
        double newMemberCashAfter = popup.getMemberCashBalance();
        double newYourCash = popup.getNewYourCashBalance();
        double newYourCashAfter = popup.getYourCashBalance();

        log("Verify 1. Message Withdraw successfully is displayed");
        Assert.assertEquals(successfulMessage, AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_SUCCESSFUL, String.format("ERROR: The expected error message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_SUCCESSFUL, successfulMessage));

        log("Verify 2. Verify Balance on Withdraw popup display corect");
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedNewYourCash, newYourCash, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCash));
        Assert.assertEquals(expectedNewYourCash, newYourCashAfter, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCashAfter));
        Assert.assertEquals(expectedNewMemberCash, newMemberCashAfter, String.format("ERROR: The expected new cash balance of member is '%s' but found '%s'", expectedNewMemberCash, newMemberCashAfter));

        log("Verify 3. Main and dowline balance updated correctly");
        List<ArrayList<String>> listMemberInfoAfter = page.getMemberInfo(1);
        Assert.assertTrue(page.verifyBalanceUpdated(withDrawAmount,loginAccBalance,listMemberInfo,listMemberInfoAfter,false, DepositWithdrawalPage.Actions.SUCCESS_ICON),"FAILED! Balance value or status not correct after deposit");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that Withdrawal popup is displayed when clicking Withdraw button
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Filter with username
     *         3. Check a checkbox of the account
     *         4. Click on Withdraw button
     * @expect:  1. Withdraw popup is displayed
     */
    @Test(groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_007() throws Exception {
        log("@title: Validate that Withdrawal popup is displayed when clicking Withdraw button");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step 2. Filter with username");
        page.filter(userCode, "", "");

        log("Step 3. Check a checkbox of the account");
        page.action(DepositWithdrawalPage.Actions.CHECK, userCode);

        log("Step 4. Click on Withdraw button");
        WithdrawalPopup popup = page.openWithdrawalPopup();

        log("Verify 1. Withdraw popup is displayed");
        Assert.assertTrue(popup.popupDepositWithDraw.isDisplayed(), "ERROR: popupDeposit is not displayed");
        String popupTitle = popup.lblTitle.getText();
        Assert.assertEquals(popupTitle, AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL, String.format("ERROR: The expected popup title is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL, popupTitle));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can withdrawn Credit Update successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Filter with username
     *         3. Check a checkbox of the account
     *         4. Open Withdraw popup
     *         5. Select Credit Update and  Withdraw 1 HKD
     * @expect: 1. An amount is withdrawn successfully
     *          2. Success icon is displayed when withdrawing completely
     */
    @Test(groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_008() throws Exception {
        log("@title: Validate can withdrawn successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        double amountWithdraw = 1;
        Double loginAccBalance= DoubleUtils.roundUpWithTwoPlaces(DownLineListingUtils.getMyCreditBalance());

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step 2. Filter with username");
        page.filter(userCode, "", "");

        log("Step 3. Check a checkbox of the account");
        page.action(DepositWithdrawalPage.Actions.CHECK, userCode);

        log("Step 4. Open Withdraw popup");
        WithdrawalPopup popup = page.openWithdrawalPopup();

        log("Step 5.Select Credit Update and Withdraw 1 HKD");
        popup.withdraw(Double.toString(amountWithdraw),String.format("Withdraw TC008 withdraw with amount %.2f from account %s",amountWithdraw,userCode));
        page.waitingLoadingSpinner();

        log("Verify  1. An amount is withdrawn successfully");
        log("Verify  2. Success icon is displayed when withdrawing completely");
        List<ArrayList<String>> listMemberInfoAfter= page.getMemberInfo(1);
        Assert.assertTrue(page.verifyUpdateStatus(listMemberInfo,true),"FAILED! Green check on Update Status column is not display as expected");
        Assert.assertTrue(page.verifyBalanceUpdated(amountWithdraw,loginAccBalance,listMemberInfo,listMemberInfoAfter,false, DepositWithdrawalPage.Actions.SUCCESS_ICON),"FAILED! Balance value or status not correct after deposit");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that cannot withdraw with amount more than current balance
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Filter with username
     *         3. Check a checkbox of the account
     *         4. Open Withdraw popup
     *         5. Withdraw an amount which is more than the current balance
     * @expect: 1. An amount is withdrawn unsuccessfully
     *          2. Failure icon is displayed when withdrawing completely
     */
    @Test(groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_009() throws Exception {
        log("@title: Validate that cannot withdraw with  amount more than current balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        double amountWithdraw =  lstUsers.get(0).getCashBalance() + 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step 2. Filter with username");
        page.filter(userCode, "", "");

        log("Step 3. Check a checkbox of the account");
        page.action(DepositWithdrawalPage.Actions.CHECK, userCode);

        log("Step 4. Open Withdraw popup");
        WithdrawalPopup popup = page.openWithdrawalPopup();

        log("Step 5. Withdraw an amount which is more than the current balance");
        popup.withdraw(Double.toString(amountWithdraw),"Withdraw TC009 withdraw credit update amount more than current balance");
        page.waitingLoadingSpinner();

        log("Verify 2. Failure icon is displayed when withdrawing completely");
        Assert.assertTrue(page.verifyUpdateStatus(listMemberInfo,false),"FAILED! Cross icon Update Status column is not display as expected");

        log("INFO: Executed completely");
    }


}
