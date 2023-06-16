package agentsite.testcase.reports;

import common.AGConstant;
import agentsite.pages.report.BigStakeConfigurationPage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BigStakeConfigurationTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void Agent_Report_Big_Stake_Configuration_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration ");
        BigStakeConfigurationPage page = agentHomePage.navigateBigStakeConfigurationPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Big Stake Configuration UI display correctly
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps:  1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration
     * @expect: 1. Verify Big Stake Configuration UI display correctly
     */
    @Test (groups = {"smoke"})
    public void  Agent_Report_Big_Stake_Configuration_002(){
        log("@title:Validate Big Stake Configuration UI display correctly");
        log("Step 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration");
        BigStakeConfigurationPage page = agentHomePage.navigateBigStakeConfigurationPage();

        log("Verify 1. Verify Big Stake Configuration UI display correctly");
        Assert.assertEquals(page.lblBigStake.getText(), AGConstant.Report.BigStakeConfiguration.LBL_BIG_STAKE,"FAILED! Big Stake label is incorrect");
        Assert.assertTrue(page.txtBigStake.isDisplayed(),"FAILED! Big Stake textbox is NOT display");
        Assert.assertEquals(page.btnSubmit.getAttribute("value"), AGConstant.BTN_SUBMIT,"FAILED! Submit button display incorrect");
        Assert.assertEquals(page.tblBigStakeConfiguration.getHeaderNameOfRows(), AGConstant.Report.BigStakeConfiguration.TABLE_BIG_STAKE_HEADER,"FAILED! Incorrect header of big stake");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can configure big stake
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps:  1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration
     *          2. Update Big stake = Min bet + 10
     * @expect: 1. Can update big stake successfully" Big stake configuration is saved successfully."
     */
    @Test (groups = {"smoke"})
    @Parameters("username")
    public void  Agent_Report_Big_Stake_Configuration_003(String username){
        log("@title:Validate can configure big stake ");
        log("Step 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration");
        BigStakeConfigurationPage page = agentHomePage.navigateBigStakeConfigurationPage();

        log("Step 2. Update Big stake = 13");
        String currentDateTime = DateUtils.getDate(0,"dd/MMM/yyyy HH:mm", AGConstant.timeZone);
        String message = page.configureBigStake("13");

        log("Verify 1. Can update big stake successfully\" Big stake configuration is saved successfully.\"");
        Assert.assertEquals(message, AGConstant.Report.BigStakeConfiguration.LBL_SUCCESS_MESSAGE,"FAILED! Success message is incorrect");

        page.btnOK.click();
        List<ArrayList<String>> configuredData =page.tblBigStakeConfiguration.getRowsWithoutHeader(1,false);
        Assert.assertTrue(configuredData.get(0).get(0).contains(currentDateTime),"FAILED! configure date time not correctlu");
        Assert.assertTrue(configuredData.get(0).get(1).contains("13.00"),"FAILED! configure date time not correctlu");
        Assert.assertTrue(configuredData.get(0).get(2).contains(username),"FAILED! configure date time not correctlu");

        log("PostCondition: Set big stake to 0");
        page.configureBigStake("0");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate can configure big stake
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps:  1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration
     *          2. Update Big stake = Min bet + 10
     * @expect: 1. Can update big stake successfully" Big stake configuration is saved successfully."
     */
    @Test (groups = {"smoke"})
    @Parameters("username")
    public void  Agent_Report_Big_Stake_Configuration_004(String username){
        log("@title:Validate can configure big stake ");
        log("Step 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration");
        BigStakeConfigurationPage page = agentHomePage.navigateBigStakeConfigurationPage();

        log("Step 2. Update Big stake = 13");
        String currentDateTime = DateUtils.getDate(0,"dd/MMM/yyyy HH:mm", AGConstant.timeZone);
        String message = page.configureBigStake("13");

        log("Verify 1. Can update big stake successfully\" Big stake configuration is saved successfully.\"");
        Assert.assertEquals(message, AGConstant.Report.BigStakeConfiguration.LBL_SUCCESS_MESSAGE,"FAILED! Success message is incorrect");

        page.btnOK.click();
        List<ArrayList<String>> configuredData =page.tblBigStakeConfiguration.getRowsWithoutHeader(1,false);
        Assert.assertTrue(configuredData.get(0).get(0).contains(currentDateTime),"FAILED! configure date time not correctlu");
        Assert.assertTrue(configuredData.get(0).get(1).contains("13.00"),"FAILED! configure date time not correctlu");
        Assert.assertTrue(configuredData.get(0).get(2).contains(username),"FAILED! configure date time not correctlu");

        log("PostCondition: Set big stake to 0");
        page.configureBigStake("0");
        log("INFO: Executed completely");
    }
    /**
     * @title: Validate can configure big stake
     * @pre-condition:
     *          1. Log in successfully by SAD
     * @steps:  1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration
     *          2. Update Big stake = Min bet + 10
     * @expect: 1. Can update big stake successfully" Big stake configuration is saved successfully."
     */
    @Test (groups = {"smoke"})
    @Parameters("username")
    public void  Agent_Report_Big_Stake_Configuration_005(String username){
        log("@title:Validate can configure big stake ");
        log("Step 1. Navigate Report > Top Gainers & Top Losers > Big Stake Configuration");
        BigStakeConfigurationPage page = agentHomePage.navigateBigStakeConfigurationPage();

        log("Step 2. Update Big stake = 13");
        String currentDateTime = DateUtils.getDate(0,"dd/MMM/yyyy HH:mm", AGConstant.timeZone);
        String message = page.configureBigStake("13");

        log("Verify 1. Can update big stake successfully\" Big stake configuration is saved successfully.\"");
        Assert.assertEquals(message, AGConstant.Report.BigStakeConfiguration.LBL_SUCCESS_MESSAGE,"FAILED! Success message is incorrect");

        page.btnOK.click();
        List<ArrayList<String>> configuredData =page.tblBigStakeConfiguration.getRowsWithoutHeader(1,false);
        Assert.assertTrue(configuredData.get(0).get(0).contains(currentDateTime),"FAILED! configure date time not correctlu");
        Assert.assertTrue(configuredData.get(0).get(1).contains("13.00"),"FAILED! configure date time not correctlu");
        Assert.assertTrue(configuredData.get(0).get(2).contains(username),"FAILED! configure date time not correctlu");

        log("PostCondition: Set big stake to 0");
        page.configureBigStake("0");
        log("INFO: Executed completely");
    }
}
