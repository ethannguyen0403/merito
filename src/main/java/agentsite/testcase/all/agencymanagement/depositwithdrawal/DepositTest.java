package agentsite.testcase.all.agencymanagement.depositwithdrawal;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import membersite.objects.AccountBalance;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.all.agentmanagement.depositwithdrawal.DepositPopup;
import agentsite.pages.all.agentmanagement.depositwithdrawal.DepositToPopup;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DepositTest extends BaseCaseMerito {
    /**
     * @title: Validate that there is an error message displayed when submitted without any amount
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *          2. Open Deposit popup of an account
     *          3. Deposit without any amount
     * @expect: 1. There is an error message when submitted without any amount
     * "Amount must be positive decimal with maximum two places and greater than zero"
     */
    @Test(groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_003() throws Exception {
        log("@title: Validate that there is an error message displayed when submitted without any amount");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String userCode =lstUsers.get(0).getUserCode();

        log("Step 2. Open Deposit popup of an account");
        DepositToPopup popup = (DepositToPopup)page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);

        log("Step 3. Deposit without any amount");
        popup.deposit("", "");
        String errorMessage = popup.lblAmountError.getText();

        log("Verify 1. There is an error message when submitted without any amount \"Amount must be positive decimal with maximum two places and greater than zero\"");
        Assert.assertEquals(errorMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_AMOUNT, String.format("ERROR: The expected error message is '%s' but found '%s'", errorMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_AMOUNT));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there is an insufficient error displayed when inputted an amount more than the current cash balance
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *          2. Open Deposit popup of an account
     *          3.  Deposit an amount more than the current cash balance
     * @expect: 1. There is an error message when submitted  amount more than the current cash balance
     */
    @Test(groups = {"smoke"})
    @Parameters({"username"})
    public void Agent_AM_DepositWithdrawal_Deposit_004(String username) throws Exception {
        log("@title: Validate that there is an insufficient error displayed when inputted an amount more than the current cash balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String userCode = memberInfo.getUserCode();
        double currentCashBalance = accountInfo.getCashBalance() + 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step 2. Open Deposit popup of an account");
        DepositToPopup popup = (DepositToPopup)page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);

        log("Step 3.  Deposit an amount more than the current cash balance");
        popup.deposit(Double.toString(currentCashBalance), String.format("Deposit amount %.2f",currentCashBalance));
        String errorMessage = popup.lblAmountError.getText();
        String expectedError = String.format(AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_INSUFFICIENT, username);

        log("Verify 1. There is an error message when submitted  amount more than the current cash balance");
        Assert.assertEquals(errorMessage, expectedError, String.format("ERROR: The expected error message is '%s' but found '%s'", expectedError,errorMessage));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can deposited  successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Deposit Withdrawal
     *          2.  Click on  Deposit link of an account
     *          3. Input amount to deposit and input remark  then click Submit
     *          4. Close Deposit popup
     * @expect: 1. Verify message "Deposit is successful"
     *          2. Verify available balance of deposit account is updated
     */
    @Test(groups = {"smoke"})
    @Parameters("currency")
    public void Agent_AM_DepositWithdrawal_Deposit_005(String currency) throws Exception {
        log("@title: Validate can deposited successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        String userCode = memberInfo.getUserCode();
        Double amountDeposit = 1.0;
        double expectedNewMemberCash = memberInfo.getCashBalance() +  amountDeposit;
        double expectedLoginAccountAvBalance = DownLineListingUtils.getMyCreditBalance() -amountDeposit;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log(String.format("Step 1.1 Get data of user code before deposit",userCode));
        List<String> userInfoBEfore = page.getRowContainsUsercode(userCode);
        String expectedAmountAfterDeposit=  String.format(Locale.getDefault(), "%,.2f", Double.valueOf(userInfoBEfore.get(page.colAvailableBalance-1).replaceAll(",", "").toString())+amountDeposit);

        log("Step 2. Click on  Deposit link of an account");
        DepositToPopup popup = (DepositToPopup)page.action(DepositWithdrawalPage.Actions.DEPOSIT,userCode);

        log("Step 3. Input amount to deposit   and input remark  then click Submit");
        popup.deposit(amountDeposit.toString(), "TC005 Deposit 1");
        String successMessage = popup.lblAmountSuccess.getText();
        double newMemberCash = popup.getNewMemberCashBalance();
        double newMemberCashAfter = popup.getMemberCashBalance();
        double newYourCash = popup.getNewYourCashBalance();
        double newYourCashAfter = popup.getYourCashBalance();

        log("Verify 1. Verify message \"Deposit is successful\"");
        Assert.assertEquals(successMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL,
                String.format("ERROR: The expected success message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL,successMessage));

        log("Verify 2. Verify available balance of deposit account is updated");
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedLoginAccountAvBalance, newYourCash, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedLoginAccountAvBalance, newYourCash));
        Assert.assertEquals(expectedLoginAccountAvBalance, newYourCashAfter, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedLoginAccountAvBalance, newYourCashAfter));
        Assert.assertEquals(expectedNewMemberCash, newMemberCashAfter, String.format("ERROR: The expected new cash balance of member is '%s' but found '%s'", expectedNewMemberCash, newMemberCashAfter));

        log("Step 4. Close Deposit popup");
        popup.clickCancelBtn();

        log(String.format("Step  Get data of user code after deposit",userCode));
        List<String> userInfoAfter = page.getRowContainsUsercode(userCode);

        log("Verify 3. Verify available balance of deposit account is updated");
        Assert.assertEquals(userInfoAfter.get(page.colAvailableBalance-1),expectedAmountAfterDeposit,
                String.format("FAIED! Availabale Balance of account %s expected is %s but found %s",userCode,expectedAmountAfterDeposit,userInfoAfter.get(page.colAvailableBalance-1)));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can multiple deposit  by click on Deposit button
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Check on more than 1 account and click on Deposit button
     *         3. Input amount to deposit enter remark and click on Submit button
     * @expect: 1. Deposit popup is disappeared and there is a green check display in Update Status column
     *          2. Verify Balance is updated correctly
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_006() throws Exception {
        log("@title: Validate can multiple deposit by click on Deposit button");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step 1.1 Select Active account status and 5 item per page");
        page.filter("","Active","");

        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        int n = lstUsers.size() > 2 ? 2: lstUsers.size();
        Double loginAccBalance= DownLineListingUtils.getMyCreditBalance();
        double depositAmount = 1;

        log("Step 1.2 Get main balance and list member info");
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(n);

        log("Step  2. Check on more than 1 account and click on Deposit button");
        page.selectUser(listMemberInfo);
        DepositPopup popup = page.openDepositPopup();

        log("Step 3. Input amount to deposit, select Credit Update enter remark and click on Submit button");
        popup.deposit(Double.toString(depositAmount),"Deposit TC006 deposit all");

        log("Verify 1. Deposit popup is disappeared and there is a green check display in Update Status column");
        List<ArrayList<String>> listMemberInfoAfter = page.getMemberInfo(n);


        log("Verify 2. Verify Balance is updated correctly");
        Assert.assertTrue(page.verifyBalanceUpdated(depositAmount,loginAccBalance,listMemberInfo,listMemberInfoAfter,true, DepositWithdrawalPage.Actions.SUCCESS_ICON),"FAILED! Balance value or status not correct after deposit");
        log("INFO: Executed completely");
    }

    /**
            * @title: Validate that Deposit popup is displayed when clicking Deposit button
     * @pre-condition:
            *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Filter with username
     *           3. Check a checkbox of the account
     *           4. Open Deposit popup
     * @expect:  1. Deposit popup is displayed and UI display correctly
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_007() throws Exception {
        log("@title: Validate that Deposit popup is displayed when clicking Deposit button");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String userCode = memberInfo.getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step 2: Filter with username " + userCode);
        page.filter(userCode, "", "");

        log("Step 3: Check a checkbox of the account " + userCode);
        page.action(DepositWithdrawalPage.Actions.CHECK, userCode);

        log("Step 4: Open Deposit popup");
        DepositPopup popup = page.openDepositPopup();

        log("Verify 1. Deposit popup is displayed and UI display correctly");
        Assert.assertTrue(popup.popupDepositWithDraw.isDisplayed(), "ERROR: popupDeposit is not displayed");
        String popupTitle = popup.lblTitle.getText();
        Assert.assertEquals(popupTitle, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT, String.format("ERROR: The expected popup title is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT, popupTitle));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate cannot deposit an amount more than the current balance
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Deposit Withdrawal
     *          2. Filter with username
     *          3. Check a checkbox of the account
     *          4. Open Deposit popup
     *          5.Deposit an amount which is more than the current balance
     * @expect: 1. An amount is not deposited successfully
     *          2. Failure icon is displayed when depositing completely
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_010() throws Exception {
        log("@title:  Validate cannot deposit an amount more than the current balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String userCode = memberInfo.getUserCode();
        double currentCashBalance = accountInfo.getCashBalance() + 1;
        double depositAmount =currentCashBalance + 0.5;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step  2. Filter with username" );
        page.filter(userCode, "", "");

        log("Step 3. Check a checkbox of the account"  );
        page.action(DepositWithdrawalPage.Actions.CHECK, userCode);

        log("Step 4. Open Deposit popup"  );
        DepositPopup popup = page.openDepositPopup();

        log("Step 5.Deposit an amount which is more than the current balance"  );
        popup.deposit(Double.toString(depositAmount),String.format("Deposit TC010 deposit Credit with amount %s while current login balance is %s",depositAmount,currentCashBalance));
        page.waitingLoadingSpinner();

        log("Verify 1. An amount is NOT deposited successfully");
        log("Verify 2. Failure icon is displayed when depositing completely");
        Assert.assertEquals(page.getMemberInfo(1),listMemberInfo,"FAILED! Members List balance is not match with the expected");
        Assert.assertTrue(page.verifyUpdateStatus(listMemberInfo,false),"FAILED! ERROR check on Update Status column is not display as expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Deposit/Withdraw Link is disabled /enable when select/unselect account
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Select/Unselect all account checkbox
     * @expect: 1. Verify Deposit/withdraw link is disabled/enabled
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_011() throws Exception {
        log("@title:  Validate Deposit/Withdraw Link is disabled /enable when select/unselect account");
        log("Step 1. Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());

        log("Step 2. Select/Unselect all account checkbox");
        page.selectAllCheckBoxes();

        log("Verify 1. Verify Deposit/withdraw link is disabled/enabled");
        Assert.assertTrue(page.verifyDepositWithDrawLink(false),String.format("ERROR! Deposit/withdraw link not disable when select all checkbox"));

        log("INFO: Executed completely");
    }

    @Test (groups = {"interaction"})
    @Parameters({"brandname","memberAccount","password"})
    public void Agent_AM_DepositWithdrawal_Deposit_013(String brandname, String memberAccount, String password) throws Exception {
        log("@title: Verify Balance member site is correct when deposit from agent site");
        log("Step 1. Navigate Agency Management > Deposit Withdrawal and filter an exist player ");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        AccountInfo accountInfo = DownLineListingUtils.getAccountInfoInList(lstUsers,memberAccount);
        String userCode = accountInfo.getUserCode();
        double playerBalanceAfterDeposit = accountInfo.getCashBalance() - accountInfo.getMyOutstanding() + 0.5;
        double playerOustanding = accountInfo.getMyOutstanding() *(-1);
        double depositAmount = 0.5;

        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());
        page.filter(userCode, "", "");

        log("Step 2. Select direct member account and click on Deposit link");
        log("Step 3. Deposit an amount for a player"+ userCode +" with amount "+depositAmount);
        page.deposit(userCode, String.format("%.2f",depositAmount), String.format("Deposit TC13 deposit for Player "+ userCode +" with amount "+depositAmount),true,true);

        log("Verify 1. The balance in member site is deposited");
        loginMember(memberAccount,password);
        AccountBalance playerAccountBalance = memberHomePage.getPlayerBalance(brandname);
        Assert.assertEquals(playerAccountBalance.getBalance(),String.format("%,.2f",playerBalanceAfterDeposit)," FAILED! Player available balance is incorrect after agent deposit! Expected is "+ String.format("%.2f",playerBalanceAfterDeposit));
        Assert.assertEquals(playerAccountBalance.getExposure(),String.format("%,.2f",playerOustanding)," FAILED! Player exposure is incorrect after agent deposit! Expected is "+ String.format("%.2f",playerOustanding));

       log("INFO: Executed completely");
    }

    @Test (groups = {"interaction"})
    @Parameters({"brandname","memberAccount","password"})
    public void Agent_AM_DepositWithdrawal_Deposit_014(String brandname, String memberAccount, String password) throws Exception {
        log("@title: Verify Balance member site is correct after withdraw from agent site");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        AccountInfo accountInfo = DownLineListingUtils.getAccountInfoInList(lstUsers,memberAccount);
        String userCode = accountInfo.getUserCode();
        double playerBalanceAfterWithdraw =accountInfo.getCashBalance() -  accountInfo.getMyOutstanding()  - 0.5;
        double playerOustanding = accountInfo.getMyOutstanding() * (-1);
        double withdraw = 0.5;

        log("Step 1. Navigate Agency Management > Deposit Withdrawal and filter an exist player ");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawal(environment.getSecurityCode());
        page.filter(userCode, "", "");

        log("Step 2. Select direct member account and click on Deposit link");
        log("Step 3. Deposit an amount for a player"+ userCode +" with amount "+withdraw);
        page.withdraw(userCode, String.format("%.2f",withdraw), String.format("Deposit TC14 withdraw for Player "+ userCode +" with amount "+withdraw),true,true);

        log("Step 4. Login member site and get balance/exposure");
        loginMember(memberAccount,password);
        AccountBalance playerAccountBalance = memberHomePage.getPlayerBalance(brandname);

        log("Verify 1. The balance in member site is withdrawal");
        Assert.assertEquals(playerAccountBalance.getBalance(),String.format("%.2f",playerBalanceAfterWithdraw)," FAILED! Player available balance is incorrect after agent withdraw! Expected is "+ String.format("%.2f",playerBalanceAfterWithdraw));
        Assert.assertEquals(playerAccountBalance.getExposure(),String.format("%,.2f",playerOustanding)," FAILED! Player exposure is incorrect after agent withdraw! Expected is "+ String.format("%.2f",playerOustanding));

        log("INFO: Executed completely");
    }
}
