package agentsite.testcase.reports;

import agentsite.pages.report.ViewLogPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class ResettlementVoidLogTest extends BaseCaseTest {
    @TestRails(id = "72921")
    @Test(groups = {"http_request"})
    public void Agent_Resettlement_VoidLog_72921() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > View Log");
        agentHomePage.navigateResettlementVoidLogPage();
        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

}
