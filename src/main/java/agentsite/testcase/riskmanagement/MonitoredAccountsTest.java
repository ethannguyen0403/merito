package agentsite.testcase.riskmanagement;

import agentsite.pages.riskmanagement.MonitoredAccountsPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

import static common.AGConstant.*;
import static common.AGConstant.RiskManagement.AgentExposureLimit.DOWNLINE_TABLE_HEADER;

public class MonitoredAccountsTest extends BaseCaseTest {
    @TestRails(id = "72920")
    @Test (groups = {"http_request"})
    public void Agent_MonitoredAccountsTest_72920(){
        //Set isProxy = true
        log("@title: Validate There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        MonitoredAccountsPage page = agentHomePage.navigateMonitoredAccountsPage();
        log("Verify 1. Create Downline page is displayed without console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");
        log("INFO: Executed completely");
    }
}
