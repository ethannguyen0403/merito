package agentsite.testcase.agencymanagement;
//Just run in STG C3670, C3677
import agentsite.pages.agentmanagement.FollowBetsPage;
import agentsite.pages.agentmanagement.followbets.GroupDetailsPopup;
import agentsite.pages.agentmanagement.followbets.PlayerDetailsPopup;
import agentsite.pages.components.ConfirmPopup;
import agentsite.ultils.agencymanagement.FollowBetsUtils;
import baseTest.BaseCaseTest;
import com.paltech.element.common.Label;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class FollowBetsTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Follow Bet
     * @expect: 1. There is no http requests error
     */
    @TestRails(id = "3664")
    @Test(groups = {"http_request"})
    public void Agent_AM_Follow_Bets_3664() {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Follow Bet");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can Add, Edit, Deleted, group for Exchange product
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management >  Follow Bets
     * 2. Select By Group
     * 3. Click Add Group
     * 4. Input the required field with valid value and select check on Follow All Bets and click Save button
     * 5. Click on Edit button and update  group name, Follow status, group color, Additional Stake, Additional Odds Range %, Product, Stake %, Uncheck Follow All Bets, only check Match odds for all sport and Win for HR and click Save button
     * 6. Click Deleted button
     * @expect: 1. The group is added into Group List with correct info
     * 2. The group info is updated as step 5
     * 3. A confirm message display to confirm deleted group and click OK to confirm.
     * - Verify Group is remove out the group list table
     */
    @TestRails(id = "755")
    @Test(groups = {"smokePO"})
    @Parameters({"accountToBet", "username"})
    public void Agent_AM_Follow_Bets_755(String accountToBet, String username) {
        log("@title: Verify can Add, Edit, Deleted, group for Exchange product");
        log("Step 1. Navigate Agency Management >  Follow Bets");
        String groupName = String.format("%s%s", "Auto Group", StringUtils.generateAlphabetic(4));
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();

        log("Step 2. Select By Group");
        log("Step 3. Click Add Group");
        log("Step 4. Input the required field with valid value and select check on Follow All Bets and click Save button");
        GroupDetailsPopup popup = page.clickAddGroup("By Group".toUpperCase());
        popup.createNewGroup(groupName, "Yes", accountToBet, "1", "2", "Exchange", "11", true);

        log("Verify 1. The group is added into Group List with correct info  ");
        Assert.assertTrue(page.verifyByGroupInfo(groupName, "Yes", "11.00", "0.00", "1.00", "2.00", accountToBet, username, ""));

        log("Step 5. Click on Edit button and update  group name, Follow status, group color, Additional Stake, Additional Odds Range %, Product, Stake %, Uncheck Follow All Bets, only check Match odds for all sport and Win for HR and click Save button");
        popup = (GroupDetailsPopup) page.clickAction(groupName, "Edit");
        popup.createNewGroup(groupName, "No", accountToBet, "2", "3", "Exchange", "9", true);

        log("Verify 2. The group info is updated as step 5");
        Assert.assertTrue(page.verifyByGroupInfo(groupName, "No", "--", "--", "--", "--", "--", username, ""));

        log("Step 6. Click Deleted button");
        ConfirmPopup confirmPopup = (ConfirmPopup) page.clickAction(groupName, "Delete");
        String confirmMsg = confirmPopup.getContentMessage();
        confirmPopup.confirm();

        log("Verify 3. A confirm message display to confirm deleted group and click OK to confirm.\n" +
                "     *          - Verify Group is remove out the group list table");

        Assert.assertEquals(confirmMsg, "Are you sure to delete this group?", "FAILED! Confirm delete group message are incorrect");
        Assert.assertFalse(page.verifyByGroupInfo(groupName, "No", "--", "--", "--", "--", "--", username, ""));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can add, remove agent for a group
     * @pre-condition: 1. Log in successfully by PO
     * 2. Have a group created
     * @steps: 1. Navigate Agency Management >  Follow Bets
     * 2. Select By Group
     * 3. Select a group in group list
     * 4. Enter a valid agent account
     * 5. Remove the agent out the group
     * @expect: 1.Verify can add agent into the group
     * 2. Verify can agent player out the group
     */
    @TestRails(id = "756")
    @Test(groups = {"smokePO"})
    @Parameters({"accountToBet", "controlBlockingAccount"})
    public void Agent_AM_Follow_Bets_756(String accountToBet, String controlBlockingAccount) {
        log("@title: Verify can Add, Edit, Deleted, group for Exchange product");
        log("Step 1. Navigate Agency Management >  Follow Bets");
        String groupName = String.format("%s%s", "Auto Group", StringUtils.generateAlphabetic(4));
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        try {
            log("Step Precondition: Have a group created");
            page.clickAddGroup("By Group".toUpperCase()).createNewGroup(groupName, "No", accountToBet, "1", "2", "Exchange", "11", true);

            log("Step 2. Select By Group");
            log("Step 3. Select a group in group list");
            log("Step 4. Enter a valid agent account");
            ConfirmPopup confirmPopup = page.addAgentPlayer(groupName, controlBlockingAccount);
            String confirmMsg = confirmPopup.getContentMessage();
            confirmPopup.confirm();
            page.waitingLoadingSpinner();

            log("Verify 1.Verify can add agent into the group");
            Assert.assertEquals(confirmMsg, String.format("Do you want to add %s to group %s?", controlBlockingAccount, groupName), String.format("FAILED! Confirm add add %s to group %s is incorrect", controlBlockingAccount, groupName));
            List<ArrayList<String>> addAgentPlayerLst = page.tblAddAgentPlayer.getRowsWithoutHeader(false);
            Assert.assertEquals(addAgentPlayerLst.get(0).get(page.colUsername - 1), controlBlockingAccount, "FAILED! Agent account added not match");

            log("Step 5. Remove the agent out the group");
            confirmPopup = page.followBets.removeAgentPlayerByGroup(controlBlockingAccount);
            confirmMsg = confirmPopup.getContentMessage();
            confirmPopup.confirm();
            page.waitingLoadingSpinner();

            log("Verify 2. Verify can remove agent out the group");
            Assert.assertEquals(confirmMsg, String.format("Confirm to remove user %s from %s.", addAgentPlayerLst.get(0).get(page.colUsername - 1), groupName), "FAILED! Confirm remove agent/player is incorrect");
            Assert.assertTrue(page.tblAddAgentPlayer.getColumn(1, 1, false).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND), "FAILED! Agent has not removed yet");
        } finally {
            log("Step PostCondition: Delete group");
            ConfirmPopup confirmPopup = (ConfirmPopup) page.clickAction(groupName, "Delete");
            confirmPopup.confirm();
        }
        log("INFO: Executed completely");
    }
    @TestRails(id = "3665")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3665() {
        log("@title: Validate Follow Bets UI - By Group display correct");
        log("@Pre-condition 1: Log in successfully by PO");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Group");
        page.rbByGroup.click();
        log("Verify 1: Validate Follow Bets - By Group UI display correctly");
        Assert.assertTrue(page.isFormSearchByGroupDisplayCorrect());
        Assert.assertTrue(page.isFormAddPlayerAgentByGroupDisplayCorrect());
        Assert.assertTrue(page.btnAddGroup.isEnabled(),"Add Group button display incorrect");
        Assert.assertTrue(page.isTblGroupListByGroupDisplayCorrect());
        Assert.assertTrue(page.followBets.isTblPlayerAgentListByGroupDisplayCorrect());
        log("INFO: Executed completely");
    }
    @TestRails(id = "3666")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3666() {
        log("@title: Validate Follow Bets UI - By Player display correct");
        log("@Pre-condition 1: Log in successfully by PO");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Player");
        page.rbByPlayer.click();
        log("Verify 1: Validate Follow Bets - By Group UI display correctly");
        Assert.assertTrue(page.lblPlayerName.isDisplayed(),"Player Name Label display incorrect!");
        Assert.assertTrue(page.txtPlayerName.isDisplayed(),"Player Name Textbox display incorrect!");
        Assert.assertTrue(page.btnSearchByPlayer.isDisplayed(),"Search Player Name Button display incorrect!");
        Assert.assertTrue(page.btnAddPlayer.isDisplayed(),"Add Player Button display incorrect!");
        Assert.assertTrue(page.followBets.isHeaderTableByPlayerDisplayCorrect());
        log("INFO: Executed completely");
    }
    @TestRails(id = "3667")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3667 () {
        log("@title: Validate can search the player that added into a group");
        log("@Pre-condition 1: Log in successfully by PO");
        log("@Pre-condition 1: Has a player is added into a group");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        String username = FollowBetsUtils.getUsernameByLevel("PL");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Group");
        page.rbByGroup.click();
        log("Step 3: Input Player Username/Login ID and click Search");
        page.searchPlayer("By Group",username);
        page.waitingLoadingSpinner();
        log("Verify 1: Validate Group List display a group that has the player is added in Player/Agent List");
        Assert.assertTrue(page.isPlayerAddedDisplayCorrect("By Group",username),"FAILED! Player is added display incorrect!");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3668")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3668 () {
        log("@title: Validate can search the agent that added into a group");
        log("@Pre-condition 1: Log in successfully by PO");
        log("@Pre-condition 1: Has a agent is added into a group");
        String username = FollowBetsUtils.getUsernameByLevel("AG");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Group");
        page.rbByGroup.click();
        log("Step 3: Input Agent Username/Login ID and click Search");
        page.searchPlayer("By Group",username);
        page.waitingLoadingSpinner();
        log("Verify 1: Validate Group List display a group that has the agent is added in Player/Agent List");
        Assert.assertTrue(page.isPlayerAddedDisplayCorrect("By Group",username),"FAILED! Agent is added display incorrect!");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3669")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3669 () {
        log("@title: Validate Group Details popup UI Display correctly");
        log("@Pre-condition 1: Log in successfully by PO");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Group");
        page.rbByGroup.click();
        log("Step 3: Click Add Group");
        GroupDetailsPopup groupDetailsPopup = page.clickAddGroup("BY GROUP");
        log("Verify 1: Validate Group Details popup display correctly");
        Assert.assertTrue(groupDetailsPopup.lblGroupDetails.isDisplayed(),"FAILED! Group Details display incorrect!");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3670")
    @Test(groups = {"stg_regression_po"})
    @Parameters({"controlBlockingAccount", "accountToBet"})
    public void Agency_Management_Follow_Bets_3670 (String controlBlockingAccount, String accountToBet) {
        log("@title: Validate can add, remove player for a group");
        log("@Pre-condition 1: Log in successfully by PO");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        String groupName = String.format("%s%s", "Auto Group", StringUtils.generateAlphabetic(4));
        try{
            log("@Pre-condition 2: Have a group created");
            log("Step 1: Navigate Agency Management >  Follow Bets");
            page.clickAddGroup("By Group".toUpperCase()).createNewGroup(groupName, "No", accountToBet, "1", "2", "Exchange", "11", true);
            log("Step 2: Select By Group");
            log("Step 3: Select a group in group list");
            log("Step 4: Add player into the group");
            ConfirmPopup confirmPopup = page.addAgentPlayer(groupName, controlBlockingAccount);
            String confirmMsg = confirmPopup.getContentMessage();
            confirmPopup.confirm();
            page.waitingLoadingSpinner();
            log("Verify 1: Validate can add player into the group");
            Assert.assertEquals(confirmMsg, String.format("Do you want to add %s to group %s?", controlBlockingAccount, groupName), String.format("FAILED! Confirm add %s to group %s is incorrect", controlBlockingAccount, groupName));
            List<ArrayList<String>> addAgentPlayerLst = page.tblAddAgentPlayer.getRowsWithoutHeader(false);
            Assert.assertTrue(page.isPlayerAddedDisplayCorrect("By Group",controlBlockingAccount),"FAILED! Player is added display incorrect!");
            log("Step 5: Remove Player out the group");
            page.followBets.removeAgentPlayerByGroup(controlBlockingAccount);
            confirmMsg = confirmPopup.getContentMessage();
            confirmPopup.confirm();
            page.waitingLoadingSpinner();
            log("Verify 2: Validate can remove player out the group");
            Assert.assertEquals(confirmMsg, String.format("Confirm to remove user %s from %s.", addAgentPlayerLst.get(0).get(page.colUsername - 1), groupName), "FAILED! Confirm remove agent/player is incorrect");
            Assert.assertTrue(page.isPlayerAddedDisplayCorrect("By Group",controlBlockingAccount),"FAILED! Player still not remove!");
        } finally {
            log("Step PostCondition: Delete group");
            page.rbByPlayer.click();
            page.rbByGroup.click();
            page.waitingLoadingSpinner();
            ConfirmPopup confirmPopup = (ConfirmPopup) page.clickAction(groupName, "Delete");
            confirmPopup.confirm();
        }
        log("INFO: Executed completely");
    }
    @TestRails(id = "3671")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3671 () {
        log("@title: Validate can add, remove multi player for a group");
        log("@Pre-condition 1: Log in successfully by PO");
        log("@Pre-condition 2: Have a group created");
        String groupName = FollowBetsUtils.getLstGroupName().get(0);
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Group");
        page.rbByGroup.click();
        log("Step 3: Select a group and input invalid account and click Add Player/Agent");
        ConfirmPopup confirmPopup = page.addAgentPlayer(groupName, "asdqwerd");
        log("Verify 1: Validate confirm popup display when add account");
        Assert.assertTrue(confirmPopup.isPopupDisplay(),"FAILED! Confirm popup display incorrect");
        confirmPopup.confirm();
        page.waitingLoadingSpinner();
        log("Verify 2: Validate The message [username] does not exist in system!");
        Assert.assertEquals(page.lblErrorContent.getText(), String.format("%s does not exist in system!","asdqwerd"));
        log("INFO: Executed completely");
    }
    @TestRails(id = "3672")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3672 () {
        log("@title: Validate cannot add the Player/Agent that added to exist group");
        log("@Pre-condition 1: Log in successfully by PO");
        log("@Pre-condition 2: Have group that is added account");
        String groupName = FollowBetsUtils.getLstGroupName().get(0);
        String username = FollowBetsUtils.getUsernameByLevel("PL");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Group");
        page.rbByGroup.click();
        log("Step 3: Select Group and add account A into");
        ConfirmPopup confirmPopup = page.addAgentPlayer(groupName, username);
        log("Verify 1: Validate confirm popup display when add account");
        Assert.assertTrue(confirmPopup.isPopupDisplay(),"FAILED! Confirm popup display incorrect");
        confirmPopup.confirm();
        page.waitingLoadingSpinner();
        log("Verify 2: Validate The message Error popup display User [username] has existed in group.");
        Assert.assertEquals(page.lblErrorContent.getText(), String.format("User %s has existed in group.",username));
        log("INFO: Executed completely");
    }
    @TestRails(id = "3673")
    @Test(groups = {"regression_po"})
    @Parameters({"accountToBet"})
    public void Agency_Management_Follow_Bets_3673 (String accountToBet) {
        log("@title: Validate group name is unique");
        log("@Pre-condition 1: Log in successfully by PO");
        log("@Pre-condition 2: Have a group name");
        String groupName = FollowBetsUtils.getLstGroupName().get(0);
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Group");
        page.rbByGroup.click();
        log("Step 3: Click Add Group and input the group name same with the group in precondition");
        log("Step 4: Input other required info and click Save");
        page.clickAddGroup("By Group".toUpperCase()).createNewGroup(groupName, "No", accountToBet, "1", "2", "Exchange", "11", true);
        log("Verify 1: Validate error message display [Group name] group is existing. Please try another name!");
        Assert.assertEquals(page.lblErrorContent.getText(), String.format("%s group is existing. Please try another name!",groupName));
        log("INFO: Executed completely");
    }
    @TestRails(id = "3674")
    @Test(groups = {"regression_po"})
    @Parameters({"accountToBet"})
    public void Agency_Management_Follow_Bets_3674 (String accountToBet) {
        log("@title: Validate cannot add group if not add market or check follow all of a sport");
        log("@Pre-condition 1: Log in successfully by PO");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        String groupName = String.format("%s%s", "Auto Group", StringUtils.generateAlphabetic(4));
        log("Step 2: Select By Group");
        page.rbByGroup.click();
        log("Step 3: Click Add Group and input the required fields, not check on Follow All Bets and not add any market of sport then click on Save");
        page.clickAddGroup("By Group".toUpperCase()).createNewGroup(groupName, "No", accountToBet, "1", "2", "Exchange", "11", false);
        log("Verify 1: Error message display \"You should add at least one market or check on Follow All Bets/Follow All of a sport\"");
        Assert.assertEquals(page.lblErrorContent.getText(), "You should add at least one market or check on Follow All Bets/Follow All of a sport.");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3675")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3675 () {
        log("@title: Validate can search by Player");
        log("@Pre-condition 1: Log in successfully by PO");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Player");
        page.rbByPlayer.click();
        page.waitingLoadingSpinner();
        log("Step 3: Enter the Player account that added into the list and click Search");
        String username = page.tblByPlayer.getColumn(page.colUsername,false).get(0);
        page.searchPlayer("By Player",username);
        log("Verify 1: Validate account is display in the list");
        Assert.assertEquals(page.tblByPlayer.getColumn(page.colUsername, false).get(0),username, "You should add at least one market or check on Follow All Bets/Follow All for a sport");
        log("INFO: Executed completely");
    }
    @TestRails(id = "3676")
    @Test(groups = {"regression_po"})
    public void Agency_Management_Follow_Bets_3676 () {
        log("@title: Validate Player Details UI popup");
        log("@Pre-condition 1: Log in successfully by PO");
        log("Step 1: Navigate Agency Management >  Follow Bets");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();
        log("Step 2: Select By Player");
        page.rbByPlayer.click();
        log("Step 3: Click Add Player");
        PlayerDetailsPopup playerDetailsPopup = page.clickAddPlayer();
        playerDetailsPopup.lblPlayerDetails.click();
        log("Verify 1: Validate Player Details UI display correctly");
        Assert.assertTrue(playerDetailsPopup.txtPlayerName.isDisplayed(),"FAILED! Player Name textbox display incorrect!");
        Assert.assertTrue(playerDetailsPopup.txtAdditionalStake.isDisplayed(),"FAILED! Additional Stake % textbox display incorrect!");
        Assert.assertTrue(playerDetailsPopup.txtAdditionalOddsRange.isDisplayed(),"FAILED! Additional Odds Range % textbox display incorrect!");
        Assert.assertTrue(playerDetailsPopup.txtStake.isDisplayed(),"FAILED! Stake % textbox display incorrect!");
        Assert.assertTrue(playerDetailsPopup.ddbFollowStatus.isEnabled(),"FAILED! Follow Status dropdown display incorrect!");
        Assert.assertTrue(playerDetailsPopup.ddbAccountToBet.isEnabled(),"FAILED! Account To Bet dropdown display incorrect!");
        Assert.assertTrue(playerDetailsPopup.ddbProduct.isEnabled(),"FAILED! Product dropdown display incorrect!");
        Assert.assertTrue(playerDetailsPopup.cbFollowAll.isEnabled(),"FAILED! Follow All Bets checkbox display incorrect!");
        Assert.assertEquals(playerDetailsPopup.tblFollowDetails.getHeaderList(),AGConstant.AgencyManagement.FollowBets.FOLLOW_DETAIL_TABLE_HEADER,"FAILED! Header table name of follow details table display incorrect!");
        log("INFO: Executed completely");
    }

}

