package agentsite.testcase.satsport.agencymanagement.depositwithdrawal;

import agentsite.pages.all.agentmanagement.depositwithdrawal.DepositPopup;
import com.paltech.utils.DateUtils;
import com.paltech.utils.StringUtils;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert;
import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.all.agentmanagement.depositwithdrawal.ViewLogPopup;
import agentsite.pages.sat.agentmanagement.SATDepositWithdrawalPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static agentsite.common.AGConstant.HomePage.DEPOSIT_WITHDRAW;

public class DepositWithdrawalTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     * @expect:  1. Deposit Withdrawal page is displayed
     */
    @Test (groups = {"http_request"})
    public void Agent_AM_DepositWithdrawal_001(){
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2: Click Submit button");
        page.filter("", "All", "All");

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that this page loading is successful
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     * @expect:  1. Items on Account Status dropdown-box are loaded correctly
     *           2. Items on Level dropdown-box are loaded correctly
     *           3. Column names on Account Balance info table are correct
     *           4. Column names on Downline info table are correct
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_002(){
        log("@title: Validate that this page loading is successful");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Verify 1: Items on Account Status dropdown-box are loaded correctly");
        Assert.assertTrue(page.ddbAccountStatus.areOptionsMatched(AGConstant.AgencyManagement.DepositWithdrawal.DDB_ACCOUNT_STATUS), "ERROR: At least an item within Account Status ddb is incorrect");

        log("Verify 2: Items on Level dropdown-box are loaded correctly");
        Assert.assertTrue(page.ddbLevel.areOptionsMatched(AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL),  "ERROR: At least an item within Level ddb is incorrect");

        log("Verify 3: Column names on Account Balance info table are correct");
        List<String> lstAccountBalanceHeaders = page.tblAccountBalance.getColumnNamesOfTable();
        Assert.assertEquals(lstAccountBalanceHeaders, AGConstant.AgencyManagement.DepositWithdrawal.TABLE_ACCOUNT_BALANCE_HEADER, "FAILED! Header account table balance not match with the expected");

        log("Verify 4: Column names on Downline info table are correct");
        List<String> lstHeader = page.tblWithdrawalDeposit.getColumnNamesOfTable();
        Assert.assertEquals(lstHeader, AGConstant.AgencyManagement.DepositWithdrawal.TABLE_HEADER_SAT,"FAILED! Header Deposit Withdraw not match with the expected");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that filtering with username is correct
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Filter a username
     * @expect:  1. Data in this table is displayed correctly after filtering
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_003(){
        log("@title: Validate that filtering with username is correct");
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE").get(0);
        String userCode = directDownline.getUserCode();
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2: Filter with username " + userCode);
        page.filter(userCode, "", "");

        List<String> lstUsername = page.tblWithdrawalDeposit.getColumn(page.defineDepositWithdrawTableColumn("User Name"), false);
        log("Verify 1: This table is displayed correctly");
        Assert.assertEquals(lstUsername.size(), 1, "ERROR: lstUsername doesn't equal to 1");
        Assert.assertEquals(lstUsername.get(0), userCode, String.format("ERROR: The expected username is '%s' but found '%s'", userCode, lstUsername.get(0)));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that filtering with username and level is correct
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Filter a username and Agent level
     * @expect:  1. Data in this table is displayed correctly after filtering
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_004(){
        log("@title: Validate that filtering with username is correct");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }

        String userCode = lstUsers.get(0).getUserCode();
        String level = AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL.get(1);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log(String.format("Step 2: Filter with username '%s' and '%s' level", userCode, level));
        page.filter(userCode, "", level);

        List<ArrayList<String>> lstRecords = page.tblWithdrawalDeposit.getRowsWithoutHeader(2, false);
        log("Verify 1: Data in this table is displayed correctly after filtering");
        Assert.assertEquals(lstRecords.size(), 1, "ERROR: lstRecords doesn't equal to 1");
        Assert.assertEquals(lstRecords.get(0).get(page.defineDepositWithdrawTableColumn("User Name")-1), userCode, String.format("ERROR: The expected username is '%s' but found '%s'", userCode, lstRecords.get(0).get(page.defineDepositWithdrawTableColumn("User Name")-1)));
        Assert.assertTrue(page.verifyFilterByLevel(lstRecords.get(0).get(page.colLevel-1),level), String.format("ERROR: The expected level is '%s' but found '%s'", level, lstRecords.get(0).get(page.colLevel-1)));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that filtering with correct username, account Status and level
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Filter a username , status and Agent level
     * @expect:  1. Data in this table is displayed correctly after filtering
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_005(){
        log("@title: Validate that filtering with correct username, account Status and level");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String status = lstUsers.get(0).getStatus().equals("ACTIVE") ? AGConstant.AgencyManagement.DepositWithdrawal.DDB_ACCOUNT_STATUS.get(1) : AGConstant.AgencyManagement.DepositWithdrawal.DDB_ACCOUNT_STATUS.get(4);
        String userCode = lstUsers.get(0).getUserCode();
        String level = AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL.get(1);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log(String.format("Step 2: Filter with username '%s', '%s' status and '%s' level", userCode, status, level));
        page.filter(userCode, status, level);

        List<ArrayList<String>> lstRecords = page.tblWithdrawalDeposit.getRowsWithoutHeader(2, false);

        log("Verify 1: Data in this table is displayed correctly after filtering");
        Assert.assertEquals(lstRecords.size(), 1, "ERROR: lstRecords doesn't equal to 1");
        Assert.assertEquals(lstRecords.get(0).get(page.defineDepositWithdrawTableColumn("User Name")-1), userCode, String.format("ERROR: The expected username is '%s' but found '%s'", userCode, lstRecords.get(0).get(page.defineDepositWithdrawTableColumn("User Name")-1)));
        Assert.assertTrue(page.verifyFilterByLevel(lstRecords.get(0).get(page.colLevel-1),level), String.format("ERROR: The expected level is '%s' but found '%s'", level, lstRecords.get(0).get(page.colLevel-1)));
        Assert.assertTrue(lstRecords.get(0).get(page.colAccountStatus-1).contains(status) , String.format("ERROR: The expected status is '%s' but found '%s'", status, lstRecords.get(0).get(page.colAccountStatus-1)));
        log("INFO: Executed completely");
    }

    /**
     * @title: TC006_Validate that there is no record found when filtering an incorrect status
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Filter a username, incorrect status, and Agent level
     * @expect:  1. There is no record found when filtering an incorrect status
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_006(){
        log("@title: Validate that there is no record found when filtering an incorrect status");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String status = lstUsers.get(0).getStatus().equals("ACTIVE") ? AGConstant.AgencyManagement.DepositWithdrawal.DDB_ACCOUNT_STATUS.get(4) : AGConstant.AgencyManagement.DepositWithdrawal.DDB_ACCOUNT_STATUS.get(1);
        String loginID = lstUsers.get(0).getLoginID();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log(String.format("Step 2: Filter with username '%s', '%s' status and '%s' level", loginID, status, "All"));
        page.filter(loginID, status,"");

        List<ArrayList<String>> lstRecords = page.tblWithdrawalDeposit.getRowsWithoutHeader(2, false);

        log("Verify 1: There is no record found when filtering an incorrect status");
        Assert.assertEquals(lstRecords.size(), 1, "ERROR: lstRecords doesn't equal to 1");
        Assert.assertEquals(lstRecords.get(0).get(0), AGConstant.NO_RECORD_FOUND , String.format("ERROR: The expected text is '%s' but found '%s'", AGConstant.NO_RECORD_FOUND, lstRecords.get(0).get(0)));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there is no record found when filtering an invalid username
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Filter an invalid username
     * @expect:  1. There is no result found when filtering an invalid username
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_007(){
        log("@title: Validate that there is no record found when filtering an invalid username");
        String userCode = StringUtils.generateAlphabetic(8);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log(String.format("Step 2: Filter an invalid username '%s'", userCode));
        page.filter(userCode, "", "");

        log("Verify 1:There is no result found when filtering an invalid username");
        Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND , String.format("ERROR: The expected text is '%s' but found '%s'", AGConstant.NO_RECORD_FOUND,page.lblNoRecord.getText()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate there is UI when drill-down
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Drill-down to indirect downline
     * @expect: 1. There is no Update Status Transfer columns
     * 2. Deposit and Withdraw button is no longer displayed
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_008(){
        log("@title: Validate there is UI when drill-down");
        AccountInfo acc = ProfileUtils.getProfile();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }

        log(String.format("Step 2. Drill-down to indirect downline %s", lstUsers.get(0).getUserCode()));
        page.clickDownline(lstUsers.get(0).getUserCode());
        log("Verify 1. Verify path is correct");
        Assert.assertEquals(page.lblDownlineBar.getText(),String.format("%s \\ %s",
                String.format("%s (%s)",acc.getUserCode(),acc.getLoginID()),
                String.format("%s (%s)",lstUsers.get(0).getUserCode(),lstUsers.get(0).getLoginID())), "FAILED! Downline Bar is incorrect");
        List<String> lstAccountBalanceHeaders = page.tblAccountBalance.getColumnNamesOfTable();
        List<String> lstHeader = page.tblWithdrawalDeposit.getColumnNamesOfTable();
        log("Verify 1. There is no Update Status, Transfer columns");
        Assert.assertFalse(lstHeader.contains("Status Update"),"FAILED! Status Update column display when drilldown");
        Assert.assertFalse(lstHeader.contains("Transfer"),"FAILED!Transfer column display when drilldown");

        log("Verify 2. Deposit and Withdraw button is no longer displayed");
        Assert.assertFalse(page.btnDeposit.isDisplayed(),"FAILED! Deposit button display");
        Assert.assertFalse(page.btnWithdraw.isDisplayed(),"FAILED! Withdraw button display");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Open View Log popup
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Click on View Log link of an account
     * @expect: 1. View Log popup display with the title "Deposit Withdrawal Log - SA1"
     *  2. View Log table display with the header "Date Time, Action, Amount, Remark, Perform By"
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_009(){
        log("@title:  Validate Open View Log popup");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String userCode =lstUsers.get(0).getUserCode();
        String formatLoginID =  lstUsers.get(0).getUserCodeAndLoginID("%s (%s)");
        log(String.format("Step 2. Click on View Log link of  account  %s", userCode));
        ViewLogPopup popup = (ViewLogPopup)page.action(DepositWithdrawalPage.Actions.VIEW_LOG, userCode);

        log("Verify 1. View Log popup display with the title \"Deposit Withdrawal Log - SA1\"");
        Assert.assertEquals(popup.getTitle(),String.format(AGConstant.AgencyManagement.DepositWithdrawal.VIEWLOG_TITLE,formatLoginID),"FAILED! View Log Title is incorrect as expected");

        log("Verify 2. View Log table display with the header \"Date Time, Action, Amount, Remark, Perform By\"");
        Assert.assertEquals(popup.getHeaderTable(),AGConstant.AgencyManagement.DepositWithdrawal.TABLE_VIEW_LOG_HEADER,"FAILED! View Log table header not match with the expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Deposit log display correctly
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Deposit for an account
     *           3. Click View Log
     * @expect: 1. Verify log data display corresponding as deposit
     */
    @Test (groups = {"satregression"})
    public void Agent_AM_DepositWithdrawal_010(){
        log("@title:  Validate Deposit log display correctly");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID,downlineLevel,"ACTIVE").get(0);
        String remark = String.format("DepositWithdrawal_010 deposit 1 on %s", DateUtils.getDate(0, "DD/MM/YYY hh:mm", "GMT -4"));
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log(String.format("Step 2.  Deposit for an account%s", directDownline.getUserCode()));
        page.deposit(directDownline.getUserCode(),"1", remark,true,true);

        log("Step 3. Click View Log");
        ViewLogPopup viewLogPopup = (ViewLogPopup)page.action(DepositWithdrawalPage.Actions.VIEW_LOG,directDownline .getUserCode());

        log("Verify 1. Verify log data display corresponding as deposit");
        List<ArrayList<String>> lstDepositLog = viewLogPopup.tblLog.getRowsWithoutHeader(1,false);
        Assert.assertEquals(lstDepositLog.get(0).get(1),String.format("%s%s%s%s",loginAccInfo.getLoginID(),"deposits to",directDownline.getLoginID(),"as Credit Update"));
        Assert.assertEquals(lstDepositLog.get(0).get(2),"1.00","FAILED! Action info is incorrect");
        Assert.assertEquals(lstDepositLog.get(0).get(3),remark,"FAILED! Remark info is incorrect");
        Assert.assertEquals(lstDepositLog.get(0).get(4),loginAccInfo.getLoginID(),"FAILED!  info is incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Withdraw log display correctly
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal
     *           2. Withdrawal for an account
     *           3. Click View Log
     * @expect:  1. Verify log data display corresponding as With draw
     */
    @Test (groups = {"satregression"})
    public void Agent_AM_DepositWithdrawal_011(){
        log("@title: Validate Withdraw log display correctly");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID,downlineLevel,"ACTIVE").get(0);
        String remark = String.format("DepositWithdrawal_011 withdraw 1 on %s", DateUtils.getDate(0, "DD/MM/YYY hh:mm", "GMT -4"));
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log(String.format("Step 2. Withdrawal for an account %s", directDownline.getUserCode()));
        page.withdraw(directDownline.getUserCode(), "1", remark, true, true);

        log("Step 3. Click View Log");
        ViewLogPopup viewLogPopup = (ViewLogPopup)page.action(DepositWithdrawalPage.Actions.VIEW_LOG, directDownline.getUserCode());

        log("Verify 1. Verify log data display corresponding as With draw");
        List<ArrayList<String>> lstDepositLog = viewLogPopup.tblLog.getRowsWithoutHeader(1,false);
        Assert.assertEquals(lstDepositLog.get(0).get(1),String.format("%s%s%s%s",loginAccInfo.getLoginID(),"withdraws from",directDownline.getLoginID(),"as Credit Update"));
        Assert.assertEquals(lstDepositLog.get(0).get(2),"1.00","FAILED! Action info is incorrect");
        Assert.assertEquals(lstDepositLog.get(0).get(3),remark,"FAILED! Remark info is incorrect");
        Assert.assertEquals(lstDepositLog.get(0).get(4),loginAccInfo.getLoginID(),"FAILED!  info is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate there is no security popup display Deposit/withdraw page
     * @pre-condition:
     *           1. Log in successfully
     * @steps:   1. Navigate Agency Management > Deposit Withdrawal     *
     * @expect:  1. Verify there is no security popup display
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_012(){
        log("@title: Validate there is no security popup display Deposit/withdraw page");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Verify 1. Verify there is no security popup display");
        Assert.assertFalse(page.popupSecurityCode.isDisplayed(),"FAILED! Security Popup should not display when open Deposit Withdraw page in SAT and White Label");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate My Credit, Total Balance, Sub Balance,Available Balance is correc
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal     *
     * @expect: In viewied level data My Credit, Total Balance, Sub Balance, Available Balance
     * In downline table  veify data to Total Balance, Sub Balance, Available Balance
     */
    @Test (groups = {"smoke"})
    public void Agent_AM_DepositWithdrawal_013() {
        log("@title: Validate My Credit, Total Balance, Sub Balance,Available Balance is correct");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Verify 1. In viewied level data My Credit, Total Balance, Sub Balance, Available Balance\n" +
                "     * In downline table  veify data to Total Balance, Sub Balance, Available Balance");


        log("INFO: Executed completely");
    }

    @Test (groups = {"interaction"})
    @Parameters("password")
    public void Agent_AM_DepositWithdrawal_Deposit_015(String password) throws Exception {
        log("@title:  Verify Balance agent is correctly is correct when deposit from agent site");
        double depositAmount = 1;
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE").get(0);
        String userCodeAndLoginID = directDownline.getUserCodeAndLoginID("%s %s");
        String [] splitUserCodeAndID = userCodeAndLoginID.split("\\s+");
        String loginId = splitUserCodeAndID[0];
        String userName = splitUserCodeAndID[1];

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2. Select direct agent account and click Deposit" );
        page.filter(loginId, "", "");
        double downlineAvailableBalance = page.getDataByColumn(loginId,11);
        Object popupObj = page.action(DepositWithdrawalPage.Actions.DEPOSIT, 1);

        log("Step 3. Deposit amount for agent" );
        DepositPopup popup = DepositPopup.class.cast(popupObj);
        popup.deposit(Double.toString(depositAmount),String.format("Deposit amount %s to user %s",depositAmount,directDownline.getLoginID()));
        popup.closeDepositPopup();
        page.logout();

        log("Step 4. Login agent with account deposited");
        loginAgent(userName,password,true);
        log("Verify downline agent balance is deposit");
        page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        Assert.assertTrue(page.verifyBalanceUpdated(depositAmount,downlineAvailableBalance,DepositWithdrawalPage.Actions.DEPOSIT));
    }

    @Test (groups = {"interaction"})
    @Parameters("password")
    public void Agent_AM_DepositWithdrawal_Deposit_016(String password) throws Exception {
        log("@title:  Verify Balance agent is correctly is correct when withdrawal from agent site");
        double withDrawalAmount = 1;
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE").get(0);
        String userCodeAndLoginID = directDownline.getUserCodeAndLoginID("%s %s");
        String [] splitUserCodeAndID = userCodeAndLoginID.split("\\s+");
        String loginId = splitUserCodeAndID[0];
        String userName = splitUserCodeAndID[1];

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        SATDepositWithdrawalPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);

        log("Step 2. Select direct agent account and click Deposit" );
        page.filter(loginId, "", "");
        double downlineAvailableBalance = page.getDataByColumn(loginId,11);
        Object popupObj = page.action(DepositWithdrawalPage.Actions.WITHDRAWAL, 1);

        log("Step 3. Deposit amount for agent" );
        DepositPopup popup = DepositPopup.class.cast(popupObj);
        popup.deposit(Double.toString(withDrawalAmount),String.format("Withdrawal amount %s from user %s",withDrawalAmount,directDownline.getLoginID()));
        popup.closeDepositPopup();
        page.logout();

        log("Step 4. Login agent with account deposited");
        loginAgent(userName,password,true);
        log("Verify Balance of Withdrawal agent");
        page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DEPOSIT_WITHDRAW, SATDepositWithdrawalPage.class);
        Assert.assertTrue(page.verifyBalanceUpdated(withDrawalAmount,downlineAvailableBalance,DepositWithdrawalPage.Actions.WITHDRAWAL));
    }
}

