package agentsite.testcase.all.riskmanagement;

import agentsite.objects.agent.AgentExposureLimitInfo;
import agentsite.pages.all.riskmanagement.AgentExposureLimitPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.riskmanagement.AgentExposureLimitUtils;
import baseTest.BaseCaseMerito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static agentsite.common.AGConstant.*;
import static agentsite.common.AGConstant.HomePage.*;
import static agentsite.common.AGConstant.RiskManagement.AgentExposureLimit.DOWNLINE_TABLE_HEADER;

public class AgentExposureTest extends BaseCaseMerito {
    /***
     * This test cases only available for F24 and Betclub9
     */

    @Test(groups = {"smoke5","smokePO"})
    public void Agent_RM_AgentExposureTest_TC001() {
        log("@title: Verify UI display correctly");
        log("Step 1. Navigate Risk Management > Agent Exposure Limit");
        AgentExposureLimitPage page = agentHomePage.clickSubMenu(RISK_MANAGEMENT,AGENT_EXPOSURE_LIMIT, AgentExposureLimitPage.class);

        log("Verify 1 Verify UI is correctly displayed");
        Assert.assertEquals(page.lblUsername.getText(),LBL_USERNAME,"FAILED! Username label is incorrect displayed");
        Assert.assertEquals(page.btnSubmit.getText(),BTN_SUBMIT,"FAILED! Username label is incorrect displayed");
        Assert.assertEquals(page.lblUsername.getAttribute("placeholder"),LBL_USERNAME_PLACE_HOLDER,"FAILED!");
        Assert.assertEquals(page.tblDownline.getHeaderNameOfRows(),DOWNLINE_TABLE_HEADER,"FAILED! Table header is incorrect");

        log("INFO: Executed completely");
    }

    @Test(groups = {"smokePO"})
    public void Agent_RM_AgentExposureTest_TC002() {
        log("@title:Verify can drill donw to agent level");
        log("Step 1.Navigate Risk Management > Agent Exposure Limit");
        String userID = ProfileUtils.getProfile().getUserID();
        AgentExposureLimitPage page = agentHomePage.clickSubMenu(RISK_MANAGEMENT,AGENT_EXPOSURE_LIMIT, AgentExposureLimitPage.class);
        List<AgentExposureLimitInfo> lstAgent= AgentExposureLimitUtils.getAgentExposureList(userID);

        log("Step 2. Drill down to agent level");
        page.clickUserName(lstAgent.get(0).getUsername());

        log("Verify 1/ Can drill down to agent level");


        log("INFO: Executed completely");
    }

    @Test(groups = {"smoke","smokePO"})
    public void Agent_RM_AgentExposureTest_TC003() {
        log("@title: Verify can open bet list in PT Mode");
        log("Step 1.Navigate Risk Management > Agent Exposure Limit");
        String userID = ProfileUtils.getProfile().getUserID();
        AgentExposureLimitPage page = agentHomePage.clickSubMenu(RISK_MANAGEMENT,AGENT_EXPOSURE_LIMIT, AgentExposureLimitPage.class);
        List<AgentExposureLimitInfo> lstAgent= AgentExposureLimitUtils.getAgentExposureList(userID);

        log("Step 2.Search an agent level under login account");
        page.search(lstAgent.get(0).getUsername());

        log("Verify 1: Verify account is display");
        Assert.assertEquals(page.tblDownline.getColumn(2,false).size(),1,"FAILED! More than 1 resuld when searchiing exactly username");
        Assert.assertEquals(page.tblDownline.getColumn(2,false).get(0),lstAgent.get(0).getUsername(),"FAILED! Username is incorrect after searching");
        log("INFO: Executed completely");
    }
}
