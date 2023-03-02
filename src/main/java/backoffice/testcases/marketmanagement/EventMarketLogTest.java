package backoffice.testcases.marketmanagement;

import backoffice.pages.bo.marketmanagement.EventMarketLogPage;
import backoffice.pages.bo.operations.MixedPTConfigurationPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class EventMarketLogTest extends BaseCaseTest {

    @TestRails(id = "1651")
    @Test (groups = {"regression"})
    public void BO_Market_Management_EventMarketLog_1651(){
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1. Access Market Management > Event Market Log");
        EventMarketLogPage page = backofficeHomePage.navigateEventMarketLog();

        log("Verify page title displays with Event/Market Log");
        Assert.assertEquals("  Event/Market Log", page.lblPageTitle.getText(), "FAILED ! Page title is not displayed correctly, actual: " + page.lblPageTitle.getText());

        log("INFO: Executed completely");
    }

    @TestRails(id = "1652")
    @Test (groups = {"regression"})
    public void BO_Market_Management_EventMarketLog_1652(){
        log("@title: Validate can refresh");
        log("Step 1. Access Market Management > Event Market Log");
        EventMarketLogPage page = backofficeHomePage.navigateEventMarketLog();
        log("Step 2. Select type = Market then refresh page");
        page.ddpType.selectByVisibleText("Market");
        page.refresh();
        log("Verify Market dropdown does not display after refresh page");
        Assert.assertFalse(page.ddpMarket.isDisplayed(),"FAILED! Market dropdown displays in page");

        log("INFO: Executed completely");
    }
}
