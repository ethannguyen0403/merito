package agentsite.testcase.agencymanagement.downlinelisting;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.List;

public class EditDownlineAgentTest extends BaseCaseTest {


    /**
     * @title: Validate Cannot update if Max Player Credit exceed the limit
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any agent
     * 3. Input Max player Credit greater than the limit
     * @expect: 1. Verify Message "Max Player Credit is invalid" display
     */
    @TestRails(id = "696")
    @Test(groups = {"smoke_credit"})
    @Parameters({"level"})
    public void Agent_AM_Downline_Listing_Edit_Agent_696(String level) throws Exception {
        log("@title: Validate cannot update if Max Player Credit exceed the limit");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, level, "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any agent");
        page.downlineListing.searchDownline(loginID, "", "Agent");
        page.downlineListing.clickEditIcon(loginID);
        page.confirmSecurityCode(environment.getSecurityCode());

        log("Step 3. Input Max player Credit greater than the limit");
        String maxPlayerCreditLitmit = String.format("%d", page.editDownlinePopup.creditBalanceInforSection.getMaxPlayerLitmitCredit() + 1);
        page.editDownlinePopup.creditBalanceInforSection.updateCashBalance(maxPlayerCreditLitmit);
        page.editDownlinePopup.btnSubmit.click();

        log("Verify 1. Verify Message \"Max Player Credit is invalid\" display");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.DownlineListing.MSG_INVALID_MAX_PLAYER_CREDIT, "FAILED! Incorrect max player credit is invalid");

        log("INFO: Executed completely");
    }


}

