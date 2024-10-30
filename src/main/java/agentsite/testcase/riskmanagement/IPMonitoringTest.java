package agentsite.testcase.riskmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.RiskSettingListingPage;
import agentsite.pages.riskmanagement.IPMonitoringPage;
import agentsite.pages.riskmanagement.MonitoredAccountsPage;
import agentsite.ultils.riskmanagement.IPMonitoringUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;
import java.util.ArrayList;
import java.util.List;
import static common.AGConstant.*;
import static common.AGConstant.HomePage.REPORT;
import static common.AGConstant.HomePage.RISK_MANAGEMENT;
import static common.AGConstant.RiskManagement.IPMonitoring.*;

public class IPMonitoringTest extends BaseCaseTest {
    @TestRails(id = "29737")
    @Test(groups = {"regression_fair999", "regression_f24"})
    public void Agent_RM_IPMonitoringTest_29737() {
        log("@title: Validate that IP Monitoring page under Risk Management");
        log("Step 1. Navigate Risk Management");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Verify Verify IP Monitoring displays under the expanded section");
        Assert.assertTrue(page.leftMenu.isListSubMenuDisplayCorrect(RISK_MANAGEMENT));
        log("INFO: Executed completely");
    }

    @TestRails(id = "61998")
    @Test(groups = {"regression_sat", "regression_fs"})
    public void Agent_RM_IPMonitoringTest_61998() {
        log("@title: Validate that IP Monitoring page under Report (SAT/Funsport)");
        log("Step 1. Navigate Report");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Verify IP Monitoring displays under the expanded section");
        Assert.assertTrue(page.leftMenu.isListSubMenuDisplayCorrect(REPORT));
        log("INFO: Executed completely");
    }

    @TestRails(id = "29738")
    @Test(groups = {"regression", "MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29738() {
        log("@title: Validate UI on IP Monitoring page");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Validate UI on IP Monitoring page displays correct");
        page.verifyUIDisplayCorrect();
        log("INFO: Executed completely");
    }

    @TestRails(id = "29739")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29739() {
        log("@title: Validate able to filter by Live Status");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Click on Login Status and filter with Live then click Submit");
        page.filter(FILTER_LIST.get(0),"","");
        log("Validate 2. Result is filtered according to Login Status");
        page.verifyFilterResultCorrect(FILTER_LIST.get(0),"","");
        log("Step 3. Click on Login Status and filter with Last 7 Days then click Submit");
        page.filter(FILTER_LIST.get(1),"","");
        log("Validate 3. Result is filtered according to Login Status");
        page.verifyFilterResultCorrect(FILTER_LIST.get(1),"","");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29740")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29740() {
        log("@title: Validate able to filter by IP Address");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Click on IP Address and filter with a specific address then click Submit");
        String ip = IPMonitoringUtils.getLoginIp(RiskManagement.IPMonitoring.MAP_FILTER_STATUS.get("Last 7 days"));
        page.filter(FILTER_LIST.get(1), ip,"");
        log("Validate 2. Result is filtered according to IP Address inputted");
        page.verifyFilterResultCorrect(FILTER_LIST.get(1), ip,"");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29741")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    @Parameters({"memberAccount"})
    public void Agent_RM_IPMonitoringTest_29741(String memberAccount) {
        log("@title: Validate able to filter by Username");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Click on Username and filter with a specific name then click Submit");
        page.filter(FILTER_LIST.get(1), "",memberAccount);
        log("Validate 2. Result is filtered according to Username inputted");
        page.verifyFilterResultCorrect(FILTER_LIST.get(1), "",memberAccount);
        log("INFO: Executed completely");
    }

    @TestRails(id = "29742")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29742() {
        log("@title: Validate able to Suspend user");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Select any record of user and click on Suspend button then observe");
        page.filter(FILTER_LIST.get(1), "", "");
        List<String> lstUsers = page.getListUsernameResult();
        try {
            page.actionOnUser(lstUsers.get(0), ACTION_SUSPEND, true);

            log("Validate 2. User record is shown with Unsuspend button");
            page.verifyActionButtonShowCorrect(lstUsers.get(0),ACTION_UNSUSPEND);

            log("Step 3. Navigate to Downline Listing and search the Suspended user then observe");
            DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
            downLineListingPage.searchDownline(lstUsers.get(0).split(",")[0], "","");

            log("Validate 3. User status is Suspended");
            String accountStatus = downLineListingPage.getAccountStatus(lstUsers.get(0).split(",")[0]);
            Assert.assertEquals(accountStatus, "Suspended", "FAILED! Account status is not Suspended");
        } finally {
            log("Post-condition: Update account suspended back to active");
            agentHomePage.navigateIPMonitoringPage();
            page.filter(FILTER_LIST.get(1), "", "");
            page.actionOnUser(lstUsers.get(0), ACTION_UNSUSPEND, true);
            log("INFO: Executed completely");
        }
    }
    @TestRails(id = "29743")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29743() {
        log("@title: Validate able to Unsuspend user");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        page.filter(FILTER_LIST.get(1), "", "");
        List<String> lstUsers = page.getListUsernameResult();
        try {
            log("Precondition: Suspend an user before do unsuspend");
            page.actionOnUser(lstUsers.get(0), ACTION_SUSPEND, true);
            log("Step 1. Search the suspended user and click on Unsuspended button of the user then observe");
            page.actionOnUser(lstUsers.get(0), ACTION_UNSUSPEND, true);

            log("Validate 2. User record is shown with Suspend button");
            page.verifyActionButtonShowCorrect(lstUsers.get(0),ACTION_SUSPEND);

            log("Step 3. Navigate to Downline Listing and search the Suspended user then observe");
            DownLineListingPage downLineListingPage = agentHomePage.navigateDownlineListingPage();
            downLineListingPage.searchDownline(lstUsers.get(0).split(",")[0], "","");

            log("Validate 3. User status is not Suspended");
            String accountStatus = downLineListingPage.getAccountStatus(lstUsers.get(0).split(",")[0]);
            Assert.assertTrue(!accountStatus.equalsIgnoreCase("Suspended"), "FAILED! Account status is Suspended");
            log("INFO: Executed completely");
        } catch (Exception e) {
            log("Post-condition: Unsuspend the suspended account");
            agentHomePage.navigateIPMonitoringPage();
            page.filter(FILTER_LIST.get(1), "", "");
            page.actionOnUser(lstUsers.get(0), ACTION_UNSUSPEND, true);
        }
    }

    @TestRails(id = "29744")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    @Parameters({"username","downlineAccount","password"})
    public void Agent_RM_IPMonitoringTest_29744(String username, String downlineAccount, String password) {
        log("@title: Validate lower agent level cannot unsuspend the user suspended from higher level");
        log("Precondition: Suspend an user by level SMA");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        page.filter(FILTER_LIST.get(1), "", "");
        List<String> lstUsers = page.getListUsernameResult();
        try {
            page.actionOnUser(lstUsers.get(0), ACTION_SUSPEND, true);
            log("Step 1. Login with lower agent level (e.g MA)");
            page.logout();
            HomePage agentHomePage = loginAgent(downlineAccount, password, _brandname);
            log("Step 2. Navigate to IP Monitoring page and search the Suspended account");
            agentHomePage.navigateIPMonitoringPage();
            page.filter(FILTER_LIST.get(1), "", lstUsers.get(0).split(",")[0]);
            log("Validate 2. Suspend button is disabled");
            page.verifySuspendButtonState(lstUsers.get(0),false);
            log("INFO: Executed completely");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + e.getCause());
        } finally {
            log("Post-condition: Unsuspend the suspended account");
            page.logout();
            try {
                HomePage agentHomePage = loginAgent(username, password, _brandname);
                agentHomePage.navigateIPMonitoringPage();
                page.filter(FILTER_LIST.get(1), "", lstUsers.get(0).split(",")[0]);
                page.actionOnUser(lstUsers.get(0), ACTION_UNSUSPEND, true);
            } catch (Exception e) {
                log("Post condition failure");
                throw new RuntimeException(e.getMessage() + e.getCause());
            }
        }
    }

    @TestRails(id = "29745")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29745() {
        log("@title: Validate able to Monitor user");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        page.filter(FILTER_LIST.get(1), "", "");
        List<String> lstUsers = page.getListUsernameResult();
        try {
            log("Step 2. Select any record of user and click on Monitor button then observe");
            page.actionOnUser(lstUsers.get(0), ACTION_MONITOR, true);

            log("Validate 2. User record is shown with Un-monitor button");
            page.verifyActionButtonShowCorrect(lstUsers.get(0), ACTION_UNMONITOR);

            log("Step 3. Access to Risk Management > Monitored Account and search the monitored user then observe");
            MonitoredAccountsPage monitoredAccountsPage = agentHomePage.navigateMonitoredAccountsPage();
            monitoredAccountsPage.filter("", lstUsers.get(0).split(",")[0], true);

            log("Validate 3. User monitored is displayed in the page");
            monitoredAccountsPage.verifyFilterResultCorrect("",lstUsers.get(0).split(",")[0]);

        } finally {
            log("Post-condition: Update account monitored back to un-monitored");
            agentHomePage.navigateIPMonitoringPage();
            page.filter(FILTER_LIST.get(1), "", lstUsers.get(0).split(",")[0]);
            page.actionOnUser(lstUsers.get(0), ACTION_UNMONITOR, true);
            log("INFO: Executed completely");
        }
    }

    @TestRails(id = "29746")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29746() {
        log("@title: Validate able to Un-Monitor user");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        page.filter(FILTER_LIST.get(1), "", "");
        List<String> lstUsers = page.getListUsernameResult();
        log("Precondition: Monitor for an user in IP Monitoring page");
        page.actionOnUser(lstUsers.get(0), ACTION_MONITOR, true);

        log("Step 2. Select the monitored user then click on Un-monitor button then observe");
        page.actionOnUser(lstUsers.get(0), ACTION_UNMONITOR, true);
        log("Validate 2. User record is shown with Monitor button");
        page.verifyActionButtonShowCorrect(lstUsers.get(0), ACTION_MONITOR);

        log("Step 3. Access to Risk Management > Monitored Account and search the un-monitored user then observe");
        MonitoredAccountsPage monitoredAccountsPage = agentHomePage.navigateMonitoredAccountsPage();
        monitoredAccountsPage.filter("", lstUsers.get(0).split(",")[0], true);

        log("Validate 3. User un-monitored is displayed in the page with status Cleared");
        monitoredAccountsPage.verifyFilterResultCorrect("Cleared",lstUsers.get(0).split(",")[0]);
        log("INFO: Executed completely");
    }

    @TestRails(id = "29747")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29747() {
        log("@title: Validate the IP address displays correctly");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Search the detected player and observe IP Address");
        String ip = IPMonitoringUtils.getLoginIp(RiskManagement.IPMonitoring.MAP_FILTER_STATUS.get("Last 7 days"));
        page.filter(FILTER_LIST.get(1), ip,"");
        log("Validate 2. IP address displays correctly with the ip value of detected user");
        page.verifyFilterResultCorrect(FILTER_LIST.get(1), ip,"");
        log("INFO: Executed completely");
    }

    @TestRails(id = "29748")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    @Parameters({"memberAccount"})
    public void Agent_RM_IPMonitoringTest_29748(String memberAccount) {
        log("@title: Validate the User Name displays correctly");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Search the detected player and observe user name");
        page.filter(FILTER_LIST.get(1), "",memberAccount);
        log("Validate 2. Displays with user name (nick name)");
        page.verifyFilterResultCorrect(FILTER_LIST.get(1), "",memberAccount);
        log("INFO: Executed completely");
    }

    @TestRails(id = "29749")
    @Test(groups = {"regression","MER.Implementation.V.2.0"})
    public void Agent_RM_IPMonitoringTest_29749() {
        log("@title: Validate the Bet Count displays correctly");
        log("Precondition: Having player detected in Live/ Last 7 Days with bet settled or placed in any product");
        log("Step 1. Navigate to IP Monitoring page");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Step 2. Search the detected player and observe bet count");
        ArrayList<List> lstUsersHasBet = IPMonitoringUtils.getListUserHasBet(RiskManagement.IPMonitoring.MAP_FILTER_STATUS.get("Last 7 days"));
        page.filter(FILTER_LIST.get(1), "","");
        log("Validate 2. Displays with bets that the detected players placed and settled within the last 7 days (all products)");
        page.verifyUserDetailInfo(lstUsersHasBet.get(0));
        log("INFO: Executed completely");
    }
    @TestRails(id = "69450")
    @Test(groups = {"http_request"})
    public void Agent_RM_IPMonitoringTest_69450(){
        log("Step 1. Navigate Report > IP Monitoring");
        IPMonitoringPage page = agentHomePage.navigateIPMonitoringPage();
        log("Verify 1: Validate there is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
    }
}
