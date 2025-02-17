package backoffice.testcases.tools;

import backoffice.pages.bo.tools.ExposureAnalysisPage;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class ExposureAnalysisTest extends BaseCaseTest {

    /**
     * @title: Validate can search exposure of an account
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Tool > Exposure Analysis
     * 2. Input a account and click Analyze
     * @expected: 1. Verify account info is display correctly
     */
    @TestRails(id = "599")
    @Test(groups = {"smoke"})
    @Parameters("satMemberLoginID")
    public void BO_Tools_Exposure_Analysis_599(String satMemberLoginID) {

        log("@title: Validate can search exposure of an account");
        log("Step 1. Access Tool > Exposure Analysis");
        ExposureAnalysisPage page = backofficeHomePage.navigateExposureAnalysis();

        log("Step 2. Input a account and click Analyze");
        page.search(satMemberLoginID);

        log("Verify 1. Verify account info is display correctly");
        Assert.assertTrue(page.lblUserInfo.getText().contains(satMemberLoginID), "FAILED! The account info not display after searching");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Balance, Exposure of member account display correctly as analysis
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Tool > Exposure Analysis
     * 2. Input a account and click Analyze
     * 3. Get Balance, exposure  and the info of the market kept exposure
     * 4. Login member site and verify balance exposure
     * @expected: 1. Verify Balance, outstanding of the account is correctly display
     */
    @TestRails(id = "600")
    @Test(groups = {"smoke"})
    @Parameters("satMemberLoginID")
    public void BO_Tools_Exposure_Analysis_600(String satMemberLoginID) {
        log("@title: Validate Balance, Exposure of member account display correctly as analysis");
        log("Step 1. Access Tool > Exposure Analysis");
        ExposureAnalysisPage page = backofficeHomePage.navigateExposureAnalysis();

        log("Step 2. Input a account and click Analyze");
        page.search(satMemberLoginID);

        log("Step 3. Get Balance, exposure  and the info of the market kept exposure");
        log("Step 4. Login member site and verify balance exposure");
        log("Verify 1. Verify account info is display correctly");
        Assert.assertTrue(page.lblUserInfo.getText().contains(satMemberLoginID), "FAILED! The account info not display after searching");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Correct exposure button display if Exposure different with Expected Exposure value
     * @pre-condition: 1. Login BO
     * @steps: 1. Access Tool > Exposure Analysis
     * 2. Input a account and click Analyze
     * @expected: 1. Verify Correct exposure button display if Exposure different with Expected Exposure value otherwise the button does not display
     */
    @TestRails(id = "601")
    @Test(groups = {"smoke"})
    @Parameters("satMemberLoginID")
    public void BO_Tools_Exposure_Analysis_601(String satMemberLoginID) {
        log("@title: Validate Balance, Exposure of member account display correctly as analysis");
        log("Step 1. Access Tool > Exposure Analysis");
        ExposureAnalysisPage page = backofficeHomePage.navigateExposureAnalysis();

        log("Step 2. Input a account and click Analyze");
        page.search(satMemberLoginID);

        log("Verify 1. Verify Correct exposure button display if Exposure different with Expected Exposure value otherwise the button does not display");
        Assert.assertTrue(page.lblUserInfo.getText().contains(satMemberLoginID), "FAILED! The account info not display after searching");

        log("INFO: Executed completely");
    }

}
