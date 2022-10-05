package agentsite.testcase.satsport.report;

import com.paltech.driver.DriverManager;
import agentsite.objects.agent.account.AccountInfo;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.DepositWithdrawalPage;
import agentsite.pages.all.agentmanagement.depositwithdrawal.ViewLogPopup;
import agentsite.pages.all.report.TransferLogPage;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.ArrayList;
import java.util.List;

import static agentsite.common.AGConstant.HomePage.REPORT;
import static agentsite.common.AGConstant.HomePage.TRANSFER_LOG;

public class TransferLogTest extends BaseCaseMerito {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report> Transfer Log
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Transfer_Log_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report> Transfer Log");
        agentHomePage.clickSubMenu(REPORT, TRANSFER_LOG, TransferLogPage.class);

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data Transfer Log display correctly
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps: 1. Get View Log info
     *          2. Navigate Report > Transfer Log
     *          3. Input the account do deposit/withdraw in steps 1 and click submit
     * @expect: 1. Verify log show correctly
     */
    @Test(groups = {"smoke"})
    public void Agent_Report_Transfer_Log_003() throws Exception {
        log("@title: Validate data Transfer Log display correctly");
        DepositWithdrawalPage page;
        List<AccountInfo> lstUsers = DownLineListingUtils.getCashCreditListing();
        Assert.assertTrue(lstUsers.size() > 0, "ERROR: lstUsers size in DownLineListing is zero");
        AccountInfo memberInfo = lstUsers.get(0);
        String userCode = memberInfo.getUserCode();
        log("Step 1. Get View Log info");
        page = agentHomePage.navigateDepositWithdrawal("");
        ViewLogPopup popup = (ViewLogPopup) page.action(DepositWithdrawalPage.Actions.VIEW_LOG, userCode);
        List<ArrayList<String>> log = popup.tblLog.getRowsWithoutHeader(1, false);
        List<ArrayList<String>> expectedData =popup.defineTransferLogbyViewLogData(log);

        log("Step 2. Navigate Report > Transfer Log");
        DriverManager.getDriver().switchToParentFrame();
        TransferLogPage tranferlogPage = agentHomePage.clickSubMenu(REPORT, TRANSFER_LOG, TransferLogPage.class);

        log("Step 3. Input the account do deposit/withdraw in steps 1 and click submit");
        tranferlogPage.filter("", "All");

        log("Verify  1. Verify log show correctly");
        List<ArrayList<String>> data = tranferlogPage.tblReport.getRowsWithoutHeader( true);
        for(int i = data.size() - 2, j= 0; i < data.size(); i++, j++)
        {
            for(int n = 1; n <data.get(i).size() ; n++){
                Assert.assertEquals(StringUtils.normalizeSpace(data.get(i).get(n).trim()),expectedData.get(j).get(n).trim(),"FAILED! Expected not match with actual");
            }
        }

        log("INFO: Executed completely");
    }
}
