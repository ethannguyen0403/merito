package backoffice.testcases.accountmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.accountmanagement.APIPlayerPage;
import backoffice.utils.tools.APIPlayerUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class APIPlayerTest extends BaseCaseTest {

    /**
     * @title: Validate can add and delete player into the list
     * @pre-condition: 1.Login BO
     * @steps: 1. Access Tool > API Player
     * 2. Input any player into the textbox and click add button
     * 3. Click on delete icon and confirm to delete
     * @expect: 1. Verify the message "User [loginId] has been added successful" and the player is added into the list
     * 2. Verify the message "User [loginId] has been removed successful" and the player is removed out the list
     */
    @TestRails(id = "618")
    @Test(groups = {"smoke","Maintenance.2024.V.6.0"})
    @Parameters({"satMemberLoginID"})
    public void BO_Tools_API_Player_618(String satMemberLoginID) {
        log("@title: Validate can add and delete player into the list");
        log("Step 1. Access Tool > API Player");
        APIPlayerPage page = backofficeHomePage.navigateAPIPlayer();

        log("Step 2. Input any player into the textbox and click add button");
        page.addAPIPlayer(satMemberLoginID);
        AlertMessageBox msgBox = new AlertMessageBox();
        String successMessage = msgBox.getSuccessAlert();

        log("Verify 1. Verify the message \"User [loginId] has been added successful\" and the player is added into the list");
        Assert.assertEquals(successMessage,String.format(BOConstants.Tools.APIPlayer.MSG_ADDED, satMemberLoginID), "FAILED! Success message when add api player is incorrect");
        Assert.assertTrue(page.isPlayerExist(satMemberLoginID), "FAILED! The added account not display in the list");

        log("Step 3. Click on delete icon and confirm to delete");
        AppConfirmPopup popup = page.removeAPIPlayer(satMemberLoginID);
        popup.confirm();
        successMessage = msgBox.getSuccessAlert();

        log("Verify 2. Verify the message \"User [loginId] has been removed successful\" and the player is removed out the list");
        Assert.assertEquals(successMessage,String.format(BOConstants.Tools.APIPlayer.MSG_REMOVED, satMemberLoginID), "Success message when remove api player is incorrect");
        Assert.assertFalse(page.isPlayerExist(satMemberLoginID), "FAILED! The added account not display in the list");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate only account added into API Player list can login api
     * @pre-condition: 1.Login BO
     * @steps:1. Access Tool > API Player
     * 2. Input any player into the textbox and click add button
     * 3. Login to API with the added account
     * 4. Remove the account from API list
     * 5. Re-login api
     * @expect: 1. Verify can login api when account is added
     * 2. Verify cannot login api when account is removed
     **/
    @TestRails(id = "619")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.4.0"})
    @Parameters({"satMemberLoginID", "memberPassword"})
    public void BO_Tools_API_Player_619(String satMemberLoginID, String memberPassword) throws Exception {
        log("@title: Validate only account added into API Player list can login api");
        log("Step Access Tool > API Player");
        String passDecrypt = StringUtils.decrypt(memberPassword);
        APIPlayerPage page = backofficeHomePage.navigateAPIPlayer();

        log("Step 2. Input any player into the textbox and click add button");
        page.addAPIPlayer(satMemberLoginID);

        log("Step 3. Login to API with the added account");
        page.btnAdd.waitForControlInvisible(1, 1);
        List<String> result = APIPlayerUtils.loginAPI(environment.getApiURL(), satMemberLoginID, passDecrypt);

        log("Verify 1. Verify can login api when account is added");
        Assert.assertEquals(result.get(0), "true", "FAILED! Cannot login api with the added account");

        log("Step 4. Remove the account from API list");
        AppConfirmPopup popup = page.removeAPIPlayer(satMemberLoginID);
        popup.confirm();

        log("Step 5. Re-login api");
        page.btnAdd.waitForControlInvisible(1, 2);
        result = APIPlayerUtils.loginAPI(environment.getApiURL(), satMemberLoginID, passDecrypt);

        log("Verify 2. Verify cannot login api when account is removed");
        Assert.assertEquals(result.get(0), "false", "FAILED! Can login api with the removed account");

        log("INFO: Executed completely");
    }
}
