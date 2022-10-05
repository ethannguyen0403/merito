package agentsite.testcase.satsport.marketsmanagement;


import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.marketsmanagement.LiquidityThresholdPage;

import static agentsite.common.AGConstant.HomePage.LIQUIDITY_THRESHOLD;
import static agentsite.common.AGConstant.HomePage.MARKET_MANAGEMENT;

public class LiquidityThresholdTest extends BaseCaseMerito {
    /**
     * @title: Validate there is no http responded error returned
     * @pre-condition: 1. Log in successfully from PO level
     * @steps: 1. Navigate Markets Management >Liquidity Threshold
     * @expect: 1. Verify there is no console error display
     */
    @Test(groups = {"http_request"})
    public void Agent_MM_Liquidity_Threshold_TC001() {
        log("@title: Validate there is no http responded error returned");

        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        agentHomePage.clickSubMenu(MARKET_MANAGEMENT, LIQUIDITY_THRESHOLD, LiquidityThresholdPage.class);

        log("Verify  1. Verify there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");

        log("INFO: Executed completely");
    }

    /**
     * @title: Can Active/Inactive Liquidity Threshold Status
     * @pre-condition:
     *           1. Log in successfully from PO level
     * @steps:   1. Navigate Markets Management >Liquidity Threshold
     *           2. Search a CO account and active threshold
     *           3. Click deactive liquidity threshold
     * @expect: 1. Can active Liquidity Threshold, the column Last Update Time, Last Update By, History is empty
     *          2. Can inactive Liquidity Threshold and show log in Last Update Time, Last Update By and View link
     */
    @Test(groups = {"smokePO"})
    @Parameters({"username","controlBlockingAccount"})
    public void Agent_MM_Liquidity_Threshold_TC005(String username,String controlBlockingAccount) {
        log("@title: Can Active/Inactive Liquidity Threshold Status");

        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        LiquidityThresholdPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, LIQUIDITY_THRESHOLD, LiquidityThresholdPage.class);

        log("Step 2. Search a CO account and active threshold");
     //   String companyUsername = page.getCompanyUsernamebyBreadcrum(controlBlockingAccount);
       // page.navigateBreadcrumb(username.substring(0,1));

        log("Step 3. Click deactive liquidity threshold");
        page.setLiquidityThreshold("SAM",true);

        log("Verify 1. Can active Liquidity Threshold, the column Last Update Time, Last Update By, History is empty");
        log("Verify 2. Can inactive Liquidity Threshold and show log in Last Update Time, Last Update By and View link");

        log("INFO: Executed completely");
    }


}
