package agentsite.testcase.riskmanagement;

import agentsite.objects.agent.AgentExposureLimitInfo;
import agentsite.pages.riskmanagement.AgentExposureLimitPage;
import agentsite.pages.riskmanagement.IPMonitoringPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.riskmanagement.AgentExposureLimitUtils;
import agentsite.ultils.riskmanagement.IPMonitoringUtils;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.*;
import static common.AGConstant.HomePage.REPORT;
import static common.AGConstant.HomePage.RISK_MANAGEMENT;
import static common.AGConstant.RiskManagement.AgentExposureLimit.DOWNLINE_TABLE_HEADER;

public class IPMonitoringTest extends BaseCaseTest {
    @TestRails(id = "29737")
    @Test(groups = {"regression_fair999, regression_f24"})
    public void Agent_RM_IPMonitoringTest_29737() {
        log("@title: Validate that IP Monitoring page under Risk Management");
        log("Step 1. Navigate Risk Management");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Verify Verify IP Monitoring displays under the expanded section");
        Assert.assertTrue(page.leftMenu.isListSubMenuDisplayCorrect(RISK_MANAGEMENT));
        log("INFO: Executed completely");
    }

    @TestRails(id = "61998")
    @Test(groups = {"regression_sat, regression_fs"})
    public void Agent_RM_IPMonitoringTest_61998() {
        log("@title: Validate that IP Monitoring page under Report (SAT/Funsport)");
        log("Step 1. Navigate Report");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Verify IP Monitoring displays under the expanded section");
        Assert.assertTrue(page.leftMenu.isListSubMenuDisplayCorrect(REPORT));
        log("INFO: Executed completely");
    }

    @TestRails(id = "29738")
    @Test(groups = {"regression"})
    public void Agent_RM_IPMonitoringTest_29738() {
        log("@title: Validate UI on IP Monitoring page");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Validate UI on IP Monitoring page displays correct");
        page.verifyUIDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "29739")
    @Test(groups = {"regression"})
    public void Agent_RM_IPMonitoringTest_29739() {
        log("@title: Validate able to filter by Live Status");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Click on Login Status and filter with Live then click Submit");
        page.filter("Live","","");
        log("Validate 2. Result is filtered according to Login Status");
        page.verifyFilterResultCorrect("Live","","");
        log("Step 3. Click on Login Status and filter with Last 7 Days then click Submit");
        page.filter("Last 7 days","","");
        log("Validate 3. Result is filtered according to Login Status");
        page.verifyFilterResultCorrect("Last 7 days","","");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29740")
    @Test(groups = {"regression"})
    public void Agent_RM_IPMonitoringTest_29740() {
        log("@title: Validate able to filter by IP Address");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Click on IP Address and filter with a specific address then click Submit");
        String ip = IPMonitoringUtils.getLoginIp(RiskManagement.IPMonitoring.MAP_FILTER_STATUS.get("Last 7 days"));
        page.filter("Last 7 days", ip,"");
        log("Validate 2. Result is filtered according to IP Address inputted");
        page.verifyFilterResultCorrect("Last 7 days", ip,"");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29741")
    @Test(groups = {"regression2"})
    @Parameters({"memberAccount"})
    public void Agent_RM_IPMonitoringTest_29741(String memberAccount) {
        log("@title: Validate able to filter by Username");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Click on Username and filter with a specific name then click Submit");
        page.filter("Last 7 days", "",memberAccount);
        log("Validate 2. Result is filtered according to Username inputted");
        page.verifyFilterResultCorrect("Last 7 days", "",memberAccount);
        log("INFO: Executed completely");
    }
}
