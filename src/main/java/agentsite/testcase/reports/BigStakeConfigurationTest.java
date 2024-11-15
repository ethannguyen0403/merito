package agentsite.testcase.reports;

import agentsite.pages.report.BigStakeConfigurationPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class BigStakeConfigurationTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration
     * @expect: 1. There is no http responded error returned
     */
    @TestRails(id = "3761")
    @Test(groups = {"http_request"})
    public void Agent_Report_Big_Stake_Configuration_3761() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration ");
        BigStakeConfigurationPage page = agentHomePage.navigateBigStakeConfigurationPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Big Stake Configuration UI display correctly
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration
     * @expect: 1. Verify Big Stake Configuration UI display correctly
     */
    @TestRails(id = "813")
    @Test(groups = {"smoke_sat"})
    public void Agent_Report_Big_Stake_Configuration_813() {
        log("@title:Validate Big Stake Configuration UI display correctly");
        log("Step 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration");
        BigStakeConfigurationPage page = agentHomePage.navigateBigStakeConfigurationPage();

        log("Verify 1. Verify Big Stake Configuration UI display correctly");
        Assert.assertEquals(page.lblBigStake.getText(), AGConstant.Report.BigStakeConfiguration.LBL_BIG_STAKE, "FAILED! Big Stake label is incorrect");
        Assert.assertTrue(page.txtBigStake.isDisplayed(), "FAILED! Big Stake textbox is NOT display");
        Assert.assertEquals(page.btnSubmit.getText(), AGConstant.BTN_SUBMIT, "FAILED! Submit button display incorrect");
        Assert.assertEquals(page.tblBigStakeConfiguration.getHeaderNameOfRows(), AGConstant.Report.BigStakeConfiguration.TABLE_BIG_STAKE_HEADER, "FAILED! Incorrect header of big stake");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can configure big stake
     * @pre-condition: 1. Log in successfully by SAD
     * @steps: 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration
     * 2. Update Big stake = Min bet + 10
     * @expect: 1. Can update big stake successfully" Big stake configuration is saved successfully."
     */
    @TestRails(id = "814")
    @Test(groups = {"smoke_sat"})
    @Parameters("username")
    public void Agent_Report_Big_Stake_Configuration_814(String username) {
        log("@title:Validate can configure big stake ");
        log("Step 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration");
        int stake = 13;
        BigStakeConfigurationPage page = agentHomePage.navigateBigStakeConfigurationPage();

        log("Step 2. Update Big stake = 13");
        String currentDateTime = DateUtils.getDate(0, "dd/MMM/yyyy HH:mm", AGConstant.timeZone);
        try {
            String message = page.configureBigStake(String.valueOf(stake));

            log("Verify 1. Can update big stake successfully\" Big stake configuration is saved successfully.\"");
            Assert.assertEquals(message, AGConstant.Report.BigStakeConfiguration.LBL_SUCCESS_MESSAGE, "FAILED! Success message is incorrect");

            page.btnOK.click();
            List<ArrayList<String>> configuredData = page.tblBigStakeConfiguration.getRowsWithoutHeader(1, false);
            Assert.assertTrue(configuredData.get(0).get(0).contains(currentDateTime), "FAILED! configure date time not correctly");
            Assert.assertTrue(configuredData.get(0).get(1).contains(String.format("%,.2f", Double.valueOf(stake))), "FAILED! configure stake not correctly");
            Assert.assertTrue(configuredData.get(0).get(2).contains(username.toLowerCase()), "FAILED! configure username not correctly");
            log("INFO: Executed completely");
        } finally {
            log("PostCondition: Set big stake to 0");
            page.configureBigStake("0");
        }

    }

}
