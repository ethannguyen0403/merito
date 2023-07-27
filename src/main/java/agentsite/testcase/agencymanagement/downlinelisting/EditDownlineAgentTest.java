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

    @Test(groups = {"http_request"})
    public void Agent_AM_Downline_Listing_Edit_Agent_001() throws Exception {
        log("@title: There is no http responded error returned");
        log("Step 1. Navigate Agency Management > Downline Listing");
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, ProfileUtils.getDownlineBalanceInfo().get(0).get(0), _brandname);
        String loginID = listAccount.get(0).getUserCode();
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Click on Edit icon of any agent");
        page.clickEditIcon(loginID);

        log("Verify There is no console error display");
        Assert.assertTrue(hasHTTPRespondedOK(), "FAILED! Console Error display");

        log("INFO: Executed completely");
    }

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
        page.searchDownline(loginID, "", "Agent");
        page.clickEditIcon(loginID);
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. Input Max player Credit greater than the limit");
        String maxPlayerCreditLitmit = String.format("%d", page.editDownlinePopup.balanceSection.getMaxPlayerLitmitCredit() + 1);
        page.editDownlinePopup.balanceSection.updateCashBalance(maxPlayerCreditLitmit);
        page.editDownlinePopup.btnSubmit.click();

        log("Verify 1. Verify Message \"Max Player Credit is invalid\" display");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.DownlineListing.MSG_INVALID_MAX_PLAYER_CREDIT, "FAILED! Incorrect max player credit is invalid");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Max Player Credit setting display correctly when create user
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any agent
     * 3. Input valid Max Player Credit and valid other information then click submit
     * 4. Login agent with the agent in step 2
     * 5. Select Agency Management > Create Downline agent
     * @expect: 1. Verify can update agent with valid max player credit
     * 2. Verify Max Player Credit display correctly as setting in First Time Deposit limit section
     */
    @TestRails(id = "679")
    @Test(groups = {"smoke_credit"})
    @Parameters({"password", "downlineLevel"})
    public void Agent_AM_Downline_Listing_Edit_Agent_679(String password, String downlineLevel) throws Exception {
        log("@title: Validate Max Player Credit setting display correctly when create user");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, downlineLevel, "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any agent");
        page.searchDownline(loginID, "", "");
        page.clickEditIcon(loginID);
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Step 3. Input valid Max Player Credit and valid other information then click submit");
        String maxPlayerCreditLitmit = "1";
        page.editDownlinePopup.balanceSection.updateCashBalance(maxPlayerCreditLitmit);
        page.editDownlinePopup.btnSubmit.click();
        String message = page.getMessageUpdate(true);

        log("Verify 1. Verify can update agent with valid max player credit");
        Assert.assertEquals(message, AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_DOWNLINE_SUCCESS, "FAILED, Success updating downline message not display");
        agentHomePage.logout();

        log("Step 4. Login agent with the agent in step 2");
        loginAgent(sosAgentURL, agentSecurityCodeURL, loginID, password, environment.getSecurityCode());

        log("5. Select Agency Management > Create Downline agent");
        CreateDownLineAgentPage createAgentPage = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());

        log("Verify 2. Verify Max Player Credit display correctly as setting in First Time Deposit limit section");
        Assert.assertEquals(Integer.toString(createAgentPage.creditBalanceSection.getMaxPlayerLitmitCredit()), maxPlayerCreditLitmit, "FAILED! Max player credit not match with the setting");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate there Cannot update if Max Player Credit exceed the limit
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     * 2. Click on Edit icon of any account
     * @expect: 1. Verify Security Code popup pormpted
     * 2. Verify page display if input valid security code
     */
    @Test(groups = {"smoke"})
    @Parameters({"level", "levelLogin"})
    public void Agent_AM_Downline_Listing_Edit_Agent_003(String level, String levelLogin) throws Exception {
        log("@title: Validate there Cannot update if Max Player Credit exceed the limit");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID, level, "ACTIVE", _brandname);
        String loginID = listAccount.get(0).getUserCode();

        log("Step 2. Click on Edit icon of any agent");
        page.searchDownline(loginID, "", "Agent");
        page.clickEditIcon(loginID);

        log("Verify 1. Verify Security Code popup prompted");
        Assert.assertTrue(page.securityPopup.isDisplayed(), "FAILED Security popup not display");

        log("Step  Enter security code");
        page.confirmSecurityCode(StringUtils.decrypt(environment.getSecurityCode()));

        log("Verify 2. Verify page display if input valid security code");
        Assert.assertEquals(page.editDownlinePopup.getTitle(), String.format("Edit %s", levelLogin), "FAILED!Page not displayed");
        log("INFO: Executed completely");
    }


}

