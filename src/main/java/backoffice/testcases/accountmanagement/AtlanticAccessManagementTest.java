package backoffice.testcases.accountmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.accountmanagement.AtlanticAccessManagementPage;
import baseTest.BaseCaseTest;
import com.paltech.constant.Helper;
import com.paltech.utils.StringUtils;
import membersite.pages.HomePage;
import membersite.pages.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.MeritoConstant.MEMBER_CAMOUFLAGE_URL_SUFFIX;
import static common.MeritoConstant.MEMBER_URL_SUFFIX;

public class AtlanticAccessManagementTest extends BaseCaseTest {

    /**
     * @title: Validate can not add account to Atlantic Access Management that not belonging to Fairenter brand
     * @pre-condition: 2. Login BO
     * @steps: 1. Access Admin Management > Atlantic Access Management
     * 2. Input a player that not belonging to Fairenter and click Add button
     * @expect: 1. Verify message display "User [account] is not in Fairenter brand"
     */
    @TestRails(id = "622")
    @Test(groups = {"smoke"})
    @Parameters("satMemberLoginID")
    public void BO_Account_Management_Atlantic_Access_Management_622(String satMemberLoginID) {
        log("@title: Validate can not add account to Atlantic Access Management that not belonging to Fairenter brand");
        log("Step 1. Access Admin Management > Atlantic Access Management");
        AtlanticAccessManagementPage page = backofficeHomePage.navigateAtlanticAccessManagement();

        log("Step 2. Input a player that not belonging to Fairenter and click Add button");
        page.addPlayer(satMemberLoginID);
        AlertMessageBox messageBox = new AlertMessageBox();
        String actualMessage = messageBox.getErrorAlert();

        log("Verify 1. Verify message display \"User [account] is not in Fairenter brand\"");
        Assert.assertTrue(actualMessage.contains(String.format(BOConstants.AdminManagement.AtlanticAccessManagement.FAILED_MSG, satMemberLoginID)), "FAILED! Error message is incorrect when add the account not belong to Funsport brand");

        log("INFO: Executed completely");

    }

    /**
     * @title: Validate can account is added/deleted to the list can access/unaccess Atlantic site
     * @pre-condition: 1. Login BO
     * 2. Pick a agent and his play  belonging to Fairenter brand
     * @steps: 1. Access Admin Management > Atlantic Access Management
     * 2. Add a player
     * 3. Access Atlantic site and login with the account above
     * 4. Logout Atlantic site and relogin BO site and deleted the account
     * 5. Relogin Atlantic
     * @expect: 1. Verify message "Add user Atlantic success!" after add agent and agent is display in the list
     * 2. Can login Atlantic site
     * 3. Verify the message "Delete user Atlantic success!" and the agent is removed
     * 4. Verify cannot login Atlantic site when account is deleted form Atlantic Access Management page
     */
    @TestRails(id = "623")
    @Test(groups = {"regression"})
    @Parameters({"username", "password", "atlanticAccount", "memberPassword"})
    public void BO_Account_Management_Atlantic_Access_Management_623(String username, String password, String atlanticAccount, String memberPassword) throws Exception {
        // this case should run with skinName = atlantic
        _brandname = "fairenter";
        camouflageLoginURL = defineCamouFlageSiteURL(_brandname, MEMBER_CAMOUFLAGE_URL_SUFFIX.get("atlantic"));
        log("@title: Validate can account is added/deleted to the list can access/un-access Atlantic site");
        log("Step 1: Access Left menu > Atlantic Access Management");
        AtlanticAccessManagementPage atlanticPage = backofficeHomePage.navigateAtlanticAccessManagement();
        log("Step 2: Add a player");
        atlanticPage.addPlayer(atlanticAccount);
        log("Verify 1: Verify agent is display in the list");
        Assert.assertTrue(atlanticPage.isAccountInList(atlanticAccount), String.format("FAILED! The account %s not display in the list after adding", atlanticAccount));

        log("Step 3: Access Atlantic site and login with the account above");
        loginCamouflageSite(atlanticAccount, memberPassword, true);
        log("Verify 2: Can login Atlantic site");
        Assert.assertTrue(memberHomePage.isMyAccountDisplay(),String.format("FAILED! Login Atlantic site successfully when the account %s is added to the list",atlanticAccount));
        log("Step 4: Logout Atlantic site and relogin BO site and deleted the account");
        loginBackoffice(username, password, true);
        backofficeHomePage.navigateAtlanticAccessManagement();
        atlanticPage.removePlayer(atlanticAccount).confirm();
        log("Verify 3: Verify agent is NOT display in the list");
        Assert.assertFalse(atlanticPage.isAccountInList(atlanticAccount), String.format("FAILED! The account %s not display in the list after adding", atlanticAccount));
        log("Step 5: Re-login with the account above");
        loginCamouflageSite(atlanticAccount, memberPassword, true);
        log("Verify 4: Can NOT login Atlantic site");
        Assert.assertFalse(memberHomePage.isMyAccountDisplay(),String.format("FAILED! Cannot login Atlantic site when the account %s is added to the list",atlanticAccount));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can/cannot login Fairenter if account is non-display/display in Atlantic site
     * @pre-condition: 1. Login BO
     * 2. Pick a agent and his play belonging to Fairenter brand
     * @steps: 1. Access Admin Management > Atlantic Access Management
     * 2. Add a player
     * 3. Access FairEnter and login
     * 3. Access FairEnter and login
     * 4. Back to BO and deleted the account out Atlantic list
     * 5. Login Fairenter again
     * @expect: 1. Verify cannot login Fairenter if account is added in Atlantic list
     * 2. Can login Fairenter when the account is removed out Atlantice list
     */
    @TestRails(id = "624")
    @Test(groups = {"regression"})
    @Parameters({"username", "atlanticAccount", "password", "memberPassword"})
    public void BO_Account_Management_Atlantic_Access_Management_624(String username, String atlanticAccount, String password, String memberPassword) throws Exception {
        log("@title: Validate can/cannot login Fairenter if account is non-display/display in Atlantic site");
        log("Step 1. Access Admin Management > Atlantic Access Management");
        // this case should run with skinName = atlantic
        String decryptPassword = StringUtils.decrypt(password);
        _brandname = "fairenter";
        camouflageLoginURL = defineCamouFlageSiteURL(_brandname, MEMBER_CAMOUFLAGE_URL_SUFFIX.get("atlantic"));
        log("@title: Validate can account is added/deleted to the list can access/un-access Atlantic site");
        log("Step 1: Access Left menu > Atlantic Access Management");
        AtlanticAccessManagementPage atlanticPage = backofficeHomePage.navigateAtlanticAccessManagement();
        log("Step 2: Add a player");
        atlanticPage.addPlayer(atlanticAccount);
        log("Verify 1: Verify agent is display in the list");
        Assert.assertTrue(atlanticPage.isAccountInList(atlanticAccount), String.format("FAILED! The account %s not display in the list after adding", atlanticAccount));

        log("Step 3: Access Fairenter site and login with the account above");
        memberLoginURL = defineURL(_brandname, MEMBER_URL_SUFFIX.get(_brandname));
        createDriver(memberLoginURL);
        HomePage homePage = new HomePage(_brandname);
        homePage.login(atlanticAccount, decryptPassword, true);
        log("Verify 2: Verify cannot login Fairenter if account is added in Atlantic list");
        Assert.assertFalse(homePage.isMyAccountDisplay(),String.format("FAILED! Login successfully Fairenter when the account %s is added to the list",atlanticAccount));
        log("Step 4: Logout Atlantic site and relogin BO site and deleted the account");
        loginBackoffice(username, password, true);
        backofficeHomePage.navigateAtlanticAccessManagement();
        atlanticPage.removePlayer(atlanticAccount).confirm();
        log("Verify 3: Verify agent is NOT display in the list");
        Assert.assertFalse(atlanticPage.isAccountInList(atlanticAccount), String.format("FAILED! The account %s display in the list after adding", atlanticAccount));
        log("Step 5: Re-login Fairenter with the account above");
        createDriver(memberLoginURL);
        homePage.login(atlanticAccount, decryptPassword, true);
        log("Verify 4: Can login Fairenter when the account is removed out Atlantic list");
        Assert.assertTrue(homePage.isMyAccountDisplay(),String.format("FAILED! Can not Fairenter site when the account %s is added to the list",atlanticAccount));
        log("INFO: Executed completely");
    }
}
