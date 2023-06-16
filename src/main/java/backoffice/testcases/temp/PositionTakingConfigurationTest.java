package backoffice.testcases.temp;

import backoffice.common.BOConstants;
import backoffice.pages.bo.temp.PositionTakingConfigurationPage;
import backoffice.utils.tools.PositionTakingConfigurationUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PositionTakingConfigurationTest extends BaseCaseTest {

    /**
     * @title: Validate UI display correctly
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool >Position Taking Configuration
     * 2. Validate UI
     * @expect: 1. Verify UI is correctly display
     * Username/login ID textbox, Search button
     * Hint message : "This page is to display the accounts whose downlines are disabled PT. The Downline PT Enabled value is not editable"
     * 2. Table header: No., User Name, Login ID, Level, Account Status, Downline PT Enabled, Brand, Line, Update By, Update Date
     */
    @Test(groups = {"smoke"})
    public void BO_Tools_Position_Taking_Configuration_001() {
        log("@title: Validate UI display correctly");
        log("Step 1. Access Tool >Position Taking Configuration");
        PositionTakingConfigurationPage page = backofficeHomePage.navigatePositionTakingConfiguration();

        log("Step 2. Validate UI");
        log("Verify  1. Verify UI is correctly display\n" +
                "     *          Username/login ID textbox, Search button \n" +
                "     *          Hint message : \"This page is to display the accounts whose downlines are disabled PT. The Downline PT Enabled value is not editable\"");
        Assert.assertEquals(page.txtUserName.getAttribute("placeholder"), BOConstants.Tools.PositionTakingConfiguration.TXT_USERNAME_PLACEHOLDER, "FAILED! Place holder of username textbox is incorrect");
        Assert.assertEquals(page.btnSearch.getText(), BOConstants.Tools.PositionTakingConfiguration.BTN_SEARCH, "FAILED! Search button text is incorrect");
        Assert.assertEquals(page.lblNote.getText(), BOConstants.Tools.PositionTakingConfiguration.LBL_NOTE, "FAILED! Note message is incorrect");

        log("Verify 2. Table header: No., User Name, Login ID, Level, Account Status, Downline PT Enabled, Brand, Line, Update By, Update Date");
        List<String> lst = page.tblAccountPTInfo.getColumnNamesOfTable();
        Assert.assertEquals(lst, BOConstants.Tools.PositionTakingConfiguration.TBL_HEADER, "FAILED! Table header is incorrectly");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search account
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool >Position Taking Configuration
     * 2. Input the user exist in the list and click search button
     * @expect: 1. Verify the account is display in the list
     **/
    @Test(groups = {"smoke"})
    public void BO_Tools_Position_Taking_Configuration_002() {
        log("@title: Validate can search account");
        log("Step 1. Access Tool >Position Taking Configuration");
        List<ArrayList<String>> lstAccountPT = PositionTakingConfigurationUtils.getListPTAccount();
        PositionTakingConfigurationPage page = backofficeHomePage.navigatePositionTakingConfiguration();
        String username = lstAccountPT.get(0).get(page.colUserName - 1);

        log("Step 2. Input the user exist in the list and click search button");
        page.search(username);

        log("Verify 1. Verify the account is display in the list");
        Assert.assertTrue(page.isAccountExist(username), "FAILED! Usermane not display when searching");

        log("INFO: Executed completely");
    }
}
