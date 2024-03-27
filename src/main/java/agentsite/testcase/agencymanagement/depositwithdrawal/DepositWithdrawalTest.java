package agentsite.testcase.agencymanagement.depositwithdrawal;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositPopup;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositWithdraw;
import agentsite.pages.agentmanagement.depositwithdrawal.NewUIDepositWithdraw;
import agentsite.pages.agentmanagement.depositwithdrawal.ViewLogPopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DepositWithdrawalTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * @expect: 1. Deposit Withdrawal page is displayed
     */
    @TestRails(id = "711")
    @Test(groups = {"http_request"})
    public void Agent_AM_DepositWithdrawal_711() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 2: Click Submit button");
        page.filter("", "All", "All");

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that this page loading is successful
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * @expect: 1. Items on Account Status dropdown-box are loaded correctly
     * 2. Items on Level dropdown-box are loaded correctly
     * 3. Column names on Account Balance info table are correct
     * 4. Column names on Downline info table are correct
     */
    @TestRails(id = "712")
    @Test(groups = {"smoke_creditcash", "nolan"})
    @Parameters("currency")
    public void Agent_AM_DepositWithdrawal_712(String currency) {
        log("@title: Validate that this page loading is successful");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        String loginAccBalance = String.format(Locale.getDefault(), "%,.2f", DownLineListingUtils.getMyCreditCashBalance());
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 1: Input security code");
//        page.securityPopup.submitSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));
        boolean isStatusItems = NewUIDepositWithdraw.ddbAccountStatus.areOptionsMatched(AGConstant.AgencyManagement.DepositWithdrawal.DDB_ACCOUNT_STATUS);
        boolean isLevel = NewUIDepositWithdraw.ddbLevel.areOptionsMatched(AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL);
        List<String> lstHeader = page.tblWithdrawalDeposit.getColumnNamesOfTable();

        log("Verify 1: Items on Account Status dropdown-box are loaded correctly");
        log("Verify 2: Items on Level dropdown-box are loaded correctly");
        Assert.assertTrue(isStatusItems, "ERROR: At least an item within Account Status ddb is incorrect");
        Assert.assertTrue(isLevel, "ERROR: At least an item within Level ddb is incorrect");
        Assert.assertEquals(page.getLabelText("lblLoginAccountAvailableBalance"), loginAccBalance, "FAILED! Available Balance is not correct");
        Assert.assertEquals(page.getLabelText("lblUsername"), AGConstant.LBL_USERNAME, "FAILED! Username label not correct");
        Assert.assertEquals(page.getLabelText("lblAccountStatus"), AGConstant.AgencyManagement.DepositWithdrawal.LBL_ACCOUNT_STATUS, "FAILED! Account status not correct");
        Assert.assertEquals(page.getLabelText("lblLevel"), AGConstant.AgencyManagement.DepositWithdrawal.LBL_LEVEL, "FAILED! Level label not correct");
        Assert.assertEquals(page.txtUsername.getAttribute("placeholder").trim(), AGConstant.AgencyManagement.DepositWithdrawal.USERNAME_NICKNAME, "FAILED! Username placeholder not correct");
        Assert.assertEquals(page.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "Failed, Submit button display incorrect");

        log("Verify 4: Column names on Deposit/withdraw info table are correct");
        Assert.assertEquals(lstHeader, AGConstant.AgencyManagement.DepositWithdrawal.TABLE_HEADER, "FAILED! Header Deposit Withdraw not match with the expected");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that filtering with username is correct
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Filter a username
     * @expect: 1. Data in this table is displayed correctly after filtering
     */
    @TestRails(id = "713")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_713() {
        log("@title: Validate that filtering with username is correct");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String userCode = lstUsers.get(0).getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

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
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Filter a username and Agent level
     * @expect: 1. Data in this table is displayed correctly after filtering
     */
    @TestRails(id = "714")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_714() {
        log("@title: Validate that filtering with username is correct");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }

        String userCode = lstUsers.get(0).getUserCode();
        String level = AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL.get(1);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log(String.format("Step 2: Filter with username '%s' and '%s' level", userCode, level));
        page.filter(userCode, "", level);

        List<ArrayList<String>> lstRecords = page.tblWithdrawalDeposit.getRowsWithoutHeader(2, false);

        log("Verify 1: Data in this table is displayed correctly after filtering");
        Assert.assertEquals(lstRecords.size(), 1, "ERROR: lstRecords doesn't equal to 1");
        Assert.assertEquals(lstRecords.get(0).get(page.defineDepositWithdrawTableColumn("User Name") - 1), userCode, String.format("ERROR: The expected username is '%s' but found '%s'", userCode, lstRecords.get(0).get(page.defineDepositWithdrawTableColumn("User Name") - 1)));
        Assert.assertTrue(page.verifyFilterByLevel(lstRecords.get(0).get(page.colLevel - 1), level), String.format("ERROR: The expected level is '%s' but found '%s'", level, lstRecords.get(0).get(page.colLevel - 1)));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that filtering with correct username, account Status and level
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Filter a username , status and Agent level
     * @expect: 1. Data in this table is displayed correctly after filtering
     */
    @TestRails(id = "715")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_715() {
        log("@title: Validate that filtering with correct username, account Status and level");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String status = lstUsers.get(0).getStatus();
        String userCode = lstUsers.get(0).getUserCode();
        String level = AGConstant.AgencyManagement.DepositWithdrawal.DDB_LEVEL.get(1);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log(String.format("Step 2: Filter with username '%s', '%s' status and '%s' level", userCode, status, level));
        page.filter(userCode, status.substring(0,1).toUpperCase() + status.substring(1).toLowerCase(), level);

        List<ArrayList<String>> lstRecords = page.tblWithdrawalDeposit.getRowsWithoutHeader(2, false);

        log("Verify 1: Data in this table is displayed correctly after filtering");
        Assert.assertEquals(lstRecords.size(), 1, "ERROR: lstRecords doesn't equal to 1");
        Assert.assertEquals(lstRecords.get(0).get(page.defineDepositWithdrawTableColumn("User Name") - 1), userCode, String.format("ERROR: The expected username is %s but found %s", userCode, lstRecords.get(0).get(page.defineDepositWithdrawTableColumn("User Name") - 1)));
        Assert.assertTrue(page.verifyFilterByLevel(lstRecords.get(0).get(page.colLevel - 1), level), String.format("ERROR: The expected level is %s but found %s", level, lstRecords.get(0).get(page.colLevel - 1)));
        Assert.assertTrue(lstRecords.get(0).get(page.colAccountStatus - 1).equalsIgnoreCase(status), String.format("ERROR: The expected status is %s but found %s", status, lstRecords.get(0).get(page.colAccountStatus - 1)));
        log("INFO: Executed completely");
    }

    /**
     * @title: TC006_Validate that there is no record found when filtering an incorrect status
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Filter a username, incorrect status, and Agent level
     * @expect: 1. There is no record found when filtering an incorrect status
     */
    @TestRails(id = "716")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_716() {
        log("@title: Validate that there is no record found when filtering an incorrect status");
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String status = lstUsers.get(0).getStatus().equals("ACTIVE") ? AGConstant.AgencyManagement.DepositWithdrawal.DDB_ACCOUNT_STATUS.get(4) : AGConstant.AgencyManagement.DepositWithdrawal.DDB_ACCOUNT_STATUS.get(1);
        String userCode = lstUsers.get(0).getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log(String.format("Step 2: Filter with username '%s', '%s' status and '%s' level", userCode, status, "All"));
        page.filter(userCode, status, "");

        List<ArrayList<String>> lstRecords = page.tblWithdrawalDeposit.getRowsWithoutHeader(2, false);

        log("Verify 1: There is no record found when filtering an incorrect status");
        Assert.assertTrue(page.lblNoRecord.getText().equals(AGConstant.NO_RECORD_FOUND), String.format("ERROR: The expected text is '%s' but found '%s'", AGConstant.NO_RECORD_FOUND, page.lblNoRecord.getText()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that there is no record found when filtering an invalid username
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Filter an invalid username
     * @expect: 1. There is no result found when filtering an invalid username
     */
    @TestRails(id = "717")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_717() {
        log("@title: Validate that there is no record found when filtering an invalid username");
        String userCode = StringUtils.generateAlphabetic(8);

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log(String.format("Step 2: Filter an invalid username '%s'", userCode));
        page.filter(userCode, "", "");

        log("Verify 1:There is no result found when filtering an invalid username");
        Assert.assertEquals(page.lblNoRecord.getText(), AGConstant.NO_RECORD_FOUND, String.format("ERROR: The expected text is '%s' but found '%s'", AGConstant.NO_RECORD_FOUND, page.lblNoRecord.getText()));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate there is UI when drill-down
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Drill-down to indirect downline
     * @expect: 1. There is no Update Status Transfer columns
     * 2. Deposit and Withdraw button is no longer displayed
     */
    @TestRails(id = "718")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_718() {
        log("@title: Validate there is UI when drill-down");
        AccountInfo acc = ProfileUtils.getProfile();
        String acc1 = acc.getUserCodeAndLoginID();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        String downlineUsename1 = lstUsers.get(0).getUserCodeAndLoginID();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        page.waitingLoadingSpinner();

        log(String.format("Step 2. Drill-down to indirect downline %s", lstUsers.get(0).getUserCode()));
        page.clickDownline(lstUsers.get(0).getUserCode());
        String actualBreadcrumb = page.getBreadcrumb();

        log("Verify 1. There is no Update Status Transfer columns");
        log("Verify 2. Deposit and Withdraw button is no longer displayed");
        Assert.assertEquals(actualBreadcrumb, String.format("%s\\%s", acc1, downlineUsename1), "FAILED! Downline Bar is incorrect");
        Assert.assertFalse(page.btnDeposit.isDisplayed(), "FAILED! Deposit button is displayed");
        Assert.assertFalse(page.btnWithdraw.isDisplayed(), "FAILED! Withdraw button is displayed");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Open View Log popup
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Click on View Log link of an account
     * @expect: 1. View Log popup display with the title "Deposit Withdrawal Log - SA1"
     * 2. View Log table display with the header "Date Time, Action, Amount, Remark, Perform By"
     */
    @TestRails(id = "719")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_AM_DepositWithdrawal_719() {
        log("@title:  Validate Open View Log popup");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        if (lstUsers.size() == 0) {
            log("INFO: There is no member under this account");
            return;
        }
        String userCode = lstUsers.get(0).getUserCode();
        String formatLoginID = lstUsers.get(0).getUserCodeAndLoginID("%s (%s)");
        log(String.format("Step 2. Click on View Log link of  account  %s", userCode));
        ViewLogPopup popup = (ViewLogPopup) page.action(DepositWithdraw.Actions.VIEW_LOG, userCode);

        log("Verify 1. View Log popup display with the title \"Deposit Withdrawal Log - SA1\"");
        Assert.assertEquals(popup.getTitle(), String.format(AGConstant.AgencyManagement.DepositWithdrawal.VIEWLOG_TITLE, formatLoginID), "FAILED! View Log Title is incorrect as expected");

        log("Verify 2. View Log table display with the header \"Date Time, Action, Amount, Remark, Perform By\"");
        Assert.assertEquals(popup.getHeaderTable(), AGConstant.AgencyManagement.DepositWithdrawal.TABLE_VIEW_LOG_HEADER, "FAILED! View Log table header not match with the expected");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Deposit log display correctly
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Deposit for an account
     * 3. Click View Log
     * @expect: 1. Verify log data display corresponding as deposit
     */
    @TestRails(id = "3619")
    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_DepositWithdrawal_3619() {
        log("@title:  Validate Deposit log display correctly");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        String remark = String.format("DepositWithdrawal_010 deposit 1 on %s", DateUtils.getDate(0, "DD/MM/YYY hh:mm", "GMT -4"));
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log(String.format("Step 2.  Deposit for an account%s", directDownline.getUserCode()));
        page.deposit(directDownline.getUserCode(), "1", remark, true, true);

        log("Step 3. Click View Log");
        ViewLogPopup viewLogPopup = (ViewLogPopup) page.action(DepositWithdraw.Actions.VIEW_LOG, directDownline.getUserCode());

        log("Verify 1. Verify log data display corresponding as deposit");
        List<ArrayList<String>> lstDepositLog = viewLogPopup.tblLog.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstDepositLog.get(0).get(1), String.format("%s%s%s%s", loginAccInfo.getLoginID(), "deposits to", directDownline.getLoginID(), "as Credit Update"));
        Assert.assertEquals(lstDepositLog.get(0).get(2), "1.00", "FAILED! Action info is incorrect");
        Assert.assertEquals(lstDepositLog.get(0).get(3), remark, "FAILED! Remark info is incorrect");
        Assert.assertEquals(lstDepositLog.get(0).get(4), loginAccInfo.getLoginID(), "FAILED!  info is incorrect");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Withdraw log display correctly
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal
     * 2. Withdrawal for an account
     * 3. Click View Log
     * @expect: 1. Verify log data display corresponding as With draw
     */
    @TestRails(id = "3620")
    @Test(groups = {"regression_creditcash"})
    public void Agent_AM_DepositWithdrawal_3620() {
        log("@title: Validate Withdraw log display correctly");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        AccountInfo loginAccInfo = ProfileUtils.getProfile();
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        String remark = String.format("DepositWithdrawal_011 withdraw 1 on %s", DateUtils.getDate(0, "DD/MM/YYY hh:mm", "GMT -4"));
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log(String.format("Step 2. Withdrawal for an account %s", directDownline.getUserCode()));
        page.withdraw(directDownline.getUserCode(), "1", remark, true, true);

        log("Step 3. Click View Log");
        ViewLogPopup viewLogPopup = (ViewLogPopup) page.action(DepositWithdraw.Actions.VIEW_LOG, directDownline.getUserCode());

        log("Verify 1. Verify log data display corresponding as With draw");
        List<ArrayList<String>> lstDepositLog = viewLogPopup.tblLog.getRowsWithoutHeader(1, false);
        Assert.assertEquals(lstDepositLog.get(0).get(1), String.format("%s%s%s%s", loginAccInfo.getLoginID(), "withdraws from", directDownline.getLoginID(), "as Credit Update"));
        Assert.assertEquals(lstDepositLog.get(0).get(2), "1.00", "FAILED! Action info is incorrect");
        Assert.assertEquals(lstDepositLog.get(0).get(3), remark, "FAILED! Remark info is incorrect");
        Assert.assertEquals(lstDepositLog.get(0).get(4), loginAccInfo.getLoginID(), "FAILED!  info is incorrect");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate security popup display Deposit/withdraw page
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Deposit Withdrawal     *
     * @expect: 1. Verify security popup display
     */
    @TestRails(id = "720")
    @Test(groups = {"smoke_creditcash", "nolan"})
    public void Agent_AM_DepositWithdrawal_720() {
        log("@title: Validate security popup display Deposit/withdraw page");

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage("");

        log("Verify 1. Verify security popup display");
        Assert.assertTrue(page.securityPopup.isDisplayed(), "FAILED! Security Popup should not display when open Deposit Withdraw page in SAT and White Label");

        log("INFO: Executed completely");
    }

    @TestRails(id = "721")
    @Test(groups = {"smoke_credticash"})
    public void Agent_AM_DepositWithdrawal_721() {
        log("@title: Validate My Credit, Total Balance, Sub Balance,Available Balance is correct");
        log("pre-condition: Log in successfully by SAD that belonging to Credit Cash line");
        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage("");
        log("Verify 1. In viewied level data My Credit, Total Balance, Sub Balance, Available Balance\n" +
                "     * In downline table  veify data to Total Balance, Sub Balance, Available Balance");
        //TODO: need handle tolerance value e.g calculate 403.06 but UI show 403.08
        page.verifyTotalBalanceHeaderCalculatedCorrect();
        page.verifySubBalanceHeaderCalculatedCorrect();
        page.verifyTotalBalanceCalculatedCorrect();
        log("INFO: Executed completely");
    }

    @Test(groups = {"interaction_creditcash"})
    @Parameters({"password"})
    public void Agent_AM_DepositWithdrawal_Deposit_015(String password) throws Exception {
        log("@title:  Verify Balance agent is correctly is correct when deposit from agent site");
        double depositAmount = 1;
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        String loginId = directDownline.getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 2. Select direct agent account and click Deposit");
        page.filter(loginId, "", "");
        double downlineAvailableBalance = page.getDataByColumn(loginId, 11);
        Object popupObj = page.action(DepositWithdraw.Actions.DEPOSIT, 1);

        log("Step 3. Deposit amount for agent");
        DepositPopup popup = DepositPopup.class.cast(popupObj);
        popup.deposit(Double.toString(depositAmount), String.format("Deposit amount %s to user %s", depositAmount, directDownline.getLoginID()));
        popup.closeDepositPopup();
        page.logout();

        log("Step 4. Login agent with account deposited");
        loginAgent(loginId, password, true);
        log("Verify Balance of deposited agent");
        page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        Assert.assertTrue(page.verifyBalanceUpdated(depositAmount, downlineAvailableBalance, DepositWithdraw.Actions.DEPOSIT));
    }

    @Test(groups = {"interaction_creditcash"})
    @Parameters({"password"})
    public void Agent_AM_DepositWithdrawal_Deposit_016(String password) throws Exception {
        log("@title:  Verify Balance agent is correctly is correct when withdraw from agent site");
        double depositAmount = 1;
        String downlineLevel = ProfileUtils.getDownlineBalanceInfo().get(0).get(0);
        String userID = ProfileUtils.getProfile().getUserID();
        AccountInfo directDownline = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname).get(0);
        String loginId = directDownline.getUserCode();

        log("Step 1: Navigate Agency Management > Deposit Withdrawal");
        DepositWithdrawalPage page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());

        log("Step 2. Select direct agent account and click Deposit");
        page.filter(loginId, "", "");
        double downlineAvailableBalance = page.getDataByColumn(loginId, 11);
        Object popupObj = page.action(DepositWithdraw.Actions.WITHDRAWAL, 1);

        log("Step 3. Deposit amount for agent");
        DepositPopup popup = DepositPopup.class.cast(popupObj);
        popup.deposit(Double.toString(depositAmount), String.format("Deposit amount %s to user %s", depositAmount, directDownline.getLoginID()));
        popup.closeDepositPopup();
        page.logout();

        log("Step 4. Login agent with account deposited");
        loginAgent(loginId, password, true);
        log("Verify Balance of deposited agent");
        page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        Assert.assertTrue(page.verifyBalanceUpdated(depositAmount, downlineAvailableBalance, DepositWithdraw.Actions.WITHDRAWAL));
    }

}

