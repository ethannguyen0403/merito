package agentsite.testcase.riskmanagement;

import agentsite.objects.agent.AgentExposureLimitInfo;
import agentsite.pages.riskmanagement.AgentExposureLimitPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.riskmanagement.AgentExposureLimitUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.*;
import static common.AGConstant.RiskManagement.AgentExposureLimit.DOWNLINE_TABLE_HEADER;

public class AgentExposureTest extends BaseCaseTest {
    /***
     * This test cases only available for F24 and Betclub9
     */
    @TestRails(id = "838")
    @Test(groups = {"smoke_po"})
    public void Agent_RM_AgentExposureTest_838() {
        log("@title: Verify UI display correctly");
        log("Step 1. Navigate Risk Management > Agent Exposure Limit");
        AgentExposureLimitPage page = agentHomePage.navigateAgentExposureLimitPage();
        log("Verify 1 Verify UI is correctly displayed");
        Assert.assertEquals(page.lblUsername.getText(), LBL_USERNAME, "FAILED! Username label is incorrect displayed");
        Assert.assertEquals(page.btnSubmit.getText(), BTN_SUBMIT, "FAILED! Username label is incorrect displayed");
        Assert.assertEquals(page.lblUsername.getAttribute("placeholder"), LBL_USERNAME_PLACE_HOLDER, "FAILED!");
        Assert.assertEquals(page.tblDownline.getHeaderNameOfRows(), DOWNLINE_TABLE_HEADER, "FAILED! Table header is incorrect");

        log("INFO: Executed completely");
    }
    @TestRails(id = "839")
    @Test(groups = {"smoke_po"})
    public void Agent_RM_AgentExposureTest_839() {
        log("@title:Verify can drill donw to agent level");
        log("Step 1.Navigate Risk Management > Agent Exposure Limit");
        String userID = ProfileUtils.getProfile().getUserID();
        AgentExposureLimitPage page = agentHomePage.navigateAgentExposureLimitPage();
        List<AgentExposureLimitInfo> lstAgent = AgentExposureLimitUtils.getAgentExposureList(userID);

        log("Step 2. Drill down to agent level");
        page.clickUserName(lstAgent.get(0).getUsername());

        log("Verify 1/ Can drill down to agent level");


        log("INFO: Executed completely");
    }
    @TestRails(id = "840")
    @Test(groups = {"smoke_po"})
    public void Agent_RM_AgentExposureTest_840() {
        log("@title: Verify can open bet list in PT Mode");
        log("Step 1.Navigate Risk Management > Agent Exposure Limit");
        String userID = ProfileUtils.getProfile().getUserID();
        AgentExposureLimitPage page = agentHomePage.navigateAgentExposureLimitPage();
        List<AgentExposureLimitInfo> lstAgent = AgentExposureLimitUtils.getAgentExposureList(userID);

        log("Step 2.Search an agent level under login account");
        page.search(lstAgent.get(0).getUsername());

        log("Verify 1: Verify account is display");
        Assert.assertEquals(page.tblDownline.getColumn(2, false).size(), 1, "FAILED! More than 1 resuld when searchiing exactly username");
        Assert.assertEquals(page.tblDownline.getColumn(2, false).get(0), lstAgent.get(0).getUsername(), "FAILED! Username is incorrect after searching");
        log("INFO: Executed completely");
    }
    @TestRails(id = "72919")
    @Test (groups = {"http_request"})
    public void Agent_RM_AgentExposureTest_72919(){
        //Set isProxy = true
        log("@title: Validate There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        AgentExposureLimitPage page = agentHomePage.navigateAgentExposureLimitPage();
        log("Verify 1. Create Downline page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");
        log("INFO: Executed completely");
    }
}
