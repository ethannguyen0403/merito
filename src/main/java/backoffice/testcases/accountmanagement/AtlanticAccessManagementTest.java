package backoffice.testcases.accountmanagement;

import baseTest.BaseCaseMerito;
import com.paltech.constant.Helper;
import com.paltech.utils.StringUtils;
import backoffice.common.BOConstants;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.accountmanagement.AtlanticAccessManagementPage;

public class AtlanticAccessManagementTest extends BaseCaseMerito {

    /**
     * @title: Validate can not add account to Atlantic Access Management that not belonging to Fairenter brand
     * @pre-condition:
     *          2. Login BO
     * @steps:  1. Access Admin Management > Atlantic Access Management
     *          2. Input a player that not belonging to Fairenter and click Add button
     * @expect: 1. Verify message display "User [account] is not in Fairenter brand"
     */
    @Test (groups = {"smoke"})
    @Parameters("satMemberLoginID")
    public void BO_Account_Management_Atlantic_Access_Management_001(String satMemberLoginID){
        log("@title: Validate can not add account to Atlantic Access Management that not belonging to Fairenter brand");
        log("Step 1. Access Admin Management > Atlantic Access Management");
        AtlanticAccessManagementPage page = backofficeHomePage.navigateAtlanticAccessManagement();

        log("Step 2. Input a player that not belonging to Fairenter and click Add button");
        page.addPlayer(satMemberLoginID);
        AlertMessageBox messageBox = new AlertMessageBox();
        String actualMessage = messageBox.getErrorAlert();

        log("Verify 1. Verify message display \"User [account] is not in Fairenter brand\"");
        Assert.assertTrue(actualMessage.contains(String.format(BOConstants.AdminManagement.AtlanticAccessManagement.FAILED_MSG,satMemberLoginID)),"FAILED! Error message is incorrect when add the account not belong to Funsport brand");

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate can account is added/deleted to the list can access/unaccess Atlantic site
     * @pre-condition:
     *          1. Login BO
     *          2. Pick a agent and his play  belonging to Fairenter brand
     * @steps: 1. Access Admin Management > Atlantic Access Management
     *          2. Add a player
     *          3. Access Atlantic site and login with the account above
     *          4. Logout Atlantic site and relogin BO site and deleted the account
     *          5. Relogin Atlantic
     * @expect: 1. Verify message "Add user Atlantic success!" after add agent and agent is display in the list
     *          2. Can login Atlantic site
     *          3. Verify the message "Delete user Atlantic success!" and the agent is removed
     *          4. Verify cannot login Atlantic site when account is deleted form Atlantic Access Management page
     */
    @Test (groups = {"smoke"})
    @Parameters({"username","password","atlanticAccount","memberPassword"})
    public void BO_Account_Management_Atlantic_Access_Management_003(String username, String password, String atlanticAccount, String memberPassword) throws Exception {
        log("@title: Validate can account is added/deleted to the list can access/un-access Atlantic site");
        log("Step 1. Access Admin Management > Atlantic Access Management");
        String passDecrypt = StringUtils.decrypt(memberPassword);
        AtlanticAccessManagementPage page = backofficeHomePage.navigateAtlanticAccessManagement();

        log("Step 2. Add a player");
        page.addPlayer(atlanticAccount);
        AlertMessageBox msgBox = new AlertMessageBox();
        String actualMsg = msgBox.getSuccessAlert();

        log("Verify 1. Verify message \"Add user Atlantic success!\" after add agent and agent is display in the list");
        Assert.assertTrue(actualMsg.contains("Add user atlantic success!"),"FAILED! Success message after add Atlantic user is incorrect");
        Assert.assertTrue(page.isAccountInList(atlanticAccount),String.format("FAILED! The account %s not display in the list after adding",atlanticAccount));

     /*   log("Step 3. Access Atlantic site and login with the account above");
        Helper.loginFairExchange(environment.getAtlanticSOSURL(),environment.getAtlanticDashboardURL(),atlanticAccount,memberPassword,true);
        pages.fairexchange.tabexchange.backofficeHomePage atlanticbackofficeHomePage = new pages.fairexchange.tabexchange.backofficeHomePage();

        log("Verify 2. Can login Atlantic site");
        Assert.assertTrue(atlanticbackofficeHomePage.ddbMyAccount.isDisplayed(),String.format("FAILED! Cannot login Atlantic site when the account %s is added to the list",atlanticAccount));

        log("Step 4. Logout Atlantic site and re-login BO site and deleted the account");
        Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);
        page = backofficeHomePage.navigateAtlanticAccessManagement();*/
        page.removePlayer(atlanticAccount).confirm();
        msgBox = new AlertMessageBox();
        actualMsg = msgBox.getSuccessAlert();

        log("Verify 3. Verify the message \"Delete user Atlantic success!\" and the agent is removed");
        Assert.assertTrue(actualMsg.contains("Delete user atlantic success!"),"FAILED! Success message after delete Atlantic user is incorrect");
        Assert.assertFalse(page.isAccountInList(atlanticAccount),String.format("FAILED! The account %s display in the list after removing",atlanticAccount));

       /* log("Step 5. Relogin Atlantic");
        Helper.loginFairExchange(environment.getAtlanticSOSURL(),environment.getAtlanticDashboardURL(),atlanticAccount,memberPassword,false);
        pages.atlantic.backofficeHomePage atlanticLoginPage = new pages.atlantic.backofficeHomePage();

        log("Verify 4. Verify cannot login Atlantic site when account is deleted form Atlantic Access Management page");
        Assert.assertTrue(atlanticLoginPage.lnkConservationOfTuna.isDisplayed(),String.format("FAILED! Can login Atlantic site when the account %s is removed to the list",atlanticAccount));*/
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can/cannot login Fairenter if account is non-display/display in Atlantic site
     * @pre-condition:
     *          1. Login BO
     *          2. Pick a agent and his play belonging to Fairenter brand
     * @steps:  1. Access Admin Management > Atlantic Access Management
     *          2. Add a player
     *          3. Access FairEnter and login
     *          3. Access FairEnter and login
     *          4. Back to BO and deleted the account out Atlantic list
     *          5. Login Fairenter again
     * @expect: 1. Verify cannot login Fairenter if account is added in Atlantic list
     *          2. Can login Fairenter when the account is removed out Atlantice list
     */
    @Test (groups = {"smoke"})
    @Parameters({"username","atlanticAccount","password","memberPassword"})
    public void BO_Account_Management_Atlantic_Access_Management_004(String username,String atlanticAccount, String password,String memberPassword) throws Exception {
        log("@title: Validate can/cannot login Fairenter if account is non-display/display in Atlantic site");
        log("Step 1. Access Admin Management > Atlantic Access Management");
        AtlanticAccessManagementPage page = backofficeHomePage.navigateAtlanticAccessManagement();

        log("Step 2. Add a player");
        page.addPlayer(atlanticAccount);
        page.isAccountInList(atlanticAccount);

      /*  log("Step 3. Access FairEnter and login");
        page.logout();
        DriverManager.getDriver().get(environment.getFairenterDashboardURL());
        //Helper.loginFairExchange(environment.getFairenterSOSURL(),environment.getFairenterDashboardURL(),atlanticAccount,memberPassword,false);

        UnderageGamblingPopup underGamblingPopup = new UnderageGamblingPopup();
        LoginPopup loginPopup = underGamblingPopup.clickConfirmation();
        loginPopup.login(username,password);
        log("Verify 1. Verify cannot login Fairenter if account is added in Atlantic list");
        Assert.assertTrue(loginPopup.btnLogin.isDisplayed(),String.format("FAILED! Can login Fairenter site when the account %s is added to Atlantic list",atlanticAccount));
*/
        log("Step 4. Back to BO and deleted the account out Atlantic list ");
        Helper.loginBOIgnoreCaptcha(backofficeSOSUrl, backofficeDashboardUrl,username,password,true);
        page = backofficeHomePage.navigateAtlanticAccessManagement();
        page.removePlayer(atlanticAccount).confirm();
      /*  page.logout();

        log("Step 5. Login Fairenter again");
        DriverManager.getDriver().get(environment.getFairenterDashboardURL());*/
        //Helper.loginFairExchange(environment.getFairenterSOSURL(),environment.getFairenterDashboardURL(),atlanticAccount,memberPassword,false);
/*
        underGamblingPopup = new UnderageGamblingPopup();
        loginPopup = underGamblingPopup.clickConfirmation();
        pages.all.tabexchange.backofficeHomePage memberbackofficeHomePage = loginPopup.login(username,password);*/
       /* Helper.loginFairExchange(environment.getFairenterSOSURL(),environment.getFairenterDashboardURL(),atlanticAccount,memberPassword,true);
        pages.fairexchange.tabexchange.backofficeHomePage fairenterbackofficeHomePage = new pages.fairexchange.tabexchange.backofficeHomePage();*/
/*
        log("Verify 2. Can login Fairenter when the account is removed out Atlantice list");
        Assert.assertTrue(memberbackofficeHomePage.iconHome.isDisplayed(),String.format("FAILED! Cannot login Fairenter site when the account %s is removed to the list",atlanticAccount));*/

        log("INFO: Executed completely");

    }
}
