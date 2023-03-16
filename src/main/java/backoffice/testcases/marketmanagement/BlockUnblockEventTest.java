package backoffice.testcases.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.marketmanagement.BeforeLoginManagementPage;
import backoffice.pages.bo.marketmanagement.BlockUnblockEventPage;
import backoffice.pages.bo.marketmanagement.components.BeforeLoginManagementPopup;
import backoffice.utils.marketmanagement.BlockUnblockEventUtils;
import backoffice.utils.system.CurrencyManagementUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BlockUnblockEventTest extends BaseCaseTest {

    @TestRails(id = "1661")
    @Test (groups = {"regression"})
    public void BO_Operations_Before_Login_Management_1661(){
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 1. Verify page title displays Block/Unblock Events");
        Assert.assertEquals("Block/Unblock Events", page.lblPageTitle.getText(), "FAILED ! Page title is not displayed correctly, actual: " + page.lblPageTitle.getText());
        log("INFO: Executed completely");
    }

    @TestRails(id = "1662")
    @Test (groups = {"regression"})
    public void BO_Operations_Before_Login_Management_1662(){
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 1. Select filter criteria of Upline and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0),"");
        String userId = page.lblUplineUser.getAttribute("value");
        List<ArrayList<String>> lstDownlineApi = BlockUnblockEventUtils.getDownlineList(userId);
        List <String> lstDownlineUI = page.getListDownlineUsers();

        log("Validate when filter with Upline the result show with downline of selected Upline accordingly");
        for (int i = 0;i < lstDownlineApi.size(); i++) {
            Assert.assertTrue(lstDownlineApi.get(i).get(4).equalsIgnoreCase(lstDownlineUI.get(i)),"FAILED! List downline does not match expected: " + lstDownlineApi.get(i).get(4) +
                    " and actual: " + lstDownlineUI.get(i));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "2054")
    @Test (groups = {"regression"})
    public void BO_Operations_Before_Login_Management_2054(){
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 1. Select filter criteria of Sport and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(5),"");
        String userId = page.lblUplineUser.getAttribute("value");
        String sportId = page.lblSport.getAttribute("value");
        List<ArrayList<String>> lstLeagueAndEventApi = BlockUnblockEventUtils.getLeagueAndEventList(userId, sportId);
        List <String> lstCompetitionUI = page.getListCompetition();
        List <String> lstEventUI = page.getListEvent();

        log("Validate when filter with Sport the result show with competition/event belong to selected sport accordingly");
        for (int i = 0;i < lstLeagueAndEventApi.size(); i++) {
            Assert.assertTrue(lstLeagueAndEventApi.get(i).get(1).equalsIgnoreCase(lstEventUI.get(i)),"FAILED! List event does not match expected: " + lstLeagueAndEventApi.get(i).get(1) +
                    " actual: " + lstEventUI.get(i));
            Assert.assertTrue(lstLeagueAndEventApi.get(i).get(2).equalsIgnoreCase(lstCompetitionUI.get(i)),"FAILED! List competition does not match expected: " + lstLeagueAndEventApi.get(i).get(2) +
                    " actual: " + lstCompetitionUI.get(i));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "2055")
    @Test (groups = {"regression"})
    public void BO_Operations_Before_Login_Management_2055(){
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Operations > Block/Unblock Events");
        BlockUnblockEventPage page = backofficeHomePage.navigateBlockUnblockEvents();

        log("Step 1. Select filter criteria of Sport and observe");
        String user = page.ddbUplineUser.getFirstSelectedOption();
        page.filter(user, BOConstants.Operations.BlockUnblockEvent.SPORTS.get(0),
                BOConstants.Operations.BlockUnblockEvent.FILTER_PERIOD.get(1));
        List <String> lstEventDateTime = page.getListEventDateTime();
        String currentDate = DateUtils.getDate(0,"yyyy-MM-dd","GMT+7");

        log("Validate when filter with Sport the result show with competition/event belong to selected sport accordingly");
        for (int i = 0;i < lstEventDateTime.size(); i++) {
            String[] parts = lstEventDateTime.get(i).split(" ");
            Assert.assertTrue(parts[0].equalsIgnoreCase(currentDate),"FAILED! Event Date Time is not in today expected: " + currentDate +
                    " actual: " + parts[0]);
        }
        log("INFO: Executed completely");
    }

}
