package backoffice.testcases.system;

import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo._components.ConfirmPopup;
import backoffice.pages.bo.system.FollowBetToThirdPartyPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class FollowBetToThirdPartyTest extends BaseCaseTest {

    /**
     * @title: Validate can add/ remove the player it hi is added in to Group/Player in follow bet in agent site
     * @pre-condition: 1. Login agent site > Follow bet > Add a player into Group or Player
     * 2. Login BO
     * @steps: 1. Access Operations > Follow Bets to 3rd Party
     * 2. Input any account in precondition and click add
     * 3. Select the delete action according the account added in step 2. Confirm to remove user
     * @expect: 1. Add user success
     * 2. Confirm remove user is display and  message "Remove user success" display after that then account is removed out the list
     */
    @TestRails(id = "647")
    @Test(groups = {"smoke1"})
    @Parameters({"followBetAccount"})
    public void BO_System_Follow_Bet_To_Third_Party_002(String followBetAccount) {
        log("@title: Validate can add/ remove the player it hi is added in to Group/Player in follow bet in agent site");
        log("Step 1. Access System > Follow Bets to 3rd Party");
        String player = followBetAccount;
        FollowBetToThirdPartyPage page = backofficeHomePage.navigateFollowBetToThirdParty();

        log("Step 2. Input any account in precondition and click add");
        if (page.isPlayerExist(player)) {
            log("Step 2.1 Remove the account before added");
            page.removePlayer(player);
            ConfirmPopup popup = new ConfirmPopup();
            popup.confirm();
            page.waitSpinIcon();
        }
        page.addPlayer(player);
        AlertMessageBox message = new AlertMessageBox();
        String actualAddMsg = message.getSuccessAlert();
        page.waitSpinIcon();

        log("Verify 1. Add user success");
        Assert.assertTrue(actualAddMsg.contains("Add user success"), "FAILED! Add user success message does not display correctly");

        log("Step 3. Select the delete action according the account added in step 2. Confirm to remove user");
        page.removePlayer(player);
        ConfirmPopup popup = new ConfirmPopup();
        String messageConfirm = popup.getContent();
        popup.confirm();
        String actualRemoveMessage = message.getSuccessAlert();
        page.waitSpinIcon();

        log("Verify 2. Confirm remove user is display and  message \"Remove user success\" display after that then account is removed out the list");
        Assert.assertEquals(messageConfirm, String.format("Are you sure to remove user %s?", player), "FAILED! Confirm remove user is displayed not correctly");
        Assert.assertTrue(actualRemoveMessage.contains("Remove user success"), "FAILED! Add user success message does not display correctly");
        Assert.assertFalse(page.isPlayerExist(player), "FAILED! The remove player still display in the list");

        log("INFO: Executed completely");
    }

}
