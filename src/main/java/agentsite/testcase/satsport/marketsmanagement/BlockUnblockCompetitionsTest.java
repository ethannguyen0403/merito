package agentsite.testcase.satsport.marketsmanagement;
import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import membersite.objects.sat.Event;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.marketsmanagement.BlockUnblockCompetitionPage;
import agentsite.pages.all.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.maketmanagement.BlockUnblockCompetitionUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.*;
import static agentsite.common.AGConstant.MarketsManagement.BlockUnblockEvent.TABs;

public class BlockUnblockCompetitionsTest extends BaseCaseMerito {
    /**
     * @title: Validate Block/Unblock Competition UI display correctly
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:   1. Navigate Markets Management > Block/Unblock Competition
     *
     * @expect:  1. Verify Block/Unblock Competition UI display correctly
     */
    @Test(groups = {"smoke"})
    public void Agent_MM_BlockUnblockCompetitions_TC002() {
        log("@title: Validate Block/Unblock Competition UI display correctly");

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");
        BlockUnblockCompetitionPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_COMPETITION, BlockUnblockCompetitionPage.class);

        log("Verify 1. Verify Block/Unblock Competition UI display correctly");
        Assert.assertEquals(page.lblSport.getText(), AGConstant.MarketsManagement.BlockUnblockCompetition.LBL_SPORT,"FAILED! Sport label displays incorrect");
        Assert.assertTrue(page.ddbSport.isDisplayed(),"FAILED! Sport dropdown not exist");
        Assert.assertEquals(page.lblInfo.getText(), AGConstant.MarketsManagement.BlockUnblockCompetition.LBL_INFO,"FAILED! Old Events tab should  display when select Event");
        Assert.assertTrue(page.btnBlock.isDisplayed(),"FAILED! Block button does not display");
        Assert.assertTrue(page.btnUnBlock.isDisplayed(),"FAILED! Unblock button does not display");
        Assert.assertTrue(page.txtSearchByUsernameLoginID.isDisplayed(),"FAILED! Search by Username or Login ID textbox not display");
        Assert.assertTrue(page.txtSearchByCompetition.isDisplayed(),"FAILED! Search by competition does not display");
        Assert.assertEquals(page.tblDownline.getColumnNamesOfTable(), AGConstant.MarketsManagement.BlockUnblockCompetition.TABLE_DOWNLINE,"FAILED! Downline table header not match with the expected");
        Assert.assertEquals(page.tblCompetition.getColumnNamesOfTable(), AGConstant.MarketsManagement.BlockUnblockCompetition.TABLE_COMPETITON,"FAILED! Competition table header not match with the expected");
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can Block a competition
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:   1. Navigate Markets Management > Block/Unblock Competition
     *           2. Select Sport
     *           3. Select downline then select a unblocked competition
     *           4. Click on Block button
     * @expect:  1. Status is change to Blocked Status, Last Update By and Last Update time display correctly
     */
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount"})
    public void Agent_MM_BlockUnblockCompetitions_TC003(String downlineAccount) {
        log("@title: Verify can Block a competition");
        log("Step 1. Navigate Markets Management > Block/Unblock Competition");
        AccountInfo acc = ProfileUtils.getProfile();
        String sportID = AGConstant.HomePage.SPORT_ID.get("Soccer");
        String lastUpdateBy = String.format("%s (%s)", acc.getUserCode(), acc.getLoginID());
        BlockUnblockCompetitionPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_COMPETITION, BlockUnblockCompetitionPage.class);
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        String competitionName = BlockUnblockCompetitionUtils.getCompetitionList(sportID,childID,"Unblocked").get(0);

        try{
        log("Step 2. Select Sport");
        log("Step 3. Select downline then select a unblocked competition");
        log("Step 4. Click on Block button");
        String updateTime = page.blockUblockCompetition("Soccer", downlineAccount, competitionName, true);

        log("Verify 1. Status is change to Blocked Status, Last Update By and Last Update time display correctly");
        page.verifyCompetitionStatus(competitionName, "Blocked",lastUpdateBy,updateTime);

        } finally {
            log("Post-condition Step: UnBlock then competition");
            page.blockUblockCompetition("Soccer","",competitionName,false);
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify can Unblock a competition
     * @pre-condition:
     *           1. Log in successfully by SAD
     *           2. There is a blocked competition
     * @steps:   1. Navigate Markets Management > Block/Unblock Competition
     *           2. Select Sport
     *           3. Select downline then select a blocked competition
     *           4. Click on Unblock button
     * @expect:  1. Status is change to Unblocked Status, Last Update By and Last Update time display correctly
     */
    @Test(groups = {"smoke"})
    @Parameters("downlineAccount")
    public void Agent_MM_BlockUnblockCompetitions_TC004(String downlineAccount) {
        log("@title: Verify can Unblock a competition");
        AccountInfo acc = ProfileUtils.getProfile();
        String lastUpdateBy = String.format("%s (%s)",acc.getUserCode(),acc.getLoginID());
        String sportID = AGConstant.HomePage.SPORT_ID.get("Soccer");

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");
        BlockUnblockCompetitionPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_COMPETITION, BlockUnblockCompetitionPage.class);
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        String competitionName = BlockUnblockCompetitionUtils.getCompetitionList(sportID,childID,"Unblocked").get(0);

        log("Pre-condition Step: Block an competition");
        page.blockUblockCompetition("Soccer", downlineAccount, competitionName, true);

        log("Step 2. Select Sport");
        log("Step 3. Select downline then select a blocked competition");
        log("Step 4. Click on Unblock button");
        String updateTime = page.blockUblockCompetition("", "", competitionName, false);

        log("Verify 1. Status is change to Unblocked Status, Last Update By and Last Update time display correctly");
        page.verifyCompetitionStatus(competitionName, "Unblocked",lastUpdateBy, updateTime);

        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Unblock Competition display in Member Site and Block/Unblock Event
     * @pre-condition:
     *           1. Log in successfully by SAD
     * @steps:   1. Navigate Markets Management > Block/Unblock Competition
     *           2. Select Sport
     *           3. Select downline then select a blocked competition
     *           4. Click on Unblock  button
     * @expect: 1. Verify the competition displays in Block/Unblock Event Report
     *          2. Verify the competition displays on member site
     */
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount","memberAccount", "password"})
    public void Agent_MM_BlockUnblockCompetitions_TC005(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Verify can Unblock a competition");

        AccountInfo acc = ProfileUtils.getProfile();
        String sportName ="Soccer";

        log("Pre-condition: get a competition in Today Tab");
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        List<Event> event = blockUnblockEventPage.getEventAvailable(acc.getUserID(), downlineAccount, "", sportName, "");
        String activeTab = blockUnblockEventPage.getActiveTab();
        String competitionName = event.get(0).getCompetitionName();
        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");

        BlockUnblockCompetitionPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_COMPETITION, BlockUnblockCompetitionPage.class);

        log("Pre-condition Step: Block an competition");
        page.blockUblockCompetition(sportName, downlineAccount, competitionName, true);

        log("Step 2. Select Sport");
        log("Step 3. Select downline then select a blocked competition");
        log("Step 4. Click on Unblock button");
        page.blockUblockCompetition("", "", competitionName, false);

        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();

        log("Verify 1. Verify the competition displays in Block/Unblock Event Report");
        blockUnblockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockUnblockEventPage.filter("", sportName, activeTab);
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        event = BlockUnblockEventsUtils.getEventList(sportName,childID,TABs.get(activeTab));
        Assert.assertTrue(blockUnblockEventPage.isCompetitionExist(event,competitionName),"FAILED! Competition is not display in Block UnBlock Event page after unblocking");

       /* log("Step 5: Logout agent site");
        agentHomePage.logout();

        log("Verify 2. Verify the competition displays on member site");
        DriverManager.getDriver().getToAvoidTimeOut(environment.getMemberSiteURL());
        loginMember(memberAccount,password);
        memberagentHomePage.clickMenu(sportName);
        List<String> leftMenuCompetitionList = memberagentHomePage.getLeftMenuList();
        Assert.assertTrue(leftMenuCompetitionList.contains(competitionName),String.format("FAILED! Member site left menu not display the competition %s",competitionName));
*/
        log("INFO: Executed completely");
    }

    /**
     * @title: Verify Blocked Competition not display in Member Site and Block/Unblock Event
     * @pre-condition:
     *           1. Log in successfully by SAD
     *           2. There is a blocked competition
     * @steps:   1. Navigate Markets Management > Current Blocking
     *           2. Block a soccer's competition
     * @expect: 1. Verify the competition not display in Block/Unblock Event Report
     *          2. Verify the competition does not display on member site
     */
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount","memberAccount", "password"})
    public void Agent_MM_BlockUnblockCompetitions_TC006(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Verify Blocked Competition not display in Member Site and Block/Unblock Event");
        AccountInfo acc = ProfileUtils.getProfile();
        String sportName ="Soccer";

        log("Pre-condition: get a competition in Today Tab");
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        List<Event> event = blockUnblockEventPage.getEventAvailable(acc.getUserID(), downlineAccount, "", sportName, "");
        String activeTab = blockUnblockEventPage.getActiveTab();
        String competitionName = event.get(0).getCompetitionName();
        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");
        BlockUnblockCompetitionPage page = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_COMPETITION, BlockUnblockCompetitionPage.class);

        log("Step 2. Select Sport");
        log("Step 3. Select downline then select a blocked competition");
        log("Step 4. Click on Unblock button");
        page.blockUblockCompetition(sportName, downlineAccount, competitionName, true);

        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();

        log("Verify  1. Verify the competition not display in Block/Unblock Event Report ");
        blockUnblockEventPage = agentHomePage.clickSubMenu(MARKET_MANAGEMENT, BLOCK_UNBLOCK_EVENT, BlockUnblockEventPage.class);
        blockUnblockEventPage.filter("", sportName, activeTab);

        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        event = BlockUnblockEventsUtils.getEventList(sportName,childID,TABs.get(activeTab));
        Assert.assertFalse(blockUnblockEventPage.isCompetitionExist(event,competitionName),"FAILED! Competition display in Block UnBlock Event page after blocking");

       /* log("Step 5: Logout agent site");
        DriverManager.getDriver().switchToParentFrame();
        agentHomePage.logout();

        log("Verify 2. Verify the competition does NOT displays on member site after blocking");
        DriverManager.getDriver().getToAvoidTimeOut(environment.getMemberSiteURL());

        log("Step 6: Logout Member Site");
        loginMember(memberAccount,password);

        log("Step 7: Get List competition in the leftmenu");
        memberagentHomePage.clickMenu(sportName);
        List<String> leftMenuCompetitionList = memberagentHomePage.getLeftMenuList();
        Assert.assertFalse(leftMenuCompetitionList.contains(competitionName),String.format("FAILED! Member site left menu display the competition %s after blocking in agent site",competitionName));
*/
        log("INFO: Executed completely");
    }
}
