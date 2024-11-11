package agentsite.testcase.reports;

import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class WinLossAnalysisTest extends BaseCaseTest {
    @TestRails(id = "72965")
    @Test(groups = {"http_request"})
    public void Agent_Report_WinLossAnalysis_72965() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > Win Loss Analysis");
        agentHomePage.navigateWinLossAnalysisPage();
        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }
}
