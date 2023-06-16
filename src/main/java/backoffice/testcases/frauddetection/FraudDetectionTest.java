package backoffice.testcases.frauddetection;

import backoffice.common.BOConstants;
import backoffice.pages.bo.frauddetection.FraudDetectionPage;
import backoffice.utils.FraudDetection.FraudDetectionUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FraudDetectionTest extends BaseCaseTest {

    /**
     * @title: Validate can search fraud by Event Date
     * @pre-condition: 1.  Login BO
     * @steps: 1. Access Fraud Detection > Fraud Detection
     * 2. Select search by Event Date
     * 3. Select Event date is today
     * Sport: any sport
     * Competition : any competition
     * Event: any event
     * Market: any market
     * Status: All
     * Click Search
     * @expect: 1. Data will display if the event have fraud bet, otherwise display the message "No record found"
     */
    @TestRails(id = "602")
    @Test(groups = {"smoke"})
    public void BO_Fraud_Detection_001() {
        log("@title: Validate can search fraud by Event Date");
        log("Step 1. Access Fraud Detection > Fraud Detection");
        FraudDetectionPage page = backofficeHomePage.navigateFraudDetection();
        String date = DateUtils.getDate(1, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = FraudDetectionUtils.getListSportHasPlace(date, "WON");
        if (Objects.isNull(lstSport)) {
            System.out.println("By passed as no sport data has Fraud today");
            return;
        }
        String sportID = lstSport.get(0).get(0);
        String sportName = lstSport.get(0).get(1);
        List<ArrayList<String>> lstCompetition = FraudDetectionUtils.getListCompetition(date, sportID, "");
        String competitionName = lstCompetition.get(0).get(1);

        log("Step 2. Select search by Event Date");
        log("Step 3. Select Event date is today " +
                "Sport: any sport\n" +
                "Competition : any competition\n" +
                "Event: any event\n" +
                "Market: any market\n" +
                "Status: All\n" +
                "Click Search");
        page.searchByEventDate("", sportName, competitionName, "", "", "All", "", "All");

        log("Verify 1. Data will display if the event have fraud bet, otherwise display the message \"No record found\"");
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.tblReport.getRowsWithoutHeader(1, false).get(0).get(0), BOConstants.NO_RECORD_FOUND, "Failed! No record label is incorrect");
        } else {
            Assert.assertTrue(page.tblReport.getRowsWithoutHeader(20, false).size() > 0, "FAILED! There is no result when has data");
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search fraud by Place Date
     * @pre-condition: 1.  Login BO
     * @steps: 1. Access Fraud Detection > Fraud Detection
     * 2. Select search by Place Date
     * 3. Select place date is today
     * Sport: any sport
     * Status: All
     * Click Search
     * @expect: 1. Data will display if the event have fraud bet, otherwise display the message "No record found"
     */
    @TestRails(id = "603")
    @Test(groups = {"smoke"})
    public void BO_Fraud_Detection_002() {
        log("@title: Validate can search fraud by Event Date");
        String date = DateUtils.getDate(0, "yyyy-MM-dd", BOConstants.GMT_FOUR);
        List<ArrayList<String>> lstSport = FraudDetectionUtils.getListSportHasPlace(date, "WON");
        if (Objects.isNull(lstSport)) {
            System.out.println("By passed as no sport data has Fraud today");
            return;
        }
        String sportName = lstSport.get(0).get(1);

        log("Step 1. Access Fraud Detection > Fraud Detection");
        FraudDetectionPage page = backofficeHomePage.navigateFraudDetection();

        log("Step 2. Select search by Event Date");
        log("Step 3. Select Event date is today " +
                "Sport: any sport\n" +
                "Competition : any competition\n" +
                "Event: any event\n" +
                "Market: any market\n" +
                "Status: All\n" +
                "Click Search");
        page.searchByPlacedDate("", sportName, "All", "", "All");

        log("Verify 1. Data will display if the event have fraud bet, otherwise display the message \"No record found\"");
        if (page.lblNoRecord.isDisplayed()) {
            Assert.assertEquals(page.tblReport.getRowsWithoutHeader(1, false).get(0).get(0), BOConstants.NO_RECORD_FOUND, "Failed! No record label is incorrect");
        } else {
            Assert.assertTrue(page.tblReport.getRowsWithoutHeader(20, false).size() > 0, "FAILED! There is no result when has data");
        }

        log("INFO: Executed completely");
    }


}
