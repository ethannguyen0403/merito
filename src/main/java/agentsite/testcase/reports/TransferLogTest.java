package agentsite.testcase.reports;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositWithdraw;
import agentsite.pages.agentmanagement.depositwithdrawal.ViewLogPopup;
import agentsite.pages.report.TransferLogPage;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class TransferLogTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Transfer Log
     * @expect: 1. There is no http responded error returned
     */
    @TestRails(id = "801")
    @Test(groups = {"http_request"})
    public void Agent_Report_Transfer_Log_801() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Transfer Log");
        agentHomePage.navigateTransferLogPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data Transfer Log display correctly
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Get View Log info
     * 2. Navigate Report > Transfer Log
     * 3. Input the account do deposit/withdraw in steps 1 and click submit
     * @expect: 1. Verify log show correctly
     */
    @TestRails(id = "802")
    @Test(groups = {"smoke_creditcash"})
    public void Agent_Report_Transfer_Log_802() {
        log("@title: Validate data Transfer Log display correctly");
        DepositWithdrawalPage page;
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        String userCode = memberInfo.getUserCode();

        log("Step 1. Get View Log info");
        page = agentHomePage.navigateDepositWithdrawalPage(environment.getSecurityCode());
        page.depositWithdraw.deposit(userCode, "1", "Deposit 1 auto script " + com.paltech.utils.StringUtils.generateString("Auto", 6), true, true);
        ViewLogPopup popup = (ViewLogPopup) page.depositWithdraw.action(DepositWithdraw.Actions.VIEW_LOG, userCode);
        List<ArrayList<String>> log = popup.tblLog.getRowsWithoutHeader(1, false);
        List<ArrayList<String>> expectedData = popup.defineTransferLogbyViewLogData(log);
        popup.closePopup();

        log("Step 2. Navigate Report > Transfer Log");
        TransferLogPage tranferlogPage = page.navigateTransferLogPage();

        log("Step 3. Input the account do deposit/withdraw in steps 1 and click submit");
        tranferlogPage.filter(userCode, "All");

        log("Verify  1. Verify log show correctly");
        List<ArrayList<String>> data = tranferlogPage.tblReport.getRowsWithoutHeader(true);
        for (int i = data.size() - 2, j = 0; i < data.size(); i++, j++) {
            for (int n = 1; n < data.get(i).size(); n++) {
                Assert.assertEquals(StringUtils.normalizeSpace(data.get(i).get(n).trim()), expectedData.get(j).get(n).trim(), "FAILED! Expected not match with actual");
            }
        }

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data Transfer Log UI display correctly
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Get View Log info
     * 2. Navigate Report > Transfer Log
     * @expect: 1. Verify page UI show correctly
     */
    @TestRails(id = "803")
    @Test(groups = {"smoke"})
    public void Agent_Report_Transfer_Log_803() {
        log("@title: Validate data Transfer Log display correctly");
        log("Step 1. Navigate Report > Transfer Log");
        TransferLogPage tranferlogPage = agentHomePage.navigateTransferLogPage();

        List<String> lstHeader = tranferlogPage.tblReport.getHeaderNameOfRows();

        log("Verify  1. Verify page UI show correctly");
        Assert.assertTrue(tranferlogPage.txtUserName.isDisplayed(), "FAILED! Username textbox is not displayed");
        Assert.assertEquals(tranferlogPage.btnToday.getText(), AGConstant.Report.BTN_TODAY, "FAILED! Today button is incorrect");
        Assert.assertEquals(tranferlogPage.btnYesterday.getText(), AGConstant.Report.BTN_YESTERDAY, "FAILED! Yesterday button is incorrect");
        Assert.assertEquals(tranferlogPage.btnLastWeek.getText(), AGConstant.Report.LAST_WEEK, "FAILED! Last Business button is incorrect");
        Assert.assertEquals(tranferlogPage.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "FAILED! Submit button is incorrect");
        Assert.assertEquals(tranferlogPage.lblInfo.getText(), AGConstant.Report.TransferLog.LBL_INFO, "FAILED! Hint message is incorrect");
        //TODO: handle for oldui/newui
//        Assert.assertEquals(lstHeader, AGConstant.Report.TransferLog.TABLE_HEADER, "FAILED! Table header is incorrect");
        log("INFO: Executed completely");
    }

}
