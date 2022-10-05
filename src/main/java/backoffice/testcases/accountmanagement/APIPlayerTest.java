package backoffice.testcases.accountmanagement;

import baseTest.BaseCaseMerito;
import com.paltech.utils.StringUtils;
import backoffice.common.BOConstants;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.accountmanagement.APIPlayerPage;
import backoffice.utils.tools.APIPlayerUtils;

import java.util.List;

public class APIPlayerTest extends BaseCaseMerito {

    /**
     * @title: Validate can add and delete player into the list
     * @pre-condition:
     *          1.Login BO
     * @steps:  1. Access Tool > API Player
     *          2. Input any player into the textbox and click add button
     *          3. Click on delete icon and confirm to delete
     * @expect: 1. Verify the message "User [loginId] has been added successful" and the player is added into the list
     *          2. Verify the message "User [loginId] has been removed successful" and the player is removed out the list
     */
    @Test (groups = {"smoke"})
    @Parameters({"satMemberLoginID"})
    public void   BO_Tools_API_Player_001(String satMemberLoginID){
        log("@title: Validate can add and delete player into the list");
        log("Step 1. Access Tool > API Player");
        APIPlayerPage page = backofficeHomePage.navigateAPIPlayer();

        log("Step 2. Input any player into the textbox and click add button");
        page.addAPIPlayer(satMemberLoginID);
        AlertMessageBox msgBox = new AlertMessageBox();
        String successMessage = msgBox.getSuccessAlert();

        log("Verify 1. Verify the message \"User [loginId] has been added successful\" and the player is added into the list");
        Assert.assertTrue(successMessage.contains(String.format(BOConstants.Tools.APIPlayer.MSG_SUCCESS,satMemberLoginID,"added")),"FAILED! Success message when add api player is incorrect");
        Assert.assertTrue(page.isPlayerExist(satMemberLoginID),"FAILED! The added account not display in the list");

        log("Step 3. Click on delete icon and confirm to delete");
        AppConfirmPopup popup =  page.removeAPIPlayer(satMemberLoginID);
        popup.confirm();
        successMessage = msgBox.getSuccessAlert();

        log("Verify 2. Verify the message \"User [loginId] has been removed successful\" and the player is removed out the list");
        Assert.assertTrue(successMessage.contains(String.format(BOConstants.Tools.APIPlayer.MSG_SUCCESS,satMemberLoginID,"removed")),"Success message when remove api player is incorrect");
        Assert.assertFalse(page.isPlayerExist(satMemberLoginID),"FAILED! The added account not display in the list");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate only account added into API Player list can login api
     * @pre-condition:
     *          1.Login BO
     * @steps:1. Access Tool > API Player
     * 2. Input any player into the textbox and click add button
     * 3. Login to API with the added account
     * 4. Remove the account from API list
     * 5. Re-login api
     * @expect: 1. Verify can login api when account is added
     *          2. Verify cannot login api when account is removed
     **/
    @Test (groups = {"smoke"})
    @Parameters({"satMemberLoginID","memberPassword"})
    public void BO_Tools_API_Player_003(String satMemberLoginID, String memberPassword) throws Exception {
        log("@title: Validate only account added into API Player list can login api");
        log("Step Access Tool > API Player");
        String passDecrypt = StringUtils.decrypt(memberPassword);
        APIPlayerPage page = backofficeHomePage.navigateAPIPlayer();

        log("Step 2. Input any player into the textbox and click add button");
        page.addAPIPlayer(satMemberLoginID);

        log("Step 3. Login to API with the added account");
        page.btnAdd.waitForControlInvisible(1,1);
        List<String> result = APIPlayerUtils.loginAPI(environment.getApiURL(),satMemberLoginID,passDecrypt);

        log("Verify 1. Verify can login api when account is added");
        Assert.assertEquals(result.get(0),"true","FAILED! Cannot login api with the added account");

        log("Step 4. Remove the account from API list");
        AppConfirmPopup popup =  page.removeAPIPlayer(satMemberLoginID);
        popup.confirm();
        popup.isInvisible(3);

        log("Step 5. Re-login api");
        page.btnAdd.waitForControlInvisible(1,1);
        result = APIPlayerUtils.loginAPI(environment.getApiURL(),satMemberLoginID,passDecrypt);

        log("Verify 2. Verify cannot login api when account is removed");
        Assert.assertEquals(result.get(0),"false","FAILED! Can login api with the removed account");

        log("INFO: Executed completely");
    }
}
