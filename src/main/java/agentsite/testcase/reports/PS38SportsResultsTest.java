package agentsite.testcase.reports;

import agentsite.pages.report.PS38SportsResultsPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;


public class PS38SportsResultsTest extends BaseCaseTest {


    /**
     * @title: Validate can access the page from PO level
     * @pre-condition: 1. Log in PO level successfully
     * @steps: 1. Navigate Report > PS38 Sports Results
     * @expect: 1. Verify can access the page from PO level
     */
    @TestRails(id = "3870")
    @Test(groups = {"regression_po"})
    public void Agent_Report_Analysis_Of_Running_Markets_3870() {
        log("@title: Validate can access the page from PO level");
        log("Step 1. Navigate Report > PS38 Sports Results");
        PS38SportsResultsPage ps38SportsResultsPage = agentHomePage.navigatePS38SportsResultsPage();

        log("Verify 1: Verify can access the page from PO level");
        Assert.assertEquals(ps38SportsResultsPage.lblPS38SportsResults.getText(), "PS38 Sports Results");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can access the page from Agent Level which active Pinnacle Sportbook (username: 07L0000)
     * @pre-condition: 1. Log in Agent level successfully
     * @steps: 1. Navigate Report > PS38 Sports Results
     * @expect: 1. Verify can access the page from Agent level
     */
    @TestRails(id = "3871")
    @Test(groups = {"regression_ag"})
    public void Agent_Report_Analysis_Of_Running_Markets_3871() {
        log("@title: Validate can access the page from PO level");
        log("Step 1. Navigate Report > PS38 Sports Results");
        PS38SportsResultsPage ps38SportsResultsPage = agentHomePage.navigatePS38SportsResultsPage();

        log("Verify 1: Verify can access the page from Agent level");
        Assert.assertEquals(ps38SportsResultsPage.lblPS38SportsResults.getText(), "PS38 Sports Results");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can NOT access the page from Agent Level which active Pinnacle Sportbook (username: 07K0000)
     * @pre-condition: 1. Log in Agent level successfully
     * @steps:
     * @expect: 1. Verify can access the page from Agent level
     */
    @TestRails(id = "3872")
    @Test(groups = {"regression_ag"})
    public void Agent_Report_Analysis_Of_Running_Markets_3872() {
        log("@title: Validate can NOT access the page from PO level");
        log("Verify 1: Verify can not access the page from Agent level");
        Assert.assertFalse(agentHomePage.isDisplayPS38SportsResults());
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search result
     * @pre-condition: 1. Login Agent that inactive Pinnacle Sportsbook
     * @steps: 1. Click on PS38 Sports Result
     * 2. Select a date and a sport: Soccer
     * @expect: 1. Verify data display. If have no record, display the message : No records found
     */
    @TestRails(id = "3873")
    @Test(groups = {"regression"})
    public void Agent_Report_Analysis_Of_Running_Markets_3873() {
        log("@title: Validate can search result");
        log("Step 1. Click on PS38 Sports Result");
        PS38SportsResultsPage ps38SportsResultsPage = agentHomePage.navigatePS38SportsResultsPage();
        log("Step 2. Select a date and a sport: Soccer");
        ps38SportsResultsPage.searchByDateAndSports("20/06/2023", "Soccer");
        log("Verify 1: Verify display the message : No records found");
        Assert.assertEquals(ps38SportsResultsPage.lblNoRecordFound.getText(), "No records found.");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search result in today
     * @pre-condition: 1. Login Agent that inactive Pinnacle Sportsbook
     * @steps: 1. Click on PS38 Sports Result
     * 2. Select a date and a sport: Soccer and Click Today
     * @expect: 1. Verify Date textbox is display today
     * 2. The result display in today (verify no records founds)
     */
    @TestRails(id = "3874")
    @Test(groups = {"regression"})
    public void Agent_Report_Analysis_Of_Running_Markets_3874() {
        log("@title: Validate can search result in today");
        log("Step 1. Click on PS38 Sports Result");
        PS38SportsResultsPage ps38SportsResultsPage = agentHomePage.navigatePS38SportsResultsPage();
        log("Step 2. Select a date and a sport: Soccer and Click Today");
        ps38SportsResultsPage.searchToday("Soccer");
        log("Verify 1: Verify Date textbox is display today");
        Assert.assertEquals(ps38SportsResultsPage.txtDate.getAttribute("value"), ps38SportsResultsPage.getTodayDate());
        log("Verify 2: Verify display the message : No records found");
        Assert.assertEquals(ps38SportsResultsPage.lblNoRecordFound.getText(), "No records found.");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search result in Yesterday
     * @pre-condition: 1. Login Agent that inactive Pinnacle Sportsbook
     * @steps: 1. Click on PS38 Sports Result
     * 2. Select a date and a sport: Soccer and Click Yesterday
     * @expect: 1. Verify Date textbox is display Yesterday date
     * 2. The result display in Yesterday (verify no records founds)
     */
    @TestRails(id = "3875")
    @Test(groups = {"regression"})
    public void Agent_Report_Analysis_Of_Running_Markets_3875() {
        log("@title: Validate can search result in Yesterday");
        log("Step 1. Click on PS38 Sports Result");
        PS38SportsResultsPage ps38SportsResultsPage = agentHomePage.navigatePS38SportsResultsPage();
        log("Step 2. Select a date and a sport: Soccer and Click Yesterday");
        ps38SportsResultsPage.searchYesterday("Soccer");
        log("Verify 1: Verify Date textbox is display Yesterday date");
        Assert.assertEquals(ps38SportsResultsPage.txtDate.getAttribute("value"), ps38SportsResultsPage.getYesterdayDate());
        log("Verify 2: Verify display the message : No records found");
        Assert.assertEquals(ps38SportsResultsPage.lblNoRecordFound.getText(), "No records found.");
        log("INFO: Executed completely");
    }

}
