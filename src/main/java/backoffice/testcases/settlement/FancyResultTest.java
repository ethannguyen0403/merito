package backoffice.testcases.settlement;

import backoffice.common.BOConstants;
import backoffice.pages.bo.settlement.FancyResultPage;
import backoffice.utils.settlement.FancyResultUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class FancyResultTest extends BaseCaseTest {
    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Settlement > Fancy Result
     * 2. Click Search button
     * @expect: 1. There is no http responded error returned
     */
    @TestRails(id = "591")
    @Test(groups = {"http_request"})
    public void BO_Settlement_FancyResult_591() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Settlement > Fancy Result");
        FancyResultPage page = backofficeHomePage.navigateFancyResult();

        log("Step 2: Click Search button");
        page.filter("");

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that this page loading is successful
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Settlement > Fancy Result
     * @expect: 1. Items on Event dropdown-box are loaded correctly
     * 2. Column names on this table are correct
     */
    @TestRails(id = "593")
    @Test(groups = {"smoke","MER.Maintenance.2024.V.5.0"})
    public void BO_Settlement_Fancy_Result_593() {
        log("@title: Validate that this page loading is successful");
        log("Step 1: Navigate Settlement > Fancy Result");
        FancyResultPage page = backofficeHomePage.navigateFancyResult();
        List<String> lstEvents = FancyResultUtils.getEvents(DateUtils.getDateBeforeCurrentDate(0, BOConstants.DASH_YYYY_MM_DD));

        boolean isEventsCorrect = page.ddbEvent.areOptionsMatched(lstEvents);
        List<String> lstHeaderNames = page.tblHeader.getColumnNamesOfTable();
        int expectedTotalColumns = BOConstants.Settlement.FancyResult.TABLE_HEADER.size();

        log("Verify 1: Items on Event dropdown-box are loaded correctly");
        log("Verify 2: Column names on this table are correct");
        Assert.assertTrue(isEventsCorrect, "ERROR: At least an item on Event ddb is incorrect");
        Assert.assertEquals(lstHeaderNames.size(), expectedTotalColumns, String.format("ERROR: The expected no of columns is '%s' but found '%s'", expectedTotalColumns, lstHeaderNames.size()));
        for (int i = 0; i < expectedTotalColumns; i++) {
            String observed = lstHeaderNames.get(i);
            String expected = BOConstants.Settlement.FancyResult.TABLE_HEADER.get(i);
            Assert.assertEquals(observed, expected, String.format("ERROR: The expected column name is '%s' but found '%s'", expected, observed));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that this page loading is successful
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Settlement > Fancy Result
     * 2. Filter Market name
     * @expect: 1. Data within Market Name - Status column returned is correct
     */
    @TestRails(id = "592")
    @Test(groups = {"smoke"})
    public void BO_Settlement_Fancy_Result_592() {
        log("@title: Validate that this page loading is successful");
        log("Step 1: Navigate Settlement > Fancy Result");
        FancyResultPage page = backofficeHomePage.navigateFancyResult();

        List<String> lstMarketNames = page.tblBody.getColumn(page.colMarketName, 2, false);
        if (lstMarketNames.size() < 1) {
            log("INFO: There is no records in this table");
            return;
        }
        String[] arr = lstMarketNames.get(0).split("\n");
        String marketName = arr[0];

        log("Step 2: Filter Market name " + marketName);
        page.filerOnTableHeader(marketName);
        List<String> lstResults = page.tblBody.getColumn(page.colMarketName, true);

        log("Verify 1: Data within Market Name - Status column returned is correct after searching");
        Assert.assertTrue(lstMarketNames.size() > 0, "ERROR: lstMarketNames is less than 1");
        for (String observed : lstResults) {
            String[] arrObserved = observed.split("\n");
            String observeMarket = arrObserved[0];
            Assert.assertTrue(observeMarket.contains(marketName), String.format("ERROR: The expected market name is '%s' but found '%s'", marketName, observeMarket));
        }
        log("INFO: Executed completely");
    }
}
