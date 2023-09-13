package agentsite.testcase.agencymanagement;

import agentsite.pages.agentmanagement.createcompany.CreateCompany;
import baseTest.BaseCaseTest;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

public class CreateCompanyPageTest extends BaseCaseTest {
    @TestRails(id = "3657")
    @Test (groups = {"smoke"})
    public void Agent_AM_CreateDownline_Agent_3657() {
        log("@title: Validate UI in Create Downline Agent with Exchange Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateCompany page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());

        log("Verify 1. Account info section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.HomePage.CREATE_COMPANY, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();

        page.accountBalanceTransferConditionInforSection.verifyUIDisplayCorrect();

        log("INFO: Executed completely");
    }

}

