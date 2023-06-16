package backoffice.testcases.system;

import backoffice.common.BOConstants;
import backoffice.pages.bo.system.BetFairAccountInfoPage;
import backoffice.pages.bo.system.productmaintenance.BetFairAccountChangeLogPopup;
import backoffice.utils.tools.BetFairAccountInfoUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class BetFairAccountInfoTest extends BaseCaseTest {

    /**
     * @title: Validate Betfair account info is display correctly
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > BetFair Account Info
     * @expect: 1.  Verify header table is correctly
     * 2.Verify has data of BetFair account
     */
    @TestRails(id = "648")
    @Test(groups = {"smoke"})
    public void BO_System_BetFair_Account_Info_001() {
        log("@title: Validate Betfair account info is display correctly");
        log("Step 1. Access Tool > BetFair Account Info");
        BetFairAccountInfoPage page = backofficeHomePage.navigateBetFairAccountInfo();
        page.tblBFAccount.isDisplayed();
        List<ArrayList<String>> lstBFAccount = BetFairAccountInfoUtils.getBetFairAccount();

        log("Verify 1. Verify header table is correctly");
        List<String> lstHeader = page.tblBFAccount.getHeaderNameOfRows();

        Assert.assertEquals(lstHeader, BOConstants.Tools.BetFairAccountInfo.LST_HEADER, "FAILED! Header table not display as expected");
        log("Verify 2. Verify has data of BetFair account");
        Assert.assertEquals(page.tblBFAccount.getColumn(2, false).get(0), lstBFAccount.get(0).get(1), "FAILED! The BF account not display correctly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can view Log of exchange and exchange game
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > BetFair Account Info
     * 2. Click on View under Change Log Exchange column
     * 3. Click on View under Change Log Exchange Game column
     * @expect: 1. Verify can open Log for exchange and exchange Game product.
     * 2. Available Balance, Current exposure match with the latest row in Change Log popup
     **/
    @TestRails(id = "649")
    @Test(groups = {"smoke"})
    public void BO_System_BetFair_Account_Info_002() {
        log("@title: Validate can view Log of exchange and exchange game");
        log("Step 1. Access Tool > BetFair Account Info");
        BetFairAccountInfoPage page = backofficeHomePage.navigateBetFairAccountInfo();

        log("Step 2. Click on View under Change Log Exchange column");
        List<ArrayList<String>> bfInfo = page.tblBFAccount.getRowsWithoutHeader(1, false);
        String bfAccount = bfInfo.get(0).get(page.colAccountName - 1);
        String exAvailableBalance = bfInfo.get(0).get(page.colEXAvailableBalance - 1);
        String exCurrentExposure = bfInfo.get(0).get(page.colEXCurrentExposure - 1);
        String egAvailableBalance = bfInfo.get(0).get(page.colEGAvailableBalance - 1);
        String egCurrentExposure = bfInfo.get(0).get(page.colEGCurrentExposure - 1);
        BetFairAccountChangeLogPopup popup = page.openViewLog(bfAccount, "EXCHANGE");

        log("Verify 1. Verify can open Log for exchange and Available Balance, Current exposure match with the latest row in Change Log popup ");
        List<ArrayList<String>> balanceInfo = popup.tblBalance.getRowsWithoutHeader(1, false);
        if (balanceInfo.get(0).get(0).equalsIgnoreCase(BOConstants.NO_RECORDS_FOUND)) {
            Assert.assertTrue(popup.lblNoRecord.getText().equalsIgnoreCase(BOConstants.NO_RECORDS_FOUND), "FAILED! No record message is incorrect");
        } else {
            Assert.assertEquals(balanceInfo.get(0).get(1), exCurrentExposure, "FAILED! Exchange Exposure display not correct");
            Assert.assertEquals(balanceInfo.get(0).get(2), exAvailableBalance, "FAILED! Exchange Available Balance is incorrect");
        }
        popup.btnClose.click();

        log("Step 3. Click on View under Change Log Exchange Game column");
        popup = page.openViewLog(bfAccount, "EXCHANGE GAME");

        log("Verify 1. Verify can open Log for exchange Game and  Available Balance, Current exposure match with the latest row in Change Log popup ");
        balanceInfo = popup.tblBalance.getRowsWithoutHeader(1, false);
        if (balanceInfo.get(0).get(0).equalsIgnoreCase(BOConstants.NO_RECORDS_FOUND)) {
            Assert.assertTrue(popup.lblNoRecord.getText().trim().equalsIgnoreCase(BOConstants.NO_RECORDS_FOUND), "FAILED! No record message is incorrect");
        } else {
            Assert.assertEquals(balanceInfo.get(0).get(1), String.format("%.2f", Double.parseDouble(egCurrentExposure)), "FAILED! Exchange Game Exposure display not correct");
            Assert.assertEquals(balanceInfo.get(0).get(2), egAvailableBalance, "FAILED! Exchange Game Available Balance is incorrect");
        }

        log("INFO: Executed completely");
    }
}
