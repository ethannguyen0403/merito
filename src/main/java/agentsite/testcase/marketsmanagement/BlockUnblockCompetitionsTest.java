package agentsite.testcase.marketsmanagement;

import common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.marketsmanagement.BlockUnblockCompetitionPage;
import agentsite.pages.marketsmanagement.BlockUnblockEventPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.maketmanagement.BlockUnblockCompetitionUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;
import baseTest.BaseCaseTest;
import common.MemberConstants;
import membersite.objects.sat.Event;
import membersite.pages.SportPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static common.AGConstant.MarketsManagement.BlockUnblockEvent.TABs;

public class BlockUnblockCompetitionsTest extends BaseCaseTest {
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
        BlockUnblockCompetitionPage page = agentHomePage.navigateBlockUnblockCompetitionPage();

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
        String updateBy = acc.getUserCodeAndLoginID();
       // String fullUserAlias = acc.getUserCodeAndLoginID();
        String sportID = MemberConstants.HomePage.SPORT_ID.get("Soccer");
        BlockUnblockCompetitionPage page = agentHomePage.navigateBlockUnblockCompetitionPage();
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        String competitionName = BlockUnblockCompetitionUtils.getCompetitionList(sportID,childID,"Unblocked").get(0);
        try{
        log("Step 2. Select Sport");
        log("Step 3. Select downline then select a unblocked competition");
        log("Step 4. Click on Block button");
        String updateTime = page.blockUblockCompetition("Soccer", downlineAccount, competitionName, true);

        log("Verify 1. Status is change to Blocked Status, Last Update By and Last Update time display correctly");
        page.verifyCompetitionStatus(competitionName, "Blocked",updateBy,updateTime);

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
        String fullUserAlias = acc.getUserCodeAndLoginID();
        String sportID = MemberConstants.HomePage.SPORT_ID.get("Soccer");

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");
        BlockUnblockCompetitionPage page = agentHomePage.navigateBlockUnblockCompetitionPage();
        String childID = BlockUnblockEventsUtils.getchildUserID(acc.getUserID(), downlineAccount);
        String competitionName = BlockUnblockCompetitionUtils.getCompetitionList(sportID,childID,"Unblocked").get(0);

        log("Pre-condition Step: Block an competition");
        page.blockUblockCompetition("Soccer", downlineAccount, competitionName, true);

        log("Step 2. Select Sport");
        log("Step 3. Select downline then select a blocked competition");
        log("Step 4. Click on Unblock button");
        String updateTime = page.blockUblockCompetition("", "", competitionName, false);

        log("Verify 1. Status is change to Unblocked Status, Last Update By and Last Update time display correctly");
        page.verifyCompetitionStatus(competitionName, "Unblocked",fullUserAlias, updateTime);

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
     */
    @Test(groups = {"smoke"})
    @Parameters({"downlineAccount","memberAccount", "password"})
    public void Agent_MM_BlockUnblockCompetitions_TC005(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Verify Unblock competition display in Block unblock event page");
        AccountInfo acc = ProfileUtils.getProfile();
        String sportName ="Soccer";

        log("Pre-condition: get a competition in Today Tab");
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        List<Event> event = blockUnblockEventPage.getEventAvailable(acc.getUserID(), downlineAccount, "", sportName, "");
        String activeTab = blockUnblockEventPage.getActiveTab();
        String competitionName = event.get(0).getCompetitionName();
        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");

        BlockUnblockCompetitionPage page = agentHomePage.navigateBlockUnblockCompetitionPage();

        log("Pre-condition Step: Block an competition");
        page.blockUblockCompetition(sportName, downlineAccount, competitionName, true);

        log("Step 2. Select Sport");
        log("Step 3. Select downline then select a blocked competition");
        log("Step 4. Click on Unblock button");
        page.blockUblockCompetition("", "", competitionName, false);

        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();

        log("Verify 1. Verify Unblock competition display in Block unblock event page");
        blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("", sportName, activeTab);
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        event = BlockUnblockEventsUtils.getEventList(sportName,childID,TABs.get(activeTab));
        Assert.assertTrue(blockUnblockEventPage.isCompetitionExist(event,competitionName),"FAILED! Competition is not display in Block UnBlock Event page after unblocking");

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
        log("@title: Verify Blocked Competition not display Block/Unblock Event");
        AccountInfo acc = ProfileUtils.getProfile();
        String sportName ="Soccer";

        log("Pre-condition: get a competition in Today Tab");
        BlockUnblockEventPage blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        List<Event> event = blockUnblockEventPage.getEventAvailable(acc.getUserID(), downlineAccount, "", sportName, "");
        String activeTab = blockUnblockEventPage.getActiveTab();
        String competitionName = event.get(0).getCompetitionName();
        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");
        BlockUnblockCompetitionPage page = agentHomePage.navigateBlockUnblockCompetitionPage();

        log("Step 2. Select Sport");
        log("Step 3. Select downline then select a blocked competition");
        log("Step 4. Click on Unblock button");
        page.blockUblockCompetition(sportName, downlineAccount, competitionName, true);

        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();

        log("Verify  1. Verify the competition not display in Block/Unblock Event Report ");
        blockUnblockEventPage = agentHomePage.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.filter("", sportName, activeTab);

        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        event = BlockUnblockEventsUtils.getEventList(sportName,childID,TABs.get(activeTab));
        Assert.assertFalse(blockUnblockEventPage.isCompetitionExist(event,competitionName),"FAILED! Competition display in Block UnBlock Event page after blocking");

        log("INFO: Executed completely");
    }

    @Test(groups = {"interaction5"})
    @Parameters({"downlineAccount","memberAccount", "password"})
    public void Agent_MM_BlockUnblockCompetitions_TC007(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Verify competition is displayed in member site when unblocked in agent site");
        log("Pre-condition: get a competition in Today Tab");
        AccountInfo acc = ProfileUtils.getProfile();
        String sportName ="Soccer";
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName,childID,"TODAY");
        Event event = eventList.get(0);
        String competitionName = event.getCompetitionName();

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");
        BlockUnblockCompetitionPage page = agentHomePage.navigateBlockUnblockCompetitionPage();

        log("Step 2. Unblock an competition of a sport for a downline");
        page.blockUblockCompetition(sportName, downlineAccount, competitionName, false);

        log("Step 3 Unblock an event of the competition in Block/Unblock Event page");
        BlockUnblockEventPage blockUnblockEventPage = page.navigateBlockUnblockEventsPage();
        blockUnblockEventPage.blockUnblockEvent(downlineAccount,event.getEventName(),"Unblock Now");

        log("Step 4 Login member site > Active "+sportName+" and get all list competitions in the left menu");
        loginMember(memberAccount,password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu("Soccer");
        List<String> leftMenuCompetitionList = sportPage.leftMenu.getLeftMenuList();

        log("Verify 1 Verity the competition displays in the left menu under selected sport");
        Assert.assertTrue(leftMenuCompetitionList.contains(competitionName),String.format("FAILED! Member site left menu should display the competition %s when it is unblocked",competitionName));
        log("INFO: Executed completely");
    }
    @Test(groups = {"interaction"})
    @Parameters({"downlineAccount","memberAccount", "password"})
    public void Agent_MM_BlockUnblockCompetitions_TC008(String downlineAccount,String memberAccount, String password) throws Exception {
        log("@title: Verify competition is not display in member site when blocked in agent site");
        log("Pre-condition: get a competition in Today Tab");
        AccountInfo acc = ProfileUtils.getProfile();
        String sportName ="Soccer";
        String childID =BlockUnblockEventsUtils.getchildUserID(acc.getUserID(),downlineAccount);
        List<Event> eventList = BlockUnblockEventsUtils.getEventList(sportName,childID,"TODAY");
        Event event = eventList.get(0);
        String competitionName = event.getCompetitionName();

        log("Step 1. Navigate Markets Management > Block/Unblock Competition");
        BlockUnblockCompetitionPage page = agentHomePage.navigateBlockUnblockCompetitionPage();

        log("Step 2. Unblock an competition of "+sportName+" for account" + downlineAccount);
        page.blockUblockCompetition(sportName, downlineAccount, competitionName, true);

        log("Step 3 Login member site account "+memberAccount+" then Active "+sportName+" and get all list competitions in the left menu");
        loginMember(memberAccount,password);
        SportPage sportPage = memberHomePage.navigateSportHeaderMenu("Soccer");
        List<String> leftMenuCompetitionList = sportPage.leftMenu.getLeftMenuList();

        log("Verify 1 Verity the competition does not display in the left menu under selected sport");
        Assert.assertFalse(leftMenuCompetitionList.contains(competitionName),String.format("FAILED! Member site left menu does not display the competition %s after blocking in agent site",competitionName));

        log("INFO: Executed completely");
    }
}
