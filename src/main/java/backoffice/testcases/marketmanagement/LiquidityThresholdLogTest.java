package backoffice.testcases.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.marketmanagement.LiquidityThresholdLogPage;
import backoffice.pages.bo.marketmanagement.components.LiquidityThresholdLogPopup;
import backoffice.utils.tools.LiquidityThresholdLogUltils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class LiquidityThresholdLogTest extends BaseCaseTest {

    /**
     * @title: Validate can search account in agent that have view log and log is matched
     * @pre-condition: 1. In agent site > Liquidity setting > Pick an account has View link display in History column
     * 2. Login BO
     * @steps: 1. Access Tool > Liquidity Threshold Log
     * 2. Input the username and click search button
     * 3. Click View link
     * @expect: 1. Verify account is display with correct data
     * 2. View log data match with view login get in agent site
     */
    @TestRails(id = "626")
    @Test(groups = {"smoke"})
    @Parameters({"companyAccount"})
    public void BO_Market_Management_Liquidity_Threshold_log_626(String companyAccount) {
        log("@title: Validate can search account in agent that have view log and log is matched");
        log("Step 1. Access Tool > Liquidity Threshold Log");
        String username = companyAccount;
        LiquidityThresholdLogPage page = backofficeHomePage.navigateLiquidityThresholdLog();
        List<ArrayList<String>> lstData = LiquidityThresholdLogUltils.getLog(username);
        if (lstData.get(0).get(1).equals(lstData.get(0).get(2))){
            lstData.get(0).set(2, "");
        }
        if (lstData.get(0).get(10).equals("View")){
            lstData.get(0).set(10, "");
        }
        log("Step 2. Input the username and click search button");
        page.search(username);

        log("Verify 1. Verify account is display with correct data");
        List<ArrayList<String>> lstDataAc = page.tblLog.getRowsWithoutHeader(3, false);
        Assert.assertEquals(lstData.size(), lstDataAc.size(), "FAILED ! Search result does not match");
        for (int i = 0; i < lstData.get(0).size() - 1; i++) {
            Assert.assertEquals(lstDataAc.get(0).get(i), lstData.get(0).get(i), "FAILED! Data not match with the api");
        }

        log("Step 3. Click View link");
        LiquidityThresholdLogPopup popup = page.openViewLog(username);
        List<ArrayList<String>> lstHistory = LiquidityThresholdLogUltils.getLiquidityHistory(username);
        List<ArrayList<String>> lstHistoryat = popup.tblLog.getRowsWithoutHeader(20, false);
        log("Verify 2. View log data match with view login get in agent site");
        Assert.assertEquals(lstHistoryat, lstHistory, "FAILED! Log data not match with the api");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can NOT search account in agent that does not have view log link
     * @pre-condition: 1. In agent site > Liquidity setting > Pick an account does not have View link display in History column
     * 2. Login BO
     * @steps: 1. Access Tool > Liquidity Threshold Log
     * 2. Input the username in precondition and click search button
     * @expect: 1. Verify message No record found
     */
    @TestRails(id = "627")
    @Test(groups = {"smoke"})
    public void BO_Market_Management_Liquidity_Threshold_log_627() {
        log("@title: Validate can NOT search account in agent that does not have view log link");
        log("Step 1. Access Tool > Liquidity Threshold Log");
        String username = "Auto.PART2";
        LiquidityThresholdLogPage page = backofficeHomePage.navigateLiquidityThresholdLog();

        log("Step 2. Input the username in precondition and click search button");
        page.search(username);

        log("Verify 1. Verify message No record found");
        Assert.assertEquals(page.lblNoRecord.getText(), BOConstants.NO_RECORDS_FOUND, "FAILED! Can search the username tha have not log view");
        log("INFO: Executed completely");
    }


}
