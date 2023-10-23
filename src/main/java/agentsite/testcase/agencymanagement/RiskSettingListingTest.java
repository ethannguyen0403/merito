package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.RiskSettingListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;


public class RiskSettingListingTest extends BaseCaseTest {

    @TestRails(id = "3641")
    @Test(groups = {"regression_credit"})
    public void Agent_AM_Tax_Setting_Listing_3641() {
        log("@title: Validate  Risk Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Risk Setting Listing");
        RiskSettingListingPage page = agentHomePage.navigateRiskSettingListingPage();

        log("Step 2. Validate UI on Risk Setting Listing display correctly");
        page.verifyUIDisplayCorrect();

        log("INFO: Executed completely");
    }

    @TestRails(id = "3642")
    @Test(groups = {"regression_credit"})
    public void Agent_AM_Tax_Setting_Listing_3642() {
        log("@title: Validate  Risk Setting Listing UI display correct");
        log("Step 1. Navigate Agency Management > Risk Setting Listing");
        AccountInfo accountInfo = ProfileUtils.getProfile();
        List<AccountInfo> lstDownline = DownLineListingUtils.getAllDownLineUsers(_brandname, accountInfo.getUserCode(), accountInfo.getUserID());
        RiskSettingListingPage page = agentHomePage.navigateRiskSettingListingPage();

        log("Step 2. Validate UI on Risk Setting Listing display correctly");
        page.openEditPopup(lstDownline.get(0).getUserCode());
        String exposureValue = page.getMaxExposure();
        try {
            page.updateExposure(exposureValue, true);
            Assert.assertTrue(page.isExposureUpdatedCorrect(lstDownline.get(0).getUserCode(), exposureValue));
        } finally {
            page.openEditPopup(lstDownline.get(0).getUserCode());
            page.updateExposure("0", true);
        }
        log("INFO: Executed completely");
    }


}

