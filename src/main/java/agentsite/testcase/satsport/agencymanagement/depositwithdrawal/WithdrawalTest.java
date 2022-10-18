package agentsite.testcase.satsport.agencymanagement.depositwithdrawal;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.all.agentmanagement.depositwithdrawal.DepositToPopup;
import agentsite.pages.all.agentmanagement.depositwithdrawal.WithdrawalPopup;
import agentsite.pages.sat.agentmanagement.SATDepositWithdrawalPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.DEPOSIT_WITHDRAW;

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
    public void Agent_AM_DepositWithdrawal_Withdraw_001(){
        log("@title: Validate that Withdrawal popup displays correct info.");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2: Open Withdrawal popup of an account " + userCode);
        WithdrawalPopup popup = (WithdrawalPopup)page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        String popupTitle = popup.lblTitle.getText();
        String expectedTitle = String.format(AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_TITLE, lstUsers.get(0).getUserCodeAndLoginID("%s (%s)"));
        String memberBalance = popup.lblMemberBalance.getText().replace(",", "");
        String yourBalance = popup.lblYourBalance.getText().replace(",", "");

        log("Verify 1: Withdrawal popup is displayed");
        log("Verify 2: Info is displayed correctly");
        Assert.assertTrue(popup.popupWithdrawal.isDisplayed(), "ERROR: popupWithdrawal is not displayed");
        Assert.assertEquals(popupTitle,expectedTitle, String.format("ERROR: The expected popup title is '%s' but found '%s'", expectedTitle, popupTitle));
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
    @Test (groups = {"regression"})
    public void Agent_AM_DepositWithdrawal_Withdraw_002(){
        log("@title: Validate that there is an error message displayed when submitted without inputting");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDownLineUsers(_brandname,userID);
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

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
    public void Agent_AM_DepositWithdrawal_Withdraw_003(){
        log("@title: Validate that there is an error message displayed when submitted without any amount");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDownLineUsers(_brandname,userID);
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2: Open Withdrawal popup of an account " + userCode);
        WithdrawalPopup popup = (WithdrawalPopup)page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log("Step 3: Withdrawal without any amount");
        popup.withdraw("", "",true,true);

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
    @Test(priority = 3,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_004(){
        log("@title: Validate that there is an insufficient error displayed when inputted an amount more than the current cash balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        String userCode = memberInfo.getUserCode();
        double withDrawAmount = memberInfo.getCashBalance() + 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step  2. Open Withdrawal popup of an account");
        WithdrawalPopup popup = (WithdrawalPopup) page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log("Step 3. Withdraw an amount more than the current cash balance");
        popup.fillWithdrawInfo(Double.toString(withDrawAmount), "WithdrawTC004 withdraw amount more than member cash balance",true);

        String errorMessage = popup.lblAmountError.getText();
        String expectedError = String.format(AGConstant.AgencyManagement.DepositWithdrawal.DEPOSIT_ERROR_INSUFFICIENT, memberInfo.getUserCodeAndLoginID("%s (%s)"));

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
    @Test(priority = 3,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_005(){
        log("@title:Validate that an amount is withdrawn successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String userCode = memberInfo.getUserCode();
        double withDrawAmount = 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step  2. Open Withdrawal popup of an account");
        WithdrawalPopup popup = (WithdrawalPopup) page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log("Step 3. Withdraw an amount");
        popup.fillWithdrawInfo(Double.toString(withDrawAmount), "WithdrawTC004 withdraw amount more than member cash balance",true);
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
    @Test(priority = 3,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_006(){
        log("@title:Validate can withdraw Credit update by click on Withdraw link");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        AccountInfo accountInfo = lstUsers.get(lstUsers.size()-1);
        String userCode = memberInfo.getUserCode();
        double withDrawAmount = 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step  2. Open Withdrawal popup of an account");
        WithdrawalPopup popup = (WithdrawalPopup) page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log("Step 3. Withdraw an amount from this username");
        popup.fillWithdrawInfo(Double.toString(withDrawAmount), "WithdrawTC004 withdraw amount more than member cash balance",true);
        String successfulMessage = popup.lblAmountSuccess.getText();

        log("Step 4. Close Withdrawal popup");
        double expectedNewMemberCash = memberInfo.getCashBalance() -  1;
        double expectedNewYourCash = accountInfo.getCashBalance() +  1;
        double newMemberCash = popup.getNewMemberCashBalance();
        double newMemberCashAfter = popup.getMemberCashBalance();
        double newYourCash = popup.getNewYourCashBalance();
        double newYourCashAfter = popup.getYourCashBalance();

        log("Verify 1. Message Withdraw successfully is displayed");
        Assert.assertEquals(successfulMessage, AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_SUCCESSFUL, String.format("ERROR: The expected error message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_SUCCESSFUL, successfulMessage));
        Assert.assertEquals(expectedNewMemberCash, newMemberCash, String.format("ERROR: The expected new cash balance of a member is '%s' but found '%s'", expectedNewMemberCash, newMemberCash));
        Assert.assertEquals(expectedNewYourCash, newYourCash, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCash));
        Assert.assertEquals(expectedNewYourCash, newYourCashAfter, String.format("ERROR: The expected your new cash balance is '%s' but found '%s'", expectedNewYourCash, newYourCashAfter));
        Assert.assertEquals(expectedNewMemberCash, newMemberCashAfter, String.format("ERROR: The expected new cash balance of member is '%s' but found '%s'", expectedNewMemberCash, newMemberCashAfter));

        log("Verify 2. An amount is updated correctly on this table");
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
    public void Agent_AM_DepositWithdrawal_Withdraw_007(){
        log("@title: Validate that Withdrawal popup is displayed when clicking Withdraw button");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

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
    @Test(priority = 3,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_008(){
        log("@title: Validate can withdrawn Credit Update successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        double amountWithdraw = 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        List<ArrayList<String>> mainBalanceInfo = page.getLoginAccountBalanceInfo();
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step 2. Filter with username");
        page.filter(userCode, "", "");

        log("Step 3. Check a checkbox of the account " + userCode);
        page.action(DepositWithdrawalPage.Actions.CHECK, userCode);

        log("Step 4. Open Withdraw popup");
        WithdrawalPopup popup = page.openWithdrawalPopup();

        log("Step 5.Select Credit Update and Withdraw 1 HKD");
        popup.withdraw(Double.toString(amountWithdraw),String.format("Withdraw TC008 withdraw credit update with amount %.2f from account %s",amountWithdraw,userCode),true,true);
        page.waitingLoadingSpinner();

        log("Verify  1. An amount is withdrawn successfully");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, amountWithdraw,false);
        List<ArrayList<String>> listMemberInfoExpected =page.calculateDownlineBalanceInfo(listMemberInfo,amountWithdraw,false,true);
        Assert.assertEquals(page.getLoginAccountBalanceInfo(),mainBalanceInfoExpected,"FAILED! Main account balance info is not match after deposit");
        Assert.assertEquals(page.getMemberInfo(1),listMemberInfoExpected,"FAILED! Downline balance is not match with the expected");

        log("Verify  2. Success icon is displayed when withdrawing completely");
        Assert.assertTrue(page.verifyUpdateStatus(listMemberInfo,true),"FAILED! Green check on Update Status column is not display as expected");

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
    @Test(priority = 3,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_009(){
        log("@title: Validate that cannot withdraw with  amount more than current balance");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        double amountWithdraw =  lstUsers.get(0).getCashBalance() + 1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        List<ArrayList<String>> mainBalanceInfo = page.getLoginAccountBalanceInfo();
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step 2. Filter with username");
        page.filter(userCode, "", "");

        log("Step 3. Check a checkbox of the account");
        page.action(DepositWithdrawalPage.Actions.CHECK, userCode);

        log("Step 4. Open Withdraw popup");
        WithdrawalPopup popup = page.openWithdrawalPopup();

        log("Step 5. Withdraw an amount which is more than the current balance");
        popup.fillWithdrawInfo(Double.toString(amountWithdraw),"Withdraw TC008 withdraw credit update amount more than current balance",true);
        page.waitingLoadingSpinner();

        log("Verify 1. An amount is withdrawn unsuccessfully");
        List<ArrayList<String>> mainBalanceInfoExpected = page.getLoginAccountBalanceInfo();
        List<ArrayList<String>> listMemberInfoExpected =page.getMemberInfo(1);
        Assert.assertEquals(mainBalanceInfo,mainBalanceInfoExpected,"FAILED! Main account balance info is not match after deposit");
        Assert.assertEquals(listMemberInfo,listMemberInfoExpected,"FAILED! Downline balance is not match with the expected");

        log("Verify 2. Failure icon is displayed when withdrawing completely");
        Assert.assertTrue(page.verifyUpdateStatus(listMemberInfo,false),"FAILED! Cross icon Update Status column is not display as expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can withdraw Win/Loss Settle successfully
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Click on withdraw link of an user
     *         3. Input amount and select Winloss by Settle then click submit
     * @expect: 1. Message Withdraw successfully is displayed
     *          2. Amount is updated correctly
     */
    @Test(priority = 3,groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_Withdraw_010(){
        log("@title: Validate can withdraw Win/Loss Settle successfully");
        List<AccountInfo> lstUsers = DownLineListingUtils.getAllDriectMember(_brandname,ProfileUtils.getProfile().getUserID());
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        String userCode = lstUsers.get(0).getUserCode();
        double amountWithdraw =  1;

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        page.filter("","Active","Member");
        List<ArrayList<String>> mainBalanceInfo = page.getLoginAccountBalanceInfo();
        List<ArrayList<String>> listMemberInfo = page.getMemberInfo(1);

        log("Step Precondition. Deposit a amount with Win/Loss option for the user " +userCode);
        DepositToPopup popupDeposit = (DepositToPopup)page.action(DepositWithdrawalPage.Actions.DEPOSIT, userCode);
        popupDeposit.deposit("1", "TC010 Deposit Win/Loss Settle 1",false,true);
        popupDeposit.clickCancelBtn();
        log("Step 2. Click on withdraw link of an user " +userCode);
        WithdrawalPopup popup = (WithdrawalPopup) page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, userCode);

        log("Step 3. Input amount and select Winloss by Settle then click submit");
        popup.withdraw(Double.toString(amountWithdraw), "Withdraw TC010 withdraw Winloss by Settle",false,true);
        String successMessage = popup.lblAmountSuccess.getText();
        popup.clickCancelBtn();

        log("Verify 1. Message Withdraw successfully is displayed");
        Assert.assertEquals(successMessage, AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_SUCCESSFUL, String.format("ERROR: The expected success message is '%s' but found '%s'", AGConstant.AgencyManagement.DepositWithdrawal.WITHDRAWAL_SUCCESSFUL,successMessage));

        log("Verify  2. Amount is updated correctly");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, amountWithdraw,false);
        List<ArrayList<String>> listMemberInfoExpected =page.calculateDownlineBalanceInfo(listMemberInfo,amountWithdraw,false,false);
        Assert.assertEquals(page.getLoginAccountBalanceInfo(),mainBalanceInfoExpected,"FAILED! Main account balance info is not match after deposit");
        Assert.assertEquals(page.getMemberInfo(1),listMemberInfoExpected,"FAILED! Members List balance is not match with the expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can multiple Withdraw by Credit Update by click on Withdraw button
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Check on more than 1 account and click on Withdraw button
     *         3. Input amount to deposit, select Credit Update enter remark and click on Submit button
     * @expect: 1. Deposit popup is disappeared and there is a green check display in Update Status column
     *          2. Verify Balance is updated correctly
     */
    @Test (priority = 3, groups = {"smoke1"})
    public void Agent_AM_DepositWithdrawal_Withdraw_011(){
        log("@title: Validate can multiple Withdraw by Credit Update by click on Withdraw button");

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
        double withdrawAmount = 1;
        double totalDepositAmount = withdrawAmount * (2);

        log("Step 1.2 Get main balance and list member info");
        List<ArrayList<String>> mainBalanceInfo = page.getLoginAccountBalanceInfo();

        log("Step  2. Check on more than 1 account and click on Withdraw button");
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
        WithdrawalPopup popup = page.openWithdrawalPopup();

        log("Step 3. Input amount to withdraw, select Win/Loss Settle enter remark and click on Submit button");
        popup.withdraw(Double.toString(withdrawAmount),"Withdraw TC012 withdraw account1 " +username1+" and account "+username2,true,true);

        log("Step 4. Get info after WithDraw");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, totalDepositAmount,false);
        double actualCreditInitationAcc1 = page.getDataByColumn(username1,page.colCreditInitiation);
        double actualTotalBalanceAcc1 = page.getDataByColumn(username1,page.colTotalBalance);
        double actualAvailableBalanceAcc1 = page.getDataByColumn(username1,page.colAvailableBalance);
        double actualWinlossAcc1 = page.getDataByColumn(username1,page.colWinloss);
        double actualCreditInitationAcc2 = page.getDataByColumn(username2,page.colCreditInitiation);
        double actualTotalBalanceAcc2 = page.getDataByColumn(username2,page.colTotalBalance);
        double actualAvailableBalanceAcc2 = page.getDataByColumn(username2,page.colAvailableBalance);
        double actualWinlossAcc2 = page.getDataByColumn(username2,page.colWinloss);

        log("Verify 1. There is a green check display in Update Status column");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(0).getUserCode()),"FAILED! Green check on Update Status column is not display as expected");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(1).getUserCode()),"FAILED! Green check on Update Status column is not display as expected");

        log("Verify  2. Verify Balance of main account");
        Assert.assertEquals(page.getLoginAccountBalanceInfo(),mainBalanceInfoExpected,"FAILED! Main account balance info is not match with the expected");

        log("Verify  3. Verify balance on deposit account");
        Assert.assertEquals(actualCreditInitationAcc1,creditInitationAcc1 -1,String.format("FAILED! Credit Initiation account %s is incorrect after withdraw",username1));
        Assert.assertEquals(actualTotalBalanceAcc1,totalBalanceAcc1 -1,String.format("FAILED! Total balance account %s is incorrect after withdraw",username1));
        Assert.assertEquals(actualAvailableBalanceAcc1,availableBalanceAcc1 -1,String.format("FAILED! Available Balance account %s is incorrect after withdraw",username1));
        Assert.assertEquals(actualWinlossAcc1,winlossAcc1,String.format("FAILED! Winloss of account %s is incorrect after withdraw",username1));
        Assert.assertEquals(actualCreditInitationAcc2,creditInitationAcc2 -1,String.format("FAILED! Credit Initiation account %s is incorrect after withdraw",username2));
        Assert.assertEquals(actualTotalBalanceAcc2,totalBalanceAcc2 -1,String.format("FAILED! Total balance account %s is incorrect after withdraw",username2));
        Assert.assertEquals(actualAvailableBalanceAcc2,availableBalanceAcc2 -1,String.format("FAILED! Available Balance account %s is incorrect after withdraw",username2));
        Assert.assertEquals(actualWinlossAcc2,winlossAcc2,String.format("FAILED! Win Loss account %s is incorrect after withdraw",username1));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can multiple Withdraw by Win/Loss Settle by click on Withdraw button
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     *         2. Check all accounts and click on Withdraw button
     *         3. Input amount to withdraw, select Win/Loss Settle enter remark and click on Submit button
     * @expect: 1. Deposit popup is disappeared and there is a green check display in Update Status column
     *          2. Verify Balance is updated correctly
     */
    @Test (groups = {"smoke1"})
    public void Agent_AM_DepositWithdrawal_Withdraw_012(){
        log("@title: Validate can multiple Withdraw by Win/Loss Settle by click on Withdraw button");

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

        log("Step  2. Check on more than 1 account and click on Withdraw button");
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
        WithdrawalPopup popup = page.openWithdrawalPopup();

        log("Step 3. Input amount to withdraw, select Win/Loss Settle enter remark and click on Submit button");
        popup.withdraw(Double.toString(depositAmount),"Withdraw TC012 withdraw account1 " +username1+" and account "+username2,false,true);

        log("Step 4. Get info after WithDraw");
        List<ArrayList<String>> mainBalanceInfoExpected = page.calculateMainAccountInfo(mainBalanceInfo, totalDepositAmount,false);
        double actualCreditInitationAcc1 = page.getDataByColumn(username1,page.colCreditInitiation);
        double actualTotalBalanceAcc1 = page.getDataByColumn(username1,page.colTotalBalance);
        double actualAvailableBalanceAcc1 = page.getDataByColumn(username1,page.colAvailableBalance);
        double actualWinlossAcc1 = page.getDataByColumn(username1,page.colWinloss);
        double actualCreditInitationAcc2 = page.getDataByColumn(username2,page.colCreditInitiation);
        double actualTotalBalanceAcc2 = page.getDataByColumn(username2,page.colTotalBalance);
        double actualAvailableBalanceAcc2 = page.getDataByColumn(username2,page.colAvailableBalance);
        double actualWinlossAcc2 = page.getDataByColumn(username2,page.colWinloss);      

        log("Verify 1. There is a green check display in Update Status column");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(0).getUserCode()),"FAILED! Green check on Update Status column is not display as expected");
        Assert.assertTrue(page.isUpdateStatusSuccess(lstUsers.get(1).getUserCode()),"FAILED! Green check on Update Status column is not display as expected");
        
        log("Verify  2. Verify Balance of main account");
        Assert.assertEquals(page.getLoginAccountBalanceInfo(),mainBalanceInfoExpected,"FAILED! Main account balance info is not match with the expected");

        log("Verify  3. Verify balance on withdraw account");
        Assert.assertEquals(actualCreditInitationAcc1,creditInitationAcc1,String.format("FAILED! Credit Initiation account %s is incorrect after withdraw",username1));
        Assert.assertEquals(actualTotalBalanceAcc1,totalBalanceAcc1 -1,String.format("FAILED! Total balance account %s is incorrect after withdraw",username1));
        Assert.assertEquals(actualAvailableBalanceAcc1,availableBalanceAcc1 -1,String.format("FAILED! Available Balance account %s is incorrect after withdraw",username1));
        Assert.assertEquals(actualWinlossAcc1,winlossAcc1 -1,String.format("FAILED! Available Balance account %s is incorrect after withdraw",username1));
        Assert.assertEquals(actualCreditInitationAcc2,creditInitationAcc2,String.format("FAILED! Credit Initiation account %s is incorrect after withdraw",username2));
        Assert.assertEquals(actualTotalBalanceAcc2,totalBalanceAcc2 -1,String.format("FAILED! Total balance account %s is incorrect after withdraw",username2));
        Assert.assertEquals(actualAvailableBalanceAcc2,availableBalanceAcc2 -1,String.format("FAILED! Available Balance account %s is incorrect after withdraw",username2));
        Assert.assertEquals(actualWinlossAcc2,winlossAcc2 -1,String.format("FAILED! Available Balance account %s is incorrect after withdraw",username1));
        log("INFO: Executed completely");
        
    }

}
