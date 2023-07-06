package backoffice.testcases.reports;

import backoffice.common.BOConstants;
import backoffice.pages.bo.reports.PunterPerformancePage;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class PunterPerformanceTest extends BaseCaseTest {

    /**
     * @title: Validate can drill down to member level
     * @pre-condition: 1. Log in successfully
     * @steps:1. Access Reports > Punter Performance
     * 2. Filter Today data
     * 3. Drill down to member level
     * @expect: 1. Can drill down to member level, at Member level there no hyperlink
     */
    @Test(groups = {"smoke"})
    public void BO_Report_Punter_Performance_001() {
        log("@title: Validate can drill down to member level");
        log("Step Access Reports > Punter Performance");
        String fromDate = DateUtils.getDate(-5, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        String toDate = DateUtils.getDate(-1, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        PunterPerformancePage page = backofficeHomePage.navigatePunterPerformance();

        log("Step 2. Filter Today data");
        page.filter(fromDate, toDate, "", "", "All");

        log("Step 3. Drill down to member level");
        page.drillDown("Member");

        log("Verify 1. Can drill down to member level, at Member level there no hyperlink");
        Assert.assertFalse(page.isUsernameAsHyperlink("Member"), "FAILED! Username displays as hyperlink when level is Member");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search data in Punter Performance
     * @pre-condition: 1. Login BO
     * @steps:  1. Access Reports > Punter Performance
     *          2. Leave all as default value and Click Search button
     * @expect: 1. There is data display. If no data display the message "No record found"
     */
    @TestRails(id = "3876")
    @Test(groups = {"Regression"})
    public void BO_Report_Punter_Performance_3876() {
        log("@title: Validate can search data in Punter Performance");
        log("Step 1. Access Reports > Punter Performance");
        PunterPerformancePage page = backofficeHomePage.navigatePunterPerformance();
        log("Step 2. Leave all as default value and Click Submit button");
        page.btnSubmit.click();
        log("Verify 1. There is data display. If no data display the message No record found");
        Assert.assertTrue(page.isDisplayData());
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate message display when no portal selected
     * @pre-condition: 1. Login BO
     * @steps:  1. Access Reports > Punter Performance
     *          2. Unselect any portal
     * @expect: 1. Verify the message "Please select at least 1 portal" display and Submit button is disable
     */
    @TestRails(id = "3877")
    @Test(groups = {"Regression"})
    public void BO_Report_Punter_Performance_3877() {
        log("@title: Validate message display when no portal selected");
        log("Step 1. Access Reports > Punter Performance");
        PunterPerformancePage page = backofficeHomePage.navigatePunterPerformance();
        log("Step 2. Unselect any portal");
        page.ddbPortal.deSelectAll(true);
        log("Verify 1. Verify the message \"Please select at least 1 portal\" display and Submit button is disable");
        Assert.assertEquals(page.lblAtLeast1.getText(),"Please select at least 1 portal");
        Assert.assertFalse(page.btnSubmit.isEnabled());
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate message display when no product selected
     * @pre-condition: 1. Login BO
     * @steps:  1. Access Reports > Punter Performance
     *          2. Unselect any product
     * @expect: 1. Verify the message "Please select at least 1 product" display and Submit button is disable
     */
    @TestRails(id = "3878")
    @Test(groups = {"Regression"})
    public void BO_Report_Punter_Performance_3878() {
        log("@title: Validate message display when no product selected");
        log("Step 1. Access Reports > Punter Performance");
        PunterPerformancePage page = backofficeHomePage.navigatePunterPerformance();
        log("Step 2. Unselect any product");
        page.ddbProduct.deSelectAll(true);
        log("Verify 1. Verify the message \"Please select at least 1 product\" display and Submit button is disable");
        Assert.assertEquals(page.lblAtLeast1.getText(),"Please select at least 1 product");
        Assert.assertFalse(page.btnSubmit.isEnabled());
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI display correctly
     * @pre-condition: 1. Login BO
     * @steps:  1. Access Reports > Punter Performance
     * @expect: 1. Verify UI display correctly:
     *             - From, To Product, Portal,  Type Currency, Submit
     *             - Verify label "You can see report data up to 6 months."
     *             - Verify table header display correctly
     */
    @TestRails(id = "3879")
    @Test(groups = {"Regression"})
    public void BO_Report_Punter_Performance_3879() {
        log("@title: Validate UI display correctly");
        log("Step 1. Access Reports > Punter Performance");
        PunterPerformancePage page = backofficeHomePage.navigatePunterPerformance();
        log("Verify 1. Verify UI display correctly");
        Assert.assertTrue(page.txtDateFrom.isDisplayed());
        Assert.assertTrue(page.txtDateTo.isEnabled());
        Assert.assertTrue(page.ddbTypeCurrency.isEnabled());
        Assert.assertTrue(page.btnSubmit.isEnabled());
        Assert.assertEquals(page.lblYouCanSeeReport.getText(),"You can see report data up to 6 months.");
        Assert.assertEquals(page.getHeaderName(),BOConstants.Reports.PunterPerformance.LST_HEADER);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate date range display correctly when searching with input from, to
     * @pre-condition: 1. Login BO
     * @steps:  1. Access Reports > Punter Performance
     *          2. Select From 08/06/2023 , To 17/06/2023 an Click Submit button
     * @expect: 1. Verify the result display in today the date range next to Punter performance is correctly (08/06/2023 ~ 17/06/2023)
     */
    @TestRails(id = "3880")
    @Test(groups = {"Regression"})
    public void BO_Report_Punter_Performance_3880() {
        log("@title: Validate date range display correctly when searching with input fromDay, toDay");
        log("Step 1. Access Reports > Punter Performance");
        PunterPerformancePage page = backofficeHomePage.navigatePunterPerformance();
        String fromDay = DateUtils.getDate(-4, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(-1, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        log("Step 2. Select From "+fromDay+" , To "+toDay+" an Click Submit button");
        page.filter(fromDay, toDay,"","","");
        log("Verify 1: Verify the result display ("+fromDay+" ~ "+toDay+")");
        Assert.assertEquals(page.lblDateRange.getText(), fromDay+" ~ "+toDay);
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate date Home link work
     * @pre-condition: 1. Login BO
     * @steps:  1. Access Reports > Punter Performance
     *          2. Filter in a range that having data
     *          3. Click on username in Portal level to drill-down
     *          4. Click on Home link
     * @expect: 1. Verify data is back in Portal level
     */
    @TestRails(id = "3881")
    @Test(groups = {"Regression"})
    public void BO_Report_Punter_Performance_3881() {
        log("@title: Validate date range display correctly when searching with input fromDay, to");
        log("Step 1. Access Reports > Punter Performance");
        PunterPerformancePage page = backofficeHomePage.navigatePunterPerformance();
        log("Step 2. Filter in a range that having data");
        String fromDay = DateUtils.getDate(-3, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        String toDay = DateUtils.getDate(-1, "dd/MM/yyyy", BOConstants.GMT_FOUR);
        page.filter(fromDay, toDay,"","","");
        log("Step 3. Click on username in Portal level to drill-down");
        page.drillDown("Company");
        log("Step 4. Click on Home link");
        page.btnHome.click();
        log("Verify 1: Verify data is back in Portal level");
        Assert.assertTrue(page.isUsernameAsHyperlink("Portal"));
        log("INFO: Executed completely");
    }

}
