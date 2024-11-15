package agentsite.testcase.agencymanagement;

import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class GroupListTest extends BaseCaseTest {
    @TestRails(id = "72963")
    @Test(groups = {"http_request"})
    public void Agent_PO_Group_List_72963() {
        //Set isProxy = true
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Group Listing");
        agentHomePage.navigateGroupListPage();
        log("Verify 1. Group Listing page is displayed with down console error");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console error display when accessing the page");
        log("INFO: Executed completely");
    }
}
