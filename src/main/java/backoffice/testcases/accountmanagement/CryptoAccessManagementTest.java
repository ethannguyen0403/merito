package backoffice.testcases.accountmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.accountmanagement.CryptoAccessManagementPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class CryptoAccessManagementTest extends BaseCaseTest {

    /**
     * @title: Validate can not add account to Crypto Access Management that not belonging to Funsport brand
     * @pre-condition: 2. Login BO
     * @steps: 1. Access Admin Management > Crypto Access Management
     * 2. Input a player that not belonging to Funsport and click Add button
     * @expect: 1. Verify message display "User [account] is not in Funsport brand"
     */
    @TestRails(id = "620")
    @Test(groups = {"smoke"})
    @Parameters("satMemberLoginID")
    public void BO_Account_Management_Crypto_Access_Management_001(String satMemberLoginID) {
        log("@title: Validate can not add account to Crypto Access Management that not belonging to Funsport brand");
        log("Step 1. Access Admin Management > Crypto Access Management");
        CryptoAccessManagementPage page = backofficeHomePage.navigateCryptoAccessManagement();

        log("Step 2. Input a player that not belonging to Funsport and click Add button");
        page.addPlayer(satMemberLoginID);
        AlertMessageBox messageBox = new AlertMessageBox();
        String actualMessage = messageBox.getErrorAlert();

        log("Verify 1. Verify message display \"User [account] is not in Funsport brand\"");
        Assert.assertTrue(actualMessage.contains(String.format(BOConstants.AdminManagement.CryptoAccessManagement.FAILED_MSG, satMemberLoginID)), "FAILED! Error message is incorrect when add the account not belong to Funsport brand");

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate account added into the list can login in crypto site
     * @pre-condition: 2. Login BO
     * @steps: 1. Access Admin Management > Crypto Access Management
     * 2. Get the account in the list with active status
     * 3. Access Crypto site and login
     * @expect: 1. Verify can login crypto site with the account in the list
     */
    @TestRails(id = "621")
    @Test(groups = {"smoke"})
    @Parameters({"cryptoAccount", "password"})
    public void BO_Account_Management_Crypto_Access_Management_002(String cryptoAccount, String password) throws Exception {
        log("@title: Validate account added into the list can login in crypto site");
        //TODO: implement this case
        Assert.assertTrue(false, "Need to implement this case");
        log("INFO: Executed completely");

    }
}
