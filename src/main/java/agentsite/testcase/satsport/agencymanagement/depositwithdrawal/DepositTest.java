package agentsite.testcase.satsport.agencymanagement.depositwithdrawal;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.all.agentmanagement.depositwithdrawal.DepositPopup;
import agentsite.pages.all.agentmanagement.depositwithdrawal.DepositToPopup;
import agentsite.pages.all.agentmanagement.depositwithdrawal.WithdrawalPopup;
import agentsite.pages.sat.agentmanagement.SATDepositWithdrawalPage;
import agentsite.pages.sat.agentmanagement.SATDownLineListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_GREATER_MAX_PLAYER_CREDIT;
import static agentsite.common.AGConstant.HomePage.*;

public class DepositTest extends BaseCaseMerito {
    /**
     * @title: Validate can deposited by Credit Update successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Deposit Withdrawal
     *          2.  Click on  Deposit link of an account
     *          3. Input amount to deposit , select Credit Update option, and input remark  then click Submit
     *          4. Close Deposit popup
     * @expect: 1. Verify message "Withdraw is successful"
     *          2. Verify available balance of deposit account is updated
     */
    @Test(priority = 2,groups = {"demo_smoke"})
    @Parameters({"memberAccount","password"})
    public void Agent_AM_DepositWithdrawal_Deposit_005_demo(String memberAccount,String password) throws Exception {
        log("@title: Validate can deposited by Credit Update successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
        String userCode = memberInfo.getUserCode();
        double cash = accountInfo.getCashBalance();
        double expectedNewMemberCash = memberInfo.getCashBalance() + 1;
        double expectedNewYourCash = accountInfo.getCashBalance() - 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2. Click on  Deposit link of an account " + userCode);
        log("Step 3. Input amount to deposit , select Credit Update option, and input remark  then click Submit");
        DepositToPopup popup = (DepositToPopup) page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);
        popup.deposit("1", "TC005 Deposit 1",true,true);
        String successMessage = popup.getMessage();
        double newMainBalance = popup.getNewYourCashBalance();
        double newMemberCash = popup.getNewMemberCashBalance();
        popup.clickCancelBtn();

        log("Verify 1. Verify message \"Withdraw is successful\"");
        Assert.assertEquals(successMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL, String.format("ERROR: The expected success message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL, successMessage));

        log("Verify 2. Verify available balance of deposit account is updated");
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedNewYourCash, newMainBalance, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newMainBalance));

        log("Step5: Get available balance of login account and deposit account");
        String loginAccountAvailabaleBalance = page.tblAccountBalance.getControlOfCell(1, page.colMainAvailableBalance, 1, null).getText().trim();
        String depositAccountAvailableBalance = String.format("%.2f",page.getDataByColumn(userCode,page.colAvailableBalance));

        log("Verify 3. Verify available balance of main account and deposit account is updated");
        Assert.assertEquals( loginAccountAvailabaleBalance,String.format("%.2f",expectedNewYourCash), String.format("ERROR: The expected available balance of deposit account is '%s' but found '%s'", expectedNewYourCash, loginAccountAvailabaleBalance));
        Assert.assertEquals(depositAccountAvailableBalance,String.format("%.2f",expectedNewMemberCash), String.format("ERROR: The expected available balance of main is '%s' but found '%s'", expectedNewYourCash, depositAccountAvailableBalance));

       /* page.logout();
        Helper.loginFairExchange(environment.getMemberSOSUrl(),environment.getMemberDashboardUrl(),memberAccount,password,true);
        HomePage memberHomePage = new HomePage();
        memberagentHomePage.

*/

        log("INFO: Executed completely");
    }
    /**
     * @title: Validate that Deposit popup displays correct info.
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Open Deposit popup of an account
     * @expect:  1. Deposit popup is displayed
     *           2. Info is displayed correctly
     */
    @Test (groups = {"regression"})
    public void Agent_AM_DepositWithdrawal_Deposit_001(){
        log("@title: Validate that Deposit popup displays correct info.");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2: Open Deposit popup of an account " + userCode);
        DepositPopup popup = (WithdrawalPopup) page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);

        String popupTitle = popup.lblTitle.getText();
        String expectedTitle = String.format(AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_TITLE, userCode,lstUsers.get(0).getLoginID());
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
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *  2. Open Deposit popup of an account
     * 3. Click Cancel button
     * @expect:  1.  Deposit popup is closed
     */
    @Test(groups = {"regression"})
    public void Agent_AM_DepositWithdrawal_Deposit_002(){
        log("@title: Validate that Deposit popup is closed when clicking Cancel button");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String userCode =lstUsers.get(0).getUserCode();

        log("Step 2. Open Deposit popup of an account " +userCode);
        DepositToPopup popup = (DepositToPopup)page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);

        log("Step 3. Click Cancel button");
        popup.clickCancelBtn();

        log("Verify 1.Deposit popup is closed");
        Assert.assertFalse(popup.lblTitle.isDisplayed(),"FAILED! Deposit popup not close after click Cancel button");

        log("INFO: Executed completely");
    }
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
    public void Agent_AM_DepositWithdrawal_Deposit_003() {
        log("@title: Validate that there is an error message displayed when submitted without any amount");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }

        log("Step 2. Open Deposit popup of an account "+ userCode);
        DepositToPopup popup = (DepositToPopup) page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);

        log("Step 3. Deposit without any amount");
        popup.deposit("0", "",true);
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
    @Test(groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_004() {
        log("@title: Validate that there is an insufficient error displayed when inputted an amount more than the current cash balance");
        // String loginUserCode = ProfileUtils.getProfile().getUserCode();
        String loginUserCode = ProfileUtils.getProfile().getUserCodeAndLoginID("%s (%s)");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
        String userCode = memberInfo.getUserCode();
        double currentCashBalance = accountInfo.getCashBalance() + 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2. Open Deposit popup of an account");
        DepositToPopup popup = (DepositToPopup)page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);

        log("Step 3.  Deposit an amount more than the current cash balance");
        popup.deposit(Double.toString(currentCashBalance), String.format("Deposit amount %.2f",currentCashBalance),true);
        String errorMessage = popup.lblAmountError.getText();
        String expectedError = String.format(AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_INSUFFICIENT, loginUserCode);

        log("Verify 1. There is an error message when submitted  amount more than the current cash balance");
        Assert.assertEquals(errorMessage, expectedError, String.format("ERROR: The expected error message is '%s' but found '%s'", expectedError,errorMessage));

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can deposited by Credit Update successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Deposit Withdrawal
     *          2.  Click on  Deposit link of an account
     *          3. Input amount to deposit , select Credit Update option, and input remark  then click Submit
     *          4. Close Deposit popup
     * @expect: 1. Verify message "Withdraw is successful"
     *          2. Verify available balance of deposit account is updated
     */
    @Test(priority = 2,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_005(){
        log("@title: Validate can deposited by Credit Update successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size() - 1);
        String userCode = memberInfo.getUserCode();
        double expectedNewMemberCash = memberInfo.getCashBalance() + 1;
        double expectedNewYourCash = accountInfo.getCashBalance() - 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2. Click on  Deposit link of an account");
        log("Step 3. Input amount to deposit , select Credit Update option, and input remark  then click Submit");
        DepositToPopup popup = (DepositToPopup) page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);
        popup.deposit("1", "TC005 Deposit 1",true,true);
        String successMessage = popup.getMessage();
        double newMainBalance = popup.getNewYourCashBalance();
        double newMemberCash = popup.getNewMemberCashBalance();
        popup.clickCancelBtn();

        log("Verify 1. Verify message \"Withdraw is successful\"");
        Assert.assertEquals(successMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL, String.format("ERROR: The expected success message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL, successMessage));

        log("Verify 2. Verify available balance of deposit account is updated");
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedNewYourCash, newMainBalance, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newMainBalance));

        log("Step5: Get available balance of login account and deposit account");
        String loginAccountAvailabaleBalance = page.tblAccountBalance.getControlOfCell(1, page.colMainAvailableBalance, 1, null).getText().trim();
        String depositAccountAvailableBalance = String.format("%.2f",page.getDataByColumn(userCode,page.colAvailableBalance));

        log("Verify 3. Verify available balance of main account and deposit account is updated");
        Assert.assertEquals( loginAccountAvailabaleBalance,String.format("%.2f",expectedNewYourCash), String.format("ERROR: The expected available balance of deposit account is '%s' but found '%s'", expectedNewYourCash, loginAccountAvailabaleBalance));
        Assert.assertEquals(depositAccountAvailableBalance,String.format("%.2f",expectedNewMemberCash), String.format("ERROR: The expected available balance of main is '%s' but found '%s'", expectedNewYourCash, depositAccountAvailableBalance));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can multiple deposit by Credit Update by click on Deposit button
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Check on more than 1 account and click on Deposit button
     *         3. Input amount to deposit, select Credit Update enter remark and click on Submit button
     * @expect: 1. Deposit popup is disappeared and there is a green check display in Update Status column
     *          2. Verify Balance is updated correctly
     */
    @Test (priority = 2,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_006(){
        log("@title: Validate can multiple deposit by Credit Update by click on Deposit button");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 1.1 Select Active account status and 5 item per page");
        page.filter("","Active","");

        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        List<AccountInfo> lstFiveUser = new ArrayList<>();
        int n = lstUsers.size() > 2 ? 2: lstFiveUser.size();
        for(int i=0; i<n;i++){
            lstFiveUser.add(i,lstUsers.get(i));
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

        double creditInitationAcc1 = page.getDataByColumn(username1,page.colCreditInitiation);
        double totalBalanceAcc1 = page.getDataByColumn(username1,page.colTotalBalance);
        double availableBalanceAcc1 = page.getDataByColumn(username1,page.colAvailableBalance);
        double creditInitationAcc2 = page.getDataByColumn(username2,page.colCreditInitiation);
        double totalBalanceAcc2 = page.getDataByColumn(username2,page.colTotalBalance);
        double availableBalanceAcc2 = page.getDataByColumn(username2,page.colAvailableBalance);
        DepositPopup popup = page.openDepositPopup();

        log("Step 3. Input amount to deposit, select Credit Update enter remark and click on Submit button");
        popup.deposit(Double.toString(depositAmount),"Deposit TC006 deposit for account" +username1+" and acocunt "+username2,true,true);

        log("Step 4. Get info after deposit");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, totalDepositAmount,true);
        double actualCreditInitationAcc1 = page.getDataByColumn(username1,page.colCreditInitiation);
        double actualTotalBalanceAcc1 = page.getDataByColumn(username1,page.colTotalBalance);
        double actualAvailableBalanceAcc1 = page.getDataByColumn(username1,page.colAvailableBalance);
        double actualCreditInitationAcc2 = page.getDataByColumn(username2,page.colCreditInitiation);
        double actualTotalBalanceAcc2 = page.getDataByColumn(username2,page.colTotalBalance);
        double actualAvailableBalanceAcc2 = page.getDataByColumn(username2,page.colAvailableBalance);

        log("Verify 1. Deposit popup is disappeared and there is a green check display in Update Status column");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(0).getUserCode()),"FAILED! Green check on Update Status column is not display as expected");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(1).getUserCode()),"FAILED! Green check on Update Status column is not display as expected");

        log("Verify 2. Verify Balance of 2 accounts is updated correctly: Credi Initiation, Total Balance, Available Balance");
        Assert.assertEquals(page.getLoginAccountBalanceInfo(),mainBalanceInfoExpected,"FAILED! Main account balance info is not match with the expected");
        Assert.assertEquals(actualCreditInitationAcc1,creditInitationAcc1 +1,String.format("FAILED! Credit Initiation account %s is incorrect after deposit",username1));
        Assert.assertEquals(actualTotalBalanceAcc1,totalBalanceAcc1 +1,String.format("FAILED! Total balance account %s is incorrect after deposit",username1));
        Assert.assertEquals(actualAvailableBalanceAcc1,availableBalanceAcc1 +1,String.format("FAILED! Available Balance account %s is incorrect after deposit",username1));
        Assert.assertEquals(actualCreditInitationAcc2,creditInitationAcc2 +1,String.format("FAILED! Credit Initiation account %s is incorrect after deposit",username2));
        Assert.assertEquals(actualTotalBalanceAcc2,totalBalanceAcc2 +1,String.format("FAILED! Total balance account %s is incorrect after deposit",username2));
        Assert.assertEquals(actualAvailableBalanceAcc2,availableBalanceAcc2 +1,String.format("FAILED! Available Balance account %s is incorrect after deposit",username2));
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
    public void Agent_AM_DepositWithdrawal_Deposit_007(){
        log("@title: Validate that Deposit popup is displayed when clicking Deposit button");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        String userCode = memberInfo.getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

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
     * @title: Validate can deposited by Win/Loss Settle successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps:    1. Navigate Agency Management > Deposit Withdrawal
     *           2.  Click icon on Deposit link of an account
     *           3. Input amount to deposit , select Win/Loss Settle option, and input remark  then click Submit
     *           4. Close Deposit popup
     * @expect:     1. Verify message "Withdraw is successful"
     *              2. Verify  Win/loss value is update correctly
     */
    @Test (priority = 2,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_008(){
        log("@title: Validate can deposited by Win/Loss Settle successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String userCode = memberInfo.getUserCode();
        double depositAmount = 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        List<ArrayList<String>> mainBalanceInfo = page.getLoginAccountBalanceInfo();
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step 2.  Click icon on Deposit link of an account " );
        DepositToPopup popup = (DepositToPopup)page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);

        log("Step 3: Input amount to deposit , select Win/Loss Settle option, and input remark  then click Submit"  );
        popup.deposit("1", "TC008 Deposit Win/Loss Settle 1",false,true);
        String successMessage = popup.getMessage();
        double expectedNewMemberCash = memberInfo.getCashBalance() +  1;
        double expectedNewYourCash = accountInfo.getCashBalance() -  1;
        double newMemberCash = popup.getNewMemberCashBalance();
        double newMemberCashAfter = popup.getMemberCashBalance();
        double newYourCash = popup.getNewYourCashBalance();
        double newYourCashAfter = popup.getYourCashBalance();

        log("Step 4. Close Deposit popup");
        popup.clickCancelBtn();

        log("Verify  1. Verify message \"Withdraw is successful\"");
        Assert.assertEquals(successMessage, AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL, String.format("ERROR: The expected success message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_SUCCESSFUL,successMessage));

        log("Verify  2. Verify  Win/loss value is update correctly as value in the popup");
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedNewYourCash, newYourCash, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCash));
        Assert.assertEquals(expectedNewYourCash, newYourCashAfter, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCashAfter));
        Assert.assertEquals(expectedNewMemberCash, newMemberCashAfter, String.format("ERROR: The expected new cash balance of member is '%s' but found '%s'", expectedNewMemberCash, newMemberCashAfter));

        log("Verify  3. Verify main balance info");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, depositAmount,true);
        Assert.assertEquals(page.getLoginAccountBalanceInfo(),mainBalanceInfoExpected,"FAILED! Main account balance info is not match after deposit");

        log("Verify  3. Verify downline balance info");
        List<ArrayList<String>> listMemberInfoExpected =page.calculateDownlineBalanceInfo(listMemberInfo,depositAmount,true,false);
        Assert.assertEquals(page.getMemberInfo(1),listMemberInfoExpected,"FAILED! Members List balance is not match with the expected");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can deposited by Win/Loss Settle by click on Deposit button
     * @pre-condition:
     *           1. Log in successfully
     * @steps:  1. Navigate Agency Management > Deposit Withdrawal
     *          2. Check on more than 1 accounts and click on deposit button
     *          3. Input amount to deposit, select Win/Loss Settle, enter remark and click on Submit button
     * @expect:  1. Deposit popup is disappeared and there is a green check display in Update Status column
     *           2. Verify WinLoss is updated correctly
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Deposit_009(){
        log("@title: Validate can deposited by Win/Loss Settle by click on Deposit button");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 1.1 Filter Active account");
        page.filter("","Active","");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        List<AccountInfo> lstFiveUser = new ArrayList<>();
        int n = lstUsers.size() > 2 ? 2: lstFiveUser.size();
        for(int i=0; i<n;i++){
            lstFiveUser.add(i,lstUsers.get(i));
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

        double creditInitationAcc1 = page.getDataByColumn(username1,page.colCreditInitiation);
        double totalBalanceAcc1 = page.getDataByColumn(username1,page.colTotalBalance);
        double availableBalanceAcc1 = page.getDataByColumn(username1,page.colAvailableBalance);
        double winlossAcc1 = page.getDataByColumn(username1,page.colWinloss);
        double creditInitationAcc2 = page.getDataByColumn(username2,page.colCreditInitiation);
        double totalBalanceAcc2 = page.getDataByColumn(username2,page.colTotalBalance);
        double availableBalanceAcc2 = page.getDataByColumn(username2,page.colAvailableBalance);
        double winlossAcc2 = page.getDataByColumn(username2,page.colWinloss);
        DepositPopup popup = page.openDepositPopup();

        log("Step 3. Input amount to deposit, select Win/Loss Settle, enter remark and click on Submit button"  );
        popup.deposit(Double.toString(depositAmount),"Deposit TC006 deposit for account" +username1+" and acocunt "+username2,false,true);

        log("Step 4. Get info after deposit");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, totalDepositAmount,true);
        double actualCreditInitationAcc1 = page.getDataByColumn(username1,page.colCreditInitiation);
        double actualTotalBalanceAcc1 = page.getDataByColumn(username1,page.colTotalBalance);
        double actualAvailableBalanceAcc1 = page.getDataByColumn(username1,page.colAvailableBalance);
        double actualWinlossAcc1 = page.getDataByColumn(username1,page.colWinloss);
        double actualCreditInitationAcc2 = page.getDataByColumn(username2,page.colCreditInitiation);
        double actualTotalBalanceAcc2 = page.getDataByColumn(username2,page.colTotalBalance);
        double actualAvailableBalanceAcc2 = page.getDataByColumn(username2,page.colAvailableBalance);
        double actualWinlossAcc2 = page.getDataByColumn(username2,page.colWinloss);

        log("Verify 1. Deposit popup is disappeared and there is a green check display in Update Status column");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(0).getUserCode()),"FAILED! Green check on Update Status column is not display as expected");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(1).getUserCode()),"FAILED! Green check on Update Status column is not display as expected");

        log("Verify  2. Verify Balance of main account");
        Assert.assertEquals(page.getLoginAccountBalanceInfo(),mainBalanceInfoExpected,"FAILED! Main account balance info is not match with the expected");

        log("Verify  3. Verify balance on deposit account");
        Assert.assertEquals(actualCreditInitationAcc1,creditInitationAcc1,String.format("FAILED! Credit Initiation account %s is incorrect after deposit",username1));
        Assert.assertEquals(actualTotalBalanceAcc1,totalBalanceAcc1 +1,String.format("FAILED! Total balance account %s is incorrect after deposit",username1));
        Assert.assertEquals(actualAvailableBalanceAcc1,availableBalanceAcc1 +1,String.format("FAILED! Available Balance account %s is incorrect after deposit",username1));
        Assert.assertEquals(actualWinlossAcc1,winlossAcc1 +1,String.format("FAILED! Available Balance account %s is incorrect after deposit",username1));
        Assert.assertEquals(actualCreditInitationAcc2,creditInitationAcc2,String.format("FAILED! Credit Initiation account %s is incorrect after deposit",username2));
        Assert.assertEquals(actualTotalBalanceAcc2,totalBalanceAcc2 +1,String.format("FAILED! Total balance account %s is incorrect after deposit",username2));
        Assert.assertEquals(actualAvailableBalanceAcc2,availableBalanceAcc2 +1,String.format("FAILED! Available Balance account %s is incorrect after deposit",username2));
        Assert.assertEquals(actualWinlossAcc2,winlossAcc2 +1,String.format("FAILED! Available Balance account %s is incorrect after deposit",username1));
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
    public void Agent_AM_DepositWithdrawal_Deposit_010(){
        log("@title:  Validate cannot deposit an amount more than the current balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String userCode = memberInfo.getUserCode();
        double currentCashBalance = accountInfo.getCashBalance() + 1;
        double depositAmount =currentCashBalance + 0.5;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        List<ArrayList<String>> mainBalanceInfo = page.getLoginAccountBalanceInfo();
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step  2. Filter with username" );
        page.filter(userCode, "", "");

        log("Step 3. Check a checkbox of the account"  );
        page.action(DepositWithdrawalPage.Actions.CHECK, userCode);

        log("Step 4. Open Deposit popup"  );
        DepositPopup popup = page.openDepositPopup();

        log("Step 5.Deposit an amount which is more than the current balance"  );
        popup.deposit(Double.toString(depositAmount),String.format("Deposit TC010 deposit Credit with amount %s while current login balance is %s",depositAmount,currentCashBalance),false);
        page.waitingLoadingSpinner();

        log("Verify 1. An amount is NOT deposited successfully");
        List<ArrayList<String>> mainBalanceInfoExpected =  page.getLoginAccountBalanceInfo();
        Assert.assertEquals(mainBalanceInfo,mainBalanceInfoExpected,"FAILED! Main account balance info is not match after deposit");

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
    public void Agent_AM_DepositWithdrawal_Deposit_011() {
        log("@title:  Validate Deposit/Withdraw Link is disabled /enable when select/unselect account");
        log("Step 1. Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2. Select/Unselect all account checkbox");
        page.selectAllCheckBoxes();

        log("Verify 1. Verify Deposit/withdraw link is disabled/enabled");
        Assert.assertTrue(page.verifyDepositWithDrawLink(false),String.format("ERROR! Deposit/withdraw link not disable when select all checkbox"));

        log("INFO: Executed completely");
    }

    /**
     * @title: Cannot Deposit if exceed Max Player Credit setting
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Select direct member account and click on Deposit link
     * 3. Deposit by Credit Update with the amount greater than Max Player Credit Setting of upline
     * 4. Click submit
     * @expect: 1.The validate message displays "New Player Credit Balance has to be less than or equal to Max Player Credit. Current Player Credit Balance is 500.5"
     */
    @Test(groups = {"regression"})
    @Parameters({"password","brandname"})
    public void Agent_AM_DepositWithdrawal_Deposit_012(String password, String brandname) throws Exception {
        log("@title: Cannot Deposit if exceed Max Player Credit setting");
        log("Step 1. Navigate Agency Management > Deposit Withdrawal");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE",brandname).get(0);
        AccountInfo inDirectDownline = DownLineListingUtils.getDownLineUsers(directDownline.getUserID(), "PL", "ACTIVE", brandname).get(0);
        SATDownLineListingPage downLineListingPage = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, SATDownLineListingPage.class);
        downLineListingPage.clickEditIcon(directDownline.getUserCode());
        int playerMaxCredit = Integer.parseInt(downLineListingPage.cashBalanceSection.getMaxPlayerCredit()) + 1;

        downLineListingPage.logout();
        loginAgent(sosAgentURL, agentSecurityCodeURL, directDownline.getLoginID(), password, environment.getSecurityCode());
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        String playerCreditBalance = page.getCreditInitation(inDirectDownline.getUserCode());

        log("Step 2. Select direct member account and click on Deposit link");
        log("Step 3 Deposit by Credit Update with the amount greater than Max Player Credit Setting of upline");
        log("Step 4. Click submit");
        String depositMessage = page.deposit(inDirectDownline.getUserCode(), Integer.toString(playerMaxCredit), "", true, true);

        log("Verify 1.The validate message displays: New Player Credit Balance has to be less than or equal to Max Player Credit. Current Player Credit Balance is ...");
        Assert.assertEquals(depositMessage, String.format(DEPOSIT_GREATER_MAX_PLAYER_CREDIT, playerCreditBalance), "FAILED! Message deposit is incorrect");

        log("INFO: Executed completely");
    }

}
