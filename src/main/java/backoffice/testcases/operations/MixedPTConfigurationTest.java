package backoffice.testcases.operations;

import backoffice.pages.bo.operations.MixedPTConfigurationPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;
import java.util.List;

public class MixedPTConfigurationTest extends BaseCaseTest {

    @TestRails(id = "1649")
    @Test(groups = {"regression"})
    public void BO_Operations_MixedPT_Configuration_1649() {
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Operations > Mixed-PT Configuration");
        MixedPTConfigurationPage page = backofficeHomePage.navigateMixedPTConfiguration();

        log("Verify page title displays with Mixed-PT Configuration");
        Assert.assertEquals("Mixed-PT Configuration", page.lblPageTitle.getText(), "FAILED ! Page title is not displayed correctly, actual: " + page.lblPageTitle.getText());

        log("INFO: Executed completely");
    }

    @TestRails(id = "1650")
    @Test(groups = {"regression"})
    public void BO_Operations_MixedPT_Configuration_1650() {
        log("@title: Validate can filter");
        log("Step 1. Access Operations > Mixed-PT Configuration");
        MixedPTConfigurationPage page = backofficeHomePage.navigateMixedPTConfiguration();

        List<String> lstData = page.getFirstRowData();
        String userName = lstData.get(1);
        String brandName = lstData.get(6);
        log("Step 2. Filter with existing data");
        page.filter(brandName, userName);
        log("Verify result is filtered correctly with search input");
        page.verifyResultDisplaysCorrect(brandName, userName);

        log("INFO: Executed completely");
    }
}
