package agentsite.testcase.reports;

import agentsite.pages.report.ViewLogPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class ViewLogTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > View Log
     * @expect: 1. Win Loss Simple page is displayed
     */
    @TestRails(id="815")
    @Test(groups = {"http_request"})
    public void Agent_Report_View_Log_001(){
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Report > View Log");
        ViewLogPage page = agentHomePage.navigateViewLogPage();

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate View Log UI display correctly
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Report > View Log
     * @expect: 1.Validate View Log UI display correctly
     */
    @TestRails(id="816")
    @Test(groups = {"smoke"})
    public void Agent_Report_View_Log_002(){
        log("@title: Validate View Log UI display correctly");
        log("Step 1: Navigate Report > View Log");
        ViewLogPage page = agentHomePage.navigateViewLogPage();

        log("Verify: Validate View Log UI display correctly");
       Assert.assertEquals( page.header.lblPageTitle.getText(),"View Log","FAILED! Page title is incorrect");
        log("INFO: Executed completely");
    }

}
