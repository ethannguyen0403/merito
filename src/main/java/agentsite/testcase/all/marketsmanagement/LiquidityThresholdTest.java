package agentsite.testcase.all.marketsmanagement;

import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.components.ConfirmPopup;
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
    public void Agent_MM_Liquidity_Threshold_TC005(String controlBlockingAccount) {
        log("@title: Can Active/Inactive Liquidity Threshold Status");

        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        LiquidityThresholdPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, LIQUIDITY_THRESHOLD, LiquidityThresholdPage.class);

        log("Step 2. Search the account account and active threshold");
        page.search(controlBlockingAccount);

        log("Step 3. Click active liquidity threshold");
        page.setLiquidityThreshold(controlBlockingAccount,true);

        log("Verify 1. Liquidity is active");
        Assert.assertTrue(page.isLiquidityStatusAsExpected(controlBlockingAccount,true));

        log("Step 3. Deactive liquidity threshold");
        page.setLiquidityThreshold(controlBlockingAccount,false);

        log("Verify 2. Can inactive Liquidity Threshold and show log in Last Update Time, Last Update By and View link");
        Assert.assertTrue(page.isLiquidityStatusAsExpected(controlBlockingAccount,false));

        log("INFO: Executed completely");
    }

    /**
     * @title:  Can not active for downline when upline is inactive
     * @pre-condition:
     *           1. Log in successfully from PO level
     * @steps:   1. Navigate Markets Management >Liquidity Threshold
     *           2. Search an account that upline is inactive liquidity Threshold
     *           3. Click Active liquidity threshold
     * @expect: 1.  Can not active for downline when upline is inactive
     */
    @Test(groups = {"smokePO"})
    @Parameters({"controlBlockingAccount","downlineAccount"})
    public void Agent_MM_Liquidity_Threshold_TC008(String controlBlockingAccount,String downlineAccount) {
        log("@title: Can Active/Inactive Liquidity Threshold Status");
        log("Step 1. Navigate Markets Management >Liquidity Threshold");
        LiquidityThresholdPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, LIQUIDITY_THRESHOLD, LiquidityThresholdPage.class);

        log(String.format("Step 2. Search the account %S",controlBlockingAccount));
        page.search(controlBlockingAccount);

        log(String.format("Step 3. Click inactive liquidity threshold of %s",controlBlockingAccount));
        page.setLiquidityThreshold(controlBlockingAccount,false);

        log(String.format("Step 4 Search the downline %s of the account: %s",downlineAccount, controlBlockingAccount));
        page.search(downlineAccount);

        log(String.format("Step 5 Active liquidity threshold of %s",downlineAccount));
        page.setLiquidityThreshold(downlineAccount,true);

        log("Verify 1. Can not active for downline when upline is inactive");
        ConfirmPopup popup = new ConfirmPopup();
        Assert.assertEquals(popup.getContentMessage(),String.format("Upline %s of %s is OFF. Please help to ON it first.",controlBlockingAccount,downlineAccount),
                "FAILED! Error message not display when acitve downline has update inacive liquidity");

        log("INFO: Executed completely");
    }

}
