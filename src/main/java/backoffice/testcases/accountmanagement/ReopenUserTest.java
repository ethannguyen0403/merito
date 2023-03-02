package backoffice.testcases.accountmanagement;

import agentsite.common.AGConstant;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import baseTest.BaseCaseMerito;
import com.paltech.driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.accountmanagement.ReopenUserPage;
import util.testraildemo.TestRails;

import java.util.List;

import static common.MeritoConstant.AGENT_SECURITY_CODE_URL_SUFFIX;
import static common.MeritoConstant.AGENT_SOS_URL_SUFFIX;

public class ReopenUserTest extends BaseCaseMerito {
    /**
     * @title: Validate can reopen user
     * @pre-condition:
     *           1. Get the account is closed in agent site
     *           2. Login BO
     * @steps: 1. Access Member Management >Reopen User
     *          2. Input the account in precondition and click Search
     *          3. Click Active
     *          4. Login agent site > downline listing > Search the account and check status
     * @expect: 1. Verify status change Close to Active after clicking on Active button
     *          2. Button Active is disable
     *          3. Account is active in agent site
     */
    @TestRails(id = "617")
    @Test (groups = {"smoke11"})
    @Parameters({"satSADAgentLoginID","memberPassword","username","password","brandname"})
    public void BO_MM_Reopen_User_001(String satSADAgentLoginID,String memberPassword,String username, String password,String brandname) throws Exception {
        log("@title:  Validate can reopen user");
backofficeHomePage.logout();

        String brand = "satsport";
        agentLoginURL = defineURL(brand,"/agent");
        sosAgentURL = defineURL(brand,AGENT_SOS_URL_SUFFIX);
        agentSecurityCodeURL =defineURL(brand, AGENT_SECURITY_CODE_URL_SUFFIX.get(brand));
        DriverManager.getDriver().get(agentLoginURL);
        agentHomePage = loginAgent(sosAgentURL,agentSecurityCodeURL,satSADAgentLoginID,memberPassword,environment.getSecurityCode());
        DownLineListingPage downLineListingPage =agentHomePage.clickSubMenu(AGConstant.HomePage.AGENCY_MANAGEMENT,AGConstant.HomePage.DOWNLINE_LISTING,DownLineListingPage.class);
        downLineListingPage.searchDownline("","Closed","All");
        String closeAccount = downLineListingPage.tblDowlineListing.getColumn(downLineListingPage.loginIDCol,false).get(0);

        log("Step 1. Access Member Management > Reopen User");
        loginBackoffice(username,password,true);
        ReopenUserPage page = backofficeHomePage.navigateReopenUser();

        log("Step 2. Input the account in precondition and click Search");
        page.search( closeAccount);

        log("Step 3. Click Active");
        page.activeCloseAccount(closeAccount,page.colUserCode);

        log("Verify 1. Verify status change Close to Active after clicking on Active button");
        AlertMessageBox alertMessage = new AlertMessageBox();
        String successMessage = alertMessage.getSuccessAlert();
        Assert.assertEquals(successMessage,"Update Success","FAILED! Success message is incorrect after active closed account");
        Assert.assertTrue(page.verifyStatusAccount(closeAccount,page.colUserCode,"ACTIVE"));

        log("Verify 2. Button Active is disable");
        Assert.assertTrue(page.verifyActionButtonIsDisable(closeAccount,page.colUserCode),"FAILED! The button of active account should be disable");
        page.logout();

        log("Step 4. Login agent site > downline listing > Search the account and check status");
        agentHomePage = loginAgent(sosAgentURL,agentSecurityCodeURL,username,password,environment.getSecurityCode());
        downLineListingPage = agentHomePage.clickSubMenu(AGConstant.HomePage.AGENCY_MANAGEMENT,AGConstant.HomePage.DOWNLINE_LISTING,DownLineListingPage.class);
        downLineListingPage.searchDownline(closeAccount,"Active","All");

         log("Step 3. Account is active in agent site");
        List<String> lstRecord = downLineListingPage.tblDowlineListing.getColumn(downLineListingPage.loginIDCol,false);
        Assert.assertEquals(lstRecord.get(0),closeAccount,String.format("Failed! Expected login id %s display but found %s",closeAccount,lstRecord.get(0)));

        log("INFO: Executed completely");
    }


}
