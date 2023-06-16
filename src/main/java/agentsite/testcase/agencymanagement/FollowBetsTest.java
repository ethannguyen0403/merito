package agentsite.testcase.agencymanagement;

import common.AGConstant;
import agentsite.pages.agentmanagement.FollowBetsPage;
import agentsite.pages.agentmanagement.followbets.GroupDetailsPopup;
import agentsite.pages.components.ConfirmPopup;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FollowBetsTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Follow Bet
     * @expect: 1. There is no http requests error
     */
    @Test(groups = {"http_request"})
    public void Agent_AM_Follow_Bets_001(){
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Follow Bet");
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can Add, Edit, Deleted, group for Exchange product
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management >  Follow Bets
     *          2. Select By Group
     *          3. Click Add Group
     *          4. Input the required field with valid value and select check on Follow All Bets and click Save button
     *          5. Click on Edit button and update  group name, Follow status, group color, Additional Stake, Additional Odds Range %, Product, Stake %, Uncheck Follow All Bets, only check Match odds for all sport and Win for HR and click Save button
     *          6. Click Deleted button
     * @expect: 1. The group is added into Group List with correct info
     *          2. The group info is updated as step 5
     *          3. A confirm message display to confirm deleted group and click OK to confirm.
     *          - Verify Group is remove out the group list table
     */
    @Test (groups = {"smokePO"})
    @Parameters({"accountToBet","username"})
    public void Agent_AM_Follow_Bets_007(String accountToBet,String username){
        log("@title: Verify can Add, Edit, Deleted, group for Exchange product");
        log("Step 1. Navigate Agency Management >  Follow Bets");
        String groupName = String.format("%s%s", "Auto Group", StringUtils.generateAlphabetic(4));
        FollowBetsPage page = agentHomePage.navigateFollowBetsPage();

        log("Step 2. Select By Group");
        log("Step 3. Click Add Group");
        log("Step 4. Input the required field with valid value and select check on Follow All Bets and click Save button");
        GroupDetailsPopup popup =page.clickAddGroup("By Group".toUpperCase());
        popup.createNewGroup(groupName,"Yes",accountToBet,"1","2","Exchange","11",true);

        log("Verify 1. The group is added into Group List with correct info  ");
        Assert.assertTrue(page.verifyByGroupInfo(groupName,"Yes","11.00","0.00","1.00","2.00",accountToBet,username,""));

        log("Step 5. Click on Edit button and update  group name, Follow status, group color, Additional Stake, Additional Odds Range %, Product, Stake %, Uncheck Follow All Bets, only check Match odds for all sport and Win for HR and click Save button");
        popup = (GroupDetailsPopup) page.clickAction(groupName,"Edit");
        popup.createNewGroup(groupName,"No",accountToBet,"2","3","Exchange","9",true);

        log("Verify 2. The group info is updated as step 5");
        Assert.assertTrue(page.verifyByGroupInfo(groupName,"No","--","--","--","--","--",username,""));

        log("Step 6. Click Deleted button");
        ConfirmPopup confirmPopup = (ConfirmPopup) page.clickAction(groupName,"Delete");
        String confirmMsg = confirmPopup.getContentMessage();
        confirmPopup.confirm();

        log("Verify 3. A confirm message display to confirm deleted group and click OK to confirm.\n" +
                "     *          - Verify Group is remove out the group list table");

        Assert.assertEquals(confirmMsg,"Are you sure to delete this group?", "FAILED! Confirm delete group message are incorrect");
        Assert.assertFalse(page.verifyByGroupInfo(groupName,"No","--","--","--","--","--",username,""));

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can add, remove agent for a group
     * @pre-condition:
     *           1. Log in successfully by PO
     *           2. Have a group created
     * @steps: 1. Navigate Agency Management >  Follow Bets
     *          2. Select By Group
     *          3. Select a group in group list
     *          4. Enter a valid agent account
     *      5. Remove the agent out the group
     * @expect: 1.Verify can add agent into the group
     *          2. Verify can agent player out the group
     */
    @Test (groups = {"smokePO"})
    @Parameters({"accountToBet","controlBlockingAccount"})
    public void Agent_AM_Follow_Bets_009(String accountToBet,String controlBlockingAccount){
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
            confirmPopup = page.removeAgentPlayerByGroup(controlBlockingAccount, controlBlockingAccount);
            confirmMsg = confirmPopup.getContentMessage();
            confirmPopup.confirm();
            page.waitingLoadingSpinner();

            log("Verify 2. Verify can remove agent out the group");
            Assert.assertEquals(confirmMsg, String.format("Confirm to remove user %s from %s.", addAgentPlayerLst.get(0).get(page.colUsername-1),groupName), "FAILED! Confirm remove agent/player is incorrect");
            Assert.assertTrue(page.tblAddAgentPlayer.getColumn(1, 1, false).get(0).equalsIgnoreCase(AGConstant.NO_RECORD_FOUND), "FAILED! Agent has not removed yet");
        }finally {
            log("Step PostCondition: Delete group");
            ConfirmPopup confirmPopup = (ConfirmPopup) page.clickAction(groupName,"Delete");
            confirmPopup.confirm();
        }
        log("INFO: Executed completely");
    }

}

