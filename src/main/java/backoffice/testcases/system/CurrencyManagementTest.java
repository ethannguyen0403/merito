package backoffice.testcases.system;

import backoffice.common.BOConstants;
import baseTest.BaseCaseMerito;
import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.system.CurrencyManagementPage;


public class CurrencyManagementTest extends BaseCaseMerito {

    /**
     * @title: Validate default UI in the page is correctly
     * @pre-condition:
     *           1. Log in BO
     * @steps:   1. Access Operations > Currency - Management
     * @expect: 1. Verify UI of Currency Management is correctly
     * - Save button disable by default
     * - Table header : From currency, Target Currency, Currency Rate, Pending Rate, Note
     */
    @Test (groups = {"smoke"})
    public void BO_System_Currency_Management_001(){
        log("@title: Validate default UI in the page is correctly");
        log("Step 1. Access Operations > Currency - Management");
        CurrencyManagementPage page = backofficeHomePage.navigateCurrencyManagement();

        log("Verify 1. Verify UI of Currency Management is correctly" +
                "- Save button disable by default");
        Assert.assertFalse(page.btnSave.isEnabled(),"FAILED! Save button is disabled by default");

        log("Verify - Table header : From currency, Target Currency, Currency Rate, Pending Rate, Note");
        Assert.assertEquals(page.tblCurrencyManagement.getColumnNamesOfTable(),BOConstants.Operations.CurrencyManagement.TABLE_HEADER,"FAILED! Table header does not display as expectation");
        log("INFO: Executed completely");
    }
}
