package agentsite.testcase.satsport.agencymanagement.downlinelisting;

import agentsite.common.AGConstant;
import agentsite.objects.agent.account.AccountInfo;
import org.testng.Assert; import baseTest.BaseCaseMerito;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import agentsite.pages.all.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.all.agentmanagement.DownLineListingPage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;

import java.util.List;

import static agentsite.common.AGConstant.HomePage.*;

public class EditDownlineAgentTest extends BaseCaseMerito {

    /**
     * @title: Validate there Cannot update if Max Player Credit exceed the limit
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *         2. Click on Edit icon of any agent
     *         3. Input Max player Credit greater than the limit
     * @expect: 1. Verify Message "Max Player Credit is invalid" display
     */
    @Test (groups = {"smoke"})
    @Parameters("level")
    public void Agent_AM_Downline_Listing_Edit_Agent_004(String level) throws Exception {
        log("@title: Validate there Cannot update if Max Player Credit exceed the limit");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,level, "ACTIVE");
        String loginID =listAccount.get(0).getLoginID();

        log("Step 2. Click on Edit icon of any agent");
        page.searchDownline(loginID,"","Agent");
        page.clickEditIcon(loginID);

        log("Step 3. Input Max player Credit greater than the limit");
        String maxPlayerCreditLitmit =String.format("%d",page.editDownlinePopup.balanceSection.getMaxPlayerLitmitCredit() +1);
        page.editDownlinePopup.balanceSection.updateCashBalance(maxPlayerCreditLitmit);
        page.editDownlinePopup.btnSubmit.click();

        log("Verify 1. Verify Message \"Max Player Credit is invalid\" display");
        Assert.assertEquals(page.lblErrorMsg.getText(), AGConstant.AgencyManagement.DownlineListing.MSG_INVALID_MAX_PLAYER_CREDIT,"FAILED! Incorrect max player credit is invalid");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Max Player Credit setting display correctly when create user
     * @pre-condition:
     *           1. Log in successfully
     * @steps: 1. Navigate Agency Management > Downline Listing
     *         2. Click on Edit icon of any agent
     *         3. Input valid Max Player Credit and valid other information then click submit
     *         4. Login agent with the agent in step 2
     *         5. Select Agency Management > Create Downline agent
     * @expect: 1. Verify can update agent with valid max player credit
     *          2. Verify Max Player Credit display correctly as setting in First Time Deposit limit section
     */
    @Test (groups = {"smoke"})
    @Parameters("password")
    public void Agent_AM_Downline_Listing_Edit_Agent_005(String password) throws Exception {
        log("@title: Validate Max Player Credit setting display correctly when create user");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, DOWNLINE_LISTING, DownLineListingPage.class);
        String userID = ProfileUtils.getProfile().getUserID();
        List<AccountInfo> listAccount = DownLineListingUtils.getDownLineUsers(userID,"AD", "ACTIVE");
        String loginID =listAccount.get(0).getLoginID();

        log("Step 2. Click on Edit icon of any agent");
        page.searchDownline(loginID,"","");
        page.clickEditIcon(loginID);

        log("Step 3. Input valid Max Player Credit and valid other information then click submit");
        String maxPlayerCreditLitmit = "1";
        page.editDownlinePopup.balanceSection.updateCashBalance(maxPlayerCreditLitmit);
        page.editDownlinePopup.btnSubmit.click();
        String message = page.getMessageUpdate(true);

        log("Verify 1. Verify can update agent with valid max player credit");
        Assert.assertEquals(message,AGConstant.AgencyManagement.DownlineListing.MSG_EDIT_DOWNLINE_SUCCESS,"FAILED, Success updating downline message not display");

        agentHomePage.logout();

        log("Step 4. Login agent with the agent in step 2");
        loginAgent(sosAgentURL,agentSecurityCodeURL,loginID,password,environment.getSecurityCode());

        log("5. Select Agency Management > Create Downline agent");
        CreateDownLineAgentPage createAgentPage = agentHomePage.clickSubMenu(AGENCY_MANAGEMENT, CREATE_DOWNLINE_AGENT, CreateDownLineAgentPage.class);

        log("Verify 2. Verify Max Player Credit display correctly as setting in First Time Deposit limit section");
        Assert.assertEquals(Integer.toString(createAgentPage.creditBalanceSection.getMaxPlayerLitmitCredit()),maxPlayerCreditLitmit,"FAILED! Max player credit not match with the setting");

        log("INFO: Executed completely");
    }

}

