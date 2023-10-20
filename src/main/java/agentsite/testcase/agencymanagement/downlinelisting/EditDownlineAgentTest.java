package agentsite.testcase.agencymanagement.downlinelisting;

import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.agentmanagement.DownLineListingPage;
import agentsite.pages.agentmanagement.EditDownLinePage;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.StringUtils;
import common.AGConstant;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Assert.assertEquals(page.downlineListing.lblErrorMsg.getText(), AGConstant.AgencyManagement.DownlineListing.MSG_INVALID_MAX_PLAYER_CREDIT, "FAILED! Incorrect max player credit is invalid");

        log("INFO: Executed completely");
    }

    @TestRails(id = "3663")
    @Test(groups = {"regression_po"})
    @Parameters({"controlBlockingAccount","downlineAccount", "passwordNonePO"})
    public void Agent_AM_Downline_Listing_Edit_Agent_3663(String controlBlockingAccount, String downlineAccount,String passwordNonePO) throws Exception {
        log("@title: Validate Portal can update products for an agent level and only the active product display for updated agent");
        log("Precondition: Log in successfully by  PO");
        log("Step 1. Navigate Agency Management > Downline Listing");
        DownLineListingPage page = agentHomePage.navigateDownlineListingPage();

        log("Step 2. Drilldown to an agent level SMA and active the below product: Exchange, Exchange Game");
        page.downlineListing.searchDownline(controlBlockingAccount, "", "");
        EditDownLinePage editDownLinePage= page.downlineListing.clickEditIcon(controlBlockingAccount);
        editDownLinePage.confirmSecurityCode(environment.getSecurityCode());
        final Map<String, Boolean> PRODUCTS1 = new HashMap<String, Boolean>() {
            {
                put("Exchange", true);
                put("Exchange Games", true);
                put("Evolution",false);
                put("CMD Sportsbook",false);
                put("Supernowa Casino",false);
                put("Live Dealer European",false);
                put("Live Dealer Asian",false);
                put("Pragmatic",false);
                put("Game Hall",false);
                put("ViVo",false);
                put("Ion",false);
                put("Sabong",false);
            }};
        editDownLinePage.updateProducts(PRODUCTS1);
        page.logout();
        log("Step 3. Login SMA level");
        loginAgent(controlBlockingAccount,passwordNonePO,true);

        log("Step 4. Navigate to the Downline listing and select any downline account");
        page = agentHomePage.navigateDownlineListingPage();
        page.downlineListing.searchDownline(downlineAccount, "", "Agent");
        editDownLinePage= page.downlineListing.clickEditIcon(downlineAccount);
        editDownLinePage.confirmSecurityCode(environment.getSecurityCode());
        List<String> lstProduct = editDownLinePage.getProductsTab();

        log("Verify: Verify only the active products tab displayed");
        Assert.assertTrue(lstProduct.containsAll(Arrays.asList("Exchange", "Exchange Games")),"Failed! Active product list is incorrect");

        log("INFO: Executed completely");
    }


}

