package agentsite.testcase.agencymanagement;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.agentmanagement.createcompany.CreateCompanyPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.ArrayList;
import java.util.List;

import static common.AGConstant.HomePage.AGENCY_MANAGEMENT;
import static common.AGConstant.HomePage.CREATE_DOWNLINE_AGENT;

public class CreateCompanyPageTest extends BaseCaseTest {
    @TestRails(id = "3657")
    @Test (groups = {"smoke1"})
    public void Agent_AM_CreateDownline_Agent_3657() {
        log("@title: Validate UI in Create Downline Agent with Exchange Product setting");
        log("Step 1. Navigate Agency Management > Create Downline Agent");
        CreateCompanyPage page = agentHomePage.navigateCreateCompanyPage(environment.getSecurityCode());

        log("Verify 1. Account info section");
        Assert.assertEquals(page.header.lblPageTitle.getText().trim(), AGConstant.AgencyManagement.CreateDownlineAgent.TITLE_PAGE, "Failed! Page title is incorrect");
        page.accountInforSection.verifyUIDisplayedCorrect();

        log("INFO: Executed completely");
    }

}

