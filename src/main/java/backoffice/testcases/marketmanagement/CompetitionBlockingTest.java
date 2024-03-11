package backoffice.testcases.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.marketmanagement.CompetitionBlockingPage;
import backoffice.utils.operations.CompetitionBlockingUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

public class CompetitionBlockingTest extends BaseCaseTest {

    /**
     * @title: Validate default UI is loaded and competition is unblocked by default
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Operations > Competition Blocking
     * @expect: 1. Verify UI is completed load
     * 2. The competition is unblock by default
     */
    @TestRails(id = "625")
    @Test(groups = {"smoke"})
    public void BO_Market_Management_Competition_Blocking_625() {
        log("@title: Validate default UI is loaded and competition is unblocked by default");
        log("Step 1. Access Operations > Competition Blocking");
        CompetitionBlockingPage page = backofficeHomePage.navigateCompetitionBlocking();
        List<ArrayList<String>> lstSport = CompetitionBlockingUtils.getSportList();

        log("Verify 1. Verify UI is completed load");
        Assert.assertEquals(page.btnBlock.getText(), "Block", "FAILED! Block button is incorrect");
        Assert.assertEquals(page.btnUnblock.getText(), "Unblock", "FAILED! Unblock button is incorrect");
        Assert.assertFalse(page.btnBlock.isEnabled(), "FAILED! Block button is not disable by default");
        Assert.assertFalse(page.btnUnblock.isEnabled(), "FAILED! Unblock button is not disable by default");
        Assert.assertEquals(page.lblNote.getText(), BOConstants.Operations.CompetitionBlocking.LBL_NOTE, "FAILED! Note lable not display as expected");

        log("Verify  2. The competition is unblock by default");
        Assert.assertTrue(page.isCompetitionUnblockByDefault(lstSport));

        log("INFO: Executed completely");
    }

//    /**
//     * @title: Validate Unblocked competition display correctly in BO - Agent> Competition Blocking, and display in block unblock event
//     * @pre-condition: 1. Login BO
//     * @steps: 1. Access Operations > Competition Blocking
//     * 2. Select a sport and block competition
//     * 3. Login agent site > Competition Blocking and Block/Unblock Events page
//     * @expect: 1 Verify the competition is in blocked status
//     * 2. Verify the status of competition in agent > Competition Blocking is correctly
//     * 3.The bocked competition not display in agent site > Block unblock event
//     */
//    @Test(groups = {"regression"})
//    @Parameters({"username", "jioPartAGAccount", "memberPassword"})
//    public void BO_Market_Management_Competition_Blocking_002(String username, String jioPartAGAccount, String memberPassword) {
//        log("@title: Validate Unblocked competition display correctly in BO - Agent> Competition Blocking, and display in block unblock event");
//        log("Step 1. Access Operations > Competition Blocking");
//        String sportName = "Darts";
//        String sportID = CompetitionBlockingUtils.getSportID(CompetitionBlockingUtils.getSportList(), sportName);
//        String competitionName = CompetitionBlockingUtils.getCompetitions(sportID).get(0).get(1);
//        CompetitionBlockingPage page = backofficeHomePage.navigateCompetitionBlocking();
//
//        log("Step 2. Select a sport and block competition");
//        page.searchSport(sportName);
//        page.selectSport(1);
//        page.waitSpinIcon();
//        if (page.lblNoCompetition.isDisplayed(2)) {
//            Assert.assertTrue(true, "Bypass the test case as there is no competition display");
//            return;
//        }
//        page.blockUnblockCompetition(competitionName, true);
//
//        log("Verify 1 Verify the competition is in blocked status");
//        Assert.assertTrue(page.verifyCompetition(competitionName, "Blocked", username), "FAILED! Competition status is incorrect");
//
//        log("Step 3. Login agent site > Competition Blocking and Block/Unblock Events page");
////Login agent and verify
//
//        log("Verify 2. Verify the status of competition in agent > Competition Blocking is unblocked");
//        log("Verify 3.The bocked competition not display in agent site > Block unblock event");
//
//        log("INFO: Executed completely");
//    }
//
//    /**
//     * @title: Validate Unblocked competition display correctly in BO - Agent> Competition Blocking, and display in block unblock event
//     * @pre-condition: 1. Login BO
//     * @steps: 1. Access Operations > Competition Blocking
//     * 2. Select a sport and unblock a blocked competition
//     * 3. Login agent site > Competition Blocking and Block/Unblock Events page
//     * @expect: 1 Verify the competition is in unblocked status
//     * 2. Verify the status of competition in agent > Competition Blocking is unblocked
//     * 3.The bocked competition display in agent site > Block unblock event
//     */
//    @Test(groups = {"regression"})
//    @Parameters({"username", "jioPartAGAccount", "memberPassword"})
//    public void BO_Market_Management_Competition_Blocking_003(String username, String jioPartAGAccount, String memberPassword) {
//        log("@title: Validate Unblocked competition display correctly in BO - Agent> Competition Blocking, and display in block unblock event");
//        log("Step 1. Access Operations > Competition Blocking");
//        String sportName = "Darts";
//        String sportID = CompetitionBlockingUtils.getSportID(CompetitionBlockingUtils.getSportList(), sportName);
//        String competitionName = CompetitionBlockingUtils.getCompetitions(sportID).get(0).get(1);
//        CompetitionBlockingPage page = backofficeHomePage.navigateCompetitionBlocking();
//
//        log("Step 2. Select a sport and unblock a blocked competition");
//        page.searchSport(sportName);
//        page.selectSport(1);
//        page.waitSpinIcon();
//        if (page.lblNoCompetition.isDisplayed()) {
//            Assert.assertTrue(true, "Bypass the test case as there is no competition display");
//            return;
//        }
//        page.blockUnblockCompetition(competitionName, false);
//
//        log("Verify 1 Verify the competition is in unblocked status");
//        page.verifyCompetition(competitionName, "Unblocked", username);
//
//        log("Step 3. Login agent site > Competition Blocking and Block/Unblock Events page");
//        //login agent site
//
//        log("Verify 2. Verify the status of competition in agent > Competition Blocking is unblocked");
//        log("Verify 3.The bocked competition display in agent site > Block unblock event");
//
//        log("INFO: Executed completely");
//    }

}
